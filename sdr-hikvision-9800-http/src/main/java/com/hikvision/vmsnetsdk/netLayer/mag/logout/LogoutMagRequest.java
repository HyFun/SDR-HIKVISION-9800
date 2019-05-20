/*    */ package com.hikvision.vmsnetsdk.netLayer.mag.logout;
/*    */ 
/*    */

import android.util.Xml;

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.mag.MagRequest;
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
/*    */ public class LogoutMagRequest
/*    */   extends MagRequest
/*    */ {
/*    */   private static final String HTTPURL_KEY_LOGOUT = "logout";
/*    */   private static final String TAG = "LogoutMagRequest";
/*    */   private String magRequestHead;
/*    */   private String sessionId;
/*    */   
/*    */   public LogoutMagRequest(String magRequestHead, IRequestTool iRequestTool, String sessionId, boolean isHttpsProtocal)
/*    */   {
/* 21 */     super(iRequestTool, isHttpsProtocal);
/* 22 */     this.magRequestHead = magRequestHead;
/* 23 */     this.sessionId = sessionId;
/*    */   }
/*    */   
/*    */   public String getRequestAddr()
/*    */   {
/* 28 */     if ((this.magRequestHead == null) || (this.magRequestHead.length() <= 0)) {
/* 29 */       return null;
/*    */     }
/* 31 */     return this.magRequestHead + "logout";
/*    */   }
/*    */   
/*    */   protected void insertXMLRequestExplain(XmlSerializer serializer)
/*    */     throws IllegalArgumentException, IllegalStateException, IOException
/*    */   {
/* 37 */     super.insertXMLRequestExplain(serializer);
/* 38 */     serializer.startTag(null, "Command");
/* 39 */     serializer.text("8");
/* 40 */     serializer.endTag(null, "Command");
/*    */   }
/*    */   
/*    */   public String getRequestData()
/*    */   {
/* 45 */     if ((this.sessionId == null) || (this.sessionId.length() <= 0)) {
/* 46 */       CNetSDKLog.e("LogoutMagRequest", "getRequestData,param error.sessionId:" + this.sessionId);
/* 47 */       return null;
/*    */     }
/*    */     
/* 50 */     StringWriter stringWriter = new StringWriter();
/* 51 */     XmlSerializer serializer = Xml.newSerializer();
/*    */     try {
/* 53 */       serializer.setOutput(stringWriter);
/* 54 */       serializer.startTag(null, "MagMessage");
/* 55 */       insertXMLRequestExplain(serializer);
/* 56 */       serializer.startTag(null, "Params");
/* 57 */       serializer.startTag(null, "SID");
/* 58 */       serializer.text(this.sessionId);
/* 59 */       serializer.endTag(null, "SID");
/* 60 */       serializer.endTag(null, "Params");
/* 61 */       serializer.endTag(null, "MagMessage");
/* 62 */       serializer.endDocument();
/* 63 */       serializer.flush();
/*    */     } catch (IllegalArgumentException e) {
/* 65 */       e.printStackTrace();
/*    */     } catch (IllegalStateException e) {
/* 67 */       e.printStackTrace();
/*    */     } catch (IOException e) {
/* 69 */       e.printStackTrace();
/*    */     }
/* 71 */     String temp = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + stringWriter.toString();
/* 72 */     CNetSDKLog.i("LogoutMagRequest", "getRequestData,requestdata:" + temp);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 82 */     return temp;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\mag\logout\LogoutMagRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */