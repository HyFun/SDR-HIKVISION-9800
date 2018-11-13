/*    */ package com.hikvision.vmsnetsdk.netLayer.mag.kms;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GetKmsRequest
/*    */   extends MagRequest
/*    */ {
/*    */   private static final String HTTPURL_KEY_GET_KMS_INFO = "getKmsInfo";
/*    */   private static final String TAG = "RegisterMagRequest";
/*    */   private String magRequestHead;
/*    */   
/*    */   public GetKmsRequest(String magRequestHead, IRequestTool iRequestTool, boolean isHttpsProtocal)
/*    */   {
/* 37 */     super(iRequestTool, isHttpsProtocal);
/* 38 */     this.magRequestHead = magRequestHead;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestAddr()
/*    */   {
/* 44 */     if ((this.magRequestHead == null) || (this.magRequestHead.length() <= 0)) {
/* 45 */       return null;
/*    */     }
/* 47 */     String requestAddr = this.magRequestHead + "getKmsInfo";
/* 48 */     CNetSDKLog.i("RegisterMagRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 49 */     return requestAddr;
/*    */   }
/*    */   
/*    */   protected void insertXMLRequestExplain(XmlSerializer serializer)
/*    */     throws IllegalArgumentException, IllegalStateException, IOException
/*    */   {
/* 55 */     super.insertXMLRequestExplain(serializer);
/* 56 */     serializer.startTag(null, "Version");
/* 57 */     serializer.text("0.1");
/* 58 */     serializer.endTag(null, "Version");
/* 59 */     serializer.startTag(null, "Sequence");
/* 60 */     serializer.text("1");
/* 61 */     serializer.endTag(null, "Sequence");
/* 62 */     serializer.startTag(null, "CommandType");
/* 63 */     serializer.text("0");
/* 64 */     serializer.endTag(null, "CommandType");
/* 65 */     serializer.startTag(null, "Command");
/* 66 */     serializer.text("14");
/* 67 */     serializer.endTag(null, "Command");
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRequestData()
/*    */   {
/* 73 */     StringWriter stringWriter = new StringWriter();
/* 74 */     XmlSerializer serializer = Xml.newSerializer();
/*    */     try {
/* 76 */       serializer.setOutput(stringWriter);
/* 77 */       serializer.startTag(null, "MagMessage");
/* 78 */       insertXMLRequestExplain(serializer);
/* 79 */       serializer.startTag(null, "Params");
/* 80 */       serializer.endTag(null, "Params");
/* 81 */       serializer.endTag(null, "MagMessage");
/* 82 */       serializer.endDocument();
/* 83 */       serializer.flush();
/*    */     } catch (IllegalArgumentException e) {
/* 85 */       e.printStackTrace();
/*    */     } catch (IllegalStateException e) {
/* 87 */       e.printStackTrace();
/*    */     } catch (IOException e) {
/* 89 */       e.printStackTrace();
/*    */     }
/* 91 */     String temp = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + stringWriter.toString();
/* 92 */     CNetSDKLog.i("RegisterMagRequest", "getRequestData,requestdata:" + temp);
/* 93 */     return temp;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\mag\kms\GetKmsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */