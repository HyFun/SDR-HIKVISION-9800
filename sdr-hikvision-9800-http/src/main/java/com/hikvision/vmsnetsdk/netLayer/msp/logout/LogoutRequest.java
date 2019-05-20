/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.logout;
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
/*    */ public class LogoutRequest
/*    */   extends MspRequest
/*    */ {
/*    */   private String ssionId;
/*    */   private static final String TAG = "LogoutRequest";
/*    */   private MspServer mspServer;
/*    */   
/*    */   public LogoutRequest(MspServer server, IRequestTool iRequestTool, String ssionId)
/*    */   {
/* 21 */     super(server, iRequestTool);
/* 22 */     this.ssionId = ssionId;
/* 23 */     this.mspServer = server;
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 28 */     CNetSDKLog.i("LogoutRequest", "getRequestAddr,start.");
/* 29 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 30 */       CNetSDKLog.e("LogoutRequest", "getRequestAddr,param error.");
/* 31 */       return null;
/*    */     }
/*    */     
/* 34 */     String requestAddr = String.format("%s/mobile/logout", new Object[] { this.mspServer.gethttpsAddr() });
/* 35 */     CNetSDKLog.i("LogoutRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 36 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 42 */     CNetSDKLog.i("LogoutRequest", "getRequestData,start.");
/* 43 */     if ((this.ssionId == null) || (this.ssionId.length() <= 0)) {
/* 44 */       CNetSDKLog.e("LogoutRequest", "getRequestData,param error.");
/* 45 */       return null;
/*    */     }
/*    */     
/* 48 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 49 */     requestDateBuilder.append("sessionID=");
/* 50 */     requestDateBuilder.append(this.ssionId);
/*    */     
/* 52 */     String requestData = requestDateBuilder.toString();
/* 53 */     CNetSDKLog.i("LogoutRequest", "getRequestString,requestData:" + requestData);
/* 54 */     return requestData;
/*    */   }
/*    */   
/*    */   public String getBackString()
/*    */   {
/* 59 */     return super.getBackString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\logout\LogoutRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */