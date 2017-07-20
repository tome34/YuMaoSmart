package com.yumao.yumaosmart.widgit;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.yumao.yumaosmart.R;

/**
 * Created by kk on 2017/7/20.
 */

public class Smartreshfooter extends LinearLayout implements RefreshFooter {

    private ImageView mIvFooterLoading;
    private AnimationDrawable mDrawable;
    private SpinnerStyle mSpinnerStyle = SpinnerStyle.Translate;
    private TextView mTvFooterFinish;
    private boolean mLoadmoreFinished = false;

    public Smartreshfooter(Context context) {
        this(context,null);
    }

    public Smartreshfooter(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Smartreshfooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View headrView = LayoutInflater.from(context).inflate(R.layout.ani_footer_loading,this);
        mIvFooterLoading = (ImageView)headrView.findViewById(R.id.iv_footer_loading);
        mTvFooterFinish = (TextView) headrView.findViewById(R.id.tv_footer_finish);
        mIvFooterLoading.setImageResource(R.mipmap.botton_loading_00000);

        //初始化动画
       /* mIvLoading.setImageResource(R.drawable.ani_header_loading);
        mDrawable = (AnimationDrawable) mIvLoading.getDrawable();*/
    }


    @Override
    public void onPullingUp(float percent, int offset, int footerHeight, int extendHeight) {

    }

    @Override
    public void onPullReleasing(float percent, int offset, int footerHeight, int extendHeight) {

    }

    /**
     * 设置数据全部加载完成，将不能再次触发加载功能
     */
    @Override
    public boolean setLoadmoreFinished(boolean finished) {
        if (mLoadmoreFinished != finished) {
            mLoadmoreFinished = finished;
            if (finished) {
                mTvFooterFinish.setText("数据已全部加载完");
            } else {

            }
            mDrawable.stop();
            mIvFooterLoading.setVisibility(GONE);
            mTvFooterFinish.setVisibility(VISIBLE);

        }
        return true;
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return mSpinnerStyle;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {

    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int height, int extendHeight) {

    }

    @Override
    public int onFinish(RefreshLayout layout, boolean success) {

        if (!mLoadmoreFinished) {
            mDrawable.stop();
            mIvFooterLoading.setVisibility(GONE);
            mTvFooterFinish.setVisibility(VISIBLE);
            mTvFooterFinish.setText("数据加载完成");
            return 500;
        }
        return 0;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {

        if (!mLoadmoreFinished) {
            switch (newState) {
                case None:

                case PullToUpLoad:    //上拉加载
                    //初始化动画
                    mIvFooterLoading.setVisibility(VISIBLE);
                    mTvFooterFinish.setVisibility(GONE);
                    mIvFooterLoading.setImageResource(R.drawable.ani_footer_loading);
                    mDrawable = (AnimationDrawable) mIvFooterLoading.getDrawable();
                    break;
                case Loading:         //正在加载
                    mDrawable.start();
                    break;
                case ReleaseToLoad:    //立刻加载

                    break;
            }
        }
    }
}
