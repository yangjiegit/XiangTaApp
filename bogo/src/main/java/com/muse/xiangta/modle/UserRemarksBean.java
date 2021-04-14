package com.muse.xiangta.modle;

import java.io.Serializable;
import java.util.List;

public class UserRemarksBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : [{"remark_id":11,"remark_name":"游泳","type":1,"create_time":1615541733,"checked":0},{"remark_id":12,"remark_name":"跑步","type":1,"create_time":1615541733,"checked":0},{"remark_id":13,"remark_name":"单车","type":1,"create_time":1615541733,"checked":0},{"remark_id":14,"remark_name":"瑜伽","type":1,"create_time":1615541733,"checked":0}]
     */

    private int code;
    private String msg;
    /**
     * remark_id : 11
     * remark_name : 游泳
     * type : 1
     * create_time : 1615541733
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
        private int remark_id;
        private String remark_name;
        private int type;
        private int create_time;
        private int checked;

        public int getRemark_id() {
            return remark_id;
        }

        public void setRemark_id(int remark_id) {
            this.remark_id = remark_id;
        }

        public String getRemark_name() {
            return remark_name;
        }

        public void setRemark_name(String remark_name) {
            this.remark_name = remark_name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public int getChecked() {
            return checked;
        }

        public void setChecked(int checked) {
            this.checked = checked;
        }
    }
}
