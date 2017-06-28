package com.yumao.yumaosmart.activity.itemactivity;

import android.os.Bundle;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseItemActivity;

public class RecommendErWeiMaActivity extends BaseItemActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_er_wei_ma);
        initToobar(getString(R.string.title_recommend_erwema));
    }
}
