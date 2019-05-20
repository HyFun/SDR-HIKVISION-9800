/*     */ package com.hikvision.vmsnetsdk;
/*     */ 
/*     */ import java.util.Calendar;

/*     */
/*     */ public class ABS_TIME
/*     */ {
/*     */   public int dwYear;
/*     */   public int dwMonth;
/*     */   public int dwDay;
/*     */   public int dwHour;
/*     */   public int dwMinute;
/*     */   public int dwSecond;
/*     */   
/*     */   public ABS_TIME(int year, int month, int day, int hour, int minite, int second) {
/*  15 */     this.dwYear = year;
/*  16 */     this.dwMonth = month;
/*  17 */     this.dwDay = day;
/*  18 */     this.dwHour = hour;
/*  19 */     this.dwMinute = minite;
/*  20 */     this.dwSecond = second;
/*     */   }
/*     */   
/*     */   public ABS_TIME(Calendar time) {
/*  24 */     this.dwYear = time.get(1);
/*  25 */     this.dwMonth = time.get(2);
/*  26 */     this.dwDay = time.get(5);
/*  27 */     this.dwHour = time.get(11);
/*  28 */     this.dwMinute = time.get(12);
/*  29 */     this.dwSecond = time.get(13);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ABS_TIME(String time)
/*     */   {
/*  36 */     if (time == null) {
/*  37 */       return;
/*     */     }
/*     */     
/*  40 */     int i = time.indexOf(".");
/*  41 */     if (i > 0) {
/*  42 */       time = time.substring(0, i);
/*     */     }
/*     */     
/*  45 */     String[] dates = time.split("T");
/*  46 */     if ((dates == null) || (dates.length < 2)) {
/*  47 */       return;
/*     */     }
/*     */     
/*  50 */     String[] ymd_date = dates[0].split("-");
/*  51 */     if ((ymd_date == null) || (ymd_date.length < 3)) {
/*  52 */       return;
/*     */     }
/*  54 */     this.dwYear = parseInteger(ymd_date[0]);
/*  55 */     this.dwMonth = (parseInteger(ymd_date[1]) - 1);
/*  56 */     this.dwDay = parseInteger(ymd_date[2]);
/*     */     
/*     */ 
/*  59 */     String[] hms_date = dates[1].split(":");
/*  60 */     if ((hms_date == null) || (hms_date.length < 3)) {
/*  61 */       return;
/*     */     }
/*  63 */     this.dwHour = parseInteger(hms_date[0]);
/*  64 */     this.dwMinute = parseInteger(hms_date[1]);
/*  65 */     this.dwSecond = parseInteger(hms_date[2]);
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/*  71 */     return "" + this.dwYear + "-" + (this.dwMonth + 1) + "-" + this.dwDay + " " + this.dwHour + ":" + this.dwMinute + ":" + this.dwSecond;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString(boolean isPlatformNew)
/*     */   {
/*  82 */     if (isPlatformNew)
/*     */     {
/*  84 */       return this.dwYear + "-" + formatUnitTime(this.dwMonth + 1) + "-" + formatUnitTime(this.dwDay) + "T" + formatUnitTime(this.dwHour) + ":" + formatUnitTime(this.dwMinute) + ":" + formatUnitTime(this.dwSecond) + ".000Z";
/*     */     }
/*     */     
/*  87 */     return toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private String formatUnitTime(int unitTime)
/*     */   {
/*  98 */     if ((unitTime >= 0) && (unitTime <= 9)) {
/*  99 */       return "0" + unitTime;
/*     */     }
/* 101 */     return Integer.toString(unitTime);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int parseInteger(String value)
/*     */   {
/* 114 */     int intValue = 0;
/*     */     
/* 116 */     if (value == null) {
/* 117 */       return intValue;
/*     */     }
/*     */     try
/*     */     {
/* 121 */       intValue = Integer.parseInt(value);
/*     */     } catch (NumberFormatException e) {
/* 123 */       e.printStackTrace();
/*     */     }
/*     */     
/* 126 */     return intValue;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\ABS_TIME.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */