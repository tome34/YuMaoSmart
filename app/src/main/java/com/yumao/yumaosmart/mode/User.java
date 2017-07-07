package com.yumao.yumaosmart.mode;

import java.util.List;

/**
 * Created by kk on 2017/3/27.
 */

public class User {

    /**
     * id : 1447368
     * username : 17817234597
     * phone : 17817234597
     * email : null
     * created_on_utc : 2017-07-01T00:00:33+00:00
     * nick_name : tome
     * state : null
     * vendor : {"id":1,"name":"玉猫平台","address":"深圳市罗湖区田贝四路水贝万山A座4006","tel":null,"vendor_type":3,"is_central_stock":true,"product_amount":7907,"logo":"http://img.yumaozhubao.com/images/00119667.png"}
     * avatar_url : http://wx.qlogo.cn/mmopen/Lgv9w33SxlgWsAibu9ZLMXXJKyeqOdQzxegYnMZjx8hLfSqM6It2YzVgbhezBDWOWgn5xiaHx2DVwse8o9jEA5v6Gb9RY8bclu/0
     * roles : ["Registered","RegionalAgentOwner"]
     * cumulative_sales : 313
     * cumulative_assets : 0
     * gender : M
     * date_of_birth : 2017-07-01
     * token : b775de952e9c4320b0fb44308d34f5e165c029a1
     */

    private int id;
    private String username;
    private String phone;
    private Object email;
    private String created_on_utc;
    private String nick_name;
    private Object state;
    private VendorBean vendor;
    private String avatar_url;
    private int cumulative_sales;
    private int cumulative_assets;
    private String gender;
    private String date_of_birth;
    private String token;
    private List<String> roles;

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public void setCreated_on_utc(String created_on_utc) {
        this.created_on_utc = created_on_utc;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public void setVendor(VendorBean vendor) {
        this.vendor = vendor;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public void setCumulative_sales(int cumulative_sales) {
        this.cumulative_sales = cumulative_sales;
    }

    public void setCumulative_assets(int cumulative_assets) {
        this.cumulative_assets = cumulative_assets;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public Object getEmail() {
        return email;
    }

    public String getCreated_on_utc() {
        return created_on_utc;
    }

    public String getNick_name() {
        return nick_name;
    }

    public Object getState() {
        return state;
    }

    public VendorBean getVendor() {
        return vendor;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public int getCumulative_sales() {
        return cumulative_sales;
    }

    public int getCumulative_assets() {
        return cumulative_assets;
    }

    public String getGender() {
        return gender;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public String getToken() {
        return token;
    }

    public List<String> getRoles() {
        return roles;
    }

    public static class VendorBean {
        /**
         * id : 1
         * name : 玉猫平台
         * address : 深圳市罗湖区田贝四路水贝万山A座4006
         * tel : null
         * vendor_type : 3
         * is_central_stock : true
         * product_amount : 7907
         * logo : http://img.yumaozhubao.com/images/00119667.png
         */

        private int id;
        private String name;
        private String address;
        private Object tel;
        private int vendor_type;
        private boolean is_central_stock;
        private int product_amount;
        private String logo;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setTel(Object tel) {
            this.tel = tel;
        }

        public void setVendor_type(int vendor_type) {
            this.vendor_type = vendor_type;
        }

        public void setIs_central_stock(boolean is_central_stock) {
            this.is_central_stock = is_central_stock;
        }

        public void setProduct_amount(int product_amount) {
            this.product_amount = product_amount;
        }

        public void setLogo(String logo) {
            this.logo = logo;
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

        public Object getTel() {
            return tel;
        }

        public int getVendor_type() {
            return vendor_type;
        }

        public boolean getIs_central_stock() {
            return is_central_stock;
        }

        public int getProduct_amount() {
            return product_amount;
        }

        public String getLogo() {
            return logo;
        }
    }
}
