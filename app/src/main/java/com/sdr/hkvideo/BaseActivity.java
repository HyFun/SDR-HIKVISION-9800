package com.sdr.hkvideo;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by HyFun on 2018/11/13.
 * Email: 775183940@qq.com
 * Description:
 */

public class BaseActivity extends com.sdr.lib.base.BaseActivity {
    @Override
    protected int onHeaderBarToolbarRes() {
        return R.layout.layout_public_toolbar_white;
    }

    @Override
    protected Drawable onHeaderBarDrawable() {
        return getHeaderBarDrawable(getContext());
    }

    public static final Drawable getHeaderBarDrawable(Context context) {
        ColorDrawable drawable = new ColorDrawable(context.getResources().getColor(R.color.colorPrimary));
        return drawable;
    }
}
