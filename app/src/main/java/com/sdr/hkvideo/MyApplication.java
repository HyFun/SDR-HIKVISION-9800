package com.sdr.hkvideo;

import android.app.Application;

import com.sdr.hklibrary.HKVideoLibrary;
import com.sdr.lib.SDRLibrary;

/**
 * Created by HyFun on 2018/11/13.
 * Email: 775183940@qq.com
 * Description:
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDRLibrary.getInstance().init(this, BuildConfig.DEBUG);
        HKVideoLibrary.getInstance().init(this, BuildConfig.DEBUG, BaseActivity.getHeaderBarDrawable(getApplicationContext()), R.layout.layout_public_toolbar_white);
    }
}
