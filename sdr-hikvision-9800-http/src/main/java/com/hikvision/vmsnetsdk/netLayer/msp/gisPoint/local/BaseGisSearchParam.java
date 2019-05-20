/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.gisPoint.local;
/*    */ 
/*    */ 
/*    */ public class BaseGisSearchParam
/*    */ {
/*    */   private String session;
/*    */   
/*    */   private String keyString;
/*    */   
/*    */   private String pointType;
/*    */   
/*    */   private int numPerPage;
/*    */   
/*    */   private int curPage;
/*    */   
/*    */   public String getSession()
/*    */   {
/* 18 */     return this.session;
/*    */   }
/*    */   
/*    */   public void setSession(String session) {
/* 22 */     this.session = session;
/*    */   }
/*    */   
/*    */   public String getKeyString() {
/* 26 */     return this.keyString;
/*    */   }
/*    */   
/*    */   public void setKeyString(String keyString) {
/* 30 */     this.keyString = keyString;
/*    */   }
/*    */   
/*    */   public String getPointType() {
/* 34 */     return this.pointType;
/*    */   }
/*    */   
/*    */   public void setPointType(String pointType) {
/* 38 */     this.pointType = pointType;
/*    */   }
/*    */   
/*    */   public int getNumPerPage() {
/* 42 */     return this.numPerPage;
/*    */   }
/*    */   
/*    */   public void setNumPerPage(int numPerPage) {
/* 46 */     this.numPerPage = numPerPage;
/*    */   }
/*    */   
/*    */   public int getCurPage() {
/* 50 */     return this.curPage;
/*    */   }
/*    */   
/*    */   public void setCurPage(int curPage) {
/* 54 */     this.curPage = curPage;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\gisPoint\local\BaseGisSearchParam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */