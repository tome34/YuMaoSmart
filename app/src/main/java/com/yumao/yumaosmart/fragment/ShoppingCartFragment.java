package com.yumao.yumaosmart.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.ShoppingCardAdapter;
import com.yumao.yumaosmart.base.LoadingPager;
import com.yumao.yumaosmart.bean.ShoppingCardbean;
import com.yumao.yumaosmart.utils.UiUtilities;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kk on 2017/2/24.
 */

public class ShoppingCartFragment extends BaseFragment {

    @BindView(R.id.lv_shopping_cart)
    ListView mLvShoppingCart;
    private List<ShoppingCardbean> mData = new ArrayList<>();
    private ShoppingCardbean mShoppingCardbean;
    private ShoppingCardAdapter mShoppingCardAdapter;
    private View mFragmentView;

    @Override

    protected void initListenner() {

    }

    @Override
    protected void initView() {


      for (int i = 0; i < 10; i++) {
          mShoppingCardbean = new ShoppingCardbean();
          mShoppingCardbean.setGoodsName("S925银镶翡翠(A货)+吊坠");
          mShoppingCardbean.setStoreName("个人店铺");
          mShoppingCardbean.setPrice("$8888.88");
          mShoppingCardbean.setNum("2");
          mShoppingCardbean.setGoodsRes(String.valueOf(R.mipmap.shopping_card_goods));
          mData.add(mShoppingCardbean);

      }
        mShoppingCardAdapter = new ShoppingCardAdapter(UiUtilities.getContex(),R.layout.item_shopping_card,mData);
        mLvShoppingCart.setAdapter(mShoppingCardAdapter);
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
        mFragmentView = View.inflate(UiUtilities.getContex(), R.layout.fragment_shopping_cart, null);

        return mFragmentView;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
