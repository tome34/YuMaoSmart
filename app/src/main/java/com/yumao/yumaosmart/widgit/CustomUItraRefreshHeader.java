package com.yumao.yumaosmart.widgit;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yumao.yumaosmart.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by asus on 2017/7/9.
 */

public class CustomUItraRefreshHeader extends RelativeLayout implements PtrUIHandler {

    private ImageView mIvLoading;
    private AnimationDrawable mDrawable;
    private AnimationDrawable mDrawable1;

    public CustomUItraRefreshHeader(Context context) {
        this(context,null);
    }

    public CustomUItraRefreshHeader(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomUItraRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    //初始化布局
    private void initView(Context context) {


       // View.inflate(context, R.layout.vie_loading_layout,this);
        View headrView = LayoutInflater.from(context).inflate(R.layout.vie_loading_layout,this);
        mIvLoading = (ImageView)headrView.findViewById(R.id.iv_loading);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec ,320);
    }
    //重置时，将动画置为初始状态
    @Override
    public void onUIReset(PtrFrameLayout frame) {
    mIvLoading.setImageResource(R.drawable.loading_00000);
    }
//下拉
    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {


    }
//开始动画
    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {

        mIvLoading.setImageResource(R.drawable.loading_animation);
        mDrawable1 = (AnimationDrawable) mIvLoading.getDrawable();
        mIvLoading.setImageDrawable(mDrawable1);
        mDrawable1.start();



    }
//加载完成
    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {

    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

    }
}
