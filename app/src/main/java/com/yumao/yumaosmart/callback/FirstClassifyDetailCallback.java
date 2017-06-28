package com.yumao.yumaosmart.callback;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yumao.yumaosmart.mode.FirstClassifyDetailMode;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import okhttp3.Response;

/**
 * Created by kk on 2017/4/5.
 */

public abstract class FirstClassifyDetailCallback extends Callback<List<FirstClassifyDetailMode>> {
    @Override
    public List<FirstClassifyDetailMode> parseNetworkResponse(Response response, int id) throws Exception {

        String string = response.body().string();
       List<FirstClassifyDetailMode> o = new Gson().fromJson(string, new TypeToken<List<FirstClassifyDetailMode>>() {
        }.getType());
        return o;
    }
}
