/*     */ package com.hikvision.vmsnetsdk;
/*     */ 
/*     */

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NotNull;
import com.lidroid.xutils.db.annotation.Unique;

import java.io.Serializable;

/*     */
/*     */
/*     */
/*     */

/*     */
/*     */ public class PlugInfo
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @NotNull
/*     */   @Unique
/*     */   @Id(column="pluginId")
/*  16 */   private String mId = "";
/*     */   
/*     */ 
/*     */ 
/*     */   @NotNull
/*     */   @Column(column="packagename")
/*  22 */   private String mPackagename = "";
/*     */   
/*     */ 
/*     */   @NotNull
/*     */   @Column(column="appname")
/*  27 */   private String mAppname = "";
/*     */   
/*     */ 
/*     */   @NotNull
/*     */   @Column(column="versioncode")
/*  32 */   private String mVersioncode = "";
/*     */   
/*     */ 
/*     */   @NotNull
/*     */   @Column(column="versionname")
/*  37 */   private String mVersionname = "";
/*     */   
/*     */ 
/*     */   @NotNull
/*     */   @Column(column="logoUrl")
/*  42 */   private String mLogoUrl = "";
/*     */   
/*     */ 
/*     */ 
/*     */   @Column(column="appDesrc")
/*  47 */   private String mAppDesrc = "";
/*     */   
/*     */   @NotNull
/*     */   @Column(column="appDownUrl")
/*  51 */   private String mAppDownUrl = "";
/*     */   
/*     */ 
/*     */   @NotNull
/*     */   @Column(column="plugfileMD5")
/*  56 */   private String mPlugfileMD5 = "";
/*     */   
/*     */ 
/*     */ 
/*     */   @Column(column="size")
/*  61 */   private long mSize = 0L;
/*     */   
/*     */ 
/*     */   @Column(column="publishTime")
/*  65 */   private long mPublishTime = 0L;
/*     */   
/*     */ 
/*     */   @Column(column="updateInfo")
/*  69 */   private String mUpdateInfo = "";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getUpdateInfo()
/*     */   {
/*  80 */     return this.mUpdateInfo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUpdateInfo(String in)
/*     */   {
/*  91 */     this.mUpdateInfo = in;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getPublishTime()
/*     */   {
/* 102 */     return this.mPublishTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPublishTime(long time)
/*     */   {
/* 113 */     this.mPublishTime = time;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSize(long size)
/*     */   {
/* 124 */     this.mSize = size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getSize()
/*     */   {
/* 135 */     return this.mSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPlugfileMD5()
/*     */   {
/* 146 */     return this.mPlugfileMD5;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPlugfileMD5(String str)
/*     */   {
/* 157 */     this.mPlugfileMD5 = str;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getId()
/*     */   {
/* 168 */     return this.mId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/* 179 */     this.mId = id;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPackagename()
/*     */   {
/* 190 */     return this.mPackagename;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPackagename(String packagename)
/*     */   {
/* 201 */     this.mPackagename = packagename;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getAppname()
/*     */   {
/* 212 */     return this.mAppname;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAppname(String appname)
/*     */   {
/* 223 */     this.mAppname = appname;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getVersioncode()
/*     */   {
/* 234 */     return this.mVersioncode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setVersioncode(String versionCode2)
/*     */   {
/* 245 */     this.mVersioncode = versionCode2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getVersionname()
/*     */   {
/* 256 */     return this.mVersionname;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setVersionname(String versionname)
/*     */   {
/* 267 */     this.mVersionname = versionname;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLogo()
/*     */   {
/* 278 */     return this.mLogoUrl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLogo(String logo)
/*     */   {
/* 289 */     this.mLogoUrl = logo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getAppDesrc()
/*     */   {
/* 300 */     return this.mAppDesrc;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAppDesrc(String appDesrc)
/*     */   {
/* 311 */     this.mAppDesrc = appDesrc;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getAppDownLoadUrl()
/*     */   {
/* 322 */     return this.mAppDownUrl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAppDownLoadUrl(String url)
/*     */   {
/* 333 */     this.mAppDownUrl = url;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\PlugInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */