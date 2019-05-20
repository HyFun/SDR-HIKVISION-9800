/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.deviceGps;
/*    */ 
/*    */ 
/*    */ public class DeviceGpsParam
/*    */ {
/*    */   private String indexCode;
/*    */   
/*    */   private int topLimit;
/*    */   
/*    */   private long startTime;
/*    */   
/*    */   private long endTime;
/*    */   
/*    */   private String sessionId;
/*    */   
/*    */ 
/*    */   public String getSessionId()
/*    */   {
/* 19 */     return this.sessionId;
/*    */   }
/*    */   
/*    */   public void setSessionId(String ssionId) {
/* 23 */     this.sessionId = ssionId;
/*    */   }
/*    */   
/*    */   public String getIndexCode() {
/* 27 */     return this.indexCode;
/*    */   }
/*    */   
/*    */   public void setIndexCode(String indexCode) {
/* 31 */     this.indexCode = indexCode;
/*    */   }
/*    */   
/*    */   public long getStartTime() {
/* 35 */     return this.startTime;
/*    */   }
/*    */   
/*    */   public void setStartTime(long startTime) {
/* 39 */     this.startTime = startTime;
/*    */   }
/*    */   
/*    */   public long getEndTime() {
/* 43 */     return this.endTime;
/*    */   }
/*    */   
/*    */   public int getTopLimit() {
/* 47 */     return this.topLimit;
/*    */   }
/*    */   
/*    */   public void setTopLimit(int topLimit) {
/* 51 */     this.topLimit = topLimit;
/*    */   }
/*    */   
/*    */   public void setEndTime(long endTime) {
/* 55 */     this.endTime = endTime;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\deviceGps\DeviceGpsParam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */