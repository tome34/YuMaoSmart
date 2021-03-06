package com.yumao.yumaosmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseItemActivity;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.manager.LoginManager;
import com.yumao.yumaosmart.mode.User;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.UiUtilities;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kk on 2017/3/6.
 */

public class LoginActivity extends BaseItemActivity {
    @BindView(R.id.tv_titlle)
    TextView  mTvTitlle;
    @BindView(R.id.my_toolbar)
    Toolbar   mMyToolbar;
    @BindView(R.id.et_activity_lonin_username)
    EditText  mEtActivityLoninUsername;
    @BindView(R.id.et_activity_lonin_password)
    EditText  mEtActivityLoninPassword;
    @BindView(R.id.btn_activity_login)
    Button    mBtnActivityLogin;
    @BindView(R.id.tv_activity_login_quikly_login)
    TextView  mTvActivityLoginQuiklyLogin;
    @BindView(R.id.tv_activity_login_forget_password)
    TextView  mTvActivityLoginForgetPassword;
    @BindView(R.id.wx_login)
    ImageView mWxLogin;
    private String  mPassword;
    private String  mPhoneNum;
    private User    mUserBean;
    private IWXAPI  mApi;
    private boolean mB;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initToobar(getString(R.string.title_login));

        //应用id注册到微信,实现微信登录功能
        //通过wxapiFactory 工厂,获取iwapi的实例
        mApi = WXAPIFactory.createWXAPI(this, Constant.APP_ID, true);
        //将应用的appid注册到微信
        mApi.registerApp(Constant.APP_ID);
    }

    @OnClick({R.id.et_activity_lonin_username, R.id.et_activity_lonin_password, R.id.btn_activity_login, R.id.tv_activity_login_quikly_login, R.id.tv_activity_login_forget_password, R.id.wx_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_activity_lonin_username:
                LogUtils.d("文件夹路径"+Environment.getExternalStorageState().toString());
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
                startActivityForResult(new Intent(this, RegistActivity.class), 110);
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

        LoginManager.getInstance().goLogin(mPhoneNum, mPassword, new LoginManager.CallBack() {
            @Override
            public void loginSuccess() {
                Toast.makeText(UiUtilities.getContex(), "登录成功", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void loginFail(int code) {
                switch (code) {
                    case 404:
                        break;
                    case 401:
                        break;
                    case 402:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void loginError() {
                Toast.makeText(UiUtilities.getContex(), "用户名或登录密码有误", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 110) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_OK);
                finish();
            }
        }
    }
}
