package com.yumao.yumaosmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.activity.itemactivity.BrandStoreActivity;
import com.yumao.yumaosmart.adapter.GoodsDetailActivityAdapter;
import com.yumao.yumaosmart.adapter.vp_adapter.FirstViewPagerAdapter;
import com.yumao.yumaosmart.base.BaseItemActivity;
import com.yumao.yumaosmart.callback.GoodsDetailCallback;
import com.yumao.yumaosmart.mode.GoodsDetailMode;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.yumao.yumaosmart.widgit.AmountView;
import com.yumao.yumaosmart.widgit.MyGridView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class GoodsDetailActivity extends BaseItemActivity {

    @BindView(R.id.gv_activity_goods_detail_show)
    MyGridView mGvActivityGoodsDetailShow;
    @BindView(R.id.btn_activity_goods_detail_service)
    Button mBtnActivityGoodsDetailService;
    @BindView(R.id.btn_activity_goods_detail_shoppingcard)
    Button mBtnActivityGoodsDetailShoppingcard;
    @BindView(R.id.btn_activity_goods_detail_addto_shoppingcard)
    Button mBtnActivityGoodsDetailAddtoShoppingcard;
    @BindView(R.id.btn_activity_goods_detail_get_it_now)
    Button mBtnActivityGoodsDetailGetItNow;
    @BindView(R.id.btn_activity_goodsdetail_see_classify)
    Button mBtnActivityGoodsdetailSeeClassify;
    @BindView(R.id.btn_activity_goodsdetail_have_a_look)
    Button mBtnActivityGoodsdetailHaveALook;
    @BindView(R.id.tv_titlle)
    TextView mTvTitlle;
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.vp_activity_goods_detail_top)
    ViewPager mVpActivityGoodsDetailTop;
    @BindView(R.id.tv_activity_goods_detail_goodsname)
    TextView mTvActivityGoodsDetailGoodsname;
    @BindView(R.id.tv_activity_goods_detail_goodsprice_title)
    TextView mTvActivityGoodsDetailGoodspriceTitle;
    @BindView(R.id.tv_activity_goods_detail_goodsprice)
    TextView mTvActivityGoodsDetailGoodsprice;
    @BindView(R.id.tv_activity_goods_detail_totalprice_title)
    TextView mTvActivityGoodsDetailTotalpriceTitle;
    @BindView(R.id.tv_activity_goods_detail_totalprice)
    TextView mTvActivityGoodsDetailTotalprice;
    @BindView(R.id.tv_activity_goods_detail_totalprice_ifcontains_delivery)
    TextView mTvActivityGoodsDetailTotalpriceIfcontainsDelivery;
    @BindView(R.id.amount_view)
    AmountView mAmountView;
    @BindView(R.id.iv_activity_goods_detail_stores_logo)
    ImageView mIvActivityGoodsDetailStoresLogo;
    @BindView(R.id.iv_activity_goods_detail_stores_kind)
    ImageView mIvActivityGoodsDetailStoresKind;
    @BindView(R.id.tv_activity_goods_detail_num_inthestores)
    TextView mTvActivityGoodsDetailNumInthestores;
    @BindView(R.id.tv_activity_goods_detail_num_inthestores_title)
    TextView mTvActivityGoodsDetailNumInthestoresTitle;
    @BindView(R.id.tv_activity_goods_goodsparamater)
    TextView mTvActivityGoodsGoodsparamater;
    @BindView(R.id.tv_activity_goods_line_code_title)
    TextView mTvActivityGoodsLineCodeTitle;
    @BindView(R.id.tv_activity_goods_line_code)
    TextView mTvActivityGoodsLineCode;
    @BindView(R.id.tv_activity_goods_time_limit_title)
    TextView mTvActivityGoodsTimeLimitTitle;
    @BindView(R.id.tv_activity_goods_time_limit)
    TextView mTvActivityGoodsTimeLimit;
    @BindView(R.id.tv_activity_goods_paramater_brand_title)
    TextView mTvActivityGoodsParamaterBrandTitle;
    @BindView(R.id.tv_activity_goods_paramater_brand)
    TextView mTvActivityGoodsParamaterBrand;
    @BindView(R.id.tv_activity_goods_paramater_description_title)
    TextView mTvActivityGoodsParamaterDescriptionTitle;
    @BindView(R.id.tv_activity_goods_paramater_description)
    TextView mTvActivityGoodsParamaterDescription;
    @BindView(R.id.activity_goods_detail)
    LinearLayout mActivityGoodsDetail;
    @BindView(R.id.tv_activity_goods_detail_address)
    TextView mActivityGoodsDetailAddress;

    private AmountView mMAmountView;
    private List<String> mData = new ArrayList<>();
    private int mGoodsid;

    private List<String> mPictures;
    private GoodsDetailMode mGoodsDetailMode;
    private String mThumb;
    private ImageView mImageView;
    private ViewGroup.LayoutParams mLayoutParams;
    private List<ImageView> mViews=new ArrayList<>();;


    private String mS;
    private List<?> mManufacturers;
    private Map map;
    private StringBuilder mStringBuilder;
    private String mStr;
    private GoodsDetailMode.VendorBean mVendor;
    private int mVendorId;
    private int mStock_quantity;
    private int mAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        initToobar(getString(R.string.title_goods_detail));

        getGoodsId();
        init();


    }
