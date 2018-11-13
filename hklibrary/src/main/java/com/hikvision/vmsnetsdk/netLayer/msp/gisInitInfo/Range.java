/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.gisInitInfo;
/*    */ 
/*    */ 
/*    */ public class Range
/*    */ {
/*    */   private double xMin;
/*    */   
/*    */   private double yMin;
/*    */   
/*    */   private double xMax;
/*    */   
/*    */   private double yMax;
/*    */   
/*    */   public double getxMin()
/*    */   {
/* 16 */     return this.xMin;
/*    */   }
/*    */   
/*    */   public void setxMin(double xMin) {
/* 20 */     this.xMin = xMin;
/*    */   }
/*    */   
/*    */   public double getyMin() {
/* 24 */     return this.yMin;
/*    */   }
/*    */   
/*    */   public void setyMin(double yMin) {
/* 28 */     this.yMin = yMin;
/*    */   }
/*    */   
/*    */   public double getxMax() {
/* 32 */     return this.xMax;
/*    */   }
/*    */   
/*    */   public void setxMax(double xMax) {
/* 36 */     this.xMax = xMax;
/*    */   }
/*    */   
/*    */   public double getyMax() {
/* 40 */     return this.yMax;
/*    */   }
/*    */   
/*    */   public void setyMax(double yMax) {
/* 44 */     this.yMax = yMax;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 49 */     return "Range [xMin=" + this.xMin + ", yMin=" + this.yMin + ", xMax=" + this.xMax + ", yMax=" + this.yMax + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\gisInitInfo\Range.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */