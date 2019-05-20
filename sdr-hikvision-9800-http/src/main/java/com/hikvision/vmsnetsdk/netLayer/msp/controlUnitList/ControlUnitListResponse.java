/*     */ package com.hikvision.vmsnetsdk.netLayer.msp.controlUnitList;
/*     */ 
/*     */

import android.text.TextUtils;

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspBackState;
import com.hikvision.vmsnetsdk.netLayer.msp.MspResponse;

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
/*     */ public class ControlUnitListResponse
/*     */   extends MspResponse
/*     */ {
/*     */   private static final String TAG = "ControlUnitListResponse";
/*     */   private ArrayList<ControlUnit> controlUnitList;
/*     */   private ControlUnitListBackState controlUnitListBackState;
/*     */   
/*     */   public ArrayList<ControlUnit> getControlUnitList()
/*     */   {
/*  25 */     return this.controlUnitList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ControlUnitListResponse(String responseData)
/*     */   {
/*  32 */     super(responseData);
/*     */   }
/*     */   
/*     */   public String getResponseData()
/*     */   {
/*  37 */     return super.getResponseData();
/*     */   }
/*     */   
/*     */   private ControlUnit parseControlUnit(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, NumberFormatException
/*     */   {
/*  42 */     xmlPullParser.require(2, null, "ControlUnit");
/*  43 */     int eventType = xmlPullParser.getEventType();
/*     */     
/*  45 */     ControlUnit controlUnit = new ControlUnit();
/*  46 */     while (eventType != 1) {
/*  47 */       String nodeName = xmlPullParser.getName();
/*  48 */       switch (eventType) {
/*     */       case 2: 
/*  50 */         CNetSDKLog.d("ControlUnitListResponse", "parseControlUnit,START_TAG：" + nodeName);
/*  51 */         if ("ID".equals(nodeName)) {
/*  52 */           String id = xmlPullParser.nextText();
/*  53 */           CNetSDKLog.d("ControlUnitListResponse", "parseControlUnit,id:" + id);
/*  54 */           controlUnit.setControlUnitID(id);
/*  55 */         } else if ("Name".equals(nodeName)) {
/*  56 */           String name = xmlPullParser.nextText();
/*  57 */           CNetSDKLog.d("ControlUnitListResponse", "parseControlUnit,name:" + name);
/*  58 */           controlUnit.setName(name);
/*  59 */         } else if ("ParentID".equals(nodeName)) {
/*  60 */           String parentId = xmlPullParser.nextText();
/*  61 */           CNetSDKLog.d("ControlUnitListResponse", "parseControlUnit,parentId:" + parentId);
/*  62 */           if (!TextUtils.isEmpty(parentId)) {
/*  63 */             controlUnit.setParentID(parentId.trim());
/*     */           }
/*  65 */         } else if ("CascadeFlag".equals(nodeName)) {
/*  66 */           String cascadeFlag = xmlPullParser.nextText();
/*  67 */           CNetSDKLog.d("ControlUnitListResponse", "parseControlUnit,cascadeFlag:" + cascadeFlag);
/*  68 */           if (!TextUtils.isEmpty(cascadeFlag)) {
/*  69 */             controlUnit.setCascadeFlag(Integer.parseInt(cascadeFlag.trim()));
/*     */           }
/*  71 */         } else if ("CurrentOnlineNum".equals(nodeName)) {
/*  72 */           String currentOnlineNum = xmlPullParser.nextText();
/*  73 */           CNetSDKLog.d("ControlUnitListResponse", "parseControlUnit,currentOnlineNum:" + currentOnlineNum);
/*  74 */           if (!TextUtils.isEmpty(currentOnlineNum)) {
/*  75 */             controlUnit.setCurrentOnlineNum(Integer.parseInt(currentOnlineNum.trim()));
/*     */           }
/*  77 */         } else if ("TotalNum".equals(nodeName)) {
/*  78 */           String totalNum = xmlPullParser.nextText();
/*  79 */           CNetSDKLog.d("ControlUnitListResponse", "parseControlUnit,totalNum:" + totalNum);
/*  80 */           if (!TextUtils.isEmpty(totalNum))
/*  81 */             controlUnit.setTotalNum(Integer.parseInt(totalNum.trim()));
/*     */         }
/*  83 */         break;
/*     */       
/*     */ 
/*     */       case 3: 
/*  87 */         CNetSDKLog.d("ControlUnitListResponse", "parseControlUnit,END_TAG：" + nodeName);
/*  88 */         if ("ControlUnit".equals(nodeName)) {
/*  89 */           return controlUnit;
/*     */         }
/*     */         
/*     */         break;
/*     */       }
/*     */       
/*  95 */       eventType = xmlPullParser.next();
/*     */     }
/*  97 */     return null;
/*     */   }
/*     */   
/*     */   public MspBackState getMspBackState()
/*     */   {
/* 102 */     return this.controlUnitListBackState;
/*     */   }
/*     */   
/*     */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*     */   {
/* 107 */     this.controlUnitListBackState = new ControlUnitListBackState();
/* 108 */     return doParseCycle(xmlPullParser);
/*     */   }
/*     */   
/*     */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*     */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*     */   {
/* 114 */     if ((xmlPullParser == null) || (nodeName == null)) {
/* 115 */       CNetSDKLog.e("ControlUnitListResponse", "handleXMLStartTag,param error");
/* 116 */       throw new Exception("handleXMLStartTag,param error");
/*     */     }
/* 118 */     if ("Status".equals(nodeName)) {
/* 119 */       String status = xmlPullParser.nextText();
/* 120 */       this.controlUnitListBackState.setCode(Integer.parseInt(status.trim()));
/* 121 */       CNetSDKLog.d("ControlUnitListResponse", "handleXMLStartTag,status：" + status);
/* 122 */     } else if ("Description".equals(nodeName)) {
/* 123 */       String description = xmlPullParser.nextText();
/* 124 */       this.controlUnitListBackState.setDescription(description);
/* 125 */       CNetSDKLog.d("ControlUnitListResponse", "handleXMLStartTag,description：" + description);
/* 126 */     } else if ("ControlUnitList".equals(nodeName)) {
/* 127 */       if (this.controlUnitList == null) {
/* 128 */         this.controlUnitList = new ArrayList();
/*     */       }
/* 130 */     } else if ("ControlUnit".equals(nodeName)) {
/* 131 */       ControlUnit controlUnit = parseControlUnit(xmlPullParser);
/* 132 */       if (controlUnit != null) {
/* 133 */         this.controlUnitList.add(controlUnit);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\controlUnitList\ControlUnitListResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */