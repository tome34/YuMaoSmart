package com.yumao.yumaosmart.callback;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yumao.yumaosmart.mode.FristBannerLMBean;
import com.zhy.http.okhttp.callback.Callback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

/**
 * Created by kk on 2017/7/7.
 */

public abstract class FirstBannerLMCallback extends Callback<List<FristBannerLMBean>> {

    @Override
    public List<FristBannerLMBean> parseNetworkResponse(Response response, int id) throws Exception {
        String s = response.body().string();
        List<FristBannerLMBean> list= new Gson().fromJson(s, new TypeToken<ArrayList<FristBannerLMBean>>() {
        }.getType());

        return list;
    }

}
