package com.yumao.yumaosmart.activity.itemactivity;

import android.os.Bundle;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseItemActivity;

public class OrderDetailActivity extends BaseItemActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initToobar(getString(R.string.title_order_detail));
    }
}
