package com.yumao.yumaosmart.bean;

/**
 * Created by kk on 2017/7/7.
 */

public class FristBannerBean {

    /**
     * id : 36
     * section_name : 爱的信物 "饰"不宜迟
     * section_href : http://mall.yumaozhubao.com/sales/41
     * display_order : 1
     * created_on_utc : 2017-06-19T07:45:20+00:00
     * updated_on_utc : 2017-06-27T11:05:48+00:00
     * published : true
     * picture : http://img.yumaozhubao.com/images/00123321.png
     * vendor : {"id":1,"name":"玉猫平台"}
     */

    private int id;
    private String section_name;
    private String section_href;
    private int display_order;
    private String created_on_utc;
    private String updated_on_utc;
    private boolean published;
    private String picture;
    private VendorBean vendor;

    public void setId(int id) {
        this.id = id;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public void setSection_href(String section_href) {
        this.section_href = section_href;
    }

    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
    }

    public void setCreated_on_utc(String created_on_utc) {
        this.created_on_utc = created_on_utc;
    }

    public void setUpdated_on_utc(String updated_on_utc) {
        this.updated_on_utc = updated_on_utc;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setVendor(VendorBean vendor) {
        this.vendor = vendor;
    }

    public int getId() {
        return id;
    }

    public String getSection_name() {
        return section_name;
    }

    public String getSection_href() {
        return section_href;
    }

    public int getDisplay_order() {
        return display_order;
    }

    public String getCreated_on_utc() {
        return created_on_utc;
    }

    public String getUpdated_on_utc() {
        return updated_on_utc;
    }

    public boolean getPublished() {
        return published;
    }

    public String getPicture() {
        return picture;
    }

    public VendorBean getVendor() {
        return vendor;
    }

    public static class VendorBean {
        /**
         * id : 1
         * name : 玉猫平台
         */

        private int id;
        private String name;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
