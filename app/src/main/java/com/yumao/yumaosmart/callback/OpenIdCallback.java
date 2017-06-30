package com.yumao.yumaosmart.callback;

import com.google.gson.Gson;
import com.yumao.yumaosmart.mode.OpenIdMode;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by kk on 2017/6/30.
 */

public abstract class OpenIdCallback extends Callback<OpenIdMode> {


    @Override
    public OpenIdMode parseNetworkResponse(Response response, int id) throws Exception {

        String string = response.body().string();
        OpenIdMode openIdBean = new Gson().fromJson(string, OpenIdMode.class);

        return openIdBean;
    }
}
