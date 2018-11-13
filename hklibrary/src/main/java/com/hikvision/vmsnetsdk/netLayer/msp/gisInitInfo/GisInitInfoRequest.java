/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.gisInitInfo;
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
/*    */ public class GisInitInfoRequest
/*    */   extends MspRequest
/*    */ {
/*    */   private String sessionId;
/*    */   private static final String TAG = "GisInitInfoRequest";
/*    */   private MspServer mspServer;
/*    */   
/*    */   public GisInitInfoRequest(MspServer mspServer, IRequestTool iRequestTool, String sessionId)
/*    */   {
/* 21 */     super(mspServer, iRequestTool);
/* 22 */     this.sessionId = sessionId;
/* 23 */     this.mspServer = mspServer;
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 28 */     CNetSDKLog.i("GisInitInfoRequest", "getRequestAddr,start.");
/* 29 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 30 */       CNetSDKLog.e("GisInitInfoRequest", "getRequestAddr,param error.");
/* 31 */       return null;
/*    */     }
/*    */     
/* 34 */     String requestAddr = String.format("%s/mobile/getGisInitInfo", new Object[] { this.mspServer.gethttpsAddr() });
/* 35 */     CNetSDKLog.i("GisInitInfoRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 36 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 42 */     CNetSDKLog.i("GisInitInfoRequest", "getRequestData,start.");
/* 43 */     if ((this.sessionId == null) || (this.sessionId.length() <= 0)) {
/* 44 */       CNetSDKLog.e("GisInitInfoRequest", "getRequestData,param error.");
/* 45 */       return null;
/*    */     }
/*    */     
/* 48 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 49 */     requestDateBuilder.append("sessionID=");
/* 50 */     requestDateBuilder.append(this.sessionId);
/*    */     
/* 52 */     String requestData = requestDateBuilder.toString();
/* 53 */     CNetSDKLog.i("GisInitInfoRequest", "getRequestString,requestData:" + requestData);
/* 54 */     return requestData;
/*    */   }
/*    */   
/*    */   public String getBackString()
/*    */   {
/* 59 */     return super.getBackString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\gisInitInfo\GisInitInfoRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */