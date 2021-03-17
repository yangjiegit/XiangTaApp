package com.muse.xiangta.json;

import com.alibaba.fastjson.JSON;
import com.muse.xiangta.json.jsonmodle.VideoModel;

import java.util.ArrayList;

/**
 * json回传数组
 */

public class JsonRequestsVideo {
    private int code;
    private String msg;
    private ArrayList<VideoModel> data;

    /**
     * 返回json解析
     * @param json json
     * @return JsonRequest<T>
     */
    public static JsonRequestsVideo getJsonObj(String json){
        return JSON.parseObject(json, JsonRequestsVideo.class);
    }

    public JsonRequestsVideo() {
        super();
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(ArrayList<VideoModel> data) {
        this.data = data;
    }

    public int getCode() {

        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<VideoModel> getData() {
        return data;
    }

    public JsonRequestsVideo(int code, String msg, ArrayList<VideoModel> data) {

        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
