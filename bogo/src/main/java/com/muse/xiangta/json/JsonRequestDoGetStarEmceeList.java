package com.muse.xiangta.json;

import com.muse.xiangta.json.jsonmodle.TargetUserData;

import java.util.List;

public class JsonRequestDoGetStarEmceeList extends JsonRequestBase{
    private List<TargetUserData> data;

    public List<TargetUserData> getData() {
        return data;
    }

    public void setData(List<TargetUserData> data) {
        this.data = data;
    }
}
