package com.yumao.yumaosmart.activity;

import android.os.Bundle;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseItemActivity;

import org.greenrobot.eventbus.EventBus;

public class MyFavoriteActivity extends BaseItemActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite);

        initToobar("我的收藏");
        //EventBus.getDefault().register(this);
        intData();
        initView();
    }

    private void intData() {

    }

    private void initView() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
