/*    */ package com.hikvision.vmsnetsdk;
/*    */ 
/*    */ import android.util.Log;

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
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CNetSDKLog
/*    */ {
/*    */   public static void setLogOption(boolean option)
/*    */   {
/* 28 */     DEBUG = option;
/*    */   }
/*    */   
/* 31 */   private static boolean DEBUG = true;
/*    */   
/*    */   public static void d(String tag, String desc) {
/* 34 */     if (DEBUG)
/* 35 */       Log.d(tag, desc);
/*    */   }
/*    */   
/*    */   public static void v(String tag, String desc) {
/* 39 */     if (DEBUG)
/* 40 */       Log.v(tag, desc);
/*    */   }
/*    */   
/*    */   public static void w(String tag, String desc) {
/* 44 */     if (DEBUG)
/* 45 */       Log.w(tag, desc);
/*    */   }
/*    */   
/*    */   public static void i(String tag, String desc) {
/* 49 */     if (DEBUG)
/* 50 */       Log.i(tag, desc);
/*    */   }
/*    */   
/*    */   public static void e(String tag, String desc) {
/* 54 */     if (DEBUG) {
/* 55 */       Log.e(tag, desc);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\CNetSDKLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */