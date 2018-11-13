/*     */ package com.hikvision.vmsnetsdk;
/*     */ 
/*     */

import com.hikvision.vmsnetsdk.netLayer.msp.cameraInfo.CameraDetailInfo;
import com.hikvision.vmsnetsdk.netLayer.msp.cameraList.Camera;

import java.util.ArrayList;
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
/*     */ public class CameraInfo
/*     */   extends Camera
/*     */ {
/*     */   private boolean isPTZControl;
/*     */   private String deviceID;
/*     */   private int channelNo;
/*  40 */   private List<Integer> recordPos = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */   private String acsIP;
/*     */   
/*     */ 
/*     */ 
/*     */   private int acsPort;
/*     */   
/*     */ 
/*     */   private String deviceIndexCode;
/*     */   
/*     */ 
/*     */   private int audioEncodeType;
/*     */   
/*     */ 
/*     */   private String guid;
/*     */   
/*     */ 
/*     */ 
/*     */   public String getDeviceID()
/*     */   {
/*  63 */     return this.deviceID;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<Integer> getRecordPos()
/*     */   {
/*  73 */     return this.recordPos;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRecordPos(List<Integer> recordPos)
/*     */   {
/*  83 */     this.recordPos = recordPos;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDeviceID(String deviceID)
/*     */   {
/*  93 */     this.deviceID = deviceID;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getChannelNo()
/*     */   {
/* 103 */     return this.channelNo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setChannelNo(int channelNo)
/*     */   {
/* 113 */     this.channelNo = channelNo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCameraInfo(Camera camera)
/*     */   {
/* 123 */     setCascadeFlag(camera.getCascadeFlag());
/* 124 */     setCollectedFlag(camera.getCollectedFlag());
/* 125 */     setGroupID(camera.getGroupID());
/* 126 */     setId(camera.getId());
/* 127 */     setName(camera.getName());
/* 128 */     setOnline(camera.isOnline());
/* 129 */     setType(camera.getType());
/* 130 */     setUserCapability(camera.getUserCapability());
/*     */     
/*     */ 
/* 133 */     this.recordPos.add(Integer.valueOf(2));
/*     */     
/*     */ 
/* 136 */     if (camera.getUserCapability() != null) {
/* 137 */       this.isPTZControl = camera.getUserCapability().contains(CameraDetailInfo.USER_CAPABILITY_PTZ_CONTROL);
/*     */     }
/* 139 */     this.deviceID = null;
/* 140 */     this.channelNo = 0;
/* 141 */     this.acsIP = null;
/* 142 */     this.acsPort = 0;
/*     */   }
/*     */   
/*     */   public boolean isPTZControl()
/*     */   {
/* 147 */     return this.isPTZControl;
/*     */   }
/*     */   
/*     */   public void setPTZControl(boolean isPTZControl) {
/* 151 */     this.isPTZControl = isPTZControl;
/*     */   }
/*     */   
/*     */   public String getAcsIP() {
/* 155 */     return this.acsIP;
/*     */   }
/*     */   
/*     */   public void setAcsIP(String acsIP) {
/* 159 */     this.acsIP = acsIP;
/*     */   }
/*     */   
/*     */   public int getAcsPort() {
/* 163 */     return this.acsPort;
/*     */   }
/*     */   
/*     */   public void setAcsPort(int acsPort) {
/* 167 */     this.acsPort = acsPort;
/*     */   }
/*     */   
/*     */   public String getDeviceIndexCode()
/*     */   {
/* 172 */     return this.deviceIndexCode;
/*     */   }
/*     */   
/*     */   public void setDeviceIndexCode(String deviceIndexCode)
/*     */   {
/* 177 */     this.deviceIndexCode = deviceIndexCode;
/*     */   }
/*     */   
/*     */   public int getAudioEncodeType()
/*     */   {
/* 182 */     return this.audioEncodeType;
/*     */   }
/*     */   
/*     */   public void setAudioEncodeType(int audioEncodeType)
/*     */   {
/* 187 */     this.audioEncodeType = audioEncodeType;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setGuid(String guid)
/*     */   {
/* 193 */     this.guid = guid;
/*     */   }
/*     */   
/*     */   public String getGuid() {
/* 197 */     return this.guid;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\CameraInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */