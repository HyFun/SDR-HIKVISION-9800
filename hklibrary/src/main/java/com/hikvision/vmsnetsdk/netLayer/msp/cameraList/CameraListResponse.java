/*     */ package com.hikvision.vmsnetsdk.netLayer.msp.cameraList;
/*     */ 
/*     */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspBackState;
import com.hikvision.vmsnetsdk.netLayer.msp.MspResponse;
import com.hikvision.vmsnetsdk.util.ParseHelper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

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
/*     */ public class CameraListResponse
/*     */   extends MspResponse
/*     */ {
/*     */   private static final String TAG = "CameraListResponse";
/*     */   private ArrayList<Camera> cameraList;
/*     */   private CameraListBackState controlUnitBackState;
/*     */   
/*     */   public ArrayList<Camera> getCameraList()
/*     */   {
/*  25 */     return this.cameraList;
/*     */   }
/*     */   
/*     */   public CameraListResponse(String responseData)
/*     */   {
/*  30 */     super(responseData);
/*     */   }
/*     */   
/*     */   public String getResponseData()
/*     */   {
/*  35 */     return super.getResponseData();
/*     */   }
/*     */   
/*     */   private Camera parseCamera(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, NumberFormatException, Exception
/*     */   {
/*  40 */     xmlPullParser.require(2, null, "Camera");
/*  41 */     int eventType = xmlPullParser.getEventType();
/*     */     
/*  43 */     Camera camera = new Camera();
/*  44 */     while (eventType != 1) {
/*  45 */       String nodeName = xmlPullParser.getName();
/*  46 */       switch (eventType) {
/*     */       case 2: 
/*  48 */         CNetSDKLog.d("CameraListResponse", "parseCamera,START_TAG：" + nodeName);
/*  49 */         if ("ID".equals(nodeName)) {
/*  50 */           String id = xmlPullParser.nextText();
/*  51 */           CNetSDKLog.d("CameraListResponse", "parseCamera,id:" + id);
/*  52 */           camera.setId(id);
/*  53 */         } else if ("Name".equals(nodeName)) {
/*  54 */           String name = xmlPullParser.nextText();
/*  55 */           CNetSDKLog.d("CameraListResponse", "parseCamera,name:" + name);
/*  56 */           camera.setName(name);
/*  57 */         } else if ("Type".equals(nodeName)) {
/*  58 */           String type = xmlPullParser.nextText();
/*  59 */           CNetSDKLog.d("CameraListResponse", "parseCamera,type:" + type);
/*  60 */           camera.setType(Integer.parseInt(type.trim()));
/*  61 */         } else if ("IsOnline".equals(nodeName)) {
/*  62 */           String isOnline = xmlPullParser.nextText();
/*  63 */           CNetSDKLog.d("CameraListResponse", "parseCamera,isOnline:" + isOnline);
/*  64 */           boolean res = camera.setOnline(Integer.parseInt(isOnline.trim()));
/*  65 */           if (!res) {
/*  66 */             throw new Exception("parseCamera error,csuse isOnline can not be other value.");
/*     */           }
/*  68 */         } else if ("UserCapability".equals(nodeName)) {
/*  69 */           String userCapability = xmlPullParser.nextText();
/*  70 */           CNetSDKLog.d("CameraListResponse", "parseCamera,userCapability:" + userCapability);
/*  71 */           camera.setUserCapability(ParseHelper.parseStrToIntByComma(userCapability));
/*  72 */         } else if ("CollectedFlag".equals(nodeName)) {
/*  73 */           String collectedFlag = xmlPullParser.nextText();
/*  74 */           CNetSDKLog.d("CameraListResponse", "parseCamera,collectedFlag:" + collectedFlag);
/*  75 */           camera.setCollectedFlag(Integer.parseInt(collectedFlag.trim()));
/*  76 */         } else if ("GroupID".equals(nodeName)) {
/*  77 */           String groupID = xmlPullParser.nextText();
/*  78 */           CNetSDKLog.d("CameraListResponse", "parseCamera,groupID:" + groupID);
/*  79 */           camera.setGroupID(Integer.parseInt(groupID.trim()));
/*  80 */         } else if ("CascadeFlag".equals(nodeName)) {
/*  81 */           String cascadeFlag = xmlPullParser.nextText();
/*  82 */           CNetSDKLog.d("CameraListResponse", "parseCamera,cascadeFlag:" + cascadeFlag);
/*  83 */           camera.setCascadeFlag(Integer.parseInt(cascadeFlag.trim())); }
/*  84 */         break;
/*     */       
/*     */ 
/*     */       case 3: 
/*  88 */         CNetSDKLog.d("CameraListResponse", "parseCamera,END_TAG：" + nodeName);
/*  89 */         if ("Camera".equals(nodeName)) {
/*  90 */           return camera;
/*     */         }
/*     */         
/*     */         break;
/*     */       }
/*     */       
/*  96 */       eventType = xmlPullParser.next();
/*     */     }
/*  98 */     return null;
/*     */   }
/*     */   
/*     */   public MspBackState getMspBackState()
/*     */   {
/* 103 */     return this.controlUnitBackState;
/*     */   }
/*     */   
/*     */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*     */   {
/* 108 */     this.controlUnitBackState = new CameraListBackState();
/* 109 */     return doParseCycle(xmlPullParser);
/*     */   }
/*     */   
/*     */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*     */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*     */   {
/* 115 */     if ((xmlPullParser == null) || (nodeName == null)) {
/* 116 */       CNetSDKLog.e("CameraListResponse", "handleXMLStartTag,param error");
/* 117 */       throw new Exception("handleXMLStartTag,param error");
/*     */     }
/* 119 */     if ("Status".equals(nodeName)) {
/* 120 */       String status = xmlPullParser.nextText();
/* 121 */       this.controlUnitBackState.setCode(Integer.parseInt(status.trim()));
/* 122 */       CNetSDKLog.d("CameraListResponse", "handleXMLStartTag,status：" + status);
/* 123 */     } else if ("Description".equals(nodeName)) {
/* 124 */       String description = xmlPullParser.nextText();
/* 125 */       this.controlUnitBackState.setDescription(description);
/* 126 */       CNetSDKLog.d("CameraListResponse", "handleXMLStartTag,description：" + description);
/* 127 */     } else if ("CameraList".equals(nodeName)) {
/* 128 */       if (this.cameraList == null) {
/* 129 */         this.cameraList = new ArrayList();
/*     */       }
/* 131 */     } else if ("Camera".equals(nodeName)) {
/* 132 */       Camera camera = parseCamera(xmlPullParser);
/* 133 */       if (camera != null) {
/* 134 */         this.cameraList.add(camera);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\cameraList\CameraListResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */