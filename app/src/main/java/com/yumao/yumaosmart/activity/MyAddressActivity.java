package com.yumao.yumaosmart.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseItemActivity;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.event.MyAddressEvent;
import com.yumao.yumaosmart.mode.CityAddressMode;
import com.yumao.yumaosmart.utils.GetJsonDataUtil;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.StringUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * Created by kk on 2017/3/8.
 */
public class MyAddressActivity extends BaseItemActivity {
    @BindView(R.id.edit_activity_myaddress_shouhuoren)
    EditText mEditActivityMyaddressShouhuoren;
    @BindView(R.id.edit_activity_myaddress_phonenum)
    EditText mEditActivityMyaddressPhonenum;
    @BindView(R.id.edit_activity_myaddress_send_area)
    EditText mEditActivityMyaddressSendArea;
    @BindView(R.id.iv_activity_myaddress_send_area)
    ImageView mIvActivityMyaddressSendArea;
    @BindView(R.id.edit_activity_myaddress_detail_area)
    EditText mEditActivityMyaddressDetailArea;
    @BindView(R.id.btn_activity_myaddress_save_address)
    Button mBtnActivityMyaddressSaveAddress;
    @BindView(R.id.rbtn_activity_myaddress_isdefault)
    RadioButton mRadioButton;


    private ArrayList<CityAddressMode> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private ArrayList<CityAddressMode> options1ItemsID = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2ItemsID = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3ItemsID = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private  boolean isChecked=false;
    private boolean isLoaded = false;
    private OptionsPickerView mPvOptions;
    private String mReceiveMan;
    private String mPhone;
    private String mArea;
    private String mDetailAddress;
    private String mProvinceID;
    private String mCityID;
    private String mCountyID;
    private  String mAid;
    private String mAreaResult;
    private String mAddress;
    private Map<String,String> map;
    private MyAddressEvent mEditAddress;
    private int  updateOrCreat=Constant.ADDRESS_CREATE ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_myaddress);
        initToobar(getString(R.string.title_add_address));
        ButterKnife.bind(this);
       setAddress();
        map = new HashMap<>();

    }
//获取编辑地址传递过来的信息
    private void setAddress() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mEditAddress = extras.getParcelable("EditAddress");
        }

        if (mEditAddress != null) {
            mEditActivityMyaddressShouhuoren.setText(mEditAddress.getName());
            mEditActivityMyaddressSendArea.setText(mEditAddress.getArea());
            mEditActivityMyaddressDetailArea.setText(mEditAddress.getDetailAddress());
            mEditActivityMyaddressPhonenum.setText(mEditAddress.getPhoneNum());
            mRadioButton.setChecked(mEditAddress.isDefaultAddress);
            updateOrCreat = Constant.ADDRESS_UPDATE;
            mProvinceID=String.valueOf(mEditAddress.getState_province());
            mCityID = String.valueOf(mEditAddress.getCity_id());
            mCountyID = String.valueOf(mEditAddress.getCounty_id());
            mAid = String.valueOf(mEditAddress.getAid());
            LogUtils.d("area" + mEditAddress.getArea());
            LogUtils.d("detail" + mEditAddress.getDetailAddress());
        } else {
            updateOrCreat=Constant.ADDRESS_CREATE;
        }
    }

    //    根据Handler信息处理信息,在子线程解析数据在主线程更新UI
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread==null){//如果已创建就不再重新创建子线程了
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:

                    isLoaded = true;
                    ShowPickerView();
                    break;

                case MSG_LOAD_FAILED:

                    break;

            }
        }
    };
    @OnClick({R.id.iv_activity_myaddress_send_area, R.id.btn_activity_myaddress_save_address,R.id.rbtn_activity_myaddress_isdefault})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_activity_myaddress_send_area:
                if (!isLoaded) {
                    mHandler.sendEmptyMessage(MSG_LOAD_DATA);
                } else {
                    ShowPickerView();
                }
                break;
            case R.id.btn_activity_myaddress_save_address:

                    saveAddaress();


                break;
            case R.id.rbtn_activity_myaddress_isdefault:
                isChecked=!isChecked;

                mRadioButton.setChecked(isChecked);
                break;

        }
    }


    //        保存收货地址
    private void saveAddaress() {
        LogUtils.d("id="+mProvinceID+"..."+mCityID+"..."+mCountyID);
        mReceiveMan = mEditActivityMyaddressShouhuoren.getText().toString().trim();
        mPhone = mEditActivityMyaddressPhonenum.getText().toString().trim();
        mArea = mEditActivityMyaddressSendArea.getText().toString().trim();
        mDetailAddress = mEditActivityMyaddressDetailArea.getText().toString().trim();
        if (TextUtils.isEmpty(mReceiveMan) ||
                TextUtils.isEmpty(mPhone) ||
                TextUtils.isEmpty(mDetailAddress) ||
                TextUtils.isEmpty(mArea)) {
            Toast.makeText(this, "请填写详细的收货人信息", Toast.LENGTH_SHORT).show();

        } else {
            if (StringUtils.isMobileNO(mPhone)) {

                mAddress = mAreaResult+mDetailAddress;
                map.put("country_id","21");
                map.put("state_province_id",""+mProvinceID);
                map.put("city_id",""+mCityID);
                map.put("county_district_id",mCountyID);
                map.put("phone_number",mPhone);
                map.put("first_name", mReceiveMan);
                map.put("last_name","");
                map.put("address1", mDetailAddress);
                map.put("is_default_address",String.valueOf(isChecked));
                if (updateOrCreat == Constant.ADDRESS_CREATE) {
                    postAddress();
                } else {
                        LogUtils.d("aid="+mAid);
                    updateAddress();

                }

            } else {
                Toast.makeText(this, "手机号码不存在请重新输入", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void method(){
        FormBody body = new FormBody.Builder()
                .add("first_name","方铭更新")
                .add("address1","方铭的地址")
                .build();



        OkHttpUtils
                .put()
                .url(Constant.BASE_URL+"customers/"+1323189+"/addresses/"+2798)
                .addHeader("X-API-TOKEN",UiUtilities.getUser().getToken())
                .requestBody(body)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                LogUtils.d("put失败e="+e.getMessage());
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                                LogUtils.d("put成功");
                    }
                });

    }
/*//    /api/customers/{cid}/addresses/{aid}
//    map.put("country_id","21");
    map.put("state_province_id",""+mProvinceID);
    map.put("city_id",""+mCityID);
    map.put("county_district_id",mCountyID);
    map.put("phone_number",mPhone);
    map.put("first_name", mReceiveMan);
    map.put("last_name","");
    map.put("address1", mDetailAddress);
    map.put("is_default_address",String.valueOf(isChecked));*/
   private void updateAddress() {
       FormBody body = new FormBody.Builder()
               .add("country_id","21")
               .add("state_province_id",mProvinceID)
               .add("city_id",mCityID)
               .add("county_district_id",mCountyID)
               .add("first_name",mReceiveMan)
               .add("last_name","")
               .add("address1",mDetailAddress)
               .add("is_default_address",String.valueOf(isChecked))
               .add("phone_number",mPhone)
               .build();
        OkHttpUtils
                .put()
                .url(Constant.BASE_URL + "customers/" + UiUtilities.getUser().getId() + "/addresses/" + mAid)
                .addHeader("X-API-TOKEN", UiUtilities.getUser().getToken())
                .requestBody(body)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                            LogUtils.d("更新地址code="+response.code());
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(MyAddressActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                            LogUtils.d("更新地址失败"+e.getMessage());
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                            LogUtils.d("更新成功");
                        MyAddressEvent myAddressEvent = new MyAddressEvent();
                        myAddressEvent.setName(mReceiveMan);
                        myAddressEvent.setArea(mArea);
                        myAddressEvent.setDetailAddress(mDetailAddress);
                        myAddressEvent.setPhoneNum(mPhone);
                        myAddressEvent.setDefaultAddress(isChecked);
                        myAddressEvent.setAid(Integer.parseInt(mAid));

                        EventBus.getDefault().post(myAddressEvent);
                        finish();
                    }
                });
    }

    private void postAddress() {

        OkHttpUtils
                .post()
                .url("https://dist.yumao168.com/api/customers/"+UiUtilities.getUser().getId()+"/addresses")
                .addHeader("X-API-TOKEN",UiUtilities.getUser().getToken())
                .params(map)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(MyAddressActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        MyAddressEvent myAddressEvent = new MyAddressEvent();
                        myAddressEvent.setName(mReceiveMan);
                        myAddressEvent.setArea(mAreaResult);
                        myAddressEvent.setDetailAddress(mDetailAddress);
                        myAddressEvent.setPhoneNum(mPhone);
                        myAddressEvent.setDefaultAddress(isChecked);
//                        mReceiveMan,mAreaResult,mDetailAddress,mPhone,isChecked
                        EventBus.getDefault().post(myAddressEvent);
                         finish();
                    }
                });
    }

    //        显示城市列表
    private void ShowPickerView() {// 弹出选择器

        OptionsPickerView  pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
             //返回的分别是三个级别的选中位置
                mAreaResult = options1Items.get(options1).getPickerViewText()+
                        options2Items.get(options1).get(options2)+
                        options3Items.get(options1).get(options2).get(options3);
                mProvinceID = options1Items.get(options1).getProvinceid();
                mCityID = options2ItemsID.get(options1).get(options2);
                mCountyID = options3ItemsID.get(options1).get(options2).get(options3);
               mEditActivityMyaddressSendArea.setText(mAreaResult);


            }
        })      .setSubmitText("确认")
                .setCancelText("取消")
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(false)// default is true
                .build();

        pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器

        pvOptions.show();
    }

