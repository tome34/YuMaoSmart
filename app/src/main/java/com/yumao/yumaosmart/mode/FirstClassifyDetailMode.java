package com.yumao.yumaosmart.mode;

import java.util.List;

/**
 * Created by kk on 2017/4/5.
 */

public class FirstClassifyDetailMode {

    /**
     * pictures : ["http://img.yumaozhubao.com/images/00097528.jpg","http://img.yumaozhubao.com/images/00097529.jpg","http://img.yumaozhubao.com/images/00097530.jpg","http://img.yumaozhubao.com/images/00097531.jpg","http://img.yumaozhubao.com/images/00097532.jpg"]
     * thumb : http://img.yumaozhubao.com/images/00097528_thumb.jpg
     * id : 4567
     * categories : [{"id":730,"name":"平安镯"},{"id":527,"name":"翡翠"}]
     * vendor : {"logo":"http://img.yumaozhubao.com/images/00090911.png","product_amount":4044,"id":1,"name":"玉猫平台","address":"深圳市罗湖区田贝四路水贝万山A座4006","vendor_type":2}
     * name : 冰种带绿圆条 54圈口
     * stock_quantity : 1
     * price : 89700
     * product_cost : 17940
     * actual_cost_price : 15600
     */

    private String thumb;
    private int id;
    private VendorBean vendor;
    private String name;
    private int stock_quantity;
    private int price;
    private int product_cost;
    private int actual_cost_price;
    private List<String> pictures;
    private List<CategoriesBean> categories;

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VendorBean getVendor() {
        return vendor;
    }

    public void setVendor(VendorBean vendor) {
        this.vendor = vendor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProduct_cost() {
        return product_cost;
    }

    public void setProduct_cost(int product_cost) {
        this.product_cost = product_cost;
    }

    public int getActual_cost_price() {
        return actual_cost_price;
    }

    public void setActual_cost_price(int actual_cost_price) {
        this.actual_cost_price = actual_cost_price;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public List<CategoriesBean> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesBean> categories) {
        this.categories = categories;
    }

    public static class VendorBean {
        /**
         * logo : http://img.yumaozhubao.com/images/00090911.png
         * product_amount : 4044
         * id : 1
         * name : 玉猫平台
         * address : 深圳市罗湖区田贝四路水贝万山A座4006
         * vendor_type : 2
         */

        private String logo;
        private int product_amount;
        private int id;
        private String name;
        private String address;
        private int vendor_type;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getProduct_amount() {
            return product_amount;
        }

        public void setProduct_amount(int product_amount) {
            this.product_amount = product_amount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getVendor_type() {
            return vendor_type;
        }

        public void setVendor_type(int vendor_type) {
            this.vendor_type = vendor_type;
        }
    }

    public static class CategoriesBean {
        /**
         * id : 730
         * name : 平安镯
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
