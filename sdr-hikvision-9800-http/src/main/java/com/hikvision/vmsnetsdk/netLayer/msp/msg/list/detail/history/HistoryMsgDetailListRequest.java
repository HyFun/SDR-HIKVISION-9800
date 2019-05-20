/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.msg.list.detail.history;
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
/*    */ public class HistoryMsgDetailListRequest
/*    */   extends MspRequest
/*    */ {
/*    */   private static final String TAG = "HistoryMsgDetailListRequest";
/*    */   private MspServer mspServer;
/*    */   private String ssionId;
/*    */   private int number;
/*    */   private String type;
/*    */   private String msgId;
/*    */   
/*    */   public HistoryMsgDetailListRequest(MspServer server, IRequestTool iRequestTool, String ssionId, String type, int number, String msgId)
/*    */   {
/* 25 */     super(server, iRequestTool);
/* 26 */     this.ssionId = ssionId;
/* 27 */     this.mspServer = server;
/* 28 */     this.number = number;
/* 29 */     this.type = type;
/* 30 */     this.msgId = msgId;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestAddr()
/*    */   {
/* 36 */     CNetSDKLog.i("HistoryMsgDetailListRequest", "getRequestAddr,start.");
/* 37 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 38 */       CNetSDKLog.e("HistoryMsgDetailListRequest", "getRequestAddr,param error.");
/* 39 */       return null;
/*    */     }
/*    */     
/* 42 */     String requestAddr = String.format("%s/mobile/getMsgDetailHistoryList", new Object[] { this.mspServer.gethttpsAddr() });
/* 43 */     CNetSDKLog.i("HistoryMsgDetailListRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 44 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 50 */     CNetSDKLog.i("HistoryMsgDetailListRequest", "getRequestData,start.");
/* 51 */     if ((this.ssionId == null) || (this.ssionId.length() <= 0) || (this.type == null) || (this.type.length() <= 0) || (this.msgId == null) || 
/* 52 */       (this.msgId.length() <= 0) || (this.number <= 0)) {
/* 53 */       CNetSDKLog.e("HistoryMsgDetailListRequest", "getRequestData,param error.");
/* 54 */       return null;
/*    */     }
/*    */     
/* 57 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 58 */     requestDateBuilder.append("sessionID=");
/* 59 */     requestDateBuilder.append(this.ssionId);
/* 60 */     requestDateBuilder.append("&msgID=");
/* 61 */     requestDateBuilder.append(this.msgId);
/* 62 */     requestDateBuilder.append("&type=");
/* 63 */     requestDateBuilder.append(this.type);
/* 64 */     requestDateBuilder.append("&number=");
/* 65 */     requestDateBuilder.append(this.number);
/*    */     
/* 67 */     String requestData = requestDateBuilder.toString();
/* 68 */     CNetSDKLog.i("HistoryMsgDetailListRequest", "getRequestString,requestData:" + requestData);
/* 69 */     return requestData;
/*    */   }
/*    */   
/*    */   public String getBackString()
/*    */   {
/* 74 */     return super.getBackString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\msg\list\detail\history\HistoryMsgDetailListRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */