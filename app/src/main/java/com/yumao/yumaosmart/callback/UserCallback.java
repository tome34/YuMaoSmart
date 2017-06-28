package com.yumao.yumaosmart.callback;

import com.yumao.yumaosmart.utils.LogUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created  on 2017/3/28.
 */
public abstract class UserCallback extends Callback<String>
{
  // private User homeBean;
    private String userData;
    public int mCode;

    @Override
    public String parseNetworkResponse(Response response, int id) throws Exception {
        mCode = response.code();
        String userData = response.body().string();
            LogUtils.d("response:"+userData);
      //homeBean = new Gson().fromJson(string,User.class);
        return userData;
    }
    @Override
    public boolean validateReponse(Response response, int id)
    {
        return true;
    }
}
