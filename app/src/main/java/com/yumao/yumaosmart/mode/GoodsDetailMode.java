package com.yumao.yumaosmart.mode;

import java.util.List;

/**
 * Created by kk on 2017/3/29.
 *
 * 产品详情页
 */

public class GoodsDetailMode  {


    /**
     * id : 19743
     * name : 冰绿戒面
     * price : 3910
     * sku : YM-42086
     * weight : .0000
     * short_description : 冰绿戒面
     * full_description : 冰绿戒面，冰透色阳，漂亮讨喜，镶嵌效果翻倍
     * created_on_utc : 2017-07-11T08:42:23+00:00
     * product_cost : 1955
     * actual_cost_price : 1700
     * stock_quantity : 1
     * vendor : {"id":1,"name":"玉猫平台","address":"深圳市罗湖区田贝四路水贝万山A座4006","vendor_type":3,"product_amount":9057}
     * categories : [{"id":527,"name":"翡翠"},{"id":759,"name":"其它"}]
     * specifications : [{"种水":"冰种"},{"颜色":"阳绿"},{"翡翠尺寸":"长17.3mm,宽6.4mm,厚3.7mm"}]
     * manufacturers : [{"id":18,"name":"玉猫"}]
     * pictures : ["http://img.yumaozhubao.com/images/00127641.jpg","http://img.yumaozhubao.com/images/00127642.jpg","http://img.yumaozhubao.com/images/00127643.jpg","http://img.yumaozhubao.com/images/00127644.jpg","http://img.yumaozhubao.com/images/00127645.jpg","http://img.yumaozhubao.com/images/00127646.jpg","http://img.yumaozhubao.com/images/00127647.jpg","http://img.yumaozhubao.com/images/00127648.jpg","http://img.yumaozhubao.com/images/00127649.jpg"]
     * warehouse : {"id":1,"name":"广东-揭阳","address":{"state_province":{"id":205}}}
     * thumb : http://img.yumaozhubao.com/images/00127641_thumb.jpg
     * work_duration : 3个工作日出货，7天无理由退换
     * resale_price : 3910
     */

    private int id;
    private String name;
    private int price;
    private String sku;
    private String weight;
    private String short_description;
    private String full_description;
    private String created_on_utc;
    private int product_cost;
    private int actual_cost_price;
    private int stock_quantity;
    private VendorBean vendor;
    private WarehouseBean warehouse;
    private String thumb;
    private String work_duration;
    private int resale_price;
    private List<CategoriesBean> categories;
    private List<SpecificationsBean> specifications;
    private List<ManufacturersBean> manufacturers;
    private List<String> pictures;
    public List<String> medias ;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public void setFull_description(String full_description) {
        this.full_description = full_description;
    }

    public void setCreated_on_utc(String created_on_utc) {
        this.created_on_utc = created_on_utc;
    }

    public void setProduct_cost(int product_cost) {
        this.product_cost = product_cost;
    }

    public void setActual_cost_price(int actual_cost_price) {
        this.actual_cost_price = actual_cost_price;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public void setVendor(VendorBean vendor) {
        this.vendor = vendor;
    }

    public void setWarehouse(WarehouseBean warehouse) {
        this.warehouse = warehouse;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public void setWork_duration(String work_duration) {
        this.work_duration = work_duration;
    }

    public void setResale_price(int resale_price) {
        this.resale_price = resale_price;
    }

    public void setCategories(List<CategoriesBean> categories) {
        this.categories = categories;
    }

    public void setSpecifications(List<SpecificationsBean> specifications) {
        this.specifications = specifications;
    }

    public void setManufacturers(List<ManufacturersBean> manufacturers) {
        this.manufacturers = manufacturers;
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

    public String getSku() {
        return sku;
    }

    public String getWeight() {
        return weight;
    }

    public String getShort_description() {
        return short_description;
    }

    public String getFull_description() {
        return full_description;
    }

    public String getCreated_on_utc() {
        return created_on_utc;
    }

    public int getProduct_cost() {
        return product_cost;
    }

    public int getActual_cost_price() {
        return actual_cost_price;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public VendorBean getVendor() {
        return vendor;
    }

    public WarehouseBean getWarehouse() {
        return warehouse;
    }

    public String getThumb() {
        return thumb;
    }

    public String getWork_duration() {
        return work_duration;
    }

    public int getResale_price() {
        return resale_price;
    }

    public List<CategoriesBean> getCategories() {
        return categories;
    }

    public List<SpecificationsBean> getSpecifications() {
        return specifications;
    }

    public List<ManufacturersBean> getManufacturers() {
        return manufacturers;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public static class VendorBean {
        /**
         * id : 1
         * name : 玉猫平台
         * address : 深圳市罗湖区田贝四路水贝万山A座4006
         * vendor_type : 3
         * product_amount : 9057
         */

        private int id;
        private String name;
        private String address;
        private int vendor_type;
        private int product_amount;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setVendor_type(int vendor_type) {
            this.vendor_type = vendor_type;
        }

        public void setProduct_amount(int product_amount) {
            this.product_amount = product_amount;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public int getVendor_type() {
            return vendor_type;
        }

        public int getProduct_amount() {
            return product_amount;
        }
    }

    public static class WarehouseBean {
        /**
         * id : 1
         * name : 广东-揭阳
         * address : {"state_province":{"id":205}}
         */

        private int id;
        private String name;
        private AddressBean address;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public AddressBean getAddress() {
            return address;
        }

        public static class AddressBean {
            /**
             * state_province : {"id":205}
             */

            private StateProvinceBean state_province;

            public void setState_province(StateProvinceBean state_province) {
                this.state_province = state_province;
            }

            public StateProvinceBean getState_province() {
                return state_province;
            }

            public static class StateProvinceBean {
                /**
                 * id : 205
                 */

                private int id;

                public void setId(int id) {
                    this.id = id;
                }

                public int getId() {
                    return id;
                }
            }
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

    public static class SpecificationsBean {
        /**
         * 种水 : 冰种
         */

        private String 种水;

        public void set种水(String 种水) {
            this.种水 = 种水;
        }

        public String get种水() {
            return 种水;
        }
    }

    public static class ManufacturersBean {
        /**
         * id : 18
         * name : 玉猫
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
