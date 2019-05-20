/*     */ package com.hikvision.vmsnetsdk.netLayer.msp.gisPoint.local;
/*     */ 
/*     */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspRequest;
import com.hikvision.vmsnetsdk.netLayer.msp.MspServer;
import com.hikvision.vmsnetsdk.util.intf.IRequestTool;

/*     */
/*     */
/*     */

/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LocalGisPointRequest
/*     */   extends MspRequest
/*     */ {
/*     */   private static final String TAG = "LocalGisPointRequest";
/*     */   private MspServer mspServer;
/*     */   private int searchMode;
/*     */   BaseGisSearchParam baseGisSearchParam;
/*     */   InRectangleGisSearchParam inRectangleGisSearchParam;
/*     */   InRoundGisSearchParam inRoundGisSearchParam;
/*     */   public static final int SEARCH_MODE_BASE = 0;
/*     */   public static final int SEARCH_MODE_IN_RECTANGLE = 1;
/*     */   public static final int SEARCH_MODE_IN_ROUND = 2;
/*     */   
/*     */   public LocalGisPointRequest(MspServer server, IRequestTool iRequestTool, BaseGisSearchParam baseGisSearchParam)
/*     */   {
/*  36 */     super(server, iRequestTool);
/*  37 */     this.mspServer = server;
/*     */     
/*  39 */     this.baseGisSearchParam = baseGisSearchParam;
/*  40 */     this.searchMode = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LocalGisPointRequest(MspServer server, IRequestTool iRequestTool, BaseGisSearchParam baseGisSearchParam, InRectangleGisSearchParam inRectangleGisSearchParam)
/*     */   {
/*  53 */     super(server, iRequestTool);
/*  54 */     this.mspServer = server;
/*     */     
/*  56 */     this.baseGisSearchParam = baseGisSearchParam;
/*  57 */     this.inRectangleGisSearchParam = inRectangleGisSearchParam;
/*  58 */     this.searchMode = 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LocalGisPointRequest(MspServer server, IRequestTool iRequestTool, BaseGisSearchParam baseGisSearchParam, InRoundGisSearchParam inRoundGisSearchParam)
/*     */   {
/*  71 */     super(server, iRequestTool);
/*  72 */     this.mspServer = server;
/*     */     
/*  74 */     this.baseGisSearchParam = baseGisSearchParam;
/*  75 */     this.inRoundGisSearchParam = inRoundGisSearchParam;
/*  76 */     this.searchMode = 2;
/*     */   }
/*     */   
/*     */   public String getRequestAddr()
/*     */   {
/*  81 */     CNetSDKLog.i("LocalGisPointRequest", "getRequestAddr,start.");
/*  82 */     if ((this.mspServer.getIp() == null) || (this.mspServer.gethttpsAddr() == null)) {
/*  83 */       CNetSDKLog.e("LocalGisPointRequest", "getRequestAddr,param error.");
/*  84 */       return null;
/*     */     }
/*     */     
/*  87 */     String requestAddr = String.format("%s/mobile/query", new Object[] { this.mspServer.gethttpsAddr() });
/*  88 */     CNetSDKLog.i("LocalGisPointRequest", "getRequestAddr,requestAddr:" + requestAddr);
/*  89 */     return requestAddr;
/*     */   }
/*     */   
/*     */ 
/*     */   public String getRequestData()
/*     */   {
/*  95 */     CNetSDKLog.i("LocalGisPointRequest", "getRequestData,start.");
/*     */     
/*  97 */     if ((this.searchMode != 0) && (this.searchMode != 1) && (this.searchMode != 2))
/*     */     {
/*  99 */       CNetSDKLog.e("LocalGisPointRequest", "getRequestData,searchMode error.");
/* 100 */       return null;
/*     */     }
/*     */     
/* 103 */     String session = this.baseGisSearchParam.getSession();
/* 104 */     String pointTypeList = this.baseGisSearchParam.getPointType();
/* 105 */     String keyString = this.baseGisSearchParam.getKeyString();
/* 106 */     int curPage = this.baseGisSearchParam.getCurPage();
/* 107 */     int numPerPage = this.baseGisSearchParam.getNumPerPage();
/*     */     
/* 109 */     if ((session == null) || (keyString == null) || (pointTypeList == null) || (session.length() <= 0) || 
/* 110 */       (pointTypeList.length() <= 0) || (curPage <= 0) || (numPerPage <= 0))
/*     */     {
/*     */ 
/* 113 */       CNetSDKLog.e("LocalGisPointRequest", "getRequestData,param error.");
/* 114 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 118 */     StringBuilder requestDateBuilder = new StringBuilder();
/* 119 */     requestDateBuilder.append("sessionID=");
/* 120 */     requestDateBuilder.append(session);
/* 121 */     requestDateBuilder.append("&pointType=");
/* 122 */     requestDateBuilder.append(pointTypeList);
/* 123 */     if (this.searchMode == 1)
/*     */     {
/* 125 */       String rectangleParamStr = null;
/* 126 */       rectangleParamStr = getRectangleParamRequestData(this.inRectangleGisSearchParam);
/* 127 */       if (rectangleParamStr != null) {
/* 128 */         requestDateBuilder.append("&");
/* 129 */         requestDateBuilder.append(rectangleParamStr);
/*     */       } else {
/* 131 */         CNetSDKLog.e("LocalGisPointRequest", "getRequestData,SEARCH_MODE_IN_RECTANGLE,getRectangleParamRequestData error.");
/* 132 */         return null;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 137 */     if (this.searchMode == 2) {
/* 138 */       String roundParamStr = getRoundParamRequestData(this.inRoundGisSearchParam);
/* 139 */       if (roundParamStr != null) {
/* 140 */         requestDateBuilder.append("&");
/* 141 */         requestDateBuilder.append(roundParamStr);
/*     */       } else {
/* 143 */         CNetSDKLog.e("LocalGisPointRequest", "getRequestData,SEARCH_MODE_IN_ROUND,getRoundParamRequestData error.");
/* 144 */         return null;
/*     */       }
/*     */     }
/* 147 */     requestDateBuilder.append("&keyword=");
/* 148 */     requestDateBuilder.append(keyString);
/*     */     
/* 150 */     requestDateBuilder.append("&startPage=");
/* 151 */     requestDateBuilder.append(this.baseGisSearchParam.getCurPage());
/* 152 */     requestDateBuilder.append("&pageSize=");
/* 153 */     requestDateBuilder.append(this.baseGisSearchParam.getNumPerPage());
/*     */     
/* 155 */     String requestData = requestDateBuilder.toString();
/* 156 */     CNetSDKLog.i("LocalGisPointRequest", "getRequestString,requestData:" + requestData);
/* 157 */     return requestData;
/*     */   }
/*     */   
/*     */   private String getRectangleParamRequestData(InRectangleGisSearchParam inRectangleGisSearchParam)
/*     */   {
/* 162 */     String xMax = inRectangleGisSearchParam.getxMax();
/* 163 */     String xMin = inRectangleGisSearchParam.getxMin();
/* 164 */     String yMax = inRectangleGisSearchParam.getyMax();
/* 165 */     String yMin = inRectangleGisSearchParam.getyMin();
/* 166 */     if ((xMax == null) || (xMin == null) || (yMax == null) || (yMin == null) || (xMax.length() <= 0) || (xMin.length() <= 0) || 
/* 167 */       (yMax.length() <= 0) || (yMin.length() <= 0)) {
/* 168 */       CNetSDKLog.e("LocalGisPointRequest", "getRectangleParamRequestData,param error.");
/* 169 */       return null;
/*     */     }
/*     */     
/* 172 */     StringBuilder requestDataBuilder = new StringBuilder();
/* 173 */     requestDataBuilder.append("xMin=");
/* 174 */     requestDataBuilder.append(xMin);
/* 175 */     requestDataBuilder.append("&yMin=");
/* 176 */     requestDataBuilder.append(yMin);
/* 177 */     requestDataBuilder.append("&xMax=");
/* 178 */     requestDataBuilder.append(xMax);
/* 179 */     requestDataBuilder.append("&yMax=");
/* 180 */     requestDataBuilder.append(yMax);
/*     */     
/* 182 */     String retData = requestDataBuilder.toString();
/* 183 */     CNetSDKLog.i("LocalGisPointRequest", "getRectangleParamRequestData,retData:" + retData);
/* 184 */     return retData;
/*     */   }
/*     */   
/*     */   private String getRoundParamRequestData(InRoundGisSearchParam inRoundGisSearchParam)
/*     */   {
/* 189 */     String latitude = inRoundGisSearchParam.getLatitude();
/* 190 */     String longitude = inRoundGisSearchParam.getLongitude();
/* 191 */     int radius = inRoundGisSearchParam.getRadius();
/* 192 */     if ((latitude == null) || (longitude == null) || (latitude.length() <= 0) || (longitude.length() <= 0) || (radius <= 0)) {
/* 193 */       CNetSDKLog.e("LocalGisPointRequest", "getRoundParamRequestData,param error.");
/* 194 */       return null;
/*     */     }
/* 196 */     StringBuilder requestDataBuilder = new StringBuilder();
/* 197 */     requestDataBuilder.append("longitude=");
/* 198 */     requestDataBuilder.append(longitude);
/*     */     
/* 200 */     requestDataBuilder.append("&latitude=");
/* 201 */     requestDataBuilder.append(latitude);
/*     */     
/* 203 */     requestDataBuilder.append("&radius=");
/* 204 */     requestDataBuilder.append(radius);
/*     */     
/* 206 */     String retData = requestDataBuilder.toString();
/* 207 */     CNetSDKLog.i("LocalGisPointRequest", "getRoundParamRequestData,retData:" + retData);
/* 208 */     return retData;
/*     */   }
/*     */   
/*     */   public String getBackString()
/*     */   {
/* 213 */     return super.getBackString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\gisPoint\local\LocalGisPointRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */