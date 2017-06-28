package com.yumao.yumaosmart.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by kk on 2017/2/21.
 */

public class BaseActivity extends AppCompatActivity {

    private LoadingPager mLoadingPager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initView();
        setContentView(mLoadingPager);
        initData();
        initListenner();
    }

    public void initListenner() {

    }

    public void initData() {

    }

    public void init() {

    }
    //构造mLoadingPager对象
    public void initView() {
        mLoadingPager = new LoadingPager(this) {
            @Override
            public View initSuccessView() {
                return onInitSuccessView();
            }

            @Override
            protected LoadingPagerEnum initData() {
                return    onInitData();
            }

        };

    }
    //在子线程中真正加载数据
    public LoadingPager.LoadingPagerEnum onInitData() {

        return null;
    }

    public View onInitSuccessView() {
        return null;
    }

}
