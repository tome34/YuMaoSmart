package com.yumao.yumaosmart.bean;

import android.os.Bundle;

public class SmartTabInfo {
    public Class  clz;
    public String title;
    public Bundle args;

    public SmartTabInfo(Class clz, String title, Bundle args) {
        this.clz = clz;
        this.title = title;
        this.args = args;
    }
}