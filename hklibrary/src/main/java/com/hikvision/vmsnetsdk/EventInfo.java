/*    */ package com.hikvision.vmsnetsdk;
/*    */ 
/*    */ import java.util.List;

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
/*    */ public class EventInfo
/*    */ {
/*    */   private String extInfo;
/*    */   private List<Trigger> triggerList;
/*    */   
/*    */   private String getExtInfo()
/*    */   {
/* 28 */     return this.extInfo;
/*    */   }
/*    */   
/*    */   public void setExtInfo(String extInfo) {
/* 32 */     this.extInfo = extInfo;
/*    */   }
/*    */   
/*    */   public List<Trigger> getTriggerList() {
/* 36 */     return this.triggerList;
/*    */   }
/*    */   
/*    */   public void setTriggerList(List<Trigger> triggerList) {
/* 40 */     this.triggerList = triggerList;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\EventInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */