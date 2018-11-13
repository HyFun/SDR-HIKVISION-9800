/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.playToken;
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
/*    */ public class PlayTokenResponse
/*    */   extends MspResponse
/*    */ {
/*    */   private static final String TAG = "PlayTokenResponse";
/*    */   private PlayTokenBackState playTokenBackState;
/*    */   private String playToken;
/*    */   
/*    */   public PlayTokenResponse(String responseData)
/*    */   {
/* 24 */     super(responseData);
/*    */   }
/*    */   
/*    */   public String getResponseData()
/*    */   {
/* 29 */     return super.getResponseData();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getPlayToken()
/*    */   {
/* 39 */     return this.playToken;
/*    */   }
/*    */   
/*    */   public MspBackState getMspBackState()
/*    */   {
/* 44 */     return this.playTokenBackState;
/*    */   }
/*    */   
/*    */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*    */   {
/* 49 */     this.playTokenBackState = new PlayTokenBackState();
/* 50 */     return doParseCycle(xmlPullParser);
/*    */   }
/*    */   
/*    */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*    */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*    */   {
/* 56 */     if ((xmlPullParser == null) || (nodeName == null)) {
/* 57 */       CNetSDKLog.e("PlayTokenResponse", "handleXMLStartTag,param error");
/* 58 */       throw new Exception("handleXMLStartTag,param error");
/*    */     }
/* 60 */     if ("Status".equals(nodeName)) {
/* 61 */       String status = xmlPullParser.nextText();
/* 62 */       this.playTokenBackState.setCode(Integer.parseInt(status.trim()));
/* 63 */       CNetSDKLog.d("PlayTokenResponse", "parseResponseData,status：" + status);
/* 64 */     } else if ("Description".equals(nodeName)) {
/* 65 */       String description = xmlPullParser.nextText();
/* 66 */       this.playTokenBackState.setDescription(description);
/* 67 */       CNetSDKLog.d("PlayTokenResponse", "parseResponseData,description：" + description);
/* 68 */     } else if ("Token".equals(nodeName)) {
/* 69 */       String token = xmlPullParser.nextText();
/* 70 */       this.playToken = token;
/* 71 */       CNetSDKLog.d("PlayTokenResponse", "parseResponseData,token：" + token);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\playToken\PlayTokenResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */