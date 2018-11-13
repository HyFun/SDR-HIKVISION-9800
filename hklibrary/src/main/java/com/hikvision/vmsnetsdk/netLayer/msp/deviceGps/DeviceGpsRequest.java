/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.deviceGps;
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
/*    */ public class DeviceGpsRequest
/*    */   extends MspRequest
/*    */ {
/*    */   private static final String TAG = "DeviceGpsRequest";
/*    */   private MspServer mspServer;
/*    */   private DeviceGpsParam deviceGpsParam;
/*    */   
/*    */   public DeviceGpsRequest(MspServer server, IRequestTool iRequestTool, DeviceGpsParam deviceGpsParam)
/*    */   {
/* 21 */     super(server, iRequestTool);
/* 22 */     this.mspServer = server;
/* 23 */     this.deviceGpsParam = deviceGpsParam;
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 28 */     CNetSDKLog.i("DeviceGpsRequest", "getRequestAddr,start.");
/* 29 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 30 */       CNetSDKLog.e("DeviceGpsRequest", "getRequestAddr,param error.");
/* 31 */       return null;
/*    */     }
/*    */     
/* 34 */     String requestAddr = String.format("%s/mobile/getDeviceGPS", new Object[] { this.mspServer.gethttpsAddr() });
/* 35 */     CNetSDKLog.i("DeviceGpsRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 36 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 42 */     CNetSDKLog.i("DeviceGpsRequest", "getRequestData,start.");
/*    */     
/* 44 */     String ssionId = this.deviceGpsParam.getSessionId();
/* 45 */     long endTime = this.deviceGpsParam.getEndTime();
/* 46 */     long startTime = this.deviceGpsParam.getStartTime();
/* 47 */     String indexCode = this.deviceGpsParam.getIndexCode();
/* 48 */     int topLimit = this.deviceGpsParam.getTopLimit();
/* 49 */     if ((indexCode == null) || (ssionId == null) || (indexCode.length() <= 0) || (ssionId.length() <= 0) || (endTime < 0L) || (startTime < 0L) || (topLimit <= 0))
/*    */     {
/* 51 */       CNetSDKLog.e("DeviceGpsRequest", "getRequestData,param error.");
/* 52 */       return null;
/*    */     }
/*    */     
/* 55 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 56 */     requestDateBuilder.append("sessionID=");
/* 57 */     requestDateBuilder.append(ssionId);
/* 58 */     requestDateBuilder.append("&indexCode=");
/* 59 */     requestDateBuilder.append(indexCode);
/*    */     
/* 61 */     requestDateBuilder.append("&startTime=");
/* 62 */     requestDateBuilder.append(startTime);
/* 63 */     requestDateBuilder.append("&endTime=");
/* 64 */     requestDateBuilder.append(endTime);
/* 65 */     requestDateBuilder.append("&topLimit=");
/* 66 */     requestDateBuilder.append(topLimit);
/* 67 */     String requestData = requestDateBuilder.toString();
/* 68 */     CNetSDKLog.i("DeviceGpsRequest", "getRequestString,requestData:" + requestData);
/* 69 */     return requestData;
/*    */   }
/*    */   
/*    */   public String getBackString()
/*    */   {
/* 74 */     return super.getBackString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\deviceGps\DeviceGpsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */