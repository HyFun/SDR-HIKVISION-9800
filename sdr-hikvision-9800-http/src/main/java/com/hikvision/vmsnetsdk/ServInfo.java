/*     */ package com.hikvision.vmsnetsdk;
/*     */ 
/*     */

import com.hikvision.vmsnetsdk.netLayer.mag.MAGServer;
import com.hikvision.vmsnetsdk.netLayer.msp.login.LoginResponse;
import com.hikvision.vmsnetsdk.util.ParseHelper;

import java.io.Serializable;
import java.util.ArrayList;
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
/*     */ public class ServInfo
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  42 */   private String userID = null;
/*     */   
/*  44 */   public List<VMSInfo> vmsList = new ArrayList();
/*     */   
/*  46 */   public VMSInfo ptzProxyInfo = new VMSInfo();
/*     */   
/*  48 */   public VMSInfo vtduInfo = new VMSInfo();
/*     */   
/*  50 */   public VMSInfo picServerInfo = new VMSInfo();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  55 */   private String sessionID = null;
/*     */   
/*  57 */   private String autoSessionID = null;
/*     */   
/*  59 */   private List<Integer> userCapability = new ArrayList(1);
/*  60 */   private List<Integer> platformCapability = new ArrayList(1);
/*     */   
/*  62 */   public VMSInfo pushServerInfo = new VMSInfo();
/*     */   
/*     */ 
/*  65 */   private boolean isWebAppDefaultSel = false;
/*     */   
/*  67 */   private boolean isTitleVisable = true;
/*     */   
/*  69 */   public List<WebAppInfo> webAppList = new ArrayList();
/*     */   
/*     */ 
/*     */   private boolean isNewPlatform;
/*     */   
/*     */ 
/*     */   private boolean isLoginRequestOk;
/*     */   
/*     */ 
/*     */   private long lifeTime;
/*     */   
/*     */   private MAGServer magServer;
/*     */   
/*     */   private int appVersion;
/*     */   
/*     */   private boolean isInternet;
/*     */   
/*     */   private int userAuthority;
/*     */   
/*     */   private int appNetId;
/*     */   
/*     */   private boolean isTokenVerify;
/*     */   
/*  92 */   private String loginModifyPasswordId = "";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  97 */   private int platformPasswordLevel = 0;
/*     */   
/*  99 */   private int passwordEncryptionMode = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 106 */   private int pwdLevel = 0;
/*     */   
/*     */ 
/*     */ 
/*     */   public static final int APP_CLIENTVERSION_2X = 1;
/*     */   
/*     */ 
/*     */ 
/*     */   public static final int APP_CLIENTVERSION_3X = 2;
/*     */   
/*     */ 
/*     */ 
/*     */   public static final int APP_CLIENTVERSION_4X = 3;
/*     */   
/*     */ 
/* 121 */   public static final Integer USER_CAPABILITY_REALPLAY = Integer.valueOf(1);
/*     */   
/* 123 */   public static final Integer USER_CAPABILITY_PLAYBACK = Integer.valueOf(2);
/*     */   
/* 125 */   public static final Integer USER_CAPABILITY_VIDEO_CAPTURE = Integer.valueOf(3);
/*     */   
/* 127 */   public static final Integer USER_CAPABILITY_BIG_SCREEN_CONTROL = Integer.valueOf(4);
/*     */   
/* 129 */   public static final Integer USER_CAPABILITY_INDUSTRY_APPLICATION = Integer.valueOf(5);
/*     */   
/* 131 */   public static final Integer USER_CAPABILITY_GIS_APPLICATION = Integer.valueOf(6);
/*     */   
/* 133 */   public static final Integer USER_CAPABILITY_PUSH = Integer.valueOf(7);
/*     */   
/*     */ 
/*     */ 
/*     */   public static final int USER_CAPABILITY_SEARCH_CAMERA = 8;
/*     */   
/*     */ 
/*     */ 
/*     */   private static final int APP_CAPABILITY_PUSH_MESSAGE = 1;
/*     */   
/*     */ 
/*     */ 
/*     */   private static final int APP_CAPABILITY_INDUSTRY_APPLICATION = 3;
/*     */   
/*     */ 
/*     */   private static final int APP_CAPABILITY_MAP = 4;
/*     */   
/*     */ 
/*     */   private static final String TAG = "ServInfo";
/*     */   
/*     */ 
/*     */   private static final int PLATFORM_SERACH_RECORD_POSITION = 1;
/*     */   
/*     */ 
/* 157 */   private int srcCode = 0;
/*     */   
/*     */ 
/*     */ 
/* 161 */   private int isHttp = -1;
/*     */   
/*     */ 
/*     */ 
/*     */   private String verifCode;
/*     */   
/*     */ 
/*     */   private String verifCodeKey;
/*     */   
/*     */ 
/*     */ 
/*     */   public static class VMSInfo
/*     */     implements Serializable
/*     */   {
/*     */     private static final long serialVersionUID = 9136810711025540030L;
/*     */     
/*     */ 
/*     */     private String servAddr;
/*     */     
/*     */ 
/*     */     private int servPort;
/*     */     
/*     */ 
/*     */ 
/*     */     public String getServAddr()
/*     */     {
/* 187 */       return this.servAddr;
/*     */     }
/*     */     
/*     */     public void setServAddr(String servAddr) {
/* 191 */       this.servAddr = servAddr;
/*     */     }
/*     */     
/*     */     public int getServPort() {
/* 195 */       return this.servPort;
/*     */     }
/*     */     
/*     */     public void setServPort(int servPort) {
/* 199 */       this.servPort = servPort;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setNewPlatform(boolean isNewPlatform) {
/* 204 */     this.isNewPlatform = isNewPlatform;
/*     */   }
/*     */   
/*     */   public void setServInfo(LoginResponse loginResponse) {
/* 208 */     if (loginResponse == null) {
/* 209 */       return;
/*     */     }
/*     */     
/* 212 */     this.vmsList.clear();
/*     */     
/* 214 */     this.sessionID = loginResponse.getSessionID();
/* 215 */     this.autoSessionID = loginResponse.getAutoSession();
/* 216 */     if (loginResponse.getPushServer() != null) {
/* 217 */       this.pushServerInfo.servAddr = loginResponse.getPushServer().getIp();
/* 218 */       this.pushServerInfo.servPort = loginResponse.getPushServer().getPort();
/*     */     }
/* 220 */     this.userCapability = adapterAppCapability(loginResponse.getAppCapability());
/* 221 */     this.platformCapability = adapterPlatformCapability(loginResponse.getPlatformCapability());
/* 222 */     if (loginResponse.getWebApp() != null)
/*     */     {
/* 224 */       this.isWebAppDefaultSel = loginResponse.getWebApp().isAppDefaultSel();
/*     */       
/* 226 */       this.isTitleVisable = (!loginResponse.getWebApp().getTitleVisable());
/* 227 */       this.webAppList.clear();
/* 228 */       WebAppInfo webAppInfo = new WebAppInfo();
/* 229 */       webAppInfo.setAppIconUrl(loginResponse.getWebApp().getAppIcon());
/* 230 */       webAppInfo.setAppIndex(1);
/* 231 */       webAppInfo.setAppLinkUrl(loginResponse.getWebApp().getAppUrl());
/* 232 */       webAppInfo.setAppName(loginResponse.getWebApp().getAppName());
/* 233 */       this.webAppList.add(webAppInfo);
/*     */     }
/*     */     
/*     */ 
/* 237 */     this.magServer = loginResponse.getMagServer();
/* 238 */     this.userAuthority = loginResponse.getUserAuthority();
/*     */     
/* 240 */     this.isInternet = loginResponse.isInternet();
/* 241 */     this.lifeTime = loginResponse.getLifeTime();
/* 242 */     this.appVersion = loginResponse.getAppVersion();
/* 243 */     this.appNetId = loginResponse.getAppNetId();
/* 244 */     this.isTokenVerify = loginResponse.isTokenVerify();
/*     */     
/*     */ 
/* 247 */     this.loginModifyPasswordId = loginResponse.getLoginModifyPasswordId();
/* 248 */     this.platformPasswordLevel = loginResponse.getPlatformPasswordLevel();
/*     */     
/*     */ 
/*     */ 
/* 252 */     this.isHttp = loginResponse.getIsHttp();
/*     */   }
/*     */   
/*     */ 
/*     */   private List<Integer> adapterAppCapability(List<Integer> appCapabilitys)
/*     */   {
/* 258 */     if (appCapabilitys == null) {
/* 259 */       return null;
/*     */     }
/* 261 */     ArrayList<Integer> userCapability = new ArrayList();
/* 262 */     for (Integer tempCapability : appCapabilitys) {
/* 263 */       switch (tempCapability.intValue())
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       case 8: 
/* 271 */         userCapability.add(Integer.valueOf(8));
/* 272 */         break;
/*     */       case 3: 
/* 274 */         userCapability.add(USER_CAPABILITY_INDUSTRY_APPLICATION);
/* 275 */         break;
/*     */       case 4: 
/* 277 */         userCapability.add(USER_CAPABILITY_GIS_APPLICATION);
/* 278 */         break;
/*     */       case 1: 
/* 280 */         userCapability.add(USER_CAPABILITY_PUSH);
/*     */       }
/*     */       
/*     */     }
/*     */     
/*     */ 
/* 286 */     CNetSDKLog.i("ServInfo", "adapterAppCapability,userCapability:" + ParseHelper.changeIntListToString(userCapability));
/* 287 */     return userCapability;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private List<Integer> adapterPlatformCapability(List<Integer> platformCapabilitys)
/*     */   {
/* 297 */     if (platformCapabilitys == null) {
/* 298 */       return null;
/*     */     }
/* 300 */     ArrayList<Integer> platformCapability = new ArrayList();
/* 301 */     for (Integer tempCapability : platformCapabilitys) {
/* 302 */       switch (tempCapability.intValue()) {
/*     */       case 1: 
/* 304 */         platformCapability.add(Integer.valueOf(1));
/*     */       }
/*     */       
/*     */     }
/*     */     
/*     */ 
/* 310 */     CNetSDKLog.i("ServInfo", "adapterAppCapability,userCapability:" + ParseHelper.changeIntListToString(platformCapability));
/* 311 */     return platformCapability;
/*     */   }
/*     */   
/*     */   public boolean isNewPlatform() {
/* 315 */     return this.isNewPlatform;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getLifeTime()
/*     */   {
/* 325 */     return this.lifeTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isInternet()
/*     */   {
/* 335 */     return this.isInternet;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getUserAuthority()
/*     */   {
/* 345 */     return this.userAuthority;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MAGServer getMagServer()
/*     */   {
/* 355 */     return this.magServer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMagServer(MAGServer magServer)
/*     */   {
/* 365 */     this.magServer = magServer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getAppVersion()
/*     */   {
/* 375 */     return this.appVersion;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getAppNetId()
/*     */   {
/* 385 */     return this.appNetId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLoginRequestOk()
/*     */   {
/* 395 */     return this.isLoginRequestOk;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLoginRequestOk(boolean isLoginRequestOk)
/*     */   {
/* 405 */     this.isLoginRequestOk = isLoginRequestOk;
/*     */   }
/*     */   
/*     */   public String getUserID() {
/* 409 */     return this.userID;
/*     */   }
/*     */   
/*     */   public void setUserID(String userID) {
/* 413 */     this.userID = userID;
/*     */   }
/*     */   
/*     */   public VMSInfo getPtzProxyInfo() {
/* 417 */     return this.ptzProxyInfo;
/*     */   }
/*     */   
/*     */   public void setPtzProxyInfo(VMSInfo ptzProxyInfo) {
/* 421 */     this.ptzProxyInfo = ptzProxyInfo;
/*     */   }
/*     */   
/*     */   public VMSInfo getPicServerInfo() {
/* 425 */     return this.picServerInfo;
/*     */   }
/*     */   
/*     */   public void setPicServerInfo(VMSInfo picServerInfo) {
/* 429 */     this.picServerInfo = picServerInfo;
/*     */   }
/*     */   
/*     */   public String getSessionID() {
/* 433 */     return this.sessionID;
/*     */   }
/*     */   
/*     */   public void setSessionID(String sessionID) {
/* 437 */     this.sessionID = sessionID;
/*     */   }
/*     */   
/*     */   public String getAutoSessionID() {
/* 441 */     return this.autoSessionID;
/*     */   }
/*     */   
/*     */   public void setAutoSessionID(String autoSessionID) {
/* 445 */     this.autoSessionID = autoSessionID;
/*     */   }
/*     */   
/*     */   public List<Integer> getUserCapability() {
/* 449 */     return this.userCapability;
/*     */   }
/*     */   
/*     */   public void setUserCapability(List<Integer> userCapability) {
/* 453 */     this.userCapability = userCapability;
/*     */   }
/*     */   
/*     */   public List<Integer> getPlatformCapability() {
/* 457 */     return this.platformCapability;
/*     */   }
/*     */   
/*     */   public void setPlatformCapability(List<Integer> platformCapability) {
/* 461 */     this.platformCapability = platformCapability;
/*     */   }
/*     */   
/*     */   public VMSInfo getPushServerInfo() {
/* 465 */     return this.pushServerInfo;
/*     */   }
/*     */   
/*     */   public void setPushServerInfo(VMSInfo pushServerInfo) {
/* 469 */     this.pushServerInfo = pushServerInfo;
/*     */   }
/*     */   
/*     */   public boolean isWebAppDefaultSel() {
/* 473 */     return this.isWebAppDefaultSel;
/*     */   }
/*     */   
/*     */   public void setWebAppDefaultSel(boolean isWebAppDefaultSel) {
/* 477 */     this.isWebAppDefaultSel = isWebAppDefaultSel;
/*     */   }
/*     */   
/*     */   public boolean isTitleVisable() {
/* 481 */     return this.isTitleVisable;
/*     */   }
/*     */   
/*     */   public void setTitleVisable(boolean isTitleVisable) {
/* 485 */     this.isTitleVisable = isTitleVisable;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isTokenVerify()
/*     */   {
/* 495 */     return this.isTokenVerify;
/*     */   }
/*     */   
/*     */   public int getSrcCode()
/*     */   {
/* 500 */     return this.srcCode;
/*     */   }
/*     */   
/*     */   public void setSrcCode(int srcCode)
/*     */   {
/* 505 */     this.srcCode = srcCode;
/*     */   }
/*     */   
/*     */   public String getLoginModifyPasswordId()
/*     */   {
/* 510 */     return this.loginModifyPasswordId;
/*     */   }
/*     */   
/*     */   public void setLoginModifyPasswordId(String loginModifyPasswordId)
/*     */   {
/* 515 */     this.loginModifyPasswordId = loginModifyPasswordId;
/*     */   }
/*     */   
/*     */   public int getPlatformPasswordLevel()
/*     */   {
/* 520 */     return this.platformPasswordLevel;
/*     */   }
/*     */   
/*     */   public void setPlatformPasswordLevel(int platformPasswordLevel)
/*     */   {
/* 525 */     this.platformPasswordLevel = platformPasswordLevel;
/*     */   }
/*     */   
/*     */   public int getPwdLevel()
/*     */   {
/* 530 */     return this.pwdLevel;
/*     */   }
/*     */   
/*     */   public void setPwdLevel(int pwdLevel)
/*     */   {
/* 535 */     this.pwdLevel = pwdLevel;
/*     */   }
/*     */   
/*     */   public int getPasswordEncryptionMode() {
/* 539 */     return this.passwordEncryptionMode;
/*     */   }
/*     */   
/*     */   public void setPasswordEncryptionMode(int passwordEncryptionMode)
/*     */   {
/* 544 */     this.passwordEncryptionMode = passwordEncryptionMode;
/*     */   }
/*     */   
/*     */   public void setAppNetId(int appNetId) {
/* 548 */     this.appNetId = appNetId;
/*     */   }
/*     */   
/* 551 */   public void setInternet(boolean isInternet) { this.isInternet = isInternet; }
/*     */   
/*     */   public void setTokenVerify(boolean isTokenVerify) {
/* 554 */     this.isTokenVerify = isTokenVerify;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getIsHttp()
/*     */   {
/* 562 */     return this.isHttp;
/*     */   }
/*     */   
/*     */   public void setLiveTime(int liveTime)
/*     */   {
/* 567 */     this.lifeTime = liveTime;
/*     */   }
/*     */   
/*     */   public String getVerifCode()
/*     */   {
/* 572 */     return this.verifCode;
/*     */   }
/*     */   
/*     */   public void setVerifCode(String mVerifCode)
/*     */   {
/* 577 */     this.verifCode = mVerifCode;
/*     */   }
/*     */   
/*     */   public String getVerifCodeKey()
/*     */   {
/* 582 */     return this.verifCodeKey;
/*     */   }
/*     */   
/*     */   public void setVerifCodeKey(String verifCodeKey)
/*     */   {
/* 587 */     this.verifCodeKey = verifCodeKey;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\ServInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */