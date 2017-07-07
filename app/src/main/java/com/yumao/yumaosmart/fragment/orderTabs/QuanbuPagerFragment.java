package com.yumao.yumaosmart.fragment.orderTabs;

import android.view.View;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseFragment2;
import com.yumao.yumaosmart.utils.UiUtilities;

/**
 * Created by kk on 2017/7/4.
 */
public class QuanbuPagerFragment extends BaseFragment2 {


    @Override
    protected void init() {
        super.init();


    }

    @Override
    protected void startLoadData() {

    }

    @Override
    protected View onCreateContentView() {


        return View.inflate(UiUtilities.getContex(), R.layout.fragment_orderlist, null);
    }
}
