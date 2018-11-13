/*     */ package com.hikvision.vmsnetsdk.netLayer.msp.cameraList;
/*     */ 
/*     */

import com.hikvision.vmsnetsdk.CNetSDKLog;

import java.util.List;

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
/*     */ public class Camera
/*     */ {
/*     */   private String id;
/*     */   private String name;
/*     */   private int type;
/*     */   private boolean isOnline;
/*     */   private List<Integer> userCapability;
/*     */   private int collectedFlag;
/*     */   private int groupID;
/*     */   private int cascadeFlag;
/*     */   private static final int ONLINE = 1;
/*     */   private static final int OFFLINE = 0;
/*     */   private static final String TAG = "Camera";
/*     */   public static final int COLLECTED_FLAG_ADDED = 1;
/*     */   public static final int COLLECTED_FLAG_UNADDED = 0;
/*     */   public static final int CAMERA_TYPE_UNKNOWN = -1;
/*     */   public static final int CAMERA_TYPE_BOX_CAMERA = 0;
/*     */   public static final int CAMERA_TYPE_DOME_CAMERA = 1;
/*     */   public static final int CAMERA_TYPE_FAST_CAMERA = 2;
/*     */   public static final int CAMERA_TYPE_PTZ_CAMERA = 3;
/*     */   private String pictureUrl;
/*     */   private double longitude;
/*     */   private double latitude;
/*     */   
/*     */   public String getId()
/*     */   {
/*  70 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  74 */     this.id = id;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/*  84 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  88 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getType()
/*     */   {
/*  98 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(int type) {
/* 102 */     this.type = type;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isOnline()
/*     */   {
/* 112 */     return this.isOnline;
/*     */   }
/*     */   
/*     */   public boolean setOnline(int isOnline) {
/* 116 */     if (isOnline == 1) {
/* 117 */       this.isOnline = true;
/* 118 */       return true; }
/* 119 */     if (isOnline == 0) {
/* 120 */       this.isOnline = false;
/* 121 */       return true;
/*     */     }
/* 123 */     CNetSDKLog.e("Camera", "setOnline,isOnline can not be other value.");
/*     */     
/* 125 */     return false;
/*     */   }
/*     */   
/*     */   public void setOnline(boolean isOnline) {
/* 129 */     this.isOnline = isOnline;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<Integer> getUserCapability()
/*     */   {
/* 139 */     return this.userCapability;
/*     */   }
/*     */   
/*     */   public void setUserCapability(List<Integer> userCapability) {
/* 143 */     this.userCapability = userCapability;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getCollectedFlag()
/*     */   {
/* 153 */     return this.collectedFlag;
/*     */   }
/*     */   
/*     */   public void setCollectedFlag(int collectedFlag) {
/* 157 */     this.collectedFlag = collectedFlag;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getGroupID()
/*     */   {
/* 167 */     return this.groupID;
/*     */   }
/*     */   
/*     */   public void setGroupID(int groupID) {
/* 171 */     this.groupID = groupID;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getCascadeFlag()
/*     */   {
/* 181 */     return this.cascadeFlag;
/*     */   }
/*     */   
/*     */   public void setCascadeFlag(int cascadeFlag) {
/* 185 */     this.cascadeFlag = cascadeFlag;
/*     */   }
/*     */   
/*     */   public String getPictureUrl()
/*     */   {
/* 190 */     return this.pictureUrl;
/*     */   }
/*     */   
/*     */   public void setPictureUrl(String pictureUrl)
/*     */   {
/* 195 */     this.pictureUrl = pictureUrl;
/*     */   }
/*     */   
/*     */   public double getLongitude()
/*     */   {
/* 200 */     return this.longitude;
/*     */   }
/*     */   
/*     */   public void setLongitude(double longitude)
/*     */   {
/* 205 */     this.longitude = longitude;
/*     */   }
/*     */   
/*     */   public double getLatitude()
/*     */   {
/* 210 */     return this.latitude;
/*     */   }
/*     */   
/*     */   public void setLatitude(double latitude)
/*     */   {
/* 215 */     this.latitude = latitude;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\cameraList\Camera.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */