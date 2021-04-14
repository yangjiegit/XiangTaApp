package com.muse.xiangta.json.jsonmodle;

import java.io.Serializable;
import java.util.List;

/**
 * 目标用户详细信息
 * Created by jiahengfei on 2018/1/22 0022.
 */

public class TargetUserData2 implements Serializable {

    /**
     * code : 1
     * msg :
     * data : {"id":102712,"sex":2,"user_nickname":"浮沉","avatar":"http://xta.zzmzrj.com/47488202104091043171109.png","address":"**","user_status":0,"level":2,"signature":"","wealth_level":5,"glamour_level":5,"image_label":["","水瓶座","游泳","瑜伽","欧美","摇滚","烤串","麻辣香锅","交付","刘慈欣","韩寒","丽江","搭理","住自己买的房子","接受约会","已购房","已购车（豪华型）"],"noble":"http://xta.zzmzrj.com/admin/20210323/a36545159119b105e121f455ed6e4ded.png","remark_type_1":"游泳-瑜伽","remark_type_2":"欧美-摇滚","remark_type_3":"烤串-麻辣香锅","remark_type_4":"交付","remark_type_5":"刘慈欣-韩寒","remark_type_6":"丽江-搭理","juzhuqingkuang":"住自己买的房子","huanqiantongju":"接受","jieshouyuehui":"是","shifougoufang":"已购房","shifougouche":"已购车（豪华型）","car":"http://xta.zzmzrj.com/admin/20210329/1000c2d961cdf9e25efc8c280450926a.gif","conceal_location":1,"conceal_visit":0,"conceal_enter":1,"conceal_protector":1,"conceal_wealth_charm_thumbs_up":0,"conceal_gifts":1,"conceal_my_protector":0,"information":["年龄：23","所在地：**","职业：传媒/艺术","身高：178","学历：博士","情感状态：保密"],"remarks":[],"attention":0,"is_black":0,"gift_count":"**","gift":[{"img":"http://xta.zzmzrj.com/admin/20210414/1513c6344edb0216d05bd41e2c9131d5.png","gift_count":"**"}],"pictures_count":0,"pictures":[],"is_online":1,"video_deduction":"15","voice_deduction":"10","guardian_user_list":[{"id":30,"uid":102712,"hostid":102712,"starttime":1618380136,"endtime":1624269601,"guardian_id":4,"guardian_user_log_id":50,"addtime":1618380136,"user_nickname":"**","avatar":"http://xta.zzmzrj.com/admin/20210414/1513c6344edb0216d05bd41e2c9131d5.png","gift_count":null,"conceal_protector":1},{"id":9,"uid":102728,"hostid":102712,"starttime":1615197601,"endtime":1618480801,"guardian_id":6,"guardian_user_log_id":13,"addtime":1615197601,"user_nickname":"晨晨","avatar":"http://xta.zzmzrj.com/e210b202104081654297235.png","gift_count":null,"conceal_protector":0}],"attention_fans":7,"attention_all":8,"call":"0","evaluation":0,"img":[],"give_like":0,"is_visible_bottom_btn":0,"has_declaration":0,"declaration":"","overlapping_sound":"","is_settled":0,"is_auth":0,"declaration_length":0,"video_url":"","video_img":""}
     */

    private int code;
    private String msg;
    /**
     * id : 102712
     * sex : 2
     * user_nickname : 浮沉
     * avatar : http://xta.zzmzrj.com/47488202104091043171109.png
     * address : **
     * user_status : 0
     * level : 2
     * signature :
     * wealth_level : 5
     * glamour_level : 5
     * image_label : ["","水瓶座","游泳","瑜伽","欧美","摇滚","烤串","麻辣香锅","交付","刘慈欣","韩寒","丽江","搭理","住自己买的房子","接受约会","已购房","已购车（豪华型）"]
     * noble : http://xta.zzmzrj.com/admin/20210323/a36545159119b105e121f455ed6e4ded.png
     * remark_type_1 : 游泳-瑜伽
     * remark_type_2 : 欧美-摇滚
     * remark_type_3 : 烤串-麻辣香锅
     * remark_type_4 : 交付
     * remark_type_5 : 刘慈欣-韩寒
     * remark_type_6 : 丽江-搭理
     * juzhuqingkuang : 住自己买的房子
     * huanqiantongju : 接受
     * jieshouyuehui : 是
     * shifougoufang : 已购房
     * shifougouche : 已购车（豪华型）
     * car : http://xta.zzmzrj.com/admin/20210329/1000c2d961cdf9e25efc8c280450926a.gif
     * conceal_location : 1
     * conceal_visit : 0
     * conceal_enter : 1
     * conceal_protector : 1
     * conceal_wealth_charm_thumbs_up : 0
     * conceal_gifts : 1
     * conceal_my_protector : 0
     * information : ["年龄：23","所在地：**","职业：传媒/艺术","身高：178","学历：博士","情感状态：保密"]
     * remarks : []
     * attention : 0
     * is_black : 0
     * gift_count : **
     * gift : [{"img":"http://xta.zzmzrj.com/admin/20210414/1513c6344edb0216d05bd41e2c9131d5.png","gift_count":"**"}]
     * pictures_count : 0
     * pictures : []
     * is_online : 1
     * video_deduction : 15
     * voice_deduction : 10
     * guardian_user_list : [{"id":30,"uid":102712,"hostid":102712,"starttime":1618380136,"endtime":1624269601,"guardian_id":4,"guardian_user_log_id":50,"addtime":1618380136,"user_nickname":"**","avatar":"http://xta.zzmzrj.com/admin/20210414/1513c6344edb0216d05bd41e2c9131d5.png","gift_count":null,"conceal_protector":1},{"id":9,"uid":102728,"hostid":102712,"starttime":1615197601,"endtime":1618480801,"guardian_id":6,"guardian_user_log_id":13,"addtime":1615197601,"user_nickname":"晨晨","avatar":"http://xta.zzmzrj.com/e210b202104081654297235.png","gift_count":null,"conceal_protector":0}]
     * attention_fans : 7
     * attention_all : 8
     * call : 0
     * evaluation : 0
     * img : []
     * give_like : 0
     * is_visible_bottom_btn : 0
     * has_declaration : 0
     * declaration :
     * overlapping_sound :
     * is_settled : 0
     * is_auth : 0
     * declaration_length : 0
     * video_url :
     * video_img :
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

    public static class DataBean implements Serializable {
        private String id;
        private String sex;
        private String user_nickname;
        private String avatar;
        private String address;
        private String user_status;
        private String level;
        private String signature;
        private String wealth_level;
        private String glamour_level;
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
        private String conceal_location;
        private String conceal_visit;
        private String conceal_enter;
        private String conceal_protector;
        private String conceal_wealth_charm_thumbs_up;
        private String conceal_gifts;
        private String conceal_my_protector;
        private String attention;
        private String is_black;
        private String gift_count;
        private String pictures_count;
        private String is_online;
        private String video_deduction;
        private String voice_deduction;
        private String attention_fans;
        private String attention_all;
        private String call;
        private String evaluation;
        private String give_like;
        private String is_visible_bottom_btn;
        private String has_declaration;
        private String declaration;
        private String overlapping_sound;
        private String is_settled;
        private String is_auth;
        private String declaration_length;
        private String video_url;
        private String video_img;
        private List<String> image_label;
        private List<String> information;
        private List<?> remarks;
        /**
         * img : http://xta.zzmzrj.com/admin/20210414/1513c6344edb0216d05bd41e2c9131d5.png
         * gift_count : **
         */

        private List<GiftBean> gift;
        private List<?> pictures;
        /**
         * id : 30
         * uid : 102712
         * hostid : 102712
         * starttime : 1618380136
         * endtime : 1624269601
         * guardian_id : 4
         * guardian_user_log_id : 50
         * addtime : 1618380136
         * user_nickname : **
         * avatar : http://xta.zzmzrj.com/admin/20210414/1513c6344edb0216d05bd41e2c9131d5.png
         * gift_count : null
         * conceal_protector : 1
         */

        private List<GuardianUserListBean> guardian_user_list;
        private List<?> img;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUser_status() {
            return user_status;
        }

        public void setUser_status(String user_status) {
            this.user_status = user_status;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getWealth_level() {
            return wealth_level;
        }

        public void setWealth_level(String wealth_level) {
            this.wealth_level = wealth_level;
        }

        public String getGlamour_level() {
            return glamour_level;
        }

        public void setGlamour_level(String glamour_level) {
            this.glamour_level = glamour_level;
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

        public String getConceal_location() {
            return conceal_location;
        }

        public void setConceal_location(String conceal_location) {
            this.conceal_location = conceal_location;
        }

        public String getConceal_visit() {
            return conceal_visit;
        }

        public void setConceal_visit(String conceal_visit) {
            this.conceal_visit = conceal_visit;
        }

        public String getConceal_enter() {
            return conceal_enter;
        }

        public void setConceal_enter(String conceal_enter) {
            this.conceal_enter = conceal_enter;
        }

        public String getConceal_protector() {
            return conceal_protector;
        }

        public void setConceal_protector(String conceal_protector) {
            this.conceal_protector = conceal_protector;
        }

        public String getConceal_wealth_charm_thumbs_up() {
            return conceal_wealth_charm_thumbs_up;
        }

        public void setConceal_wealth_charm_thumbs_up(String conceal_wealth_charm_thumbs_up) {
            this.conceal_wealth_charm_thumbs_up = conceal_wealth_charm_thumbs_up;
        }

        public String getConceal_gifts() {
            return conceal_gifts;
        }

        public void setConceal_gifts(String conceal_gifts) {
            this.conceal_gifts = conceal_gifts;
        }

        public String getConceal_my_protector() {
            return conceal_my_protector;
        }

        public void setConceal_my_protector(String conceal_my_protector) {
            this.conceal_my_protector = conceal_my_protector;
        }

        public String getAttention() {
            return attention;
        }

        public void setAttention(String attention) {
            this.attention = attention;
        }

        public String getIs_black() {
            return is_black;
        }

        public void setIs_black(String is_black) {
            this.is_black = is_black;
        }

        public String getGift_count() {
            return gift_count;
        }

        public void setGift_count(String gift_count) {
            this.gift_count = gift_count;
        }

        public String getPictures_count() {
            return pictures_count;
        }

        public void setPictures_count(String pictures_count) {
            this.pictures_count = pictures_count;
        }

        public String getIs_online() {
            return is_online;
        }

        public void setIs_online(String is_online) {
            this.is_online = is_online;
        }

        public String getVideo_deduction() {
            return video_deduction;
        }

        public void setVideo_deduction(String video_deduction) {
            this.video_deduction = video_deduction;
        }

        public String getVoice_deduction() {
            return voice_deduction;
        }

        public void setVoice_deduction(String voice_deduction) {
            this.voice_deduction = voice_deduction;
        }

        public String getAttention_fans() {
            return attention_fans;
        }

        public void setAttention_fans(String attention_fans) {
            this.attention_fans = attention_fans;
        }

        public String getAttention_all() {
            return attention_all;
        }

        public void setAttention_all(String attention_all) {
            this.attention_all = attention_all;
        }

        public String getCall() {
            return call;
        }

        public void setCall(String call) {
            this.call = call;
        }

        public String getEvaluation() {
            return evaluation;
        }

        public void setEvaluation(String evaluation) {
            this.evaluation = evaluation;
        }

        public String getGive_like() {
            return give_like;
        }

        public void setGive_like(String give_like) {
            this.give_like = give_like;
        }

        public String getIs_visible_bottom_btn() {
            return is_visible_bottom_btn;
        }

        public void setIs_visible_bottom_btn(String is_visible_bottom_btn) {
            this.is_visible_bottom_btn = is_visible_bottom_btn;
        }

        public String getHas_declaration() {
            return has_declaration;
        }

        public void setHas_declaration(String has_declaration) {
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

        public String getIs_settled() {
            return is_settled;
        }

        public void setIs_settled(String is_settled) {
            this.is_settled = is_settled;
        }

        public String getIs_auth() {
            return is_auth;
        }

        public void setIs_auth(String is_auth) {
            this.is_auth = is_auth;
        }

        public String getDeclaration_length() {
            return declaration_length;
        }

        public void setDeclaration_length(String declaration_length) {
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

        public List<String> getImage_label() {
            return image_label;
        }

        public void setImage_label(List<String> image_label) {
            this.image_label = image_label;
        }

        public List<String> getInformation() {
            return information;
        }

        public void setInformation(List<String> information) {
            this.information = information;
        }

        public List<?> getRemarks() {
            return remarks;
        }

        public void setRemarks(List<?> remarks) {
            this.remarks = remarks;
        }

        public List<GiftBean> getGift() {
            return gift;
        }

        public void setGift(List<GiftBean> gift) {
            this.gift = gift;
        }

        public List<?> getPictures() {
            return pictures;
        }

        public void setPictures(List<?> pictures) {
            this.pictures = pictures;
        }

        public List<GuardianUserListBean> getGuardian_user_list() {
            return guardian_user_list;
        }

        public void setGuardian_user_list(List<GuardianUserListBean> guardian_user_list) {
            this.guardian_user_list = guardian_user_list;
        }

        public List<?> getImg() {
            return img;
        }

        public void setImg(List<?> img) {
            this.img = img;
        }

        public static class GiftBean implements Serializable {
            private String img;
            private String gift_count;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getGift_count() {
                return gift_count;
            }

            public void setGift_count(String gift_count) {
                this.gift_count = gift_count;
            }
        }

        public static class GuardianUserListBean implements Serializable {
            private String id;
            private String uid;
            private String hostid;
            private String starttime;
            private String endtime;
            private String guardian_id;
            private String guardian_user_log_id;
            private String addtime;
            private String user_nickname;
            private String avatar;
            private Object gift_count;
            private String conceal_protector;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getHostid() {
                return hostid;
            }

            public void setHostid(String hostid) {
                this.hostid = hostid;
            }

            public String getStarttime() {
                return starttime;
            }

            public void setStarttime(String starttime) {
                this.starttime = starttime;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getGuardian_id() {
                return guardian_id;
            }

            public void setGuardian_id(String guardian_id) {
                this.guardian_id = guardian_id;
            }

            public String getGuardian_user_log_id() {
                return guardian_user_log_id;
            }

            public void setGuardian_user_log_id(String guardian_user_log_id) {
                this.guardian_user_log_id = guardian_user_log_id;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
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

            public Object getGift_count() {
                return gift_count;
            }

            public void setGift_count(Object gift_count) {
                this.gift_count = gift_count;
            }

            public String getConceal_protector() {
                return conceal_protector;
            }

            public void setConceal_protector(String conceal_protector) {
                this.conceal_protector = conceal_protector;
            }
        }
    }
}