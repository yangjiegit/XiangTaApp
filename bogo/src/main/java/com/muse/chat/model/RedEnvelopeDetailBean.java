package com.muse.chat.model;

import java.io.Serializable;
import java.util.List;

public class RedEnvelopeDetailBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : {"amount":"1","history":[{"red_envelope_id":18,"to_uid":102727,"create_time":"04-09 11:54","amount":1,"to_user":{"user_nickname":"江晚吟","avatar":"http://xta.zzmzrj.com/avatar/1617958475701_1617958473510.jpg","id":102727}}],"total_count":1,"count":0,"from_user":{"user_nickname":"浮沉","avatar":"http://xta.zzmzrj.com/47488202104091043171109.png"}}
     */

    private int code;
    private String msg;
    /**
     * amount : 1
     * history : [{"red_envelope_id":18,"to_uid":102727,"create_time":"04-09 11:54","amount":1,"to_user":{"user_nickname":"江晚吟","avatar":"http://xta.zzmzrj.com/avatar/1617958475701_1617958473510.jpg","id":102727}}]
     * total_count : 1
     * count : 0
     * from_user : {"user_nickname":"浮沉","avatar":"http://xta.zzmzrj.com/47488202104091043171109.png"}
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
        private String amount;
        private int total_count;
        private int count;
        /**
         * user_nickname : 浮沉
         * avatar : http://xta.zzmzrj.com/47488202104091043171109.png
         */

        private FromUserBean from_user;
        /**
         * red_envelope_id : 18
         * to_uid : 102727
         * create_time : 04-09 11:54
         * amount : 1
         * to_user : {"user_nickname":"江晚吟","avatar":"http://xta.zzmzrj.com/avatar/1617958475701_1617958473510.jpg","id":102727}
         */

        private List<HistoryBean> history;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public int getTotal_count() {
            return total_count;
        }

        public void setTotal_count(int total_count) {
            this.total_count = total_count;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public FromUserBean getFrom_user() {
            return from_user;
        }

        public void setFrom_user(FromUserBean from_user) {
            this.from_user = from_user;
        }

        public List<HistoryBean> getHistory() {
            return history;
        }

        public void setHistory(List<HistoryBean> history) {
            this.history = history;
        }

        public static class FromUserBean implements Serializable{
            private String user_nickname;
            private String avatar;

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
        }

        public static class HistoryBean implements Serializable{
            private int red_envelope_id;
            private int to_uid;
            private String create_time;
            private int amount;
            /**
             * user_nickname : 江晚吟
             * avatar : http://xta.zzmzrj.com/avatar/1617958475701_1617958473510.jpg
             * id : 102727
             */

            private ToUserBean to_user;

            public int getRed_envelope_id() {
                return red_envelope_id;
            }

            public void setRed_envelope_id(int red_envelope_id) {
                this.red_envelope_id = red_envelope_id;
            }

            public int getTo_uid() {
                return to_uid;
            }

            public void setTo_uid(int to_uid) {
                this.to_uid = to_uid;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public ToUserBean getTo_user() {
                return to_user;
            }

            public void setTo_user(ToUserBean to_user) {
                this.to_user = to_user;
            }

            public static class ToUserBean implements Serializable{
                private String user_nickname;
                private String avatar;
                private int id;

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

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }
        }
    }
}
