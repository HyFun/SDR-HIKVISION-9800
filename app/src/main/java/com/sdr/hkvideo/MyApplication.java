package com.sdr.hkvideo;

import android.app.Application;

import com.sdr.hklibrary.SDR_HIKVISION_9800_HTTP;
import com.sdr.lib.SDR_LIBRARY;

/**
 * Created by HyFun on 2018/11/13.
 * Email: 775183940@qq.com
 * Description:
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDR_LIBRARY.register(this, new BaseActivityConfig(getApplicationContext()));
        SDR_HIKVISION_9800_HTTP.getInstance().init(this, BuildConfig.DEBUG);
        SDR_HIKVISION_9800_HTTP.getInstance().setControl(false);
    }
}
