package com.muse.xiangta.json;

import com.alibaba.fastjson.JSON;
import com.muse.xiangta.json.jsonmodle.TargetUserData;
import com.muse.xiangta.json.jsonmodle.TargetUserData2;

/**
 * json返回解析类型TargetUserData
 * Created by weipeng on 2018/1/26 0026.
 */
public class JsonRequestTarget2 {
    private int code;
    private String msg;
    private TargetUserData2 data;

    /**
     * 返回json解析
     * @param json json
     * @return JsonRequest<T>
     */
    public static JsonRequestTarget2 getJsonObj(String json){
        return JSON.parseObject(json, JsonRequestTarget2.class);
    }

    public JsonRequestTarget2() {
        super();
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(TargetUserData2 data) {
        this.data = data;
    }

    public int getCode() {

        return code;
    }

    public String getMsg() {
        return msg;
    }

    public TargetUserData2 getData() {
        return data;
    }

    public JsonRequestTarget2(int code, String msg, TargetUserData2 data) {

        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
