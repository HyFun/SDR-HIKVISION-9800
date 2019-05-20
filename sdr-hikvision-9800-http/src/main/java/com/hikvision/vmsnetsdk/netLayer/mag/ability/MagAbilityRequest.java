/*    */ package com.hikvision.vmsnetsdk.netLayer.mag.ability;
/*    */ 
/*    */

import android.util.Xml;

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.mag.MagRequest;
import com.hikvision.vmsnetsdk.util.AESUtil;
import com.hikvision.vmsnetsdk.util.intf.IRequestTool;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class MagAbilityRequest
/*    */   extends MagRequest
/*    */ {
/*    */   private static final String HTTPURL_KEY_ABITITY = "getAbility";
/*    */   private static final String TAG = "MagAbilityRequest";
/*    */   private String magRequestHead;
/*    */   
/*    */   public MagAbilityRequest(String magRequestHead, IRequestTool iRequestTool, boolean isHttpsProtocal)
/*    */   {
/* 21 */     super(iRequestTool, isHttpsProtocal);
/* 22 */     this.magRequestHead = magRequestHead;
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 27 */     if ((this.magRequestHead == null) || (this.magRequestHead.length() <= 0)) {
/* 28 */       CNetSDKLog.e("MagAbilityRequest", "getRequestAddr,param error.");
/* 29 */       return null;
/*    */     }
/* 31 */     String requestAddr = this.magRequestHead + "getAbility";
/* 32 */     CNetSDKLog.i("MagAbilityRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 33 */     return requestAddr;
/*    */   }
/*    */   
/*    */   protected void insertXMLRequestExplain(XmlSerializer serializer)
/*    */     throws IllegalArgumentException, IllegalStateException, IOException
/*    */   {
/* 39 */     super.insertXMLRequestExplain(serializer);
/* 40 */     serializer.startTag(null, "Command");
/* 41 */     serializer.text("9");
/* 42 */     serializer.endTag(null, "Command");
/*    */   }
/*    */   
/*    */   public String getRequestData()
/*    */   {
/* 47 */     StringWriter stringWriter = new StringWriter();
/* 48 */     XmlSerializer serializer = Xml.newSerializer();
/*    */     try {
/* 50 */       serializer.setOutput(stringWriter);
/* 51 */       serializer.startTag(null, "MagMessage");
/* 52 */       insertXMLRequestExplain(serializer);
/* 53 */       serializer.endTag(null, "MagMessage");
/* 54 */       serializer.endDocument();
/* 55 */       serializer.flush();
/*    */     } catch (IllegalArgumentException e) {
/* 57 */       e.printStackTrace();
/*    */     } catch (IllegalStateException e) {
/* 59 */       e.printStackTrace();
/*    */     } catch (IOException e) {
/* 61 */       e.printStackTrace();
/*    */     }
/* 63 */     String temp = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + stringWriter.toString();
/* 64 */     CNetSDKLog.i("MagAbilityRequest", "getRequestData,requestdata:" + temp);
/* 65 */     String params = null;
/*    */     try
/*    */     {
/* 68 */       params = AESUtil.encrypt("12345678", temp);
/*    */     } catch (Exception e) {
/* 70 */       e.printStackTrace();
/*    */     }
/*    */     
/* 73 */     return params;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\mag\ability\MagAbilityRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */