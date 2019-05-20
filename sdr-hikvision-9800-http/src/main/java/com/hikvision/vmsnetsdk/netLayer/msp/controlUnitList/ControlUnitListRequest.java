/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.controlUnitList;
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
/*    */ public class ControlUnitListRequest
/*    */   extends MspRequest
/*    */ {
/*    */   private static final String TAG = "ControlUnitListRequest";
/*    */   private MspServer mspServer;
/*    */   private String sessionId;
/*    */   private String curControlUnitId;
/*    */   private int numPerPage;
/*    */   private int curPage;
/*    */   
/*    */   public ControlUnitListRequest(MspServer server, IRequestTool iRequestTool, String sessionId, String curControlUnitID, int numPerPage, int curPage)
/*    */   {
/* 25 */     super(server, iRequestTool);
/* 26 */     this.sessionId = sessionId;
/* 27 */     this.mspServer = server;
/* 28 */     this.curControlUnitId = curControlUnitID;
/* 29 */     this.numPerPage = numPerPage;
/* 30 */     this.curPage = curPage;
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 35 */     CNetSDKLog.i("ControlUnitListRequest", "getRequestAddr,start.");
/* 36 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 37 */       CNetSDKLog.e("ControlUnitListRequest", "getRequestAddr,param error.");
/* 38 */       return null;
/*    */     }
/*    */     
/* 41 */     String requestAddr = String.format("%s/mobile/getControlUnit", new Object[] { this.mspServer.gethttpsAddr() });
/* 42 */     CNetSDKLog.i("ControlUnitListRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 43 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 49 */     CNetSDKLog.i("ControlUnitListRequest", "getRequestData,start.");
/* 50 */     if ((this.sessionId == null) || (this.sessionId.length() <= 0) || (this.numPerPage <= 0) || (this.curPage <= 0)) {
/* 51 */       CNetSDKLog.e("ControlUnitListRequest", "getRequestData,param error.");
/* 52 */       return null;
/*    */     }
/*    */     
/* 55 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 56 */     requestDateBuilder.append("sessionID=");
/* 57 */     requestDateBuilder.append(this.sessionId);
/* 58 */     requestDateBuilder.append("&curControlUnitID=");
/* 59 */     requestDateBuilder.append(this.curControlUnitId);
/* 60 */     requestDateBuilder.append("&numPerPage=");
/* 61 */     requestDateBuilder.append(this.numPerPage);
/* 62 */     requestDateBuilder.append("&curPage=");
/* 63 */     requestDateBuilder.append(this.curPage);
/*    */     
/* 65 */     String requestData = requestDateBuilder.toString();
/* 66 */     CNetSDKLog.i("ControlUnitListRequest", "getRequestString,requestData:" + requestData);
/* 67 */     return requestData;
/*    */   }
/*    */   
/*    */   public String getBackString()
/*    */   {
/* 72 */     return super.getBackString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\controlUnitList\ControlUnitListRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */