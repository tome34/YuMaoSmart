package com.yumao.yumaosmart.mode;

/**
 * Created by kk on 2017/4/14.
 */

public class MyAddressMode {


    /**
     * id : 2796
     * country : {"id":21,"name":"中国"}
     * state_province : {"id":198,"name":"北京"}
     * city : {"id":1206,"name":"北京"}
     * county_district : {"id":7251,"name":"阿荣旗"}
     * first_name : 宝翠林
     * phone_number : 15919439757
     * created_on_utc : 2017-04-18T02:30:21+0000
     * is_default_address : false
     */

    private int id;
    private CountryBean country;
    private StateProvinceBean state_province;
    private CityBean city;
    private CountyDistrictBean county_district;
    private String first_name;
    private String last_name;
    private String address1;

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    private String phone_number;
    private String created_on_utc;
    private boolean is_default_address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CountryBean getCountry() {
        return country;
    }

    public void setCountry(CountryBean country) {
        this.country = country;
    }

    public StateProvinceBean getState_province() {
        return state_province;
    }

    public void setState_province(StateProvinceBean state_province) {
        this.state_province = state_province;
    }

    public CityBean getCity() {
        return city;
    }

    public void setCity(CityBean city) {
        this.city = city;
    }

    public CountyDistrictBean getCounty_district() {
        return county_district;
    }

    public void setCounty_district(CountyDistrictBean county_district) {
        this.county_district = county_district;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCreated_on_utc() {
        return created_on_utc;
    }

    public void setCreated_on_utc(String created_on_utc) {
        this.created_on_utc = created_on_utc;
    }

    public boolean isIs_default_address() {
        return is_default_address;
    }

    public void setIs_default_address(boolean is_default_address) {
        this.is_default_address = is_default_address;
    }

    public static class CountryBean {
        /**
         * id : 21
         * name : 中国
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

    public static class StateProvinceBean {
        /**
         * id : 198
         * name : 北京
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

    public static class CityBean {
        /**
         * id : 1206
         * name : 北京
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

    public static class CountyDistrictBean {
        /**
         * id : 7251
         * name : 阿荣旗
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
