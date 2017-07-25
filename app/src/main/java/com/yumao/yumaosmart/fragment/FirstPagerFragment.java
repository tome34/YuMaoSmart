package com.yumao.yumaosmart.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.activity.FirstClassifyDetail;
import com.yumao.yumaosmart.activity.LanMujingxuanActivity;
import com.yumao.yumaosmart.activity.LoginActivity;
import com.yumao.yumaosmart.activity.MainActivity;
import com.yumao.yumaosmart.activity.MyMaterial2Activity;
import com.yumao.yumaosmart.activity.SearchActivity;
import com.yumao.yumaosmart.adapter.FirstClassifyAdapter;
import com.yumao.yumaosmart.adapter.FirstListAdaper;
import com.yumao.yumaosmart.adapter.FirstListRvAdaper;
import com.yumao.yumaosmart.base.LoadingPager;
import com.yumao.yumaosmart.bean.ClassifyBean;
import com.yumao.yumaosmart.bean.FirstListBean;
import com.yumao.yumaosmart.callback.FirstBannerCallback;
import com.yumao.yumaosmart.callback.FirstBannerLMCallback;
import com.yumao.yumaosmart.callback.FirstClassifyCallback;
import com.yumao.yumaosmart.callback.FirstListRvCallback;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.inter.OnItemClickListener;
import com.yumao.yumaosmart.manager.LoginManager;
import com.yumao.yumaosmart.manager.UserInformationManager;
import com.yumao.yumaosmart.mode.FirstListRvMode;
import com.yumao.yumaosmart.mode.FristBannerBean;
import com.yumao.yumaosmart.mode.FristBannerLMBean;
import com.yumao.yumaosmart.mode.FristClassifyMode;
import com.yumao.yumaosmart.mode.ProductsMode;
import com.yumao.yumaosmart.mode.User;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.SPUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.yumao.yumaosmart.web.FristBannerWebActivity;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.yumao.yumaosmart.base.MyApplication.mContext;

/**
 * 首页实现
 */

