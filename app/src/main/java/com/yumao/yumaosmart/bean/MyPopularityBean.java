package com.yumao.yumaosmart.bean;

/**
 * Created by kk on 2017/3/15.
 */

public class MyPopularityBean {
    private String petName;
    private String time;
    private  String touXiangRes;

    public String getTouXiangRes() {
        return touXiangRes;
    }

    public void setTouXiangRes(String touXiangRes) {
        this.touXiangRes = touXiangRes;
    }

    public int getFavour() {
        return favour;
    }

    public void setFavour(int favour) {
        this.favour = favour;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private  int favour;


}
