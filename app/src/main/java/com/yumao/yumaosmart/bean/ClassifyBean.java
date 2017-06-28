package com.yumao.yumaosmart.bean;

/**
 * Created by kk on 2017/3/1.
 */

public class ClassifyBean  {
  public   String name;
 public String resid;

    public ClassifyBean(String resid, String name) {
        this.name = name;
        this.resid = resid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResid() {
        return resid;
    }

    public void setResid(String resid) {
        this.resid = resid;
    }
}
