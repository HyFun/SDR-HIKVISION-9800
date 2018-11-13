/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.login;
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
/*    */ public class AutoLoginExcuteRequest
/*    */   extends MspRequest
/*    */ {
/* 11 */   private String session = null;
/*    */   
/* 13 */   private String mac = null;
/*    */   
/* 15 */   private String serviceIp = null;
/*    */   
/* 17 */   private String clientIp = null;
/*    */   private static final String TAG = "AutoLoginExcuteRequest";
/* 19 */   private MspServer mSpServer = null;
/*    */   
/*    */   public AutoLoginExcuteRequest(MspServer server, IRequestTool iRequestTool, String session, String mac, String serviceIp, String clientIp)
/*    */   {
/* 23 */     super(server, iRequestTool);
/* 24 */     this.session = session;
/* 25 */     this.mac = mac;
/* 26 */     this.serviceIp = serviceIp;
/* 27 */     this.clientIp = clientIp;
/* 28 */     this.mSpServer = server;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestAddr()
/*    */   {
/* 34 */     CNetSDKLog.i("AutoLoginExcuteRequest", "getRequestAddr,start.");
/* 35 */     if ((this.mSpServer.getIp() == null) || (this.mSpServer.gethttpsAddr() == null)) {
/* 36 */       CNetSDKLog.e("AutoLoginExcuteRequest", "getRequestAddr,param error.");
/* 37 */       return null;
/*    */     }
/*    */     
/* 40 */     String requestAddr = String.format("%s/mobile/autoLogin", new Object[] {this.mSpServer
/* 41 */       .gethttpsAddr() });
/* 42 */     CNetSDKLog.i("AutoLoginExcuteRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 43 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 49 */     CNetSDKLog.i("AutoLoginExcuteRequest", "getRequestData,start.");
/* 50 */     if (((this.session == null) || (this.session.length() <= 0)) && ((this.mac == null) || 
/* 51 */       (this.mac.length() <= 0)) && ((this.serviceIp == null) || 
/* 52 */       (this.serviceIp.length() <= 0)) && ((this.clientIp == null) || 
/* 53 */       (this.clientIp.length() <= 0))) {
/* 54 */       CNetSDKLog.e("AutoLoginExcuteRequest", "getRequestData,param error.");
/* 55 */       return null;
/*    */     }
/*    */     
/* 58 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 59 */     requestDateBuilder.append("autoLoginSession=");
/* 60 */     requestDateBuilder.append(this.session);
/* 61 */     requestDateBuilder.append("&clientmac=");
/* 62 */     requestDateBuilder.append(this.mac);
/* 63 */     requestDateBuilder.append("&serviceIp=");
/* 64 */     requestDateBuilder.append(this.serviceIp);
/* 65 */     requestDateBuilder.append("&clientIp=");
/* 66 */     requestDateBuilder.append(this.clientIp);
/*    */     
/* 68 */     String requestData = requestDateBuilder.toString();
/* 69 */     CNetSDKLog.i("AutoLoginExcuteRequest", "getRequestString,requestData:" + requestData);
/* 70 */     return requestData;
/*    */   }
/*    */   
/*    */   public String getBackString()
/*    */   {
/* 75 */     return super.getBackString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\login\AutoLoginExcuteRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */