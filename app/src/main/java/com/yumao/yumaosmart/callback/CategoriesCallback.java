package com.yumao.yumaosmart.callback;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yumao.yumaosmart.mode.ClassiFyMode;
import com.zhy.http.okhttp.callback.Callback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

/**
 * Created by kk on 2017/3/30.
 */

public abstract class CategoriesCallback extends Callback<List<ClassiFyMode>>{
    @Override
    public List<ClassiFyMode> parseNetworkResponse(Response response, int id) throws Exception {
        String string = response.body().string();
        List<ClassiFyMode> list = new Gson().fromJson(string, new TypeToken<ArrayList<ClassiFyMode>>() {
        }.getType());

        return list;
    }

}
