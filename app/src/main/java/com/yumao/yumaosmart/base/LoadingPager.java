package com.yumao.yumaosmart.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.utils.UiUtilities;

/**
 * Created by kk on 2017/2/23.
 */

public abstract class LoadingPager extends FrameLayout {
    public  int  mCurrentState=STATE_LOADING;
    public static final int STATE_LOADING=0;
    public static final int  STATE_EMPTY=1;  //空
    public static final int  STATE_ERROR=2;  //失败
    public static final int STATE_SUCCESS=3;  //成功

    private View mErrorViw;
    private View mEmptyView;
    private View mLoadingView;
    private View mSuccessView;
    private ImageView mImageView;
    private MyAsyncTask mTask;

    public LoadingPager(Context context) {
        super(context);
        initContentView(context);
    }

    public void initContentView(Context context) {
        mErrorViw = View.inflate(context, R.layout.loading_error, null);
        addView(mErrorViw);

        mEmptyView = View.inflate(context, R.layout.loading_empty, null);
        addView(mEmptyView);

        mLoadingView = View.inflate(context, R.layout.loading_loading, null);
        addView(mLoadingView);

        mSuccessView = initSuccessView();
        addView(mSuccessView);

        triggleLoadData();
    }

    //加载成功,由自己子类实现
    public abstract View initSuccessView();

    public void refreshUIByState() {
        mLoadingView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
        mErrorViw.setVisibility(View.GONE);

        if (mSuccessView != null) {
            mSuccessView.setVisibility(View.GONE);
        }
        switch (mCurrentState) {

            case STATE_LOADING:
                mLoadingView.setVisibility(View.VISIBLE);
            break;
            case STATE_ERROR:
                mErrorViw.setVisibility(View.VISIBLE);
                mImageView = (ImageView) mErrorViw.findViewById(R.id.iv_loading_again);
                mImageView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        triggleLoadData();
                    }
                });
                break;
            case STATE_EMPTY:
                mEmptyView.setVisibility(View.VISIBLE);
                break;
            case STATE_SUCCESS:
                if (mSuccessView == null) {
                    mSuccessView=initSuccessView();
                    addView(mSuccessView);
                }
                mSuccessView.setVisibility(View.VISIBLE);
                break;


        }
    }


    public void triggleLoadData(){
        mCurrentState=STATE_LOADING;
        refreshUIByState();
        if (mCurrentState != STATE_SUCCESS&&mTask==null) {
            mTask = new MyAsyncTask();

            ThreadPoorProxy.getInstance().execute(mTask);

        }

    };

    class MyAsyncTask implements Runnable {


        @Override
        public void run() {
            LoadingPagerEnum loadingEnumRst = initData();
            int state=loadingEnumRst.state;
            mCurrentState=state;
            UiUtilities.getHandler().post(new Runnable() {
                @Override
                public void run() {

                    refreshUIByState();
                }
            });
            mTask=null;
        }
    }

    protected abstract LoadingPagerEnum initData();


    public enum LoadingPagerEnum{
       LOADING(STATE_LOADING), EMPTY(STATE_EMPTY),ERROR(STATE_ERROR), SUCCESS(STATE_SUCCESS),;
        int state;
        LoadingPagerEnum(int i) {
            this.state = i;
        }
    }
}
