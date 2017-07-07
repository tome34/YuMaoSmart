package com.yumao.yumaosmart.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.OrderListDataAdapter;
import com.yumao.yumaosmart.base.LoadingPager;
import com.yumao.yumaosmart.bean.OrderListBean;
import com.yumao.yumaosmart.utils.UiUtilities;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by kk on 2017/3/10.
 */

public class OrderListFragment extends BaseFragment {
    public List<OrderListBean> mList;
    @BindView(R.id.lv_orderlist_activity)
    ListView mLvOrderlistActivity;

    @SuppressLint({"NewApi", "ValidFragment"})
    public OrderListFragment(List<OrderListBean> list) {
        mList = list;
    }
    public  OrderListFragment(){

    }
    @Override
    protected void initListenner() {

    }

    @Override
    protected void initView() {
        mLvOrderlistActivity.setAdapter(new OrderListDataAdapter(UiUtilities.getContex(), R.layout.item_orderlist_activity,mList));
    }

    @Override
    protected void init() {

    }

    @Override
    public LoadingPager.LoadingPagerEnum onInitData() {
        return LoadingPager.LoadingPagerEnum.SUCCESS;
    }

    @Override
    public View onInitView() {
        return View.inflate(UiUtilities.getContex(), R.layout.fragment_orderlist, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
