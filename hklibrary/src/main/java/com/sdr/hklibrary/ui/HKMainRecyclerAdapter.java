package com.sdr.hklibrary.ui;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hikvision.vmsnetsdk.CameraInfo;
import com.sdr.hklibrary.R;
import com.sdr.hklibrary.constant.HKConstants;
import com.sdr.hklibrary.contract.HKPlayContract;
import com.sdr.hklibrary.data.HKHistory;
import com.sdr.hklibrary.data.HKItemControl;
import com.sdr.hklibrary.support.HKVideoPlayListDialog;
import com.sdr.lib.mvp.AbstractView;
import com.sdr.lib.ui.tree.TreeNode;

import java.util.List;

/**
 * Created by HyFun on 2018/11/13.
 * Email: 775183940@qq.com
 * Description:
 */

public class HKMainRecyclerAdapter extends BaseQuickAdapter<HKItemControl, BaseViewHolder> implements HKPlayContract.View {

    private AbstractView view;
    private List<TreeNode> treeNodeList;
    private List<HKHistory.CameraInfo> mCameraInfos;

    private int lastClickPosition = -1;

    public HKMainRecyclerAdapter(int layoutResId, @Nullable List<HKItemControl> data, AbstractView view, List<TreeNode> treeNodeList) {
        super(layoutResId, data);
        this.view = view;
        this.treeNodeList = treeNodeList;
    }

    public void setmCameraInfos(List<HKHistory.CameraInfo> mCameraInfos) {
        this.mCameraInfos = mCameraInfos;
    }

    @Override
    protected void convert(BaseViewHolder helper, HKItemControl item) {
        final int position = helper.getLayoutPosition();

        final FrameLayout frameLayout = helper.getView(R.id.hk_video_main_item_sfl_container);
        final SurfaceView surfaceView = helper.getView(R.id.hk_video_main_item_sv);
        final ImageView imageView = helper.getView(R.id.hk_video_main_item_iv_add);
        surfaceView.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);

        // 开始播放历史播放
        // 判断cameraid是否为空
        if (mCameraInfos != null && !mCameraInfos.isEmpty()) {
            for (HKHistory.CameraInfo cameraInfo : mCameraInfos) {
                if (position == cameraInfo.getPosition()) {
                    surfaceView.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.GONE);
                    item.startPlay(cameraInfo.getCameraID(), surfaceView);
                }
            }
        }

        // 点击事件
        {
            surfaceView.setOnClickListener(v -> {
                if (lastClickPosition != -1) {
                    // 设置之前的view为透明
                    FrameLayout lastFrameLayout = (FrameLayout) getViewByPosition(lastClickPosition, R.id.hk_video_main_item_sfl_container);
                    lastFrameLayout.setBackgroundColor(Color.TRANSPARENT);
                }
                // 设置当前的view为
                frameLayout.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
                lastClickPosition = position;
            });

            surfaceView.setOnLongClickListener(v -> {
                new MaterialDialog.Builder(mContext)
                        .title("提示")
                        .content("是否关闭播放")
                        .positiveText("关闭")
                        .negativeText("取消")
                        .onPositive((dialog, which) -> item.stopPlay())
                        .show();
                return true;
            });

            imageView.setOnClickListener(v -> {
                // 显示选择的dialog
                new HKVideoPlayListDialog(mContext, treeNodeList, (treeNode, visablePositon, realDatasPositon, isLeaf) -> {
                    // 开始加载播放
                    CameraInfo cameraInfo = null;
                    if (treeNode.getObject() instanceof CameraInfo) {
                        cameraInfo = (CameraInfo) treeNode.getObject();
                    }
                    if (cameraInfo == null) return;
                    surfaceView.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.GONE);
                    item.startPlay(cameraInfo.getId(), surfaceView);
                }).show();
            });
        }
    }

    @Override
    public void showLoadingDialog(String message) {
        view.showLoadingDialog(message);
    }

    @Override
    public void hideLoadingDialog() {
        view.hideLoadingDialog();
    }

    @Override
    public void onPlayMsg(int position, int code, String msg) {
        SurfaceView surfaceView = (SurfaceView) getViewByPosition(position, R.id.hk_video_main_item_sv);
        ImageView imageView = (ImageView) getViewByPosition(position, R.id.hk_video_main_item_iv_add);
        if (surfaceView == null || imageView == null) return;
        if (code == HKConstants.PlayLive.PLAY_LIVE_RTSP_SUCCESS) {
            // 取流成功
            surfaceView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
        } else if (code == HKConstants.PlayLive.PLAY_LIVE_STOP_SUCCESS) {
            // 停止成功
            surfaceView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
        } else if (code == HKConstants.PlayLive.PLAY_LIVE_FAILED || code == HKConstants.PlayLive.PLAY_LIVE_RTSP_FAIL) {
            surfaceView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            // 播放失败
            view.showErrorMsg("第" + (position + 1) + "个位置" + msg);
        }
    }


    public int getSelectedPosition(){
        return lastClickPosition;
    }
}
