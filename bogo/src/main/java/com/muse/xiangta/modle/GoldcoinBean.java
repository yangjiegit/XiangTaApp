package com.muse.xiangta.modle;

import java.io.Serializable;
import java.util.List;

public class GoldcoinBean implements Serializable {

    /**
     * code : 1
     * msg : 金币兑换产品
     * data : {"income":111000049,"list":[{"id":1,"related_id":0,"name":"5元（仅限首次）","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":50,"type":0,"need_money_title":"50金币"},{"id":2,"related_id":0,"name":"20元人民币","image":"http://xta.zzmzrj.com/admin/20210320/9715fac693af08bf63c8194361275daf.png","need_money":210,"type":0,"need_money_title":"210金币"},{"id":3,"related_id":0,"name":"100元人民币","image":"http://xta.zzmzrj.com/admin/20210320/5c27ef416e0836541da68506fc1fd5b9.png","need_money":1030,"type":0,"need_money_title":"1030金币"},{"id":4,"related_id":0,"name":"25钻石","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":15,"type":1,"need_money_title":"15金币"},{"id":5,"related_id":0,"name":"100钻石","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":60,"type":1,"need_money_title":"60金币"},{"id":6,"related_id":0,"name":"500钻石","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":300,"type":1,"need_money_title":"300金币"},{"id":7,"related_id":3,"name":"1个月伯爵贵族","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":980,"type":2,"need_money_title":"980金币"},{"id":8,"related_id":4,"name":"1个月侯爵贵族","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":2380,"type":2,"need_money_title":"2380金币"},{"id":9,"related_id":5,"name":"1个月公爵贵族","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":5880,"type":2,"need_money_title":"5880金币"},{"id":10,"related_id":6,"name":"1个月国王贵族","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":25980,"type":2,"need_money_title":"25980金币"},{"id":11,"related_id":0,"name":"1个月哈雷摩托","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":120,"type":3,"need_money_title":"120金币"},{"id":12,"related_id":2,"name":"1个月宝马","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":280,"type":3,"need_money_title":"280金币"},{"id":13,"related_id":3,"name":"1个月兰博基尼","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":980,"type":3,"need_money_title":"980金币"},{"id":14,"related_id":4,"name":"1个月布加迪威龙","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":2380,"type":3,"need_money_title":"2380金币"},{"id":15,"related_id":4,"name":"1个月布加迪威龙","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":2380,"type":3,"need_money_title":"2380金币"}]}
     */

    private int code;
    private String msg;
    /**
     * income : 111000049
     * list : [{"id":1,"related_id":0,"name":"5元（仅限首次）","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":50,"type":0,"need_money_title":"50金币"},{"id":2,"related_id":0,"name":"20元人民币","image":"http://xta.zzmzrj.com/admin/20210320/9715fac693af08bf63c8194361275daf.png","need_money":210,"type":0,"need_money_title":"210金币"},{"id":3,"related_id":0,"name":"100元人民币","image":"http://xta.zzmzrj.com/admin/20210320/5c27ef416e0836541da68506fc1fd5b9.png","need_money":1030,"type":0,"need_money_title":"1030金币"},{"id":4,"related_id":0,"name":"25钻石","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":15,"type":1,"need_money_title":"15金币"},{"id":5,"related_id":0,"name":"100钻石","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":60,"type":1,"need_money_title":"60金币"},{"id":6,"related_id":0,"name":"500钻石","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":300,"type":1,"need_money_title":"300金币"},{"id":7,"related_id":3,"name":"1个月伯爵贵族","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":980,"type":2,"need_money_title":"980金币"},{"id":8,"related_id":4,"name":"1个月侯爵贵族","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":2380,"type":2,"need_money_title":"2380金币"},{"id":9,"related_id":5,"name":"1个月公爵贵族","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":5880,"type":2,"need_money_title":"5880金币"},{"id":10,"related_id":6,"name":"1个月国王贵族","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":25980,"type":2,"need_money_title":"25980金币"},{"id":11,"related_id":0,"name":"1个月哈雷摩托","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":120,"type":3,"need_money_title":"120金币"},{"id":12,"related_id":2,"name":"1个月宝马","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":280,"type":3,"need_money_title":"280金币"},{"id":13,"related_id":3,"name":"1个月兰博基尼","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":980,"type":3,"need_money_title":"980金币"},{"id":14,"related_id":4,"name":"1个月布加迪威龙","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":2380,"type":3,"need_money_title":"2380金币"},{"id":15,"related_id":4,"name":"1个月布加迪威龙","image":"http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png","need_money":2380,"type":3,"need_money_title":"2380金币"}]
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
        private int income;
        /**
         * id : 1
         * related_id : 0
         * name : 5元（仅限首次）
         * image : http://xta.zzmzrj.com/admin/20210319/fe49e16880ff966978ad34dfa0526d16.png
         * need_money : 50
         * type : 0
         * need_money_title : 50金币
         */

        private List<ListBean> list;

        public int getIncome() {
            return income;
        }

        public void setIncome(int income) {
            this.income = income;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            private int id;
            private int related_id;
            private String name;
            private String image;
            private int need_money;
            private int type;
            private String need_money_title;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getRelated_id() {
                return related_id;
            }

            public void setRelated_id(int related_id) {
                this.related_id = related_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getNeed_money() {
                return need_money;
            }

            public void setNeed_money(int need_money) {
                this.need_money = need_money;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getNeed_money_title() {
                return need_money_title;
            }

            public void setNeed_money_title(String need_money_title) {
                this.need_money_title = need_money_title;
            }
        }
    }
}
