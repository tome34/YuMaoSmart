package com.yumao.yumaosmart.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.MyPopularityAdapter;
import com.yumao.yumaosmart.base.LoadingPager;
import com.yumao.yumaosmart.bean.MyPopularityBean;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kk on 2017/3/15.
 */

public class MyPopularityFragment extends BaseFragment {
    @BindView(R.id.rv_fragment_mypopularity)
    RecyclerView mRvFragmentMypopularity;
    private List<MyPopularityBean> mList;
    private MyPopularityAdapter mPopularityAdapter;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private LayoutInflater mInflater;

    public MyPopularityFragment(List<MyPopularityBean> list) {
        mList = list;
    }

    public MyPopularityFragment() {

    }

    @Override

    protected void initListenner() {

    }

    @Override
    protected void initView() {
        mPopularityAdapter = new MyPopularityAdapter(UiUtilities.getContex(), R.layout.item_fragment_my_popularity, mList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UiUtilities.getContex());
        mRvFragmentMypopularity.setLayoutManager(linearLayoutManager);
      mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mPopularityAdapter);
      mInflater = LayoutInflater.from(UiUtilities.getContex());
      RelativeLayout headView = (RelativeLayout) mInflater.inflate(R.layout.item_fragment_my_popularity_headview, null);
        TextView tv = (TextView) headView.findViewById(R.id.tv_item_fragment_mypopularity_head_num);
        tv.setText(mList.size()+"");


        mHeaderAndFooterWrapper.addHeaderView(headView);


      mRvFragmentMypopularity.setAdapter(mHeaderAndFooterWrapper);
    mHeaderAndFooterWrapper.notifyDataSetChanged();
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
        return View.inflate(UiUtilities.getContex(), R.layout.fragment_mypopularity, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
