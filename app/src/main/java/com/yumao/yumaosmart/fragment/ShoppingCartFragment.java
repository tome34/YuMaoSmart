package com.yumao.yumaosmart.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.activity.LoginActivity;
import com.yumao.yumaosmart.activity.MainActivity;
import com.yumao.yumaosmart.adapter.ShoppingCartAdapter;
import com.yumao.yumaosmart.base.BaseCartFragment;
import com.yumao.yumaosmart.bean.Shopping;
import com.yumao.yumaosmart.callback.ShoppingCartCallback;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.manager.CartManager;
import com.yumao.yumaosmart.manager.LoginManager;
import com.yumao.yumaosmart.mode.ShoppingCartMode;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.SPUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.http.okhttp.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static android.content.ContentValues.TAG;

/**
 * Created by kk on 2017/2/24.
 */

public class ShoppingCartFragment extends BaseCartFragment {

    @BindView(R.id.recyclerview_cart)
    RecyclerView mRecyclerviewCart;
    @BindView(R.id.check_box_all)
    CheckBox mCheckBoxAll;
    @BindView(R.id.tv_commit)
    TextView mTvCommit;
    @BindView(R.id.tv_total_money)
    TextView mTvTotalMoney;
    @BindView(R.id.ll_middle_view)
    LinearLayout mLlMiddleView;
    @BindView(R.id.no_freight)
    TextView mNoFreight;
    @BindView(R.id.cart_button_layout)
    LinearLayout mCartButtonLayout;
    @BindView(R.id.cart_go_login)
    TextView mCartGoLogin;
    @BindView(R.id.cart_button_sbgg)
    ImageView mCartButtonSbgg;
    @BindView(R.id.cart_empty_layout)
    LinearLayout mCartEmptyLayout;
    @BindView(R.id.smartLayout)
    SmartRefreshLayout mSmartLayout;
    private int mCid;
    private String mToken;

    private int page = 1;
    public boolean isChecked = true;  //购物车是否选中


    private List<Integer> mCartId;  //购物车id
    private List<Integer> mProductId; //产品id
    private List<String> mProductName; //产品名称
    private List<Integer> mResalePrice; //转卖价格
    private List<Integer> mPrice;       //价格
    private List<String> mThumbIv;       //item图片
    private List<Integer> mQuantity;      //产品数量

    public ShoppingCartAdapter mCartAdapter;

    public String mGetCarJsonData;  //获取购物车的所有json数据

    public ShoppingCartFragment() {

    }


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shopping_cart, null);
        return view;
    }

    @Override
    public void initData() {

        initCartData();

        //下拉加载
        Smartrefresh();
    }

    //下拉刷新
    private void Smartrefresh() {

        mSmartLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                UpDataView();

            }
        });
        mSmartLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.setEnableAutoLoadmore(false);//不监听自动加载更多
                refreshlayout.finishLoadmore(1300);
                UpDataLoadmore();
            }
        });
    }



    private void initCartData() {

        boolean loginState = LoginManager.getInstance().isLoginState(UiUtilities.getContex());
        LogUtils.d("购物车登录状态:" + loginState);
        //判断是否登录
        if (loginState) {

            page =1 ;

            mGetCarJsonData = null;
            mSmartLayout.setLoadmoreFinished(false); //可以下拉刷新

            mCartButtonLayout.setVisibility(View.VISIBLE);
            mCartGoLogin.setVisibility(View.GONE);
            mCartEmptyLayout.setVisibility(View.GONE);

            mCartId = new ArrayList<>();  //购物车id
            mProductId = new ArrayList<>(); //产品id
            mProductName = new ArrayList<>(); //产品名称
            mResalePrice = new ArrayList<>(); //转卖价格
            mPrice = new ArrayList<>();       //价格
            mThumbIv = new ArrayList<>();       //item图片
            mQuantity = new ArrayList<>();      //产品数量

            //获取用户的id
            mCid = SPUtils.getInt(UiUtilities.getContex(), Constant.USER_CID);
            //获取token
            mToken = SPUtils.getString(UiUtilities.getContex(), Constant.TOKEN);
            LogUtils.d("购物车:" + mCid + " " + "token:" + mToken);

            OkHttpUtils
                    .get()
                    .url(Constant.BASE_URL + "customers/" + mCid + "/shopping-cart-items")
                    .addHeader("X-API-TOKEN", mToken)
                    .addParams("page", String.valueOf(page))
                    .build()
                    .execute(new ShoppingCartCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                            LogUtils.d("购物车数据失败" + e + "错误码:" + mCode);
                            SPUtils.putString(UiUtilities.getContex(), Constant.CART_JSON, "");

                           // CartManager.getInstance().deleteCartData();
                            mCartButtonLayout.setVisibility(View.GONE);
                            mCartEmptyLayout.setVisibility(View.VISIBLE);


                            // Toast.makeText(mContext, "请重新登录" + e, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(List<ShoppingCartMode> response, int id) {

                            //CartManager.getInstance().deleteCartData();

                            LogUtils.d("购物车数据成功" + "条目数量:" + response.size());
                            //获取购物车的所有json数据
                            mGetCarJsonData = cartJsonData;

                            SPUtils.putString(UiUtilities.getContex(), Constant.CART_JSON, mGetCarJsonData);

                            for (int i = 0; i < response.size(); i++) {
                                mCartId.add(response.get(i).getId());
                                mProductId.add(response.get(i).getProduct().getId());
                                mProductName.add(response.get(i).getProduct().getName());
                                mResalePrice.add(response.get(i).getProduct().getResale_price());
                                mPrice.add(response.get(i).getProduct().getPrice());
                                mThumbIv.add(response.get(i).getProduct().getThumb());
                                mQuantity.add(response.get(i).getQuantity());

                            }

                            initCartView();

                        }

                    });


        } else {
            LogUtils.d("购物车:用户没登录");
            CartManager.getInstance().deleteCartData();

            mCartButtonLayout.setVisibility(View.GONE);

            mCartGoLogin.setVisibility(View.VISIBLE);

            // Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
        }


    }

    private void initCartView() {
        // String cartJson = SPUtils.getString(UiUtilities.getContex(), Constant.CART_JSON);
        CartManager.getInstance().loadLocalToMemory();

        List<Shopping> memoryAll = CartManager.getInstance().getMemoryAll();

        /*mCartAdapter = new ShoppingCartAdapter(mContext,mCartId,mProductId,mProductName
        ,mResalePrice,mPrice,mThumbIv,mQuantity);*/

        upCartData(memoryAll);
    }

    private void upCartData(List<Shopping> cartList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(UiUtilities.getContex());
        mRecyclerviewCart.setLayoutManager(layoutManager);
        mCartAdapter = new ShoppingCartAdapter(mContext, cartList);
        mRecyclerviewCart.setAdapter(mCartAdapter);

        //初始化全选部分
        EventBus.getDefault().post(new String());
    }

    //加载更多
    private void UpDataLoadmore() {

        page++ ;

        OkHttpUtils
                .get()
                .url(Constant.BASE_URL + "customers/" + mCid + "/shopping-cart-items")
                .addHeader("X-API-TOKEN", mToken)
                .addParams("page", String.valueOf(page))
                .build()
                .execute(new ShoppingCartCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        LogUtils.d("没有更多数据了");
                        mSmartLayout.setLoadmoreFinished(true);

                    }

                    @Override
                    public void onResponse(List<ShoppingCartMode> response, int id) {

                        LogUtils.d("购物车数据成功" + "条目数量:" + response.size());
                        //获取购物车的所有json数据
                        String mGetMoreCarJsonData = cartJsonData;
                        StringBuffer sb = new StringBuffer(mGetCarJsonData);
                        String substring = sb.substring(0, mGetCarJsonData.length()-1);
                           // LogUtils.d("substring:"+substring);

                        StringBuffer sb1 = new StringBuffer(mGetMoreCarJsonData);
                        //String substring1 = sb1.substring(1, mGetMoreCarJsonData.length());
                        String replace = mGetMoreCarJsonData.replace(mGetMoreCarJsonData.substring(0, 1), ",");
                           // LogUtils.d("replace:"+replace);

                        //数据拼接
                        String cartJsonData = substring +replace;
                           // LogUtils.d("拼接字符串:"+cartJsonData);

                        SPUtils.putString(UiUtilities.getContex(), Constant.CART_JSON, cartJsonData);

                        initCartView();

                    }

                });


    }


    @Override
    protected void free() {

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: 重新获取数据");
        UpDataView();
    }

    //刷新数据
    public void UpDataView() {

        initCartData();

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void initBottomUI(String s) {
        boolean allChecked = CartManager.getInstance().isAllChecked();
        //LogUtils.d("全选1:", "" + allChecked);
        mCheckBoxAll.setChecked(CartManager.getInstance().isAllChecked());

        mTvTotalMoney.setText("¥" + CartManager.getInstance().getTotalProice());


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void RefreshData(MessageData data) {

        UpDataView();
        LogUtils.d("刷新数据");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    public static class MessageData {
        public final String name;

        public MessageData(String name) {
            this.name = name;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void UpAdapter(MessageAdapter adapter) {

        List<Shopping> cars = CartManager.getInstance().getMemoryAll();//不能从网络获取数据,一丛网络获取,数据就初始化了


        upCartData(cars);

        //mCartAdapter.notifyDataSetChanged();
        LogUtils.d("adatper--");
    }

    //evenbus
    public static class MessageAdapter {
        public final String name;

        public MessageAdapter(String name) {
            this.name = name;
        }
    }


    //开启eventbus
    @Override
    public boolean isRegisterEventBus() {
        return true;
    }


    @OnClick({R.id.check_box_all, R.id.tv_commit, R.id.cart_go_login, R.id.cart_button_sbgg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.check_box_all:  //全选

                // Toast.makeText(mContext, "全选" + mCheckBoxAll.isChecked(), Toast.LENGTH_SHORT).show();


                // mMemoryAll.get(position).isChecked = !mMemoryAll.get(position).isChecked;


                mCheckBoxAll.setChecked(CartManager.getInstance().setCheckState(mCheckBoxAll.isChecked()));
                //CartManager.getInstance().setCheckState
                List<Shopping> cars = CartManager.getInstance().getMemoryAll();//不能从网络获取数据,一丛网络获取,数据就初始化了


                upCartData(cars);

                EventBus.getDefault().post(new String());
                //EventBus.getDefault().post("dd");

                break;
            case R.id.tv_commit:  //结算

                break;

            case R.id.cart_go_login:  //跳转到登录界面
                startActivity(new Intent(UiUtilities.getContex(), LoginActivity.class));

                break;
            case R.id.cart_button_sbgg:  //随便逛逛,跳转到首页
                startActivity(new Intent(UiUtilities.getContex(), MainActivity.class));

                break;
        }
    }


}
