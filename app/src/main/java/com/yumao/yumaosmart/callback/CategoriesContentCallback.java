package com.yumao.yumaosmart.callback;

import com.google.gson.Gson;
import com.yumao.yumaosmart.mode.CategoriesContentMode;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by kk on 2017/3/30.
 */

public abstract class CategoriesContentCallback extends Callback<CategoriesContentMode> {
    @Override
    public CategoriesContentMode parseNetworkResponse(Response response, int id) throws Exception {
        String string = response.body().string();
        CategoriesContentMode categoriesContentMode = new Gson().fromJson(string, CategoriesContentMode.class);

        return categoriesContentMode;
    }
}
