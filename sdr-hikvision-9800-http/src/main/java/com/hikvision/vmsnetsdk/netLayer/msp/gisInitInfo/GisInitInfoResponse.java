/*     */ package com.hikvision.vmsnetsdk.netLayer.msp.gisInitInfo;
/*     */ 
/*     */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspBackState;
import com.hikvision.vmsnetsdk.netLayer.msp.MspResponse;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
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
/*     */ public class GisInitInfoResponse
/*     */   extends MspResponse
/*     */ {
/*     */   private static final String TAG = "GisInitInfoResponse";
/*     */   private static final String XMLKEY_FULL_RANGE = "FullRange";
/*     */   private static final String XMLKEY_INIT_RANGE = "InitRange";
/*     */   private static final String XMLKEY_MAP_LEVEL = "MapLevel";
/*     */   private static final String XMLKEY_GOV_MAP = "GovMap";
/*     */   private static final String XMLKEY_IMAGE_MAP = "ImageMap";
/*     */   private int mapType;
/*     */   private String resType;
/*     */   private GisMapInfo govMap;
/*     */   private GisMapInfo imageMap;
/*     */   private GisInitInfoBackState gisInitInfoBackState;
/*     */   
/*     */   public GisInitInfoResponse(String responseData)
/*     */   {
/*  39 */     super(responseData);
/*     */   }
/*     */   
/*     */   public String getResponseData()
/*     */   {
/*  44 */     return super.getResponseData();
/*     */   }
/*     */   
/*     */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*     */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*     */   {
/*  50 */     if ((xmlPullParser == null) || (nodeName == null)) {
/*  51 */       CNetSDKLog.e("GisInitInfoResponse", "handleXMLStartTag,param error");
/*  52 */       throw new Exception("handleXMLStartTag,param error");
/*     */     }
/*  54 */     if ("Status".equals(nodeName)) {
/*  55 */       String status = xmlPullParser.nextText();
/*  56 */       this.gisInitInfoBackState.setCode(Integer.parseInt(status.trim()));
/*  57 */       CNetSDKLog.d("GisInitInfoResponse", "handleXMLStartTag,status：" + status);
/*  58 */     } else if ("Description".equals(nodeName)) {
/*  59 */       String description = xmlPullParser.nextText();
/*  60 */       this.gisInitInfoBackState.setDescription(description);
/*  61 */       CNetSDKLog.d("GisInitInfoResponse", "handleXMLStartTag,description：" + description);
/*  62 */     } else if ("MapType".equals(nodeName)) {
/*  63 */       String mapType = xmlPullParser.nextText();
/*  64 */       CNetSDKLog.d("GisInitInfoResponse", "handleXMLStartTag,mapType：" + mapType);
/*  65 */       if ((mapType != null) && (mapType.length() > 0)) {
/*  66 */         this.mapType = Integer.parseInt(mapType);
/*     */       }
/*  68 */     } else if ("ResType".equals(nodeName)) {
/*  69 */       String resType = xmlPullParser.nextText();
/*  70 */       CNetSDKLog.d("GisInitInfoResponse", "handleXMLStartTag,resType：" + resType);
/*  71 */       this.resType = resType;
/*  72 */     } else if ("GovMap".equals(nodeName)) {
/*  73 */       GisMapInfo govMap = parseGisMapInfo(xmlPullParser, "GovMap");
/*  74 */       if (govMap != null) {
/*  75 */         this.govMap = govMap;
/*     */       }
/*  77 */     } else if ("ImageMap".equals(nodeName)) {
/*  78 */       GisMapInfo imageMap = parseGisMapInfo(xmlPullParser, "ImageMap");
/*  79 */       if (imageMap != null) {
/*  80 */         this.imageMap = imageMap;
/*     */       }
/*     */     }
/*     */   }
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
/*     */   private Range parseRange(XmlPullParser xmlPullParser, String xmlKey)
/*     */     throws XmlPullParserException, IOException, NumberFormatException, Exception
/*     */   {
/*  99 */     if ((xmlPullParser == null) || (xmlKey == null) || (xmlKey.length() <= 0)) {
/* 100 */       CNetSDKLog.e("GisInitInfoResponse", "parseRange,param error.rangeKey:" + xmlKey);
/* 101 */       return null;
/*     */     }
/* 103 */     xmlPullParser.require(2, null, xmlKey);
/* 104 */     int eventType = xmlPullParser.getEventType();
/*     */     
/* 106 */     Range range = new Range();
/*     */     
/* 108 */     while (eventType != 1) {
/* 109 */       String nodeName = xmlPullParser.getName();
/* 110 */       switch (eventType) {
/*     */       case 2: 
/* 112 */         CNetSDKLog.d("GisInitInfoResponse", "parseRange,START_TAG：" + nodeName);
/* 113 */         if ("Xmin".equals(nodeName)) {
/* 114 */           String xMin = xmlPullParser.nextText();
/* 115 */           CNetSDKLog.d("GisInitInfoResponse", "parseRange,xMin:" + xMin);
/* 116 */           if ((xMin != null) && (xMin.length() > 0)) {
/* 117 */             range.setxMin(Double.parseDouble(xMin));
/*     */           }
/* 119 */         } else if ("Ymin".equals(nodeName)) {
/* 120 */           String yMin = xmlPullParser.nextText();
/* 121 */           CNetSDKLog.d("GisInitInfoResponse", "parseRange,yMin:" + yMin);
/* 122 */           if ((yMin != null) && (yMin.length() > 0)) {
/* 123 */             range.setyMin(Double.parseDouble(yMin));
/*     */           }
/* 125 */         } else if ("Xmax".equals(nodeName)) {
/* 126 */           String xMax = xmlPullParser.nextText();
/* 127 */           CNetSDKLog.d("GisInitInfoResponse", "parseRange,xMax:" + xMax);
/* 128 */           if ((xMax != null) && (xMax.length() > 0)) {
/* 129 */             range.setxMax(Double.parseDouble(xMax));
/*     */           }
/* 131 */         } else if ("Ymax".equals(nodeName)) {
/* 132 */           String yMax = xmlPullParser.nextText();
/* 133 */           CNetSDKLog.d("GisInitInfoResponse", "parseRange,yMax:" + yMax);
/* 134 */           if ((yMax != null) && (yMax.length() > 0))
/* 135 */             range.setyMax(Double.parseDouble(yMax));
/*     */         }
/* 137 */         break;
/*     */       
/*     */ 
/*     */       case 3: 
/* 141 */         CNetSDKLog.d("GisInitInfoResponse", "parseRange,END_TAG：" + nodeName);
/* 142 */         if (xmlKey.equals(nodeName)) {
/* 143 */           return range;
/*     */         }
/*     */         
/*     */         break;
/*     */       }
/*     */       
/* 149 */       eventType = xmlPullParser.next();
/*     */     }
/* 151 */     return null;
/*     */   }
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
/*     */   private MapLevel parseMapLevel(XmlPullParser xmlPullParser, String xmlKey)
/*     */     throws XmlPullParserException, IOException, NumberFormatException, Exception
/*     */   {
/* 168 */     if ((xmlPullParser == null) || (xmlKey == null) || (xmlKey.length() <= 0)) {
/* 169 */       CNetSDKLog.e("GisInitInfoResponse", "parseMapLevel,param error.mapLevelKey:" + xmlKey);
/* 170 */       return null;
/*     */     }
/* 172 */     xmlPullParser.require(2, null, xmlKey);
/* 173 */     int eventType = xmlPullParser.getEventType();
/*     */     
/* 175 */     MapLevel mapLevel = new MapLevel();
/*     */     
/* 177 */     while (eventType != 1) {
/* 178 */       String nodeName = xmlPullParser.getName();
/* 179 */       switch (eventType) {
/*     */       case 2: 
/* 181 */         CNetSDKLog.d("GisInitInfoResponse", "parseMapLevel,START_TAG：" + nodeName);
/* 182 */         if ("Level".equals(nodeName)) {
/* 183 */           String level = xmlPullParser.nextText();
/* 184 */           CNetSDKLog.d("GisInitInfoResponse", "parseMapLevel,level:" + level);
/* 185 */           if ((level != null) && (level.length() > 0)) {
/* 186 */             mapLevel.setLevel(Integer.parseInt(level));
/*     */           }
/* 188 */         } else if ("Resolution".equals(nodeName)) {
/* 189 */           String resolution = xmlPullParser.nextText();
/* 190 */           CNetSDKLog.d("GisInitInfoResponse", "parseMapLevel,resolution:" + resolution);
/* 191 */           if ((resolution != null) && (resolution.length() > 0)) {
/* 192 */             mapLevel.setResolution(Double.parseDouble(resolution));
/*     */           }
/* 194 */         } else if ("Scale".equals(nodeName)) {
/* 195 */           String scale = xmlPullParser.nextText();
/* 196 */           CNetSDKLog.d("GisInitInfoResponse", "parseMapLevel,scale:" + scale);
/* 197 */           if ((scale != null) && (scale.length() > 0))
/* 198 */             mapLevel.setScale(Double.parseDouble(scale));
/*     */         }
/* 200 */         break;
/*     */       
/*     */       case 3: 
/* 203 */         CNetSDKLog.d("GisInitInfoResponse", "parseMapLevel,END_TAG：" + nodeName);
/* 204 */         if (xmlKey.equals(nodeName)) {
/* 205 */           return mapLevel;
/*     */         }
/*     */         
/*     */         break;
/*     */       }
/*     */       
/* 211 */       eventType = xmlPullParser.next();
/*     */     }
/* 213 */     return null;
/*     */   }
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
/*     */   private GisMapInfo parseGisMapInfo(XmlPullParser xmlPullParser, String xmlKey)
/*     */     throws XmlPullParserException, IOException, NumberFormatException, Exception
/*     */   {
/* 230 */     if ((xmlPullParser == null) || (xmlKey == null) || (xmlKey.length() <= 0)) {
/* 231 */       CNetSDKLog.e("GisInitInfoResponse", "parseGisMapInfo,param error.gisMapKey:" + xmlKey);
/* 232 */       return null;
/*     */     }
/* 234 */     xmlPullParser.require(2, null, xmlKey);
/* 235 */     int eventType = xmlPullParser.getEventType();
/*     */     
/* 237 */     GisMapInfo gisMap = new GisMapInfo();
/* 238 */     while (eventType != 1) {
/* 239 */       String nodeName = xmlPullParser.getName();
/* 240 */       switch (eventType) {
/*     */       case 2: 
/* 242 */         CNetSDKLog.d("GisInitInfoResponse", "parseGisMapInfo,START_TAG：" + nodeName);
/* 243 */         if ("MapName".equals(nodeName)) {
/* 244 */           String mapName = xmlPullParser.nextText();
/* 245 */           CNetSDKLog.d("GisInitInfoResponse", "parseGisMapInfo,mapName:" + mapName);
/* 246 */           gisMap.setMapName(mapName);
/* 247 */         } else if ("MapUrl".equals(nodeName)) {
/* 248 */           String mapUrl = xmlPullParser.nextText();
/* 249 */           CNetSDKLog.d("GisInitInfoResponse", "parseGisMapInfo,vectorUrl:" + mapUrl);
/* 250 */           gisMap.setMapUrl(mapUrl);
/* 251 */         } else if ("Wkid".equals(nodeName)) {
/* 252 */           String wkid = xmlPullParser.nextText();
/* 253 */           CNetSDKLog.d("GisInitInfoResponse", "parseGisMapInfo,wkid:" + wkid);
/* 254 */           if ((wkid != null) && (wkid.length() > 0)) {
/* 255 */             gisMap.setWkid(Integer.parseInt(wkid));
/*     */           }
/* 257 */         } else if ("FullRange".equals(nodeName)) {
/* 258 */           Range fullRange = parseRange(xmlPullParser, "FullRange");
/* 259 */           CNetSDKLog.d("GisInitInfoResponse", "parseGisMapInfo,fullRange:" + fullRange);
/* 260 */           gisMap.setFullRange(fullRange);
/* 261 */         } else if ("InitRange".equals(nodeName)) {
/* 262 */           Range initRange = parseRange(xmlPullParser, "InitRange");
/* 263 */           CNetSDKLog.d("GisInitInfoResponse", "parseGisMapInfo,initRange:" + initRange);
/* 264 */           gisMap.setInitRange(initRange);
/* 265 */         } else if ("MapLevelList".equals(nodeName)) {
/* 266 */           if (gisMap.getMapLevels() == null) {
/* 267 */             List<MapLevel> mapLevels = new ArrayList();
/* 268 */             gisMap.setMapLevels(mapLevels);
/*     */           }
/* 270 */         } else if ("MapLevel".equals(nodeName)) {
/* 271 */           MapLevel mapLevel = parseMapLevel(xmlPullParser, "MapLevel");
/* 272 */           CNetSDKLog.d("GisInitInfoResponse", "parseGisMapInfo,mapLevel:" + mapLevel);
/* 273 */           if (mapLevel != null)
/* 274 */             gisMap.getMapLevels().add(mapLevel);
/*     */         }
/* 276 */         break;
/*     */       
/*     */       case 3: 
/* 279 */         CNetSDKLog.d("GisInitInfoResponse", "parseGisMapInfo,END_TAG：" + nodeName);
/* 280 */         if (xmlKey.equals(nodeName)) {
/* 281 */           return gisMap;
/*     */         }
/*     */         
/*     */         break;
/*     */       }
/*     */       
/* 287 */       eventType = xmlPullParser.next();
/*     */     }
/* 289 */     return null;
/*     */   }
/*     */   
/*     */   public int getMapType() {
/* 293 */     return this.mapType;
/*     */   }
/*     */   
/*     */   public String getResType() {
/* 297 */     return this.resType;
/*     */   }
/*     */   
/*     */   public GisMapInfo getGovMap() {
/* 301 */     return this.govMap;
/*     */   }
/*     */   
/*     */   public GisMapInfo getImageMap() {
/* 305 */     return this.imageMap;
/*     */   }
/*     */   
/*     */   public MspBackState getMspBackState()
/*     */   {
/* 310 */     return this.gisInitInfoBackState;
/*     */   }
/*     */   
/*     */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*     */   {
/* 315 */     this.gisInitInfoBackState = new GisInitInfoBackState();
/* 316 */     return doParseCycle(xmlPullParser);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\gisInitInfo\GisInitInfoResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */