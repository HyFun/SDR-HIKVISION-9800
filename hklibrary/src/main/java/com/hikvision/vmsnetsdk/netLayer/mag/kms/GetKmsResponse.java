/*     */ package com.hikvision.vmsnetsdk.netLayer.mag.kms;
/*     */ 
/*     */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.mag.MagBackState;
import com.hikvision.vmsnetsdk.netLayer.mag.MagResponse;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GetKmsResponse
/*     */   extends MagResponse
/*     */ {
/*     */   private static final String TAG = "GetKmsResponse";
/*  30 */   private String kmsToken = "";
/*     */   
/*  32 */   private String kmsIp = "";
/*     */   
/*     */   private String kmsPort;
/*     */   
/*  36 */   private String kmsUserName = "";
/*     */   
/*  38 */   private String kmsPassword = "";
/*     */   
/*     */   public GetKmsResponse(String responseData)
/*     */   {
/*  42 */     super(responseData);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected boolean doParse(XmlPullParser xmlPullParser)
/*     */     throws XmlPullParserException, Exception
/*     */   {
/*  50 */     this.magBackState = new MagBackState();
/*  51 */     return doParseCycle(xmlPullParser);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*     */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*     */   {
/*  58 */     if ((xmlPullParser == null) || (nodeName == null) || (this.magBackState == null))
/*     */     {
/*  60 */       CNetSDKLog.e("GetKmsResponse", "handleXMLStartTag,param error");
/*  61 */       throw new Exception("handleXMLStartTag,param error");
/*     */     }
/*  63 */     if (parseBackStateByXMLStartTag(xmlPullParser, nodeName))
/*     */     {
/*  65 */       return;
/*     */     }
/*  67 */     if ("KmsIp".equals(nodeName))
/*     */     {
/*  69 */       String ip = xmlPullParser.nextText();
/*  70 */       CNetSDKLog.d("GetKmsResponse", "parseResponseData,ip：" + ip);
/*  71 */       this.kmsIp = ip;
/*     */     }
/*  73 */     if ("KmsPort".equals(nodeName))
/*     */     {
/*  75 */       String port = xmlPullParser.nextText();
/*  76 */       CNetSDKLog.d("GetKmsResponse", "parseResponseData,port：" + port);
/*  77 */       this.kmsPort = port;
/*     */     }
/*  79 */     else if ("KmsUserName".equals(nodeName))
/*     */     {
/*  81 */       String name = xmlPullParser.nextText();
/*  82 */       CNetSDKLog.d("GetKmsResponse", "parseResponseData,name：" + name);
/*  83 */       this.kmsUserName = name;
/*     */     }
/*  85 */     else if ("kmsPassword".equals(nodeName))
/*     */     {
/*  87 */       String password = xmlPullParser.nextText();
/*  88 */       CNetSDKLog.d("GetKmsResponse", "parseResponseData,password：" + password);
/*  89 */       this.kmsPassword = password;
/*     */     }
/*  91 */     else if ("KmsToken".equals(nodeName))
/*     */     {
/*  93 */       String token = xmlPullParser.nextText();
/*  94 */       CNetSDKLog.d("GetKmsResponse", "parseResponseData,token：" + token);
/*  95 */       this.kmsToken = token;
/*     */     }
/*     */   }
/*     */   
/*     */   public String getKmsToken()
/*     */   {
/* 101 */     return this.kmsToken;
/*     */   }
/*     */   
/*     */   public void setKmsToken(String kmsToken)
/*     */   {
/* 106 */     this.kmsToken = kmsToken;
/*     */   }
/*     */   
/*     */   public String getKmsIp()
/*     */   {
/* 111 */     return this.kmsIp;
/*     */   }
/*     */   
/*     */   public void setKmsIp(String kmsIp)
/*     */   {
/* 116 */     this.kmsIp = kmsIp;
/*     */   }
/*     */   
/*     */   public String getKmsPort()
/*     */   {
/* 121 */     return this.kmsPort;
/*     */   }
/*     */   
/*     */   public void setKmsPort(String kmsPort)
/*     */   {
/* 126 */     this.kmsPort = kmsPort;
/*     */   }
/*     */   
/*     */   public String getKmsUserName()
/*     */   {
/* 131 */     return this.kmsUserName;
/*     */   }
/*     */   
/*     */   public void setKmsUserName(String kmsUserName)
/*     */   {
/* 136 */     this.kmsUserName = kmsUserName;
/*     */   }
/*     */   
/*     */   public String getKmsPassword()
/*     */   {
/* 141 */     return this.kmsPassword;
/*     */   }
/*     */   
/*     */   public void setKmsPassword(String kmsPassword)
/*     */   {
/* 146 */     this.kmsPassword = kmsPassword;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\mag\kms\GetKmsResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */