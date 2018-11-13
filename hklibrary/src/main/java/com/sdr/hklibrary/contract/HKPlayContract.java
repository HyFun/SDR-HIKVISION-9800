package com.sdr.hklibrary.contract;

import android.view.SurfaceView;

/**
 * Created by HyFun on 2018/11/13.
 * Email: 775183940@qq.com
 * Description:
 */

public interface HKPlayContract {
    interface View {
        void showLoadingDialog(String message);

        void hideLoadingDialog();

        void showErrorMessage(String message);
    }

    interface Presenter {
        void startPlay(String cameraID, SurfaceView surfaceView);
    }
}
