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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServerConfig
/*    */ {
/* 20 */   private int passwordEncryptionMode = 0;
/*    */   
/* 22 */   private int platformPasswordLevel = 0;
/*    */   
/*    */   private String platformType;
/*    */   
/*    */   private String randomCode;
/*    */   
/*    */   private String randomKey;
/*    */   
/* 30 */   public static String PLATFORM_V15 = "V1.5.0";
/*    */   
/* 32 */   public static String PLATFORM_V16 = "V1.6.0";
/*    */   
/*    */   public int getPasswordEncryptionMode() {
/* 35 */     return this.passwordEncryptionMode;
/*    */   }
/*    */   
/*    */   public void setPasswordEncryptionMode(int passwordEncryptionMode) {
/* 39 */     this.passwordEncryptionMode = passwordEncryptionMode;
/*    */   }
/*    */   
/*    */   public int getPlatformPasswordLevel() {
/* 43 */     return this.platformPasswordLevel;
/*    */   }
/*    */   
/*    */   public void setPlatformPasswordLevel(int platformPasswordLevel) {
/* 47 */     this.platformPasswordLevel = platformPasswordLevel;
/*    */   }
/*    */   
/*    */   public String getPlatformType() {
/* 51 */     return this.platformType;
/*    */   }
/*    */   
/*    */   public void setPlatformType(String platformType) {
/* 55 */     this.platformType = platformType;
/*    */   }
/*    */   
/*    */   public String getRandomCode() {
/* 59 */     return this.randomCode;
/*    */   }
/*    */   
/*    */   public void setRandomCode(String randomCode) {
/* 63 */     this.randomCode = randomCode;
/*    */   }
/*    */   
/*    */   public String getRandomKey() {
/* 67 */     return this.randomKey;
/*    */   }
/*    */   
/*    */   public void setRandomKey(String randomKey) {
/* 71 */     this.randomKey = randomKey;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\ServerConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */