package com.yumao.yumaosmart.mode;

import java.util.List;

/**
 * Created by kk on 2017/7/12.
 * 首页分类
 */

public class FristClassifyMode {

    /**
     * children : [{"children":[{"contains":true,"id":677,"name":"观音"},{"contains":false,"id":696,"name":"佛公"},{"contains":true,"id":699,"name":"福豆"},{"contains":false,"id":700,"name":"葫芦"},{"contains":false,"id":703,"name":"如意"},{"contains":false,"id":706,"name":"树叶"},{"contains":false,"id":707,"name":"神兽"},{"contains":false,"id":709,"name":"生肖"},{"contains":true,"id":721,"name":"平安扣"},{"contains":true,"id":722,"name":"其它"}],"contains":true,"id":671,"name":"挂件","picture":"http://img.yumaozhubao.com/images/00124054.png"},{"children":[{"contains":false,"id":725,"name":"福镯"},{"contains":true,"id":730,"name":"平安镯"},{"contains":false,"id":727,"name":"贵妃镯"}],"contains":true,"id":723,"name":"手镯","picture":"http://img.yumaozhubao.com/images/00124055.png"},{"children":[{"contains":false,"id":738,"name":"把玩"},{"contains":false,"id":740,"name":"摆件"},{"contains":false,"id":744,"name":"车挂/包挂"},{"contains":false,"id":746,"name":"印章"},{"contains":false,"id":747,"name":"其它"}],"contains":false,"id":736,"name":"手玩/把件","picture":"http://img.yumaozhubao.com/images/00124056.png"},{"children":[{"contains":true,"id":750,"name":"正圆"},{"contains":false,"id":752,"name":"椭圆"},{"contains":false,"id":754,"name":"正方"},{"contains":false,"id":755,"name":"长方"},{"contains":false,"id":757,"name":"马眼"},{"contains":true,"id":759,"name":"其它"}],"contains":true,"id":749,"name":"戒面","picture":"http://img.yumaozhubao.com/images/00124057.png"},{"children":[{"contains":false,"id":763,"name":"戒指"},{"contains":true,"id":765,"name":"吊坠"},{"contains":false,"id":767,"name":"耳饰"},{"contains":false,"id":769,"name":"手链"},{"contains":false,"id":771,"name":"其它"}],"contains":true,"id":761,"name":"镶嵌饰品","picture":"http://img.yumaozhubao.com/images/00124058.png"},{"children":[],"contains":false,"id":773,"name":"其它","picture":"http://img.yumaozhubao.com/images/00124059.png"},{"children":[],"contains":false,"id":865,"name":"摆件","picture":"http://img.yumaozhubao.com/images/00124060.png"}]
     * contains : true
     * id : 3
     * name : 翡翠
     */

    private boolean contains;
    private int id;
    private String name;
    private List<ChildrenBean> children;

    public void setContains(boolean contains) {
        this.contains = contains;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public boolean getContains() {
        return contains;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public static class ChildrenBean {
        /**
         * children : [{"contains":true,"id":677,"name":"观音"},{"contains":false,"id":696,"name":"佛公"},{"contains":true,"id":699,"name":"福豆"},{"contains":false,"id":700,"name":"葫芦"},{"contains":false,"id":703,"name":"如意"},{"contains":false,"id":706,"name":"树叶"},{"contains":false,"id":707,"name":"神兽"},{"contains":false,"id":709,"name":"生肖"},{"contains":true,"id":721,"name":"平安扣"},{"contains":true,"id":722,"name":"其它"}]
         * contains : true
         * id : 671
         * name : 挂件
         * picture : http://img.yumaozhubao.com/images/00124054.png
         */

        private boolean contains;
        private int id;
        private String name;
        private String picture;
        private List<ChildrenBean> children;

        public void setContains(boolean contains) {
            this.contains = contains;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public boolean getContains() {
            return contains;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPicture() {
            return picture;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public static class ChildrenBean2 {
            /**
             * contains : true
             * id : 677
             * name : 观音
             */

            private boolean contains;
            private int id;
            private String name;

            public void setContains(boolean contains) {
                this.contains = contains;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean getContains() {
                return contains;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }
        }
    }
}
