/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.pushServerInfo;
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
/*    */ public class PushServerInfoResponse
/*    */   extends MspResponse
/*    */ {
/*    */   private static final String TAG = "PushServerInfoResponse";
/*    */   private PushServerInfoBackState pushServerInfoBackState;
/*    */   
/*    */   public PushServerInfoResponse(String responseData)
/*    */   {
/* 23 */     super(responseData);
/*    */   }
/*    */   
/*    */   public String getResponseData()
/*    */   {
/* 28 */     return super.getResponseData();
/*    */   }
/*    */   
/*    */   public MspBackState getMspBackState()
/*    */   {
/* 33 */     return this.pushServerInfoBackState;
/*    */   }
/*    */   
/*    */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*    */   {
/* 38 */     this.pushServerInfoBackState = new PushServerInfoBackState();
/* 39 */     return doParseCycle(xmlPullParser);
/*    */   }
/*    */   
/*    */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*    */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*    */   {
/* 45 */     if ((xmlPullParser == null) || (nodeName == null)) {
/* 46 */       CNetSDKLog.e("PushServerInfoResponse", "handleXMLStartTag,param error");
/* 47 */       throw new Exception("handleXMLStartTag,param error");
/*    */     }
/*    */     
/* 50 */     if ("Status".equals(nodeName)) {
/* 51 */       String status = xmlPullParser.nextText();
/* 52 */       this.pushServerInfoBackState.setCode(Integer.parseInt(status.trim()));
/* 53 */       CNetSDKLog.d("PushServerInfoResponse", "parseResponseData,status：" + status);
/* 54 */     } else if ("Description".equals(nodeName)) {
/* 55 */       String description = xmlPullParser.nextText();
/* 56 */       this.pushServerInfoBackState.setDescription(description);
/* 57 */       CNetSDKLog.d("PushServerInfoResponse", "parseResponseData,description：" + description);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\pushServerInfo\PushServerInfoResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */