/*     */ package com.hikvision.vmsnetsdk.netLayer.msp.msg.list.detail;
/*     */ 
/*     */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspBackState;
import com.hikvision.vmsnetsdk.netLayer.msp.MspResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.msg.detail.MsgDetail;
import com.hikvision.vmsnetsdk.netLayer.msp.msg.list.MsgListBackState;

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
/*     */ 
/*     */ 
/*     */ public class MsgDetailListResponse
/*     */   extends MspResponse
/*     */ {
/*     */   private static final String TAG = "MsgDetailListResponse";
/*     */   private ArrayList<MsgDetail> msgDetails;
/*     */   private MsgListBackState msgDetailListBackState;
/*     */   
/*     */   public MsgDetailListResponse(String responseData)
/*     */   {
/*  31 */     super(responseData);
/*     */   }
/*     */   
/*     */   public String getResponseData()
/*     */   {
/*  36 */     return super.getResponseData();
/*     */   }
/*     */   
/*     */   public ArrayList<MsgDetail> getMsgDetails() {
/*  40 */     return this.msgDetails;
/*     */   }
/*     */   
/*     */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*     */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*     */   {
/*  46 */     if ((xmlPullParser == null) || (nodeName == null)) {
/*  47 */       CNetSDKLog.e("MsgDetailListResponse", "handleXMLStartTag,param error");
/*  48 */       throw new Exception("handleXMLStartTag,param error");
/*     */     }
/*  50 */     if ("Status".equals(nodeName)) {
/*  51 */       String status = xmlPullParser.nextText();
/*  52 */       this.msgDetailListBackState.setCode(Integer.parseInt(status.trim()));
/*  53 */       CNetSDKLog.d("MsgDetailListResponse", "handleXMLStartTag,status：" + status);
/*  54 */     } else if ("Description".equals(nodeName)) {
/*  55 */       String description = xmlPullParser.nextText();
/*  56 */       this.msgDetailListBackState.setDescription(description);
/*  57 */       CNetSDKLog.d("MsgDetailListResponse", "handleXMLStartTag,description：" + description);
/*  58 */     } else if ("MessageDetailList".equals(nodeName)) {
/*  59 */       if (this.msgDetails == null) {
/*  60 */         this.msgDetails = new ArrayList();
/*     */       }
/*  62 */     } else if ("MessageDetail".equals(nodeName)) {
/*  63 */       MsgDetail msgDetail = parseMsgDetail(xmlPullParser);
/*  64 */       if (msgDetail != null) {
/*  65 */         this.msgDetails.add(msgDetail);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private MsgDetail parseMsgDetail(XmlPullParser xmlPullParser)
/*     */     throws XmlPullParserException, IOException, NumberFormatException, Exception
/*     */   {
/*  73 */     xmlPullParser.require(2, null, "MessageDetail");
/*  74 */     int eventType = xmlPullParser.getEventType();
/*     */     
/*  76 */     MsgDetail msgDetail = new MsgDetail();
/*  77 */     while (eventType != 1) {
/*  78 */       String nodeName = xmlPullParser.getName();
/*  79 */       switch (eventType) {
/*     */       case 2: 
/*  81 */         CNetSDKLog.d("MsgDetailListResponse", "parseMsgDetail,START_TAG：" + nodeName);
/*  82 */         if ("ID".equals(nodeName)) {
/*  83 */           String id = xmlPullParser.nextText();
/*  84 */           CNetSDKLog.d("MsgDetailListResponse", "parseMsgDetail,id:" + id);
/*  85 */           msgDetail.setId(id);
/*  86 */         } else if ("Type".equals(nodeName)) {
/*  87 */           String type = xmlPullParser.nextText();
/*  88 */           CNetSDKLog.d("MsgDetailListResponse", "parseMsgDetail,type:" + type);
/*  89 */           msgDetail.setType(type);
/*  90 */         } else if ("Title".equals(nodeName)) {
/*  91 */           String title = xmlPullParser.nextText();
/*  92 */           CNetSDKLog.d("MsgDetailListResponse", "parseMsgDetail,title:" + title);
/*  93 */           msgDetail.setTitle(title);
/*  94 */         } else if ("Content".equals(nodeName)) {
/*  95 */           String content = xmlPullParser.nextText();
/*  96 */           CNetSDKLog.d("MsgDetailListResponse", "parseMsgDetail,content:" + content);
/*  97 */           if ((content == null) || (content.length() <= 0)) {
/*  98 */             msgDetail.setContent(content);
/*  99 */           } else if (msgDetail.getType().equals("PM"))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 106 */             msgDetail.setContent(content);
/*     */           }
/*     */           else {
/* 109 */             msgDetail.setContent(content);
/*     */           }
/* 111 */         } else if ("TypeDescribe".equals(nodeName)) {
/* 112 */           String typeDescribe = xmlPullParser.nextText();
/* 113 */           CNetSDKLog.d("MsgDetailListResponse", "parseMsgDetail,typeDescribe:" + typeDescribe);
/* 114 */           msgDetail.setTypeDescribe(typeDescribe);
/* 115 */         } else if ("IsChecked".equals(nodeName)) {
/* 116 */           String isChecked = xmlPullParser.nextText();
/* 117 */           CNetSDKLog.d("MsgDetailListResponse", "parseMsgDetail,isChecked:" + isChecked);
/* 118 */           boolean res = msgDetail.setChecked(Integer.parseInt(isChecked));
/* 119 */           if (!res) {
/* 120 */             CNetSDKLog.e("MsgDetailListResponse", "parseMsgDetail,isChecked can not be other values");
/* 121 */             return null;
/*     */           }
/* 123 */         } else if ("CameraID".equals(nodeName)) {
/* 124 */           String cameraID = xmlPullParser.nextText();
/* 125 */           CNetSDKLog.d("MsgDetailListResponse", "parseMsgDetail,cameraID:" + cameraID);
/* 126 */           msgDetail.setCameraID(cameraID);
/*     */         }
/* 128 */         else if ("PictureUrl".equals(nodeName)) {
/* 129 */           String pictureUrl = xmlPullParser.nextText();
/* 130 */           CNetSDKLog.d("MsgDetailListResponse", "parseMsgDetail,pictureUrl:" + pictureUrl);
/* 131 */           msgDetail.setPictureUrl(pictureUrl);
/*     */         }
/* 133 */         else if ("Longitude".equals(nodeName)) {
/* 134 */           String longitude = xmlPullParser.nextText();
/* 135 */           CNetSDKLog.d("MsgDetailListResponse", "parseMsgDetail,longitude:" + longitude);
/* 136 */           if ((longitude != null) && (longitude.length() > 0)) {
/* 137 */             msgDetail.setLongitude(Double.parseDouble(longitude));
/*     */           }
/* 139 */         } else if ("Latitude".equals(nodeName)) {
/* 140 */           String latitude = xmlPullParser.nextText();
/* 141 */           CNetSDKLog.d("MsgDetailListResponse", "parseMsgDetail,latitude:" + latitude);
/* 142 */           if ((latitude != null) && (latitude.length() > 0)) {
/* 143 */             msgDetail.setLatitude(Double.parseDouble(latitude));
/*     */           }
/* 145 */         } else if ("ThumbPicUrl".equals(nodeName)) {
/* 146 */           String thumbPicUrl = xmlPullParser.nextText();
/* 147 */           CNetSDKLog.d("MsgDetailListResponse", "parseMsgDetail,thumbPicUrl:" + thumbPicUrl);
/* 148 */           msgDetail.setThumbPicUrl(thumbPicUrl);
/*     */         }
/* 150 */         else if ("MediaUrl".equals(nodeName)) {
/* 151 */           String mediaUrl = xmlPullParser.nextText();
/* 152 */           CNetSDKLog.d("MsgDetailListResponse", "parseMsgDetail,mediaUrl:" + mediaUrl);
/* 153 */           msgDetail.setMediaUrl(mediaUrl);
/* 154 */         } else if ("CreateTime".equals(nodeName)) {
/* 155 */           String createTime = xmlPullParser.nextText();
/* 156 */           CNetSDKLog.d("MsgDetailListResponse", "parseMsgDetail,collectedFlag:" + createTime);
/* 157 */           msgDetail.setCreateTime(Long.parseLong(createTime.trim())); }
/* 158 */         break;
/*     */       
/*     */ 
/*     */       case 3: 
/* 162 */         CNetSDKLog.d("MsgDetailListResponse", "parseMsgDetail,END_TAG：" + nodeName);
/* 163 */         if ("MessageDetail".equals(nodeName)) {
/* 164 */           return msgDetail;
/*     */         }
/*     */         
/*     */         break;
/*     */       }
/*     */       
/* 170 */       eventType = xmlPullParser.next();
/*     */     }
/* 172 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public MspBackState getMspBackState()
/*     */   {
/* 178 */     return this.msgDetailListBackState;
/*     */   }
/*     */   
/*     */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*     */   {
/* 183 */     this.msgDetailListBackState = new MsgListBackState();
/* 184 */     return doParseCycle(xmlPullParser);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\msg\list\detail\MsgDetailListResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */