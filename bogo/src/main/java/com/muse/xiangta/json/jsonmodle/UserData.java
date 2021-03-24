package com.muse.xiangta.json.jsonmodle;

import java.io.Serializable;
import java.util.List;

/**
 * 用户基本信息
 */

public class UserData implements Serializable {

    /**
     * code : 1
     * msg :
     * data : {"sex":2,"user_nickname":"Joker","avatar":"http://xta.zzmzrj.com/avatar/1616397194924_1616397191882.png","is_change_name":1,"height":"172","weight":"40","constellation":"处女座","introduce":"P5天下第一","image_label":"逗比","occupation":"金融/投资/保险","education":"硕士","love_status":"单身","overlapping_sound":"魔道祖师天下第一","signature":"还未设置个性签名","sign":"还未设置个性签名","img":[{"id":487,"img":"http://xta.zzmzrj.com/avatar/1616570441505_mmexport1587029168758.jpg","uid":102597,"addtime":1616572843,"status":0},{"id":486,"img":"http://xta.zzmzrj.com/avatar/1616570441525_mmexport1589959367700.jpg","uid":102597,"addtime":1616572843,"status":0},{"id":485,"img":"http://xta.zzmzrj.com/avatar/1616570441515_mmexport1583308013326.jpg","uid":102597,"addtime":1616572843,"status":0},{"id":484,"img":"http://xta.zzmzrj.com/avatar/1616570441533_mmexport1589959174953.jpg","uid":102597,"addtime":1616572843,"status":0}],"declaration_length":5,"video_url":"shipin","video_img":"xuanyan"}
     */

    private int code;
    private String msg;
    /**
     * sex : 2
     * user_nickname : Joker
     * avatar : http://xta.zzmzrj.com/avatar/1616397194924_1616397191882.png
     * is_change_name : 1
     * height : 172
     * weight : 40
     * constellation : 处女座
     * introduce : P5天下第一
     * image_label : 逗比
     * occupation : 金融/投资/保险
     * education : 硕士
     * love_status : 单身
     * overlapping_sound : 魔道祖师天下第一
     * signature : 还未设置个性签名
     * sign : 还未设置个性签名
     * img : [{"id":487,"img":"http://xta.zzmzrj.com/avatar/1616570441505_mmexport1587029168758.jpg","uid":102597,"addtime":1616572843,"status":0},{"id":486,"img":"http://xta.zzmzrj.com/avatar/1616570441525_mmexport1589959367700.jpg","uid":102597,"addtime":1616572843,"status":0},{"id":485,"img":"http://xta.zzmzrj.com/avatar/1616570441515_mmexport1583308013326.jpg","uid":102597,"addtime":1616572843,"status":0},{"id":484,"img":"http://xta.zzmzrj.com/avatar/1616570441533_mmexport1589959174953.jpg","uid":102597,"addtime":1616572843,"status":0}]
     * declaration_length : 5
     * video_url : shipin
     * video_img : xuanyan
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
        private int sex;
        private String user_nickname;
        private String avatar;
        private int is_change_name;
        private String height;
        private String weight;
        private String constellation;
        private String introduce;
        private String image_label;
        private String occupation;
        private String education;
        private String love_status;
        private String overlapping_sound;
        private String signature;
        private String sign;
        private int declaration_length;
        private String video_url;
        private String video_img;
        /**
         * id : 487
         * img : http://xta.zzmzrj.com/avatar/1616570441505_mmexport1587029168758.jpg
         * uid : 102597
         * addtime : 1616572843
         * status : 0
         */

        private List<ImgBean> img;

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

        public int getIs_change_name() {
            return is_change_name;
        }

        public void setIs_change_name(int is_change_name) {
            this.is_change_name = is_change_name;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getConstellation() {
            return constellation;
        }

        public void setConstellation(String constellation) {
            this.constellation = constellation;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getImage_label() {
            return image_label;
        }

        public void setImage_label(String image_label) {
            this.image_label = image_label;
        }

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
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

        public String getOverlapping_sound() {
            return overlapping_sound;
        }

        public void setOverlapping_sound(String overlapping_sound) {
            this.overlapping_sound = overlapping_sound;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
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

        public List<ImgBean> getImg() {
            return img;
        }

        public void setImg(List<ImgBean> img) {
            this.img = img;
        }

        public static class ImgBean implements Serializable {
            private int id;
            private String img;
            private int uid;
            private int addtime;
            private int status;

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

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public int getAddtime() {
                return addtime;
            }

            public void setAddtime(int addtime) {
                this.addtime = addtime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
