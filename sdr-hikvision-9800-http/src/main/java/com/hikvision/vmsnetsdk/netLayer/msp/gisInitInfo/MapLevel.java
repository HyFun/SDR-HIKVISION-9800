/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.gisInitInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MapLevel
/*    */ {
/*    */   private int level;
/*    */   
/*    */ 
/*    */   private double resolution;
/*    */   
/*    */ 
/*    */   private double scale;
/*    */   
/*    */ 
/*    */   public int getLevel()
/*    */   {
/* 18 */     return this.level;
/*    */   }
/*    */   
/*    */   public void setLevel(int level) {
/* 22 */     this.level = level;
/*    */   }
/*    */   
/*    */   public double getResolution() {
/* 26 */     return this.resolution;
/*    */   }
/*    */   
/*    */   public void setResolution(double resolution) {
/* 30 */     this.resolution = resolution;
/*    */   }
/*    */   
/*    */   public double getScale() {
/* 34 */     return this.scale;
/*    */   }
/*    */   
/*    */   public void setScale(double scale) {
/* 38 */     this.scale = scale;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 43 */     return "MapLevel [level=" + this.level + ", resolution=" + this.resolution + ", scale=" + this.scale + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\gisInitInfo\MapLevel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */