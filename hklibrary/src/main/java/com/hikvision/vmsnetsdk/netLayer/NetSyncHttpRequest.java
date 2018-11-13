/*    */ package com.hikvision.vmsnetsdk.netLayer;
/*    */ 
/*    */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.base.NetHttpRequest;
import com.hikvision.vmsnetsdk.netLayer.base.NetHttpServer;
import com.hikvision.vmsnetsdk.util.intf.IRequestTool;

/*    */
/*    */
/*    */

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class NetSyncHttpRequest
/*    */   extends NetHttpRequest
/*    */ {
/*    */   private static final String TAG = "NetSyncHttpRequest";
/*    */   
/*    */   public NetSyncHttpRequest(NetHttpServer server, IRequestTool iRequestTool)
/*    */   {
/* 19 */     super(server, iRequestTool);
/*    */   }
/*    */   
/*    */   public NetSyncHttpRequest(IRequestTool iRequestTool) {
/* 23 */     super(iRequestTool);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public abstract boolean request();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean isParamOk()
/*    */   {
/* 36 */     String requestAddr = getRequestAddr();
/* 37 */     String requestData = getRequestData();
/* 38 */     return isParamOk(requestAddr, requestData);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected boolean isParamOk(String requestAddr, String requestData)
/*    */   {
/* 50 */     if ((requestAddr == null) || (requestData == null)) {
/* 51 */       CNetSDKLog.e("NetSyncHttpRequest", "isParamOk,can not get requestAddr or requestData");
/* 52 */       return false;
/*    */     }
/* 54 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\NetSyncHttpRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */