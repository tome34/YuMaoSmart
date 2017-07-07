package com.yumao.yumaosmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.AddressAdapter;
import com.yumao.yumaosmart.base.BaseItemActivity;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.event.MyAddressEvent;
import com.yumao.yumaosmart.mode.MyAddressMode;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.SPUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by kk on 2017/3/8.
 */
public class AddressActivity extends BaseItemActivity  implements AddressAdapter.OnDeleteAddressListenner{
    @BindView(R.id.lv_activity_address_add)
    ListView mLvActivityAddressAdd;
    @BindView(R.id.btn_activity_address_add)
    Button mBtnActivityAddressAdd;
    private List mList = new ArrayList<MyAddressEvent>();
    private AddressAdapter mAddressAdapter;
    private int mAid;
    public MyAddressEvent mO;
    private  boolean fromEdit=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        initToobar(getString(R.string.title_myaddress));
        ButterKnife.bind(this);

       init();
        EventBus.getDefault().register(this);


//        initView();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnMyAddressEvent(MyAddressEvent message){
        mAid = message.getAid();
        for (int i = 0; i < mList.size(); i++) {
            mO = (MyAddressEvent) mList.get(i);
            if (mAid == mO.getAid()) {

                mO.setName(message.getName());
                mO.setPhoneNum(message.phoneNum);
                mO.setArea(message.getArea());
                mO.setDetailAddress(message.getDetailAddress());
                mO.setDefaultAddress(message.isDefaultAddress());


                        LogUtils.d("mo="+mO.name);
                        LogUtils.d("mlist="+message.name);
                    LogUtils.d("数据更改了");
                fromEdit=true;
                break;
            }

        }
        if (!fromEdit) {
            if (message.isDefaultAddress) {
                MyAddressEvent o = (MyAddressEvent) mList.get(0);
                o.isDefaultAddress = false;
                mList.add(0,message);
            } else {
                mList.add(1,message);
            }
        }
        mAddressAdapter.notifyDataSetChanged();
    }

    private void init() {

        OkHttpUtils
                .get()
                .url(Constant.BASE_URL+"customers/"+ SPUtils.getString(UiUtilities.getContex(),Constant.USER_CID)+"/addresses")
                .addHeader("X-API-TOKEN", SPUtils.getString(UiUtilities.getContex(),Constant.TOKEN))
                .addParams("cid",String.valueOf(SPUtils.getString(UiUtilities.getContex(),Constant.USER_CID)))
                .build()
                .execute(new Callback<List<MyAddressMode>>() {
                    @Override
                    public List<MyAddressMode> parseNetworkResponse(Response response, int id) throws Exception {
                        String string = response.body().string();
                        LogUtils.d("code="+response.code());
                        List o = new Gson().fromJson(string, new TypeToken<List<MyAddressMode>>() {
                        }.getType());
                        return o;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(AddressActivity.this, "还没有创建收货地址", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<MyAddressMode> response, int id) {
                        for (int i = 0; i < response.size(); i++) {
                            MyAddressMode myAddressMode = response.get(i);
                            int aid = myAddressMode.getId();
                            int state_province = myAddressMode.getState_province().getId();
                            int city_id = myAddressMode.getCity().getId();
                            int county_id=myAddressMode.getCounty_district().getId();
                            String firstName = myAddressMode.getFirst_name();
                            String last_name = myAddressMode.getLast_name();
                            if (firstName == null) {
                                firstName ="";
                            }
                            if (last_name == null) {
                                last_name ="";
                            }

                            String name=firstName+last_name;
                            String detailAddress = myAddressMode.getAddress1();
                            if (detailAddress == null) {
                                detailAddress ="";
                            }
                            String area=myAddressMode.getState_province().getName()+
                                    myAddressMode.getCity().getName()+
                                    myAddressMode.getCounty_district().getName();
                            String phone=myAddressMode.getPhone_number();

                            boolean isDefault=myAddressMode.isIs_default_address();
                            MyAddressEvent myAddressEvent = new MyAddressEvent();
                            myAddressEvent.setName(name);
                            myAddressEvent.setArea(area);
                            myAddressEvent.setDetailAddress(detailAddress);
                            myAddressEvent.setDefaultAddress(isDefault);
                            myAddressEvent.setAid(aid);
                            myAddressEvent.setState_province(state_province);
                            myAddressEvent.setCity_id(city_id);
                            myAddressEvent.setCounty_id(county_id);
                            myAddressEvent.setPhoneNum(phone);
                            mList.add(myAddressEvent);
                        }
                        setListView();
                    }
                });


    }

    private void setListView() {
        mAddressAdapter = new AddressAdapter(AddressActivity.this, R.layout.item_activity_address, mList,this);
        mLvActivityAddressAdd.setAdapter(mAddressAdapter);
        mAddressAdapter.setOndelete(new AddressAdapter.OnDeleteAddressListenner() {
            @Override
            public void onDeleteAddress(MyAddressEvent item) {

                mList.remove(item);
                mAddressAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initView() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.btn_activity_address_add)
    public void onClick() {
        startActivity(new Intent(this,MyAddressActivity.class));
    }

    @Override
    public void onDeleteAddress(MyAddressEvent item) {
        mList.remove(item);
        mAddressAdapter.notifyDataSetChanged();
    }
}
