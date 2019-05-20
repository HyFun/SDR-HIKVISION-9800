/*     */ package com.hikvision.vmsnetsdk.netLayer.msp.gisPoint;
/*     */ 
/*     */ import java.util.List;

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
/*     */ public class GISPointInfo
/*     */ {
/*     */   private int pointType;
/*     */   private String objectId;
/*     */   private String objectName;
/*     */   private int monitorType;
/*     */   private boolean isOnline;
/*     */   private String longitude;
/*     */   private String latitude;
/*     */   private String controlUnitId;
/*     */   private String remark;
/*     */   private String deviceID;
/*     */   private String deviceName;
/*     */   private List<Integer> userCapability;
/*     */   public static final int POINT_TYPE_MONITOR = 1;
/*     */   public static final int POINT_TYPE_ALARM_INPUT = 2;
/*     */   public static final int POINT_TYPE_ALARM_OUTPUT = 3;
/*     */   public static final int POINT_TYPE_INTEREST = 4;
/*     */   public static final int POINT_TYPE_INDIVIDUAL_SOLDIER = 5;
/*     */   public static final int POINT_TYPE_VEHICLE_EQUIPMENT = 6;
/*     */   public static final int POINT_TYPE_BONET = 7;
/*     */   public static final int MONITOR_POINT_TYPE_BLOT = 0;
/*     */   public static final int MONITOR_POINT_TYPE_HALFSPHERE = 1;
/*     */   public static final int MONITOR_POINT_TYPE_FASTBALL = 2;
/*     */   public static final int MONITOR_POINT_TYPE_PTZ = 3;
/*     */   private static final int ONLINE = 1;
/*     */   
/*     */   public int getPointType()
/*     */   {
/*  48 */     return this.pointType;
/*     */   }
/*     */   
/*     */   public void setPointType(int pointType) {
/*  52 */     this.pointType = pointType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getControlUnitId(boolean isPlatformNew)
/*     */   {
/*  62 */     if (!isPlatformNew) {
/*  63 */       return null;
/*     */     }
/*  65 */     return this.controlUnitId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setControlUnitId(String controlUnitId)
/*     */   {
/*  75 */     this.controlUnitId = controlUnitId;
/*     */   }
/*     */   
/*     */   public String getObjectId() {
/*  79 */     return this.objectId;
/*     */   }
/*     */   
/*     */   public void setObjectId(String objectId) {
/*  83 */     this.objectId = objectId;
/*     */   }
/*     */   
/*     */   public String getObjectName() {
/*  87 */     return this.objectName;
/*     */   }
/*     */   
/*     */   public void setObjectName(String objectName) {
/*  91 */     this.objectName = objectName;
/*     */   }
/*     */   
/*     */   public int getMonitorType() {
/*  95 */     return this.monitorType;
/*     */   }
/*     */   
/*     */   public void setMonitorType(int monitorType) {
/*  99 */     this.monitorType = monitorType;
/*     */   }
/*     */   
/*     */   public boolean isOnline() {
/* 103 */     return this.isOnline;
/*     */   }
/*     */   
/*     */   public boolean setOnline(int isOnline)
/*     */   {
/* 108 */     if (isOnline == 1) {
/* 109 */       this.isOnline = true;
/* 110 */       return true;
/*     */     }
/* 112 */     this.isOnline = false;
/* 113 */     return true;
/*     */   }
/*     */   
/*     */   public void setOnline(boolean isOnline)
/*     */   {
/* 118 */     this.isOnline = isOnline;
/*     */   }
/*     */   
/*     */   public String getLongitude() {
/* 122 */     return this.longitude;
/*     */   }
/*     */   
/*     */   public void setLongitude(String longitude) {
/* 126 */     this.longitude = longitude;
/*     */   }
/*     */   
/*     */   public String getLatitude() {
/* 130 */     return this.latitude;
/*     */   }
/*     */   
/*     */   public void setLatitude(String latitude) {
/* 134 */     this.latitude = latitude;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getRemark(boolean isPlatformNew)
/*     */   {
/* 144 */     if (!isPlatformNew) {
/* 145 */       return null;
/*     */     }
/* 147 */     return this.remark;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 157 */     this.remark = remark;
/*     */   }
/*     */   
/*     */   public String getDeviceID() {
/* 161 */     return this.deviceID;
/*     */   }
/*     */   
/*     */   public void setDeviceID(String deviceID) {
/* 165 */     this.deviceID = deviceID;
/*     */   }
/*     */   
/*     */   public String getDeviceName() {
/* 169 */     return this.deviceName;
/*     */   }
/*     */   
/*     */   public void setDeviceName(String deviceName) {
/* 173 */     this.deviceName = deviceName;
/*     */   }
/*     */   
/*     */   public List<Integer> getUserCapability() {
/* 177 */     return this.userCapability;
/*     */   }
/*     */   
/*     */   public void setUserCapability(List<Integer> userCapability) {
/* 181 */     this.userCapability = userCapability;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\gisPoint\GISPointInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */