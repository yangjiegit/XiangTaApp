package com.muse.xiangta.json;

import com.muse.xiangta.modle.HistoryModel;

import java.util.ArrayList;

public class JsonDoRequestGetSearchHistoryList extends JsonRequestBase{
    private ArrayList<HistoryModel> list = new ArrayList<>();

    public ArrayList<HistoryModel> getList() {
        return list;
    }

    public void setList(ArrayList<HistoryModel> list) {
        this.list = list;
    }
}
