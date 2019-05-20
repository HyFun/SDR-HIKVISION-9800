/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.msg.detail;
/*    */ 
/*    */

import com.hikvision.vmsnetsdk.EventInfo;
import com.hikvision.vmsnetsdk.netLayer.msp.msg.Msg;

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
/*    */ 
/*    */ public class MsgDetail
/*    */   extends Msg
/*    */ {
/*    */   private String content;
/*    */   private String cameraID;
/*    */   private String pictureUrl;
/*    */   private String thumbPicUrl;
/*    */   private String mediaUrl;
/* 29 */   private double longitude = -6.516E7D;
/*    */   
/* 31 */   private double latitude = -3.276E7D;
/*    */   private EventInfo linkageCameraList;
/*    */   
/*    */   public String getContent() {
/* 35 */     return this.content;
/*    */   }
/*    */   
/*    */   public void setContent(String content) {
/* 39 */     this.content = content;
/*    */   }
/*    */   
/*    */   public String getCameraID() {
/* 43 */     return this.cameraID;
/*    */   }
/*    */   
/*    */   public void setCameraID(String cameraID) {
/* 47 */     this.cameraID = cameraID;
/*    */   }
/*    */   
/*    */   public String getPictureUrl() {
/* 51 */     return this.pictureUrl;
/*    */   }
/*    */   
/*    */   public void setPictureUrl(String pictureUrl) {
/* 55 */     this.pictureUrl = pictureUrl;
/*    */   }
/*    */   
/*    */   public String getThumbPicUrl() {
/* 59 */     return this.thumbPicUrl;
/*    */   }
/*    */   
/*    */   public void setThumbPicUrl(String thumbPicUrl) {
/* 63 */     this.thumbPicUrl = thumbPicUrl;
/*    */   }
/*    */   
/*    */   public String getMediaUrl() {
/* 67 */     return this.mediaUrl;
/*    */   }
/*    */   
/*    */   public void setMediaUrl(String mediaUrl) {
/* 71 */     this.mediaUrl = mediaUrl;
/*    */   }
/*    */   
/*    */   public double getLongitude() {
/* 75 */     return this.longitude;
/*    */   }
/*    */   
/*    */   public void setLongitude(double longitude) {
/* 79 */     this.longitude = longitude;
/*    */   }
/*    */   
/*    */   public double getLatitude() {
/* 83 */     return this.latitude;
/*    */   }
/*    */   
/*    */   public void setLatitude(double latitude) {
/* 87 */     this.latitude = latitude;
/*    */   }
/*    */   
/*    */   public EventInfo getLinkageCameraList()
/*    */   {
/* 92 */     return this.linkageCameraList;
/*    */   }
/*    */   
/*    */   public void setLinkageCameraList(EventInfo linkageCameraList)
/*    */   {
/* 97 */     this.linkageCameraList = linkageCameraList;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\msg\detail\MsgDetail.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */