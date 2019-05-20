/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.alarmInfo;
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
/*    */ public class AlarmInfoRequest
/*    */   extends MspRequest
/*    */ {
/*    */   private String sessionId;
/*    */   private static final String TAG = "AlarmInfoRequest";
/*    */   private MspServer mspServer;
/*    */   private String alarmId;
/*    */   
/*    */   public AlarmInfoRequest(MspServer server, IRequestTool iRequestTool, String sessionId, String alarmId)
/*    */   {
/* 22 */     super(server, iRequestTool);
/* 23 */     this.sessionId = sessionId;
/* 24 */     this.mspServer = server;
/* 25 */     this.alarmId = alarmId;
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 30 */     CNetSDKLog.i("AlarmInfoRequest", "getRequestAddr,start.");
/* 31 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 32 */       CNetSDKLog.e("AlarmInfoRequest", "getRequestAddr,param error.");
/* 33 */       return null;
/*    */     }
/*    */     
/* 36 */     String requestAddr = String.format("%s/mobile/getAlarmInfo", new Object[] { this.mspServer.gethttpsAddr() });
/* 37 */     CNetSDKLog.i("AlarmInfoRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 38 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 44 */     CNetSDKLog.i("AlarmInfoRequest", "getRequestData,start.");
/* 45 */     if ((this.sessionId == null) || (this.alarmId == null) || (this.sessionId.length() <= 0) || (this.alarmId.length() <= 0)) {
/* 46 */       CNetSDKLog.e("AlarmInfoRequest", "getRequestData,param error.");
/* 47 */       return null;
/*    */     }
/*    */     
/* 50 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 51 */     requestDateBuilder.append("sessionID=");
/* 52 */     requestDateBuilder.append(this.sessionId);
/*    */     
/* 54 */     requestDateBuilder.append("&alarmID=");
/* 55 */     requestDateBuilder.append(this.alarmId);
/* 56 */     String requestData = requestDateBuilder.toString();
/* 57 */     CNetSDKLog.i("AlarmInfoRequest", "getRequestString,requestData:" + requestData);
/* 58 */     return requestData;
/*    */   }
/*    */   
/*    */   public String getBackString()
/*    */   {
/* 63 */     return super.getBackString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\alarmInfo\AlarmInfoRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */