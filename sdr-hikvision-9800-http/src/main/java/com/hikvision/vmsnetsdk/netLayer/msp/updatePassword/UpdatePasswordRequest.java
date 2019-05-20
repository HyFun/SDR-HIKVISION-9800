//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hikvision.vmsnetsdk.netLayer.msp.updatePassword;

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.MspServer;
import com.hikvision.vmsnetsdk.util.EnCode;
import com.hikvision.vmsnetsdk.util.intf.IRequestTool;

public class UpdatePasswordRequest extends MspRequest {
    private static final String TAG = "UpdatePasswordRequest";
    private MspServer mspServer;
    private String sessionId;
    private String oldPassword;
    private String newPassword;
    private String verCode;
    private int pwdLevel;

    public UpdatePasswordRequest(MspServer server, IRequestTool iRequestTool, String sessionId, String oldPassword, String newPassword, String code, int level) {
        super(server, iRequestTool);
        this.sessionId = sessionId;
        this.mspServer = server;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.verCode = code;
        this.pwdLevel = level;
    }

    public String getRequestAddr() {
        CNetSDKLog.i("UpdatePasswordRequest", "getRequestAddr,start.");
        if(this.mspServer.getIp() != null && this.mspServer.gethttpsAddr() != null) {
            String requestAddr = String.format("%s/mobile/updatePassword", new Object[]{this.mspServer.gethttpsAddr()});
            CNetSDKLog.i("UpdatePasswordRequest", "getRequestAddr,requestAddr:" + requestAddr);
            return requestAddr;
        } else {
            CNetSDKLog.e("UpdatePasswordRequest", "getRequestAddr,param error.");
            return null;
        }
    }

    public String getRequestData() {
        CNetSDKLog.i("UpdatePasswordRequest", "getRequestData,start.");
        if(this.sessionId != null && this.oldPassword != null && this.newPassword != null && this.sessionId.length() > 0 && this.oldPassword.length() > 0 && this.newPassword.length() > 0) {
            StringBuilder requestDateBuilder = new StringBuilder();
            requestDateBuilder.append("sessionID=");
            requestDateBuilder.append(this.sessionId);
            requestDateBuilder.append("&oldPassword=");
            requestDateBuilder.append(EnCode.enCodeBySHA256(this.oldPassword));
            requestDateBuilder.append("&newPassword=");
            requestDateBuilder.append(EnCode.enCodeBySHA256(this.newPassword));
            if(null != this.verCode && !"".equals(this.verCode)) {
                requestDateBuilder.append("&verificationID=").append(this.verCode);
                requestDateBuilder.append("&password_level=").append(this.pwdLevel);
            }

            String requestData = requestDateBuilder.toString();
            CNetSDKLog.i("UpdatePasswordRequest", "getRequestString,requestData:" + requestData);
            return requestData;
        } else {
            CNetSDKLog.e("UpdatePasswordRequest", "getRequestData,param error.");
            return null;
        }
    }

    public String getBackString() {
        return super.getBackString();
    }
}
