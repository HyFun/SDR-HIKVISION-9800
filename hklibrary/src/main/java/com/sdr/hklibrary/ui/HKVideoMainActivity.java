package com.sdr.hklibrary.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sdr.hklibrary.HKVideoLibrary;
import com.sdr.hklibrary.R;
import com.sdr.hklibrary.base.HKBaseActivity;
import com.sdr.hklibrary.contract.HKMainContract;
import com.sdr.hklibrary.data.HKDataInfo;
import com.sdr.hklibrary.data.HKHistory;
import com.sdr.hklibrary.data.HKItemControl;
import com.sdr.hklibrary.presenter.HKMainPresenter;
import com.sdr.lib.ui.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class HKVideoMainActivity extends HKBaseActivity<HKMainPresenter> implements HKMainContract.View {

    RecyclerView rvHkMain;
    RadioGroup rgSwitchView;
    ImageView ivHistory;
    ImageView ivZoomOut;

    private HKMainRecyclerAdapter mainRecyclerAdapter;
    // 默认显示的数量  2  x  2
    private int currentViewNum = 2;
    private List<TreeNode> treeNodeList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hkvideo_main);
        initToolbar();
        initView();
        initData();
        initListener();
    }

    private void initToolbar() {
        setTitle("实时监控");
        setDisplayHomeAsUpEnabled();
    }

    private void initView() {
        rvHkMain = findViewById(R.id.hk_video_main_rv);
        rgSwitchView = findViewById(R.id.hk_video_main_rg_switch);
        ivHistory = findViewById(R.id.hk_video_main_iv_history);
        ivZoomOut = findViewById(R.id.hk_video_main_iv_zoom_out);
    }

    private void initData() {
        // 登录
        presenter = new HKMainPresenter(this);
        String url = HKDataInfo.getInstance().getUrl();
        showLoadingView();
        presenter.init(url, HKDataInfo.getInstance().getUserName(), HKDataInfo.getInstance().getPassWord(), HKVideoLibrary.getInstance().getMacAddr(), HKDataInfo.getInstance().getServInfo());

        changeRecycler(currentViewNum, null);
    }

    private void initListener() {
        for (int i = 0; i < rgSwitchView.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) rgSwitchView.getChildAt(i);
            final int switchCount = i + 1;
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentViewNum == switchCount) return;
                    changeRecycler(switchCount, null);
                }
            });
        }
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
//        new HKVideoPlayListDialog(getContext(), treeNodeList, new TreeNodeRecyclerAdapter.OnTreeNodeSigleClickListener() {
//            @Override
//            public void onSigleClick(TreeNode treeNode, int i, int i1, boolean b) {
//                ToastUtil.showCorrectMsg(treeNode.getLabel());
//            }
//        }).show();
        this.treeNodeList.clear();
        this.treeNodeList.addAll(treeNodeList);
    }

    @Override
    public void initFailed(String message) {
        showErrorMsg(message);
        finish();
    }


    // ———————————————————— PRIVE ————————————————————————

    /**
     * 切换recycler view的视图
     *
     * @param num
     * @param cameraInfoList
     */
    private void changeRecycler(int num, List<HKHistory.CameraInfo> cameraInfoList) {
        if (mainRecyclerAdapter == null || cameraInfoList != null) {
            List<HKItemControl> itemList = new ArrayList<>();
            for (int i = 0; i < num * num; i++) {
                itemList.add(new HKItemControl(i, mainRecyclerAdapter));
            }
            mainRecyclerAdapter = new HKMainRecyclerAdapter(R.layout.hk_layout_hkvideo_main_recycler_item, itemList, this, treeNodeList);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), num);
            rvHkMain.setLayoutManager(gridLayoutManager);
            rvHkMain.setAdapter(mainRecyclerAdapter);
        } else {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) rvHkMain.getLayoutManager();
            gridLayoutManager.setSpanCount(num);
            if (num >= currentViewNum) {
                // 少 变  多
                int addCount = (num * num) - (currentViewNum * currentViewNum);
                for (int i = 0; i < addCount; i++) {
                    mainRecyclerAdapter.addData(new HKItemControl(i + (currentViewNum * currentViewNum), mainRecyclerAdapter));
                }
            } else {
                // 多 变 少
                for (int i = (num * num); i < mainRecyclerAdapter.getData().size(); i++) {
                    mainRecyclerAdapter.remove(i);
                }
            }
        }
        currentViewNum = num;
    }
}
