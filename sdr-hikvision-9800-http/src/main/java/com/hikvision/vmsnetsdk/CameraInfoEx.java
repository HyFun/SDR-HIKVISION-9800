/*    */ package com.hikvision.vmsnetsdk;
/*    */ 
/*    */

import com.hikvision.vmsnetsdk.netLayer.msp.cameraInfo.CameraDetailInfo;

import java.util.ArrayList;
import java.util.List;

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
/*    */ public class CameraInfoEx
/*    */   extends CameraDetailInfo
/*    */ {
/* 18 */   private boolean isPTZControl = false;
/*    */   
/* 20 */   private String acsIP = null;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private int acsPort;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setCameraInfoEx(CameraDetailInfo cameraDetailInfo)
/*    */   {
/* 34 */     if (cameraDetailInfo != null)
/*    */     {
/* 36 */       setCascadeFlag(cameraDetailInfo.getCascadeFlag());
/* 37 */       setChannelNo(cameraDetailInfo.getChannelNo());
/* 38 */       setCollectedFlag(cameraDetailInfo.getCollectedFlag());
/* 39 */       setDeviceId(cameraDetailInfo.getDeviceId());
/* 40 */       setDeviceNetId(cameraDetailInfo.getDeviceNetId());
/* 41 */       setGroupId(cameraDetailInfo.getGroupId());
/* 42 */       setId(cameraDetailInfo.getId());
/* 43 */       setLatitude(cameraDetailInfo.getLatitude());
/* 44 */       setLongitude(cameraDetailInfo.getLongitude());
/* 45 */       setName(cameraDetailInfo.getName());
/* 46 */       setRecordPos(cameraDetailInfo.getRecordPos());
/* 47 */       setType(cameraDetailInfo.getType());
/* 48 */       setUserCapability(cameraDetailInfo.getUserCapability());
/* 49 */       setOnline(cameraDetailInfo.isOnline());
/* 50 */       setCPosition(cameraDetailInfo.getCPosition());
/*    */       
/* 52 */       List<Integer> recordPos = new ArrayList();
/* 53 */       recordPos.add(Integer.valueOf(2));
/* 54 */       setRecordPos(recordPos);
/*    */       
/*    */ 
/* 57 */       if (cameraDetailInfo.getUserCapability() != null) {
/* 58 */         this.isPTZControl = cameraDetailInfo.getUserCapability().contains(CameraDetailInfo.USER_CAPABILITY_PTZ_CONTROL);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean isPTZControl()
/*    */   {
/* 65 */     return this.isPTZControl;
/*    */   }
/*    */   
/*    */   public void setPTZControl(boolean isPTZControl) {
/* 69 */     this.isPTZControl = isPTZControl;
/*    */   }
/*    */   
/*    */   public String getAcsIP() {
/* 73 */     return this.acsIP;
/*    */   }
/*    */   
/*    */   public void setAcsIP(String acsIP) {
/* 77 */     this.acsIP = acsIP;
/*    */   }
/*    */   
/*    */   public int getAcsPort() {
/* 81 */     return this.acsPort;
/*    */   }
/*    */   
/*    */   public void setAcsPort(int acsPort) {
/* 85 */     this.acsPort = acsPort;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\CameraInfoEx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */