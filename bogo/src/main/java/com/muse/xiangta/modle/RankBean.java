package com.muse.xiangta.modle;

import java.io.Serializable;
import java.util.List;

public class RankBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : {"top_3":[{"uid":102712,"sex":2,"avatar":"http://xta.zzmzrj.com/47488202104091043171109.png","user_nickname":"浮沉","glamour":503,"glamour_level_id":5,"wealth":375,"wealth_level_id":5,"age":23,"rank":1},{"uid":102714,"sex":1,"avatar":"http://xta.zzmzrj.com/idImage/1617851407844.png","user_nickname":"挖藕","glamour":2,"glamour_level_id":1,"wealth":32,"wealth_level_id":5,"age":18,"rank":2},{"uid":102728,"sex":2,"avatar":"http://xta.zzmzrj.com/e210b202104081654297235.png","user_nickname":"晨晨","glamour":0,"glamour_level_id":1,"wealth":23,"wealth_level_id":1,"age":18,"rank":3}],"others":[{"uid":102727,"sex":1,"avatar":"http://xta.zzmzrj.com/avatar/1617958475701_1617958473510.jpg","user_nickname":"江晚吟","glamour":2,"glamour_level_id":1,"wealth":18,"wealth_level_id":1,"age":18,"rank":4},{"uid":102730,"sex":1,"avatar":"http://xiangta.zzmzrj.com/public/image/headicon.png","user_nickname":"新注册用户-91349","glamour":0,"glamour_level_id":1,"wealth":0,"wealth_level_id":1,"age":18,"rank":5}]}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * uid : 102712
         * sex : 2
         * avatar : http://xta.zzmzrj.com/47488202104091043171109.png
         * user_nickname : 浮沉
         * glamour : 503
         * glamour_level_id : 5
         * wealth : 375
         * wealth_level_id : 5
         * age : 23
         * rank : 1
         */

        private List<Top3Bean> top_3;
        /**
         * uid : 102727
         * sex : 1
         * avatar : http://xta.zzmzrj.com/avatar/1617958475701_1617958473510.jpg
         * user_nickname : 江晚吟
         * glamour : 2
         * glamour_level_id : 1
         * wealth : 18
         * wealth_level_id : 1
         * age : 18
         * rank : 4
         */

        private List<OthersBean> others;

        public List<Top3Bean> getTop_3() {
            return top_3;
        }

        public void setTop_3(List<Top3Bean> top_3) {
            this.top_3 = top_3;
        }

        public List<OthersBean> getOthers() {
            return others;
        }

        public void setOthers(List<OthersBean> others) {
            this.others = others;
        }

        public static class Top3Bean implements Serializable {
            private int uid;
            private int sex;
            private String avatar;
            private String user_nickname;
            private int glamour;
            private int glamour_level_id;
            private int wealth;
            private int wealth_level_id;
            private int age;
            private int rank;

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getUser_nickname() {
                return user_nickname;
            }

            public void setUser_nickname(String user_nickname) {
                this.user_nickname = user_nickname;
            }

            public int getGlamour() {
                return glamour;
            }

            public void setGlamour(int glamour) {
                this.glamour = glamour;
            }

            public int getGlamour_level_id() {
                return glamour_level_id;
            }

            public void setGlamour_level_id(int glamour_level_id) {
                this.glamour_level_id = glamour_level_id;
            }

            public int getWealth() {
                return wealth;
            }

            public void setWealth(int wealth) {
                this.wealth = wealth;
            }

            public int getWealth_level_id() {
                return wealth_level_id;
            }

            public void setWealth_level_id(int wealth_level_id) {
                this.wealth_level_id = wealth_level_id;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public int getRank() {
                return rank;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }
        }

        public static class OthersBean implements Serializable {
            private int uid;
            private int sex;
            private String avatar;
            private String user_nickname;
            private int glamour;
            private int glamour_level_id;
            private int wealth;
            private int wealth_level_id;
            private int age;
            private int rank;

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getUser_nickname() {
                return user_nickname;
            }

            public void setUser_nickname(String user_nickname) {
                this.user_nickname = user_nickname;
            }

            public int getGlamour() {
                return glamour;
            }

            public void setGlamour(int glamour) {
                this.glamour = glamour;
            }

            public int getGlamour_level_id() {
                return glamour_level_id;
            }

            public void setGlamour_level_id(int glamour_level_id) {
                this.glamour_level_id = glamour_level_id;
            }

            public int getWealth() {
                return wealth;
            }

            public void setWealth(int wealth) {
                this.wealth = wealth;
            }

            public int getWealth_level_id() {
                return wealth_level_id;
            }

            public void setWealth_level_id(int wealth_level_id) {
                this.wealth_level_id = wealth_level_id;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public int getRank() {
                return rank;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }
        }
    }
}
