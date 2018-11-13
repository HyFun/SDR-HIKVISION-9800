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
/*    */ public class AutoLoginResponse extends MspResponse
/*    */ {
/*    */   private static final String TAG = "AutoLoginResponse";
/*    */   private PlayTokenBackState playTokenBackState;
/*    */   private String Session;
/*    */   
/*    */   public AutoLoginResponse(String responseData)
/*    */   {
/* 19 */     super(responseData);
/*    */   }
/*    */   
/*    */   public String getResponseData()
/*    */   {
/* 24 */     return super.getResponseData();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getSession()
/*    */   {
/* 33 */     return this.Session;
/*    */   }
/*    */   
/*    */ 
/*    */   public MspBackState getMspBackState()
/*    */   {
/* 39 */     return this.playTokenBackState;
/*    */   }
/*    */   
/*    */   protected boolean doParse(XmlPullParser xmlPullParser)
/*    */     throws XmlPullParserException, Exception
/*    */   {
/* 45 */     this.playTokenBackState = new PlayTokenBackState();
/* 46 */     return doParseCycle(xmlPullParser);
/*    */   }
/*    */   
/*    */ 
/*    */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*    */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*    */   {
/* 53 */     if ((xmlPullParser == null) || (nodeName == null)) {
/* 54 */       CNetSDKLog.e("AutoLoginResponse", "handleXMLStartTag,param error");
/* 55 */       throw new Exception("handleXMLStartTag,param error");
/*    */     }
/* 57 */     if ("Status".equals(nodeName)) {
/* 58 */       String status = xmlPullParser.nextText();
/* 59 */       this.playTokenBackState.setCode(Integer.parseInt(status.trim()));
/* 60 */       CNetSDKLog.d("AutoLoginResponse", "parseResponseData,status：" + status);
/* 61 */     } else if ("Description".equals(nodeName)) {
/* 62 */       String description = xmlPullParser.nextText();
/* 63 */       this.playTokenBackState.setDescription(description);
/* 64 */       CNetSDKLog.d("AutoLoginResponse", "parseResponseData,description：" + description);
/* 65 */     } else if ("AutoSession".equals(nodeName)) {
/* 66 */       String autoSession = xmlPullParser.nextText();
/* 67 */       this.Session = autoSession;
/* 68 */       CNetSDKLog.d("AutoLoginResponse", "parseResponseData,AutoSession：" + autoSession);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\login\AutoLoginResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */