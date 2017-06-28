package com.yumao.yumaosmart.activity.itemactivity;

import android.os.Bundle;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseItemActivity;

public class MyErWeiMaActivity extends BaseItemActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_er_wei_my);
        initToobar(getString(R.string.title_my_erweima));
    }
}
