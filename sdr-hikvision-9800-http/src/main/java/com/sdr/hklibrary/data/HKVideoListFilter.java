package com.sdr.hklibrary.data;

import com.sdr.lib.ui.tree.TreeNode;

import java.util.List;

/**
 * Created by HyFun on 2019/03/27.
 * Email: 775183940@qq.com
 * Description:
 */

public interface HKVideoListFilter {
    List<TreeNode> filterCameraList(List<TreeNode> treeNodeList);
}
