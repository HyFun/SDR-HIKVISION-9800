/*     */ package com.hikvision.vmsnetsdk.netLayer.msp.bonetInfo;
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
/*     */ public class BonetInfo
/*     */ {
/*     */   private String id;
/*     */   private String name;
/*     */   private String deviceId;
/*     */   private int channelNo;
/*  19 */   private int type = -1;
/*     */   
/*     */   private boolean isOnline;
/*     */   
/*     */   private boolean isPTZControl;
/*     */   
/*     */   private List<Integer> userCapability;
/*     */   
/*     */   private List<Integer> recordPos;
/*     */   
/*     */   private String acsIp;
/*     */   
/*     */   private int acsPort;
/*     */   
/*     */   private int collectedFlag;
/*     */   private int groupId;
/*     */   private int cascadeFlag;
/*     */   private String longitude;
/*     */   private String latitude;
/*     */   public static final int MONITOR_POINT_TYPE_BLOT = 0;
/*     */   public static final int MONITOR_POINT_TYPE_HALFSPHERE = 1;
/*     */   public static final int MONITOR_POINT_TYPE_FASTBALL = 2;
/*     */   public static final int MONITOR_POINT_TYPE_PTZ = 3;
/*     */   private static final int ONLINE = 1;
/*     */   private static final int OFFLINE = 0;
/*     */   private static final int PTZ_PERMISSION_TURE = 1;
/*     */   private static final int PTZ_PERMISSION_FALSE = 0;
/*     */   private static final String TAG = "BonetInfo";
/*     */   public static final int USER_CAPABILITY_PREVIEW = 1;
/*     */   public static final int USER_CAPABILITY_PLAYBACK = 2;
/*     */   public static final int USER_CAPABILITY_MODIFY_POSITION = 3;
/*     */   public static final int USER_CAPABILITY_PTZ_CONTROL = 4;
/*     */   public static final int RECORD_POS_NOPLAN = -1;
/*     */   public static final int RECORD_POS_IPSAN = 0;
/*     */   public static final int RECORD_POS_DEVICE = 1;
/*     */   public static final int RECORD_POS_PCNVR = 2;
/*     */   public static final int RECORD_POS_ENVR = 3;
/*     */   public static final int RECORD_POS_CISCO = 4;
/*     */   public static final int RECORD_POS_DSNVR = 5;
/*     */   public static final int RECORD_POS_CVR = 7;
/*     */   
/*     */   public String getId()
/*     */   {
/*  62 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  66 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  70 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  74 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getDeviceId() {
/*  78 */     return this.deviceId;
/*     */   }
/*     */   
/*     */   public void setDeviceId(String deviceId) {
/*  82 */     this.deviceId = deviceId;
/*     */   }
/*     */   
/*     */   public int getChannelNo() {
/*  86 */     return this.channelNo;
/*     */   }
/*     */   
/*     */   public void setChannelNo(int channelNo) {
/*  90 */     this.channelNo = channelNo;
/*     */   }
/*     */   
/*     */   public int getType() {
/*  94 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(int type) {
/*  98 */     this.type = type;
/*     */   }
/*     */   
/*     */   public boolean isOnline() {
/* 102 */     return this.isOnline;
/*     */   }
/*     */   
/*     */   public boolean setOnline(int isOnline) {
/* 106 */     if (isOnline == 1) {
/* 107 */       this.isOnline = true;
/* 108 */       return true; }
/* 109 */     if (isOnline == 0) {
/* 110 */       this.isOnline = false;
/* 111 */       return true;
/*     */     }
/* 113 */     CNetSDKLog.e("BonetInfo", "setOnline,isOnline can not be other value.");
/*     */     
/* 115 */     return false;
/*     */   }
/*     */   
/*     */   public void setOnline(boolean isOnline) {
/* 119 */     this.isOnline = isOnline;
/*     */   }
/*     */   
/*     */   public boolean isPTZControl() {
/* 123 */     return this.isPTZControl;
/*     */   }
/*     */   
/*     */   public boolean setPTZControl(int isPTZControl)
/*     */   {
/* 128 */     if (isPTZControl == 1) {
/* 129 */       this.isPTZControl = true;
/* 130 */       return true; }
/* 131 */     if (isPTZControl == 0) {
/* 132 */       this.isPTZControl = false;
/* 133 */       return true;
/*     */     }
/* 135 */     CNetSDKLog.e("BonetInfo", "setPTZControl,isOnline can not be other value.");
/*     */     
/* 137 */     return false;
/*     */   }
/*     */   
/*     */   public void setPTZControl(boolean isPTZControl) {
/* 141 */     this.isPTZControl = isPTZControl;
/*     */   }
/*     */   
/*     */   public List<Integer> getUserCapability() {
/* 145 */     return this.userCapability;
/*     */   }
/*     */   
/*     */   public void setUserCapability(List<Integer> userCapability) {
/* 149 */     this.userCapability = userCapability;
/*     */   }
/*     */   
/*     */   public List<Integer> getRecordPos() {
/* 153 */     return this.recordPos;
/*     */   }
/*     */   
/*     */   public void setRecordPos(List<Integer> recordPos) {
/* 157 */     this.recordPos = recordPos;
/*     */   }
/*     */   
/*     */   public String getAcsIp() {
/* 161 */     return this.acsIp;
/*     */   }
/*     */   
/*     */   public void setAcsIp(String acsIp) {
/* 165 */     this.acsIp = acsIp;
/*     */   }
/*     */   
/*     */   public int getAcsPort() {
/* 169 */     return this.acsPort;
/*     */   }
/*     */   
/*     */   public void setAcsPort(int acsPort) {
/* 173 */     this.acsPort = acsPort;
/*     */   }
/*     */   
/*     */   public int getCollectedFlag() {
/* 177 */     return this.collectedFlag;
/*     */   }
/*     */   
/*     */   public void setCollectedFlag(int collectedFlag) {
/* 181 */     this.collectedFlag = collectedFlag;
/*     */   }
/*     */   
/*     */   public int getGroupId() {
/* 185 */     return this.groupId;
/*     */   }
/*     */   
/*     */   public void setGroupId(int groupId) {
/* 189 */     this.groupId = groupId;
/*     */   }
/*     */   
/*     */   public int getCascadeFlag() {
/* 193 */     return this.cascadeFlag;
/*     */   }
/*     */   
/*     */   public void setCascadeFlag(int cascadeFlag) {
/* 197 */     this.cascadeFlag = cascadeFlag;
/*     */   }
/*     */   
/*     */   public String getLongitude() {
/* 201 */     return this.longitude;
/*     */   }
/*     */   
/*     */   public void setLongitude(String longitude) {
/* 205 */     this.longitude = longitude;
/*     */   }
/*     */   
/*     */   public String getLatitude() {
/* 209 */     return this.latitude;
/*     */   }
/*     */   
/*     */   public void setLatitude(String latitude) {
/* 213 */     this.latitude = latitude;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\bonetInfo\BonetInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */