package com.yumao.yumaosmart.bean;

/**
 * Created by kk on 2017/3/14.
 */

public class VipOrderListBean  {
    private String touXiangRes;
    private String  orderSerialnum;
    private  String petName;
    private String orderTime;
    private  String orderTotalMoney;
    private  String cityPartner;
    private  String centerPartner;
    private  String orderState;

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getTouXiangRes() {
        return touXiangRes;
    }

    public void setTouXiangRes(String touXiangRes) {
        this.touXiangRes = touXiangRes;
    }

    public String getCenterPartner() {
        return centerPartner;
    }

    public void setCenterPartner(String centerPartner) {
        this.centerPartner = centerPartner;
    }

    public String getOrderSerialnum() {
        return orderSerialnum;
    }

    public void setOrderSerialnum(String orderSerialnum) {
        this.orderSerialnum = orderSerialnum;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTotalMoney() {
        return orderTotalMoney;
    }

    public void setOrderTotalMoney(String orderTotalMoney) {
        this.orderTotalMoney = orderTotalMoney;
    }

    public String getCityPartner() {
        return cityPartner;
    }

    public void setCityPartner(String cityPartner) {
        this.cityPartner = cityPartner;
    }
}
