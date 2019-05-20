/*    */ package com.hikvision.vmsnetsdk.netLayer.mag.logout;
/*    */ 
/*    */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.mag.MagBackState;
import com.hikvision.vmsnetsdk.netLayer.mag.MagResponse;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class LogoutMagResponse extends MagResponse
/*    */ {
/*    */   private static final String TAG = "LogoutMagResponse";
/*    */   
/*    */   public LogoutMagResponse(String responseData)
/*    */   {
/* 15 */     super(responseData);
/*    */   }
/*    */   
/*    */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*    */   {
/* 20 */     this.magBackState = new MagBackState();
/* 21 */     return doParseCycle(xmlPullParser);
/*    */   }
/*    */   
/*    */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*    */     throws NumberFormatException, XmlPullParserException, java.io.IOException, Exception
/*    */   {
/* 27 */     if ((xmlPullParser == null) || (nodeName == null) || (this.magBackState == null)) {
/* 28 */       CNetSDKLog.e("LogoutMagResponse", "handleXMLStartTag,param error");
/* 29 */       throw new Exception("handleXMLStartTag,param error");
/*    */     }
/* 31 */     parseBackStateByXMLStartTag(xmlPullParser, nodeName);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\mag\logout\LogoutMagResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */