package com.yumao.yumaosmart.mode;

import java.util.List;

/**
 * Created by kk on 2017/3/29.
 */

public class GoodsDetailMode  {

    /**
     * work_duration : 3个工作日出货，7天无理由退换
     * pictures : ["http://img.yumaozhubao.com/images/00087892.jpg","http://img.yumaozhubao.com/images/00087893.jpg","http://img.yumaozhubao.com/images/00087894.jpg","http://img.yumaozhubao.com/images/00087895.jpg"]
     * thumb : http://img.yumaozhubao.com/images/00087892_thumb.jpg
     * id : 1125
     * categories : [{"id":693,"name":"吊坠"},{"id":528,"name":"银饰"}]
     * vendor : {"logo":"http://img.yumaozhubao.com/images/00090911.png","product_amount":3479,"id":1,"name":"玉猫平台","address":"深圳市罗湖区田贝四路水贝万山A座4006","vendor_type":2}
     * manufacturers : []
     * name : S925蛋形五角星石榴吊坠
     * short_description : (73970)S925蛋形五角星石榴吊坠，材质：S925，总重约2.9g，主石：石榴石，尺寸约4*6mm
     * sku : CB1011135
     * stock_quantity : 1
     * price : 559
     * product_cost : 208
     * actual_cost_price : 208
     * created_on_utc : 2017-03-02T07:12:22+0000
     */

    private String work_duration;
    private String thumb;
    private int id;
    private VendorBean vendor;
    private String name;
    private String short_description;
    private String sku;
    private int stock_quantity;
    private int price;
    private int product_cost;
    private int actual_cost_price;
    private String created_on_utc;
    private List<String> pictures;
    private List<CategoriesBean> categories;
    private List<?> manufacturers;

    public String getWork_duration() {
        return work_duration;
    }

    public void setWork_duration(String work_duration) {
        this.work_duration = work_duration;
    }

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

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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

    public String getCreated_on_utc() {
        return created_on_utc;
    }

    public void setCreated_on_utc(String created_on_utc) {
        this.created_on_utc = created_on_utc;
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

    public List<?> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(List<?> manufacturers) {
        this.manufacturers = manufacturers;
    }

    public static class VendorBean {
        /**
         * logo : http://img.yumaozhubao.com/images/00090911.png
         * product_amount : 3479
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
         * id : 693
         * name : 吊坠
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
