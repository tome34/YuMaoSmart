package com.yumao.yumaosmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.yumao.yumaosmart.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kk on 2017/2/21.
 */

public class MyJewelrystore extends AppCompatActivity {


    @BindView(R.id.iv_activity_myjewelry_store)
    ImageView mIvActivityMyjewelryStore;
    @BindView(R.id.iv_activity_myjewelry_active_area)
    ImageView mIvActivityMyjewelryActiveArea;
    @BindView(R.id.iv_activity_myjewelry_order_area)
    ImageView mIvActivityMyjewelryOrderArea;
    @BindView(R.id.iv_activity_myjewelry_active_my_order)
    ImageView mIvActivityMyjewelryActiveMyOrder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.iv_activity_myjewelry_store, R.id.iv_activity_myjewelry_active_area, R.id.iv_activity_myjewelry_order_area, R.id.iv_activity_myjewelry_active_my_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_activity_myjewelry_store:
                startActivity(new Intent(this,UpLoadActivity.class));
                break;
            case R.id.iv_activity_myjewelry_active_area:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.iv_activity_myjewelry_order_area:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.iv_activity_myjewelry_active_my_order:
                break;
        }
    }



}
