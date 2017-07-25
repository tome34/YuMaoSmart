package com.yumao.yumaosmart.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.ShoppingCartAdapter;
import com.yumao.yumaosmart.base.BaseCartFragment;
import com.yumao.yumaosmart.callback.ShoppingCartCallback;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.manager.LoginManager;
import com.yumao.yumaosmart.mode.ShoppingCartMode;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.SPUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

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
    private int mCid;
    private String mToken;
    private int page = 1 ;
    public boolean isChecked = true;  //购物车是否选中

    private List<Integer> mCartId ;  //购物车id
    private List<Integer> mProductId ; //产品id
    private List<String> mProductName ; //产品名称
    private List<Integer> mResalePrice ; //转卖价格
    private List<Integer> mPrice ;       //价格
    private List<String> mThumbIv ;       //item图片
    private List<Integer> mQuantity;      //产品数量
    private List<Boolean> mCheckBox ;     //checkBox的状态

    private ShoppingCartAdapter mCartAdapter ;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shopping_cart, null);
        return view;
    }

    @Override
    public void initData() {
        //下拉加载
        //Smartrefresh();
        initCartData();

    }

    private void initCartData() {

        //判断是否登录
        if (LoginManager.getInstance().isLoginState(UiUtilities.getContex())){

            mCartId = new ArrayList<>();  //购物车id
            mProductId = new ArrayList<>(); //产品id
            mProductName = new ArrayList<>(); //产品名称
            mResalePrice = new ArrayList<>(); //转卖价格
            mPrice = new ArrayList<>();       //价格
            mThumbIv = new ArrayList<>();       //item图片
            mQuantity= new ArrayList<>();      //产品数量
            mCheckBox = new ArrayList<>();     //checkBox的状态

            //获取用户的id
            mCid = SPUtils.getInt(UiUtilities.getContex(), Constant.USER_CID);
            //获取token
            mToken = SPUtils.getString(UiUtilities.getContex(), Constant.TOKEN);


            OkHttpUtils
                    .get()
                    .url(Constant.BASE_URL+"customers/"+mCid+"/shopping-cart-items")
                    .addHeader("X-API-TOKEN",mToken)
                    .addParams("page",String.valueOf(page))
                    .build()
                    .execute(new ShoppingCartCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            LogUtils.d("购物车数据失败");
                            Toast.makeText(mContext, "请重新登录", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(List<ShoppingCartMode> response, int id) {
                            LogUtils.d("购物车数据成功"+"条目数量:"+response.size());

                            for (int i = 0;i<response.size();i++){
                                mCartId.add(response.get(i).getId());
                                mProductId.add(response.get(i).getProduct().getId());
                                mProductName.add(response.get(i).getProduct().getName());
                                mResalePrice.add(response.get(i).getProduct().getResale_price());
                                mPrice.add(response.get(i).getProduct().getPrice());
                                mThumbIv.add(response.get(i).getProduct().getThumb());
                                mQuantity.add(response.get(i).getQuantity());

                                //初始化checkBox
                                mCheckBox.add(false);
                            }
                            initCartView();

                        }

                    });


        }else{
            LogUtils.d("购物车:用户没登录");
            Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
        }


    }

    private void initCartView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(UiUtilities.getContex());
        mRecyclerviewCart.setLayoutManager(layoutManager);

        mCartAdapter = new ShoppingCartAdapter(mContext,mCartId,mProductId,mProductName
        ,mResalePrice,mPrice,mThumbIv,mQuantity,mCheckBox);

        mRecyclerviewCart.setAdapter(mCartAdapter);


    }

/*    //下拉刷新
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
                refreshlayout.finishLoadmore(1300);
                //UpDataLoadmore();
            }
        });
    }*/

    @Override
    protected void free() {

    }

    //刷新数据
    public void UpDataView() {

    }


    //开启eventbus
    @Override
    public boolean isRegisterEventBus() {
        return false;
    }


    @OnClick({R.id.check_box_all, R.id.tv_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.check_box_all:  //全选


                break;
            case R.id.tv_commit:  //结算

                break;
        }
    }









}
