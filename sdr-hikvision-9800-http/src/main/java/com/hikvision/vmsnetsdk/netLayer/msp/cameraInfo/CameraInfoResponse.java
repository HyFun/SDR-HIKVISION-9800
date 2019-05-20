/*     */ package com.hikvision.vmsnetsdk.netLayer.msp.cameraInfo;
/*     */ 
/*     */

import android.text.TextUtils;

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspBackState;
import com.hikvision.vmsnetsdk.netLayer.msp.MspResponse;
import com.hikvision.vmsnetsdk.util.ParseHelper;

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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CameraInfoResponse
/*     */   extends MspResponse
/*     */ {
/*     */   private static final String TAG = "CameraInfoResponse";
/*     */   private CameraInfoBackState cameraInfoBackState;
/*     */   private CameraDetailInfo cameraInfo;
/*     */   
/*     */   public CameraDetailInfo getCameraInfo()
/*     */   {
/*  27 */     return this.cameraInfo;
/*     */   }
/*     */   
/*     */   public CameraInfoResponse(String responseData)
/*     */   {
/*  32 */     super(responseData);
/*     */   }
/*     */   
/*     */   public String getResponseData()
/*     */   {
/*  37 */     return super.getResponseData();
/*     */   }
/*     */   
/*     */   public MspBackState getMspBackState()
/*     */   {
/*  42 */     return this.cameraInfoBackState;
/*     */   }
/*     */   
/*     */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*     */   {
/*  47 */     this.cameraInfo = new CameraDetailInfo();
/*  48 */     this.cameraInfoBackState = new CameraInfoBackState();
/*  49 */     return doParseCycle(xmlPullParser);
/*     */   }
/*     */   
/*     */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*     */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*     */   {
/*  55 */     if ((xmlPullParser == null) || (nodeName == null)) {
/*  56 */       CNetSDKLog.e("CameraInfoResponse", "handleXMLStartTag,param error");
/*  57 */       throw new Exception("handleXMLStartTag,param error");
/*     */     }
/*  59 */     if ("Status".equals(nodeName)) {
/*  60 */       String status = xmlPullParser.nextText();
/*  61 */       this.cameraInfoBackState.setCode(Integer.parseInt(status.trim()));
/*  62 */       CNetSDKLog.d("CameraInfoResponse", "handleXMLStartTag,status：" + status);
/*  63 */     } else if ("Description".equals(nodeName)) {
/*  64 */       String description = xmlPullParser.nextText();
/*  65 */       this.cameraInfoBackState.setDescription(description);
/*  66 */       CNetSDKLog.d("CameraInfoResponse", "handleXMLStartTag,description：" + description);
/*  67 */     } else if ("ID".equals(nodeName)) {
/*  68 */       String id = xmlPullParser.nextText();
/*  69 */       CNetSDKLog.d("CameraInfoResponse", "handleXMLStartTag,id：" + id);
/*  70 */       this.cameraInfo.setId(id);
/*  71 */     } else if ("Name".equals(nodeName)) {
/*  72 */       String name = xmlPullParser.nextText();
/*  73 */       CNetSDKLog.d("CameraInfoResponse", "handleXMLStartTag,name：" + name);
/*  74 */       this.cameraInfo.setName(name);
/*  75 */     } else if ("DeviceID".equals(nodeName)) {
/*  76 */       String deviceId = xmlPullParser.nextText();
/*  77 */       CNetSDKLog.d("CameraInfoResponse", "handleXMLStartTag,deviceId：" + deviceId);
/*  78 */       this.cameraInfo.setDeviceId(deviceId);
/*  79 */     } else if ("DeviceNetID".equals(nodeName)) {
/*  80 */       String deviceNetId = xmlPullParser.nextText();
/*  81 */       CNetSDKLog.d("CameraInfoResponse", "handleXMLStartTag,deviceNetId：" + deviceNetId);
/*  82 */       if (!TextUtils.isEmpty(deviceNetId)) {
/*  83 */         this.cameraInfo.setDeviceNetId(Integer.parseInt(deviceNetId));
/*     */       }
/*  85 */     } else if ("ChannelNo".equals(nodeName)) {
/*  86 */       String channelNo = xmlPullParser.nextText();
/*  87 */       CNetSDKLog.d("CameraInfoResponse", "handleXMLStartTag,channelNo：" + channelNo);
/*  88 */       this.cameraInfo.setChannelNo(Integer.parseInt(channelNo));
/*  89 */     } else if ("Type".equals(nodeName)) {
/*  90 */       String type = xmlPullParser.nextText();
/*  91 */       CNetSDKLog.d("CameraInfoResponse", "handleXMLStartTag,type：" + type);
/*  92 */       this.cameraInfo.setType(Integer.parseInt(type));
/*  93 */     } else if ("IsOnline".equals(nodeName)) {
/*  94 */       String isOnline = xmlPullParser.nextText();
/*  95 */       CNetSDKLog.d("CameraInfoResponse", "handleXMLStartTag,isOnline：" + isOnline);
/*  96 */       if (!TextUtils.isEmpty(isOnline)) {
/*  97 */         boolean res = this.cameraInfo.setOnline(Integer.parseInt(isOnline));
/*  98 */         if (!res) {
/*  99 */           throw new Exception("handleXMLStartTag,isonline can not be other values");
/*     */         }
/*     */       }
/* 102 */     } else if ("UserCapability".equals(nodeName)) {
/* 103 */       String userCapability = xmlPullParser.nextText();
/* 104 */       CNetSDKLog.d("CameraInfoResponse", "handleXMLStartTag,userCapability：" + userCapability);
/* 105 */       this.cameraInfo.setUserCapability(ParseHelper.parseStrToIntByComma(userCapability));
/* 106 */     } else if ("RecordPos".equals(nodeName)) {
/* 107 */       String recordPos = xmlPullParser.nextText();
/* 108 */       CNetSDKLog.d("CameraInfoResponse", "handleXMLStartTag,recordPos：" + recordPos);
/* 109 */       this.cameraInfo.setRecordPos(ParseHelper.parseStrToIntByComma(recordPos));
/* 110 */     } else if ("CollectedFlag".equals(nodeName)) {
/* 111 */       String collectedFlag = xmlPullParser.nextText();
/* 112 */       CNetSDKLog.d("CameraInfoResponse", "handleXMLStartTag,collectedFlag：" + collectedFlag);
/* 113 */       this.cameraInfo.setCollectedFlag(Integer.parseInt(collectedFlag));
/* 114 */     } else if ("GroupID".equals(nodeName)) {
/* 115 */       String groupID = xmlPullParser.nextText();
/* 116 */       if (!TextUtils.isEmpty(groupID)) {
/* 117 */         this.cameraInfo.setGroupId(Integer.parseInt(groupID));
/*     */       }
/* 119 */       CNetSDKLog.d("CameraInfoResponse", "handleXMLStartTag,groupID：" + groupID);
/* 120 */     } else if ("CascadeFlag".equals(nodeName)) {
/* 121 */       String cascadeFlag = xmlPullParser.nextText();
/* 122 */       CNetSDKLog.d("CameraInfoResponse", "handleXMLStartTag,cascadeFlag：" + cascadeFlag);
/* 123 */       if (!TextUtils.isEmpty(cascadeFlag)) {
/* 124 */         this.cameraInfo.setCascadeFlag(Integer.parseInt(cascadeFlag));
/*     */       }
/* 126 */     } else if ("Longitude".equals(nodeName)) {
/* 127 */       String longitude = xmlPullParser.nextText();
/* 128 */       CNetSDKLog.d("CameraInfoResponse", "handleXMLStartTag,longitude：" + longitude);
/* 129 */       this.cameraInfo.setLongitude(longitude);
/* 130 */     } else if ("Latitude".equals(nodeName)) {
/* 131 */       String latitude = xmlPullParser.nextText();
/* 132 */       CNetSDKLog.d("CameraInfoResponse", "handleXMLStartTag,latitude：" + latitude);
/* 133 */       this.cameraInfo.setLatitude(latitude);
/* 134 */     } else if ("Position".equals(nodeName)) {
/* 135 */       String Position = xmlPullParser.nextText();
/* 136 */       CNetSDKLog.d("CameraInfoResponse", "handleXMLStartTag,Position：" + Position);
/* 137 */       this.cameraInfo.setCPosition(Position);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\cameraInfo\CameraInfoResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */