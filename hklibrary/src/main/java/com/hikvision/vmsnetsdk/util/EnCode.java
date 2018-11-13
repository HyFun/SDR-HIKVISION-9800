//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hikvision.vmsnetsdk.util;

import com.hikvision.vmsnetsdk.CNetSDKLog;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EnCode {
    private static final String TAG = "EnCode";
    private static String UTF_8_KEY = "utf-8";
    private static String SHA_256_KEY = "SHA-256";
    private static String MD_5_KEY = "MD5";

    public EnCode() {
    }

    static void testUtftKey(String testKey) {
        if(testKey == null) {
            UTF_8_KEY = "utf-8";
        } else {
            UTF_8_KEY = testKey;
        }
    }

    static void testSHAKey(String testKey) {
        if(testKey == null) {
            SHA_256_KEY = "SHA-256";
        } else {
            SHA_256_KEY = testKey;
        }
    }

    static void testMDKey(String testKey) {
        if(testKey == null) {
            MD_5_KEY = "MD5";
        } else {
            MD_5_KEY = testKey;
        }
    }

    public static String enCodeByUtf8(String str) {
        if(str == null) {
            return null;
        } else {
            try {
                String backStr = URLEncoder.encode(str, UTF_8_KEY);
                return backStr;
            } catch (Exception var3) {
                var3.printStackTrace();
                return str;
            }
        }
    }

    public static String enCodeBySHA256(String str) {
        if(str == null) {
            return null;
        } else {
            byte[] bytes = null;
            String retStr = null;
            try {
                bytes = str.getBytes(UTF_8_KEY);
            } catch (UnsupportedEncodingException var10) {
                var10.printStackTrace();
                return null;
            }

            try {
                MessageDigest md = MessageDigest.getInstance(SHA_256_KEY);
                if(bytes != null && md != null && bytes.length > 0) {
                    md.update(bytes);
                    StringBuilder buf = new StringBuilder(bytes.length * 2);
                    byte[] var5 = md.digest();
                    int var6 = var5.length;

                    for(int var7 = 0; var7 < var6; ++var7) {
                        byte b = var5[var7];
                        String strTemp = String.format("%02x", new Object[]{Byte.valueOf(b)});
                        buf.append(strTemp);
                    }

                    retStr = buf.toString();
                    return retStr;
                } else {
                    CNetSDKLog.e("EnCode", "enCodeBySHA256,bytes or md is null");
                    return null;
                }
            } catch (NoSuchAlgorithmException var11) {
                var11.printStackTrace();
                return null;
            }
        }
    }

    public static String enCodeByMd5(String input) {
        if(input != null && input.length() > 0) {
            char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

            try {
                byte[] btInput = input.getBytes();
                MessageDigest mdInst = MessageDigest.getInstance(MD_5_KEY);
                mdInst.update(btInput);
                byte[] md = mdInst.digest();
                int j = md.length;
                char[] str = new char[j * 2];
                int k = 0;

                for(int i = 0; i < j; ++i) {
                    byte byte0 = md[i];
                    str[k++] = hexDigits[byte0 >>> 4 & 15];
                    str[k++] = hexDigits[byte0 & 15];
                }

                return new String(str);
            } catch (Exception var10) {
                var10.printStackTrace();
                return null;
            }
        } else {
            return input;
        }
    }
}
