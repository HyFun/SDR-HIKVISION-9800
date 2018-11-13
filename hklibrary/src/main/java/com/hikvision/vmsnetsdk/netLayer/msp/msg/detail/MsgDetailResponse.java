/*     */ package com.hikvision.vmsnetsdk.netLayer.msp.msg.detail;
/*     */ 
/*     */

import android.text.TextUtils;

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.EventInfo;
import com.hikvision.vmsnetsdk.Trigger;
import com.hikvision.vmsnetsdk.netLayer.msp.MspBackState;
import com.hikvision.vmsnetsdk.netLayer.msp.MspResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.msg.list.MsgListBackState;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
/*     */ public class MsgDetailResponse
/*     */   extends MspResponse
/*     */ {
/*     */   private static final String TAG = "MsgDetailResponse";
/*     */   private MsgDetail msgDetail;
/*     */   private MsgListBackState msgDetailBackState;
/*     */   
/*     */   public MsgDetailResponse(String responseData)
/*     */   {
/*  49 */     super(responseData);
/*     */   }
/*     */   
/*     */ 
/*     */   public String getResponseData()
/*     */   {
/*  55 */     return super.getResponseData();
/*     */   }
/*     */   
/*     */ 
/*     */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*     */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*     */   {
/*  62 */     if ((xmlPullParser == null) || (nodeName == null))
/*     */     {
/*  64 */       CNetSDKLog.e("MsgDetailResponse", "handleXMLStartTag,param error");
/*  65 */       throw new Exception("handleXMLStartTag,param error");
/*     */     }
/*  67 */     if ("Status".equals(nodeName))
/*     */     {
/*  69 */       String status = xmlPullParser.nextText();
/*  70 */       this.msgDetailBackState.setCode(Integer.parseInt(status.trim()));
/*  71 */       CNetSDKLog.d("MsgDetailResponse", "handleXMLStartTag,status：" + status);
/*     */     }
/*  73 */     else if ("Description".equals(nodeName))
/*     */     {
/*  75 */       String description = xmlPullParser.nextText();
/*  76 */       this.msgDetailBackState.setDescription(description);
/*  77 */       CNetSDKLog.d("MsgDetailResponse", "handleXMLStartTag,description：" + description);
/*     */     }
/*  79 */     else if ("MessageDetail".equals(nodeName))
/*     */     {
/*  81 */       this.msgDetail = parseMsgDetail(xmlPullParser);
/*     */     }
/*     */   }
/*     */   
/*     */   private MsgDetail parseMsgDetail(XmlPullParser xmlPullParser)
/*     */     throws XmlPullParserException, IOException, NumberFormatException, Exception
/*     */   {
/*  88 */     xmlPullParser.require(2, null, "MessageDetail");
/*  89 */     int eventType = xmlPullParser.getEventType();
/*     */     
/*  91 */     MsgDetail msgDetail = new MsgDetail();
/*  92 */     while (eventType != 1)
/*     */     {
/*  94 */       String nodeName = xmlPullParser.getName();
/*  95 */       switch (eventType)
/*     */       {
/*     */       case 2: 
/*  98 */         CNetSDKLog.d("MsgDetailResponse", "parseMsgDetail,START_TAG：" + nodeName);
/*  99 */         if ("ID".equals(nodeName))
/*     */         {
/* 101 */           String id = xmlPullParser.nextText();
/* 102 */           CNetSDKLog.d("MsgDetailResponse", "parseMsgDetail,id:" + id);
/* 103 */           msgDetail.setId(id);
/*     */         }
/* 105 */         else if ("Type".equals(nodeName))
/*     */         {
/* 107 */           String type = xmlPullParser.nextText();
/* 108 */           CNetSDKLog.d("MsgDetailResponse", "parseMsgDetail,type:" + type);
/* 109 */           msgDetail.setType(type);
/*     */         }
/* 111 */         else if ("Title".equals(nodeName))
/*     */         {
/* 113 */           String title = xmlPullParser.nextText();
/* 114 */           CNetSDKLog.d("MsgDetailResponse", "parseMsgDetail,title:" + title);
/* 115 */           msgDetail.setTitle(title);
/*     */         }
/* 117 */         else if ("Content".equals(nodeName))
/*     */         {
/* 119 */           String content = xmlPullParser.nextText();
/* 120 */           CNetSDKLog.d("MsgDetailResponse", "parseMsgDetail,content:" + content);
/* 121 */           if ((content == null) || (content.length() <= 0))
/*     */           {
/* 123 */             msgDetail.setContent(content);
/*     */           }
/* 125 */           else if (msgDetail.getType().equals("PM"))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 134 */             msgDetail.setContent(content);
/*     */ 
/*     */           }
/*     */           else
/*     */           {
/* 139 */             msgDetail.setContent(content);
/*     */           }
/*     */         }
/* 142 */         else if ("TypeDescribe".equals(nodeName))
/*     */         {
/* 144 */           String typeDescribe = xmlPullParser.nextText();
/* 145 */           CNetSDKLog.d("MsgDetailResponse", "parseMsgDetail,typeDescribe:" + typeDescribe);
/* 146 */           msgDetail.setTypeDescribe(typeDescribe);
/*     */         }
/* 148 */         else if ("IsChecked".equals(nodeName))
/*     */         {
/* 150 */           String isChecked = xmlPullParser.nextText();
/* 151 */           CNetSDKLog.d("MsgDetailResponse", "parseMsgDetail,isChecked:" + isChecked);
/* 152 */           boolean res = msgDetail.setChecked(Integer.parseInt(isChecked));
/* 153 */           if (!res)
/*     */           {
/* 155 */             CNetSDKLog.e("MsgDetailResponse", "parseMsgDetail,isChecked can not be other values");
/* 156 */             return null;
/*     */           }
/*     */         }
/* 159 */         else if ("CameraID".equals(nodeName))
/*     */         {
/* 161 */           String cameraID = xmlPullParser.nextText();
/* 162 */           CNetSDKLog.d("MsgDetailResponse", "parseMsgDetail,cameraID:" + cameraID);
/* 163 */           msgDetail.setCameraID(cameraID);
/*     */ 
/*     */         }
/* 166 */         else if ("PictureUrl".equals(nodeName))
/*     */         {
/* 168 */           String pictureUrl = xmlPullParser.nextText();
/* 169 */           CNetSDKLog.d("MsgDetailResponse", "parseMsgDetail,pictureUrl:" + pictureUrl);
/* 170 */           if ((null != pictureUrl) && (!pictureUrl.equals("")))
/*     */           {
/* 172 */             if (pictureUrl.contains("&amp;")) {
/* 173 */               pictureUrl = pictureUrl.replace("&amp;", "&");
/*     */             }
/* 175 */             msgDetail.setPictureUrl(pictureUrl);
/*     */           }
/*     */           
/*     */         }
/* 179 */         else if ("Longitude".equals(nodeName))
/*     */         {
/* 181 */           String longitude = xmlPullParser.nextText();
/* 182 */           CNetSDKLog.d("MsgDetailResponse", "parseMsgDetail,longitude:" + longitude);
/* 183 */           if ((longitude != null) && (longitude.length() > 0))
/*     */           {
/* 185 */             msgDetail.setLongitude(Double.parseDouble(longitude));
/*     */           }
/*     */           
/*     */         }
/* 189 */         else if ("Latitude".equals(nodeName))
/*     */         {
/* 191 */           String latitude = xmlPullParser.nextText();
/* 192 */           CNetSDKLog.d("MsgDetailResponse", "parseMsgDetail,latitude:" + latitude);
/* 193 */           if ((latitude != null) && (latitude.length() > 0))
/*     */           {
/* 195 */             msgDetail.setLatitude(Double.parseDouble(latitude));
/*     */           }
/*     */           
/*     */         }
/* 199 */         else if ("ThumbPicUrl".equals(nodeName))
/*     */         {
/* 201 */           String thumbPicUrl = xmlPullParser.nextText();
/* 202 */           CNetSDKLog.d("MsgDetailResponse", "parseMsgDetail,thumbPicUrl:" + thumbPicUrl);
/* 203 */           msgDetail.setThumbPicUrl(thumbPicUrl);
/*     */ 
/*     */         }
/* 206 */         else if ("MediaUrl".equals(nodeName))
/*     */         {
/* 208 */           String mediaUrl = xmlPullParser.nextText();
/* 209 */           CNetSDKLog.d("MsgDetailResponse", "parseMsgDetail,mediaUrl:" + mediaUrl);
/* 210 */           msgDetail.setMediaUrl(mediaUrl);
/*     */         }
/* 212 */         else if ("CreateTime".equals(nodeName))
/*     */         {
/* 214 */           String createTime = xmlPullParser.nextText();
/* 215 */           CNetSDKLog.d("MsgDetailResponse", "parseMsgDetail,createTime:" + createTime);
/* 216 */           msgDetail.setCreateTime(Long.parseLong(createTime.trim()));
/*     */ 
/*     */         }
/* 219 */         else if ("LinkageCameraList".equals(nodeName)) {
/* 220 */           String linkageCameraContent = xmlPullParser.nextText();
/* 221 */           CNetSDKLog.d("MsgDetailResponse", "parseMsgDetail,LinkageCameraList:" + linkageCameraContent);
/* 222 */           EventInfo linkageCameraList = parseLinkageCameraList(linkageCameraContent);
/* 223 */           if (linkageCameraList != null) {
/* 224 */             msgDetail.setLinkageCameraList(linkageCameraList);
/*     */           }
/*     */         }
/* 227 */         break;
/*     */       
/*     */ 
/*     */       case 3: 
/* 231 */         CNetSDKLog.d("MsgDetailResponse", "parseMsgDetail,END_TAG：" + nodeName);
/* 232 */         if ("MessageDetail".equals(nodeName))
/*     */         {
/* 234 */           return msgDetail;
/*     */         }
/*     */         
/*     */         break;
/*     */       }
/*     */       
/* 240 */       eventType = xmlPullParser.next();
/*     */     }
/* 242 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private EventInfo parseLinkageCameraList(String xmlPullParser)
/*     */     throws XmlPullParserException, IOException
/*     */   {
/* 255 */     if (TextUtils.isEmpty(xmlPullParser))
/*     */     {
/* 257 */       CNetSDKLog.d("MsgDetailResponse", "parseLinkageCameraList() parseLinkageCameraList is null");
/* 258 */       return null;
/*     */     }
/* 260 */     EventInfo eventInfo = null;
/* 261 */     List<Trigger> triggerList = null;
/* 262 */     XmlPullParserFactory pullParserFactory = XmlPullParserFactory.newInstance();
/* 263 */     pullParserFactory.setNamespaceAware(true);
/* 264 */     XmlPullParser pullparse = pullParserFactory.newPullParser();
/* 265 */     if (pullparse == null)
/*     */     {
/* 267 */       CNetSDKLog.d("MsgDetailResponse", "parseLinkageCameraList() XmlPullParser is null");
/* 268 */       return null;
/*     */     }
/* 270 */     InputStream in = new ByteArrayInputStream(xmlPullParser.getBytes());
/* 271 */     pullparse.setInput(in, "utf-8");
/* 272 */     int eventType = pullparse.getEventType();
/* 273 */     String nodeName = null;
/* 274 */     while (eventType != 1)
/*     */     {
/* 276 */       nodeName = pullparse.getName();
/* 277 */       switch (eventType)
/*     */       {
/*     */       case 2: 
/* 280 */         if ("EventInfo".equals(nodeName)) {
/* 281 */           eventInfo = new EventInfo();
/* 282 */         } else if ("ExtInfo".equals(nodeName)) {
/* 283 */           if (eventInfo != null) {
/* 284 */             eventInfo.setExtInfo(nodeName);
/*     */           }
/* 286 */         } else if ("TriggerList".equals(nodeName)) {
/* 287 */           triggerList = parseTriggerList(pullparse);
/* 288 */           if (eventInfo != null) {
/* 289 */             eventInfo.setTriggerList(triggerList);
/*     */           }
/*     */         }
/* 292 */         CNetSDKLog.d("MsgDetailResponse", "parseLinkageCameraList() nodeName:" + nodeName);
/* 293 */         break;
/*     */       case 3: 
/* 295 */         if ("EventInfo".equals(nodeName)) {
/* 296 */           return eventInfo;
/*     */         }
/*     */         
/*     */         break;
/*     */       }
/*     */       
/* 302 */       eventType = pullparse.next();
/*     */     }
/* 304 */     return null;
/*     */   }
/*     */   
/*     */   private List<Trigger> parseTriggerList(XmlPullParser pullparse)
/*     */     throws XmlPullParserException, IOException
/*     */   {
/* 310 */     pullparse.require(2, null, "TriggerList");
/* 311 */     List<Trigger> listTrigger = new ArrayList();
/* 312 */     Trigger trigger = null;
/* 313 */     int eventType = pullparse.getEventType();
/*     */     
/* 315 */     while (eventType != 1)
/*     */     {
/* 317 */       String nodeName = pullparse.getName();
/* 318 */       switch (eventType)
/*     */       {
/*     */       case 2: 
/* 321 */         if ("Trigger".endsWith(nodeName)) {
/* 322 */           trigger = new Trigger();
/* 323 */         } else if ("Type".endsWith(nodeName)) {
/* 324 */           String type = pullparse.nextText();
/* 325 */           if (trigger != null) {
/* 326 */             trigger.setType(Integer.parseInt(type));
/*     */           }
/*     */         }
/* 329 */         else if ("Index".endsWith(nodeName))
/*     */         {
/* 331 */           String index = pullparse.nextText();
/* 332 */           if (trigger != null) {
/* 333 */             trigger.setIndex(index);
/*     */           }
/* 335 */         } else if ("PictrueUrl".endsWith(nodeName))
/*     */         {
/* 337 */           String pictrueUrl = pullparse.nextText();
/* 338 */           if ((trigger != null) && 
/* 339 */             (!TextUtils.isEmpty(pictrueUrl))) {
/* 340 */             if (pictrueUrl.contains("&amp;")) {
/* 341 */               pictrueUrl = pictrueUrl.replace("&amp;", "&");
/*     */             }
/* 343 */             trigger.setPictureUrl(pictrueUrl);
/*     */           }
/*     */         }
/*     */         
/* 347 */         break;
/*     */       
/*     */       case 3: 
/* 350 */         if ("TriggerList".endsWith(nodeName))
/* 351 */           return listTrigger;
/* 352 */         if (("Trigger".endsWith(nodeName)) && 
/* 353 */           (listTrigger != null)) {
/* 354 */           listTrigger.add(trigger);
/*     */         }
/*     */         
/*     */         break;
/*     */       }
/*     */       
/*     */       
/* 361 */       eventType = pullparse.next();
/*     */     }
/* 363 */     return null;
/*     */   }
/*     */   
/*     */   public MsgDetail getMsgDetail()
/*     */   {
/* 368 */     return this.msgDetail;
/*     */   }
/*     */   
/*     */ 
/*     */   public MspBackState getMspBackState()
/*     */   {
/* 374 */     return this.msgDetailBackState;
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean doParse(XmlPullParser xmlPullParser)
/*     */     throws XmlPullParserException, Exception
/*     */   {
/* 381 */     this.msgDetailBackState = new MsgListBackState();
/* 382 */     return doParseCycle(xmlPullParser);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\msg\detail\MsgDetailResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */