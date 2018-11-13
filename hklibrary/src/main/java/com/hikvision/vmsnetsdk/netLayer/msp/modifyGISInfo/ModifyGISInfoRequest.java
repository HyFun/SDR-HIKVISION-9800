/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.modifyGISInfo;
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
/*    */ public class ModifyGISInfoRequest
/*    */   extends MspRequest
/*    */ {
/*    */   private static final String TAG = "CollectCameraRequest";
/*    */   private MspServer mspServer;
/*    */   private String sessionId;
/*    */   private double longitude;
/*    */   private double latitude;
/*    */   private String objectId;
/*    */   
/*    */   public ModifyGISInfoRequest(MspServer server, IRequestTool iRequestTool, String sessionId, double longitude, double latitude, String objectId)
/*    */   {
/* 26 */     super(server, iRequestTool);
/* 27 */     this.sessionId = sessionId;
/* 28 */     this.mspServer = server;
/* 29 */     this.longitude = longitude;
/* 30 */     this.latitude = latitude;
/* 31 */     this.objectId = objectId;
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 36 */     CNetSDKLog.i("CollectCameraRequest", "getRequestAddr,start.");
/* 37 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 38 */       CNetSDKLog.e("CollectCameraRequest", "getRequestAddr,param error.");
/* 39 */       return null;
/*    */     }
/*    */     
/* 42 */     String requestAddr = String.format("%s/mobile/modifyGisInfo", new Object[] { this.mspServer.gethttpsAddr() });
/* 43 */     CNetSDKLog.i("CollectCameraRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 44 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 50 */     CNetSDKLog.i("CollectCameraRequest", "getRequestData,start.");
/* 51 */     if ((this.sessionId == null) || (this.sessionId.length() <= 0) || (this.objectId == null) || (this.objectId.length() <= 0)) {
/* 52 */       CNetSDKLog.e("CollectCameraRequest", "getRequestData,param error.");
/* 53 */       return null;
/*    */     }
/*    */     
/* 56 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 57 */     requestDateBuilder.append("sessionID=");
/* 58 */     requestDateBuilder.append(this.sessionId);
/* 59 */     requestDateBuilder.append("&objectID=");
/* 60 */     requestDateBuilder.append(this.objectId);
/* 61 */     requestDateBuilder.append("&longitude=");
/* 62 */     requestDateBuilder.append(this.longitude);
/* 63 */     requestDateBuilder.append("&latitude=");
/* 64 */     requestDateBuilder.append(this.latitude);
/*    */     
/* 66 */     String requestData = requestDateBuilder.toString();
/* 67 */     CNetSDKLog.i("CollectCameraRequest", "getRequestString,requestData:" + requestData);
/* 68 */     return requestData;
/*    */   }
/*    */   
/*    */   public String getBackString()
/*    */   {
/* 73 */     return super.getBackString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\modifyGISInfo\ModifyGISInfoRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */