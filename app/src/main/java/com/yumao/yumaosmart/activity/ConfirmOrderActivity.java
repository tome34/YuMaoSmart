package com.yumao.yumaosmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.activity.itemactivity.OrderDetailActivity;
import com.yumao.yumaosmart.adapter.ConfirmOrderActivityAdapter;
import com.yumao.yumaosmart.base.BaseItemActivity;
import com.yumao.yumaosmart.mode.ShoppingCardMode;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class ConfirmOrderActivity extends BaseItemActivity {

    @BindView(R.id.tv_titlle)
    TextView mTvTitlle;

    @BindView(R.id.iv_activity_confirm_title_logo)
    ImageView mIvActivityConfirmTitleLogo;
    @BindView(R.id.tv_activity_confirm_order_name)
    TextView mTvActivityConfirmOrderName;
    @BindView(R.id.tv_activity_confirm_order_phone)
    TextView mTvActivityConfirmOrderPhone;
    @BindView(R.id.tv_activity_confirm_order_address)
    TextView mTvActivityConfirmOrderAddress;
    @BindView(R.id.iv_activity_confirm_sendmethod)
    ImageView mIvActivityConfirmSendmethod;
    @BindView(R.id.rbtn_activity_confirm_order_first)
    RadioButton mRbtnActivityConfirmOrderFirst;
    @BindView(R.id.rbtn_activity_confirm_order_second)
    RadioButton mRbtnActivityConfirmOrderSecond;
    @BindView(R.id.lv_activity_confirm_order)
    ListView mLvActivityConfirmOrder;
    @BindView(R.id.iv_activity_confirm_paymethod)
    ImageView mIvActivityConfirmPaymethod;
    @BindView(R.id.iv_activity_confirm_order_paymethod_logo)
    ImageView mIvActivityConfirmOrderPaymethodLogo;
    @BindView(R.id.tv_activity_confirm_order_paymethod_text)
    TextView mTvActivityConfirmOrderPaymethodText;

    @BindView(R.id.tv_activity_confirm_order_message)
    TextView mTvActivityConfirmOrderMessage;
    @BindView(R.id.tv_activity_confirm_order_payment_title)
    TextView mTvActivityConfirmOrderPaymentTitle;
    @BindView(R.id.tv_activity_confirm_order_payment)
    TextView mTvActivityConfirmOrderPayment;
    @BindView(R.id.btn_activity_confirm_order_postorder)
    Button mBtnActivityConfirmOrderPostOrder;
    @BindView(R.id.iv_activity_confirm_order_orderlist)
    ImageView mIvActivityConfirmOrderOrderlist;
    @BindView(R.id.rbtn_activity_confirm_order_paymethod)
    RadioButton mRbtnActivityConfirmOrderPaymethod;
    boolean useWeiXin=false;
    @BindView(R.id.iv_activity_confirm_order_personinfo)
    ImageView mIvActivityConfirmOrderPersoninfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        ButterKnife.bind(this);
        initToobar(getString(R.string.title_confirm_order));
        initData();
    }

    private void initData() {

        OkHttpUtils
                .get()
                .url("https://test-dist.yumao168.com/api/customers/" + UiUtilities.getUser().getId() + "/shopping-cart-items")
                .addHeader("X-API-TOKEN", UiUtilities.getUser().getToken())
                .build()
                .execute(new Callback<List<ShoppingCardMode>>() {


                    @Override
                    public List<ShoppingCardMode> parseNetworkResponse(Response response, int id) throws Exception {
                        String string = response.body().string();
                        List<ShoppingCardMode> o = new Gson().fromJson(string, new TypeToken<List<ShoppingCardMode>>() {
                        }.getType());
                        return o;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("网络连接失败");

                    }

                    @Override
                    public void onResponse(List<ShoppingCardMode> response, int id) {
                        mLvActivityConfirmOrder.setAdapter(new ConfirmOrderActivityAdapter(UiUtilities.getContex(), R.layout.item_activity_confirm_order, response));

                    }


                });
    }

    @Override
    public Toolbar getToolBar() {
        return (Toolbar) findViewById(R.id.toolbar_activity_confirm_order);
    }


    @OnClick({R.id.rbtn_activity_confirm_order_paymethod, R.id.btn_activity_confirm_order_postorder,R.id.iv_activity_confirm_order_personinfo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_activity_confirm_order_personinfo:

                    startActivity(new Intent(this,AddressActivity.class));

                break;
            case R.id.rbtn_activity_confirm_order_paymethod:
                useWeiXin=!useWeiXin;
                ((RadioButton) view).setChecked(useWeiXin);
                break;
            case R.id.btn_activity_confirm_order_postorder:
                getDataForOrderDeatail();
                startActivity(new Intent(this, OrderDetailActivity.class));
                break;
        }
    }

    public Object getDataForOrderDeatail() {

        return null;
    }


}
