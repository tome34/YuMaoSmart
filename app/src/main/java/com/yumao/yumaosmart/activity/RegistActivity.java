package com.yumao.yumaosmart.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseItemActivity;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class RegistActivity extends BaseItemActivity {

    private static final int TIME_MINUS = 1000;
    private static final int TIME_IS_OUT = 1001;

    @BindView(R.id.tv_titlle)
    TextView mTvTitlle;
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.et_activity_regist_username)
    EditText mEtActivityRegistUsername;
    @BindView(R.id.et_activity_regist_testcode)
    EditText mEtActivityRegistTestcode;
    @BindView(R.id.btn_activity_regist_gettestcode)
    TextView mBtnActivityRegistGettestcode;
    @BindView(R.id.et_activity_regist_password)
    EditText mEtActivityRegistPassword;
    @BindView(R.id.et_activity_regist_password_again)
    EditText mEtActivityRegistPasswordAgain;
    @BindView(R.id.btn_activity_regist_finishregist)
    Button mBtnActivityRegistFinishregist;
    @BindView(R.id.activity_regist)
    LinearLayout mActivityRegist;


    private String mPhoneNum;
    private String mTestCode;
    private String mPassWord;
    private String mPassWordAgain;
    private int mCode;
    private int time = 60;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
        initToobar(getString(R.string.title_regist));

    }

    @OnClick({R.id.et_activity_regist_username, R.id.et_activity_regist_testcode,
            R.id.btn_activity_regist_gettestcode, R.id.et_activity_regist_password,
            R.id.et_activity_regist_password_again, R.id.btn_activity_regist_finishregist})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_activity_regist_username:
                break;
            case R.id.et_activity_regist_testcode:
                break;
            case R.id.btn_activity_regist_gettestcode:
                mPhoneNum = mEtActivityRegistUsername.getText().toString().trim();
                if (testPhone()) {
                    getTestCode();
                    mBtnActivityRegistGettestcode.setText("剩余时间（" + time + ")秒");
                    mBtnActivityRegistGettestcode.setEnabled(false);
                    new Thread(new CutDownTask()).start();
                }

                break;
            case R.id.et_activity_regist_password:
                break;
            case R.id.et_activity_regist_password_again:
                break;
            case R.id.btn_activity_regist_finishregist:
                if (testBefore()) {
                    registRealy();
                }
                break;
        }
    }

    private boolean testPhone() {
        if (TextUtils.isEmpty(mPhoneNum)) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();

        } else {
            if (isMobileNO(mPhoneNum)) {
                return true;
            } else {
                Toast.makeText(this, "您输入的手机号码不存在", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

    private void registRealy() {
        OkHttpUtils
                .post()
                .url(Constant.BASE_URL + "register")
                .addParams("phone", mPhoneNum)
                .addParams("password", mPassWord)
                .addParams("code", mTestCode)
                .addParams("vendor_id", "1")  //玉猫平台
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        //Toast.makeText(RegistActivity.this, "网络连接失败1", Toast.LENGTH_SHORT).show();
                        LogUtils.d("tag", "1:" + response.code());
                        mCode =  response.code();
                        return true;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        //Toast.makeText(RegistActivity.this, "网络连接失败2", Toast.LENGTH_SHORT).show();
                        LogUtils.d("tag", "2:" + e);
                        if (mCode ==403){
                            Toast.makeText(RegistActivity.this, "账号已经存在", Toast.LENGTH_SHORT).show();
                        }else if (mCode == 400) {
                            Toast.makeText(RegistActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RegistActivity.this, "账号已经存在或验证码错误", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        //Toast.makeText(RegistActivity.this, "网络连接失败3", Toast.LENGTH_SHORT).show();
                        LogUtils.d("tag", "3:" + response.toString());
                        if (mCode == 204) {
                            Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        } else if (mCode == 403) {
                            Toast.makeText(RegistActivity.this, "账号已经存在", Toast.LENGTH_SHORT).show();
                        } else if (mCode == 404) {
                            Toast.makeText(RegistActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                /*.execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                       // Toast.makeText(RegistActivity.this, "网络连接失败1", Toast.LENGTH_SHORT).show();
                            LogUtils.d("tag","1:"+e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (mCode == 204) {
                            Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        } else if (mCode == 403) {
                            Toast.makeText(RegistActivity.this, "账号已经存在", Toast.LENGTH_SHORT).show();
                        } else if (mCode == 404) {
                            Toast.makeText(RegistActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                        }

                       // Toast.makeText(RegistActivity.this, "成功", Toast.LENGTH_SHORT).show();
                        LogUtils.d("tag","2:"+response.toString());
                    }

                    *//*@Override
                    public boolean validateReponse(Response response, int id) {
                        response.code()
                       // Toast.makeText(RegistActivity.this, "网络连接失败2", Toast.LENGTH_SHORT).show();
                        return  true;
                    }*//*

                    @Override
                    public String parseNetworkResponse(Response response, int id) throws IOException {
                       // Toast.makeText(RegistActivity.this, "网络连接失败3", Toast.LENGTH_SHORT).show();
                        mCode =  response.code();
                        return super.parseNetworkResponse(response, id);

                    }
                });*/
    }

    private boolean testBefore() {
        mPhoneNum = mEtActivityRegistUsername.getText().toString().trim();
        mTestCode = mEtActivityRegistTestcode.getText().toString().trim();
        mPassWord = mEtActivityRegistPassword.getText().toString().trim();
        mPassWordAgain = mEtActivityRegistPasswordAgain.getText().toString().trim();
        if (TextUtils.isEmpty(mPhoneNum) || TextUtils.isEmpty(mTestCode) ||
                TextUtils.isEmpty(mPassWord) || TextUtils.isEmpty(mPassWordAgain)) {
            Toast.makeText(this, "请输入完整信息", Toast.LENGTH_SHORT).show();
                return false;
        } else {
            if (isMobileNO(mPhoneNum)) {
                if (mPassWord.equals(mPassWordAgain)) {

                    return true;
                } else {
                    Toast.makeText(this, "两次输入了密码不一致", Toast.LENGTH_SHORT).show();
                    return false;
                }

            } else {
                Toast.makeText(this, "您输入的电话号码不存在", Toast.LENGTH_SHORT).show();
                return false;
            }

        }

    }



    public static boolean isMobileNO(String mobiles){

        String telRegex = "[1][34578]\\d{9}";
        //Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Pattern p = Pattern.compile(telRegex);
        Matcher m = p.matcher(mobiles);
        return m.matches();

        }
    public void getTestCode() {

        OkHttpUtils
                .post()
                .url(Constant.BASE_URL+"sms-code")
                .addParams("phone", mPhoneNum)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(RegistActivity.this, "验证码发送失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Toast.makeText(RegistActivity.this, "验证码已发送请注意查收", Toast.LENGTH_SHORT).show();

                    }
                });

    }
    private class CutDownTask implements Runnable {
        @Override
        public void run() {
            for(;time>0;time--){
                SystemClock.sleep(999);
                mHandler.sendEmptyMessage(TIME_MINUS);
            }
            mHandler.sendEmptyMessage(TIME_IS_OUT);
        }
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){

                case TIME_MINUS:
                    mBtnActivityRegistGettestcode.setText("剩余时间（" + time + ")秒");
                break;
                case TIME_IS_OUT:
                    mBtnActivityRegistGettestcode.setText("重新获取验证码");
                    mBtnActivityRegistGettestcode.setEnabled(true);
                    time = 60;
                    break;
            }

        }
    };

}
