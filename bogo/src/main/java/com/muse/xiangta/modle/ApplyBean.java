package com.muse.xiangta.modle;

import java.io.Serializable;

public class ApplyBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : {"cover_video":2,"is_auth":1,"has_avatar":1,"need_perfect_pictures":1}
     */

    private int code;
    private String msg;
    /**
     * cover_video : 2
     * is_auth : 1
     * has_avatar : 1
     * need_perfect_pictures : 1
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
        private int cover_video;
        private int is_auth;
        private int has_avatar;
        private int need_perfect_pictures;

        public int getCover_video() {
            return cover_video;
        }

        public void setCover_video(int cover_video) {
            this.cover_video = cover_video;
        }

        public int getIs_auth() {
            return is_auth;
        }

        public void setIs_auth(int is_auth) {
            this.is_auth = is_auth;
        }

        public int getHas_avatar() {
            return has_avatar;
        }

        public void setHas_avatar(int has_avatar) {
            this.has_avatar = has_avatar;
        }

        public int getNeed_perfect_pictures() {
            return need_perfect_pictures;
        }

        public void setNeed_perfect_pictures(int need_perfect_pictures) {
            this.need_perfect_pictures = need_perfect_pictures;
        }
    }
}
