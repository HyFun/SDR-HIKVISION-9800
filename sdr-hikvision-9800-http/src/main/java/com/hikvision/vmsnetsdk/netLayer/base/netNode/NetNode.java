/*    */ package com.hikvision.vmsnetsdk.netLayer.base.netNode;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NetNode
/*    */ {
/* 11 */   private String ip = null;
/*    */   
/* 13 */   private int port = 0;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public NetNode(String ip, int port)
/*    */   {
/* 23 */     this.port = port;
/* 24 */     this.ip = ip;
/*    */   }
/*    */   
/*    */   public String getIp() {
/* 28 */     return this.ip;
/*    */   }
/*    */   
/*    */   public int getPort() {
/* 32 */     return this.port;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\base\netNode\NetNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */