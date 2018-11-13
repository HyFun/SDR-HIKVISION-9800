//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hikvision.vmsnetsdk.util;

import android.text.TextUtils;

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.util.intf.IRequestTool;

import org.apache.http.ConnectionClosedException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Locale;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpUtil implements IRequestTool {
    private static final String TAG = "HttpUtils";
    private int mTimeOut = 20000;
    private static final String CLIENT_AGREEMENT = "TLS";
    private static HttpUtil mHttpUtils = null;
    private static boolean mPrintLog;
    private int mLastError = 200;
    private String mLastErrorDescribe = "no error";
    static final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    public HttpUtil() {
    }

    public void setTimeout(int timeout) {
        this.mTimeOut = timeout;
    }

    public String httpGetRequest(String requestUrl) {
        if(requestUrl != null && requestUrl.length() > 0) {
            BufferedReader in = null;
            CNetSDKLog.d("HttpUtils", "httpGetRequest request:" + requestUrl);
            String page = null;

            try {
                URI url = new URI(requestUrl);
                BasicHttpParams httpParams = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(httpParams, this.mTimeOut);
                HttpConnectionParams.setSoTimeout(httpParams, this.mTimeOut);
                HttpClient client = new DefaultHttpClient(httpParams);
                HttpGet request = new HttpGet();
                request.setURI(url);
                HttpResponse response = client.execute(request);
                in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");

                while((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }

                in.close();
                page = sb.toString();
            } catch (ConnectionClosedException var34) {
                printStackTrace(var34);
                this.mLastError = 126;
                this.mLastErrorDescribe = "Connect failed";
            } catch (ConnectException var35) {
                printStackTrace(var35);
                this.mLastError = 126;
                this.mLastErrorDescribe = "Connect failed";
            } catch (ConnectionPoolTimeoutException var36) {
                printStackTrace(var36);
                this.mLastError = 122;
                this.mLastErrorDescribe = "Connect time out";
            } catch (ConnectTimeoutException var37) {
                printStackTrace(var37);
                this.mLastError = 122;
                this.mLastErrorDescribe = "Connect time out";
            } catch (SocketTimeoutException var38) {
                printStackTrace(var38);
                this.mLastError = 122;
                this.mLastErrorDescribe = "Connect time out";
            } catch (MalformedURLException var39) {
                printStackTrace(var39);
                this.mLastError = 123;
                this.mLastErrorDescribe = "Malformed URL Exception";
            } catch (IOException var40) {
                printStackTrace(var40);
                this.mLastError = 123;
                this.mLastErrorDescribe = "IOException";
            } catch (Exception var41) {
                printStackTrace(var41);
                this.mLastError = 123;
                this.mLastErrorDescribe = "Http Other request exception";
            } finally {
                try {
                    if(in != null) {
                        in.close();
                        in = null;
                    }
                } catch (IOException var33) {
                    printStackTrace(var33);
                }

            }

            CNetSDKLog.d("HttpUtils", "httpGetRequest response:" + page);
            return page;
        } else {
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return null;
        }
    }

    public static void printStackTrace(Exception e) {
        if(mPrintLog && e != null) {
            e.printStackTrace();
        }

    }

    public String httpPostRequest(String requestAddr, String xmlBody) {
        if(requestAddr != null && requestAddr.length() > 0) {
            CNetSDKLog.d("HttpUtils", "httpPostRequest requestAddr:" + requestAddr);
            CNetSDKLog.d("HttpUtils", "httpPostRequest xmlBody:" + xmlBody);
            String response = null;
            HttpURLConnection httpConn = null;
            OutputStream outputStream = null;
            InputStream inputStream = null;
            InputStream in = null;
            DataOutputStream os = null;

            try {
                URL url = new URL(requestAddr);
                httpConn = (HttpURLConnection)url.openConnection();
                httpConn.setConnectTimeout(this.mTimeOut);
                httpConn.setReadTimeout(this.mTimeOut);
                httpConn.setDoInput(true);
                httpConn.setDoOutput(true);
                httpConn.setRequestMethod("POST");
                //httpConn.setRequestProperty("Content-type", "text/xml; charset=utf-8");
                String sessionID = this.getSessionId(requestAddr);
                if(!TextUtils.isEmpty(sessionID)) {
                    String cookie_key = "JSESSIONID=" + sessionID;
                    httpConn.setRequestProperty("Cookie", cookie_key);
                }

                if(xmlBody != null && xmlBody.length() > 0) {
                    outputStream = httpConn.getOutputStream();
                    os = new DataOutputStream(outputStream);
                    os.write(xmlBody.getBytes(), 0, xmlBody.getBytes().length);
                    os.flush();
                    os.close();
                }

                inputStream = httpConn.getInputStream();
                in = new BufferedInputStream(inputStream);
                response = inputStream2String(in);
                CNetSDKLog.d("HttpUtils", "httpPostRequest response:" + response);
            } catch (ConnectionClosedException var34) {
                printStackTrace(var34);
                this.mLastError = 126;
                this.mLastErrorDescribe = "Connect failed";
            } catch (ConnectException var35) {
                printStackTrace(var35);
                this.mLastError = 126;
                this.mLastErrorDescribe = "Connect failed";
            } catch (ConnectionPoolTimeoutException var36) {
                printStackTrace(var36);
                this.mLastError = 122;
                this.mLastErrorDescribe = "Connect time out";
            } catch (ConnectTimeoutException var37) {
                printStackTrace(var37);
                this.mLastError = 122;
                this.mLastErrorDescribe = "Connect time out";
            } catch (SocketTimeoutException var38) {
                printStackTrace(var38);
                this.mLastError = 122;
                this.mLastErrorDescribe = "Connect time out";
            } catch (MalformedURLException var39) {
                printStackTrace(var39);
                this.mLastError = 123;
                this.mLastErrorDescribe = "Malformed URL Exception";
            } catch (IOException var40) {
                printStackTrace(var40);
                this.mLastError = 123;
                this.mLastErrorDescribe = "IOException";
            } catch (Exception var41) {
                printStackTrace(var41);
                this.mLastError = 123;
                this.mLastErrorDescribe = "Http Other request exception";
            } finally {
                try {
                    if(in != null) {
                        in.close();
                        in = null;
                    }

                    if(os != null) {
                        os.close();
                        os = null;
                    }

                    if(outputStream != null) {
                        outputStream.close();
                        outputStream = null;
                    }

                    if(inputStream != null) {
                        inputStream.close();
                        inputStream = null;
                    }
                } catch (IOException var33) {
                    printStackTrace(var33);
                    this.mLastError = 123;
                    this.mLastErrorDescribe = "IOException";
                }

                if(httpConn != null) {
                    httpConn.disconnect();
                    httpConn = null;
                }

            }

            CNetSDKLog.d("HttpUtils", "httpPostRequest response:" + response);
            return response;
        } else {
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return null;
        }
    }

    private String getSessionId(String requestAddr) {
        String sessionId = null;
        if(!TextUtils.isEmpty(requestAddr) && requestAddr.contains("sessionID")) {
            String[] strs = requestAddr.split("sessionID=");
            if(strs.length >= 2) {
                if(strs[1].contains("&")) {
                    strs = strs[1].split("&");
                    sessionId = strs[0];
                } else {
                    sessionId = strs[1];
                }
            }
        }

        CNetSDKLog.d("HttpUtils", "getSessionId sessionId:" + sessionId);
        return sessionId;
    }

    public String httpsGetRequest(String servHttpsAddr) {
        if(servHttpsAddr != null && !servHttpsAddr.equals("")) {
            CNetSDKLog.d("HttpUtils", "httpsGetRequest request:" + servHttpsAddr);
            boolean bRet = this.verifyHttpsUrl(servHttpsAddr);
            if(!bRet) {
                CNetSDKLog.d("HttpUtils", "sslGetRequest verifyHttpsUrl fail");
                return "";
            } else {
                String response = "";

                try {
                    response = this.getSslRequest(servHttpsAddr);
                } catch (HttpUtil.HTTPException var5) {
                    printStackTrace(var5);
                    CNetSDKLog.d("HttpUtils", "sslGetRequest verifyHttpsUrl fail");
                    return "";
                }

                CNetSDKLog.d("HttpUtils", "httpsGetRequest response:" + response);
                return response;
            }
        } else {
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return null;
        }
    }

    public String httpsPostRequest(String servHttpsAddr, String xmlBody) {
        if(servHttpsAddr != null && !servHttpsAddr.equals("")) {
            CNetSDKLog.d("HttpUtils", "httpsPostRequest request:" + servHttpsAddr + "?" + xmlBody);
            boolean bRet = this.verifyHttpsUrl(servHttpsAddr);
            if(!bRet) {
                this.mLastError = 100;
                this.mLastErrorDescribe = "input param error";
                return null;
            } else {
                String response = null;
                response = this.postSslRequest(servHttpsAddr, xmlBody);
                CNetSDKLog.d("HttpUtils", "httpsPostRequest response:" + response);
                return response;
            }
        } else {
            this.mLastError = 100;
            this.mLastErrorDescribe = "input param error";
            return null;
        }
    }

    private static String inputStream2String(InputStream is) {
        BufferedReader in = null;

        try {
            in = new BufferedReader(new InputStreamReader(is, "utf-8"));
        } catch (UnsupportedEncodingException var14) {
            var14.printStackTrace();
            return null;
        }

        StringBuffer buffer = new StringBuffer();
        String line = "";

        try {
            while((line = in.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException var15) {
            printStackTrace(var15);
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException var13) {
                    printStackTrace(var13);
                }

                in = null;
            }

        }

        return buffer.toString();
    }

    private String getSslRequest(String servHttpsAddr) throws HttpUtil.HTTPException {
        String response = null;
        HttpURLConnection conn = null;
        InputStream in = null;
        InputStream inputStream = null;
        InputStream errorStream = null;

        try {
            URL url = new URL(servHttpsAddr);
            trustAllHosts();
            conn = (HttpsURLConnection)url.openConnection();
            ((HttpsURLConnection)conn).setHostnameVerifier(DO_NOT_VERIFY);
            conn.setConnectTimeout(this.mTimeOut);
            conn.setReadTimeout(this.mTimeOut);
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "text/xml; charset=utf-8");
            inputStream = conn.getInputStream();
            errorStream = conn.getErrorStream();
            if(conn.getResponseCode() == 200) {
                in = new BufferedInputStream(inputStream);
            } else {
                in = new BufferedInputStream(errorStream);
            }

            if(inputStream != null) {
                inputStream.close();
                inputStream = null;
            }

            if(errorStream != null) {
                errorStream.close();
                errorStream = null;
            }

            response = inputStream2String(in);
            if(response == null || response.length() <= 0) {
                this.mLastError = 125;
                this.mLastErrorDescribe = "Request return null";
            }

            in.close();
            in = null;
        } catch (ConnectionClosedException var30) {
            printStackTrace(var30);
            this.mLastError = 126;
            this.mLastErrorDescribe = "Connect failed";
        } catch (ConnectException var31) {
            printStackTrace(var31);
            this.mLastError = 126;
            this.mLastErrorDescribe = "Connect failed";
        } catch (ConnectionPoolTimeoutException var32) {
            printStackTrace(var32);
            this.mLastError = 122;
            this.mLastErrorDescribe = "Connect time out";
        } catch (ConnectTimeoutException var33) {
            printStackTrace(var33);
            this.mLastError = 122;
            this.mLastErrorDescribe = "Connect time out";
        } catch (SocketTimeoutException var34) {
            printStackTrace(var34);
            this.mLastError = 122;
            this.mLastErrorDescribe = "Connect time out";
        } catch (MalformedURLException var35) {
            printStackTrace(var35);
            this.mLastError = 123;
            this.mLastErrorDescribe = "Malformed URL Exception";
        } catch (IOException var36) {
            printStackTrace(var36);
            this.mLastError = 123;
            this.mLastErrorDescribe = "IOException";
        } catch (Exception var37) {
            printStackTrace(var37);
            this.mLastError = 123;
            this.mLastErrorDescribe = "Http Other request exception";
        } finally {
            try {
                if(in != null) {
                    in.close();
                    in = null;
                }

                if(inputStream != null) {
                    inputStream.close();
                    inputStream = null;
                }

                if(errorStream != null) {
                    errorStream.close();
                    errorStream = null;
                }
            } catch (IOException var29) {
                printStackTrace(var29);
                this.mLastError = 123;
                this.mLastErrorDescribe = "Http Other request exception";
            }

            if(conn != null) {
                conn.disconnect();
                conn = null;
            }

        }

        return response;
    }

    private boolean verifyHttpsUrl(String httpsAddr) {
        if(httpsAddr != null && !httpsAddr.equals("")) {
            URL httpsUurl;
            try {
                httpsUurl = new URL(httpsAddr);
            } catch (MalformedURLException var4) {
                printStackTrace(var4);
                CNetSDKLog.e("HttpUtils", "verifyHttpsUrl httpsAddr not url, error url:" + httpsAddr);
                return false;
            }

            if(!httpsUurl.getProtocol().toLowerCase(Locale.ENGLISH).equals("https")) {
                CNetSDKLog.e("HttpUtils", "verifyHttpsUrl httpsAddr not https, error url:" + httpsAddr);
                return false;
            } else {
                return true;
            }
        } else {
            CNetSDKLog.e("HttpUtils", "verifyHttpsUrl httpsAddr == null");
            return false;
        }
    }

    private String postSslRequest(String servHttpsAddr, String xmlBody) {
        String response = null;
        InputStream in = null;
        HttpsURLConnection httpsConn = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        InputStream errorStream = null;
        DataOutputStream os = null;

        try {
            URL url = new URL(servHttpsAddr);
            trustAllHosts();
            httpsConn = (HttpsURLConnection)url.openConnection();
            httpsConn.setHostnameVerifier(DO_NOT_VERIFY);
            httpsConn.setConnectTimeout(this.mTimeOut);
            httpsConn.setReadTimeout(this.mTimeOut);
            httpsConn.setDoInput(true);
            httpsConn.setDoOutput(true);
            if(!TextUtils.isEmpty(xmlBody)) {
                outputStream = httpsConn.getOutputStream();
                os = new DataOutputStream(outputStream);
                os.write(xmlBody.getBytes("utf-8"), 0, xmlBody.getBytes("utf-8").length);
                os.flush();
                if(outputStream != null) {
                    outputStream.close();
                    outputStream = null;
                }

                os.close();
                os = null;
            }

            inputStream = httpsConn.getInputStream();
            errorStream = httpsConn.getErrorStream();
            if(httpsConn.getResponseCode() == 200) {
                in = new BufferedInputStream(inputStream);
            } else {
                in = new BufferedInputStream(errorStream);
            }

            response = inputStream2String(in);
            if(response == null || response.length() <= 0) {
                this.mLastError = 125;
                this.mLastErrorDescribe = "Request return null";
            }

            if(inputStream != null) {
                inputStream.close();
                inputStream = null;
            }

            if(errorStream != null) {
                errorStream.close();
                errorStream = null;
            }

            in.close();
            in = null;
            if(httpsConn != null) {
                httpsConn.disconnect();
                httpsConn = null;
            }
        } catch (ConnectionClosedException var33) {
            printStackTrace(var33);
            this.mLastError = 126;
            this.mLastErrorDescribe = "Connect failed";
        } catch (ConnectException var34) {
            printStackTrace(var34);
            this.mLastError = 126;
            this.mLastErrorDescribe = "Connect failed";
        } catch (ConnectionPoolTimeoutException var35) {
            printStackTrace(var35);
            this.mLastError = 122;
            this.mLastErrorDescribe = "Connect time out";
        } catch (ConnectTimeoutException var36) {
            printStackTrace(var36);
            this.mLastError = 122;
            this.mLastErrorDescribe = "Connect time out";
        } catch (SocketTimeoutException var37) {
            printStackTrace(var37);
            this.mLastError = 122;
            this.mLastErrorDescribe = "Connect time out";
        } catch (MalformedURLException var38) {
            printStackTrace(var38);
            this.mLastError = 123;
            this.mLastErrorDescribe = "Malformed URL Exception";
        } catch (IOException var39) {
            printStackTrace(var39);
            this.mLastError = 123;
            this.mLastErrorDescribe = "IOException";
        } catch (Exception var40) {
            printStackTrace(var40);
            this.mLastError = 123;
            this.mLastErrorDescribe = "Http Other request exception";
        } finally {
            try {
                if(in != null) {
                    in.close();
                    in = null;
                }

                if(os != null) {
                    os.close();
                    os = null;
                }

                if(outputStream != null) {
                    outputStream.close();
                    outputStream = null;
                }

                if(inputStream != null) {
                    inputStream.close();
                    inputStream = null;
                }

                if(errorStream != null) {
                    errorStream.close();
                    errorStream = null;
                }
            } catch (IOException var32) {
                printStackTrace(var32);
                this.mLastError = 123;
                this.mLastErrorDescribe = "IOException";
            }

            if(httpsConn != null) {
                httpsConn.disconnect();
                httpsConn = null;
            }

        }

        return response;
    }

    private static void trustAllHosts() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
        }};

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init((KeyManager[])null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception var2) {
            printStackTrace(var2);
        }

    }

    public static String convertToHttp(String address) {
        if(address != null && !address.equalsIgnoreCase("")) {
            String tempString = address.replaceAll(" ", "");
            boolean hasHttpString = tempString.length() >= 7 && tempString.subSequence(0, 7).equals("http://");
            if(!hasHttpString) {
                tempString = ("http://" + tempString.trim()).trim();
            }

            return tempString;
        } else {
            return "";
        }
    }

    public static String convertToHttps(String address) {
        if(address != null && !address.equalsIgnoreCase("")) {
            String tempString = address.replaceAll(" ", "");
            boolean hasHttpString = tempString.length() >= 7 && tempString.subSequence(0, 7).equals("https://");
            if(!hasHttpString) {
                tempString = ("https://" + tempString.trim()).trim();
            }

            return tempString;
        } else {
            return "";
        }
    }

    public int getLastErrorCode() {
        return this.mLastError;
    }

    public String getLastErrorDesc() {
        return this.mLastErrorDescribe;
    }

    public boolean probeMagToHttp(String requestUrl) {
        CNetSDKLog.d("HttpUtils", "probeMagToHttp requestUrl:" + requestUrl);
        Object page = null;

        try {
            URI url = new URI(requestUrl);
            BasicHttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, this.mTimeOut);
            HttpConnectionParams.setSoTimeout(httpParams, this.mTimeOut);
            HttpClient client = new DefaultHttpClient(httpParams);
            HttpGet request = new HttpGet();
            request.setURI(url);
            HttpResponse response = client.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            CNetSDKLog.d("HttpUtils", "probeMagToHttp() statusCode is:" + statusCode);
            if(statusCode != 404 && statusCode != 200) {
                return true;
            }

            return false;
        } catch (URISyntaxException var9) {
            printStackTrace(var9);
        } catch (ClientProtocolException var10) {
            printStackTrace(var10);
        } catch (IOException var11) {
            printStackTrace(var11);
        }

        CNetSDKLog.d("HttpUtils", "httpGetRequest response:" + page);
        return true;
    }

    public static void setLogOption(boolean isPrintLog) {
        mPrintLog = isPrintLog;
    }

    public static class HTTPException extends Exception {
        private static final long serialVersionUID = 1L;
        public static final int NO_ERROR = 0;
        private int mErrorCode = 0;
        public static final int HTTP_NETWORD_EXCEPTION = 123;

        public int getErrorCode() {
            return this.mErrorCode;
        }

        public HTTPException(String desc, int errorCode) {
            super(desc);
            this.mErrorCode = errorCode;
        }
    }

    public static class _FakeX509TrustManager implements X509TrustManager {
        private static TrustManager[] trustManagers;
        private static final X509Certificate[] _AcceptedIssuers = new X509Certificate[0];

        public _FakeX509TrustManager() {
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public boolean isClientTrusted(X509Certificate[] chain) {
            return true;
        }

        public boolean isServerTrusted(X509Certificate[] chain) {
            return true;
        }

        public X509Certificate[] getAcceptedIssuers() {
            return _AcceptedIssuers;
        }

        public static void allowAllSSL() {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            SSLContext context = null;
            if(trustManagers == null) {
                trustManagers = new TrustManager[]{new HttpUtil._FakeX509TrustManager()};
            }

            try {
                context = SSLContext.getInstance("TLS");
                context.init((KeyManager[])null, trustManagers, new SecureRandom());
            } catch (NoSuchAlgorithmException var2) {
                HttpUtil.printStackTrace(var2);
            } catch (KeyManagementException var3) {
                HttpUtil.printStackTrace(var3);
            }

            if(context != null) {
                SSLSocketFactory defaultSSLSocketFactory = context.getSocketFactory();
                if(defaultSSLSocketFactory != null) {
                    HttpsURLConnection.setDefaultSSLSocketFactory(defaultSSLSocketFactory);
                }

            }
        }
    }
}
