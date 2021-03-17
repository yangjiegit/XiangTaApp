package com.muse.xiangta.modle;

import java.io.Serializable;
import java.util.List;

public class MessageBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : [{"id":102600,"avatar":"http://xta.zzmzrj.com/f0153202103080951388905.png","user_nickname":"帅哥1","sex":1,"level":6,"birthday":0,"height":"","city":"","address":"郑州市","has_declaration":0,"declaration":"","overlapping_sound":"","focus":1,"age":18,"nob":"http://xiangta.zzmzrj.com/mapi/public/upload/20210302/20210302183711D7fV-hrkkwei0552408.jpg","declaration_type":2}]
     */

    private int code;
    private String msg;
    /**
     * id : 102600
     * avatar : http://xta.zzmzrj.com/f0153202103080951388905.png
     * user_nickname : 帅哥1
     * sex : 1
     * level : 6
     * birthday : 0
     * height :
     * city :
     * address : 郑州市
     * has_declaration : 0
     * declaration :
     * overlapping_sound :
     * focus : 1
     * age : 18
     * nob : http://xiangta.zzmzrj.com/mapi/public/upload/20210302/20210302183711D7fV-hrkkwei0552408.jpg
     * declaration_type : 2
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private int id;
        private String avatar;
        private String user_nickname;
        private int sex;
        private int level;
        private int birthday;
        private String height;
        private String city;
        private String address;
        private int has_declaration;
        private String declaration;
        private String overlapping_sound;
        private int focus;
        private int age;
        private String nob;
        private int declaration_type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getBirthday() {
            return birthday;
        }

        public void setBirthday(int birthday) {
            this.birthday = birthday;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getHas_declaration() {
            return has_declaration;
        }

        public void setHas_declaration(int has_declaration) {
            this.has_declaration = has_declaration;
        }

        public String getDeclaration() {
            return declaration;
        }

        public void setDeclaration(String declaration) {
            this.declaration = declaration;
        }

        public String getOverlapping_sound() {
            return overlapping_sound;
        }

        public void setOverlapping_sound(String overlapping_sound) {
            this.overlapping_sound = overlapping_sound;
        }

        public int getFocus() {
            return focus;
        }

        public void setFocus(int focus) {
            this.focus = focus;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getNob() {
            return nob;
        }

        public void setNob(String nob) {
            this.nob = nob;
        }

        public int getDeclaration_type() {
            return declaration_type;
        }

        public void setDeclaration_type(int declaration_type) {
            this.declaration_type = declaration_type;
        }
    }
}
