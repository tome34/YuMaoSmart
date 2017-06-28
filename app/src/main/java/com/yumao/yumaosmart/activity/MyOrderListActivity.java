package com.yumao.yumaosmart.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.OrderlistPagerAdapter;
import com.yumao.yumaosmart.base.BaseItemActivity;
import com.yumao.yumaosmart.bean.MyOrderlistBean;
import com.yumao.yumaosmart.bean.OrderListBean;
import com.yumao.yumaosmart.fragment.OrderListFragment;

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
 * Created by kk on 2017/3/10.
 */
public class MyOrderListActivity extends BaseItemActivity {

    @BindView(R.id.vp_orderlist_activity)
    ViewPager mVpOrderlistActivity;

    @BindView(R.id.indicator_myorderlist)
    MagicIndicator mIndicatorMyorderlist;
    private List<String> mTitleDataList = new ArrayList<>();
   private List<Fragment> mList = new ArrayList<>();
    private  List<OrderListBean> mData = new ArrayList<>();
    private OrderListBean mOrderListBean;

    private CommonNavigator mCommonNavigator;
    public int mPostion=0 ;

@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_myorderlist);
    initToobar(getString(R.string.title_myorderlist));
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        MyOrderlistBean key = bundle.getParcelable("key");
        mPostion=key.getPostion();
        ButterKnife.bind(this);
        initData();
        init();
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            mOrderListBean = new OrderListBean();
            mOrderListBean.isSelected = false;
            mOrderListBean.setOrderlistSerialNumber("N5536332586");
            mOrderListBean.setOrderlistState("待付款");
            mOrderListBean.setLogoRes(String.valueOf(R.mipmap.item_orderlist_logo));
            mOrderListBean.setMallName("珠宝商城");
            mOrderListBean.setGoodsNumber("3");
            mOrderListBean.setGoodsDetail("S928银镶翡翠(C货)");
            mOrderListBean.setGoodsPrice("￥80");
            mOrderListBean.setGoodsPictureRes(String.valueOf(R.mipmap.item_orderlist_goods));
            mOrderListBean.setGoodsTotal("共3件商品 总计 : ￥2345 (不含运费)");
            mData.add(mOrderListBean);

        }

        mTitleDataList.add("全部");
        mTitleDataList.add("待付款");
        mTitleDataList.add("待发货");
        mTitleDataList.add("待收货");
        mTitleDataList.add("已完成");

    }

    private void init() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        mList.add(new OrderListFragment(mData));
        mList.add(new OrderListFragment(mData));
        mList.add(new OrderListFragment(mData));
        mList.add(new OrderListFragment(mData));
        mList.add(new OrderListFragment(mData));
        OrderlistPagerAdapter orderlistPagerAdapter = new OrderlistPagerAdapter(fragmentManager, mList);
        mVpOrderlistActivity.setAdapter(orderlistPagerAdapter);
        initIndicator(mPostion);

        ViewPagerHelper.bind(mIndicatorMyorderlist, mVpOrderlistActivity);
        mVpOrderlistActivity.setCurrentItem(mPostion);
    }

    private void initIndicator(final int postion) {

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
                        mVpOrderlistActivity.setCurrentItem(index);
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
        mIndicatorMyorderlist.setNavigator(mCommonNavigator);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
