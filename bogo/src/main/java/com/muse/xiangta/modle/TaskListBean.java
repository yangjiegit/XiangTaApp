package com.muse.xiangta.modle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TaskListBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : {"new":[{"id":1,"type":0,"title":"上传头像","num":3,"details":"成功上传头像","llink":null,"createtime":null,"is_ou":1,"btn_title":"去上传","btn_type":1,"is_complete":1},{"id":2,"type":0,"title":"完善资料","num":3,"details":"完善个人基本资料","llink":null,"createtime":null,"is_ou":1,"btn_title":"去完善","btn_type":1,"is_complete":1},{"id":3,"type":0,"title":"上传照片","num":3,"details":"至少上传4张照片","llink":null,"createtime":null,"is_ou":1,"btn_title":"去上传","btn_type":1,"is_complete":1},{"id":7,"type":0,"title":"真人认证","num":10,"details":"完成真人认证","llink":null,"createtime":null,"is_ou":null,"btn_title":"去认证","btn_type":2,"is_complete":0},{"id":8,"type":0,"title":"关注","num":1,"details":"关注喜欢的人","llink":null,"createtime":null,"is_ou":1,"btn_title":"去关注","btn_type":3,"is_complete":0},{"id":9,"type":0,"title":"视频速配","num":10,"details":"与你有缘的人视频聊天","llink":null,"createtime":null,"is_ou":null,"btn_title":"去速配","btn_type":3,"is_complete":0},{"id":10,"type":0,"title":"点赞","num":2,"details":"点赞喜欢的交友宣言，喜欢动态","llink":null,"createtime":null,"is_ou":1,"btn_title":"去点赞","btn_type":4,"is_complete":0},{"id":11,"type":0,"title":"动态评论","num":2,"details":"给好友动态评论","llink":null,"createtime":null,"is_ou":1,"btn_title":"去评论","btn_type":4,"is_complete":0}],"daily":[{"id":5,"type":1,"title":"文字聊天","num":3,"details":"文字聊天","llink":null,"createtime":null,"is_ou":null,"btn_title":"去聊天","btn_type":3,"is_complete":0},{"id":6,"type":1,"title":"发布动态","num":3,"details":"发布动态","llink":null,"createtime":null,"is_ou":null,"btn_title":"去发布","btn_type":4,"is_complete":0}]}
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

    public static class DataBean implements Serializable {
        /**
         * id : 1
         * type : 0
         * title : 上传头像
         * num : 3
         * details : 成功上传头像
         * llink : null
         * createtime : null
         * is_ou : 1
         * btn_title : 去上传
         * btn_type : 1
         * is_complete : 1
         */

        @SerializedName("new")
        private List<NewBean> newX;
        /**
         * id : 5
         * type : 1
         * title : 文字聊天
         * num : 3
         * details : 文字聊天
         * llink : null
         * createtime : null
         * is_ou : null
         * btn_title : 去聊天
         * btn_type : 3
         * is_complete : 0
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

        public static class NewBean implements Serializable {
            private int id;
            private int type;
            private String title;
            private int num;
            private String details;
            private Object llink;
            private Object createtime;
            private int is_ou;
            private String btn_title;
            private int btn_type;
            private int is_complete;

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

            public int getIs_ou() {
                return is_ou;
            }

            public void setIs_ou(int is_ou) {
                this.is_ou = is_ou;
            }

            public String getBtn_title() {
                return btn_title;
            }

            public void setBtn_title(String btn_title) {
                this.btn_title = btn_title;
            }

            public int getBtn_type() {
                return btn_type;
            }

            public void setBtn_type(int btn_type) {
                this.btn_type = btn_type;
            }

            public int getIs_complete() {
                return is_complete;
            }

            public void setIs_complete(int is_complete) {
                this.is_complete = is_complete;
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
            private Object is_ou;
            private String btn_title;
            private int btn_type;
            private int is_complete;

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

            public Object getIs_ou() {
                return is_ou;
            }

            public void setIs_ou(Object is_ou) {
                this.is_ou = is_ou;
            }

            public String getBtn_title() {
                return btn_title;
            }

            public void setBtn_title(String btn_title) {
                this.btn_title = btn_title;
            }

            public int getBtn_type() {
                return btn_type;
            }

            public void setBtn_type(int btn_type) {
                this.btn_type = btn_type;
            }

            public int getIs_complete() {
                return is_complete;
            }

            public void setIs_complete(int is_complete) {
                this.is_complete = is_complete;
            }
        }
    }
}
