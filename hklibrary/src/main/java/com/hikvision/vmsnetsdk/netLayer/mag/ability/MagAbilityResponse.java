/*    */ package com.hikvision.vmsnetsdk.netLayer.mag.ability;
/*    */ 
/*    */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.mag.MagBackState;
import com.hikvision.vmsnetsdk.netLayer.mag.MagResponse;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.util.List;

/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class MagAbilityResponse extends MagResponse
/*    */ {
/*    */   private static final String TAG = "RegisterMagResponse";
/*    */   private String magVersion;
/*    */   private List<Integer> abilitys;
/*    */   
/*    */   public MagAbilityResponse(String responseData)
/*    */   {
/* 18 */     super(responseData);
/*    */   }
/*    */   
/*    */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*    */   {
/* 23 */     this.magBackState = new MagBackState();
/* 24 */     return doParseCycle(xmlPullParser);
/*    */   }
/*    */   
/*    */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*    */     throws NumberFormatException, XmlPullParserException, java.io.IOException, Exception
/*    */   {
/* 30 */     if ((xmlPullParser == null) || (nodeName == null) || (this.magBackState == null)) {
/* 31 */       CNetSDKLog.e("RegisterMagResponse", "handleXMLStartTag,param error");
/* 32 */       throw new Exception("handleXMLStartTag,param error");
/*    */     }
/* 34 */     if (parseBackStateByXMLStartTag(xmlPullParser, nodeName))
/* 35 */       return;
/* 36 */     if ("MagVersion".equals(nodeName)) {
/* 37 */       String magVersion = xmlPullParser.nextText();
/* 38 */       CNetSDKLog.d("RegisterMagResponse", "parseResponseData,magVersion：" + magVersion);
/* 39 */       this.magVersion = magVersion;
/*    */     }
/* 41 */     if ("Ability".equals(nodeName)) {
/* 42 */       String abilitys = xmlPullParser.nextText();
/* 43 */       CNetSDKLog.d("RegisterMagResponse", "parseResponseData,abilitys：" + abilitys);
/* 44 */       this.abilitys = com.hikvision.vmsnetsdk.util.ParseHelper.parseStrToIntByComma(abilitys);
/*    */     }
/*    */   }
/*    */   
/*    */   public String getMagVersion()
/*    */   {
/* 50 */     return this.magVersion;
/*    */   }
/*    */   
/*    */   public List<Integer> getAbilitys() {
/* 54 */     return this.abilitys;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\mag\ability\MagAbilityResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */