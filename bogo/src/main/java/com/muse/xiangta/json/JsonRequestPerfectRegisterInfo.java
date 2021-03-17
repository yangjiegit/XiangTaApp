package com.muse.xiangta.json;

import com.muse.xiangta.modle.UserModel;

public class JsonRequestPerfectRegisterInfo extends JsonRequestBase{

    private UserModel data;

    public UserModel getData() {
        return data;
    }

    public void setData(UserModel data) {
        this.data = data;
    }
}
