package com.muse.xiangta.modle;

import com.muse.xiangta.json.JsonRequestBase;

public class JsonDoGetVideoCallInfoModel extends JsonRequestBase{
    private String ext;

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
