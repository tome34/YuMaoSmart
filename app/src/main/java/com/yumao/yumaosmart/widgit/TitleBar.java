package com.yumao.yumaosmart.widgit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yumao.yumaosmart.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by cheegon on 3/18/2017.
 */

public class TitleBar extends FrameLayout {
    @BindView(R.id.tv_title)
    TextView  mTvTitle;
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;

    public TitleBar(Context context) {
        super(context);
        initView(null, 0, 0);
    }


    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs, 0, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(attrs, defStyleAttr, defStyleRes);
    }

    private void initView(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_title_bar, this, true);
        ButterKnife.bind(this);
        // 通过在属性里赋值
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TitleBar, defStyleAttr, defStyleRes);
            String title = typedArray.getString(R.styleable.TitleBar_aTitle);
            Drawable drawable = typedArray.getDrawable(R.styleable.TitleBar_aIcon);
            mTvTitle.setText(title);
            mIvIcon.setImageDrawable(drawable);
        } else {
            mIvIcon.setVisibility(View.GONE);
        }
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.main_black));// 设置背景颜色
        //  setPadding(getLeft(), getTop() + UiUtil.getStatusBarHeight(getContext()), getRight(), getBottom());
    }

    public void setTitle(@StringRes int titleRes) {
        if (titleRes <= 0) return;
        mTvTitle.setText(titleRes);
    }

    public void setIcon(int iconRes) {
        if (iconRes <= 0) {
            mIvIcon.setVisibility(View.GONE);
            return;
        }
        mIvIcon.setImageResource(iconRes);
    }

    public void setIconOnClickListener(OnClickListener iconClickListener) {
        mIvIcon.setOnClickListener(iconClickListener);
    }


}
