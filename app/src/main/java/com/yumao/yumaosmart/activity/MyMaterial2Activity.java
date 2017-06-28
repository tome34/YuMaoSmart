package com.yumao.yumaosmart.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseItemActivity;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.mode.User;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.SPUtils;
import com.yumao.yumaosmart.widgit.MyMaterialItemView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMaterial2Activity extends BaseItemActivity implements View.OnClickListener {


    @BindView(R.id.tv_titlle)
    TextView mTvTitlle;
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.tv_mymaterial_title)
    TextView mTvMymaterialLogo;
    @BindView(R.id.iv_my_material_touxiang)
    ImageView mIvMyMaterialTouxiang;
    @BindView(R.id.activity_my_material_nick_name)
    MyMaterialItemView mActivityMyMaterialNickName;
    @BindView(R.id.activity_my_material_phone)
    MyMaterialItemView mActivityMyMaterialPhone;
    @BindView(R.id.activity_my_material_address)
    MyMaterialItemView mActivityMyMaterialAddress;
    @BindView(R.id.activity_my_material_gender)
    MyMaterialItemView mActivityMyMaterialGender;
    @BindView(R.id.activity_my_material_data_of_birth)
    MyMaterialItemView mActivityMyMaterialDataOfBirth;
    @BindView(R.id.activity_my_material_id)
    MyMaterialItemView mActivityMyMaterialId;
    @BindView(R.id.activity_my_material_real_name)
    MyMaterialItemView mActivityMyMaterialRealName;
    @BindView(R.id.activity_my_material_reset_password)
    MyMaterialItemView mActivityMyMaterialResetPassword;
    @BindView(R.id.activity_my_material2)
    LinearLayout mActivityMyMaterial2;
    @BindView(R.id.layout_mymaterial_touxiong)
    RelativeLayout mLayoutMymaterialTouxiong;
    @BindView(R.id.activity_my_material_button_back)
    Button mActivityMyMaterialButtonBack;


    private Context mContext;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_material2);

        ButterKnife.bind(this);
        initToobar("我的资料");

        initView();
    }

    private void initView() {
        mContext = MyMaterial2Activity.this;
        String userData = SPUtils.getString(MyMaterial2Activity.this, Constant.USER_DATA);
        if (TextUtils.isEmpty(userData)) {
            LogUtils.d("tag", "用户信息为空:");
        } else {
            User userBean = new Gson().fromJson(userData, User.class);
            String token = userBean.getToken();
            LogUtils.d("tag", "token:" + token);
            //设置头像
            String logoUrl = userBean.getVendor().getLogo();
            Picasso.with(mContext).load(logoUrl).into(mIvMyMaterialTouxiang);
            mLayoutMymaterialTouxiong.setOnClickListener(this);

            //设置昵称
            TextView nickName = (TextView) mActivityMyMaterialNickName.findViewById(R.id.tv_my_material_other_content);
            nickName.setText(userBean.getNick_name());
            mActivityMyMaterialNickName.setOnClickListener(this);

            //手机号码
            TextView phone = (TextView) mActivityMyMaterialPhone.findViewById(R.id.tv_my_material_other_content);
            phone.setText(userBean.getPhone());
            mActivityMyMaterialPhone.setOnClickListener(this);

            //我的收货地址
            TextView address = (TextView) mActivityMyMaterialAddress.findViewById(R.id.tv_my_material_other_content);
            address.setText("");
            mActivityMyMaterialAddress.setOnClickListener(this);

            //性别
            TextView gender = (TextView) mActivityMyMaterialGender.findViewById(R.id.tv_my_material_other_content);
            if (userBean.getGender().equals("M")) {
                gender.setText("男");
            } else {
                gender.setText("女");
            }
            mActivityMyMaterialGender.setOnClickListener(this);

            //生日
            TextView dataOfBirth = (TextView) mActivityMyMaterialDataOfBirth.findViewById(R.id.tv_my_material_other_content);
            dataOfBirth.setText(userBean.getDate_of_birth());
            mActivityMyMaterialDataOfBirth.setOnClickListener(this);

            //我的推荐码
            TextView id = (TextView) mActivityMyMaterialId.findViewById(R.id.tv_my_material_other_content);
            id.setText(userBean.getId() + "");

            //实名认证
            TextView realName = (TextView) mActivityMyMaterialRealName.findViewById(R.id.tv_my_material_other_content);
            realName.setText("未认证");
            mActivityMyMaterialRealName.setOnClickListener(this);

            //重置密码
            TextView resetPassword = (TextView) mActivityMyMaterialResetPassword.findViewById(R.id.tv_my_material_other_content);
            resetPassword.setText("");
            mActivityMyMaterialResetPassword.setOnClickListener(this);

            //退出当前登录
            mActivityMyMaterialButtonBack.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.layout_mymaterial_touxiong:

                break;
            case R.id.activity_my_material_nick_name:
                mIntent = new Intent(this ,NickNameActivity.class);
                startActivity(mIntent);
                break;
            case R.id.activity_my_material_phone:
                mIntent = new Intent(this ,PhoneNumberActivity.class);
                startActivity(mIntent);
                break;
            case R.id.activity_my_material_address:

                break;
            case R.id.activity_my_material_gender:

                break;
            case R.id.activity_my_material_data_of_birth:

                break;
            case R.id.activity_my_material_real_name:
                mIntent = new Intent(this ,RealNameActivity.class);
                startActivity(mIntent);
                break;

            case R.id.activity_my_material_reset_password:
                mIntent = new Intent(this ,ReSetPasswordActivity.class);
                startActivity(mIntent);
                break;

            //退出当前登录
            case R.id.activity_my_material_button_back:
                SPUtils.putString(mContext ,Constant.TOKEN,null);
                SPUtils.putString(mContext ,Constant.USER_DATA,null);
                finish();
                break;

        }
    }
}
