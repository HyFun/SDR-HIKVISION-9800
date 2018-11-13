/*    */ package com.hikvision.vmsnetsdk;
/*    */ 
/*    */ import com.hikvision.vmsnetsdk.netLayer.msp.msg.detail.MsgDetail;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SDKAlarmBulletinDetail
/*    */   extends SDKBulletinDetail
/*    */ {
/*    */   private String cameraID;
/*    */   private String pictureUrl;
/* 15 */   private double longitude = -6.516E7D;
/*    */   
/* 17 */   private double latitude = -3.276E7D;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private EventInfo linkageCameraList;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setAlarmSDKBulletinDetail(MsgDetail msgDetail)
/*    */   {
/* 30 */     if (msgDetail == null) {
/* 31 */       return;
/*    */     }
/*    */     
/* 34 */     setSDKBulletinDetail(msgDetail);
/*    */     
/* 36 */     setCameraID(msgDetail.getCameraID());
/* 37 */     setLatitude(msgDetail.getLatitude());
/* 38 */     setLongitude(msgDetail.getLongitude());
/* 39 */     setPictureUrl(msgDetail.getPictureUrl());
/* 40 */     setLinkageCameraList(msgDetail.getLinkageCameraList());
/*    */   }
/*    */   
/*    */   public void setLinkageCameraList(EventInfo linkageCameraList)
/*    */   {
/* 45 */     this.linkageCameraList = linkageCameraList;
/*    */   }
/*    */   
/* 48 */   public EventInfo getLinkageCameraList() { return this.linkageCameraList; }
/*    */   
/*    */   public String getCameraID() {
/* 51 */     return this.cameraID;
/*    */   }
/*    */   
/*    */   public void setCameraID(String cameraID) {
/* 55 */     this.cameraID = cameraID;
/*    */   }
/*    */   
/*    */   public String getPictureUrl() {
/* 59 */     return this.pictureUrl;
/*    */   }
/*    */   
/*    */   public void setPictureUrl(String pictureUrl) {
/* 63 */     this.pictureUrl = pictureUrl;
/*    */   }
/*    */   
/*    */   public double getLongitude() {
/* 67 */     return this.longitude;
/*    */   }
/*    */   
/*    */   public void setLongitude(double longitude) {
/* 71 */     this.longitude = longitude;
/*    */   }
/*    */   
/*    */   public double getLatitude() {
/* 75 */     return this.latitude;
/*    */   }
/*    */   
/*    */   public void setLatitude(double latitude) {
/* 79 */     this.latitude = latitude;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\SDKAlarmBulletinDetail.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */