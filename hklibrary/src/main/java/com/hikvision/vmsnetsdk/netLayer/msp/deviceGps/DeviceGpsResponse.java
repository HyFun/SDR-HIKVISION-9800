/*     */ package com.hikvision.vmsnetsdk.netLayer.msp.deviceGps;
/*     */ 
/*     */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspBackState;
import com.hikvision.vmsnetsdk.netLayer.msp.MspResponse;

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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DeviceGpsResponse
/*     */   extends MspResponse
/*     */ {
/*     */   private static final String TAG = "DeviceGpsResponse";
/*     */   private ArrayList<GISTrackPoint> gpsTrackList;
/*     */   private DeviceGpsBackState deviceGpsBackState;
/*     */   private int total;
/*     */   private int gpsNum;
/*     */   
/*     */   public DeviceGpsResponse(String responseData)
/*     */   {
/*  31 */     super(responseData);
/*     */   }
/*     */   
/*     */   public String getResponseData()
/*     */   {
/*  36 */     return super.getResponseData();
/*     */   }
/*     */   
/*     */   public ArrayList<GISTrackPoint> getGpsTrackList() {
/*  40 */     return this.gpsTrackList;
/*     */   }
/*     */   
/*     */   public int getGpsNum() {
/*  44 */     return this.gpsNum;
/*     */   }
/*     */   
/*     */   private GISTrackPoint parseGps(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, NumberFormatException, Exception
/*     */   {
/*  49 */     xmlPullParser.require(2, null, "GPS");
/*  50 */     int eventType = xmlPullParser.getEventType();
/*     */     
/*  52 */     GISTrackPoint gps = new GISTrackPoint();
/*  53 */     while (eventType != 1) {
/*  54 */       String nodeName = xmlPullParser.getName();
/*  55 */       switch (eventType) {
/*     */       case 2: 
/*  57 */         CNetSDKLog.d("DeviceGpsResponse", "parseGps,START_TAG：" + nodeName);
/*  58 */         if ("GpsTime".equals(nodeName)) {
/*  59 */           String gpsTime = xmlPullParser.nextText();
/*  60 */           CNetSDKLog.d("DeviceGpsResponse", "parseGps,gpsTime:" + gpsTime);
/*  61 */           gps.setGpsTime(gpsTime);
/*  62 */         } else if ("Speed".equals(nodeName)) {
/*  63 */           String speed = xmlPullParser.nextText();
/*  64 */           CNetSDKLog.d("DeviceGpsResponse", "parseGps,speed:" + speed);
/*  65 */           gps.setSpeed(speed);
/*  66 */         } else if ("Direction".equals(nodeName)) {
/*  67 */           String direction = xmlPullParser.nextText();
/*  68 */           CNetSDKLog.d("DeviceGpsResponse", "parseGps,direction:" + direction);
/*  69 */           gps.setDirection(direction);
/*  70 */         } else if ("Longitude".equals(nodeName)) {
/*  71 */           String longitude = xmlPullParser.nextText();
/*  72 */           CNetSDKLog.d("DeviceGpsResponse", "parseGps,longitude:" + longitude);
/*  73 */           gps.setLongitude(longitude);
/*  74 */         } else if ("Latitude".equals(nodeName)) {
/*  75 */           String latitude = xmlPullParser.nextText();
/*  76 */           CNetSDKLog.d("DeviceGpsResponse", "parseGps,latitude:" + latitude);
/*  77 */           gps.setLatitude(latitude); }
/*  78 */         break;
/*     */       
/*     */ 
/*     */       case 3: 
/*  82 */         CNetSDKLog.d("DeviceGpsResponse", "parseGps,END_TAG：" + nodeName);
/*  83 */         if ("GPS".equals(nodeName)) {
/*  84 */           return gps;
/*     */         }
/*     */         
/*     */         break;
/*     */       }
/*     */       
/*  90 */       eventType = xmlPullParser.next();
/*     */     }
/*  92 */     return null;
/*     */   }
/*     */   
/*     */   public MspBackState getMspBackState()
/*     */   {
/*  97 */     return this.deviceGpsBackState;
/*     */   }
/*     */   
/*     */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*     */   {
/* 102 */     this.deviceGpsBackState = new DeviceGpsBackState();
/* 103 */     return doParseCycle(xmlPullParser);
/*     */   }
/*     */   
/*     */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*     */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*     */   {
/* 109 */     if ((xmlPullParser == null) || (nodeName == null)) {
/* 110 */       CNetSDKLog.e("DeviceGpsResponse", "handleXMLStartTag,param error");
/* 111 */       throw new Exception("handleXMLStartTag,param error");
/*     */     }
/*     */     
/* 114 */     if ("Status".equals(nodeName)) {
/* 115 */       String status = xmlPullParser.nextText();
/* 116 */       this.deviceGpsBackState.setCode(Integer.parseInt(status.trim()));
/* 117 */       CNetSDKLog.d("DeviceGpsResponse", "handleXMLStartTag,status：" + status);
/* 118 */     } else if ("Description".equals(nodeName)) {
/* 119 */       String description = xmlPullParser.nextText();
/* 120 */       this.deviceGpsBackState.setDescription(description);
/* 121 */       CNetSDKLog.d("DeviceGpsResponse", "handleXMLStartTag,description：" + description);
/* 122 */     } else if ("GPSList".equals(nodeName)) {
/* 123 */       if (this.gpsTrackList == null) {
/* 124 */         this.gpsTrackList = new ArrayList();
/*     */       }
/* 126 */     } else if ("Total".equals(nodeName)) {
/* 127 */       String total = xmlPullParser.nextText();
/* 128 */       this.total = Integer.parseInt(total.trim());
/* 129 */       CNetSDKLog.d("DeviceGpsResponse", "handleXMLStartTag,total：" + total);
/* 130 */     } else if ("Num".equals(nodeName)) {
/* 131 */       String num = xmlPullParser.nextText();
/* 132 */       this.gpsNum = Integer.parseInt(num.trim());
/* 133 */       CNetSDKLog.d("DeviceGpsResponse", "handleXMLStartTag,num：" + num);
/* 134 */     } else if ("GPS".equals(nodeName)) {
/* 135 */       GISTrackPoint gps = parseGps(xmlPullParser);
/* 136 */       if (gps != null) {
/* 137 */         this.gpsTrackList.add(gps);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public int getTotal()
/*     */   {
/* 144 */     return this.total;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\deviceGps\DeviceGpsResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */