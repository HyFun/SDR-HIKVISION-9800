/*     */ package com.hikvision.vmsnetsdk.netLayer.msp.gisPoint.all;
/*     */ 
/*     */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspBackState;
import com.hikvision.vmsnetsdk.netLayer.msp.MspResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.gisPoint.GISPointInfo;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

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
/*     */ public class AllGisPointResponse
/*     */   extends MspResponse
/*     */ {
/*     */   private static final String TAG = "AllGisPointResponse";
/*     */   private ArrayList<GISPointInfo> gisPointList;
/*     */   private AllGisPointBackState controlUnitBackState;
/*     */   
/*     */   public ArrayList<GISPointInfo> getGisPointList()
/*     */   {
/*  25 */     return this.gisPointList;
/*     */   }
/*     */   
/*     */   public AllGisPointResponse(String responseData)
/*     */   {
/*  30 */     super(responseData);
/*     */   }
/*     */   
/*     */   public String getResponseData()
/*     */   {
/*  35 */     return super.getResponseData();
/*     */   }
/*     */   
/*     */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*     */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*     */   {
/*  41 */     if ((xmlPullParser == null) || (nodeName == null)) {
/*  42 */       CNetSDKLog.e("AllGisPointResponse", "handleXMLStartTag,param error");
/*  43 */       throw new Exception("handleXMLStartTag,param error");
/*     */     }
/*  45 */     if ("Status".equals(nodeName)) {
/*  46 */       String status = xmlPullParser.nextText();
/*  47 */       this.controlUnitBackState.setCode(Integer.parseInt(status.trim()));
/*  48 */       CNetSDKLog.d("AllGisPointResponse", "handleXMLStartTag,status：" + status);
/*  49 */     } else if ("Description".equals(nodeName)) {
/*  50 */       String description = xmlPullParser.nextText();
/*  51 */       this.controlUnitBackState.setDescription(description);
/*  52 */       CNetSDKLog.d("AllGisPointResponse", "handleXMLStartTag,description：" + description);
/*  53 */     } else if ("GisPointList".equals(nodeName)) {
/*  54 */       if (this.gisPointList == null) {
/*  55 */         this.gisPointList = new ArrayList();
/*     */       }
/*  57 */     } else if ("GisPoint".equals(nodeName)) {
/*  58 */       GISPointInfo gisPoint = parseGisPoint(xmlPullParser);
/*  59 */       if (gisPoint != null) {
/*  60 */         this.gisPointList.add(gisPoint);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private GISPointInfo parseGisPoint(XmlPullParser xmlPullParser)
/*     */     throws XmlPullParserException, IOException, NumberFormatException, Exception
/*     */   {
/*  68 */     xmlPullParser.require(2, null, "GisPoint");
/*  69 */     int eventType = xmlPullParser.getEventType();
/*     */     
/*  71 */     GISPointInfo gisPoint = new GISPointInfo();
/*  72 */     while (eventType != 1) {
/*  73 */       String nodeName = xmlPullParser.getName();
/*  74 */       switch (eventType) {
/*     */       case 2: 
/*  76 */         CNetSDKLog.d("AllGisPointResponse", "parseGisPoint,START_TAG：" + nodeName);
/*  77 */         if ("PointType".equals(nodeName)) {
/*  78 */           String pointType = xmlPullParser.nextText();
/*  79 */           CNetSDKLog.d("AllGisPointResponse", "parseGisPoint,pointType:" + pointType);
/*  80 */           gisPoint.setPointType(Integer.parseInt(pointType.trim()));
/*  81 */         } else if ("ControlUnitID".equals(nodeName)) {
/*  82 */           String controlUnitId = xmlPullParser.nextText();
/*  83 */           CNetSDKLog.d("AllGisPointResponse", "parseGisPoint,controlUnitId:" + controlUnitId);
/*  84 */           gisPoint.setControlUnitId(controlUnitId);
/*  85 */         } else if ("ObjectID".equals(nodeName)) {
/*  86 */           String objectId = xmlPullParser.nextText();
/*  87 */           CNetSDKLog.d("AllGisPointResponse", "parseGisPoint,objectId:" + objectId);
/*  88 */           if ((gisPoint.getPointType() == 1) && (objectId == null)) {
/*  89 */             throw new Exception("parseGisPoint error,csuse objectId can not be null when pointType = 1.");
/*     */           }
/*     */           
/*  92 */           gisPoint.setObjectId(objectId);
/*  93 */         } else if ("ObjectName".equals(nodeName)) {
/*  94 */           String objectName = xmlPullParser.nextText();
/*  95 */           CNetSDKLog.d("AllGisPointResponse", "parseGisPoint,objectName:" + objectName);
/*  96 */           if ((gisPoint.getPointType() == 1) && (objectName == null)) {
/*  97 */             throw new Exception("parseGisPoint error,csuse objectName can not be null when pointType = 1.");
/*     */           }
/*     */           
/* 100 */           gisPoint.setObjectName(objectName);
/* 101 */         } else if ("Type".equals(nodeName)) {
/* 102 */           String monitorType = xmlPullParser.nextText();
/* 103 */           CNetSDKLog.d("AllGisPointResponse", "parseGisPoint,monitorType:" + monitorType);
/* 104 */           if ((gisPoint.getPointType() == 1) && (monitorType == null)) {
/* 105 */             throw new Exception("parseGisPoint error,csuse monitorType can not be null when pointType = 1.");
/*     */           }
/*     */           
/* 108 */           gisPoint.setMonitorType(Integer.parseInt(monitorType.trim()));
/* 109 */         } else if ("IsOnline".equals(nodeName)) {
/* 110 */           String isOnline = xmlPullParser.nextText();
/* 111 */           CNetSDKLog.d("AllGisPointResponse", "parseGisPoint,isOnline:" + isOnline);
/* 112 */           if (gisPoint.getPointType() == 1) {
/* 113 */             boolean res = gisPoint.setOnline(Integer.parseInt(isOnline.trim()));
/* 114 */             if (!res) {
/* 115 */               throw new Exception("parseGisPoint error,csuse isOnline can not be other value.");
/*     */             }
/* 117 */           } else if (isOnline != null) {
/* 118 */             boolean res = gisPoint.setOnline(Integer.parseInt(isOnline.trim()));
/* 119 */             if (!res) {
/* 120 */               throw new Exception("parseGisPoint error,csuse isOnline can not be other value.");
/*     */             }
/*     */           }
/*     */         }
/* 124 */         else if ("Longitude".equals(nodeName)) {
/* 125 */           String longitude = xmlPullParser.nextText();
/* 126 */           CNetSDKLog.d("AllGisPointResponse", "parseGisPoint,longitude:" + longitude);
/* 127 */           gisPoint.setLongitude(longitude);
/* 128 */         } else if ("Latitude".equals(nodeName)) {
/* 129 */           String latitude = xmlPullParser.nextText();
/* 130 */           CNetSDKLog.d("AllGisPointResponse", "parseGisPoint,latitude:" + latitude);
/* 131 */           gisPoint.setLatitude(latitude);
/* 132 */         } else if ("Remark".equals(nodeName)) {
/* 133 */           String remark = xmlPullParser.nextText();
/* 134 */           CNetSDKLog.d("AllGisPointResponse", "parseGisPoint,remark:" + remark);
/* 135 */           gisPoint.setRemark(remark); }
/* 136 */         break;
/*     */       
/*     */ 
/*     */       case 3: 
/* 140 */         CNetSDKLog.d("AllGisPointResponse", "parseGisPoint,END_TAG：" + nodeName);
/* 141 */         if ("GisPoint".equals(nodeName)) {
/* 142 */           return gisPoint;
/*     */         }
/*     */         
/*     */         break;
/*     */       }
/*     */       
/* 148 */       eventType = xmlPullParser.next();
/*     */     }
/* 150 */     return null;
/*     */   }
/*     */   
/*     */   public MspBackState getMspBackState()
/*     */   {
/* 155 */     return this.controlUnitBackState;
/*     */   }
/*     */   
/*     */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*     */   {
/* 160 */     this.controlUnitBackState = new AllGisPointBackState();
/* 161 */     return doParseCycle(xmlPullParser);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\gisPoint\all\AllGisPointResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */