package com.yumao.yumaosmart.manager;

import android.content.Intent;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yumao.yumaosmart.activity.LoginActivity;
import com.yumao.yumaosmart.bean.Shopping;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.fragment.ShoppingCartFragment;
import com.yumao.yumaosmart.utils.Arith;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.SPUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by kk on 2017/7/25.
 */

public class CartManager {

    private static CartManager sCartManager;

    //购物车
    public SparseArray<Shopping> mSparseArray = null;

    //软引用
    public volatile static WeakReference<CartManager> sInstance = null;

    //public Context mContext;

    //获取用户的id
    private  int mCid ;
    //获取token
    private  String mToken ;


   /* public CartManager() {

    }*/

    public CartManager() {

        //mContext = context;
        //初始化购物车
        mSparseArray = new SparseArray<>();

        //加载网络数据到内存中
        //loadLocalToMemory();

    }

    /**
     * 加载网络数据到内存中
     */
    public void loadLocalToMemory() {
        List<Shopping> cars = getLoadAll();
        if (null != cars && cars.size() > 0) {
            for (Shopping car : cars) {
                mSparseArray.put(car.getId(), car);
            }
        }
    }

    private List<Shopping> getLoadAll() {
        String cartJson = SPUtils.getString(UiUtilities.getContex(), Constant.CART_JSON);

        List<Shopping> mCars = null;
        LogUtils.d("tag", "购物车json数据:" + cartJson);

        if (cartJson != null) {
            mCars = new Gson().fromJson(cartJson, new TypeToken<ArrayList<Shopping>>() {
            }.getType());
        }
        return mCars;
    }

    //单例模式
    public static CartManager getInstance() {
        if (sCartManager == null) {
            synchronized (CartManager.class) {
                if (sCartManager == null) {
                    sCartManager = new CartManager();
                }
            }
        }

        return sCartManager;
    }


    //是否全选
    public boolean isAllChecked() {

        return getSelectCount() == mSparseArray.size();
    }

    //已选择的个数
    private int getSelectCount() {

        int count = 0;

        int size = mSparseArray.size();
        Log.d(TAG, "getSelectCount: " + size);

        for (int i = 0; i < size; i++) {
            /*int i1 = mSparseArray.keyAt(i);
            Shopping shopping = mSparseArray.get(i1);
            boolean isChecked = shopping.isChecked;*/
            if (mSparseArray.get(mSparseArray.keyAt(i)).isChecked) {
                count++;
            }
        }


        return count;
    }

    /**
     * 获取到商品的总价格
     *
     * @return
     */
    public double getTotalProice() {

        double tempTotalPrice = 0;
        int goodsSize = mSparseArray.size();
        for (int i = 0; i < goodsSize; i++) {
            Shopping tempCar = mSparseArray.get(mSparseArray.keyAt(i));
            if (tempCar.isChecked) {
                if (tempCar.getProduct().getResale_price() == 0) {
                    tempTotalPrice = Arith.add(tempTotalPrice, Arith.mul(tempCar.getProduct().getPrice(), tempCar.count));
                } else {
                    tempTotalPrice = Arith.add(tempTotalPrice, Arith.mul(tempCar.getProduct().getResale_price(), tempCar.count));
                }

            }
        }
        return tempTotalPrice;
    }

    //更新购物车
    public void updateGoodsCar(Shopping data) {
        mSparseArray.remove(data.getId());
        mSparseArray.put(data.getId(), data);
        //持久化,更新购物车条目
        // saveAllGoodsLocal(data);
    }

    //更新购物车条目
    private void saveAllGoodsLocal(Shopping data) {
        OkHttpUtils
                .put()
                .url(Constant.BASE_URL + "customers/" + mCid + "/shopping-cart-items/" + data.getId())
                .addHeader("X-API-TOKEN", mToken)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("更新失败" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("更新成功");

                    }
                });
    }

    /**
     * 从内存获取所有的商品
     *
     * @return
     */
    public List<Shopping> getMemoryAll() {
        //加载网络数据到内存中
        //loadLocalToMemory();

        List<Shopping> cars = new ArrayList<>();
        int carSize = mSparseArray.size();
        for (int i = 0; i < carSize; i++) {
            cars.add(mSparseArray.get(mSparseArray.keyAt(i)));
        }
        return cars;
    }


    //全选按钮
    public boolean setCheckState(boolean state) {
        List<Shopping> memoryAll = getMemoryAll();
        int size = mSparseArray.size();

        for (int i = 0; i < size; i++) {

            mSparseArray.get(mSparseArray.keyAt(i)).isChecked = state;

            //Log.d(TAG, "setCheckState: " + mSparseArray.get(mSparseArray.keyAt(i)).isChecked);
            memoryAll.get(i).isChecked = mSparseArray.get(mSparseArray.keyAt(i)).isChecked;
            //Log.d(TAG, "setCheckState1: " + memoryAll.get(i).isChecked);
        }

        //saveAllGoodsLocal();

        return state;
    }

    //清空购物车内存数据
    public void deleteCartData(){

        int size = mSparseArray.size();
        for (int i = size - 1 ;i> 0 ;i--){
            mSparseArray.removeAt(i);
        }
    }

    //删除购物条目
    public void deleteCartItem(final int iid) {

        //获取用户的id
        mCid = SPUtils.getInt(UiUtilities.getContex(), Constant.USER_CID);
        //获取token
        mToken = SPUtils.getString(UiUtilities.getContex(), Constant.TOKEN);

            LogUtils.d("购物车iid:"+iid);
        OkHttpUtils
                .delete()
                .url(Constant.BASE_URL + "customers/" + mCid + "/shopping-cart-items/" + iid)
                .addHeader("X-API-TOKEN", mToken)
                .build()
                .execute(new Callback() {
                    private int mCode;
                    @Override
                    public Integer parseNetworkResponse(Response response, int id) throws Exception {
                        mCode = response.code();
                        return mCode;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("删除失败" + e +"错误码:"+mCode);
                        Toast.makeText(UiUtilities.getContex(), "删除失败"+e, Toast.LENGTH_SHORT).show();
                        //重新刷新数据
                        EventBus.getDefault().post(new ShoppingCartFragment.MessageData("刷新数据"));
                    }

                    @Override
                    public void onResponse(Object response, int id) {

                            LogUtils.d("code:"+response);

                            Toast.makeText(UiUtilities.getContex(), "删除成功", Toast.LENGTH_SHORT).show();

                            //删除内存的条目购物车
                            deleteCarGoods(iid);


                    }
                });

    }

    private void deleteCarGoods(int iid) {
        mSparseArray.remove(iid);
        EventBus.getDefault().post(new ShoppingCartFragment.MessageAdapter("更新adapter"));
        if (mSparseArray.size() ==0 ){
            EventBus.getDefault().post(new ShoppingCartFragment.MessageData("更新数据"));
        }

    }

    //加入购物条目
    public void postCartItem(int productId, int quantity) {

        if (LoginManager.getInstance().isLoginState(UiUtilities.getContex())) {
            //获取用户的id
            mCid = SPUtils.getInt(UiUtilities.getContex(), Constant.USER_CID);
            //获取token
            mToken = SPUtils.getString(UiUtilities.getContex(), Constant.TOKEN);

            LogUtils.d("token:" + mToken + "  " + mCid);

            OkHttpUtils
                    .post()
                    .url(Constant.BASE_URL + "customers/" + mCid + "/shopping-cart-items")
                    .addHeader("X-API-TOKEN", mToken)
                    .addParams("product_id", String.valueOf(productId))
                    .addParams("store_id", "1")
                    .addParams("quantity", String.valueOf(quantity))
                    .build()
                    .execute(new Callback() {

                        private int mCode;

                        @Override
                        public Object parseNetworkResponse(Response response, int id) throws Exception {
                            mCode = response.code();
                            return null;
                        }

                        @Override
                        public void onError(Call call, Exception e, int id) {
                            LogUtils.d("加入购物车失败" + e + "错误码:" + mCode + "  " + Thread.currentThread());

                            Toast.makeText(UiUtilities.getContex(), "商品已在购物车", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onResponse(Object response, int id) {
                            LogUtils.d("加入购物车成功" + Thread.currentThread());
                            Toast.makeText(UiUtilities.getContex(), "已加入购物车", Toast.LENGTH_SHORT).show();
                        }
                    });

        } else {
            //如果用户没登录,跳转到登录界面
            Intent intent = new Intent(UiUtilities.getContex(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            UiUtilities.getContex().startActivity(intent);

        }
    }

}
