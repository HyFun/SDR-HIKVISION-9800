/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.gisPoint.all;
/*    */ 
/*    */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.MspServer;
import com.hikvision.vmsnetsdk.util.ParseHelper;
import com.hikvision.vmsnetsdk.util.intf.IRequestTool;

import java.util.ArrayList;

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
/*    */ public class AllGisPointRequest
/*    */   extends MspRequest
/*    */ {
/*    */   private static final String TAG = "AllGisPointRequest";
/*    */   private MspServer mspServer;
/*    */   private String sessionId;
/*    */   private String pointTypes;
/*    */   private int numPerPage;
/*    */   private int curPage;
/*    */   
/*    */   public AllGisPointRequest(MspServer server, IRequestTool iRequestTool, String sessionId, ArrayList<Integer> pointTypes, int numPerPage, int curPage)
/*    */   {
/* 28 */     super(server, iRequestTool);
/* 29 */     this.sessionId = sessionId;
/* 30 */     this.mspServer = server;
/* 31 */     this.pointTypes = ParseHelper.changeIntListToString(pointTypes);
/* 32 */     this.numPerPage = numPerPage;
/* 33 */     this.curPage = curPage;
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 38 */     CNetSDKLog.i("AllGisPointRequest", "getRequestAddr,start.");
/* 39 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 40 */       CNetSDKLog.e("AllGisPointRequest", "getRequestAddr,param error.");
/* 41 */       return null;
/*    */     }
/*    */     
/* 44 */     String requestAddr = String.format("%s/mobile/getGisPoint", new Object[] { this.mspServer.gethttpsAddr() });
/* 45 */     CNetSDKLog.i("AllGisPointRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 46 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 52 */     CNetSDKLog.i("AllGisPointRequest", "getRequestData,start.");
/* 53 */     if ((this.sessionId == null) || (this.sessionId.length() <= 0) || (this.curPage <= 0) || (this.numPerPage <= 0) || (this.pointTypes == null)) {
/* 54 */       CNetSDKLog.e("AllGisPointRequest", "getRequestData,param error.");
/* 55 */       return null;
/*    */     }
/*    */     
/* 58 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 59 */     requestDateBuilder.append("sessionID=");
/* 60 */     requestDateBuilder.append(this.sessionId);
/* 61 */     requestDateBuilder.append("&pointType=");
/* 62 */     requestDateBuilder.append(this.pointTypes);
/* 63 */     requestDateBuilder.append("&numPerPage=");
/* 64 */     requestDateBuilder.append(this.numPerPage);
/* 65 */     requestDateBuilder.append("&curPage=");
/* 66 */     requestDateBuilder.append(this.curPage);
/*    */     
/* 68 */     String requestData = requestDateBuilder.toString();
/* 69 */     CNetSDKLog.i("AllGisPointRequest", "getRequestString,requestData:" + requestData);
/* 70 */     return requestData;
/*    */   }
/*    */   
/*    */   public String getBackString()
/*    */   {
/* 75 */     return super.getBackString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\gisPoint\all\AllGisPointRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */