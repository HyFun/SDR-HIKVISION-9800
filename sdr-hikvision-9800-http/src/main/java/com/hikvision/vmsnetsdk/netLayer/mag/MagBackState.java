/*     */ package com.hikvision.vmsnetsdk.netLayer.mag;
/*     */ 
/*     */ import com.hikvision.vmsnetsdk.netLayer.base.NetHttpBackState;

/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MagBackState
/*     */   extends NetHttpBackState
/*     */ {
/*     */   private String version;
/*     */   private int sequence;
/*     */   private int commandType;
/*     */   private int command;
/*     */   public static final int CODE_SUCESS = 200;
/*     */   public static final int CODE_PARAM_ERROR = 201;
/*     */   public static final int CODE_REGISTER_FAIL = 202;
/*     */   public static final int CODE_RELOGOUT_FAIL = 203;
/*     */   public static final int CODE_QUERY_FAIL = 204;
/*     */   public static final int CODE_QUERY_PARAM_ERROR = 205;
/*     */   public static final int CODE_QUERY_CAMERA_UNEXIST = 206;
/*     */   public static final int CODE_QUERY_HANDLE_FAIL = 207;
/*     */   public static final int CODE_QUERY_UNSUPPORT = 208;
/*     */   public static final int CODE_QUERY_CONNECT_FAIL = 209;
/*     */   public static final int CODE_PTZ_FAIL = 210;
/*     */   public static final int CODE_PTZ_LOGIN_VAG_FAIL = 211;
/*     */   public static final int CODE_PTZ_PARSE_CMD_ERROR = 212;
/*     */   public static final int CODE_PTZ_NO_PERMISSION = 213;
/*     */   public static final int CODE_PTZ_SZCAM_LOCKED = 214;
/*     */   public static final int CODE_PTZ_SZCAM_INDEX_UNEXIST = 215;
/*     */   public static final int CODE_PTZ_LOGIN_DEVICE_FAIL = 216;
/*     */   public static final int CODE_IO_CONNECT_SERVER_FAIL = 217;
/*     */   public static final int CODE_MAG_ERROR = 218;
/*     */   public static final int CODE_GET_PLATFORM_INFO_FAIL = 219;
/*     */   public static final String DESCRIPTION_SUCCESS = "Successfull";
/*     */   
/*     */   public String getVersion()
/*     */   {
/*  55 */     return this.version;
/*     */   }
/*     */   
/*     */   public void setVersion(String version) {
/*  59 */     this.version = version;
/*     */   }
/*     */   
/*     */   public int getSequence() {
/*  63 */     return this.sequence;
/*     */   }
/*     */   
/*     */   public void setSequence(int sequence) {
/*  67 */     this.sequence = sequence;
/*     */   }
/*     */   
/*     */   public int getCommandType() {
/*  71 */     return this.commandType;
/*     */   }
/*     */   
/*     */   public void setCommandType(int commandType) {
/*  75 */     this.commandType = commandType;
/*     */   }
/*     */   
/*     */   public int getCommand() {
/*  79 */     return this.command;
/*     */   }
/*     */   
/*     */   public void setCommand(int command) {
/*  83 */     this.command = command;
/*     */   }
/*     */   
/*     */   public boolean isSuccess()
/*     */   {
/*  88 */     if (getCode() == 200) {
/*  89 */       return true;
/*     */     }
/*  91 */     return false;
/*     */   }
/*     */   
/*     */   public String getDescription()
/*     */   {
/*  96 */     if (getCode() == 200) {
/*  97 */       setDescription("Successfull");
/*     */     }
/*  99 */     if (super.getDescription() == null) {
/* 100 */       return "";
/*     */     }
/* 102 */     return super.getDescription();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\mag\MagBackState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */