package com.yumao.yumaosmart.callback;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yumao.yumaosmart.mode.ProductsMode;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import okhttp3.Response;

/**
 * Created by kk on 2017/3/28.
 */

public abstract class ProductsCallback extends Callback<List<ProductsMode>> {

    private int mCode;
    private List<ProductsMode> mO;

    @Override
    public List<ProductsMode> parseNetworkResponse(Response response, int id) throws Exception {
        String string = response.body().string();
        mCode = response.code();

        mO = new Gson().fromJson(string, new TypeToken<List<ProductsMode>>() {
        }.getType());


        return mO;
    }


}
