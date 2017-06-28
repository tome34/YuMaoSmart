package com.yumao.yumaosmart.bean;

/**
 * Created by kk on 2017/3/3.
 */

public class ShoppingCardbean {
    private String  storeName;
    private String  goodsName;
    private String  price;
    private String  num;

    public String getGoodsRes() {
        return goodsRes;
    }

    public void setGoodsRes(String goodsRes) {
        this.goodsRes = goodsRes;
    }

    private String  goodsRes;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    private  boolean isStore;
    private  boolean  isstoreSelected;
    private  boolean isGoodsSelected;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isStore() {
        return isStore;
    }

    public void setStore(boolean store) {
        isStore = store;
    }

    public boolean isstoreSelected() {
        return isstoreSelected;
    }

    public void setIsstoreSelected(boolean isstoreSelected) {
        this.isstoreSelected = isstoreSelected;
    }

    public boolean isGoodsSelected() {
        return isGoodsSelected;
    }

    public void setGoodsSelected(boolean goodsSelected) {
        isGoodsSelected = goodsSelected;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
