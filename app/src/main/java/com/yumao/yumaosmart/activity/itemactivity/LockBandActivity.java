package com.yumao.yumaosmart.activity.itemactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseItemActivity;

/**
 * Created by kk on 2017/3/14.
 */
public class LockBandActivity  extends BaseItemActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lockband);
        initToobar(getString(R.string.title_toband));
    }
}
