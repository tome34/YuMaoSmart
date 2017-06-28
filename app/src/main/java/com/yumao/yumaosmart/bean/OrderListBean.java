package com.yumao.yumaosmart.bean;

/**
 * Created by kk on 2017/3/10.
 */

public class OrderListBean {
   public String orderlistSerialNumber;
    public String orderlistState;
    public boolean isSelected;
    public  String logoRes;
    public String mallName;
    public String goodsNumber;
    public String goodsDetail;
    public String goodsPrice;
    public String goodsPictureRes;
    public  String goodsTotal;
    public OrderListBean(){}
    public String getOrderlistSerialNumber() {
        return orderlistSerialNumber;
    }

    public void setOrderlistSerialNumber(String orderlistSerialNumber) {
        this.orderlistSerialNumber = orderlistSerialNumber;
    }

    public String getOrderlistState() {
        return orderlistState;
    }

    public void setOrderlistState(String orderlistState) {
        this.orderlistState = orderlistState;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getLogoRes() {
        return logoRes;
    }

    public void setLogoRes(String logoRes) {
        this.logoRes = logoRes;
    }

    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsPictureRes() {
        return goodsPictureRes;
    }

    public void setGoodsPictureRes(String goodsPictureRes) {
        this.goodsPictureRes = goodsPictureRes;
    }

    public String getGoodsTotal() {
        return goodsTotal;
    }

    public void setGoodsTotal(String goodsTotal) {
        this.goodsTotal = goodsTotal;
    }

    public OrderListBean(String goodsTotal, String goodsPictureRes, String orderlistSerialNumber,
                         String orderlistState, boolean isSelected, String logoRes, String mallName,
                         String goodsNumber, String goodsDetail, String goodsPrice) {
        this.goodsTotal = goodsTotal;
        this.goodsPictureRes = goodsPictureRes;
        this.orderlistSerialNumber = orderlistSerialNumber;
        this.orderlistState = orderlistState;
        this.isSelected = isSelected;

        this.logoRes = logoRes;
        this.mallName = mallName;
        this.goodsNumber = goodsNumber;
        this.goodsDetail = goodsDetail;
        this.goodsPrice = goodsPrice;
    }
}
