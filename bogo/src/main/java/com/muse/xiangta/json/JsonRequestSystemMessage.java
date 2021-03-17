package com.muse.xiangta.json;

import com.muse.xiangta.modle.SystemMessageModel;

import java.util.List;

/**
 * Created by 魏鹏 on 2018/3/19.
 * email:1403102936@qq.com
 * 山东布谷鸟网络科技有限公司著
 */

public class JsonRequestSystemMessage extends JsonRequestBase {

    private List<SystemMessageModel> list;

    public List<SystemMessageModel> getList() {
        return list;
    }

    public void setList(List<SystemMessageModel> list) {
        this.list = list;
    }
}
