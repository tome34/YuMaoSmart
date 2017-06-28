package com.yumao.yumaosmart.activity.itemactivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.MyVipAdapter;
import com.yumao.yumaosmart.base.BaseItemActivity;
import com.yumao.yumaosmart.bean.MyVipBean;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceCenterActivity extends BaseItemActivity {


    @BindView(R.id.search_activity_servicecenter)
    SearchView mSearchActivityServicecenter;
    @BindView(R.id.rv_activity_servicecenter)
    RecyclerView mRvActivityServicecenter;
    @BindView(R.id.activity_my_partner)
    LinearLayout mActivityMyPartner;
    private List<MyVipBean> mData = new ArrayList<>();
    private MyVipAdapter mMyVipAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_center);
        ButterKnife.bind(this);
        initToobar(getString(R.string.title_service_center));
        init();
        initData();

        initView();

    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvActivityServicecenter.setLayoutManager(linearLayoutManager);
        mMyVipAdapter = new MyVipAdapter(this, R.layout.item_activity_my_vip, mData);


        mRvActivityServicecenter.setLayoutManager(linearLayoutManager);
        HeaderAndFooterWrapper mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mMyVipAdapter);
        LayoutInflater mInflater = LayoutInflater.from(this);
        RelativeLayout headView = (RelativeLayout) mInflater.inflate(R.layout.item_fragment_my_popularity_headview, null);
        TextView tv = (TextView) headView.findViewById(R.id.tv_item_fragment_mypopularity_head_num);
        tv.setText(mData.size() + "");


        mHeaderAndFooterWrapper.addHeaderView(headView);


        mRvActivityServicecenter.setAdapter(mHeaderAndFooterWrapper);
        mHeaderAndFooterWrapper.notifyDataSetChanged();
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            MyVipBean myVipBean = new MyVipBean();
            myVipBean.setTouXiangRes(String.valueOf(R.mipmap.first_page_person_icon_touxiang));
            myVipBean.setPetName("会员昵称");
            myVipBean.setEnterTime("2017-01-01");
            myVipBean.setOrderNum("20单");
            mData.add(myVipBean);
        }

    }

    private void init() {
    }
}
