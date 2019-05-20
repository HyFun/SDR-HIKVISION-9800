/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.logout;
/*    */ 
/*    */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspBackState;
import com.hikvision.vmsnetsdk.netLayer.msp.MspResponse;

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
/*    */ public class LogoutResponse
/*    */   extends MspResponse
/*    */ {
/*    */   private static final String TAG = "LogoutResponse";
/*    */   private LogoutBackState logoutBackState;
/*    */   
/*    */   public LogoutResponse(String responseData)
/*    */   {
/* 23 */     super(responseData);
/*    */   }
/*    */   
/*    */   public String getResponseData()
/*    */   {
/* 28 */     return super.getResponseData();
/*    */   }
/*    */   
/*    */   public boolean parseResponseData()
/*    */   {
/* 33 */     return parseResponseData(this.responseData);
/*    */   }
/*    */   
/*    */   public MspBackState getMspBackState()
/*    */   {
/* 38 */     return this.logoutBackState;
/*    */   }
/*    */   
/*    */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*    */   {
/* 43 */     this.logoutBackState = new LogoutBackState();
/* 44 */     return doParseCycle(xmlPullParser);
/*    */   }
/*    */   
/*    */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*    */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*    */   {
/* 50 */     if ((xmlPullParser == null) || (nodeName == null)) {
/* 51 */       CNetSDKLog.e("LogoutResponse", "handleXMLStartTag,param error");
/* 52 */       throw new Exception("handleXMLStartTag,param error");
/*    */     }
/* 54 */     if ("Status".equals(nodeName)) {
/* 55 */       String status = xmlPullParser.nextText();
/* 56 */       this.logoutBackState.setCode(Integer.parseInt(status.trim()));
/* 57 */       CNetSDKLog.d("LogoutResponse", "handleXMLStartTag,status：" + status);
/* 58 */     } else if ("Description".equals(nodeName)) {
/* 59 */       String description = xmlPullParser.nextText();
/* 60 */       this.logoutBackState.setDescription(description);
/* 61 */       CNetSDKLog.d("LogoutResponse", "handleXMLStartTag,description：" + description);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\logout\LogoutResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */