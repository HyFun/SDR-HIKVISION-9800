/*     */ package com.hikvision.vmsnetsdk;
/*     */ 
/*     */

import com.hikvision.vmsnetsdk.netLayer.msp.gisInitInfo.GisMapInfo;
import com.hikvision.vmsnetsdk.netLayer.msp.gisInitInfo.SDKArcGIS;
import com.hikvision.vmsnetsdk.netLayer.msp.gisInitInfo.SDKGIS;

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
/*     */ public class SDKGISInitInfo
/*     */ {
/*     */   public static final int EZ_GIS = 0;
/*     */   public static final int STATIC_GIS = 1;
/*     */   public static final int GOOGLE_GIS = 2;
/*     */   public static final int ARC_GIS = 3;
/*     */   public static final int WEB_GIS = 4;
/*     */   public static final int GAO_DE_GIS = 5;
/*     */   public static final int BAI_DU_GIS = 6;
/*  29 */   public SDKArcGIS sdkArcGIS = new SDKArcGIS();
/*     */   
/*     */ 
/*  32 */   public SDKGIS sdkGIS = new SDKGIS();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private int mapType;
/*     */   
/*     */ 
/*     */ 
/*     */   private String resType;
/*     */   
/*     */ 
/*     */ 
/*     */   private GisMapInfo govMap;
/*     */   
/*     */ 
/*     */ 
/*     */   private GisMapInfo imageMap;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMapType()
/*     */   {
/*  56 */     return this.mapType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMapType(int mapType)
/*     */   {
/*  67 */     this.mapType = mapType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getResType()
/*     */   {
/*  78 */     return this.resType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setResType(String resType)
/*     */   {
/*  89 */     this.resType = resType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public GisMapInfo getGovMap()
/*     */   {
/* 100 */     return this.govMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGovMap(GisMapInfo govMap)
/*     */   {
/* 111 */     this.govMap = govMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public GisMapInfo getImageMap()
/*     */   {
/* 122 */     return this.imageMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setImageMap(GisMapInfo imageMap)
/*     */   {
/* 133 */     this.imageMap = imageMap;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\SDKGISInitInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */