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
/*    */ 
/*    */ public class ServerVersionInfo
/*    */ {
/*    */   private int platformID;
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
/* 29 */   private String platformVersion = null;
/*    */   
/* 31 */   private String mspVersion = null;
/*    */   
/* 33 */   private String magVersion = null;
/*    */   
/* 35 */   private String vtduVersion = null;
/*    */   
/* 37 */   private String ptzProxyVersion = null;
/*    */   
/* 39 */   private String vmsVersion = null;
/*    */   
/*    */   public int getPlatformID() {
/* 42 */     return this.platformID;
/*    */   }
/*    */   
/*    */   public void setPlatformID(int platformID) {
/* 46 */     this.platformID = platformID;
/*    */   }
/*    */   
/*    */   public String getPlatformVersion() {
/* 50 */     return this.platformVersion;
/*    */   }
/*    */   
/*    */   public void setPlatformVersion(String platformVersion) {
/* 54 */     this.platformVersion = platformVersion;
/*    */   }
/*    */   
/*    */   public String getMSPVersion() {
/* 58 */     return this.mspVersion;
/*    */   }
/*    */   
/*    */   public void setMSPVersion(String mSPVersion) {
/* 62 */     this.mspVersion = mSPVersion;
/*    */   }
/*    */   
/*    */   public String getMAGVersion() {
/* 66 */     return this.magVersion;
/*    */   }
/*    */   
/*    */   public void setMAGVersion(String mAGVersion) {
/* 70 */     this.magVersion = mAGVersion;
/*    */   }
/*    */   
/*    */   public String getVTDUVersion() {
/* 74 */     return this.vtduVersion;
/*    */   }
/*    */   
/*    */   public void setVTDUVersion(String vTDUVersion) {
/* 78 */     this.vtduVersion = vTDUVersion;
/*    */   }
/*    */   
/*    */   public String getPTZProxyVersion() {
/* 82 */     return this.ptzProxyVersion;
/*    */   }
/*    */   
/*    */   public void setPTZProxyVersion(String pTZProxyVersion) {
/* 86 */     this.ptzProxyVersion = pTZProxyVersion;
/*    */   }
/*    */   
/*    */   public String getVMSVersion() {
/* 90 */     return this.vmsVersion;
/*    */   }
/*    */   
/*    */   public void setVMSVersion(String vMSVersion) {
/* 94 */     this.vmsVersion = vMSVersion;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\ServerVersionInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */