package com.yumao.yumaosmart.activity.itemactivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.MyBaseFragmentAdapter;
import com.yumao.yumaosmart.base.BaseItemActivity;
import com.yumao.yumaosmart.bean.MyPopularityBean;
import com.yumao.yumaosmart.fragment.MyPopularityFragment;

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

public class MyPularityActvity extends BaseItemActivity {


    @BindView(R.id.magic_indicator_mypopularity)
    MagicIndicator mMagicIndicatorMypopularity;
    @BindView(R.id.vp_mypopularity)
    ViewPager mVpMypopularity;
    private List<String> mTitleDataList = new ArrayList<>();
    private List<Fragment> mFragmentData = new ArrayList<>();
    private List<MyPopularityBean> mPopularityData = new ArrayList<>();


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pularity_actvity);
        ButterKnife.bind(this);
        initToobar(getString(R.string.title_my_popularity));
        init();
        initData();

        initView();
        ViewPagerHelper.bind(mMagicIndicatorMypopularity, mVpMypopularity);
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
                        mVpMypopularity.setCurrentItem(index);
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
        mMagicIndicatorMypopularity.setNavigator(commonNavigator);
    }

    private void initData() {
        mTitleDataList.add("今日人气");
        mTitleDataList.add("本月人气");
        mTitleDataList.add("上月人气");
        mTitleDataList.add("总人气");
        for (int i = 0; i < 10; i++) {
            MyPopularityBean myPopularityBean = new MyPopularityBean();
            myPopularityBean.setFavour(3);
            myPopularityBean.setPetName("用户昵称");
            myPopularityBean.setTouXiangRes(String.valueOf(R.mipmap.first_page_person_icon_touxiang));
            myPopularityBean.setTime("2015.9.12 19:45:12");
            mPopularityData.add(myPopularityBean);
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        mFragmentData.add(new MyPopularityFragment(mPopularityData));
        mFragmentData.add(new MyPopularityFragment(mPopularityData));
        mFragmentData.add(new MyPopularityFragment(mPopularityData));
        mFragmentData.add(new MyPopularityFragment(mPopularityData));
        mVpMypopularity.setAdapter(new MyBaseFragmentAdapter(supportFragmentManager,mFragmentData));

    }

    private void init() {

    }
}
