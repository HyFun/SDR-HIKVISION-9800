/*     */ package com.hikvision.vmsnetsdk.netLayer.msp.login;
/*     */ 
/*     */

import android.text.TextUtils;

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.mag.MAGServer;
import com.hikvision.vmsnetsdk.netLayer.msp.MspBackState;
import com.hikvision.vmsnetsdk.netLayer.msp.MspResponse;
import com.hikvision.vmsnetsdk.util.ParseHelper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

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
/*     */ public class LoginResponse
/*     */   extends MspResponse
/*     */ {
/*     */   private String sessionID;
/*     */   private long lifeTime;
/*     */   private int appVersion;
/*     */   private boolean isInternet;
/*     */   private boolean isTokenVerify;
/*     */   private MAGServer magServer;
/*     */   private PushServer pushServer;
/*     */   private WebApp webApp;
/*     */   private static final String TAG = "LoginResponse";
/*     */   private LoginBackSate loginBackSate;
/*     */   private List<Integer> appCapability;
/*     */   private List<Integer> platformCapability;
/*     */   private int userAuthority;
/*     */   private int appNetId;
/*     */   private String autoSession;
/*  40 */   private int isHttp = -1;
/*     */   
/*     */ 
/*  43 */   private String loginModifyPasswordId = "";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  48 */   private int platformPasswordLevel = 0;
/*     */   
/*  50 */   private int pwdLevel = 0;
/*     */   
/*     */   private String verifCode;
/*     */   
/*     */   private String verifCodeKey;
/*     */   
/*     */   private static final int INTERNET_TYPE_PUBLIC = 1;
/*     */   
/*     */   private static final int TOKEN_VERIFY_TRUE = 1;
/*     */   
/*     */ 
/*     */   public static String getTag()
/*     */   {
/*  63 */     return "LoginResponse";
/*     */   }
/*     */   
/*     */   public String getVerifCode() {
/*  67 */     return this.verifCode;
/*     */   }
/*     */   
/*     */   public String getVerifCodeKey() {
/*  71 */     return this.verifCodeKey;
/*     */   }
/*     */   
/*     */   public static int getInternetTypePublic() {
/*  75 */     return 1;
/*     */   }
/*     */   
/*     */   public static int getTokenVerifyTrue() {
/*  79 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public LoginResponse(String responseData)
/*     */   {
/*  86 */     super(responseData);
/*     */   }
/*     */   
/*     */   public String getSessionID()
/*     */   {
/*  91 */     return this.sessionID;
/*     */   }
/*     */   
/*     */   public String getAutoSession()
/*     */   {
/*  96 */     return this.autoSession;
/*     */   }
/*     */   
/*     */   public void setAutoSession(String autoSession) {
/* 100 */     this.autoSession = autoSession;
/*     */   }
/*     */   
/*     */   public long getLifeTime() {
/* 104 */     return this.lifeTime;
/*     */   }
/*     */   
/*     */   public int getAppVersion() {
/* 108 */     return this.appVersion;
/*     */   }
/*     */   
/*     */   public boolean isInternet() {
/* 112 */     return this.isInternet;
/*     */   }
/*     */   
/*     */   public MAGServer getMagServer() {
/* 116 */     return this.magServer;
/*     */   }
/*     */   
/*     */   public PushServer getPushServer() {
/* 120 */     return this.pushServer;
/*     */   }
/*     */   
/*     */   public WebApp getWebApp() {
/* 124 */     return this.webApp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getIsHttp()
/*     */   {
/* 134 */     return this.isHttp;
/*     */   }
/*     */   
/*     */   public boolean parseResponseData()
/*     */   {
/* 139 */     return parseResponseData(this.responseData);
/*     */   }
/*     */   
/*     */   private boolean getBooleanValue(String inputStr, int trueValue) throws Exception, NumberFormatException {
/* 143 */     if ((inputStr == null) || (inputStr.length() <= 0)) {
/* 144 */       CNetSDKLog.e("LoginResponse", "inputStr can not be null.");
/* 145 */       throw new Exception("inputStr can not be null or empty_v1");
/*     */     }
/* 147 */     CNetSDKLog.d("LoginResponse", "getBooleanValue,inputStr:" + inputStr);
/* 148 */     String tempStr = inputStr.trim();
/* 149 */     if (Integer.parseInt(tempStr) == trueValue) {
/* 150 */       return true;
/*     */     }
/* 152 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*     */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*     */   {
/* 159 */     if ((xmlPullParser == null) || (nodeName == null)) {
/* 160 */       CNetSDKLog.e("LoginResponse", "handleXMLStartTag,param error");
/* 161 */       throw new Exception("handleXMLStartTag,param error");
/*     */     }
/* 163 */     if ("Status".equals(nodeName)) {
/* 164 */       String status = xmlPullParser.nextText();
/* 165 */       this.loginBackSate.setCode(Integer.parseInt(status.trim()));
/* 166 */       CNetSDKLog.d("LoginResponse", "handleXMLStartTag,status：" + status);
/* 167 */     } else if ("Description".equals(nodeName)) {
/* 168 */       String description = xmlPullParser.nextText();
/* 169 */       this.loginBackSate.setDescription(description);
/* 170 */       CNetSDKLog.d("LoginResponse", "handleXMLStartTag,description：" + description);
/* 171 */     } else if ("SessionID".equals(nodeName)) {
/* 172 */       this.sessionID = xmlPullParser.nextText();
/* 173 */       CNetSDKLog.d("LoginResponse", "handleXMLStartTag,sessionID：" + this.sessionID);
/* 174 */     } else if (nodeName.equalsIgnoreCase("VerifCode")) {
/* 175 */       this.verifCode = xmlPullParser.nextText();
/* 176 */     } else if (nodeName.equalsIgnoreCase("VerifCodeKey")) {
/* 177 */       this.verifCodeKey = xmlPullParser.nextText();
/* 178 */     } else if ("AutoSession".equals(nodeName)) {
/* 179 */       String autoSession = xmlPullParser.nextText();
/* 180 */       CNetSDKLog.d("LoginResponse", "handleXMLStartTag,autoSession：" + autoSession);
/* 181 */       this.autoSession = autoSession;
/* 182 */     } else if ("LifeTime".equals(nodeName)) {
/* 183 */       String lifeTime = xmlPullParser.nextText();
/* 184 */       this.lifeTime = Integer.parseInt(lifeTime.trim());
/* 185 */       CNetSDKLog.d("LoginResponse", "handleXMLStartTag,lifeTime：" + lifeTime);
/* 186 */     } else if ("AppVersion".equals(nodeName)) {
/* 187 */       String appVersion = xmlPullParser.nextText();
/* 188 */       this.appVersion = Integer.parseInt(appVersion.trim());
/* 189 */       CNetSDKLog.d("LoginResponse", "handleXMLStartTag,appVersion：" + appVersion);
/* 190 */     } else if ("PlatformCapability".equals(nodeName)) {
/* 191 */       String platformCapability = xmlPullParser.nextText();
/* 192 */       CNetSDKLog.d("LoginResponse", "handleXMLStartTag,platformCapability：" + platformCapability);
/* 193 */       this.platformCapability = ParseHelper.parseStrToIntByComma(platformCapability);
/* 194 */     } else if ("AppCapability".equals(nodeName)) {
/* 195 */       String appCapability = xmlPullParser.nextText();
/* 196 */       CNetSDKLog.d("LoginResponse", "handleXMLStartTag,appCapability：" + appCapability);
/* 197 */       this.appCapability = ParseHelper.parseStrToIntByComma(appCapability);
/* 198 */     } else if ("AppNetID".equals(nodeName)) {
/* 199 */       String appNetID = xmlPullParser.nextText();
/* 200 */       CNetSDKLog.d("LoginResponse", "handleXMLStartTag,appNetID：" + appNetID);
/* 201 */       this.appNetId = Integer.parseInt(appNetID);
/* 202 */     } else if ("UserAuthority".equals(nodeName)) {
/* 203 */       String userAuthority = xmlPullParser.nextText();
/* 204 */       CNetSDKLog.d("LoginResponse", "handleXMLStartTag,userAuthority：" + userAuthority);
/* 205 */       this.userAuthority = Integer.parseInt(userAuthority);
/* 206 */     } else if ("IsInternet".equals(nodeName)) {
/* 207 */       String isInternet = xmlPullParser.nextText();
/* 208 */       CNetSDKLog.d("LoginResponse", "handleXMLStartTag,isInternet：" + isInternet);
/* 209 */       this.isInternet = getBooleanValue(isInternet, 1);
/* 210 */     } else if ("IsTokenVerify".equals(nodeName)) {
/* 211 */       String isTokenVerify = xmlPullParser.nextText();
/* 212 */       CNetSDKLog.d("LoginResponse", "handleXMLStartTag,isTokenVerify：" + isTokenVerify);
/* 213 */       this.isTokenVerify = getBooleanValue(isTokenVerify, 1);
/* 214 */     } else if ("IsHttpPlatform".equals(nodeName)) {
/* 215 */       String isHttpStr = xmlPullParser.nextText();
/* 216 */       if (!TextUtils.isEmpty(isHttpStr)) {
/*     */         try {
/* 218 */           this.isHttp = Integer.parseInt(isHttpStr);
/*     */         } catch (NumberFormatException e) {
/* 220 */           e.printStackTrace();
/*     */         }
/*     */       }
/* 223 */     } else if ("MAGServer".equals(nodeName)) {
/* 224 */       this.magServer = parseMagServer(xmlPullParser);
/* 225 */     } else if ("PushServerAddr".equals(nodeName)) {
/* 226 */       String pushServerIp = xmlPullParser.nextText();
/* 227 */       CNetSDKLog.d("LoginResponse", "handleXMLStartTag,pushServerIp:" + pushServerIp);
/* 228 */       this.pushServer.setIp(pushServerIp);
/* 229 */     } else if ("PushServerPort".equals(nodeName)) {
/* 230 */       String pushServerPort = xmlPullParser.nextText();
/* 231 */       CNetSDKLog.d("LoginResponse", "handleXMLStartTag,pushServerPort:" + pushServerPort);
/* 232 */       if ((pushServerPort == null) || (pushServerPort.trim().length() <= 0)) {
/* 233 */         this.pushServer.setPort(0);
/*     */       } else {
/* 235 */         this.pushServer.setPort(Integer.parseInt(pushServerPort.trim()));
/*     */       }
/* 237 */     } else if ("WebApp".equals(nodeName)) {
/* 238 */       this.webApp = parseWebApp(xmlPullParser);
/*     */ 
/*     */     }
/* 241 */     else if ("login_modify_password_id".equals(nodeName))
/*     */     {
/* 243 */       this.loginModifyPasswordId = xmlPullParser.nextText();
/*     */     }
/* 245 */     else if ("platform_password_level".equals(nodeName))
/*     */     {
/* 247 */       this.platformPasswordLevel = Integer.parseInt(xmlPullParser.nextText());
/*     */ 
/*     */ 
/*     */     }
/* 251 */     else if ("PlatformPasswordLevel".equals(nodeName)) {
/* 252 */       this.platformPasswordLevel = Integer.parseInt(xmlPullParser.nextText());
/*     */     }
/*     */   }
/*     */   
/*     */   private MAGServer parseMagServer(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, NumberFormatException, Exception
/*     */   {
/* 258 */     xmlPullParser.require(2, null, "MAGServer");
/* 259 */     int eventType = xmlPullParser.getEventType();
/*     */     
/* 261 */     MAGServer magServer = new MAGServer();
/* 262 */     while (eventType != 1) {
/* 263 */       String nodeName = xmlPullParser.getName();
/* 264 */       switch (eventType) {
/*     */       case 2: 
/* 266 */         CNetSDKLog.d("LoginResponse", "parseMagServer,START_TAG：" + nodeName);
/* 267 */         if ("MAGCapability".equals(nodeName)) {
/* 268 */           String magCapability = xmlPullParser.nextText();
/* 269 */           CNetSDKLog.d("LoginResponse", "parseMagServer,magCapability:" + magCapability);
/* 270 */           magServer.setMagCapability(ParseHelper.parseStrToIntByComma(magCapability));
/* 271 */         } else if ("MAGStreamSerAddr".equals(nodeName)) {
/* 272 */           String magStreamSerAddr = xmlPullParser.nextText();
/* 273 */           magStreamSerAddr = ParseHelper.inetAddress(magStreamSerAddr);
/* 274 */           CNetSDKLog.d("LoginResponse", "parseMagServer,magStreamSerAddr:" + magStreamSerAddr);
/* 275 */           magServer.setMagStreamSerAddr(magStreamSerAddr);
/* 276 */         } else if ("MAGStreamSerPort".equals(nodeName)) {
/* 277 */           String magStreamSerPort = xmlPullParser.nextText();
/* 278 */           CNetSDKLog.d("LoginResponse", "parseMagServer,magStreamSerPort:" + magStreamSerPort);
/* 279 */           if ((magStreamSerPort == null) || (magStreamSerPort.trim().length() <= 0)) {
/* 280 */             magServer.setMagStreamSerPort(0);
/*     */           } else {
/* 282 */             magServer.setMagStreamSerPort(Integer.parseInt(magStreamSerPort));
/*     */           }
/* 284 */         } else if ("MAGFileSerAddr".equals(nodeName)) {
/* 285 */           String magFileSerAddr = xmlPullParser.nextText();
/* 286 */           magFileSerAddr = ParseHelper.inetAddress(magFileSerAddr);
/* 287 */           CNetSDKLog.d("LoginResponse", "parseMagServer,magFileSerAddr:" + magFileSerAddr);
/* 288 */           magServer.setMagFileSerAddr(magFileSerAddr);
/* 289 */         } else if ("MAGFileSerPort".equals(nodeName)) {
/* 290 */           String magFileSerPort = xmlPullParser.nextText();
/* 291 */           CNetSDKLog.d("LoginResponse", "parseMagServer,magFileSerPort:" + magFileSerPort);
/* 292 */           if ((magFileSerPort == null) || (magFileSerPort.trim().length() <= 0)) {
/* 293 */             magServer.setMagFileSerPort(0);
/*     */           } else {
/* 295 */             magServer.setMagFileSerPort(Integer.parseInt(magFileSerPort));
/*     */           }
/* 297 */         } else if ("UserName".equals(nodeName)) {
/* 298 */           String userName = xmlPullParser.nextText();
/* 299 */           CNetSDKLog.d("LoginResponse", "parseMagServer,userName:" + userName);
/* 300 */           magServer.setUserName(userName);
/* 301 */         } else if ("Password".equals(nodeName)) {
/* 302 */           String password = xmlPullParser.nextText();
/* 303 */           CNetSDKLog.d("LoginResponse", "parseMagServer,password:" + password);
/* 304 */           magServer.setPassword(password);
/* 305 */         } else if ("MAGHttpSerAddr".equals(nodeName)) {
/* 306 */           String magHttpSerAddr = xmlPullParser.nextText();
/* 307 */           magHttpSerAddr = ParseHelper.inetAddress(magHttpSerAddr);
/* 308 */           CNetSDKLog.d("LoginResponse", "parseMagServer,magHttpSerAddr:" + magHttpSerAddr);
/* 309 */           magServer.setMagHttpSerAddr(magHttpSerAddr);
/* 310 */         } else if ("MAGHttpSerPort".equals(nodeName)) {
/* 311 */           String magHttpSerPort = xmlPullParser.nextText();
/* 312 */           CNetSDKLog.d("LoginResponse", "parseMagServer,magHttpSerPort:" + magHttpSerPort);
/* 313 */           if ((magHttpSerPort == null) || (magHttpSerPort.trim().length() <= 0)) {
/* 314 */             magServer.setMagHttpSerPort(0);
/*     */           } else {
/* 316 */             magServer.setMagHttpSerPort(Integer.parseInt(magHttpSerPort));
/*     */           }
/* 318 */         } else if ("MAGGPSSerAddr".equals(nodeName)) {
/* 319 */           String magGPSSerAddr = xmlPullParser.nextText();
/* 320 */           magGPSSerAddr = ParseHelper.inetAddress(magGPSSerAddr);
/* 321 */           CNetSDKLog.d("LoginResponse", "parseMagServer,magGPSSerAddr:" + magGPSSerAddr);
/* 322 */           magServer.setMagGPSSerAddr(magGPSSerAddr);
/* 323 */         } else if ("MAGGPSSerPort".equals(nodeName)) {
/* 324 */           String magGPSSerPort = xmlPullParser.nextText();
/* 325 */           CNetSDKLog.d("LoginResponse", "parseMagServer,magGPSSerPort:" + magGPSSerPort);
/* 326 */           if ((magGPSSerPort == null) || (magGPSSerPort.trim().length() <= 0)) {
/* 327 */             magServer.setMagGPSSerPort(0);
/*     */           } else {
/* 329 */             magServer.setMagGPSSerPort(Integer.parseInt(magGPSSerPort));
/*     */           }
/* 331 */         } else if ("MAGNotifyAddr".equals(nodeName)) {
/* 332 */           String magNotifyAddr = xmlPullParser.nextText();
/* 333 */           magNotifyAddr = ParseHelper.inetAddress(magNotifyAddr);
/* 334 */           CNetSDKLog.d("LoginResponse", "parseMagServer,magNotifyAddr:" + magNotifyAddr);
/* 335 */           magServer.setMagNotifyAddr(magNotifyAddr);
/* 336 */         } else if ("MAGNotifyPort".equals(nodeName)) {
/* 337 */           String magNotifyPort = xmlPullParser.nextText();
/* 338 */           CNetSDKLog.d("LoginResponse", "parseMagServer,magNotifyPort:" + magNotifyPort);
/* 339 */           if ((magNotifyPort == null) || (magNotifyPort.trim().length() <= 0)) {
/* 340 */             magServer.setMagNotifyPort(0);
/*     */           } else {
/* 342 */             magServer.setMagNotifyPort(Integer.parseInt(magNotifyPort));
/*     */           }
/*     */         }
/* 345 */         else if ("MAGTalkAddr".equals(nodeName)) {
/* 346 */           String magTalkAddr = xmlPullParser.nextText();
/* 347 */           magTalkAddr = ParseHelper.inetAddress(magTalkAddr);
/* 348 */           CNetSDKLog.d("LoginResponse", "parseMagServer,magTalkAddr:" + magTalkAddr);
/* 349 */           magServer.setMagTalkAddr(magTalkAddr);
/* 350 */         } else if ("MAGTalkPort".equals(nodeName)) {
/* 351 */           String magTalkPort = xmlPullParser.nextText();
/* 352 */           CNetSDKLog.d("LoginResponse", "parseMagServer,magTalkPort:" + magTalkPort);
/* 353 */           if ((magTalkPort == null) || (magTalkPort.trim().length() <= 0)) {
/* 354 */             magServer.setMagTalkPort(0);
/*     */           } else
/* 356 */             magServer.setMagTalkPort(Integer.parseInt(magTalkPort));
/*     */         }
/* 358 */         break;
/*     */       
/*     */       case 3: 
/* 361 */         CNetSDKLog.d("LoginResponse", "parseMagServer,END_TAG：" + nodeName);
/* 362 */         if ("MAGServer".equals(nodeName)) {
/* 363 */           return magServer;
/*     */         }
/*     */         
/*     */         break;
/*     */       }
/*     */       
/* 369 */       eventType = xmlPullParser.next();
/*     */     }
/* 371 */     return null;
/*     */   }
/*     */   
/*     */   private WebApp parseWebApp(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, NumberFormatException, Exception
/*     */   {
/* 376 */     xmlPullParser.require(2, null, "WebApp");
/* 377 */     int eventType = xmlPullParser.getEventType();
/*     */     
/* 379 */     WebApp webapp = new WebApp();
/* 380 */     while (eventType != 1) {
/* 381 */       String nodeName = xmlPullParser.getName();
/* 382 */       switch (eventType) {
/*     */       case 2: 
/* 384 */         CNetSDKLog.d("LoginResponse", "parseWebApp,START_TAG：" + nodeName);
/* 385 */         if ("AppDefaultSel".equals(nodeName)) {
/* 386 */           String appDefaultSel = xmlPullParser.nextText();
/* 387 */           CNetSDKLog.d("LoginResponse", "parseWebApp,appDefaultSel:" + appDefaultSel);
/* 388 */           webapp.setAppDefaultSel(Integer.parseInt(appDefaultSel.trim()));
/* 389 */         } else if ("TitleVisable".equals(nodeName)) {
/* 390 */           String titleVisable = xmlPullParser.nextText();
/* 391 */           CNetSDKLog.d("LoginResponse", "parseWebApp,titleVisable:" + titleVisable);
/* 392 */           boolean res = webapp.setTitleVisable(Integer.parseInt(titleVisable.trim()));
/* 393 */           if (!res) {
/* 394 */             throw new Exception("parseWebApp,titleVisable can not be other value.");
/*     */           }
/* 396 */         } else if ("AppName".equals(nodeName)) {
/* 397 */           String appName = xmlPullParser.nextText();
/* 398 */           CNetSDKLog.d("LoginResponse", "parseWebApp,appName:" + appName);
/* 399 */           webapp.setAppName(appName);
/* 400 */         } else if ("AppIcon".equals(nodeName)) {
/* 401 */           String appIcon = xmlPullParser.nextText();
/* 402 */           CNetSDKLog.d("LoginResponse", "parseWebApp,appIcon:" + appIcon);
/* 403 */           webapp.setAppIcon(appIcon);
/* 404 */         } else if ("AppUrl".equals(nodeName)) {
/* 405 */           String appUrl = xmlPullParser.nextText();
/* 406 */           CNetSDKLog.d("LoginResponse", "parseWebApp,appUrl:" + appUrl);
/* 407 */           webapp.setAppUrl(appUrl); }
/* 408 */         break;
/*     */       
/*     */ 
/*     */       case 3: 
/* 412 */         CNetSDKLog.d("LoginResponse", "parseWebApp,END_TAG：" + nodeName);
/* 413 */         if ("WebApp".equals(nodeName)) {
/* 414 */           return webapp;
/*     */         }
/*     */         
/*     */         break;
/*     */       }
/*     */       
/* 420 */       eventType = xmlPullParser.next();
/*     */     }
/* 422 */     return null;
/*     */   }
/*     */   
/*     */   public MspBackState getMspBackState()
/*     */   {
/* 427 */     return this.loginBackSate;
/*     */   }
/*     */   
/*     */   public List<Integer> getAppCapability() {
/* 431 */     return this.appCapability;
/*     */   }
/*     */   
/* 434 */   public List<Integer> getPlatformCapability() { return this.platformCapability; }
/*     */   
/*     */   public int getUserAuthority()
/*     */   {
/* 438 */     return this.userAuthority;
/*     */   }
/*     */   
/*     */   public int getAppNetId() {
/* 442 */     return this.appNetId;
/*     */   }
/*     */   
/*     */ 
/*     */   public String getLoginModifyPasswordId()
/*     */   {
/* 448 */     return this.loginModifyPasswordId;
/*     */   }
/*     */   
/*     */   public void setLoginModifyPasswordId(String loginModifyPasswordId)
/*     */   {
/* 453 */     this.loginModifyPasswordId = loginModifyPasswordId;
/*     */   }
/*     */   
/*     */   public int getPlatformPasswordLevel()
/*     */   {
/* 458 */     return this.platformPasswordLevel;
/*     */   }
/*     */   
/*     */   public void setPlatformPasswordLevel(int platformPasswordLevel)
/*     */   {
/* 463 */     this.platformPasswordLevel = platformPasswordLevel;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getPwdLevel()
/*     */   {
/* 469 */     return this.pwdLevel;
/*     */   }
/*     */   
/*     */   public void setPwdLevel(int pwdLevel)
/*     */   {
/* 474 */     this.pwdLevel = pwdLevel;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isTokenVerify()
/*     */   {
/* 484 */     return this.isTokenVerify;
/*     */   }
/*     */   
/*     */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*     */   {
/* 489 */     this.pushServer = new PushServer();
/* 490 */     this.loginBackSate = new LoginBackSate();
/* 491 */     return doParseCycle(xmlPullParser);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\login\LoginResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */