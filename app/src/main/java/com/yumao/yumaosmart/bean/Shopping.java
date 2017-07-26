package com.yumao.yumaosmart.bean;

import com.yumao.yumaosmart.mode.ShoppingCartMode;

/**
 * Created by mwqi on 2017/3/15.
 */

public class Shopping {

    /**
     * id : 10580
     * quantity : 1
     * store : {"id":1}
     * product : {"id":19743,"name":"冰绿戒面","price":3910,"product_cost":1955,"stock_quantity":1,"thumb":"http://img.yumaozhubao.com/images/00127641_thumb.jpg","vendor":{"id":1},"warehouse":{"address":{"state_province":{"id":205}}},"resale_price":3910}
     */

    private int id;
    private int quantity;
    private ShoppingCartMode.StoreBean store;
    private ShoppingCartMode.ProductBean product;

    public boolean isChecked = false;  //checked

    public int count = 1;              //购物车数量

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStore(ShoppingCartMode.StoreBean store) {
        this.store = store;
    }

    public void setProduct(ShoppingCartMode.ProductBean product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public ShoppingCartMode.StoreBean getStore() {
        return store;
    }

    public ShoppingCartMode.ProductBean getProduct() {
        return product;
    }

    public static class StoreBean {
        /**
         * id : 1
         */

        private int id;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    public static class ProductBean {
        /**
         * id : 19743
         * name : 冰绿戒面
         * price : 3910
         * product_cost : 1955
         * stock_quantity : 1
         * thumb : http://img.yumaozhubao.com/images/00127641_thumb.jpg
         * vendor : {"id":1}
         * warehouse : {"address":{"state_province":{"id":205}}}
         * resale_price : 3910
         */

        private int id;
        private String name;
        private int price;
        private int product_cost;
        private int stock_quantity;
        private String thumb;
        private ShoppingCartMode.ProductBean.VendorBean vendor;
        private ShoppingCartMode.ProductBean.WarehouseBean warehouse;
        private int resale_price;

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

        public void setStock_quantity(int stock_quantity) {
            this.stock_quantity = stock_quantity;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public void setVendor(ShoppingCartMode.ProductBean.VendorBean vendor) {
            this.vendor = vendor;
        }

        public void setWarehouse(ShoppingCartMode.ProductBean.WarehouseBean warehouse) {
            this.warehouse = warehouse;
        }

        public void setResale_price(int resale_price) {
            this.resale_price = resale_price;
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

        public int getStock_quantity() {
            return stock_quantity;
        }

        public String getThumb() {
            return thumb;
        }

        public ShoppingCartMode.ProductBean.VendorBean getVendor() {
            return vendor;
        }

        public ShoppingCartMode.ProductBean.WarehouseBean getWarehouse() {
            return warehouse;
        }

        public int getResale_price() {
            return resale_price;
        }

        public static class VendorBean {
            /**
             * id : 1
             */

            private int id;

            public void setId(int id) {
                this.id = id;
            }

            public int getId() {
                return id;
            }
        }

        public static class WarehouseBean {
            /**
             * address : {"state_province":{"id":205}}
             */

            private ShoppingCartMode.ProductBean.WarehouseBean.AddressBean address;

            public void setAddress(ShoppingCartMode.ProductBean.WarehouseBean.AddressBean address) {
                this.address = address;
            }

            public ShoppingCartMode.ProductBean.WarehouseBean.AddressBean getAddress() {
                return address;
            }

            public static class AddressBean {
                /**
                 * state_province : {"id":205}
                 */

                private ShoppingCartMode.ProductBean.WarehouseBean.AddressBean.StateProvinceBean state_province;

                public void setState_province(ShoppingCartMode.ProductBean.WarehouseBean.AddressBean.StateProvinceBean state_province) {
                    this.state_province = state_province;
                }

                public ShoppingCartMode.ProductBean.WarehouseBean.AddressBean.StateProvinceBean getState_province() {
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
    }

}
