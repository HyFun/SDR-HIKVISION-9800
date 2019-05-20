/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.checkupdate;
/*    */ 
/*    */ 
/*    */ public abstract class RequestResult
/*    */ {
/*  6 */   private int resultCode = 0;
/*    */   
/*    */ 
/*  9 */   private String resultDesrc = "no error";
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setResultCode(int code)
/*    */   {
/* 19 */     this.resultCode = code;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int getResultCode()
/*    */   {
/* 30 */     return this.resultCode;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setResultDesrc(String desrc)
/*    */   {
/* 41 */     this.resultDesrc = desrc;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getResultDesrc()
/*    */   {
/* 52 */     return this.resultDesrc;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\checkupdate\RequestResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */