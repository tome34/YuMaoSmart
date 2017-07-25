package com.yumao.yumaosmart.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
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
import com.yumao.yumaosmart.callback.CategoriesContentCallback;
import com.yumao.yumaosmart.callback.LanMuJingXuanCallback;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.inter.OnItemClickListener;
import com.yumao.yumaosmart.mode.CategoriesContentMode;
import com.yumao.yumaosmart.mode.LanMuJingXuanBean;
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

public class FirstClassifyDetail extends AppCompatActivity implements SearchView.OnQueryTextListener {


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
    @BindView(R.id.toolbar)
    Toolbar mToolbar;



    private String mText;
    private List<String> mTextList = new ArrayList<>();

    private List<String> categry = new ArrayList<>();
    private CustomRadioGroup mCustomRadioGroup;
    private List mData = new ArrayList();
    private RadioButton mRadioButton;
    private List<CategoriesContentMode.ChildrenBeanX> mChildren;
    private CategoriesContentMode.ChildrenBeanX mChildrenBeanX;
    private Map<String, String> map = new HashMap<>();
    private int mCategoryId;
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

    private SearchView searchView;


    private LanMujiangXuanAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_classify_detail);
        ButterKnife.bind(this);

        //下拉加载
        Smartrefresh();

        //触发自动加载
        mSmartLayout.autoRefresh(10);

        //顶部toolbar搜索
        initSearchView();
    }

    private void initSearchView() {

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.search_toolbar, menu);
        MenuItem menuItem = menu.findItem(R.id.search);//
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);//加载searchview
        searchView.setOnQueryTextListener(this);//为搜索框设置监听事件
        searchView.setSubmitButtonEnabled(true);//设置是否显示搜索按钮
        searchView.setQueryHint("查找");//设置提示信息
        searchView.setIconifiedByDefault(true);//设置搜索默认为图标
        return true;

    }

    //搜索监听
    @Override
    public boolean onQueryTextSubmit(String query) {
        //在输入法按下搜索或者回车时，会调用次方法，在这里可以作保存历史记录的操作，
        // 我这里用了 sharepreference保存

        Toast.makeText(this, "保存" + query, Toast.LENGTH_SHORT).show();


        //.searchKnowledge(query);


        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Intent intent = new Intent(this, SearchViewActivity.class);
        startActivity(intent);
        //输入字符则回调此方法
        //Toast.makeText(this,"154",Toast.LENGTH_SHORT).show();
        if (TextUtils.isEmpty((newText))) {
            //mListview.clearTextFilter();
        } else {
            //mListview.setFilterText(newText);
        }
        return true;
    }

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
        ClassifyData();  //加载数据
        init();
    }


    //初始化mPostion和mCategoryId
    private void setCategoryId() {
        /*Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mCategoryId = extras.getInt("mCategoryId");*/
        Intent intent = getIntent();
        mCategoryId = intent.getIntExtra(Constant.CATEGORY_ID, -1);
        mVid = intent.getIntExtra("vid", 1);
        LogUtils.d("id:" + mCategoryId + " " + mVid);

    }

    private void ClassifyData() {


        page = 1;      //页数
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
                .addParams("category_id", mCategoryId + "")
                .addParams("page", String.valueOf(page))
                .addParams("sort_by", mSortBy)
                .addParams("order", mOrder)
                .build()
                .execute(new LanMuJingXuanCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("列表页失败");
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
        mAdapter = new LanMujiangXuanAdapter(FirstClassifyDetail.this, mImageList, mTiltisList, mResalePriceList, mPriceList, mNumberList);

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

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
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


    private void initData(List<CategoriesContentMode.ChildrenBeanX> data) {


    }

    private void initContent(List<CategoriesContentMode.ChildrenBeanX> data) {
       /* map.clear();

        if (mPotion == 0) {

        } else {
            mChildrenBeanX = data.get(mPotion - 1);
            mId = mChildrenBeanX.getId();

            map.put("category_id", String.valueOf(mId));
        }

        map.put("page", String.valueOf(1));
        map.put("limit", String.valueOf(20));
        map.put("sort_by", "id");
        map.put("order", "DESC");
        OkHttpUtils
                .get()
                .url("https://dist.yumao168.com/api/products")
                .params(map)
                .build()
                .execute(new FirstClassifyDetailCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(List<FirstClassifyDetailMode> response, int id) {

                        //initView(response);
                    }
                });*/


    }


    private void init() {
        initRadioGroup();
    }

    private void setSpacing(CustomRadioGroup cg, int widthdp, int heightdp) {
        cg.setHorizontalSpacing(widthdp);
        cg.setVerticalSpacing(heightdp);

    }

    private void initRadioGroup() {
        setDataForRadioGroup();
        mRadiogroupFirstClassifyDetail.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                group.check(checkedId);


            }
        });
        mCustomRadioGroup = (CustomRadioGroup) findViewById(R.id.customRadioGroup);
        setSpacing(mCustomRadioGroup, 12, 8);

        mCustomRadioGroup.setListener(new CustomRadioGroup.OnclickListener() {
            @Override
            public void OnText(String text) {
                mText = text;
                //mPotion = mTextList.indexOf(text);
                if (mChildren != null) {
                    initContent(mChildren);
                }

            }
        });


    }

    //    初始化RadioGroup,请求网络 获取去RadioButton显示数据,完了更新内容的视图
    private void setDataForRadioGroup() {
        OkHttpUtils
                .get()
                .url("https://dist.yumao168.com/api/categories/")
                .build()
                .execute(new CategoriesContentCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        //Toast.makeText(FirstClassifyDetail.this, "网络连接失败", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(CategoriesContentMode response, int id) {
                        mChildren = response.getChildren();
                        initData(mChildren);
                        for (int i = 0; i < mChildren.size() + 1; i++) {
                            mRadioButton = (RadioButton) FirstClassifyDetail.this.getLayoutInflater().inflate(R.layout.radiobutton_addcart, null);
                            if (i == 0) {
                                mRadioButton.setText("所有");
                                mTextList.add("所有");
                                mCustomRadioGroup.addView(mRadioButton);
                                mRadioButton.setChecked(true);
                            } else {
                                mRadioButton.setText(mChildren.get(i - 1).getName());
                                mTextList.add(mChildren.get(i - 1).getName());
                                mCustomRadioGroup.addView(mRadioButton);

                            }


                        }

                        initContent(mChildren);
                    }
                });

    }


}
