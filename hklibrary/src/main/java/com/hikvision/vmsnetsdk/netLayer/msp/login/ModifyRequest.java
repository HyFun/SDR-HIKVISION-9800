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
/*    */ public class ModifyRequest extends MspRequest
/*    */ {
/*    */   private String password;
/*    */   private String userName;
/*    */   private String modifyId;
/*    */   private int pwdLevel;
/*    */   private static final String TAG = "LoginRequest";
/*    */   MspServer mspServer;
/*    */   
/*    */   public ModifyRequest(MspServer server, IRequestTool iRequestTool, String password, String userName, String modifyId, int pwdLevel)
/*    */   {
/* 19 */     super(server, iRequestTool);
/* 20 */     this.password = password;
/* 21 */     this.userName = userName;
/* 22 */     this.mspServer = server;
/* 23 */     this.modifyId = modifyId;
/* 24 */     this.pwdLevel = pwdLevel;
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 29 */     CNetSDKLog.i("LoginRequest", "getRequestAddr,start.");
/* 30 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 31 */       CNetSDKLog.e("LoginRequest", "getRequestAddr,param error.");
/* 32 */       return null;
/*    */     }
/*    */     
/* 35 */     String requestAddr = "";
/* 36 */     requestAddr = String.format("%s/mobile/loginModifyPassword", new Object[] { this.mspServer.gethttpsAddr() });
/* 37 */     CNetSDKLog.i("LoginRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 38 */     return requestAddr;
/*    */   }
/*    */   
/*    */   public String getRequestData()
/*    */   {
/* 43 */     CNetSDKLog.i("LoginRequest", "getRequestData,start.");
/*    */     
/* 45 */     if ((this.modifyId == null) || (this.password == null) || (this.modifyId.length() <= 0) || (this.password.length() <= 0)) {
/* 46 */       CNetSDKLog.e("LoginRequest", "getRequestData,param error.");
/* 47 */       return null;
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 52 */     String passwordHAS256 = com.hikvision.vmsnetsdk.util.EnCode.enCodeBySHA256(this.password);
/* 53 */     if (passwordHAS256 == null) {
/* 54 */       CNetSDKLog.e("LoginRequest", "getRequestString,encode param error.");
/*    */     }
/*    */     
/* 57 */     StringBuilder requestDateBuilder = new StringBuilder();
/*    */     
/*    */ 
/* 60 */     requestDateBuilder.append("login_modify_password_id=").append(this.modifyId);
/* 61 */     requestDateBuilder.append("&new_password=").append(passwordHAS256);
/* 62 */     requestDateBuilder.append("&password_level=").append(this.pwdLevel);
/* 63 */     String requestData = requestDateBuilder.toString();
/* 64 */     CNetSDKLog.i("LoginRequest", "getRequestString,requestData:" + requestData);
/*    */     
/* 66 */     return requestData;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\login\ModifyRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */