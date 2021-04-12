package com.muse.xiangta.json;

/**
 * 猜拳
 */

public class JsonRequestDoPrivateSendGuessing extends JsonRequestBase {

    /**
     * send : {"from_msg":"送给你一个小幽灵","from_score":"你的经验值+200","to_ticket":200,"to_diamonds":"200","to_user_id":100115,"prop_icon":"http://p4ulgsz1p.bkt.clouddn.com/'admin/20180307/ca4bde3fff7cd169fec65ed16ff17dbe.png'","status":1,"prop_id":1,"total_ticket":0}
     */

    private SendBean send;

    public SendBean getSend() {
        return send;
    }

    public void setSend(SendBean send) {
        this.send = send;
    }

    public static class SendBean {
        private String gifimg; // 动态图
        private String staticimg; // 拳头点数

        public String getGifimg() {
            return gifimg;
        }

        public void setGifimg(String gifimg) {
            this.gifimg = gifimg;
        }

        public String getStaticimg() {
            return staticimg;
        }

        public void setStaticimg(String staticimg) {
            this.staticimg = staticimg;
        }
    }
}
