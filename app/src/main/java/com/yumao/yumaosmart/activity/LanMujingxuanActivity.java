package com.yumao.yumaosmart.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.roundedimageview.RoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.picasso.Picasso;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.LanMujiangXuanAdapter;
import com.yumao.yumaosmart.base.BaseItemActivity;
import com.yumao.yumaosmart.callback.LanMuJingXuanCallback;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.inter.OnItemClickListener;
import com.yumao.yumaosmart.mode.LanMuJingXuanBean;
import com.yumao.yumaosmart.utils.GetNunberUtils;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.SPUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.yumao.yumaosmart.base.MyApplication.mContext;

public class LanMujingxuanActivity extends BaseItemActivity {


    @BindView(R.id.iv_first_item_person_topleft)
    ImageView mIvFirstItemPersonTopleft;
    @BindView(R.id.iv_first_item_person_topright)
    RoundedImageView mIvFirstItemPersonTopright;
    @BindView(R.id.tv_first_list_user_name)
    TextView mTvFirstListUserName;
    @BindView(R.id.tv_first_page_person_tel)
    ImageView mTvFirstPagePersonTel;
    @BindView(R.id.tv_first_page_shanjiajieshao)
    ImageView mTvFirstPageShanjiajieshao;
    @BindView(R.id.first_banner_iv)
    ImageView mFirstBannerIv;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.activity_lan_mujingxuan)
    LinearLayout mActivityLanMujingxuan;
    @BindView(R.id.tv_titlle)
    TextView mTvTitlle;
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.smartLayout)
    SmartRefreshLayout mSmartLayout;
    private LanMujiangXuanAdapter mAdapter;

    List<String> mImageList; //栏目精选图片集合
    List<String> mTiltisList; //栏目精选标题集合
    List<Integer> mProductIdList;     //产品id
    List<Integer> mPriceList;  //产品价格
    List<Integer> mProductCostList; //产品的成本价
    List<Integer> mResalePriceList; //产品的转售价格,以转卖价优先
    List<String> mNumberList;//产品编号
    private String mLogoUri;
    private Intent mIntent;

    private int page; //页数
    private int mVsid;  //栏目vsid

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lan_mujingxuan);
        initToobar("栏目精选");
        ButterKnife.bind(this);

        //下拉刷新
        initRefresh();
        //触发自动加载
        mSmartLayout.autoRefresh(10);

    }

    //刷新数据
    private void UpDataView() {
        initData(); //初始化数据
    }

    private void initRefresh() {

        mSmartLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //刷新数据
                UpDataView();

            }
        });
        mSmartLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
               //加载更多
                UpDataLoadmore();
                //结束加载更多动画
                mSmartLayout.finishLoadmore(1500);
            }
        });
    }

    private void initData() {

        Intent intent = getIntent();
        //分类id
        mVsid = intent.getIntExtra("vsid", -1);
        LogUtils.d("tag", "vsid:" + mVsid);

        page = 1 ; //页数
        mSmartLayout.setLoadmoreFinished(false);

        String bannerImage = intent.getStringExtra("BannerImage");
        //设置banner图
        Picasso.with(UiUtilities.getContex()).load(bannerImage).fit().into(mFirstBannerIv);
        //首页左边商城头像
        Picasso.with(mContext).load(mLogoUri).resize(180, 180).placeholder(R.mipmap.yumao_mall).into(mIvFirstItemPersonTopleft);

        //用户头像
        String iconUrl = SPUtils.getString(UiUtilities.getContex(), Constant.AVATAR_URL);
        Picasso.with(mContext).load(iconUrl).placeholder(R.mipmap.first_page_person_icon_touxiang).into(mIvFirstItemPersonTopright);

        //用户名

        String niceName = SPUtils.getString(UiUtilities.getContex(), Constant.NICK_NAME);
        mTvFirstListUserName.setText(niceName);
        mTvFirstListUserName.setTextColor(Color.BLACK);


        mImageList = new ArrayList<>(); //栏目精选图片集合
        mTiltisList = new ArrayList<>(); //栏目精选标题集合
        mProductIdList = new ArrayList<>();     //产品id
        mPriceList = new ArrayList<>();  //产品价格
        mProductCostList = new ArrayList<>(); //产品的成本价
        mResalePriceList = new ArrayList<>(); //产品的转售价格,以转卖价优先
        mNumberList = new ArrayList<>(); //产品的编号

        OkHttpUtils
                .get()
                .url(Constant.BASE_URL + "vendor-sections/" + mVsid + "/section-featured/products")  //栏目二级详情页
                .addParams("page", String.valueOf(page))
                .addParams("sort_by","id")
                .addParams("order","DESC")
                .build()
                .execute(new LanMuJingXuanCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("tag", "失败");
                    }

                    @Override
                    public void onResponse(List<LanMuJingXuanBean> response, int id) {
                        LogUtils.d("tag", "成功");
                        mLogoUri = response.get(0).getVendor().getLogo();  //页面的门店logo图
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

                        initView(); //等数据加载完,初始化视图

                        mSmartLayout.finishRefresh(2000);//刷新时间
                    }


                });
    }

    private void initView() {

        // 竖直方向的网格样式，每行2个Item
        GridLayoutManager mLayoutManager = new GridLayoutManager(UiUtilities.getContex(), 2, OrientationHelper.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                //return super.canScrollVertically();
                // 直接禁止垂直滑动
                return false;
            }
        };
        //设置管理器
        mRecyclerview.setLayoutManager(mLayoutManager);
        //设置adapter
        mAdapter = new LanMujiangXuanAdapter(LanMujingxuanActivity.this, mImageList, mTiltisList, mResalePriceList, mPriceList, mNumberList);

        mRecyclerview.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItenClick(View view, int position) {
                Toast.makeText(UiUtilities.getContex(), "被点击了" + position, Toast.LENGTH_SHORT).show();
                mIntent = new Intent(LanMujingxuanActivity.this, GoodsDetailActivity.class);
                mIntent.putExtra(Constant.PRODUCT_ID, mProductIdList.get(position));  //产品id
                startActivity(mIntent);

            }

            @Override
            public void onItemLongCllick(View view, int position) {

            }
        });

    }

    //上拉加载更多
    private void UpDataLoadmore() {

        page ++;

        OkHttpUtils
                .get()
                .url(Constant.BASE_URL + "vendor-sections/" + mVsid + "/section-featured/products")  //栏目二级详情页
                .addParams("page", String.valueOf(page))
                .addParams("sort_by","id")
                .addParams("order","DESC")
                .build()
                .execute(new LanMuJingXuanCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("tag", "失败");
                        mSmartLayout.setLoadmoreFinished(true); //没有数据了
                    }

                    @Override
                    public void onResponse(List<LanMuJingXuanBean> response, int id) {
                        LogUtils.d("tag", "成功");
                        mLogoUri = response.get(0).getVendor().getLogo();  //页面的门店logo图
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

                        //更新数据
                        mAdapter.notifyDataSetChanged();

                    }


                });
    }


}
