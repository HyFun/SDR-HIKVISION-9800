/*     */ package com.hikvision.vmsnetsdk.netLayer.mag.queryNcg;
/*     */ 
/*     */

import android.util.Xml;

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.mag.MagRequest;
import com.hikvision.vmsnetsdk.util.intf.IRequestTool;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;

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
/*     */ public class QueryNcgRequest
/*     */   extends MagRequest
/*     */ {
/*     */   private static final String TAG = "QueryNcgRequest";
/*     */   private static final String HTTPURL_KEY_QUERY_NCG = "queryNcgRecording";
/*     */   private static final int PLAYBACK_VRM4_IPSCAN = 0;
/*     */   private static final int PLAYBACK_VRM4_CVR = 7;
/*     */   private static final int PLAYBACK_NCG_CVR = 3;
/*     */   private String magRequestHead;
/*     */   private String cameraindexcode;
/*     */   private String beginTime;
/*     */   private String endTime;
/*  43 */   private int recType = 23;
/*     */   
/*     */   private int recPos;
/*     */   
/*  47 */   private int fromIndex = 1;
/*     */   
/*  49 */   private int toIndex = 10;
/*     */   
/*     */ 
/*     */   public QueryNcgRequest(String magRequestHead, IRequestTool iRequestTool, String cameraindexcode, String beginTime, String endTime, int recType, int recPos, boolean isHttpsProtocal)
/*     */   {
/*  54 */     super(iRequestTool, isHttpsProtocal);
/*     */     
/*  56 */     this.magRequestHead = magRequestHead;
/*  57 */     this.cameraindexcode = cameraindexcode;
/*  58 */     this.beginTime = beginTime;
/*  59 */     this.endTime = endTime;
/*  60 */     this.recType = recType;
/*  61 */     this.recPos = recPos;
/*     */   }
/*     */   
/*     */ 
/*     */   public QueryNcgRequest(String magRequestHead, IRequestTool iRequestTool, String cameraindexcode, String beginTime, String endTime, int recType, int recPos, boolean isHttpsProtocal, boolean isPlatformOldWithMag)
/*     */   {
/*  67 */     super(iRequestTool, isHttpsProtocal);
/*     */     
/*  69 */     this.magRequestHead = magRequestHead;
/*  70 */     this.cameraindexcode = cameraindexcode;
/*  71 */     this.beginTime = beginTime;
/*  72 */     this.endTime = endTime;
/*  73 */     this.recType = recType;
/*  74 */     if (isPlatformOldWithMag) {
/*  75 */       if (recPos == 7) {
/*  76 */         this.recPos = 3;
/*  77 */       } else if (recPos == 0) {
/*  78 */         this.recPos = 7;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String getRequestAddr()
/*     */   {
/*  86 */     if ((this.magRequestHead == null) || (this.magRequestHead.length() <= 0)) {
/*  87 */       CNetSDKLog.e("QueryNcgRequest", "getRequestAddr,magRequestHead:" + this.magRequestHead);
/*  88 */       return null;
/*     */     }
/*  90 */     return this.magRequestHead + "queryNcgRecording";
/*     */   }
/*     */   
/*     */   protected void insertXMLRequestExplain(XmlSerializer serializer) throws IllegalArgumentException, IllegalStateException, IOException
/*     */   {
/*  95 */     super.insertXMLRequestExplain(serializer);
/*  96 */     serializer.startTag(null, "Command");
/*  97 */     serializer.text("10");
/*  98 */     serializer.endTag(null, "Command");
/*     */   }
/*     */   
/*     */   public String getRequestData()
/*     */   {
/* 103 */     if ((this.cameraindexcode == null) || (this.beginTime == null) || (this.endTime == null)) {
/* 104 */       CNetSDKLog.e("QueryNcgRequest", "getRequestData,param error.beginTime:" + this.beginTime + "endTime:" + this.endTime);
/* 105 */       return null;
/*     */     }
/* 107 */     StringWriter stringWriter = new StringWriter();
/* 108 */     XmlSerializer serializer = Xml.newSerializer();
/*     */     try {
/* 110 */       serializer.setOutput(stringWriter);
/* 111 */       serializer.startTag(null, "MagMessage");
/* 112 */       insertXMLRequestExplain(serializer);
/* 113 */       serializer.startTag(null, "Params");
/* 114 */       serializer.startTag(null, "QueryCondition");
/* 115 */       serializer.startTag(null, "CameraIndexcode");
/* 116 */       serializer.text(this.cameraindexcode);
/* 117 */       serializer.endTag(null, "CameraIndexcode");
/* 118 */       serializer.startTag(null, "BeginTime");
/* 119 */       serializer.text(this.beginTime);
/* 120 */       serializer.endTag(null, "BeginTime");
/* 121 */       serializer.startTag(null, "EndTime");
/* 122 */       serializer.text(this.endTime);
/* 123 */       serializer.endTag(null, "EndTime");
/* 124 */       serializer.startTag(null, "RecordPos");
/* 125 */       serializer.text(this.recPos + "");
/* 126 */       serializer.endTag(null, "RecordPos");
/* 127 */       serializer.startTag(null, "RecordType");
/* 128 */       serializer.text(this.recType + "");
/* 129 */       serializer.endTag(null, "RecordType");
/* 130 */       serializer.startTag(null, "FromIndex");
/* 131 */       serializer.text(this.fromIndex + "");
/* 132 */       serializer.endTag(null, "FromIndex");
/* 133 */       serializer.startTag(null, "ToIndex");
/* 134 */       serializer.text(this.toIndex + "");
/* 135 */       serializer.endTag(null, "ToIndex");
/* 136 */       serializer.endTag(null, "QueryCondition");
/* 137 */       serializer.endTag(null, "Params");
/* 138 */       serializer.endTag(null, "MagMessage");
/* 139 */       serializer.endDocument();
/* 140 */       serializer.flush();
/*     */     } catch (IllegalArgumentException e) {
/* 142 */       e.printStackTrace();
/*     */     } catch (IllegalStateException e) {
/* 144 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/* 146 */       e.printStackTrace();
/*     */     }
/* 148 */     String temp = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + stringWriter.toString();
/* 149 */     CNetSDKLog.i("QueryNcgRequest", "getRequestData,requestData:" + temp);
/* 150 */     return temp;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\mag\queryNcg\QueryNcgRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */