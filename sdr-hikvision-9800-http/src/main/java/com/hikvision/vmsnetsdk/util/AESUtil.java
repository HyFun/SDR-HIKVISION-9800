//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hikvision.vmsnetsdk.util;

import android.os.Build.VERSION;
import android.util.Base64;

import com.hikvision.vmsnetsdk.CNetSDKLog;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
    public static final String MAG_AES_KEY = "12345678";
    private static final String TAG = "AESUtil";
    private static final String HEX = "0123456789ABCDEF";
    private static final int JELLY_BEAN_4_2 = 17;
    private static final byte[] key = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public AESUtil() {
    }

    public static String encrypt(String seed, String cleartext) throws Exception {
        byte[] rawKey = getRawKey(seed.getBytes("utf-8"));
        byte[] result = encrypt(rawKey, cleartext.getBytes("utf-8"));
        String fromHex = toHex(result);
        String base64 = Base64.encodeToString(fromHex.getBytes("utf-8"), 0);
        return base64;
    }

    public static String decrypt(String seed, String encrypted) throws Exception {
        byte[] seedByte = seed.getBytes("utf-8");
        System.arraycopy(seedByte, 0, key, 0, seedByte.length < 16?seedByte.length:16);
        String base64 = new String(Base64.decode(encrypted, 0), "utf-8");
        byte[] rawKey = getRawKey(seedByte);
        byte[] enc = toByte(base64);
        byte[] result = decrypt(rawKey, enc);
        return new String(result, "utf-8");
    }

    public static byte[] encryptBytes(String seed, byte[] cleartext) throws Exception {
        byte[] rawKey = getRawKey(seed.getBytes("utf-8"));
        byte[] result = encrypt(rawKey, cleartext);
        return result;
    }

    public static byte[] decryptBytes(String seed, byte[] encrypted) throws Exception {
        byte[] rawKey = getRawKey(seed.getBytes("utf-8"));
        byte[] result = decrypt(rawKey, encrypted);
        return result;
    }

    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = null;
        if(VERSION.SDK_INT >= 17) {
            sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        } else {
            sr = SecureRandom.getInstance("SHA1PRNG");
        }

        sr.setSeed(seed);

        try {
            kgen.init(256, sr);
        } catch (Exception var6) {
            CNetSDKLog.w("AESUtil", "This device doesn't suppor 256bits, trying 192bits.");

            try {
                kgen.init(192, sr);
            } catch (Exception var5) {
                CNetSDKLog.w("AESUtil", "This device doesn't suppor 192bits, trying 128bits.");
                kgen.init(128, sr);
            }
        }

        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }

    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(1, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }

    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(2, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    public static String toHex(String txt) {
        try {
            return toHex(txt.getBytes("utf-8"));
        } catch (UnsupportedEncodingException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static String fromHex(String hex) {
        try {
            return new String(toByte(hex), "utf-8");
        } catch (UnsupportedEncodingException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];

        for(int i = 0; i < len; ++i) {
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        }

        return result;
    }

    public static String toHex(byte[] buf) {
        if(buf == null) {
            return "";
        } else {
            StringBuffer result = new StringBuffer(2 * buf.length);

            for(int i = 0; i < buf.length; ++i) {
                appendHex(result, buf[i]);
            }

            return result.toString();
        }
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append("0123456789ABCDEF".charAt(b >> 4 & 15)).append("0123456789ABCDEF".charAt(b & 15));
    }
}
