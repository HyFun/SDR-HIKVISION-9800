/*     */ package com.hikvision.vmsnetsdk.netLayer.msp.msg;
/*     */ 
/*     */ import com.hikvision.vmsnetsdk.CNetSDKLog;

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
/*     */ 
/*     */ public class Msg
/*     */ {
/*     */   private String id;
/*     */   private String type;
/*     */   private String typeDescribe;
/*     */   private String title;
/*     */   private boolean isChecked;
/*     */   private long createTime;
/*     */   public static final String TYPE_SM = "SM";
/*     */   public static final String TYPE_PM = "PM";
/*     */   public static final String TYPE_AM = "AM";
/*     */   public static final String TYPE_TM = "TM";
/*     */   private static final int CHECKED_TURE = 1;
/*     */   private static final int CHECKED_FALSE = 0;
/*     */   private static final String TAG = "Msg";
/*     */   
/*     */   public String getId()
/*     */   {
/*  43 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  47 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getType() {
/*  51 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(String type) {
/*  55 */     this.type = type;
/*     */   }
/*     */   
/*     */   public String getTitle() {
/*  59 */     return this.title;
/*     */   }
/*     */   
/*     */   public void setTitle(String title) {
/*  63 */     this.title = title;
/*     */   }
/*     */   
/*     */   public String getTypeDescribe() {
/*  67 */     return this.typeDescribe;
/*     */   }
/*     */   
/*     */   public void setTypeDescribe(String typeDescribe) {
/*  71 */     this.typeDescribe = typeDescribe;
/*     */   }
/*     */   
/*     */   public boolean isChecked() {
/*  75 */     return this.isChecked;
/*     */   }
/*     */   
/*     */   public boolean setChecked(int isChecked) {
/*  79 */     if (isChecked == 1) {
/*  80 */       this.isChecked = true;
/*  81 */       return true; }
/*  82 */     if (isChecked == 0) {
/*  83 */       this.isChecked = false;
/*  84 */       return true;
/*     */     }
/*  86 */     CNetSDKLog.e("Msg", "setChecked,isChecked can not be other value.");
/*     */     
/*  88 */     return false;
/*     */   }
/*     */   
/*     */   public void setChecked(boolean isChecked) {
/*  92 */     this.isChecked = isChecked;
/*     */   }
/*     */   
/*     */   public long getCreateTime() {
/*  96 */     return this.createTime;
/*     */   }
/*     */   
/*     */   public void setCreateTime(long createTime) {
/* 100 */     this.createTime = createTime;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 105 */     return "Msg [id=" + this.id + ", type=" + this.type + ", typeDescribe=" + this.typeDescribe + ", title=" + this.title + ", isChecked=" + this.isChecked + ", createTime=" + this.createTime + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\msg\Msg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */