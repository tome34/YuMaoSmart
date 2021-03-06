package com.yumao.yumaosmart.callback;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yumao.yumaosmart.mode.FristClassifyMode;
import com.zhy.http.okhttp.callback.Callback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

/**
 * Created by kk on 2017/7/7.
 */

public abstract class FirstClassifyCallback extends Callback<List<FristClassifyMode>> {

    @Override
    public List<FristClassifyMode> parseNetworkResponse(Response response, int id) throws Exception {
        String s = response.body().string();
        List<FristClassifyMode> list= new Gson().fromJson(s, new TypeToken<ArrayList<FristClassifyMode>>() {
        }.getType());

        return list;
    }

}
