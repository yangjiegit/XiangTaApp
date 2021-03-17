package com.muse.xiangta.modle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pay4Bean2 implements Serializable {

    /**
     * code : 1
     * msg :
     * pay : {"is_wap":0,"type":2,"pay_info":{"appid":"wx9d720ef784131129","noncestr":"844552226b7f545cc9384137bb81aa12","package":"Sign=WXPay","partnerid":"1605345991","prepayid":"wx101753011709294ae483b427953aef0000","timestamp":1615369981,"sign":"B3CF0BDBE791015CD96AD68805BEA6D9"}}
     */

    private int code;
    private String msg;
    /**
     * is_wap : 0
     * type : 2
     * pay_info : {"appid":"wx9d720ef784131129","noncestr":"844552226b7f545cc9384137bb81aa12","package":"Sign=WXPay","partnerid":"1605345991","prepayid":"wx101753011709294ae483b427953aef0000","timestamp":1615369981,"sign":"B3CF0BDBE791015CD96AD68805BEA6D9"}
     */

    private PayBean pay;

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

    public PayBean getPay() {
        return pay;
    }

    public void setPay(PayBean pay) {
        this.pay = pay;
    }

    public static class PayBean implements Serializable {
        private int is_wap;
        private int type;
        /**
         * appid : wx9d720ef784131129
         * noncestr : 844552226b7f545cc9384137bb81aa12
         * package : Sign=WXPay
         * partnerid : 1605345991
         * prepayid : wx101753011709294ae483b427953aef0000
         * timestamp : 1615369981
         * sign : B3CF0BDBE791015CD96AD68805BEA6D9
         */

        private PayInfoBean pay_info;

        public int getIs_wap() {
            return is_wap;
        }

        public void setIs_wap(int is_wap) {
            this.is_wap = is_wap;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public PayInfoBean getPay_info() {
            return pay_info;
        }

        public void setPay_info(PayInfoBean pay_info) {
            this.pay_info = pay_info;
        }

        public static class PayInfoBean implements Serializable {
            private String appid;
            private String noncestr;
            @SerializedName("package")
            private String packageX;
            private String partnerid;
            private String prepayid;
            private int timestamp;
            private String sign;

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public int getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(int timestamp) {
                this.timestamp = timestamp;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }
        }
    }
}
