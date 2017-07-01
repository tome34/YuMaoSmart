package com.yumao.yumaosmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseItemActivity;
import com.yumao.yumaosmart.callback.UserCallback;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.mode.User;
import com.yumao.yumaosmart.utils.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by kk on 2017/3/6.
 */

public class LoginActivity extends BaseItemActivity {
    @BindView(R.id.tv_titlle)
    TextView mTvTitlle;
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.et_activity_lonin_username)
    EditText mEtActivityLoninUsername;
    @BindView(R.id.et_activity_lonin_password)
    EditText mEtActivityLoninPassword;
    @BindView(R.id.btn_activity_login)
    Button mBtnActivityLogin;
    @BindView(R.id.tv_activity_login_quikly_login)
    TextView mTvActivityLoginQuiklyLogin;
    @BindView(R.id.tv_activity_login_forget_password)
    TextView mTvActivityLoginForgetPassword;
    @BindView(R.id.wx_login)
    ImageView mWxLogin;
    private String mPassword;
    private String mPhoneNum;
    private User mUserBean;
    private IWXAPI mApi;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initToobar(getString(R.string.title_login));

        //应用id注册到微信,实现微信登录功能
        //通过wxapiFactory 工厂,获取iwapi的实例
        mApi = WXAPIFactory.createWXAPI(this , Constant.APP_ID,true);
        //将应用的appid注册到微信
        mApi.registerApp(Constant.APP_ID);
    }

    @OnClick({R.id.et_activity_lonin_username, R.id.et_activity_lonin_password, R.id.btn_activity_login, R.id.tv_activity_login_quikly_login, R.id.tv_activity_login_forget_password, R.id.wx_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_activity_lonin_username:
                break;
            case R.id.et_activity_lonin_password:
                break;
            case R.id.btn_activity_login:
                mPhoneNum = mEtActivityLoninUsername.getText().toString().trim();
                mPassword = mEtActivityLoninPassword.getText().toString().trim();

                if (TextUtils.isEmpty(mPhoneNum) || TextUtils.isEmpty(mPassword)) {
                    Toast.makeText(this, "有户名或密码不能为空", Toast.LENGTH_SHORT).show();
                } else {

                    login();
                }

                break;
            case R.id.tv_activity_login_quikly_login:
                startActivity(new Intent(this, RegistActivity.class));
                break;
            case R.id.tv_activity_login_forget_password:
                startActivity(new Intent(this, ReSetPasswordActivity.class));
                break;
            case R.id.wx_login:

               // Toast.makeText(this,"微信登录",Toast.LENGTH_LONG).show();
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo"; //授权范围
                req.state = "wechat1";
                req.openId = Constant.APP_ID;
                mApi.sendReq(req);
                break;
        }
    }

    private Boolean testPassword() {
        if (TextUtils.isEmpty(mPassword)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
        }
        return !TextUtils.isEmpty(mPassword);
    }


    private void login() {

        OkHttpUtils
                .post()
                .url(Constant.BASE_URL + "login")
                .addParams("username", mPhoneNum)
                .addParams("password", mPassword)
                .build()
                .execute(new UserCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        Toast.makeText(LoginActivity.this, "用户名或登录密码有误", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        //UiUtilities.setUser(response);
                        if (mCode == 404) {
                            Toast.makeText(LoginActivity.this, "该用户不存在,请先注册", Toast.LENGTH_SHORT).show();
                        } else if (mCode == 400) {
                            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        } else if (mCode == 403) {
                            Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                        } else if (mCode == 200) {
                            //UiUtilities.setUser(response);
                            mUserBean = new Gson().fromJson(response, User.class);
                            String token = mUserBean.getToken();
                            Toast.makeText(LoginActivity.this, "token:" + token, Toast.LENGTH_SHORT).show();
                            SPUtils.putString(LoginActivity.this, Constant.TOKEN, token);
                            SPUtils.putString(LoginActivity.this, Constant.USER_DATA, response);
                            finish();
                        }

                    }

                });
    }


    private boolean testPhone() {
        if (TextUtils.isEmpty(mPhoneNum)) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();

        } else {
            //if (isMobileNO(mPhoneNum)) {
            if (true) {
                return true;
            } else {
                Toast.makeText(this, "您输入的手机号码不存在", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

}
