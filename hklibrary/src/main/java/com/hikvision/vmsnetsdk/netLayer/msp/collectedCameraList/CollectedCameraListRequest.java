/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.collectedCameraList;
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
/*    */ public class CollectedCameraListRequest
/*    */   extends MspRequest
/*    */ {
/*    */   private static final String TAG = "CollectedCameraListRequest";
/*    */   private MspServer mspServer;
/*    */   private String sessionId;
/*    */   private int numPerPage;
/*    */   private int curPage;
/*    */   private String mGroupID;
/*    */   
/*    */   public CollectedCameraListRequest(MspServer server, IRequestTool iRequestTool, String sessionId, int numPerPage, int curPage)
/*    */   {
/* 25 */     super(server, iRequestTool);
/* 26 */     this.sessionId = sessionId;
/* 27 */     this.mspServer = server;
/* 28 */     this.curPage = curPage;
/* 29 */     this.numPerPage = numPerPage;
/*    */   }
/*    */   
/*    */   public CollectedCameraListRequest(MspServer server, IRequestTool iRequestTool, String sessionId, int numPerPage, int curPage, String groupID)
/*    */   {
/* 34 */     super(server, iRequestTool);
/* 35 */     this.sessionId = sessionId;
/* 36 */     this.mspServer = server;
/* 37 */     this.curPage = curPage;
/* 38 */     this.numPerPage = numPerPage;
/* 39 */     this.mGroupID = groupID;
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 44 */     CNetSDKLog.i("CollectedCameraListRequest", "getRequestAddr,start.");
/* 45 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/* 46 */       CNetSDKLog.e("CollectedCameraListRequest", "getRequestAddr,param error.");
/* 47 */       return null;
/*    */     }
/*    */     
/* 50 */     String requestAddr = String.format("%s/mobile/getCollectedCamera", new Object[] { this.mspServer.gethttpsAddr() });
/* 51 */     CNetSDKLog.i("CollectedCameraListRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 52 */     return requestAddr;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 58 */     CNetSDKLog.i("CollectedCameraListRequest", "getRequestData,start.");
/* 59 */     if ((this.sessionId == null) || (this.sessionId.length() <= 0) || (this.numPerPage <= 0) || (this.curPage <= 0)) {
/* 60 */       CNetSDKLog.e("CollectedCameraListRequest", "getRequestData,param error.");
/* 61 */       return null;
/*    */     }
/*    */     
/* 64 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 65 */     requestDateBuilder.append("sessionID=");
/* 66 */     requestDateBuilder.append(this.sessionId);
/* 67 */     requestDateBuilder.append("&numPerPage=");
/* 68 */     requestDateBuilder.append(this.numPerPage);
/* 69 */     requestDateBuilder.append("&curPage=");
/* 70 */     requestDateBuilder.append(this.curPage);
/* 71 */     requestDateBuilder.append("&groupID=");
/* 72 */     requestDateBuilder.append(this.mGroupID);
/*    */     
/* 74 */     String requestData = requestDateBuilder.toString();
/* 75 */     CNetSDKLog.i("CollectedCameraListRequest", "getRequestString,requestData:" + requestData);
/* 76 */     return requestData;
/*    */   }
/*    */   
/*    */   public String getBackString()
/*    */   {
/* 81 */     return super.getBackString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\collectedCameraList\CollectedCameraListRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */