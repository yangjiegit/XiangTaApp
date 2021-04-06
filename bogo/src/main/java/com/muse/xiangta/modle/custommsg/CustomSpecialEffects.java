package com.muse.xiangta.modle.custommsg;

public class CustomSpecialEffects extends CustomMsg {

    //    @{@"to_user_id":curUser.id,
    //    @"to_msg":curUser.user_nickname,
    //    @"prop_icon":curUser.avatar,
    //    @"selfcar":curUser.car,
    //    @"groupID":group.groupId}
//    private VipInfoBean vip_info;
//
//    public VipInfoBean getVip_info() {
//        return vip_info;
//    }
//
//    public void setVip_info(VipInfoBean vip_info) {
//        this.vip_info = vip_info;
//    }
//
//    public static class VipInfoBean {
//        private String send_msg;
//
//        public String getSend_msg() {
//            return send_msg;
//        }
//
//        public void setSend_msg(String send_msg) {
//            this.send_msg = send_msg;
//        }
//    }
    private String to_user_id;
    private String to_msg;
    private String prop_icon;
    private String selfcar;
    private String groupID;

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
