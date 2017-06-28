package com.yumao.yumaosmart.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.fragment.ShenHeShiBaiFragment;
import com.yumao.yumaosmart.fragment.ShenHeZhongFragment;
import com.yumao.yumaosmart.fragment.WeiShangJiaFragment;
import com.yumao.yumaosmart.fragment.YiShangJiaFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpLoadActivity extends AppCompatActivity implements TabHost.OnTabChangeListener {

    @BindView(R.id.iv_activity_upload_back)
    ImageView mIvActivityUploadBack;
    @BindView(R.id.btn_activity_upload_add_goods)
    Button mBtnActivityUploadAddGoods;
    @BindView(android.R.id.tabcontent)
    FrameLayout mTabcontent;
    @BindView(android.R.id.tabs)
    TabWidget mTabs;
    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabhost;
    @BindView(R.id.activity_up_load)
    LinearLayout mActivityUpLoad;
    private  List<Fragment> mList;
    private FragmentManager mFragmentManager;
   private LayoutInflater mInflater;
    private String[] textArray={"已上架","未上架","审核中","审核失败"};
    private String[] textNumArray={"155","156","157","158"};
    private TabHost.TabSpec mTabSpec;
    public int tabDrableArray[] = {R.drawable.nav_first, R.drawable.nav_classify, R.drawable.nav_shopping, R.drawable.nav_personnal};
    private View mTabRootView;
    private TextView mTabView;
    private Drawable mDrawableTop;
    private TextView mTvSmallNum;
    private YiShangJiaFragment mMYiShangJiaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_load);
        ButterKnife.bind(this);
        initStatusBar();
        init();
        initData();
        initView();
        initListenner();

    }

    private void initView() {

    }

    private void initData() {
    }


    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    @OnClick({R.id.iv_activity_upload_back, R.id.btn_activity_upload_add_goods})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_activity_upload_back:
                finish();
                break;
            case R.id.btn_activity_upload_add_goods:
                startActivity(new Intent(this, GoodsStandardActivity.class));
                break;
        }
    }
    private void initListenner() {

        mTabhost.setup(this, mFragmentManager, android.R.id.tabcontent);
        mTabhost.setOnTabChangedListener(this);
        for (int i = 0; i < textArray.length; i++) {

            mTabSpec = mTabhost.newTabSpec(textArray[i]).setIndicator(getTabView(i));

            mTabhost.addTab(mTabSpec, mList.get(i).getClass(), null);
        }
    }

    private View getTabView(int i) {

        mTabRootView = mInflater.inflate(R.layout.tab_item_upload, null);
        mTabView = (TextView) mTabRootView.findViewById(R.id.tv_activity_upload_tab);
        mTvSmallNum = (TextView) mTabRootView.findViewById(R.id.tv_activity_upload_small_number);

        if (i == 0) {
            mTabView.setSelected(true);
        }
        mTabView.setText(textArray[i]);
        mTvSmallNum.setText(textNumArray[i]);


        return mTabRootView;
    }


    //    初始化数据
    private void init() {
        mList = new ArrayList<>();
        mFragmentManager=getSupportFragmentManager();
        mMYiShangJiaFragment = new YiShangJiaFragment();

        mList.add(mMYiShangJiaFragment);
        mList.add(new WeiShangJiaFragment());
        mList.add(new ShenHeZhongFragment());
        mList.add(new ShenHeShiBaiFragment());

        mInflater = LayoutInflater.from(this);


    }

    @Override
    public void onTabChanged(String tabId) {

    }
}
