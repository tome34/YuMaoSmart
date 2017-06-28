package com.yumao.yumaosmart.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.BrandStoreAdapter;
import com.yumao.yumaosmart.base.LoadingPager;
import com.yumao.yumaosmart.utils.UiUtilities;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kk on 2017/3/18.
 */
public class BrandStoreFragment extends BaseFragment {
    @BindView(R.id.gv_fragment_brand_store)
    GridView mGvFragmentBrandStore;
    private List<String> mData;

    public BrandStoreFragment(List<String> data) {
        mData = data;
    }

    @Override
    protected void initListenner() {

    }

    @Override
    protected void initView() {
        mGvFragmentBrandStore.setNumColumns(2);
        mGvFragmentBrandStore.setAdapter(new BrandStoreAdapter(UiUtilities.getContex(),R.layout.item_brand_store_fragment,mData));
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


        return View.inflate(UiUtilities.getContex(), R.layout.fragment_brand_store, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
