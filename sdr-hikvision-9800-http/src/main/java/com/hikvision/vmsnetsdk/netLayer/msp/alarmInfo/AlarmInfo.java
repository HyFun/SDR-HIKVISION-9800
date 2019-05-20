/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.alarmInfo;
/*    */ 
/*    */ import java.util.List;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AlarmInfo
/*    */ {
/*    */   private String id;
/*    */   private String name;
/*    */   private List<Integer> userCapability;
/*    */   private String longitude;
/*    */   private String latitude;
/*    */   public static final int USER_CAPABILITY_PREVIEW = 1;
/*    */   public static final int USER_CAPABILITY_PLAYBACK = 2;
/*    */   public static final int USER_CAPABILITY_MODIFY_POSITION = 3;
/*    */   public static final int USER_CAPABILITY_PTZ_CONTROL = 4;
/*    */   
/*    */   public String getId()
/*    */   {
/* 26 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(String id) {
/* 30 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 34 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 38 */     this.name = name;
/*    */   }
/*    */   
/*    */   public List<Integer> getUserCapability() {
/* 42 */     return this.userCapability;
/*    */   }
/*    */   
/*    */   public void setUserCapability(List<Integer> userCapability) {
/* 46 */     this.userCapability = userCapability;
/*    */   }
/*    */   
/*    */   public String getLongitude() {
/* 50 */     return this.longitude;
/*    */   }
/*    */   
/*    */   public void setLongitude(String longitude) {
/* 54 */     this.longitude = longitude;
/*    */   }
/*    */   
/*    */   public String getLatitude() {
/* 58 */     return this.latitude;
/*    */   }
/*    */   
/*    */   public void setLatitude(String latitude) {
/* 62 */     this.latitude = latitude;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\alarmInfo\AlarmInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */