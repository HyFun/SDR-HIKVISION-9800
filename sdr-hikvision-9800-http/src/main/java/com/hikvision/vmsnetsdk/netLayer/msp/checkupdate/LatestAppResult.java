/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.checkupdate;
/*    */ 
/*    */ import com.hikvision.vmsnetsdk.PlugInfo;

/*    */
/*    */ public class LatestAppResult extends RequestResult
/*    */ {
/*  7 */   private PlugInfo mAppInfo = null;
/*    */   
/*    */   public PlugInfo getAppInfo()
/*    */   {
/* 11 */     return this.mAppInfo;
/*    */   }
/*    */   
/*    */   public void setAppInfo(PlugInfo info)
/*    */   {
/* 16 */     this.mAppInfo = info;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\checkupdate\LatestAppResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */