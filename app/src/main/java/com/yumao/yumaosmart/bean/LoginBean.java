package com.yumao.yumaosmart.bean;

import java.util.List;

/**
 * Created by kk on 2017/6/27.
 */

public class LoginBean {

    /**
     * created_on_utc : 2017-06-26T00:57:31+00:00
     * cumulative_assets : 0
     * cumulative_sales : 0
     * date_of_birth : 2017-06-26
     * gender : M
     * id : 1439452
     * nick_name : Tome
     * phone : 17817234597
     * roles : ["Registered","RegionalAgentOwner"]
     * token : 1d9d5aea0f80cfec16550cea4760b8d57aa759b7
     * username : 17817234597
     * vendor : {"address":"深圳市罗湖区田贝4路万山珠宝园东座A4006","id":1513,"is_central_stock":false,"logo":"http://img.yumaozhubao.com/images/00122885.png","name":"林少通","product_amount":0,"tel":"17817234597","vendor_type":3}
     */

    private String created_on_utc;
    private int cumulative_assets;
    private int cumulative_sales;
    private String date_of_birth;
    private String gender;
    private int id;
    private String nick_name;
    private String phone;
    private String token;
    private String username;
    private VendorBean vendor;
    private List<String> roles;

    public void setCreated_on_utc(String created_on_utc) {
        this.created_on_utc = created_on_utc;
    }

    public void setCumulative_assets(int cumulative_assets) {
        this.cumulative_assets = cumulative_assets;
    }

    public void setCumulative_sales(int cumulative_sales) {
        this.cumulative_sales = cumulative_sales;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setVendor(VendorBean vendor) {
        this.vendor = vendor;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getCreated_on_utc() {
        return created_on_utc;
    }

    public int getCumulative_assets() {
        return cumulative_assets;
    }

    public int getCumulative_sales() {
        return cumulative_sales;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public VendorBean getVendor() {
        return vendor;
    }

    public List<String> getRoles() {
        return roles;
    }

    public static class VendorBean {
        /**
         * address : 深圳市罗湖区田贝4路万山珠宝园东座A4006
         * id : 1513
         * is_central_stock : false
         * logo : http://img.yumaozhubao.com/images/00122885.png
         * name : 林少通
         * product_amount : 0
         * tel : 17817234597
         * vendor_type : 3
         */

        private String address;
        private int id;
        private boolean is_central_stock;
        private String logo;
        private String name;
        private int product_amount;
        private String tel;
        private int vendor_type;

        public void setAddress(String address) {
            this.address = address;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setIs_central_stock(boolean is_central_stock) {
            this.is_central_stock = is_central_stock;
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

        public void setTel(String tel) {
            this.tel = tel;
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

        public boolean getIs_central_stock() {
            return is_central_stock;
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

        public String getTel() {
            return tel;
        }

        public int getVendor_type() {
            return vendor_type;
        }
    }
}
