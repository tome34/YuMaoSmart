package com.yumao.yumaosmart.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.activity.LoginActivity;
import com.yumao.yumaosmart.activity.MyMaterial2Activity;
import com.yumao.yumaosmart.activity.MyOrderListActivity;
import com.yumao.yumaosmart.adapter.PersonnalcenterAdapter;
import com.yumao.yumaosmart.base.LoadingPager;
import com.yumao.yumaosmart.bean.MyOrderlistBean;
import com.yumao.yumaosmart.bean.PersonnalBean;
import com.yumao.yumaosmart.callback.UserCallback;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.SPUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by kk on 2017/2/24.
 */

public class PersonalCenterFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.iv_personnal_fragment_touxiang)
    ImageView mIvPersonnalFragmentTouxiang;
    @BindView(R.id.tv_personnal_petname)
    TextView mTvPersonnalPetname;
    @BindView(R.id.tv_personnal_regist_time)
    TextView mTvPersonnalRegistTime;
    @BindView(R.id.tv_personnal_identity)
    TextView mTvPersonnalIdentity;
    @BindView(R.id.iv_personnal_daifukuan)
    ImageView mIvPersonnalDaifukuan;
    @BindView(R.id.iv_personnal_daifahuo)
    ImageView mIvPersonnalDaifahuo;
    @BindView(R.id.iv_personnal_daishouhuo)
    ImageView mIvPersonnalDaishouhuo;
    @BindView(R.id.iv_personnal_yiwancheng)
    ImageView mIvPersonnalYiwancheng;
    @BindView(R.id.iv_personnal_tuihuanhuo)
    ImageView mIvPersonnalTuihuanhuo;
    @BindView(R.id.rv_personal_center)
    RecyclerView mRvPersonalCenter;
    @BindView(R.id.iv_personnal_fragment_lookfororder1)
    TextView mIvPersonnalFragmentLookfororder1;
    @BindView(R.id.iv_personnal_fragment_lookfororder2)
    ImageView mIvPersonnalFragmentLookfororder2;
    private PersonnalcenterAdapter mPersonnalcenterAdapter;
    private List<PersonnalBean> mData;
    private List<String> mItemList;
    private int itemPostion;
    private PersonnalBean mPersonnalBean;
    private View mRootView;
    private Intent mIntent;
    private Boolean mLoginState;
    private Boolean mIsCode;

    //    初始化监听
    @Override

    protected void initListenner() {
        mIvPersonnalDaifahuo.setOnClickListener(this);
        mIvPersonnalDaifukuan.setOnClickListener(this);
        mIvPersonnalDaishouhuo.setOnClickListener(this);
        mIvPersonnalFragmentTouxiang.setOnClickListener(this);
        mIvPersonnalTuihuanhuo.setOnClickListener(this);
        mIvPersonnalYiwancheng.setOnClickListener(this);
        mIvPersonnalFragmentLookfororder1.setOnClickListener(this);
        mIvPersonnalFragmentLookfororder2.setOnClickListener(this);

    }

    //初始化视图
    @Override
    protected void initView() {

        // 竖直方向的网格样式，每行四个Item

        GridLayoutManager mLayoutManager = new GridLayoutManager(UiUtilities.getContex() ,4,OrientationHelper.VERTICAL,false);
        mRvPersonalCenter.setLayoutManager(mLayoutManager);
        mRvPersonalCenter.setAdapter(mPersonnalcenterAdapter);


        /*mRvPersonalCenter.setLayoutManager(new GridLayoutManager(UiUtilities.getContex(), 4));
        mPersonnalcenterAdapter = new PersonnalcenterAdapter(UiUtilities.getContex(), R.layout.item_personnal_rv, mData, getActivity());


        mRvPersonalCenter.setAdapter(mPersonnalcenterAdapter);*/

    }

    //    初始化数据
    @Override
    protected void init() {
        mData = new ArrayList<>();
     /*   mPersonnalBean = new PersonnalBean("我的资料", String.valueOf(R.mipmap.personnal_my_material));
        mData.add(mPersonnalBean);
        mPersonnalBean = new PersonnalBean("我的佣金", String.valueOf(R.mipmap.personnal_my_employree));
        mData.add(mPersonnalBean);
        mPersonnalBean = new PersonnalBean("会员订单", String.valueOf(R.mipmap.personnalcenter_vip_orderlist));
        mData.add(mPersonnalBean);
        mPersonnalBean = new PersonnalBean("我的人气", String.valueOf(R.mipmap.personnal_my_populatrity));
        mData.add(mPersonnalBean);
        mPersonnalBean = new PersonnalBean("我的推广", String.valueOf(R.mipmap.personnal_center_my_spread));
        mData.add(mPersonnalBean);
        mPersonnalBean = new PersonnalBean("门店订单", String.valueOf(R.mipmap.personnal_stores_orderlistl));
        mData.add(mPersonnalBean);
        mPersonnalBean = new PersonnalBean("我的会员", String.valueOf(R.mipmap.personnal_center_my_vip));
        mData.add(mPersonnalBean);
        mPersonnalBean = new PersonnalBean("门店资料", String.valueOf(R.mipmap.personnal_my_material));
        mData.add(mPersonnalBean);
        mPersonnalBean = new PersonnalBean("服务中心", String.valueOf(R.mipmap.personnalcenter_my_partner));
        mData.add(mPersonnalBean);*/


        mItemList = new ArrayList<>();
        mItemList.add("daifukuan");
        mItemList.add("daifahuo");
        mItemList.add("daishouhuo");
        mItemList.add("yiwancheng");
        mItemList.add("tuihuanhuo");
    }

    @Override
    public LoadingPager.LoadingPagerEnum onInitData() {
        return LoadingPager.LoadingPagerEnum.SUCCESS;
    }

    @Override
    public View onInitView() {
        View view = View.inflate(UiUtilities.getContex(), R.layout.fragment_personal_center, null);

        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View mRootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }


    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_personnal_fragment_lookfororder1:
            case R.id.iv_personnal_fragment_lookfororder2:
                itemPostion = 0;
                jump2OrderListActivity();
                break;
            case R.id.iv_personnal_fragment_touxiang:

                Toast.makeText(UiUtilities.getContex(), "个人中心头像被点击", Toast.LENGTH_SHORT).show();
                //调转到登录界面
                //判断是否登录
                if (isLoginState()) {
                    //跳转到我的资料
                    mIntent = new Intent(getActivity(), MyMaterial2Activity.class);

                    startActivity(mIntent);

                } else {
                    mIntent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(mIntent);
                }

                break;
            case R.id.iv_personnal_daifukuan:
