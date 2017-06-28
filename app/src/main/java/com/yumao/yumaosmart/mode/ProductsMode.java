package com.yumao.yumaosmart.mode;

import java.util.List;

/**
 * Created by kk on 2017/3/28.
 */

public class ProductsMode {


    /**
     * category : {"id":3,"name":"翡翠","display_order":1}
     * products : [{"id":1167,"name":"一手绿戒面","price":"27429.0000","pictures":["http://img.yumaozhubao.com/images/00088094.jpg","http://img.yumaozhubao.com/images/00088095.jpg","http://img.yumaozhubao.com/images/00088096.jpg","http://img.yumaozhubao.com/images/00088097.jpg","http://img.yumaozhubao.com/images/00088098.jpg"],"thumb":"http://img.yumaozhubao.com/images/00088094_thumb.jpg"},{"id":1173,"name":"冰飘色手镯","price":"16989.0000","pictures":["http://img.yumaozhubao.com/images/00088123.jpg","http://img.yumaozhubao.com/images/00088124.jpg","http://img.yumaozhubao.com/images/00088125.jpg","http://img.yumaozhubao.com/images/00088126.jpg","http://img.yumaozhubao.com/images/00088127.jpg"],"thumb":"http://img.yumaozhubao.com/images/00088123_thumb.jpg"},{"id":1181,"name":"大件高冰坐佛","price":"54312.0000","pictures":["http://img.yumaozhubao.com/images/00088163.jpg","http://img.yumaozhubao.com/images/00088164.jpg","http://img.yumaozhubao.com/images/00088165.jpg","http://img.yumaozhubao.com/images/00088166.jpg","http://img.yumaozhubao.com/images/00088167.jpg"],"thumb":"http://img.yumaozhubao.com/images/00088163_thumb.jpg"}]
     */

    private CategoryBean category;
    private List<ProductsBean> products;

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public static class CategoryBean {
        /**
         * id : 3
         * name : 翡翠
         * display_order : 1
         */

        private int id;
        private String name;
        private int display_order;

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

        public int getDisplay_order() {
            return display_order;
        }

        public void setDisplay_order(int display_order) {
            this.display_order = display_order;
        }
    }

    public static class ProductsBean {
        /**
         * id : 1167
         * name : 一手绿戒面
         * price : 27429.0000
         * pictures : ["http://img.yumaozhubao.com/images/00088094.jpg","http://img.yumaozhubao.com/images/00088095.jpg","http://img.yumaozhubao.com/images/00088096.jpg","http://img.yumaozhubao.com/images/00088097.jpg","http://img.yumaozhubao.com/images/00088098.jpg"]
         * thumb : http://img.yumaozhubao.com/images/00088094_thumb.jpg
         */

        private int id;
        private String name;
        private String price;
        private String thumb;
        private List<String> pictures;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public List<String> getPictures() {
            return pictures;
        }

        public void setPictures(List<String> pictures) {
            this.pictures = pictures;
        }
    }
}
