package com.sdr.hklibrary.data;

import android.os.SystemClock;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.hik.mcrsdk.rtsp.LiveInfo;
import com.hik.mcrsdk.rtsp.RtspClient;
import com.hik.mcrsdk.rtsp.RtspClientCallback;
import com.hikvision.vmsnetsdk.CameraInfoEx;
import com.hikvision.vmsnetsdk.RealPlayURL;
import com.hikvision.vmsnetsdk.ServInfo;
import com.hikvision.vmsnetsdk.VMSNetSDK;
import com.hikvision.vmsnetsdk.netLayer.msp.deviceInfo.DeviceInfo;
import com.orhanobut.logger.Logger;
import com.sdr.hklibrary.constant.HKConstants;
import com.sdr.hklibrary.contract.HKPlayContract;
import com.sdr.hklibrary.util.UtilSDCard;
import com.sdr.lib.rx.RxUtils;
import com.sdr.lib.util.HttpUtil;

import org.MediaPlayer.PlayM4.Player;
import org.MediaPlayer.PlayM4.PlayerCallBack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

import static com.sdr.hklibrary.constant.HKConstants.PlayStatus.LIVE_PLAY;

/**
 * Created by HyFun on 2018/11/13.
 * Email: 775183940@qq.com
 * Description:
 */

