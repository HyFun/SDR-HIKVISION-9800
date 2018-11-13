/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.deleteMsg;
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
/*    */ public class DeleteMsgRequest
/*    */   extends MspRequest
/*    */ {
/*    */   private String ssionId;
/*    */   private static final String TAG = "DeleteMsgRequest";
/*    */   private MspServer mspServer;
/*    */   private MODE deleteMode;
/*    */   private String msgId;
/*    */   private String msgType;
/*    */   
/*    */   public static enum MODE
/*    */   {
/* 21 */     BY_MSGID, 
/* 22 */     BY_MSGTYPE;
/*    */     
/*    */ 
/*    */     private MODE() {}
/*    */   }
/*    */   
/*    */   public DeleteMsgRequest(MspServer server, IRequestTool iRequestTool, String ssionId, MODE deleteMode, String param)
/*    */   {
/* 30 */     super(server, iRequestTool);
/* 31 */     this.ssionId = ssionId;
/* 32 */     this.mspServer = server;
/* 33 */     this.deleteMode = deleteMode;
/* 34 */     if (deleteMode == MODE.BY_MSGID) {
/* 35 */       this.msgId = param;
/* 36 */     } else if (deleteMode == MODE.BY_MSGTYPE) {
/* 37 */       this.msgType = param;
/*    */     }
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 43 */     CNetSDKLog.i("DeleteMsgRequest", "getRequestAddr,start.");
/* 44 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 45 */       CNetSDKLog.e("DeleteMsgRequest", "getRequestAddr,param error.");
/* 46 */       return null;
/*    */     }
/*    */     
/* 49 */     String requestAddr = String.format("%s/mobile/deleteMsg", new Object[] { this.mspServer.gethttpsAddr() });
/* 50 */     CNetSDKLog.i("DeleteMsgRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 51 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 57 */     CNetSDKLog.i("DeleteMsgRequest", "getRequestData,start.");
/* 58 */     if ((this.ssionId == null) || (this.ssionId.length() <= 0)) {
/* 59 */       CNetSDKLog.e("DeleteMsgRequest", "getRequestData,param error.");
/* 60 */       return null;
/*    */     }
/*    */     
/* 63 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 64 */     requestDateBuilder.append("sessionID=");
/* 65 */     requestDateBuilder.append(this.ssionId);
/* 66 */     if (this.deleteMode == MODE.BY_MSGID) {
/* 67 */       if ((this.msgId == null) || (this.msgId.length() <= 0)) {
/* 68 */         CNetSDKLog.e("DeleteMsgRequest", "getRequestData,param error.");
/* 69 */         return null;
/*    */       }
/* 71 */       requestDateBuilder.append("&msgID=");
/* 72 */       requestDateBuilder.append(this.msgId);
/* 73 */     } else if (this.deleteMode == MODE.BY_MSGTYPE) {
/* 74 */       if ((this.msgType == null) || (this.msgType.length() <= 0)) {
/* 75 */         CNetSDKLog.e("DeleteMsgRequest", "getRequestData,param error.");
/* 76 */         return null;
/*    */       }
/* 78 */       requestDateBuilder.append("&type=");
/* 79 */       requestDateBuilder.append(this.msgType);
/*    */     }
/*    */     
/* 82 */     String requestData = requestDateBuilder.toString();
/* 83 */     CNetSDKLog.i("DeleteMsgRequest", "getRequestString,requestData:" + requestData);
/* 84 */     return requestData;
/*    */   }
/*    */   
/*    */   public String getBackString()
/*    */   {
/* 89 */     return super.getBackString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\deleteMsg\DeleteMsgRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */