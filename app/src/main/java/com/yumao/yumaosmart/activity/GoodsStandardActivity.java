package com.yumao.yumaosmart.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseItemActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsStandardActivity extends BaseItemActivity {

    @BindView(R.id.tv_titlle)
    TextView mTvTitlle;
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.iv_activity_goods_standard_add_big)
    ImageView mIvActivityGoodsStandardAddBig;
    @BindView(R.id.iv_activity_goods_standard_add)
    ImageView mIvActivityGoodsStandardAdd;
    @BindView(R.id.iv_activity_goods_standard_delete)
    ImageView mIvActivityGoodsStandardDelete;
    @BindView(R.id.vp_activity_goods_standard)
    ViewPager mVpActivityGoodsStandard;
    @BindView(R.id.tv_activity_goods_standard_nametitle)
    TextView mTvActivityGoodsStandardNametitle;
    @BindView(R.id.et_activity_goods_standard_name)
    EditText mEtActivityGoodsStandardName;
    @BindView(R.id.et_activity_goods_standard_detail)
    EditText mEtActivityGoodsStandardDetail;
    @BindView(R.id.tv_activity_goods_standard_serialnum_title)
    TextView mTvActivityGoodsStandardSerialnumTitle;
    @BindView(R.id.et_activity_goods_standard_serialnum)
    EditText mEtActivityGoodsStandardSerialnum;
    @BindView(R.id.tv_activity_goods_standard_pricetitle)
    TextView mTvActivityGoodsStandardPricetitle;
    @BindView(R.id.et_activity_goods_standard_price)
    EditText mEtActivityGoodsStandardPrice;
    @BindView(R.id.tv_activity_goods_standard_repertory_title)
    TextView mTvActivityGoodsStandardRepertoryTitle;
    @BindView(R.id.et_activity_goods_standard_repertory)
    EditText mEtActivityGoodsStandardRepertory;
    @BindView(R.id.iv_activity_goods_standard_classify_one)
    ImageView mIvActivityGoodsStandardClassifyOne;
    @BindView(R.id.tv_goods_standard_classify_infoone)
    TextView mTvGoodsStandardClassifyInfoone;
    @BindView(R.id.iv_activity_goods_standard_classify_two)
    ImageView mIvActivityGoodsStandardClassifyTwo;
    @BindView(R.id.tv_goods_standard_classify_infotwo)
    TextView mTvGoodsStandardClassifyInfotwo;
    @BindView(R.id.activity_goods_stard)
    LinearLayout mActivityGoodsStard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_stard);
        ButterKnife.bind(this);
        initToobar(getString(R.string.title_publish_goods));

    }

    @OnClick({R.id.iv_activity_goods_standard_add_big, R.id.iv_activity_goods_standard_add, R.id.iv_activity_goods_standard_delete, R.id.vp_activity_goods_standard, R.id.iv_activity_goods_standard_classify_one, R.id.iv_activity_goods_standard_classify_two})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_activity_goods_standard_add_big:
                break;
            case R.id.iv_activity_goods_standard_add:
                break;
            case R.id.iv_activity_goods_standard_delete:
                break;
            case R.id.vp_activity_goods_standard:
                break;
            case R.id.iv_activity_goods_standard_classify_one:
                break;
            case R.id.iv_activity_goods_standard_classify_two:
                break;
        }
    }
}
