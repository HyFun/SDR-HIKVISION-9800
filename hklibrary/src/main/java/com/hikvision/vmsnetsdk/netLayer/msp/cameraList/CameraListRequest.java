/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.cameraList;
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
/*    */ public class CameraListRequest
/*    */   extends MspRequest
/*    */ {
/*    */   private static final String TAG = "CameraListRequest";
/*    */   private MspServer mspServer;
/*    */   private String sessionId;
/*    */   private String curControlUnitId;
/*    */   private int numPerPage;
/*    */   private int curPage;
/*    */   public static final int CONTROLUNITID_ALL = 0;
/*    */   
/*    */   public CameraListRequest(MspServer server, IRequestTool iRequestTool, String sessionId, String curControlUnitID, int numPerPage, int curPage)
/*    */   {
/* 28 */     super(server, iRequestTool);
/* 29 */     this.sessionId = sessionId;
/* 30 */     this.mspServer = server;
/* 31 */     this.curControlUnitId = curControlUnitID;
/* 32 */     this.numPerPage = numPerPage;
/* 33 */     this.curPage = curPage;
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 38 */     CNetSDKLog.i("CameraListRequest", "getRequestAddr,start.");
/* 39 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 40 */       CNetSDKLog.e("CameraListRequest", "getRequestAddr,param error.");
/* 41 */       return null;
/*    */     }
/*    */     
/* 44 */     String requestAddr = String.format("%s/mobile/getCamera", new Object[] { this.mspServer.gethttpsAddr() });
/* 45 */     CNetSDKLog.i("CameraListRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 46 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 52 */     CNetSDKLog.i("CameraListRequest", "getRequestData,start.");
/* 53 */     if ((this.sessionId == null) || (this.sessionId.length() <= 0) || (this.numPerPage <= 0) || (this.curPage <= 0)) {
/* 54 */       CNetSDKLog.e("CameraListRequest", "getRequestData,param error.");
/* 55 */       return null;
/*    */     }
/*    */     
/* 58 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 59 */     requestDateBuilder.append("sessionID=");
/* 60 */     requestDateBuilder.append(this.sessionId);
/* 61 */     requestDateBuilder.append("&curControlUnitID=");
/* 62 */     requestDateBuilder.append(this.curControlUnitId);
/* 63 */     requestDateBuilder.append("&numPerPage=");
/* 64 */     requestDateBuilder.append(this.numPerPage);
/* 65 */     requestDateBuilder.append("&curPage=");
/* 66 */     requestDateBuilder.append(this.curPage);
/*    */     
/* 68 */     String requestData = requestDateBuilder.toString();
/* 69 */     CNetSDKLog.i("CameraListRequest", "getRequestString,requestData:" + requestData);
/* 70 */     return requestData;
/*    */   }
/*    */   
/*    */   public String getBackString()
/*    */   {
/* 75 */     return super.getBackString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\cameraList\CameraListRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */