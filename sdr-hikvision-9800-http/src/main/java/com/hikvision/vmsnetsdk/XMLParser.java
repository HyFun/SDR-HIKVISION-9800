/*      */ package com.hikvision.vmsnetsdk;
/*      */ 
/*      */

import android.text.TextUtils;

import com.hikvision.vmsnetsdk.netLayer.mag.MAGServer;
import com.hikvision.vmsnetsdk.netLayer.msp.checkupdate.LatestAppResult;
import com.hikvision.vmsnetsdk.netLayer.msp.deviceGps.GISTrackPoint;
import com.hikvision.vmsnetsdk.netLayer.msp.deviceInfo.DeviceInfo;
import com.hikvision.vmsnetsdk.util.AESUtil;
import com.hikvision.vmsnetsdk.util.ParseHelper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */

/*      */
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class XMLParser
/*      */ {
/*      */   private static final String TAG = "XMLParser";
/*   64 */   private static String mErrorDesc = "";
/*      */   
/*   66 */   private static int mError = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static int status;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean parseServVersionInfo(String responseXML, ServerVersionInfo serverVersionInfo)
/*      */   {
/*   79 */     if ((responseXML == null) || (serverVersionInfo == null) || (responseXML.length() <= 0))
/*      */     {
/*   81 */       mError = 100;
/*   82 */       mErrorDesc = "input param error!";
/*   83 */       return false;
/*      */     }
/*   85 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseGisInfo() respone:" + responseXML);
/*      */     
/*      */ 
/*   88 */     Element root = getRootElement(responseXML);
/*   89 */     if (root == null) {
/*   90 */       return false;
/*      */     }
/*      */     
/*      */ 
/*   94 */     boolean ret = parseStatus(root);
/*   95 */     if (!ret) {
/*   96 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  100 */     NodeList nodeList = root.getElementsByTagName("Params");
/*  101 */     int size = nodeList.getLength();
/*  102 */     for (int i = 0; i < size; i++)
/*      */     {
/*  104 */       NodeList childNodeList = nodeList.item(i).getChildNodes();
/*  105 */       int childNodeSize = childNodeList.getLength();
/*  106 */       for (int j = 0; j < childNodeSize; j++) {
/*  107 */         Node childNode = childNodeList.item(j);
/*  108 */         String nodeName = childNode.getNodeName();
/*  109 */         if (nodeName.equalsIgnoreCase("PlatformID"))
/*      */         {
/*  111 */           String value = getNodeValue(childNode);
/*  112 */           if ((value != null) && (!value.equals(""))) {
/*  113 */             serverVersionInfo.setPlatformID(parseInteger(value));
/*      */           }
/*  115 */         } else if (nodeName.equalsIgnoreCase("PlatformVersion")) {
/*  116 */           serverVersionInfo.setPlatformVersion(getNodeValue(childNode));
/*  117 */         } else if (nodeName.equalsIgnoreCase("MSPVersion")) {
/*  118 */           serverVersionInfo.setMSPVersion(getNodeValue(childNode));
/*  119 */         } else if (nodeName.equalsIgnoreCase("MAGVersion")) {
/*  120 */           serverVersionInfo.setMAGVersion(getNodeValue(childNode));
/*  121 */         } else if (nodeName.equalsIgnoreCase("VTDUVersion")) {
/*  122 */           serverVersionInfo.setVTDUVersion(getNodeValue(childNode));
/*  123 */         } else if (nodeName.equalsIgnoreCase("PTZProxyVersion")) {
/*  124 */           serverVersionInfo.setPTZProxyVersion(getNodeValue(childNode));
/*  125 */         } else if (nodeName.equalsIgnoreCase("VMSVersion")) {
/*  126 */           serverVersionInfo.setVMSVersion(getNodeValue(childNode));
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  133 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean parseLineInfoList(String responseXML, List<LineInfo> lineInfoList)
/*      */   {
/*  146 */     if ((responseXML == null) || (lineInfoList == null) || (responseXML.length() <= 0))
/*      */     {
/*  148 */       mError = 100;
/*  149 */       mErrorDesc = "input param error!";
/*  150 */       return false;
/*      */     }
/*      */     
/*  153 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseLineInfoList() respone:" + responseXML);
/*      */     
/*      */ 
/*  156 */     Element root = getRootElement(responseXML);
/*  157 */     if (root == null) {
/*  158 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  162 */     boolean ret = parseStatus(root);
/*  163 */     if (!ret) {
/*  164 */       return false;
/*      */     }
/*      */     
/*  167 */     lineInfoList.clear();
/*      */     
/*  169 */     NodeList nodeList = root.getElementsByTagName("LineList");
/*  170 */     int size = nodeList.getLength();
/*  171 */     if (size > 0)
/*      */     {
/*  173 */       NodeList childNodeList = nodeList.item(0).getChildNodes();
/*  174 */       int childNodeSize = childNodeList.getLength();
/*  175 */       for (int j = 0; j < childNodeSize; j++)
/*      */       {
/*  177 */         LineInfo lineInfo = new LineInfo();
/*  178 */         NodeList childNodeList1 = childNodeList.item(j).getChildNodes();
/*  179 */         int childNodeSize1 = childNodeList1.getLength();
/*  180 */         for (int k = 0; k < childNodeSize1; k++)
/*      */         {
/*  182 */           Node childNode = childNodeList1.item(k);
/*  183 */           String nodeName = childNode.getNodeName();
/*  184 */           if (nodeName.equalsIgnoreCase("ID"))
/*      */           {
/*  186 */             String value = getNodeValue(childNode);
/*  187 */             if ((value != null) && (!value.equals(""))) {
/*  188 */               lineInfo.lineID = parseInteger(value);
/*      */             }
/*  190 */           } else if (nodeName.equalsIgnoreCase("Name")) {
/*  191 */             lineInfo.lineName = getNodeValue(childNode);
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*  197 */         if ((lineInfo.lineID != 64536) && (lineInfo.lineName != null)) {
/*  198 */           lineInfoList.add(lineInfo);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  203 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean parseServInfo(String responseXML, ServInfo servInfo)
/*      */   {
/*  216 */     if ((responseXML == null) || (servInfo == null) || (responseXML.length() <= 0))
/*      */     {
/*  218 */       mError = 141;
/*  219 */       mErrorDesc = "get root node fail, Maybe the xml doc format error!";
/*  220 */       return false;
/*      */     }
/*  222 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseServInfo() respone:" + responseXML);
/*      */     
/*      */ 
/*  225 */     Element root = getRootElement(responseXML);
/*  226 */     if (root == null) {
/*  227 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  231 */     boolean ret = parseStatus(root);
/*  232 */     servInfo.setSrcCode(getStatus());
/*  233 */     if (!ret) {
/*  234 */       return false;
/*      */     }
/*      */     
/*  237 */     NodeList nodeList = root.getElementsByTagName("Params");
/*  238 */     int size = nodeList.getLength();
/*  239 */     if (size <= 0) {
/*  240 */       return false;
/*      */     }
/*      */     
/*  243 */     nodeList = nodeList.item(0).getChildNodes();
/*  244 */     size = nodeList.getLength();
/*  245 */     for (int i = 0; i < size; i++) {
/*  246 */       Node node = nodeList.item(i);
/*  247 */       String nodeName = node.getNodeName();
/*  248 */       if (nodeName.equalsIgnoreCase("SessionID"))
/*      */       {
/*  250 */         servInfo.setSessionID(getNodeValue(node));
/*  251 */       } else if (nodeName.equalsIgnoreCase("UserID"))
/*      */       {
/*  253 */         servInfo.setUserID(getNodeValue(node));
/*  254 */       } else if (nodeName.equalsIgnoreCase("LifeTime")) {
/*  255 */         if (!TextUtils.isEmpty(getNodeValue(node))) {
/*  256 */           servInfo.setLiveTime(Integer.parseInt(getNodeValue(node)));
/*      */         }
/*  258 */       } else if (nodeName.equalsIgnoreCase("UserCapability"))
/*      */       {
/*  260 */         String value = getNodeValue(node);
/*  261 */         if ((value != null) && (!value.equals(""))) {
/*  262 */           servInfo.setUserCapability(getListFromString(value));
/*      */ 
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */       }
/*  270 */       else if (nodeName.equalsIgnoreCase("VerifCode")) {
/*  271 */         servInfo.setVerifCode(getNodeValue(node));
/*  272 */       } else if (nodeName.equalsIgnoreCase("VerifCodeKey")) {
/*  273 */         servInfo.setVerifCodeKey(getNodeValue(node));
/*      */ 
/*      */       }
/*  276 */       else if (nodeName.equalsIgnoreCase("AppNetID")) {
/*  277 */         String value = getNodeValue(node);
/*  278 */         servInfo.setAppNetId(Integer.parseInt(value));
/*  279 */       } else if (nodeName.equalsIgnoreCase("IsInternet")) {
/*  280 */         String value = getNodeValue(node);
/*  281 */         if ("1".equals(value)) {
/*  282 */           servInfo.setInternet(true);
/*  283 */         } else if ("0".endsWith(value)) {
/*  284 */           servInfo.setInternet(false);
/*      */         }
/*  286 */       } else if (nodeName.equalsIgnoreCase("IsTokenVerify")) {
/*  287 */         String value = getNodeValue(node);
/*  288 */         if ("1".equals(value)) {
/*  289 */           servInfo.setTokenVerify(true);
/*  290 */         } else if ("0".endsWith(value)) {
/*  291 */           servInfo.setTokenVerify(false);
/*      */         }
/*      */         
/*      */       }
/*  295 */       else if (!nodeName.equalsIgnoreCase("VMSList"))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  301 */         if (nodeName.equalsIgnoreCase("PTZProxy")) {
/*  302 */           NodeList childNodeList = node.getChildNodes();
/*  303 */           int childNodeSize = childNodeList.getLength();
/*  304 */           for (int j = 0; j < childNodeSize; j++) {
/*  305 */             Node childNode = childNodeList.item(j);
/*  306 */             String childNodeName = childNode.getNodeName();
/*  307 */             if (childNodeName.equalsIgnoreCase("PTZProxyAddr"))
/*      */             {
/*  309 */               servInfo.ptzProxyInfo.setServAddr(getNodeValue(childNode));
/*  310 */             } else if (childNodeName.equalsIgnoreCase("PTZProxyPort"))
/*      */             {
/*  312 */               String value = getNodeValue(childNode);
/*  313 */               if ((value != null) && (!value.equals(""))) {
/*  314 */                 servInfo.ptzProxyInfo.setServPort(parseInteger(value));
/*      */               }
/*      */               
/*      */             }
/*      */           }
/*      */         }
/*  320 */         else if (nodeName.equalsIgnoreCase("VTDU")) {
/*  321 */           NodeList childNodeList = node.getChildNodes();
/*  322 */           int childNodeSize = childNodeList.getLength();
/*  323 */           for (int j = 0; j < childNodeSize; j++) {
/*  324 */             Node childNode = childNodeList.item(j);
/*  325 */             String childNodeName = childNode.getNodeName();
/*  326 */             if (childNodeName.equalsIgnoreCase("VTDUAddr"))
/*      */             {
/*  328 */               servInfo.vtduInfo.setServAddr(getNodeValue(childNode));
/*  329 */             } else if (childNodeName.equalsIgnoreCase("VTDUPort"))
/*      */             {
/*  331 */               String value = getNodeValue(childNode);
/*  332 */               if ((value != null) && (!value.equals(""))) {
/*  333 */                 servInfo.vtduInfo.setServPort(parseInteger(value));
/*      */               }
/*      */               
/*      */             }
/*      */           }
/*      */         }
/*  339 */         else if (nodeName.equalsIgnoreCase("PicServer")) {
/*  340 */           NodeList childNodeList = node.getChildNodes();
/*  341 */           int childNodeSize = childNodeList.getLength();
/*  342 */           for (int j = 0; j < childNodeSize; j++) {
/*  343 */             Node childNode = childNodeList.item(j);
/*  344 */             String childNodeName = childNode.getNodeName();
/*  345 */             if (childNodeName.equalsIgnoreCase("PicServerAddr"))
/*      */             {
/*  347 */               servInfo.picServerInfo.setServAddr(getNodeValue(childNode));
/*  348 */             } else if (childNodeName.equalsIgnoreCase("PicServerPort"))
/*      */             {
/*  350 */               String value = getNodeValue(childNode);
/*  351 */               if ((value != null) && (!value.equals(""))) {
/*  352 */                 servInfo.picServerInfo.setServPort(parseInteger(value));
/*      */               }
/*      */               
/*      */             }
/*      */           }
/*      */         }
/*  358 */         else if (nodeName.equalsIgnoreCase("PushServer")) {
/*  359 */           NodeList childNodeList = node.getChildNodes();
/*  360 */           int childNodeSize = childNodeList.getLength();
/*  361 */           for (int j = 0; j < childNodeSize; j++) {
/*  362 */             Node childNode = childNodeList.item(j);
/*  363 */             String childNodeName = childNode.getNodeName();
/*  364 */             if (childNodeName.equalsIgnoreCase("PushServerAddr"))
/*      */             {
/*  366 */               servInfo.pushServerInfo.setServAddr(getNodeValue(childNode));
/*  367 */             } else if (childNodeName.equalsIgnoreCase("PushServerPort"))
/*      */             {
/*  369 */               String value = getNodeValue(childNode);
/*  370 */               if ((value != null) && (!value.equals(""))) {
/*  371 */                 servInfo.pushServerInfo.setServPort(parseInteger(value));
/*      */               }
/*      */               
/*      */             }
/*      */           }
/*      */         }
/*  377 */         else if (nodeName.equalsIgnoreCase("WebAppList")) {
/*  378 */           NodeList childNodeList = node.getChildNodes();
/*  379 */           int childNodeSize = childNodeList.getLength();
/*  380 */           for (int j = 0; j < childNodeSize; j++) {
/*  381 */             Node childNode = childNodeList.item(j);
/*  382 */             String childNodeName = childNode.getNodeName();
/*  383 */             if (childNodeName.equalsIgnoreCase("AppDefaultSel"))
/*      */             {
/*  385 */               String value = getNodeValue(childNode);
/*  386 */               if ((value != null) && (!value.equals("")) && (value.equals("1"))) {
/*  387 */                 servInfo.setWebAppDefaultSel(true);
/*      */               } else {
/*  389 */                 servInfo.setWebAppDefaultSel(false);
/*      */               }
/*  391 */             } else if (childNodeName.equalsIgnoreCase("TitleVisable"))
/*      */             {
/*  393 */               String value = getNodeValue(childNode);
/*  394 */               if ((value != null) && (!value.equals("")) && (value.equals("1"))) {
/*  395 */                 servInfo.setTitleVisable(true);
/*      */               } else {
/*  397 */                 servInfo.setTitleVisable(false);
/*      */               }
/*  399 */             } else if (childNodeName.equalsIgnoreCase("WebApp")) {
/*  400 */               NodeList webApps = childNode.getChildNodes();
/*  401 */               int webAppSize = webApps.getLength();
/*  402 */               WebAppInfo webAppInfo = new WebAppInfo();
/*  403 */               for (int k = 0; k < webAppSize; k++) {
/*  404 */                 Node app = webApps.item(k);
/*  405 */                 String name = app.getNodeName();
/*  406 */                 if (name.equalsIgnoreCase("AppName"))
/*      */                 {
/*  408 */                   webAppInfo.setAppName(getNodeValue(app));
/*  409 */                 } else if (name.equalsIgnoreCase("AppIcon"))
/*      */                 {
/*  411 */                   webAppInfo.setAppIconUrl(getNodeValue(app));
/*  412 */                 } else if (name.equalsIgnoreCase("AppUrl"))
/*      */                 {
/*  414 */                   webAppInfo.setAppLinkUrl(getNodeValue(app));
/*  415 */                 } else if (name.equalsIgnoreCase("AppIndex"))
/*      */                 {
/*  417 */                   String value = getNodeValue(app);
/*  418 */                   if ((value != null) && (!value.equals(""))) {
/*  419 */                     webAppInfo.setAppIndex(parseInteger(value));
/*      */                   }
/*      */                 }
/*      */               }
/*  423 */               servInfo.webAppList.add(webAppInfo);
/*      */             }
/*      */             
/*      */           }
/*      */         }
/*  428 */         else if (nodeName.equalsIgnoreCase("PlatformPasswordLevel")) {
/*  429 */           int pwdLevel = Integer.parseInt(getNodeValue(node));
/*  430 */           servInfo.setPwdLevel(pwdLevel);
/*      */ 
/*      */         }
/*  433 */         else if (nodeName.equalsIgnoreCase("MAGServer")) {
/*  434 */           NodeList childNodes = node.getChildNodes();
/*  435 */           int nodesLength = childNodes.getLength();
/*  436 */           MAGServer magServer = new MAGServer();
/*  437 */           for (int magPos = 0; magPos < nodesLength; magPos++) {
/*  438 */             Node magNode = childNodes.item(magPos);
/*  439 */             String magName = magNode.getNodeName();
/*  440 */             if (magName.equalsIgnoreCase("MAGStreamSerAddr")) {
/*  441 */               String magStreamSerAddr = getNodeValue(magNode);
/*  442 */               magStreamSerAddr = ParseHelper.inetAddress(magStreamSerAddr);
/*  443 */               if (!TextUtils.isEmpty(magStreamSerAddr)) {
/*  444 */                 magServer.setMagStreamSerAddr(magStreamSerAddr);
/*      */               }
/*  446 */             } else if (magName.equalsIgnoreCase("MAGStreamSerPort")) {
/*  447 */               String magStreamSerPort = getNodeValue(magNode);
/*  448 */               if (!TextUtils.isEmpty(magStreamSerPort)) {
/*  449 */                 magServer.setMagStreamSerPort(Integer.parseInt(magStreamSerPort));
/*      */               }
/*  451 */             } else if (magName.equalsIgnoreCase("MAGHttpSerAddr")) {
/*  452 */               String magHttpSerAddr = getNodeValue(magNode);
/*  453 */               magHttpSerAddr = ParseHelper.inetAddress(magHttpSerAddr);
/*  454 */               if (!TextUtils.isEmpty(magHttpSerAddr)) {
/*  455 */                 magServer.setMagHttpSerAddr(magHttpSerAddr);
/*      */               }
/*  457 */             } else if (magName.equalsIgnoreCase("MAGHttpSerPort")) {
/*  458 */               String magHttpSerPort = getNodeValue(magNode);
/*  459 */               if (!TextUtils.isEmpty(magHttpSerPort)) {
/*  460 */                 magServer.setMagHttpSerPort(Integer.parseInt(magHttpSerPort));
/*      */               }
/*  462 */             } else if (magName.equalsIgnoreCase("MAGFileSerAddr")) {
/*  463 */               String magFileSerAddr = getNodeValue(magNode);
/*  464 */               magFileSerAddr = ParseHelper.inetAddress(magFileSerAddr);
/*  465 */               if (!TextUtils.isEmpty(magFileSerAddr)) {
/*  466 */                 magServer.setMagFileSerAddr(magFileSerAddr);
/*      */               }
/*  468 */             } else if (magName.equalsIgnoreCase("MAGFileSerPort")) {
/*  469 */               String magFileSerPort = getNodeValue(magNode);
/*  470 */               if (!TextUtils.isEmpty(magFileSerPort)) {
/*  471 */                 magServer.setMagFileSerPort(Integer.parseInt(magFileSerPort));
/*      */               }
/*  473 */             } else if (magName.equalsIgnoreCase("MAGGPSSerAddr")) {
/*  474 */               String magGPSSerAddr = getNodeValue(magNode);
/*  475 */               magGPSSerAddr = ParseHelper.inetAddress(magGPSSerAddr);
/*  476 */               if (!TextUtils.isEmpty(magGPSSerAddr)) {
/*  477 */                 magServer.setMagGPSSerAddr(magGPSSerAddr);
/*      */               }
/*  479 */             } else if (magName.equalsIgnoreCase("MAGGPSSerPort")) {
/*  480 */               String magGPSSerPort = getNodeValue(magNode);
/*  481 */               if (!TextUtils.isEmpty(magGPSSerPort)) {
/*  482 */                 magServer.setMagGPSSerPort(Integer.parseInt(magGPSSerPort));
/*      */               }
/*  484 */             } else if (magName.equalsIgnoreCase("MAGNotifyAddr")) {
/*  485 */               String magNotifySerAddr = getNodeValue(magNode);
/*  486 */               magNotifySerAddr = ParseHelper.inetAddress(magNotifySerAddr);
/*  487 */               if (!TextUtils.isEmpty(magNotifySerAddr)) {
/*  488 */                 magServer.setMagNotifyAddr(magNotifySerAddr);
/*      */               }
/*  490 */             } else if (magName.equalsIgnoreCase("MAGNotifyPort")) {
/*  491 */               String magNotifySerPort = getNodeValue(magNode);
/*  492 */               if (!TextUtils.isEmpty(magNotifySerPort)) {
/*  493 */                 magServer.setMagNotifyPort(Integer.parseInt(magNotifySerPort));
/*      */               }
/*  495 */             } else if (magName.equalsIgnoreCase("MAGTalkAddr")) {
/*  496 */               String magTalkAddr = getNodeValue(magNode);
/*  497 */               magTalkAddr = ParseHelper.inetAddress(magTalkAddr);
/*  498 */               if (!TextUtils.isEmpty(magTalkAddr)) {
/*  499 */                 magServer.setMagTalkAddr(magTalkAddr);
/*      */               }
/*  501 */             } else if (magName.equalsIgnoreCase("MAGTalkPort")) {
/*  502 */               String magTalkPort = getNodeValue(magNode);
/*  503 */               if (!TextUtils.isEmpty(magTalkPort)) {
/*  504 */                 magServer.setMagTalkPort(Integer.parseInt(magTalkPort));
/*      */               }
/*  506 */             } else if (magName.equalsIgnoreCase("UserName")) {
/*  507 */               String ftpUserName = getNodeValue(magNode);
/*  508 */               magServer.setUserName(ftpUserName);
/*  509 */             } else if (magName.equalsIgnoreCase("Password")) {
/*  510 */               String ftpPassword = getNodeValue(magNode);
/*  511 */               magServer.setPassword(ftpPassword);
/*      */             }
/*      */           }
/*  514 */           servInfo.setMagServer(magServer);
/*      */         }
/*      */       }
/*      */     }
/*  518 */     return true;
/*      */   }
/*      */   
/*      */   private static int getStatus()
/*      */   {
/*  523 */     return status;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean parseLogoutInfo(String responseXML)
/*      */   {
/*  535 */     if ((responseXML == null) || (responseXML.length() <= 0))
/*      */     {
/*  537 */       mError = 100;
/*  538 */       mErrorDesc = "input param error!";
/*  539 */       return false;
/*      */     }
/*  541 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseLogoutInfo() respone:" + responseXML);
/*      */     
/*      */ 
/*  544 */     Element root = getRootElement(responseXML);
/*  545 */     if (root == null) {
/*  546 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  550 */     boolean ret = parseStatus(root);
/*  551 */     if (!ret) {
/*  552 */       return false;
/*      */     }
/*  554 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean parseControlUnitListInfo(String responseXML, List<ControlUnitInfo> controlUnitList)
/*      */   {
/*  568 */     if ((responseXML == null) || (controlUnitList == null) || (responseXML.length() <= 0))
/*      */     {
/*  570 */       mError = 100;
/*  571 */       mErrorDesc = "input param error!";
/*  572 */       return false;
/*      */     }
/*  574 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseControlUnitListInfo() respone:" + responseXML);
/*      */     
/*      */ 
/*  577 */     Element root = getRootElement(responseXML);
/*  578 */     if (root == null) {
/*  579 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  583 */     boolean ret = parseStatus(root);
/*  584 */     if (!ret) {
/*  585 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  589 */     NodeList nodeList = root.getElementsByTagName("Params");
/*  590 */     int size = nodeList.getLength();
/*  591 */     if (size <= 0) {
/*  592 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  596 */     controlUnitList.clear();
/*      */     
/*      */ 
/*  599 */     nodeList = nodeList.item(0).getChildNodes();
/*  600 */     size = nodeList.getLength();
/*  601 */     for (int i = 0; i < size; i++) {
/*  602 */       Node node = nodeList.item(i);
/*  603 */       String nodeName = node.getNodeName();
/*  604 */       if (!nodeName.equalsIgnoreCase("ControlUnitNum"))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  609 */         if (nodeName.equalsIgnoreCase("ControlUnitList"))
/*      */         {
/*  611 */           NodeList childNodeList = node.getChildNodes();
/*  612 */           int childNodeSize = childNodeList.getLength();
/*  613 */           for (int j = 0; j < childNodeSize; j++) {
/*  614 */             Node childNode = childNodeList.item(j);
/*  615 */             String childNodeName = childNode.getNodeName();
/*  616 */             if (childNodeName.equalsIgnoreCase("ControlUnit"))
/*      */             {
/*  618 */               ControlUnitInfo ctrlUnitInfo = new ControlUnitInfo();
/*  619 */               NodeList ctrlUnits = childNode.getChildNodes();
/*  620 */               int ctrlUnitSize = ctrlUnits.getLength();
/*  621 */               for (int k = 0; k < ctrlUnitSize; k++) {
/*  622 */                 Node ctrlUnitNode = ctrlUnits.item(k);
/*  623 */                 String name = ctrlUnitNode.getNodeName();
/*  624 */                 if (name.equalsIgnoreCase("ID")) {
/*  625 */                   String value = getNodeValue(ctrlUnitNode);
/*      */                   
/*  627 */                   if ((value != null) && (!value.equals(""))) {
/*  628 */                     ctrlUnitInfo.setControlUnitID(value);
/*      */                   }
/*  630 */                 } else if (name.equalsIgnoreCase("Name")) {
/*  631 */                   ctrlUnitInfo.setName(getNodeValue(ctrlUnitNode));
/*      */                 }
/*  633 */                 else if (name.equalsIgnoreCase("ParentID")) {
/*  634 */                   String value = getNodeValue(ctrlUnitNode);
/*      */                   
/*  636 */                   if ((value != null) && (!value.equals(""))) {
/*  637 */                     ctrlUnitInfo.setParentID(value);
/*      */                   }
/*      */                 }
/*      */               }
/*      */               
/*      */ 
/*  643 */               controlUnitList.add(ctrlUnitInfo);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  653 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean parseRegionListInfo(String responseXML, List<RegionInfo> regionList)
/*      */   {
/*  666 */     if ((responseXML == null) || (regionList == null) || (responseXML.length() <= 0))
/*      */     {
/*  668 */       mError = 100;
/*  669 */       mErrorDesc = "input param error!";
/*  670 */       return false;
/*      */     }
/*  672 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseRegionListInfo() respone:" + responseXML);
/*      */     
/*      */ 
/*  675 */     Element root = getRootElement(responseXML);
/*  676 */     if (root == null) {
/*  677 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  681 */     boolean ret = parseStatus(root);
/*  682 */     if (!ret) {
/*  683 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  687 */     NodeList nodeList = root.getElementsByTagName("Params");
/*  688 */     int size = nodeList.getLength();
/*  689 */     if (size <= 0) {
/*  690 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  694 */     regionList.clear();
/*      */     
/*      */ 
/*  697 */     nodeList = nodeList.item(0).getChildNodes();
/*  698 */     size = nodeList.getLength();
/*  699 */     for (int i = 0; i < size; i++) {
/*  700 */       Node node = nodeList.item(i);
/*  701 */       String nodeName = node.getNodeName();
/*  702 */       if (!nodeName.equalsIgnoreCase("RegionNum"))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  707 */         if (nodeName.equalsIgnoreCase("RegionList"))
/*      */         {
/*  709 */           NodeList childNodeList = node.getChildNodes();
/*  710 */           int childNodeSize = childNodeList.getLength();
/*  711 */           for (int j = 0; j < childNodeSize; j++) {
/*  712 */             Node childNode = childNodeList.item(j);
/*  713 */             String childNodeName = childNode.getNodeName();
/*  714 */             if (childNodeName.equalsIgnoreCase("Region"))
/*      */             {
/*  716 */               RegionInfo regionInfo = new RegionInfo();
/*  717 */               NodeList regions = childNode.getChildNodes();
/*  718 */               int regionSize = regions.getLength();
/*  719 */               for (int k = 0; k < regionSize; k++) {
/*  720 */                 Node regionNode = regions.item(k);
/*  721 */                 String name = regionNode.getNodeName();
/*  722 */                 if (name.equalsIgnoreCase("ID"))
/*      */                 {
/*  724 */                   String value = getNodeValue(regionNode);
/*  725 */                   if ((value != null) && (!value.equals(""))) {
/*  726 */                     regionInfo.setRegionID(value);
/*      */                   }
/*  728 */                 } else if (name.equalsIgnoreCase("Name"))
/*      */                 {
/*  730 */                   regionInfo.setName(getNodeValue(regionNode));
/*  731 */                 } else if (name.equalsIgnoreCase("ControlUnitId"))
/*      */                 {
/*  733 */                   String value = getNodeValue(regionNode);
/*  734 */                   if ((value != null) && (!value.equals(""))) {
/*  735 */                     regionInfo.setControlUnitID(parseInteger(value));
/*      */                   }
/*  737 */                 } else if (name.equalsIgnoreCase("ParentID"))
/*      */                 {
/*  739 */                   String value = getNodeValue(regionNode);
/*  740 */                   if ((value != null) && (!value.equals(""))) {
/*  741 */                     regionInfo.setParentID(value);
/*      */                   }
/*      */                 }
/*      */               }
/*      */               
/*      */ 
/*  747 */               regionList.add(regionInfo);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  757 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean parseCameraListInfo(String responseXML, List<CameraInfo> cameraList)
/*      */   {
/*  770 */     if ((responseXML == null) || (cameraList == null) || (responseXML.length() <= 0))
/*      */     {
/*  772 */       mError = 100;
/*  773 */       mErrorDesc = "input param error!";
/*  774 */       return false;
/*      */     }
/*  776 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseCameraListInfo() respone:" + responseXML);
/*      */     
/*      */ 
/*  779 */     Element root = getRootElement(responseXML);
/*  780 */     if (root == null) {
/*  781 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  785 */     boolean ret = parseStatus(root);
/*  786 */     if (!ret) {
/*  787 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  791 */     NodeList nodeList = root.getElementsByTagName("Params");
/*  792 */     int size = nodeList.getLength();
/*  793 */     if (size <= 0) {
/*  794 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  798 */     cameraList.clear();
/*      */     
/*      */ 
/*  801 */     nodeList = nodeList.item(0).getChildNodes();
/*  802 */     size = nodeList.getLength();
/*  803 */     for (int i = 0; i < size; i++) {
/*  804 */       Node node = nodeList.item(i);
/*  805 */       String nodeName = node.getNodeName();
/*  806 */       if (!nodeName.equalsIgnoreCase("CameraNum"))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  811 */         if (nodeName.equalsIgnoreCase("CameraList"))
/*      */         {
/*  813 */           NodeList childNodeList = node.getChildNodes();
/*  814 */           int childNodeSize = childNodeList.getLength();
/*  815 */           for (int j = 0; j < childNodeSize; j++) {
/*  816 */             Node childNode = childNodeList.item(j);
/*  817 */             String childNodeName = childNode.getNodeName();
/*  818 */             if (childNodeName.equalsIgnoreCase("Camera"))
/*      */             {
/*  820 */               CameraInfo cameraInfo = new CameraInfo();
/*  821 */               NodeList cameras = childNode.getChildNodes();
/*  822 */               int cameraSize = cameras.getLength();
/*  823 */               for (int k = 0; k < cameraSize; k++) {
/*  824 */                 Node cameraNode = cameras.item(k);
/*  825 */                 String name = cameraNode.getNodeName();
/*  826 */                 if (name.equalsIgnoreCase("ID"))
/*      */                 {
/*  828 */                   cameraInfo.setId(getNodeValue(cameraNode));
/*  829 */                 } else if (name.equalsIgnoreCase("Name"))
/*      */                 {
/*  831 */                   cameraInfo.setName(getNodeValue(cameraNode));
/*      */                 }
/*  833 */                 else if (name.equalsIgnoreCase("DeviceID"))
/*      */                 {
/*  835 */                   cameraInfo.setDeviceID(getNodeValue(cameraNode));
/*  836 */                 } else if (name.equalsIgnoreCase("ChannelNo"))
/*      */                 {
/*  838 */                   String value = getNodeValue(cameraNode);
/*  839 */                   if ((value != null) && (!value.equals(""))) {
/*  840 */                     cameraInfo.setChannelNo(parseInteger(value));
/*      */                   }
/*  842 */                 } else if (name.equalsIgnoreCase("Type"))
/*      */                 {
/*  844 */                   String value = getNodeValue(cameraNode);
/*  845 */                   if ((value != null) && (!value.equals(""))) {
/*  846 */                     cameraInfo.setType(parseInteger(value));
/*      */                   }
/*  848 */                 } else if (name.equalsIgnoreCase("IsOnline"))
/*      */                 {
/*  850 */                   String value = getNodeValue(cameraNode);
/*  851 */                   if ((value != null) && (!value.equals("")) && (value.equals("1"))) {
/*  852 */                     cameraInfo.setOnline(true);
/*      */                   } else {
/*  854 */                     cameraInfo.setOnline(false);
/*      */                   }
/*  856 */                 } else if (name.equalsIgnoreCase("IsPTZControl"))
/*      */                 {
/*  858 */                   String value = getNodeValue(cameraNode);
/*  859 */                   if ((value != null) && (!value.equals("")) && (value.equals("1"))) {
/*  860 */                     cameraInfo.setPTZControl(true);
/*      */                   } else {
/*  862 */                     cameraInfo.setPTZControl(false);
/*      */                   }
/*  864 */                 } else if (name.equalsIgnoreCase("UserCapability")) {
/*  865 */                   String value = getNodeValue(cameraNode);
/*  866 */                   if ((value != null) && (!value.equals(""))) {
/*  867 */                     cameraInfo.setUserCapability(getListFromString(value));
/*      */                   }
/*  869 */                 } else if (name.equalsIgnoreCase("RecordPos")) {
/*  870 */                   String value = getNodeValue(cameraNode);
/*  871 */                   if ((value != null) && (!value.equals(""))) {
/*  872 */                     cameraInfo.setRecordPos(getListFromString(value));
/*      */                   }
/*  874 */                 } else if (name.equalsIgnoreCase("ACSIP")) {
/*  875 */                   cameraInfo.setAcsIP(getNodeValue(cameraNode));
/*  876 */                 } else if (name.equalsIgnoreCase("ACSPort")) {
/*  877 */                   String value = getNodeValue(cameraNode);
/*  878 */                   if ((value != null) && (!value.equals(""))) {
/*  879 */                     cameraInfo.setAcsPort(parseInteger(value));
/*      */                   }
/*  881 */                 } else if (name.equalsIgnoreCase("CollectedFlag")) {
/*  882 */                   String value = getNodeValue(cameraNode);
/*  883 */                   if ((value != null) && (!value.equals(""))) {
/*  884 */                     cameraInfo.setCollectedFlag(Integer.valueOf(value).intValue());
/*      */                   }
/*  886 */                 } else if (name.equalsIgnoreCase("GroupID")) {
/*  887 */                   if ((getNodeValue(cameraNode) != null) && (!getNodeValue(cameraNode).equals(""))) {
/*  888 */                     cameraInfo.setGroupID(Integer.valueOf(getNodeValue(cameraNode)).intValue());
/*      */                   }
/*  890 */                 } else if (name.equalsIgnoreCase("CascadeFlag")) {
/*  891 */                   String value = getNodeValue(cameraNode);
/*  892 */                   if ((value != null) && (!value.equals(""))) {
/*  893 */                     cameraInfo.setCascadeFlag(Integer.valueOf(value).intValue());
/*      */                   }
/*      */                 }
/*  896 */                 else if (name.equalsIgnoreCase("DeviceIndexCode"))
/*      */                 {
/*  898 */                   String value = getNodeValue(cameraNode);
/*  899 */                   if ((value != null) && (!"".equals(value)))
/*      */                   {
/*  901 */                     cameraInfo.setDeviceIndexCode(value);
/*      */                   }
/*      */                 }
/*  904 */                 else if (name.equalsIgnoreCase("VoiceDataType"))
/*      */                 {
/*  906 */                   String value = getNodeValue(cameraNode);
/*  907 */                   if ((value != null) && (!"".equals(value)))
/*      */                   {
/*  909 */                     cameraInfo.setAudioEncodeType(Integer.parseInt(value));
/*      */                   }
/*      */                 }
/*  912 */                 else if (name.equalsIgnoreCase("Guid"))
/*      */                 {
/*  914 */                   String value = getNodeValue(cameraNode);
/*  915 */                   if ((value != null) && (!"".equals(value)))
/*      */                   {
/*  917 */                     cameraInfo.setGuid(value);
/*      */                   }
/*      */                 }
/*      */               }
/*  921 */               cameraList.add(cameraInfo);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  931 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static List<Integer> getListFromString(String value)
/*      */   {
/*  943 */     if (value == null) {
/*  944 */       return null;
/*      */     }
/*      */     
/*  947 */     String temp = value.replace("，", ",");
/*  948 */     String[] tempArray = temp.split(",");
/*  949 */     int size = tempArray.length;
/*  950 */     if (size <= 0) {
/*  951 */       return null;
/*      */     }
/*      */     
/*      */ 
/*  955 */     List<Integer> ret = new ArrayList();
/*  956 */     for (int i = 0; i < size; i++) {
/*      */       try {
/*  958 */         ret.add(Integer.valueOf(parseInteger(tempArray[i])));
/*      */       } catch (NumberFormatException e) {
/*  960 */         ret = null;
/*      */       }
/*      */     }
/*      */     
/*  964 */     return ret;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean parseDeviceInfo(String responseXML, DeviceInfo deviceInfo)
/*      */   {
/*  977 */     if ((responseXML == null) || (deviceInfo == null) || (responseXML.length() <= 0))
/*      */     {
/*  979 */       mError = 100;
/*  980 */       mErrorDesc = "input param error!";
/*  981 */       return false;
/*      */     }
/*      */     
/*  984 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseDeviceInfo() respone:" + responseXML);
/*      */     
/*      */ 
/*  987 */     Element root = getRootElement(responseXML);
/*  988 */     if (root == null) {
/*  989 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  993 */     boolean ret = parseStatus(root);
/*  994 */     if (!ret) {
/*  995 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  999 */     NodeList nodeList = root.getElementsByTagName("Params");
/* 1000 */     int size = nodeList.getLength();
/* 1001 */     if (size > 0) {
/* 1002 */       nodeList = nodeList.item(0).getChildNodes();
/* 1003 */       size = nodeList.getLength();
/* 1004 */       for (int i = 0; i < size; i++) {
/* 1005 */         Node node = nodeList.item(i);
/* 1006 */         if (node != null)
/*      */         {
/*      */ 
/* 1009 */           String nodeName = node.getNodeName();
/* 1010 */           if (nodeName.equalsIgnoreCase("Supplier"))
/*      */           {
/* 1012 */             deviceInfo.setSupplier(getNodeValue(node));
/* 1013 */           } else if (nodeName.equalsIgnoreCase("DeviceName")) {
/* 1014 */             deviceInfo.setDeviceName(getNodeValue(node));
/* 1015 */           } else if (nodeName.equalsIgnoreCase("IndexCode")) {
/* 1016 */             deviceInfo.setIndexCode(getNodeValue(node));
/* 1017 */           } else if (nodeName.equalsIgnoreCase("DeviceType"))
/*      */           {
/* 1019 */             deviceInfo.setDeviceType(getNodeValue(node));
/* 1020 */           } else if (nodeName.equalsIgnoreCase("DeviceIP"))
/*      */           {
/* 1022 */             deviceInfo.setDeviceIP(getNodeValue(node));
/* 1023 */           } else if (nodeName.equalsIgnoreCase("DevicePort"))
/*      */           {
/* 1025 */             String value = getNodeValue(node);
/* 1026 */             if ((value != null) && (!value.equals(""))) {
/* 1027 */               deviceInfo.setDevicePort(parseInteger(value));
/*      */             }
/* 1029 */           } else if (nodeName.equalsIgnoreCase("UserName"))
/*      */           {
/* 1031 */             deviceInfo.setLoginName(getNodeValue(node));
/* 1032 */           } else if (nodeName.equalsIgnoreCase("Password"))
/*      */           {
/* 1034 */             deviceInfo.setLoginPsw(getNodeValue(node));
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1040 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static String getNodeValue(Node node)
/*      */   {
/* 1052 */     if (node == null) {
/* 1053 */       return null;
/*      */     }
/*      */     
/* 1056 */     Node tmpNode = node.getFirstChild();
/* 1057 */     if (tmpNode == null) {
/* 1058 */       return null;
/*      */     }
/*      */     
/* 1061 */     return tmpNode.getNodeValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static int parseInteger(String value)
/*      */   {
/* 1073 */     int intValue = 0;
/*      */     try {
/* 1075 */       intValue = Integer.parseInt(value);
/*      */     } catch (NumberFormatException e) {
/* 1077 */       e.printStackTrace();
/*      */     }
/*      */     
/* 1080 */     return intValue;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean parseResultCode(String responseXML)
/*      */   {
/* 1093 */     if ((responseXML == null) || (responseXML.length() <= 0))
/*      */     {
/* 1095 */       mError = 100;
/* 1096 */       mErrorDesc = "input param error!";
/* 1097 */       return false;
/*      */     }
/*      */     
/* 1100 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseResultCode() respone:" + responseXML);
/*      */     
/*      */ 
/* 1103 */     Element root = getRootElement(responseXML);
/* 1104 */     if (root == null) {
/* 1105 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 1109 */     boolean ret = parseStatus(root);
/* 1110 */     if (!ret) {
/* 1111 */       return false;
/*      */     }
/*      */     
/* 1114 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean parseRealPlayURL(String responseXML, RealPlayURL realPlayURL)
/*      */   {
/* 1127 */     if ((responseXML == null) || (realPlayURL == null) || (responseXML.length() <= 0))
/*      */     {
/* 1129 */       mError = 100;
/* 1130 */       mErrorDesc = "input param error!";
/* 1131 */       return false;
/*      */     }
/*      */     
/* 1134 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseRealPlayURL() respone:" + responseXML);
/*      */     
/* 1136 */     CNetSDKLog.d("XMLParser", responseXML);
/*      */     
/*      */ 
/* 1139 */     Element root = getRootElement(responseXML);
/* 1140 */     if (root == null) {
/* 1141 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 1145 */     boolean ret = parseStatus(root);
/* 1146 */     if (!ret) {
/* 1147 */       return false;
/*      */     }
/*      */     
/* 1150 */     int strLen = responseXML.length();
/* 1151 */     int pos1 = responseXML.indexOf("<VTDUStreamURL>");
/* 1152 */     int pos2 = responseXML.indexOf("</VTDUStreamURL>");
/* 1153 */     if ((pos1 < 0) || (pos2 < 0) || (pos1 > strLen) || (pos2 > strLen) || (pos1 + 15 > pos2))
/*      */     {
/* 1155 */       realPlayURL.setUrl1("");
/*      */     } else {
/* 1157 */       realPlayURL.setUrl1(responseXML.substring(pos1 + 15, pos2).replaceAll("&amp;", "&"));
/*      */     }
/*      */     
/* 1160 */     pos1 = responseXML.indexOf("<MAGStreamURL>");
/* 1161 */     pos2 = responseXML.indexOf("</MAGStreamURL>");
/* 1162 */     if ((pos1 < 0) || (pos2 < 0) || (pos1 > strLen) || (pos2 > strLen) || (pos1 + 14 > pos2))
/*      */     {
/* 1164 */       realPlayURL.setUrl2("");
/*      */     } else {
/* 1166 */       realPlayURL.setUrl2(responseXML.substring(pos1 + 14, pos2).replaceAll("&amp;", "&"));
/*      */     }
/*      */     
/* 1169 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static boolean parseStatus(Element root)
/*      */   {
/* 1182 */     int statusCode = 200;
/* 1183 */     String statusDesc = null;
/* 1184 */     NodeList nodeList = root.getElementsByTagName("Status");
/* 1185 */     int size = nodeList.getLength();
/* 1186 */     if (size <= 0) {
/* 1187 */       return false;
/*      */     }
/* 1189 */     for (int i = 0; i < size; i++) {
/* 1190 */       Element element = (Element)nodeList.item(i);
/* 1191 */       String value = element.getFirstChild().getNodeValue();
/* 1192 */       if (value != null) {
/* 1193 */         statusCode = parseInteger(value);
/*      */       }
/*      */     }
/* 1196 */     setStatusCode(statusCode);
/*      */     
/* 1198 */     setLastErrorCode(statusCode);
/*      */     
/*      */ 
/* 1201 */     if ((statusCode != 200) && (statusCode != 20030) && (statusCode != 20031) && (statusCode != 20032) && (statusCode != 20042) && (statusCode != 20043) && (statusCode != 20044) && (statusCode != 20045))
/*      */     {
/*      */ 
/* 1204 */       nodeList = root.getElementsByTagName("Description");
/* 1205 */       size = nodeList.getLength();
/* 1206 */       for (int i = 0; i < size; i++)
/*      */       {
/* 1208 */         Element element = (Element)nodeList.item(i);
/* 1209 */         if (element != null) {
/* 1210 */           Node childNode = element.getFirstChild();
/* 1211 */           if (childNode != null) {
/* 1212 */             statusDesc = childNode.getNodeValue();
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1218 */       mErrorDesc = statusDesc;
/*      */       
/* 1220 */       return false;
/*      */     }
/* 1222 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */   private static void setStatusCode(int statusCode)
/*      */   {
/* 1228 */     status = statusCode;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static Element getRootElement(String responseXML)
/*      */   {
/* 1239 */     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/*      */     
/* 1241 */     DocumentBuilder builder = null;
/* 1242 */     Document document = null;
/*      */     try {
/* 1244 */       builder = factory.newDocumentBuilder();
/* 1245 */       InputStream is = String2InputStream(responseXML);
/* 1246 */       if (is == null)
/*      */       {
/* 1248 */         mError = 140;
/* 1249 */         mErrorDesc = "new datadoc obj fail, Maybe the xml doc format error";
/* 1250 */         return null;
/*      */       }
/*      */       try {
/* 1253 */         document = builder.parse(is);
/*      */       } catch (SAXException e) {
/* 1255 */         e.printStackTrace();
/*      */         
/*      */ 
/* 1258 */         mError = 140;
/* 1259 */         mErrorDesc = "new datadoc obj fail, Maybe the xml doc format error";
/* 1260 */         return null;
/*      */       } catch (IOException e) {
/* 1262 */         e.printStackTrace();
/*      */         
/*      */ 
/* 1265 */         mError = 140;
/* 1266 */         mErrorDesc = "new datadoc obj fail, Maybe the xml doc format error";
/* 1267 */         return null;
/*      */       } catch (IllegalStateException e) {
/* 1269 */         e.printStackTrace();
/* 1270 */         mError = 140;
/* 1271 */         mErrorDesc = "new datadoc obj fail, Maybe the xml doc format error";
/* 1272 */         return null;
/*      */       } catch (Exception e) {
/* 1274 */         e.printStackTrace();
/* 1275 */         mError = 140;
/* 1276 */         mErrorDesc = "new datadoc obj fail, Maybe the xml doc format error";
/* 1277 */         return null;
/*      */       }
/*      */     } catch (ParserConfigurationException e) {
/* 1280 */       e.printStackTrace();
/*      */       
/*      */ 
/* 1283 */       mError = 140;
/* 1284 */       mErrorDesc = "new datadoc obj fail, Maybe the xml doc format error";
/* 1285 */       return null;
/*      */     }
/*      */     
/*      */ 
/* 1289 */     Element root = document.getDocumentElement();
/* 1290 */     if (root == null)
/*      */     {
/* 1292 */       mError = 141;
/* 1293 */       mErrorDesc = "get root node fail, Maybe the xml doc format error";
/* 1294 */       return null;
/*      */     }
/* 1296 */     return root;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static InputStream String2InputStream(String src)
/*      */   {
/* 1308 */     ByteArrayInputStream stream = null;
/*      */     try {
/* 1310 */       stream = new ByteArrayInputStream(src.getBytes("utf-8"));
/*      */     } catch (UnsupportedEncodingException e) {
/* 1312 */       e.printStackTrace();
/* 1313 */       return null;
/*      */     }
/* 1315 */     return stream;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void setLastErrorCode(int statusCode)
/*      */   {
/* 1326 */     switch (statusCode) {
/*      */     case 200: 
/* 1328 */       mError = 0;
/* 1329 */       break;
/*      */     case 201: 
/* 1331 */       mError = 160;
/* 1332 */       break;
/*      */     case 205: 
/* 1334 */       mError = 161;
/* 1335 */       break;
/*      */     case 206: 
/* 1337 */       mError = 162;
/* 1338 */       break;
/*      */     case 220: 
/* 1340 */       mError = 163;
/* 1341 */       break;
/*      */     case 221: 
/* 1343 */       mError = 164;
/* 1344 */       break;
/*      */     case 222: 
/* 1346 */       mError = 165;
/* 1347 */       break;
/*      */     case 223: 
/* 1349 */       mError = 166;
/* 1350 */       break;
/*      */     case 224: 
/* 1352 */       mError = 167;
/* 1353 */       break;
/*      */     case 225: 
/* 1355 */       mError = 168;
/* 1356 */       break;
/*      */     case 226: 
/* 1358 */       mError = 169;
/* 1359 */       break;
/*      */     case 227: 
/* 1361 */       mError = 170;
/* 1362 */       break;
/*      */     case 228: 
/* 1364 */       mError = 171;
/* 1365 */       break;
/*      */     case 229: 
/* 1367 */       mError = 172;
/* 1368 */       break;
/*      */     case 230: 
/* 1370 */       mError = 230;
/* 1371 */       break;
/*      */     case 240: 
/* 1373 */       mError = 173;
/* 1374 */       break;
/*      */     case 241: 
/* 1376 */       mError = 174;
/* 1377 */       break;
/*      */     case 242: 
/* 1379 */       mError = 242;
/* 1380 */       break;
/*      */     case 243: 
/* 1382 */       mError = 175;
/* 1383 */       break;
/*      */     case 231: 
/* 1385 */       mError = 231;
/* 1386 */       break;
/*      */     case 207: 
/* 1388 */       mError = 207;
/* 1389 */       break;
/*      */     case 202: case 203: case 204: case 208: case 209: case 210: case 211: case 212: case 213: case 214: case 215: case 216: case 217: case 218: case 219: case 232: case 233: case 234: case 235: case 236: case 237: case 238: case 239: default: 
/* 1391 */       mError = 199;
/* 1392 */       mErrorDesc = "unknown error";
/*      */     }
/*      */     
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int getError()
/*      */   {
/* 1404 */     return mError;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String getErrorDesc()
/*      */   {
/* 1414 */     return mErrorDesc;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean parseRecordInfo(String responseXML, RecordInfo recordInfo)
/*      */   {
/* 1427 */     if ((responseXML == null) || (recordInfo == null) || (responseXML.length() <= 0))
/*      */     {
/* 1429 */       mError = 100;
/* 1430 */       mErrorDesc = "input param error!";
/* 1431 */       return false;
/*      */     }
/*      */     
/* 1434 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseRecordInfo() respone:" + responseXML);
/*      */     
/*      */ 
/* 1437 */     String afterFilterString = filterDecode(responseXML);
/*      */     
/*      */ 
/* 1440 */     Element root = getRootElement(afterFilterString);
/* 1441 */     if (root == null) {
/* 1442 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 1446 */     boolean ret = parseStatus(root);
/* 1447 */     if (!ret) {
/* 1448 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 1452 */     NodeList params = root.getElementsByTagName("Params");
/* 1453 */     int size = params.getLength();
/* 1454 */     if (size > 0) {
/* 1455 */       params = params.item(0).getChildNodes();
/* 1456 */       size = params.getLength();
/* 1457 */       for (int i = 0; i < size; i++) {
/* 1458 */         Node node = params.item(i);
/* 1459 */         if (node != null)
/*      */         {
/*      */ 
/*      */ 
/* 1463 */           String nodeName = node.getNodeName();
/* 1464 */           if (nodeName.equalsIgnoreCase("QueryType"))
/*      */           {
/* 1466 */             String value = getNodeValue(node);
/* 1467 */             if ((value != null) && (!value.equals(""))) {
/* 1468 */               recordInfo.queryType = parseInteger(value);
/*      */             }
/* 1470 */           } else if (nodeName.equalsIgnoreCase("bRecvAll"))
/*      */           {
/* 1472 */             String value = getNodeValue(node);
/* 1473 */             if ((value != null) && (!value.equals("")) && (value.equals("1"))) {
/* 1474 */               recordInfo.isRecvAll = true;
/*      */             } else {
/* 1476 */               recordInfo.isRecvAll = false;
/*      */             }
/* 1478 */           } else if (nodeName.equalsIgnoreCase("SegmentCount"))
/*      */           {
/* 1480 */             String value = getNodeValue(node);
/* 1481 */             if ((value != null) && (!value.equals(""))) {
/* 1482 */               recordInfo.segmentCount = parseInteger(value);
/*      */             }
/* 1484 */           } else if (nodeName.equalsIgnoreCase("SegmentListPlayUrl")) {
/* 1485 */             String value = "";
/* 1486 */             if (getNodeValue(node) != null)
/*      */             {
/* 1488 */               value = getNodeValue(node);
/*      */             }
/* 1490 */             if ((value != null) && (!value.equals(""))) {
/* 1491 */               recordInfo.segmentListPlayUrl = filterDollor(value);
/*      */             }
/*      */           }
/* 1494 */           else if (nodeName.equalsIgnoreCase("SegmentList")) {
/* 1495 */             NodeList segmentList = node.getChildNodes();
/* 1496 */             int segmentListNum = segmentList.getLength();
/* 1497 */             for (int j = 0; j < segmentListNum; j++) {
/* 1498 */               Node segmentListItem = segmentList.item(j);
/* 1499 */               if (segmentListItem != null)
/*      */               {
/*      */ 
/*      */ 
/* 1503 */                 String segmentListItemName = segmentListItem.getNodeName();
/* 1504 */                 if (segmentListItemName.equalsIgnoreCase("RecordSegment")) {
/* 1505 */                   NodeList recordSegmentItems = segmentListItem.getChildNodes();
/* 1506 */                   int recordSegmentItemNum = recordSegmentItems.getLength();
/* 1507 */                   RecordSegment recordSegment = new RecordSegment();
/* 1508 */                   for (int k = 0; k < recordSegmentItemNum; k++) {
/* 1509 */                     Node recordsegmentItem = recordSegmentItems.item(k);
/* 1510 */                     if (recordsegmentItem != null)
/*      */                     {
/*      */ 
/* 1513 */                       String segItemName = recordsegmentItem.getNodeName();
/* 1514 */                       if (segItemName.equalsIgnoreCase("BeginTime"))
/*      */                       {
/* 1516 */                         String value = getNodeValue(recordsegmentItem);
/* 1517 */                         if ((value != null) && (!value.equals(""))) {
/* 1518 */                           value = changeTimeFormat(value);
/* 1519 */                           recordSegment.setStartTime(string2ABSTime(value));
/*      */                         }
/*      */                       }
/* 1522 */                       else if (segItemName.equalsIgnoreCase("EndTime"))
/*      */                       {
/* 1524 */                         String value = getNodeValue(recordsegmentItem);
/* 1525 */                         if ((value != null) && (!value.equals(""))) {
/* 1526 */                           value = changeTimeFormat(value);
/* 1527 */                           recordSegment.setEndTime(string2ABSTime(value));
/*      */                         }
/*      */                       }
/* 1530 */                       else if (segItemName.equalsIgnoreCase("RecordType"))
/*      */                       {
/* 1532 */                         String value = getNodeValue(recordsegmentItem);
/* 1533 */                         if ((value != null) && (!value.equals(""))) {
/* 1534 */                           recordSegment.setRecordType(parseInteger(value));
/*      */                         }
/* 1536 */                       } else if (segItemName.equalsIgnoreCase("MediaDataLen"))
/*      */                       {
/* 1538 */                         String value = getNodeValue(recordsegmentItem);
/* 1539 */                         if ((value != null) && (!value.equals(""))) {
/* 1540 */                           recordSegment.setMediaDataLen(parseInteger(value));
/*      */                         }
/* 1542 */                       } else if (segItemName.equalsIgnoreCase("MetaDataLen"))
/*      */                       {
/* 1544 */                         String value = getNodeValue(recordsegmentItem);
/* 1545 */                         if ((value != null) && (!value.equals(""))) {
/* 1546 */                           recordSegment.setMetaDataLen(parseInteger(value));
/*      */                         }
/* 1548 */                       } else if (segItemName.equalsIgnoreCase("IsLocked"))
/*      */                       {
/* 1550 */                         String value = getNodeValue(recordsegmentItem);
/* 1551 */                         if ((value != null) && (value.equals("")) && (value.equals("1"))) {
/* 1552 */                           recordSegment.setLocked(true);
/*      */                         } else {
/* 1554 */                           recordSegment.setLocked(false);
/*      */                         }
/* 1556 */                       } else if (segItemName.equalsIgnoreCase("PlayURL")) {
/* 1557 */                         String value = "";
/* 1558 */                         if (getNodeValue(recordsegmentItem) != null)
/*      */                         {
/* 1560 */                           value = getNodeValue(recordsegmentItem);
/*      */                         }
/* 1562 */                         if ((value != null) && (!value.equals(""))) {
/* 1563 */                           recordSegment.setPlayUrl(filterDollor(value));
/*      */                         }
/*      */                       }
/*      */                     }
/*      */                   }
/* 1568 */                   recordInfo.recSegmentList.add(recordSegment);
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         } } }
/* 1574 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String changeTimeFormat(String str)
/*      */   {
/*      */     try
/*      */     {
/* 1586 */       if (str.contains("T")) {
/* 1587 */         Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.CHINA).parse(str);
/* 1588 */         return date.getTime() / 1000L + "";
/*      */       }
/* 1590 */       return str;
/*      */     }
/*      */     catch (Exception ex) {}
/* 1593 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static ABS_TIME string2ABSTime(String value)
/*      */   {
/* 1606 */     if ((value == null) || (value.equalsIgnoreCase(""))) {
/* 1607 */       return null;
/*      */     }
/*      */     
/*      */ 
/* 1611 */     Calendar time = Calendar.getInstance();
/* 1612 */     long lTime = 0L;
/*      */     try {
/* 1614 */       lTime = Long.parseLong(value) * 1000L;
/* 1615 */       time.setTimeInMillis(lTime);
/*      */     } catch (NumberFormatException e) {
/* 1617 */       return null;
/*      */     }
/*      */     
/* 1620 */     ABS_TIME absTime = new ABS_TIME(time);
/*      */     
/* 1622 */     return absTime;
/*      */   }
/*      */   
/*      */   private static String filterDecode(String url) {
/* 1626 */     if (url == null) {
/* 1627 */       return "";
/*      */     }
/*      */     
/* 1630 */     url = url.replaceAll("&amp;", ";amp;");
/* 1631 */     return url;
/*      */   }
/*      */   
/*      */   private static String filterDollor(String url) {
/* 1635 */     if (url == null) {
/* 1636 */       return "";
/*      */     }
/*      */     
/* 1639 */     url = url.replaceAll(";amp;", "&");
/* 1640 */     return url;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean parseGISCameraListInfo(String responseXML, List<GISCameraInfo> gisCameraList)
/*      */   {
/* 1652 */     if ((responseXML == null) || (gisCameraList == null) || (responseXML.length() <= 0))
/*      */     {
/* 1654 */       mError = 100;
/* 1655 */       mErrorDesc = "input param error!";
/* 1656 */       return false;
/*      */     }
/*      */     
/* 1659 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseGISCameraListInfo() respone:" + responseXML);
/*      */     
/*      */ 
/* 1662 */     Element root = getRootElement(responseXML);
/* 1663 */     if (root == null) {
/* 1664 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 1668 */     boolean ret = parseStatus(root);
/* 1669 */     if (!ret) {
/* 1670 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 1674 */     NodeList nodeList = root.getElementsByTagName("Params");
/* 1675 */     int size = nodeList.getLength();
/* 1676 */     if (size <= 0) {
/* 1677 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 1681 */     gisCameraList.clear();
/*      */     
/*      */ 
/* 1684 */     nodeList = nodeList.item(0).getChildNodes();
/* 1685 */     size = nodeList.getLength();
/* 1686 */     for (int i = 0; i < size; i++) {
/* 1687 */       Node node = nodeList.item(i);
/* 1688 */       if (node != null)
/*      */       {
/*      */ 
/* 1691 */         String nodeName = node.getNodeName();
/* 1692 */         if (nodeName != null)
/*      */         {
/*      */ 
/* 1695 */           if (!nodeName.equalsIgnoreCase("CameraNum"))
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/* 1700 */             if (nodeName.equalsIgnoreCase("CameraList"))
/*      */             {
/* 1702 */               NodeList childNodeList = node.getChildNodes();
/* 1703 */               int childNodeSize = childNodeList.getLength();
/* 1704 */               for (int j = 0; j < childNodeSize; j++) {
/* 1705 */                 Node childNode = childNodeList.item(j);
/* 1706 */                 if (childNode != null)
/*      */                 {
/*      */ 
/* 1709 */                   String childNodeName = childNode.getNodeName();
/* 1710 */                   if (childNodeName != null)
/*      */                   {
/*      */ 
/* 1713 */                     if (childNodeName.equalsIgnoreCase("Camera"))
/*      */                     {
/* 1715 */                       GISCameraInfo cameraInfo = new GISCameraInfo();
/* 1716 */                       NodeList cameras = childNode.getChildNodes();
/* 1717 */                       int cameraSize = cameras.getLength();
/* 1718 */                       for (int k = 0; k < cameraSize; k++) {
/* 1719 */                         Node cameraNode = cameras.item(k);
/* 1720 */                         if (cameraNode != null)
/*      */                         {
/*      */ 
/* 1723 */                           String name = cameraNode.getNodeName();
/* 1724 */                           if (name != null)
/*      */                           {
/*      */ 
/* 1727 */                             if (name.equalsIgnoreCase("ID"))
/*      */                             {
/* 1729 */                               cameraInfo.setCameraID(getNodeValue(cameraNode));
/* 1730 */                             } else if (name.equalsIgnoreCase("Name"))
/*      */                             {
/* 1732 */                               cameraInfo.setName(getNodeValue(cameraNode));
/* 1733 */                             } else if (name.equalsIgnoreCase("Type"))
/*      */                             {
/* 1735 */                               String value = getNodeValue(cameraNode);
/* 1736 */                               if ((null != value) && (!value.equals(""))) {
/* 1737 */                                 cameraInfo.setType(Integer.valueOf(value).intValue());
/*      */                               }
/* 1739 */                             } else if (name.equalsIgnoreCase("DeviceID")) {
/* 1740 */                               cameraInfo.setDeviceID(getNodeValue(cameraNode));
/* 1741 */                             } else if (name.equalsIgnoreCase("DeviceName")) {
/* 1742 */                               cameraInfo.setDeviceName(getNodeValue(cameraNode));
/* 1743 */                             } else if (name.equalsIgnoreCase("PointType")) {
/* 1744 */                               String value = getNodeValue(cameraNode);
/* 1745 */                               if ((null != value) && (!value.equals(""))) {
/* 1746 */                                 cameraInfo.setPointType(Integer.valueOf(value).intValue());
/*      */                               }
/* 1748 */                             } else if (name.equalsIgnoreCase("IsOnline"))
/*      */                             {
/* 1750 */                               String value = getNodeValue(cameraNode);
/* 1751 */                               if ((value != null) && (!value.equals("")) && (value.equals("1"))) {
/* 1752 */                                 cameraInfo.setOnline(true);
/*      */                               } else {
/* 1754 */                                 cameraInfo.setOnline(false);
/*      */                               }
/* 1756 */                             } else if (name.equalsIgnoreCase("UserCapability")) {
/* 1757 */                               String value = getNodeValue(cameraNode);
/* 1758 */                               if ((null != value) && (!value.equals(""))) {
/* 1759 */                                 cameraInfo.setUserCapability(getListFromString(value));
/*      */                               }
/* 1761 */                             } else if (name.equalsIgnoreCase("Longitude")) {
/* 1762 */                               cameraInfo.setLongitude(getNodeValue(cameraNode));
/* 1763 */                             } else if (name.equalsIgnoreCase("Latitude")) {
/* 1764 */                               cameraInfo.setLatitude(getNodeValue(cameraNode));
/*      */                             } }
/*      */                         } }
/* 1767 */                       gisCameraList.add(cameraInfo);
/*      */                     }
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 1777 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean parseCameraInfoEx(String responseXML, CameraInfoEx cameraInfoEx) {
/* 1781 */     if ((responseXML == null) || (cameraInfoEx == null) || (responseXML.length() <= 0))
/*      */     {
/* 1783 */       mError = 100;
/* 1784 */       mErrorDesc = "input param error!";
/* 1785 */       return false;
/*      */     }
/* 1787 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseCameraInfoEx() respone:" + responseXML);
/*      */     
/*      */ 
/* 1790 */     Element root = getRootElement(responseXML);
/* 1791 */     if (root == null) {
/* 1792 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 1796 */     boolean ret = parseStatus(root);
/* 1797 */     if (!ret) {
/* 1798 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 1802 */     NodeList nodeList = root.getElementsByTagName("Camera");
/* 1803 */     int size = nodeList.getLength();
/* 1804 */     if (size > 0) {
/* 1805 */       nodeList = nodeList.item(0).getChildNodes();
/* 1806 */       size = nodeList.getLength();
/* 1807 */       for (int i = 0; i < size; i++) {
/* 1808 */         Node cameraNode = nodeList.item(i);
/* 1809 */         if (cameraNode != null)
/*      */         {
/*      */ 
/*      */ 
/* 1813 */           String name = cameraNode.getNodeName();
/* 1814 */           if (name.equalsIgnoreCase("ID"))
/*      */           {
/* 1816 */             cameraInfoEx.setId(getNodeValue(cameraNode));
/* 1817 */           } else if (name.equalsIgnoreCase("Name"))
/*      */           {
/* 1819 */             cameraInfoEx.setName(getNodeValue(cameraNode));
/* 1820 */           } else if (name.equalsIgnoreCase("DeviceID"))
/*      */           {
/* 1822 */             cameraInfoEx.setDeviceId(getNodeValue(cameraNode));
/* 1823 */           } else if (name.equalsIgnoreCase("ChannelNo"))
/*      */           {
/* 1825 */             String value = getNodeValue(cameraNode);
/* 1826 */             if ((value != null) && (!value.equals(""))) {
/* 1827 */               cameraInfoEx.setChannelNo(parseInteger(value));
/*      */             }
/* 1829 */           } else if (name.equalsIgnoreCase("Type"))
/*      */           {
/* 1831 */             String value = getNodeValue(cameraNode);
/* 1832 */             if ((value != null) && (!value.equals(""))) {
/* 1833 */               cameraInfoEx.setType(parseInteger(value));
/*      */             }
/* 1835 */           } else if (name.equalsIgnoreCase("IsOnline"))
/*      */           {
/* 1837 */             String value = getNodeValue(cameraNode);
/* 1838 */             if ((value != null) && (!value.equals("")) && (value.equals("1"))) {
/* 1839 */               cameraInfoEx.setOnline(true);
/*      */             } else {
/* 1841 */               cameraInfoEx.setOnline(false);
/*      */             }
/* 1843 */           } else if (name.equalsIgnoreCase("IsPTZControl"))
/*      */           {
/* 1845 */             String value = getNodeValue(cameraNode);
/* 1846 */             if ((value != null) && (!value.equals("")) && (value.equals("1"))) {
/* 1847 */               cameraInfoEx.setPTZControl(true);
/*      */             } else {
/* 1849 */               cameraInfoEx.setPTZControl(false);
/*      */             }
/* 1851 */           } else if (name.equalsIgnoreCase("UserCapability")) {
/* 1852 */             String value = getNodeValue(cameraNode);
/* 1853 */             if ((value != null) && (!value.equals(""))) {
/* 1854 */               cameraInfoEx.setUserCapability(getListFromString(value));
/*      */             }
/* 1856 */           } else if (name.equalsIgnoreCase("RecordPos")) {
/* 1857 */             String value = getNodeValue(cameraNode);
/* 1858 */             if ((value != null) && (!value.equals(""))) {
/* 1859 */               cameraInfoEx.setRecordPos(getListFromString(value));
/*      */             }
/* 1861 */           } else if (name.equalsIgnoreCase("ACSIP")) {
/* 1862 */             cameraInfoEx.setAcsIP(getNodeValue(cameraNode));
/* 1863 */           } else if (name.equalsIgnoreCase("ACSPort")) {
/* 1864 */             String value = getNodeValue(cameraNode);
/* 1865 */             if ((value != null) && (!value.equals(""))) {
/* 1866 */               cameraInfoEx.setAcsPort(parseInteger(value));
/*      */             }
/* 1868 */           } else if (name.equalsIgnoreCase("CollectedFlag")) {
/* 1869 */             String value = getNodeValue(cameraNode);
/* 1870 */             if ((value != null) && (!value.equals(""))) {
/* 1871 */               cameraInfoEx.setCollectedFlag(Integer.valueOf(value).intValue());
/*      */             }
/* 1873 */           } else if (name.equalsIgnoreCase("GroupID")) {
/* 1874 */             if ((getNodeValue(cameraNode) != null) && (!getNodeValue(cameraNode).equals(""))) {
/* 1875 */               cameraInfoEx.setGroupId(Integer.valueOf(getNodeValue(cameraNode)).intValue());
/*      */             }
/* 1877 */           } else if (name.equalsIgnoreCase("CascadeFlag")) {
/* 1878 */             String value = getNodeValue(cameraNode);
/* 1879 */             if ((value != null) && (!value.equals(""))) {
/* 1880 */               cameraInfoEx.setCascadeFlag(Integer.valueOf(value).intValue());
/*      */             }
/*      */           }
/* 1883 */           else if (name.equalsIgnoreCase("DeviceIndexCode"))
/*      */           {
/* 1885 */             String value = getNodeValue(cameraNode);
/* 1886 */             if ((value != null) && (!"".equals(value)))
/*      */             {
/* 1888 */               cameraInfoEx.setDeviceIndexCode(value);
/*      */             }
/*      */           }
/* 1891 */           else if (name.equalsIgnoreCase("VoiceDataType"))
/*      */           {
/* 1893 */             String value = getNodeValue(cameraNode);
/* 1894 */             if ((null != value) && (!"".equals(value)))
/*      */             {
/* 1896 */               cameraInfoEx.setAudioEncodeType(Integer.valueOf(value).intValue());
/*      */             }
/*      */           }
/* 1899 */           else if (name.equalsIgnoreCase("Longitude")) {
/* 1900 */             cameraInfoEx.setLongitude(getNodeValue(cameraNode));
/* 1901 */           } else if (name.equalsIgnoreCase("Latitude")) {
/* 1902 */             cameraInfoEx.setLatitude(getNodeValue(cameraNode));
/*      */           }
/* 1904 */           else if (name.equalsIgnoreCase("DeviceNetID"))
/*      */           {
/* 1906 */             cameraInfoEx.setDeviceNetId(Integer.parseInt(getNodeValue(cameraNode)));
/*      */           }
/*      */         }
/*      */       } }
/* 1910 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean parseGISTrackListInfo(String responseXML, List<GISTrackPoint> trackList)
/*      */   {
/* 1922 */     if ((responseXML == null) || (trackList == null) || (responseXML.length() <= 0)) {
/* 1923 */       mError = 100;
/* 1924 */       mErrorDesc = "input param error!";
/* 1925 */       return false;
/*      */     }
/*      */     
/* 1928 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseGISTrackListInfo() respone:" + responseXML);
/*      */     
/* 1930 */     Element root = getRootElement(responseXML);
/* 1931 */     if (root == null) {
/* 1932 */       return false;
/*      */     }
/* 1934 */     boolean ret = parseStatus(root);
/* 1935 */     if (!ret) {
/* 1936 */       return false;
/*      */     }
/* 1938 */     NodeList nodeList = root.getElementsByTagName("Params");
/* 1939 */     int size = nodeList.getLength();
/* 1940 */     if (size <= 0) {
/* 1941 */       return false;
/*      */     }
/* 1943 */     trackList.clear();
/*      */     
/*      */ 
/* 1946 */     nodeList = nodeList.item(0).getChildNodes();
/* 1947 */     size = nodeList.getLength();
/* 1948 */     for (int i = 0; i < size; i++) {
/* 1949 */       Node node = nodeList.item(i);
/* 1950 */       String nodeName = node.getNodeName();
/* 1951 */       if (!nodeName.equalsIgnoreCase("Num"))
/*      */       {
/* 1953 */         if (nodeName.equalsIgnoreCase("GPS")) {
/* 1954 */           GISTrackPoint gisTrackInfo = new GISTrackPoint();
/* 1955 */           NodeList tracks = node.getChildNodes();
/* 1956 */           int trackNum = tracks.getLength();
/* 1957 */           for (int k = 0; k < trackNum; k++) {
/* 1958 */             Node cameraNode = tracks.item(k);
/* 1959 */             if (cameraNode != null)
/*      */             {
/*      */ 
/*      */ 
/* 1963 */               String name = cameraNode.getNodeName();
/* 1964 */               if ((name != null) && (!name.equalsIgnoreCase("")))
/*      */               {
/*      */ 
/*      */ 
/* 1968 */                 if (name.equalsIgnoreCase("DeviceID")) {
/* 1969 */                   gisTrackInfo.setDeviceID(getNodeValue(cameraNode));
/* 1970 */                 } else if (name.equalsIgnoreCase("Longitude")) {
/* 1971 */                   gisTrackInfo.setLongitude(getNodeValue(cameraNode));
/* 1972 */                 } else if (name.equalsIgnoreCase("Latitude")) {
/* 1973 */                   gisTrackInfo.setLatitude(getNodeValue(cameraNode));
/* 1974 */                 } else if (name.equalsIgnoreCase("Speed")) {
/* 1975 */                   gisTrackInfo.setSpeed(getNodeValue(cameraNode));
/* 1976 */                 } else if (name.equalsIgnoreCase("Direction")) {
/* 1977 */                   gisTrackInfo.setDirection(getNodeValue(cameraNode));
/* 1978 */                 } else if (name.equalsIgnoreCase("GpsTime"))
/* 1979 */                   gisTrackInfo.setGpsTime(getNodeValue(cameraNode)); }
/*      */             }
/*      */           }
/* 1982 */           trackList.add(gisTrackInfo);
/*      */         }
/*      */       }
/*      */     }
/* 1986 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String parseGISPointType(String responseXML)
/*      */   {
/* 1998 */     if ((responseXML == null) || (responseXML.length() <= 0)) {
/* 1999 */       mError = 100;
/* 2000 */       mErrorDesc = "input param error!";
/* 2001 */       return null;
/*      */     }
/*      */     
/* 2004 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseGISPointType() respone:" + responseXML);
/*      */     
/* 2006 */     Element root = getRootElement(responseXML);
/* 2007 */     if (root == null) {
/* 2008 */       return null;
/*      */     }
/* 2010 */     boolean ret = parseStatus(root);
/* 2011 */     if (!ret) {
/* 2012 */       return null;
/*      */     }
/* 2014 */     NodeList nodeList = root.getElementsByTagName("PointType");
/* 2015 */     int size = nodeList.getLength();
/* 2016 */     if (size <= 0) {
/* 2017 */       return null;
/*      */     }
/*      */     
/* 2020 */     String stringType = null;
/* 2021 */     Node node = nodeList.item(0);
/* 2022 */     if (node != null) {
/* 2023 */       stringType = getNodeValue(node);
/*      */     } else {
/* 2025 */       return null;
/*      */     }
/* 2027 */     return stringType;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean parseUnMsgCount(String responseXML, SDKUnBullentinCount msgCount)
/*      */   {
/* 2039 */     if ((responseXML == null) || (msgCount == null) || (responseXML.equals("")))
/*      */     {
/* 2041 */       mError = 100;
/* 2042 */       mErrorDesc = "input param error!";
/* 2043 */       return false;
/*      */     }
/*      */     
/* 2046 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseUnMsgCount() respone:" + responseXML);
/*      */     
/*      */ 
/* 2049 */     Element root = getRootElement(responseXML);
/* 2050 */     if (root == null) {
/* 2051 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 2055 */     boolean ret = parseStatus(root);
/* 2056 */     if (!ret) {
/* 2057 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 2061 */     NodeList nodeList = root.getElementsByTagName("Params");
/* 2062 */     int size = nodeList.getLength();
/* 2063 */     if (size > 0) {
/* 2064 */       nodeList = nodeList.item(0).getChildNodes();
/* 2065 */       size = nodeList.getLength();
/* 2066 */       for (int i = 0; i < size; i++) {
/* 2067 */         Node node = nodeList.item(i);
/* 2068 */         if (node != null)
/*      */         {
/*      */ 
/* 2071 */           String nodeName = node.getNodeName();
/* 2072 */           if (nodeName.equalsIgnoreCase("TotalCount")) {
/* 2073 */             String value = getNodeValue(node);
/* 2074 */             if (value != null) {
/* 2075 */               msgCount.setTotalCount(parseInteger(value));
/*      */             }
/* 2077 */           } else if (nodeName.equalsIgnoreCase("SMCount")) {
/* 2078 */             String value = getNodeValue(node);
/* 2079 */             if (value != null) {
/* 2080 */               msgCount.setSmCount(parseInteger(value));
/*      */             }
/* 2082 */           } else if (nodeName.equalsIgnoreCase("PMCount")) {
/* 2083 */             String value = getNodeValue(node);
/* 2084 */             if (value != null) {
/* 2085 */               msgCount.setPmCount(parseInteger(value));
/*      */             }
/* 2087 */           } else if (nodeName.equalsIgnoreCase("TMCount")) {
/* 2088 */             String value = getNodeValue(node);
/* 2089 */             if (value != null) {
/* 2090 */               msgCount.setTmCount(parseInteger(value));
/*      */             }
/* 2092 */           } else if (nodeName.equalsIgnoreCase("AMCount")) {
/* 2093 */             String value = getNodeValue(node);
/* 2094 */             if (value != null) {
/* 2095 */               msgCount.setAmCount(parseInteger(value));
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 2101 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean parseBulletinList(String responseXML, List<SDKBulletin> bullentinList) {
/* 2105 */     if ((responseXML == null) || (bullentinList == null) || (responseXML.equals("")))
/*      */     {
/* 2107 */       mError = 100;
/* 2108 */       mErrorDesc = "input param error!";
/* 2109 */       return false;
/*      */     }
/* 2111 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseBulletinList() respone:" + responseXML);
/*      */     
/*      */ 
/* 2114 */     Element root = getRootElement(responseXML);
/* 2115 */     if (root == null) {
/* 2116 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 2120 */     boolean ret = parseStatus(root);
/* 2121 */     if (!ret) {
/* 2122 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 2126 */     bullentinList.clear();
/*      */     
/*      */ 
/* 2129 */     NodeList nodeList = root.getElementsByTagName("Params");
/* 2130 */     int size = nodeList.getLength();
/* 2131 */     if (size <= 0) {
/* 2132 */       return false;
/*      */     }
/* 2134 */     nodeList = nodeList.item(0).getChildNodes();
/* 2135 */     size = nodeList.getLength();
/* 2136 */     for (int i = 0; i < size; i++) {
/* 2137 */       Node node = nodeList.item(i);
/* 2138 */       String nodeName = node.getNodeName();
/* 2139 */       if (nodeName.equalsIgnoreCase("MessageList"))
/*      */       {
/* 2141 */         NodeList childNodeList = node.getChildNodes();
/* 2142 */         int childNodeSize = childNodeList.getLength();
/* 2143 */         for (int j = 0; j < childNodeSize; j++) {
/* 2144 */           Node childNode = childNodeList.item(j);
/* 2145 */           String childNodeName = childNode.getNodeName();
/* 2146 */           if (childNodeName.equalsIgnoreCase("Message"))
/*      */           {
/* 2148 */             SDKBulletin bullent = new SDKBulletin();
/* 2149 */             NodeList bullentUnits = childNode.getChildNodes();
/* 2150 */             int bullentUnitSize = bullentUnits.getLength();
/* 2151 */             for (int k = 0; k < bullentUnitSize; k++) {
/* 2152 */               Node bullentUnitNode = bullentUnits.item(k);
/* 2153 */               String name = bullentUnitNode.getNodeName();
/* 2154 */               if (name.equalsIgnoreCase("ID")) {
/* 2155 */                 bullent.setId(getNodeValue(bullentUnitNode));
/* 2156 */               } else if (name.equalsIgnoreCase("Type")) {
/* 2157 */                 bullent.setType(getNodeValue(bullentUnitNode));
/* 2158 */               } else if (name.equalsIgnoreCase("TypeDescribe")) {
/* 2159 */                 bullent.setTypeDescribe(getNodeValue(bullentUnitNode));
/* 2160 */               } else if (name.equalsIgnoreCase("Title")) {
/* 2161 */                 bullent.setTitle(getNodeValue(bullentUnitNode));
/* 2162 */               } else if (name.equalsIgnoreCase("IsChecked")) {
/* 2163 */                 String value = getNodeValue(bullentUnitNode);
/* 2164 */                 if ((value != null) && (value.equals("0"))) {
/* 2165 */                   bullent.setChecked(false);
/*      */                 } else {
/* 2167 */                   bullent.setChecked(true);
/*      */                 }
/* 2169 */               } else if (name.equalsIgnoreCase("CreateTime")) {
/* 2170 */                 String value = getNodeValue(bullentUnitNode);
/* 2171 */                 if ((value != null) && (!value.equals(""))) {
/*      */                   try {
/* 2173 */                     bullent.setCreateTime(Long.valueOf(value).longValue());
/*      */                   }
/*      */                   catch (NumberFormatException localNumberFormatException) {}
/*      */                 }
/*      */               }
/*      */             }
/*      */             
/* 2180 */             bullentinList.add(bullent);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2190 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean parseAlarmBulletinDetailList(String responseXML, List<SDKAlarmBulletinDetail> alarmbltnDetailList)
/*      */   {
/* 2203 */     if ((responseXML == null) || (alarmbltnDetailList == null) || (responseXML.equals("")))
/*      */     {
/* 2205 */       mError = 100;
/* 2206 */       mErrorDesc = "input param error!";
/* 2207 */       return false;
/*      */     }
/*      */     
/* 2210 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseAlarmBulletinDetailList() respone:" + responseXML);
/*      */     
/*      */ 
/* 2213 */     Element root = getRootElement(responseXML);
/* 2214 */     if (root == null) {
/* 2215 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 2219 */     boolean ret = parseStatus(root);
/* 2220 */     if (!ret) {
/* 2221 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 2225 */     alarmbltnDetailList.clear();
/*      */     
/*      */ 
/* 2228 */     NodeList nodeList = root.getElementsByTagName("Params");
/* 2229 */     int size = nodeList.getLength();
/* 2230 */     if (size <= 0) {
/* 2231 */       return false;
/*      */     }
/* 2233 */     nodeList = nodeList.item(0).getChildNodes();
/* 2234 */     size = nodeList.getLength();
/* 2235 */     for (int i = 0; i < size; i++) {
/* 2236 */       Node node = nodeList.item(i);
/* 2237 */       String nodeName = node.getNodeName();
/* 2238 */       if (nodeName.equalsIgnoreCase("MessageDetailList"))
/*      */       {
/* 2240 */         NodeList childNodeList = node.getChildNodes();
/* 2241 */         int childNodeSize = childNodeList.getLength();
/* 2242 */         for (int j = 0; j < childNodeSize; j++) {
/* 2243 */           Node childNode = childNodeList.item(j);
/* 2244 */           String childNodeName = childNode.getNodeName();
/* 2245 */           if (childNodeName.equalsIgnoreCase("MessageDetail"))
/*      */           {
/* 2247 */             SDKAlarmBulletinDetail alarmBltnDetail = new SDKAlarmBulletinDetail();
/* 2248 */             NodeList bullentUnits = childNode.getChildNodes();
/* 2249 */             int bullentUnitSize = bullentUnits.getLength();
/* 2250 */             for (int k = 0; k < bullentUnitSize; k++) {
/* 2251 */               Node bullentUnitNode = bullentUnits.item(k);
/* 2252 */               String name = bullentUnitNode.getNodeName();
/* 2253 */               if (name.equalsIgnoreCase("ID")) {
/* 2254 */                 alarmBltnDetail.setId(getNodeValue(bullentUnitNode));
/* 2255 */               } else if (name.equalsIgnoreCase("Type")) {
/* 2256 */                 alarmBltnDetail.setType(getNodeValue(bullentUnitNode));
/* 2257 */               } else if (name.equalsIgnoreCase("TypeDescribe")) {
/* 2258 */                 alarmBltnDetail.setTypeDescribe(getNodeValue(bullentUnitNode));
/* 2259 */               } else if (name.equalsIgnoreCase("Title")) {
/* 2260 */                 alarmBltnDetail.setTitle(getNodeValue(bullentUnitNode));
/* 2261 */               } else if (name.equalsIgnoreCase("Content")) {
/* 2262 */                 alarmBltnDetail.setContent(getNodeValue(bullentUnitNode));
/* 2263 */               } else if (name.equalsIgnoreCase("IsChecked")) {
/* 2264 */                 String value = getNodeValue(bullentUnitNode);
/* 2265 */                 if ((value != null) && (value.equals("0"))) {
/* 2266 */                   alarmBltnDetail.setChecked(false);
/*      */                 } else {
/* 2268 */                   alarmBltnDetail.setChecked(true);
/*      */                 }
/* 2270 */               } else if (name.equalsIgnoreCase("CreateTime")) {
/* 2271 */                 String value = getNodeValue(bullentUnitNode);
/* 2272 */                 if ((value != null) && (!value.equals(""))) {
/*      */                   try {
/* 2274 */                     alarmBltnDetail.setCreateTime(Long.valueOf(value).longValue());
/*      */                   }
/*      */                   catch (NumberFormatException localNumberFormatException) {}
/*      */                 }
/* 2278 */               } else if (name.equalsIgnoreCase("CameraID")) {
/* 2279 */                 alarmBltnDetail.setCameraID(getNodeValue(bullentUnitNode));
/* 2280 */               } else if (name.equalsIgnoreCase("PictureUrl")) {
/* 2281 */                 alarmBltnDetail.setPictureUrl(getNodeValue(bullentUnitNode));
/* 2282 */               } else if (name.equalsIgnoreCase("Longitude")) {
/* 2283 */                 String value = getNodeValue(bullentUnitNode);
/* 2284 */                 if ((value != null) && (!value.equals(""))) {
/*      */                   try {
/* 2286 */                     alarmBltnDetail.setLongitude(Double.valueOf(value).doubleValue());
/*      */                   }
/*      */                   catch (NumberFormatException localNumberFormatException1) {}
/*      */                 }
/* 2290 */               } else if (name.equalsIgnoreCase("Latitude")) {
/* 2291 */                 String value = getNodeValue(bullentUnitNode);
/* 2292 */                 if ((value != null) && (!value.equals(""))) {
/*      */                   try {
/* 2294 */                     alarmBltnDetail.setLatitude(Double.valueOf(value).doubleValue());
/*      */                   }
/*      */                   catch (NumberFormatException localNumberFormatException2) {}
/*      */                 }
/*      */               }
/*      */             }
/*      */             
/* 2301 */             alarmbltnDetailList.add(alarmBltnDetail);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2311 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean parseBulletinDetailList(String responseXML, List<SDKBulletinDetail> bltnDetailList) {
/* 2315 */     if ((responseXML == null) || (bltnDetailList == null) || (responseXML.equals("")))
/*      */     {
/* 2317 */       mError = 100;
/* 2318 */       mErrorDesc = "input param error!";
/* 2319 */       return false;
/*      */     }
/*      */     
/* 2322 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseBulletinDetailList() respone:" + responseXML);
/*      */     
/*      */ 
/* 2325 */     Element root = getRootElement(responseXML);
/* 2326 */     if (root == null) {
/* 2327 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 2331 */     boolean ret = parseStatus(root);
/* 2332 */     if (!ret) {
/* 2333 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 2337 */     bltnDetailList.clear();
/*      */     
/*      */ 
/* 2340 */     NodeList nodeList = root.getElementsByTagName("Params");
/* 2341 */     int size = nodeList.getLength();
/* 2342 */     if (size <= 0) {
/* 2343 */       return false;
/*      */     }
/* 2345 */     nodeList = nodeList.item(0).getChildNodes();
/* 2346 */     size = nodeList.getLength();
/* 2347 */     for (int i = 0; i < size; i++) {
/* 2348 */       Node node = nodeList.item(i);
/* 2349 */       String nodeName = node.getNodeName();
/* 2350 */       if (nodeName.equalsIgnoreCase("MessageDetailList"))
/*      */       {
/* 2352 */         NodeList childNodeList = node.getChildNodes();
/* 2353 */         int childNodeSize = childNodeList.getLength();
/* 2354 */         for (int j = 0; j < childNodeSize; j++) {
/* 2355 */           Node childNode = childNodeList.item(j);
/* 2356 */           String childNodeName = childNode.getNodeName();
/* 2357 */           if (childNodeName.equalsIgnoreCase("MessageDetail"))
/*      */           {
/* 2359 */             SDKBulletinDetail bltnDetail = new SDKBulletinDetail();
/* 2360 */             NodeList bullentUnits = childNode.getChildNodes();
/* 2361 */             int bullentUnitSize = bullentUnits.getLength();
/* 2362 */             for (int k = 0; k < bullentUnitSize; k++) {
/* 2363 */               Node bullentUnitNode = bullentUnits.item(k);
/* 2364 */               String name = bullentUnitNode.getNodeName();
/* 2365 */               if (name.equalsIgnoreCase("ID")) {
/* 2366 */                 bltnDetail.setId(getNodeValue(bullentUnitNode));
/* 2367 */               } else if (name.equalsIgnoreCase("Type")) {
/* 2368 */                 bltnDetail.setType(getNodeValue(bullentUnitNode));
/* 2369 */               } else if (name.equalsIgnoreCase("TypeDescribe")) {
/* 2370 */                 bltnDetail.setTypeDescribe(getNodeValue(bullentUnitNode));
/* 2371 */               } else if (name.equalsIgnoreCase("Title")) {
/* 2372 */                 bltnDetail.setTitle(getNodeValue(bullentUnitNode));
/* 2373 */               } else if (name.equalsIgnoreCase("Content")) {
/* 2374 */                 bltnDetail.setContent(getNodeValue(bullentUnitNode));
/* 2375 */               } else if (name.equalsIgnoreCase("IsChecked")) {
/* 2376 */                 String value = getNodeValue(bullentUnitNode);
/* 2377 */                 if ((value != null) && (value.equals("0"))) {
/* 2378 */                   bltnDetail.setChecked(false);
/*      */                 } else {
/* 2380 */                   bltnDetail.setChecked(true);
/*      */                 }
/* 2382 */               } else if (name.equalsIgnoreCase("CreateTime")) {
/* 2383 */                 String value = getNodeValue(bullentUnitNode);
/* 2384 */                 if ((value != null) && (!value.equals(""))) {
/*      */                   try {
/* 2386 */                     bltnDetail.setCreateTime(Long.valueOf(value).longValue());
/*      */                   }
/*      */                   catch (NumberFormatException localNumberFormatException) {}
/*      */                 }
/*      */               }
/*      */             }
/*      */             
/* 2393 */             bltnDetailList.add(bltnDetail);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2403 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean parseBulletinDetail(String responseXML, SDKBulletinDetail bltnDetail)
/*      */   {
/* 2415 */     if ((responseXML == null) || (bltnDetail == null) || (responseXML.equals("")))
/*      */     {
/* 2417 */       mError = 100;
/* 2418 */       mErrorDesc = "input param error!";
/* 2419 */       return false;
/*      */     }
/*      */     
/* 2422 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseBulletinDetail() respone:" + responseXML);
/*      */     
/*      */ 
/* 2425 */     Element root = getRootElement(responseXML);
/* 2426 */     if (root == null) {
/* 2427 */       CNetSDKLog.e("XMLParser", "VMSNetSDK::parseBulletinDetail() root == null");
/* 2428 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 2432 */     boolean ret = parseStatus(root);
/* 2433 */     if (!ret) {
/* 2434 */       CNetSDKLog.e("XMLParser", "VMSNetSDK::parseBulletinDetail() parseStatus fail");
/* 2435 */       return false;
/*      */     }
/*      */     
/* 2438 */     NodeList nodeList = root.getElementsByTagName("MessageDetail");
/* 2439 */     if (nodeList != null) {
/* 2440 */       Node node = nodeList.item(0);
/*      */       
/* 2442 */       NodeList childNodeList = node.getChildNodes();
/* 2443 */       int childNodeSize = childNodeList.getLength();
/* 2444 */       for (int j = 0; j < childNodeSize; j++) {
/* 2445 */         Node childNode = childNodeList.item(j);
/* 2446 */         String name = childNode.getNodeName();
/* 2447 */         if (name.equalsIgnoreCase("ID")) {
/* 2448 */           bltnDetail.setId(getNodeValue(childNode));
/* 2449 */         } else if (name.equalsIgnoreCase("Type")) {
/* 2450 */           bltnDetail.setType(getNodeValue(childNode));
/* 2451 */         } else if (name.equalsIgnoreCase("TypeDescribe")) {
/* 2452 */           bltnDetail.setTypeDescribe(getNodeValue(childNode));
/* 2453 */         } else if (name.equalsIgnoreCase("Title")) {
/* 2454 */           bltnDetail.setTitle(getNodeValue(childNode));
/* 2455 */         } else if (name.equalsIgnoreCase("Content")) {
/* 2456 */           bltnDetail.setContent(getNodeValue(childNode));
/* 2457 */         } else if (name.equalsIgnoreCase("IsChecked")) {
/* 2458 */           String value = getNodeValue(childNode);
/* 2459 */           if ((value != null) && (value.equals("0"))) {
/* 2460 */             bltnDetail.setChecked(false);
/*      */           } else {
/* 2462 */             bltnDetail.setChecked(true);
/*      */           }
/* 2464 */         } else if (name.equalsIgnoreCase("CreateTime")) {
/* 2465 */           String value = getNodeValue(childNode);
/* 2466 */           if ((value != null) && (!value.equals(""))) {
/*      */             try {
/* 2468 */               bltnDetail.setCreateTime(Long.valueOf(value).longValue());
/*      */             }
/*      */             catch (NumberFormatException localNumberFormatException) {}
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 2477 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean parseAlarmBulletinDetail(String responseXML, SDKAlarmBulletinDetail alarmBltnDetail)
/*      */   {
/* 2489 */     if ((responseXML == null) || (alarmBltnDetail == null) || (responseXML.equals("")))
/*      */     {
/* 2491 */       mError = 100;
/* 2492 */       mErrorDesc = "input param error!";
/* 2493 */       return false;
/*      */     }
/* 2495 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseAlarmBulletinDetail() respone:" + responseXML);
/*      */     
/*      */ 
/* 2498 */     Element root = getRootElement(responseXML);
/* 2499 */     if (root == null) {
/* 2500 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 2504 */     boolean ret = parseStatus(root);
/* 2505 */     if (!ret) {
/* 2506 */       return false;
/*      */     }
/*      */     
/* 2509 */     NodeList nodeList = root.getElementsByTagName("MessageDetail");
/* 2510 */     if (nodeList != null) {
/* 2511 */       Node node = nodeList.item(0);
/* 2512 */       NodeList childNodeList = node.getChildNodes();
/* 2513 */       int childNodeSize = childNodeList.getLength();
/* 2514 */       for (int j = 0; j < childNodeSize; j++) {
/* 2515 */         Node childNode = childNodeList.item(j);
/* 2516 */         String name = childNode.getNodeName();
/* 2517 */         if (name.equalsIgnoreCase("ID")) {
/* 2518 */           alarmBltnDetail.setId(getNodeValue(childNode));
/* 2519 */         } else if (name.equalsIgnoreCase("Type")) {
/* 2520 */           alarmBltnDetail.setType(getNodeValue(childNode));
/* 2521 */         } else if (name.equalsIgnoreCase("TypeDescribe")) {
/* 2522 */           alarmBltnDetail.setTypeDescribe(getNodeValue(childNode));
/* 2523 */         } else if (name.equalsIgnoreCase("Title")) {
/* 2524 */           alarmBltnDetail.setTitle(getNodeValue(childNode));
/* 2525 */         } else if (name.equalsIgnoreCase("Content")) {
/* 2526 */           alarmBltnDetail.setContent(getNodeValue(childNode));
/* 2527 */         } else if (name.equalsIgnoreCase("IsChecked")) {
/* 2528 */           String value = getNodeValue(childNode);
/* 2529 */           if ((value != null) && (value.equals("0"))) {
/* 2530 */             alarmBltnDetail.setChecked(false);
/*      */           } else {
/* 2532 */             alarmBltnDetail.setChecked(true);
/*      */           }
/* 2534 */         } else if (name.equalsIgnoreCase("CreateTime")) {
/* 2535 */           String value = getNodeValue(childNode);
/* 2536 */           if ((value != null) && (!value.equals(""))) {
/*      */             try {
/* 2538 */               alarmBltnDetail.setCreateTime(Long.valueOf(value).longValue());
/*      */             }
/*      */             catch (NumberFormatException localNumberFormatException) {}
/*      */           }
/* 2542 */         } else if (name.equalsIgnoreCase("CameraID")) {
/* 2543 */           alarmBltnDetail.setCameraID(getNodeValue(childNode));
/* 2544 */         } else if (name.equalsIgnoreCase("PictureUrl")) {
/* 2545 */           String pictureUrl = getNodeValue(childNode);
/* 2546 */           if ((!TextUtils.isEmpty(pictureUrl)) && (pictureUrl.contains("&amp;"))) {
/* 2547 */             pictureUrl = pictureUrl.replace("&amp;", "&");
/*      */           }
/* 2549 */           alarmBltnDetail.setPictureUrl(pictureUrl);
/* 2550 */         } else if (name.equalsIgnoreCase("Longitude")) {
/* 2551 */           String value = getNodeValue(childNode);
/* 2552 */           if ((value != null) && (!value.equals(""))) {
/*      */             try {
/* 2554 */               alarmBltnDetail.setLongitude(Double.valueOf(value).doubleValue());
/*      */             }
/*      */             catch (NumberFormatException localNumberFormatException1) {}
/*      */           }
/* 2558 */         } else if (name.equalsIgnoreCase("Latitude")) {
/* 2559 */           String value = getNodeValue(childNode);
/* 2560 */           if ((value != null) && (!value.equals(""))) {
/*      */             try {
/* 2562 */               alarmBltnDetail.setLatitude(Double.valueOf(value).doubleValue());
/*      */ 
/*      */             }
/*      */             catch (NumberFormatException localNumberFormatException2) {}
/*      */           }
/*      */         }
/* 2568 */         else if (name.equalsIgnoreCase("LinkageCameraList")) {
/* 2569 */           EventInfo eventInfo = new EventInfo();
/* 2570 */           NodeList nodes = root.getElementsByTagName("EventInfo");
/* 2571 */           int nodeLength = nodes.getLength();
/* 2572 */           for (int i = 0; i < nodeLength; i++) {
/* 2573 */             Node nodeLinkage = nodes.item(i);
/* 2574 */             String nodeLinkageName = nodeLinkage.getNodeName();
/* 2575 */             if ("ExtInfo".equalsIgnoreCase(nodeLinkageName)) {
/* 2576 */               String extInfo = nodeLinkage.getNodeValue();
/* 2577 */               eventInfo.setExtInfo(extInfo);
/* 2578 */             } else if ("TriggerList".equalsIgnoreCase(nodeLinkageName)) {
/* 2579 */               NodeList linkageList = nodeLinkage.getChildNodes();
/* 2580 */               List<Trigger> triggers = parseLinkageCamera(linkageList);
/* 2581 */               eventInfo.setTriggerList(triggers);
/*      */             }
/*      */           }
/* 2584 */           alarmBltnDetail.setLinkageCameraList(eventInfo);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 2589 */     return true;
/*      */   }
/*      */   
/*      */   private static List<Trigger> parseLinkageCamera(NodeList nodeList)
/*      */   {
/* 2594 */     if (nodeList == null) {
/* 2595 */       return null;
/*      */     }
/* 2597 */     List<Trigger> triggers = new ArrayList();
/* 2598 */     int linkageCameraSize = nodeList.getLength();
/* 2599 */     for (int i = 0; i < linkageCameraSize; i++) {
/* 2600 */       Node node = nodeList.item(i);
/* 2601 */       String nodeName = node.getNodeName();
/* 2602 */       Trigger trigger = new Trigger();
/* 2603 */       if ("Type".equalsIgnoreCase(nodeName)) {
/* 2604 */         String type = getNodeValue(node);
/* 2605 */         trigger.setType(Integer.parseInt(type));
/* 2606 */       } else if ("Index".equalsIgnoreCase(nodeName)) {
/* 2607 */         String index = getNodeValue(node);
/* 2608 */         trigger.setIndex(index);
/* 2609 */       } else if ("Pictrueurl".equalsIgnoreCase(nodeName)) {
/* 2610 */         String pictureUrl = getNodeValue(node);
/* 2611 */         if ((TextUtils.isEmpty(pictureUrl)) && (pictureUrl.contains("&amp;"))) {
/* 2612 */           pictureUrl = pictureUrl.replace("&amp;", "&");
/*      */         }
/* 2614 */         trigger.setPictureUrl(pictureUrl);
/*      */       }
/* 2616 */       triggers.add(trigger);
/*      */     }
/* 2618 */     return triggers;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean parseGisInfo(String responseXML, SDKGISInitInfo sdkGISInitInfo)
/*      */   {
/* 2630 */     if ((responseXML == null) || (sdkGISInitInfo == null) || (responseXML.equals("")))
/*      */     {
/* 2632 */       mError = 100;
/* 2633 */       mErrorDesc = "input param error!";
/* 2634 */       return false;
/*      */     }
/*      */     
/* 2637 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseGisInfo() respone:" + responseXML);
/*      */     
/*      */ 
/* 2640 */     Element root = getRootElement(responseXML);
/* 2641 */     if (root == null) {
/* 2642 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 2646 */     boolean ret = parseStatus(root);
/* 2647 */     if (!ret) {
/* 2648 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 2652 */     NodeList nodeList = root.getElementsByTagName("Params");
/* 2653 */     int size = nodeList.getLength();
/* 2654 */     if (size > 0) {
/* 2655 */       nodeList = nodeList.item(0).getChildNodes();
/* 2656 */       size = nodeList.getLength();
/* 2657 */       for (int i = 0; i < size; i++) {
/* 2658 */         Node node = nodeList.item(i);
/* 2659 */         if (node != null)
/*      */         {
/*      */ 
/* 2662 */           String nodeName = node.getNodeName();
/* 2663 */           if (nodeName.equalsIgnoreCase("Gis")) {
/* 2664 */             NodeList bullentUnits = node.getChildNodes();
/* 2665 */             int bullentUnitSize = bullentUnits.getLength();
/* 2666 */             for (int k = 0; k < bullentUnitSize; k++) {
/* 2667 */               Node bullentUnitNode = bullentUnits.item(k);
/* 2668 */               String name = bullentUnitNode.getNodeName();
/* 2669 */               if (name.equalsIgnoreCase("MapType")) {
/* 2670 */                 String value = getNodeValue(bullentUnitNode);
/* 2671 */                 if ((value != null) && (!value.equals(""))) {
/*      */                   try {
/* 2673 */                     sdkGISInitInfo.sdkGIS.setMapType(Integer.valueOf(value).intValue());
/*      */                   }
/*      */                   catch (NumberFormatException localNumberFormatException) {}
/*      */                 }
/* 2677 */               } else if (name.equalsIgnoreCase("MapName")) {
/* 2678 */                 sdkGISInitInfo.sdkGIS.setMapName(getNodeValue(bullentUnitNode));
/* 2679 */               } else if (name.equalsIgnoreCase("InitLat")) {
/* 2680 */                 String value = getNodeValue(bullentUnitNode);
/* 2681 */                 if ((value != null) && (!value.equals(""))) {
/*      */                   try {
/* 2683 */                     sdkGISInitInfo.sdkGIS.setInitLat(Double.valueOf(value).doubleValue());
/*      */                   }
/*      */                   catch (NumberFormatException localNumberFormatException1) {}
/*      */                 }
/* 2687 */               } else if (name.equalsIgnoreCase("InitLng")) {
/* 2688 */                 String value = getNodeValue(bullentUnitNode);
/* 2689 */                 if ((value != null) && (!value.equals(""))) {
/*      */                   try {
/* 2691 */                     sdkGISInitInfo.sdkGIS.setInitLng(Double.valueOf(value).doubleValue());
/*      */                   }
/*      */                   catch (NumberFormatException localNumberFormatException2) {}
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/* 2698 */           else if (nodeName.equalsIgnoreCase("Arcgis")) {
/* 2699 */             NodeList bullentUnits = node.getChildNodes();
/* 2700 */             int bullentUnitSize = bullentUnits.getLength();
/* 2701 */             for (int k = 0; k < bullentUnitSize; k++) {
/* 2702 */               Node bullentUnitNode = bullentUnits.item(k);
/* 2703 */               String name = bullentUnitNode.getNodeName();
/* 2704 */               if (name.equalsIgnoreCase("ImageUrl")) {
/* 2705 */                 sdkGISInitInfo.sdkArcGIS.setImageUrl(getNodeValue(bullentUnitNode));
/* 2706 */               } else if (name.equalsIgnoreCase("VectorUrl")) {
/* 2707 */                 sdkGISInitInfo.sdkArcGIS.setVectorUrl(getNodeValue(bullentUnitNode));
/* 2708 */               } else if (name.equalsIgnoreCase("ReverseUrl")) {
/* 2709 */                 sdkGISInitInfo.sdkArcGIS.setReverseUrl(getNodeValue(bullentUnitNode));
/* 2710 */               } else if (name.equalsIgnoreCase("NameAttr")) {
/* 2711 */                 sdkGISInitInfo.sdkArcGIS.setNameAttr(getNodeValue(bullentUnitNode));
/* 2712 */               } else if (name.equalsIgnoreCase("GeometryServiceUrl")) {
/* 2713 */                 sdkGISInitInfo.sdkArcGIS.setGeometryServiceUrl(getNodeValue(bullentUnitNode));
/* 2714 */               } else if (name.equalsIgnoreCase("RouteUrl")) {
/* 2715 */                 sdkGISInitInfo.sdkArcGIS.setRouteUrl(getNodeValue(bullentUnitNode));
/* 2716 */               } else if (name.equalsIgnoreCase("Xmin")) {
/* 2717 */                 String value = getNodeValue(bullentUnitNode);
/* 2718 */                 if ((value != null) && (!value.equals(""))) {
/*      */                   try {
/* 2720 */                     sdkGISInitInfo.sdkArcGIS.setxMin(Double.valueOf(value).doubleValue());
/*      */                   }
/*      */                   catch (NumberFormatException localNumberFormatException3) {}
/*      */                 }
/* 2724 */               } else if (name.equalsIgnoreCase("Ymin")) {
/* 2725 */                 String value = getNodeValue(bullentUnitNode);
/* 2726 */                 if ((value != null) && (!value.equals(""))) {
/*      */                   try {
/* 2728 */                     sdkGISInitInfo.sdkArcGIS.setyMin(Double.valueOf(value).doubleValue());
/*      */                   }
/*      */                   catch (NumberFormatException localNumberFormatException4) {}
/*      */                 }
/* 2732 */               } else if (name.equalsIgnoreCase("Xmax")) {
/* 2733 */                 String value = getNodeValue(bullentUnitNode);
/* 2734 */                 if ((value != null) && (!value.equals(""))) {
/*      */                   try {
/* 2736 */                     sdkGISInitInfo.sdkArcGIS.setxMax(Double.valueOf(value).doubleValue());
/*      */                   }
/*      */                   catch (NumberFormatException localNumberFormatException5) {}
/*      */                 }
/* 2740 */               } else if (name.equalsIgnoreCase("Ymax")) {
/* 2741 */                 String value = getNodeValue(bullentUnitNode);
/* 2742 */                 if ((value != null) && (!value.equals(""))) {
/*      */                   try {
/* 2744 */                     sdkGISInitInfo.sdkArcGIS.setyMax(Double.valueOf(value).doubleValue());
/*      */                   }
/*      */                   catch (NumberFormatException localNumberFormatException6) {}
/*      */                 }
/* 2748 */               } else if (name.equalsIgnoreCase("Wkid")) {
/* 2749 */                 sdkGISInitInfo.sdkArcGIS.setwKid(getNodeValue(bullentUnitNode));
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 2757 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean parseRecordInfo_ex(String strResponse, RecordInfo recordInfo) {
/* 2761 */     if ((strResponse == null) || (recordInfo == null) || (strResponse.length() <= 0))
/*      */     {
/* 2763 */       mError = 100;
/* 2764 */       mErrorDesc = "input param error!";
/* 2765 */       return false;
/*      */     }
/*      */     
/* 2768 */     String responseXML = "";
/*      */     try {
/* 2770 */       responseXML = AESUtil.decrypt("12345678", strResponse);
/*      */     } catch (Exception e) {
/* 2772 */       e.printStackTrace();
/*      */     }
/*      */     
/*      */ 
/* 2776 */     Element root = getRootElement(responseXML);
/* 2777 */     if (root == null) {
/* 2778 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 2782 */     boolean ret = parseStatus(root);
/* 2783 */     if (!ret) {
/* 2784 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 2788 */     NodeList params = root.getElementsByTagName("Params");
/* 2789 */     int size = params.getLength();
/* 2790 */     if (size > 0) {
/* 2791 */       params = params.item(0).getChildNodes();
/* 2792 */       size = params.getLength();
/* 2793 */       for (int i = 0; i < size; i++) {
/* 2794 */         Node node = params.item(i);
/* 2795 */         if (node != null)
/*      */         {
/*      */ 
/*      */ 
/* 2799 */           String nodeName = node.getNodeName();
/* 2800 */           if (nodeName.equalsIgnoreCase("QueryType"))
/*      */           {
/* 2802 */             String value = getNodeValue(node);
/* 2803 */             if ((value != null) && (!value.equals(""))) {
/* 2804 */               recordInfo.queryType = parseInteger(value);
/*      */             }
/* 2806 */           } else if (nodeName.equalsIgnoreCase("bRecvAll"))
/*      */           {
/* 2808 */             String value = getNodeValue(node);
/* 2809 */             if ((value != null) && (!value.equals("")) && (value.equals("1"))) {
/* 2810 */               recordInfo.isRecvAll = true;
/*      */             } else {
/* 2812 */               recordInfo.isRecvAll = false;
/*      */             }
/* 2814 */           } else if (nodeName.equalsIgnoreCase("SegmentCount"))
/*      */           {
/* 2816 */             String value = getNodeValue(node);
/* 2817 */             if ((value != null) && (!value.equals(""))) {
/* 2818 */               recordInfo.segmentCount = parseInteger(value);
/*      */             }
/* 2820 */           } else if (nodeName.equalsIgnoreCase("SegmentListPlayUrl")) {
/* 2821 */             String value = "";
/* 2822 */             if (getNodeValue(node) != null)
/*      */             {
/* 2824 */               value = getNodeValue(node);
/*      */             }
/* 2826 */             if ((value != null) && (!value.equals(""))) {
/* 2827 */               recordInfo.segmentListPlayUrl = filterDollor(value);
/*      */             }
/*      */           }
/* 2830 */           else if (nodeName.equalsIgnoreCase("SegmentList")) {
/* 2831 */             NodeList segmentList = node.getChildNodes();
/* 2832 */             int segmentListNum = segmentList.getLength();
/* 2833 */             for (int j = 0; j < segmentListNum; j++) {
/* 2834 */               Node segmentListItem = segmentList.item(j);
/* 2835 */               if (segmentListItem != null)
/*      */               {
/*      */ 
/*      */ 
/* 2839 */                 String segmentListItemName = segmentListItem.getNodeName();
/* 2840 */                 if (segmentListItemName.equalsIgnoreCase("RecordSegment")) {
/* 2841 */                   NodeList recordSegmentItems = segmentListItem.getChildNodes();
/* 2842 */                   int recordSegmentItemNum = recordSegmentItems.getLength();
/* 2843 */                   RecordSegment recordSegment = new RecordSegment();
/* 2844 */                   for (int k = 0; k < recordSegmentItemNum; k++) {
/* 2845 */                     Node recordsegmentItem = recordSegmentItems.item(k);
/* 2846 */                     if (recordsegmentItem != null)
/*      */                     {
/*      */ 
/* 2849 */                       String segItemName = recordsegmentItem.getNodeName();
/* 2850 */                       if (segItemName.equalsIgnoreCase("BeginTime"))
/*      */                       {
/* 2852 */                         String value = getNodeValue(recordsegmentItem);
/* 2853 */                         if ((value != null) && (!value.equals(""))) {
/* 2854 */                           recordSegment.setStartTime(string2ABSTime(value));
/*      */                         }
/*      */                         
/*      */                       }
/* 2858 */                       else if (segItemName.equalsIgnoreCase("EndTime"))
/*      */                       {
/* 2860 */                         String value = getNodeValue(recordsegmentItem);
/* 2861 */                         if ((value != null) && (!value.equals(""))) {
/* 2862 */                           recordSegment.setEndTime(string2ABSTime(value));
/*      */                         }
/*      */                         
/*      */                       }
/* 2866 */                       else if (segItemName.equalsIgnoreCase("RecordType"))
/*      */                       {
/* 2868 */                         String value = getNodeValue(recordsegmentItem);
/* 2869 */                         if ((value != null) && (!value.equals(""))) {
/* 2870 */                           recordSegment.setRecordType(parseInteger(value));
/*      */                         }
/* 2872 */                       } else if (segItemName.equalsIgnoreCase("MediaDataLen"))
/*      */                       {
/* 2874 */                         String value = getNodeValue(recordsegmentItem);
/* 2875 */                         if ((value != null) && (!value.equals(""))) {
/* 2876 */                           recordSegment.setMediaDataLen(parseInteger(value));
/*      */                         }
/* 2878 */                       } else if (segItemName.equalsIgnoreCase("MetaDataLen"))
/*      */                       {
/* 2880 */                         String value = getNodeValue(recordsegmentItem);
/* 2881 */                         if ((value != null) && (!value.equals(""))) {
/* 2882 */                           recordSegment.setMetaDataLen(parseInteger(value));
/*      */                         }
/* 2884 */                       } else if (segItemName.equalsIgnoreCase("IsLocked"))
/*      */                       {
/* 2886 */                         String value = getNodeValue(recordsegmentItem);
/* 2887 */                         if ((value != null) && (value.equals("")) && (value.equals("1"))) {
/* 2888 */                           recordSegment.setLocked(true);
/*      */                         } else {
/* 2890 */                           recordSegment.setLocked(false);
/*      */                         }
/* 2892 */                       } else if (segItemName.equalsIgnoreCase("PlayURL")) {
/* 2893 */                         String value = "";
/* 2894 */                         if (getNodeValue(recordsegmentItem) != null)
/*      */                         {
/* 2896 */                           value = getNodeValue(recordsegmentItem);
/*      */                         }
/* 2898 */                         if ((value != null) && (!value.equals(""))) {
/* 2899 */                           recordSegment.setPlayUrl(filterDollor(value));
/*      */                         }
/*      */                       }
/*      */                     }
/*      */                   }
/* 2904 */                   recordInfo.recSegmentList.add(recordSegment);
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         } } }
/* 2910 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean parseLatestApp(String xml, LatestAppResult result)
/*      */   {
/* 2923 */     CNetSDKLog.d("XMLParser", "parseLatestApp() xml is " + xml);
/* 2924 */     XmlPullParserFactory factory = null;
/*      */     try
/*      */     {
/* 2927 */       factory = XmlPullParserFactory.newInstance();
/* 2928 */       factory.setNamespaceAware(true);
/* 2929 */       XmlPullParser xmlPullParser = factory.newPullParser();
/* 2930 */       InputStream in = new ByteArrayInputStream(xml.getBytes());
/* 2931 */       xmlPullParser.setInput(in, "utf-8");
/* 2932 */       int eventType = xmlPullParser.getEventType();
/* 2933 */       String nodeName = "";
/* 2934 */       while (eventType != 1)
/*      */       {
/* 2936 */         nodeName = xmlPullParser.getName();
/* 2937 */         switch (eventType)
/*      */         {
/*      */         case 2: 
/* 2940 */           if ("Status".equalsIgnoreCase(nodeName))
/*      */           {
/* 2942 */             String status = xmlPullParser.nextText();
/* 2943 */             result.setResultCode(getIntFromString(status));
/*      */           }
/* 2945 */           else if ("Description".equals(nodeName))
/*      */           {
/* 2947 */             String description = xmlPullParser.nextText();
/* 2948 */             result.setResultDesrc(description);
/*      */           }
/* 2950 */           else if ("AppInfo".equals(nodeName))
/*      */           {
/* 2952 */             PlugInfo plugInfo = parseAppInfo(xmlPullParser);
/* 2953 */             if (plugInfo != null)
/*      */             {
/* 2955 */               result.setAppInfo(plugInfo); }
/*      */           }
/* 2957 */           break;
/*      */         }
/*      */         
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 2964 */         eventType = xmlPullParser.next();
/*      */       }
/*      */     }
/*      */     catch (XmlPullParserException e)
/*      */     {
/* 2969 */       result.setResultCode(999);
/* 2970 */       e.printStackTrace();
/* 2971 */       return false;
/*      */     }
/*      */     catch (IOException e)
/*      */     {
/* 2975 */       result.setResultCode(999);
/* 2976 */       e.printStackTrace();
/* 2977 */       return false;
/*      */     }
/*      */     catch (NumberFormatException e)
/*      */     {
/* 2981 */       result.setResultCode(999);
/* 2982 */       e.printStackTrace();
/* 2983 */       return false;
/*      */     }
/*      */     catch (NullPointerException e)
/*      */     {
/* 2987 */       result.setResultCode(999);
/* 2988 */       e.printStackTrace();
/* 2989 */       return false;
/*      */     }
/*      */     
/* 2992 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static PlugInfo parseAppInfo(XmlPullParser xmlPullParser)
/*      */   {
/* 3004 */     PlugInfo result = new PlugInfo();
/*      */     try
/*      */     {
/* 3007 */       xmlPullParser.require(2, null, "AppInfo");
/* 3008 */       int eventType = xmlPullParser.getEventType();
/* 3009 */       String nodeName = "";
/* 3010 */       while (eventType != 1)
/*      */       {
/* 3012 */         nodeName = xmlPullParser.getName();
/* 3013 */         switch (eventType)
/*      */         {
/*      */         case 2: 
/* 3016 */           CNetSDKLog.d("XMLParser", "parseAppInfo() START_TAG is " + nodeName);
/* 3017 */           if ("ID".equals(nodeName))
/*      */           {
/* 3019 */             String id = xmlPullParser.nextText();
/* 3020 */             CNetSDKLog.d("XMLParser", "parseAppInfo() id is " + id);
/* 3021 */             result.setId(id);
/*      */           }
/* 3023 */           else if ("PackageName".equals(nodeName))
/*      */           {
/* 3025 */             String packageName = xmlPullParser.nextText();
/* 3026 */             CNetSDKLog.d("XMLParser", "parseAppInfo() packageName is " + packageName);
/* 3027 */             result.setPackagename(packageName);
/*      */           }
/* 3029 */           else if ("DisplayName".equals(nodeName))
/*      */           {
/* 3031 */             String displayName = xmlPullParser.nextText();
/* 3032 */             CNetSDKLog.d("XMLParser", "parseAppInfo() displayName is " + displayName);
/* 3033 */             result.setAppname(displayName);
/*      */           }
/* 3035 */           else if ("Icon".equals(nodeName))
/*      */           {
/* 3037 */             String icon = xmlPullParser.nextText();
/* 3038 */             CNetSDKLog.d("XMLParser", "parseAppInfo() icon is " + icon);
/* 3039 */             result.setLogo(icon);
/*      */           }
/* 3041 */           else if ("VersionCode".equals(nodeName))
/*      */           {
/* 3043 */             String versionCode = xmlPullParser.nextText();
/* 3044 */             CNetSDKLog.d("XMLParser", "parseAppInfo() VersionCode is " + versionCode);
/* 3045 */             result.setVersioncode(versionCode);
/*      */           }
/* 3047 */           else if ("VersionName".equals(nodeName))
/*      */           {
/* 3049 */             String versionName = xmlPullParser.nextText();
/* 3050 */             CNetSDKLog.d("XMLParser", "parseAppInfo() VersionName is " + versionName);
/* 3051 */             result.setVersionname(versionName);
/*      */           }
/* 3053 */           else if ("Address".equals(nodeName))
/*      */           {
/* 3055 */             String address = xmlPullParser.nextText();
/* 3056 */             CNetSDKLog.d("XMLParser", "parseAppInfo() Address is " + address);
/* 3057 */             result.setAppDownLoadUrl(address);
/*      */           }
/* 3059 */           else if ("PlugfileMD5 ".equals(nodeName))
/*      */           {
/* 3061 */             String fileMd5 = xmlPullParser.nextText();
/* 3062 */             CNetSDKLog.d("XMLParser", "parseAppInfo() PlugfileMD5 is " + fileMd5);
/* 3063 */             result.setPlugfileMD5(fileMd5);
/*      */           }
/* 3065 */           else if ("UpdateInfo".equals(nodeName))
/*      */           {
/* 3067 */             String updateInfo = xmlPullParser.nextText();
/* 3068 */             CNetSDKLog.d("XMLParser", "parseAppInfo() UpdateInfo is " + updateInfo);
/* 3069 */             result.setUpdateInfo(updateInfo); }
/* 3070 */           break;
/*      */         
/*      */ 
/*      */         case 3: 
/* 3074 */           if ("AppInfo".equals(nodeName))
/*      */           {
/* 3076 */             return result;
/*      */           }
/*      */           break;
/*      */         }
/* 3080 */         eventType = xmlPullParser.next();
/*      */       }
/*      */     }
/*      */     catch (XmlPullParserException e)
/*      */     {
/* 3085 */       e.printStackTrace();
/*      */     }
/*      */     catch (IOException e)
/*      */     {
/* 3089 */       e.printStackTrace();
/*      */     }
/*      */     catch (NumberFormatException e)
/*      */     {
/* 3093 */       e.printStackTrace();
/*      */     }
/* 3095 */     return result;
/*      */   }
/*      */   
/*      */   private static int getIntFromString(String str) {
/* 3099 */     int temp = 0;
/*      */     try
/*      */     {
/* 3102 */       temp = Integer.parseInt(str);
/*      */     }
/*      */     catch (NumberFormatException e)
/*      */     {
/* 3106 */       CNetSDKLog.e("XMLParser", "getIntFromString error");
/* 3107 */       return temp;
/*      */     }
/* 3109 */     return temp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean parseServerConfig(String xmlServerConfig, ServerConfig serverConfig)
/*      */   {
/* 3125 */     if ((TextUtils.isEmpty(xmlServerConfig)) || (serverConfig == null))
/*      */     {
/* 3127 */       mError = 100;
/* 3128 */       mErrorDesc = "input param error!";
/* 3129 */       return false;
/*      */     }
/* 3131 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseServerConfig() respone:" + xmlServerConfig);
/*      */     
/*      */ 
/* 3134 */     Element root = getRootElement(xmlServerConfig);
/* 3135 */     if (root == null) {
/* 3136 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 3140 */     boolean ret = parseStatus(root);
/* 3141 */     if (!ret) {
/* 3142 */       return false;
/*      */     }
/*      */     
/* 3145 */     NodeList nodeList = root.getElementsByTagName("Params");
/* 3146 */     if (nodeList.getLength() < 0) {
/* 3147 */       return false;
/*      */     }
/*      */     
/* 3150 */     NodeList childNodeList = nodeList.item(0).getChildNodes();
/* 3151 */     for (int i = 0; i < childNodeList.getLength(); i++) {
/* 3152 */       Node node = childNodeList.item(i);
/* 3153 */       if (node != null)
/*      */       {
/*      */ 
/* 3156 */         String nodeName = node.getNodeName();
/* 3157 */         if (nodeName.equalsIgnoreCase("PasswordEncryptionMode")) {
/* 3158 */           int passwordEncryptionMode = parseInteger(getNodeValue(node));
/* 3159 */           serverConfig.setPasswordEncryptionMode(passwordEncryptionMode);
/* 3160 */         } else if (nodeName.equalsIgnoreCase("PlatformPasswordLevel")) {
/* 3161 */           int platformPasswordLevel = parseInteger(getNodeValue(node));
/* 3162 */           serverConfig.setPlatformPasswordLevel(platformPasswordLevel);
/* 3163 */         } else if (nodeName.equalsIgnoreCase("RandomCode")) {
/* 3164 */           serverConfig.setRandomCode(getNodeValue(node));
/* 3165 */         } else if (nodeName.equalsIgnoreCase("RandomKey")) {
/* 3166 */           serverConfig.setRandomKey(getNodeValue(node));
/* 3167 */         } else if (nodeName.equalsIgnoreCase("Version")) {
/* 3168 */           serverConfig.setPlatformType(getNodeValue(node));
/*      */         }
/*      */       } }
/* 3171 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static boolean parseRecordPosInfo(String xmlRecordPosInfo, CameraInfo cameraInfo)
/*      */   {
/* 3178 */     if ((TextUtils.isEmpty(xmlRecordPosInfo)) || (xmlRecordPosInfo == null) || (cameraInfo == null))
/*      */     {
/* 3180 */       mError = 100;
/* 3181 */       mErrorDesc = "input param error!";
/* 3182 */       return false;
/*      */     }
/* 3184 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseServerConfig() respone:" + xmlRecordPosInfo);
/*      */     
/*      */ 
/* 3187 */     Element root = getRootElement(xmlRecordPosInfo);
/* 3188 */     if (root == null) {
/* 3189 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 3193 */     boolean ret = parseStatus(root);
/* 3194 */     if (!ret) {
/* 3195 */       return false;
/*      */     }
/*      */     
/* 3198 */     NodeList nodeList = root.getElementsByTagName("Params");
/* 3199 */     if (nodeList.getLength() < 0) {
/* 3200 */       return false;
/*      */     }
/*      */     
/* 3203 */     nodeList = nodeList.item(0).getChildNodes();
/* 3204 */     int size = nodeList.getLength();
/* 3205 */     for (int i = 0; i < size; i++) {
/* 3206 */       Node node = nodeList.item(i);
/* 3207 */       String nodeName = node.getNodeName();
/* 3208 */       if (nodeName.equalsIgnoreCase("CameraList"))
/*      */       {
/* 3210 */         NodeList childNodeList = node.getChildNodes();
/* 3211 */         int childNodeSize = childNodeList.getLength();
/* 3212 */         for (int j = 0; j < childNodeSize; j++) {
/* 3213 */           Node childNode = childNodeList.item(j);
/* 3214 */           String childNodeName = childNode.getNodeName();
/* 3215 */           if (childNodeName.equalsIgnoreCase("Camera"))
/*      */           {
/* 3217 */             NodeList cameras = childNode.getChildNodes();
/* 3218 */             int cameraSize = cameras.getLength();
/* 3219 */             for (int k = 0; k < cameraSize; k++) {
/* 3220 */               Node cameraNode = cameras.item(k);
/* 3221 */               String name = cameraNode.getNodeName();
/* 3222 */               if ((!name.equalsIgnoreCase("ID")) && 
/* 3223 */                 (name.equalsIgnoreCase("RecordPos")))
/*      */               {
/* 3225 */                 String recordPosStr = getNodeValue(cameraNode);
/* 3226 */                 if (!TextUtils.isEmpty(recordPosStr)) {
/* 3227 */                   cameraInfo.setRecordPos(getListFromString(recordPosStr));
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 3235 */     return true;
/*      */   }
/*      */   
/*      */   public static boolean parseVerifCodeInfo(String xmlVerifCodeInfo, ServInfo servInfo)
/*      */   {
/* 3240 */     if ((TextUtils.isEmpty(xmlVerifCodeInfo)) || (servInfo == null))
/*      */     {
/* 3242 */       mError = 100;
/* 3243 */       mErrorDesc = "input param error!";
/* 3244 */       return false;
/*      */     }
/* 3246 */     CNetSDKLog.d("XMLParser", "VMSNetSDK::parseServerConfig() respone:" + xmlVerifCodeInfo);
/*      */     
/*      */ 
/* 3249 */     Element root = getRootElement(xmlVerifCodeInfo);
/* 3250 */     if (root == null) {
/* 3251 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 3255 */     boolean ret = parseStatus(root);
/* 3256 */     if (!ret) {
/* 3257 */       return false;
/*      */     }
/*      */     
/* 3260 */     NodeList nodeList = root.getElementsByTagName("Params");
/* 3261 */     if (nodeList.getLength() < 0) {
/* 3262 */       return false;
/*      */     }
/*      */     
/* 3265 */     NodeList childNodeList = nodeList.item(0).getChildNodes();
/* 3266 */     for (int i = 0; i < childNodeList.getLength(); i++) {
/* 3267 */       Node node = childNodeList.item(i);
/* 3268 */       if (node != null)
/*      */       {
/*      */ 
/* 3271 */         String nodeName = node.getNodeName();
/* 3272 */         if (nodeName.equalsIgnoreCase("VerifCode")) {
/* 3273 */           servInfo.setVerifCode(getNodeValue(node));
/* 3274 */         } else if (nodeName.equalsIgnoreCase("VerifCodeKey"))
/* 3275 */           servInfo.setVerifCodeKey(getNodeValue(node));
/*      */       }
/*      */     }
/* 3278 */     return true;
/*      */   }
/*      */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\XMLParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */