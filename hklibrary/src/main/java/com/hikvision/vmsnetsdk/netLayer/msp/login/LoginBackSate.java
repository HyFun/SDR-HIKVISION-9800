/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.login;
/*    */ 
/*    */ import com.hikvision.vmsnetsdk.netLayer.msp.MspBackState;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LoginBackSate
/*    */   extends MspBackState
/*    */ {
/*    */   public static final int CODE_USER_NOT_EXIST = 220;
/*    */   public static final int CODE_PASSWORD_ERROR = 221;
/*    */   public static final int CODE_EXCEPTION = 230;
/*    */   public static final int CODE_ERROR = 231;
/*    */   public static final int CODE_USER_IS_FROZEN = 240;
/*    */   public static final int CODE_USER_HAS_LOGGED_IN = 241;
/*    */   public static final int CODE_USER_PASSWORD_ERROR = 20005;
/*    */   public static final int CODE_SUCCESS_FIRST = 20030;
/*    */   public static final int CODE_SUCCESS_WEAK = 20031;
/*    */   public static final int CODE_SUCCESS_PAST = 20032;
/*    */   public static final int CODE_SUCCESS_PWD_ERR_3 = 20042;
/*    */   public static final int CODE_SUCCESS_PWD_ERR_5 = 20043;
/*    */   public static final int CODE_SUCCESS_VERIFCODE_ERR = 20044;
/*    */   public static final int CODE_SUCCESS_VERIFCODE_INVALIDE = 20045;
/*    */   
/*    */   public boolean isSuccess()
/*    */   {
/* 30 */     if ((this.code == 200) || (this.code == 20030) || (this.code == 20031) || (this.code == 20032) || (this.code == 20042) || (this.code == 20043) || (this.code == 20044) || (this.code == 20045))
/*    */     {
/*    */ 
/*    */ 
/* 34 */       return true;
/*    */     }
/* 36 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\login\LoginBackSate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */