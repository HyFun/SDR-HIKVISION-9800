/*    */ package com.hikvision.vmsnetsdk.netLayer.base;
/*    */ 
/*    */ import com.hikvision.vmsnetsdk.util.intf.IRequestTool;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class NetHttpRequest
/*    */ {
/*    */   protected String backStr;
/*    */   protected IRequestTool iRequestTool;
/*    */   
/*    */   public NetHttpRequest(NetHttpServer server, IRequestTool iRequestTool)
/*    */   {
/* 16 */     this.iRequestTool = iRequestTool;
/*    */   }
/*    */   
/*    */   public NetHttpRequest(IRequestTool iRequestTool) {
/* 20 */     this.iRequestTool = iRequestTool;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public abstract boolean request();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public abstract String getRequestAddr();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public abstract String getRequestData();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getBackString()
/*    */   {
/* 53 */     return this.backStr;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\base\NetHttpRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */