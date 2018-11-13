/*    */ package com.hikvision.vmsnetsdk.netLayer.msp;
/*    */ 
/*    */ import com.hikvision.vmsnetsdk.netLayer.base.NetHttpResponse;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class MspResponse
/*    */   extends NetHttpResponse
/*    */ {
/*    */   public MspResponse(String responseData)
/*    */   {
/* 13 */     super(responseData);
/*    */   }
/*    */   
/*    */   public abstract MspBackState getMspBackState();
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\MspResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */