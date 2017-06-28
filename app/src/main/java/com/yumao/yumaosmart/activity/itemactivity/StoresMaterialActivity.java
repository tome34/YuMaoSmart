package com.yumao.yumaosmart.activity.itemactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseItemActivity;
import com.yumao.yumaosmart.bean.StoresMaterialBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoresMaterialActivity extends BaseItemActivity {

    @BindView(R.id.tv_activity_stores_material_stores_name)
    TextView mTvActivityStoresMaterialStoresName;

    @BindView(R.id.tv_activity_stores_material_stores_phone)
    TextView mTvActivityStoresMaterialStoresPhone;

    @BindView(R.id.tv_activity_stores_material_stores_address)
    TextView mTvActivityStoresMaterialStoresAddress;
    @BindView(R.id.rbtn_activity_stores_material_zhubao)
    RadioButton mRbtnActivityStoresMaterialZhubao;
    @BindView(R.id.rbtn_activity_stores_material_zuanshi)
    RadioButton mRbtnActivityStoresMaterialZuanshi;
    @BindView(R.id.activity_stores_material)
    LinearLayout mActivityStoresMaterial;
    private List<StoresMaterialBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores_material);
        ButterKnife.bind(this);
        initToobar(getString(R.string.title_stores_material));
        init();
        initData();
        initView();
    }

    private void initView() {
    }

    private void initData() {
        mList = new ArrayList<>();
        StoresMaterialBean storesMaterialBean = new StoresMaterialBean();
        storesMaterialBean.setShowZhuanShi(true);
        storesMaterialBean.setShowZhuBao(true);
        storesMaterialBean.setStoresAddress("深圳市罗湖区田贝四路万山珠宝园深圳市罗湖区田贝四路");
        storesMaterialBean.setStoresPhone("15547544578");
        storesMaterialBean.setStoresName("玉猫商城");
    }

    private void init() {
    }

    @OnClick({R.id.rbtn_activity_stores_material_zhubao, R.id.rbtn_activity_stores_material_zuanshi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rbtn_activity_stores_material_zhubao:
                break;
            case R.id.rbtn_activity_stores_material_zuanshi:
                break;
        }
    }
}
