package com.muse.xiangta.json;

import com.muse.xiangta.json.jsonmodle.VideoModel;

import java.util.List;

/**
 * Created by weipeng on 2018/3/2.
 */

public class JsonRequestGetShortVideoList extends JsonRequestBase {

    private List<VideoModel> list;

    public List<VideoModel> getList() {
        return list;
    }

    public void setList(List<VideoModel> list) {
        this.list = list;
    }
}
