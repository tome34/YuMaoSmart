package com.yumao.yumaosmart.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseItemActivity;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.SPUtils;
import com.yumao.yumaosmart.utils.StringUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * Created by kk on 2017/3/8.
 */
public class PhoneNumberActivity extends BaseItemActivity {
    @BindView(R.id.edit_phoneactivity_phonenum)
    EditText mEditPhoneactivityPhonenum;
    @BindView(R.id.edit_phoneactivityt_yanzhengma)
    EditText mEditPhoneactivitytYanzhengma;
    @BindView(R.id.btn_phoneactivityt_getyanzhengma)
    Button mBtnPhoneactivitytgetYanzhengma;
    @BindView(R.id.btn_activity_phonenum_save)
    Button mBtnActivityPhonenumSave;
    private boolean mIsphoneNum;
    private String mNum;
    private String mTestCode;
    private boolean mHasGetCode;
    private int time = 60;

    private static final int TIME_MINUS = 1000;
    private static final int TIME_IS_OUT = 1001;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonenumber);
        initToobar(getString(R.string.title_phonenum_idname));
        ButterKnife.bind(this);

        mEditPhoneactivityPhonenum.addTextChangedListener(new UserEditTextChangeListener());
    }

    class UserEditTextChangeListener implements TextWatcher {

        /**
         * 编辑框的内容发生改变之前的回调方法
         */
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        /**
         * 编辑框的内容正在发生改变时的回调方法 >>用户正在输入
         * 我们可以在这里实时地 通过搜索匹配用户的输入
         */
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (StringUtils.isMobileNO(s + "")) {
                mBtnPhoneactivitytgetYanzhengma.setEnabled(true);
                mBtnPhoneactivitytgetYanzhengma.setBackgroundResource(R.drawable.shap_ring_yellow);
            } else {
                mBtnPhoneactivitytgetYanzhengma.setEnabled(false);
                mBtnPhoneactivitytgetYanzhengma.setBackgroundResource(R.drawable.shap_ring_gray);
            }

        }

        /**
         * 编辑框的内容改变以后,用户没有继续输入时 的回调方法
         */
        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    @OnClick({R.id.btn_phoneactivityt_getyanzhengma, R.id.btn_activity_phonenum_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_phoneactivityt_getyanzhengma:
                mNum = mEditPhoneactivityPhonenum.getText().toString().trim();
                //mIsphoneNum = checkPhone(mNum);
                if (StringUtils.isMobileNO(mNum)) {
                    getTestCode();
                    mBtnPhoneactivitytgetYanzhengma.setText("剩余时间（" + time + ")秒");
                    mBtnPhoneactivitytgetYanzhengma.setEnabled(false);
                    mBtnPhoneactivitytgetYanzhengma.setBackgroundResource(R.drawable.shap_ring_gray);
                    new Thread(new CutDownTask()).start();

                    return;
                } else {
                    Toast.makeText(this, "您输入的号码格式不正确", Toast.LENGTH_SHORT).show();

                }

                break;
            case R.id.btn_activity_phonenum_save:
                mNum = mEditPhoneactivityPhonenum.getText().toString().trim();
                //mIsphoneNum = checkPhone(mNum);
                if (StringUtils.isMobileNO(mNum)) {
                    if (mHasGetCode) {
                        mTestCode =  mEditPhoneactivitytYanzhengma.getText().toString().trim();
                        if(!TextUtils.isEmpty(mTestCode)){
                            updatePhone();
                        }else{
                            Toast.makeText(this, "请输入您的验证码", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(this, "请先获取验证码", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "您输入的号码格式不正确", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
    private void updatePhone() {
        
        OkHttpUtils
                .post()
                .url(Constant.BASE_URL+"sms-code/verification")
                .addParams("phone",mNum)
                .addParams("code",mTestCode)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(PhoneNumberActivity.this, "手机号码或验证码错误", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                            LogUtils.d("tag","手机号码和验证码验证成功");
                        putMether();

                    }
                });



    }

    private void putMether() {

        FormBody.Builder builder = new FormBody.Builder();

        FormBody body = builder
                .add("phone", mNum)
                .build();
        OkHttpUtils
                .put()
                .url(Constant.BASE_URL+"customers/"+ SPUtils.getInt(PhoneNumberActivity.this,Constant.USER_CID))
                .addHeader("X-API-TOKEN",SPUtils.getString(PhoneNumberActivity.this,Constant.TOKEN))
                .requestBody(body)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(PhoneNumberActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                       /* EventBus.getDefault().post(new PhoneNumEvent(mNum,2));
                        User user = UiUtilities.getUser();
                        LogUtils.d("号码 put完成");
                         user.setPhone(mNum);*/
                        SPUtils.putString(UiUtilities.getContex(),Constant.PHONE,mNum);
                        finish();

                    }
                });
    }

    private boolean checkPhone(String s) {
        if (TextUtils.isEmpty(s)) {
            Toast.makeText(this, "您输入的手机号码为空", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }
/*获取验证码*/
    public void getTestCode() {
        OkHttpUtils
                .post()
                .url(Constant.BASE_URL+"sms-code")
                .addParams("phone",mNum)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(PhoneNumberActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        mHasGetCode = true;

                    }
                });

    }
    private class CutDownTask implements Runnable {
        @Override
        public void run() {
            for (; time > 0; time--) {
                SystemClock.sleep(999);
                mHandler.sendEmptyMessage(TIME_MINUS);
            }
            mHandler.sendEmptyMessage(TIME_IS_OUT);
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case TIME_MINUS:
                    mBtnPhoneactivitytgetYanzhengma.setText("剩余时间（" + time + ")秒");
                    break;
                case TIME_IS_OUT:
                    mBtnPhoneactivitytgetYanzhengma.setText("重新获取验证码");
                    mBtnPhoneactivitytgetYanzhengma.setEnabled(true);
                    time = 60;
                    break;
            }

        }
    };
}
