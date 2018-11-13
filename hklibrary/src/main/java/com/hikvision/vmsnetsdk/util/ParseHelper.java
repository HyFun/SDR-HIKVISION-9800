//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hikvision.vmsnetsdk.util;

import android.text.TextUtils;

import com.hikvision.vmsnetsdk.CNetSDKLog;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

public class ParseHelper {
    private static final String TAG = "ParseHelper";

    public ParseHelper() {
    }

    public static ArrayList<Integer> parseStrToIntByComma(String str) throws NumberFormatException {
        if(str != null && str.length() > 0) {
            String normalInput = str.trim();
            CNetSDKLog.d("ParseHelper", "parseStrToIntByComma,normalInput:" + normalInput);
            ArrayList<Integer> intList = new ArrayList();
            if(!normalInput.contains(",")) {
                Integer tempInt = Integer.valueOf(Integer.parseInt(normalInput));
                intList.add(tempInt);
                return intList;
            } else {
                String[] strArr = normalInput.split(",");

                for(int i = 0; i < strArr.length; ++i) {
                    String tempStr = strArr[i];
                    CNetSDKLog.d("ParseHelper", "parseStrToIntByComma,tempStr:" + tempStr);
                    int tempInteger = Integer.parseInt(tempStr);
                    intList.add(Integer.valueOf(tempInteger));
                }

                return intList;
            }
        } else {
            CNetSDKLog.d("ParseHelper", "parseStrToIntByComma,param is null or empty_v1.");
            return null;
        }
    }

    public static String changeIntListToString(ArrayList<Integer> intList) {
        if(intList != null && !intList.isEmpty()) {
            StringBuilder strBuilder = new StringBuilder();
            Iterator var2 = intList.iterator();

            while(var2.hasNext()) {
                Integer integer = (Integer)var2.next();
                strBuilder.append(integer);
                strBuilder.append(",");
            }

            try {
                strBuilder.deleteCharAt(strBuilder.lastIndexOf(","));
            } catch (StringIndexOutOfBoundsException var4) {
                var4.printStackTrace();
                CNetSDKLog.e("ParseHelper", "changeIntListToString,StringIndexOutOfBoundsException");
                return null;
            }

            String str = strBuilder.toString();
            CNetSDKLog.d("ParseHelper", "changeIntListToString,str:" + str);
            return str;
        } else {
            CNetSDKLog.d("ParseHelper", "changeIntListToString,param is null or empty_v1.");
            return null;
        }
    }

    public static String inetAddress(String host) {
        if(TextUtils.isEmpty(host)) {
            return null;
        } else {
            String ipAddress = "";

            try {
                InetAddress inetAddress = InetAddress.getByName(host);
                ipAddress = inetAddress.getHostAddress();
                CNetSDKLog.d("ParseHelper", "inetAddress() ipAddress:" + ipAddress);
            } catch (UnknownHostException var3) {
                CNetSDKLog.d("ParseHelper", "inetAddress() UnknownHostException:" + host);
                var3.printStackTrace();
            }

            return ipAddress;
        }
    }
}
