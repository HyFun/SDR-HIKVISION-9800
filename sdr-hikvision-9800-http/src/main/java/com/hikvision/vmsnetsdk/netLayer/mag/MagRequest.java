/*    */ package com.hikvision.vmsnetsdk.netLayer.mag;
/*    */ 
/*    */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.NetSyncHttpRequest;
import com.hikvision.vmsnetsdk.util.intf.IRequestTool;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ 
/*    */ 
/*    */ public abstract class MagRequest
/*    */   extends NetSyncHttpRequest
/*    */ {
/*    */   private static final String TAG = "MagRequest";
/*    */   private int errorCode;
/*    */   private String errorDescribe;
/*    */   private boolean isHttpsRequest;
/*    */   protected static final String XML_BEGIN_DOC = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
/*    */   public static final String COMMAND_QUERY_VRM = "0";
/*    */   public static final String COMMAND_PTZ = "1";
/*    */   public static final String COMMAND_REGISTER_MAG = "7";
/*    */   public static final String COMMAND_LOGOUT_MAG = "8";
/*    */   public static final String COMMAND_ABILITY_OF_MAG = "9";
/*    */   public static final String COMMAND_GET_KMS_INFO = "14";
/*    */   public static final String COMMAND_QUERY_NCG = "10";
/*    */   public static final String COMMAND_NCG_PTZ = "17";
/*    */   public static final String COMMAND_QUERY_VERM_4 = "13";
/*    */   
/*    */   public MagRequest(IRequestTool iRequestTool, boolean isHttpsRequest)
/*    */   {
/* 31 */     super(iRequestTool);
/* 32 */     this.isHttpsRequest = isHttpsRequest;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void insertXMLRequestExplain(XmlSerializer serializer)
/*    */     throws IllegalArgumentException, IllegalStateException, IOException
/*    */   {
/* 46 */     serializer.startTag(null, "Version");
/* 47 */     serializer.text("0.1");
/* 48 */     serializer.endTag(null, "Version");
/* 49 */     serializer.startTag(null, "Sequence");
/* 50 */     serializer.text("1");
/* 51 */     serializer.endTag(null, "Sequence");
/* 52 */     serializer.startTag(null, "CommandType");
/* 53 */     serializer.text("0");
/* 54 */     serializer.endTag(null, "CommandType");
/*    */   }
/*    */   
/*    */   public boolean request()
/*    */   {
/* 59 */     if (this.iRequestTool == null) {
/* 60 */       CNetSDKLog.e("MagRequest", "request,no iRequestTool.");
/* 61 */       return false;
/*    */     }
/*    */     try
/*    */     {
/* 65 */       String requestAddr = getRequestAddr();
/* 66 */       CNetSDKLog.i("MagRequest", "request,requestAddr:" + requestAddr);
/* 67 */       String requestData = getRequestData();
/* 68 */       if (!isParamOk(requestAddr, requestData)) {
/* 69 */         return false;
/*    */       }
/*    */       
/* 72 */       if (this.isHttpsRequest) {
/* 73 */         this.backStr = this.iRequestTool.httpsPostRequest(requestAddr, requestData);
/*    */       } else {
/* 75 */         this.backStr = this.iRequestTool.httpPostRequest(requestAddr, requestData);
/*    */       }
/*    */     }
/*    */     catch (Exception e) {
/* 79 */       CNetSDKLog.e("MagRequest", "request,Exception.");
/* 80 */       return false;
/*    */     }
/* 82 */     if ((this.backStr == null) || (this.backStr.equals(""))) {
/* 83 */       this.errorCode = this.iRequestTool.getLastErrorCode();
/* 84 */       this.errorDescribe = this.iRequestTool.getLastErrorDesc();
/* 85 */       return false;
/*    */     }
/* 87 */     CNetSDKLog.i("MagRequest", "request,backStr:" + this.backStr);
/* 88 */     return true;
/*    */   }
/*    */   
/*    */   public int getErrorCode() {
/* 92 */     return this.errorCode;
/*    */   }
/*    */   
/*    */   public String getErrorDescribe() {
/* 96 */     return this.errorDescribe;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\mag\MagRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */