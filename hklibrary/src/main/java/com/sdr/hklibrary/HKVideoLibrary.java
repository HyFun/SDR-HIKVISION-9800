package com.sdr.hklibrary;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiManager;

import com.hik.mcrsdk.MCRSDK;
import com.hik.mcrsdk.rtsp.RtspClient;
import com.hikvision.vmsnetsdk.ServInfo;
import com.hikvision.vmsnetsdk.VMSNetSDK;
import com.orhanobut.logger.Logger;
import com.sdr.hklibrary.data.HKDataInfo;
import com.sdr.hklibrary.ui.HKVideoMainActivity;
import com.sdr.lib.rx.RxUtils;
import com.sdr.lib.util.ToastTopUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by HyFun on 2018/11/13.
 * Email: 775183940@qq.com
 * Description: 初始化HKLibrary，开启HKMainActivity
 */

public class HKVideoLibrary {
    private static HKVideoLibrary hkVideoLibrary;

    /**
     * 获取HKVideoLibrary 的实例
     *
     * @return
     */
    public static final HKVideoLibrary getInstance() {
        if (hkVideoLibrary == null) {
            synchronized (HKVideoLibrary.class) {
                if (hkVideoLibrary == null) {
                    hkVideoLibrary = new HKVideoLibrary();
                }
            }
        }
        return hkVideoLibrary;
    }

    private Application application;
    private boolean debug;
    private Drawable drawable;
    private int toolbarRes;
    private boolean loadJNI; // 是否已经加载过库文件

    public void init(Application application, final boolean debug, Drawable drawable, int toolbarRes) {
        this.application = application;
        this.debug = debug;
        this.drawable = drawable;
        this.toolbarRes = toolbarRes;
    }

    public Application getApplication() {
        return application;
    }

    public boolean isDebug() {
        return debug;
    }

    public Drawable getDrawable() {
        return drawable.getConstantState().newDrawable();
    }

    public int getToolbarRes() {
        return toolbarRes;
    }

    /**
     * 获取登录设备mac地址
     *
     * @return
     */
    public String getMacAddr() {
        WifiManager wm = (WifiManager) application.getSystemService(Context.WIFI_SERVICE);
        String mac = wm.getConnectionInfo().getMacAddress();
        return mac == null ? "" : mac;
    }

    public void start(Context context, String url, String userName, String passWord) {
        // 先加载
        if (loadJNI) {
            startMain(context, url, userName, passWord);
        } else {
            // 动态加载
            Observable.just(0)
                    .flatMap(new Function<Integer, ObservableSource<Boolean>>() {
                        @Override
                        public ObservableSource<Boolean> apply(Integer integer) throws Exception {
                            try {
                                System.loadLibrary("gnustl_shared");
                                MCRSDK.init();
                                RtspClient.initLib();
                                MCRSDK.setPrint(1, null);
                                VMSNetSDK.getInstance().openLog(debug);
                                return obser -> {
                                    obser.onNext(true);
                                    obser.onComplete();
                                };
                            } catch (Exception e) {
                                return Observable.error(e);
                            }
                        }
                    })
                    .compose(RxUtils.io_main())
                    .subscribe(result -> {
                        startMain(context, url, userName, passWord);
                        loadJNI = true;
                    }, error -> {
                        Logger.e(error.getMessage(), error);
                        ToastTopUtil.showErrorTopToast("海康视频库文件加载失败");
                    });
        }
    }


    // —————————————————私有方法—————————————————

    /**
     * 开启启动 HKMainActivity
     *
     * @param context
     * @param url
     * @param userName
     * @param passWord
     */
    private void startMain(Context context, String url, String userName, String passWord) {
        HKDataInfo.getInstance().setUrl(url);
        HKDataInfo.getInstance().setUserName(userName);
        HKDataInfo.getInstance().setPassWord(passWord);
        HKDataInfo.getInstance().setServInfo(new ServInfo());
        context.startActivity(new Intent(context, HKVideoMainActivity.class));
    }

}
