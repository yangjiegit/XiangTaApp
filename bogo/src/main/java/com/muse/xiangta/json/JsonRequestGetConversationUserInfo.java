package com.muse.xiangta.json;

import com.muse.xiangta.modle.UserModel;

import java.util.List;

/**
 * Created by weipeng on 2018/2/22.
 */

public class JsonRequestGetConversationUserInfo extends JsonRequestBase {

    private List<UserModel> list;

    public List<UserModel> getList() {
        return list;
    }

    public void setList(List<UserModel> list) {
        this.list = list;
    }
}
