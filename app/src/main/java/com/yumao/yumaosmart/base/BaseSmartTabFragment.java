package com.yumao.yumaosmart.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.SmartTabPagerAdapter;
import com.yumao.yumaosmart.bean.SmartTabInfo;
import com.yumao.yumaosmart.widgit.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public abstract class BaseSmartTabFragment extends BaseTabFragment {

    protected List<SmartTabInfo> mSmartTabInfos;
    @BindView(R.id.tab_smartlayout)
    SmartTabLayout mTabSmartlayout;
    @BindView(R.id.view_pager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.activity_my_order)
    LinearLayout mActivityMyOrder;



      @Override
    public View initView() {
        return View.inflate(mContext, R.layout.fragment_base_smarttab, null);
    }

    @Override
    public void initData() {
        super.initData();
        //数据集-->模拟数据集-->集合
        mSmartTabInfos = new ArrayList<>();

        initSmartTabInfos();


        //1.为viewpager绑定一个adapter
        // MyNewsTabPagerAdapter adapter = new MyNewsTabPagerAdapter(getChildFragmentManager(), mSmartTabInfos, mContext);
        SmartTabPagerAdapter adapter = new SmartTabPagerAdapter(getFragmentManager(), mSmartTabInfos, mContext);
        mViewPager.setAdapter(adapter);

        //2.indicator和viewpager的绑定
        mTabSmartlayout.setViewPager(mViewPager);
    }

    /**
     * 初始化具体的smartTabInfos集合信息
     * 在BaseSmartTabFragment里面,不知道initSmartTabInfos具体实现,交给子类
     * 子类必须实现即可,定义成为抽象方法
     */
    public abstract void initSmartTabInfos();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
