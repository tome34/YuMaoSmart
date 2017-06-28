package com.yumao.yumaosmart.bean;

/**
 * Created by kk on 2017/3/9.
 */

public class MyAddressBean {
    public String name;
    public  String detailAddress;
    public  String phoneNum;
    public boolean isDefaultAddress;
    public MyAddressBean(){

    }
    public MyAddressBean(String name, String detailAddress, String phoneNum, boolean isDefaultAddress) {
        this.name = name;
        this.detailAddress = detailAddress;
        this.phoneNum = phoneNum;
        this.isDefaultAddress = isDefaultAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDefaultAddress() {
        return isDefaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        isDefaultAddress = defaultAddress;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
