package com.yumao.yumaosmart.callback;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yumao.yumaosmart.mode.ShoppingCartMode;
import com.zhy.http.okhttp.callback.Callback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

/**
 * Created by kk on 2017/7/7.
 */

public abstract class ShoppingCartCallback extends Callback<List<ShoppingCartMode>> {

    public String cartJsonData ;

    @Override
    public List<ShoppingCartMode> parseNetworkResponse(Response response, int id) throws Exception {
        cartJsonData = response.body().string();
        List<ShoppingCartMode> list= new Gson().fromJson(cartJsonData, new TypeToken<ArrayList<ShoppingCartMode>>() {
        }.getType());

        return list;
    }

}
