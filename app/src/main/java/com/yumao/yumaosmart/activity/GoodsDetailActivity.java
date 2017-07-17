package com.yumao.yumaosmart.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.GoodsDetailPiAdapter;
import com.yumao.yumaosmart.adapter.GoodsDetailSpAdapter;
import com.yumao.yumaosmart.base.BaseItemActivity;
import com.yumao.yumaosmart.callback.GoodsDetailCallback;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.manager.LoginManager;
import com.yumao.yumaosmart.manager.UserInformationManager;
import com.yumao.yumaosmart.mode.GoodsDetailMode;
import com.yumao.yumaosmart.mode.User;
import com.yumao.yumaosmart.utils.GetNunberUtils;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.yumao.yumaosmart.widgit.AmountView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class GoodsDetailActivity extends BaseItemActivity {


    @BindView(R.id.tv_titlle)
    TextView mTvTitlle;
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.tv_activity_goods_detail_goodsname)
    TextView mTvActivityGoodsDetailGoodsname;
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
    @BindView(R.id.tv_activity_goods_goodsparamater)
    TextView mTvActivityGoodsGoodsparamater;
    @BindView(R.id.tv_activity_goods_line_code)
    TextView mTvActivityGoodsLineCode;
    @BindView(R.id.tv_activity_goods_work_duration)
    TextView mTvActivityGoodsWorkDuration;
    @BindView(R.id.tv_activity_goods_warehouse_address)
    TextView mTvActivityGoodsWarehouseAddress;
    @BindView(R.id.tv_activity_goods_full_description)
    TextView mTvActivityGoodsFullDescription;
    @BindView(R.id.rv_specifications)
    RecyclerView mRvSpecifications;
    @BindView(R.id.rv_pictures)
    RecyclerView mRvPictures;
    @BindView(R.id.tv_activity_goods_detail_address)
    TextView mTvActivityGoodsDetailAddress;
    @BindView(R.id.detail_activity_collection_iv)
    ImageView mDetailActivityCollectionIv;
    @BindView(R.id.detail_activity_forward_iv)
    ImageView mDetailActivityForwardIv;
    @BindView(R.id.detail_activity_shoppingcard_bt)
    Button mDetailActivityShoppingcardBt;
    @BindView(R.id.detail_activity_get_it_now_bt)
    Button mDetailActivityGetItNowBt;
    @BindView(R.id.activity_goods_detail)
    LinearLayout mActivityGoodsDetail;

    private AmountView mMAmountView;
    private List<String> mData = new ArrayList<>();

    private int mProductId;  //产品id
    private int vId;//门店id

    private List<String> mPictures;  //banner图集合
    private String mProductName ;    //产品名称
    private int resalePrice ;        //转售价格
    private int price ;              //销售价
    private int prodctCost ;         //成本价格

    private String mNumber ;             //条码 编号
    private String workDuration;      //工期
    private String wareHouseAddress ;  //发货仓
    private String fullDescription;   //描述
    private List<String> specificationsList ;//品种集合

    private List<String> mDetailStringList; //商品参数 中文key

    private int mStock_quantity;
    private int mAmount;

    private GoodsDetailSpAdapter mSpAdapter;
    private GoodsDetailPiAdapter mPiAdapter;

    private List<GoodsDetailMode.SpecificationsBean> mSpecifications;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        initToobar(getString(R.string.title_goods_detail));

        getGoodsId();
        initData();


    }

    //    获取商品ID
    private void getGoodsId() {
        ButterKnife.bind(this);
        Intent intent = getIntent();

        mProductId = intent.getIntExtra(Constant.PRODUCT_ID, -1);  //产品id

    }

    private void initData() {

        mPictures = new ArrayList<>();  //banner图集合
        specificationsList = new ArrayList<>();//品种集合
        mSpecifications = new ArrayList<>() ;

        //判断是否登录  ,获取门店id
        if (LoginManager.getInstance().isLoginState(UiUtilities.getContex())) {
            User userInformation = UserInformationManager.getInstance().getUserInformation();
            int id = userInformation.getVendor().getId();
            LogUtils.d("tag", "" + id);
            vId = id;
        } else {
            vId = 1;
        }

        OkHttpUtils
                .get()
                .url(Constant.BASE_URL + "vendors/" + vId + "/vendor-products/" + mProductId)
                .build()
                .execute(new GoodsDetailCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        //Toast.makeText(GoodsDetailActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                        LogUtils.d("详情页加载失败");

                    }

                    @Override
                    public void onResponse(GoodsDetailMode response, int id) {
                        LogUtils.d("详情页加载成功");
                        // initData(response);
                        mPictures = response.getPictures();
                        mSpecifications = response.getSpecifications();
                        mProductName = response.getName() ;    //产品名称
                        mProductId = response.getId();  //产品id
                        resalePrice = response.getResale_price();        //转售价格
                        price = response.getPrice();              //销售价
                        prodctCost =response.getProduct_cost();         //成本价格
                        workDuration = response.getWork_duration();      //工期


                        String detailString = mDetailString;

                        JSONObject object = null ;
                        mDetailStringList = new ArrayList<String>();  //存储字符串  "种水":"冰种"

                        for (int i =0 ;i< response.getSpecifications().size();i++){
                            try{
                                object = new JSONObject(detailString);
                                JSONArray specifications1 = object.optJSONArray("specifications");
                                String s = specifications1.optString(i);
                                StringBuffer sb = new StringBuffer(s);
                                String substring = sb.substring(1, s.length()-1);
                                LogUtils.d("tag",substring);
                                mDetailStringList.add(substring);
                            } catch (JSONException e){
                                e.printStackTrace();
                            }
                        }



                        if (response.getFull_description() ==null){
                            mTvActivityGoodsFullDescription.setVisibility(View.GONE);
                        }else {
                            fullDescription = response.getFull_description();   //描述
                            mTvActivityGoodsFullDescription.setText("描述:     "+fullDescription);
                        }

                        if (response.getWarehouse()==null){
                            mTvActivityGoodsWarehouseAddress.setVisibility(View.GONE);
                        }else{
                            wareHouseAddress = response.getWarehouse().getName();   //发货仓
                            mTvActivityGoodsWarehouseAddress.setText("货发仓:    "+wareHouseAddress);
                        }


                        //产品编号
                        int a = (int) Math.floor((float)(price - prodctCost) / price * 0.6 * 100);

                        String number = GetNunberUtils.getNumber(a);
                        LogUtils.d("编号:"+number);
                        int length = String.valueOf(mProductId).length();
                        StringBuffer stringBuffer = new StringBuffer();
                        if(length<8){
                            int i1 = 8 - length;
                            for(int j =0;j<i1;j++){
                                stringBuffer.append(0);
                            }
                            stringBuffer.append(mProductId);
                        }
                        //LogUtils.d("最终编号:"+number+stringBuffer);
                        mNumber = number + stringBuffer;
                            LogUtils.d("最终编号:"+mNumber);


                        initView();
                    }


                });

    }

    //加载详情页西方GridVLayout数据
    private void initView() {
        /*mGvActivityGoodsDetailShow.setNumColumns(2);

        mGvActivityGoodsDetailShow.setAdapter(new GoodsDetailActivityAdapter(this, R.layout.item_activity_goods_detail, mPictures));
        initDetailView();
        initAmountView();*/

        //头部轮播图
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(mPictures);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.RotateDown);
        //设置标题集合（当banner样式有显示title时）
        //mBanner.setBannerTitles(imageTitle);
        //设置轮播时间
        mBanner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();

        //设置标题
        mTvActivityGoodsDetailGoodsname.setText(mProductName);
        //设置产品价格
        if (resalePrice ==0){
            mTvActivityGoodsDetailGoodsprice.setText("价格: "+price+"元");
        }else {
            mTvActivityGoodsDetailGoodsprice.setText("价格: "+resalePrice+"元");
        }

        //设置总价
        if (resalePrice ==0){
            mTvActivityGoodsDetailTotalprice.setText(" "+price+"元");
        }else {
            mTvActivityGoodsDetailTotalprice.setText(" "+resalePrice+"元");
        }

       //条码
        mTvActivityGoodsLineCode.setText("条码:     "+mNumber);
        //工期
        mTvActivityGoodsWorkDuration.setText("工期:     "+workDuration);


        //参数的recyclerview
        // 竖直方向的网格样式，每行1个Item
        GridLayoutManager mLayoutManager = new GridLayoutManager(UiUtilities.getContex(), 1, OrientationHelper.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                //return super.canScrollVertically();
                // 直接禁止垂直滑动
                return false;
            }
        };
        //设置管理器
        mRvSpecifications.setLayoutManager(mLayoutManager);
        //设置adapter
        mSpAdapter = new GoodsDetailSpAdapter(UiUtilities.getContex(), mDetailStringList);

        mRvSpecifications.setAdapter(mSpAdapter);




        //图片的recyclerview
        // 竖直方向的网格样式，每行1个Item
        GridLayoutManager mLayoutManager2 = new GridLayoutManager(UiUtilities.getContex(), 1, OrientationHelper.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                //return super.canScrollVertically();
                // 直接禁止垂直滑动
                return false;
            }
        };
        //设置管理器
        mRvPictures.setLayoutManager(mLayoutManager2);
        //设置adapter
        mPiAdapter = new GoodsDetailPiAdapter(UiUtilities.getContex(), mPictures);

        mRvPictures.setAdapter(mPiAdapter);




    }
    private class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);
        }
    }

    //设置详情页其他数据
    private void initDetailView() {
        /*mThumb = mGoodsDetailMode.getThumb();
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
        // Picasso.with(this).load(mVendor.getLogo()).into( mIvActivityGoodsDetailStoresLogo);
        mTvActivityGoodsDetailNumInthestores.setText(String.valueOf(mVendor.getProduct_amount()));
        mVendorId = mVendor.getId();*/

    }

    //    初始化AmountView
    private void initAmountView() {
        mMAmountView = (AmountView) findViewById(R.id.amount_view);
        mMAmountView.setGoods_storage(50);

        mMAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {

               /* if (amount <= mStock_quantity) {
                    mAmount = amount;
                    mTvActivityGoodsDetailTotalprice.setText(String.valueOf(mGoodsDetailMode.getPrice() * mAmount));
                }*/

            }
        });
    }

    @OnClick({R.id.detail_activity_collection_iv,
            R.id.detail_activity_forward_iv,
            R.id.detail_activity_shoppingcard_bt,
            R.id.detail_activity_get_it_now_bt
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.detail_activity_collection_iv:
                break;
            case R.id.detail_activity_forward_iv:
                startActivity(new Intent(this, ConfirmOrderActivity.class));
                break;
            case R.id.detail_activity_shoppingcard_bt:
                if (mStock_quantity > 0 && mAmount > 0) {
                    add2shoppingcard();
                } else {
                    Toast.makeText(this, "库存不足加入购车失败", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.detail_activity_get_it_now_bt:
                /*if (!(mVendorId == 0)) {
                    Intent intent = new Intent();
                    intent.putExtra("venderId", mVendorId);
                    intent.setClass(this, BrandStoreActivity.class);
                    startActivity(intent);
                }
*/
                break;
        }
    }

    private void add2shoppingcard() {
        OkHttpUtils
                .post()
                .url("https://dist.yumao168.com/api/customers/" + UiUtilities.getUser().getId() + "/shopping-cart-items")
                .addHeader("X-API-TOKEN", "" + UiUtilities.getUser().getToken())
                .addParams("product_id", "" + mProductId)
                .addParams("store_id", "1")
                .addParams("quantity", "" + mAmount)
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
                        mStock_quantity = mStock_quantity - mAmount;
                    }
                });
    }
}
