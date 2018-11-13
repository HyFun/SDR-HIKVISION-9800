/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.login;
/*    */ 
/*    */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.MspServer;

/*    */
/*    */

/*    */
/*    */ public class AutoLoginRequest extends MspRequest
/*    */ {
/*  9 */   private String mPlayToken = null;
/*    */   private static final String TAG = "AutoLoginRequest";
/* 11 */   private MspServer mSpServer = null;
/*    */   
/* 13 */   private int mDay = 10;
/*    */   
/*    */   public AutoLoginRequest(MspServer mspServer, com.hikvision.vmsnetsdk.util.intf.IRequestTool iRequestTool, String token, int day) {
/* 16 */     super(mspServer, iRequestTool);
/*    */     
/* 18 */     this.mPlayToken = token;
/* 19 */     this.mSpServer = mspServer;
/* 20 */     this.mDay = day;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestAddr()
/*    */   {
/* 26 */     CNetSDKLog.i("AutoLoginRequest", "getRequestAddr,start.");
/* 27 */     if ((this.mSpServer.getIp() == null) || (this.mSpServer.gethttpsAddr() == null)) {
/* 28 */       CNetSDKLog.e("AutoLoginRequest", "getRequestAddr,param error.");
/* 29 */       return null;
/*    */     }
/*    */     
/* 32 */     String requestAddr = String.format("%s/mobile/applyAutoLogin", new Object[] { this.mSpServer.gethttpsAddr() });
/* 33 */     CNetSDKLog.i("AutoLoginRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 34 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 40 */     CNetSDKLog.i("AutoLoginRequest", "getRequestData,start.");
/* 41 */     if ((this.mPlayToken == null) || (this.mPlayToken.length() <= 0)) {
/* 42 */       CNetSDKLog.e("AutoLoginRequest", "getRequestData,param error.");
/* 43 */       return null;
/*    */     }
/*    */     
/* 46 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 47 */     requestDateBuilder.append("st=");
/* 48 */     requestDateBuilder.append(this.mPlayToken);
/* 49 */     requestDateBuilder.append("&validity=");
/* 50 */     requestDateBuilder.append(this.mDay);
/*    */     
/* 52 */     String requestData = requestDateBuilder.toString();
/* 53 */     CNetSDKLog.i("AutoLoginRequest", "getRequestString,requestData:" + requestData);
/* 54 */     return requestData;
/*    */   }
/*    */   
/*    */   public String getBackString()
/*    */   {
/* 59 */     return super.getBackString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\login\AutoLoginRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */