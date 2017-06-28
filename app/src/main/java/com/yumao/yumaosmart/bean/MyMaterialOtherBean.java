package com.yumao.yumaosmart.bean;

import com.yumao.yumaosmart.delegate.DisPlay;

/**
 * Created by kk on 2017/3/6.
 */

public class MyMaterialOtherBean implements DisPlay {
    public String title;
    public String content;

    public MyMaterialOtherBean() {

    }
    public MyMaterialOtherBean(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
