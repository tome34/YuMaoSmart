package com.yumao.yumaosmart.event;

/**
 * Created by kk on 2017/3/8.
 */

public class PhoneNumEvent {
    public String phoneNum;
    public int postion;



    public PhoneNumEvent(String name, int postion) {
        this.phoneNum = name;
        this.postion = postion;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    public PhoneNumEvent(String name) {
        this.phoneNum = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
