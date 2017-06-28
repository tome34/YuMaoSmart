package com.yumao.yumaosmart.event;

/**
 * Created by kk on 2017/3/9.
 */

public class BirthDayEvent {
    public String birthDay;
    public  int postion;

    public BirthDayEvent(String birthDay, int postion) {
        this.birthDay = birthDay;
        this.postion = postion;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }
}
