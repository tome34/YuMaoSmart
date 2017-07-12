package com.yumao.yumaosmart.activity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.MyBaseFragmentAdapter;
import com.yumao.yumaosmart.fragment.ClassifyFragment;
import com.yumao.yumaosmart.fragment.FirstPagerFragment;
import com.yumao.yumaosmart.fragment.PersonalCenterFragment;
import com.yumao.yumaosmart.fragment.ShoppingCartFragment;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.UiUtilities;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener {


    @BindView(android.R.id.tabs)
    TabWidget mTabs;
    @BindView(android.R.id.tabcontent)
    FrameLayout mTabcontent;
    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabhost;


    private List<Fragment> mList;

    private FragmentManager mFragmentManager;
    private MyBaseFragmentAdapter mFragmentAdapter;

    public String[] textArray = {"首页", "分类", "购物车", "个人中心"};
    private LayoutInflater mInflater;
    public int tabDrableArray[] = {R.drawable.nav_first, R.drawable.nav_classify, R.drawable.nav_shopping, R.drawable.nav_personnal};
    private TabHost.TabSpec mTabSpec;
    private View mTabRootView;
    private TextView mTabView;
    private Drawable mDrawableTop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }*/
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
            LogUtils.d("id="+ UiUtilities.getUser().getId());
                LogUtils.d("toke="+UiUtilities.getUser().getToken());
            /*LogUtils.d("venderid="+UiUtilities.getUser().getVendor().getId());
                    LogUtils.d("gender="+UiUtilities.getUser().getGender());*/
        initStatusBar();
        init();
        initListenner();

        //从微信界面跳转到个人中心界面,activity跳转fragment


    }

    public void initStatusBar() {
        //设置状态栏为透明

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }


    }

    //透明状态栏无效果


    private void initListenner() {


        mTabhost.setup(this, mFragmentManager, android.R.id.tabcontent);
       mTabhost.setOnTabChangedListener(this);
        for (int i = 0; i < textArray.length; i++) {

            mTabSpec = mTabhost.newTabSpec(textArray[i]).setIndicator(getTabView(i));

            mTabhost.addTab(mTabSpec, mList.get(i).getClass(), null);
        }
    }

    private View getTabView(int i) {

        mTabRootView = mInflater.inflate(R.layout.tab_item, null);
        mTabView = (TextView) mTabRootView.findViewById(R.id.id_tab);


        if (i == 0) {
            mTabView.setSelected(true);
        }
        mTabView.setText(textArray[i]);
        mDrawableTop = getResources().getDrawable(tabDrableArray[i]);
        mDrawableTop.setBounds(0,0,mDrawableTop.getMinimumWidth(),mDrawableTop.getMinimumHeight());
        mTabView.setCompoundDrawables(null, mDrawableTop, null, null);

        return mTabRootView;
    }


    //    初始化数据
    private void init() {
        mList = new ArrayList<>();
        mList.add(new FirstPagerFragment());
        mList.add(new ClassifyFragment());
        mList.add(new ShoppingCartFragment());
        mList.add(new PersonalCenterFragment());

        mFragmentManager = getSupportFragmentManager();
       // mFragmentManager.beginTransaction().add().commit();
        mInflater = LayoutInflater.from(this);


    }


    @Override
    public void onTabChanged(String tabId) {


    }
}



