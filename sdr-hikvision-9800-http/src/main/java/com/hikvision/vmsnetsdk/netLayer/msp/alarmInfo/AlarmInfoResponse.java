/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.alarmInfo;
/*    */ 
/*    */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspBackState;
import com.hikvision.vmsnetsdk.netLayer.msp.MspResponse;
import com.hikvision.vmsnetsdk.util.ParseHelper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AlarmInfoResponse
/*    */   extends MspResponse
/*    */ {
/*    */   private static final String TAG = "AlarmInfoResponse";
/*    */   private AlarmInfoBackState alarmInfoBackState;
/*    */   private AlarmInfo alarmInfo;
/*    */   
/*    */   public AlarmInfoResponse(String responseData)
/*    */   {
/* 25 */     super(responseData);
/*    */   }
/*    */   
/*    */   public String getResponseData()
/*    */   {
/* 30 */     return super.getResponseData();
/*    */   }
/*    */   
/*    */   public AlarmInfo getAlarmInfo() {
/* 34 */     return this.alarmInfo;
/*    */   }
/*    */   
/*    */   public MspBackState getMspBackState()
/*    */   {
/* 39 */     return this.alarmInfoBackState;
/*    */   }
/*    */   
/*    */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*    */   {
/* 44 */     this.alarmInfo = new AlarmInfo();
/* 45 */     this.alarmInfoBackState = new AlarmInfoBackState();
/* 46 */     return doParseCycle(xmlPullParser);
/*    */   }
/*    */   
/*    */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*    */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*    */   {
/* 52 */     if ((xmlPullParser == null) || (nodeName == null)) {
/* 53 */       CNetSDKLog.e("AlarmInfoResponse", "handleXMLStartTag,param error");
/* 54 */       throw new Exception("handleXMLStartTag,param error");
/*    */     }
/* 56 */     if ("Status".equals(nodeName)) {
/* 57 */       String status = xmlPullParser.nextText();
/* 58 */       this.alarmInfoBackState.setCode(Integer.parseInt(status.trim()));
/* 59 */       CNetSDKLog.d("AlarmInfoResponse", "handleXMLStartTag,status：" + status);
/* 60 */     } else if ("Description".equals(nodeName)) {
/* 61 */       String description = xmlPullParser.nextText();
/* 62 */       this.alarmInfoBackState.setDescription(description);
/* 63 */       CNetSDKLog.d("AlarmInfoResponse", "handleXMLStartTag,description：" + description);
/* 64 */     } else if ("ID".equals(nodeName)) {
/* 65 */       String id = xmlPullParser.nextText();
/* 66 */       CNetSDKLog.d("AlarmInfoResponse", "handleXMLStartTag,id：" + id);
/* 67 */       this.alarmInfo.setId(id);
/* 68 */     } else if ("Name".equals(nodeName)) {
/* 69 */       String name = xmlPullParser.nextText();
/* 70 */       CNetSDKLog.d("AlarmInfoResponse", "handleXMLStartTag,name：" + name);
/* 71 */       this.alarmInfo.setName(name);
/* 72 */     } else if ("UserCapability".equals(nodeName)) {
/* 73 */       String userCapability = xmlPullParser.nextText();
/* 74 */       CNetSDKLog.d("AlarmInfoResponse", "handleXMLStartTag,userCapability：" + userCapability);
/* 75 */       this.alarmInfo.setUserCapability(ParseHelper.parseStrToIntByComma(userCapability));
/* 76 */     } else if ("Longitude".equals(nodeName)) {
/* 77 */       String longitude = xmlPullParser.nextText();
/* 78 */       CNetSDKLog.d("AlarmInfoResponse", "handleXMLStartTag,longitude：" + longitude);
/* 79 */       this.alarmInfo.setLongitude(longitude);
/* 80 */     } else if ("Latitude".equals(nodeName)) {
/* 81 */       String latitude = xmlPullParser.nextText();
/* 82 */       CNetSDKLog.d("AlarmInfoResponse", "handleXMLStartTag,latitude：" + latitude);
/* 83 */       this.alarmInfo.setLatitude(latitude);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\alarmInfo\AlarmInfoResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */