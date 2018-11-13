/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.collectCamera;
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
/*    */ 
/*    */ public class CollectCameraRequest
/*    */   extends MspRequest
/*    */ {
/*    */   private static final String TAG = "CollectCameraRequest";
/*    */   private MspServer mspServer;
/*    */   private String sessionId;
/*    */   private String cameraId;
/*    */   private int groupId;
/*    */   private int collectFlag;
/*    */   public static final int COLLECTFLAG_ADD = 1;
/*    */   public static final int COLLECTFLAG_CANCEL = 0;
/*    */   
/*    */   public CollectCameraRequest(MspServer server, IRequestTool iRequestTool, String sessionId, String cameraId, int groupId, int collectFlag)
/*    */   {
/* 28 */     super(server, iRequestTool);
/* 29 */     this.sessionId = sessionId;
/* 30 */     this.mspServer = server;
/* 31 */     this.cameraId = cameraId;
/* 32 */     this.groupId = groupId;
/* 33 */     this.collectFlag = collectFlag;
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 38 */     CNetSDKLog.i("CollectCameraRequest", "getRequestAddr,start.");
/* 39 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 40 */       CNetSDKLog.e("CollectCameraRequest", "getRequestAddr,param error.");
/* 41 */       return null;
/*    */     }
/*    */     
/* 44 */     String requestAddr = String.format("%s/mobile/collectCamera", new Object[] { this.mspServer.gethttpsAddr() });
/* 45 */     CNetSDKLog.i("CollectCameraRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 46 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 52 */     CNetSDKLog.i("CollectCameraRequest", "getRequestData,start.");
/* 53 */     if ((this.sessionId == null) || (this.sessionId.length() <= 0) || ((this.collectFlag != 1) && (this.collectFlag != 0)))
/*    */     {
/* 55 */       CNetSDKLog.e("CollectCameraRequest", "getRequestData,param error.");
/* 56 */       return null;
/*    */     }
/*    */     
/* 59 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 60 */     requestDateBuilder.append("sessionID=");
/* 61 */     requestDateBuilder.append(this.sessionId);
/* 62 */     requestDateBuilder.append("&cameraID=");
/* 63 */     requestDateBuilder.append(this.cameraId);
/* 64 */     requestDateBuilder.append("&collectFlag=");
/* 65 */     requestDateBuilder.append(this.collectFlag);
/* 66 */     requestDateBuilder.append("&groupID=");
/* 67 */     requestDateBuilder.append(this.groupId);
/*    */     
/* 69 */     String requestData = requestDateBuilder.toString();
/* 70 */     CNetSDKLog.i("CollectCameraRequest", "getRequestString,requestData:" + requestData);
/* 71 */     return requestData;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\collectCamera\CollectCameraRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */