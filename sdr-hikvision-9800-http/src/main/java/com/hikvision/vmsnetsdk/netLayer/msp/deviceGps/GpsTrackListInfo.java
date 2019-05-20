/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.deviceGps;
/*    */ 
/*    */ import java.util.List;

/*    */
/*    */ 
/*    */ public class GpsTrackListInfo
/*    */ {
/*    */   private List<GISTrackPoint> gpsTrackList;
/*    */   private int totalNum;
/*    */   private int realBackNum;
/*    */   
/*    */   public List<GISTrackPoint> getGpsTrackList()
/*    */   {
/* 14 */     return this.gpsTrackList;
/*    */   }
/*    */   
/*    */   public void setGpsTrackList(List<GISTrackPoint> gpsTrackList) {
/* 18 */     this.gpsTrackList = gpsTrackList;
/*    */   }
/*    */   
/*    */   public int getTotalNum() {
/* 22 */     return this.totalNum;
/*    */   }
/*    */   
/*    */   public void setTotalNum(int totalNum) {
/* 26 */     this.totalNum = totalNum;
/*    */   }
/*    */   
/*    */   public int getRealBackNum() {
/* 30 */     return this.realBackNum;
/*    */   }
/*    */   
/*    */   public void setRealBackNum(int realBackNum) {
/* 34 */     this.realBackNum = realBackNum;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\deviceGps\GpsTrackListInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */