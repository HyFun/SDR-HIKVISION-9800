/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.deviceInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DeviceInfo
/*    */ {
/*    */   private String deviceName;
/*    */   
/*    */ 
/*    */   private String deviceType;
/*    */   
/*    */ 
/*    */   private String supplier;
/*    */   
/*    */ 
/*    */   private String indexCode;
/*    */   
/*    */   private String deviceIP;
/*    */   
/*    */   private int devicePort;
/*    */   
/*    */   private String loginName;
/*    */   
/*    */   private String loginPsw;
/*    */   
/*    */   public static final String SUPPLIER_HIKVISION = "HIKVISION";
/*    */   
/*    */   public static final String SUPPLIER_DAHUA = "DAHUA";
/*    */   
/*    */   public static final String SUPPLIER_OTHER = "Other";
/*    */   
/*    */ 
/*    */   public String getSupplier()
/*    */   {
/* 35 */     return this.supplier;
/*    */   }
/*    */   
/*    */   public void setSupplier(String supplier) {
/* 39 */     this.supplier = supplier;
/*    */   }
/*    */   
/*    */   public String getIndexCode() {
/* 43 */     return this.indexCode;
/*    */   }
/*    */   
/*    */   public void setIndexCode(String indexCode) {
/* 47 */     this.indexCode = indexCode;
/*    */   }
/*    */   
/*    */   public String getDeviceName() {
/* 51 */     return this.deviceName;
/*    */   }
/*    */   
/*    */   public void setDeviceName(String deviceName) {
/* 55 */     this.deviceName = deviceName;
/*    */   }
/*    */   
/*    */   public String getDeviceType() {
/* 59 */     return this.deviceType;
/*    */   }
/*    */   
/*    */   public void setDeviceType(String deviceType) {
/* 63 */     this.deviceType = deviceType;
/*    */   }
/*    */   
/*    */   public String getDeviceIP() {
/* 67 */     return this.deviceIP;
/*    */   }
/*    */   
/*    */   public void setDeviceIP(String deviceIP) {
/* 71 */     this.deviceIP = deviceIP;
/*    */   }
/*    */   
/*    */   public int getDevicePort() {
/* 75 */     return this.devicePort;
/*    */   }
/*    */   
/*    */   public void setDevicePort(int devicePort) {
/* 79 */     this.devicePort = devicePort;
/*    */   }
/*    */   
/*    */   public String getLoginPsw() {
/* 83 */     return this.loginPsw;
/*    */   }
/*    */   
/*    */   public void setLoginPsw(String loginpsw) {
/* 87 */     this.loginPsw = loginpsw;
/*    */   }
/*    */   
/*    */   public String getLoginName() {
/* 91 */     return this.loginName;
/*    */   }
/*    */   
/*    */   public void setLoginName(String loginName) {
/* 95 */     this.loginName = loginName;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\deviceInfo\DeviceInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */