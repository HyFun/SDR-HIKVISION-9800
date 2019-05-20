//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hikvision.vmsnetsdk.util.intf;

public interface IRequestTool {
  String httpsPostRequest(String var1, String var2);

  String httpPostRequest(String var1, String var2);

  String httpsGetRequest(String var1);

  String httpGetRequest(String var1);

  int getLastErrorCode();

  String getLastErrorDesc();

  void setTimeout(int var1);
}