//    获取商品ID
    private void getGoodsId() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mGoodsid = extras.getInt("goodsid");

    }

    private void initData(GoodsDetailMode goodsDetailMode) {
        mGoodsDetailMode = goodsDetailMode;
        mPictures = mGoodsDetailMode.getPictures();
        mStock_quantity = mGoodsDetailMode.getStock_quantity();
        initView();
    }

    private void init() {

        OkHttpUtils
                .get()
                .url("https://test-dist.yumao168.com/api/products/" + mGoodsid)

                .build()
                .execute(new GoodsDetailCallback() {


                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(GoodsDetailActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(GoodsDetailMode response, int id) {

                        initData(response);
                    }


                });
    }
//加载详情页西方GridVLayout数据
    private void initView() {
        mGvActivityGoodsDetailShow.setNumColumns(2);

        mGvActivityGoodsDetailShow.setAdapter(new GoodsDetailActivityAdapter(this, R.layout.item_activity_goods_detail, mPictures));
        initDetailView();
        initAmountView();
    }

    //设置详情页其他数据
    private void initDetailView() {
        mThumb = mGoodsDetailMode.getThumb();
        for (int i = 0; i < mPictures.size(); i++) {
            mImageView = new ImageView(this);


            mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mImageView.setLayoutParams(mLayoutParams);
                 mViews.add(mImageView);
                Picasso.with(this).load(mPictures.get(i)).into(mImageView);
        }
        mTvActivityGoodsDetailTotalprice.setText(String.valueOf(mGoodsDetailMode.getPrice()));
        mVpActivityGoodsDetailTop.setAdapter(new FirstViewPagerAdapter(mViews));
        mTvActivityGoodsDetailGoodsname.setText(mGoodsDetailMode.getName());
        mTvActivityGoodsDetailGoodsprice.setText(String.valueOf(mGoodsDetailMode.getPrice()));
        mTvActivityGoodsLineCode.setText(mGoodsDetailMode.getSku());
        mTvActivityGoodsTimeLimit.setText(mGoodsDetailMode.getWork_duration());
//        mManufacturers = (List<String>) mGoodsDetailMode.getManufacturers();
        mManufacturers = mGoodsDetailMode.getManufacturers();
        if (!(mManufacturers == null)) {
            mStringBuilder = new StringBuilder();
            for (int i = 0; i < mManufacturers.size(); i++) {
                map = (Map) mManufacturers.get(i);
                mStr = (String) map.get("name");
                mStringBuilder.append(mStr);
            }
            mTvActivityGoodsParamaterBrand.setText(mS);
        } else {
            mTvActivityGoodsParamaterBrand.setText(R.string.brand_yumaopintai);
        }
        mTvActivityGoodsParamaterDescription.setText(mGoodsDetailMode.getShort_description());
        mVendor = mGoodsDetailMode.getVendor();
        mActivityGoodsDetailAddress.setText(mVendor.getAddress());
        Picasso.with(this).load(mVendor.getLogo()).into( mIvActivityGoodsDetailStoresLogo);
        mTvActivityGoodsDetailNumInthestores.setText(String.valueOf(mVendor.getProduct_amount()));
        mVendorId = mVendor.getId();

    }
//    初始化AmountView
    private void initAmountView() {
        mMAmountView = (AmountView) findViewById(R.id.amount_view);
        mMAmountView.setGoods_storage(50);

        mMAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {

                if (amount <= mStock_quantity) {
                    mAmount=amount;
                    mTvActivityGoodsDetailTotalprice.setText(String.valueOf(mGoodsDetailMode.getPrice()*mAmount));
                }

            }
        });
    }

    @OnClick({R.id.btn_activity_goodsdetail_see_classify,
            R.id.btn_activity_goodsdetail_have_a_look,
            R.id.btn_activity_goods_detail_addto_shoppingcard,
            R.id.btn_activity_goods_detail_get_it_now
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_activity_goodsdetail_see_classify:
                break;
            case R.id.btn_activity_goods_detail_get_it_now:
                    startActivity(new Intent(this,ConfirmOrderActivity.class));
                break;
            case R.id.btn_activity_goods_detail_addto_shoppingcard:
                if (mStock_quantity > 0&&mAmount>0) {
                    add2shoppingcard();
                } else {
                    Toast.makeText(this, "库存不足加入购车失败", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_activity_goodsdetail_have_a_look:
                if (!(mVendorId== 0)) {
                    Intent intent = new Intent();
                    intent.putExtra("venderId", mVendorId);
                    intent.setClass(this, BrandStoreActivity.class);
                  startActivity(intent);
                }

                break;
        }
    }

    private void add2shoppingcard() {
        OkHttpUtils
                .post()
                .url("https://dist.yumao168.com/api/customers/"+ UiUtilities.getUser().getId()+"/shopping-cart-items")
                .addHeader("X-API-TOKEN",""+UiUtilities.getUser().getToken())
                .addParams("product_id",""+mGoodsid)
                .addParams("store_id","1")
                .addParams("quantity",""+mAmount)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(GoodsDetailActivity.this, "加入购物车失败", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        Toast.makeText(GoodsDetailActivity.this, "加入购物车成功", Toast.LENGTH_SHORT).show();
                            mStock_quantity=mStock_quantity-mAmount;
                    }
                });
    }
}
