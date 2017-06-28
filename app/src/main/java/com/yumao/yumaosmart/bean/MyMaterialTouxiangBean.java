package com.yumao.yumaosmart.bean;

import com.yumao.yumaosmart.delegate.DisPlay;

/**
 * Created by kk on 2017/3/6.
 */

public class MyMaterialTouxiangBean implements DisPlay {
    public String touxiangRes;
    public String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTouxiangRes() {
        return touxiangRes;
    }

    public void setTouxiangRes(String touxiangRes) {
        this.touxiangRes = touxiangRes;
    }
}
