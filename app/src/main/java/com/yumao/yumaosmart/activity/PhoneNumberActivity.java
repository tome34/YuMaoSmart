package com.yumao.yumaosmart.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseItemActivity;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.event.PhoneNumEvent;
import com.yumao.yumaosmart.mode.User;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.greenrobot.eventbus.EventBus;

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
    private String mTestCode1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonenumber);
        initToobar(getString(R.string.title_phonenum_idname));
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_phoneactivityt_getyanzhengma, R.id.btn_activity_phonenum_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_phoneactivityt_getyanzhengma:
                mNum = mEditPhoneactivityPhonenum.getText().toString().trim();
                mIsphoneNum = checkPhone(mNum);
                if (!mIsphoneNum) {
                    Toast.makeText(this, "您输入的号码不存在", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                 getTestCode();

                }

                break;
            case R.id.btn_activity_phonenum_save:
                mNum = mEditPhoneactivityPhonenum.getText().toString().trim();
                mIsphoneNum = checkPhone(mNum);
                if (!mIsphoneNum) {
                    Toast.makeText(this, "您输入的号码不存在", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (mHasGetCode) {
               mTestCode =  mEditPhoneactivitytYanzhengma.getText().toString().trim();

                        updatePhone();
                    }else{
                        Toast.makeText(this, "请先获取验证码", Toast.LENGTH_SHORT).show();
                    }


                }

                break;
        }
    }
    private void updatePhone() {
        FormBody.Builder builder = new FormBody.Builder();

        FormBody body = builder
                .add("phone", mNum)
                .build();
        OkHttpUtils
                .put()
                .url(Constant.BASE_URL+"customers/"+ UiUtilities.getUser().getId())
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
                        Toast.makeText(PhoneNumberActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        EventBus.getDefault().post(new PhoneNumEvent(mNum,2));
                        User user = UiUtilities.getUser();
                        LogUtils.d("put完成");
                      user.setPhone(mNum);
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
}
