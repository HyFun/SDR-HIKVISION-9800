//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hikvision.vmsnetsdk.netLayer.mag;

import com.hikvision.vmsnetsdk.CNetSDKLog;

import java.io.Serializable;
import java.util.List;

public class MAGServer implements Serializable {
    private static final long serialVersionUID = 1L;
    private String magNotifyAddr;
    private int magNotifyPort;
    private List<Integer> magCapability;
    private String magStreamSerAddr;
    private int magStreamSerPort;
    private String magFileSerAddr;
    private int magFileSerPort;
    private String userName;
    private String password;
    private String magHttpSerAddr;
    private int magHttpSerPort;
    private String magGPSSerAddr;
    private int magGPSSerPort;
    private String magTalkAddr;
    private int magTalkPort;
    private static final String TAG = "MAGServer";
    public static final String HTTPURL_KEY_LOGOUT = "logout";

    public MAGServer() {
    }

    public String getMagNotifyAddr() {
        return this.magNotifyAddr;
    }

    public void setMagNotifyAddr(String magNotifyAddr) {
        this.magNotifyAddr = magNotifyAddr;
    }

    public int getMagNotifyPort() {
        return this.magNotifyPort;
    }

    public void setMagNotifyPort(int magNotifyPort) {
        this.magNotifyPort = magNotifyPort;
    }

    public String getMagGPSSerAddr() {
        return this.magGPSSerAddr;
    }

    public void setMagGPSSerAddr(String magGPSSerAddr) {
        this.magGPSSerAddr = magGPSSerAddr;
    }

    public int getMagGPSSerPort() {
        return this.magGPSSerPort;
    }

    public void setMagGPSSerPort(int magGPSSerPort) {
        this.magGPSSerPort = magGPSSerPort;
    }

    public List<Integer> getMagCapability() {
        return this.magCapability;
    }

    public void setMagCapability(List<Integer> magCapability) {
        this.magCapability = magCapability;
    }

    public String getMagStreamSerAddr() {
        return this.magStreamSerAddr;
    }

    public void setMagStreamSerAddr(String magStreamSerAddr) {
        this.magStreamSerAddr = magStreamSerAddr;
    }

    public int getMagStreamSerPort() {
        return this.magStreamSerPort;
    }

    public void setMagStreamSerPort(int magStreamSerPort) {
        this.magStreamSerPort = magStreamSerPort;
    }

    public String getMagFileSerAddr() {
        return this.magFileSerAddr;
    }

    public void setMagFileSerAddr(String magFileSerAddr) {
        this.magFileSerAddr = magFileSerAddr;
    }

    public int getMagFileSerPort() {
        return this.magFileSerPort;
    }

    public void setMagFileSerPort(int magFileSerPort) {
        this.magFileSerPort = magFileSerPort;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String ftpUserName) {
        this.userName = ftpUserName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String ftpPassword) {
        this.password = ftpPassword;
    }

    public String getMagHttpSerAddr() {
        return this.magHttpSerAddr;
    }

    public void setMagHttpSerAddr(String magHttpSerAddr) {
        this.magHttpSerAddr = magHttpSerAddr;
    }

    public String getMagHttpsRequestAddrHead(boolean isPlatformNew) {
        if(this.magHttpSerAddr != null && !this.magHttpSerAddr.equals("")) {
            String requestIp;
            if(this.magHttpSerAddr.startsWith("https://")) {
                requestIp = this.magHttpSerAddr.substring("https://".length());
            } else {
                requestIp = this.magHttpSerAddr;
            }

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://");
            stringBuilder.append(requestIp);
            if(this.magHttpSerPort > 0) {
                stringBuilder.append(":");
                stringBuilder.append(this.magHttpSerPort);
            }

            if(isPlatformNew) {
                stringBuilder.append("/mag/");
            }

            String requestAddrHead = stringBuilder.toString();
            CNetSDKLog.i("MAGServer", "getMagHttpRequestAddrHead,requestAddrHead:" + requestAddrHead);
            return requestAddrHead;
        } else {
            return null;
        }
    }

    public String getMagHttpRequestAddrHead(boolean isPlatformNew) {
        if(this.magHttpSerAddr != null && !this.magHttpSerAddr.equals("")) {
            String requestIp;
            if(this.magHttpSerAddr.startsWith("http://")) {
                requestIp = this.magHttpSerAddr.substring("http://".length());
            } else {
                requestIp = this.magHttpSerAddr;
            }

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("http://");
            stringBuilder.append(requestIp);
            if(this.magHttpSerPort > 0) {
                stringBuilder.append(":");
                stringBuilder.append(this.magHttpSerPort);
            }

            if(isPlatformNew) {
                stringBuilder.append("/mag/");
            }

            String requestAddrHead = stringBuilder.toString();
            CNetSDKLog.i("MAGServer", "getMagHttpRequestAddrHead,requestAddrHead:" + requestAddrHead);
            return requestAddrHead;
        } else {
            return null;
        }
    }

    public int getMagHttpSerPort() {
        return this.magHttpSerPort;
    }

    public void setMagHttpSerPort(int magHttpSerPort) {
        this.magHttpSerPort = magHttpSerPort;
    }

    public String toString() {
        return "MAGServer [magNotifyAddr=" + this.magNotifyAddr + ", magNotifyPort=" + this.magNotifyPort + ", magCapability=" + this.magCapability + ", magStreamSerAddr=" + this.magStreamSerAddr + ", magStreamSerPort=" + this.magStreamSerPort + ", magFileSerAddr=" + this.magFileSerAddr + ", magFileSerPort=" + this.magFileSerPort + ", ftpUserName=" + this.userName + ", ftpPassword=" + this.password + ", magHttpSerAddr=" + this.magHttpSerAddr + ", magHttpSerPort=" + this.magHttpSerPort + ", magGPSSerAddr=" + this.magGPSSerAddr + ", magGPSSerPort=" + this.magGPSSerPort + ", magTalkAddr=" + this.magTalkAddr + ", magTalkPort=" + this.magTalkPort + "]";
    }

    public String getMagTalkAddr() {
        return this.magTalkAddr;
    }

    public void setMagTalkAddr(String magTalkAddr) {
        this.magTalkAddr = magTalkAddr;
    }

    public int getMagTalkPort() {
        return this.magTalkPort;
    }

    public void setMagTalkPort(int magTalkPort) {
        this.magTalkPort = magTalkPort;
    }
}
