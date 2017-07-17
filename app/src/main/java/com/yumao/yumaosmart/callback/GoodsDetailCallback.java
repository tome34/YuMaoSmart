package com.yumao.yumaosmart.callback;

import com.google.gson.Gson;
import com.yumao.yumaosmart.mode.GoodsDetailMode;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by kk on 2017/3/29.
 */

public abstract class GoodsDetailCallback extends Callback<GoodsDetailMode> {

    public String mDetailString;
    private GoodsDetailMode mGoodsDetailMode;


    @Override
    public GoodsDetailMode parseNetworkResponse(Response response, int id) throws Exception {
        mDetailString = response.body().string();

         mGoodsDetailMode = new Gson().fromJson(mDetailString, GoodsDetailMode.class);

            return mGoodsDetailMode;
    }


}
