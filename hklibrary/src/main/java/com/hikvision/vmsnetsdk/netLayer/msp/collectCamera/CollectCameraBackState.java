/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.collectCamera;
/*    */ 
/*    */ import com.hikvision.vmsnetsdk.netLayer.msp.MspBackState;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CollectCameraBackState
/*    */   extends MspBackState
/*    */ {
/*    */   public static final int CODE_SESSION_ERROR = 206;
/*    */   public static final int CODE_NO_SUPPORT = 207;
/*    */   public static final int CODE_EXCEPTION = 230;
/*    */   public static final int CODE_ERROR = 231;
/*    */   
/*    */   public boolean isSuccess()
/*    */   {
/* 20 */     if (this.code == 200) {
/* 21 */       return true;
/*    */     }
/* 23 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\collectCamera\CollectCameraBackState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */