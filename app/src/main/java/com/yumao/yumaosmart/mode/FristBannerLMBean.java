package com.yumao.yumaosmart.mode;

import java.util.List;

/**
 * Created by kk on 2017/7/11.
 *
 * 栏目轮播图
 */

public class FristBannerLMBean {

    /**
     * vendor_section : {"id":38,"section_name":"VSN_170620_093314","picture":"http://img.yumaozhubao.com/images/00123319.png"}
     * products : [{"id":17702,"name":"足银手镯","price":424,"product_cost":353,"created_on_utc":"2017-06-15T08:47:17+00:00","pictures":["http://img.yumaozhubao.com/images/00120140.jpg","http://img.yumaozhubao.com/images/00120141.jpg","http://img.yumaozhubao.com/images/00120142.jpg","http://img.yumaozhubao.com/images/00120139.jpg"],"thumb":"http://img.yumaozhubao.com/images/00120140_thumb.jpg","resale_price":459},{"id":17703,"name":"足银手镯","price":341,"product_cost":284,"created_on_utc":"2017-06-15T08:47:18+00:00","pictures":["http://img.yumaozhubao.com/images/00120143.jpg","http://img.yumaozhubao.com/images/00120146.jpg","http://img.yumaozhubao.com/images/00120145.jpg","http://img.yumaozhubao.com/images/00120144.jpg"],"thumb":"http://img.yumaozhubao.com/images/00120143_thumb.jpg","resale_price":369},{"id":17704,"name":"足银手镯","price":349,"product_cost":291,"created_on_utc":"2017-06-15T08:47:18+00:00","pictures":["http://img.yumaozhubao.com/images/00120149.jpg","http://img.yumaozhubao.com/images/00120148.jpg","http://img.yumaozhubao.com/images/00120150.jpg","http://img.yumaozhubao.com/images/00120147.jpg"],"thumb":"http://img.yumaozhubao.com/images/00120149_thumb.jpg","resale_price":378}]
     */

    private VendorSectionBean vendor_section;
    private List<ProductsBean> products;

    public void setVendor_section(VendorSectionBean vendor_section) {
        this.vendor_section = vendor_section;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public VendorSectionBean getVendor_section() {
        return vendor_section;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public static class VendorSectionBean {
        /**
         * id : 38
         * section_name : VSN_170620_093314
         * picture : http://img.yumaozhubao.com/images/00123319.png
         */

        private int id;
        private String section_name;
        private String picture;

        public void setId(int id) {
            this.id = id;
        }

        public void setSection_name(String section_name) {
            this.section_name = section_name;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getId() {
            return id;
        }

        public String getSection_name() {
            return section_name;
        }

        public String getPicture() {
            return picture;
        }
    }

    public static class ProductsBean {
        /**
         * id : 17702
         * name : 足银手镯
         * price : 424
         * product_cost : 353
         * created_on_utc : 2017-06-15T08:47:17+00:00
         * pictures : ["http://img.yumaozhubao.com/images/00120140.jpg","http://img.yumaozhubao.com/images/00120141.jpg","http://img.yumaozhubao.com/images/00120142.jpg","http://img.yumaozhubao.com/images/00120139.jpg"]
         * thumb : http://img.yumaozhubao.com/images/00120140_thumb.jpg
         * resale_price : 459
         */

        private int id;
        private String name;
        private int price;
        private int product_cost;
        private String created_on_utc;
        private String thumb;
        private int resale_price;
        private List<String> pictures;

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

        public void setCreated_on_utc(String created_on_utc) {
            this.created_on_utc = created_on_utc;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public void setResale_price(int resale_price) {
            this.resale_price = resale_price;
        }

        public void setPictures(List<String> pictures) {
            this.pictures = pictures;
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

        public String getCreated_on_utc() {
            return created_on_utc;
        }

        public String getThumb() {
            return thumb;
        }

        public int getResale_price() {
            return resale_price;
        }

        public List<String> getPictures() {
            return pictures;
        }
    }
}
