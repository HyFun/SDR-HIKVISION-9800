package com.sdr.hklibrary.contract;

import com.hikvision.vmsnetsdk.ServInfo;
import com.sdr.lib.mvp.AbstractPresenter;
import com.sdr.lib.mvp.AbstractView;
import com.sdr.lib.ui.tree.TreeNode;

import java.util.List;

/**
 * Created by HyFun on 2018/11/13.
 * Email: 775183940@qq.com
 * Description:
 */

public interface HKMainContract {
    interface View extends AbstractView {
        void initSuccess(List<TreeNode> treeNodeList);

        void initFailed(String message);
    }

    interface Presenter extends AbstractPresenter<View> {
        void init(String url, String userName, String passWord, String macAddr, ServInfo servInfo);
    }
}
