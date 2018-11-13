/*     */ package com.hikvision.vmsnetsdk.netLayer.mag.queryVrm;
/*     */ 
/*     */

import android.text.TextUtils;

import com.hikvision.vmsnetsdk.ABS_TIME;
import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.RecordInfo;
import com.hikvision.vmsnetsdk.RecordSegment;
import com.hikvision.vmsnetsdk.netLayer.mag.MagBackState;
import com.hikvision.vmsnetsdk.netLayer.mag.MagResponse;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

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
/*     */ public class QueryVrmResponse
/*     */   extends MagResponse
/*     */ {
/*     */   private static final String TAG = "QueryVrmResponse";
/*     */   private boolean isComplete;
/*     */   private RecordInfo recordInfo;
/*     */   public static final int COMPLETE_ST_TRUE = 1;
/*     */   public static final int COMPLETE_ST_FALSE = 0;
/*     */   
/*     */   public boolean isComplete()
/*     */   {
/*  26 */     return this.isComplete;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public QueryVrmResponse(String responseData)
/*     */   {
/*  33 */     super(responseData);
/*     */   }
/*     */   
/*     */   public boolean parseResponseData()
/*     */   {
/*  38 */     if (!parseResponseData(this.responseData)) {
/*  39 */       CNetSDKLog.e("QueryVrmResponse", "parseResponseData,parseResponseData failed");
/*  40 */       return false;
/*     */     }
/*     */     
/*  43 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*     */   {
/*  48 */     this.magBackState = new MagBackState();
/*  49 */     return doParseCycle(xmlPullParser);
/*     */   }
/*     */   
/*     */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*     */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*     */   {
/*  55 */     if ((xmlPullParser == null) || (nodeName == null) || (this.magBackState == null)) {
/*  56 */       CNetSDKLog.e("QueryVrmResponse", "handleXMLStartTag,param error");
/*  57 */       throw new Exception("handleXMLStartTag,param error");
/*     */     }
/*  59 */     if (this.recordInfo == null) {
/*  60 */       this.recordInfo = new RecordInfo();
/*     */     }
/*  62 */     if (parseBackStateByXMLStartTag(xmlPullParser, nodeName))
/*  63 */       return;
/*  64 */     if ("BComplete".equals(nodeName)) {
/*  65 */       String isComplete = xmlPullParser.nextText();
/*  66 */       CNetSDKLog.d("QueryVrmResponse", "handleXMLStartTag,isComplete：" + isComplete);
/*  67 */       if ((isComplete != null) && (!TextUtils.isEmpty(isComplete))) {
/*  68 */         int isCompleteInt = Integer.parseInt(isComplete);
/*  69 */         CNetSDKLog.d("QueryVrmResponse", "handleXMLStartTag,isCompleteInt：" + isCompleteInt);
/*  70 */         setIsComplete(isCompleteInt);
/*     */       }
/*  72 */     } else if (!"QueryResult".equals(nodeName)) {
/*  73 */       if ("QueryType".equals(nodeName)) {
/*  74 */         String queryType = xmlPullParser.nextText();
/*  75 */         if ((this.recordInfo != null) && (!TextUtils.isEmpty(queryType))) {
/*  76 */           this.recordInfo.queryType = Integer.parseInt(queryType);
/*     */         }
/*  78 */       } else if ("bRecvAll".equals(nodeName)) {
/*  79 */         String recvAll = xmlPullParser.nextText();
/*  80 */         if (this.recordInfo != null) {
/*  81 */           if ((recvAll != null) && (!recvAll.equals("")) && (recvAll.equals("1"))) {
/*  82 */             this.recordInfo.isRecvAll = true;
/*     */           } else {
/*  84 */             this.recordInfo.isRecvAll = false;
/*     */           }
/*     */         }
/*  87 */       } else if ("SegmentCount".equals(nodeName)) {
/*  88 */         String segmentCount = xmlPullParser.nextText();
/*  89 */         CNetSDKLog.d("QueryVrmResponse", "handleXMLStartTag,segmentCount：" + segmentCount);
/*  90 */         if ((this.recordInfo != null) && (!TextUtils.isEmpty(segmentCount))) {
/*  91 */           this.recordInfo.segmentCount = Integer.parseInt(segmentCount);
/*     */         }
/*  93 */       } else if ("SegmentListPlayUrl".equals(nodeName)) {
/*  94 */         this.recordInfo.segmentListPlayUrl = xmlPullParser.nextText();
/*  95 */       } else if ("DayLightStart".equals(nodeName)) {
/*  96 */         this.recordInfo.dayLightStart = xmlPullParser.nextText();
/*  97 */       } else if ("DayLightEnd".equals(nodeName)) {
/*  98 */         this.recordInfo.dayLightEnd = xmlPullParser.nextText();
/*  99 */       } else if ("DayLightMinute".equals(nodeName)) {
/* 100 */         String lightMinute = xmlPullParser.nextText();
/* 101 */         this.recordInfo.dayLightMinute = Long.parseLong(lightMinute);
/* 102 */       } else if ("SegmentList".equals(nodeName)) {
/* 103 */         CNetSDKLog.d("QueryVrmResponse", "handleXMLStartTag,SegmentList");
/* 104 */       } else if ("RecordSegment".equals(nodeName)) {
/* 105 */         RecordSegment recordSegment = parseRecordSegment(xmlPullParser);
/* 106 */         if ((recordSegment != null) && (this.recordInfo != null)) {
/* 107 */           this.recordInfo.recSegmentList.add(recordSegment);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private RecordSegment parseRecordSegment(XmlPullParser xmlPullParser)
/*     */     throws XmlPullParserException, IOException
/*     */   {
/* 122 */     xmlPullParser.require(2, null, "RecordSegment");
/* 123 */     int eventType = xmlPullParser.getEventType();
/*     */     
/* 125 */     RecordSegment recordSegment = new RecordSegment();
/* 126 */     while (eventType != 1) {
/* 127 */       String nodeName = xmlPullParser.getName();
/* 128 */       switch (eventType) {
/*     */       case 2: 
/* 130 */         CNetSDKLog.d("QueryVrmResponse", "parseRecordSegment,START_TAG：" + nodeName);
/* 131 */         if ("BeginTime".equals(nodeName)) {
/* 132 */           String beginTime = xmlPullParser.nextText();
/* 133 */           CNetSDKLog.d("QueryVrmResponse", "parseRecordSegment,id:" + beginTime);
/* 134 */           recordSegment.setStartTime(new ABS_TIME(beginTime));
/* 135 */         } else if ("EndTime".equals(nodeName)) {
/* 136 */           String endTime = xmlPullParser.nextText();
/* 137 */           CNetSDKLog.d("QueryVrmResponse", "parseRecordSegment,endTime:" + endTime);
/* 138 */           recordSegment.setEndTime(new ABS_TIME(endTime));
/* 139 */         } else if ("RecordType".equals(nodeName)) {
/* 140 */           String recordType = xmlPullParser.nextText();
/* 141 */           CNetSDKLog.d("QueryVrmResponse", "parseRecordSegment,recordType:" + recordType);
/* 142 */           if (!TextUtils.isEmpty(recordType)) {
/* 143 */             recordSegment.setRecordType(Integer.parseInt(recordType));
/*     */           }
/* 145 */         } else if ("MediaDataLen".equals(nodeName)) {
/* 146 */           String mediaDataLen = xmlPullParser.nextText();
/* 147 */           CNetSDKLog.d("QueryVrmResponse", "parseRecordSegment,mediaDataLen:" + mediaDataLen);
/*     */         }
/* 149 */         else if ("IsLocked".equals(nodeName)) {
/* 150 */           String isLocked = xmlPullParser.nextText();
/* 151 */           CNetSDKLog.d("QueryVrmResponse", "parseRecordSegment,isLocked:" + isLocked);
/* 152 */           if ((isLocked != null) && (isLocked.equals("")) && (isLocked.equals("1"))) {
/* 153 */             recordSegment.setLocked(true);
/*     */           } else {
/* 155 */             recordSegment.setLocked(false);
/*     */           }
/* 157 */         } else if ("PlayURL".equals(nodeName)) {
/* 158 */           String playURL = xmlPullParser.nextText();
/* 159 */           CNetSDKLog.d("QueryVrmResponse", "parseRecordSegment,playURL:" + playURL);
/* 160 */           recordSegment.setPlayUrl(playURL); }
/* 161 */         break;
/*     */       
/*     */       case 3: 
/* 164 */         CNetSDKLog.d("QueryVrmResponse", "parseRecordSegment,END_TAG：" + nodeName);
/* 165 */         if ("RecordSegment".equals(nodeName)) {
/* 166 */           return recordSegment;
/*     */         }
/*     */         
/*     */         break;
/*     */       }
/*     */       
/* 172 */       eventType = xmlPullParser.next();
/*     */     }
/* 174 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void setIsComplete(int bComplete)
/*     */   {
/* 184 */     if (bComplete == 1) {
/* 185 */       this.isComplete = true;
/* 186 */       return;
/*     */     }
/* 188 */     this.isComplete = false;
/*     */   }
/*     */   
/*     */   public RecordInfo getRecordInfo() {
/* 192 */     return this.recordInfo;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\mag\queryVrm\QueryVrmResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */