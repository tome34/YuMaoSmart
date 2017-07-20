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

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.yumao.yumaosmart.R;

/**
 * Created by kk on 2017/7/20.
 */

public class SmartreshHeader extends LinearLayout implements RefreshHeader {

    private ImageView mIvLoading;
    private AnimationDrawable mDrawable;
    private SpinnerStyle mSpinnerStyle = SpinnerStyle.Translate;
    private TextView mTvFinish;

    public SmartreshHeader(Context context) {
        this(context,null);
    }

    public SmartreshHeader(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SmartreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View headrView = LayoutInflater.from(context).inflate(R.layout.ani_header_loading,this);
        mIvLoading = (ImageView)headrView.findViewById(R.id.iv_loading);
        mTvFinish = (TextView) headrView.findViewById(R.id.tv_finish);
        mIvLoading.setImageResource(R.mipmap.loading_00000);

        //初始化动画
       /* mIvLoading.setImageResource(R.drawable.ani_header_loading);
        mDrawable = (AnimationDrawable) mIvLoading.getDrawable();*/
    }

    @Override
    public void onPullingDown(float percent, int offset, int headerHeight, int extendHeight) {

    }

    @Override
    public void onReleasing(float percent, int offset, int headerHeight, int extendHeight) {

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

       // mIvLoading.setImageDrawable(mDrawable);

    }

    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        mDrawable.stop();
        mIvLoading.setVisibility(GONE);
        mTvFinish.setVisibility(VISIBLE);
        if (success) {  //刷新成功

            mTvFinish.setText("加载完成");

        } else {       //刷新失败

            mTvFinish.setText("加载失败");
        }
        return 500;//延迟500毫秒之后再弹回
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        switch (newState) {
            case None:

            case PullDownToRefresh: //下拉刷新
                //初始化动画
                mIvLoading.setVisibility(VISIBLE);
                mTvFinish.setVisibility(GONE);
                mIvLoading.setImageResource(R.drawable.ani_header_loading);
                mDrawable = (AnimationDrawable) mIvLoading.getDrawable();
                break;
            case Refreshing:        //正在刷新
                mDrawable.start();
                break;
            case ReleaseToRefresh:  //释放立刻刷新

                break;
        }
    }
}
