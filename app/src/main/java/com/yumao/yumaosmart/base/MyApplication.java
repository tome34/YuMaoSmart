package com.yumao.yumaosmart.base;

import android.content.Context;
import android.os.Handler;

import com.mob.MobApplication;
import com.squareup.picasso.Picasso;
import com.yumao.yumaosmart.mode.User;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by kk on 2017/2/21.
 */

public class MyApplication extends MobApplication {
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

        //加载图片框架
        Picasso.with(this)
                .setIndicatorsEnabled(true);//显示指示器
        Picasso.with(this)
                .setLoggingEnabled(true);//开启日志打印
    }

    //初始化Okhttp
    private void creatOkhttpClient() {

        {
            super.onCreate();

            //.addInterceptor(new LoggerInterceptor("TAG"))
            //其他配置00
           // HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // 設置攔截等級
           // logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            mClient = new OkHttpClient.Builder()
                   // .addInterceptor(logging)  // 日誌攔截器,用於日誌的打印
                    .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                    .readTimeout(10000L, TimeUnit.MILLISECONDS)

                    //其他配置
                    .build();

            OkHttpUtils.initClient(mClient);


        }


    }
}