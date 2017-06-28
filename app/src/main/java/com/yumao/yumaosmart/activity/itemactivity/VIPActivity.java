package com.yumao.yumaosmart.activity.itemactivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.MyBaseFragmentAdapter;
import com.yumao.yumaosmart.base.BaseItemActivity;
import com.yumao.yumaosmart.bean.VipOrderListBean;
import com.yumao.yumaosmart.fragment.VipActivityFragment;

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

/**
 * Created by kk on 2017/3/14.
 */
public class VIPActivity extends BaseItemActivity {



    @BindView(R.id.indicator_vipactivity)
    MagicIndicator mIndicatorVipactivity;
    @BindView(R.id.vp_vipactivity)
    ViewPager mVpVipactivity;
    private List<String> mTitleDataList = new ArrayList<>();
    private List<Fragment> mList = new ArrayList<>();
    private List<VipOrderListBean> mData = new ArrayList<>();
    private VipOrderListBean mVipOrderListBean;

    private CommonNavigator mCommonNavigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_activity);
        ButterKnife.bind(this);
        initToobar(getString(R.string.title_vip_order));
        initData();
        init();
    }
//初始化数据
    private void initData() {
        for (int i = 0; i < 5; i++) {
            mVipOrderListBean = new VipOrderListBean();
            mVipOrderListBean.setTouXiangRes(String.valueOf(R.mipmap.first_page_person_icon_touxiang));
            mVipOrderListBean.setOrderSerialnum("001558858");
            mVipOrderListBean.setOrderState("待收货");
            mVipOrderListBean.setPetName("中心合伙人");
            mVipOrderListBean.setOrderTime("2017-2-3");
            mVipOrderListBean.setOrderTotalMoney("2360");
            mVipOrderListBean.setCityPartner("￥56");
            mVipOrderListBean.setCenterPartner("￥56");
            mData.add(mVipOrderListBean);

        }

        mTitleDataList.add("全部");
        mTitleDataList.add("待付款");
        mTitleDataList.add("待发货");
        mTitleDataList.add("待收货");
        mTitleDataList.add("已完成");

    }

    private void init() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        mList.add(new VipActivityFragment(mData));
        mList.add(new VipActivityFragment(mData));
        mList.add(new VipActivityFragment(mData));
        mList.add(new VipActivityFragment(mData));
        mList.add(new VipActivityFragment(mData));

        MyBaseFragmentAdapter myBaseFragmentAdapter = new MyBaseFragmentAdapter(fragmentManager, mList);
        mVpVipactivity.setAdapter(myBaseFragmentAdapter);
        initIndicator();

        ViewPagerHelper.bind(mIndicatorVipactivity, mVpVipactivity);
    }
//初始化指示器
    private void initIndicator() {

        mCommonNavigator = new CommonNavigator(this);
        mCommonNavigator.setAdapter(new CommonNavigatorAdapter() {

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
                        mVpVipactivity.setCurrentItem(index);
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

        mIndicatorVipactivity.setNavigator(mCommonNavigator);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
