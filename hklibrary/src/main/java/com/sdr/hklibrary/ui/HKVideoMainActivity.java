package com.sdr.hklibrary.ui;

import android.os.Bundle;

import com.orhanobut.logger.Logger;
import com.sdr.hklibrary.HKVideoLibrary;
import com.sdr.hklibrary.R;
import com.sdr.hklibrary.base.HKBaseActivity;
import com.sdr.hklibrary.constant.HKConstants;
import com.sdr.hklibrary.contract.HKMainContract;
import com.sdr.hklibrary.data.HKDataInfo;
import com.sdr.hklibrary.presenter.HKMainPresenter;
import com.sdr.lib.http.HttpClient;
import com.sdr.lib.ui.tree.TreeNode;

import java.util.List;

public class HKVideoMainActivity extends HKBaseActivity<HKMainPresenter> implements HKMainContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hkvideo_main);
        initToolbar();
        initData();
    }

    private void initToolbar() {
        setTitle("实时监控");
        setDisplayHomeAsUpEnabled();
    }

    private void initData() {
        // 登录
        presenter = new HKMainPresenter(this);
        String url = HKDataInfo.getInstance().getUrl();
        showLoadingView();
        presenter.init(url, HKDataInfo.getInstance().getUserName(), HKDataInfo.getInstance().getPassWord(), HKVideoLibrary.getInstance().getMacAddr(), HKDataInfo.getInstance().getServInfo());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HKDataInfo.destory();
    }

    // ————————————————————VIEW————————————————————————

    @Override
    public void initSuccess(List<TreeNode> treeNodeList) {
        showContentView();
        Logger.t(HKConstants.HK_TAG).json(HttpClient.gson.toJson(treeNodeList));
    }

    @Override
    public void initFailed(String message) {
        showErrorMsg(message);
        finish();
    }
}
