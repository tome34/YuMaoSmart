package com.yumao.yumaosmart.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.itheima.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
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
import com.yumao.yumaosmart.event.PersonalCenterEvent;
import com.yumao.yumaosmart.inter.OnItemClickListener;
import com.yumao.yumaosmart.manager.LoginManager;
import com.yumao.yumaosmart.manager.UserInformationManager;
import com.yumao.yumaosmart.mode.User;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.SPUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.yumao.yumaosmart.widgit.CustomUItraRefreshHeader;
import com.zhy.http.okhttp.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import okhttp3.Call;

import static android.app.Activity.RESULT_OK;

/**
 * Created by kk on 2017/2/24.
 */

public class PersonalCenterFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tv_personnal_petname)
    TextView mTvPersonnalPetname;
    @BindView(R.id.tv_personnal_regist_time)
    TextView mTvPersonnalRegistTime;
    @BindView(R.id.iv_personnal_identity)
    ImageView mIvPersonnalIdentity;
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
    @BindView(R.id.iv_personnal_fragment_touxiang)
    RoundedImageView mIvPersonnalFragmentTouxiang;
    @BindView(R.id.tv_personnal_xiaoliang)
    TextView mTvPersonnalXiaoliang;
    @BindView(R.id.tv_personnal_zichang)
    TextView mTvPersonnalZichang;
    @BindView(R.id.iv_personnal_fragment_setting)
    ImageView mIvPersonnalFragmentSetting;
    @BindView(R.id.ultra_ptr_frame)
    PtrClassicFrameLayout mUltraPtrFrame;

    private PersonnalcenterAdapter mPersonnalcenterAdapter;
    private List<PersonnalBean> mData;
    private List<String> mItemList;
    private int itemPostion;
    private PersonnalBean mPersonnalBean;
    private View mRootView;
    private Intent mIntent;
    private Boolean mLoginState;
    private Boolean mIsCode;
    private List<String> mNameDatas;
    private List<Integer> mIconDatas;
    private String mRolesString;
    private List<String> mRoles;


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
        mIvPersonnalFragmentSetting.setOnClickListener(this);

    }

    //初始化视图
    @Override
    protected void initView() {
        /**
         * 经典 风格的头部实现
         */
        //final PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(UiUtilities.getContex());
       // header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);

        CustomUItraRefreshHeader header = new CustomUItraRefreshHeader(UiUtilities.getContex());

        mUltraPtrFrame.setHeaderView(header);
        mUltraPtrFrame.addPtrUIHandler(header);
        mUltraPtrFrame.setLastUpdateTimeRelateObject(this);
        mUltraPtrFrame.setPtrHandler(new PtrHandler() {
            /**
             * 检查是否可以执行下来刷新，比如列表为空或者列表第一项在最上面时。
             */
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);

            }
            //需要加载数据时触发
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                mUltraPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mUltraPtrFrame.refreshComplete();
                        updateView();

                    }
                },2500);
            }

        });

        // 竖直方向的网格样式，每行四个Item
        GridLayoutManager mLayoutManager = new GridLayoutManager(UiUtilities.getContex(), 4, OrientationHelper.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                //return super.canScrollVertically();
                // 直接禁止垂直滑动
                return false;
            }
        };
        //添加分割线(需要依赖recycleritemdecoration第三方库)
        // mRvPersonalCenter.addItemDecoration(new LinearDividerItemDecoration(UiUtilities.getContex(),LinearDividerItemDecoration.LINEAR_DIVIDER_VERTICAL));

        //设置布局管理器
        mRvPersonalCenter.setLayoutManager(mLayoutManager);
        //设置分割线
        //mRvPersonalCenter.addItemDecoration(new SpacesItemDecoration());

        //设置固定大小
        // mRvPersonalCenter.setHasFixedSize(true);
        //创建适配器，并且设置
        mPersonnalcenterAdapter = new PersonnalcenterAdapter(UiUtilities.getContex(), mNameDatas, mIconDatas);
        mRvPersonalCenter.setAdapter(mPersonnalcenterAdapter);

        //条目的点击事件
        mPersonnalcenterAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItenClick(View view, int position) {
                Toast.makeText(UiUtilities.getContex(), "被点击了" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongCllick(View view, int position) {

            }
        });


        /*mRvPersonalCenter.setLayoutManager(new GridLayoutManager(UiUtilities.getContex(), 4));
        mPersonnalcenterAdapter = new PersonnalcenterAdapter(UiUtilities.getContex(), R.layout.item_personnal_rv, mData, getActivity());


        mRvPersonalCenter.setAdapter(mPersonnalcenterAdapter);*/

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(UiUtilities.getContex());
    }

    @Override
    public void onResume() {
        super.onResume();
        updateView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnPersonalEvent(PersonalCenterEvent event) {
        init();
    }

    //    初始化数据
    @Override
    protected void init() {

        mNameDatas = new ArrayList<>();

        mIconDatas = new ArrayList<>();
        /*String userData = SPUtils.getString(UiUtilities.getContex(), Constant.USER_DATA);

        //判断是否登录
        if (!TextUtils.isEmpty(userData)) {
        User userBean = new Gson().fromJson(userData, User.class);
        //角色集合

            mRoles = userBean.getRoles();

            mRolesString = String.valueOf(mRoles);
        LogUtils.d("tag","角色:"+ mRolesString);*/
        //判断是否登录,如果登录就显示数据
        if (LoginManager.getInstance().isLoginState(UiUtilities.getContex())) {
            //显示头像数据
            showTouXiangData();
            //显示recyclerview
            mRvPersonalCenter.setVisibility(View.VISIBLE);

            int grade = UserInformationManager.getInstance().userGrade();
            if (grade == 1) {
                //LogUtils.d("tag","分公司");
                String[] stringArr = new String[]{"我的佣金", "我的货款", "我的销售", "会员订单", "我的收藏", "我的推广",
                        "门店资料", "中央仓", "商品管理", "城市总店", "城市分店", "城市V店"};
                for (int i = 0; i < stringArr.length; i++) {
                    mNameDatas.add(stringArr[i]);
                }
                int[] intArr = new int[]{R.mipmap.personal_icon_yj, R.mipmap.personal_icon_dk, R.mipmap.personal_icon_xs, R.mipmap.personal_icon_dd, R.mipmap.personal_icon_scj,
                        R.mipmap.personal_icon_tg, R.mipmap.personal_icon_md, R.mipmap.personal_icon_zyc, R.mipmap.personal_icon_gl, R.mipmap.personal_icon_zd,
                        R.mipmap.personal_icon_fd, R.mipmap.personal_icon_vd};
                for (int i = 0; i < intArr.length; i++) {
                    mIconDatas.add(intArr[i]);
                }
                return;

            } else if (grade == 2) {
                LogUtils.d("tag", "总店");
                String[] stringArr = new String[]{"我的佣金", "我的货款", "我的销售", "会员订单", "我的收藏", "我的推广",
                        "门店资料", "中央仓", "商品管理", "城市分店", "城市V店"};
                for (int i = 0; i < stringArr.length; i++) {
                    mNameDatas.add(stringArr[i]);
                }
                int[] intArr = new int[]{R.mipmap.personal_icon_yj, R.mipmap.personal_icon_dk, R.mipmap.personal_icon_xs, R.mipmap.personal_icon_dd, R.mipmap.personal_icon_scj,
                        R.mipmap.personal_icon_tg, R.mipmap.personal_icon_md, R.mipmap.personal_icon_zyc, R.mipmap.personal_icon_gl,
                        R.mipmap.personal_icon_fd, R.mipmap.personal_icon_vd};
                for (int i = 0; i < intArr.length; i++) {
                    mIconDatas.add(intArr[i]);
                }
                return;

            } else if (grade == 3) {
                LogUtils.d("tag", "城市分店");
                String[] stringArr = new String[]{"我的佣金", "我的销售", "会员订单", "城市V店", "我的收藏", "我的推广",
                        "门店资料"};
                for (int i = 0; i < stringArr.length; i++) {
                    mNameDatas.add(stringArr[i]);
                }
                int[] intArr = new int[]{R.mipmap.personal_icon_yj, R.mipmap.personal_icon_xs, R.mipmap.personal_icon_dd, R.mipmap.personal_icon_vd, R.mipmap.personal_icon_scj,
                        R.mipmap.personal_icon_tg, R.mipmap.personal_icon_md};
                for (int i = 0; i < intArr.length; i++) {
                    mIconDatas.add(intArr[i]);
                }
                return;

            } else if (grade == 4) {
                LogUtils.d("tag", "合伙人,v店");
                String[] stringArr = new String[]{"我的佣金", "我的销售", "我的推广", "我的收藏",
                        "门店资料"};
                for (int i = 0; i < stringArr.length; i++) {
                    mNameDatas.add(stringArr[i]);
                }
                int[] intArr = new int[]{R.mipmap.personal_icon_yj, R.mipmap.personal_icon_xs, R.mipmap.personal_icon_tg, R.mipmap.personal_icon_scj, R.mipmap.personal_icon_md};
                for (int i = 0; i < intArr.length; i++) {
                    mIconDatas.add(intArr[i]);
                }
                return;
            } else {
                LogUtils.d("tag", "普通会员");
                String[] stringArr = new String[]{"我的收藏"};
                for (int i = 0; i < stringArr.length; i++) {
                    mNameDatas.add(stringArr[i]);
                }
                int[] intArr = new int[]{R.mipmap.personal_icon_scj};
                for (int i = 0; i < intArr.length; i++) {
                    mIconDatas.add(intArr[i]);
                }
                return;
            }


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
        } else {

            mTvPersonnalPetname.setText("登录/注册");
            mTvPersonnalPetname.setTextColor(Color.WHITE);
            mTvPersonnalPetname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });

            mTvPersonnalRegistTime.setVisibility(View.GONE);
            mIvPersonnalIdentity.setVisibility(View.GONE);
            mTvPersonnalXiaoliang.setVisibility(View.INVISIBLE);
            mTvPersonnalZichang.setVisibility(View.INVISIBLE);
            //隐藏recyclerview
            mRvPersonalCenter.setVisibility(View.GONE);


            //设置头像
            mIvPersonnalFragmentTouxiang.setImageResource(R.mipmap.first_page_person_icon_touxiang);


        }
    }

    //显示个人中心头像数据
    private void showTouXiangData() {

        mTvPersonnalRegistTime.setVisibility(View.VISIBLE);
        mIvPersonnalIdentity.setVisibility(View.VISIBLE);
        mTvPersonnalXiaoliang.setVisibility(View.VISIBLE);
        mTvPersonnalZichang.setVisibility(View.VISIBLE);

        //User userInformation = UserInformationManager.getInstance().getUserInformation();
        String userData = SPUtils.getString(UiUtilities.getContex(), Constant.USER_DATA);
        User userInformation = new Gson().fromJson(userData, User.class);
        //设置个人中心的头像
        String touxiangUrl = SPUtils.getString(UiUtilities.getContex(), Constant.AVATAR_URL);
        LogUtils.d("tag", "url:" + touxiangUrl);
        Picasso.with(UiUtilities.getContex()).load(touxiangUrl).placeholder(R.mipmap.first_page_person_icon_touxiang).into(mIvPersonnalFragmentTouxiang);

        //用户名称
        String nick_name = SPUtils.getString(UiUtilities.getContex(), Constant.NICK_NAME);
        LogUtils.d("tag", "名称:" + nick_name);
        mTvPersonnalPetname.setText(nick_name);

        //注册时间
        String created_on_utc = userInformation.getCreated_on_utc();
        LogUtils.d("tag", "时间:" + created_on_utc);
        String createdOnUtc = created_on_utc.substring(0, 10);
        mTvPersonnalRegistTime.setText("注册时间:" + createdOnUtc);
        LogUtils.d("tag", "全部:" + userData);

        //累计销量
        int cumulative_sales = userInformation.getCumulative_sales();
        mTvPersonnalXiaoliang.setText("累计销量: " + cumulative_sales);

        //累计资产
        int cumulative_assets = userInformation.getCumulative_assets();
        mTvPersonnalZichang.setText("累计资产: " + cumulative_assets);

        //用户等级
        int anInt = SPUtils.getInt(UiUtilities.getContex(), Constant.USER_GRADE);
        if (anInt == 1) {
            mIvPersonnalIdentity.setImageResource(R.mipmap.personal_btn_fgs);
        } else if (anInt == 2) {
            mIvPersonnalIdentity.setImageResource(R.mipmap.personal_btn_cszd);
        } else if (anInt == 3) {
            mIvPersonnalIdentity.setImageResource(R.mipmap.personal_btn_csfd);
        } else if (anInt == 4) {
            mIvPersonnalIdentity.setImageResource(R.mipmap.personal_btn_pphhr);
        } else {
            mIvPersonnalIdentity.setImageResource(R.mipmap.personal_btn_zchh);
        }

    }

    @Override
    protected void onCreateViewBefore() {
       /* ((MainActivity)getContext()).getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = ((MainActivity)getContext()).getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(Color.TRANSPARENT);
    }*/
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
            case R.id.iv_personnal_fragment_setting:  //个人中心的设置

            case R.id.iv_personnal_fragment_touxiang:

                //Toast.makeText(UiUtilities.getContex(), "个人中心头像被点击", Toast.LENGTH_SHORT).show();
                //调转到登录界面
                //判断是否登录
                if (LoginManager.getInstance().isLoginState(getActivity())) {
                    //跳转到我的资料
                    mIntent = new Intent(getActivity(), MyMaterial2Activity.class);
                    startActivity(mIntent);

                } else {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), 111);
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
                Toast.makeText(UiUtilities.getContex(), "退换货", Toast.LENGTH_SHORT).show();
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


    public Boolean isNetQueue(String token) {
        OkHttpUtils.post().url(Constant.BASE_URL + "token").addParams("X-API-TOKEN", token).build().execute(new UserCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("tag", e);
                mIsCode = false;
            }

            @Override
            public void onResponse(String response, int id) {

                if (mCode == 200) {
                    mIsCode = true;
                } else {
                    mIsCode = false;
                }
            }
        });

        return mIsCode;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111) {
            if (resultCode == RESULT_OK) {
                updateView();
            }
        }
    }

    private void updateView() {
        init();
    }


}