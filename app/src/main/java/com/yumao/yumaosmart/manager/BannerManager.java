package com.yumao.yumaosmart.manager;

import com.yumao.yumaosmart.mode.FristBannerBean;
import com.yumao.yumaosmart.callback.FirstBannerCallback;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.mode.User;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import okhttp3.Call;

/**
 * Created by kk on 2017/7/7.
 */

public class BannerManager {

    //判断活动是否有数据
    public static void isBannerData(){

        int vId ;
        if (LoginManager.getInstance().isLoginState(UiUtilities.getContex())){
            User userInformation = UserInformationManager.getInstance().getUserInformation();
            int id = userInformation.getVendor().getId();
                LogUtils.d("tag",""+id);
            vId = id;
        }else {
            vId = 1;
        }
        OkHttpUtils
                .get()
                .url(Constant.BASE_URL+"vendor-sections")
                .addParams("vendor_id",vId+"")
                .build()
                .execute(new FirstBannerCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("tag","轮播图无图片");
                    }

                    @Override
                    public void onResponse(List<FristBannerBean> response, int id) {
                            LogUtils.d("tag","成功");

                    }
                });
    }
}
