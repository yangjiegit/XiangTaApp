package com.muse.xiangta.modle;

import com.muse.xiangta.utils.StringUtils;

import java.io.Serializable;
import java.util.List;

public class DynamicListModel implements Serializable {

    /**
     * id : 73
     * is_audio : 0
     * audio_file :
     * publish_time : 4小时前
     * msg_content : 挖藕
     * uid : 102714
     * video_url :
     * cover_url :
     * city : 郑州市
     * lat : 34.785649
     * lng : 113.686272
     * duration :
     * is_love : 0
     * title : 恋爱了
     * love_status : 0
     * comment_count : 1
     * userInfo : {"id":102714,"avatar":"http://xta.zzmzrj.com/idImage/1617851407844.png","user_nickname":"挖藕","sex":1,"level":1,"coin":99275,"user_status":2,"is_auth":1,"is_online":1,"link_id":"0","signature":"","noble":"http://xta.zzmzrj.com/admin/20210323/4a039486c67cd136462b9b90cf0c9d4f.png"}
     * originalPicUrls : ["http://xta.zzmzrj.com/idImage/161827903756.png"]
     * thumbnailPicUrls : ["http://xta.zzmzrj.com/idImage/161827903756.png"]
     * is_like : 0
     * like_count : 0
     */

    private int id;
    private int is_audio;
    private String audio_file;
    private String publish_time;
    private String msg_content;
    private int uid;
    private String video_url;
    private String cover_url;
    private String city;
    private String lat;
    private String lng;
    private String duration;
    private int is_love;
    private String title;
    private int love_status;
    private int comment_count;
    /**
     * id : 102714
     * avatar : http://xta.zzmzrj.com/idImage/1617851407844.png
     * user_nickname : 挖藕
     * sex : 1
     * level : 1
     * coin : 99275
     * user_status : 2
     * is_auth : 1
     * is_online : 1
     * link_id : 0
     * signature :
     * noble : http://xta.zzmzrj.com/admin/20210323/4a039486c67cd136462b9b90cf0c9d4f.png
     */

    private UserInfoBean userInfo;
    private String is_like;
    private int like_count;
    private List<String> originalPicUrls;
    private List<String> thumbnailPicUrls;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIs_audio() {
        return is_audio;
    }

    public void setIs_audio(int is_audio) {
        this.is_audio = is_audio;
    }

    public String getAudio_file() {
        return audio_file;
    }

    public void setAudio_file(String audio_file) {
        this.audio_file = audio_file;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getIs_love() {
        return is_love;
    }

    public void setIs_love(int is_love) {
        this.is_love = is_love;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLove_status() {
        return love_status;
    }

    public void setLove_status(int love_status) {
        this.love_status = love_status;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public String getIs_like() {
        return is_like;
    }

    public void setIs_like(String is_like) {
        this.is_like = is_like;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public void plusLikeCount(int count) {
        like_count = like_count + count;
    }

    public void decLikeCount(int count) {
        like_count = like_count - count;
    }

    public List<String> getOriginalPicUrls() {
        return originalPicUrls;
    }

    public void setOriginalPicUrls(List<String> originalPicUrls) {
        this.originalPicUrls = originalPicUrls;
    }

    public List<String> getThumbnailPicUrls() {
        return thumbnailPicUrls;
    }

    public void setThumbnailPicUrls(List<String> thumbnailPicUrls) {
        this.thumbnailPicUrls = thumbnailPicUrls;
    }

    public static class UserInfoBean implements Serializable {
        private int id;
        private String avatar;
        private String user_nickname;
        private int sex;
        private int level;
        private String coin;
        private int user_status;
        private int is_auth;
        private int is_online;
        private String link_id;
        private String signature;
        private String noble;

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

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }

        public int getUser_status() {
            return user_status;
        }

        public void setUser_status(int user_status) {
            this.user_status = user_status;
        }

        public int getIs_auth() {
            return is_auth;
        }

        public void setIs_auth(int is_auth) {
            this.is_auth = is_auth;
        }

        public int getIs_online() {
            return is_online;
        }

        public void setIs_online(int is_online) {
            this.is_online = is_online;
        }

        public String getLink_id() {
            return link_id;
        }

        public void setLink_id(String link_id) {
            this.link_id = link_id;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getNoble() {
            return noble;
        }

        public void setNoble(String noble) {
            this.noble = noble;
        }
    }
}
