/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.msg.list.detail.latest;
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
/*    */ 
/*    */ public class LatestMsgDetailListRequest
/*    */   extends MspRequest
/*    */ {
/*    */   private static final String TAG = "MsgDetailListRequest";
/*    */   private MspServer mspServer;
/*    */   private String ssionId;
/*    */   private int number;
/*    */   private String type;
/*    */   
/*    */   public LatestMsgDetailListRequest(MspServer server, IRequestTool iRequestTool, String ssionId, String type, int number)
/*    */   {
/* 24 */     super(server, iRequestTool);
/* 25 */     this.ssionId = ssionId;
/* 26 */     this.mspServer = server;
/* 27 */     this.number = number;
/* 28 */     this.type = type;
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 33 */     CNetSDKLog.i("MsgDetailListRequest", "getRequestAddr,start.");
/* 34 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 35 */       CNetSDKLog.e("MsgDetailListRequest", "getRequestAddr,param error.");
/* 36 */       return null;
/*    */     }
/*    */     
/* 39 */     String requestAddr = String.format("%s/mobile/getMsgDetailList", new Object[] { this.mspServer.gethttpsAddr() });
/* 40 */     CNetSDKLog.i("MsgDetailListRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 41 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 47 */     CNetSDKLog.i("MsgDetailListRequest", "getRequestData,start.");
/* 48 */     if ((this.ssionId == null) || (this.ssionId.length() <= 0) || (this.type == null) || (this.type.length() <= 0) || (this.number <= 0)) {
/* 49 */       CNetSDKLog.e("MsgDetailListRequest", "getRequestData,param error.");
/* 50 */       return null;
/*    */     }
/*    */     
/* 53 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 54 */     requestDateBuilder.append("sessionID=");
/* 55 */     requestDateBuilder.append(this.ssionId);
/* 56 */     requestDateBuilder.append("&type=");
/* 57 */     requestDateBuilder.append(this.type);
/* 58 */     requestDateBuilder.append("&number=");
/* 59 */     requestDateBuilder.append(this.number);
/*    */     
/* 61 */     String requestData = requestDateBuilder.toString();
/* 62 */     CNetSDKLog.i("MsgDetailListRequest", "getRequestString,requestData:" + requestData);
/* 63 */     return requestData;
/*    */   }
/*    */   
/*    */   public String getBackString()
/*    */   {
/* 68 */     return super.getBackString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\msg\list\detail\latest\LatestMsgDetailListRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */