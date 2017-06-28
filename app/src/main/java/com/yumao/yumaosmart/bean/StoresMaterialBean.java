package com.yumao.yumaosmart.bean;

/**
 * Created by kk on 2017/3/15.
 */

public class StoresMaterialBean {
    private  String storesName;
    private  String storesPhone;
    private  String storesAddress;
    private  boolean showZhuBao;
    private  boolean showZhuanShi;

    public String getStoresName() {
        return storesName;
    }

    public void setStoresName(String storesName) {
        this.storesName = storesName;
    }

    public String getStoresPhone() {
        return storesPhone;
    }

    public void setStoresPhone(String storesPhone) {
        this.storesPhone = storesPhone;
    }

    public String getStoresAddress() {
        return storesAddress;
    }

    public void setStoresAddress(String storesAddress) {
        this.storesAddress = storesAddress;
    }

    public boolean isShowZhuBao() {
        return showZhuBao;
    }

    public void setShowZhuBao(boolean showZhuBao) {
        this.showZhuBao = showZhuBao;
    }

    public boolean isShowZhuanShi() {
        return showZhuanShi;
    }

    public void setShowZhuanShi(boolean showZhuanShi) {
        this.showZhuanShi = showZhuanShi;
    }
}
