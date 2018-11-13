/*    */ package com.hikvision.vmsnetsdk;
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
/*    */ public class RegionInfo
/*    */ {
/*    */   private String regionID;
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
/* 28 */   private String name = null;
/*    */   
/*    */   private String parentID;
/*    */   private int controlUnitID;
/*    */   
/*    */   public String getName()
/*    */   {
/* 35 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 39 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String getRegionID() {
/* 43 */     return this.regionID;
/*    */   }
/*    */   
/*    */   public void setRegionID(String regionID) {
/* 47 */     this.regionID = regionID;
/*    */   }
/*    */   
/*    */   public String getParentID() {
/* 51 */     return this.parentID;
/*    */   }
/*    */   
/*    */   public void setParentID(String parentID) {
/* 55 */     this.parentID = parentID;
/*    */   }
/*    */   
/*    */   public int getControlUnitID() {
/* 59 */     return this.controlUnitID;
/*    */   }
/*    */   
/*    */   public void setControlUnitID(int controlUnitID) {
/* 63 */     this.controlUnitID = controlUnitID;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\RegionInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */