//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hikvision.vmsnetsdk;

import android.os.Environment;
import android.text.TextUtils;

import com.hikvision.vmsnetsdk.bygone.BG_VMSNetSDK;
import com.hikvision.vmsnetsdk.intf.IVMSNetSDK;
import com.hikvision.vmsnetsdk.intf.IVMSNetSDKGis;
import com.hikvision.vmsnetsdk.intf.IVMSNetSDKMsg;
import com.hikvision.vmsnetsdk.intf.IVMSNetSDKResource;
import com.hikvision.vmsnetsdk.netLayer.mag.MAGServer;
import com.hikvision.vmsnetsdk.netLayer.mag.MagBackState;
import com.hikvision.vmsnetsdk.netLayer.mag.MagRequest;
import com.hikvision.vmsnetsdk.netLayer.mag.MagResponse;
import com.hikvision.vmsnetsdk.netLayer.mag.ability.MagAbilityRequest;
import com.hikvision.vmsnetsdk.netLayer.mag.ability.MagAbilityResponse;
import com.hikvision.vmsnetsdk.netLayer.mag.kms.GetKmsRequest;
import com.hikvision.vmsnetsdk.netLayer.mag.kms.GetKmsResponse;
import com.hikvision.vmsnetsdk.netLayer.mag.kms.KmsInfo;
import com.hikvision.vmsnetsdk.netLayer.mag.logout.LogoutMagRequest;
import com.hikvision.vmsnetsdk.netLayer.mag.logout.LogoutMagResponse;
import com.hikvision.vmsnetsdk.netLayer.mag.ptz.PtzBaseParam;
import com.hikvision.vmsnetsdk.netLayer.mag.ptz.PtzCommandRequest;
import com.hikvision.vmsnetsdk.netLayer.mag.ptz.PtzCommandResponse;
import com.hikvision.vmsnetsdk.netLayer.mag.ptz.PtzEnlargeParam;
import com.hikvision.vmsnetsdk.netLayer.mag.queryNcg.QueryNcgRequest;
import com.hikvision.vmsnetsdk.netLayer.mag.queryNcg.QueryNcgResponse;
import com.hikvision.vmsnetsdk.netLayer.mag.queryVrm.QueryVrmRequest;
import com.hikvision.vmsnetsdk.netLayer.mag.queryVrm.QueryVrmResponse;
import com.hikvision.vmsnetsdk.netLayer.mag.register.RegisterMagRequest;
import com.hikvision.vmsnetsdk.netLayer.mag.register.RegisterMagResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.MspBackState;
import com.hikvision.vmsnetsdk.netLayer.msp.MspRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.MspResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.MspServer;
import com.hikvision.vmsnetsdk.netLayer.msp.alarmInfo.AlarmInfo;
import com.hikvision.vmsnetsdk.netLayer.msp.alarmInfo.AlarmInfoRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.alarmInfo.AlarmInfoResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.bonetInfo.BonetInfo;
import com.hikvision.vmsnetsdk.netLayer.msp.bonetInfo.BonetInfoRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.bonetInfo.BonetInfoResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.cameraInfo.CameraInfoRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.cameraInfo.CameraInfoResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.cameraList.Camera;
import com.hikvision.vmsnetsdk.netLayer.msp.cameraList.CameraListRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.cameraList.CameraListResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.checkMsg.CheckMsgRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.checkMsg.CheckMsgResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.checkupdate.LatestAppResult;
import com.hikvision.vmsnetsdk.netLayer.msp.checkupdate.NetCallback;
import com.hikvision.vmsnetsdk.netLayer.msp.checkupdate.RequestResult;
import com.hikvision.vmsnetsdk.netLayer.msp.collectCamera.CollectCameraRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.collectCamera.CollectCameraResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.collectedCameraList.CollectedCameraListRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.collectedCameraList.CollectedCameraListResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.controlUnitList.ControlUnit;
import com.hikvision.vmsnetsdk.netLayer.msp.controlUnitList.ControlUnitListRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.controlUnitList.ControlUnitListResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.deleteMsg.DeleteMsgRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.deleteMsg.DeleteMsgRequest.MODE;
import com.hikvision.vmsnetsdk.netLayer.msp.deleteMsg.DeleteMsgResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.deviceGps.DeviceGpsParam;
import com.hikvision.vmsnetsdk.netLayer.msp.deviceGps.DeviceGpsRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.deviceGps.DeviceGpsResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.deviceGps.GISTrackPoint;
import com.hikvision.vmsnetsdk.netLayer.msp.deviceGps.GpsTrackListInfo;
import com.hikvision.vmsnetsdk.netLayer.msp.deviceInfo.DeviceInfo;
import com.hikvision.vmsnetsdk.netLayer.msp.deviceInfo.DeviceInfoRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.deviceInfo.DeviceInfoResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.gisInitInfo.GisInitInfoRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.gisInitInfo.GisInitInfoResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.gisPoint.GISPointInfo;
import com.hikvision.vmsnetsdk.netLayer.msp.gisPoint.all.AllGisPointRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.gisPoint.all.AllGisPointResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.gisPoint.local.BaseGisSearchParam;
import com.hikvision.vmsnetsdk.netLayer.msp.gisPoint.local.InRectangleGisSearchParam;
import com.hikvision.vmsnetsdk.netLayer.msp.gisPoint.local.InRoundGisSearchParam;
import com.hikvision.vmsnetsdk.netLayer.msp.gisPoint.local.LocalGisPointRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.gisPoint.local.LocalGisPointResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.gisPoint.local.SingleGisPointRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.keeplive.KeepliveRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.keeplive.KeepliveResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.login.AutoLoginExcuteRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.login.AutoLoginRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.login.AutoLoginResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.login.LoginRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.login.LoginResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.login.ModifyRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.logout.LogoutRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.logout.LogoutResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.modifyGISInfo.ModifyGISInfoRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.modifyGISInfo.ModifyGISInfoResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.msg.Msg;
import com.hikvision.vmsnetsdk.netLayer.msp.msg.detail.MsgDetail;
import com.hikvision.vmsnetsdk.netLayer.msp.msg.detail.MsgDetailRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.msg.detail.MsgDetailResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.msg.list.detail.history.HistoryMsgDetailListRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.msg.list.detail.history.HistoryMsgDetailListResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.msg.list.detail.latest.LatestMsgDetailListRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.msg.list.detail.latest.LatestMsgDetailListResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.msg.list.history.HistoryMsgListRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.msg.list.history.HistoryMsgListResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.msg.list.latest.LatestMsgListRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.msg.list.latest.LatestMsgListResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.playToken.PlayTokenRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.playToken.PlayTokenResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.pushServerInfo.PushServerInfoRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.pushServerInfo.PushServerInfoResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.unreadMsgCount.UnreadMsgCountRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.unreadMsgCount.UnreadMsgCountResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.updatePassword.GainVerCodeRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.updatePassword.GainVerCodeResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.updatePassword.UpdatePasswordRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.updatePassword.UpdatePasswordResponse;
import com.hikvision.vmsnetsdk.util.EnCode;
import com.hikvision.vmsnetsdk.util.HttpUtil;
import com.hikvision.vmsnetsdk.util.ParseHelper;
import com.hikvision.vmsnetsdk.util.intf.IRequestTool;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class VMSNetSDK implements IVMSNetSDK, IVMSNetSDKMsg, IVMSNetSDKGis, IVMSNetSDKResource {
    public static final int DATA_TYPE_CONTROL_UNIT = 1;
    public static final int DATA_TYPE_REGION = 2;
    public static final int DATA_TYPE_CAMERA = 3;
    public static final int PTZ_CMD_UP = 1;
    public static final int PTZ_CMD_DOWN = 2;
    public static final int PTZ_CMD_LEFT = 3;
    public static final int PTZ_CMD_RIGHT = 4;
    public static final int PTZ_CMD_BRIGHTEN = 5;
    public static final int PTZ_CMD_DARKEN = 6;
    public static final int PTZ_CMD_ZOOMIN = 7;
    public static final int PTZ_CMD_ZOOMOUT = 8;
    public static final int PTZ_CMD_FOCUSNEAR = 9;
    public static final int PTZ_CMD_FOCUSFAR = 10;
    public static final int PTZ_CMD_UPLEFT = 11;
    public static final int PTZ_CMD_UPRIGHT = 12;
    public static final int PTZ_CMD_DOWNLEFT = 13;
    public static final int PTZ_CMD_DOWNRIGHT = 14;
    public static final int PTZ_CMD_STOP = 15;
    public static final int PTZ_CMD_AUTOPAN = 16;
    public static final int PTZ_CMD_SETPRESET = 17;
    public static final int PTZ_CMD_GOTOPRESET = 18;
    public static final int PTZ_CMD_CLEPRESET = 19;
    public static final int PTZ_CMD_CLOSE_WIPERS = 20;
    public static final int PTZ_CMD_OPEN_WIPERS = 21;
    public static final int PTZ_CMD_OPEN_LIGHT = 22;
    public static final int PTZ_CMD_CLOSE_LIGHT = 23;
    public static final int PTZ_CMD_3D_ENLARGE = 99;
    public static final int VMSNETSDK_NO_ERROR = 0;
    public static final int VMSNETSDK_INPUT_PARAM_ERROR = 100;
    public static final int VMSNETSDK_HTTP_NEW_URL_OBJ_FAIL = 120;
    public static final int VMSNETSDK_HTTP_NEW_REQUEST_OBJ_FAIL = 121;
    public static final int VMSNETSDK_HTTP_REQUEST_TIMEOUT = 122;
    public static final int VMSNETSDK_HTTP_REQUEST_EXCEPTION = 123;
    public static final int VMSNETSDK_HTTP_REQUEST_RETURN_NOT_200 = 124;
    public static final int VMSNETSDK_HTTP_REQUEST_RETURN_NULL = 125;
    public static final int VMSNETSDK_HTTP_REQUEST_CONNECT_FAIL = 126;
    public static final int VMSNETSDK_XMLPARSER_NEW_DATADOC_OBJ_FAIL = 140;
    public static final int VMSNETSDK_XMLPARSER_GET_ROOTNODE_FAIL = 141;
    public static final int VMSNETSDK_XMLPARSER_PARSESTATUS_FAIL = 142;
    public static final int VMSNETSDK_MSP_NO_DATA = 160;
    public static final int VMSNETSDK_MSP_PARAM_ERROR = 161;
    public static final int VMSNETSDK_MSP_SESSION_ERROR = 162;
    public static final int VMSNETSDK_MSP_USER_NOT_EXIST = 163;
    public static final int VMSNETSDK_MSP_PASSWORD_ERROR = 164;
    public static final int VMSNETSDK_MSP_DEVICE_NOT_EXIST = 165;
    public static final int VMSNETSDK_MSP_CAMERA_NOT_EXIST = 166;
    public static final int VMSNETSDK_MSP_LINE_NOT_EXIST = 167;
    public static final int VMSNETSDK_MSP_CTRLUNIT_NOT_EXIST = 168;
    public static final int VMSNETSDK_MSP_REGION_NOT_EXIST = 169;
    public static final int VMSNETSDK_MSP_VTDU_DISABLE = 170;
    public static final int VMSNETSDK_MSP_NO_PERMISSION = 171;
    public static final int VMSNETSDK_MSP_RECORDPOS_NOT_EXIST = 172;
    public static final int VMSNETSDK_MSP_USER_HAS_FROZEN = 173;
    public static final int VMSNETSDK_MSP_USER_HAS_LOGEDIN = 174;
    public static final int VMSNETSDK_MSP_USER_IN_NON_TIME = 175;
    public static final int VMSNETSDK_MSP_UNKNOWN_ERROR = 199;
    public static final int VMSNETSDK_MSP_SERVER_EXCEPTION = 230;
    public static final int VMSNETSDK_MSP_NO_SUPPORT = 207;
    public static final int VMSNETSDK_MSP_TIP = 231;
    public static final int VMSNETSDK_MSP_TIP_USER_LOGIN_PC = 242;
    public static final int VMSNETSDK_MSP_USEER_HAS_LOCK = 20043;
    public static final int VMSNETSDK_MSP_NEED_VERIFCODE = 20042;
    public static final int VMSNETSDK_MSP_VERIFCODE_ERR = 20044;
    public static final int VMSNETSDK_MSP_VERIFCODE_INVALIDE = 20045;
    private static String TAG = "VMSNetSDK";
    private static VMSNetSDK mVMSNetSDK = null;
    private static final String SDK_VERSION = "V1.6 build20170313";
    private int mLastError = 0;
    private String mLastErrorDescribe = "no error";
    private boolean mIsPrintLog = false;
    private boolean isPlatformNew;
    private int userAuthority;
    private BG_VMSNetSDK bg_VMSNetSDK = BG_VMSNetSDK.getInstance();
    private List<Integer> needStopPtzCmdList = new ArrayList();
    private MspServer mspServer;
    private MAGServer magServer;
    private IRequestTool requestTool;
    public static final String GET_LATEST_APP_INFO = "/getLatestApp";
    public static final int VMSNETSDK_PASSWORD_FIRSTLOGIN = 20030;
    public static final int VMSNETSDK_PASSWORD_WEAK = 20031;
    public static final int VMSNETSDK_PASSWORD_OVERDUE = 20032;
    private HttpUtils mHttpUtils = new HttpUtils();
    private NetCallback mNetCallback;
    private HttpHandler<File> hanlderDownload;
    private ServerConfig serverConfig;
    private boolean isPlatformOldWithMag;
    private int magVersion = 0;
    private boolean isHttpsProtocal;
    private String guid;
    private ServInfo mServerInfo;
    private boolean mIsSafeModel;

    private VMSNetSDK() {
        this.mHttpUtils.configCurrentHttpCacheExpiry(10000L);
        this.mHttpUtils.configSoTimeout(10000);
    }

    void setRequestTool(IRequestTool requestTool) {
        this.requestTool = requestTool;
        if(this.bg_VMSNetSDK != null) {
            this.bg_VMSNetSDK.setRequestTool(requestTool);
        }

    }

    private IRequestTool getRequestTool() {
        this.requestTool = new HttpUtil();
        return this.requestTool;
    }

    private void setIsPlatformNew(boolean isPlatformNew) {
        this.isPlatformNew = isPlatformNew;
        CNetSDKLog.d(TAG, "setIsPlatformNew,isPlatformNew:" + isPlatformNew);
    }

    public static VMSNetSDK getInstance() {
        if(mVMSNetSDK == null) {
            mVMSNetSDK = new VMSNetSDK();
        }

        return mVMSNetSDK;
    }

    public void openLog(boolean isPrintLog) {
        this.mIsPrintLog = isPrintLog;
        CNetSDKLog.setLogOption(isPrintLog);
        BG_VMSNetSDK.getInstance().openLog(isPrintLog);
        HttpUtil.setLogOption(isPrintLog);
    }

    public void setTimeOut(int milliSecond) {
        if(this.bg_VMSNetSDK != null) {
            this.bg_VMSNetSDK.setTimeOut(milliSecond);
        }

        this.getRequestTool().setTimeout(milliSecond);
    }

    public boolean getLineList(String servAddr, List<LineInfo> lineInfoList) {
        if(this.bg_VMSNetSDK == null) {
            return false;
        } else {
            boolean isOk = this.bg_VMSNetSDK.getLineList(this.changeServAddrToHttpAddr(servAddr, false), lineInfoList);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        }
    }

    private String changeServAddrToHttpAddr(String servAddr, boolean isSaveModel) {
        MspServer mspServer = new MspServer(servAddr);
        return isSaveModel?mspServer.gethttpsAddr():mspServer.gethttpAddr();
    }

    private String changeServAddrToHttpAddr(String servAddr) {
        return this.changeServAddrToHttpAddr(servAddr, this.mIsSafeModel);
    }

    private String changeServAddrToHttpsAddr(String servAddr) {
        MspServer mspServer = new MspServer(servAddr);
        return mspServer.gethttpsAddr();
    }

    public boolean safeLoginOldPlatform(String servAddr, String userName, String password, String macAddress, int pwdLevel, String appType, String verifCode, String verifCodekey, ServInfo servInfo, ServerConfig serConfig) {
        this.mIsSafeModel = true;
        this.bg_VMSNetSDK.setLoginModel(this.mIsSafeModel);
        if(servAddr != null && userName != null && password != null && servInfo != null && servAddr.length() > 0 && userName.length() > 0 && this.bg_VMSNetSDK != null && serConfig != null && !TextUtils.isEmpty(appType)) {
            this.serverConfig = serConfig;
            servInfo.setPlatformPasswordLevel(this.serverConfig.getPlatformPasswordLevel());
            String strServAddr = this.bg_VMSNetSDK.getLoginRequestAddr(this.changeServAddrToHttpsAddr(servAddr));
            String strData = this.bg_VMSNetSDK.getSaveLoginRequestData(userName, password, macAddress, this.serverConfig.getPasswordEncryptionMode(), pwdLevel, appType, verifCode, verifCodekey, this.getAddressIp(servAddr), this.serverConfig.getRandomCode(), serConfig.getRandomKey());
            if(strServAddr != null && strData != null && strServAddr.length() > 0 && strData.length() > 0) {
                CNetSDKLog.d(TAG, "login save server strServAddr:" + strServAddr);
                CNetSDKLog.d(TAG, "login save server strData:" + strData);
                String strResponse = this.bg_syncPostRequest(strServAddr, strData);
                if(TextUtils.isEmpty(strResponse)) {
                    return false;
                } else {
                    if(this.mIsPrintLog) {
                        CNetSDKLog.i(TAG, "login():: xml is " + strResponse);
                    }

                    boolean ret = XMLParser.parseServInfo(strResponse, servInfo);
                    if(!ret) {
                        this.mLastError = XMLParser.getError();
                        this.mLastErrorDescribe = XMLParser.getErrorDesc();
                        return false;
                    } else {
                        servInfo.setLoginRequestOk(true);
                        this.magServer = servInfo.getMagServer();
                        if(this.magServer == null) {
                            servInfo.setNewPlatform(false);
                            this.setIsPlatformOldWidthMag(false);
                            this.setIsPlatformNew(false);
                        } else {
                            servInfo.setNewPlatform(true);
                            this.setIsPlatformOldWidthMag(true);
                            this.setIsPlatformNew(true);
                        }

                        int isHttp = servInfo.getIsHttp();
                        if(isHttp == -1) {
                            this.probeMag();
                        } else if(isHttp == 0) {
                            this.isHttpsProtocal = true;
                        }

                        return true;
                    }
                }
            } else {
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::login() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean login(String servAddr, String userName, String password, int lineID, String macAddress, int pwdLevel, ServInfo servInfo) {
        if(servAddr != null && userName != null && password != null && servInfo != null && servAddr.length() > 0 && userName.length() > 0 && this.bg_VMSNetSDK != null) {
            this.mIsSafeModel = false;
            this.bg_VMSNetSDK.setLoginModel(false);
            this.serverConfig = new ServerConfig();
            boolean ret = this.getServerConfig(servAddr, this.serverConfig);
            if(!ret) {
                CNetSDKLog.i(TAG, "login():: 服务器密码配置信息获取失败");
            }

            servInfo.setPlatformPasswordLevel(this.serverConfig.getPlatformPasswordLevel());
            String strServAddr = this.bg_VMSNetSDK.getLoginRequestAddr(this.changeServAddrToHttpAddr(servAddr));
            String strData = this.bg_VMSNetSDK.getLoginRequestData(userName, password, lineID, macAddress, this.serverConfig.getPasswordEncryptionMode(), pwdLevel, this.getAddressIp(servAddr));
            if(strServAddr != null && strData != null && strServAddr.length() > 0 && strData.length() > 0) {
                CNetSDKLog.d(TAG, "login old server strData:" + strData);
                String strResponse = this.bg_syncPostRequest(strServAddr, strData);
                if(TextUtils.isEmpty(strResponse)) {
                    return false;
                } else {
                    if(this.mIsPrintLog) {
                        CNetSDKLog.i(TAG, "login():: xml is " + strResponse);
                    }

                    ret = XMLParser.parseServInfo(strResponse, servInfo);
                    if(!ret) {
                        this.mLastError = XMLParser.getError();
                        this.mLastErrorDescribe = XMLParser.getErrorDesc();
                        return false;
                    } else {
                        servInfo.setLoginRequestOk(true);
                        this.magServer = servInfo.getMagServer();
                        if(this.magServer == null) {
                            servInfo.setNewPlatform(false);
                            this.setIsPlatformOldWidthMag(false);
                            this.setIsPlatformNew(false);
                        } else {
                            servInfo.setNewPlatform(true);
                            this.setIsPlatformOldWidthMag(true);
                            this.setIsPlatformNew(true);
                        }

                        int isHttp = servInfo.getIsHttp();
                        if(isHttp == -1) {
                            this.probeMag();
                        } else if(isHttp == 0) {
                            this.isHttpsProtocal = true;
                        }

                        return true;
                    }
                }
            } else {
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::login() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public void probeMag() {
        if(!TextUtils.isEmpty(this.getNetProtocol(false))) {
            (new Thread(new Runnable() {
                public void run() {
                    VMSNetSDK.this.probeMag(VMSNetSDK.this.getNetProtocol(false));
                    CNetSDKLog.d(VMSNetSDK.TAG, "login() isHttpsProtocal==" + VMSNetSDK.this.isHttpsProtocal);
                }
            })).start();
        }

    }

    public String getAutoLoginSession(String token, int day) {
        if(!this.isPlatformNew) {
            CNetSDKLog.e(TAG, "getAutoLoginSession,can not getAutoLoginSession in old platform.");
            return null;
        } else {
            AutoLoginRequest mAutoLoginRequest = new AutoLoginRequest(this.mspServer, this.getRequestTool(), token, day);
            boolean isRequestOk = this.handleMspRequest(mAutoLoginRequest);
            if(!isRequestOk) {
                return null;
            } else {
                AutoLoginResponse mAutoLoginSessionResponse = new AutoLoginResponse(mAutoLoginRequest.getBackString());
                boolean isResponseOk = this.handleMspReponse(mAutoLoginSessionResponse);
                return !isResponseOk?null:mAutoLoginSessionResponse.getSession();
            }
        }
    }

    public boolean autoLogin(String servAddr, String session, String mac, String serviceIp, String clientIp, ServInfo servInfo) {
        this.mspServer = new MspServer(servAddr);
        AutoLoginExcuteRequest mAutoLoginRequest = new AutoLoginExcuteRequest(this.mspServer, this.getRequestTool(), session, mac, serviceIp, clientIp);
        if(!this.handleMspRequest(mAutoLoginRequest)) {
            CNetSDKLog.e(TAG, "autoLogin()::fail");
            return false;
        } else {
            LoginResponse mAutoLoginExcuteResponse = new LoginResponse(mAutoLoginRequest.getBackString());
            boolean isResponseOk = this.handleMspReponse(mAutoLoginExcuteResponse);
            servInfo.setSrcCode(mAutoLoginExcuteResponse.getMspBackState().getCode());
            if(isResponseOk) {
                servInfo.setServInfo(mAutoLoginExcuteResponse);
                this.userAuthority = mAutoLoginExcuteResponse.getUserAuthority();
                this.magServer = mAutoLoginExcuteResponse.getMagServer();
                this.mServerInfo = servInfo;
                this.setIsPlatformNew(true);
                servInfo.setNewPlatform(true);
                servInfo.setLoginRequestOk(true);
                this.setIsPlatformOldWidthMag(false);
                int isHttp = servInfo.getIsHttp();
                if(isHttp == -1) {
                    this.probeMag();
                } else if(isHttp == 0) {
                    this.isHttpsProtocal = true;
                }

                return true;
            } else {
                return false;
            }
        }
    }

    private void setIsPlatformOldWidthMag(boolean isPlatformOldWithMag) {
        this.isPlatformOldWithMag = isPlatformOldWithMag;
    }

    public boolean login(String servAddr, String userName, String password, String macAddress, ServInfo servInfo) {
        return this.login(servAddr, userName, password, macAddress, servInfo, false, (String)null, servAddr);
    }

    public boolean login(String servAddr, String userName, String password, String macAddress, ServInfo servInfo, String domainAddress) {
        return this.login(servAddr, userName, password, macAddress, servInfo, false, (String)null, domainAddress);
    }

    public boolean modifyPwd(String servAddr, String newPwd, String modifyId, ServInfo servInfo) {
        return this.login(servAddr, (String)null, newPwd, (String)null, servInfo, true, modifyId, servAddr);
    }

    public boolean safeLoginNewPlatform(String servAddr, String userName, String password, String macAddress, int pwdLevel, String appType, String verifCode, String verifCodekey, ServInfo servInfo, ServerConfig serConfig) {
        this.mIsSafeModel = true;
        this.bg_VMSNetSDK.setLoginModel(this.mIsSafeModel);
        if(servAddr != null && userName != null && password != null && servInfo != null && servAddr.length() > 0 && userName.length() > 0 && this.bg_VMSNetSDK != null && serConfig != null && !TextUtils.isEmpty(appType)) {
            this.serverConfig = serConfig;
            servInfo.setPlatformPasswordLevel(this.serverConfig.getPlatformPasswordLevel());
            this.mspServer = new MspServer(servAddr);
            String strServAddr = this.bg_VMSNetSDK.getLoginRequestAddr(this.changeServAddrToHttpsAddr(servAddr));
            String strData = this.bg_VMSNetSDK.getSaveLoginRequestData(userName, password, macAddress, this.serverConfig.getPasswordEncryptionMode(), pwdLevel, appType, verifCode, verifCodekey, this.getAddressIp(servAddr), this.serverConfig.getRandomCode(), serConfig.getRandomKey());
            if(strServAddr != null && strData != null && strServAddr.length() > 0 && strData.length() > 0) {
                CNetSDKLog.d(TAG, "login save server strServAddr:" + strServAddr);
                CNetSDKLog.d(TAG, "login save server strData:" + strData);
                String strResponse = this.bg_syncPostRequest(strServAddr, strData);
                if(TextUtils.isEmpty(strResponse)) {
                    return false;
                } else {
                    if(this.mIsPrintLog) {
                        CNetSDKLog.i(TAG, "login():: xml is " + strResponse);
                    }

                    boolean isResponseOk = false;
                    LoginResponse loginResponse = new LoginResponse(strResponse);
                    isResponseOk = this.handleMspReponse(loginResponse);
                    servInfo.setSrcCode(loginResponse.getMspBackState().getCode());
                    if(isResponseOk) {
                        servInfo.setServInfo(loginResponse);
                        this.userAuthority = loginResponse.getUserAuthority();
                        this.magServer = loginResponse.getMagServer();
                        this.mServerInfo = servInfo;
                        this.setIsPlatformNew(true);
                        servInfo.setNewPlatform(true);
                        servInfo.setLoginRequestOk(true);
                        this.setIsPlatformOldWidthMag(false);
                        int isHttp = servInfo.getIsHttp();
                        if(isHttp == -1) {
                            this.probeMag();
                        } else if(isHttp == 0) {
                            this.isHttpsProtocal = true;
                        }

                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::login() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    private boolean login(String servAddr, String userName, String password, String macAddress, ServInfo servInfo, boolean isModifyPwd, String modifyId, String domainAddress) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(password) && servInfo != null) {
            this.mIsSafeModel = false;
            this.bg_VMSNetSDK.setLoginModel(false);
            this.mspServer = new MspServer(servAddr);
            MspRequest loginRequest = null;
            if(!isModifyPwd) {
                this.serverConfig = new ServerConfig();
                this.serverConfig.setPasswordEncryptionMode(1);
                servInfo.setPlatformPasswordLevel(this.serverConfig.getPlatformPasswordLevel());
                loginRequest = new LoginRequest(this.mspServer, this.getRequestTool(), password, userName, macAddress, this.serverConfig.getPasswordEncryptionMode(), domainAddress);
            } else {
                loginRequest = new ModifyRequest(this.mspServer, this.getRequestTool(), password, userName, modifyId, servInfo.getPwdLevel());
            }

            boolean isRequestOk = this.handleMspRequest((MspRequest)loginRequest);
            if(!isRequestOk) {
                return false;
            } else {
                boolean isResponseOk = false;
                LoginResponse loginResponse = new LoginResponse(((MspRequest)loginRequest).getBackString());
                isResponseOk = this.handleMspReponse(loginResponse);
                servInfo.setSrcCode(loginResponse.getMspBackState().getCode());
                if(isResponseOk) {
                    servInfo.setServInfo(loginResponse);
                    this.userAuthority = loginResponse.getUserAuthority();
                    this.magServer = loginResponse.getMagServer();
                    this.mServerInfo = servInfo;
                    this.setIsPlatformNew(true);
                    servInfo.setNewPlatform(true);
                    servInfo.setLoginRequestOk(true);
                    this.setIsPlatformOldWidthMag(false);
                    int isHttp = servInfo.getIsHttp();
                    if(isHttp == -1) {
                        this.probeMag();
                    } else if(isHttp == 0) {
                        this.isHttpsProtocal = true;
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            CNetSDKLog.e(TAG, "login,servInfo is null,param error.");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean keepLive(String serverAddr, String sessionId) {
        HttpUtil httpUtils = new HttpUtil();
        KeepliveRequest keepliveRequest = new KeepliveRequest(this.mspServer, httpUtils, sessionId);
        boolean isRequestOk = this.handleMspRequest(keepliveRequest);
        if(!isRequestOk) {
            return false;
        } else {
            KeepliveResponse keepliveResponse = new KeepliveResponse(keepliveRequest.getBackString());
            boolean isResponseOk = this.handleMspReponse(keepliveResponse);
            return isResponseOk;
        }
    }

    public String getPlayToken(String sessionId) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            PlayTokenRequest playTokenRequest = new PlayTokenRequest(this.mspServer, this.getRequestTool(), sessionId);
            boolean isRequestOk = this.handleMspRequest(playTokenRequest);
            if(!isRequestOk) {
                return null;
            } else {
                PlayTokenResponse playTokenResponse = new PlayTokenResponse(playTokenRequest.getBackString());
                boolean isResponseOk = this.handleMspReponse(playTokenResponse);
                if(!isResponseOk) {
                    return null;
                } else {
                    String playToken = playTokenResponse.getPlayToken();
                    return playToken;
                }
            }
        } else {
            CNetSDKLog.e(TAG, "getPlayToken,can not getPlayToken in old platform.");
            return null;
        }
    }

    private boolean handleMagRequest(MagRequest magRequest) {
        if(!magRequest.isParamOk()) {
            CNetSDKLog.e(TAG, "handleMagRequest,can not get requestAddr or requestData,param error.");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        } else {
            boolean isOk = magRequest.request();
            if(!isOk) {
                CNetSDKLog.e(TAG, "handleMagRequest, request failed.");
                this.mLastError = magRequest.getErrorCode();
                this.mLastErrorDescribe = magRequest.getErrorDescribe();
                return false;
            } else {
                return true;
            }
        }
    }

    private boolean handleMspRequest(MspRequest mspRequest) {
        if(!mspRequest.isParamOk()) {
            CNetSDKLog.e(TAG, "handleMspRequest,can not get requestAddr or requestData,param error.");
            CNetSDKLog.e(TAG, "handleMspRequest,can not get requestAddr or requestData,param error.");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        } else {
            boolean isOk = mspRequest.request();
            if(!isOk) {
                CNetSDKLog.e(TAG, "handleMspRequest, request failed.");
                CNetSDKLog.e(TAG, "handleMspRequest, request failed.");
                this.mLastError = mspRequest.getErrorCode();
                this.mLastErrorDescribe = mspRequest.getErrorDescribe();
                return false;
            } else {
                return true;
            }
        }
    }

    private void handleOldPlatformError(BG_VMSNetSDK bg_VMSNetSDK) {
        this.mLastError = bg_VMSNetSDK.getLastErrorCode();
        this.mLastErrorDescribe = bg_VMSNetSDK.getLastErrorDesc();
    }

    private boolean handleMspReponse(MspResponse mspResponse) {
        boolean isOk = mspResponse.parseResponseData();
        if(!isOk) {
            CNetSDKLog.e(TAG, "handleMspReponse,mspResponse parseResponseData failed.");
            this.mLastError = 141;
            this.mLastErrorDescribe = "get root node fail, Maybe the xml doc format error!!";
            return false;
        } else {
            MspBackState mspBackState = mspResponse.getMspBackState();
            if(!mspBackState.isSuccess()) {
                CNetSDKLog.e(TAG, "handleMspReponse,mspBackState is unsuccess.");
                this.mLastError = MspBackState.adapterErrorCode(mspBackState.getCode());
                this.mLastErrorDescribe = mspBackState.getDescription();
                return false;
            } else {
                return true;
            }
        }
    }

    private boolean handleMagReponse(MagResponse magResponse) {
        boolean isOk = magResponse.parseResponseData();
        if(!isOk) {
            CNetSDKLog.e(TAG, "handleMspReponse,mspResponse parseResponseData failed.");
            this.mLastError = 141;
            this.mLastErrorDescribe = "get root node fail, Maybe the xml doc format error!!";
            return false;
        } else {
            MagBackState mspBackState = magResponse.getmagBackState();
            if(!mspBackState.isSuccess()) {
                CNetSDKLog.e(TAG, "handleMspReponse,mspBackState is unsuccess.");
                this.mLastError = mspBackState.getCode();
                this.mLastErrorDescribe = mspBackState.getDescription();
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean logout(String servAddr, String sessionID, String deviceID) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            LogoutRequest logoutRequest = new LogoutRequest(this.mspServer, this.getRequestTool(), sessionID);
            boolean isRequestOk = this.handleMspRequest(logoutRequest);
            if(!isRequestOk) {
                return false;
            } else {
                LogoutResponse logoutResponse = new LogoutResponse(logoutRequest.getBackString());
                boolean isResponseOk = this.handleMspReponse(logoutResponse);
                return isResponseOk;
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else {
            boolean isOk = this.bg_VMSNetSDK.logout(this.changeServAddrToHttpAddr(servAddr), sessionID, deviceID);
            if(!isOk) {
                this.mLastError = this.bg_VMSNetSDK.getLastErrorCode();
                this.mLastErrorDescribe = this.bg_VMSNetSDK.getLastErrorDesc();
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean getControlUnitList(String servAddr, String sessionID, String controlUnitID, int numPerPage, int curPage, List<ControlUnitInfo> controlUnitList) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(controlUnitList == null) {
                CNetSDKLog.e(TAG, "getControlUnitList,controlUnitList is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            } else {
                ControlUnitListRequest controlUnitListRequest = new ControlUnitListRequest(this.mspServer, this.getRequestTool(), sessionID, controlUnitID, numPerPage, curPage);
                boolean isRequestOk = this.handleMspRequest(controlUnitListRequest);
                if(!isRequestOk) {
                    return false;
                } else {
                    ControlUnitListResponse controlUnitListResponse = new ControlUnitListResponse(controlUnitListRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(controlUnitListResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        controlUnitList.clear();
                        ArrayList<ControlUnit> superCUList = controlUnitListResponse.getControlUnitList();
                        if(superCUList != null) {
                            Iterator var12 = superCUList.iterator();

                            while(var12.hasNext()) {
                                ControlUnit tempCU = (ControlUnit)var12.next();
                                ControlUnitInfo tempCUInfo = new ControlUnitInfo();
                                tempCUInfo.setControlUnitInfo(tempCU);
                                controlUnitList.add(tempCUInfo);
                            }
                        }

                        return true;
                    }
                }
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else {
            boolean isOk = this.bg_VMSNetSDK.getControlUnitList(this.changeServAddrToHttpAddr(servAddr), sessionID, controlUnitID, numPerPage, curPage, controlUnitList);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean getRegionListFromCtrlUnit(String servAddr, String sessionID, String controlUnitID, int numPerPage, int curPage, List<RegionInfo> regionList) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            CNetSDKLog.e(TAG, "getRegionListFromCtrlUnit,the new vision platform dose not support to getRegionListFromCtrlUnit,regionList empty_v1.");
            return true;
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else {
            boolean isOk = this.bg_VMSNetSDK.getRegionListFromCtrlUnit(this.changeServAddrToHttpAddr(servAddr), sessionID, controlUnitID, numPerPage, curPage, regionList);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean getRegionListFromRegion(String servAddr, String sessionID, String regionID, int numPerPage, int curPage, List<RegionInfo> regionList) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            CNetSDKLog.e(TAG, "getRegionListFromCtrlUnit,the new vision platform dose not support to getRegionListFromCtrlUnit.");
            return false;
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else {
            boolean isOk = this.bg_VMSNetSDK.getRegionListFromRegion(this.changeServAddrToHttpAddr(servAddr), sessionID, regionID, numPerPage, curPage, regionList);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean getAllCameraList(String servAddr, String sessionID, int numPerPage, int curPage, List<CameraInfo> cameraList) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            CNetSDKLog.e(TAG, "getAllCameraList,the new vision platform dose not suppot to get all cameraList");
            return false;
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else {
            boolean isOk = this.bg_VMSNetSDK.getAllCameraList(this.changeServAddrToHttpAddr(servAddr), sessionID, numPerPage, curPage, cameraList);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean getCameraListFromCtrlUnit(String servAddr, String sessionID, String controlUnitID, int numPerPage, int curPage, List<CameraInfo> cameraList) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(cameraList == null) {
                CNetSDKLog.e(TAG, "getCameraListFromCtrlUnit,cameraList is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            } else {
                CameraListRequest cameraListRequest = new CameraListRequest(this.mspServer, this.getRequestTool(), sessionID, controlUnitID, numPerPage, curPage);
                boolean isOk = this.handleMspRequest(cameraListRequest);
                if(!isOk) {
                    return false;
                } else {
                    CameraListResponse cameraListResponse = new CameraListResponse(cameraListRequest.getBackString());
                    isOk = this.handleMspReponse(cameraListResponse);
                    if(!isOk) {
                        return false;
                    } else {
                        ArrayList<Camera> easyCList = cameraListResponse.getCameraList();
                        cameraList.clear();
                        Iterator var11 = easyCList.iterator();

                        while(var11.hasNext()) {
                            Camera easyCTemp = (Camera)var11.next();
                            CameraInfo cameraInfo = new CameraInfo();
                            cameraInfo.setCameraInfo(easyCTemp);
                            cameraList.add(cameraInfo);
                        }

                        return true;
                    }
                }
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else {
            boolean isOk = this.bg_VMSNetSDK.getCameraListFromCtrlUnit(this.changeServAddrToHttpAddr(servAddr), sessionID, controlUnitID, numPerPage, curPage, cameraList);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean getCameraListFromRegion(String servAddr, String sessionID, String regionID, int numPerPage, int curPage, List<CameraInfo> cameraList) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            CNetSDKLog.e(TAG, "getCameraListFromRegion,the new vision platform dose not support to getCameraListFromRegion.");
            return false;
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else {
            boolean isOk = this.bg_VMSNetSDK.getCameraListFromRegion(this.changeServAddrToHttpAddr(servAddr), sessionID, regionID, numPerPage, curPage, cameraList);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean getDeviceInfo(String servAddr, String sessionID, String deviceID, DeviceInfo deviceInfo) {
        if(this.mIsSafeModel) {
            CNetSDKLog.d(TAG, "getDeviceInfo() save model login,no need getDeviceInfo");
            return true;
        } else if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(deviceInfo == null) {
                CNetSDKLog.e(TAG, "getDeviceInfo,deviceInfo is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            } else {
                DeviceInfoRequest deviceInfoRequest = new DeviceInfoRequest(this.mspServer, this.getRequestTool(), sessionID, deviceID);
                boolean isRequestOK = this.handleMspRequest(deviceInfoRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    DeviceInfoResponse deviceInfoResponse = new DeviceInfoResponse(deviceInfoRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(deviceInfoResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        DeviceInfo deviceInfoTemp = deviceInfoResponse.getDeviceInfo();
                        deviceInfo.setDeviceIP(deviceInfoTemp.getDeviceIP());
                        deviceInfo.setDeviceName(deviceInfoTemp.getDeviceName());
                        deviceInfo.setDevicePort(deviceInfoTemp.getDevicePort());
                        deviceInfo.setDeviceType(deviceInfoTemp.getDeviceType());
                        deviceInfo.setIndexCode(deviceInfoTemp.getIndexCode());
                        deviceInfo.setLoginPsw(deviceInfoTemp.getLoginPsw());
                        deviceInfo.setSupplier(deviceInfoTemp.getSupplier());
                        deviceInfo.setLoginName(deviceInfoTemp.getLoginName());
                        return true;
                    }
                }
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else {
            boolean isOk = this.bg_VMSNetSDK.getDeviceInfo(this.changeServAddrToHttpAddr(servAddr), sessionID, deviceID, deviceInfo);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean getDeviceGPSTrackInfoByIndex(String servAddr, String sessionID, String deviceIndexCode, int numPerPage, int currentPage, String startTime, String endTime, int topLimit, GpsTrackListInfo gpsTrackListInfo) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(gpsTrackListInfo == null) {
                CNetSDKLog.e(TAG, "getDeviceGPSTrackInfoByIndex,gpsTrackListInfo is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            } else {
                DeviceGpsParam deviceGpsParam = new DeviceGpsParam();
                deviceGpsParam.setTopLimit(topLimit);
                deviceGpsParam.setEndTime(Long.parseLong(endTime));
                deviceGpsParam.setIndexCode(deviceIndexCode);
                deviceGpsParam.setSessionId(sessionID);
                deviceGpsParam.setStartTime(Long.parseLong(startTime));
                DeviceGpsRequest deviceGpsRequest = new DeviceGpsRequest(this.mspServer, this.getRequestTool(), deviceGpsParam);
                boolean isRequestOK = this.handleMspRequest(deviceGpsRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    DeviceGpsResponse deviceGpsResponse = new DeviceGpsResponse(deviceGpsRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(deviceGpsResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        List<GISTrackPoint> gpsTrackList = new ArrayList();
                        Iterator var16 = deviceGpsResponse.getGpsTrackList().iterator();

                        while(var16.hasNext()) {
                            GISTrackPoint gisTrackPoint = (GISTrackPoint)var16.next();
                            gpsTrackList.add(gisTrackPoint);
                        }

                        gpsTrackListInfo.setGpsTrackList(gpsTrackList);
                        gpsTrackListInfo.setTotalNum(deviceGpsResponse.getTotal());
                        gpsTrackListInfo.setRealBackNum(deviceGpsResponse.getGpsNum());
                        return true;
                    }
                }
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else {
            List<GISTrackPoint> gpsTrackList = new ArrayList();
            boolean isOk = this.bg_VMSNetSDK.getDeviceGPSTrackInfoByIndex(this.changeServAddrToHttpAddr(servAddr), sessionID, deviceIndexCode, numPerPage, currentPage, startTime, endTime, gpsTrackList);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                gpsTrackListInfo.setGpsTrackList(gpsTrackList);
                return true;
            }
        }
    }

    public boolean getGISPointInfo(String servAddr, String sessionID, ArrayList<Integer> pointTypes, int numPerPage, int curPage, List<GISPointInfo> gisInfoList) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(gisInfoList == null) {
                CNetSDKLog.e(TAG, "getGISPointInfo,gisInfoList is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            } else {
                AllGisPointRequest allGisPointRequest = new AllGisPointRequest(this.mspServer, this.getRequestTool(), sessionID, pointTypes, numPerPage, curPage);
                boolean isRequestOK = this.handleMspRequest(allGisPointRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    AllGisPointResponse allGisPointResponse = new AllGisPointResponse(allGisPointRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(allGisPointResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        Iterator var11 = allGisPointResponse.getGisPointList().iterator();

                        while(var11.hasNext()) {
                            GISPointInfo gisPointInfo = (GISPointInfo)var11.next();
                            gisInfoList.add(gisPointInfo);
                        }

                        return true;
                    }
                }
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else {
            boolean isOk = this.bg_VMSNetSDK.getGISPointInfo(this.changeServAddrToHttpAddr(servAddr), sessionID, pointTypes, numPerPage, curPage, gisInfoList);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean getRealPlayURL(String servAddr, String sessionID, String cameraID, int streamType, RealPlayURL realPlayURL) {
        servAddr = this.changeServAddrToHttpAddr(servAddr);
        if(servAddr != null && sessionID != null && cameraID != null && realPlayURL != null && servAddr.length() > 0 && sessionID.length() > 0 && cameraID.length() > 0) {
            String strServAddr = String.format("%s/mobile/getRealPlayURL", new Object[]{servAddr});
            CNetSDKLog.d(TAG, strServAddr);
            String paramData = String.format(Locale.ENGLISH, "sessionID=%s&cameraID=%s&streamType=%d", new Object[]{sessionID, cameraID, Integer.valueOf(streamType)});
            CNetSDKLog.d(TAG, paramData);
            String strResponse = this.bg_syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                if(this.mIsPrintLog) {
                    CNetSDKLog.i(TAG, "getRealPlayURL():: xml is " + strResponse);
                }

                boolean ret = XMLParser.parseRealPlayURL(strResponse, realPlayURL);
                if(!ret) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::getRealPlayURL() parse camera list info fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getRealPlayURL() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean queryCameraRecord(String qureyAddrHead, String sessionID, String cameraID, String recordType, String recordPos, ABS_TIME startTime, ABS_TIME endTime, RecordInfo recordInfo) {
        if(!TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(cameraID) && !TextUtils.isEmpty(recordType) && !TextUtils.isEmpty(recordPos)) {
            if(this.isPlatformNew) {
                if(this.magServer == null) {
                    CNetSDKLog.e(TAG, "registerMag,magServer is null,param error.");
                    this.mLastError = 100;
                    this.mLastErrorDescribe = "input param error";
                    return false;
                } else {
                    QueryVrmRequest queryVrmRequest = null;
                    if(this.isPlatformOldWithMag) {
                        if(TextUtils.isEmpty(this.guid)) {
                            queryVrmRequest = new QueryVrmRequest(this.getNetProtocol(this.isHttpsProtocal), this.getRequestTool(), cameraID, startTime, endTime, recordType, recordPos, this.isPlatformOldWithMag, this.isHttpsProtocal);
                        } else {
                            queryVrmRequest = new QueryVrmRequest(this.getNetProtocol(this.isHttpsProtocal), this.getRequestTool(), cameraID, startTime, endTime, recordType, recordPos, this.isPlatformOldWithMag, this.isHttpsProtocal, this.guid);
                        }
                    } else {
                        queryVrmRequest = new QueryVrmRequest(this.getNetProtocol(this.isHttpsProtocal), this.getRequestTool(), cameraID, startTime, endTime, recordType, recordPos, this.isHttpsProtocal);
                    }

                    boolean isRequestOK = this.handleMagRequest(queryVrmRequest);
                    if(!isRequestOK) {
                        return false;
                    } else {
                        QueryVrmResponse queryVrmResponse = new QueryVrmResponse(queryVrmRequest.getBackString());
                        boolean isResponseOk = this.handleMagReponse(queryVrmResponse);
                        if(!isResponseOk) {
                            return false;
                        } else {
                            recordInfo.setRecordInfo(queryVrmResponse.getRecordInfo());
                            return true;
                        }
                    }
                }
            } else if(this.bg_VMSNetSDK == null) {
                return false;
            } else {
                if(this.mIsSafeModel) {
                    qureyAddrHead = this.changeServAddrToHttpsAddr(qureyAddrHead);
                } else {
                    qureyAddrHead = this.changeServAddrToHttpAddr(qureyAddrHead);
                }

                boolean isOk = this.bg_VMSNetSDK.queryCameraRecord(qureyAddrHead, sessionID, cameraID, recordType, recordPos, startTime, endTime, recordInfo);
                if(!isOk) {
                    this.mLastError = this.bg_VMSNetSDK.getLastErrorCode();
                    this.mLastErrorDescribe = this.bg_VMSNetSDK.getLastErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.e(TAG, "registerMag,magServer is null,param error.");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean queryNcgCameraRecord(String qureyAddrHead, String sessionID, String cameraID, int recordType, int recordPos, String startTime, String endTime, RecordInfo recordInfo) {
        if(this.isPlatformNew) {
            if(this.magServer == null) {
                CNetSDKLog.e(TAG, "registerMag,magServer is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            }

            qureyAddrHead = this.getNetProtocol(this.isHttpsProtocal);
        }

        QueryNcgRequest queryNcgRequest = null;
        if(this.isPlatformOldWithMag) {
            queryNcgRequest = new QueryNcgRequest(qureyAddrHead, this.getRequestTool(), cameraID, startTime, endTime, recordType, recordPos, this.isHttpsProtocal, this.isPlatformOldWithMag);
        } else {
            queryNcgRequest = new QueryNcgRequest(qureyAddrHead, this.getRequestTool(), cameraID, startTime, endTime, recordType, recordPos, this.isHttpsProtocal);
        }

        boolean isRequestOK = this.handleMagRequest(queryNcgRequest);
        if(!isRequestOK) {
            return false;
        } else {
            QueryNcgResponse queryNcgResponse = new QueryNcgResponse(queryNcgRequest.getBackString());
            boolean isResponseOk = this.handleMagReponse(queryNcgResponse);
            if(!isResponseOk) {
                return false;
            } else {
                recordInfo.setRecordInfo(queryNcgResponse.getRecordInfo());
                return true;
            }
        }
    }

    public boolean getUnreadMsgCount(String servAddr, String sessionID, SDKUnBullentinCount msgCount) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(msgCount == null) {
                CNetSDKLog.e(TAG, "getUnreadMsgCount,msgCount is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            } else {
                UnreadMsgCountRequest unreadMsgCountRequest = new UnreadMsgCountRequest(this.mspServer, this.getRequestTool(), sessionID);
                boolean isRequestOK = this.handleMspRequest(unreadMsgCountRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    UnreadMsgCountResponse unreadMsgCountResponse = new UnreadMsgCountResponse(unreadMsgCountRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(unreadMsgCountResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        msgCount.setAmCount(unreadMsgCountResponse.getAmCount());
                        msgCount.setPmCount(unreadMsgCountResponse.getPmCount());
                        msgCount.setSmCount(unreadMsgCountResponse.getSmCount());
                        msgCount.setTmCount(unreadMsgCountResponse.getTmCount());
                        msgCount.setTotalCount(unreadMsgCountResponse.getTotalCount());
                        return true;
                    }
                }
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else {
            boolean isOk = this.bg_VMSNetSDK.getUnreadMsgCount(this.changeServAddrToHttpAddr(servAddr), sessionID, msgCount);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean getMsgList(String servAddr, String sessionID, int number, List<SDKBulletin> bullentinList) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(bullentinList != null && !TextUtils.isEmpty(sessionID)) {
                LatestMsgListRequest latestMsgListRequest = new LatestMsgListRequest(this.mspServer, this.getRequestTool(), sessionID, number);
                boolean isRequestOK = this.handleMspRequest(latestMsgListRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    LatestMsgListResponse latestMsgListResponse = new LatestMsgListResponse(latestMsgListRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(latestMsgListResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        bullentinList.clear();
                        List<Msg> msgList = latestMsgListResponse.getMsgList();
                        Iterator var10 = msgList.iterator();

                        while(var10.hasNext()) {
                            Msg msg = (Msg)var10.next();
                            SDKBulletin sdkBulletin = new SDKBulletin();
                            sdkBulletin.setChecked(msg.isChecked());
                            sdkBulletin.setCreateTime(msg.getCreateTime());
                            sdkBulletin.setId(msg.getId());
                            sdkBulletin.setTitle(msg.getTitle());
                            sdkBulletin.setType(msg.getType());
                            sdkBulletin.setTypeDescribe(msg.getTypeDescribe());
                            bullentinList.add(sdkBulletin);
                        }

                        return true;
                    }
                }
            } else {
                CNetSDKLog.e(TAG, "getMsgList,bullentinList is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID)) {
            boolean isOk = this.bg_VMSNetSDK.getMsgList(this.changeServAddrToHttpAddr(servAddr), sessionID, number, bullentinList);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        } else {
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean getMsgHistoryList(String servAddr, String sessionID, String msgID, String type, int number, List<SDKBulletin> bullentinList) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(bullentinList == null) {
                CNetSDKLog.e(TAG, "getMsgHistoryList,bullentinList is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            } else {
                HistoryMsgListRequest historyMsgListRequest = new HistoryMsgListRequest(this.mspServer, this.getRequestTool(), sessionID, type, msgID, number);
                boolean isRequestOK = this.handleMspRequest(historyMsgListRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    HistoryMsgListResponse historyMsgListResponse = new HistoryMsgListResponse(historyMsgListRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(historyMsgListResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        bullentinList.clear();
                        List<Msg> msgList = historyMsgListResponse.getMsgList();
                        Iterator var12 = msgList.iterator();

                        while(var12.hasNext()) {
                            Msg msg = (Msg)var12.next();
                            SDKBulletin sdkBulletin = new SDKBulletin();
                            sdkBulletin.setChecked(msg.isChecked());
                            sdkBulletin.setCreateTime(msg.getCreateTime());
                            sdkBulletin.setId(msg.getId());
                            sdkBulletin.setTitle(msg.getTitle());
                            sdkBulletin.setType(msg.getType());
                            sdkBulletin.setTypeDescribe(msg.getTypeDescribe());
                            bullentinList.add(sdkBulletin);
                            bullentinList.add(sdkBulletin);
                        }

                        return true;
                    }
                }
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else {
            boolean isOk = this.bg_VMSNetSDK.getMsgHistoryList(this.changeServAddrToHttpAddr(servAddr), sessionID, msgID, type, number, bullentinList);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean getAlarmMsgDetailList(String servAddr, String sessionID, String type, int number, List<SDKAlarmBulletinDetail> alarmbltnDetailList) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(alarmbltnDetailList != null && !TextUtils.isEmpty(sessionID)) {
                LatestMsgDetailListRequest latestMsgDetailListRequest = new LatestMsgDetailListRequest(this.mspServer, this.getRequestTool(), sessionID, type, number);
                boolean isRequestOK = this.handleMspRequest(latestMsgDetailListRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    LatestMsgDetailListResponse latestMsgDetailListResponse = new LatestMsgDetailListResponse(latestMsgDetailListRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(latestMsgDetailListResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        alarmbltnDetailList.clear();
                        List<MsgDetail> msgList = latestMsgDetailListResponse.getMsgDetails();
                        Iterator var11 = msgList.iterator();

                        while(var11.hasNext()) {
                            MsgDetail msgDetail = (MsgDetail)var11.next();
                            SDKAlarmBulletinDetail sdkAlarmBulletinDetail = new SDKAlarmBulletinDetail();
                            sdkAlarmBulletinDetail.setAlarmSDKBulletinDetail(msgDetail);
                            alarmbltnDetailList.add(sdkAlarmBulletinDetail);
                        }

                        return true;
                    }
                }
            } else {
                CNetSDKLog.e(TAG, "getAlarmMsgDetailList,alarmbltnDetailList is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID)) {
            boolean isOk = this.bg_VMSNetSDK.getAlarmMsgDetailList(servAddr, sessionID, type, number, alarmbltnDetailList);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        } else {
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean getAlarmMsgDetailHistoryList(String servAddr, String sessionID, String msgID, String type, int number, List<SDKAlarmBulletinDetail> alarmbltnDetailList) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(alarmbltnDetailList == null) {
                CNetSDKLog.e(TAG, "getAlarmMsgDetailHistoryList,alarmbltnDetailList is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            } else {
                HistoryMsgDetailListRequest historyMsgDetailListRequest = new HistoryMsgDetailListRequest(this.mspServer, this.getRequestTool(), sessionID, type, number, msgID);
                boolean isRequestOK = this.handleMspRequest(historyMsgDetailListRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    HistoryMsgDetailListResponse historyMsgDetailListResponse = new HistoryMsgDetailListResponse(historyMsgDetailListRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(historyMsgDetailListResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        alarmbltnDetailList.clear();
                        List<MsgDetail> sdkBulletinDetails = historyMsgDetailListResponse.getMsgDetails();
                        if(sdkBulletinDetails != null) {
                            Iterator var12 = sdkBulletinDetails.iterator();

                            while(var12.hasNext()) {
                                MsgDetail msgDetail = (MsgDetail)var12.next();
                                SDKAlarmBulletinDetail sdkAlarmBulletinDetail = new SDKAlarmBulletinDetail();
                                sdkAlarmBulletinDetail.setAlarmSDKBulletinDetail(msgDetail);
                                alarmbltnDetailList.add(sdkAlarmBulletinDetail);
                            }
                        }

                        return true;
                    }
                }
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else {
            boolean isOk = this.bg_VMSNetSDK.getAlarmMsgDetailHistoryList(this.changeServAddrToHttpAddr(servAddr), sessionID, msgID, type, number, alarmbltnDetailList);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean getMsgDetailList(String servAddr, String sessionID, String type, int number, List<SDKBulletinDetail> bltnDetailList) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(bltnDetailList != null && !TextUtils.isEmpty(sessionID)) {
                LatestMsgDetailListRequest latestMsgDetailListRequest = new LatestMsgDetailListRequest(this.mspServer, this.getRequestTool(), sessionID, type, number);
                boolean isRequestOK = this.handleMspRequest(latestMsgDetailListRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    LatestMsgDetailListResponse latestMsgDetailListResponse = new LatestMsgDetailListResponse(latestMsgDetailListRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(latestMsgDetailListResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        bltnDetailList.clear();
                        List<MsgDetail> msgDetails = latestMsgDetailListResponse.getMsgDetails();
                        Iterator var11 = msgDetails.iterator();

                        while(var11.hasNext()) {
                            MsgDetail msgDetail = (MsgDetail)var11.next();
                            SDKBulletinDetail sdkBulletinDetail = new SDKBulletinDetail();
                            sdkBulletinDetail.setSDKBulletinDetail(msgDetail);
                            bltnDetailList.add(sdkBulletinDetail);
                        }

                        return true;
                    }
                }
            } else {
                CNetSDKLog.e(TAG, "getMsgDetailList,bltnDetailList is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID)) {
            boolean isOk = this.bg_VMSNetSDK.getMsgDetailList(this.changeServAddrToHttpAddr(servAddr), sessionID, type, number, bltnDetailList);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        } else {
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean getMsgDetailHistoryList(String servAddr, String sessionID, String msgID, String type, int number, List<SDKBulletinDetail> bltnDetailList) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(bltnDetailList != null && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(msgID)) {
                HistoryMsgDetailListRequest historyMsgDetailListRequest = new HistoryMsgDetailListRequest(this.mspServer, this.getRequestTool(), sessionID, type, number, msgID);
                boolean isRequestOK = this.handleMspRequest(historyMsgDetailListRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    HistoryMsgDetailListResponse historyMsgDetailListResponse = new HistoryMsgDetailListResponse(historyMsgDetailListRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(historyMsgDetailListResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        bltnDetailList.clear();
                        List<MsgDetail> msgDetails = historyMsgDetailListResponse.getMsgDetails();
                        Iterator var12 = msgDetails.iterator();

                        while(var12.hasNext()) {
                            MsgDetail msgDetail = (MsgDetail)var12.next();
                            SDKBulletinDetail sdkBulletinDetail = new SDKBulletinDetail();
                            sdkBulletinDetail.setSDKBulletinDetail(msgDetail);
                            bltnDetailList.add(sdkBulletinDetail);
                        }

                        return true;
                    }
                }
            } else {
                CNetSDKLog.e(TAG, "getMsgDetailHistoryList,bltnDetailList is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(msgID)) {
            boolean isOk = this.bg_VMSNetSDK.getMsgDetailHistoryList(this.changeServAddrToHttpAddr(servAddr), sessionID, msgID, type, number, bltnDetailList);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        } else {
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean getMsgDetail(String servAddr, String sessionID, String msgID, SDKBulletinDetail bltnDetail) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(bltnDetail != null && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(msgID)) {
                MsgDetailRequest msgDetailRequest = new MsgDetailRequest(this.mspServer, this.getRequestTool(), sessionID, msgID);
                boolean isRequestOK = this.handleMspRequest(msgDetailRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    MsgDetailResponse msgDetailResponse = new MsgDetailResponse(msgDetailRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(msgDetailResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        bltnDetail.setSDKBulletinDetail(msgDetailResponse.getMsgDetail());
                        return true;
                    }
                }
            } else {
                CNetSDKLog.e(TAG, "getMsgDetail,bltnDetail is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(msgID)) {
            boolean isOk = this.bg_VMSNetSDK.getMsgDetail(this.changeServAddrToHttpAddr(servAddr), sessionID, msgID, bltnDetail);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        } else {
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean getAlarmMsgDetail(String servAddr, String sessionID, String msgID, SDKAlarmBulletinDetail alarmBltnDetail) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(alarmBltnDetail != null && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(msgID)) {
                MsgDetailRequest msgDetailRequest = new MsgDetailRequest(this.mspServer, this.getRequestTool(), sessionID, msgID);
                boolean isRequestOK = this.handleMspRequest(msgDetailRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    MsgDetailResponse msgDetailResponse = new MsgDetailResponse(msgDetailRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(msgDetailResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        alarmBltnDetail.setAlarmSDKBulletinDetail(msgDetailResponse.getMsgDetail());
                        return true;
                    }
                }
            } else {
                CNetSDKLog.e(TAG, "getAlarmMsgDetail,alarmBltnDetail is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(msgID)) {
            boolean isOk = this.bg_VMSNetSDK.getAlarmMsgDetail(this.changeServAddrToHttpAddr(servAddr), sessionID, msgID, alarmBltnDetail);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        } else {
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean deleteMsgByType(String servAddr, String sessionID, String type) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(this.mspServer != null && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(type)) {
                DeleteMsgRequest deleteMsgRequest = new DeleteMsgRequest(this.mspServer, this.getRequestTool(), sessionID, MODE.BY_MSGTYPE, type);
                boolean isRequestOK = this.handleMspRequest(deleteMsgRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    DeleteMsgResponse deleteMsgResponse = new DeleteMsgResponse(deleteMsgRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(deleteMsgResponse);
                    return isResponseOk;
                }
            } else {
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(type)) {
            boolean isOk = this.bg_VMSNetSDK.deleteMsgByType(this.changeServAddrToHttpAddr(servAddr), sessionID, type);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        } else {
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean deleteMsgByID(String servAddr, String sessionID, String msgId) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(this.mspServer != null && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(msgId)) {
                DeleteMsgRequest deleteMsgRequest = new DeleteMsgRequest(this.mspServer, this.getRequestTool(), sessionID, MODE.BY_MSGID, msgId);
                boolean isRequestOK = this.handleMspRequest(deleteMsgRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    DeleteMsgResponse deleteMsgResponse = new DeleteMsgResponse(deleteMsgRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(deleteMsgResponse);
                    return isResponseOk;
                }
            } else {
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(msgId)) {
            boolean isOk = this.bg_VMSNetSDK.deleteMsgByID(this.changeServAddrToHttpAddr(servAddr), sessionID, msgId);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        } else {
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean checkMsgByID(String servAddr, String sessionID, String msgID) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(this.mspServer != null && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(msgID)) {
                CheckMsgRequest checkMsgRequest = new CheckMsgRequest(this.mspServer, this.getRequestTool(), sessionID, com.hikvision.vmsnetsdk.netLayer.msp.checkMsg.CheckMsgRequest.MODE.BY_MSGID, msgID);
                boolean isRequestOK = this.handleMspRequest(checkMsgRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    CheckMsgResponse checkMsgResponse = new CheckMsgResponse(checkMsgRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(checkMsgResponse);
                    return isResponseOk;
                }
            } else {
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(msgID)) {
            boolean isOk = this.bg_VMSNetSDK.checkMsgByID(this.changeServAddrToHttpAddr(servAddr), sessionID, msgID);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        } else {
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean checkMsgByType(String servAddr, String sessionID, String type) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(this.mspServer != null && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(type)) {
                CheckMsgRequest checkMsgRequest = new CheckMsgRequest(this.mspServer, this.getRequestTool(), sessionID, com.hikvision.vmsnetsdk.netLayer.msp.checkMsg.CheckMsgRequest.MODE.BY_MSGTYPE, type);
                boolean isRequestOK = this.handleMspRequest(checkMsgRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    CheckMsgResponse checkMsgResponse = new CheckMsgResponse(checkMsgRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(checkMsgResponse);
                    return isResponseOk;
                }
            } else {
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            }
        } else if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(type)) {
            boolean isOk = this.bg_VMSNetSDK.checkMsgByType(this.changeServAddrToHttpAddr(servAddr), sessionID, type);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        } else {
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean getGISInitInfo(String servAddr, String sessionID, SDKGISInitInfo sdkGISInitInfo) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(sdkGISInitInfo == null) {
                CNetSDKLog.e(TAG, "getGISInitInfo,sdkGISInitInfo is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            } else {
                GisInitInfoRequest gisInitInfoRequest = new GisInitInfoRequest(this.mspServer, this.getRequestTool(), sessionID);
                boolean isRequestOK = this.handleMspRequest(gisInitInfoRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    GisInitInfoResponse gisInitInfoResponse = new GisInitInfoResponse(gisInitInfoRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(gisInitInfoResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        sdkGISInitInfo.setMapType(gisInitInfoResponse.getMapType());
                        sdkGISInitInfo.setResType(gisInitInfoResponse.getResType());
                        sdkGISInitInfo.setGovMap(gisInitInfoResponse.getGovMap());
                        sdkGISInitInfo.setImageMap(gisInitInfoResponse.getImageMap());
                        return true;
                    }
                }
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else {
            boolean isOk = this.bg_VMSNetSDK.getGISInitInfo(this.changeServAddrToHttpAddr(servAddr), sessionID, sdkGISInitInfo);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean sendDeviceId(String servAddr, String sessionID, String deviceId, String appType) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            PushServerInfoRequest pushServerInfoRequest = new PushServerInfoRequest(this.mspServer, this.getRequestTool(), sessionID, appType);
            boolean isRequestOK = this.handleMspRequest(pushServerInfoRequest);
            if(!isRequestOK) {
                return false;
            } else {
                PushServerInfoResponse pushServerInfoResponse = new PushServerInfoResponse(pushServerInfoRequest.getBackString());
                boolean isResponseOk = this.handleMspReponse(pushServerInfoResponse);
                return isResponseOk;
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else {
            boolean isOk = this.bg_VMSNetSDK.sendDeviceId(this.changeServAddrToHttpAddr(servAddr), sessionID, deviceId, appType);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean registerMag(boolean isInternet, String sessionID, String tokenId, String userName, String appType) {
        if(!this.isPlatformNew) {
            CNetSDKLog.i(TAG, "registerMag,no such function in old platform.default return true");
            return true;
        } else if(this.magServer == null) {
            CNetSDKLog.e(TAG, "registerMag,magServer is null,param error.");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        } else {
            RegisterMagRequest registerMagRequest = new RegisterMagRequest(this.getNetProtocol(this.isHttpsProtocal), this.getRequestTool(), sessionID, tokenId, userName, appType, isInternet, this.isHttpsProtocal);
            boolean isRequestOK = this.handleMagRequest(registerMagRequest);
            if(!isRequestOK) {
                return false;
            } else {
                RegisterMagResponse registerMagResponse = new RegisterMagResponse(registerMagRequest.getBackString());
                boolean isResponseOk = this.handleMagReponse(registerMagResponse);
                if(!isResponseOk) {
                    return false;
                } else {
                    String magVersion = registerMagResponse.getmagBackState().getVersion();
                    CNetSDKLog.d(TAG, "registerMag() magVersion:" + magVersion);
                    if("0.1".equals(magVersion)) {
                        this.setMagVersion(1);
                    } else {
                        this.setMagVersion(0);
                    }

                    return true;
                }
            }
        }
    }

    public String getNetProtocol(boolean isHttps) {
        return this.magServer == null?null:(isHttps?this.magServer.getMagHttpsRequestAddrHead(true):this.magServer.getMagHttpRequestAddrHead(true));
    }

    public boolean probeMag(String magHttpRequestAddrHead) {
        String MAG_PROBE = "probe";
        String url = magHttpRequestAddrHead + MAG_PROBE;
        CNetSDKLog.d(TAG, "probeMag() url is:" + url);
        HttpUtil iRequestTool = (HttpUtil)this.getRequestTool();
        this.isHttpsProtocal = iRequestTool.probeMagToHttp(url);
        return this.isHttpsProtocal;
    }

    private void setMagVersion(int magVersion) {
        this.magVersion = magVersion;
    }

    public int isNewMagVersion() {
        return this.magVersion;
    }

    public boolean getMagAbility(List<Integer> magAbility) {
        if(!this.isPlatformNew) {
            CNetSDKLog.i(TAG, "getMagAbility,no such function in old platform.default return true");
            return true;
        } else if(this.magServer == null) {
            CNetSDKLog.e(TAG, "getMagAbility,magServer is null,param error.");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        } else if(magAbility == null) {
            CNetSDKLog.e(TAG, "login,servInfo is null,param error.");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        } else {
            MagAbilityRequest magAbilityRequest = new MagAbilityRequest(this.getNetProtocol(this.isHttpsProtocal), this.getRequestTool(), this.isHttpsProtocal);
            boolean isRequestOK = this.handleMagRequest(magAbilityRequest);
            if(!isRequestOK) {
                return false;
            } else {
                MagAbilityResponse magAbilityResponse = new MagAbilityResponse(magAbilityRequest.getBackString());
                boolean isResponseOk = this.handleMagReponse(magAbilityResponse);
                return isResponseOk;
            }
        }
    }

    public boolean reLogoutMag(String sessionID) {
        if(!this.isPlatformNew) {
            CNetSDKLog.i(TAG, "reLogoutMag,no such function in old platform.default return true");
            return true;
        } else if(this.magServer == null) {
            CNetSDKLog.e(TAG, "reLogoutMag,magServer is null,param error.");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        } else {
            MagRequest relogoutRequest = new LogoutMagRequest(this.getNetProtocol(this.isHttpsProtocal), this.getRequestTool(), sessionID, this.isHttpsProtocal);
            boolean isRequestOK = this.handleMagRequest(relogoutRequest);
            if(!isRequestOK) {
                return false;
            } else {
                LogoutMagResponse logoutMgaResponse = new LogoutMagResponse(relogoutRequest.getBackString());
                boolean isResponseOk = this.handleMagReponse(logoutMgaResponse);
                return isResponseOk;
            }
        }
    }

    public boolean modifyGISInfo(String servAddr, String sessionID, String cameraID, double longitude, double latitude) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            ModifyGISInfoRequest modifyGISInfoRequest = new ModifyGISInfoRequest(this.mspServer, this.getRequestTool(), sessionID, longitude, latitude, cameraID);
            boolean isRequestOK = this.handleMspRequest(modifyGISInfoRequest);
            if(!isRequestOK) {
                return false;
            } else {
                ModifyGISInfoResponse modifyGISInfoResponse = new ModifyGISInfoResponse(modifyGISInfoRequest.getBackString());
                boolean isResponseOk = this.handleMspReponse(modifyGISInfoResponse);
                return isResponseOk;
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else {
            boolean isOk = this.bg_VMSNetSDK.modifyGISInfo(this.changeServAddrToHttpAddr(servAddr), sessionID, cameraID, longitude, latitude);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean getVerCode(String sessionId) {
        if(null != sessionId && !"".equals(sessionId)) {
            GainVerCodeRequest gainVerCodeRequest = new GainVerCodeRequest(this.mspServer, this.getRequestTool(), sessionId);
            boolean isRequestOk = this.handleMspRequest(gainVerCodeRequest);
            if(!isRequestOk) {
                return false;
            } else {
                GainVerCodeResponse response = new GainVerCodeResponse(gainVerCodeRequest.getBackString());
                boolean isResponseOk = this.handleMspReponse(response);
                return isResponseOk;
            }
        } else {
            return false;
        }
    }

    public boolean updatePassword(String servAddr, String sessionID, String oldPassword, String newPassword, String code, int pwdLevel) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest(this.mspServer, this.getRequestTool(), sessionID, oldPassword, newPassword, code, pwdLevel);
            boolean isRequestOK = this.handleMspRequest(updatePasswordRequest);
            if(!isRequestOK) {
                return false;
            } else {
                UpdatePasswordResponse updatePasswordResponse = new UpdatePasswordResponse(updatePasswordRequest.getBackString());
                boolean isResponseOk = this.handleMspReponse(updatePasswordResponse);
                return isResponseOk;
            }
        } else {
            return false;
        }
    }

    public boolean updatePasswordAndLogin(String servAddr, String name, String oldPassword, String newPassword, int pwdLevel, ServInfo servInfo) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(oldPassword) && !TextUtils.isEmpty(newPassword) && servInfo != null) {
            if(this.mIsSafeModel) {
                boolean ret = this.getServerConfig(servAddr, this.serverConfig, true);
                if(!ret) {
                    return false;
                }
            }

            String strServAddr = String.format("%s/mobile/loginModifyPassword", new Object[]{this.changeServAddrToHttpAddr(servAddr)});
            CNetSDKLog.d(TAG, "updatePasswordAndLogin() strServAddr");
            if(this.bg_VMSNetSDK != null && this.serverConfig != null) {
                String strData = this.bg_VMSNetSDK.getUpdatePwdRequestData(name, oldPassword, newPassword, pwdLevel, this.serverConfig, this.getAddressIp(strServAddr));
                if(strServAddr != null && strData != null && strServAddr.length() > 0 && strData.length() > 0) {
                    String strResponse = this.bg_syncPostRequest(strServAddr, strData);
                    CNetSDKLog.i(TAG, "updatePasswordAndLogin():: xml is " + strResponse);
                    if(strResponse == null) {
                        return false;
                    } else {
                        boolean ret = XMLParser.parseServInfo(strResponse, servInfo);
                        if(!ret) {
                            this.mLastError = XMLParser.getError();
                            this.mLastErrorDescribe = XMLParser.getErrorDesc();
                            return false;
                        } else {
                            servInfo.setLoginRequestOk(true);
                            this.magServer = servInfo.getMagServer();
                            if(this.magServer == null) {
                                this.setIsPlatformNew(false);
                                servInfo.setNewPlatform(false);
                                this.setIsPlatformOldWidthMag(false);
                            } else {
                                this.setIsPlatformNew(true);
                                servInfo.setNewPlatform(true);
                                this.setIsPlatformOldWidthMag(true);
                            }

                            return true;
                        }
                    }
                } else {
                    this.mLastError = 100;
                    this.mLastErrorDescribe = "input param error";
                    return false;
                }
            } else {
                return false;
            }
        } else {
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean stopPlayback(String servAddr, String sessionID, String cameraID) {
        if(servAddr != null && sessionID != null && cameraID != null && servAddr.length() > 0 && sessionID.length() > 0 && cameraID.length() > 0) {
            String strServAddr = String.format("%s/mobile/stopPlayback", new Object[]{this.changeServAddrToHttpAddr(servAddr)});
            String paramData = String.format("sessionID=%s&cameraID=%s", new Object[]{sessionID, cameraID});
            String strResponse = this.bg_syncPostRequest(strServAddr, paramData);
            if(strResponse != null && strResponse.length() > 0) {
                boolean ret = XMLParser.parseResultCode(strResponse);
                if(!ret) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::stopPlayback() parse camera list info fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::stopPlayback() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public synchronized boolean sendStartPTZCmd(String servAddr, int port, String sessionID, String cameraID, int cmdID, int param1, int param2, String cascadeFlag) {
        if(!this.isPlatformNew && !"1".equalsIgnoreCase(cascadeFlag)) {
            if(this.bg_VMSNetSDK == null) {
                CNetSDKLog.e(TAG, "login,servInfo is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            } else {
                boolean isOk = this.bg_VMSNetSDK.sendStartPTZCmd(servAddr, port, sessionID, cameraID, cmdID, param1, param2);
                if(!isOk) {
                    this.mLastError = this.bg_VMSNetSDK.getLastErrorCode();
                    this.mLastErrorDescribe = this.bg_VMSNetSDK.getLastErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else if(this.magServer == null) {
            CNetSDKLog.e(TAG, "sendStartPTZCmd,magServer is null,param error.");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        } else {
            int adapteredCmdId = PtzBaseParam.adapterPtzCmd(cmdID);
            PtzBaseParam ptzBaseParam = new PtzBaseParam();
            ptzBaseParam.setSessionId(sessionID);
            ptzBaseParam.setSzCamIndexCode(cameraID);
            ptzBaseParam.setiPtzCommand(adapteredCmdId);
            ptzBaseParam.setiPriority(this.userAuthority);
            PtzCommandRequest ptzCommandRequest = null;
            if(PtzCommandRequest.needActionParam(adapteredCmdId)) {
                ptzCommandRequest = new PtzCommandRequest(this.getNetProtocol(this.isHttpsProtocal), this.getRequestTool(), ptzBaseParam, 0, param1, cascadeFlag, this.isHttpsProtocal);
            } else if(!PtzCommandRequest.needIndexParam(adapteredCmdId) && !PtzCommandRequest.needLockTime(adapteredCmdId)) {
                if(PtzCommandRequest.needIPtzCruisePointAndIPtzCruiseInput(adapteredCmdId) || PtzCommandRequest.needEnlargeParam(adapteredCmdId)) {
                    CNetSDKLog.e(TAG, "sendStartPTZCmd,param error.please use another sendStartPTZCmd method.");
                    return false;
                }

                ptzCommandRequest = new PtzCommandRequest(this.getNetProtocol(this.isHttpsProtocal), this.getRequestTool(), ptzBaseParam, cascadeFlag, this.isHttpsProtocal);
            } else {
                ptzCommandRequest = new PtzCommandRequest(this.getNetProtocol(this.isHttpsProtocal), this.getRequestTool(), ptzBaseParam, param1, cascadeFlag, this.isHttpsProtocal);
            }

            boolean isRequestOK = this.handleMagRequest(ptzCommandRequest);
            if(!isRequestOK) {
                return false;
            } else {
                PtzCommandResponse ptzCommandResponse = new PtzCommandResponse(ptzCommandRequest.getBackString());
                boolean isResponseOk = this.handleMagReponse(ptzCommandResponse);
                if(isResponseOk) {
                    this.saveStartedPtzCmd(Integer.valueOf(adapteredCmdId));
                }

                return isResponseOk;
            }
        }
    }

    private void saveStartedPtzCmd(Integer ptzCmd) {
        if(this.needStopPtzCmdList != null) {
            if(this.needStopPtzCmdList.size() <= 0) {
                this.needStopPtzCmdList.add(ptzCmd);
                CNetSDKLog.i(TAG, "saveStartedPtzCmd,ptzCmd:" + ptzCmd + " added");
            } else {
                boolean isAlreadyAdded = true;
                Iterator var3 = this.needStopPtzCmdList.iterator();

                while(var3.hasNext()) {
                    Integer temp = (Integer)var3.next();
                    if(!ptzCmd.equals(temp)) {
                        isAlreadyAdded = false;
                        break;
                    }
                }

                if(!isAlreadyAdded) {
                    this.needStopPtzCmdList.add(ptzCmd);
                    CNetSDKLog.i(TAG, "saveStartedPtzCmd,ptzCmd:" + ptzCmd + " added");
                }
            }

        }
    }

    public synchronized boolean sendStartPTZCmd(String servAddr, int port, String sessionID, String userId, String cameraID, int cmdId, int param1, int param2, int param3, int param4, String cascadeFlag) {
        if(!this.isPlatformNew && !"1".equalsIgnoreCase(cascadeFlag)) {
            if(this.bg_VMSNetSDK == null) {
                CNetSDKLog.e(TAG, "login,servInfo is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            } else {
                boolean isOk = this.bg_VMSNetSDK.sendStartPTZCmd(servAddr, port, sessionID, userId, cameraID, cmdId, param1, param2, param3, param4);
                if(!isOk) {
                    this.mLastError = this.bg_VMSNetSDK.getLastErrorCode();
                    this.mLastErrorDescribe = this.bg_VMSNetSDK.getLastErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else if(this.magServer == null) {
            CNetSDKLog.e(TAG, "sendStartPTZCmd,magServer is null,param error.");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        } else {
            int adapteredCmdId = PtzBaseParam.adapterPtzCmd(cmdId);
            PtzBaseParam ptzBaseParam = new PtzBaseParam();
            ptzBaseParam.setSessionId(sessionID);
            ptzBaseParam.setSzCamIndexCode(cameraID);
            ptzBaseParam.setiPtzCommand(adapteredCmdId);
            ptzBaseParam.setiPriority(this.userAuthority);
            PtzEnlargeParam ptzZoomParam = new PtzEnlargeParam();
            ptzZoomParam.setStartX(param1);
            ptzZoomParam.setStartY(param2);
            ptzZoomParam.setStopX(param3);
            ptzZoomParam.setStopY(param4);
            PtzCommandRequest ptzCommandRequest = null;
            if(PtzCommandRequest.needEnlargeParam(adapteredCmdId)) {
                ptzCommandRequest = new PtzCommandRequest(this.getNetProtocol(this.isHttpsProtocal), this.getRequestTool(), ptzBaseParam, ptzZoomParam, cascadeFlag, this.isHttpsProtocal);
                boolean var16 = this.handleMagRequest(ptzCommandRequest);
                if(!var16) {
                    return false;
                } else {
                    PtzCommandResponse ptzCommandResponse = new PtzCommandResponse(ptzCommandRequest.getBackString());
                    boolean isResponseOk = this.handleMagReponse(ptzCommandResponse);
                    if(isResponseOk) {
                        this.saveStartedPtzCmd(Integer.valueOf(adapteredCmdId));
                    }

                    return isResponseOk;
                }
            } else {
                CNetSDKLog.e(TAG, "sendStartPTZCmd,param error.please use another sendStartPTZCmd method.");
                return false;
            }
        }
    }

    public synchronized boolean sendStopPTZCmd(String servAddr, int port, String sessionID, String cameraID, String cascadeFlag) {
        if(!this.isPlatformNew && !"1".equalsIgnoreCase(cascadeFlag)) {
            if(this.bg_VMSNetSDK == null) {
                CNetSDKLog.e(TAG, "sendStopPTZCmd,servInfo is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            } else {
                boolean isOk = this.bg_VMSNetSDK.sendStopPTZCmd(servAddr, port, sessionID, cameraID);
                if(!isOk) {
                    this.mLastError = this.bg_VMSNetSDK.getLastErrorCode();
                    this.mLastErrorDescribe = this.bg_VMSNetSDK.getLastErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            int stopFailNum = 0;
            if(this.needStopPtzCmdList != null && this.needStopPtzCmdList.size() > 0) {
                List<Integer> okCmdList = new ArrayList();
                Iterator var8 = this.needStopPtzCmdList.iterator();

                Integer temp;
                while(var8.hasNext()) {
                    temp = (Integer)var8.next();
                    if(!this.sendStopPTZCmdToNewPlatform(sessionID, cameraID, temp.intValue(), cascadeFlag)) {
                        ++stopFailNum;
                    } else {
                        okCmdList.add(temp);
                    }
                }

                if(okCmdList.size() > 0) {
                    var8 = okCmdList.iterator();

                    while(var8.hasNext()) {
                        temp = (Integer)var8.next();
                        this.needStopPtzCmdList.remove(temp);
                    }
                }

                return stopFailNum <= 0;
            } else {
                CNetSDKLog.i(TAG, "sendStopPTZCmd,needStopPtzCmdList is null or empty_v1.No need to stop PTZ.");
                return true;
            }
        }
    }

    private boolean sendStopPTZCmdToNewPlatform(String sessionID, String cameraID, int adapateredPtzCmd, String cascadeFlag) {
        if(this.magServer == null) {
            CNetSDKLog.e(TAG, "sendStopPTZCmdToNewPlatform,magServer is null,param error.");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        } else {
            PtzBaseParam ptzBaseParam = new PtzBaseParam();
            ptzBaseParam.setSessionId(sessionID);
            ptzBaseParam.setSzCamIndexCode(cameraID);
            ptzBaseParam.setiPtzCommand(adapateredPtzCmd);
            ptzBaseParam.setiPriority(this.userAuthority);
            PtzCommandRequest ptzCommandRequest = null;
            if(PtzCommandRequest.needActionParam(adapateredPtzCmd)) {
                ptzCommandRequest = new PtzCommandRequest(this.getNetProtocol(this.isHttpsProtocal), this.getRequestTool(), ptzBaseParam, 1, 0, cascadeFlag, this.isHttpsProtocal);
                boolean isRequestOK = this.handleMagRequest(ptzCommandRequest);
                if(!isRequestOK) {
                    CNetSDKLog.e(TAG, "sendStopPTZCmdToNewPlatform,isRequestOK:" + isRequestOK);
                    return false;
                } else {
                    PtzCommandResponse ptzCommandResponse = new PtzCommandResponse(ptzCommandRequest.getBackString());
                    boolean isResponseOk = this.handleMagReponse(ptzCommandResponse);
                    CNetSDKLog.i(TAG, "sendStopPTZCmdToNewPlatform,isResponseOk:" + isResponseOk);
                    return isResponseOk;
                }
            } else {
                CNetSDKLog.e(TAG, "sendStopPTZCmdToNewPlatform,param error.please use sendStartPTZCmd method.");
                return false;
            }
        }
    }

    public String getVmsNetSDKVersion() {
        return "V1.6 build20170313";
    }

    public int getLastErrorCode() {
        return this.mLastError;
    }

    public String getLastErrorDesc() {
        return this.mLastErrorDescribe;
    }

    private String bg_syncPostRequest(String strServAddr, String param) {
        if(strServAddr != null && this.bg_VMSNetSDK != null) {
            String backString = this.bg_VMSNetSDK.syncPostRequest(strServAddr, param);
            if(backString == null) {
                this.mLastError = this.bg_VMSNetSDK.getLastErrorCode();
                this.mLastErrorDescribe = this.bg_VMSNetSDK.getLastErrorDesc();
            }

            return backString;
        } else {
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return null;
        }
    }

    public boolean collectCamera(String servAddr, String sessionID, String cameraID, int groupID) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(this.mspServer != null && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(cameraID)) {
                CollectCameraRequest collectCameraRequest = new CollectCameraRequest(this.mspServer, this.getRequestTool(), sessionID, cameraID, groupID, 1);
                boolean isRequestOK = this.handleMspRequest(collectCameraRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    CollectCameraResponse collectCameraResponse = new CollectCameraResponse(collectCameraRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(collectCameraResponse);
                    return isResponseOk;
                }
            } else {
                return false;
            }
        } else if(this.bg_VMSNetSDK != null && !TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(cameraID)) {
            boolean isOk = this.bg_VMSNetSDK.collectCamera(this.changeServAddrToHttpAddr(servAddr), sessionID, cameraID, groupID);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean discollectCamera(String servAddr, String sessionID, String cameraID, int groupID) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            CollectCameraRequest collectCameraRequest = new CollectCameraRequest(this.mspServer, this.getRequestTool(), sessionID, cameraID, groupID, 0);
            boolean isRequestOK = this.handleMspRequest(collectCameraRequest);
            if(!isRequestOK) {
                return false;
            } else {
                CollectCameraResponse collectCameraResponse = new CollectCameraResponse(collectCameraRequest.getBackString());
                boolean isResponseOk = this.handleMspReponse(collectCameraResponse);
                return isResponseOk;
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else {
            boolean isOk = this.bg_VMSNetSDK.discollectCamera(this.changeServAddrToHttpAddr(servAddr), sessionID, cameraID, groupID);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean getCollectedCameraList(String servAddr, String sessionID, int numPerPage, int curPage, List<CameraInfo> cameraList) {
        return this.getCollectedCameraList(servAddr, sessionID, numPerPage, curPage, cameraList, "");
    }

    public boolean getCollectedCameraList(String servAddr, String sessionID, int numPerPage, int curPage, List<CameraInfo> cameraList, String groupID) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(cameraList == null) {
                CNetSDKLog.e(TAG, "getCollectedCameraList,cameraList is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            } else {
                CollectedCameraListRequest collectedCameraListRequest = new CollectedCameraListRequest(this.mspServer, this.getRequestTool(), sessionID, numPerPage, curPage, groupID);
                boolean isRequestOK = this.handleMspRequest(collectedCameraListRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    CollectedCameraListResponse collectedCameraListResponse = new CollectedCameraListResponse(collectedCameraListRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(collectedCameraListResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        cameraList.clear();
                        Iterator var11 = collectedCameraListResponse.getCameraList().iterator();

                        while(var11.hasNext()) {
                            Camera camera = (Camera)var11.next();
                            CameraInfo cameraInfo = new CameraInfo();
                            cameraInfo.setCameraInfo(camera);
                            cameraList.add(cameraInfo);
                        }

                        return true;
                    }
                }
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else {
            boolean isOk = this.bg_VMSNetSDK.getCollectedCameraList(this.changeServAddrToHttpAddr(servAddr), sessionID, numPerPage, curPage, cameraList);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean getGISCameraList(String servAddr, String sessionID, int numPerPage, int curPage, List<GISCameraInfo> gisCameraList) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            CNetSDKLog.e(TAG, "getGISCameraList,the new platform dose not support to getGISCameraList");
            return false;
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else {
            boolean isOk = this.bg_VMSNetSDK.getGISCameraList(this.changeServAddrToHttpAddr(servAddr), sessionID, numPerPage, curPage, gisCameraList);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean searchGisPoint(String servAddr, String sessionId, String indexCode, String pointType, List<GISPointInfo> gisPointInfos) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(gisPointInfos == null) {
                CNetSDKLog.e(TAG, "searchGisPoint,gisPointInfos is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            } else {
                SingleGisPointRequest singleGisPointRequest = new SingleGisPointRequest(this.mspServer, this.getRequestTool(), sessionId, indexCode, pointType);
                boolean isRequestOK = this.handleMspRequest(singleGisPointRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    LocalGisPointResponse localGisPointResponse = new LocalGisPointResponse(singleGisPointRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(localGisPointResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        Iterator var10 = localGisPointResponse.getGisPointList().iterator();

                        while(var10.hasNext()) {
                            GISPointInfo gisPointInfo = (GISPointInfo)var10.next();
                            gisPointInfos.add(gisPointInfo);
                        }

                        return true;
                    }
                }
            }
        } else {
            CNetSDKLog.e(TAG, "the old platform should use other method");
            return false;
        }
    }

    public boolean searchGisPoint(String servAddr, String sessionID, int numPerPage, int curPage, String keyString, String pointType, List<GISPointInfo> gisPointInfos) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(gisPointInfos == null) {
                CNetSDKLog.e(TAG, "searchGisPoint,gisPointInfos is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            } else {
                BaseGisSearchParam baseGisSearchParam = new BaseGisSearchParam();
                baseGisSearchParam.setCurPage(curPage);
                baseGisSearchParam.setKeyString(keyString);
                baseGisSearchParam.setNumPerPage(numPerPage);
                baseGisSearchParam.setPointType(pointType);
                baseGisSearchParam.setSession(sessionID);
                LocalGisPointRequest localGisPointRequest = new LocalGisPointRequest(this.mspServer, this.getRequestTool(), baseGisSearchParam);
                boolean isRequestOK = this.handleMspRequest(localGisPointRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    LocalGisPointResponse localGisPointResponse = new LocalGisPointResponse(localGisPointRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(localGisPointResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        Iterator var13 = localGisPointResponse.getGisPointList().iterator();

                        while(var13.hasNext()) {
                            GISPointInfo gisPointInfo = (GISPointInfo)var13.next();
                            gisPointInfos.add(gisPointInfo);
                        }

                        return true;
                    }
                }
            }
        } else {
            CNetSDKLog.e(TAG, "the old platform should use the mathod getGISCameraListByPostion");
            return false;
        }
    }

    public boolean getGISCameraListByName(String servAddr, String sessionID, int numPerPage, int curPage, String keyString, String pointType, List<GISCameraInfo> gisCameraList) {
        if(servAddr != null && sessionID != null && gisCameraList != null && keyString != null && pointType != null && !servAddr.equalsIgnoreCase("") && !sessionID.equalsIgnoreCase("") && !pointType.equalsIgnoreCase("")) {
            String keyStringEnc = null;

            try {
                keyStringEnc = URLEncoder.encode(URLEncoder.encode(keyString, "utf-8"), "utf-8");
            } catch (UnsupportedEncodingException var13) {
                var13.printStackTrace();
                this.mLastError = 100;
                this.mLastErrorDescribe = "user name encorde error";
                return false;
            }

            String strServAddr = String.format("%s/mobile/getGISCamera", new Object[]{this.changeServAddrToHttpAddr(servAddr)});
            String paramData = String.format(Locale.ENGLISH, "sessionID=%s&keyString=%s&pointType=%s&numPerPage=%d&curPage=%d", new Object[]{sessionID, keyStringEnc, pointType, Integer.valueOf(numPerPage), Integer.valueOf(curPage)});
            String strResponse = this.bg_syncPostRequest(strServAddr, paramData);
            if(strResponse != null && strResponse.length() > 0) {
                if(this.mIsPrintLog) {
                    CNetSDKLog.i(TAG, "getGISCameraListByName():: xml is " + strResponse);
                }

                boolean ret = XMLParser.parseGISCameraListInfo(strResponse, gisCameraList);
                if(!ret) {
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getGISCameraByName() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean getGISCameraListByPostion(String servAddr, String sessionID, String keyString, int numPerPage, int curPage, String longitude, String latitude, int radius, List<GISCameraInfo> gisCameraList) {
        if(servAddr != null && sessionID != null && gisCameraList != null && !servAddr.equals("") && !sessionID.equals("")) {
            String strServAddr = String.format("%s/mobile/getGISCamera", new Object[]{this.changeServAddrToHttpAddr(servAddr)});
            String paramData = String.format(Locale.ENGLISH, "sessionID=%s&keyString=%s&longitude=%s&latitude=%s&radius=%d&numPerPage=%d&curPage=%d", new Object[]{sessionID, keyString, longitude, latitude, Integer.valueOf(radius), Integer.valueOf(numPerPage), Integer.valueOf(curPage)});
            String strResponse = this.bg_syncPostRequest(strServAddr, paramData);
            if(strResponse != null && strResponse.length() > 0) {
                if(this.mIsPrintLog) {
                    CNetSDKLog.i(TAG, "getGISCameraListByPostion():: xml is " + strResponse);
                }

                boolean ret = XMLParser.parseGISCameraListInfo(strResponse, gisCameraList);
                if(!ret) {
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getGISCameraByPostion() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean searchGisPointInRound(String servAddr, String sessionID, String keyString, String pointType, int numPerPage, int curPage, String longitude, String latitude, int radius, List<GISPointInfo> gisPointInfos) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(gisPointInfos == null) {
                CNetSDKLog.e(TAG, "searchGisPointInRound,gisPointInfos is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            } else {
                BaseGisSearchParam baseGisSearchParam = new BaseGisSearchParam();
                baseGisSearchParam.setCurPage(curPage);
                baseGisSearchParam.setKeyString(keyString);
                baseGisSearchParam.setNumPerPage(numPerPage);
                baseGisSearchParam.setPointType(pointType);
                baseGisSearchParam.setSession(sessionID);
                InRoundGisSearchParam inRoundGisSearchParam = new InRoundGisSearchParam();
                inRoundGisSearchParam.setLatitude(latitude);
                inRoundGisSearchParam.setLongitude(longitude);
                inRoundGisSearchParam.setRadius(radius);
                LocalGisPointRequest localGisPointRequest = new LocalGisPointRequest(this.mspServer, this.getRequestTool(), baseGisSearchParam, inRoundGisSearchParam);
                boolean isRequestOK = this.handleMspRequest(localGisPointRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    LocalGisPointResponse localGisPointResponse = new LocalGisPointResponse(localGisPointRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(localGisPointResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        Iterator var17 = localGisPointResponse.getGisPointList().iterator();

                        while(var17.hasNext()) {
                            GISPointInfo gisPointInfo = (GISPointInfo)var17.next();
                            gisPointInfos.add(gisPointInfo);
                        }

                        return true;
                    }
                }
            }
        } else {
            List<Integer> pointTypeList = ParseHelper.parseStrToIntByComma(pointType);
            if(!pointTypeList.contains(Integer.valueOf(1))) {
                CNetSDKLog.e(TAG, "searchGisPointInRound,the old vision platform dose not support to search other type of gisPoints but monitor type.");
                return false;
            } else {
                CNetSDKLog.e(TAG, "the old platform should use the mathod getGISCameraListByPostion");
                return false;
            }
        }
    }

    public boolean searchGisPointInRectangle(String sessionId, String keyString, String pointType, int numPerPage, int curPage, String xMin, String xMax, String yMin, String yMax, List<GISPointInfo> gisPointInfos) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(gisPointInfos != null && this.mspServer != null) {
                BaseGisSearchParam baseGisSearchParam = new BaseGisSearchParam();
                baseGisSearchParam.setCurPage(curPage);
                baseGisSearchParam.setKeyString(keyString);
                baseGisSearchParam.setNumPerPage(numPerPage);
                baseGisSearchParam.setPointType(pointType);
                baseGisSearchParam.setSession(sessionId);
                InRectangleGisSearchParam inRectangleGisSearchParam = new InRectangleGisSearchParam();
                inRectangleGisSearchParam.setxMax(xMax);
                inRectangleGisSearchParam.setyMax(yMax);
                inRectangleGisSearchParam.setxMin(xMin);
                inRectangleGisSearchParam.setyMin(yMin);
                LocalGisPointRequest localGisPointRequest = new LocalGisPointRequest(this.mspServer, this.getRequestTool(), baseGisSearchParam, inRectangleGisSearchParam);
                boolean isRequestOK = this.handleMspRequest(localGisPointRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    LocalGisPointResponse localGisPointResponse = new LocalGisPointResponse(localGisPointRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(localGisPointResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        Iterator var17 = localGisPointResponse.getGisPointList().iterator();

                        while(var17.hasNext()) {
                            GISPointInfo gisPointInfo = (GISPointInfo)var17.next();
                            gisPointInfos.add(gisPointInfo);
                        }

                        return true;
                    }
                }
            } else {
                CNetSDKLog.e(TAG, "searchGisPointInRectangle,gisPointInfos is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            }
        } else {
            CNetSDKLog.e(TAG, "searchGisPointInRectangle,the old vision platform dose not support to search in Rectangle.");
            return false;
        }
    }

    public boolean getBonetDetailInfo(String servAddr, String sessionID, String cameraId, BonetInfo bonetInfo) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(bonetInfo == null) {
                CNetSDKLog.e(TAG, "getBonetDetailInfo,bonetInfo is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            } else {
                BonetInfoRequest bonetInfoRequest = new BonetInfoRequest(this.mspServer, this.getRequestTool(), sessionID, cameraId);
                boolean isRequestOK = this.handleMspRequest(bonetInfoRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    BonetInfoResponse bonetInfoResponse = new BonetInfoResponse(bonetInfoRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(bonetInfoResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        BonetInfo bonetInfoTemp = bonetInfoResponse.getBonetInfo();
                        bonetInfo.setAcsIp(bonetInfoTemp.getAcsIp());
                        bonetInfo.setAcsPort(bonetInfoTemp.getAcsPort());
                        bonetInfo.setCascadeFlag(bonetInfoTemp.getCascadeFlag());
                        bonetInfo.setChannelNo(bonetInfoTemp.getChannelNo());
                        bonetInfo.setCollectedFlag(bonetInfoTemp.getCollectedFlag());
                        bonetInfo.setDeviceId(bonetInfoTemp.getDeviceId());
                        bonetInfo.setGroupId(bonetInfoTemp.getGroupId());
                        bonetInfo.setId(bonetInfoTemp.getId());
                        bonetInfo.setLatitude(bonetInfoTemp.getLatitude());
                        bonetInfo.setLongitude(bonetInfoTemp.getLongitude());
                        bonetInfo.setName(bonetInfoTemp.getName());
                        bonetInfo.setOnline(bonetInfoTemp.isOnline());
                        bonetInfo.setPTZControl(bonetInfoTemp.isPTZControl());
                        bonetInfo.setRecordPos(bonetInfoTemp.getRecordPos());
                        bonetInfo.setType(bonetInfoTemp.getType());
                        bonetInfo.setUserCapability(bonetInfoTemp.getUserCapability());
                        return true;
                    }
                }
            }
        } else {
            CNetSDKLog.e(TAG, "getBonetDetailInfo,the old vision platform dose not support to get bonetInfo.");
            return false;
        }
    }

    public boolean getAlarmDetailInfo(String servAddr, String sessionID, String alarmId, AlarmInfo alarmInfo) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(alarmInfo == null) {
                CNetSDKLog.e(TAG, "getAlarmDetailInfo,alarmInfo is null,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            } else {
                AlarmInfoRequest alarmInfoRequest = new AlarmInfoRequest(this.mspServer, this.getRequestTool(), sessionID, alarmId);
                boolean isRequestOK = this.handleMspRequest(alarmInfoRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    AlarmInfoResponse alarmInfoResponse = new AlarmInfoResponse(alarmInfoRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(alarmInfoResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        AlarmInfo tempAlarmInfo = alarmInfoResponse.getAlarmInfo();
                        alarmInfo.setId(tempAlarmInfo.getId());
                        alarmInfo.setLatitude(tempAlarmInfo.getLatitude());
                        alarmInfo.setLongitude(tempAlarmInfo.getLongitude());
                        alarmInfo.setName(tempAlarmInfo.getName());
                        alarmInfo.setUserCapability(tempAlarmInfo.getUserCapability());
                        return true;
                    }
                }
            }
        } else {
            CNetSDKLog.e(TAG, "getAlarmDetailInfo,the old vision platform dose not support to get alarmInfo.");
            return false;
        }
    }

    public boolean getCameraInfoEx(String servAddr, String sessionID, String cameraID, CameraInfoEx cameraInfoEx) {
        if(this.isPlatformNew && !this.isPlatformOldWithMag) {
            if(cameraInfoEx == null) {
                CNetSDKLog.e(TAG, "getCameraInfoEx,param error.");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            } else {
                CameraInfoRequest cameraInfoRequest = new CameraInfoRequest(this.mspServer, this.getRequestTool(), sessionID, cameraID);
                boolean isRequestOK = this.handleMspRequest(cameraInfoRequest);
                if(!isRequestOK) {
                    return false;
                } else {
                    CameraInfoResponse cameraInfoResponse = new CameraInfoResponse(cameraInfoRequest.getBackString());
                    boolean isResponseOk = this.handleMspReponse(cameraInfoResponse);
                    if(!isResponseOk) {
                        return false;
                    } else {
                        cameraInfoEx.setCameraInfoEx(cameraInfoResponse.getCameraInfo());
                        return true;
                    }
                }
            }
        } else if(this.bg_VMSNetSDK == null) {
            return false;
        } else {
            boolean isOk = this.bg_VMSNetSDK.getCameraInfoEx(this.changeServAddrToHttpAddr(servAddr), sessionID, cameraID, cameraInfoEx);
            if(!isOk) {
                this.handleOldPlatformError(this.bg_VMSNetSDK);
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean isPlatformNew() {
        return this.isPlatformNew;
    }

    public boolean ismIsSaveModel() {
        return this.mIsSafeModel;
    }

    public boolean getLatestApp(String appIdStr, String mobilePlatformAddr) {
        if(this.mHttpUtils == null) {
            CNetSDKLog.d(TAG, "getLatestApp():: mHttpUtils is null");
            return false;
        } else {
            final LatestAppResult result = new LatestAppResult();
            RequestParams params = new RequestParams();
            params.addBodyParameter("appID", appIdStr);
            params.addBodyParameter("clientType", "Android");
            this.mHttpUtils.send(HttpMethod.POST, mobilePlatformAddr + "/getLatestApp", params, new RequestCallBack<String>() {
                public void onFailure(HttpException e, String errorDesrc) {
                    CNetSDKLog.d(VMSNetSDK.TAG, "getLatestApp fail errorDesrc is " + errorDesrc);
                    if(VMSNetSDK.this.mNetCallback != null) {
                        VMSNetSDK.this.setResultCode(result, errorDesrc, e);
                        result.setResultDesrc(errorDesrc);
                        VMSNetSDK.this.mNetCallback.callback(1302, result);
                    } else {
                        CNetSDKLog.d(VMSNetSDK.TAG, "onFailure() mNetCallback is null");
                    }

                }

                public void onSuccess(ResponseInfo<String> response) {
                    boolean ret = XMLParser.parseLatestApp((String)response.result, result);
                    CNetSDKLog.d(VMSNetSDK.TAG, "onSuccess() result is " + (String)response.result);
                    if(VMSNetSDK.this.mNetCallback != null) {
                        if(ret) {
                            if(result.getResultCode() == 200) {
                                VMSNetSDK.this.mNetCallback.callback(1303, result);
                            } else {
                                VMSNetSDK.this.mNetCallback.callback(1302, result);
                            }
                        } else {
                            VMSNetSDK.this.mNetCallback.callback(1302, result);
                        }
                    } else {
                        CNetSDKLog.d(VMSNetSDK.TAG, "onSuccess() mNetCallback is null");
                    }

                }
            });
            return true;
        }
    }

    public boolean downLoadApp(String mUpdateUrl, boolean autoResume) {
        if(this.mHttpUtils == null) {
            CNetSDKLog.d(TAG, "downLoadApp():: mHttpUtils is null");
            return false;
        } else {
            CNetSDKLog.i(TAG, "mUpdateUrl ::" + mUpdateUrl);
            String filename = mUpdateUrl.substring(mUpdateUrl.lastIndexOf("/") + 1);
            final String target = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + filename;
            CNetSDKLog.i(TAG, "downLoadApp() target ::" + target);
            this.hanlderDownload = this.mHttpUtils.download(mUpdateUrl, target, autoResume, new RequestCallBack<File>() {
                public void onFailure(HttpException e, String errDesc) {
                    File f = new File(target);
                    if(f.exists()) {
                        boolean result = f.delete();
                        if(result) {
                            CNetSDKLog.d(VMSNetSDK.TAG, "删除已存在文件成功");
                        } else {
                            CNetSDKLog.d(VMSNetSDK.TAG, "删除已存在文件失败");
                        }
                    }

                    CNetSDKLog.d(VMSNetSDK.TAG, "downLoadApp(url) getLatestApp fail errorDesrc is " + errDesc);
                    if(VMSNetSDK.this.mNetCallback != null) {
                        VMSNetSDK.this.mNetCallback.callback(1010, errDesc);
                    } else {
                        CNetSDKLog.d(VMSNetSDK.TAG, "downLoadApp() mNetCallback is null");
                    }

                }

                public void onLoading(long total, long current, boolean isUploading) {
                    super.onLoading(total, current, isUploading);
                    int currentPercent = (int)((float)current / (float)total * 100.0F);
                    CNetSDKLog.d(VMSNetSDK.TAG, "downLoadApp(url) onLoading::" + currentPercent);
                    if(VMSNetSDK.this.mNetCallback != null) {
                        VMSNetSDK.this.mNetCallback.callback(1011, Integer.valueOf(currentPercent));
                    } else {
                        CNetSDKLog.d(VMSNetSDK.TAG, "downLoadApp() mNetCallback is null");
                    }

                }

                public void onSuccess(ResponseInfo<File> result) {
                    if(VMSNetSDK.this.mNetCallback != null) {
                        VMSNetSDK.this.mNetCallback.callback(1012, target);
                    } else {
                        CNetSDKLog.d(VMSNetSDK.TAG, "downLoadApp() mNetCallback is null");
                    }

                }
            });
            return true;
        }
    }

    public void clacleDown() {
        if(this.hanlderDownload != null) {
            CNetSDKLog.i(TAG, "取消下载。。。");
            this.hanlderDownload.cancel();
        }

    }

    public KmsInfo getKmsInfo() {
        if(!this.isPlatformNew) {
            CNetSDKLog.i(TAG, "getKmsInfo,no such function in old platform.default return true");
            return null;
        } else if(this.magServer == null) {
            CNetSDKLog.e(TAG, "getKmsInfo,magServer is null,param error.");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return null;
        } else {
            GetKmsRequest getKmsRequest = new GetKmsRequest(this.getNetProtocol(this.isHttpsProtocal), this.getRequestTool(), this.isHttpsProtocal);
            boolean isRequestOK = this.handleMagRequest(getKmsRequest);
            if(!isRequestOK) {
                CNetSDKLog.e(TAG, "getKmsInfo,handleMagRequest fail.");
                return null;
            } else {
                GetKmsResponse getKmsResponse = new GetKmsResponse(getKmsRequest.getBackString());
                boolean isResponseOk = this.handleMagReponse(getKmsResponse);
                KmsInfo kmsInfo = new KmsInfo();
                if(!isResponseOk) {
                    CNetSDKLog.e(TAG, "getKmsInfo,handleMagReponse fail.");
                    return null;
                } else {
                    kmsInfo.setKmsIp(getKmsResponse.getKmsIp());
                    kmsInfo.setKmsPort(getKmsResponse.getKmsPort());
                    kmsInfo.setKmsUserName(getKmsResponse.getKmsUserName());
                    kmsInfo.setKmsPassword(getKmsResponse.getKmsPassword());
                    kmsInfo.setKmsToken(getKmsResponse.getKmsToken());
                    return kmsInfo;
                }
            }
        }
    }

    public void setCallback(NetCallback callback) {
        this.mNetCallback = callback;
    }

    private void setResultCode(RequestResult result, String errorDesrc, HttpException e) {
        if(errorDesrc.contains("ConnectTimeoutException")) {
            result.setResultCode(1304);
        } else if(errorDesrc.contains("IOException")) {
            result.setResultCode(1305);
        } else {
            result.setResultCode(e.getExceptionCode());
        }

    }

    public boolean getServerConfig(String servAddr, ServerConfig serverConfig, boolean isSaveModel) {
        if(servAddr != null && serverConfig != null && servAddr.length() > 0) {
            String serverAddr = isSaveModel?this.changeServAddrToHttpsAddr(servAddr):this.changeServAddrToHttpAddr(servAddr);
            String strServer;
            if(isSaveModel) {
                strServer = String.format("%s/mobile/serverConfig?appType=%s", new Object[]{serverAddr, "5060"});
            } else {
                strServer = String.format("%s/mobile/serverConfig", new Object[]{serverAddr});
            }

            this.requestTool = this.getRequestTool();
            String xmlServerConfig;
            if(isSaveModel) {
                xmlServerConfig = this.requestTool.httpsPostRequest(strServer, (String)null);
            } else {
                xmlServerConfig = this.requestTool.httpGetRequest(strServer);
            }

            if(TextUtils.isEmpty(xmlServerConfig)) {
                this.mLastError = this.requestTool.getLastErrorCode();
                this.mLastErrorDescribe = this.requestTool.getLastErrorDesc();
                return false;
            } else {
                boolean ret = XMLParser.parseServerConfig(xmlServerConfig, serverConfig);
                if(!ret) {
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return ret;
                } else {
                    return ret;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getLineList() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    private boolean getServerConfig(String servAddr, ServerConfig serverConfig) {
        return this.getServerConfig(servAddr, serverConfig, false);
    }

    public boolean searchCamera(String servAddr, String sessionId, String cameraName, int numPerPage, int curpage, List<CameraInfo> cameraList) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionId) && !TextUtils.isEmpty(cameraName)) {
            String strServer = null;
            if(this.isPlatformNew && !this.isPlatformOldWithMag) {
                strServer = String.format("%s/mobile/searchCamera", new Object[]{this.changeServAddrToHttpsAddr(servAddr)});
            } else {
                strServer = String.format("%s/mobile/searchCamera", new Object[]{this.changeServAddrToHttpAddr(servAddr)});
            }

            cameraName = EnCode.enCodeByUtf8(cameraName);
            String strData = String.format(Locale.ENGLISH, "sessionID=%s&cameraName=%s&numPerPage=%d&curPage=%d", new Object[]{sessionId, cameraName, Integer.valueOf(numPerPage), Integer.valueOf(curpage)});
            this.requestTool = this.getRequestTool();
            String xmlSearchCamera = null;
            if(this.isPlatformNew && !this.isPlatformOldWithMag) {
                xmlSearchCamera = this.requestTool.httpsPostRequest(strServer, strData);
            } else {
                xmlSearchCamera = this.requestTool.httpGetRequest(strServer + "?" + strData);
            }

            if(TextUtils.isEmpty(xmlSearchCamera)) {
                this.mLastError = this.requestTool.getLastErrorCode();
                this.mLastErrorDescribe = this.requestTool.getLastErrorDesc();
                return false;
            } else {
                boolean ret = XMLParser.parseCameraListInfo(xmlSearchCamera, cameraList);
                if(!ret) {
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return ret;
                } else {
                    return ret;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::searchCamera() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean isPlatformOldWithMag() {
        return this.isPlatformOldWithMag;
    }

    private String getAddressIp(String serverAddress) {
        MspServer mspServer = new MspServer(serverAddress);
        return mspServer.getIp();
    }

    public static VMSNetSDK getVmsNetSdk() {
        return new VMSNetSDK();
    }

    public boolean isHttpsProtocol() {
        return this.mServerInfo != null && this.mServerInfo.getIsHttp() != -1?this.mServerInfo.getIsHttp() != 1:this.isHttpsProtocal;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public CameraInfo getRecordPos(String cameraID, String sessionID, String serverAddr) {
        if(!TextUtils.isEmpty(cameraID) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(serverAddr)) {
            String strServer = String.format("%s/mobile/getRecordPos", new Object[]{this.changeServAddrToHttpsAddr(serverAddr)});
            String strData = String.format(Locale.ENGLISH, "sessionID=%s&cameraID=%s", new Object[]{sessionID, cameraID});
            this.requestTool = this.getRequestTool();
            String xmlRecordPosInfo = this.requestTool.httpsPostRequest(strServer, strData);
            if(TextUtils.isEmpty(xmlRecordPosInfo)) {
                this.mLastError = this.requestTool.getLastErrorCode();
                this.mLastErrorDescribe = this.requestTool.getLastErrorDesc();
                return null;
            } else {
                CameraInfo cameraInfo = new CameraInfo();
                boolean ret = XMLParser.parseRecordPosInfo(xmlRecordPosInfo, cameraInfo);
                if(!ret) {
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return null;
                } else {
                    return cameraInfo;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getRecordPos() input param error");
            return null;
        }
    }

    public boolean getVerifCode(String serverAddr, ServInfo servInfo) {
        if(servInfo == null) {
            CNetSDKLog.d(TAG, "VMSNetSDK::getVerifCode() input param error");
            return false;
        } else {
            String strServer = String.format("%s/mobile/verifCode", new Object[]{this.changeServAddrToHttpsAddr(serverAddr)});
            this.requestTool = this.getRequestTool();
            String xmlVerifCodeInfo = this.requestTool.httpsPostRequest(strServer, (String)null);
            if(TextUtils.isEmpty(xmlVerifCodeInfo)) {
                this.mLastError = this.requestTool.getLastErrorCode();
                this.mLastErrorDescribe = this.requestTool.getLastErrorDesc();
                return false;
            } else {
                boolean ret = XMLParser.parseVerifCodeInfo(xmlVerifCodeInfo, servInfo);
                if(!ret) {
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return ret;
                }
            }
        }
    }
}
