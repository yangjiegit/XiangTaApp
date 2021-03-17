package com.muse.xiangta.modle;

import java.io.Serializable;

public class DeclarationBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : {"text":"春暖花开"}
     */

    private int code;
    private String msg;
    /**
     * text : 春暖花开
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

    public static class DataBean {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
