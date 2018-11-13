/*     */ package com.hikvision.vmsnetsdk;
/*     */ 
/*     */

import com.hikvision.vmsnetsdk.netLayer.msp.checkupdate.EnumAppPluginType;
import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

/*     */
/*     */

/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(name="AppPluginInfo")
/*     */ public class AppPluginInfo
/*     */   extends PlugInfo
/*     */ {
/*     */   private static final long serialVersionUID = 2L;
/*     */   @Column(column="apptype")
/*  16 */   private int mAppType = EnumAppPluginType.TYPE_APK
/*  17 */     .ordinal();
/*     */   
/*     */   @Column(column="action")
/*  20 */   private String mAction = "";
/*     */   
/*     */ 
/*     */   @Column(column="category")
/*  24 */   private String mCategory = "android.intent.category.DEFAULT";
/*     */   
/*     */ 
/*     */   @Column(column="progress")
/*  28 */   private int mProgress = 0;
/*     */   
/*     */ 
/*     */   @Column(column="SDCardPath")
/*  32 */   private String mSDCardPath = "";
/*     */   
/*     */ 
/*     */   @Column(column="isShowDeleteIcon")
/*  36 */   private boolean mIsShowDeleteIcon = false;
/*     */   
/*     */ 
/*     */   @Column(column="logoBitmapData")
/*  40 */   private byte[] mLogoBitmapData = null;
/*     */   
/*     */ 
/*     */   @Column(column="isDefaultStart")
/*  44 */   private boolean mIsDefaultStart = false;
/*     */   
/*     */ 
/*     */   @Column(column="msgNum")
/*  48 */   private long mMsgNum = 0L;
/*     */   
/*     */   @Column(column="isHaveNewVersion")
/*  51 */   private boolean mIsHaveNewVersion = false;
/*     */   
/*     */   @Column(column="isAdded")
/*  54 */   private boolean mIsAdded = false;
/*     */   
/*     */   @Column(column="isInstalled")
/*  57 */   private boolean mIsInstalled = false;
/*     */   
/*     */ 
/*     */   @Column(column="newAppname")
/*  61 */   private String mNewAppname = "";
/*     */   
/*     */ 
/*     */   @Column(column="newVersioncode")
/*  65 */   private String mNewVersioncode = "";
/*     */   
/*     */ 
/*     */   @Column(column="newVersionname")
/*  69 */   private String mNewVersionname = "";
/*     */   
/*     */ 
/*     */   @Column(column="newLogoUrl")
/*  73 */   private String mNewLogoUrl = "";
/*     */   
/*     */ 
/*     */   @Column(column="newSize")
/*  77 */   private long mNewSize = 0L;
/*     */   
/*     */ 
/*     */   @Column(column="newPublishTime")
/*  81 */   private long mNewPublishTime = 0L;
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
/*     */   public String toString()
/*     */   {
/*  98 */     return "AppPluginInfo [pluginId=" + getId() + ", packagename=" + getPackagename() + ", appname=" + getAppname() + ", newappname=" + getNewAppname() + ", versioncode=" + getVersioncode() + ", newversioncode=" + getNewVersioncode() + ", versionname=" + getVersionname() + ", newversionname=" + getNewVersionname() + ", logoUrl=" + getLogo() + ", newlogoUrl=" + getNewLogo() + ", appDesrc=" + getAppDesrc() + ", appDownUrl=" + getAppDownLoadUrl() + ", plugfileMD5=" + getPlugfileMD5() + ", size=" + getSize() + ", newsize=" + getNewSize() + ", publishTime=" + getPublishTime() + ", newpublishTime=" + getNewPublishTime() + ", apptype=" + getPluginType() + ", action=" + getAction() + ", mCategory=" + getCategory() + ", progress=" + getProgress() + ", SDCardPath=" + getSDCardPath() + ", isShowDeleteIcon=" + getIsShowDeleteIcon() + ", isDefaultStart=" + getIsDefaultStart() + ", msgNum=" + getIsDefaultStart() + ", updateInfo=" + getUpdateInfo() + ", isHaveNewVersion=" + getIsHaveNewVersion() + ", isAdded=" + getIsAdded() + ", isInstalled=" + getIsInstalled() + "]";
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean getIsInstalled()
/*     */   {
/* 104 */     return this.mIsInstalled;
/*     */   }
/*     */   
/*     */   public void setIsInstalled(boolean ret)
/*     */   {
/* 109 */     this.mIsInstalled = ret;
/*     */   }
/*     */   
/*     */   public boolean getIsAdded()
/*     */   {
/* 114 */     return this.mIsAdded;
/*     */   }
/*     */   
/*     */   public void setIsAdded(boolean ret)
/*     */   {
/* 119 */     this.mIsAdded = ret;
/*     */   }
/*     */   
/*     */   public boolean getIsHaveNewVersion()
/*     */   {
/* 124 */     return this.mIsHaveNewVersion;
/*     */   }
/*     */   
/*     */   public void setIsHaveNewVersion(boolean ret)
/*     */   {
/* 129 */     this.mIsHaveNewVersion = ret;
/*     */   }
/*     */   
/*     */   public long getMsgNum()
/*     */   {
/* 134 */     return this.mMsgNum;
/*     */   }
/*     */   
/*     */   public void setMsgNum(long num)
/*     */   {
/* 139 */     this.mMsgNum = num;
/*     */   }
/*     */   
/*     */   public boolean getIsDefaultStart()
/*     */   {
/* 144 */     return this.mIsDefaultStart;
/*     */   }
/*     */   
/*     */   public void setIsDefaultStart(boolean ret)
/*     */   {
/* 149 */     this.mIsDefaultStart = ret;
/*     */   }
/*     */   
/*     */   public void setLogoBitmapData(byte[] data)
/*     */   {
/* 154 */     this.mLogoBitmapData = data;
/*     */   }
/*     */   
/*     */   public byte[] getLogoBitmapData()
/*     */   {
/* 159 */     return this.mLogoBitmapData;
/*     */   }
/*     */   
/*     */   public void setCategory(String category)
/*     */   {
/* 164 */     this.mCategory = category;
/*     */   }
/*     */   
/*     */   public String getCategory()
/*     */   {
/* 169 */     return this.mCategory;
/*     */   }
/*     */   
/*     */   public void setAction(String action)
/*     */   {
/* 174 */     this.mAction = action;
/*     */   }
/*     */   
/*     */   public String getAction()
/*     */   {
/* 179 */     return this.mAction;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setIsShowDeleteIcon(boolean ret)
/*     */   {
/* 190 */     this.mIsShowDeleteIcon = ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getIsShowDeleteIcon()
/*     */   {
/* 201 */     return this.mIsShowDeleteIcon;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSDCardPath(String path)
/*     */   {
/* 212 */     this.mSDCardPath = path;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getSDCardPath()
/*     */   {
/* 222 */     return this.mSDCardPath;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setProgress(int progress)
/*     */   {
/* 233 */     this.mProgress = progress;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getProgress()
/*     */   {
/* 244 */     return this.mProgress;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getPluginType()
/*     */   {
/* 255 */     return this.mAppType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPluginType(int type)
/*     */   {
/* 266 */     this.mAppType = type;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getNewPublishTime()
/*     */   {
/* 277 */     return this.mNewPublishTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNewPublishTime(long time)
/*     */   {
/* 288 */     this.mNewPublishTime = time;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNewSize(long size)
/*     */   {
/* 299 */     this.mNewSize = size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getNewSize()
/*     */   {
/* 310 */     return this.mNewSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getNewAppname()
/*     */   {
/* 321 */     return this.mNewAppname;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNewAppname(String appname)
/*     */   {
/* 332 */     this.mNewAppname = appname;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getNewVersioncode()
/*     */   {
/* 343 */     return this.mNewVersioncode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNewVersioncode(String versionCode2)
/*     */   {
/* 354 */     this.mNewVersioncode = versionCode2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getNewVersionname()
/*     */   {
/* 365 */     return this.mNewVersionname;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNewVersionname(String versionname)
/*     */   {
/* 376 */     this.mNewVersionname = versionname;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getNewLogo()
/*     */   {
/* 387 */     return this.mNewLogoUrl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNewLogo(String logo)
/*     */   {
/* 398 */     this.mNewLogoUrl = logo;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\AppPluginInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */