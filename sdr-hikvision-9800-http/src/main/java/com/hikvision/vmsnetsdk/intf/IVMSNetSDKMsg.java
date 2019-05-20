package com.hikvision.vmsnetsdk.intf;

import com.hikvision.vmsnetsdk.SDKAlarmBulletinDetail;
import com.hikvision.vmsnetsdk.SDKBulletin;
import com.hikvision.vmsnetsdk.SDKBulletinDetail;
import com.hikvision.vmsnetsdk.SDKUnBullentinCount;
import com.hikvision.vmsnetsdk.netLayer.mag.kms.KmsInfo;
import com.hikvision.vmsnetsdk.netLayer.msp.alarmInfo.AlarmInfo;

import java.util.List;

public abstract interface IVMSNetSDKMsg
  extends IVMSNetSDK
{
  public abstract boolean getAlarmMsgDetailList(String paramString1, String paramString2, String paramString3, int paramInt, List<SDKAlarmBulletinDetail> paramList);
  
  public abstract boolean getAlarmMsgDetailHistoryList(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, List<SDKAlarmBulletinDetail> paramList);
  
  public abstract boolean getAlarmMsgDetail(String paramString1, String paramString2, String paramString3, SDKAlarmBulletinDetail paramSDKAlarmBulletinDetail);
  
  public abstract boolean getAlarmDetailInfo(String paramString1, String paramString2, String paramString3, AlarmInfo paramAlarmInfo);
  
  public abstract boolean checkMsgByID(String paramString1, String paramString2, String paramString3);
  
  public abstract boolean checkMsgByType(String paramString1, String paramString2, String paramString3);
  
  public abstract boolean getMsgHistoryList(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, List<SDKBulletin> paramList);
  
  public abstract boolean getMsgList(String paramString1, String paramString2, int paramInt, List<SDKBulletin> paramList);
  
  public abstract boolean getMsgDetailList(String paramString1, String paramString2, String paramString3, int paramInt, List<SDKBulletinDetail> paramList);
  
  public abstract boolean getMsgDetailHistoryList(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, List<SDKBulletinDetail> paramList);
  
  public abstract boolean getMsgDetail(String paramString1, String paramString2, String paramString3, SDKBulletinDetail paramSDKBulletinDetail);
  
  public abstract boolean getUnreadMsgCount(String paramString1, String paramString2, SDKUnBullentinCount paramSDKUnBullentinCount);
  
  public abstract boolean deleteMsgByType(String paramString1, String paramString2, String paramString3);
  
  public abstract boolean deleteMsgByID(String paramString1, String paramString2, String paramString3);
  
  public abstract KmsInfo getKmsInfo();
}


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\intf\IVMSNetSDKMsg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */