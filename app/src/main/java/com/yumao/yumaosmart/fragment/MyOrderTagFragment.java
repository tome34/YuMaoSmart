package com.yumao.yumaosmart.fragment;

import com.yumao.yumaosmart.base.BaseSmartTabFragment;
import com.yumao.yumaosmart.bean.SmartTabInfo;
import com.yumao.yumaosmart.fragment.orderTabs.QuanbuPagerFragment;

/**
 * Created by kk on 2017/7/4.
 */

public class MyOrderTagFragment extends BaseSmartTabFragment {
    @Override
    public void initSmartTabInfos() {
        mSmartTabInfos.add(new SmartTabInfo(QuanbuPagerFragment.class ,"全部",null) );
        mSmartTabInfos.add(new SmartTabInfo(QuanbuPagerFragment.class ,"待付款",null) );
        mSmartTabInfos.add(new SmartTabInfo(QuanbuPagerFragment.class ,"待发货",null) );
        mSmartTabInfos.add(new SmartTabInfo(QuanbuPagerFragment.class ,"待收货",null) );
        mSmartTabInfos.add(new SmartTabInfo(QuanbuPagerFragment.class ,"已完成",null) );
    }
}
