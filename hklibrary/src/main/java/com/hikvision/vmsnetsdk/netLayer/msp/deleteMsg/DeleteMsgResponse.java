/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.deleteMsg;
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
/*    */ public class DeleteMsgResponse
/*    */   extends MspResponse
/*    */ {
/*    */   private static final String TAG = "DeleteMsgResponse";
/*    */   private DeleteMsgBackState checkMsgBackState;
/*    */   
/*    */   public DeleteMsgResponse(String responseData)
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
/* 33 */     return this.checkMsgBackState;
/*    */   }
/*    */   
/*    */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*    */   {
/* 38 */     this.checkMsgBackState = new DeleteMsgBackState();
/* 39 */     return doParseCycle(xmlPullParser);
/*    */   }
/*    */   
/*    */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*    */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*    */   {
/* 45 */     if ((xmlPullParser == null) || (nodeName == null)) {
/* 46 */       CNetSDKLog.e("DeleteMsgResponse", "handleXMLStartTag,param error");
/* 47 */       throw new Exception("handleXMLStartTag,param error");
/*    */     }
/* 49 */     if ("Status".equals(nodeName)) {
/* 50 */       String status = xmlPullParser.nextText();
/* 51 */       this.checkMsgBackState.setCode(Integer.parseInt(status.trim()));
/* 52 */       CNetSDKLog.d("DeleteMsgResponse", "parseResponseData,status：" + status);
/* 53 */     } else if ("Description".equals(nodeName)) {
/* 54 */       String description = xmlPullParser.nextText();
/* 55 */       this.checkMsgBackState.setDescription(description);
/* 56 */       CNetSDKLog.d("DeleteMsgResponse", "parseResponseData,description：" + description);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\deleteMsg\DeleteMsgResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */