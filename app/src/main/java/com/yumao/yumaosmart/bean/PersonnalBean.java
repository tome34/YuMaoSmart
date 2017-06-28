package com.yumao.yumaosmart.bean;

/**
 * Created by kk on 2017/3/6.
 */

public class PersonnalBean {
    public String name;
    public String backSrc;

    public PersonnalBean(String name, String backSrc) {
        this.name = name;
        this.backSrc = backSrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackSrc() {
        return backSrc;
    }

    public void setBackSrc(String backSrc) {
        this.backSrc = backSrc;
    }
}
