package com.yumao.yumaosmart.activity.itemactivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.MyBaseFragmentAdapter;
import com.yumao.yumaosmart.fragment.BrandStoreFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandStoreActivity extends AppCompatActivity {

    @BindView(R.id.vp_activity_brand_store)
    ViewPager mVpActivityBrandStore;
    @BindView(R.id.magic_indicator_brand_store)
    MagicIndicator mMagicIndicatorBrandStore;
    private List<String> mTitleDataList = new ArrayList<>();
    private List<Fragment> mList = new ArrayList<>();
    private List<String> mData = new ArrayList<>();
    private int mVenderId;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_store);
        ButterKnife.bind(this);
        setVenderId();
        init();
        initData();


        initView();
        ViewPagerHelper.bind(mMagicIndicatorBrandStore, mVpActivityBrandStore);
    }

    private void initData() {
            mTitleDataList.add("店铺首页");
            mTitleDataList.add("全部商品");
            mTitleDataList.add("新    品");
            mTitleDataList.add("特    卖");
        for (int i = 0; i <100; i++) {
            mData.add(String.valueOf(R.mipmap.activity_first_classify_detail_main_pic));

        }
        mList.add(new BrandStoreFragment(mData));
        mList.add(new BrandStoreFragment(mData));
        mList.add(new BrandStoreFragment(mData));
        mList.add(new BrandStoreFragment(mData));
        mVpActivityBrandStore.setAdapter(new MyBaseFragmentAdapter(getSupportFragmentManager(),mList));
    }

    private void init() {
    }

    private void initView() {

        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setSelectedColor(Color.RED);
                colorTransitionPagerTitleView.setTextSize(20);
                colorTransitionPagerTitleView.setText(mTitleDataList.get(index));
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mVpActivityBrandStore.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        });
        mMagicIndicatorBrandStore.setNavigator(commonNavigator);
    }

    public void setVenderId() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
       mVenderId= extras.getInt("venderId");

    }
}
