/*    */ package com.hikvision.vmsnetsdk.netLayer.mag.register;
/*    */ 
/*    */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.mag.MagBackState;
import com.hikvision.vmsnetsdk.netLayer.mag.MagResponse;
import com.hikvision.vmsnetsdk.util.ParseHelper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.util.List;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class RegisterMagResponse extends MagResponse
/*    */ {
/*    */   private static final String TAG = "RegisterMagResponse";
/*    */   private String magVersion;
/*    */   private List<Integer> abilitys;
/*    */   private long timeout;
/*    */   
/*    */   public RegisterMagResponse(String responseData)
/*    */   {
/* 20 */     super(responseData);
/*    */   }
/*    */   
/*    */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*    */   {
/* 25 */     this.magBackState = new MagBackState();
/* 26 */     return doParseCycle(xmlPullParser);
/*    */   }
/*    */   
/*    */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*    */     throws NumberFormatException, XmlPullParserException, java.io.IOException, Exception
/*    */   {
/* 32 */     if ((xmlPullParser == null) || (nodeName == null) || (this.magBackState == null)) {
/* 33 */       CNetSDKLog.e("RegisterMagResponse", "handleXMLStartTag,param error");
/* 34 */       throw new Exception("handleXMLStartTag,param error");
/*    */     }
/* 36 */     if (parseBackStateByXMLStartTag(xmlPullParser, nodeName))
/* 37 */       return;
/* 38 */     if ("MagVersion".equals(nodeName)) {
/* 39 */       String magVersion = xmlPullParser.nextText();
/* 40 */       CNetSDKLog.d("RegisterMagResponse", "parseResponseData,magVersion：" + magVersion);
/* 41 */       this.magVersion = magVersion;
/*    */     }
/* 43 */     if ("Ability".equals(nodeName)) {
/* 44 */       String abilitys = xmlPullParser.nextText();
/* 45 */       CNetSDKLog.d("RegisterMagResponse", "parseResponseData,abilitys：" + abilitys);
/* 46 */       this.abilitys = ParseHelper.parseStrToIntByComma(abilitys);
/* 47 */     } else if ("Timeout".equals(nodeName)) {
/* 48 */       String timeout = xmlPullParser.nextText();
/* 49 */       CNetSDKLog.d("RegisterMagResponse", "parseResponseData,timeout：" + timeout);
/* 50 */       this.timeout = Long.parseLong(timeout);
/*    */     }
/*    */   }
/*    */   
/*    */   public String getMagVersion()
/*    */   {
/* 56 */     return this.magVersion;
/*    */   }
/*    */   
/*    */   public List<Integer> getAbilitys() {
/* 60 */     return this.abilitys;
/*    */   }
/*    */   
/*    */   public long getTimeout() {
/* 64 */     return this.timeout;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\mag\register\RegisterMagResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */