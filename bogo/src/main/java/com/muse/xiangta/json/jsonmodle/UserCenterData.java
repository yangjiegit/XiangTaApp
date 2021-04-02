package com.muse.xiangta.json.jsonmodle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by weipeng on 2018/2/10.
 */

public class UserCenterData implements Serializable {

    /**
     * code : 1
     * msg :
     * data : {"sex":1,"user_nickname":"薛洋","avatar":"http://xta.zzmzrj.com/avatar/1617345382567_1617345380702.jpg","coin":9999374,"user_status":2,"level":3,"is_open_do_not_disturb":0,"is_open_auto_see_hi":0,"is_vip":0,"signature":"都得死","is_auth":1,"age":18,"noble":"","image_label":["呵呵哒","哇塞"],"remark_type_1":"","remark_type_2":"","remark_type_3":"","remark_type_4":"","remark_type_5":"","remark_type_6":"","juzhuqingkuang":"","huanqiantongju":"","jieshouyuehui":"","shifougoufang":"","shifougouche":"","car":"","user_auth_status":1,"split":0,"attention_fans":2,"attention_all":3,"friends_all":0,"private_friends_all":0,"pay_coin":[],"has_declaration":1,"declaration":"http://xta.zzmzrj.com/audio/1617265259472_306eb320-c037-49bf-8144-e2561b89e6d21617265253293.aac","overlapping_sound":"内心独白","education":"","love_status":"","occupation":"","is_settled":0,"declaration_length":6,"video_url":"","video_img":"","is_president":0,"remarks":[]}
     */

    private int code;
    private String msg;
    /**
     * sex : 1
     * user_nickname : 薛洋
     * avatar : http://xta.zzmzrj.com/avatar/1617345382567_1617345380702.jpg
     * coin : 9999374
     * user_status : 2
     * level : 3
     * is_open_do_not_disturb : 0
     * is_open_auto_see_hi : 0
     * is_vip : 0
     * signature : 都得死
     * is_auth : 1
     * age : 18
     * noble :
     * image_label : ["呵呵哒","哇塞"]
     * remark_type_1 :
     * remark_type_2 :
     * remark_type_3 :
     * remark_type_4 :
     * remark_type_5 :
     * remark_type_6 :
     * juzhuqingkuang :
     * huanqiantongju :
     * jieshouyuehui :
     * shifougoufang :
     * shifougouche :
     * car :
     * user_auth_status : 1
     * split : 0
     * attention_fans : 2
     * attention_all : 3
     * friends_all : 0
     * private_friends_all : 0
     * pay_coin : []
     * has_declaration : 1
     * declaration : http://xta.zzmzrj.com/audio/1617265259472_306eb320-c037-49bf-8144-e2561b89e6d21617265253293.aac
     * overlapping_sound : 内心独白
     * education :
     * love_status :
     * occupation :
     * is_settled : 0
     * declaration_length : 6
     * video_url :
     * video_img :
     * is_president : 0
     * remarks : []
     */

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

    public static class DataBean implements Serializable{
        private int sex;
        private String user_nickname;
        private String avatar;
        private int coin;
        private int user_status;
        private int level;
        private int is_open_do_not_disturb;
        private int is_open_auto_see_hi;
        private int is_vip;
        private String signature;
        private int is_auth;
        private int age;
        private String noble;
        private String remark_type_1;
        private String remark_type_2;
        private String remark_type_3;
        private String remark_type_4;
        private String remark_type_5;
        private String remark_type_6;
        private String juzhuqingkuang;
        private String huanqiantongju;
        private String jieshouyuehui;
        private String shifougoufang;
        private String shifougouche;
        private String car;
        private int user_auth_status;
        private int split;
        private int attention_fans;
        private int attention_all;
        private int friends_all;
        private int private_friends_all;
        private int has_declaration;
        private String declaration;
        private String overlapping_sound;
        private String education;
        private String love_status;
        private String occupation;
        private int is_settled;
        private int declaration_length;
        private String video_url;
        private String video_img;
        private int is_president;
        private List<String> image_label;
        private List<?> pay_coin;
        private List<?> remarks;

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

        public int getCoin() {
            return coin;
        }

        public void setCoin(int coin) {
            this.coin = coin;
        }

        public int getUser_status() {
            return user_status;
        }

        public void setUser_status(int user_status) {
            this.user_status = user_status;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getIs_open_do_not_disturb() {
            return is_open_do_not_disturb;
        }

        public void setIs_open_do_not_disturb(int is_open_do_not_disturb) {
            this.is_open_do_not_disturb = is_open_do_not_disturb;
        }

        public int getIs_open_auto_see_hi() {
            return is_open_auto_see_hi;
        }

        public void setIs_open_auto_see_hi(int is_open_auto_see_hi) {
            this.is_open_auto_see_hi = is_open_auto_see_hi;
        }

        public int getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(int is_vip) {
            this.is_vip = is_vip;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getIs_auth() {
            return is_auth;
        }

        public void setIs_auth(int is_auth) {
            this.is_auth = is_auth;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getNoble() {
            return noble;
        }

        public void setNoble(String noble) {
            this.noble = noble;
        }

        public String getRemark_type_1() {
            return remark_type_1;
        }

        public void setRemark_type_1(String remark_type_1) {
            this.remark_type_1 = remark_type_1;
        }

        public String getRemark_type_2() {
            return remark_type_2;
        }

        public void setRemark_type_2(String remark_type_2) {
            this.remark_type_2 = remark_type_2;
        }

        public String getRemark_type_3() {
            return remark_type_3;
        }

        public void setRemark_type_3(String remark_type_3) {
            this.remark_type_3 = remark_type_3;
        }

        public String getRemark_type_4() {
            return remark_type_4;
        }

        public void setRemark_type_4(String remark_type_4) {
            this.remark_type_4 = remark_type_4;
        }

        public String getRemark_type_5() {
            return remark_type_5;
        }

        public void setRemark_type_5(String remark_type_5) {
            this.remark_type_5 = remark_type_5;
        }

        public String getRemark_type_6() {
            return remark_type_6;
        }

        public void setRemark_type_6(String remark_type_6) {
            this.remark_type_6 = remark_type_6;
        }

        public String getJuzhuqingkuang() {
            return juzhuqingkuang;
        }

        public void setJuzhuqingkuang(String juzhuqingkuang) {
            this.juzhuqingkuang = juzhuqingkuang;
        }

        public String getHuanqiantongju() {
            return huanqiantongju;
        }

        public void setHuanqiantongju(String huanqiantongju) {
            this.huanqiantongju = huanqiantongju;
        }

        public String getJieshouyuehui() {
            return jieshouyuehui;
        }

        public void setJieshouyuehui(String jieshouyuehui) {
            this.jieshouyuehui = jieshouyuehui;
        }

        public String getShifougoufang() {
            return shifougoufang;
        }

        public void setShifougoufang(String shifougoufang) {
            this.shifougoufang = shifougoufang;
        }

        public String getShifougouche() {
            return shifougouche;
        }

        public void setShifougouche(String shifougouche) {
            this.shifougouche = shifougouche;
        }

        public String getCar() {
            return car;
        }

        public void setCar(String car) {
            this.car = car;
        }

        public int getUser_auth_status() {
            return user_auth_status;
        }

        public void setUser_auth_status(int user_auth_status) {
            this.user_auth_status = user_auth_status;
        }

        public int getSplit() {
            return split;
        }

        public void setSplit(int split) {
            this.split = split;
        }

        public int getAttention_fans() {
            return attention_fans;
        }

        public void setAttention_fans(int attention_fans) {
            this.attention_fans = attention_fans;
        }

        public int getAttention_all() {
            return attention_all;
        }

        public void setAttention_all(int attention_all) {
            this.attention_all = attention_all;
        }

        public int getFriends_all() {
            return friends_all;
        }

        public void setFriends_all(int friends_all) {
            this.friends_all = friends_all;
        }

        public int getPrivate_friends_all() {
            return private_friends_all;
        }

        public void setPrivate_friends_all(int private_friends_all) {
            this.private_friends_all = private_friends_all;
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

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getLove_status() {
            return love_status;
        }

        public void setLove_status(String love_status) {
            this.love_status = love_status;
        }

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        public int getIs_settled() {
            return is_settled;
        }

        public void setIs_settled(int is_settled) {
            this.is_settled = is_settled;
        }

        public int getDeclaration_length() {
            return declaration_length;
        }

        public void setDeclaration_length(int declaration_length) {
            this.declaration_length = declaration_length;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public String getVideo_img() {
            return video_img;
        }

        public void setVideo_img(String video_img) {
            this.video_img = video_img;
        }

        public int getIs_president() {
            return is_president;
        }

        public void setIs_president(int is_president) {
            this.is_president = is_president;
        }

        public List<String> getImage_label() {
            return image_label;
        }

        public void setImage_label(List<String> image_label) {
            this.image_label = image_label;
        }

        public List<?> getPay_coin() {
            return pay_coin;
        }

        public void setPay_coin(List<?> pay_coin) {
            this.pay_coin = pay_coin;
        }

        public List<?> getRemarks() {
            return remarks;
        }

        public void setRemarks(List<?> remarks) {
            this.remarks = remarks;
        }
    }
}
