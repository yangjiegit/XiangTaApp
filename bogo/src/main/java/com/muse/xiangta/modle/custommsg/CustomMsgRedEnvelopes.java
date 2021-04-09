package com.muse.xiangta.modle.custommsg;

import com.muse.xiangta.LiveConstant;
import com.muse.xiangta.json.JsonRequestDoPrivateSendRedEnvelopes;

/**
 * 红包消息
 */
public class CustomMsgRedEnvelopes extends CustomMsg {

    private String rptit; // 标题
    private String rppnum; // 红包数量
    private String rpnum; // 红包金额
    private String rptype; // 红包类型
    private String rpID; // 红包ID

    public CustomMsgRedEnvelopes() {
        super();
        setType(LiveConstant.CustomMsgType.MSG_ALL_RED_ENVELOPES);
    }

    public void fillData(JsonRequestDoPrivateSendRedEnvelopes.SendBean model) {
        if (model != null) {
            rptit = model.getRptit();
            rppnum = model.getRppnum();
            rpnum = model.getRpnum();
            rptype = model.getRptype();
            rpID = model.getRpID();
        }
    }

    public String getRptit() {
        return rptit;
    }

    public void setRptit(String rptit) {
        this.rptit = rptit;
    }

    public String getRppnum() {
        return rppnum;
    }

    public void setRppnum(String rppnum) {
        this.rppnum = rppnum;
    }

    public String getRpnum() {
        return rpnum;
    }

    public void setRpnum(String rpnum) {
        this.rpnum = rpnum;
    }

    public String getRptype() {
        return rptype;
    }

    public void setRptype(String rptype) {
        this.rptype = rptype;
    }

    public String getRpID() {
        return rpID;
    }

    public void setRpID(String rpID) {
        this.rpID = rpID;
    }

    @Override
    public String getConversationDesc() {
        return "[红包]";
    }

}
