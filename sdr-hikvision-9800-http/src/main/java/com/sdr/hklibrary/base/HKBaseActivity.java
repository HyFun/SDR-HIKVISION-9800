package com.sdr.hklibrary.base;

import com.sdr.lib.mvp.AbstractPresenter;
import com.sdr.lib.mvp.AbstractView;
import com.sdr.lib.ui.dialog.SDRLoadingDialog;
import com.sdr.lib.util.AlertUtil;

/**
 * Created by HyFun on 2018/11/13.
 * Email: 775183940@qq.com
 * Description:
 */

public class HKBaseActivity<T extends AbstractPresenter> extends com.sdr.lib.base.BaseActivity implements AbstractView {
    protected T presenter;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }


    // ——————————————————————————————————————————————
    private SDRLoadingDialog sdrLoadingDialog;

    @Override
    public void showLoadingDialog(String s) {
        if (sdrLoadingDialog == null) {
            sdrLoadingDialog = new SDRLoadingDialog.Builder(getContext())
                    .blur(true)
                    .cancel(false)
                    .build();
        }
        sdrLoadingDialog.setContent(s);
    }

    @Override
    public void hideLoadingDialog() {
        if (sdrLoadingDialog != null) {
            sdrLoadingDialog.dismiss();
        }
    }

    @Override
    public void showSuccessMsg(String msg, String content) {
        AlertUtil.showPositiveToastTop(msg, content);
    }

    @Override
    public void showErrorMsg(String msg, String content) {
        AlertUtil.showNegativeToastTop(msg, content);
    }

    @Override
    public void showNormalMsg(String msg, String content) {
        AlertUtil.showNormalToastTop(msg, content);
    }
}
