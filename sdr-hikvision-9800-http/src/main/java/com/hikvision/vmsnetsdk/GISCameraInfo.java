/*     */ package com.hikvision.vmsnetsdk;
/*     */ 
/*     */

import java.util.ArrayList;
import java.util.List;

/*     */

/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GISCameraInfo
/*     */ {
/*  13 */   private String cameraID = null;
/*  14 */   private String name = null;
/*  15 */   private int type = -1;
/*  16 */   private String deviceID = "";
/*  17 */   private String deviceName = "";
/*  18 */   private int pointType = -1;
/*     */   
/*  20 */   private boolean isOnline = false;
/*  21 */   private List<Integer> userCapability = new ArrayList(1);
/*  22 */   private String longitude = null;
/*  23 */   private String latitude = null;
/*     */   
/*     */   public String getCameraID() {
/*  26 */     return this.cameraID;
/*     */   }
/*     */   
/*     */   public void setCameraID(String cameraID) {
/*  30 */     this.cameraID = cameraID;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  34 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  38 */     this.name = name;
/*     */   }
/*     */   
/*     */   public int getType() {
/*  42 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(int type) {
/*  46 */     this.type = type;
/*     */   }
/*     */   
/*     */   public String getDeviceID() {
/*  50 */     return this.deviceID;
/*     */   }
/*     */   
/*     */   public void setDeviceID(String deviceID) {
/*  54 */     this.deviceID = deviceID;
/*     */   }
/*     */   
/*     */   public String getDeviceName() {
/*  58 */     return this.deviceName;
/*     */   }
/*     */   
/*     */   public void setDeviceName(String deviceName) {
/*  62 */     this.deviceName = deviceName;
/*     */   }
/*     */   
/*     */   public int getPointType() {
/*  66 */     return this.pointType;
/*     */   }
/*     */   
/*     */   public void setPointType(int pointType) {
/*  70 */     this.pointType = pointType;
/*     */   }
/*     */   
/*     */   public boolean isOnline() {
/*  74 */     return this.isOnline;
/*     */   }
/*     */   
/*     */   public void setOnline(boolean isOnline) {
/*  78 */     this.isOnline = isOnline;
/*     */   }
/*     */   
/*     */   public List<Integer> getUserCapability() {
/*  82 */     return this.userCapability;
/*     */   }
/*     */   
/*     */   public void setUserCapability(List<Integer> userCapability) {
/*  86 */     this.userCapability = userCapability;
/*     */   }
/*     */   
/*     */   public String getLongitude() {
/*  90 */     return this.longitude;
/*     */   }
/*     */   
/*     */   public void setLongitude(String longitude) {
/*  94 */     this.longitude = longitude;
/*     */   }
/*     */   
/*     */   public String getLatitude() {
/*  98 */     return this.latitude;
/*     */   }
/*     */   
/*     */   public void setLatitude(String latitude) {
/* 102 */     this.latitude = latitude;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\GISCameraInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */