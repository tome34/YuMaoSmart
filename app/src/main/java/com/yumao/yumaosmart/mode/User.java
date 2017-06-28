package com.yumao.yumaosmart.mode;

import java.util.List;

/**
 * Created by kk on 2017/3/27.
 */

public class User {

    /**
     * gender : M
     * date_of_birth : 2017-03-07
     * state : 3
     * cumulative_sales : 51485251
     * cumulative_assets : 56.7
     * roles : ["Registered","SalesPerson","StoreOwner"]
     * id : 1226405
     * vendor : {"logo":"http://img.yumaozhubao.com/images/00090865.png","product_amount":5,"id":1084,"name":"小泽珠宝",
     * "address":"深圳市罗湖区万山珠宝园A4006","vendor_type":2,"is_central_stock":true}
     * username : 13480666908
     * created_on_utc : 2017-02-20T06:04:19+0000
     * nick_name : 泽兴了
     * phone : 13480666908
     * token : b5b3858cadc9892e0924975253729dafbe0e04a5
     *cid=1323189venderid=1195
     *  X-API-TOKEN
     */

    private String gender;
    private String date_of_birth;
    private int state;
    private int cumulative_sales;
    private double cumulative_assets;
    private int id;
    private VendorBean vendor;
    private String username;
    private String created_on_utc;
    private String nick_name;
    private String phone;
    private String token;
    private List<String> roles;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCumulative_sales() {
        return cumulative_sales;
    }

    public void setCumulative_sales(int cumulative_sales) {
        this.cumulative_sales = cumulative_sales;
    }

    public double getCumulative_assets() {
        return cumulative_assets;
    }

    public void setCumulative_assets(double cumulative_assets) {
        this.cumulative_assets = cumulative_assets;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreated_on_utc() {
        return created_on_utc;
    }

    public void setCreated_on_utc(String created_on_utc) {
        this.created_on_utc = created_on_utc;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public static class VendorBean {
        /**
         * logo : http://img.yumaozhubao.com/images/00090865.png
         * product_amount : 5
         * id : 1084
         * name : 小泽珠宝
         * address : 深圳市罗湖区万山珠宝园A4006
         * vendor_type : 2
         * is_central_stock : true
         */

        private String logo;
        private int product_amount;
        private int id;
        private String name;
        private String address;
        private int vendor_type;
        private boolean is_central_stock;

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

        public boolean isIs_central_stock() {
            return is_central_stock;
        }

        public void setIs_central_stock(boolean is_central_stock) {
            this.is_central_stock = is_central_stock;
        }
    }
}
