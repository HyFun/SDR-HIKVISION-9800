/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.cameraInfo;
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
/*    */ public class CameraInfoRequest
/*    */   extends MspRequest
/*    */ {
/*    */   private String sessionId;
/*    */   private static final String TAG = "CameraInfoRequest";
/*    */   private MspServer mspServer;
/*    */   private String cameraId;
/*    */   
/*    */   public CameraInfoRequest(MspServer server, IRequestTool iRequestTool, String sessionId, String cameraId)
/*    */   {
/* 22 */     super(server, iRequestTool);
/* 23 */     this.sessionId = sessionId;
/* 24 */     this.mspServer = server;
/* 25 */     this.cameraId = cameraId;
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 30 */     CNetSDKLog.i("CameraInfoRequest", "getRequestAddr,start.");
/* 31 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 32 */       CNetSDKLog.e("CameraInfoRequest", "getRequestAddr,param error.");
/* 33 */       return null;
/*    */     }
/*    */     
/* 36 */     String requestAddr = String.format("%s/mobile/getCameraInfo", new Object[] { this.mspServer.gethttpsAddr() });
/* 37 */     CNetSDKLog.i("CameraInfoRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 38 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 44 */     CNetSDKLog.i("CameraInfoRequest", "getRequestData,start.");
/* 45 */     if ((this.sessionId == null) || (this.cameraId == null) || (this.sessionId.length() <= 0) || (this.cameraId.length() <= 0)) {
/* 46 */       CNetSDKLog.e("CameraInfoRequest", "getRequestData,param error.");
/* 47 */       return null;
/*    */     }
/*    */     
/* 50 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 51 */     requestDateBuilder.append("sessionID=");
/* 52 */     requestDateBuilder.append(this.sessionId);
/*    */     
/* 54 */     requestDateBuilder.append("&cameraID=");
/* 55 */     requestDateBuilder.append(this.cameraId);
/* 56 */     String requestData = requestDateBuilder.toString();
/* 57 */     CNetSDKLog.i("CameraInfoRequest", "getRequestString,requestData:" + requestData);
/* 58 */     return requestData;
/*    */   }
/*    */   
/*    */   public String getBackString()
/*    */   {
/* 63 */     return super.getBackString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\cameraInfo\CameraInfoRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */