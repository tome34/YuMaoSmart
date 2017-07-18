package com.yumao.yumaosmart.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
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
import cn.sharesdk.onekeyshare.OnekeyShare;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
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
    @BindView(R.id.details_icon_share)
    ImageView mDetailsIconShare;
    @BindView(R.id.tv_activity_goods_full_description_title)
    TextView mTvActivityGoodsFullDescriptionTitle;
    @BindView(R.id.tv_activity_goods_full_description_layout)
    LinearLayout mTvActivityGoodsFullDescriptionLayout;
    @BindView(R.id.videoplayer)
    JCVideoPlayerStandard mVideoplayer;



    private AmountView mMAmountView;
    private List<String> mData = new ArrayList<>();

    private int mProductId;  //产品id
    private String mVendorName;  //平台名字

    private int vId;//门店id

    private List<String> mPictures;  //banner图集合
    private String mProductName;    //产品名称
    private int resalePrice;        //转售价格
    private int price;              //销售价
    private int prodctCost;         //成本价格

    private String mNumber;             //条码 编号
    private String workDuration;      //工期
    private String wareHouseAddress;  //发货仓
    private String fullDescription;   //描述
    private List<String> specificationsList;//品种集合

    private List<String> mDetailStringList; //商品参数 中文key

    private int mStock_quantity;
    private int mAmount;

    private GoodsDetailSpAdapter mSpAdapter;
    private GoodsDetailPiAdapter mPiAdapter;

    StandardGSYVideoPlayer gsyVideoPlayer;

    private List<GoodsDetailMode.SpecificationsBean> mSpecifications;
    private String mMedias;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;
    private boolean mMediasFlas = false;
    private String mAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        initToobar(getString(R.string.title_goods_detail));

        initHeight();
        getGoodsId();
        initData();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
         mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        dm = UiUtilities.getContex().getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels; // 屏幕宽（像素，如：3200px）


        ViewGroup.LayoutParams params = mBanner.getLayoutParams();
        params.width = screenWidth;
        params.height = screenWidth;
            LogUtils.d("长度:"+screenWidth);

        mBanner.setLayoutParams(params);

        ViewGroup.LayoutParams paramjc = mVideoplayer.getLayoutParams();
        paramjc.width = screenWidth;
        paramjc.height = screenWidth;
        LogUtils.d("长度:"+screenWidth);

        mVideoplayer.setLayoutParams(paramjc);

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
        mSpecifications = new ArrayList<>();

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
                        mProductName = response.getName();    //产品名称
                        mVendorName = response.getVendor().getName(); //获取品台的名字
                        mProductId = response.getId();  //产品id
                        resalePrice = response.getResale_price();        //转售价格
                        price = response.getPrice();              //销售价
                        prodctCost = response.getProduct_cost();         //成本价格
                        workDuration = response.getWork_duration();      //工期

                        //详情页底部地址
                        mAddress = response.getVendor().getAddress();

                        if (response.medias == null) {                     //判断视频是否为空
                            mVideoplayer.setVisibility(View.GONE);
                            mBanner.setVisibility(View.VISIBLE);
                        } else {
                            mBanner.setVisibility(View.GONE);
                            mVideoplayer.setVisibility(View.VISIBLE);
                            mMedias = response.medias.get(0);  //视频MP4
                            mMediasFlas = true;
                        }


                        String detailString = mDetailString;

                        JSONObject object = null;
                        mDetailStringList = new ArrayList<String>();  //存储字符串  "种水":"冰种"

                        for (int i = 0; i < response.getSpecifications().size(); i++) {
                            try {
                                object = new JSONObject(detailString);
                                JSONArray specifications1 = object.optJSONArray("specifications");
                                String s = specifications1.optString(i);
                                StringBuffer sb = new StringBuffer(s);
                                String substring = sb.substring(1, s.length() - 1);
                                LogUtils.d("tag", substring);
                                mDetailStringList.add(substring);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                        if (response.getFull_description() == null) {
                            mTvActivityGoodsFullDescriptionLayout.setVisibility(View.GONE);
                        } else {
                            fullDescription = response.getFull_description();   //描述
                            mTvActivityGoodsFullDescription.setText(fullDescription);
                        }

                        if (response.getWarehouse() == null) {
                            mTvActivityGoodsWarehouseAddress.setVisibility(View.GONE);
                        } else {
                            wareHouseAddress = response.getWarehouse().getName();   //发货仓
                            mTvActivityGoodsWarehouseAddress.setText("发货仓:    " + wareHouseAddress);
                        }


                        //产品编号
                        int a = (int) Math.floor((float) (price - prodctCost) / price * 0.6 * 100);

                        String number = GetNunberUtils.getNumber(a);
                        LogUtils.d("编号:" + number);
                        int length = String.valueOf(mProductId).length();
                        StringBuffer stringBuffer = new StringBuffer();
                        if (length < 8) {
                            int i1 = 8 - length;
                            for (int j = 0; j < i1; j++) {
                                stringBuffer.append(0);
                            }
                            stringBuffer.append(mProductId);
                        }
                        //LogUtils.d("最终编号:"+number+stringBuffer);
                        mNumber = number + stringBuffer;
                        LogUtils.d("最终编号:" + mNumber);


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
        for (int i = 0; i < mPictures.size(); i++) {
            LogUtils.d("tag", "轮播图:" + mPictures.get(i));
        }
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

        if (mMediasFlas) {
            //设置视频播放
            mediasPlayer(mMedias);
            mMediasFlas = false;
        }


        //设置标题
        mTvActivityGoodsDetailGoodsname.setText(mProductName);
        //设置产品价格
        if (resalePrice == 0) {
            mTvActivityGoodsDetailGoodsprice.setText("价格: " + price + "元");
        } else {
            mTvActivityGoodsDetailGoodsprice.setText("价格: " + resalePrice + "元");
        }

        //设置总价
        if (resalePrice == 0) {
            mTvActivityGoodsDetailTotalprice.setText(" " + price + "元");
        } else {
            mTvActivityGoodsDetailTotalprice.setText(" " + resalePrice + "元");
        }

        //条码
        mTvActivityGoodsLineCode.setText("条码:     " + mNumber);
        //工期
        mTvActivityGoodsWorkDuration.setText("工期:     " + workDuration);

        //设置底部地址
        mTvActivityGoodsDetailAddress.setText(mAddress + "玉猫平台提供支持");


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

    //视频播放
    private void mediasPlayer(String medias) {
        String url = medias;//视频的uri


        mVideoplayer.setUp(url, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, mProductName);
        // mVideoplayer.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");
        //设置图片
        Picasso.with(this)
                .load(Uri.parse(mPictures.get(0)))
                .placeholder(R.mipmap.details_icon_play)
                .into(mVideoplayer.thumbImageView);
        LogUtils.d("视频图:" + mPictures.get(0));

    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("GoodsDetail Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient.connect();
        AppIndex.AppIndexApi.start(mClient, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(mClient, getIndexApiAction());
        mClient.disconnect();
    }


    private class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Picasso.with(context).load(Uri.parse((String) path)).placeholder(R.mipmap.details_icon_bj).into(imageView);
            //Glide.with(context).load((String) path).into(imageView);
            LogUtils.d("图片:" + path);
            LogUtils.d("上下文context:" + context);
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
            R.id.detail_activity_get_it_now_bt,
            R.id.details_icon_share
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

            case R.id.details_icon_share:
                //Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();

                showShare();
                break;
        }
    }

    private void showShare() {

        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(mVendorName + "-" + mProductName);
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://mall.yumaozhubao.com/product/" + mProductId + ".html");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("mall.yumaozhubao.com");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/123.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
       /* if (mPictures.get(0) ==null){
            oks.setImagePath(Uri.parse("android.resource://com.yumao.yumaosmart/mipmap/details_icon_kf.png").toString());
        }else{
            oks.setImageUrl(mPictures.get(1));
        }*/

        //oks.setima
        oks.setUrl("http://mall.yumaozhubao.com/product/" + mProductId + ".html");
        oks.setImagePath(Uri.parse("android.resource://com.yumao.yumaosmart/mipmap/details_icon_kf.png").toString());
        //oks.setImagePath(mPictures.get(0));
        oks.setImageUrl("http://mall.yumaozhubao.com/product/" + mProductId + ".html");
        //LogUtils.d("分享:"+mProductId +" "+mPictures.get(0));
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        //oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        //oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        //oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
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
