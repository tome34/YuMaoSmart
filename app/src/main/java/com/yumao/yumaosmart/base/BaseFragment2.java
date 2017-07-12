package com.yumao.yumaosmart.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.yumao.yumaosmart.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Leon on 2017/2/15.
 */

public abstract class BaseFragment2 extends Fragment {

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.load_failed_container)
    LinearLayout mLoadFailedContainer;

    @BindView(R.id.layout_container)
    FrameLayout mFrameLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment2_base, null);
        ButterKnife.bind(this, root);
        init();
        return root;
    }

    protected void init() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        startLoadData();
    }

    /**
     * 子类去实现该方法加载各自的数据
     */
    protected abstract void startLoadData();

    /**
     * 加载数据成功的方法
     */
    protected void onDataLoadSuccess() {
        //隐藏进度条
        mProgressBar.setVisibility(View.GONE);
        //加载各个页面的布局
        mFrameLayout.addView(onCreateContentView());

    }

    /**
     *  创建页面内容的视图，子类去实现各自的布局
     */
    protected abstract View onCreateContentView();

    protected void onDataLoadFailed() {
        //隐藏进度条
        mProgressBar.setVisibility(View.GONE);
        //显示错误布局
        mLoadFailedContainer.setVisibility(View.VISIBLE);
    }

    /**
     *  重试按钮的封装
     */
    @OnClick(R.id.load_retry)
    public void retry() {
        //隐藏错误视图
        mLoadFailedContainer.setVisibility(View.GONE);
        //显示进度条
        mProgressBar.setVisibility(View.VISIBLE);
        //重新加载数据
        startLoadData();
    }
}
