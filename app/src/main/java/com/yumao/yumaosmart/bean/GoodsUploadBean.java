package com.yumao.yumaosmart.bean;

/**
 * Created by kk on 2017/3/21.
 */

public class GoodsUploadBean  {
        private  boolean isSelected;
        private  String  pictureRes;
        private  String name;
        private  String serialNum;
        private  String price;
        private  String time;
        private  String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPictureRes() {
        return pictureRes;
    }

    public void setPictureRes(String pictureRes) {
        this.pictureRes = pictureRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
