package com.muse.xiangta.json;

import com.muse.xiangta.modle.CustomMsgModel;

import java.util.List;

public class JsonCustomMsgList extends JsonRequestBase {
    private List<CustomMsgModel> list;

    public List<CustomMsgModel> getList() {
        return list;
    }

    public void setList(List<CustomMsgModel> list) {
        this.list = list;
    }
}
