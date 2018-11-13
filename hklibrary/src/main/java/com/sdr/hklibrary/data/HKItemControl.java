package com.sdr.hklibrary.data;

import android.text.TextUtils;
import android.view.SurfaceView;

import com.hik.mcrsdk.rtsp.RtspClient;
import com.hikvision.vmsnetsdk.CameraInfoEx;
import com.hikvision.vmsnetsdk.RealPlayURL;
import com.hikvision.vmsnetsdk.VMSNetSDK;
import com.hikvision.vmsnetsdk.netLayer.msp.deviceInfo.DeviceInfo;
import com.sdr.hklibrary.constant.HKConstants;
import com.sdr.hklibrary.contract.HKPlayContract;
import com.sdr.lib.util.HttpUtil;

import org.MediaPlayer.PlayM4.Player;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by HyFun on 2018/11/13.
 * Email: 775183940@qq.com
 * Description:
 */

public class HKItemControl implements HKPlayContract.Presenter {
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

    private String url;
    private String userName;
    private String passWord;
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
            view.showErrorMessage("cameraID不能为空");
            return;
        }
        if (surfaceView == null) {
            view.showErrorMessage("surfaceView不能为空");
            return;
        }
        this.mCameraID = cameraID;
        this.mSurfaceView = mSurfaceView;
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
                        return null;
                    }
                })
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

}
