package com.muse.xiangta.json;

import java.io.Serializable;

public class JsonDoRequestGetOssInfo2 implements Serializable {

    /**
     * code : 1
     * msg :
     * bucket : xiangta
     * domain : http://xta.zzmzrj.com
     * token : k_ToV90Qba7-FVIE44gWy9FEN6I9jcvKlj0Sps6k:q-PuOX6lmdkT-w-QZKGBAi7vuZw=:eyJzY29wZSI6InhpYW5ndGEiLCJkZWFkbGluZSI6MTYxNTU0MjIxN30=
     */

    private int code;
    private String msg;
    private String bucket;
    private String domain;
    private String token;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
