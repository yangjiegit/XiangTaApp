package com.muse.xiangta.modle;

import java.io.Serializable;
import java.util.List;


public class ChargeBean implements Serializable {
    private String code;
    private int status;
    private int val;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
