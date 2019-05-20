/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.msg.list.latest;
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
/*    */ public class LatestMsgListRequest
/*    */   extends MspRequest
/*    */ {
/*    */   private static final String TAG = "MsgListRequest";
/*    */   private MspServer mspServer;
/*    */   private String ssionId;
/*    */   private int number;
/*    */   
/*    */   public LatestMsgListRequest(MspServer server, IRequestTool iRequestTool, String ssionId, int number)
/*    */   {
/* 22 */     super(server, iRequestTool);
/* 23 */     this.ssionId = ssionId;
/* 24 */     this.mspServer = server;
/* 25 */     this.number = number;
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 30 */     CNetSDKLog.i("MsgListRequest", "getRequestAddr,start.");
/* 31 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 32 */       CNetSDKLog.e("MsgListRequest", "getRequestAddr,param error.");
/* 33 */       return null;
/*    */     }
/*    */     
/* 36 */     String requestAddr = String.format("%s/mobile/getMsgList", new Object[] { this.mspServer.gethttpsAddr() });
/* 37 */     CNetSDKLog.i("MsgListRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 38 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 44 */     CNetSDKLog.i("MsgListRequest", "getRequestData,start.");
/* 45 */     if ((this.ssionId == null) || (this.ssionId.length() <= 0) || (this.number <= 0)) {
/* 46 */       CNetSDKLog.e("MsgListRequest", "getRequestData,param error.");
/* 47 */       return null;
/*    */     }
/*    */     
/* 50 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 51 */     requestDateBuilder.append("sessionID=");
/* 52 */     requestDateBuilder.append(this.ssionId);
/* 53 */     requestDateBuilder.append("&number=");
/* 54 */     requestDateBuilder.append(this.number);
/*    */     
/* 56 */     String requestData = requestDateBuilder.toString();
/* 57 */     CNetSDKLog.i("MsgListRequest", "getRequestString,requestData:" + requestData);
/* 58 */     return requestData;
/*    */   }
/*    */   
/*    */   public String getBackString()
/*    */   {
/* 63 */     return super.getBackString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\msg\list\latest\LatestMsgListRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */