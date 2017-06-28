package com.yumao.yumaosmart.event;

/**
 * Created by kk on 2017/3/9.
 */

public class GenderEvent
{
    public String gender;
    public int postion;

    public GenderEvent(String gender, int postion) {
        this.gender = gender;
        this.postion = postion;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public GenderEvent(String gender) {

        this.gender = gender;
    }
}
