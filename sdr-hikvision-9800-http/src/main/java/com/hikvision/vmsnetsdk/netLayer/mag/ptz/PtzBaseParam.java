/*     */ package com.hikvision.vmsnetsdk.netLayer.mag.ptz;
/*     */ 
/*     */ 
/*     */ public class PtzBaseParam
/*     */ {
/*     */   private String szCamIndexCode;
/*     */   
/*     */   private int iPriority;
/*     */   
/*     */   private int iPtzCommand;
/*     */   
/*     */   private String sessionId;
/*     */   
/*     */   public static final int PTZ_CMD_UP = 21;
/*     */   
/*     */   public static final int PTZ_CMD_DOWN = 22;
/*     */   
/*     */   public static final int PTZ_CMD_LEFT = 23;
/*     */   
/*     */   public static final int PTZ_CMD_RIGHT = 24;
/*     */   
/*     */   public static final int PTZ_CMD_UPLEFT = 25;
/*     */   
/*     */   public static final int PTZ_CMD_UPRIGHT = 26;
/*     */   
/*     */   public static final int PTZ_CMD_DOWNLEFT = 27;
/*     */   
/*     */   public static final int PTZ_CMD_DOWNRIGHT = 28;
/*     */   
/*     */   public static final int PTZ_CMD_AUTOPAN = 29;
/*     */   
/*     */   public static final int PTZ_CMD_ZOOMIN = 11;
/*     */   
/*     */   public static final int PTZ_CMD_ZOOMOUT = 12;
/*     */   
/*     */   public static final int PTZ_CMD_FOCUSNEAR = 13;
/*     */   
/*     */   public static final int PTZ_CMD_FOCUSFAR = 14;
/*     */   
/*     */   public static final int PTZ_CMD_BRIGHTEN = 15;
/*     */   
/*     */   public static final int PTZ_CMD_DARKEN = 16;
/*     */   
/*     */   public static final int PTZ_CMD_OPEN_LIGHT = 2;
/*     */   
/*     */   public static final int PTZ_CMD_CLOSE_LIGHT = 2;
/*     */   
/*     */   public static final int PTZ_CMD_CLOSE_WIPERS = 3;
/*     */   
/*     */   public static final int PTZ_CMD_OPEN_WIPERS = 3;
/*     */   
/*     */   public static final int PTZ_CMD_SETPRESET = 8;
/*     */   public static final int PTZ_CMD_CLEPRESET = 9;
/*     */   public static final int PTZ_CMD_GOTOPRESET = 39;
/*     */   public static final int PTZ_CMD_3D_ENLARGE = 99;
/*     */   
/*     */   public String getSessionId()
/*     */   {
/*  59 */     return this.sessionId;
/*     */   }
/*     */   
/*     */   public void setSessionId(String sessionId) {
/*  63 */     this.sessionId = sessionId;
/*     */   }
/*     */   
/*     */   public int getiPtzCommand() {
/*  67 */     return this.iPtzCommand;
/*     */   }
/*     */   
/*     */   public void setiPtzCommand(int iPtzCommand) {
/*  71 */     this.iPtzCommand = iPtzCommand;
/*     */   }
/*     */   
/*     */   public String getSzCamIndexCode() {
/*  75 */     return this.szCamIndexCode;
/*     */   }
/*     */   
/*     */   public void setSzCamIndexCode(String szCamIndexCode) {
/*  79 */     this.szCamIndexCode = szCamIndexCode;
/*     */   }
/*     */   
/*     */   public int getiPriority() {
/*  83 */     return this.iPriority;
/*     */   }
/*     */   
/*     */   public void setiPriority(int iPriority) {
/*  87 */     this.iPriority = iPriority;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int adapterPtzCmd(int oriPtzCmd)
/*     */   {
/*  98 */     switch (oriPtzCmd) {
/*     */     case 1: 
/* 100 */       return 21;
/*     */     case 2: 
/* 102 */       return 22;
/*     */     case 3: 
/* 104 */       return 23;
/*     */     case 4: 
/* 106 */       return 24;
/*     */     case 11: 
/* 108 */       return 25;
/*     */     case 12: 
/* 110 */       return 26;
/*     */     case 13: 
/* 112 */       return 27;
/*     */     case 14: 
/* 114 */       return 28;
/*     */     case 16: 
/* 116 */       return 29;
/*     */     case 22: 
/* 118 */       return 2;
/*     */     case 23: 
/* 120 */       return 2;
/*     */     case 20: 
/* 122 */       return 3;
/*     */     case 21: 
/* 124 */       return 3;
/*     */     case 7: 
/* 126 */       return 11;
/*     */     case 8: 
/* 128 */       return 12;
/*     */     case 9: 
/* 130 */       return 13;
/*     */     case 10: 
/* 132 */       return 14;
/*     */     case 5: 
/* 134 */       return 15;
/*     */     case 6: 
/* 136 */       return 16;
/*     */     case 17: 
/* 138 */       return 8;
/*     */     case 19: 
/* 140 */       return 9;
/*     */     case 18: 
/* 142 */       return 39;
/*     */     case 99: 
/* 144 */       return 99;
/*     */     }
/* 146 */     return oriPtzCmd;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\mag\ptz\PtzBaseParam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */