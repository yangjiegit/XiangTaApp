package com.muse.xiangta.json;

import com.muse.xiangta.modle.CuckooUserEvaluateListModel;

import java.util.List;

public class JsonDoRequestGetEvaluateList extends JsonRequestBase{
    private List<CuckooUserEvaluateListModel> evaluate_list;

    public List<CuckooUserEvaluateListModel> getEvaluate_list() {
        return evaluate_list;
    }

    public void setEvaluate_list(List<CuckooUserEvaluateListModel> evaluate_list) {
        this.evaluate_list = evaluate_list;
    }
}
