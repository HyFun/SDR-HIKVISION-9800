/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.login;
/*    */ 
/*    */ import com.hikvision.vmsnetsdk.CNetSDKLog;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WebApp
/*    */ {
/*    */   private boolean titleVisable;
/*    */   private String appName;
/*    */   private String appIcon;
/*    */   private String appUrl;
/*    */   private boolean isAppDefaultSel;
/*    */   private static final int VISABLE_TURE = 1;
/*    */   private static final int VISABLE_FALSE = 0;
/*    */   private static final String TAG = "WebApp";
/*    */   private static final int APP_DEFAULT_SEL_TURE = 1;
/*    */   private static final int APP_DEFAULT_SEL_FALSE = 0;
/*    */   
/*    */   public boolean isAppDefaultSel()
/*    */   {
/* 27 */     return this.isAppDefaultSel;
/*    */   }
/*    */   
/*    */   public void setAppDefaultSel(int appDefaultSel)
/*    */   {
/* 32 */     if (1 == appDefaultSel) {
/* 33 */       this.isAppDefaultSel = true;
/* 34 */     } else if (0 == appDefaultSel) {
/* 35 */       this.isAppDefaultSel = false;
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean getTitleVisable() {
/* 40 */     return this.titleVisable;
/*    */   }
/*    */   
/*    */   public boolean setTitleVisable(int titleVisable) {
/* 44 */     if (titleVisable == 1) {
/* 45 */       this.titleVisable = true;
/* 46 */       return true; }
/* 47 */     if (titleVisable == 0) {
/* 48 */       this.titleVisable = false;
/* 49 */       return true;
/*    */     }
/* 51 */     CNetSDKLog.e("WebApp", "setTitleVisable error.");
/* 52 */     return false;
/*    */   }
/*    */   
/*    */   public String getAppName()
/*    */   {
/* 57 */     return this.appName;
/*    */   }
/*    */   
/*    */   public void setAppName(String appName) {
/* 61 */     this.appName = appName;
/*    */   }
/*    */   
/*    */   public String getAppIcon() {
/* 65 */     return this.appIcon;
/*    */   }
/*    */   
/*    */   public void setAppIcon(String appIcon) {
/* 69 */     this.appIcon = appIcon;
/*    */   }
/*    */   
/*    */   public String getAppUrl() {
/* 73 */     return this.appUrl;
/*    */   }
/*    */   
/*    */   public void setAppUrl(String appUrl) {
/* 77 */     this.appUrl = appUrl;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\login\WebApp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */