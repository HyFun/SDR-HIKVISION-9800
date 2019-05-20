//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hikvision.vmsnetsdk.bygone;

import android.text.TextUtils;

import com.hikvision.vmsnetsdk.ABS_TIME;
import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.CameraInfo;
import com.hikvision.vmsnetsdk.CameraInfoEx;
import com.hikvision.vmsnetsdk.ControlUnitInfo;
import com.hikvision.vmsnetsdk.GISCameraInfo;
import com.hikvision.vmsnetsdk.LineInfo;
import com.hikvision.vmsnetsdk.RealPlayURL;
import com.hikvision.vmsnetsdk.RecordInfo;
import com.hikvision.vmsnetsdk.RegionInfo;
import com.hikvision.vmsnetsdk.SDKAlarmBulletinDetail;
import com.hikvision.vmsnetsdk.SDKBulletin;
import com.hikvision.vmsnetsdk.SDKBulletinDetail;
import com.hikvision.vmsnetsdk.SDKGISInitInfo;
import com.hikvision.vmsnetsdk.SDKUnBullentinCount;
import com.hikvision.vmsnetsdk.ServerConfig;
import com.hikvision.vmsnetsdk.ServerVersionInfo;
import com.hikvision.vmsnetsdk.XMLParser;
import com.hikvision.vmsnetsdk.netLayer.msp.deviceGps.GISTrackPoint;
import com.hikvision.vmsnetsdk.netLayer.msp.deviceInfo.DeviceInfo;
import com.hikvision.vmsnetsdk.netLayer.msp.gisPoint.GISPointInfo;
import com.hikvision.vmsnetsdk.util.EnCode;
import com.hikvision.vmsnetsdk.util.HttpUtil;
import com.hikvision.vmsnetsdk.util.intf.IRequestTool;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class BG_VMSNetSDK {
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
    public static final int VMSNETSDK_MSP_UNKNOWN_ERROR = 199;
    public static final int VMSNETSDK_MSP_SERVER_EXCEPTION = 200;
    public static final int VMSNETSDK_MSP_NO_SUPPORT = 207;
    public static final int VMSNETSDK_MSP_TIP = 231;
    private static String TAG = "VMSNetSDK";
    private static BG_VMSNetSDK mVMSNetSDK = null;
    private static final String SDK_VERSION = "V1.10 build20130408";
    private int mLastError = 0;
    private String mLastErrorDescribe = "no error";
    private boolean mIsPrintLog = false;
    private IRequestTool mRequestTool = new HttpUtil();
    private boolean mIsSaveModel;

    private BG_VMSNetSDK() {
    }

    public static BG_VMSNetSDK getInstance() {
        if(mVMSNetSDK == null) {
            mVMSNetSDK = new BG_VMSNetSDK();
        }

        return mVMSNetSDK;
    }

    public void setRequestTool(IRequestTool requestTool) {
        if(requestTool == null) {
            this.mRequestTool = new HttpUtil();
        } else {
            this.mRequestTool = requestTool;
        }
    }

    public void openLog(boolean isPrintLog) {
        this.mIsPrintLog = isPrintLog;
    }

    public void setTimeOut(int milliSecond) {
        if(this.mRequestTool != null) {
            this.mRequestTool.setTimeout(milliSecond);
        }

    }

    public boolean getServerVersion(String servAddr, ServerVersionInfo serverVersionInfo) {
        if(servAddr != null && serverVersionInfo != null) {
            String strServAddr = String.format("%s/mobile/getServerInfo", new Object[]{servAddr});
            String strResponse = this.syncPostRequest(strServAddr, (String)null);
            if(strResponse == null) {
                return false;
            } else {
                if(this.mIsPrintLog) {
                    CNetSDKLog.i(TAG, "getServerVersion():: xml is " + strResponse);
                }

                boolean ret = XMLParser.parseServVersionInfo(strResponse, serverVersionInfo);
                if(!ret) {
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getServerVersion() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean getLineList(String servAddr, List<LineInfo> lineInfoList) {
        if(servAddr != null && lineInfoList != null && servAddr.length() > 0) {
            String strServAddr = String.format("%s/mobile/getLine", new Object[]{servAddr});
            String strResponse = this.syncPostRequest(strServAddr, (String)null);
            if(strResponse == null) {
                return false;
            } else {
                if(this.mIsPrintLog) {
                    CNetSDKLog.i(TAG, "getLineList():: xml is " + strResponse);
                }

                boolean ret = XMLParser.parseLineInfoList(strResponse, lineInfoList);
                if(!ret) {
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getLineList() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    private String syncPostRequest(String strServAddr, Object object, boolean isSaveModel) {
        return null;
    }

    public String getLoginRequestData(String userName, String password, int lineID, String macAddress, int passwordEncryptionMode, int pwdLevel, String ip) {
        if(userName != null && password != null && userName.length() > 0) {
            String userNameEnc = null;

            try {
                userNameEnc = URLEncoder.encode(URLEncoder.encode(userName, "utf-8"), "utf-8");
            } catch (UnsupportedEncodingException var10) {
                var10.printStackTrace();
                this.mLastError = 100;
                this.mLastErrorDescribe = "user name encorde error";
                return null;
            }

            CNetSDKLog.d(TAG, "getLoginRequestData() ::passwordEncryptionMode  :" + passwordEncryptionMode);
            if(passwordEncryptionMode == 0) {
                password = this.md5Crypto(password);
            } else {
                password = EnCode.enCodeBySHA256(password);
            }

            String strData = "";
            if(macAddress == null) {
                strData = String.format(Locale.ENGLISH, "username=%s&password=%s&line=%d&loginAddr=%s&passwordLevel=%d", new Object[]{userNameEnc, password, Integer.valueOf(lineID), ip, Integer.valueOf(pwdLevel)});
            } else {
                strData = String.format(Locale.ENGLISH, "username=%s&password=%s&line=%d&mac=%s&passwordLevel=%d&loginAddr=%s", new Object[]{userNameEnc, password, Integer.valueOf(lineID), macAddress, Integer.valueOf(pwdLevel), ip});
            }

            return strData;
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::login() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return null;
        }
    }

    public String getLoginRequestAddr(String servAddr) {
        if(servAddr != null && servAddr.length() > 0) {
            String strServAddr = String.format("%s/mobile/login", new Object[]{servAddr});
            return strServAddr;
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::login() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return null;
        }
    }

    private String md5Crypto(String src) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            byte[] btInput = src.getBytes("utf-8");
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for(int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            return new String(str);
        } catch (Exception var11) {
            var11.printStackTrace();
            return null;
        }
    }

    public boolean logout(String servAddr, String sessionID, String deviceID) {
        if(servAddr != null && sessionID != null && servAddr.length() > 0 && sessionID.length() > 0) {
            String strServAddr = String.format("%s/mobile/logout", new Object[]{servAddr});
            String strData = String.format("sessionID=%s&tokenID=%s", new Object[]{sessionID, deviceID});
            String strResponse = this.syncPostRequest(strServAddr, strData);
            if(strResponse == null) {
                return false;
            } else {
                if(this.mIsPrintLog) {
                    CNetSDKLog.i(TAG, "logout():: xml is " + strResponse);
                }

                boolean ret = XMLParser.parseLogoutInfo(strResponse);
                if(!ret) {
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::logout() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean getControlUnitList(String servAddr, String sessionID, String controlUnitID, int numPerPage, int curPage, List<ControlUnitInfo> controlUnitList) {
        if(servAddr != null && sessionID != null && controlUnitList != null && servAddr.length() > 0 && sessionID.length() > 0 && numPerPage > 0 && curPage > 0) {
            String strServAddr = String.format("%s/mobile/getControlUnit", new Object[]{servAddr});
            String paramData = null;
            paramData = String.format(Locale.ENGLISH, "sessionID=%s&curControlUnitID=%s&numPerPage=%d&curPage=%d", new Object[]{sessionID, controlUnitID, Integer.valueOf(numPerPage), Integer.valueOf(curPage)});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                if(this.mIsPrintLog) {
                    CNetSDKLog.i(TAG, "getControlUnitList():: xml is " + strResponse);
                }

                boolean ret = XMLParser.parseControlUnitListInfo(strResponse, controlUnitList);
                if(!ret) {
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getControlUnitList() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean getRegionListFromCtrlUnit(String servAddr, String sessionID, String controlUnitID, int numPerPage, int curPage, List<RegionInfo> regionList) {
        if(servAddr != null && sessionID != null && regionList != null && servAddr.length() > 0 && sessionID.length() > 0 && numPerPage > 0 && curPage > 0) {
            String strServAddr = String.format("%s/mobile/getRegion", new Object[]{servAddr});
            String paramData = null;
            paramData = String.format(Locale.ENGLISH, "sessionID=%s&curControlUnitID=%s&numPerPage=%d&curPage=%d", new Object[]{sessionID, controlUnitID, Integer.valueOf(numPerPage), Integer.valueOf(curPage)});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                if(this.mIsPrintLog) {
                    CNetSDKLog.i(TAG, "getRegionListFromCtrlUnit():: xml is " + strResponse);
                }

                boolean ret = XMLParser.parseRegionListInfo(strResponse, regionList);
                if(!ret) {
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getControlUnitList() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean getRegionListFromRegion(String servAddr, String sessionID, String regionID, int numPerPage, int curPage, List<RegionInfo> regionList) {
        if(servAddr != null && sessionID != null && regionList != null && servAddr.length() > 0 && sessionID.length() > 0 && numPerPage > 0 && curPage > 0) {
            String strServAddr = String.format("%s/mobile/getRegion", new Object[]{servAddr});
            String paramData = String.format(Locale.ENGLISH, "sessionID=%s&curRegionID=%s&numPerPage=%d&curPage=%d", new Object[]{sessionID, regionID, Integer.valueOf(numPerPage), Integer.valueOf(curPage)});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                if(this.mIsPrintLog) {
                    CNetSDKLog.i(TAG, "getRegionListFromRegion():: xml is " + strResponse);
                }

                boolean ret = XMLParser.parseRegionListInfo(strResponse, regionList);
                if(!ret) {
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getControlUnitList() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean getAllCameraList(String servAddr, String sessionID, int numPerPage, int curPage, List<CameraInfo> cameraList) {
        if(servAddr != null && sessionID != null && cameraList != null && servAddr.length() > 0 && sessionID.length() > 0 && numPerPage > 0 && curPage > 0) {
            String strServAddr = String.format("%s/mobile/getCameraInfo", new Object[]{servAddr});
            String paramData = String.format(Locale.ENGLISH, "sessionID=%s&numPerPage=%d&curPage=%d", new Object[]{sessionID, Integer.valueOf(numPerPage), Integer.valueOf(curPage)});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                if(this.mIsPrintLog) {
                    CNetSDKLog.i(TAG, "getAllCameraList():: xml is " + strResponse);
                }

                boolean ret = XMLParser.parseCameraListInfo(strResponse, cameraList);
                if(!ret) {
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getCameraListFromCtrlUnit() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean getCameraListFromCtrlUnit(String servAddr, String sessionID, String controlUnitID, int numPerPage, int curPage, List<CameraInfo> cameraList) {
        if(servAddr != null && sessionID != null && cameraList != null && servAddr.length() > 0 && sessionID.length() > 0 && numPerPage > 0 && curPage > 0) {
            String strServAddr = String.format("%s/mobile/getCameraInfo", new Object[]{servAddr});
            String paramData = null;
            paramData = String.format(Locale.ENGLISH, "sessionID=%s&curControlUnitID=%s&numPerPage=%d&curPage=%d", new Object[]{sessionID, controlUnitID, Integer.valueOf(numPerPage), Integer.valueOf(curPage)});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                if(this.mIsPrintLog) {
                    CNetSDKLog.i(TAG, "getCameraListFromCtrlUnit():: xml is " + strResponse);
                }

                boolean ret = XMLParser.parseCameraListInfo(strResponse, cameraList);
                if(!ret) {
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getCameraListFromCtrlUnit() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean getCameraListFromRegion(String servAddr, String sessionID, String regionID, int numPerPage, int curPage, List<CameraInfo> cameraList) {
        if(servAddr != null && sessionID != null && cameraList != null && servAddr.length() > 0 && sessionID.length() > 0 && numPerPage > 0 && curPage > 0) {
            String strServAddr = String.format("%s/mobile/getCameraInfo", new Object[]{servAddr});
            String paramData = String.format(Locale.ENGLISH, "sessionID=%s&curRegionID=%s&numPerPage=%d&curPage=%d", new Object[]{sessionID, regionID, Integer.valueOf(numPerPage), Integer.valueOf(curPage)});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                if(this.mIsPrintLog) {
                    CNetSDKLog.i(TAG, "getCameraListFromRegion():: xml is " + strResponse);
                }

                boolean ret = XMLParser.parseCameraListInfo(strResponse, cameraList);
                if(!ret) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::getCameraListFromRegion() parse camera list info fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getCameraListFromRegion() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean getDeviceInfo(String servAddr, String sessionID, String deviceID, DeviceInfo deviceInfo) {
        if(servAddr != null && sessionID != null && deviceID != null && deviceInfo != null && servAddr.length() > 0 && sessionID.length() > 0 && deviceID.length() > 0) {
            String strServAddr = String.format("%s/mobile/getDeviceInfo", new Object[]{servAddr});
            String paramData = String.format("sessionID=%s&deviceID=%s", new Object[]{sessionID, deviceID});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                if(this.mIsPrintLog) {
                    CNetSDKLog.i(TAG, "getDeviceInfo():: xml is " + strResponse);
                }

                boolean ret = XMLParser.parseDeviceInfo(strResponse, deviceInfo);
                if(!ret) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::getDeviceInfo() parse camera list info fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getDeviceInfo() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean getDeviceGPSTrackInfoByIndex(String servAddr, String sessionID, String deviceIndexCode, int numPerPage, int currentPage, String startTime, String endTime, List<GISTrackPoint> trackList) {
        if(servAddr != null && sessionID != null && deviceIndexCode != null && numPerPage > 0 && currentPage >= 0 && startTime != null && endTime != null && trackList != null && !deviceIndexCode.equalsIgnoreCase("") && !sessionID.equalsIgnoreCase("") && !servAddr.equalsIgnoreCase("") && !startTime.equalsIgnoreCase("") && !endTime.equalsIgnoreCase("")) {
            String deviceIndexCodeEnc = null;

            try {
                deviceIndexCodeEnc = URLEncoder.encode(URLEncoder.encode(deviceIndexCode, "utf-8"), "utf-8");
            } catch (UnsupportedEncodingException var14) {
                var14.printStackTrace();
                this.mLastError = 100;
                this.mLastErrorDescribe = "user name encorde error";
                return false;
            }

            String strServAddr = String.format("%s/mobile/getDeviceGPS", new Object[]{servAddr});
            String paramData = String.format(Locale.ENGLISH, "sessionID=%s&indexCode=%s&numPerPage=%d&curPage=%d&startTime=%s&endTime=%s", new Object[]{sessionID, deviceIndexCodeEnc, Integer.valueOf(numPerPage), Integer.valueOf(currentPage), startTime, endTime});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                if(this.mIsPrintLog) {
                    CNetSDKLog.i(TAG, "getDeviceGPSTrackInfoByIndex():: xml is " + strResponse);
                }

                boolean ret = XMLParser.parseGISTrackListInfo(strResponse, trackList);
                if(!ret) {
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getDeviceGPSTrackInfoByName() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public String getGISPointTypeFromServer(String servAddr, String sessionID) {
        if(sessionID != null && !sessionID.equalsIgnoreCase("")) {
            String strServAddr = String.format("%s/mobile/getPointType", new Object[]{servAddr});
            String paramData = String.format("sessionID=%s", new Object[]{sessionID});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return null;
            } else {
                if(this.mIsPrintLog) {
                    CNetSDKLog.i(TAG, "getGISPointTypeFromServer():: xml is " + strResponse);
                }

                String pointType = XMLParser.parseGISPointType(strResponse);
                if(pointType != null && pointType.length() > 0) {
                    return pointType;
                } else {
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return null;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getGISPointTypeFromServer() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return null;
        }
    }

    public boolean getGISPointInfo(String servAddr, String sessionID, ArrayList<Integer> pointTypes, int numPerPage, int curPage, List<GISPointInfo> gisInfoList) {
        return true;
    }

    public boolean getRealPlayURL(String servAddr, String sessionID, String cameraID, int streamType, RealPlayURL realPlayURL) {
        if(servAddr != null && sessionID != null && cameraID != null && realPlayURL != null && servAddr.length() > 0 && sessionID.length() > 0 && cameraID.length() > 0) {
            String strServAddr = String.format("%s/mobile/getRealPlayURL", new Object[]{servAddr});
            CNetSDKLog.d(TAG, strServAddr);
            String paramData = String.format(Locale.ENGLISH, "sessionID=%s&cameraID=%s&streamType=%d", new Object[]{sessionID, cameraID, Integer.valueOf(streamType)});
            CNetSDKLog.d(TAG, paramData);
            String strResponse = this.syncPostRequest(strServAddr, paramData);
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

    public boolean queryCameraRecord(String servAddr, String sessionID, String cameraID, String recordType, String recordPos, ABS_TIME startTime, ABS_TIME endTime, RecordInfo recordInfo) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(cameraID) && recordInfo != null) {
            if(recordType != null && recordPos != null) {
                String strServAddr = String.format("%s/mobile/queryCameraRecord", new Object[]{servAddr});
                CNetSDKLog.d(TAG, "VMSNetSDK::queryCameraRecord() ServAddr:" + strServAddr);
                String strStartTime = this.ABSTime2String(startTime);
                String strEndTime = this.ABSTime2String(endTime);
                String paramData = String.format("sessionID=%s&cameraID=%s&recordType=%s&recordPos=%s&startTime=%s&endTime=%s", new Object[]{sessionID, cameraID, recordType, recordPos, strStartTime, strEndTime});
                CNetSDKLog.d(TAG, "VMSNetSDK::queryCameraRecord() request:" + paramData);
                String strResponse = this.syncPostRequest(strServAddr, paramData);
                if(strResponse == null) {
                    return false;
                } else {
                    if(this.mIsPrintLog) {
                        CNetSDKLog.i(TAG, "queryCameraRecord():: xml is " + strResponse);
                    }

                    boolean ret = XMLParser.parseRecordInfo(strResponse, recordInfo);
                    if(!ret) {
                        CNetSDKLog.d(TAG, "VMSNetSDK::queryCameraRecord() parse camera list info fail");
                        this.mLastError = XMLParser.getError();
                        this.mLastErrorDescribe = XMLParser.getErrorDesc();
                        return false;
                    } else {
                        return true;
                    }
                }
            } else {
                CNetSDKLog.d(TAG, "VMSNetSDK::queryCameraRecord() input param error");
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return false;
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::queryCameraRecord() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public synchronized boolean getUnreadMsgCount(String servAddr, String sessionID, SDKUnBullentinCount msgCount) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && msgCount != null) {
            String strServAddr = String.format("%s/mobile/getUnreadMsgCount", new Object[]{servAddr});
            CNetSDKLog.d(TAG, "VMSNetSDK::getUnreadMsgCount() ServAddr:" + strServAddr);
            String paramData = String.format("sessionID=%s", new Object[]{sessionID});
            CNetSDKLog.d(TAG, "VMSNetSDK::getUnreadMsgCount() request:" + paramData);
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                CNetSDKLog.d(TAG, strResponse);
                boolean ret = XMLParser.parseUnMsgCount(strResponse, msgCount);
                if(!ret && 160 != XMLParser.getError()) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::getUnreadMsgCount() parse unMessage fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getUnreadMsgCount() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public synchronized boolean getMsgList(String servAddr, String sessionID, int number, List<SDKBulletin> bullentinList) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && bullentinList != null) {
            String strServAddr = String.format("%s/mobile/getMsgList", new Object[]{servAddr});
            CNetSDKLog.d(TAG, "VMSNetSDK::getMsgList() ServAddr:" + strServAddr);
            String paramData = String.format(Locale.ENGLISH, "sessionID=%s&number=%d", new Object[]{sessionID, Integer.valueOf(number)});
            CNetSDKLog.d(TAG, "VMSNetSDK::getMsgList() request:" + paramData);
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                CNetSDKLog.d(TAG, strResponse);
                boolean ret = XMLParser.parseBulletinList(strResponse, bullentinList);
                if(!ret && 160 != XMLParser.getError()) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::getMsgList() parse BulletinList fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getMsgList() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public synchronized boolean getMsgHistoryList(String servAddr, String sessionID, String msgID, String type, int number, List<SDKBulletin> bullentinList) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && bullentinList != null) {
            String strServAddr = String.format("%s/mobile/getMsgHistoryList", new Object[]{servAddr});
            CNetSDKLog.d(TAG, "VMSNetSDK::getMsgHistoryList() ServAddr:" + strServAddr);
            String paramData = String.format(Locale.ENGLISH, "sessionID=%s&msgID=%s&type=%s&number=%d", new Object[]{sessionID, msgID, type, Integer.valueOf(number)});
            CNetSDKLog.d(TAG, "VMSNetSDK::getMsgHistoryList() request:" + paramData);
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                CNetSDKLog.d(TAG, strResponse);
                boolean ret = XMLParser.parseBulletinList(strResponse, bullentinList);
                if(!ret && 160 != XMLParser.getError()) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::getMsgHistoryList() parse BullentinList fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getMsgHistoryList() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public synchronized boolean getAlarmMsgDetailList(String servAddr, String sessionID, String type, int number, List<SDKAlarmBulletinDetail> alarmbltnDetailList) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(type) && alarmbltnDetailList != null) {
            String strServAddr = String.format("%s/mobile/getMsgDetailList", new Object[]{servAddr});
            CNetSDKLog.d(TAG, "VMSNetSDK::getAlarmMsgDetailList() ServAddr:" + strServAddr);
            String paramData = String.format(Locale.ENGLISH, "sessionID=%s&type=%s&number=%d", new Object[]{sessionID, type, Integer.valueOf(number)});
            CNetSDKLog.d(TAG, "VMSNetSDK::getAlarmMsgDetailList() request:" + paramData);
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                CNetSDKLog.d(TAG, strResponse);
                boolean ret = XMLParser.parseAlarmBulletinDetailList(strResponse, alarmbltnDetailList);
                if(!ret && 160 != XMLParser.getError()) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::getAlarmMsgDetailList() parse unMessage fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getAlarmMsgDetailList() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public synchronized boolean getAlarmMsgDetailHistoryList(String servAddr, String sessionID, String msgID, String type, int number, List<SDKAlarmBulletinDetail> alarmbltnDetailList) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(msgID) && !TextUtils.isEmpty(type) && alarmbltnDetailList != null) {
            String strServAddr = String.format("%s/mobile/getMsgDetailList", new Object[]{servAddr});
            CNetSDKLog.d(TAG, "VMSNetSDK::getAlarmMsgDetailHistoryList() ServAddr:" + strServAddr);
            String paramData = String.format(Locale.ENGLISH, "sessionID=%s&msgID=%s&type=%s&number=%d", new Object[]{sessionID, msgID, type, Integer.valueOf(number)});
            CNetSDKLog.d(TAG, "VMSNetSDK::getAlarmMsgDetailHistoryList() request:" + paramData);
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                CNetSDKLog.d(TAG, strResponse);
                boolean ret = XMLParser.parseAlarmBulletinDetailList(strResponse, alarmbltnDetailList);
                if(!ret && 160 != XMLParser.getError()) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::getAlarmMsgDetailHistoryList() parse unMessage fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getAlarmMsgDetailHistoryList() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public synchronized boolean getMsgDetailList(String servAddr, String sessionID, String type, int number, List<SDKBulletinDetail> bltnDetailList) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(type) && bltnDetailList != null) {
            String strServAddr = String.format("%s/mobile/getMsgDetailList", new Object[]{servAddr});
            CNetSDKLog.d(TAG, "VMSNetSDK::getMsgDetailList() ServAddr:" + strServAddr);
            String paramData = String.format(Locale.ENGLISH, "sessionID=%s&type=%s&number=%d", new Object[]{sessionID, type, Integer.valueOf(number)});
            CNetSDKLog.d(TAG, "VMSNetSDK::getMsgDetailList() request:" + paramData);
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                CNetSDKLog.d(TAG, strResponse);
                boolean ret = XMLParser.parseBulletinDetailList(strResponse, bltnDetailList);
                if(!ret && 160 != XMLParser.getError()) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::getMsgDetailList() parse unMessage fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getMsgDetailList() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public synchronized boolean getMsgDetailHistoryList(String servAddr, String sessionID, String msgID, String type, int number, List<SDKBulletinDetail> bltnDetailList) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(type) && bltnDetailList != null) {
            String strServAddr = String.format("%s/mobile/getMsgDetailHistoryList", new Object[]{servAddr});
            CNetSDKLog.d(TAG, "VMSNetSDK::getMsgDetailHistoryList() ServAddr:" + strServAddr);
            String paramData = String.format(Locale.ENGLISH, "sessionID=%s&msgID=%s&type=%s&number=%d", new Object[]{sessionID, msgID, type, Integer.valueOf(number)});
            CNetSDKLog.d(TAG, "VMSNetSDK::getMsgDetailHistoryList() request:" + paramData);
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                CNetSDKLog.d(TAG, strResponse);
                boolean ret = XMLParser.parseBulletinDetailList(strResponse, bltnDetailList);
                if(!ret && 160 != XMLParser.getError()) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::getMsgDetailHistoryList() parse unMessage fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getMsgDetailHistoryList() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public synchronized boolean getMsgDetail(String servAddr, String sessionID, String msgID, SDKBulletinDetail bltnDetail) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(msgID) && bltnDetail != null) {
            String strServAddr = String.format("%s/mobile/getMsgDetail", new Object[]{servAddr});
            String paramData = String.format("sessionID=%s&msgID=%s", new Object[]{sessionID, msgID});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                CNetSDKLog.d(TAG, strResponse);
                boolean ret = XMLParser.parseBulletinDetail(strResponse, bltnDetail);
                if(!ret && 160 != XMLParser.getError()) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::getMsgDetail() parse parseBulletinDetail fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getMsgDetail() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public synchronized boolean getAlarmMsgDetail(String servAddr, String sessionID, String msgID, SDKAlarmBulletinDetail alarmBltnDetail) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(msgID) && alarmBltnDetail != null) {
            String strServAddr = String.format("%s/mobile/getMsgDetail", new Object[]{servAddr});
            CNetSDKLog.d(TAG, "VMSNetSDK::getAlarmMsgDetail() ServAddr:" + strServAddr);
            String paramData = String.format("sessionID=%s&msgID=%s", new Object[]{sessionID, msgID});
            CNetSDKLog.d(TAG, "VMSNetSDK::getAlarmMsgDetail() request:" + paramData);
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                CNetSDKLog.d(TAG, strResponse);
                boolean ret = XMLParser.parseAlarmBulletinDetail(strResponse, alarmBltnDetail);
                if(!ret && 160 != XMLParser.getError()) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::getAlarmMsgDetail() parse parseAlarmBulletinDetail fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getAlarmMsgDetail() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean deleteMsgByType(String servAddr, String sessionID, String type) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(type)) {
            String strServAddr = String.format("%s/mobile/deleteMsg", new Object[]{servAddr});
            String paramData = String.format("sessionID=%s&type=%s", new Object[]{sessionID, type});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                CNetSDKLog.d(TAG, strResponse);
                boolean ret = XMLParser.parseResultCode(strResponse);
                if(!ret) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::type() parse camera list info fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::type input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean deleteMsgByID(String servAddr, String sessionID, String MsgID) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(MsgID)) {
            String strServAddr = String.format("%s/mobile/deleteMsg", new Object[]{servAddr});
            String paramData = String.format("sessionID=%s&msgID=%s", new Object[]{sessionID, MsgID});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                CNetSDKLog.d(TAG, strResponse);
                boolean ret = XMLParser.parseResultCode(strResponse);
                if(!ret) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::deleteMsgByID() parse camera list info fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::deleteMsgByID input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean checkMsgByID(String servAddr, String sessionID, String MsgID) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(MsgID)) {
            String strServAddr = String.format("%s/mobile/checkMsg", new Object[]{servAddr});
            String paramData = String.format("sessionID=%s&msgID=%s", new Object[]{sessionID, MsgID});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                CNetSDKLog.d(TAG, strResponse);
                boolean ret = XMLParser.parseResultCode(strResponse);
                if(!ret) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::checkMsgByID() parse camera list info fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::checkMsgByID input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean checkMsgByType(String servAddr, String sessionID, String type) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(type)) {
            String strServAddr = String.format("%s/mobile/checkMsg", new Object[]{servAddr});
            String paramData = String.format("sessionID=%s&type=%s", new Object[]{sessionID, type});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                CNetSDKLog.d(TAG, strResponse);
                boolean ret = XMLParser.parseResultCode(strResponse);
                if(!ret) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::checkMsgByType() parse camera list info fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::checkMsgByType input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public synchronized boolean getGISInitInfo(String servAddr, String sessionID, SDKGISInitInfo sdkGISInitInfo) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && sdkGISInitInfo != null) {
            String strServAddr = String.format("%s/mobile/getGISInitInfo", new Object[]{servAddr});
            CNetSDKLog.d(TAG, "VMSNetSDK::getGISInitInfo() ServAddr:" + strServAddr);
            String paramData = String.format("sessionID=%s", new Object[]{sessionID});
            CNetSDKLog.d(TAG, "VMSNetSDK::getGISInitInfo() request:" + paramData);
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                CNetSDKLog.d(TAG, strResponse);
                boolean ret = XMLParser.parseGisInfo(strResponse, sdkGISInitInfo);
                if(!ret) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::getMsgDetailInfo() parse unMessage fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getGISInitInfo input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public synchronized boolean sendDeviceId(String servAddr, String sessionID, String deviceId, String appType) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(deviceId) && !TextUtils.isEmpty(appType)) {
            String strServAddr = String.format("%s/mobile/pushServerInfo", new Object[]{servAddr});
            CNetSDKLog.d(TAG, "VMSNetSDK::sendDeviceId() ServAddr:" + strServAddr);
            String paramData = String.format("sessionID=%s&tokenID=%s&appType=%s", new Object[]{sessionID, deviceId, appType});
            CNetSDKLog.d(TAG, "VMSNetSDK::sendDeviceId() request:" + paramData);
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                CNetSDKLog.d(TAG, strResponse);
                boolean ret = XMLParser.parseResultCode(strResponse);
                if(!ret) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::checkBullentin() parse camera list info fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getArcGISInitInfo input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean modifyGISInfo(String servAddr, String sessionID, String cameraID, double longitude, double latitude) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(cameraID)) {
            String strServAddr = String.format("%s/mobile/modifyGISInfo", new Object[]{servAddr});
            String paramData = String.format(Locale.ENGLISH, "sessionID=%s&cameraID=%s&longitude=%f&latitude=%f", new Object[]{sessionID, cameraID, Double.valueOf(longitude), Double.valueOf(latitude)});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                CNetSDKLog.d(TAG, strResponse);
                boolean ret = XMLParser.parseResultCode(strResponse);
                if(!ret) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::modifyGISInfo() parse camera list info fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::modifyGISInfo input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    private String ABSTime2String(ABS_TIME time) {
        if(time == null) {
            return "";
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.set(time.dwYear, time.dwMonth, time.dwDay, time.dwHour, time.dwMinute, time.dwSecond);
            String retTime = "" + calendar.getTimeInMillis();
            return retTime;
        }
    }

    public boolean stopPlayback(String servAddr, String sessionID, String cameraID) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(cameraID)) {
            String strServAddr = String.format("%s/mobile/stopPlayback", new Object[]{servAddr});
            String paramData = String.format("sessionID=%s&cameraID=%s", new Object[]{sessionID, cameraID});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            return !TextUtils.isEmpty(strResponse);
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::stopPlayback() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public synchronized boolean sendStartPTZCmd(String servAddr, int port, String sessionID, String cameraID, int cmdID, int param1, int param2) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(cameraID)) {
            boolean ret = true;
            DatagramSocket cmdSocket = null;

            try {
                String str = String.format(Locale.ENGLISH, "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r%n<Message Version=\"3.0\">\r%n<PTZ_HEADER MessageType=\"MSG_PTZ_SET_REQ\" SessionID=\"%s\" CameraID=\"%s\"/>\r%n<PTZ_PARAM OpID=\"%d\" Param1=\"%d\" Param2=\"%d\"/>\r%n</Message>", new Object[]{sessionID, cameraID, Integer.valueOf(cmdID), Integer.valueOf(param1), Integer.valueOf(param2)});
                cmdSocket = new DatagramSocket(port);
                InetAddress addr = InetAddress.getByName(servAddr);
                byte[] data = str.getBytes("utf-8");
                DatagramPacket packet = new DatagramPacket(data, data.length, addr, port);
                cmdSocket.send(packet);
            } catch (Exception var17) {
                var17.printStackTrace();
                CNetSDKLog.d(TAG, "exception:" + var17.getLocalizedMessage());
                ret = false;
            } finally {
                if(cmdSocket != null) {
                    cmdSocket.close();
                    cmdSocket = null;
                }

            }

            return ret;
        } else {
            CNetSDKLog.d(TAG, "sendStartPTZCmd input param error");
            return false;
        }
    }

    public synchronized boolean sendStartPTZCmd(String servAddr, int port, String sessionID, String userId, String cameraID, int cmdID, int param1, int param2, int param3, int param4) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(cameraID)) {
            boolean ret = true;
            DatagramSocket cmdSocket = null;

            try {
                String str = String.format(Locale.ENGLISH, "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r%n<Message Version=\"3.0\">\r%n<PTZ_HEADER MessageType=\"MSG_PTZ_SET_REQ\" SessionID=\"%s\" UserID=\"%s\" CameraID=\"%s\"/>\r%n<PTZ_PARAM OpID=\"%d\" Param1=\"%d\" Param2=\"%d\" Param3=\"%d\" Param4=\"%d\"/>\r%n</Message>", new Object[]{sessionID, userId, cameraID, Integer.valueOf(cmdID), Integer.valueOf(param1), Integer.valueOf(param2), Integer.valueOf(param3), Integer.valueOf(param4)});
                cmdSocket = new DatagramSocket(port);
                InetAddress addr = InetAddress.getByName(servAddr);
                byte[] data = str.getBytes("utf-8");
                DatagramPacket packet = new DatagramPacket(data, data.length, addr, port);
                cmdSocket.send(packet);
            } catch (Exception var20) {
                var20.printStackTrace();
                CNetSDKLog.d(TAG, "exception:" + var20.getLocalizedMessage());
                ret = false;
            } finally {
                if(cmdSocket != null) {
                    cmdSocket.close();
                    cmdSocket = null;
                }

            }

            return ret;
        } else {
            CNetSDKLog.d(TAG, "sendStartPTZCmd input param error");
            return false;
        }
    }

    public synchronized boolean sendStopPTZCmd(String servAddr, int port, String sessionID, String cameraID) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(cameraID)) {
            boolean ret = true;
            DatagramSocket cmdSocket = null;

            try {
                String str = String.format(Locale.ENGLISH, "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r%n<Message Version=\"3.0\">\r%n<PTZ_HEADER MessageType=\"MSG_PTZ_SET_REQ\" SessionID=\"%s\" CameraID=\"%s\"/>\r%n<PTZ_PARAM OpID=\"%d\" Param1=\"%d\" Param2=\"%d\"/>\r%n</Message>", new Object[]{sessionID, cameraID, Integer.valueOf(15), Integer.valueOf(1), Integer.valueOf(0)});
                cmdSocket = new DatagramSocket(port);
                InetAddress addr = InetAddress.getByName(servAddr);
                byte[] data = str.getBytes("utf-8");
                DatagramPacket packet = new DatagramPacket(data, data.length, addr, port);
                cmdSocket.send(packet);
            } catch (Exception var14) {
                var14.printStackTrace();
                ret = false;
            } finally {
                if(cmdSocket != null) {
                    cmdSocket.close();
                    cmdSocket = null;
                }

            }

            return ret;
        } else {
            return false;
        }
    }

    public boolean sendStopPTZCmd(String servAddr, int port, String sessionID, String userId, String cameraID) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(cameraID)) {
            boolean ret = true;
            DatagramSocket cmdSocket = null;

            try {
                String str = String.format(Locale.ENGLISH, "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r%n<Message Version=\"3.0\">\r%n<PTZ_HEADER MessageType=\"MSG_PTZ_SET_REQ\" SessionID=\"%s\" UserID=\"%s\" CameraID=\"%s\"/>\r%n<PTZ_PARAM OpID=\"%d\" Param1=\"%d\" Param2=\"%d\" Param3=\"%d\" Param4=\"%d\"/>\r%n</Message>", new Object[]{sessionID, userId, cameraID, Integer.valueOf(15), Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)});
                cmdSocket = new DatagramSocket(port);
                InetAddress addr = InetAddress.getByName(servAddr);
                byte[] data = str.getBytes("utf-8");
                DatagramPacket packet = new DatagramPacket(data, data.length, addr, port);
                cmdSocket.send(packet);
            } catch (Exception var15) {
                var15.printStackTrace();
                ret = false;
            } finally {
                if(cmdSocket != null) {
                    cmdSocket.close();
                    cmdSocket = null;
                }

            }

            return ret;
        } else {
            return false;
        }
    }

    public String getSDKVersion() {
        return "V1.10 build20130408";
    }

    public int getLastErrorCode() {
        return this.mLastError;
    }

    public String getLastErrorDesc() {
        return this.mLastErrorDescribe;
    }

    public String toString() {
        return "VMSNetSDK [toString()=" + super.toString() + "]";
    }

    public String syncPostRequest(String strServAddr, String param) {
        if(strServAddr != null && this.mRequestTool != null) {
            if(param != null) {
                strServAddr = strServAddr + "?";
                strServAddr = strServAddr + param;
            }

            String str;
            if(this.mIsSaveModel) {
                str = this.mRequestTool.httpsPostRequest(strServAddr, (String)null);
            } else {
                str = this.mRequestTool.httpPostRequest(strServAddr, (String)null);
            }

            if(str == null || str.length() <= 0) {
                this.mLastError = this.mRequestTool.getLastErrorCode();
                this.mLastErrorDescribe = this.mRequestTool.getLastErrorDesc();
            }

            return str;
        } else {
            this.mLastError = 125;
            this.mLastErrorDescribe = "Request return null";
            return null;
        }
    }

    public boolean collectCamera(String servAddr, String sessionID, String cameraID, int groupID) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(cameraID)) {
            String strServAddr = String.format("%s/mobile/collectCamera", new Object[]{servAddr});
            String paramData = String.format(Locale.ENGLISH, "sessionID=%s&cameraID=%s&collectFlag=1&groupID=%d", new Object[]{sessionID, cameraID, Integer.valueOf(groupID)});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                if(this.mIsPrintLog) {
                    CNetSDKLog.i(TAG, "collectCamera():: xml is " + strResponse);
                }

                boolean ret = XMLParser.parseResultCode(strResponse);
                if(!ret) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::collectCamera() parse camera list info fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::collectCamera() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean discollectCamera(String servAddr, String sessionID, String cameraID, int groupID) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(cameraID)) {
            String strServAddr = String.format("%s/mobile/collectCamera", new Object[]{servAddr});
            String paramData = String.format(Locale.ENGLISH, "sessionID=%s&cameraID=%s&collectFlag=0&groupID=%d", new Object[]{sessionID, cameraID, Integer.valueOf(groupID)});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                if(this.mIsPrintLog) {
                    CNetSDKLog.i(TAG, "discollectCamera():: xml is " + strResponse);
                }

                boolean ret = XMLParser.parseResultCode(strResponse);
                if(!ret) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::discollectCamera() parse camera list info fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::discollectCamera() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean getCollectedCameraList(String servAddr, String sessionID, int numPerPage, int curPage, List<CameraInfo> cameraList) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && cameraList != null) {
            String strServAddr = String.format("%s/mobile/getCollectedCamera", new Object[]{servAddr});
            String paramData = String.format(Locale.ENGLISH, "sessionID=%s&numPerPage=%d&curPage=%d", new Object[]{sessionID, Integer.valueOf(numPerPage), Integer.valueOf(curPage)});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                if(this.mIsPrintLog) {
                    CNetSDKLog.i(TAG, "getCollectedCameraList():: xml is " + strResponse);
                }

                boolean ret = XMLParser.parseCameraListInfo(strResponse, cameraList);
                if(!ret) {
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getCollectedCamera() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean getGISCameraList(String servAddr, String sessionID, int numPerPage, int curPage, List<GISCameraInfo> gisCameraList) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && gisCameraList != null) {
            String strServAddr = String.format("%s/mobile/getGISCamera", new Object[]{servAddr});
            String paramData = String.format(Locale.ENGLISH, "sessionID=%s&numPerPage=%d&curPage=%d", new Object[]{sessionID, Integer.valueOf(numPerPage), Integer.valueOf(curPage)});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                if(this.mIsPrintLog) {
                    CNetSDKLog.i(TAG, "getGISCameraList():: xml is " + strResponse);
                }

                boolean ret = XMLParser.parseGISCameraListInfo(strResponse, gisCameraList);
                if(!ret) {
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getGISCameraList() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean getGISCameraListByName(String servAddr, String sessionID, int numPerPage, int curPage, String keyString, String pointType, List<GISCameraInfo> gisCameraList) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(keyString) && !TextUtils.isEmpty(pointType) && gisCameraList != null) {
            String keyStringEnc = null;

            try {
                keyStringEnc = URLEncoder.encode(URLEncoder.encode(keyString, "utf-8"), "utf-8");
            } catch (UnsupportedEncodingException var13) {
                var13.printStackTrace();
                this.mLastError = 100;
                this.mLastErrorDescribe = "user name encorde error";
                return false;
            }

            String strServAddr = String.format("%s/mobile/getGISCamera", new Object[]{servAddr});
            String paramData = String.format(Locale.ENGLISH, "sessionID=%s&keyString=%s&pointType=%s&numPerPage=%d&curPage=%d", new Object[]{sessionID, keyStringEnc, pointType, Integer.valueOf(numPerPage), Integer.valueOf(curPage)});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
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
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getGISCameraByName() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean getGISCameraListByPostion(String servAddr, String sessionID, String keyString, int numPerPage, int curPage, String longitude, String latitude, int radius, List<GISCameraInfo> gisCameraList) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && gisCameraList != null) {
            String strServAddr = String.format("%s/mobile/getGISCamera", new Object[]{servAddr});
            String paramData = String.format(Locale.ENGLISH, "sessionID=%s&keyString=%s&longitude=%s&latitude=%s&radius=%d&numPerPage=%d&curPage=%d", new Object[]{sessionID, keyString, longitude, latitude, Integer.valueOf(radius), Integer.valueOf(numPerPage), Integer.valueOf(curPage)});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
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
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getGISCameraByPostion() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public boolean getCameraInfoEx(String servAddr, String sessionID, String cameraID, CameraInfoEx cameraInfoEx) {
        if(!TextUtils.isEmpty(servAddr) && !TextUtils.isEmpty(sessionID) && !TextUtils.isEmpty(cameraID)) {
            String strServAddr = String.format("%s/mobile/getCameraDetailInfo", new Object[]{servAddr});
            String paramData = String.format("sessionID=%s&cameraID=%s", new Object[]{sessionID, cameraID});
            String strResponse = this.syncPostRequest(strServAddr, paramData);
            if(strResponse == null) {
                return false;
            } else {
                if(this.mIsPrintLog) {
                    CNetSDKLog.i(TAG, "getCameraInfoEx():: xml is " + strResponse);
                }

                boolean ret = XMLParser.parseCameraInfoEx(strResponse, cameraInfoEx);
                if(!ret) {
                    CNetSDKLog.d(TAG, "VMSNetSDK::getCameraDetailInfo() parse camera list info fail");
                    this.mLastError = XMLParser.getError();
                    this.mLastErrorDescribe = XMLParser.getErrorDesc();
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getCameraDetailInfo() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return false;
        }
    }

    public String getUpdatePwdRequestData(String userName, String oldPassword, String newPassword, int pwdLevel, ServerConfig serverConfig, String address) {
        if(!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(oldPassword) && !TextUtils.isEmpty(newPassword) && serverConfig != null) {
            String userNameEnc = null;

            try {
                userNameEnc = URLEncoder.encode(URLEncoder.encode(userName, "utf-8"), "utf-8");
            } catch (UnsupportedEncodingException var9) {
                var9.printStackTrace();
                this.mLastError = 100;
                this.mLastErrorDescribe = "user name encorde error";
                return null;
            }

            oldPassword = this.getEncodePassword(oldPassword, serverConfig.getPasswordEncryptionMode());
            newPassword = this.getEncodePassword(newPassword, serverConfig.getPasswordEncryptionMode());
            if(!TextUtils.isEmpty(oldPassword) && !TextUtils.isEmpty(newPassword)) {
                String strData = String.format(Locale.ENGLISH, "username=%s&oldPassword=%s&newPassword=%s&passwordLevel=%d&appType=%s&loginAddr=%s", new Object[]{userNameEnc, oldPassword, newPassword, Integer.valueOf(pwdLevel), "5060", address});
                return strData;
            } else {
                CNetSDKLog.d(TAG, "VMSNetSDK::getUpdatePwdRequestData() EncodePassword error");
                this.mLastError = 100;
                this.mLastErrorDescribe = "encodePassword error";
                return null;
            }
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::getUpdatePwdRequestData() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return null;
        }
    }

    private String getEncodePassword(String oldPassword, int passwordEncryptionMode) {
        String password = "";
        if(passwordEncryptionMode == 0) {
            password = EnCode.enCodeByMd5(oldPassword);
        } else if(passwordEncryptionMode != 1 && passwordEncryptionMode != 3) {
            if(passwordEncryptionMode == 2) {
                password = EnCode.enCodeBySHA256(this.md5Crypto(oldPassword));
            }
        } else {
            password = EnCode.enCodeBySHA256(oldPassword);
        }

        return password;
    }

    public String getSaveLoginRequestData(String userName, String password, String macAddress, int passwordEncryptionMode, int pwdLevel, String appType, String verifCode, String verifCodekey, String addressIp, String randomCode, String randomKey) {
        if(!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
            String userNameEnc = null;

            try {
                userNameEnc = URLEncoder.encode(URLEncoder.encode(userName, "utf-8"), "utf-8");
            } catch (UnsupportedEncodingException var14) {
                var14.printStackTrace();
                this.mLastError = 100;
                this.mLastErrorDescribe = "user name encorde error";
                return null;
            }

            CNetSDKLog.d(TAG, "getLoginRequestData() ::passwordEncryptionMode  :" + passwordEncryptionMode);
            if(passwordEncryptionMode == 0) {
                password = this.md5Crypto(password);
            } else if(passwordEncryptionMode == 1) {
                password = EnCode.enCodeBySHA256(password);
            } else if(passwordEncryptionMode == 2) {
                password = EnCode.enCodeBySHA256(EnCode.enCodeBySHA256(this.md5Crypto(password)) + randomCode);
            } else if(passwordEncryptionMode == 3) {
                password = EnCode.enCodeBySHA256(EnCode.enCodeBySHA256(password) + randomCode);
            }

            String strData = "";
            if(macAddress == null) {
                strData = String.format(Locale.ENGLISH, "username=%s&password=%s&loginAddr=%s&passwordLevel=%d&verifCode=%s&verifCodeKey=%s&appType=%s&randomKey=%s", new Object[]{userNameEnc, password, addressIp, Integer.valueOf(pwdLevel), verifCode, verifCodekey, appType, randomKey});
            } else {
                strData = String.format(Locale.ENGLISH, "username=%s&password=%s&loginAddr=%s&passwordLevel=%d&verifCode=%s&verifCodeKey=%s&appType=%s&mac=%s&randomKey=%s", new Object[]{userNameEnc, password, addressIp, Integer.valueOf(pwdLevel), verifCode, verifCodekey, appType, macAddress, randomKey});
            }

            return strData;
        } else {
            CNetSDKLog.d(TAG, "VMSNetSDK::login() input param error");
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return null;
        }
    }

    public void setLoginModel(boolean mIsSaveModel) {
        this.mIsSaveModel = mIsSaveModel;
    }
}
