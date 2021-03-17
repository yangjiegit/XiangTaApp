package com.muse.xiangta.modle;

import com.muse.xiangta.json.JsonRequestBase;

public class Pay4Bean extends JsonRequestBase {

    /**
     * pay : {"is_wap":1,"type":2,"pay_info":{"version":"1.0","result_code":"0","return_msg":"https://pay.iisck.cn/alih5pay.jsp?data=OTg0Mjg0OTQsOTg0Mjg0OTQyMDUyMDE5MTIyNjEwMDY0NjgxMjgzNSwxNTAwLDg1NDIsMTU3NzMyNjAwNjEwMDQ3OCxodHRwOi8vbXVzZTEyLmNvbS9tYXBpL3B1YmxpYy9pbmRleC5waHAvYXBpL25vdGlmeV9hcGkvaGVuZ3l1bl9ub3RpZnk=","out_trade_no":"1577326006100478","total_amount":"1500","code_url":"","code_img_url":""}}
     */

    private PayBean pay;

    public PayBean getPay() {
        return pay;
    }

    public void setPay(PayBean pay) {
        this.pay = pay;
    }

    public static class PayBean {
        /**
         * is_wap : 1
         * type : 2
         * pay_info : {"version":"1.0","result_code":"0","return_msg":"https://pay.iisck.cn/alih5pay.jsp?data=OTg0Mjg0OTQsOTg0Mjg0OTQyMDUyMDE5MTIyNjEwMDY0NjgxMjgzNSwxNTAwLDg1NDIsMTU3NzMyNjAwNjEwMDQ3OCxodHRwOi8vbXVzZTEyLmNvbS9tYXBpL3B1YmxpYy9pbmRleC5waHAvYXBpL25vdGlmeV9hcGkvaGVuZ3l1bl9ub3RpZnk=","out_trade_no":"1577326006100478","total_amount":"1500","code_url":"","code_img_url":""}
         */

        private int is_wap;
        private int type;
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

        public static class PayInfoBean {
            /**
             * version : 1.0
             * result_code : 0
             * return_msg : https://pay.iisck.cn/alih5pay.jsp?data=OTg0Mjg0OTQsOTg0Mjg0OTQyMDUyMDE5MTIyNjEwMDY0NjgxMjgzNSwxNTAwLDg1NDIsMTU3NzMyNjAwNjEwMDQ3OCxodHRwOi8vbXVzZTEyLmNvbS9tYXBpL3B1YmxpYy9pbmRleC5waHAvYXBpL25vdGlmeV9hcGkvaGVuZ3l1bl9ub3RpZnk=
             * out_trade_no : 1577326006100478
             * total_amount : 1500
             * code_url :
             * code_img_url :
             */

            private String version;
            private String result_code;
            private String return_msg;
            private String out_trade_no;
            private String total_amount;
            private String code_url;
            private String code_img_url;

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getResult_code() {
                return result_code;
            }

            public void setResult_code(String result_code) {
                this.result_code = result_code;
            }

            public String getReturn_msg() {
                return return_msg;
            }

            public void setReturn_msg(String return_msg) {
                this.return_msg = return_msg;
            }

            public String getOut_trade_no() {
                return out_trade_no;
            }

            public void setOut_trade_no(String out_trade_no) {
                this.out_trade_no = out_trade_no;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }

            public String getCode_url() {
                return code_url;
            }

            public void setCode_url(String code_url) {
                this.code_url = code_url;
            }

            public String getCode_img_url() {
                return code_img_url;
            }

            public void setCode_img_url(String code_img_url) {
                this.code_img_url = code_img_url;
            }
        }
    }
}
