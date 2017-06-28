package com.yumao.yumaosmart.mode;

import java.util.List;

/**
 * Created by kk on 2017/3/30.
 */

public    class CategoriesContentMode {


    /**
     * children : [{"children":[{"id":658,"name":"吊坠"},{"id":659,"name":"戒指"},{"id":660,"name":"耳饰"},{"id":661,"name":"手链/手镯"},{"id":848,"name":"套链"}],"picture":"http://img.yumaozhubao.com/images/00087352.png","id":657,"name":"白玉"},{"children":[{"id":663,"name":"吊坠"},{"id":664,"name":"戒指"},{"id":666,"name":"耳饰"},{"id":667,"name":"手链/手镯"},{"id":849,"name":"套链"}],"picture":"http://img.yumaozhubao.com/images/00087359.png","id":662,"name":"碧玉"},{"children":[{"id":672,"name":"吊坠"},{"id":674,"name":"戒指"},{"id":676,"name":"耳饰"},{"id":678,"name":"手链/手镯"},{"id":850,"name":"套链"}],"picture":"http://img.yumaozhubao.com/images/00087365.png","id":669,"name":"墨玉"},{"children":[{"id":682,"name":"吊坠"},{"id":683,"name":"戒指"},{"id":685,"name":"耳饰"},{"id":688,"name":"手链/手镯"},{"id":851,"name":"套链"}],"picture":"http://img.yumaozhubao.com/images/00087366.png","id":681,"name":"金镶玉"}]
     * id : 21
     * name : 和田玉
     *
     *
     *
     *
     */

    private int id;
    private String name;
    private List<ChildrenBeanX> children;

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

    public List<ChildrenBeanX> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBeanX> children) {
        this.children = children;
    }

    public static class ChildrenBeanX {
        /**
         * children : [{"id":658,"name":"吊坠"},{"id":659,"name":"戒指"},{"id":660,"name":"耳饰"},{"id":661,"name":"手链/手镯"},{"id":848,"name":"套链"}]
         * picture : http://img.yumaozhubao.com/images/00087352.png
         * id : 657
         * name : 白玉
         *
         *
         */

        private String picture;
        private int id;
        private String name;
        private List<ChildrenBean> children;

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
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

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
            /**
             * id : 658
             * name : 吊坠
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
