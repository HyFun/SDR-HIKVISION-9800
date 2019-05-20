/*     */ package com.hikvision.vmsnetsdk.netLayer.mag.ptz;
/*     */ 
/*     */

import android.text.TextUtils;
import android.util.Xml;

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.mag.MagRequest;
import com.hikvision.vmsnetsdk.util.intf.IRequestTool;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;

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
/*     */ public class PtzCommandRequest
/*     */   extends MagRequest
/*     */ {
/*     */   private PtzBaseParam ptzBaseParam;
/*     */   private String magRequestHead;
/*     */   private int paramMode;
/*     */   private static final int PARAM_MODE_BASE = 0;
/*     */   private static final int PARAM_MODE_WITH_ACTION_SPEED = 1;
/*     */   private static final int PARAM_MODE_WITH_INDEX_OR_LOCKTIME = 2;
/*     */   private static final int PARAM_MODE_WITH_INDEX_PTZCRUISE = 3;
/*     */   private static final int PARAM_MODE_WITH_ZOOMPARAM = 4;
/*     */   private static final String TAG = "PtzCommandRequest";
/*     */   private static final String HTTPURL_KEY_PTZ = "ptz";
/*     */   private static final String HTTPURL_NCG_PTZ = "ncgPtz";
/*     */   private int iAction;
/*     */   private int iSpeed;
/*     */   private int iIndex;
/*     */   private String lockTime;
/*     */   private int iPtzCruisePoint;
/*     */   private int iPtzCruiseInput;
/*     */   private PtzEnlargeParam ptzEnlargeParam;
/*  43 */   private int cascadeFlag = 0;
/*     */   public static final int ACTION_START = 0;
/*     */   public static final int ACTION_STOP = 1;
/*     */   
/*     */   public PtzCommandRequest(String magRequestHead, IRequestTool iRequestTool, PtzBaseParam ptzBaseParam, String cascadeFlag, boolean isHttpsProtocal)
/*     */   {
/*  49 */     super(iRequestTool, isHttpsProtocal);
/*  50 */     this.ptzBaseParam = ptzBaseParam;
/*  51 */     this.magRequestHead = magRequestHead;
/*  52 */     this.paramMode = 0;
/*  53 */     if (!TextUtils.isEmpty(cascadeFlag)) {
/*  54 */       this.cascadeFlag = Integer.parseInt(cascadeFlag);
/*     */     }
/*     */   }
/*     */   
/*     */   public PtzCommandRequest(String magRequestHead, IRequestTool iRequestTool, PtzBaseParam ptzBaseParam, int iAction, int iSpeed, String cascadeFlag, boolean isHttpsProtocal)
/*     */   {
/*  60 */     super(iRequestTool, isHttpsProtocal);
/*  61 */     this.ptzBaseParam = ptzBaseParam;
/*  62 */     this.magRequestHead = magRequestHead;
/*  63 */     this.iAction = iAction;
/*  64 */     this.iSpeed = iSpeed;
/*  65 */     this.paramMode = 1;
/*  66 */     if (!TextUtils.isEmpty(cascadeFlag)) {
/*  67 */       this.cascadeFlag = Integer.parseInt(cascadeFlag);
/*     */     }
/*     */   }
/*     */   
/*     */   public PtzCommandRequest(String magRequestHead, IRequestTool iRequestTool, PtzBaseParam ptzBaseParam, int indexOrLockTime, String cascadeFlag, boolean isHttpsProtocal)
/*     */   {
/*  73 */     super(iRequestTool, isHttpsProtocal);
/*  74 */     this.ptzBaseParam = ptzBaseParam;
/*  75 */     this.magRequestHead = magRequestHead;
/*  76 */     this.iIndex = indexOrLockTime;
/*  77 */     this.lockTime = Long.toString(indexOrLockTime);
/*  78 */     this.paramMode = 2;
/*  79 */     if (!TextUtils.isEmpty(cascadeFlag)) {
/*  80 */       this.cascadeFlag = Integer.parseInt(cascadeFlag);
/*     */     }
/*     */   }
/*     */   
/*     */   public PtzCommandRequest(String magRequestHead, IRequestTool iRequestTool, PtzBaseParam ptzBaseParam, int index, int iPtzCruisePoint, int iPtzCruiseInput, String cascadeFlag, boolean isHttpsProtocal)
/*     */   {
/*  86 */     super(iRequestTool, isHttpsProtocal);
/*  87 */     this.ptzBaseParam = ptzBaseParam;
/*  88 */     this.magRequestHead = magRequestHead;
/*  89 */     this.iIndex = index;
/*  90 */     this.iPtzCruisePoint = iPtzCruisePoint;
/*  91 */     this.iPtzCruiseInput = iPtzCruiseInput;
/*  92 */     this.paramMode = 3;
/*  93 */     if (!TextUtils.isEmpty(cascadeFlag)) {
/*  94 */       this.cascadeFlag = Integer.parseInt(cascadeFlag);
/*     */     }
/*     */   }
/*     */   
/*     */   public PtzCommandRequest(String magRequestHead, IRequestTool iRequestTool, PtzBaseParam ptzBaseParam, PtzEnlargeParam ptzEnlargeParam, String cascadeFlag, boolean isHttpsProtocal)
/*     */   {
/* 100 */     super(iRequestTool, isHttpsProtocal);
/* 101 */     this.ptzBaseParam = ptzBaseParam;
/* 102 */     this.magRequestHead = magRequestHead;
/* 103 */     this.ptzEnlargeParam = ptzEnlargeParam;
/* 104 */     this.paramMode = 4;
/* 105 */     if (!TextUtils.isEmpty(cascadeFlag)) {
/* 106 */       this.cascadeFlag = Integer.parseInt(cascadeFlag);
/*     */     }
/*     */   }
/*     */   
/*     */   public String getRequestAddr()
/*     */   {
/* 112 */     if ((this.magRequestHead == null) || (this.magRequestHead.length() <= 0)) {
/* 113 */       CNetSDKLog.e("PtzCommandRequest", "getRequestAddr,param error.");
/* 114 */       return null;
/*     */     }
/*     */     
/* 117 */     String requestAddr = null;
/* 118 */     if (this.cascadeFlag != 1) {
/* 119 */       requestAddr = this.magRequestHead + "ptz";
/*     */     } else {
/* 121 */       requestAddr = this.magRequestHead + "ncgPtz";
/*     */     }
/*     */     
/* 124 */     CNetSDKLog.i("PtzCommandRequest", "getRequestAddr,requestAddr:" + requestAddr);
/* 125 */     return requestAddr;
/*     */   }
/*     */   
/*     */   protected void insertXMLRequestExplain(XmlSerializer serializer)
/*     */     throws IllegalArgumentException, IllegalStateException, IOException
/*     */   {
/* 131 */     super.insertXMLRequestExplain(serializer);
/* 132 */     serializer.startTag(null, "Command");
/* 133 */     if (this.cascadeFlag == 1) {
/* 134 */       serializer.text("17");
/*     */     } else {
/* 136 */       serializer.text("1");
/*     */     }
/* 138 */     serializer.endTag(null, "Command");
/*     */   }
/*     */   
/*     */   public String getRequestData()
/*     */   {
/* 143 */     if (!isPtzParamOk()) {
/* 144 */       CNetSDKLog.e("PtzCommandRequest", "getRequestData, param error");
/* 145 */       return null;
/*     */     }
/* 147 */     int iPtzCommand = this.ptzBaseParam.getiPtzCommand();
/* 148 */     StringWriter stringWriter = new StringWriter();
/* 149 */     XmlSerializer serializer = Xml.newSerializer();
/*     */     try {
/* 151 */       boolean needParam = needIPtzCruisePointAndIPtzCruiseInput(iPtzCommand);
/* 152 */       serializer.setOutput(stringWriter);
/* 153 */       serializer.startTag(null, "MagMessage");
/* 154 */       insertXMLRequestExplain(serializer);
/* 155 */       serializer.startTag(null, "Params");
/* 156 */       serializer.startTag(null, "PTZControl");
/*     */       
/* 158 */       if (this.cascadeFlag != 1) {
/* 159 */         serializer.startTag(null, "SessionId");
/* 160 */         serializer.text(this.ptzBaseParam.getSessionId());
/* 161 */         serializer.endTag(null, "SessionId");
/* 162 */         serializer.startTag(null, "IIndex");
/* 163 */         if (needIndexParam(iPtzCommand)) {
/* 164 */           serializer.text(Integer.toString(this.iIndex));
/*     */         } else {
/* 166 */           serializer.text("");
/*     */         }
/* 168 */         serializer.endTag(null, "IIndex");
/* 169 */         serializer.startTag(null, "IUserId");
/* 170 */         serializer.text("");
/* 171 */         serializer.endTag(null, "IUserId");
/* 172 */         serializer.startTag(null, "ILockTime");
/* 173 */         if (needLockTime(iPtzCommand)) {
/* 174 */           serializer.text(this.lockTime);
/*     */         } else {
/* 176 */           serializer.text("");
/*     */         }
/* 178 */         serializer.endTag(null, "ILockTime");
/* 179 */         serializer.startTag(null, "IPtzCruisePoint");
/* 180 */         if (needParam) {
/* 181 */           serializer.text(Integer.toString(this.iPtzCruisePoint));
/*     */         } else {
/* 183 */           serializer.text("");
/*     */         }
/* 185 */         serializer.endTag(null, "IPtzCruisePoint");
/* 186 */         serializer.startTag(null, "IPtzCruiseInput");
/* 187 */         if (needParam) {
/* 188 */           serializer.text(Integer.toString(this.iPtzCruiseInput));
/*     */         } else {
/* 190 */           serializer.text("");
/*     */         }
/* 192 */         serializer.endTag(null, "IPtzCruiseInput");
/*     */       } else {
/* 194 */         serializer.startTag(null, "IPresetIndex");
/* 195 */         if (needIndexParam(iPtzCommand)) {
/* 196 */           serializer.text(Integer.toString(this.iIndex));
/*     */         } else {
/* 198 */           serializer.text("");
/*     */         }
/* 200 */         serializer.endTag(null, "IPresetIndex");
/*     */       }
/*     */       
/* 203 */       serializer.startTag(null, "SzCamIndexCode");
/* 204 */       serializer.text(this.ptzBaseParam.getSzCamIndexCode());
/* 205 */       serializer.endTag(null, "SzCamIndexCode");
/* 206 */       serializer.startTag(null, "IPtzCommand");
/* 207 */       serializer.text(Integer.toString(this.ptzBaseParam.getiPtzCommand()));
/* 208 */       serializer.endTag(null, "IPtzCommand");
/* 209 */       serializer.startTag(null, "IAction");
/* 210 */       if (needActionParam(iPtzCommand)) {
/* 211 */         serializer.text(Integer.toString(this.iAction));
/*     */       } else {
/* 213 */         serializer.text("");
/*     */       }
/* 215 */       serializer.endTag(null, "IAction");
/* 216 */       serializer.startTag(null, "ISpeed");
/* 217 */       if (needSpeedParam(iPtzCommand)) {
/* 218 */         serializer.text(Integer.toString(this.iSpeed));
/*     */       } else {
/* 220 */         serializer.text("");
/*     */       }
/* 222 */       serializer.endTag(null, "ISpeed");
/* 223 */       serializer.startTag(null, "IPriority");
/* 224 */       serializer.text(Integer.toString(this.ptzBaseParam.getiPriority()));
/* 225 */       serializer.endTag(null, "IPriority");
/* 226 */       serializer.startTag(null, "IMatrixCameraId");
/* 227 */       serializer.text("");
/* 228 */       serializer.endTag(null, "IMatrixCameraId");
/* 229 */       serializer.startTag(null, "IMonitorId");
/* 230 */       serializer.text("");
/* 231 */       serializer.endTag(null, "IMonitorId");
/* 232 */       needParam = needEnlargeParam(iPtzCommand);
/* 233 */       serializer.startTag(null, "Param1");
/* 234 */       if (needParam) {
/* 235 */         serializer.text(Integer.toString(this.ptzEnlargeParam.getStartX()));
/*     */       } else {
/* 237 */         serializer.text("");
/*     */       }
/* 239 */       serializer.endTag(null, "Param1");
/* 240 */       serializer.startTag(null, "Param2");
/* 241 */       if (needParam) {
/* 242 */         serializer.text(Integer.toString(this.ptzEnlargeParam.getStartY()));
/*     */       } else {
/* 244 */         serializer.text("");
/*     */       }
/* 246 */       serializer.endTag(null, "Param2");
/* 247 */       serializer.startTag(null, "Param3");
/* 248 */       if (needParam) {
/* 249 */         serializer.text(Integer.toString(this.ptzEnlargeParam.getStopX()));
/*     */       } else {
/* 251 */         serializer.text("");
/*     */       }
/* 253 */       serializer.endTag(null, "Param3");
/* 254 */       serializer.startTag(null, "Param4");
/* 255 */       if (needParam) {
/* 256 */         serializer.text(Integer.toString(this.ptzEnlargeParam.getStopY()));
/*     */       } else {
/* 258 */         serializer.text("");
/*     */       }
/* 260 */       serializer.endTag(null, "Param4");
/* 261 */       serializer.endTag(null, "PTZControl");
/* 262 */       serializer.endTag(null, "Params");
/* 263 */       serializer.endTag(null, "MagMessage");
/* 264 */       serializer.endDocument();
/* 265 */       serializer.flush();
/*     */     }
/*     */     catch (IllegalArgumentException e) {
/* 268 */       e.printStackTrace();
/* 269 */       return null;
/*     */     } catch (IllegalStateException e) {
/* 271 */       e.printStackTrace();
/* 272 */       return null;
/*     */     } catch (IOException e) {
/* 274 */       e.printStackTrace();
/* 275 */       return null;
/*     */     }
/* 277 */     String temp = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + stringWriter.toString();
/* 278 */     CNetSDKLog.i("PtzCommandRequest", "getRequestData,requestdata:" + temp);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 287 */     return temp;
/*     */   }
/*     */   
/*     */   private boolean isPtzParamOk()
/*     */   {
/* 292 */     if ((this.ptzBaseParam == null) || (this.ptzBaseParam.getSessionId() == null) || (this.ptzBaseParam.getSzCamIndexCode() == null) || 
/* 293 */       (this.ptzBaseParam.getSessionId().length() <= 0) || (this.ptzBaseParam.getSzCamIndexCode().length() <= 0)) {
/* 294 */       CNetSDKLog.e("PtzCommandRequest", "isParamOk,param error.");
/* 295 */       return false;
/*     */     }
/* 297 */     int iPtzCommand = this.ptzBaseParam.getiPtzCommand();
/* 298 */     CNetSDKLog.i("PtzCommandRequest", "isParamOk,iPtzCommang:" + iPtzCommand);
/* 299 */     if ((needActionParam(iPtzCommand)) && (this.paramMode != 1))
/*     */     {
/* 301 */       CNetSDKLog.e("PtzCommandRequest", "isParamOk,param error when not use PARAM_MODE_WITH_ACTION_SPEED");
/* 302 */       return false; }
/* 303 */     if ((needIndexParam(iPtzCommand)) && (this.paramMode != 2) && (this.paramMode != 3))
/*     */     {
/*     */ 
/*     */ 
/* 307 */       CNetSDKLog.e("PtzCommandRequest", "isParamOk,param error when not use PARAM_MODE_WITH_INDEX_OR_LOCKTIME or PARAM_MODE_WITH_INDEX_PTZCRUISE");
/*     */       
/* 309 */       return false; }
/* 310 */     if ((needLockTime(iPtzCommand)) && (this.paramMode != 2))
/*     */     {
/* 312 */       CNetSDKLog.e("PtzCommandRequest", "isParamOk,param error when not use PARAM_MODE_WITH_INDEX_OR_LOCKTIME");
/* 313 */       return false; }
/* 314 */     if ((needIPtzCruisePointAndIPtzCruiseInput(iPtzCommand)) && (this.paramMode != 3))
/*     */     {
/* 316 */       CNetSDKLog.e("PtzCommandRequest", "isParamOk,param error when not use PARAM_MODE_WITH_INDEX_PTZCRUISE ");
/* 317 */       return false; }
/* 318 */     if (needEnlargeParam(iPtzCommand)) {
/* 319 */       if (this.paramMode != 4)
/*     */       {
/* 321 */         CNetSDKLog.e("PtzCommandRequest", "isParamOk,param error when not use PARAM_MODE_WITH_ZOOMPARAM ");
/* 322 */         return false; }
/* 323 */       if (this.ptzEnlargeParam == null) {
/* 324 */         CNetSDKLog.e("PtzCommandRequest", "isParamOk,param error ptzZoomParam is null.");
/* 325 */         return false;
/*     */       }
/*     */     }
/* 328 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean needActionParam(int iPtzCommand) {
/* 332 */     CNetSDKLog.i("PtzCommandRequest", "needActionParam,iPtzCommand:" + iPtzCommand);
/* 333 */     if ((needSpeedParam(iPtzCommand)) || ((iPtzCommand >= 2) && (iPtzCommand <= 7))) {
/* 334 */       return true;
/*     */     }
/* 336 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean needEnlargeParam(int iPtzCommand) {
/* 340 */     if (iPtzCommand == 99) {
/* 341 */       return true;
/*     */     }
/* 343 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean needSpeedParam(int iPtzCommand) {
/* 347 */     if (((iPtzCommand >= 21) && (iPtzCommand <= 29)) || ((iPtzCommand >= 11) && (iPtzCommand <= 16))) {
/* 348 */       return true;
/*     */     }
/* 350 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean needIndexParam(int iPtzCommand)
/*     */   {
/* 355 */     if ((iPtzCommand == 8) || (iPtzCommand == 9) || (iPtzCommand == 31) || (iPtzCommand == 32) || ((iPtzCommand >= 37) && (iPtzCommand <= 39)))
/*     */     {
/* 357 */       return true;
/*     */     }
/* 359 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean needLockTime(int iPtzCommand)
/*     */   {
/* 364 */     if (iPtzCommand == 200) {
/* 365 */       return true;
/*     */     }
/* 367 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean needIPtzCruisePointAndIPtzCruiseInput(int iPtzCommand) {
/* 371 */     if ((iPtzCommand == 31) || (iPtzCommand == 32)) {
/* 372 */       return true;
/*     */     }
/* 374 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\mag\ptz\PtzCommandRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */