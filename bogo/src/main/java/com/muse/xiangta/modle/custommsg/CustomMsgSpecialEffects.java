package com.muse.xiangta.modle.custommsg;

import com.muse.xiangta.LiveConstant;
import com.muse.xiangta.json.JsonRequestDoPrivateSendRedEnvelopes;
import com.muse.xiangta.json.JsonRequestDoPrivateSendSpecialEffects;

/**
 * 进场特效
 */
public class CustomMsgSpecialEffects extends CustomMsg {

    private String to_user_id; // 标题
    private String to_msg; // 红包数量
    private String prop_icon; // 红包金额
    private String selfcar; // 红包类型
    private String groupID; // 红包ID

    public CustomMsgSpecialEffects() {
        super();
        setType(LiveConstant.CustomMsgType.MSG_APPROACH);
    }

    public void fillData(JsonRequestDoPrivateSendSpecialEffects.SendBean model) {
        if (model != null) {
            to_user_id = model.getTo_user_id();
            to_msg = model.getTo_msg();
            prop_icon = model.getProp_icon();
            selfcar = model.getSelfcar();
            groupID = model.getGroupID();
        }
    }

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

    @Override
    public String getConversationDesc() {
        return "[全局提示消息]";
    }

}
