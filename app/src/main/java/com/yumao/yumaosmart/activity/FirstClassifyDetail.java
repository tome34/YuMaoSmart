package com.yumao.yumaosmart.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.LanMujiangXuanAdapter;
import com.yumao.yumaosmart.callback.LanMuJingXuanCallback;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.inter.OnItemClickListener;
import com.yumao.yumaosmart.manager.LoginManager;
import com.yumao.yumaosmart.manager.UserInformationManager;
import com.yumao.yumaosmart.mode.LanMuJingXuanBean;
import com.yumao.yumaosmart.mode.User;
import com.yumao.yumaosmart.utils.GetNunberUtils;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.yumao.yumaosmart.widget.CustomRadioGroup;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class FirstClassifyDetail extends AppCompatActivity {


    @BindView(R.id.activity_first_classify_detail)
    LinearLayout mActivityFirstClassifyDetail;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.btn_first_classify_detail_update)
    Button mBtnFirstClassifyDetailUpdate;
    @BindView(R.id.btn_first_classify_detail_price)
    Button mBtnFirstClassifyDetailPrice;
    @BindView(R.id.btn_first_classify_detail_choose)
    Button mBtnFirstClassifyDetailChoose;

    @BindView(R.id.smartLayout)
    SmartRefreshLayout mSmartLayout;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.search_et_input)
    LinearLayout mSearchEtInput;
    @BindView(R.id.classify_back)
    ImageView mClassifyBack;
    @BindView(R.id.search_empty_layout)
    LinearLayout mSearchEmptyLayout;
    @BindView(R.id.liebiao_layout)
    LinearLayout mLiebiaoLayout;
    @BindView(R.id.classify_detail_iv)
    ImageView mClassifyDetailIv;
    @BindView(R.id.text)
    TextView mText;
    @BindView(R.id.first_classify_detail_layout)
    LinearLayout mFirstClassifyDetailLayout;
    @BindView(R.id.customRadioGroup)
    CustomRadioGroup mCustomRadioGroup;
    @BindView(R.id.et_search)
    TextView mEtSearch;
    @BindView(R.id.search_et_input2)
    LinearLayout mSearchEtInput2;


    private int mCategoryId;
    private int mVid;
    private Intent mIntent;

    private int page;

    private String mSortBy = "id";  //筛选,默认id
    private String mOrder = "DESC"; //筛选,默认降序
    private int mSX_order = 1;  //筛选:降序,默认为1 ;

    List<String> mImageList; //栏目精选图片集合
    List<String> mTiltisList; //栏目精选标题集合
    List<Integer> mProductIdList;     //产品id
    List<Integer> mPriceList;  //产品价格
    List<Integer> mProductCostList; //产品的成本价
    List<Integer> mResalePriceList; //产品的转售价格,以转卖价优先
    List<String> mNumberList;//产品编号

    Map<String, String> mMapParams;   //网络请求参数

    //判断从哪里跳转过来的,有三种情况
    private int CATEGORY_TO = 1;    //分类跳转
    private int HOME_SEARCH = 2;   //从首页搜索跳转
    private int CATEGORY_SEARCH = 3; //分类搜索跳转

    private String mSearchResult;


    private LanMujiangXuanAdapter mAdapter;
    private int mIntExtraTage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_classify_detail);
        ButterKnife.bind(this);

        //下拉加载
        Smartrefresh();

        //触发自动加载
        mSmartLayout.autoRefresh(10);

    }

    //下拉刷新
    private void Smartrefresh() {

        mSmartLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //refreshlayout.finishRefresh(2000);
                UpDataView();

            }
        });
        mSmartLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(1300);
                UpDataLoadmore();
            }
        });

    }


    //刷新数据
    private void UpDataView() {
        initStatusBar();
        setCategoryId();

    }


    //初始化mPostion和mCategoryId
    private void setCategoryId() {

        mLiebiaoLayout.setVisibility(View.VISIBLE);
        mSearchEmptyLayout.setVisibility(View.GONE);

        page = 1;      //页数,默认第一页

        if (mSX_order == 2) {
            mOrder = "ASC";
        }

        //判断是否登录
        if (LoginManager.getInstance().isLoginState(UiUtilities.getContex())) {
            User userInformation = UserInformationManager.getInstance().getUserInformation();
            int id = userInformation.getVendor().getId();
            LogUtils.d("tag", "" + id);
            mVid = id;
        } else {
            mVid = 1;
        }

        Intent intent = getIntent();
        mIntExtraTage = intent.getIntExtra(Constant.SEARCH_TAGE, -1);

        LogUtils.d("tag", "搜索标注:" + mIntExtraTage);

        if (mIntExtraTage == CATEGORY_TO) { //从分类跳转1

            mSearchEtInput.setVisibility(View.VISIBLE);
            mSearchEtInput2.setVisibility(View.GONE);  //有编辑框

            mCategoryId = intent.getIntExtra(Constant.CATEGORY_ID, -1);
            //mVid = intent.getIntExtra("vid", 1);
            //LogUtils.d("id:" + mCategoryId + " " + mVid);

            mMapParams = new HashMap<>();  //网络请求参数集合
            mMapParams.put("category_id", mCategoryId + "");
            mMapParams.put("sort_by", mSortBy);
            mMapParams.put("page", String.valueOf(page));
            mMapParams.put("order", mOrder);

        } else if (mIntExtraTage == HOME_SEARCH) {  //从首页搜索2
            //搜索结果
            mSearchResult = intent.getStringExtra(Constant.SEARCH_RESULT);

            mSearchEtInput.setVisibility(View.GONE);
            mSearchEtInput2.setVisibility(View.VISIBLE);
            mEtSearch.setText(mSearchResult);

            mMapParams = new HashMap<>();  //网络请求参数集合
            mMapParams.put("keyword", mSearchResult);
            mMapParams.put("sort_by", mSortBy);
            mMapParams.put("page", String.valueOf(page));
            mMapParams.put("order", mOrder);

        } else if (mIntExtraTage == CATEGORY_SEARCH) {  //从分类搜索3
            //搜索结果
            mSearchResult = intent.getStringExtra(Constant.SEARCH_RESULT);
            //分类id
            mCategoryId = intent.getIntExtra(Constant.CATEGORY_ID, -1);
            LogUtils.d("分类的id:" + mCategoryId);

            mSearchEtInput.setVisibility(View.GONE);
            mSearchEtInput2.setVisibility(View.VISIBLE);
            mEtSearch.setText(mSearchResult);

            mMapParams = new HashMap<>();  //网络请求参数集合
            mMapParams.put("keyword", mSearchResult);
            mMapParams.put("category_id", mCategoryId + "");
            mMapParams.put("sort_by", mSortBy);
            mMapParams.put("page", String.valueOf(page));
            mMapParams.put("order", mOrder);

        } else {
            LogUtils.d("跳转有问题了");
        }

        ClassifyData();  //加载数据
        LogUtils.d("tag", "vid:" + mVid + ",分类id:" + mCategoryId + ",keyword:" + mSearchResult);
    }

    private void ClassifyData() {


        mSmartLayout.setLoadmoreFinished(false);

        //mSortBy = "id"; //筛选类型
        mImageList = new ArrayList<>(); //栏目精选图片集合
        mTiltisList = new ArrayList<>(); //栏目精选标题集合
        mProductIdList = new ArrayList<>();     //产品id
        mPriceList = new ArrayList<>();  //产品价格
        mProductCostList = new ArrayList<>(); //产品的成本价
        mResalePriceList = new ArrayList<>(); //产品的转售价格,以转卖价优先
        mNumberList = new ArrayList<>(); //产品的编号


        OkHttpUtils
                .get()
                .url(Constant.BASE_URL + "vendors/" + mVid + "/vendor-products")  //栏目二级详情页
                .params(mMapParams)
                .build()
                .execute(new LanMuJingXuanCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("列表页失败");
                        //mRadiogroupFirstClassifyDetail.setVisibility(View.GONE);
                        mSmartLayout.finishRefresh(1000);
                        mLiebiaoLayout.setVisibility(View.GONE);
                        mSearchEmptyLayout.setVisibility(View.VISIBLE);


                    }

                    @Override
                    public void onResponse(List<LanMuJingXuanBean> response, int id) {
                        LogUtils.d("列表页成功");
                        for (int i = 0; i < response.size(); i++) {

                            mImageList.add(response.get(i).getThumb()); //图片
                            mTiltisList.add(response.get(i).getName());
                            mProductIdList.add(response.get(i).getId());
                            mPriceList.add(response.get(i).getPrice());
                            mProductCostList.add(response.get(i).getProduct_cost());
                            mResalePriceList.add(response.get(i).getResale_price());

                            //产品编号
                            int a = (int) Math.floor((float) (mPriceList.get(i) - mProductCostList.get(i)) / mPriceList.get(i) * 0.6 * 100);

                            String number = GetNunberUtils.getNumber(a);
                            LogUtils.d("编号:" + number);
                            int length = String.valueOf(mProductIdList.get(i)).length();
                            StringBuffer stringBuffer = new StringBuffer();
                            if (length < 8) {
                                int i1 = 8 - length;
                                for (int j = 0; j < i1; j++) {
                                    stringBuffer.append(0);
                                }
                                stringBuffer.append(mProductIdList.get(i));
                            }
                            LogUtils.d("最终编号:" + number + stringBuffer);
                            String s = number + stringBuffer;

                            //编号加入集合
                            mNumberList.add(s);
                        }
                        mFirstClassifyDetailLayout.setVisibility(View.VISIBLE);
                        initView(); //等数据加载完,初始化视图
                        mSmartLayout.finishRefresh(2000);
                    }
                });

    }

    private void initView() {
        // 竖直方向的网格样式，每行2个Item
        GridLayoutManager mLayoutManager = new GridLayoutManager(UiUtilities.getContex(), 2, OrientationHelper.VERTICAL, false);
        //设置管理器
        mRecyclerview.setLayoutManager(mLayoutManager);
        //设置adapter
        mAdapter = new LanMujiangXuanAdapter(FirstClassifyDetail.this, mImageList, mTiltisList, mResalePriceList, mPriceList, mNumberList, mProductIdList);

        mRecyclerview.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItenClick(View view, int position) {
                Toast.makeText(UiUtilities.getContex(), "被点击了" + position, Toast.LENGTH_SHORT).show();
                mIntent = new Intent(UiUtilities.getContex(), GoodsDetailActivity.class);
                mIntent.putExtra(Constant.PRODUCT_ID, mProductIdList.get(position));  //产品id
                startActivity(mIntent);
            }

            @Override
            public void onItemLongCllick(View view, int position) {

            }
        });
    }

    //加载更多
    private void UpDataLoadmore() {

        page++;

        OkHttpUtils
                .get()
                .url(Constant.BASE_URL + "vendors/" + mVid + "/vendor-products")  //栏目二级详情页
                .addParams("category_id", mCategoryId + "")
                .addParams("page", String.valueOf(page))
                .addParams("sort_by", mSortBy)
                .addParams("order", mOrder)
                .build()
                .execute(new LanMuJingXuanCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("没有更多数据了");
                        mSmartLayout.setLoadmoreFinished(true);
                    }

                    @Override
                    public void onResponse(List<LanMuJingXuanBean> response, int id) {
                        LogUtils.d("列表页成功");
                        for (int i = 0; i < response.size(); i++) {

                            mImageList.add(response.get(i).getThumb()); //图片
                            mTiltisList.add(response.get(i).getName());
                            mProductIdList.add(response.get(i).getId());
                            mPriceList.add(response.get(i).getPrice());
                            mProductCostList.add(response.get(i).getProduct_cost());
                            mResalePriceList.add(response.get(i).getResale_price());

                            //产品编号
                            int a = (int) Math.floor((float) (mPriceList.get(i) - mProductCostList.get(i)) / mPriceList.get(i) * 0.6 * 100);

                            String number = GetNunberUtils.getNumber(a);
                            LogUtils.d("编号:" + number);
                            int length = String.valueOf(mProductIdList.get(i)).length();
                            StringBuffer stringBuffer = new StringBuffer();
                            if (length < 8) {
                                int i1 = 8 - length;
                                for (int j = 0; j < i1; j++) {
                                    stringBuffer.append(0);
                                }
                                stringBuffer.append(mProductIdList.get(i));
                            }
                            LogUtils.d("最终编号:" + number + stringBuffer);
                            String s = number + stringBuffer;

                            //编号加入集合
                            mNumberList.add(s);
                        }

                        //initView(); //等数据加载完,初始化视图
                        mAdapter.notifyDataSetChanged();

                    }
                });
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }


    @OnClick({R.id.btn_first_classify_detail_update, R.id.btn_first_classify_detail_price, R.id.btn_first_classify_detail_choose
            , R.id.classify_back, R.id.search_et_input, R.id.search_et_input2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_first_classify_detail_update:  //更新时间筛选

                mBtnFirstClassifyDetailUpdate.setTextColor(Color.rgb(237, 86, 80));
                mBtnFirstClassifyDetailPrice.setTextColor(Color.rgb(39, 39, 39));
                mBtnFirstClassifyDetailChoose.setTextColor(Color.rgb(39, 39, 39));
                mClassifyDetailIv.setImageResource(R.drawable.warehouse_icon_back);

                mSortBy = "created_on_utc";         //筛选条件
                mOrder = "DESC";
                //触发自动加载
                mSmartLayout.autoRefresh();


                break;
            case R.id.btn_first_classify_detail_price:   //价格筛选

                mBtnFirstClassifyDetailUpdate.setTextColor(Color.rgb(39, 39, 39));
                mBtnFirstClassifyDetailPrice.setTextColor(Color.rgb(237, 86, 80));
                mBtnFirstClassifyDetailChoose.setTextColor(Color.rgb(39, 39, 39));


                mSortBy = "price";         //筛选条件
                if (mSX_order == 1) {
                    mClassifyDetailIv.setImageResource(R.drawable.warehouse_icon_back2);
                    mSX_order = 2;
                    mOrder = "ASC";  //升序(从小到大)
                } else if (mSX_order == 2) {
                    mClassifyDetailIv.setImageResource(R.drawable.warehouse_icon_back3);
                    mSX_order = 1;
                    mOrder = "DESC";  //升序(从小到大)
                } else {

                }
                //触发自动加载
                mSmartLayout.autoRefresh();

                break;
            case R.id.btn_first_classify_detail_choose:

                mBtnFirstClassifyDetailUpdate.setTextColor(Color.rgb(39, 39, 39));
                mBtnFirstClassifyDetailPrice.setTextColor(Color.rgb(39, 39, 39));
                mBtnFirstClassifyDetailChoose.setTextColor(Color.rgb(237, 86, 80));
                mClassifyDetailIv.setImageResource(R.drawable.warehouse_icon_back);

                break;

            case R.id.classify_back:  //返回
                LogUtils.d("返回");
                finish();

                break;

            case R.id.search_et_input:  //搜索
                if (mIntExtraTage == CATEGORY_TO) {  //从分类跳转
                    mIntent = new Intent(FirstClassifyDetail.this, SearchActivity.class);
                    mIntent.putExtra(Constant.SEARCH_TAGE, 3);
                    mIntent.putExtra(Constant.CATEGORY_ID, mCategoryId); //分类id
                    startActivity(mIntent);

                } else if (mIntExtraTage == HOME_SEARCH) {
                    finish();
                    mIntent = new Intent(FirstClassifyDetail.this, SearchActivity.class);
                    mIntent.putExtra(Constant.SEARCH_TAGE, 2); //从首页跳转
                    startActivity(mIntent);

                } else if (mIntExtraTage == CATEGORY_SEARCH) {
                    finish();
                    mIntent = new Intent(FirstClassifyDetail.this, SearchActivity.class);
                    mIntent.putExtra(Constant.SEARCH_TAGE, 3);  //从分类搜索跳转
                    mIntent.putExtra(Constant.CATEGORY_ID, mCategoryId); //分类id
                    startActivity(mIntent);
                } else {

                }


                break;
            case R.id.search_et_input2:  //搜索2,有编辑框
                if (mIntExtraTage == CATEGORY_TO) {  //从分类跳转
                    mIntent = new Intent(FirstClassifyDetail.this, SearchActivity.class);
                    mIntent.putExtra(Constant.SEARCH_TAGE, 3);
                    mIntent.putExtra(Constant.CATEGORY_ID, mCategoryId); //分类id
                    startActivity(mIntent);

                } else if (mIntExtraTage == HOME_SEARCH) {
                    finish();
                    mIntent = new Intent(FirstClassifyDetail.this, SearchActivity.class);
                    mIntent.putExtra(Constant.SEARCH_TAGE, 2); //从首页跳转
                    startActivity(mIntent);

                } else if (mIntExtraTage == CATEGORY_SEARCH) {
                    finish();
                    mIntent = new Intent(FirstClassifyDetail.this, SearchActivity.class);
                    mIntent.putExtra(Constant.SEARCH_TAGE, 3);  //从分类搜索跳转
                    mIntent.putExtra(Constant.CATEGORY_ID, mCategoryId); //分类id
                    startActivity(mIntent);
                } else {

                }


                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirstClassifyDetailLayout.setVisibility(View.GONE);
        mSortBy = "id";  //筛选,默认id
        mOrder = "DESC"; //筛选,默认降序
        mClassifyDetailIv.setImageResource(R.drawable.warehouse_icon_back);

    }

}