public class FirstPagerFragment extends BaseFragment {
    public View mFragmentView;
    @BindView(R.id.iv_first_item_person_topleft)
    ImageView mIvFirstItemPersonTopleft;
    @BindView(R.id.iv_first_item_person_topright)
    ImageView mIvFirstItemPersonTopright;
    @BindView(R.id.rv_first_classify)
    RecyclerView mRvFirstClassify;
    @BindView(R.id.first_scrollView)
    ScrollView mFirstScrollView;
    @BindView(R.id.lv_first_list)
    RecyclerView mLvFirstList;
    @BindView(R.id.tv_first_list_user_name)
    TextView mTvFirstListUserName;
    @BindView(R.id.tv_first_page_shanjiajieshao)
    ImageView mTvFirstPageShanjiajieshao;
    @BindView(R.id.tv_first_page_person_tel)
    ImageView mTvFirstPagePersonTel;
    @BindView(R.id.tv_first_text)
    TextView mTvFirstText;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.smartLayout)
    SmartRefreshLayout mSmartLayout;
    @BindView(R.id.search_et_input)
    EditText mSearchEtInput;

    private List<ClassifyBean> mClassifyData;
    private ClassifyBean mClassifyBeanFeiCui;
    private ClassifyBean mClassifyBeanCaiBao;
    private ClassifyBean mClassifyBeanDiamond;
    private ClassifyBean mClassifyBeanBoJin;
    private ClassifyBean mClassifyBeanZhenZhu;
    private ClassifyBean mClassifyBeanSilver;
    private ClassifyBean mClassifyBeanHeTianYu;
    private ClassifyBean mClassifyBeanOther;
    private FirstClassifyAdapter mClassifyAdapter;
    private List<FirstListBean> mListData;
    private FirstListAdaper mFirstListAdaper;
    private FirstListBean mFirstListBean;
    private ProductsMode mProductsMode;
    private List<ProductsMode.ProductsBean> mProducts;
    private List<String> vpRes = new ArrayList<>();
    private List<ImageView> views = new ArrayList<>();
    private ImageView mImageView;
    private ViewGroup.LayoutParams mLayoutParams;

    private List<String> imageArray;  //轮播图图片集合
    private List<String> imageTitle;  //轮播图标题集合
    private List<String> imageWebView; //轮播图的点击详情页
    private List<Integer> imageVsid;    //栏目精选id 二级分页

    private List<String> mClassifyName;//分类名称
    private List<Boolean> mContainsList;//分类是否显示
    private List<Integer> mIdList; //全部的分类的id,总数有8个
    private List<Integer> mCategoriesId; //筛选后的分类id
    private List<Integer> mImageList; //显示图标
    private List<Integer> mImageUnList; //不显示图标
    private FirstClassifyAdapter mAdapter;

    private List<String> mPictureImage;   //栏目大图
    private List<Integer> mPictureImageLocal;   //本地栏目大图
    private List<Integer> mProductId; //产品id
    private List<String> mProductName;   //产品名称
    private List<Integer> mPrice;   //产品的销售价
    private List<Integer> mResalePrice;   //产品的转售价
    private List<String> mProductImage;   //每个item的产品的图片

    private List<List<Integer>> mProductIdList; //产品id集合
    private List<List<String>> mProductNameList;   //产品名称集合
    private List<List<Integer>> mPriceList;   //产品的销售价集合
    private List<List<Integer>> mResalePriceList;   //产品的转售价集合
    private List<List<String>> mProductImageList; //每个item的产品图片集合

    private List<Integer> mItemSize;      //条目图片的大小
    private List<Integer> mResList;      //列表栏目

    private List<String> mImageLMList;    //列表栏目大图
    private FirstListRvAdaper mRvAdaper;


    private boolean mFlag;
    private int mBannerFlag; //判断轮播图
    private int mListFlag; //判断列表
    private Intent mIntent;

    private int vId;   //门店的id
    private int mPictureImageLenght;

    private boolean searchFlag = false;


    @Override
    protected void init() {

        //下拉刷新
        mSmartLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                updateView();
                refreshlayout.finishRefresh(3000);
            }
        });

        //禁止上拉加载更多
        mSmartLayout.setEnableLoadmore(false);
    }


    //下拉刷新数据
    private void updateView() {

        initView();
    }

    @Override
    protected void initView() {

        initLogin();

        //判断活动是否有数据,如果没有数据在判断栏目的数据
        isBannerData();

        initClassifyData();  //分类加载数据

        initListItemData();  //首页列表数据

        //initList(list); //产品展示

    }

    //初始化布局
    @Override
    public View onInitView() {
        mFragmentView = View.inflate(UiUtilities.getContex(), R.layout.fragment_first_pager, null);
        ButterKnife.bind(this, mFragmentView);
        return mFragmentView;
    }


    //初始化登录信息
    private void initLogin() {
        boolean loginState = LoginManager.getInstance().isLoginState(UiUtilities.getContex());
        if (loginState) {
            mTvFirstListUserName.setVisibility(View.VISIBLE);

            User userBean = UserInformationManager.getInstance().getUserInformation();
            String logoUrl = userBean.getVendor().getLogo();
            //首页左边商城头像
            Picasso.with(mContext).load(logoUrl).placeholder(R.mipmap.yumao_mall).into(mIvFirstItemPersonTopleft);

            //用户头像
            String iconUrl = SPUtils.getString(UiUtilities.getContex(), Constant.AVATAR_URL);
            Picasso.with(mContext).load(iconUrl).placeholder(R.mipmap.first_page_person_icon_touxiang).into(mIvFirstItemPersonTopright);

            //用户名

            String niceName = SPUtils.getString(UiUtilities.getContex(), Constant.NICK_NAME);
            mTvFirstListUserName.setText(niceName);
            mTvFirstListUserName.setTextColor(Color.BLACK);

            //首页底部地址
            String address = userBean.getVendor().getAddress();
            mTvFirstText.setText("地址: " + address);


        } else {
            mIvFirstItemPersonTopright.setImageResource(R.mipmap.first_page_person_icon_touxiang);
            mTvFirstListUserName.setVisibility(View.GONE);
        }
    }


    //判断活动是否有数据
    public void isBannerData() {
        imageArray = new ArrayList<>();    //轮播活动图图片集合
        imageTitle = new ArrayList<>();    //轮播图标题集合
        imageWebView = new ArrayList<>(); //轮播图的点击详情页
        imageVsid = new ArrayList<>();       //栏目精选id

        mImageLMList = new ArrayList<>();    //列表栏目大图

        mFlag = false;
        mBannerFlag = 0;


        //判断是否登录
        if (LoginManager.getInstance().isLoginState(UiUtilities.getContex())) {
            User userInformation = UserInformationManager.getInstance().getUserInformation();
            int id = userInformation.getVendor().getId();
            LogUtils.d("tag", "" + id);
            vId = id;
        } else {
            vId = 1;
        }

        //轮播图的活动图,传入门店id为1 代表玉猫平台的门店id
        OkHttpUtils
                .get()
                .url(Constant.BASE_URL + "vendor-sections")
                .addParams("vendor_id", "1")
                .build()
                .execute(new FirstBannerCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("tag", "轮播图无图片");
                        //隐藏轮播图
                        mBanner.setVisibility(View.GONE);
                        mFlag = true;
                        //栏目轮播图
                        bannerLMGetData();
                    }

                    @Override
                    public void onResponse(List<FristBannerBean> response, int id) {
                        LogUtils.d("tag", "成功");

                        for (int i = 0; i < response.size(); i++) {

                            FristBannerBean fristBannerBean = response.get(i);
                            imageArray.add(fristBannerBean.getPicture());    //活动轮播图
                            imageTitle.add(fristBannerBean.getSection_name());
                            LogUtils.d("tag", "图片:" + fristBannerBean.getPicture());
                            imageWebView.add(fristBannerBean.getSection_href());

                        }
                        LogUtils.d("轮播图1");
                        mBannerFlag = 1;
                        //initViewPager(); //轮播图
                        //栏目轮播图
                        bannerLMGetData();

                    }
                });


        LogUtils.d("轮播图3");
        //initViewPager(); //轮播图
    }

    private void bannerLMGetData() {
        //轮播图的栏目图片,
        OkHttpUtils
                .get()
                .url(Constant.BASE_URL + "vendors/" + vId + "/section-recommended/products")
                .build()
                .execute(new FirstBannerLMCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("tag", "栏目无图片");
                        if (mBannerFlag == 1) {  //证明活动有轮播图
                            initViewPager(); //轮播图
                        }

                    }

                    @Override
                    public void onResponse(List<FristBannerLMBean> response, int id) {
                        LogUtils.d("tag", "栏目有图片");
                        if (mFlag) {
                            mBanner.setVisibility(View.VISIBLE);
                        }

                        for (int i = 0; i < response.size(); i++) {
                            FristBannerLMBean fristBannerLMBean = response.get(i);
                            imageArray.add(fristBannerLMBean.getVendor_section().getPicture());   //栏目轮播图
                            mImageLMList.add(fristBannerLMBean.getVendor_section().getPicture());  //给列表的栏目大图

                            imageVsid.add(fristBannerLMBean.getVendor_section().getId());

                            imageTitle.add(fristBannerLMBean.getVendor_section().getSection_name());

                            //imageWebView.add(fristBannerLMBean.)

                        }
                        LogUtils.d("轮播图2");

                        initViewPager(); //轮播图

                    }
                });
    }

    //        首页轮播图
    private void initViewPager() {
      /*  vpRes.add(String.valueOf(R.mipmap.first_page_picture));
        vpRes.add(String.valueOf(R.mipmap.first_page_picture));
        vpRes.add(String.valueOf(R.mipmap.first_page_picture));

        for (int i = 0; i < vpRes.size(); i++) {
            mImageView = new ImageView(getActivity());
            mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mImageView.setImageResource(Integer.parseInt(vpRes.get(i)));
            mLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mImageView.setLayoutParams(mLayoutParams);
            views.add(mImageView);
        }

        mViewPager.setAdapter(new FirstViewPagerAdapter(views));*/

        //设置图片加载集合
        //imageArray=new ArrayList<>();
        // imageArray.add("http://img3.imgtn.bdimg.com/it/u=2758743658,581437775&fm=15&gp=0.jpg");
        //设置图片标题集合
        //imageTitle=new ArrayList<>();
        //imageTitle.add("aaaaaaaaa");


        //LogUtils.d("tag","图片个数:"+imageArray.size());

        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(imageArray);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.RotateDown);
        //设置标题集合（当banner样式有显示title时）
        //mBanner.setBannerTitles(imageTitle);
        //设置轮播时间
        mBanner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //轮播图的点击事件
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                LogUtils.d("tag", "条目:" + imageWebView.size());
                if (position < imageWebView.size()) {
                    Toast.makeText(UiUtilities.getContex(), "点击了" + position, Toast.LENGTH_SHORT).show();
                    mIntent = new Intent(getActivity(), FristBannerWebActivity.class);
                    mIntent.putExtra("uriWeb", imageWebView.get(position));
                    mIntent.putExtra("titles", imageTitle.get(position));
                    getActivity().startActivity(mIntent);
                } else {
                    Toast.makeText(UiUtilities.getContex(), "栏目精选点击了" + position, Toast.LENGTH_SHORT).show();
                    mIntent = new Intent(getActivity(), LanMujingxuanActivity.class);
                    //   LogUtils.d("tag","id:"+imageId.get(position));
                    mIntent.putExtra("vsid", imageVsid.get(position - imageWebView.size()));
                    mIntent.putExtra("BannerImage", imageArray.get(position));
                    getActivity().startActivity(mIntent);
                }


            }
        });
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }


    private class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);
        }
    }

    //首页分类加载数据
    private void initClassifyData() {

        mClassifyName = new ArrayList<>();//分类名称
        mContainsList = new ArrayList<>();//分类是否显示
        mIdList = new ArrayList<>(); //全部分类的id
        mCategoriesId = new ArrayList<>(); //筛选后的分类id
        mImageList = new ArrayList<>(); //显示图标
        mImageUnList = new ArrayList<>(); //不显示图标

        int[] imageId = {R.mipmap.home_icon_sel_fc, R.mipmap.home__icon_sel_zs, R.mipmap.home_icon_sel_cb, R.mipmap.home_icon_sel_kj,
                R.mipmap.home_icon_sel_zz, R.mipmap.home_icon_sel_ys, R.mipmap.home_icon_sel_hty, R.mipmap.home_icon_sel_qt};

        int[] imageUnId = {R.mipmap.home_icon_uncheck_fc, R.mipmap.home__icon_uncheck_zs, R.mipmap.home_icon_uncheck_cb, R.mipmap.home_icon_uncheck_kj,
                R.mipmap.home_icon_uncheckl_zz, R.mipmap.home_icon_unchecky_ys, R.mipmap.home_icon_uncheck_hty, R.mipmap.home_icon_unchecky_qt};

        for (int i = 0; i < imageId.length; i++) {
            mImageList.add(imageId[i]);
            mImageUnList.add(imageUnId[i]);
        }

        OkHttpUtils
                .get()
                .url(Constant.BASE_URL + "vendors/" + vId + "/categories")
                .build()
                .execute(new FirstClassifyCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        LogUtils.d("分类失败");
                    }

                    @Override
                    public void onResponse(List<FristClassifyMode> response, int id) {
                        LogUtils.d("分类成功" + response.size());
                        response.size();
                        for (int i = 0; i < response.size(); i++) {
                            mClassifyName.add(response.get(i).getName());
                            mContainsList.add(response.get(i).getContains());
                            mIdList.add(response.get(i).getId());
                            //LogUtils.d("tag","分类的id:"+response.get(i).getId());
                        }
                        initClassify(); //分类
                    }
                });
    }

    //首页分类初始化
    private void initClassify() {

     /*   mClassifyBeanFeiCui = new ClassifyBean(String.valueOf(R.mipmap.home_icon_sel_fc), getString(R.string.feicui));
        mClassifyBeanCaiBao = new ClassifyBean(String.valueOf(R.mipmap.home_icon_sel_cb), getString(R.string.caibao));
        mClassifyBeanDiamond = new ClassifyBean(String.valueOf(R.mipmap.home__icon_sel_zs), getString(R.string.zuanshi));
        mClassifyBeanBoJin = new ClassifyBean(String.valueOf(R.mipmap.home_icon_sel_kj), getString(R.string.huangbojin));
        mClassifyBeanZhenZhu = new ClassifyBean(String.valueOf(R.mipmap.home_icon_sel_zz), getString(R.string.zhenzhu));
        mClassifyBeanSilver = new ClassifyBean(String.valueOf(R.mipmap.home_icon_sel_ys), getString(R.string.yinshi));
        mClassifyBeanHeTianYu = new ClassifyBean(String.valueOf(R.mipmap.home_icon_sel_hty), getString(R.string.hetianyu));
        mClassifyBeanOther = new ClassifyBean(String.valueOf(R.mipmap.home_icon_sel_qt), getString(R.string.other));
//        mClassifyAdapter = new FirstClassifyAdapter(UiUtilities.getContex(), R.layout.item_first_classify, mClassifyData);
        mClassifyData = new ArrayList<>();
        mClassifyData.add(mClassifyBeanFeiCui);
        mClassifyData.add(mClassifyBeanCaiBao);
        mClassifyData.add(mClassifyBeanDiamond);
        mClassifyData.add(mClassifyBeanBoJin);
        mClassifyData.add(mClassifyBeanZhenZhu);
        mClassifyData.add(mClassifyBeanSilver);
        mClassifyData.add(mClassifyBeanHeTianYu);
        mClassifyData.add(mClassifyBeanOther);
        mClassifyAdapter = new FirstClassifyAdapter(UiUtilities.getContex(), R.layout.item_first_classify, mClassifyData, getActivity());
        mRvFirstClassify.setLayoutManager(new GridLayoutManager(UiUtilities.getContex(), 4));
        mRvFirstClassify.setAdapter(mClassifyAdapter);*/
        // 竖直方向的网格样式，每行2个Item
        GridLayoutManager mLayoutManager = new GridLayoutManager(UiUtilities.getContex(), 4, OrientationHelper.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                //return super.canScrollVertically();
                // 直接禁止垂直滑动
                return false;
            }
        };
        //设置管理器
        mRvFirstClassify.setLayoutManager(mLayoutManager);
        //设置adapter
        mAdapter = new FirstClassifyAdapter(UiUtilities.getContex(), mImageList, mImageUnList, mClassifyName, mContainsList);

        mRvFirstClassify.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItenClick(View view, int position) {
                if (mContainsList.get(position)) {

                    //Toast.makeText(UiUtilities.getContex(), "被点击了" + position, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UiUtilities.getContex(), FirstClassifyDetail.class);
                    intent.putExtra(Constant.CATEGORY_ID, mIdList.get(position));  //分类的id
                    intent.putExtra("vid", vId);//门店id
                    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getActivity().startActivity(intent);
                }
            }

            @Override
            public void onItemLongCllick(View view, int position) {

            }
        });

    }

    //首页列表数据
    private void initListItemData() {

        mPictureImage = new ArrayList<>();   //栏目大图
        mPictureImageLocal = new ArrayList<>();   //本地栏目大图
        mProductId = new ArrayList<>(); //产品id
        mProductName = new ArrayList<>();   //产品名称
        mResalePrice = new ArrayList<>();   //产品的转售价
        mPrice = new ArrayList<>();         //产品的销售价
        mProductImage = new ArrayList<>();   //每个item的产品的图片
        LogUtils.d("tag", "数组初始化:" + mProductImage);

        mItemSize = new ArrayList<>();      //条目的大小
        mResList = new ArrayList<>();       //列表栏目

        mProductIdList = new ArrayList<>(); //产品id集合
        mProductNameList = new ArrayList<>();   //产品名称集合
        mPriceList = new ArrayList<>();   //产品的销售价集合
        mResalePriceList = new ArrayList<>();   //产品的转售价集合
        mProductImageList = new ArrayList<>(); //每个item的产品图片集合

        mListFlag = 0;   //判断列表


        int[] titleRes = {R.mipmap.home_banner_fc, R.mipmap.home_banner_zs,
                R.mipmap.home_banner_cb,
                R.mipmap.home_banner_kj,
                R.mipmap.home_banner_zz,
                R.mipmap.home_banner_ys,
                R.mipmap.home_banner_hty,
                R.mipmap.home_banner_qt

        };

        for (int i = 0; i < titleRes.length; i++) {
            mResList.add(titleRes[i]);
        }


        //获取栏目图
        OkHttpUtils
                .get()
                .url(Constant.BASE_URL + "vendors/" + vId + "/section-recommended/products")
                .build()
                .execute(new FirstBannerLMCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("tag", "栏目无图片2");
                        mListFlag = 1;  //栏目无图片
                        //列表数据
                        ListLMGetData();

                    }

                    @Override
                    public void onResponse(List<FristBannerLMBean> response, int id) {
                        LogUtils.d("tag", "栏目有图片2");
                        mListFlag = 2;  //栏目有图片

                        for (int i = 0; i < response.size(); i++) {

                            mProductId = new ArrayList<Integer>();
                            mProductName = new ArrayList<String>();
                            mResalePrice = new ArrayList<Integer>();
                            mPrice = new ArrayList<Integer>();
                            mProductImage = new ArrayList<>();


                            mPictureImageLenght = response.size();  //栏目的数量
                            mPictureImage.add(response.get(i).getVendor_section().getPicture());
                            LogUtils.d("列表栏目图片:" + mPictureImage.get(i));
                            mItemSize.add(response.get(i).getProducts().size());
                            for (int j = 0; j < response.get(i).getProducts().size(); j++) {

                                mProductId.add(response.get(i).getProducts().get(j).getId());
                                mProductName.add(response.get(i).getProducts().get(j).getName());
                                mResalePrice.add(response.get(i).getProducts().get(j).getResale_price());
                                mPrice.add(response.get(i).getProducts().get(j).getPrice());
                                mProductImage.add(response.get(i).getProducts().get(j).getThumb());
                            }
                            LogUtils.d("tag", "tup:" + mProductImage.size());

                            mProductIdList.add(mProductId);
                            mProductNameList.add(mProductName);
                            mResalePriceList.add(mResalePrice);
                            mPriceList.add(mPrice);
                            mProductImageList.add(mProductImage);

                        }
                        //列表数据
                        ListLMGetData();
                        // initList(); //首页列表初始化
                    }
                });


    }

    //加载完栏目自定义,在加载列表数据
    private void ListLMGetData() {
        //列表分类
        OkHttpUtils
                .get()
                .url(Constant.BASE_URL + "vendors/" + vId + "/category-products")
                .build()
                .execute(new FirstListRvCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("tag", "分类失败2");
                        if (mListFlag == 2) {
                            initList(); //首页列表初始化
                        }
                    }

                    @Override
                    public void onResponse(List<FirstListRvMode> response, int id) {
                        LogUtils.d("tag", "分类成功2:" + response.size());
                        //筛选本地列表大图
                        for (int k = 0; k < 8; k++) {
                            if (mContainsList.get(k)) {
                                mPictureImageLocal.add(mResList.get(k));  //列表大图
                                mCategoriesId.add(mIdList.get(k));            //筛选分类id
                            }
                        }


                        for (int i = 0; i < response.size(); i++) {
                            //mPictureImageLocal.add(mResList.get(i));  //有问题

                            mProductId = new ArrayList<Integer>();
                            mProductName = new ArrayList<String>();
                            mResalePrice = new ArrayList<Integer>();
                            mPrice = new ArrayList<Integer>();
                            mProductImage = new ArrayList<>();


                            mItemSize.add(response.get(i).getProducts().size());
                            LogUtils.d("mitemSize:" + mItemSize);

                            for (int j = 0; j < response.get(i).getProducts().size(); j++) {

                                mProductId.add(response.get(i).getProducts().get(j).getId());
                                mProductName.add(response.get(i).getProducts().get(j).getName());
                                mResalePrice.add(response.get(i).getProducts().get(j).getResale_price());
                                mPrice.add(response.get(i).getProducts().get(j).getPrice());
                                mProductImage.add(response.get(i).getProducts().get(j).getThumb());
                            }
                            mProductIdList.add(mProductId);
                            mProductNameList.add(mProductName);
                            mResalePriceList.add(mResalePrice);
                            mPriceList.add(mPrice);
                            mProductImageList.add(mProductImage);


                        }

                        initList(); //首页列表初始化
                    }
                });
    }

    //    首页列表初始化
    private void initList() {
        /*mFirstListAdaper = new FirstListAdaper(UiUtilities.getContex(), R.layout.item_first_list, list, getActivity());
        mLvFirstList.setAdapter(mFirstListAdaper);*/
        // 竖直方向的网格样式，每行2个Item
       /* LinearLayoutManager mLayoutManager = new LinearLayoutManager(UiUtilities.getContex()) {
            @Override
            public boolean canScrollVertically() {
                //return super.canScrollVertically();
                // 直接禁止垂直滑动
                return false;
            }
        };*/
        // 竖直方向的网格样式，每行1个Item
        GridLayoutManager mLayoutManager = new GridLayoutManager(UiUtilities.getContex(), 1, OrientationHelper.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                //return super.canScrollVertically();
                // 直接禁止垂直滑动
                return false;
            }
        };
        mLvFirstList.setNestedScrollingEnabled(false);
        //mLayoutManager.setOrientation(OrientationHelper. VERTICAL);
        //设置管理器
        mLvFirstList.setLayoutManager(mLayoutManager);
        //设置adapter
        mRvAdaper = new FirstListRvAdaper(UiUtilities.getContex(), mPictureImage, mItemSize, mProductNameList, mResalePriceList, mPriceList, mProductImageList, mPictureImageLenght, mPictureImageLocal
                , imageVsid, mImageLMList, vId, mCategoriesId, mProductIdList);

        // TODO: 2017/7/14点击事件传递数据

        mLvFirstList.setAdapter(mRvAdaper);


    }

