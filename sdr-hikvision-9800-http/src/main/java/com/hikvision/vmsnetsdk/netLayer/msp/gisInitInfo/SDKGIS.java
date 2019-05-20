/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.gisInitInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SDKGIS
/*    */ {
/*    */   private int mapType;
/*    */   
/*    */ 
/*    */   private String mapName;
/*    */   
/*    */ 
/*    */   private double initLat;
/*    */   
/*    */ 
/*    */   private double initLng;
/*    */   
/*    */ 
/*    */   public static final int EZ_GIS = 0;
/*    */   
/*    */   public static final int STATIC_GIS = 1;
/*    */   
/*    */   public static final int GOOGLE_GIS = 2;
/*    */   
/*    */   public static final int ARC_GIS = 3;
/*    */   
/*    */   public static final int WEB_GIS = 4;
/*    */   
/*    */ 
/*    */   public int getMapType()
/*    */   {
/* 32 */     return this.mapType;
/*    */   }
/*    */   
/*    */   public void setMapType(int mapType) {
/* 36 */     this.mapType = mapType;
/*    */   }
/*    */   
/*    */   public String getMapName() {
/* 40 */     return this.mapName;
/*    */   }
/*    */   
/*    */   public void setMapName(String mapName) {
/* 44 */     this.mapName = mapName;
/*    */   }
/*    */   
/*    */   public double getInitLat() {
/* 48 */     return this.initLat;
/*    */   }
/*    */   
/*    */   public void setInitLat(double initLat) {
/* 52 */     this.initLat = initLat;
/*    */   }
/*    */   
/*    */   public double getInitLng() {
/* 56 */     return this.initLng;
/*    */   }
/*    */   
/*    */   public void setInitLng(double initLng) {
/* 60 */     this.initLng = initLng;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\gisInitInfo\SDKGIS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */