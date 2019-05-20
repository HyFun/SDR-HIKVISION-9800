/*    */ package com.hikvision.vmsnetsdk.netLayer.mag.ptz;
/*    */ 
/*    */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.mag.MagBackState;
import com.hikvision.vmsnetsdk.netLayer.mag.MagResponse;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class PtzCommandResponse
/*    */   extends MagResponse
/*    */ {
/*    */   private static final String TAG = "PtzCommandResponse";
/*    */   
/*    */   public PtzCommandResponse(String responseData)
/*    */   {
/* 17 */     super(responseData);
/*    */   }
/*    */   
/*    */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*    */   {
/* 22 */     this.magBackState = new MagBackState();
/* 23 */     return doParseCycle(xmlPullParser);
/*    */   }
/*    */   
/*    */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*    */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*    */   {
/* 29 */     if ((xmlPullParser == null) || (nodeName == null) || (this.magBackState == null)) {
/* 30 */       CNetSDKLog.e("PtzCommandResponse", "handleXMLStartTag,param error");
/* 31 */       throw new Exception("handleXMLStartTag,param error");
/*    */     }
/* 33 */     parseBackStateByXMLStartTag(xmlPullParser, nodeName);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\mag\ptz\PtzCommandResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */