/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.pushServerInfo;
/*    */ 
/*    */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.MspServer;
import com.hikvision.vmsnetsdk.util.intf.IRequestTool;

/*    */
/*    */
/*    */

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PushServerInfoRequest
/*    */   extends MspRequest
/*    */ {
/*    */   private static final String TAG = "PushServerInfoRequest";
/*    */   private MspServer mspServer;
/*    */   private String sessionId;
/*    */   private String apptype;
/*    */   
/*    */   public PushServerInfoRequest(MspServer server, IRequestTool iRequestTool, String sessionId, String apptype)
/*    */   {
/* 22 */     super(server, iRequestTool);
/* 23 */     this.sessionId = sessionId;
/* 24 */     this.mspServer = server;
/* 25 */     this.apptype = apptype;
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 30 */     CNetSDKLog.i("PushServerInfoRequest", "getRequestAddr,start.");
/* 31 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 32 */       CNetSDKLog.e("PushServerInfoRequest", "getRequestAddr,param error.");
/* 33 */       return null;
/*    */     }
/*    */     
/* 36 */     String requestAddr = String.format("%s/mobile/pushServerInfo", new Object[] { this.mspServer.gethttpsAddr() });
/* 37 */     CNetSDKLog.i("PushServerInfoRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 38 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 44 */     CNetSDKLog.i("PushServerInfoRequest", "getRequestData,start.");
/* 45 */     if ((this.sessionId == null) || (this.sessionId.length() <= 0) || (this.apptype == null) || (this.apptype.length() <= 0)) {
/* 46 */       CNetSDKLog.e("PushServerInfoRequest", "getRequestData,param error.");
/* 47 */       return null;
/*    */     }
/*    */     
/* 50 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 51 */     requestDateBuilder.append("sessionID=");
/* 52 */     requestDateBuilder.append(this.sessionId);
/* 53 */     requestDateBuilder.append("&apptype=");
/* 54 */     requestDateBuilder.append(this.apptype);
/*    */     
/* 56 */     String requestData = requestDateBuilder.toString();
/* 57 */     CNetSDKLog.i("PushServerInfoRequest", "getRequestString,requestData:" + requestData);
/* 58 */     return requestData;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\pushServerInfo\PushServerInfoRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */