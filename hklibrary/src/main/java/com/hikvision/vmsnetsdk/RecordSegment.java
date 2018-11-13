/*     */ package com.hikvision.vmsnetsdk;
/*     */ 
/*     */ import java.util.List;

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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecordSegment
/*     */ {
/*     */   private ABS_TIME startTime;
/*     */   private ABS_TIME endTime;
/*     */   private int recordType;
/*     */   private int mediaDataLen;
/*     */   private int metaDataLen;
/*     */   private boolean isLocked;
/*  42 */   private String playUrl = null;
/*     */   
/*     */   private String ncg_startTime;
/*     */   private String ncg_endTime;
/*     */   private List<FileInfoList> fileInfoList;
/*     */   
/*     */   public String getPlayUrl()
/*     */   {
/*  50 */     return this.playUrl;
/*     */   }
/*     */   
/*     */   public void setPlayUrl(String playUrl) {
/*  54 */     this.playUrl = playUrl;
/*     */   }
/*     */   
/*     */   public ABS_TIME getStartTime() {
/*  58 */     return this.startTime;
/*     */   }
/*     */   
/*     */   public void setStartTime(ABS_TIME startTime) {
/*  62 */     this.startTime = startTime;
/*     */   }
/*     */   
/*     */   public ABS_TIME getEndTime() {
/*  66 */     return this.endTime;
/*     */   }
/*     */   
/*     */   public void setEndTime(ABS_TIME endTime) {
/*  70 */     this.endTime = endTime;
/*     */   }
/*     */   
/*     */   public int getRecordType() {
/*  74 */     return this.recordType;
/*     */   }
/*     */   
/*     */   public void setRecordType(int recordType) {
/*  78 */     this.recordType = recordType;
/*     */   }
/*     */   
/*     */   public int getMediaDataLen() {
/*  82 */     return this.mediaDataLen;
/*     */   }
/*     */   
/*     */   public void setMediaDataLen(int mediaDataLen) {
/*  86 */     this.mediaDataLen = mediaDataLen;
/*     */   }
/*     */   
/*     */   public int getMetaDataLen() {
/*  90 */     return this.metaDataLen;
/*     */   }
/*     */   
/*     */   public void setMetaDataLen(int metaDataLen) {
/*  94 */     this.metaDataLen = metaDataLen;
/*     */   }
/*     */   
/*     */   public boolean isLocked() {
/*  98 */     return this.isLocked;
/*     */   }
/*     */   
/*     */   public void setLocked(boolean isLocked) {
/* 102 */     this.isLocked = isLocked;
/*     */   }
/*     */   
/*     */   public String getNcg_startTime()
/*     */   {
/* 107 */     return this.ncg_startTime;
/*     */   }
/*     */   
/*     */   public void setNcg_startTime(String ncg_startTime)
/*     */   {
/* 112 */     this.ncg_startTime = ncg_startTime;
/*     */   }
/*     */   
/*     */   public String getNcg_endTime()
/*     */   {
/* 117 */     return this.ncg_endTime;
/*     */   }
/*     */   
/*     */   public void setNcg_endTime(String ncg_endTime)
/*     */   {
/* 122 */     this.ncg_endTime = ncg_endTime;
/*     */   }
/*     */   
/*     */   public List<FileInfoList> getFileInfoList()
/*     */   {
/* 127 */     return this.fileInfoList;
/*     */   }
/*     */   
/*     */   public void setFileInfoList(List<FileInfoList> fileInfoList)
/*     */   {
/* 132 */     this.fileInfoList = fileInfoList;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\RecordSegment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */