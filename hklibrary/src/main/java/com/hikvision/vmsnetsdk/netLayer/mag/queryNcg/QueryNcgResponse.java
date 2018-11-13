/*     */ package com.hikvision.vmsnetsdk.netLayer.mag.queryNcg;
/*     */ 
/*     */

import android.text.TextUtils;

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.FileInfoList;
import com.hikvision.vmsnetsdk.RecordInfo;
import com.hikvision.vmsnetsdk.RecordSegment;
import com.hikvision.vmsnetsdk.netLayer.mag.MagBackState;
import com.hikvision.vmsnetsdk.netLayer.mag.MagResponse;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
/*     */ public class QueryNcgResponse
/*     */   extends MagResponse
/*     */ {
/*     */   private static final String TAG = "QueryNcgResponse";
/*     */   private boolean isComplete;
/*     */   private RecordInfo recordInfo;
/*     */   public static final int COMPLETE_ST_TRUE = 1;
/*     */   public static final int COMPLETE_ST_FALSE = 0;
/*     */   
/*     */   public boolean isComplete()
/*     */   {
/*  28 */     return this.isComplete;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public QueryNcgResponse(String responseData)
/*     */   {
/*  35 */     super(responseData);
/*     */   }
/*     */   
/*     */   public boolean parseResponseData()
/*     */   {
/*  40 */     if (!parseResponseData(this.responseData)) {
/*  41 */       CNetSDKLog.e("QueryNcgResponse", "parseResponseData,parseResponseData failed");
/*  42 */       return false;
/*     */     }
/*     */     
/*  45 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*     */   {
/*  50 */     this.magBackState = new MagBackState();
/*  51 */     return doParseCycle(xmlPullParser);
/*     */   }
/*     */   
/*     */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*     */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*     */   {
/*  57 */     if ((xmlPullParser == null) || (nodeName == null) || (this.magBackState == null)) {
/*  58 */       CNetSDKLog.e("QueryNcgResponse", "handleXMLStartTag,param error");
/*  59 */       throw new Exception("handleXMLStartTag,param error");
/*     */     }
/*  61 */     if (this.recordInfo == null) {
/*  62 */       this.recordInfo = new RecordInfo();
/*     */     }
/*  64 */     if (parseBackStateByXMLStartTag(xmlPullParser, nodeName))
/*  65 */       return;
/*  66 */     if ("BComplete".equals(nodeName)) {
/*  67 */       String isComplete = xmlPullParser.nextText();
/*  68 */       CNetSDKLog.d("QueryNcgResponse", "handleXMLStartTag,isComplete：" + isComplete);
/*  69 */       if (!TextUtils.isEmpty(isComplete)) {
/*  70 */         int isCompleteInt = Integer.parseInt(isComplete);
/*  71 */         CNetSDKLog.d("QueryNcgResponse", "handleXMLStartTag,isCompleteInt：" + isCompleteInt);
/*  72 */         setIsComplete(isCompleteInt);
/*     */       }
/*  74 */     } else if ("Message".equals(nodeName)) {
/*  75 */       RecordSegment recordSegment = parseRecordSegment(xmlPullParser);
/*  76 */       if ((recordSegment != null) && (this.recordInfo != null)) {
/*  77 */         List<FileInfoList> fileInfoLists = recordSegment.getFileInfoList();
/*  78 */         if ((fileInfoLists != null) && (fileInfoLists.size() > 0)) {
/*  79 */           recordSegment.setNcg_startTime(((FileInfoList)fileInfoLists.get(0)).getBeginTime());
/*  80 */           recordSegment.setNcg_endTime(((FileInfoList)fileInfoLists.get(fileInfoLists.size() - 1)).getEndTime());
/*     */         }
/*  82 */         this.recordInfo.recSegmentList.add(recordSegment);
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
/*     */ 
/*     */   private RecordSegment parseRecordSegment(XmlPullParser xmlPullParser)
/*     */     throws XmlPullParserException, IOException
/*     */   {
/*  97 */     xmlPullParser.require(2, null, "Message");
/*  98 */     int eventType = xmlPullParser.getEventType();
/*     */     
/* 100 */     RecordSegment recordSegment = new RecordSegment();
/* 101 */     List<FileInfoList> fileInfoLists = new ArrayList();
/* 102 */     while (eventType != 1) {
/* 103 */       String nodeName = xmlPullParser.getName();
/* 104 */       switch (eventType) {
/*     */       case 2: 
/* 106 */         CNetSDKLog.d("QueryNcgResponse", "parseRecordSegment,START_TAG：" + nodeName);
/* 107 */         if ("RealFileNum".equals(nodeName)) {
/* 108 */           String realFileNum = xmlPullParser.nextText();
/* 109 */           CNetSDKLog.d("QueryNcgResponse", "parseRecordSegment,realFileNum:" + realFileNum);
/* 110 */           if (!TextUtils.isEmpty(realFileNum)) {
/* 111 */             this.recordInfo.segmentCount = Integer.parseInt(realFileNum);
/*     */           }
/* 113 */         } else if ((!"FromIndex".equals(nodeName)) && 
/* 114 */           (!"ToIndex".equals(nodeName)) && 
/* 115 */           ("Item".equals(nodeName))) {
/* 116 */           FileInfoList fileInfoList = parseFileInfoList(xmlPullParser);
/* 117 */           if (fileInfoList != null)
/* 118 */             fileInfoLists.add(fileInfoList);
/*     */         }
/* 120 */         break;
/*     */       
/*     */       case 3: 
/* 123 */         CNetSDKLog.d("QueryNcgResponse", "parseRecordSegment,END_TAG：" + nodeName);
/* 124 */         if ("Message".equals(nodeName)) {
/* 125 */           if ((fileInfoLists != null) && (fileInfoLists.get(0) != null)) {
/* 126 */             recordSegment.setRecordType(((FileInfoList)fileInfoLists.get(0)).getRecordType());
/*     */           }
/* 128 */           recordSegment.setFileInfoList(fileInfoLists);
/* 129 */           CNetSDKLog.d("QueryNcgResponse", "parseRecordSegment,recordSegment is null：" + (recordSegment == null));
/* 130 */           return recordSegment;
/*     */         }
/*     */         
/*     */         break;
/*     */       }
/*     */       
/* 136 */       eventType = xmlPullParser.next();
/*     */     }
/*     */     
/* 139 */     return null;
/*     */   }
/*     */   
/*     */   private FileInfoList parseFileInfoList(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException
/*     */   {
/* 144 */     xmlPullParser.require(2, null, "Item");
/* 145 */     int eventType = xmlPullParser.getEventType();
/*     */     
/* 147 */     FileInfoList fileList = new FileInfoList();
/* 148 */     while (eventType != 1) {
/* 149 */       String nodeName = xmlPullParser.getName();
/* 150 */       switch (eventType) {
/*     */       case 2: 
/* 152 */         CNetSDKLog.d("QueryNcgResponse", "parseFileInfoList,START_TAG：" + nodeName);
/* 153 */         if ("Name".equals(nodeName)) {
/* 154 */           String name = xmlPullParser.nextText();
/* 155 */           CNetSDKLog.d("QueryNcgResponse", "parseFileInfoList,name：" + name);
/* 156 */           fileList.setName(name);
/* 157 */         } else if ("BeginTime".equals(nodeName)) {
/* 158 */           String beginTime = xmlPullParser.nextText();
/* 159 */           CNetSDKLog.d("QueryNcgResponse", "parseFileInfoList,BeginTime：" + beginTime);
/* 160 */           fileList.setBeginTime(beginTime);
/* 161 */         } else if ("EndTime".equals(nodeName)) {
/* 162 */           String endTime = xmlPullParser.nextText();
/* 163 */           CNetSDKLog.d("QueryNcgResponse", "parseFileInfoList,EndTime：" + endTime);
/* 164 */           fileList.setEndTime(endTime);
/* 165 */         } else if ("RecordType".equals(nodeName)) {
/* 166 */           String recordType = xmlPullParser.nextText();
/* 167 */           CNetSDKLog.d("QueryNcgResponse", "parseFileInfoList,RecordType：" + recordType);
/* 168 */           if (!TextUtils.isEmpty(recordType))
/* 169 */             fileList.setRecordType(Integer.parseInt(recordType));
/*     */         }
/* 171 */         break;
/*     */       
/*     */       case 3: 
/* 174 */         CNetSDKLog.d("QueryNcgResponse", "parseFileInfoList,END_TAG：" + nodeName);
/* 175 */         if ("Item".equals(nodeName)) {
/* 176 */           return fileList;
/*     */         }
/*     */         
/*     */         break;
/*     */       }
/*     */       
/* 182 */       eventType = xmlPullParser.next();
/*     */     }
/* 184 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void setIsComplete(int bComplete)
/*     */   {
/* 194 */     if (bComplete == 1) {
/* 195 */       this.isComplete = true;
/* 196 */       return;
/*     */     }
/* 198 */     this.isComplete = false;
/*     */   }
/*     */   
/*     */   public RecordInfo getRecordInfo() {
/* 202 */     return this.recordInfo;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\mag\queryNcg\QueryNcgResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */