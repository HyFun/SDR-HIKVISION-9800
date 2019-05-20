package com.sdr.hkvideo;

import android.app.Application;

import com.sdr.hklibrary.HKVideoLibrary;
import com.sdr.lib.SDR;

/**
 * Created by HyFun on 2018/11/13.
 * Email: 775183940@qq.com
 * Description:
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDR.register(this, new BaseActivityConfig(getApplicationContext()));
        HKVideoLibrary.getInstance().init(this, BuildConfig.DEBUG);
    }
}
