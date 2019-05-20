/*     */ package com.hikvision.vmsnetsdk.netLayer.msp.controlUnitList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ControlUnit
/*     */ {
/*     */   private String controlUnitID;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  15 */   private String name = null;
/*     */   
/*     */ 
/*     */ 
/*     */   private String parentID;
/*     */   
/*     */ 
/*     */ 
/*     */   private int CascadeFlag;
/*     */   
/*     */ 
/*     */   private int CurrentOnlineNum;
/*     */   
/*     */ 
/*     */   private int TotalNum;
/*     */   
/*     */ 
/*     */ 
/*     */   public String getControlUnitID()
/*     */   {
/*  35 */     return this.controlUnitID;
/*     */   }
/*     */   
/*     */   public void setControlUnitID(String controlUnitID) {
/*  39 */     this.controlUnitID = controlUnitID;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/*  49 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  53 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getParentID()
/*     */   {
/*  63 */     return this.parentID;
/*     */   }
/*     */   
/*     */   public void setParentID(String parentID) {
/*  67 */     this.parentID = parentID;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getCascadeFlag()
/*     */   {
/*  77 */     return this.CascadeFlag;
/*     */   }
/*     */   
/*     */   public void setCascadeFlag(int cascadeFlag) {
/*  81 */     this.CascadeFlag = cascadeFlag;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getCurrentOnlineNum()
/*     */   {
/*  91 */     return this.CurrentOnlineNum;
/*     */   }
/*     */   
/*     */   public void setCurrentOnlineNum(int currentOnlineNum) {
/*  95 */     this.CurrentOnlineNum = currentOnlineNum;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getTotalNum()
/*     */   {
/* 105 */     return this.TotalNum;
/*     */   }
/*     */   
/*     */   public void setTotalNum(int totalNum) {
/* 109 */     this.TotalNum = totalNum;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\controlUnitList\ControlUnit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */