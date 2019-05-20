/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.msg.detail;
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
/*    */ public class MsgDetailRequest
/*    */   extends MspRequest
/*    */ {
/*    */   private static final String TAG = "MsgDetailRequest";
/*    */   private MspServer mspServer;
/*    */   private String ssionId;
/*    */   private String msgId;
/*    */   
/*    */   public MsgDetailRequest(MspServer server, IRequestTool iRequestTool, String ssionId, String msgId)
/*    */   {
/* 22 */     super(server, iRequestTool);
/* 23 */     this.ssionId = ssionId;
/* 24 */     this.mspServer = server;
/* 25 */     this.msgId = msgId;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestAddr()
/*    */   {
/* 31 */     CNetSDKLog.i("MsgDetailRequest", "getRequestAddr,start.");
/* 32 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 33 */       CNetSDKLog.e("MsgDetailRequest", "getRequestAddr,param error.");
/* 34 */       return null;
/*    */     }
/*    */     
/* 37 */     String requestAddr = String.format("%s/mobile/getMsgDetail", new Object[] { this.mspServer.gethttpsAddr() });
/* 38 */     CNetSDKLog.i("MsgDetailRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 39 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 45 */     CNetSDKLog.i("MsgDetailRequest", "getRequestData,start.");
/* 46 */     if ((this.ssionId == null) || (this.ssionId.length() <= 0) || (this.msgId == null) || (this.msgId.length() <= 0)) {
/* 47 */       CNetSDKLog.e("MsgDetailRequest", "getRequestData,param error.");
/* 48 */       return null;
/*    */     }
/*    */     
/* 51 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 52 */     requestDateBuilder.append("sessionID=");
/* 53 */     requestDateBuilder.append(this.ssionId);
/* 54 */     requestDateBuilder.append("&msgID=");
/* 55 */     requestDateBuilder.append(this.msgId);
/*    */     
/* 57 */     String requestData = requestDateBuilder.toString();
/* 58 */     CNetSDKLog.i("MsgDetailRequest", "getRequestString,requestData:" + requestData);
/* 59 */     return requestData;
/*    */   }
/*    */   
/*    */   public String getBackString()
/*    */   {
/* 64 */     return super.getBackString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\msg\detail\MsgDetailRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */