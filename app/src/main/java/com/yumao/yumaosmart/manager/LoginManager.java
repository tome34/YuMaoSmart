package com.yumao.yumaosmart.manager;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yumao.yumaosmart.callback.UserCallback;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.mode.User;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.SPUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

/**
 * Created by kk on 2017/7/1.
 * <p>
 * 登录管理类
 */

public class LoginManager {

    private static LoginManager sLoginManager;
    private        CallBack     mCallBack;

    private LoginManager() {
    }

    //单例模式
    public static LoginManager getInstance() {
        if (sLoginManager == null) {
            synchronized (LoginManager.class) {
                if (sLoginManager == null) {
                    sLoginManager = new LoginManager();
                }
            }
        }

        return sLoginManager;
    }


    //判断用户是否登录
    public boolean isLoginState(Context context) {
        boolean mLoginState;

        String token = SPUtils.getString(context, Constant.TOKEN, null);
        LogUtils.d("token:" + token);
        if (!TextUtils.isEmpty(token)) {

            mLoginState = true;
        } else {

            mLoginState = false;

        }
        return mLoginState;

    }


    //退出当前登录
    public void signOutLogin(Context context) {
        //清楚sp所有数据
        SPUtils.clear(UiUtilities.getContex());
    }

    private User    mUserBean;

    //用户登录
    public void goLogin(String user, String passWord,final CallBack callBack) {


        OkHttpUtils
                .post()
                .url(Constant.BASE_URL + "login")
                .addParams("username", user)
                .addParams("password", passWord)
                .build()
                .execute(new UserCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.loginError();

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        //UiUtilities.setUser(response);
                        if (mCode == 200) {
                            //UiUtilities.setUser(response);
                            mUserBean = new Gson().fromJson(response, User.class);
                            String token = mUserBean.getToken();
                            int cid = mUserBean.getId();
                            String avatar_url = mUserBean.getAvatar_url();
                            String nick_name = mUserBean.getNick_name();
                            String phone = mUserBean.getPhone();
                            String date_of_birth = mUserBean.getDate_of_birth();
                            String gender = mUserBean.getGender();


                            SPUtils.putString(UiUtilities.getContex(), Constant.TOKEN, token);
                            SPUtils.putString(UiUtilities.getContex(), Constant.USER_DATA, response);

                            //保存用户头像
                            SPUtils.putString(UiUtilities.getContex(), Constant.AVATAR_URL, avatar_url);
                            LogUtils.d("tag", "用户头像:" + avatar_url);
                            //保存cid号
                            SPUtils.putInt(UiUtilities.getContex(), Constant.USER_CID, cid);
                            //保存用户昵称
                            SPUtils.putString(UiUtilities.getContex(), Constant.NICK_NAME, nick_name);
                            //保存用户手机号码
                            SPUtils.putString(UiUtilities.getContex(), Constant.PHONE, phone);
                            //保存生日
                            SPUtils.putString(UiUtilities.getContex(), Constant.DATA_OF_BIRTH, date_of_birth);
                            //性别
                            SPUtils.putString(UiUtilities.getContex(), Constant.GENDER, gender);
                            callBack.loginSuccess();
                        } else {
                            callBack.loginFail(mCode);
                        }

                    }

                });

    }

    public interface CallBack {
        void loginSuccess();

        void loginFail(int code);

        void loginError();
    }

}

