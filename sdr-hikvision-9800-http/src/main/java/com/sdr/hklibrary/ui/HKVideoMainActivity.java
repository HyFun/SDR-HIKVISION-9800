package com.sdr.hklibrary.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.sdr.hklibrary.R;
import com.sdr.hklibrary.SDR_HIKVISION_9800_HTTP;
import com.sdr.hklibrary.base.HKBaseActivity;
import com.sdr.hklibrary.constant.HKConstants;
import com.sdr.hklibrary.contract.HKMainContract;
import com.sdr.hklibrary.data.HKDataInfo;
import com.sdr.hklibrary.data.HKHistory;
import com.sdr.hklibrary.data.HKItemControl;
import com.sdr.hklibrary.data.HKVideoListFilter;
import com.sdr.hklibrary.presenter.HKMainPresenter;
import com.sdr.hklibrary.support.HKVideoUtil;
import com.sdr.lib.ui.tree.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.functions.Consumer;

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
        presenter.init(url, HKDataInfo.getInstance().getUserName(), HKDataInfo.getInstance().getPassWord(), SDR_HIKVISION_9800_HTTP.getInstance().getMacAddr(), HKDataInfo.getInstance().getServInfo());

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


        // 点击历史的时候
        ivHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取历史记录
                final HKHistory hkHistory = (HKHistory) HKVideoUtil.getHkACache().getAsObject(HKConstants.HIK_HISTORY);
                if (hkHistory == null || hkHistory.getCameraInfoList().isEmpty()) {
                    showErrorMsg("没有浏览历史记录");
                    return;
                }
                // 有历史记录  开启预览
                // 关闭正在播放的视频  然后开启历史记录
                HKVideoUtil.closeAllPlayingVideo(mainRecyclerAdapter.getData())
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                changeRecycler(hkHistory.getViewNum(), hkHistory.getCameraInfoList());
                            }
                        });
            }
        });

        // 点击放大的时候
        ivZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainRecyclerAdapter.getSelectedPosition() != -1 && mainRecyclerAdapter.getData().get(mainRecyclerAdapter.getSelectedPosition()).getCurrentStatus() !=
                        HKConstants.PlayStatus.LIVE_INIT) {
                    // 正在播放的时候才方法
                    HKVideoControlActivity.startHKVideoControlActivity(getActivity(), mainRecyclerAdapter.getData().get(mainRecyclerAdapter.getSelectedPosition()).getCameraID());
                } else {
                    showErrorMsg("请选择一个正在播放的窗口");
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mainRecyclerAdapter == null) return;
        // 获取到所有正在播放视频的cameraid
        List<HKItemControl> hkItemControls = mainRecyclerAdapter.getData();
        final List<HKHistory.CameraInfo> cameraInfoList = new ArrayList<>();
        for (HKItemControl item : hkItemControls) {
            if (item.getCurrentStatus() != HKConstants.PlayStatus.LIVE_INIT)
                cameraInfoList.add(new HKHistory.CameraInfo(item.getPosition(), item.getCameraID()));
        }
        // 关闭所有的视频  然后重新开启预览
        HKVideoUtil.closeAllPlayingVideo(mainRecyclerAdapter.getData()).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                changeRecycler(currentViewNum, cameraInfoList);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HKDataInfo.destory();
    }

    @Override
    protected void setNavigationOnClickListener() {
        new MaterialDialog.Builder(getContext())
                .title("提示")
                .content("确定退出？")
                .positiveText("退出")
                .negativeText("取消")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        // 1.先保存最后的信息
                        if (mainRecyclerAdapter != null) {
                            List<HKHistory.CameraInfo> cameraInfoList = new ArrayList<>();
                            List<HKItemControl> hkItemControls = mainRecyclerAdapter.getData();
                            for (HKItemControl item : hkItemControls) {
                                if (item.getCurrentStatus() != HKConstants.PlayStatus.LIVE_INIT && item.getCameraID() != null) {
                                    cameraInfoList.add(new HKHistory.CameraInfo(item.getPosition(), item.getCameraID()));
                                }
                            }
                            HKHistory hkHistory = new HKHistory(currentViewNum, cameraInfoList);
                            HKVideoUtil.getHkACache().put(HKConstants.HIK_HISTORY, hkHistory);
                        }
                        // 2.关闭正在播放的视频  关闭完成之后  结束anctivity
                        showLoadingDialog("正在关闭视频");
                        HKVideoUtil.closeAllPlayingVideo(mainRecyclerAdapter.getData()).subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                hideLoadingDialog();
                                finish();
                            }
                        });
                    }
                })
                .show();
    }

    @Override
    public void onBackPressed() {
        setNavigationOnClickListener();
    }

    // ————————————————————VIEW————————————————————————

    @Override
    public void initSuccess(List<TreeNode> treeNodeList) {
        showContentView();
        this.treeNodeList.clear();
        // 过滤摄像头列表
        HKVideoListFilter hkVideoListFilter = SDR_HIKVISION_9800_HTTP.getInstance().getHkVideoListFilter();
        if (hkVideoListFilter != null && hkVideoListFilter.filterCameraList(treeNodeList) != null) {
            this.treeNodeList.addAll(hkVideoListFilter.filterCameraList(treeNodeList));
        } else {
            this.treeNodeList.addAll(treeNodeList);
        }
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
    private void changeRecycler(final int num, List<HKHistory.CameraInfo> cameraInfoList) {
        if (mainRecyclerAdapter == null || cameraInfoList != null) {
            List<HKItemControl> itemList = new ArrayList<>();
            mainRecyclerAdapter = new HKMainRecyclerAdapter(R.layout.hk_layout_hkvideo_main_recycler_item, itemList, this, treeNodeList);
            mainRecyclerAdapter.setmCameraInfos(cameraInfoList);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), num);
            rvHkMain.setLayoutManager(gridLayoutManager);
            mainRecyclerAdapter.bindToRecyclerView(rvHkMain);
            rvHkMain.setAdapter(mainRecyclerAdapter);
            for (int i = 0; i < num * num; i++) {
                itemList.add(new HKItemControl(i, mainRecyclerAdapter));
            }
            mainRecyclerAdapter.notifyDataSetChanged();
        } else {
            mainRecyclerAdapter.setmCameraInfos(cameraInfoList);
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
                // 找出后边所有正在播放的窗口
                List<HKItemControl> hkItemControlList = new ArrayList<>();
                for (int i = (num * num); i < mainRecyclerAdapter.getData().size(); i++) {
                    HKItemControl hkItemControl = mainRecyclerAdapter.getData().get(i);
                    hkItemControlList.add(hkItemControl);
                }
                // 关闭播放
                HKVideoUtil.closeAllPlayingVideo(hkItemControlList)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                List<HKItemControl> hkItemControls = mainRecyclerAdapter.getData();
                                Iterator<HKItemControl> iterator = hkItemControls.iterator();
                                while (iterator.hasNext()) {
                                    HKItemControl item = iterator.next();
                                    if (item.getPosition() >= (num * num)) {
                                        iterator.remove();
                                        mainRecyclerAdapter.notifyItemRemoved(item.getPosition());
                                    }
                                }
                            }
                        });
            }
        }
        currentViewNum = num;
    }
}
