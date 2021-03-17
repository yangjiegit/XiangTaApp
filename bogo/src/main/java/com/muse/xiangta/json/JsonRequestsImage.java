package com.muse.xiangta.json;

import com.alibaba.fastjson.JSON;
import com.muse.xiangta.modle.BannerImgBean;

import java.util.List;

/**
 * json回传数组
 * Created by jiahengfei on 2018/1/26 0026.
 */

public class JsonRequestsImage {
    private int code;
    private String msg;
    private List<BannerImgBean> data;

    /**
     * 返回json解析
     * @param json json
     * @return JsonRequest<T>
     */
    public static JsonRequestsImage getJsonObj(String json){
        return JSON.parseObject(json, JsonRequestsImage.class);
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<BannerImgBean> data) {
        this.data = data;
    }

    public int getCode() {

        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<BannerImgBean> getData() {
        return data;
    }

    public JsonRequestsImage() {
    }

    public JsonRequestsImage(int code, String msg, List<BannerImgBean> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
