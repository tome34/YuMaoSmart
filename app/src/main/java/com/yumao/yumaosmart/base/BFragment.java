package com.yumao.yumaosmart.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 顶层fragment的基类抽取
 * Created by cheegon on 3/4/2017.
 */

public abstract class BFragment extends Fragment {
    protected Context mContext;
    private Bundle mBundle;
    protected View mRootView;
    protected Context mAppContext;
    protected Activity mActivity;
    private String packageName4Umeng = this.getClass().getName();
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = getActivity();
        // 获取application的context,避免出现toast弹出或者glide等在异步加载图片时候,报空指针问题
        mAppContext = context.getApplicationContext();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取传递的参数
        mBundle = getArguments();
        initBundle(mBundle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 判断该根目录是否存在,如果存在的话,直接移除掉
        if (mRootView != null) {
            ViewGroup viewParent = (ViewGroup) mRootView.getParent();// 强转为viewgroup
            if (viewParent != null) {
                viewParent.removeView(mRootView);
            }
        } else {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            onBindViewBefore(mRootView);
            unbinder = ButterKnife.bind(this, mRootView);
            // 保存状态数据,当重启的该fragment的时候,获取bundle里面的数据savedInstanceState
            if (savedInstanceState != null) {
                onStoreInstance(savedInstanceState);
            }
            initWidget(mRootView);
            initData();
        }
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
      //  MobclickAgent.onPageStart(this.packageName4Umeng);
    }

    @Override
    public void onPause() {
        super.onPause();
      //  MobclickAgent.onPageEnd(this.packageName4Umeng);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        mBundle = null;// 在退出时,清空bundle里面的数据,避免出现重复
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }


    /**
     * 在绑定view之前,可以进行添加一些view
     *
     * @param rootView
     */
    protected void onBindViewBefore(View rootView) {

    }

    /**
     * 初始化控件,调用该方法可以updateUI
     *
     * @param rootView
     */
    protected void initWidget(View rootView) {

    }

    /**
     * 加载数据
     */
    protected void initData() {
    }


    /**
     * 重启fragment时,调用该方法,获得上次退出的数据
     *
     * @param savedInstanceState
     */
    protected void onStoreInstance(Bundle savedInstanceState) {

    }

    /**
     * 获取layout
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 处理传递过来的bundle数据
     *
     * @param bundle
     */
    protected void initBundle(Bundle bundle) {

    }

    /**
     * 获取序列化的数据,泛型T,需要序列化,可以传递ArrayList以及序列化过的map/bean类等
     *
     * @param key
     * @param <T>
     * @return
     */
    protected <T extends Serializable> T getBundleSerializable(String key) {
        if (mBundle == null) {
            return null;
        } else {
            return (T) mBundle.getSerializable(key);
        }
    }

    /**
     * 刷新ui
     */
    protected void updateView() {

    }




    private <T extends View> T findView(int viewId) {
        return (T) mRootView.findViewById(viewId);
    }

    protected void setText(int viewId, String text) {
        TextView textView = findView(viewId);
        setText(textView, text);
    }

    protected void setText(TextView textView, String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        textView.setText(text);
    }

    protected void setText(int viewId, String text, String emptyTip) {
        TextView textView = findView(viewId);
        setText(textView, text, emptyTip);
    }

    protected void setText(TextView textView, String text, String emptyTip) {
        if (TextUtils.isEmpty(text)) {
            textView.setText(emptyTip);
            return;
        }
        textView.setText(text);
    }

    protected void setTextEmptyGone(int viewId, String text) {
        TextView textView = findView(viewId);
        if (TextUtils.isEmpty(text)) {
            textView.setVisibility(View.GONE);
            return;
        }
        textView.setText(text);
    }

    /**
     * 显示视图
     *
     * @param view
     */
    protected void setVisibility(View view) {
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 不显示视图
     *
     * @param view
     */
    protected void setInvisibility(View view) {
        view.setVisibility(View.INVISIBLE);
    }



}
