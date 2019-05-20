/*    */ package com.hikvision.vmsnetsdk;
/*    */ 
/*    */ import com.hikvision.vmsnetsdk.netLayer.msp.controlUnitList.ControlUnit;

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
/*    */ 
/*    */ public class ControlUnitInfo
/*    */   extends ControlUnit
/*    */ {
/*    */   public void setControlUnitInfo(ControlUnit controlUnit)
/*    */   {
/* 30 */     if (controlUnit != null) {
/* 31 */       setCascadeFlag(controlUnit.getCascadeFlag());
/* 32 */       setControlUnitID(controlUnit.getControlUnitID());
/* 33 */       setCurrentOnlineNum(controlUnit.getCurrentOnlineNum());
/* 34 */       setName(controlUnit.getName());
/* 35 */       setParentID(controlUnit.getParentID());
/* 36 */       setTotalNum(controlUnit.getTotalNum());
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\ControlUnitInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */