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
     * data : {"id":102595,"sex":2,"user_nickname":"晨晨！","avatar":"http://xta.zzmzrj.com/6e3ea202103081120398748.png","address":"郑州市","user_status":1,"level":7,"signature":"","wealth_level":5,"glamour_level":1,"information":["内心独白：呵呵哒","职业：程序员","所在地：郑州市","年收入：2193","情感状态：1"],"remarks":[],"attention":0,"is_black":0,"gift_count":32,"gift":[{"id":1,"name":"小幽灵","coin":"10","img":"/public/image/gift/xiaoyouling.png","addtime":1574495601,"orderno":1,"type":1,"is_all_notify":0,"gift_count":"21"},{"id":3,"name":"女神","coin":"66","img":"/public/image/gift/nvshen.png","addtime":1574494569,"orderno":1,"type":1,"is_all_notify":0,"gift_count":"6"},{"id":5,"name":"水晶鞋","coin":"188","img":"/public/image/gift/shuijingxie.png","addtime":1574491919,"orderno":4,"type":1,"is_all_notify":0,"gift_count":"1"},{"id":10,"name":"吻","coin":"50","img":"/public/image/gift/wen.png","addtime":1574495514,"orderno":4,"type":1,"is_all_notify":0,"gift_count":"2"},{"id":11,"name":"浪漫单车","coin":"60","img":"/public/image/gift/langmandanche.png","addtime":1520386726,"orderno":12,"type":1,"is_all_notify":0,"gift_count":"1"},{"id":12,"name":"樱花雨","coin":"100","img":"/public/image/gift/yinghuayu.png","addtime":1520386746,"orderno":12,"type":1,"is_all_notify":0,"gift_count":"1"}],"pictures_count":3,"pictures":[{"img":"http://xta.zzmzrj.com/21b6220210310154321495.png?imageMogr2/auto-orient/blur/40x50","id":339,"watch":1},{"img":"http://xta.zzmzrj.com/df09f202103101543227711.png?imageMogr2/auto-orient/blur/40x50","id":340,"watch":1},{"img":"http://xta.zzmzrj.com/bb972202103101543228356.png?imageMogr2/auto-orient/blur/40x50","id":341,"watch":1}],"is_online":1,"video_deduction":20,"voice_deduction":"10","guardian_user_list":[{"id":11,"uid":102600,"hostid":102595,"starttime":1615198788,"endtime":1624443573,"guardian_id":6,"guardian_user_log_id":16,"addtime":1615198788,"user_nickname":"帅哥1","avatar":"http://xta.zzmzrj.com/f0153202103080951388905.png","gift_count":1932},{"id":10,"uid":102595,"hostid":102595,"starttime":1615197663,"endtime":1623837663,"guardian_id":5,"guardian_user_log_id":14,"addtime":1615197663,"user_nickname":"晨晨！","avatar":"http://xta.zzmzrj.com/6e3ea202103081120398748.png","gift_count":null}],"attention_fans":9,"attention_all":3,"call":"3 时s, 50 分39秒","evaluation":0,"img":[],"give_like":1,"is_visible_bottom_btn":0,"has_declaration":1,"declaration":"http://xta.zzmzrj.com/idIosAudio/1615343949220.wav","overlapping_sound":""}
     */

    private int code;
    private String msg;
    /**
     * id : 102595
     * sex : 2
     * user_nickname : 晨晨！
     * avatar : http://xta.zzmzrj.com/6e3ea202103081120398748.png
     * address : 郑州市
     * user_status : 1
     * level : 7
     * signature :
     * wealth_level : 5
     * glamour_level : 1
     * information : ["内心独白：呵呵哒","职业：程序员","所在地：郑州市","年收入：2193","情感状态：1"]
     * remarks : []
     * attention : 0
     * is_black : 0
     * gift_count : 32
     * gift : [{"id":1,"name":"小幽灵","coin":"10","img":"/public/image/gift/xiaoyouling.png","addtime":1574495601,"orderno":1,"type":1,"is_all_notify":0,"gift_count":"21"},{"id":3,"name":"女神","coin":"66","img":"/public/image/gift/nvshen.png","addtime":1574494569,"orderno":1,"type":1,"is_all_notify":0,"gift_count":"6"},{"id":5,"name":"水晶鞋","coin":"188","img":"/public/image/gift/shuijingxie.png","addtime":1574491919,"orderno":4,"type":1,"is_all_notify":0,"gift_count":"1"},{"id":10,"name":"吻","coin":"50","img":"/public/image/gift/wen.png","addtime":1574495514,"orderno":4,"type":1,"is_all_notify":0,"gift_count":"2"},{"id":11,"name":"浪漫单车","coin":"60","img":"/public/image/gift/langmandanche.png","addtime":1520386726,"orderno":12,"type":1,"is_all_notify":0,"gift_count":"1"},{"id":12,"name":"樱花雨","coin":"100","img":"/public/image/gift/yinghuayu.png","addtime":1520386746,"orderno":12,"type":1,"is_all_notify":0,"gift_count":"1"}]
     * pictures_count : 3
     * pictures : [{"img":"http://xta.zzmzrj.com/21b6220210310154321495.png?imageMogr2/auto-orient/blur/40x50","id":339,"watch":1},{"img":"http://xta.zzmzrj.com/df09f202103101543227711.png?imageMogr2/auto-orient/blur/40x50","id":340,"watch":1},{"img":"http://xta.zzmzrj.com/bb972202103101543228356.png?imageMogr2/auto-orient/blur/40x50","id":341,"watch":1}]
     * is_online : 1
     * video_deduction : 20
     * voice_deduction : 10
     * guardian_user_list : [{"id":11,"uid":102600,"hostid":102595,"starttime":1615198788,"endtime":1624443573,"guardian_id":6,"guardian_user_log_id":16,"addtime":1615198788,"user_nickname":"帅哥1","avatar":"http://xta.zzmzrj.com/f0153202103080951388905.png","gift_count":1932},{"id":10,"uid":102595,"hostid":102595,"starttime":1615197663,"endtime":1623837663,"guardian_id":5,"guardian_user_log_id":14,"addtime":1615197663,"user_nickname":"晨晨！","avatar":"http://xta.zzmzrj.com/6e3ea202103081120398748.png","gift_count":null}]
     * attention_fans : 9
     * attention_all : 3
     * call : 3 时s, 50 分39秒
     * evaluation : 0
     * img : []
     * give_like : 1
     * is_visible_bottom_btn : 0
     * has_declaration : 1
     * declaration : http://xta.zzmzrj.com/idIosAudio/1615343949220.wav
     * overlapping_sound :
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
        private int id;
        private int sex;
        private int is_auth;
        private String user_nickname;
        private String avatar;
        private String address;
        private int user_status;
        private int level;
        private String signature;
        private int wealth_level;
        private int glamour_level;
        private int attention;
        private int is_black;
        private int gift_count;
        private int pictures_count;
        private int is_online;
        private int video_deduction;
        private String voice_deduction;
        private int attention_fans;
        private int attention_all;
        private String call;
        private int evaluation;
        private int give_like;
        private int is_visible_bottom_btn;
        private int has_declaration;
        private String declaration;
        private String overlapping_sound;
        private List<String> information;
        private String noble;

        public String getNoble() {
            return noble;
        }

        public void setNoble(String noble) {
            this.noble = noble;
        }

        public int getIs_auth() {
            return is_auth;
        }

        public void setIs_auth(int is_auth) {
            this.is_auth = is_auth;
        }

        /**
         * id : 1
         * name : 小幽灵
         * coin : 10
         * img : /public/image/gift/xiaoyouling.png
         * addtime : 1574495601
         * orderno : 1
         * type : 1
         * is_all_notify : 0
         * gift_count : 21
         */



        private List<GiftBean> gift;
        /**
         * img : http://xta.zzmzrj.com/21b6220210310154321495.png?imageMogr2/auto-orient/blur/40x50
         * id : 339
         * watch : 1
         */

        private List<PicturesBean> pictures;
        /**
         * id : 11
         * uid : 102600
         * hostid : 102595
         * starttime : 1615198788
         * endtime : 1624443573
         * guardian_id : 6
         * guardian_user_log_id : 16
         * addtime : 1615198788
         * user_nickname : 帅哥1
         * avatar : http://xta.zzmzrj.com/f0153202103080951388905.png
         * gift_count : 1932
         */

        private List<GuardianUserListBean> guardian_user_list;
        private List<ImgBean> img;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getWealth_level() {
            return wealth_level;
        }

        public void setWealth_level(int wealth_level) {
            this.wealth_level = wealth_level;
        }

        public int getGlamour_level() {
            return glamour_level;
        }

        public void setGlamour_level(int glamour_level) {
            this.glamour_level = glamour_level;
        }

        public int getAttention() {
            return attention;
        }

        public void setAttention(int attention) {
            this.attention = attention;
        }

        public int getIs_black() {
            return is_black;
        }

        public void setIs_black(int is_black) {
            this.is_black = is_black;
        }

        public int getGift_count() {
            return gift_count;
        }

        public void setGift_count(int gift_count) {
            this.gift_count = gift_count;
        }

        public int getPictures_count() {
            return pictures_count;
        }

        public void setPictures_count(int pictures_count) {
            this.pictures_count = pictures_count;
        }

        public int getIs_online() {
            return is_online;
        }

        public void setIs_online(int is_online) {
            this.is_online = is_online;
        }

        public int getVideo_deduction() {
            return video_deduction;
        }

        public void setVideo_deduction(int video_deduction) {
            this.video_deduction = video_deduction;
        }

        public String getVoice_deduction() {
            return voice_deduction;
        }

        public void setVoice_deduction(String voice_deduction) {
            this.voice_deduction = voice_deduction;
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

        public String getCall() {
            return call;
        }

        public void setCall(String call) {
            this.call = call;
        }

        public int getEvaluation() {
            return evaluation;
        }

        public void setEvaluation(int evaluation) {
            this.evaluation = evaluation;
        }

        public int getGive_like() {
            return give_like;
        }

        public void setGive_like(int give_like) {
            this.give_like = give_like;
        }

        public int getIs_visible_bottom_btn() {
            return is_visible_bottom_btn;
        }

        public void setIs_visible_bottom_btn(int is_visible_bottom_btn) {
            this.is_visible_bottom_btn = is_visible_bottom_btn;
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

        public List<String> getInformation() {
            return information;
        }

        public void setInformation(List<String> information) {
            this.information = information;
        }

        public List<GiftBean> getGift() {
            return gift;
        }

        public void setGift(List<GiftBean> gift) {
            this.gift = gift;
        }

        public List<PicturesBean> getPictures() {
            return pictures;
        }

        public void setPictures(List<PicturesBean> pictures) {
            this.pictures = pictures;
        }

        public List<GuardianUserListBean> getGuardian_user_list() {
            return guardian_user_list;
        }

        public void setGuardian_user_list(List<GuardianUserListBean> guardian_user_list) {
            this.guardian_user_list = guardian_user_list;
        }

        public List<ImgBean> getImg() {
            return img;
        }

        public void setImg(List<ImgBean> img) {
            this.img = img;
        }

        public static class ImgBean implements Serializable{

            /**
             * id : 512
             * img : http://xta.zzmzrj.com/avatar/1617183317929_Screenshot_2021-02-28-15-26-09-30.jpg
             */

            private int id;
            private String img;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }

        public static class GiftBean implements Serializable {
            private int id;
            private String name;
            private String coin;
            private String img;
            private int addtime;
            private int orderno;
            private int type;
            private int is_all_notify;
            private String gift_count;

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

            public String getCoin() {
                return coin;
            }

            public void setCoin(String coin) {
                this.coin = coin;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getAddtime() {
                return addtime;
            }

            public void setAddtime(int addtime) {
                this.addtime = addtime;
            }

            public int getOrderno() {
                return orderno;
            }

            public void setOrderno(int orderno) {
                this.orderno = orderno;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getIs_all_notify() {
                return is_all_notify;
            }

            public void setIs_all_notify(int is_all_notify) {
                this.is_all_notify = is_all_notify;
            }

            public String getGift_count() {
                return gift_count;
            }

            public void setGift_count(String gift_count) {
                this.gift_count = gift_count;
            }
        }

        public static class PicturesBean implements Serializable {
            private String img;
            private int id;
            private int watch;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getWatch() {
                return watch;
            }

            public void setWatch(int watch) {
                this.watch = watch;
            }
        }

        public static class GuardianUserListBean implements Serializable {
            private int id;
            private int uid;
            private int hostid;
            private int starttime;
            private int endtime;
            private int guardian_id;
            private int guardian_user_log_id;
            private int addtime;
            private String user_nickname;
            private String avatar;
            private int gift_count;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public int getHostid() {
                return hostid;
            }

            public void setHostid(int hostid) {
                this.hostid = hostid;
            }

            public int getStarttime() {
                return starttime;
            }

            public void setStarttime(int starttime) {
                this.starttime = starttime;
            }

            public int getEndtime() {
                return endtime;
            }

            public void setEndtime(int endtime) {
                this.endtime = endtime;
            }

            public int getGuardian_id() {
                return guardian_id;
            }

            public void setGuardian_id(int guardian_id) {
                this.guardian_id = guardian_id;
            }

            public int getGuardian_user_log_id() {
                return guardian_user_log_id;
            }

            public void setGuardian_user_log_id(int guardian_user_log_id) {
                this.guardian_user_log_id = guardian_user_log_id;
            }

            public int getAddtime() {
                return addtime;
            }

            public void setAddtime(int addtime) {
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

            public int getGift_count() {
                return gift_count;
            }

            public void setGift_count(int gift_count) {
                this.gift_count = gift_count;
            }
        }
    }
}