/*    //        初始化网络,获取Fragment的数据
    @Override
    protected void init() {
        OkHttpUtils
                .get()
                .url("https://dist.yumao168.com/api/recommended-products")
                .build()
                .execute(new ProductsCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<ProductsMode> response, int id) {
                        initData(response);
                    }
                });
    }*/

    @Override
    public LoadingPager.LoadingPagerEnum onInitData() {

        return LoadingPager.LoadingPagerEnum.SUCCESS;
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        ButterKnife.bind(this, rootView);
//        return rootView;
//    }

    @Override
    protected void initListenner() {


    }


    //首页个人中心点击事件
    @OnClick({R.id.iv_first_item_person_topleft, R.id.iv_first_item_person_topright, R.id.tv_first_list_user_name,
            R.id.tv_first_page_shanjiajieshao, R.id.tv_first_page_person_tel,R.id.search_et_input})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_first_item_person_topleft:
                Toast.makeText(UiUtilities.getContex(), "首页品牌点击事件收到", Toast.LENGTH_SHORT).show();

                break;

            case R.id.iv_first_item_person_topright:
                // Toast.makeText(UiUtilities.getContex(), "首页头像点击事件收到", Toast.LENGTH_SHORT).show();
                if (LoginManager.getInstance().isLoginState(UiUtilities.getContex())) {
                    MainActivity activity = (MainActivity) getActivity();
                    activity.startActivity(new Intent(activity, MyMaterial2Activity.class));
                } else {
                    startActivity(new Intent(UiUtilities.getContex(), LoginActivity.class));
                }

                break;

            case R.id.tv_first_page_shanjiajieshao: //品牌介绍
                if (LoginManager.getInstance().isLoginState(UiUtilities.getContex())) {
                    Toast.makeText(UiUtilities.getContex(), "品牌介绍", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(UiUtilities.getContex(), LoginActivity.class));
                }
                break;

            case R.id.tv_first_page_person_tel:  //tell电话
                boolean loginState = LoginManager.getInstance().isLoginState(UiUtilities.getContex());
                if (loginState) {
                    User userBean = UserInformationManager.getInstance().getUserInformation();
                    Object tel = userBean.getVendor().getTel();

                    if (tel != null) {
                        //需要处理权限问题
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel)));

                    } else {
                        Toast.makeText(UiUtilities.getContex(), "未填写电话号码", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    startActivity(new Intent(UiUtilities.getContex(), LoginActivity.class));
                }

                break;

            case R.id.search_et_input:  //首页搜索栏跳转到搜索页

                startActivity(new Intent(UiUtilities.getContex(), SearchActivity.class));
                break;

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
