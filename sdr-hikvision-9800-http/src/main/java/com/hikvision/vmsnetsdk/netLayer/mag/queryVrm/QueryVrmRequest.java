/*     */ package com.hikvision.vmsnetsdk.netLayer.mag.queryVrm;
/*     */ 
/*     */

import android.text.TextUtils;
import android.util.Xml;

import com.hikvision.vmsnetsdk.ABS_TIME;
import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.mag.MagRequest;
import com.hikvision.vmsnetsdk.util.ParseHelper;
import com.hikvision.vmsnetsdk.util.intf.IRequestTool;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
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
/*     */ 
/*     */ 
/*     */ public class QueryVrmRequest
/*     */   extends MagRequest
/*     */ {
/*     */   private static final String TAG = "QueryVrmRequest";
/*     */   private static final String HTTPURL_KEY_QUERY_VRM = "queryVrmRecording";
/*  24 */   private final int PLAYBACK_DEVICE = 1;
/*  25 */   private final int PLAYBACK_PCNVR = 2;
/*  26 */   private final int PLAYBACK_ENVR = 3;
/*  27 */   private final int PLAYBACK_CLOUD = 6;
/*  28 */   private final int PLAYBACK_DSNVR = 5;
/*  29 */   private final int PLAYBACK_CVR = 7;
/*     */   
/*     */   private static final int NET_ZONE_DEFAULT = 0;
/*     */   private String magRequestHead;
/*     */   private String cameraindexcode;
/*     */   private ABS_TIME beginTime;
/*     */   private ABS_TIME endTime;
/*     */   private String recTypesStr;
/*     */   private List<Integer> recTypes;
/*     */   private boolean isPlatformOldWidthMag;
/*  39 */   private String recordPos = "0";
/*     */   private long utc_beginTime;
/*     */   private long utc_endTime;
/*     */   private String guid;
/*     */   
/*     */   public QueryVrmRequest(String magRequestHead, IRequestTool iRequestTool, String cameraindexcode, ABS_TIME beginTime, ABS_TIME endTime, String reTypesStr, String recordPos, boolean isHttpsProtocal)
/*     */   {
/*  46 */     super(iRequestTool, isHttpsProtocal);
/*  47 */     this.magRequestHead = magRequestHead;
/*  48 */     this.cameraindexcode = cameraindexcode;
/*  49 */     this.beginTime = beginTime;
/*  50 */     this.endTime = endTime;
/*  51 */     this.recTypesStr = reTypesStr;
/*  52 */     this.recordPos = recordPos;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public QueryVrmRequest(String magRequestHead, IRequestTool iRequestTool, String cameraindexcode, ABS_TIME beginTime, ABS_TIME endTime, String reTypesStr, String recordPos, boolean isPlatformOldWidthMag, boolean isHttpsProtocal)
/*     */   {
/*  59 */     super(iRequestTool, isHttpsProtocal);
/*  60 */     this.magRequestHead = magRequestHead;
/*  61 */     this.cameraindexcode = cameraindexcode;
/*  62 */     this.beginTime = beginTime;
/*  63 */     this.endTime = endTime;
/*  64 */     this.recTypesStr = reTypesStr;
/*  65 */     this.recordPos = changeRecordPosToV4(recordPos);
/*  66 */     this.isPlatformOldWidthMag = isPlatformOldWidthMag;
/*  67 */     if ("3".equals(this.recordPos)) {
/*  68 */       this.utc_beginTime = getUtcTime(beginTime);
/*  69 */       this.utc_endTime = getUtcTime(endTime);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public QueryVrmRequest(String magRequestHead, IRequestTool iRequestTool, String cameraindexcode, ABS_TIME beginTime, ABS_TIME endTime, String reTypesStr, String recordPos, boolean isPlatformOldWidthMag, boolean isHttpsProtocal, String guid)
/*     */   {
/*  77 */     super(iRequestTool, isHttpsProtocal);
/*  78 */     this.magRequestHead = magRequestHead;
/*  79 */     this.cameraindexcode = cameraindexcode;
/*  80 */     this.beginTime = beginTime;
/*  81 */     this.endTime = endTime;
/*  82 */     this.recTypesStr = reTypesStr;
/*  83 */     this.recordPos = changeRecordPosToV4(recordPos);
/*  84 */     this.isPlatformOldWidthMag = isPlatformOldWidthMag;
/*  85 */     this.guid = guid;
/*  86 */     if ("3".equals(this.recordPos)) {
/*  87 */       this.utc_beginTime = getUtcTime(beginTime);
/*  88 */       this.utc_endTime = getUtcTime(endTime);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private String changeRecordPosToV4(String recordPos)
/*     */   {
/*  99 */     int pos = Integer.parseInt(recordPos);
/* 100 */     String recordpos = "";
/* 101 */     switch (pos)
/*     */     {
/*     */     case 1: 
/* 104 */       recordpos = "0";
/* 105 */       break;
/*     */     case 2: 
/* 107 */       recordpos = "1";
/* 108 */       break;
/*     */     case 3: 
/* 110 */       recordpos = "2";
/* 111 */       break;
/*     */     case 6: 
/* 113 */       recordpos = "3";
/* 114 */       break;
/*     */     case 5: 
/* 116 */       recordpos = "2";
/* 117 */       break;
/*     */     case 7: 
/* 119 */       recordpos = "2";
/* 120 */       break;
/*     */     case 4: 
/*     */     default: 
/* 123 */       recordpos = "0";
/*     */     }
/*     */     
/* 126 */     return recordpos;
/*     */   }
/*     */   
/*     */   private long getUtcTime(ABS_TIME time)
/*     */   {
/* 131 */     Calendar mCalendar = Calendar.getInstance();
/* 132 */     mCalendar.set(1, time.dwYear);
/* 133 */     mCalendar.set(2, time.dwMonth);
/* 134 */     mCalendar.set(5, time.dwDay);
/* 135 */     mCalendar.set(11, time.dwHour);
/* 136 */     mCalendar.set(12, time.dwMinute);
/* 137 */     mCalendar.set(13, time.dwSecond);
/* 138 */     return mCalendar.getTimeInMillis() / 1000L;
/*     */   }
/*     */   
/*     */   public String getRequestAddr()
/*     */   {
/* 143 */     if ((this.magRequestHead == null) || (this.magRequestHead.length() <= 0)) {
/* 144 */       CNetSDKLog.e("QueryVrmRequest", "getRequestAddr,magRequestHead:" + this.magRequestHead);
/* 145 */       return null;
/*     */     }
/* 147 */     return this.magRequestHead + "queryVrmRecording";
/*     */   }
/*     */   
/*     */   protected void insertXMLRequestExplain(XmlSerializer serializer)
/*     */     throws IllegalArgumentException, IllegalStateException, IOException
/*     */   {
/* 153 */     super.insertXMLRequestExplain(serializer);
/* 154 */     serializer.startTag(null, "Command");
/*     */     
/* 156 */     if (!this.isPlatformOldWidthMag) {
/* 157 */       serializer.text("0");
/*     */     } else {
/* 159 */       serializer.text("13");
/*     */     }
/* 161 */     serializer.endTag(null, "Command");
/*     */   }
/*     */   
/*     */   public String getRequestData()
/*     */   {
/* 166 */     if ((this.cameraindexcode == null) || (this.beginTime == null) || (this.endTime == null) || (this.recTypesStr == null) || 
/* 167 */       (!checkRecTypes(this.recTypesStr)) || (TextUtils.isEmpty(this.recordPos))) {
/* 168 */       CNetSDKLog.e("QueryVrmRequest", "getRequestData,param error.beginTime:" + this.beginTime + "endTime:" + this.endTime);
/* 169 */       return null;
/*     */     }
/* 171 */     StringWriter stringWriter = new StringWriter();
/* 172 */     XmlSerializer serializer = Xml.newSerializer();
/*     */     try {
/* 174 */       serializer.setOutput(stringWriter);
/* 175 */       serializer.startTag(null, "MagMessage");
/* 176 */       insertXMLRequestExplain(serializer);
/* 177 */       serializer.startTag(null, "Params");
/* 178 */       serializer.startTag(null, "QueryCondition");
/*     */       
/* 180 */       serializer.startTag(null, "cameraindexcode");
/* 181 */       serializer.text(this.cameraindexcode);
/* 182 */       serializer.endTag(null, "cameraindexcode");
/*     */       
/* 184 */       serializer.startTag(null, "netzone");
/* 185 */       serializer.text(Integer.toString(0));
/* 186 */       serializer.endTag(null, "netzone");
/*     */       
/*     */ 
/* 189 */       if (!this.isPlatformOldWidthMag) {
/* 190 */         serializer.startTag(null, "beginTime");
/* 191 */         serializer.text(this.beginTime.toString(true));
/* 192 */         serializer.endTag(null, "beginTime");
/*     */         
/* 194 */         serializer.startTag(null, "endTime");
/* 195 */         serializer.text(this.endTime.toString(true));
/* 196 */         serializer.endTag(null, "endTime");
/*     */         
/* 198 */         if ("57".equals(this.recordPos))
/*     */         {
/* 200 */           serializer.startTag(null, "storeDeviceType");
/* 201 */           serializer.text(this.recordPos);
/* 202 */           serializer.endTag(null, "storeDeviceType");
/*     */         }
/*     */         
/* 205 */         serializer.startTag(null, "recTypes");
/* 206 */         for (Integer recType : this.recTypes) {
/* 207 */           serializer.startTag(null, "recType");
/* 208 */           serializer.text(Integer.toString(recType.intValue()));
/* 209 */           serializer.endTag(null, "recType");
/*     */         }
/* 211 */         serializer.endTag(null, "recTypes");
/*     */       }
/*     */       else
/*     */       {
/* 215 */         serializer.startTag(null, "StorageType");
/* 216 */         serializer.text(this.recordPos);
/* 217 */         serializer.endTag(null, "StorageType");
/* 218 */         serializer.startTag(null, "Guid");
/* 219 */         if (TextUtils.isEmpty(this.guid)) {
/* 220 */           serializer.text(this.cameraindexcode);
/*     */         } else {
/* 222 */           serializer.text(this.guid);
/*     */         }
/* 224 */         serializer.endTag(null, "Guid");
/*     */         
/* 226 */         serializer.startTag(null, "BeginTime");
/* 227 */         if ("3".equals(this.recordPos)) {
/* 228 */           serializer.text(String.valueOf(this.utc_beginTime));
/*     */         } else {
/* 230 */           serializer.text(this.beginTime.toString(true));
/*     */         }
/* 232 */         serializer.endTag(null, "BeginTime");
/*     */         
/* 234 */         serializer.startTag(null, "EndTime");
/* 235 */         if ("3".equals(this.recordPos)) {
/* 236 */           serializer.text(String.valueOf(this.utc_endTime));
/*     */         } else {
/* 238 */           serializer.text(this.endTime.toString(true));
/*     */         }
/* 240 */         serializer.endTag(null, "EndTime");
/*     */         
/* 242 */         serializer.startTag(null, "RecTypes");
/* 243 */         for (Integer recType : this.recTypes) {
/* 244 */           serializer.startTag(null, "RecType");
/* 245 */           serializer.text(Integer.toString(recType.intValue()));
/* 246 */           serializer.endTag(null, "RecType");
/*     */         }
/* 248 */         serializer.endTag(null, "RecTypes");
/*     */       }
/*     */       
/* 251 */       serializer.endTag(null, "QueryCondition");
/* 252 */       serializer.endTag(null, "Params");
/* 253 */       serializer.endTag(null, "MagMessage");
/* 254 */       serializer.endDocument();
/* 255 */       serializer.flush();
/*     */     } catch (IllegalArgumentException e) {
/* 257 */       e.printStackTrace();
/*     */     } catch (IllegalStateException e) {
/* 259 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/* 261 */       e.printStackTrace();
/*     */     }
/* 263 */     String temp = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + stringWriter.toString();
/* 264 */     CNetSDKLog.i("QueryVrmRequest", "getRequestData,requestData:" + temp);
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
/* 275 */     return temp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean checkRecTypes(String recTypesStr)
/*     */   {
/* 286 */     if ((recTypesStr == null) || (recTypesStr.length() <= 0)) {
/* 287 */       CNetSDKLog.e("QueryVrmRequest", "checkRecTypes,param error.recTypesStr:" + recTypesStr);
/* 288 */       return false;
/*     */     }
/* 290 */     CNetSDKLog.i("QueryVrmRequest", "checkRecTypes,start.recTypesStr:" + recTypesStr);
/*     */     try
/*     */     {
/* 293 */       List<Integer> temp = ParseHelper.parseStrToIntByComma(recTypesStr);
/* 294 */       if (temp == null) {
/* 295 */         CNetSDKLog.e("QueryVrmRequest", "checkRecTypes,parseStrToIntByComma failed.");
/* 296 */         return false;
/*     */       }
/* 298 */       this.recTypes = temp;
/*     */     } catch (NumberFormatException e) {
/* 300 */       e.printStackTrace();
/* 301 */       CNetSDKLog.e("QueryVrmRequest", "checkRecTypes,parseStrToIntByComma NumberFormatException.");
/* 302 */       return false;
/*     */     }
/* 304 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\mag\queryVrm\QueryVrmRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */