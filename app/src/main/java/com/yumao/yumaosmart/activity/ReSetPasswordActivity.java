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
import com.yumao.yumaosmart.utils.StringUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class ReSetPasswordActivity extends BaseItemActivity {

    private static final int TIME_MINUS = 1000;
    private static final int TIME_IS_OUT = 1001;

    @BindView(R.id.tv_titlle)
    TextView mTvTitlle;
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.et_activity_reset_username)
    EditText mEtActivityResetUsername;
    @BindView(R.id.et_activity_reset_testcode)
    EditText mEtActivityResetTestcode;
    @BindView(R.id.btn_activity_reset_gettestcode)
    TextView mBtnActivityResetGettestcode;
    @BindView(R.id.et_activity_reset_password)
    EditText mEtActivityResetPassword;
    @BindView(R.id.et_activity_reset_password_again)
    EditText mEtActivityResetPasswordAgain;
    @BindView(R.id.btn_activity_reset_finishregist)
    Button mBtnActivityResetFinishregist;
    @BindView(R.id.activity_regist)
    LinearLayout mActivityRegist;
    private String mPhoneNum;
    private String mTestCode;
    private String mPassWord;
    private String mPassWordAgain;
    //private int mCode;
    private int time = 60;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_set_password);
        ButterKnife.bind(this);
        initToobar(getString(R.string.title_reset_password));

    }

    @OnClick({R.id.btn_activity_reset_gettestcode, R.id.btn_activity_reset_finishregist})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_activity_reset_gettestcode:
                mPhoneNum = mEtActivityResetUsername.getText().toString().trim();
                if (testPhone()) {
                    getTestCode();
                    //开启倒计时
                    mBtnActivityResetGettestcode.setText("剩余时间（" + time + ")秒");
                    mBtnActivityResetGettestcode.setEnabled(false);
                    new Thread(new CutDownTask()){}.start();
                }
                break;
            case R.id.btn_activity_reset_finishregist:
                if (testBefore()) {
                    resetReally();
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


    private void resetReally() {
        OkHttpUtils
                .post()
                .url(Constant.BASE_URL+"reset-password") //"https://dist.yumao168.com/api/reset-password"
                .addParams("phone", mPhoneNum)
                .addParams("password", mPassWord)
                .addParams("code",mTestCode)
                .build()
                /*.execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        String string = response.body().string();
                            LogUtils.d("tag","返回码:"+string);
                        return string;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(ReSetPasswordActivity.this, "重置密码失败,验证码错误", Toast.LENGTH_SHORT).show();
                        LogUtils.d("tag","重置密码的错误信息:"+e.toString());
                    }

                    @Override
                    public void onResponse(Object response, int id) {

                        LogUtils.d("tag","返回码2:"+response.toString());
                    }
                });*/
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        Toast.makeText(ReSetPasswordActivity.this, "重置密码失败,验证码错误", Toast.LENGTH_SHORT).show();
                            LogUtils.d("tag","重置密码的错误信息:"+e.toString());

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Toast.makeText(ReSetPasswordActivity.this, "密码重置成功", Toast.LENGTH_SHORT).show();
                        finish();
                        }

                });
    }

    private boolean testBefore() {
        mPhoneNum = mEtActivityResetUsername.getText().toString().trim();
        mTestCode = mEtActivityResetTestcode.getText().toString().trim();
        mPassWord = mEtActivityResetPassword.getText().toString().trim();
        mPassWordAgain = mEtActivityResetPasswordAgain.getText().toString().trim();
        if (TextUtils.isEmpty(mPhoneNum) || TextUtils.isEmpty(mTestCode) ||
                TextUtils.isEmpty(mPassWord) || TextUtils.isEmpty(mPassWordAgain)) {
            Toast.makeText(this, "请输入完整信息", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (StringUtils.isMobileNO(mPhoneNum)) {
                if (isPassWorkFormat(mPassWord) ) {
                    if (mPassWord.equals(mPassWordAgain)){
                        return true;
                    }else{
                        Toast.makeText(this, "两次输入了密码不一致", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                } else {
                    Toast.makeText(this, "密码必须是4-16位", Toast.LENGTH_SHORT).show();
                    return false;
                }

            } else {
                Toast.makeText(this, "您输入的手机号码格式不对,请重新输入", Toast.LENGTH_SHORT).show();
                return false;
            }

        }

    }

    //判断密码的格式 4-16位
    public boolean isPassWorkFormat(String passWord){
        String pwRegex = "\\w{4,16}";
        Pattern p = Pattern.compile(pwRegex);
        Matcher m = p.matcher(passWord);

        return m.matches();
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
                        Toast.makeText(ReSetPasswordActivity.this, "验证码发送失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Toast.makeText(ReSetPasswordActivity.this, "验证码已发送请注意查收", Toast.LENGTH_SHORT).show();
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
                    mBtnActivityResetGettestcode.setText("剩余时间（" + time + ")秒");
                    break;
                case TIME_IS_OUT:
                    mBtnActivityResetGettestcode.setText("重新获取验证码");
                    mBtnActivityResetGettestcode.setEnabled(true);
                    time = 60;
                    break;
            }
        }
    };
}
