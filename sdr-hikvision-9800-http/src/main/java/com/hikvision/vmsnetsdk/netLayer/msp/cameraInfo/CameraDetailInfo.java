/*     */ package com.hikvision.vmsnetsdk.netLayer.msp.cameraInfo;
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
/*     */ public class CameraDetailInfo
/*     */ {
/*     */   private String id;
/*     */   private String name;
/*     */   private String deviceId;
/*     */   private String deviceIndexCode;
/*     */   private int audioEncodeType;
/*     */   private int channelNo;
/*  36 */   private int type = -1;
/*     */   
/*     */   private boolean isOnline;
/*     */   
/*     */   private List<Integer> userCapability;
/*     */   
/*     */   private List<Integer> recordPos;
/*     */   
/*     */   private int collectedFlag;
/*     */   
/*     */   private int groupId;
/*     */   
/*     */   private int cascadeFlag;
/*     */   
/*     */   private String longitude;
/*     */   
/*     */   private String latitude;
/*     */   
/*     */   private String CPosition;
/*     */   
/*     */   private int deviceNetId;
/*     */   
/*     */   public static final int MONITOR_POINT_TYPE_BLOT = 0;
/*     */   
/*     */   public static final int MONITOR_POINT_TYPE_HALFSPHERE = 1;
/*     */   
/*     */   public static final int MONITOR_POINT_TYPE_FASTBALL = 2;
/*     */   
/*     */   public static final int MONITOR_POINT_TYPE_PTZ = 3;
/*     */   
/*     */   private static final int ONLINE = 1;
/*     */   
/*     */   private static final int OFFLINE = 0;
/*     */   
/*     */   private static final String TAG = "BonetInfo";
/*  71 */   public static final Integer USER_CAPABILITY_PREVIEW = Integer.valueOf(1);
/*  72 */   public static final Integer USER_CAPABILITY_PLAYBACK = Integer.valueOf(2);
/*  73 */   public static final Integer USER_CAPABILITY_MODIFY_POSITION = Integer.valueOf(3);
/*  74 */   public static final Integer USER_CAPABILITY_PTZ_CONTROL = Integer.valueOf(4);
/*     */   
/*     */   public static final int RECORD_POS_NOPLAN = -1;
/*     */   
/*     */   public static final int RECORD_POS_IPSAN = 0;
/*     */   public static final int RECORD_POS_DEVICE = 1;
/*     */   public static final int RECORD_POS_PCNVR = 2;
/*     */   public static final int RECORD_POS_ENVR = 3;
/*     */   public static final int RECORD_POS_CISCO = 4;
/*     */   public static final int RECORD_POS_DSNVR = 5;
/*     */   public static final int RECORD_POS_CVR = 7;
/*     */   
/*     */   public String getCPosition()
/*     */   {
/*  88 */     return this.CPosition;
/*     */   }
/*     */   
/*     */   public void setCPosition(String cPosition) {
/*  92 */     this.CPosition = cPosition;
/*     */   }
/*     */   
/*     */   public String getId() {
/*  96 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/* 100 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 104 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 108 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getDeviceId() {
/* 112 */     return this.deviceId;
/*     */   }
/*     */   
/*     */   public void setDeviceId(String deviceId) {
/* 116 */     this.deviceId = deviceId;
/*     */   }
/*     */   
/*     */   public int getChannelNo() {
/* 120 */     return this.channelNo;
/*     */   }
/*     */   
/*     */   public void setChannelNo(int channelNo) {
/* 124 */     this.channelNo = channelNo;
/*     */   }
/*     */   
/*     */   public int getType() {
/* 128 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(int type) {
/* 132 */     this.type = type;
/*     */   }
/*     */   
/*     */   public boolean isOnline() {
/* 136 */     return this.isOnline;
/*     */   }
/*     */   
/*     */   public boolean setOnline(int isOnline) {
/* 140 */     if (isOnline == 1) {
/* 141 */       this.isOnline = true;
/* 142 */       return true; }
/* 143 */     if (isOnline == 0) {
/* 144 */       this.isOnline = false;
/* 145 */       return true;
/*     */     }
/* 147 */     CNetSDKLog.e("BonetInfo", "setOnline,isOnline can not be other value.");
/*     */     
/* 149 */     return false;
/*     */   }
/*     */   
/*     */   public void setOnline(boolean isOnline) {
/* 153 */     this.isOnline = isOnline;
/*     */   }
/*     */   
/*     */   public List<Integer> getUserCapability() {
/* 157 */     return this.userCapability;
/*     */   }
/*     */   
/*     */   public void setUserCapability(List<Integer> userCapability) {
/* 161 */     this.userCapability = userCapability;
/*     */   }
/*     */   
/*     */   public List<Integer> getRecordPos() {
/* 165 */     return this.recordPos;
/*     */   }
/*     */   
/*     */   public void setRecordPos(List<Integer> recordPos) {
/* 169 */     this.recordPos = recordPos;
/*     */   }
/*     */   
/*     */   public int getCollectedFlag() {
/* 173 */     return this.collectedFlag;
/*     */   }
/*     */   
/*     */   public void setCollectedFlag(int collectedFlag) {
/* 177 */     this.collectedFlag = collectedFlag;
/*     */   }
/*     */   
/*     */   public int getGroupId() {
/* 181 */     return this.groupId;
/*     */   }
/*     */   
/*     */   public void setGroupId(int groupId) {
/* 185 */     this.groupId = groupId;
/*     */   }
/*     */   
/*     */   public int getCascadeFlag() {
/* 189 */     return this.cascadeFlag;
/*     */   }
/*     */   
/*     */   public void setCascadeFlag(int cascadeFlag) {
/* 193 */     this.cascadeFlag = cascadeFlag;
/*     */   }
/*     */   
/*     */   public String getLongitude() {
/* 197 */     return this.longitude;
/*     */   }
/*     */   
/*     */   public void setLongitude(String longitude) {
/* 201 */     this.longitude = longitude;
/*     */   }
/*     */   
/*     */   public String getLatitude() {
/* 205 */     return this.latitude;
/*     */   }
/*     */   
/*     */   public void setLatitude(String latitude) {
/* 209 */     this.latitude = latitude;
/*     */   }
/*     */   
/*     */   public int getDeviceNetId() {
/* 213 */     return this.deviceNetId;
/*     */   }
/*     */   
/*     */   public void setDeviceNetId(int deviceNetId) {
/* 217 */     this.deviceNetId = deviceNetId;
/*     */   }
/*     */   
/*     */   public String getDeviceIndexCode()
/*     */   {
/* 222 */     return this.deviceIndexCode;
/*     */   }
/*     */   
/*     */   public void setDeviceIndexCode(String deviceIndexCode)
/*     */   {
/* 227 */     this.deviceIndexCode = deviceIndexCode;
/*     */   }
/*     */   
/*     */   public int getAudioEncodeType()
/*     */   {
/* 232 */     return this.audioEncodeType;
/*     */   }
/*     */   
/*     */   public void setAudioEncodeType(int audioEncodeType)
/*     */   {
/* 237 */     this.audioEncodeType = audioEncodeType;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\cameraInfo\CameraDetailInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */