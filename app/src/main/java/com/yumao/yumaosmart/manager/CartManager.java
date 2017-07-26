package com.yumao.yumaosmart.manager;

import android.util.SparseArray;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yumao.yumaosmart.bean.Shopping;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.fragment.ShoppingCartFragment;
import com.yumao.yumaosmart.utils.Arith;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.SPUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

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
    private static int mCid = SPUtils.getInt(UiUtilities.getContex(), Constant.USER_CID);
    //获取token
    private static String mToken= SPUtils.getString(UiUtilities.getContex(), Constant.TOKEN);;

   /* public CartManager() {

    }*/

    public CartManager() {

        //mContext = context;
        //初始化购物车
        mSparseArray = new SparseArray<>();

        //加载网络数据到内存中
        loadLocalToMemory();

    }

    /**
     * 加载网络数据到内存中
     */
    private void loadLocalToMemory() {
        List<Shopping> cars = getLoadAll();
        if(null != cars && cars.size() > 0){
            for (Shopping car : cars) {
                mSparseArray.put(car.getId(), car);
            }
        }
    }


    private List<Shopping> getLoadAll() {
        String cartJson = SPUtils.getString(UiUtilities.getContex(), Constant.CART_JSON);

        List<Shopping> mCars = null;
            LogUtils.d("tag","购物车json数据:"+cartJson);

        if (cartJson != null){
            mCars= new Gson().fromJson(cartJson, new TypeToken<ArrayList<Shopping>>() {
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

        for (int i = 0; i < size; i++) {
            if(mSparseArray.get(mSparseArray.keyAt(i)).isChecked){
                count++;
            }
        }


        return count;
    }

    /**
     * 获取到商品的总价格
     * @return
     */
    public double getTotalProice() {

        double tempTotalPrice = 0;
        int goodsSize = mSparseArray.size();
        for (int i = 0; i < goodsSize; i++) {
            Shopping tempCar = mSparseArray.get(mSparseArray.keyAt(i));
            if (tempCar.isChecked) {
                if (tempCar.getProduct().getResale_price()== 0){
                    tempTotalPrice = Arith.add(tempTotalPrice, Arith.mul(tempCar.getProduct().getPrice(), tempCar.count));
                }else{
                    tempTotalPrice = Arith.add(tempTotalPrice, Arith.mul(tempCar.getProduct().getResale_price(), tempCar.count));
                }

            }
        }
        return tempTotalPrice;
    }

    //更新购物车
    public void updateGoodsCar(Shopping data) {
        mSparseArray.remove(data.getId());
        mSparseArray.put(data.getId(),data);
        //持久化,更新购物车条目
       // saveAllGoodsLocal(data);
    }

    //更新购物车条目
    private void saveAllGoodsLocal(Shopping data) {
        OkHttpUtils
                .put()
                .url(Constant.BASE_URL+"customers/"+mCid+"/shopping-cart-items/"+data.getId())
                .addHeader("X-API-TOKEN",mToken)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("更新失败"+e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("更新成功");

                    }
                });
    }

    /**
     * 从内存获取所有的商品
     * @return
     */
    public List<Shopping> getMemoryAll() {

        List<Shopping> cars = new ArrayList<>();
        int carSize = mSparseArray.size();
        for (int i = 0; i < carSize; i++) {
            cars.add(mSparseArray.get(mSparseArray.keyAt(i)));
        }
        return cars;
    }


    public boolean setCheckState(boolean state) {

        int size = mSparseArray.size();

        for (int i = 0; i < size; i++) {

            mSparseArray.get(mSparseArray.keyAt(i)).isChecked = state;
        }

        //saveAllGoodsLocal();

        return state;
    }



    //删除购物条目
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

                        EventBus.getDefault().post(new ShoppingCartFragment.MessageAdapter("更新adapter"));

                    }
                });
    }

    //加入购物条目
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
