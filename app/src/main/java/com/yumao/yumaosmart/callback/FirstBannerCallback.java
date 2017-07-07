package com.yumao.yumaosmart.callback;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yumao.yumaosmart.bean.FristBannerBean;
import com.zhy.http.okhttp.callback.Callback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

/**
 * Created by kk on 2017/7/7.
 */

public abstract class FirstBannerCallback extends Callback<List<FristBannerBean>> {

    @Override
    public List<FristBannerBean> parseNetworkResponse(Response response, int id) throws Exception {
        String s = response.body().toString();
        List<FristBannerBean> list= new Gson().fromJson(s, new TypeToken<ArrayList<FristBannerBean>>() {
        }.getType());

        return list;
    }

}
