package com.yumao.yumaosmart.manager;

import android.widget.Toast;

import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.SPUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by kk on 2017/7/25.
 */

public class CartManager {

    private static CartManager sCartManager;

    //获取用户的id
    private static int mCid = SPUtils.getInt(UiUtilities.getContex(), Constant.USER_CID);
    //获取token
    private static String mToken= SPUtils.getString(UiUtilities.getContex(), Constant.TOKEN);;

    public CartManager() {
    }


    //单例模式
    public static CartManager getInstance() {
        if (sCartManager == null) {
            synchronized (LoginManager.class) {
                if (sCartManager == null) {
                    sCartManager = new CartManager();
                }
            }
        }

        return sCartManager;
    }


    public void deleteCartItem(int iid){

        OkHttpUtils
                .delete()
                .url(Constant.BASE_URL+"customers/"+mCid+"/shopping-cart-items/"+iid)
                .addHeader("X-API-TOKEN",mToken)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("删除失败"+e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("删除成功");

                    }
                });
    }

    public void postCartItem(int productId,int quantity){
            LogUtils.d("token:"+mToken+"  "+mCid);

        OkHttpUtils
                .post()
                .url(Constant.BASE_URL+"customers/"+mCid+"/shopping-cart-items")
                .addHeader("X-API-TOKEN",mToken)
                .addParams("product_id",String.valueOf(productId))
                .addParams("store_id","1")
                .addParams("quantity",String.valueOf(quantity))
                .build()
               .execute(new StringCallback() {
                   @Override
                   public void onError(Call call, Exception e, int id) {
                       LogUtils.d("加入购物车失败"+e +"  "+Thread.currentThread());

                       Toast.makeText(UiUtilities.getContex(), "商品已在购物车", Toast.LENGTH_SHORT).show();
                   }

                   @Override
                   public void onResponse(String response, int id) {
                       LogUtils.d("加入购物车成功"+Thread.currentThread());
                       Toast.makeText(UiUtilities.getContex(), "已加入购物车", Toast.LENGTH_SHORT).show();
                   }
               });
    }


}
