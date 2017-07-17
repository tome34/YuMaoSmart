package com.yumao.yumaosmart.mode;

import java.util.List;

/**
 * Created by kk on 2017/7/13.
 *
 * 首页分类列表recyclerview
 */

public class FirstListRvMode {

    /**
     * category : {"id":3,"name":"翡翠"}
     * products : [{"created_on_utc":"2017-07-11T08:42:34+00:00","id":19746,"name":"冰糯种扁条镯","pictures":["http://img.yumaozhubao.com/images/00127668.jpg","http://img.yumaozhubao.com/images/00127669.jpg","http://img.yumaozhubao.com/images/00127670.jpg","http://img.yumaozhubao.com/images/00127671.jpg","http://img.yumaozhubao.com/images/00127672.jpg","http://img.yumaozhubao.com/images/00127673.jpg","http://img.yumaozhubao.com/images/00127674.jpg","http://img.yumaozhubao.com/images/00127675.jpg","http://img.yumaozhubao.com/images/00127676.jpg"],"price":11500,"product_cost":5750,"resale_price":11500,"thumb":"http://img.yumaozhubao.com/images/00127668_thumb.jpg"},{"created_on_utc":"2017-07-11T08:42:30+00:00","id":19745,"name":"冰糯种带绿扁条镯","pictures":["http://img.yumaozhubao.com/images/00127659.jpg","http://img.yumaozhubao.com/images/00127660.jpg","http://img.yumaozhubao.com/images/00127661.jpg","http://img.yumaozhubao.com/images/00127662.jpg","http://img.yumaozhubao.com/images/00127663.jpg","http://img.yumaozhubao.com/images/00127664.jpg","http://img.yumaozhubao.com/images/00127665.jpg","http://img.yumaozhubao.com/images/00127666.jpg","http://img.yumaozhubao.com/images/00127667.jpg"],"price":11500,"product_cost":5750,"resale_price":11500,"thumb":"http://img.yumaozhubao.com/images/00127659_thumb.jpg"},{"created_on_utc":"2017-07-11T08:42:27+00:00","id":19744,"name":"冰种飘绿平安扣一对","pictures":["http://img.yumaozhubao.com/images/00127650.jpg","http://img.yumaozhubao.com/images/00127651.jpg","http://img.yumaozhubao.com/images/00127652.jpg","http://img.yumaozhubao.com/images/00127653.jpg","http://img.yumaozhubao.com/images/00127654.jpg","http://img.yumaozhubao.com/images/00127655.jpg","http://img.yumaozhubao.com/images/00127656.jpg","http://img.yumaozhubao.com/images/00127657.jpg","http://img.yumaozhubao.com/images/00127658.jpg"],"price":2530,"product_cost":1265,"resale_price":2530,"thumb":"http://img.yumaozhubao.com/images/00127650_thumb.jpg"}]
     */

    private CategoryBean category;
    private List<ProductsBean> products;

    public void setCategory(CategoryBean category) {
        this.category = category;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public CategoryBean getCategory() {
        return category;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public static class CategoryBean {
        /**
         * id : 3
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

    public static class ProductsBean {
        /**
         * created_on_utc : 2017-07-11T08:42:34+00:00
         * id : 19746
         * name : 冰糯种扁条镯
         * pictures : ["http://img.yumaozhubao.com/images/00127668.jpg","http://img.yumaozhubao.com/images/00127669.jpg","http://img.yumaozhubao.com/images/00127670.jpg","http://img.yumaozhubao.com/images/00127671.jpg","http://img.yumaozhubao.com/images/00127672.jpg","http://img.yumaozhubao.com/images/00127673.jpg","http://img.yumaozhubao.com/images/00127674.jpg","http://img.yumaozhubao.com/images/00127675.jpg","http://img.yumaozhubao.com/images/00127676.jpg"]
         * price : 11500
         * product_cost : 5750
         * resale_price : 11500
         * thumb : http://img.yumaozhubao.com/images/00127668_thumb.jpg
         */

        private String created_on_utc;
        private int id;
        private String name;
        private int price;
        private int product_cost;
        private int resale_price;
        private String thumb;
        private List<String> pictures;

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

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public void setPictures(List<String> pictures) {
            this.pictures = pictures;
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

        public String getThumb() {
            return thumb;
        }

        public List<String> getPictures() {
            return pictures;
        }
    }
}
