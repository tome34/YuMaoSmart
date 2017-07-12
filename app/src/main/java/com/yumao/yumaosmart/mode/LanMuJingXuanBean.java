package com.yumao.yumaosmart.mode;

import java.util.List;

/**
 * Created by kk on 2017/7/12.
 * 栏目精选详情二级页,去掉了 "specifications": [] 规格这个字段
 */

public class LanMuJingXuanBean {

    /**
     * actual_cost_price : 1700
     * categories : [{"id":527,"name":"翡翠"},{"id":759,"name":"其它"}]
     * created_on_utc : 2017-07-11T08:42:23+00:00
     * id : 19743
     * name : 冰绿戒面
     * pictures : ["http://img.yumaozhubao.com/images/00127641.jpg","http://img.yumaozhubao.com/images/00127642.jpg","http://img.yumaozhubao.com/images/00127643.jpg","http://img.yumaozhubao.com/images/00127644.jpg","http://img.yumaozhubao.com/images/00127645.jpg","http://img.yumaozhubao.com/images/00127646.jpg","http://img.yumaozhubao.com/images/00127647.jpg","http://img.yumaozhubao.com/images/00127648.jpg","http://img.yumaozhubao.com/images/00127649.jpg"]
     * price : 3910
     * product_cost : 1955
     * resale_price : 3910
     * stock_quantity : 1
     * thumb : http://img.yumaozhubao.com/images/00127641_thumb.jpg
     * vendor : {"address":"深圳市罗湖区田贝四路水贝万山A座4006","id":1,"logo":"http://img.yumaozhubao.com/images/00119667.png","name":"玉猫平台","product_amount":8985,"vendor_type":3}
     */

    private int actual_cost_price;
    private String created_on_utc;
    private int id;
    private String name;
    private int price;
    private int product_cost;
    private int resale_price;
    private int stock_quantity;
    private String thumb;
    private VendorBean vendor;
    private List<CategoriesBean> categories;
    private List<String> pictures;

    public void setActual_cost_price(int actual_cost_price) {
        this.actual_cost_price = actual_cost_price;
    }

    public void setCreated_on_utc(String created_on_utc) {
        this.created_on_utc = created_on_utc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setProduct_cost(int product_cost) {
        this.product_cost = product_cost;
    }

    public void setResale_price(int resale_price) {
        this.resale_price = resale_price;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public void setVendor(VendorBean vendor) {
        this.vendor = vendor;
    }

    public void setCategories(List<CategoriesBean> categories) {
        this.categories = categories;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public int getActual_cost_price() {
        return actual_cost_price;
    }

    public String getCreated_on_utc() {
        return created_on_utc;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getProduct_cost() {
        return product_cost;
    }

    public int getResale_price() {
        return resale_price;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public String getThumb() {
        return thumb;
    }

    public VendorBean getVendor() {
        return vendor;
    }

    public List<CategoriesBean> getCategories() {
        return categories;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public static class VendorBean {
        /**
         * address : 深圳市罗湖区田贝四路水贝万山A座4006
         * id : 1
         * logo : http://img.yumaozhubao.com/images/00119667.png
         * name : 玉猫平台
         * product_amount : 8985
         * vendor_type : 3
         */

        private String address;
        private int id;
        private String logo;
        private String name;
        private int product_amount;
        private int vendor_type;

        public void setAddress(String address) {
            this.address = address;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setProduct_amount(int product_amount) {
            this.product_amount = product_amount;
        }

        public void setVendor_type(int vendor_type) {
            this.vendor_type = vendor_type;
        }

        public String getAddress() {
            return address;
        }

        public int getId() {
            return id;
        }

        public String getLogo() {
            return logo;
        }

        public String getName() {
            return name;
        }

        public int getProduct_amount() {
            return product_amount;
        }

        public int getVendor_type() {
            return vendor_type;
        }
    }

    public static class CategoriesBean {
        /**
         * id : 527
         * name : 翡翠
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
