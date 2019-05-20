package com.sdr.hkvideo;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by HyFun on 2019/05/20.
 * Email: 775183940@qq.com
 * Description:
 */

public class BaseActivityConfig extends com.sdr.lib.base.BaseActivityConfig {
    private Context context;

    public BaseActivityConfig(Context context) {
        this.context = context;
    }

    @Override
    public int onHeaderBarToolbarRes() {
        return R.layout.layout_public_toolbar_white;
    }

    @Override
    public Drawable onHeaderBarDrawable() {
        ColorDrawable drawable = new ColorDrawable(context.getResources().getColor(R.color.colorPrimary));
        return drawable;
    }
}
