/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.login;
/*    */ 
/*    */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspBackState;
import com.hikvision.vmsnetsdk.netLayer.msp.MspResponse;
import com.hikvision.vmsnetsdk.netLayer.msp.playToken.PlayTokenBackState;

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
/*    */ public class AutoLoginExcuteResponse extends MspResponse
/*    */ {
/*    */   private static final String TAG = "AutoLoginExcuteResponse";
/*    */   private PlayTokenBackState playTokenBackState;
/*    */   private String Session;
/*    */   
/*    */   public AutoLoginExcuteResponse(String responseData)
/*    */   {
/* 19 */     super(responseData);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getResponseData()
/*    */   {
/* 26 */     return super.getResponseData();
/*    */   }
/*    */   
/*    */ 
/*    */   public MspBackState getMspBackState()
/*    */   {
/* 32 */     return this.playTokenBackState;
/*    */   }
/*    */   
/*    */ 
/*    */   protected boolean doParse(XmlPullParser xmlPullParser)
/*    */     throws XmlPullParserException, Exception
/*    */   {
/* 39 */     this.playTokenBackState = new PlayTokenBackState();
/* 40 */     return doParseCycle(xmlPullParser);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*    */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*    */   {
/* 48 */     if ((xmlPullParser == null) || (nodeName == null)) {
/* 49 */       CNetSDKLog.e("AutoLoginExcuteResponse", "handleXMLStartTag,param error");
/* 50 */       throw new Exception("handleXMLStartTag,param error");
/*    */     }
/* 52 */     if ("Status".equals(nodeName)) {
/* 53 */       String status = xmlPullParser.nextText();
/* 54 */       this.playTokenBackState.setCode(Integer.parseInt(status.trim()));
/* 55 */       CNetSDKLog.d("AutoLoginExcuteResponse", "parseResponseData,status：" + status);
/* 56 */     } else if ("Description".equals(nodeName)) {
/* 57 */       String description = xmlPullParser.nextText();
/* 58 */       this.playTokenBackState.setDescription(description);
/* 59 */       CNetSDKLog.d("AutoLoginExcuteResponse", "parseResponseData,description：" + description);
/* 60 */     } else if ("AutoSession".equals(nodeName)) {
/* 61 */       String autoSession = xmlPullParser.nextText();
/* 62 */       this.Session = autoSession;
/* 63 */       CNetSDKLog.d("AutoLoginExcuteResponse", "parseResponseData,AutoSession：" + autoSession);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\login\AutoLoginExcuteResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */