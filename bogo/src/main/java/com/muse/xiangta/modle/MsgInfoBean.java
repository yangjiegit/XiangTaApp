package com.muse.xiangta.modle;

import java.io.Serializable;

public class MsgInfoBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : {"system":"系统消息","group":"已有100人在这里嗨","family":"多人免费聊天，让快乐加倍"}
     */

    private int code;
    private String msg;
    /**
     * system : 系统消息
     * group : 已有100人在这里嗨
     * family : 多人免费聊天，让快乐加倍
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
        private String system;
        private String group;
        private String family;

        public String getSystem() {
            return system;
        }

        public void setSystem(String system) {
            this.system = system;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public String getFamily() {
            return family;
        }

        public void setFamily(String family) {
            this.family = family;
        }
    }
}
