package com.muse.xiangta.modle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TaskListBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : {"new":[{"id":1,"type":0,"title":"上传头像","num":3,"details":"成功上传头像","llink":null,"createtime":null},{"id":2,"type":0,"title":"完善资料","num":3,"details":"完善个人基础资料","llink":null,"createtime":null},{"id":3,"type":0,"title":"上传照片","num":3,"details":"至少上传4张照片","llink":null,"createtime":null}],"daily":[{"id":4,"type":1,"title":"签到","num":2,"details":"每日签到","llink":null,"createtime":null},{"id":5,"type":1,"title":"文字聊天","num":3,"details":"文字聊天","llink":null,"createtime":null},{"id":6,"type":1,"title":"发布动态","num":3,"details":"发布动态","llink":null,"createtime":null}]}
     */

    private int code;
    private String msg;
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
        /**
         * id : 1
         * type : 0
         * title : 上传头像
         * num : 3
         * details : 成功上传头像
         * llink : null
         * createtime : null
         */

        @SerializedName("new")
        private List<NewBean> newX;
        /**
         * id : 4
         * type : 1
         * title : 签到
         * num : 2
         * details : 每日签到
         * llink : null
         * createtime : null
         */

        private List<DailyBean> daily;

        public List<NewBean> getNewX() {
            return newX;
        }

        public void setNewX(List<NewBean> newX) {
            this.newX = newX;
        }

        public List<DailyBean> getDaily() {
            return daily;
        }

        public void setDaily(List<DailyBean> daily) {
            this.daily = daily;
        }

        public static class NewBean implements Serializable{
            private int id;
            private int type;
            private String title;
            private int num;
            private String details;
            private Object llink;
            private Object createtime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getDetails() {
                return details;
            }

            public void setDetails(String details) {
                this.details = details;
            }

            public Object getLlink() {
                return llink;
            }

            public void setLlink(Object llink) {
                this.llink = llink;
            }

            public Object getCreatetime() {
                return createtime;
            }

            public void setCreatetime(Object createtime) {
                this.createtime = createtime;
            }
        }

        public static class DailyBean implements Serializable {
            private int id;
            private int type;
            private String title;
            private int num;
            private String details;
            private Object llink;
            private Object createtime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getDetails() {
                return details;
            }

            public void setDetails(String details) {
                this.details = details;
            }

            public Object getLlink() {
                return llink;
            }

            public void setLlink(Object llink) {
                this.llink = llink;
            }

            public Object getCreatetime() {
                return createtime;
            }

            public void setCreatetime(Object createtime) {
                this.createtime = createtime;
            }
        }
    }
}