//    解析json数据


   private void  initJsonData(){
       String json = new GetJsonDataUtil().getJson(this, "citylist.json");

       ArrayList<CityAddressMode> jsonBean = new Gson().fromJson(json, new TypeToken<ArrayList<CityAddressMode>>() {
       }.getType());


       /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

       for (int i=0;i<jsonBean.size();i++){//遍历省份
           ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
           ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

           for (int c=0; c<jsonBean.get(i).getCity().size(); c++){//遍历该省份的所有城市
               String CityName = jsonBean.get(i).getCity().get(c).getName();
               CityList.add(CityName);//添加城市

               ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

               //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
               if (jsonBean.get(i).getCity().get(c).getArea() == null
                       ||jsonBean.get(i).getCity().get(c).getArea().size()==0) {
                   City_AreaList.add("");
               }else {

                   for (int d=0; d < jsonBean.get(i).getCity().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                       String AreaName = jsonBean.get(i).getCity().get(c).getArea().get(d).getName();

                       City_AreaList.add(AreaName);//添加该城市所有地区数据
                   }
               }
               Province_AreaList.add(City_AreaList);//添加该省所有地区数据
           }

           /**
            * 添加城市数据
            */
           options2Items.add(CityList);

           /**
            * 添加地区数据
            */
           options3Items.add(Province_AreaList);
       }

//获取id数据
       options1ItemsID = jsonBean;

       for (int i=0;i<jsonBean.size();i++){//遍历省份
           ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
           ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

           for (int c=0; c<jsonBean.get(i).getCity().size(); c++){//遍历该省份的所有城市
               String CityName = jsonBean.get(i).getCity().get(c).getCityid();
               CityList.add(CityName);//添加城市

               ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

               //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
               if (jsonBean.get(i).getCity().get(c).getArea() == null
                       ||jsonBean.get(i).getCity().get(c).getArea().size()==0) {
                   City_AreaList.add("");
               }else {

                   for (int d=0; d < jsonBean.get(i).getCity().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                       String AreaName = jsonBean.get(i).getCity().get(c).getArea().get(d).getCountyid();

                       City_AreaList.add(AreaName);//添加该城市所有地区数据
                   }
               }
               Province_AreaList.add(City_AreaList);//添加该省所有地区数据
           }

           /**
            * 添加城市数据
            */
           options2ItemsID.add(CityList);

           /**
            * 添加地区数据
            */
           options3ItemsID.add(Province_AreaList);
       }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);


   }




}
