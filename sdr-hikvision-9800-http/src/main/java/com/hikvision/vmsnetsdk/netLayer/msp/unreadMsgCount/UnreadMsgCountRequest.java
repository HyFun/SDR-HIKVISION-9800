//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hikvision.vmsnetsdk.netLayer.msp.unreadMsgCount;

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.MspServer;
import com.hikvision.vmsnetsdk.util.intf.IRequestTool;

public class UnreadMsgCountRequest extends MspRequest {
    private String ssionId;
    private static final String TAG = "UnreadMsgCountRequest";
    private MspServer mspServer;

    public UnreadMsgCountRequest(MspServer server, IRequestTool iRequestTool, String ssionId) {
        super(server, iRequestTool);
        this.ssionId = ssionId;
        this.mspServer = server;
    }

    public String getRequestAddr() {
        CNetSDKLog.i("UnreadMsgCountRequest", "getRequestAddr,start.");
        if(this.mspServer.getIp() != null && this.mspServer.gethttpsAddr() != null) {
            String requestAddr = String.format("%s/mobile/getUnreadMsgCount", new Object[]{this.mspServer.gethttpsAddr()});
            CNetSDKLog.i("UnreadMsgCountRequest", "getRequestAddr,requestAddr:" + requestAddr);
            return requestAddr;
        } else {
            CNetSDKLog.e("UnreadMsgCountRequest", "getRequestAddr,param error.");
            return null;
        }
    }

    public String getRequestData() {
        CNetSDKLog.i("UnreadMsgCountRequest", "getRequestData,start.");
        if(this.ssionId != null && this.ssionId.length() > 0) {
            StringBuilder requestDateBuilder = new StringBuilder();
            requestDateBuilder.append("sessionID=");
            requestDateBuilder.append(this.ssionId);
            String requestData = requestDateBuilder.toString();
            CNetSDKLog.i("UnreadMsgCountRequest", "getRequestString,requestData:" + requestData);
            return requestData;
        } else {
            CNetSDKLog.e("UnreadMsgCountRequest", "getRequestData,param error.");
            return null;
        }
    }

    public String getBackString() {
        return super.getBackString();
    }
}
