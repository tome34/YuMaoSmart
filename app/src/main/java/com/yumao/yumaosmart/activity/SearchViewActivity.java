package com.yumao.yumaosmart.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class SearchViewActivity extends AppCompatActivity  {


    @BindView(R.id.activity_first_classify_detail)
    LinearLayout mActivityFirstClassifyDetail;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.btn_first_classify_detail_update)
    RadioButton mBtnFirstClassifyDetailUpdate;
    @BindView(R.id.btn_first_classify_detail_price)
    RadioButton mBtnFirstClassifyDetailPrice;
    @BindView(R.id.btn_first_classify_detail_choose)
    RadioButton mBtnFirstClassifyDetailChoose;
    @BindView(R.id.radiogroup_first_classify_detail)
    RadioGroup mRadiogroupFirstClassifyDetail;

    @BindView(R.id.smartLayout)
    SmartRefreshLayout mSmartLayout;

    private String mCategoryId ;

    private int mVid;
    private Intent mIntent;

    private int page;
    private String mSortBy = "id";
    private String mOrder = "DESC";
    private int mShaiXuan = 1;  //筛选,默认为1 ;


    List<String> mImageList; //栏目精选图片集合
    List<String> mTiltisList; //栏目精选标题集合
    List<Integer> mIdList;     //产品id
    List<Integer> mPriceList;  //产品价格
    List<Integer> mProductCostList; //产品的成本价
    List<Integer> mResalePriceList; //产品的转售价格,以转卖价优先
    List<String> mNumberList;//产品编号

    private LanMujiangXuanAdapter mAdapter;

    private String mSearchResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        ButterKnife.bind(this);
        //顶部toolbar搜索
        initSearchView();

        //下拉加载
        Smartrefresh();

        //触发自动加载
        mSmartLayout.autoRefresh(10);


    }

    private void initSearchView() {
        Intent intent = getIntent();
        //搜索结果
        mSearchResult = intent.getStringExtra(Constant.SEARCH_RESULT);

       // mCategoryId =  intent.getStringExtra(Constant.CATEGORY_ID);

    }

    private void Smartrefresh() {

        mSmartLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //refreshlayout.finishRefresh(2000);
                UpDataView();
                mSmartLayout.finishRefresh(2000);
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
        ClassifyData();  //加载数据
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }


    private void ClassifyData() {

        //判断是否登录
        if (LoginManager.getInstance().isLoginState(UiUtilities.getContex())) {
            User userInformation = UserInformationManager.getInstance().getUserInformation();
            int id = userInformation.getVendor().getId();
            LogUtils.d("tag", "" + id);
            mVid = id;
        } else {
            mVid = 1;
        }

            LogUtils.d("tag","搜索:"+mSearchResult +"  "+mVid);


        page = 1;      //页数
        //可加载更多
        mSmartLayout.setLoadmoreFinished(false);

        if (mShaiXuan == 1) {
            mOrder = "DESC";
        }
        //mSortBy = "id"; //筛选类型
        mImageList = new ArrayList<>(); //栏目精选图片集合
        mTiltisList = new ArrayList<>(); //栏目精选标题集合
        mIdList = new ArrayList<>();     //产品id
        mPriceList = new ArrayList<>();  //产品价格
        mProductCostList = new ArrayList<>(); //产品的成本价
        mResalePriceList = new ArrayList<>(); //产品的转售价格,以转卖价优先
        mNumberList = new ArrayList<>(); //产品的编号


        OkHttpUtils
                .get()
                .url(Constant.BASE_URL + "vendors/" + mVid + "/vendor-products")  //栏目二级详情页
                .addParams("keyword",mSearchResult)
                //.addParams("category_id","")
                .addParams("page", String.valueOf(page))
                .addParams("sort_by", mSortBy)
                .addParams("order", mOrder)
                .build()
                .execute(new LanMuJingXuanCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("列表页失败"+e);
                        mRadiogroupFirstClassifyDetail.setVisibility(View.VISIBLE);
                        mSmartLayout.finishRefresh(2000);
                    }

                    @Override
                    public void onResponse(List<LanMuJingXuanBean> response, int id) {
                        LogUtils.d("列表页成功");
                        for (int i = 0; i < response.size(); i++) {

                            mImageList.add(response.get(i).getThumb()); //图片
                            mTiltisList.add(response.get(i).getName());
                            mIdList.add(response.get(i).getId());
                            mPriceList.add(response.get(i).getPrice());
                            mProductCostList.add(response.get(i).getProduct_cost());
                            mResalePriceList.add(response.get(i).getResale_price());

                            //产品编号
                            int a = (int) Math.floor((float) (mPriceList.get(i) - mProductCostList.get(i)) / mPriceList.get(i) * 0.6 * 100);

                            String number = GetNunberUtils.getNumber(a);
                            LogUtils.d("编号:" + number);
                            int length = String.valueOf(mIdList.get(i)).length();
                            StringBuffer stringBuffer = new StringBuffer();
                            if (length < 8) {
                                int i1 = 8 - length;
                                for (int j = 0; j < i1; j++) {
                                    stringBuffer.append(0);
                                }
                                stringBuffer.append(mIdList.get(i));
                            }
                            LogUtils.d("最终编号:" + number + stringBuffer);
                            String s = number + stringBuffer;

                            //编号加入集合
                            mNumberList.add(s);
                        }
                        mRadiogroupFirstClassifyDetail.setVisibility(View.VISIBLE);
                        initView(); //等数据加载完,初始化视图

                    }
                });

    }

    private void initView() {
        // 竖直方向的网格样式，每行2个Item
        GridLayoutManager mLayoutManager = new GridLayoutManager(UiUtilities.getContex(), 2, OrientationHelper.VERTICAL, false);
        //设置管理器
        mRecyclerview.setLayoutManager(mLayoutManager);
        //设置adapter
        mAdapter = new LanMujiangXuanAdapter(SearchViewActivity.this, mImageList, mTiltisList, mResalePriceList, mPriceList, mNumberList);

        mRecyclerview.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItenClick(View view, int position) {
                Toast.makeText(UiUtilities.getContex(), "被点击了" + position, Toast.LENGTH_SHORT).show();
                mIntent = new Intent(UiUtilities.getContex(), GoodsDetailActivity.class);
                mIntent.putExtra(Constant.PRODUCT_ID, mIdList.get(position));  //产品id
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
                .addParams("category_id", "")
                .addParams("keyword", mSearchResult)
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
                            mIdList.add(response.get(i).getId());
                            mPriceList.add(response.get(i).getPrice());
                            mProductCostList.add(response.get(i).getProduct_cost());
                            mResalePriceList.add(response.get(i).getResale_price());

                            //产品编号
                            int a = (int) Math.floor((float) (mPriceList.get(i) - mProductCostList.get(i)) / mPriceList.get(i) * 0.6 * 100);

                            String number = GetNunberUtils.getNumber(a);
                            LogUtils.d("编号:" + number);
                            int length = String.valueOf(mIdList.get(i)).length();
                            StringBuffer stringBuffer = new StringBuffer();
                            if (length < 8) {
                                int i1 = 8 - length;
                                for (int j = 0; j < i1; j++) {
                                    stringBuffer.append(0);
                                }
                                stringBuffer.append(mIdList.get(i));
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

    @OnClick({R.id.btn_first_classify_detail_update, R.id.btn_first_classify_detail_price, R.id.btn_first_classify_detail_choose})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_first_classify_detail_update:  //更新时间筛选

                mSortBy = "created_on_utc";         //筛选条件
                //触发自动加载
                mSmartLayout.autoRefresh();


                break;
            case R.id.btn_first_classify_detail_price:   //价格筛选

                mSortBy = "price";         //筛选条件
                if (mShaiXuan == 1) {
                    mShaiXuan = 2;
                    mOrder = "ASC";  //升序(从小到大)
                } else if (mShaiXuan == 2) {
                    mShaiXuan = 1;
                    mOrder = "DESC";  //升序(从小到大)
                } else {

                }
                //触发自动加载
                mSmartLayout.autoRefresh();

                break;
            case R.id.btn_first_classify_detail_choose:


                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRadiogroupFirstClassifyDetail.setVisibility(View.GONE);

    }

}
