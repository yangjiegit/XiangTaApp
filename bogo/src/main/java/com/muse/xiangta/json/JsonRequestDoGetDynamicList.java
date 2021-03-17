package com.muse.xiangta.json;

import com.muse.xiangta.modle.DynamicListModel;

import java.util.List;

public class JsonRequestDoGetDynamicList extends JsonRequestBase {
    private List<DynamicListModel> list;

    public List<DynamicListModel> getList() {
        return list;
    }

    public void setList(List<DynamicListModel> list) {
        this.list = list;
    }
}
