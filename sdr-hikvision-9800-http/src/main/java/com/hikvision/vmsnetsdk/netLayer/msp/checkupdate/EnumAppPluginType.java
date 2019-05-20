/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.checkupdate;
/*    */ 
/*    */ public enum EnumAppPluginType {
/*  4 */   TYPE_APK(1), 
/*  5 */   TYPE_MORE(2), 
/*  6 */   TYPE_WEB(3), 
/*  7 */   TYPE_BLANK(4);
/*    */   
/*  9 */   private int mCode = 0;
/*    */   
/*    */   private EnumAppPluginType(int code) {
/* 12 */     this.mCode = code;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 17 */     return String.valueOf(this.mCode);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\checkupdate\EnumAppPluginType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */