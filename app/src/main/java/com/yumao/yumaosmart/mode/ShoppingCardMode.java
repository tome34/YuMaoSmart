package com.yumao.yumaosmart.mode;

import java.util.List;

/**
 * Created by kk on 2017/4/10.
 */

public class ShoppingCardMode

{

    /**
     * id : 8672
     * store : {"id":1,"name":"玉猫平台"}
     * product : {"pictures":["http://img.yumaozhubao.com/images/00088163.jpg","http://img.yumaozhubao.com/images/00088164.jpg","http://img.yumaozhubao.com/images/00088165.jpg","http://img.yumaozhubao.com/images/00088166.jpg","http://img.yumaozhubao.com/images/00088167.jpg"],"thumb":"http://img.yumaozhubao.com/images/00088163_thumb.jpg","short_description":"大件高冰坐佛","full_description":"大件高冰坐佛，种老起光，冰润通透，圆润饱满！实物更美！","id":1181,"categories":[{"id":696,"name":"佛公"},{"id":527,"name":"翡翠"}],"vendor":{"logo":"http://img.yumaozhubao.com/images/00090911.png","product_amount":4332,"id":1,"name":"玉猫平台","address":"深圳市罗湖区田贝四路水贝万山A座4006","vendor_type":2},"name":"大件高冰坐佛","stock_quantity":1,"price":54312,"product_cost":45622,"actual_cost_price":43450}
     * shopping_cart_type_id : 1
     * created_on_utc : 2017-04-10T07:29:08+0000
     * quantity : 1
     */

    private int id;
    private StoreBean store;
    private ProductBean product;
    private int shopping_cart_type_id;
    private String created_on_utc;
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StoreBean getStore() {
        return store;
    }

    public void setStore(StoreBean store) {
        this.store = store;
    }

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public int getShopping_cart_type_id() {
        return shopping_cart_type_id;
    }

    public void setShopping_cart_type_id(int shopping_cart_type_id) {
        this.shopping_cart_type_id = shopping_cart_type_id;
    }

    public String getCreated_on_utc() {
        return created_on_utc;
    }

    public void setCreated_on_utc(String created_on_utc) {
        this.created_on_utc = created_on_utc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static class StoreBean {
        /**
         * id : 1
         * name : 玉猫平台
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

    public static class ProductBean {
        /**
         * pictures : ["http://img.yumaozhubao.com/images/00088163.jpg","http://img.yumaozhubao.com/images/00088164.jpg","http://img.yumaozhubao.com/images/00088165.jpg","http://img.yumaozhubao.com/images/00088166.jpg","http://img.yumaozhubao.com/images/00088167.jpg"]
         * thumb : http://img.yumaozhubao.com/images/00088163_thumb.jpg
         * short_description : 大件高冰坐佛
         * full_description : 大件高冰坐佛，种老起光，冰润通透，圆润饱满！实物更美！
         * id : 1181
         * categories : [{"id":696,"name":"佛公"},{"id":527,"name":"翡翠"}]
         * vendor : {"logo":"http://img.yumaozhubao.com/images/00090911.png","product_amount":4332,"id":1,"name":"玉猫平台","address":"深圳市罗湖区田贝四路水贝万山A座4006","vendor_type":2}
         * name : 大件高冰坐佛
         * stock_quantity : 1
         * price : 54312
         * product_cost : 45622
         * actual_cost_price : 43450
         */

        private String thumb;
        private String short_description;
        private String full_description;
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

        public String getShort_description() {
            return short_description;
        }

        public void setShort_description(String short_description) {
            this.short_description = short_description;
        }

        public String getFull_description() {
            return full_description;
        }

        public void setFull_description(String full_description) {
            this.full_description = full_description;
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
             * product_amount : 4332
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
             * id : 696
             * name : 佛公
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
}
