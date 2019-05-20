package com.hikvision.vmsnetsdk.intf;

import com.hikvision.vmsnetsdk.GISCameraInfo;
import com.hikvision.vmsnetsdk.netLayer.msp.deviceGps.GpsTrackListInfo;
import com.hikvision.vmsnetsdk.netLayer.msp.gisPoint.GISPointInfo;

import java.util.ArrayList;
import java.util.List;

public abstract interface IVMSNetSDKGis
  extends IVMSNetSDK
{
  public abstract boolean getGISPointInfo(String paramString1, String paramString2, ArrayList<Integer> paramArrayList, int paramInt1, int paramInt2, List<GISPointInfo> paramList);
  
  public abstract boolean getGISCameraList(String paramString1, String paramString2, int paramInt1, int paramInt2, List<GISCameraInfo> paramList);
  
  public abstract boolean getGISCameraListByName(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3, String paramString4, List<GISCameraInfo> paramList);
  
  public abstract boolean getGISCameraListByPostion(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, String paramString4, String paramString5, int paramInt3, List<GISCameraInfo> paramList);
  
  public abstract boolean searchGisPoint(String paramString1, String paramString2, String paramString3, String paramString4, List<GISPointInfo> paramList);
  
  public abstract boolean searchGisPoint(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3, String paramString4, List<GISPointInfo> paramList);
  
  public abstract boolean searchGisPointInRound(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, int paramInt2, String paramString5, String paramString6, int paramInt3, List<GISPointInfo> paramList);
  
  public abstract boolean searchGisPointInRectangle(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, String paramString4, String paramString5, String paramString6, String paramString7, List<GISPointInfo> paramList);
  
  public abstract boolean getDeviceGPSTrackInfoByIndex(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, String paramString4, String paramString5, int paramInt3, GpsTrackListInfo paramGpsTrackListInfo);
}


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\intf\IVMSNetSDKGis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */