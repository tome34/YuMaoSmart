package com.yumao.yumaosmart.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.VipActivityAdapter;
import com.yumao.yumaosmart.base.LoadingPager;
import com.yumao.yumaosmart.bean.VipOrderListBean;
import com.yumao.yumaosmart.utils.UiUtilities;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kk on 2017/3/14.
 */

public class VipActivityFragment extends BaseFragment {
    @BindView(R.id.lv_fragment_vipactivity)
   public ListView mLvFragmentVipactivity;
    private List<VipOrderListBean> mData;

    @SuppressLint({"NewApi", "ValidFragment"})
    public VipActivityFragment(List<VipOrderListBean> data) {
        mData = data;
    }

    public VipActivityFragment() {

    }
    @Override

    protected void initListenner() {

    }

    @Override
    protected void initView() {
        mLvFragmentVipactivity.setAdapter(new VipActivityAdapter(UiUtilities.getContex(), R.layout.item_fragment_vip_activity,mData ));
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
        return View.inflate(UiUtilities.getContex(), R.layout.fragment_vip_activity, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
