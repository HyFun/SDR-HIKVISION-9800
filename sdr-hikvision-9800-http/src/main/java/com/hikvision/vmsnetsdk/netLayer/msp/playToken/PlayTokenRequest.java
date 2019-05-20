/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.playToken;
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
/*    */ public class PlayTokenRequest
/*    */   extends MspRequest
/*    */ {
/*    */   private String sessionId;
/*    */   private static final String TAG = "PlayTokenRequest";
/*    */   private MspServer mspServer;
/*    */   
/*    */   public PlayTokenRequest(MspServer mspServer, IRequestTool iRequestTool, String sessionId)
/*    */   {
/* 21 */     super(mspServer, iRequestTool);
/* 22 */     this.sessionId = sessionId;
/* 23 */     this.mspServer = mspServer;
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 28 */     CNetSDKLog.i("PlayTokenRequest", "getRequestAddr,start.");
/* 29 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 30 */       CNetSDKLog.e("PlayTokenRequest", "getRequestAddr,param error.");
/* 31 */       return null;
/*    */     }
/*    */     
/* 34 */     String requestAddr = String.format("%s/mobile/getPlayToken", new Object[] { this.mspServer.gethttpsAddr() });
/* 35 */     CNetSDKLog.i("PlayTokenRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 36 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 42 */     CNetSDKLog.i("PlayTokenRequest", "getRequestData,start.");
/* 43 */     if ((this.sessionId == null) || (this.sessionId.length() <= 0)) {
/* 44 */       CNetSDKLog.e("PlayTokenRequest", "getRequestData,param error.");
/* 45 */       return null;
/*    */     }
/*    */     
/* 48 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 49 */     requestDateBuilder.append("sessionID=");
/* 50 */     requestDateBuilder.append(this.sessionId);
/*    */     
/* 52 */     String requestData = requestDateBuilder.toString();
/* 53 */     CNetSDKLog.i("PlayTokenRequest", "getRequestString,requestData:" + requestData);
/* 54 */     return requestData;
/*    */   }
/*    */   
/*    */   public String getBackString()
/*    */   {
/* 59 */     return super.getBackString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\playToken\PlayTokenRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */