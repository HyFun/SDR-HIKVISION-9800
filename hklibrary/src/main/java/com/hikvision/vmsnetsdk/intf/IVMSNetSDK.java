package com.hikvision.vmsnetsdk.intf;

import com.hikvision.vmsnetsdk.LineInfo;
import com.hikvision.vmsnetsdk.SDKGISInitInfo;
import com.hikvision.vmsnetsdk.ServInfo;
import com.hikvision.vmsnetsdk.ServerConfig;

import java.util.List;

public abstract interface IVMSNetSDK
{
  public abstract boolean getVerCode(String paramString);
  
  public abstract boolean updatePassword(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt);
  
  public abstract boolean getLineList(String paramString, List<LineInfo> paramList);
  
  public abstract boolean login(String paramString1, String paramString2, String paramString3, int paramInt1, String paramString4, int paramInt2, ServInfo paramServInfo);
  
  public abstract boolean login(String paramString1, String paramString2, String paramString3, String paramString4, ServInfo paramServInfo);
  
  public abstract boolean login(String paramString1, String paramString2, String paramString3, String paramString4, ServInfo paramServInfo, String paramString5);
  
  public abstract boolean getGISInitInfo(String paramString1, String paramString2, SDKGISInitInfo paramSDKGISInitInfo);
  
  public abstract int getLastErrorCode();
  
  public abstract String getLastErrorDesc();
  
  public abstract boolean isPlatformNew();
  
  public abstract String getAutoLoginSession(String paramString, int paramInt);
  
  public abstract String getPlayToken(String paramString);
  
  public abstract boolean autoLogin(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, ServInfo paramServInfo);
  
  public abstract boolean safeLoginOldPlatform(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, String paramString5, String paramString6, String paramString7, ServInfo paramServInfo, ServerConfig paramServerConfig);
  
  public abstract boolean safeLoginNewPlatform(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, String paramString5, String paramString6, String paramString7, ServInfo paramServInfo, ServerConfig paramServerConfig);
  
  public abstract boolean getServerConfig(String paramString, ServerConfig paramServerConfig, boolean paramBoolean);
}


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\intf\IVMSNetSDK.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */