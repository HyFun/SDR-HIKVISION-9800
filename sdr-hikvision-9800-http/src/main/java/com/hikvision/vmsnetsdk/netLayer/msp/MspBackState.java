/*    */ package com.hikvision.vmsnetsdk.netLayer.msp;
/*    */ 
/*    */ import com.hikvision.vmsnetsdk.netLayer.base.NetHttpBackState;

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
/*    */ public abstract class MspBackState
/*    */   extends NetHttpBackState
/*    */ {
/*    */   public static final int CODE_SUCCESS = 200;
/*    */   protected static final int CODE_NO_DATA = 201;
/*    */   protected static final int CODE_PARAM_ERROR = 205;
/*    */   protected static final int CODE_SESSION_ERROR = 206;
/*    */   protected static final int CODE_NO_SUPPORT = 207;
/*    */   protected static final int CODE_USER_NOT_EXIST = 220;
/*    */   protected static final int CODE_PASSWORD_ERROR = 221;
/*    */   protected static final int CODE_DEVICE_NOT_EXIST = 222;
/*    */   protected static final int CODE_CONTROLUNIT_UNEXIST = 225;
/*    */   protected static final int CODE_NO_PERMISSION = 228;
/*    */   protected static final int CODE_EXCEPTION = 230;
/*    */   protected static final int CODE_ERROR = 231;
/*    */   protected static final int CODE_USER_IS_FROZEN = 240;
/*    */   protected static final int CODE_USER_HAS_LOGGED_IN = 241;
/*    */   protected static final int CODE_USER_PASSWORD_ERROR = 20005;
/*    */   protected static final int CODE_ORI_PASSWORD_ERROR = 20012;
/*    */   
/*    */   public static int adapterErrorCode(int code)
/*    */   {
/* 41 */     switch (code) {
/*    */     case 201: 
/* 43 */       return 160;
/*    */     case 205: 
/* 45 */       return 161;
/*    */     case 206: 
/* 47 */       return 162;
/*    */     case 207: 
/* 49 */       return 207;
/*    */     case 220: 
/* 51 */       return 163;
/*    */     case 221: 
/* 53 */       return 164;
/*    */     case 222: 
/* 55 */       return 165;
/*    */     
/*    */     case 225: 
/* 58 */       return 168;
/*    */     case 228: 
/* 60 */       return 171;
/*    */     case 230: 
/* 62 */       return 230;
/*    */     case 231: 
/* 64 */       return 199;
/*    */     case 241: 
/* 66 */       return 174;
/*    */     case 240: 
/* 68 */       return 173;
/*    */     }
/*    */     
/* 71 */     return code;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\MspBackState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */