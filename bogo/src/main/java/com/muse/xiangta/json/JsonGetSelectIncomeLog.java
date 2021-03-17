package com.muse.xiangta.json;

import com.muse.xiangta.modle.SelectIncomeLogModel;

import java.util.List;

public class JsonGetSelectIncomeLog extends JsonRequestBase {
    private List<SelectIncomeLogModel> list;

    public List<SelectIncomeLogModel> getList() {
        return list;
    }

    public void setList(List<SelectIncomeLogModel> list) {
        this.list = list;
    }
}
