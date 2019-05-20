package com.hikvision.vmsnetsdk.intf;

import com.hikvision.vmsnetsdk.CameraInfo;
import com.hikvision.vmsnetsdk.CameraInfoEx;
import com.hikvision.vmsnetsdk.ControlUnitInfo;
import com.hikvision.vmsnetsdk.GISCameraInfo;
import com.hikvision.vmsnetsdk.RegionInfo;

import java.util.List;

public abstract interface IVMSNetSDKResource
  extends IVMSNetSDK
{
  public abstract boolean getCameraListFromCtrlUnit(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, List<CameraInfo> paramList);
  
  public abstract boolean getCameraListFromRegion(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, List<CameraInfo> paramList);
  
  public abstract boolean collectCamera(String paramString1, String paramString2, String paramString3, int paramInt);
  
  public abstract boolean discollectCamera(String paramString1, String paramString2, String paramString3, int paramInt);
  
  public abstract boolean getCollectedCameraList(String paramString1, String paramString2, int paramInt1, int paramInt2, List<CameraInfo> paramList);
  
  public abstract boolean getGISCameraList(String paramString1, String paramString2, int paramInt1, int paramInt2, List<GISCameraInfo> paramList);
  
  public abstract boolean getCameraInfoEx(String paramString1, String paramString2, String paramString3, CameraInfoEx paramCameraInfoEx);
  
  public abstract boolean getControlUnitList(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, List<ControlUnitInfo> paramList);
  
  public abstract boolean getRegionListFromCtrlUnit(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, List<RegionInfo> paramList);
  
  public abstract boolean getRegionListFromRegion(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, List<RegionInfo> paramList);
}


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\intf\IVMSNetSDKResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */