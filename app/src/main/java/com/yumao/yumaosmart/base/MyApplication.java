package com.yumao.yumaosmart.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.yumao.yumaosmart.mode.User;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by kk on 2017/2/21.
 */

public class MyApplication extends Application {
    public static Handler mHandler;
    public static Context mContext;
    public HashMap<String, String> mHashMap = new HashMap<>();
    private OkHttpClient mClient;
    private static User mUser;

    public HashMap<String, String> getHashMap() {
        return mHashMap;
    }


    public static Context getContext() {
        mHandler = new Handler();

        return mContext;
    }

    public static User getUser() {
        mHandler = new Handler();

        return mUser;
    }

    public static void setUser(User user) {
        mUser = user;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
        mContext = getApplicationContext();
        mUser = new User();
        creatOkhttpClient();
    }

    //初始化Okhttp
    private void creatOkhttpClient() {

        {
            super.onCreate();

            //.addInterceptor(new LoggerInterceptor("TAG"))
//其他配置00

            mClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                    .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                    .readTimeout(10000L, TimeUnit.MILLISECONDS)

                    //其他配置
                    .build();

            OkHttpUtils.initClient(mClient);


        }


    }
}