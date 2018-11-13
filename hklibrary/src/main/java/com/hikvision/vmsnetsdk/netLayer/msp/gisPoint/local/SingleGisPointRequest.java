/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.gisPoint.local;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SingleGisPointRequest
/*    */   extends MspRequest
/*    */ {
/*    */   private static final String TAG = "SingleGisPointRequest";
/*    */   private String sessionId;
/*    */   private String indexCode;
/*    */   private MspServer mspServer;
/*    */   private String pointType;
/*    */   
/*    */   public SingleGisPointRequest(MspServer server, IRequestTool iRequestTool, String sessionId, String indexCode, String pointType)
/*    */   {
/* 30 */     super(server, iRequestTool);
/* 31 */     this.mspServer = server;
/* 32 */     this.sessionId = sessionId;
/* 33 */     this.indexCode = indexCode;
/* 34 */     this.pointType = pointType;
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 39 */     if ((this.mspServer == null) || (this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 40 */       CNetSDKLog.e("SingleGisPointRequest", "getRequestAddr,param error.");
/* 41 */       return null;
/*    */     }
/*    */     
/* 44 */     String requestAddr = String.format("%s/mobile/query", new Object[] { this.mspServer.gethttpsAddr() });
/* 45 */     CNetSDKLog.i("SingleGisPointRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 46 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 52 */     if ((this.sessionId == null) || (this.indexCode == null) || (this.sessionId.length() <= 0) || (this.indexCode.length() <= 0) || (this.pointType == null) || 
/* 53 */       (this.pointType.length() <= 0)) {
/* 54 */       CNetSDKLog.e("SingleGisPointRequest", "getRequestData,param error.");
/* 55 */       return null;
/*    */     }
/*    */     
/*    */ 
/* 59 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 60 */     requestDateBuilder.append("sessionID=");
/* 61 */     requestDateBuilder.append(this.sessionId);
/* 62 */     requestDateBuilder.append("&pointType=");
/* 63 */     requestDateBuilder.append(this.pointType);
/* 64 */     requestDateBuilder.append("&indexCode=");
/* 65 */     requestDateBuilder.append(this.indexCode);
/*    */     
/* 67 */     String requestData = requestDateBuilder.toString();
/* 68 */     CNetSDKLog.i("SingleGisPointRequest", "getRequestString,requestData:" + requestData);
/* 69 */     return requestData;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\gisPoint\local\SingleGisPointRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */