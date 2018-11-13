package com.sdr.hklibrary.ui;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sdr.hklibrary.data.HKItemControl;

import java.util.List;

/**
 * Created by HyFun on 2018/11/13.
 * Email: 775183940@qq.com
 * Description:
 */

public class HKMainRecyclerAdapter extends BaseQuickAdapter<HKItemControl,BaseViewHolder>{
    public HKMainRecyclerAdapter(int layoutResId, @Nullable List<HKItemControl> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HKItemControl item) {

    }
}
