package com.muse.xiangta.modle;

import java.io.Serializable;
import java.util.List;

public class RecommendUserBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : [{"id":102600,"is_auth":1,"sex":1,"user_nickname":"宋拾壹","avatar":"http://xta.zzmzrj.com/idImage/1616120589871.png","level":8,"signature":"","is_online":1,"address":"郑州市","vip_end_time":0,"birthday":0,"height":"0","city":"郑州市","has_declaration":1,"declaration_length":0,"fans":"361","call_length":null,"focus":0,"age":18,"nob":"http://xta.zzmzrj.com/admin/20210323/2f24322830b51713b203ddaff825dfa3.png","declaration_type":1,"charging_coin":"15","vip_price":7.5,"is_vip":0,"sign":""},{"id":102595,"is_auth":1,"sex":2,"user_nickname":"晨晨！","avatar":"http://xta.zzmzrj.com/6e3ea202103081120398748.png","level":8,"signature":"嗯哼大王叫我来巡山～","is_online":1,"address":"郑州市","vip_end_time":0,"birthday":0,"height":"178","city":"郑州市","has_declaration":1,"declaration_length":12,"fans":"105","call_length":null,"focus":0,"age":18,"nob":"http://xta.zzmzrj.com/admin/20210323/e50f3988f15e5d1a0ae3f427a4660f6c.png","declaration_type":1,"charging_coin":"15","vip_price":11,"is_vip":0,"sign":"嗯哼大王叫我来巡山～"},{"id":102597,"is_auth":1,"sex":2,"user_nickname":"Joker","avatar":"http://xta.zzmzrj.com/avatar/1616397194924_1616397191882.png","level":1,"signature":"还未设置个性签名","is_online":1,"address":"","vip_end_time":0,"birthday":0,"height":"0","city":"","has_declaration":1,"declaration_length":5,"fans":"11","call_length":null,"focus":0,"age":18,"nob":"","declaration_type":1,"charging_coin":"15","vip_price":7.5,"is_vip":0,"sign":"还未设置个性签名"},{"id":102612,"is_auth":1,"sex":1,"user_nickname":"我的菜","avatar":"http://xta.zzmzrj.com/8c06d202103131511332376.png","level":20,"signature":"","is_online":0,"address":"广州市","vip_end_time":0,"birthday":0,"height":"0","city":"广州市","has_declaration":0,"declaration_length":0,"fans":null,"call_length":null,"focus":0,"age":18,"nob":"","declaration_type":2,"charging_coin":"15","vip_price":7.5,"is_vip":0,"sign":""},{"id":102630,"is_auth":1,"sex":2,"user_nickname":"呵呵哒","avatar":"http://xta.zzmzrj.com/b1232202103161014012061.png","level":6,"signature":"111","is_online":0,"address":"郑州市","vip_end_time":0,"birthday":0,"height":"132","city":"郑州市","has_declaration":0,"declaration_length":0,"fans":null,"call_length":null,"focus":0,"age":18,"nob":"http://xta.zzmzrj.com/admin/20210323/4705d7cb8e9fc580231e35047439cee9.png","declaration_type":2,"charging_coin":"15","vip_price":7.5,"is_vip":0,"sign":"111"},{"id":102631,"is_auth":1,"sex":1,"user_nickname":"夜色殘","avatar":"http://xta.zzmzrj.com/b6eca20210316101957493.jpg","level":1,"signature":"","is_online":0,"address":"","vip_end_time":0,"birthday":0,"height":"","city":"","has_declaration":0,"declaration_length":0,"fans":null,"call_length":null,"focus":0,"age":18,"nob":"http://xta.zzmzrj.com/admin/20210323/4705d7cb8e9fc580231e35047439cee9.png","declaration_type":2,"charging_coin":"15","vip_price":7.5,"is_vip":0,"sign":""},{"id":102605,"is_auth":1,"sex":2,"user_nickname":"哈哈好","avatar":"http://xta.zzmzrj.com/1f6d3202103101748513983.jpg","level":1,"signature":"","is_online":0,"address":"","vip_end_time":0,"birthday":0,"height":"","city":"","has_declaration":1,"declaration_length":11,"fans":null,"call_length":null,"focus":0,"age":18,"nob":"","declaration_type":1,"charging_coin":"15","vip_price":7.5,"is_vip":0,"sign":""},{"id":102624,"is_auth":1,"sex":1,"user_nickname":"Ww","avatar":"http://xta.zzmzrj.com/e2354202103131620272534.jpg","level":1,"signature":"还未设置个性签名","is_online":0,"address":"","vip_end_time":0,"birthday":0,"height":"0","city":"","has_declaration":0,"declaration_length":0,"fans":null,"call_length":null,"focus":0,"age":18,"nob":"","declaration_type":2,"charging_coin":"15","vip_price":7.5,"is_vip":0,"sign":"还未设置个性签名"},{"id":102623,"is_auth":1,"sex":2,"user_nickname":"剑心通明","avatar":"http://xta.zzmzrj.com/8f5f5202103131607088813.jpeg","level":1,"signature":"","is_online":0,"address":"","vip_end_time":0,"birthday":0,"height":"","city":"","has_declaration":0,"declaration_length":0,"fans":null,"call_length":null,"focus":0,"age":18,"nob":"","declaration_type":2,"charging_coin":"15","vip_price":7.5,"is_vip":0,"sign":""},{"id":102626,"is_auth":1,"sex":1,"user_nickname":"廖芮的老大","avatar":"http://xta.zzmzrj.com/6f7df202103131733311128.png","level":1,"signature":"","is_online":0,"address":"广州市","vip_end_time":1616596200,"birthday":0,"height":"","city":"广州市","has_declaration":0,"declaration_length":0,"fans":null,"call_length":null,"focus":0,"age":18,"nob":"","declaration_type":2,"charging_coin":"15","vip_price":7.5,"is_vip":1,"sign":""}]
     * online_emcee_count : 6
     * online_emcee : [{"id":102595,"avatar":"http://xta.zzmzrj.com/6e3ea202103081120398748.png"},{"id":102597,"avatar":"http://xta.zzmzrj.com/avatar/1616397194924_1616397191882.png"},{"id":102598,"avatar":"http://xta.zzmzrj.com/idImage/1615016457439.png"}]
     */

    private int code;
    private String msg;
    private int online_emcee_count;
    /**
     * id : 102600
     * is_auth : 1
     * sex : 1
     * user_nickname : 宋拾壹
     * avatar : http://xta.zzmzrj.com/idImage/1616120589871.png
     * level : 8
     * signature :
     * is_online : 1
     * address : 郑州市
     * vip_end_time : 0
     * birthday : 0
     * height : 0
     * city : 郑州市
     * has_declaration : 1
     * declaration_length : 0
     * fans : 361
     * call_length : null
     * focus : 0
     * age : 18
     * nob : http://xta.zzmzrj.com/admin/20210323/2f24322830b51713b203ddaff825dfa3.png
     * declaration_type : 1
     * charging_coin : 15
     * vip_price : 7.5
     * is_vip : 0
     * sign :
     */

    private List<DataBean> data;
    /**
     * id : 102595
     * avatar : http://xta.zzmzrj.com/6e3ea202103081120398748.png
     */

    private List<OnlineEmceeBean> online_emcee;

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

    public int getOnline_emcee_count() {
        return online_emcee_count;
    }

    public void setOnline_emcee_count(int online_emcee_count) {
        this.online_emcee_count = online_emcee_count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<OnlineEmceeBean> getOnline_emcee() {
        return online_emcee;
    }

    public void setOnline_emcee(List<OnlineEmceeBean> online_emcee) {
        this.online_emcee = online_emcee;
    }

    public static class DataBean implements Serializable{
        private int id;
        private int is_auth;
        private int sex;
        private String user_nickname;
        private String avatar;
        private int level;
        private String signature;
        private int is_online;
        private String address;
        private int vip_end_time;
        private int birthday;
        private String height;
        private String city;
        private int has_declaration;
        private int declaration_length;
        private String fans;
        private Object call_length;
        private int focus;
        private int age;
        private String nob;
        private int declaration_type;
        private String charging_coin;
        private double vip_price;
        private int is_vip;
        private String sign;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIs_auth() {
            return is_auth;
        }

        public void setIs_auth(int is_auth) {
            this.is_auth = is_auth;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getUser_nickname() {
            return user_nickname;
        }

        public void setUser_nickname(String user_nickname) {
            this.user_nickname = user_nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getIs_online() {
            return is_online;
        }

        public void setIs_online(int is_online) {
            this.is_online = is_online;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getVip_end_time() {
            return vip_end_time;
        }

        public void setVip_end_time(int vip_end_time) {
            this.vip_end_time = vip_end_time;
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

        public int getHas_declaration() {
            return has_declaration;
        }

        public void setHas_declaration(int has_declaration) {
            this.has_declaration = has_declaration;
        }

        public int getDeclaration_length() {
            return declaration_length;
        }

        public void setDeclaration_length(int declaration_length) {
            this.declaration_length = declaration_length;
        }

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public Object getCall_length() {
            return call_length;
        }

        public void setCall_length(Object call_length) {
            this.call_length = call_length;
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

        public String getCharging_coin() {
            return charging_coin;
        }

        public void setCharging_coin(String charging_coin) {
            this.charging_coin = charging_coin;
        }

        public double getVip_price() {
            return vip_price;
        }

        public void setVip_price(double vip_price) {
            this.vip_price = vip_price;
        }

        public int getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(int is_vip) {
            this.is_vip = is_vip;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }

    public static class OnlineEmceeBean implements Serializable{
        private int id;
        private String avatar;

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
    }
}
