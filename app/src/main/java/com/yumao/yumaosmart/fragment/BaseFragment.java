package com.yumao.yumaosmart.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.yumao.yumaosmart.base.LoadingPager;
import com.yumao.yumaosmart.utils.UiUtilities;

/**
 * Created by kk on 2017/2/23.
 */

public abstract class BaseFragment extends Fragment {
    LoadingPager mLoadingPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mLoadingPager == null) {


            mLoadingPager = new LoadingPager(UiUtilities.getContex()) {
                @Override
                public View initSuccessView() {
                    return onInitView();
                }

                @Override
                protected LoadingPagerEnum initData() {
                    return onInitData();
                }
            };
        } else {
            ViewParent parent = mLoadingPager.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(mLoadingPager);
            }
        }

        return mLoadingPager;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initView();
        initListenner();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    protected abstract void initListenner();

    protected abstract void initView();

    protected abstract void init();

    //    子线程加载数据
    public abstract LoadingPager.LoadingPagerEnum onInitData();
//    加载布局文件
    public abstract View onInitView();
}
