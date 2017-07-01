package com.yumao.yumaosmart.manager;

import android.content.Context;
import android.text.TextUtils;

import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.SPUtils;

/**
 * Created by kk on 2017/7/1.
 *
 * 登录管理类
 */

public class LoginManager {

    private static LoginManager sLoginManager;

    private LoginManager(){}

    //单例模式
    public static LoginManager getInstance(){
        if (sLoginManager == null){
            synchronized (LoginManager.class){
                if (sLoginManager == null){
                    sLoginManager = new LoginManager();
                }
            }
        }

        return sLoginManager;
    }



    //判断用户是否登录
    public boolean isLoginState(Context context) {
        boolean mLoginState ;

        String token = SPUtils.getString(context, Constant.TOKEN, null);
        LogUtils.d("token:" + token);
        if (!TextUtils.isEmpty(token) ) {

            mLoginState = true;
        } else {

            mLoginState = false;

        }
        return mLoginState;

    }

    //退出当前登录
    public void  signOutLogin(Context context){
        SPUtils.putString(context ,Constant.TOKEN,null);
        SPUtils.putString(context ,Constant.USER_DATA,null);
    }


}

