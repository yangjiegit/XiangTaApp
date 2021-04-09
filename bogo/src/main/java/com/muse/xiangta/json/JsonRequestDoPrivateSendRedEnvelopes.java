package com.muse.xiangta.json;

/**
 * Created by 魏鹏 on 2018/3/11.
 * email:1403102936@qq.com
 * 山东布谷鸟网络科技有限公司著
 */

public class JsonRequestDoPrivateSendRedEnvelopes extends JsonRequestBase {

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
        private String rptit; // 标题
        private String rppnum; // 红包数量
        private String rpnum; // 红包金额
        private String rptype; // 红包类型
        private String rpID; // 红包ID

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
    }
}
