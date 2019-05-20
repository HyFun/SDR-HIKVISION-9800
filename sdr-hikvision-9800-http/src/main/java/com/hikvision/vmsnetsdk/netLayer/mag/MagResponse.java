/*    */ package com.hikvision.vmsnetsdk.netLayer.mag;
/*    */ 
/*    */

import android.text.TextUtils;

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.base.NetHttpResponse;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public abstract class MagResponse
/*    */   extends NetHttpResponse
/*    */ {
/*    */   protected MagBackState magBackState;
/*    */   private static final String TAG = "MagResponse";
/*    */   
/*    */   public MagResponse(String responseData)
/*    */   {
/* 18 */     super(responseData);
/*    */   }
/*    */   
/*    */   protected abstract boolean doParse(XmlPullParser paramXmlPullParser)
/*    */     throws XmlPullParserException, Exception;
/*    */   
/*    */   protected abstract void handleXMLStartTag(XmlPullParser paramXmlPullParser, String paramString)
/*    */     throws NumberFormatException, XmlPullParserException, IOException, Exception;
/*    */   
/*    */   public MagBackState getmagBackState()
/*    */   {
/* 29 */     return this.magBackState;
/*    */   }
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
/*    */ 
/*    */   protected boolean parseBackStateByXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*    */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*    */   {
/* 46 */     if ((xmlPullParser == null) || (nodeName == null) || (this.magBackState == null)) {
/* 47 */       CNetSDKLog.e("MagResponse", "handleXMLStartTag,param error");
/* 48 */       throw new Exception("handleXMLStartTag,param error");
/*    */     }
/* 50 */     if ("Version".equals(nodeName)) {
/* 51 */       String version = xmlPullParser.nextText();
/* 52 */       CNetSDKLog.d("MagResponse", "parseResponseData,version：" + version);
/* 53 */       this.magBackState.setVersion(version);
/* 54 */       return true; }
/* 55 */     if ("Sequence".equals(nodeName)) {
/* 56 */       String sequence = xmlPullParser.nextText();
/* 57 */       CNetSDKLog.d("MagResponse", "parseResponseData,sequence：" + sequence);
/* 58 */       if ((sequence != null) && (sequence.length() > 0)) {
/* 59 */         this.magBackState.setSequence(Integer.parseInt(sequence));
/*    */       }
/* 61 */       return true; }
/* 62 */     if ("CommandType".equals(nodeName)) {
/* 63 */       String commandType = xmlPullParser.nextText();
/* 64 */       CNetSDKLog.d("MagResponse", "parseResponseData,commandType：" + commandType);
/* 65 */       if (!TextUtils.isEmpty(commandType)) {
/* 66 */         this.magBackState.setCommandType(Integer.parseInt(commandType));
/*    */       }
/* 68 */       return true; }
/* 69 */     if ("Command".equals(nodeName)) {
/* 70 */       String command = xmlPullParser.nextText();
/* 71 */       CNetSDKLog.d("MagResponse", "parseResponseData,command：" + command);
/* 72 */       if (!TextUtils.isEmpty(command)) {
/* 73 */         this.magBackState.setCommand(Integer.parseInt(command));
/*    */       }
/* 75 */       return true;
/*    */     }
/* 77 */     if ("Status".equals(nodeName)) {
/* 78 */       String status = xmlPullParser.nextText();
/* 79 */       CNetSDKLog.d("MagResponse", "parseResponseData,status：" + status);
/* 80 */       if (status != null) {
/* 81 */         this.magBackState.setCode(Integer.parseInt(status.trim()));
/*    */       }
/* 83 */       return true;
/*    */     }
/* 85 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\mag\MagResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */