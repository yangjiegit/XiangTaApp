package com.muse.xiangta.modle;

import java.io.Serializable;
import java.util.List;

public class LablesBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : [{"id":1,"type":1,"sort":0,"name":"呵呵哒","checked":0},{"id":3,"type":1,"sort":0,"name":"哇塞","checked":0},{"id":5,"type":1,"sort":0,"name":"逗比","checked":0}]
     */

    private int code;
    private String msg;
    /**
     * id : 1
     * type : 1
     * sort : 0
     * name : 呵呵哒
     * checked : 0
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

    public static class DataBean implements Serializable {
        private int id;
        private int type;
        private int sort;
        private String name;
        private int checked;

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

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getChecked() {
            return checked;
        }

        public void setChecked(int checked) {
            this.checked = checked;
        }
    }
}
