package com.yumao.yumaosmart.event;

/**
 * Created by kk on 2017/3/7.
 */

public class SweetNameEvent {
    public String sweetName;

    public SweetNameEvent(String sweetName, int postion) {
        this.sweetName = sweetName;
        this.postion = postion;
    }

    public int postion;

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    public SweetNameEvent(String sweetName) {
        this.sweetName = sweetName;
    }

    public String getSweetName() {
        return sweetName;
    }

    public void setSweetName(String sweetName) {
        this.sweetName = sweetName;
    }
}