//                待付款
                itemPostion = 1;
                jump2OrderListActivity();


                break;
            case R.id.iv_personnal_daifahuo:
                itemPostion = 2;
                jump2OrderListActivity();
                break;
            case R.id.iv_personnal_daishouhuo:
                itemPostion = 3;
                jump2OrderListActivity();
                break;
            case R.id.iv_personnal_yiwancheng:
                itemPostion = 4;
                jump2OrderListActivity();
                break;
            case R.id.iv_personnal_tuihuanhuo:
                itemPostion = 0;
                jump2OrderListActivity();
                break;

        }
    }

    private void jump2OrderListActivity() {
        Activity activity = getActivity();
        Intent intent = new Intent(activity, MyOrderListActivity.class);
        Bundle bundle = new Bundle();
        MyOrderlistBean myOrderlistBean = new MyOrderlistBean(mItemList.get(itemPostion), itemPostion);
        bundle.putParcelable("key", myOrderlistBean);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public boolean isLoginState() {


        String token = SPUtils.getString(getActivity(), Constant.TOKEN, null);
        LogUtils.d("token:" + token);
        if (!TextUtils.isEmpty(token) ) {

            mLoginState = true;
        } else {

            mLoginState = false;

        }
        return mLoginState;



    }

    public Boolean isNetQueue(String token) {
        OkHttpUtils.post().url(Constant.BASE_URL + "token").addParams("X-API-TOKEN", token).build().execute(new UserCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                    LogUtils.d("tag",e);
                mIsCode=false;
            }

            @Override
            public void onResponse(String response, int id) {

                if (mCode ==200){
                    mIsCode = true ;
                }else {
                    mIsCode=false ;
                }
            }
        });

        return mIsCode;
    }

}