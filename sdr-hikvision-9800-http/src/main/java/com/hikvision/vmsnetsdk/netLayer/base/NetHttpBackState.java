/*    */ package com.hikvision.vmsnetsdk.netLayer.base;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class NetHttpBackState
/*    */ {
/* 10 */   protected int code = -1;
/*    */   protected String description;
/*    */   
/*    */   public void setCode(int code) {
/* 14 */     this.code = code;
/*    */   }
/*    */   
/*    */   public void setDescription(String description) {
/* 18 */     this.description = description;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public abstract boolean isSuccess();
/*    */   
/*    */ 
/*    */ 
/*    */   public int getCode()
/*    */   {
/* 30 */     return this.code;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getDescription()
/*    */   {
/* 40 */     return this.description;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\base\NetHttpBackState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */