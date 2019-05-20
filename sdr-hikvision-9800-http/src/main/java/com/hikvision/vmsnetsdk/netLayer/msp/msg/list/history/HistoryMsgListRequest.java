/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.msg.list.history;
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
/*    */ public class HistoryMsgListRequest
/*    */   extends MspRequest
/*    */ {
/*    */   private static final String TAG = "HistoryMsgListRequest";
/*    */   private MspServer mspServer;
/*    */   private String ssionId;
/*    */   private int number;
/*    */   private String msgId;
/*    */   private String type;
/*    */   
/*    */   public HistoryMsgListRequest(MspServer server, IRequestTool iRequestTool, String ssionId, String type, String msgId, int number)
/*    */   {
/* 25 */     super(server, iRequestTool);
/* 26 */     this.ssionId = ssionId;
/* 27 */     this.mspServer = server;
/* 28 */     this.number = number;
/* 29 */     this.msgId = msgId;
/* 30 */     this.type = type;
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 35 */     CNetSDKLog.i("HistoryMsgListRequest", "getRequestAddr,start.");
/* 36 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 37 */       CNetSDKLog.e("HistoryMsgListRequest", "getRequestAddr,param error.");
/* 38 */       return null;
/*    */     }
/*    */     
/* 41 */     String requestAddr = String.format("%s/mobile/getMsgHistoryList", new Object[] { this.mspServer.gethttpsAddr() });
/* 42 */     CNetSDKLog.i("HistoryMsgListRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 43 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 49 */     CNetSDKLog.i("HistoryMsgListRequest", "getRequestData,start.");
/* 50 */     if ((this.ssionId == null) || (this.ssionId.length() <= 0) || (this.msgId == null) || (this.msgId.length() <= 0) || (this.type == null) || 
/* 51 */       (this.type.length() <= 0) || (this.number <= 0)) {
/* 52 */       CNetSDKLog.e("HistoryMsgListRequest", "getRequestData,param error.");
/* 53 */       return null;
/*    */     }
/*    */     
/* 56 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 57 */     requestDateBuilder.append("sessionID=");
/* 58 */     requestDateBuilder.append(this.ssionId);
/* 59 */     requestDateBuilder.append("&msgID=");
/* 60 */     requestDateBuilder.append(this.msgId);
/* 61 */     requestDateBuilder.append("&type=");
/* 62 */     requestDateBuilder.append(this.type);
/* 63 */     requestDateBuilder.append("&number=");
/* 64 */     requestDateBuilder.append(this.number);
/*    */     
/* 66 */     String requestData = requestDateBuilder.toString();
/* 67 */     CNetSDKLog.i("HistoryMsgListRequest", "getRequestString,requestData:" + requestData);
/* 68 */     return requestData;
/*    */   }
/*    */   
/*    */   public String getBackString()
/*    */   {
/* 73 */     return super.getBackString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\msg\list\history\HistoryMsgListRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */