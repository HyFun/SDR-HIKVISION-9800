/*    */ package com.hikvision.vmsnetsdk.netLayer.msp;
/*    */ 
/*    */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.NetSyncHttpRequest;
import com.hikvision.vmsnetsdk.netLayer.base.NetHttpServer;
import com.hikvision.vmsnetsdk.util.intf.IRequestTool;

/*    */
/*    */
/*    */

/*    */
/*    */ public abstract class MspRequest
/*    */   extends NetSyncHttpRequest
/*    */ {
/*    */   private int errorCode;
/*    */   private String errorDescribe;
/*    */   private static final String TAG = "MspRequest";
/*    */   
/*    */   public MspRequest(NetHttpServer paramNetHttpServer, IRequestTool paramIRequestTool)
/*    */   {
/* 17 */     super(paramNetHttpServer, paramIRequestTool);
/*    */   }
/*    */   
/*    */   public boolean request()
/*    */   {
/* 22 */     if (this.iRequestTool == null)
/*    */     {
/* 24 */       CNetSDKLog.e("MspRequest", "request,no iRequestTool.");
/* 25 */       return false;
/*    */     }
/* 27 */     String str1 = getRequestAddr();
/* 28 */     String str2 = getRequestData();
/* 29 */     if (!isParamOk(str1, str2)) {
/* 30 */       return false;
/*    */     }
/* 32 */     str1 = str1.replaceFirst("https", "http");
/* 33 */     CNetSDKLog.i("request url transform >>>>>>>>>", str1);
/* 34 */     this.backStr = this.iRequestTool.httpPostRequest(str1, str2);
/* 35 */     if ((this.backStr == null) || (this.backStr.equals("")))
/*    */     {
/* 37 */       this.errorCode = this.iRequestTool.getLastErrorCode();
/* 38 */       this.errorDescribe = this.iRequestTool.getLastErrorDesc();
/* 39 */       return false;
/*    */     }
/* 41 */     CNetSDKLog.i("MspRequest", "request,backStr:" + this.backStr);
/* 42 */     return true;
/*    */   }
/*    */   
/*    */   public int getErrorCode()
/*    */   {
/* 47 */     return this.errorCode;
/*    */   }
/*    */   
/*    */   public String getErrorDescribe()
/*    */   {
/* 52 */     return this.errorDescribe;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\MspRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */