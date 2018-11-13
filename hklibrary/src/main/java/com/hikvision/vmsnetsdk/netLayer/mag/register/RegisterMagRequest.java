/*     */ package com.hikvision.vmsnetsdk.netLayer.mag.register;
/*     */ 
/*     */

import android.util.Xml;

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.mag.MagRequest;
import com.hikvision.vmsnetsdk.util.intf.IRequestTool;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */

/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RegisterMagRequest
/*     */   extends MagRequest
/*     */ {
/*     */   private static final String HTTPURL_KEY_REGISTER = "register";
/*     */   private static final String TAG = "RegisterMagRequest";
/*     */   private String magRequestHead;
/*     */   private String sessionId;
/*     */   private String tokenId;
/*     */   private String userName;
/*     */   private String appType;
/*     */   private boolean isInternet;
/*     */   public static final String REDIRECT_NEEDED = "1";
/*     */   public static final String REDIRECT_NONEEDED = "0";
/*     */   public static final String APP_TYPE_5060 = "5060";
/*     */   public static final String APP_TYPE_5070 = "5070";
/*     */   
/*     */   public RegisterMagRequest(String magRequestHead, IRequestTool iRequestTool, String sessionId, String tokenId, String userName, String appType, boolean isInternet, boolean isHttpsProtocal)
/*     */   {
/*  37 */     super(iRequestTool, isHttpsProtocal);
/*  38 */     this.magRequestHead = magRequestHead;
/*  39 */     this.sessionId = sessionId;
/*  40 */     this.tokenId = tokenId;
/*  41 */     this.userName = userName;
/*  42 */     this.appType = appType;
/*  43 */     this.isInternet = isInternet;
/*     */   }
/*     */   
/*     */   public String getRequestAddr()
/*     */   {
/*  48 */     if ((this.magRequestHead == null) || (this.magRequestHead.length() <= 0)) {
/*  49 */       return null;
/*     */     }
/*  51 */     String requestAddr = this.magRequestHead + "register";
/*  52 */     CNetSDKLog.i("RegisterMagRequest", "getRequestAddr,requestAddr:" + requestAddr);
/*  53 */     return requestAddr;
/*     */   }
/*     */   
/*     */   protected void insertXMLRequestExplain(XmlSerializer serializer)
/*     */     throws IllegalArgumentException, IllegalStateException, IOException
/*     */   {
/*  59 */     super.insertXMLRequestExplain(serializer);
/*  60 */     serializer.startTag(null, "Command");
/*  61 */     serializer.text("7");
/*  62 */     serializer.endTag(null, "Command");
/*     */   }
/*     */   
/*     */   public String getRequestData()
/*     */   {
/*  67 */     if ((this.sessionId == null) || (this.sessionId.length() <= 0)) {
/*  68 */       CNetSDKLog.e("RegisterMagRequest", "getRequestData,param error.sessionId:" + this.sessionId);
/*  69 */       return null;
/*     */     }
/*     */     
/*  72 */     if ((this.isInternet) && (
/*  73 */       (this.tokenId == null) || (this.tokenId.length() <= 0) || (this.userName == null) || (this.userName.length() <= 0) || (this.appType == null) || 
/*  74 */       (this.appType.length() <= 0))) {
/*  75 */       CNetSDKLog.e("RegisterMagRequest", "getRequestData,param error.tokenId:" + this.tokenId + ",userName:" + this.userName + ",appType:" + this.appType);
/*     */       
/*  77 */       return null;
/*     */     }
/*     */     
/*  80 */     StringWriter stringWriter = new StringWriter();
/*  81 */     XmlSerializer serializer = Xml.newSerializer();
/*     */     try {
/*  83 */       serializer.setOutput(stringWriter);
/*  84 */       serializer.startTag(null, "MagMessage");
/*  85 */       insertXMLRequestExplain(serializer);
/*  86 */       serializer.startTag(null, "Params");
/*  87 */       serializer.startTag(null, "Redirect");
/*  88 */       if (this.isInternet)
/*     */       {
/*  90 */         serializer.text("0");
/*     */       } else {
/*  92 */         serializer.text("1");
/*     */       }
/*  94 */       serializer.endTag(null, "Redirect");
/*  95 */       serializer.startTag(null, "SID");
/*  96 */       serializer.text(this.sessionId);
/*  97 */       serializer.endTag(null, "SID");
/*  98 */       if (this.isInternet) {
/*  99 */         serializer.startTag(null, "PID");
/* 100 */         serializer.text(this.tokenId);
/* 101 */         serializer.endTag(null, "PID");
/* 102 */         serializer.startTag(null, "UserName");
/* 103 */         serializer.text(this.userName);
/* 104 */         serializer.endTag(null, "UserName");
/* 105 */         serializer.startTag(null, "McuType");
/* 106 */         serializer.text(this.appType);
/* 107 */         serializer.endTag(null, "McuType");
/*     */       }
/* 109 */       serializer.endTag(null, "Params");
/* 110 */       serializer.endTag(null, "MagMessage");
/* 111 */       serializer.endDocument();
/* 112 */       serializer.flush();
/*     */     } catch (IllegalArgumentException e) {
/* 114 */       e.printStackTrace();
/*     */     } catch (IllegalStateException e) {
/* 116 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/* 118 */       e.printStackTrace();
/*     */     }
/* 120 */     String temp = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + stringWriter.toString();
/* 121 */     CNetSDKLog.i("RegisterMagRequest", "getRequestData,requestdata:" + temp);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 131 */     return temp;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\mag\register\RegisterMagRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */