package com.muse.xiangta.modle;

import java.io.Serializable;
import java.util.List;

public class GoldcoinLogBean implements Serializable {

    /**
     * code : 1
     * msg : 金币兑换记录
     * data : {"count":2,"total_page":1,"list":[{"id":53,"title":"现金兑换","memo":"20元人民币","need_money":"-210","type":0,"addtime":"2021.03.23 18:15"},{"id":52,"title":"现金兑换","memo":"5元（仅限首次）","need_money":"-50","type":0,"addtime":"2021.03.23 18:13"}]}
     */

    private int code;
    private String msg;
    /**
     * count : 2
     * total_page : 1
     * list : [{"id":53,"title":"现金兑换","memo":"20元人民币","need_money":"-210","type":0,"addtime":"2021.03.23 18:15"},{"id":52,"title":"现金兑换","memo":"5元（仅限首次）","need_money":"-50","type":0,"addtime":"2021.03.23 18:13"}]
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private int count;
        private int total_page;
        /**
         * id : 53
         * title : 现金兑换
         * memo : 20元人民币
         * need_money : -210
         * type : 0
         * addtime : 2021.03.23 18:15
         */

        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getTotal_page() {
            return total_page;
        }

        public void setTotal_page(int total_page) {
            this.total_page = total_page;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            private int id;
            private String title;
            private String memo;
            private String need_money;
            private int type;
            private String addtime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getNeed_money() {
                return need_money;
            }

            public void setNeed_money(String need_money) {
                this.need_money = need_money;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }
        }
    }
}
