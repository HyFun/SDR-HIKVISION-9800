/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.deviceInfo;
/*    */ 
/*    */ import com.hikvision.vmsnetsdk.netLayer.msp.MspBackState;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DeviceInfoBackState
/*    */   extends MspBackState
/*    */ {
/*    */   public static final int CODE_NO_DATA = 201;
/*    */   public static final int CODE_PARAM_ERROR = 205;
/*    */   public static final int CODE_SESSION_ERROR = 206;
/*    */   public static final int CODE_NO_SUPPORT = 207;
/*    */   public static final int CODE_EXCEPTION = 230;
/*    */   public static final int CODE_ERROR = 231;
/*    */   public static final int CODE_DEVICE_NOT_EXIST = 222;
/*    */   
/*    */   public boolean isSuccess()
/*    */   {
/* 23 */     if (this.code == 200) {
/* 24 */       return true;
/*    */     }
/* 26 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\deviceInfo\DeviceInfoBackState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */