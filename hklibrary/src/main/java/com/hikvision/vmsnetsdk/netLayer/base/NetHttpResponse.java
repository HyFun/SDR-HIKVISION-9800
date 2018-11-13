/*     */ package com.hikvision.vmsnetsdk.netLayer.base;
/*     */ 
/*     */

import com.hikvision.vmsnetsdk.CNetSDKLog;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

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
/*     */ public abstract class NetHttpResponse
/*     */ {
/*     */   protected String responseData;
/*     */   private static final String TAG = "NetHttpResponse";
/*     */   
/*     */   public NetHttpResponse(String responseData)
/*     */   {
/*  23 */     this.responseData = responseData;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean parseResponseData(String responseData)
/*     */   {
/*  34 */     CNetSDKLog.i("NetHttpResponse", "parseResponseData,responseData：" + responseData);
/*  35 */     if ((responseData == null) || (responseData.length() <= 0)) {
/*  36 */       CNetSDKLog.e("NetHttpResponse", "parseResponseData,param error.");
/*  37 */       return false;
/*     */     }
/*     */     try {
/*  40 */       XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
/*  41 */       XmlPullParser xmlPullParser = factory.newPullParser();
/*  42 */       if (null == xmlPullParser) {
/*  43 */         return false;
/*     */       }
/*  45 */       xmlPullParser.setInput(new StringReader(responseData));
/*  46 */       if (!doParse(xmlPullParser)) {
/*  47 */         return false;
/*     */       }
/*     */     } catch (XmlPullParserException e) {
/*  50 */       e.printStackTrace();
/*  51 */       CNetSDKLog.e("NetHttpResponse", "parseResponseData,XmlPullParserException");
/*  52 */       return false;
/*     */     } catch (Exception e) {
/*  54 */       e.printStackTrace();
/*  55 */       CNetSDKLog.e("NetHttpResponse", "parseResponseData,Exception");
/*  56 */       return false;
/*     */     }
/*  58 */     return true;
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
/*     */ 
/*     */   protected abstract boolean doParse(XmlPullParser paramXmlPullParser)
/*     */     throws XmlPullParserException, Exception;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract void handleXMLStartTag(XmlPullParser paramXmlPullParser, String paramString)
/*     */     throws NumberFormatException, XmlPullParserException, IOException, Exception;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean doParseCycle(XmlPullParser xmlPullParser)
/*     */     throws XmlPullParserException, Exception
/*     */   {
/*  95 */     int eventType = xmlPullParser.getEventType();
/*     */     try
/*     */     {
/*  98 */       while (eventType != 1) {
/*  99 */         String nodeName = xmlPullParser.getName();
/* 100 */         switch (eventType) {
/*     */         case 2: 
/* 102 */           CNetSDKLog.d("NetHttpResponse", "parseResponseData,START_TAG：" + nodeName);
/* 103 */           handleXMLStartTag(xmlPullParser, nodeName);
/* 104 */           break;
/*     */         case 3: 
/* 106 */           CNetSDKLog.d("NetHttpResponse", "parseResponseData,END_TAG：" + nodeName);
/* 107 */           break;
/*     */         }
/*     */         
/*     */         
/* 111 */         eventType = xmlPullParser.next();
/*     */       }
/*     */     } catch (IOException e) {
/* 114 */       e.printStackTrace();
/* 115 */       CNetSDKLog.e("NetHttpResponse", "parseResponseData,IOException");
/* 116 */       return false;
/*     */     } catch (NumberFormatException e) {
/* 118 */       e.printStackTrace();
/* 119 */       CNetSDKLog.e("NetHttpResponse", "parseResponseData,NumberFormatException");
/* 120 */       return false;
/*     */     }
/* 122 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean parseResponseData()
/*     */   {
/* 131 */     return parseResponseData(this.responseData);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getResponseData()
/*     */   {
/* 141 */     return this.responseData;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\base\NetHttpResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */