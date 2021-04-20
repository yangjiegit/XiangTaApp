package com.muse.xiangta.json;

/**
 * 特效
 */

public class JsonRequestDoPrivateSendSpecialEffects extends JsonRequestBase {

    /**
     *
     */

    private SendBean send;

    public SendBean getSend() {
        return send;
    }

    public void setSend(SendBean send) {
        this.send = send;
    }

    public static class SendBean {
        private String to_user_id; //
        private String to_msg; //
        private String prop_icon; //
        private String selfcar; //
        private String groupID; //

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
