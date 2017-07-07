package com.yumao.yumaosmart.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.activity.MainActivity;
import com.yumao.yumaosmart.activity.MyMaterial2Activity;
import com.yumao.yumaosmart.adapter.FirstClassifyAdapter;
import com.yumao.yumaosmart.adapter.FirstListAdaper;
import com.yumao.yumaosmart.adapter.vp_adapter.FirstViewPagerAdapter;
import com.yumao.yumaosmart.base.LoadingPager;
import com.yumao.yumaosmart.bean.ClassifyBean;
import com.yumao.yumaosmart.bean.FirstListBean;
import com.yumao.yumaosmart.callback.ProductsCallback;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.manager.LoginManager;
import com.yumao.yumaosmart.manager.UserInformationManager;
import com.yumao.yumaosmart.mode.ProductsMode;
import com.yumao.yumaosmart.mode.User;
import com.yumao.yumaosmart.utils.SPUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
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
    ListView mLvFirstList;
    @BindView(R.id.vp_fragment_firstager)
    ViewPager mViewPager;
    @BindView(R.id.tv_first_list_user_name)
    TextView mTvFirstListUserName;
    @BindView(R.id.tv_first_page_shanjiajieshao)
    ImageView mTvFirstPageShanjiajieshao;
    @BindView(R.id.tv_first_page_person_tel)
    ImageView mTvFirstPagePersonTel;
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


    @Override

    protected void initView() {

        boolean loginState = LoginManager.getInstance().isLoginState(UiUtilities.getContex());
        if (loginState){
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




        }


    }




    private void initData(List<ProductsMode> list) {
        initViewPager();
        initClassify();
        initList(list);
    }

    //        首页轮播图
    private void initViewPager() {
        vpRes.add(String.valueOf(R.mipmap.first_page_picture));
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

        mViewPager.setAdapter(new FirstViewPagerAdapter(views));
    }

    //    首页列表初始化
    private void initList(List<ProductsMode> list) {
        mFirstListAdaper = new FirstListAdaper(UiUtilities.getContex(), R.layout.item_first_list, list, getActivity());
        mLvFirstList.setAdapter(mFirstListAdaper);
    }

    //首页分类初始化
    private void initClassify() {
        mClassifyBeanFeiCui = new ClassifyBean(String.valueOf(R.mipmap.home_icon_sel_fc), getString(R.string.feicui));
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
        mRvFirstClassify.setAdapter(mClassifyAdapter);
    }

    //        初始化网络,获取Fragment的数据
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
    }

    @Override
    public LoadingPager.LoadingPagerEnum onInitData() {

        return LoadingPager.LoadingPagerEnum.SUCCESS;
    }

    @Override
    public View onInitView() {
        mFragmentView = View.inflate(UiUtilities.getContex(), R.layout.fragment_first_pager, null);
        ButterKnife.bind(this, mFragmentView);
        return mFragmentView;
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
    @OnClick({R.id.iv_first_item_person_topleft,R.id.iv_first_item_person_topright, R.id.tv_first_list_user_name,
            R.id.tv_first_page_shanjiajieshao,R.id.tv_first_page_person_tel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_first_item_person_topleft:
                Toast.makeText(UiUtilities.getContex(), "首页品牌点击事件收到", Toast.LENGTH_SHORT).show();

                break;

            case R.id.iv_first_item_person_topright:
                Toast.makeText(UiUtilities.getContex(), "首页头像点击事件收到", Toast.LENGTH_SHORT).show();
                MainActivity activity = (MainActivity) getActivity();
                activity.startActivity(new Intent(activity, MyMaterial2Activity.class));
                break;

            case R.id.tv_first_page_shanjiajieshao: //品牌介绍
                Toast.makeText(UiUtilities.getContex(), "品牌介绍", Toast.LENGTH_SHORT).show();

                break;

            case R.id.tv_first_page_person_tel:  //tell电话
                boolean loginState = LoginManager.getInstance().isLoginState(UiUtilities.getContex());
                if (loginState){
                    User userBean = UserInformationManager.getInstance().getUserInformation();
                    Object tel = userBean.getVendor().getTel();

                    if (tel !=null){
                        //需要处理权限问题
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+tel)));

                    }else{
                        Toast.makeText(UiUtilities.getContex(), "未填写电话号码", Toast.LENGTH_SHORT).show();
                    }
                }

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
