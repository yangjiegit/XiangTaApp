package com.muse.xiangta.json;

/**
 * 特效
 */

public class JsonRequestDoPrivateSendSpecialEffects extends JsonRequestBase {

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
        private String to_user_id; // 标题
        private String to_msg; // 红包数量
        private String prop_icon; // 红包金额
        private String selfcar; // 红包类型
        private String groupID; // 红包ID

        public String getTo_user_id() {
            return to_user_id;
        }

        public void setTo_user_id(String to_user_id) {
            this.to_user_id = to_user_id;
        }

        public String getTo_msg() {
            return to_msg;
        }

        public void setTo_msg(String to_msg) {
            this.to_msg = to_msg;
        }

        public String getProp_icon() {
            return prop_icon;
        }

        public void setProp_icon(String prop_icon) {
            this.prop_icon = prop_icon;
        }

        public String getSelfcar() {
            return selfcar;
        }

        public void setSelfcar(String selfcar) {
            this.selfcar = selfcar;
        }

        public String getGroupID() {
            return groupID;
        }

        public void setGroupID(String groupID) {
            this.groupID = groupID;
        }
    }
}
