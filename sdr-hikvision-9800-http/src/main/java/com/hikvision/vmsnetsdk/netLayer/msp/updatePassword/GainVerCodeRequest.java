//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hikvision.vmsnetsdk.netLayer.msp.updatePassword;

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.MspServer;
import com.hikvision.vmsnetsdk.util.intf.IRequestTool;

public class GainVerCodeRequest extends MspRequest {
    private static final String TAG = "GainVerCodeRequest";
    private String sessionId;
    private MspServer mspServer;

    public GainVerCodeRequest(MspServer server, IRequestTool iRequestTool, String sessionId) {
        super(server, iRequestTool);
        this.sessionId = sessionId;
        this.mspServer = server;
    }

    public String getRequestAddr() {
        if(this.mspServer.getIp() != null && this.mspServer.gethttpsAddr() != null) {
            String requestAddr = String.format("%s/mobile/updatePasswordVerification", new Object[]{this.mspServer.gethttpsAddr()});
            CNetSDKLog.i("GainVerCodeRequest", "getRequestAddr,requestAddr:" + requestAddr);
            return requestAddr;
        } else {
            return null;
        }
    }

    public String getRequestData() {
        String data = "sessionID=" + this.sessionId;
        return data;
    }
}
