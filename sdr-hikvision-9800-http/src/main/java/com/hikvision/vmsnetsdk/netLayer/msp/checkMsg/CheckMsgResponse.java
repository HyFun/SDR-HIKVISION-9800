/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.checkMsg;
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
/*    */ public class CheckMsgResponse
/*    */   extends MspResponse
/*    */ {
/*    */   private static final String TAG = "CheckMsgResponse";
/*    */   private CheckMsgBackState checkMsgBackState;
/*    */   
/*    */   public CheckMsgResponse(String responseData)
/*    */   {
/* 23 */     super(responseData);
/*    */   }
/*    */   
/*    */   public String getResponseData()
/*    */   {
/* 28 */     return super.getResponseData();
/*    */   }
/*    */   
/*    */   public MspBackState getMspBackState() {
/* 32 */     return this.checkMsgBackState;
/*    */   }
/*    */   
/*    */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*    */   {
/* 37 */     this.checkMsgBackState = new CheckMsgBackState();
/* 38 */     return doParseCycle(xmlPullParser);
/*    */   }
/*    */   
/*    */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*    */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*    */   {
/* 44 */     if ((xmlPullParser == null) || (nodeName == null)) {
/* 45 */       CNetSDKLog.e("CheckMsgResponse", "handleXMLStartTag,param error");
/* 46 */       throw new Exception("handleXMLStartTag,param error");
/*    */     }
/* 48 */     if ("Status".equals(nodeName)) {
/* 49 */       String status = xmlPullParser.nextText();
/* 50 */       this.checkMsgBackState.setCode(Integer.parseInt(status.trim()));
/* 51 */       CNetSDKLog.d("CheckMsgResponse", "parseResponseData,status：" + status);
/* 52 */     } else if ("Description".equals(nodeName)) {
/* 53 */       String description = xmlPullParser.nextText();
/* 54 */       this.checkMsgBackState.setDescription(description);
/* 55 */       CNetSDKLog.d("CheckMsgResponse", "parseResponseData,description：" + description);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\checkMsg\CheckMsgResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */