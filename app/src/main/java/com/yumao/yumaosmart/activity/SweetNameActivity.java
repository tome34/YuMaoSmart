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
import com.yumao.yumaosmart.event.SweetNameEvent;
import com.yumao.yumaosmart.mode.User;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * Created by kk on 2017/3/7.
 */
public class SweetNameActivity extends BaseItemActivity implements View.OnClickListener {
    @BindView(R.id.edit_sweetactivity)
    EditText mEditSweetactivity;
    @BindView(R.id.btn_sweetactivity_save)
    Button mBtnSweetactivitySave;
    private String mTrim;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sweetname);
        initToobar(getString(R.string.title_sweetname));
        ButterKnife.bind(this);





        mBtnSweetactivitySave.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

            mTrim = mEditSweetactivity.getText().toString();
        if (TextUtils.isEmpty(mTrim)) {
            Toast.makeText(this, "昵称不能为空", Toast.LENGTH_SHORT).show();
        } else {
            updateSweetName();
        }




}

    private void updateSweetName() {
        FormBody.Builder builder = new FormBody.Builder();

        FormBody body = builder
                .add("nick_name", mTrim)
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
                Toast.makeText(SweetNameActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Object response, int id) {
                EventBus.getDefault().post(new SweetNameEvent(mTrim,1));
                User user = UiUtilities.getUser();
                LogUtils.d("put完成");
                user.setNick_name(mTrim);
                finish();

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
