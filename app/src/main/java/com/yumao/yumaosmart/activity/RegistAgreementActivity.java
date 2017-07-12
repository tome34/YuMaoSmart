package com.yumao.yumaosmart.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseItemActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistAgreementActivity extends BaseItemActivity {

    @BindView(R.id.tv_titlle)
    TextView mTvTitlle;
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.activity_regist_agreement)
    LinearLayout mActivityRegistAgreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_agreement);

        initToobar("用户注册协议");
        ButterKnife.bind(this);
    }
}