public class HKItemControl implements HKPlayContract.Presenter,
        RtspClientCallback, PlayerCallBack.PlayerDisplayCB {
    // 当前视频的播放状态
    private int currentStatus = HKConstants.PlayStatus.LIVE_INIT;

    private int position;
    private HKPlayContract.View view;

    public HKItemControl(int position, HKPlayContract.View view) {
        this.position = position;
        this.view = view;
    }

    /**
     * 创建RTSP引擎索引
     */
    private int mRtspEngineIndex = RtspClient.RTSPCLIENT_INVALIDATE_ENGINEID;

    private RealPlayURL mRealPlayURL = new RealPlayURL();
    private RtspClient mRtspHandler = RtspClient.getInstance();
    private Player mPlayerHandler = Player.getInstance();
    private int mPlayerPort = -1;
    private int mTransState = -1;

    private CameraInfoEx mCameraInfoEx;

    /**
     * 是否正在录像
     */
    private boolean mIsRecord = true;
    /**
     * 数据流
     */
    private ByteBuffer mStreamHeadDataBuffer;

    /**
     * 抓图文件
     */
    private File mPictureFile;
    /**
     * 录像文件
     */
    private File mRecordFile = null;
    /**
     * 文件输出流
     */
    private FileOutputStream mRecordFileOutputStream;


    private SurfaceView mSurfaceView;
    private String mCameraID;


    // ————————————————————Presenter——————————————————————

    /**
     * 开始预览
     *
     * @param cameraID
     * @param surfaceView
     */
    @Override
    public void startPlay(String cameraID, SurfaceView surfaceView) {
        if (TextUtils.isEmpty(cameraID)) {
            view.onPlayMsg(position, HKConstants.PlayLive.PLAY_CAMERA_INFO_ID_NULL, "cameraInfo中的id为空");
            return;
        }
        if (surfaceView == null) {
            view.onPlayMsg(position, HKConstants.PlayLive.PLAY_SURFACEVIEW_NULL, "surfaceView为空");
            return;
        }
        this.mCameraID = cameraID;
        this.mSurfaceView = surfaceView;
        // 显示加载框
        view.showLoadingDialog("正在加载视频中...");

        Observable.just(0)
                .flatMap(new Function<Integer, ObservableSource<Boolean>>() {
                    @Override
                    public ObservableSource<Boolean> apply(Integer integer) throws Exception {
                        String domainUrl = HttpUtil.clearDomainAddress(HKDataInfo.getInstance().getUrl());

                        CameraInfoEx cameraInfoEx = new CameraInfoEx();
                        cameraInfoEx.setId(cameraID);
                        boolean getCameraDetailInfoResult = VMSNetSDK.getInstance().getCameraInfoEx(domainUrl, HKDataInfo.getInstance().getServInfo().getSessionID(),
                                cameraID, cameraInfoEx);
                        String mDeviceID = cameraInfoEx.getDeviceId();
                        DeviceInfo deviceInfo = new DeviceInfo();
                        boolean getDeviceInfoResult = VMSNetSDK.getInstance().getDeviceInfo(domainUrl, HKDataInfo.getInstance().getServInfo().getSessionID(),
                                mDeviceID, deviceInfo);
                        if (!getDeviceInfoResult || null == deviceInfo || TextUtils.isEmpty(deviceInfo.getLoginName())
                                || TextUtils.isEmpty(deviceInfo.getLoginPsw())) {
                            deviceInfo.setLoginName(HKDataInfo.getInstance().getUserName());
                            deviceInfo.setLoginPsw(HKDataInfo.getInstance().getPassWord());
                        }
                        String mName = deviceInfo.getLoginName();
                        String mPassword = deviceInfo.getLoginPsw();
                        if (TextUtils.isEmpty(mName) || TextUtils.isEmpty(mPassword)) {
                            // 说明获取设备详细信息失败
                            return Observable.error(new Exception("获取设备详细信息失败!"));
                        }
                        String url = getPlayUrl(HKConstants.PlayLive.MAIN_STREAM, cameraInfoEx, deviceInfo);
                        String userName = mName;
                        String passWord = mPassword;

                        HKItemControl.this.mCameraInfoEx = cameraInfoEx;
                        // 开始取流进行播放
                        mRtspEngineIndex = mRtspHandler.createRtspClientEngine(HKItemControl.this, RtspClient.RTPRTSP_TRANSMODE);
                        if (mRtspEngineIndex < 0) {
                            int errorCode = mRtspHandler.getLastError();
                            Logger.t(HKConstants.HK_TAG).e("startRtsp():: errorCode is R" + errorCode);
                            return Observable.error(new Exception("startRtsp():: errorCode is R" + errorCode));
                        }
                        Logger.t(HKConstants.HK_TAG).d("mRtspEngineIndex: " + mRtspEngineIndex + "mUrl: " + url + "mDeviceUserName: " + userName +
                                "mDevicePassword: " + passWord);

                        boolean ret = mRtspHandler.startRtspProc(mRtspEngineIndex, url, userName, passWord);
                        if (!ret) {
                            int errorCode = mRtspHandler.getLastError();
                            return Observable.error(new Exception("startRtsp():: errorCode is R" + errorCode));
                        }
                        return observer -> {
                            observer.onNext(ret);
                            observer.onComplete();
                        };
                    }
                })
                .compose(RxUtils.io_main())
                .subscribe(ret -> {
                    view.onPlayMsg(position, HKConstants.PlayLive.PLAY_LIVE_RTSP_SUCCESS, "取流成功");
                }, error -> {
                    view.onPlayMsg(position, HKConstants.PlayLive.PLAY_LIVE_RTSP_FAIL, error.getMessage());
                    view.hideLoadingDialog();
                }, () -> {
                    view.hideLoadingDialog();
                });
    }

    @Override
    public void stopPlay() {
        if (HKConstants.PlayStatus.LIVE_INIT == currentStatus) {
            return;
        }
        view.showLoadingDialog("正在关闭视频中...");
        Observable.just(0)
                .flatMap(new Function<Integer, ObservableSource<Boolean>>() {
                    @Override
                    public ObservableSource<Boolean> apply(Integer integer) throws Exception {
                        try {
                            stopPlaySyn();
                            return observer -> {
                                observer.onNext(true);
                                observer.onComplete();
                            };
                        } catch (Exception e) {
                            return Observable.error(e);
                        }
                    }
                })
                .compose(RxUtils.io_main())
                .subscribe(ret -> {
                    view.onPlayMsg(position, HKConstants.PlayLive.PLAY_LIVE_STOP_SUCCESS, "停止播放成功");
                }, error -> {
                    view.hideLoadingDialog();
                }, () -> {
                    view.hideLoadingDialog();
                });
    }

    // 同步停止
    @Override
    public void stopPlaySyn() {
        if (mIsRecord) {
            stopRecord();
            mIsRecord = false;
        }
        stopRtsp();
        closePlayer();
        currentStatus = HKConstants.PlayStatus.LIVE_INIT;
    }

    @Override
    public void sendCtrlCmd(int gestureID) {
        view.showLoadingDialog("正在执行命令");
        Observable.just(0)
                .flatMap(new Function<Integer, ObservableSource<Boolean>>() {
                    @Override
                    public ObservableSource<Boolean> apply(Integer integer) throws Exception {
                        String sessionID = HKDataInfo.getInstance().getServInfo().getSessionID();
                        // 云台控制速度 取值范围(1-10)
                        int speed = 5;
                        // 发送控制命令
                        boolean ret =
                                VMSNetSDK.getInstance().sendStartPTZCmd(mCameraInfoEx.getAcsIP(),
                                        mCameraInfoEx.getAcsPort(),
                                        sessionID,
                                        mCameraID,
                                        gestureID,
                                        speed,
                                        600, mCameraInfoEx.getCascadeFlag() + "");
                        Logger.d(HKConstants.HK_TAG + "sendStartPTZCmd ret:" + ret);
                        if (!ret) {
                            return Observable.error(new Exception("指令执行失败"));
                        }
                        return observer -> {
                            observer.onNext(ret);
                            observer.onComplete();
                        };
                    }
                })
                .compose(RxUtils.io_main())
                .subscribe(ret -> {
                    view.hideLoadingDialog();
                    view.onPlayMsg(position, HKConstants.PlayLive.SEND_CTRL_CMD_SUCCESS, "指令执行成功");
                }, error -> {
                    view.hideLoadingDialog();
                    view.onPlayMsg(position, HKConstants.PlayLive.SEND_CTRL_CMD_FAILED, error.getMessage());
                });
    }

    @Override
    public void stopControl() {
        view.showLoadingDialog("正在停止指令");
        Observable.just(0)
                .flatMap(new Function<Integer, ObservableSource<Boolean>>() {
                    @Override
                    public ObservableSource<Boolean> apply(Integer integer) throws Exception {
                        String sessionID = HKDataInfo.getInstance().getServInfo().getSessionID();
                        boolean ret =
                                VMSNetSDK.getInstance().sendStopPTZCmd(mCameraInfoEx.getAcsIP(),
                                        mCameraInfoEx.getAcsPort(),
                                        sessionID,
                                        mCameraID, mCameraInfoEx.getCascadeFlag() + "");
                        Logger.d(HKConstants.HK_TAG + "stopPtzCmd sent,ret:" + ret);
                        if (!ret) {
                            return Observable.error(new Exception("停止控制失败"));
                        }
                        return observer -> {
                            observer.onNext(ret);
                            observer.onComplete();
                        };
                    }
                }).compose(RxUtils.io_main())
                .subscribe(ret -> {
                    view.onPlayMsg(position, HKConstants.PlayLive.STOP_CONTROL_SUCCESS, "停止控制成功");
                    view.hideLoadingDialog();
                }, error -> {
                    view.onPlayMsg(position, HKConstants.PlayLive.STOP_CONTROL_FAILED, "停止控制失败");
                    view.hideLoadingDialog();
                });
    }


    /**
     * 抓拍 void
     *
     * @param filePath 存放文件路径
     * @param picName  抓拍时文件的名称
     * @return true-抓拍成功，false-抓拍失败
     * @since V1.0
     */
    public boolean capture(String filePath, String picName) {
        if (!UtilSDCard.isSDCardUsable()) {
            return false;
        }

        if (LIVE_PLAY != currentStatus) {
            view.onPlayMsg(position, HKConstants.PlayLive.CAPTURE_FAILED, "没有检测到正在播放");
            return false;
        }

        byte[] pictureBuffer = getPictureOnJPEG();
        if (null == pictureBuffer || pictureBuffer.length == 0) {
            return false;
        }

        boolean ret = createPictureFile(filePath, picName);
        if (!ret) {
            pictureBuffer = null;
            return false;
        }

        ret = writePictureToFile(pictureBuffer, pictureBuffer.length);
        if (!ret) {
            pictureBuffer = null;
            removePictureFile();
            view.onPlayMsg(position, HKConstants.PlayLive.CAPTURE_FAILED, "抓图失败");
            return false;
        }
        view.onPlayMsg(position, HKConstants.PlayLive.CAPTURE_SUCCESS, mPictureFile.getAbsolutePath());
        return true;
    }

    /**
     * 获取JPEG图片数据
     *
     * @return JPEG图片的数据.
     * @since V1.0
     */
    private byte[] getPictureOnJPEG() {
        if (null == mPlayerHandler) {
            return new byte[0];
        }

        if (-1 == mPlayerPort) {
            return new byte[0];
        }

        int picSize = getPictureSize();
        if (picSize <= 0) {
            return new byte[0];
        }

        byte[] pictureBuffer = null;
        try {
            pictureBuffer = new byte[picSize];
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            pictureBuffer = null;
            return new byte[0];
        }

        Player.MPInteger jpgSize = new Player.MPInteger();

        boolean ret = mPlayerHandler.getJPEG(mPlayerPort, pictureBuffer, picSize, jpgSize);
        if (!ret) {
            return new byte[0];
        }

        int jpegSize = jpgSize.value;
        if (jpegSize <= 0) {
            pictureBuffer = null;
            return new byte[0];
        }

        ByteBuffer jpgBuffer = ByteBuffer.wrap(pictureBuffer, 0, jpegSize);
        if (null == jpgBuffer) {
            pictureBuffer = null;
            return new byte[0];
        }

        return jpgBuffer.array();
    }

    /**
     * 获取JPEG图片大小
     *
     * @return JPEG图片的大小.
     * @since V1.0
     */
    private int getPictureSize() {
        Player.MPInteger width = new Player.MPInteger();
        Player.MPInteger height = new Player.MPInteger();
        boolean ret = mPlayerHandler.getPictureSize(mPlayerPort, width, height);
        if (!ret) {
            return 0;
        }
        int pictureSize = width.value * height.value * 3;
        return pictureSize;
    }


    /**
     * 创建图片文件
     *
     * @param path     图片路径
     * @param fileName 图片名字
     * @return true - 图片创建成功 or false - 图片创建失败
     * @since V1.0
     */
    private boolean createPictureFile(String path, String fileName) {
        if (null == path || null == fileName || path.equals("") || fileName.equals("")) {
            return false;
        }

        String dirPath = createFileDir(path);
        if (null == dirPath || dirPath.equals("")) {
            return false;
        }

        try {
            mPictureFile = new File(dirPath + File.separator + fileName);
            if ((null != mPictureFile) && (!mPictureFile.exists())) {
                mPictureFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
            mPictureFile = null;
            return false;
        }
        return true;
    }

    /**
     * 创建文件夹
     *
     * @param path 文件路径
     * @return 文件夹路径
     * @since V1.0
     */
    private String createFileDir(String path) {
        if (null == path || path.equals("")) {
            return "";
        }
        File tempFile = null;
        try {
            tempFile = new File(path);
            if ((null != tempFile) && (!tempFile.exists())) {
                tempFile.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return tempFile.getAbsolutePath();
    }

    /**
     * 抓拍图片写到SDCard
     *
     * @param picData 图片数据
     * @param length  图片数据长度
     * @since V1.0
     */
    private boolean writePictureToFile(byte[] picData, int length) {
        if (null == picData || length <= 0) {
            return false;
        }

        if (null == mPictureFile) {
            return false;
        }

        FileOutputStream fOut = null;
        try {
            if (!mPictureFile.exists()) {
                mPictureFile.createNewFile();
            }
            fOut = new FileOutputStream(mPictureFile);
            fOut.write(picData, 0, length);
            fOut.flush();
            fOut.close();
            fOut = null;
        } catch (Exception e) {
            e.printStackTrace();
            fOut = null;
            mPictureFile.delete();
            mPictureFile = null;
            return false;
        }
        return true;
    }

    /**
     * 删除图片文件
     *
     * @since V1.0
     */
    private void removePictureFile() {
        try {
            if (null == mPictureFile) {
                return;
            }
            mPictureFile.delete();
            mPictureFile = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 启动录像方法
     *
     * @param filePath 录像文件路径
     * @param fileName 录像文件名称
     * @return true-启动录像成功，false-启动录像失败
     * @since V1.0
     */
    public boolean startRecord(String filePath, String fileName) {

        if (LIVE_PLAY != currentStatus) {
            view.onPlayMsg(position, HKConstants.PlayLive.RECORD_FAILED, "非播放状态不能录像");
            return false;
        }

        boolean ret = createRecordFile(filePath, fileName);
        if (!ret) {
            view.onPlayMsg(position, HKConstants.PlayLive.RECORD_FAILED, "创建录像文件失败");
            return false;
        }

        ret = writeStreamHead(mRecordFile);
        if (!ret) {
            view.onPlayMsg(position, HKConstants.PlayLive.RECORD_FAILED, "写文件失败");
            removeRecordFile();
            return false;
        }

        mIsRecord = true;
        view.onPlayMsg(position, HKConstants.PlayLive.RECORD_START, "开始录像");
        return true;
    }


    /**
     * 创建录像文件
     *
     * @param path     文件路径
     * @param fileName 文件名
     * @return true - 创建成功 or false - 创建失败
     * @since V1.0
     */
    private boolean createRecordFile(String path, String fileName) {
        if (null == path || path.equals("") || null == fileName || fileName.equals("")) {
            return false;
        }

        try {
            mRecordFile = new File(path + File.separator + fileName);
            if ((null != mRecordFile) && (!mRecordFile.exists())) {
                mRecordFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();

            mRecordFile = null;
            return false;
        }

        return true;
    }


    /**
     * 写流头文件
     *
     * @param file 写入的文件
     * @return true - 写入头文件成功. false - 写入头文件失败.
     * @since V1.0
     */
    private boolean writeStreamHead(File file) {
        if (null == file || null == mStreamHeadDataBuffer) {
            return false;
        }

        byte[] tempByte = mStreamHeadDataBuffer.array();
        if (null == tempByte) {
            return false;
        }
        try {
            if (null == mRecordFileOutputStream) {
                mRecordFileOutputStream = new FileOutputStream(file);
            }
            mRecordFileOutputStream.write(tempByte, 0, tempByte.length);
        } catch (Exception e) {
            e.printStackTrace();
            if (mRecordFileOutputStream != null) {
                try {
                    mRecordFileOutputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            mRecordFileOutputStream = null;
            mStreamHeadDataBuffer = null;
            tempByte = null;
            return false;
        }

        return true;
    }

    /**
     * 删除录像文件
     *
     * @since V1.0
     */
    private void removeRecordFile() {
        try {
            if (null == mRecordFile) {
                return;
            }
            mRecordFile.delete();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mRecordFile = null;
        }
    }


    /**
     * 开启音频
     *
     * @return boolean
     * @since V1.0
     */
    public boolean startAudio() {
        if (LIVE_PLAY != currentStatus) {
            view.onPlayMsg(position, HKConstants.PlayLive.AUDIO_FAILED, "非播放状态不能开启音频");
            return false;
        }

        if (null == mPlayerHandler) {
            return false;
        }

        boolean ret = mPlayerHandler.playSound(mPlayerPort);
        if (!ret) {
            view.onPlayMsg(position, HKConstants.PlayLive.AUDIO_FAILED, "开启声音失败");
            return false;
        }
        view.onPlayMsg(position, HKConstants.PlayLive.AUDIO_SUCCESS, "开启声音成功");
        return true;
    }

    /**
     * 关闭音频
     *
     * @return boolean
     * @since V1.0
     */
    public boolean stopAudio() {
        if (LIVE_PLAY != currentStatus) {
            view.onPlayMsg(position, HKConstants.PlayLive.AUDIO_FAILED, "非播放状态不能关闭音频");
            return false;
        }

        if (null == mPlayerHandler) {
            return false;
        }

        boolean ret = mPlayerHandler.stopSound();
        if (!ret) {
            return false;
        }
        view.onPlayMsg(position, HKConstants.PlayLive.AUDIO_CLOSE_SUCCESS, "关闭声音成功");
        return true;
    }
    // ————————————————————接口——————————————————————


    /**
     * 播放流量
     */
    private long mStreamRate = 0;

    @Override
    public void onDataCallBack(int handle, int dataType, byte[] data, int length, int timeStamp, int packetNo, int useId) {
        if (mStreamRate + length >= Long.MAX_VALUE) {
            mStreamRate = 0;
        }
        mStreamRate += length;

        switch (dataType) {
            case RtspClient.DATATYPE_HEADER:
                boolean ret = processStreamHeader(data, length);
                if (!ret) {
                    view.onPlayMsg(position, HKConstants.PlayLive.PLAY_LIVE_FAILED, "启动播放失败");
                } else {
                    Logger.t(HKConstants.HK_TAG).d("MediaPlayer Header success!");
                }
                break;
            default:
                processStreamData(data, length);
                break;
        }
        processRecordData(dataType, data, length);
    }

    private int connectNum = 0;

    /*
         * handle - - 引擎id opt - -回调消息，包括：RTSPCLIENT_MSG_PLAYBACK_FINISH,RTSPCLIENT_MSG_BUFFER_OVERFLOW
         * ,RTSPCLIENT_MSG_CONNECTION_EXCEPTION 三种 param1 - - 保留参数 param2 - - 保留参数 useId - - 用户数据，默认就是引擎id与handle相同
         */
    @Override
    public void onMessageCallBack(int handle, int opt, int param1, int param2, int useId) {

        if (opt == RtspClient.RTSPCLIENT_MSG_CONNECTION_EXCEPTION) {
            stopPlay();
            Logger.e(HKConstants.HK_TAG + "onMessageCallBack():: rtsp connection exception");
            if (connectNum > 3) {
                Logger.e(HKConstants.HK_TAG + "onMessageCallBack():: rtsp connection more than three times");
                connectNum = 0;
            } else {
                startPlay(mCameraID, mSurfaceView);
                connectNum++;
            }
        }
    }


    @Override
    public void onDisplay(int i, byte[] bytes, int i1, int i2, int i3, int i4, int i5, int i6) {
        if (LIVE_PLAY != currentStatus) {
            currentStatus = LIVE_PLAY;
            view.onPlayMsg(position, HKConstants.PlayLive.PLAY_LIVE_SUCCESS, "播放成功");
        }
    }


    // ————————————————————GET——————————————————————

    /**
     * 获取当前的播放状态
     *
     * @return
     */
    public int getCurrentStatus() {
        return currentStatus;
    }

    /**
     * 获取当前位置
     *
     * @return
     */
    public int getPosition() {
        return position;
    }

    /**
     * 获取 cameraID
     *
     * @return
     */
    public String getCameraID() {
        return mCameraID;
    }

    // ————————————————————PRIVATE——————————————————————

    /**
     * 播放的时候使用 获取播放url
     *
     * @param streamType
     * @param cameraInfoEx
     * @param deviceInfo
     * @return
     */
    private final String getPlayUrl(int streamType, CameraInfoEx cameraInfoEx, DeviceInfo deviceInfo) {
        String url = "";

        if (mRealPlayURL == null) {
            return null;
        }
        ServInfo mServInfo = HKDataInfo.getInstance().getServInfo();
        String mToken = null;
        // 获取播放Token
        if (mServInfo.isTokenVerify()) {
            mToken = VMSNetSDK.getInstance().getPlayToken(mServInfo.getSessionID());
            Logger.t(HKConstants.HK_TAG).d("mToken is :" + mToken);
        }
        Logger.t(HKConstants.HK_TAG).d("generateLiveUrl MagStreamSerAddr:" + mServInfo.getMagServer().getMagStreamSerAddr());
        Logger.t(HKConstants.HK_TAG).d("generateLiveUrl MagStreamSerPort:" + mServInfo.getMagServer().getMagStreamSerPort());
        Logger.t(HKConstants.HK_TAG).d("generateLiveUrl cameraId:" + cameraInfoEx.getId());
        Logger.t(HKConstants.HK_TAG).d("generateLiveUrl token:" + mToken);
        Logger.t(HKConstants.HK_TAG).d("generateLiveUrl streamType:" + streamType);
        Logger.t(HKConstants.HK_TAG).d("generateLiveUrl appNetId:" + mServInfo.getAppNetId());
        Logger.t(HKConstants.HK_TAG).d("generateLiveUrl deviceNetID:" + cameraInfoEx.getDeviceNetId());
        Logger.t(HKConstants.HK_TAG).d("generateLiveUrl userAuthority:" + mServInfo.getUserAuthority());
        Logger.t(HKConstants.HK_TAG).d("generateLiveUrl cascadeFlag:" + cameraInfoEx.getCascadeFlag());
        Logger.t(HKConstants.HK_TAG).d("generateLiveUrl internet:" + mServInfo.isInternet());

        LiveInfo liveInfo = new LiveInfo();
        liveInfo.setMagIp(mServInfo.getMagServer().getMagStreamSerAddr());
        liveInfo.setMagPort(mServInfo.getMagServer().getMagStreamSerPort());
        liveInfo.setCameraIndexCode(cameraInfoEx.getId());
        if (mServInfo.isTokenVerify()) {
            liveInfo.setToken(mToken);
        } else {
            liveInfo.setToken(null);
        }

        // 转码不区分主子码流
        liveInfo.setStreamType(streamType);
        liveInfo.setMcuNetID(mServInfo.getAppNetId());
        liveInfo.setDeviceNetID(cameraInfoEx.getDeviceNetId());
        liveInfo.setiPriority(mServInfo.getUserAuthority());
        liveInfo.setCascadeFlag(cameraInfoEx.getCascadeFlag());

        if (deviceInfo != null) {
            if (cameraInfoEx.getCascadeFlag() == LiveInfo.CASCADE_TYPE_YES) {
                deviceInfo.setLoginName("admin");
                deviceInfo.setLoginPsw("12345");
            }
        }

        if (mServInfo.isInternet()) {
            liveInfo.setIsInternet(LiveInfo.NETWORK_TYPE_INTERNET);
            // 获取不转码地址
            liveInfo.setbTranscode(false);
            mRealPlayURL.setUrl1(mRtspHandler.generateLiveUrl(liveInfo));

            // 获取转码地址
            // 使用默认转码参数cif 128 15 h264 ps
            liveInfo.setbTranscode(true);
            mRealPlayURL.setUrl2(mRtspHandler.generateLiveUrl(liveInfo));
        } else {
            liveInfo.setIsInternet(LiveInfo.NETWORK_TYPE_LOCAL);
            liveInfo.setbTranscode(false);
            // 内网不转码
            mRealPlayURL.setUrl1(mRtspHandler.generateLiveUrl(liveInfo));
            mRealPlayURL.setUrl2("");
        }

        Logger.t(HKConstants.HK_TAG).d("url1:" + mRealPlayURL.getUrl1());
        Logger.t(HKConstants.HK_TAG).d("url2:" + mRealPlayURL.getUrl2());

        url = mRealPlayURL.getUrl1();
        if (streamType == 2 && mRealPlayURL.getUrl2() != null && mRealPlayURL.getUrl2().length() > 0) {
            url = mRealPlayURL.getUrl2();
        }
        Logger.t(HKConstants.HK_TAG).i("mRTSPUrl" + url);
        return url;
    }

    /**
     * 处理数据流头
     *
     * @param data
     * @param len
     * @return boolean
     * @since V1.0
     */
    private boolean processStreamHeader(byte[] data, int len) {
        if (-1 != mPlayerPort) {
            closePlayer();
        }
        boolean ret = startPlayer(data, len);
        return ret;
    }

    /**
     * 开启播放库方法
     *
     * @param data
     * @param len
     * @return boolean
     * @since V1.0
     */
    private boolean startPlayer(byte[] data, int len) {
        if (null == data || 0 == len) {
            Logger.e(HKConstants.HK_TAG + "startPlayer() Stream data error data is null or len is 0");
            return false;
        }

        if (null == mPlayerHandler) {
            Logger.e(HKConstants.HK_TAG + "startPlayer(): mPlayerHandler is null!");
            return false;
        }

        mPlayerPort = mPlayerHandler.getPort();
        if (-1 == mPlayerPort) {
            Logger.e(HKConstants.HK_TAG + "startPlayer(): mPlayerPort is -1");
            return false;
        }

        boolean ret = mPlayerHandler.setStreamOpenMode(mPlayerPort, Player.STREAM_REALTIME);
        if (!ret) {
            int tempErrorCode = mPlayerHandler.getLastError(mPlayerPort);
            mPlayerHandler.freePort(mPlayerPort);
            mPlayerPort = -1;
            Logger.e(HKConstants.HK_TAG + "startPlayer(): Player setStreamOpenMode failed! errorCord is P" + tempErrorCode);
            return false;
        }

        ret = mPlayerHandler.openStream(mPlayerPort, data, len, 2 * 1024 * 1024);
        if (!ret) {
            Logger.e(HKConstants.HK_TAG + "startPlayer() mPlayerHandle.openStream failed!" + "Port: " + mPlayerPort
                    + "ErrorCode is P " + mPlayerHandler.getLastError(mPlayerPort));
            return false;
        }

        ret = mPlayerHandler.setDisplayCB(mPlayerPort, this);
        if (!ret) {
            Logger.e(HKConstants.HK_TAG + "startPlayer() mPlayerHandle.setDisplayCB() failed errorCode is P"
                    + mPlayerHandler.getLastError(mPlayerPort));
            return false;
        }

        if (null == mSurfaceView) {
            Logger.e(HKConstants.HK_TAG + "startPlayer():: mSurfaceView is null");
            return false;
        }

        SurfaceHolder surfaceHolder = mSurfaceView.getHolder();
        if (null == surfaceHolder) {
            Logger.e(HKConstants.HK_TAG + "startPlayer() mPlayer mainSurface is null!");
            return false;
        }

        ret = mPlayerHandler.play(mPlayerPort, surfaceHolder);
        if (!ret) {
            Logger.e(HKConstants.HK_TAG + "startPlayer() mPlayerHandle.play failed!" + "Port: " + mPlayerPort + "PlayView Surface: "
                    + surfaceHolder + "errorCode is P" + mPlayerHandler.getLastError(mPlayerPort));
            return false;
        }
        return true;
    }

    /**
     * 向播放库塞数据
     *
     * @param data
     * @param len  void
     * @since V1.0
     */
    private void processStreamData(byte[] data, int len) {
        if (null == data || 0 == len) {
            Logger.e(HKConstants.HK_TAG + "processStreamData() Stream data is null or len is 0");
            return;
        }
        if (null != mPlayerHandler) {
            boolean ret = mPlayerHandler.inputData(mPlayerPort, data, len);
            if (!ret) {
                SystemClock.sleep(10);
            }
        }
    }

    /**
     * 录像数据处理
     *
     * @param dataType   数据流
     * @param dataBuffer 数据缓存
     * @param dataLength 数据长度
     */
    private void processRecordData(int dataType, byte[] dataBuffer, int dataLength) {
        if (null == dataBuffer || dataLength == 0) {
            return;
        }
        if (mIsRecord) {
            if (RtspClient.DATATYPE_HEADER == dataType) {
                mStreamHeadDataBuffer = ByteBuffer.allocate(dataLength);
                for (int i = 0; i < dataLength; i++) {
                    mStreamHeadDataBuffer.put(dataBuffer[i]);
                }
            } else if (RtspClient.DATATYPE_STREAM == dataType) {
                writeStreamData(dataBuffer, dataLength);
            }
        } else {
            if (-1 != mTransState) {
                mTransState = -1;
            }
        }
    }

    /**
     * 录像数据写到文件
     *
     * @param recordData 录像数据
     * @param length     录像数据长度
     * @since V1.0
     */
    private boolean writeStreamData(byte[] recordData, int length) {
        if (null == recordData || length <= 0) {
            return false;
        }

        if (null == mRecordFile) {
            return false;
        }

        try {
            if (null == mRecordFileOutputStream) {
                mRecordFileOutputStream = new FileOutputStream(mRecordFile);
            }
            mRecordFileOutputStream.write(recordData, 0, length);
            Logger.e(HKConstants.HK_TAG + "writeStreamData() success");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    /**
     * 停止录像 void
     *
     * @since V1.0
     */
    public void stopRecord() {
        if (!mIsRecord) {
            return;
        }
        mIsRecord = false;
        stopWriteStreamData();
    }

    /**
     * 停止写入数据流
     *
     * @since V1.0
     */
    private void stopWriteStreamData() {
        if (null == mRecordFileOutputStream) {
            return;
        }

        try {
            mRecordFileOutputStream.flush();
            mRecordFileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            mRecordFileOutputStream = null;
            mRecordFile = null;
            mStreamRate = 0;
        }
        view.onPlayMsg(position, HKConstants.PlayLive.RECORD_SUCCESS, mRecordFile.getAbsolutePath());
    }


    /**
     * 停止RTSP
     *
     * @since V1.0
     */
    private void stopRtsp() {
        if (null != mRtspHandler) {
            if (RtspClient.RTSPCLIENT_INVALIDATE_ENGINEID != mRtspEngineIndex) {
                mRtspHandler.stopRtspProc(mRtspEngineIndex);
                mRtspHandler.releaseRtspClientEngineer(mRtspEngineIndex);
                mRtspEngineIndex = RtspClient.RTSPCLIENT_INVALIDATE_ENGINEID;
            }
        }
    }

    /**
     * 关闭播放库 void
     *
     * @since V1.0
     */
    private void closePlayer() {
        if (null != mPlayerHandler) {
            if (-1 != mPlayerPort) {
                boolean ret = mPlayerHandler.stop(mPlayerPort);
                if (!ret) {
                    Logger.e(HKConstants.HK_TAG + "closePlayer(): Player stop  failed!  errorCode is P"
                            + mPlayerHandler.getLastError(mPlayerPort));
                }

                ret = mPlayerHandler.closeStream(mPlayerPort);
                if (!ret) {
                    Logger.e(HKConstants.HK_TAG + "closePlayer(): Player closeStream  failed!");
                }
                ret = mPlayerHandler.freePort(mPlayerPort);
                if (!ret) {
                    Logger.e(HKConstants.HK_TAG + "closePlayer(): Player freePort  failed!");
                }
                mPlayerPort = -1;
            }
        }
    }
}
