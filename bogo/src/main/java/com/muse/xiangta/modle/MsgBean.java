package com.muse.xiangta.modle;

import java.io.Serializable;

public class MsgBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : {"id":2,"from_user":"浮沉","to_user":"江晚吟","group_name":"天上人间","gift_name":"[金话筒]","count":"1个","msg":",赶紧去围观吧！！","status":1}
     */

    private int code;
    private String msg;
    /**
     * id : 2
     * from_user : 浮沉
     * to_user : 江晚吟
     * group_name : 天上人间
     * gift_name : [金话筒]
     * count : 1个
     * msg : ,赶紧去围观吧！！
     * status : 1
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
        private String from_user;
        private String to_user;
        private String group_name;
        private String gift_name;
        private String count;
        private String msg;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFrom_user() {
            return from_user;
        }

        public void setFrom_user(String from_user) {
            this.from_user = from_user;
        }

        public String getTo_user() {
            return to_user;
        }

        public void setTo_user(String to_user) {
            this.to_user = to_user;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public String getGift_name() {
            return gift_name;
        }

        public void setGift_name(String gift_name) {
            this.gift_name = gift_name;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
