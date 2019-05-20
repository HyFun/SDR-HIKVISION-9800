/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.gisInitInfo;
/*    */ 
/*    */ import java.util.List;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GisMapInfo
/*    */ {
/*    */   private String mapName;
/*    */   private String mapUrl;
/*    */   private int wkid;
/*    */   private Range fullRange;
/*    */   private Range initRange;
/*    */   private List<MapLevel> mapLevels;
/*    */   
/*    */   public String getMapName()
/*    */   {
/* 29 */     return this.mapName;
/*    */   }
/*    */   
/*    */   public void setMapName(String mapName) {
/* 33 */     this.mapName = mapName;
/*    */   }
/*    */   
/*    */   public String getMapUrl() {
/* 37 */     return this.mapUrl;
/*    */   }
/*    */   
/*    */   public void setMapUrl(String mapUrl) {
/* 41 */     this.mapUrl = mapUrl;
/*    */   }
/*    */   
/*    */   public int getWkid() {
/* 45 */     return this.wkid;
/*    */   }
/*    */   
/*    */   public void setWkid(int wkid) {
/* 49 */     this.wkid = wkid;
/*    */   }
/*    */   
/*    */   public Range getFullRange() {
/* 53 */     return this.fullRange;
/*    */   }
/*    */   
/*    */   public void setFullRange(Range fullRange) {
/* 57 */     this.fullRange = fullRange;
/*    */   }
/*    */   
/*    */   public Range getInitRange() {
/* 61 */     return this.initRange;
/*    */   }
/*    */   
/*    */   public void setInitRange(Range initRange) {
/* 65 */     this.initRange = initRange;
/*    */   }
/*    */   
/*    */   public List<MapLevel> getMapLevels() {
/* 69 */     return this.mapLevels;
/*    */   }
/*    */   
/*    */   public void setMapLevels(List<MapLevel> mapLevels) {
/* 73 */     this.mapLevels = mapLevels;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\gisInitInfo\GisMapInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */