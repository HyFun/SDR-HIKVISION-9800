/*     */ package com.hikvision.vmsnetsdk.netLayer.msp.msg.list.latest;
/*     */ 
/*     */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspBackState;
import com.hikvision.vmsnetsdk.netLayer.msp.MspResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.msg.Msg;

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
/*     */ public class LatestMsgListResponse
/*     */   extends MspResponse
/*     */ {
/*     */   private static final String TAG = "LatestMsgListResponse";
/*     */   private ArrayList<Msg> msgList;
/*     */   private LatestMsgListBackState msgListBackState;
/*     */   
/*     */   public ArrayList<Msg> getMsgList()
/*     */   {
/*  24 */     return this.msgList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public LatestMsgListResponse(String responseData)
/*     */   {
/*  31 */     super(responseData);
/*     */   }
/*     */   
/*     */   public String getResponseData()
/*     */   {
/*  36 */     return super.getResponseData();
/*     */   }
/*     */   
/*     */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*     */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*     */   {
/*  42 */     if ((xmlPullParser == null) || (nodeName == null)) {
/*  43 */       CNetSDKLog.e("LatestMsgListResponse", "handleXMLStartTag,param error");
/*  44 */       throw new Exception("handleXMLStartTag,param error");
/*     */     }
/*  46 */     if ("Status".equals(nodeName)) {
/*  47 */       String status = xmlPullParser.nextText();
/*  48 */       this.msgListBackState.setCode(Integer.parseInt(status.trim()));
/*  49 */       CNetSDKLog.d("LatestMsgListResponse", "handleXMLStartTag,status：" + status);
/*  50 */     } else if ("Description".equals(nodeName)) {
/*  51 */       String description = xmlPullParser.nextText();
/*  52 */       this.msgListBackState.setDescription(description);
/*  53 */       CNetSDKLog.d("LatestMsgListResponse", "handleXMLStartTag,description：" + description);
/*  54 */     } else if ("MessageList".equals(nodeName)) {
/*  55 */       if (this.msgList == null) {
/*  56 */         this.msgList = new ArrayList();
/*     */       }
/*  58 */     } else if ("Message".equals(nodeName)) {
/*  59 */       Msg msg = parseMsg(xmlPullParser);
/*  60 */       if (msg != null) {
/*  61 */         this.msgList.add(msg);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private Msg parseMsg(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, NumberFormatException, Exception
/*     */   {
/*  68 */     xmlPullParser.require(2, null, "Message");
/*  69 */     int eventType = xmlPullParser.getEventType();
/*     */     
/*  71 */     Msg msg = new Msg();
/*  72 */     while (eventType != 1) {
/*  73 */       String nodeName = xmlPullParser.getName();
/*  74 */       switch (eventType) {
/*     */       case 2: 
/*  76 */         CNetSDKLog.d("LatestMsgListResponse", "parseMsg,START_TAG：" + nodeName);
/*  77 */         if ("ID".equals(nodeName)) {
/*  78 */           String id = xmlPullParser.nextText();
/*  79 */           CNetSDKLog.d("LatestMsgListResponse", "parseMsg,id:" + id);
/*  80 */           msg.setId(id);
/*  81 */         } else if ("Type".equals(nodeName)) {
/*  82 */           String type = xmlPullParser.nextText();
/*  83 */           CNetSDKLog.d("LatestMsgListResponse", "parseMsg,type:" + type);
/*  84 */           msg.setType(type);
/*  85 */         } else if ("Title".equals(nodeName)) {
/*  86 */           String title = xmlPullParser.nextText();
/*  87 */           CNetSDKLog.d("LatestMsgListResponse", "parseMsg,title:" + title);
/*  88 */           msg.setTitle(title);
/*  89 */         } else if ("TypeDescribe".equals(nodeName)) {
/*  90 */           String typeDescribe = xmlPullParser.nextText();
/*  91 */           CNetSDKLog.d("LatestMsgListResponse", "parseMsg,typeDescribe:" + typeDescribe);
/*  92 */           msg.setTypeDescribe(typeDescribe);
/*  93 */         } else if ("IsChecked".equals(nodeName)) {
/*  94 */           String isChecked = xmlPullParser.nextText();
/*  95 */           CNetSDKLog.d("LatestMsgListResponse", "parseMsg,isChecked:" + isChecked);
/*  96 */           boolean res = msg.setChecked(Integer.parseInt(isChecked));
/*  97 */           if (!res) {
/*  98 */             CNetSDKLog.e("LatestMsgListResponse", "parseMsg,isChecked can not be other values");
/*  99 */             return null;
/*     */           }
/* 101 */         } else if ("CreateTime".equals(nodeName)) {
/* 102 */           String createTime = xmlPullParser.nextText();
/* 103 */           CNetSDKLog.d("LatestMsgListResponse", "parseMsg,collectedFlag:" + createTime);
/* 104 */           msg.setCreateTime(Long.parseLong(createTime.trim())); }
/* 105 */         break;
/*     */       
/*     */ 
/*     */       case 3: 
/* 109 */         CNetSDKLog.d("LatestMsgListResponse", "parseMsg,END_TAG：" + nodeName);
/* 110 */         if ("Message".equals(nodeName)) {
/* 111 */           return msg;
/*     */         }
/*     */         
/*     */         break;
/*     */       }
/*     */       
/* 117 */       eventType = xmlPullParser.next();
/*     */     }
/* 119 */     return null;
/*     */   }
/*     */   
/*     */   public MspBackState getMspBackState()
/*     */   {
/* 124 */     return this.msgListBackState;
/*     */   }
/*     */   
/*     */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*     */   {
/* 129 */     this.msgListBackState = new LatestMsgListBackState();
/* 130 */     return doParseCycle(xmlPullParser);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\msg\list\latest\LatestMsgListResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */