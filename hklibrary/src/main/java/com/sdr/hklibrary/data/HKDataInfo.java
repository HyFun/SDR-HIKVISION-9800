package com.sdr.hklibrary.data;

import com.hikvision.vmsnetsdk.ServInfo;

/**
 * Created by HyFun on 2018/11/13.
 * Email: 775183940@qq.com
 * Description:
 */

public class HKDataInfo {
    private HKDataInfo() {
    }

    private static HKDataInfo dataInfo;

    public static final HKDataInfo getInstance() {
        if (dataInfo == null) {
            synchronized (HKDataInfo.class) {
                if (dataInfo == null) {
                    dataInfo = new HKDataInfo();
                }
            }
        }
        return dataInfo;
    }

    public static final void destory() {
        dataInfo = null;
    }


    private ServInfo servInfo;
    private String url;
    private String userName;
    private String passWord;

    public ServInfo getServInfo() {
        return servInfo;
    }

    public void setServInfo(ServInfo servInfo) {
        this.servInfo = servInfo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
