package com.sdr.hklibrary.support;

import com.sdr.hklibrary.HKVideoLibrary;
import com.sdr.hklibrary.constant.HKConstants;
import com.sdr.hklibrary.data.HKItemControl;
import com.sdr.lib.rx.RxUtils;
import com.sdr.lib.support.ACache;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by HyFun on 2018/11/14.
 * Email: 775183940@qq.com
 * Description:
 */

public class HKVideoUtil {
    private static ACache hkACache;

    public static final ACache getHkACache() {
        if (hkACache == null) {
            synchronized (HKVideoUtil.class) {
                if (hkACache == null) {
                    hkACache = ACache.get(HKVideoLibrary.getInstance().getApplication().getExternalCacheDir());
                }
            }
        }
        return hkACache;
    }

    /**
     * 关闭所有的视频
     *
     * @param list
     * @return
     */
    public static Observable<Boolean> closeAllPlayingVideo(List<HKItemControl> list) {
        List<HKItemControl> hkItemControlList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            HKItemControl hkItemControl = list.get(i);
            if (hkItemControl.getCurrentStatus() != HKConstants.PlayStatus.LIVE_INIT) {
                // 说明正在播放
                hkItemControlList.add(hkItemControl);
            }
        }
        return Observable.just(0)
                .flatMap(new Function<Integer, ObservableSource<Boolean>>() {
                    @Override
                    public ObservableSource<Boolean> apply(Integer integer) throws Exception {
                        for (HKItemControl item : hkItemControlList) {
                            item.stopPlaySyn();
                        }
                        return observer -> {
                            observer.onNext(true);
                            observer.onComplete();
                        };
                    }
                })
                .compose(RxUtils.io_main());
    }
}
