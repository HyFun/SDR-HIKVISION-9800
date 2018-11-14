package com.sdr.hklibrary.contract;

import android.view.SurfaceView;

import io.reactivex.Observable;
import io.reactivex.observers.ResourceObserver;

/**
 * Created by HyFun on 2018/11/13.
 * Email: 775183940@qq.com
 * Description:
 */

public interface HKPlayContract {
    interface View {
        void playLiveFailed(int position,String message);

        void stopPlayComplete(int position,String message);

        void showLoadingDialog(String message);

        void hideLoadingDialog();

//        void showErrorMessage(String message);
    }

    interface Presenter {
        void startPlay(String cameraID, SurfaceView surfaceView);

        void stopPlay();

        void stopPlaySyn();
    }
}
