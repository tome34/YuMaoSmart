package com.yumao.yumaosmart.web;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseItemActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FristBannerWebActivity extends BaseItemActivity {


    @BindView(R.id.webview)
    WebView mWebView;
    @BindView(R.id.activity_frist_banner_web)
    LinearLayout mActivityFristBannerWeb;
    private String mUriWeb;
    private String mMUriWeb;
    private String mTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frist_banner_web);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        initData();
        initToobar(mTitles);

        mWebView.loadUrl(mMUriWeb);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true); //支持js
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口

        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。 这个取决于setSupportZoom(), 若setSupportZoom(false)，则该WebView不可缩放，这个不管设置什么都不能缩放。
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件


    }

    private void initData() {
        Intent intent = getIntent();
        mMUriWeb = intent.getStringExtra("uriWeb");
        mTitles = intent.getStringExtra("titles");


    }


}
