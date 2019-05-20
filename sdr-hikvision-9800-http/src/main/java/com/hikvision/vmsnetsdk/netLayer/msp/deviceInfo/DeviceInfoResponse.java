/*    */ package com.hikvision.vmsnetsdk.netLayer.msp.deviceInfo;
/*    */ 
/*    */

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspBackState;
import com.hikvision.vmsnetsdk.netLayer.msp.MspResponse;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DeviceInfoResponse
/*    */   extends MspResponse
/*    */ {
/*    */   private static final String TAG = "DeviceInfoResponse";
/*    */   private DeviceInfoBackState deviceInfoBackState;
/*    */   private DeviceInfo deviceInfo;
/*    */   
/*    */   public DeviceInfo getDeviceInfo()
/*    */   {
/* 25 */     return this.deviceInfo;
/*    */   }
/*    */   
/*    */   public DeviceInfoResponse(String responseData)
/*    */   {
/* 30 */     super(responseData);
/*    */   }
/*    */   
/*    */   public String getResponseData()
/*    */   {
/* 35 */     return super.getResponseData();
/*    */   }
/*    */   
/*    */   public MspBackState getMspBackState()
/*    */   {
/* 40 */     return this.deviceInfoBackState;
/*    */   }
/*    */   
/*    */   protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception
/*    */   {
/* 45 */     this.deviceInfo = new DeviceInfo();
/* 46 */     this.deviceInfoBackState = new DeviceInfoBackState();
/* 47 */     return doParseCycle(xmlPullParser);
/*    */   }
/*    */   
/*    */   protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName)
/*    */     throws NumberFormatException, XmlPullParserException, IOException, Exception
/*    */   {
/* 53 */     if ((xmlPullParser == null) || (nodeName == null)) {
/* 54 */       CNetSDKLog.e("DeviceInfoResponse", "handleXMLStartTag,param error");
/* 55 */       throw new Exception("handleXMLStartTag,param error");
/*    */     }
/* 57 */     if ("Status".equals(nodeName)) {
/* 58 */       String status = xmlPullParser.nextText();
/* 59 */       this.deviceInfoBackState.setCode(Integer.parseInt(status.trim()));
/* 60 */       CNetSDKLog.d("DeviceInfoResponse", "handleXMLStartTag,status：" + status);
/* 61 */     } else if ("Description".equals(nodeName)) {
/* 62 */       String description = xmlPullParser.nextText();
/* 63 */       this.deviceInfoBackState.setDescription(description);
/* 64 */       CNetSDKLog.d("DeviceInfoResponse", "handleXMLStartTag,description：" + description);
/* 65 */     } else if ("Supplier".equals(nodeName)) {
/* 66 */       String supplier = xmlPullParser.nextText();
/* 67 */       CNetSDKLog.d("DeviceInfoResponse", "handleXMLStartTag,supplier：" + supplier);
/* 68 */       this.deviceInfo.setSupplier(supplier);
/* 69 */     } else if ("IndexCode".equals(nodeName)) {
/* 70 */       String indexCode = xmlPullParser.nextText();
/* 71 */       CNetSDKLog.d("DeviceInfoResponse", "handleXMLStartTag,indexCode：" + indexCode);
/* 72 */       this.deviceInfo.setIndexCode(indexCode);
/* 73 */     } else if ("DeviceName".equals(nodeName)) {
/* 74 */       String deviceName = xmlPullParser.nextText();
/* 75 */       CNetSDKLog.d("DeviceInfoResponse", "handleXMLStartTag,deviceName：" + deviceName);
/* 76 */       this.deviceInfo.setDeviceName(deviceName);
/* 77 */     } else if ("DeviceType".equals(nodeName)) {
/* 78 */       String deviceType = xmlPullParser.nextText();
/* 79 */       CNetSDKLog.d("DeviceInfoResponse", "handleXMLStartTag,deviceType：" + deviceType);
/* 80 */       this.deviceInfo.setDeviceType(deviceType);
/* 81 */     } else if ("DeviceIP".equals(nodeName)) {
/* 82 */       String deviceIP = xmlPullParser.nextText();
/* 83 */       CNetSDKLog.d("DeviceInfoResponse", "handleXMLStartTag,deviceIP：" + deviceIP);
/* 84 */       this.deviceInfo.setDeviceIP(deviceIP);
/* 85 */     } else if ("DevicePort".equals(nodeName)) {
/* 86 */       String devicePort = xmlPullParser.nextText();
/* 87 */       CNetSDKLog.d("DeviceInfoResponse", "handleXMLStartTag,devicePort：" + devicePort);
/* 88 */       if ((devicePort != null) && (devicePort.length() > 0)) {
/* 89 */         this.deviceInfo.setDevicePort(Integer.parseInt(devicePort));
/*    */       }
/* 91 */     } else if ("UserName".equals(nodeName)) {
/* 92 */       String userName = xmlPullParser.nextText();
/* 93 */       CNetSDKLog.d("DeviceInfoResponse", "handleXMLStartTag,userName：" + userName);
/* 94 */       this.deviceInfo.setLoginName(userName);
/* 95 */     } else if ("Password".equals(nodeName)) {
/* 96 */       String password = xmlPullParser.nextText();
/* 97 */       CNetSDKLog.d("DeviceInfoResponse", "handleXMLStartTag,password：" + password);
/* 98 */       this.deviceInfo.setLoginPsw(password);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\netLayer\msp\deviceInfo\DeviceInfoResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */