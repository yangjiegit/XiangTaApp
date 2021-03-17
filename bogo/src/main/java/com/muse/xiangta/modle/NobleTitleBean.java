package com.muse.xiangta.modle;

import java.io.Serializable;
import java.util.List;

public class NobleTitleBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : [{"id":1,"title":"骑士"},{"id":2,"title":"子爵"},{"id":3,"title":"伯爵"},{"id":4,"title":"侯爵"},{"id":5,"title":"公爵"},{"id":6,"title":"国王"}]
     */

    private int code;
    private String msg;
    /**
     * id : 1
     * title : 骑士
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private int id;
        private String title;
        private boolean check;

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
