package com.yumao.yumaosmart.mode;

/**
 * Created on 15/11/22.
 */
public class ProvinceBean  {
    private String id;
    private String name;

    public ProvinceBean() {

    }

    public ProvinceBean(String  id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
