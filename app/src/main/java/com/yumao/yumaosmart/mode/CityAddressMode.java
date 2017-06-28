package com.yumao.yumaosmart.mode;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.List;

/**
 * Created by kk on 2017/4/13.
 */

public class CityAddressMode implements IPickerViewData {


    /**
     * name : 北京
     * provinceid : 198
     * city : [{"area":[{"name":"宣武区","countyid":"10163"},{"name":"延庆县","countyid":"10184"},{"name":"昌平区","countyid":"7420"},{"name":"朝阳区","countyid":"7452"},{"name":"崇文区","countyid":"7501"},{"name":"大兴区","countyid":"7562"},{"name":"东城区","countyid":"7641"},{"name":"房山区","countyid":"7732"},{"name":"丰台区","countyid":"7749"},{"name":"海淀区","countyid":"7945"},{"name":"怀柔区","countyid":"8092"},{"name":"门头沟区","countyid":"8729"},{"name":"密云县","countyid":"8752"},{"name":"平谷区","countyid":"8911"},{"name":"其他区","countyid":"8976"},{"name":"石景山区","countyid":"9559"},{"name":"顺义区","countyid":"9616"},{"name":"通州区","countyid":"9760"},{"name":"西城区","countyid":"9944"}],"name":"北京","cityid":"1206"}]
     */

    private String name;
    private String provinceid;
    private List<CityBean> city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }

    public List<CityBean> getCity() {
        return city;
    }

    public void setCity(List<CityBean> city) {
        this.city = city;
    }

    @Override
    public String getPickerViewText() {
        return this.name;
    }


    public static class CityBean  {
        /**
         * area : [{"name":"宣武区","countyid":"10163"},{"name":"延庆县","countyid":"10184"},{"name":"昌平区","countyid":"7420"},{"name":"朝阳区","countyid":"7452"},{"name":"崇文区","countyid":"7501"},{"name":"大兴区","countyid":"7562"},{"name":"东城区","countyid":"7641"},{"name":"房山区","countyid":"7732"},{"name":"丰台区","countyid":"7749"},{"name":"海淀区","countyid":"7945"},{"name":"怀柔区","countyid":"8092"},{"name":"门头沟区","countyid":"8729"},{"name":"密云县","countyid":"8752"},{"name":"平谷区","countyid":"8911"},{"name":"其他区","countyid":"8976"},{"name":"石景山区","countyid":"9559"},{"name":"顺义区","countyid":"9616"},{"name":"通州区","countyid":"9760"},{"name":"西城区","countyid":"9944"}]
         * name : 北京
         * cityid : 1206
         */

        private String name;
        private String cityid;
        private List<AreaBean> area;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public List<AreaBean> getArea() {
            return area;
        }

        public void setArea(List<AreaBean> area) {
            this.area = area;
        }



        public static class AreaBean {
            /**
             * name : 宣武区
             * countyid : 10163
             */

            private String name;
            private String countyid;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCountyid() {
                return countyid;
            }

            public void setCountyid(String countyid) {
                this.countyid = countyid;
            }
        }
    }
}
