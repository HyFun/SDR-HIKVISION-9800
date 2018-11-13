//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hikvision.vmsnetsdk.netLayer.msp;

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.base.NetHttpServer;

public class MspServer extends NetHttpServer {
    private String TAG = "MspServer";
    private String inputAddr;
    private String ip = null;
    private int port = 0;

    public MspServer(String ip, int port) {
        super(ip, port);
        this.ip = ip;
        this.port = port;
        this.ip = this.getNoHeadAddr(ip);
        if(this.ip.contains(":")) {
            this.ip = null;
        }

    }

    public MspServer(String addr) {
        super(addr, 0);
        this.inputAddr = addr;
        boolean res = this.initIpPortByAddr(addr);
        if(!res) {
            this.inputAddr = null;
            this.ip = null;
            this.port = 0;
        }

    }

    private boolean initIpPortByAddr(String addr) {
        if(addr != null && addr.length() > 0) {
            String cleanAddr = this.getNoHeadAddr(addr);
            cleanAddr = this.getNoEndAddr(cleanAddr);
            if(!cleanAddr.contains(":")) {
                CNetSDKLog.i(this.TAG, "initIpPortByAddr,the input addr error,no : found");
                this.ip = cleanAddr;
                this.port = 0;
                return true;
            } else {
                String[] strArr = cleanAddr.split(":");
                if(strArr.length != 2) {
                    CNetSDKLog.e(this.TAG, "initIpPortByAddr,the input addr error,found to much :");
                    return false;
                } else {
                    this.ip = strArr[0];

                    try {
                        this.port = Integer.parseInt(strArr[1]);
                        return true;
                    } catch (Exception var5) {
                        var5.printStackTrace();
                        CNetSDKLog.e(this.TAG, "initIpPortByAddr,Exception");
                        return false;
                    }
                }
            }
        } else {
            CNetSDKLog.e(this.TAG, "initIpPortByAddr,the input addr error");
            return false;
        }
    }

    public String gethttpAddr() {
        if(this.inputAddr != null && this.inputAddr.startsWith("http://")) {
            return this.inputAddr;
        } else {
            String ip = this.getIp();
            CNetSDKLog.i(this.TAG, "ip:" + ip);
            if(this.inputAddr == null && ip == null) {
                return null;
            } else {
                StringBuilder httpIpStrBuilder = new StringBuilder();
                httpIpStrBuilder.append("http://");
                httpIpStrBuilder.append(this.getCleanAddr());
                String httpIp = httpIpStrBuilder.toString();
                return httpIp;
            }
        }
    }

    public String getCleanAddr() {
        return this.inputAddr != null?this.getNoHeadAddr(this.inputAddr):(this.ip != null?(this.port != 0?this.ip + ":" + this.port:this.ip):null);
    }

    private String getNoHeadAddr(String addr) {
        if(addr == null) {
            return null;
        } else {
            String str;
            if(addr.startsWith("http://")) {
                str = addr.substring(7);
            } else if(addr.startsWith("https://")) {
                str = addr.substring(8);
            } else {
                str = addr;
            }

            CNetSDKLog.i(this.TAG, "getNoHeadAddr:" + str);
            return str;
        }
    }

    private String getNoEndAddr(String addr) {
        if(addr == null) {
            return null;
        } else if(!addr.contains("/")) {
            return addr;
        } else {
            String[] tempStrs = addr.split("/");
            CNetSDKLog.i(this.TAG, "getNoEndAddr:" + tempStrs[0]);
            return tempStrs[0];
        }
    }

    public String gethttpsAddr() {
        if(this.inputAddr != null && this.inputAddr.startsWith("https://")) {
            return this.inputAddr;
        } else {
            String ip = this.getIp();
            CNetSDKLog.i(this.TAG, "ip:" + ip);
            if(this.inputAddr == null && ip == null) {
                return null;
            } else {
                StringBuilder httpIpStrBuilder = new StringBuilder();
                httpIpStrBuilder.append("https://");
                httpIpStrBuilder.append(this.getCleanAddr());
                String httpsIp = httpIpStrBuilder.toString();
                CNetSDKLog.i(this.TAG, "gethttpsAddr:" + httpsIp);
                return httpsIp;
            }
        }
    }

    public String getIp() {
        return this.ip;
    }

    public int getPort() {
        return this.port;
    }
}
