/*     */ package com.hikvision.vmsnetsdk.netLayer.msp.bonetInfo;
/*     */ 
/*     */

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
/*     */ public class BonetInfoResponse
/*     */   extends MspResponse
/*     */ {
/*     */   private static final String TAG = "PlayTokenResponse";
/*     */   private BonetInfoBackState bonetInfoBackState;
/*     */   private BonetInfo bonetInfo;
/*     */   
/*     */   public BonetInfoResponse(String responseData)
/*     */   {
/*  25 */     super(responseData);
/*     */   }
/*     */   
/*     */   public String getResponseData()
/*     */   {
/*  30 */     return super.getResponseData();
/*     */   }
/*     */   
/*     */   public BonetInfo getBonetInfo() {
/*  34 */     return this.bonetInfo;
/*     */   }
/*     */   
/*     */   public MspBackState getMspBackState()
/*     */   {
/*  39 */     return this.bonetInfoBackState;
/*     */   }
/*     */   
/*     */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*     */   {
/*  44 */     this.bonetInfo = new BonetInfo();
/*  45 */     this.bonetInfoBackState = new BonetInfoBackState();
/*  46 */     return doParseCycle(xmlPullParser);
/*     */   }
/*     */   
/*     */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*     */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*     */   {
/*  52 */     if ((xmlPullParser == null) || (nodeName == null)) {
/*  53 */       CNetSDKLog.e("PlayTokenResponse", "handleXMLStartTag,param error");
/*  54 */       throw new Exception("handleXMLStartTag,param error");
/*     */     }
/*  56 */     if ("Status".equals(nodeName)) {
/*  57 */       String status = xmlPullParser.nextText();
/*  58 */       this.bonetInfoBackState.setCode(Integer.parseInt(status.trim()));
/*  59 */       CNetSDKLog.d("PlayTokenResponse", "handleXMLStartTag,status：" + status);
/*  60 */     } else if ("Description".equals(nodeName)) {
/*  61 */       String description = xmlPullParser.nextText();
/*  62 */       this.bonetInfoBackState.setDescription(description);
/*  63 */       CNetSDKLog.d("PlayTokenResponse", "handleXMLStartTag,description：" + description);
/*  64 */     } else if ("ID".equals(nodeName)) {
/*  65 */       String id = xmlPullParser.nextText();
/*  66 */       CNetSDKLog.d("PlayTokenResponse", "handleXMLStartTag,id：" + id);
/*  67 */       this.bonetInfo.setId(id);
/*  68 */     } else if ("Name".equals(nodeName)) {
/*  69 */       String name = xmlPullParser.nextText();
/*  70 */       CNetSDKLog.d("PlayTokenResponse", "handleXMLStartTag,name：" + name);
/*  71 */       this.bonetInfo.setName(name);
/*  72 */     } else if ("DeviceID".equals(nodeName)) {
/*  73 */       String deviceId = xmlPullParser.nextText();
/*  74 */       CNetSDKLog.d("PlayTokenResponse", "handleXMLStartTag,deviceId：" + deviceId);
/*  75 */       this.bonetInfo.setDeviceId(deviceId);
/*  76 */     } else if ("ChannelNo".equals(nodeName)) {
/*  77 */       String channelNo = xmlPullParser.nextText();
/*  78 */       CNetSDKLog.d("PlayTokenResponse", "handleXMLStartTag,channelNo：" + channelNo);
/*  79 */       this.bonetInfo.setChannelNo(Integer.parseInt(channelNo));
/*  80 */     } else if ("Type".equals(nodeName)) {
/*  81 */       String type = xmlPullParser.nextText();
/*  82 */       CNetSDKLog.d("PlayTokenResponse", "handleXMLStartTag,type：" + type);
/*  83 */       this.bonetInfo.setType(Integer.parseInt(type));
/*  84 */     } else if ("IsOnline".equals(nodeName)) {
/*  85 */       String isOnline = xmlPullParser.nextText();
/*  86 */       CNetSDKLog.d("PlayTokenResponse", "handleXMLStartTag,isOnline：" + isOnline);
/*  87 */       boolean res = this.bonetInfo.setOnline(Integer.parseInt(isOnline));
/*  88 */       if (!res) {
/*  89 */         throw new Exception("handleXMLStartTag,isonline can not be other values");
/*     */       }
/*  91 */     } else if ("IsPTZControl".equals(nodeName)) {
/*  92 */       String isPTZControl = xmlPullParser.nextText();
/*  93 */       CNetSDKLog.d("PlayTokenResponse", "handleXMLStartTag,isPTZControl：" + isPTZControl);
/*  94 */       boolean res = this.bonetInfo.setPTZControl(Integer.parseInt(isPTZControl));
/*  95 */       if (!res) {
/*  96 */         throw new Exception("handleXMLStartTag,isPTZControl can not be other values");
/*     */       }
/*  98 */     } else if ("UserCapability".equals(nodeName)) {
/*  99 */       String userCapability = xmlPullParser.nextText();
/* 100 */       CNetSDKLog.d("PlayTokenResponse", "handleXMLStartTag,userCapability：" + userCapability);
/* 101 */       this.bonetInfo.setUserCapability(ParseHelper.parseStrToIntByComma(userCapability));
/* 102 */     } else if ("RecordPos".equals(nodeName)) {
/* 103 */       String recordPos = xmlPullParser.nextText();
/* 104 */       CNetSDKLog.d("PlayTokenResponse", "handleXMLStartTag,recordPos：" + recordPos);
/* 105 */       this.bonetInfo.setRecordPos(ParseHelper.parseStrToIntByComma(recordPos));
/* 106 */     } else if ("ACSIP".equals(nodeName)) {
/* 107 */       String acsIp = xmlPullParser.nextText();
/* 108 */       CNetSDKLog.d("PlayTokenResponse", "handleXMLStartTag,acsIp：" + acsIp);
/* 109 */       this.bonetInfo.setAcsIp(acsIp);
/* 110 */     } else if ("ACSPort".equals(nodeName)) {
/* 111 */       String acsPort = xmlPullParser.nextText();
/* 112 */       CNetSDKLog.d("PlayTokenResponse", "handleXMLStartTag,acsPort：" + acsPort);
/* 113 */       this.bonetInfo.setAcsPort(Integer.parseInt(acsPort));
/* 114 */     } else if ("CollectedFlag".equals(nodeName)) {
/* 115 */       String collectedFlag = xmlPullParser.nextText();
/* 116 */       CNetSDKLog.d("PlayTokenResponse", "handleXMLStartTag,collectedFlag：" + collectedFlag);
/* 117 */       this.bonetInfo.setCollectedFlag(Integer.parseInt(collectedFlag));
/* 118 */     } else if ("GroupID".equals(nodeName)) {
/* 119 */       String groupID = xmlPullParser.nextText();
/* 120 */       CNetSDKLog.d("PlayTokenResponse", "handleXMLStartTag,groupID：" + groupID);
/* 121 */       this.bonetInfo.setGroupId(Integer.parseInt(groupID));
/* 122 */     } else if ("CascadeFlag".equals(nodeName)) {
/* 123 */       String cascadeFlag = xmlPullParser.nextText();
/* 124 */       CNetSDKLog.d("PlayTokenResponse", "handleXMLStartTag,cascadeFlag：" + cascadeFlag);
/* 125 */       this.bonetInfo.setCascadeFlag(Integer.parseInt(cascadeFlag));
/* 126 */     } else if ("Longitude".equals(nodeName)) {
/* 127 */       String longitude = xmlPullParser.nextText();
/* 128 */       CNetSDKLog.d("PlayTokenResponse", "handleXMLStartTag,longitude：" + longitude);
/* 129 */       this.bonetInfo.setLongitude(longitude);
/* 130 */     } else if ("Latitude".equals(nodeName)) {
/* 131 */       String latitude = xmlPullParser.nextText();
/* 132 */       CNetSDKLog.d("PlayTokenResponse", "handleXMLStartTag,latitude：" + latitude);
/* 133 */       this.bonetInfo.setLatitude(latitude);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\bonetInfo\BonetInfoResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */