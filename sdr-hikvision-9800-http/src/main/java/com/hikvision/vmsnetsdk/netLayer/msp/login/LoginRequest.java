/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.login;
/*    */ 
/*    */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.MspServer;
import com.hikvision.vmsnetsdk.util.EnCode;
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
/*    */ 
/*    */ public class LoginRequest
/*    */   extends MspRequest
/*    */ {
/*    */   private String password;
/*    */   private String userName;
/*    */   private String macAddress;
/*    */   private static final String TAG = "LoginRequest";
/*    */   MspServer mspServer;
/*    */   private int passwordEncryptionMode;
/*    */   private String domainAddress;
/*    */   
/*    */   public LoginRequest(MspServer server, IRequestTool iRequestTool, String password, String userName, String macAddress, int passwordEncryptionMode, String domainAddress)
/*    */   {
/* 28 */     super(server, iRequestTool);
/* 29 */     this.password = password;
/* 30 */     this.userName = userName;
/* 31 */     this.macAddress = macAddress;
/* 32 */     this.mspServer = server;
/* 33 */     this.passwordEncryptionMode = passwordEncryptionMode;
/* 34 */     this.domainAddress = domainAddress;
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 39 */     CNetSDKLog.i("LoginRequest", "getRequestAddr,start.");
/* 40 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 41 */       CNetSDKLog.e("LoginRequest", "getRequestAddr,param error.");
/* 42 */       return null;
/*    */     }
/*    */     
/* 45 */     String requestAddr = "";
/* 46 */     requestAddr = String.format("%s/mobile/login", new Object[] { this.mspServer.gethttpsAddr() });
/* 47 */     CNetSDKLog.i("LoginRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 48 */     return requestAddr;
/*    */   }
/*    */   
/*    */   public String getRequestData()
/*    */   {
/* 53 */     CNetSDKLog.i("LoginRequest", "getRequestData,start.");
/*    */     
/* 55 */     if ((this.userName == null) || (this.password == null) || (this.userName.length() <= 0) || (this.password.length() <= 0)) {
/* 56 */       CNetSDKLog.e("LoginRequest", "getRequestData,param error.");
/* 57 */       return null;
/*    */     }
/*    */     
/*    */ 
/* 61 */     String userNameUtf8 = EnCode.enCodeByUtf8(this.userName);
/* 62 */     String passwordEncode = null;
/* 63 */     if (this.passwordEncryptionMode == 0) {
/* 64 */       passwordEncode = EnCode.enCodeByMd5(this.password);
/*    */     } else {
/* 66 */       passwordEncode = EnCode.enCodeBySHA256(this.password);
/*    */     }
/*    */     
/* 69 */     if ((userNameUtf8 == null) || (passwordEncode == null)) {
/* 70 */       CNetSDKLog.e("LoginRequest", "getRequestString,encode param error.");
/*    */     }
/*    */     
/* 73 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 74 */     requestDateBuilder.append("username=");
/* 75 */     requestDateBuilder.append(userNameUtf8);
/* 76 */     requestDateBuilder.append("&password=");
/* 77 */     requestDateBuilder.append(passwordEncode);
/* 78 */     if ((this.macAddress != null) && (this.macAddress.length() > 0)) {
/* 79 */       requestDateBuilder.append("&mac=");
/* 80 */       requestDateBuilder.append(this.macAddress);
/*    */     }
/* 82 */     requestDateBuilder.append("&loginAddr=");
/* 83 */     requestDateBuilder.append(this.domainAddress);
/* 84 */     String requestData = requestDateBuilder.toString();
/* 85 */     CNetSDKLog.i("LoginRequest", "getRequestString,requestData:" + requestData);
/*    */     
/* 87 */     return requestData;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\login\LoginRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */