package com.muse.xiangta.modle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class NobleBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : {"nob_info":{"id":1,"title":"骑士","iamge":"http://xiangta.zzmzrj.com/mapi/public/upload/20210302/20210302183711D7fV-hrkkwei0552408.jpg","privilege":"1","1_month":"8.00","month_1":0,"3_month":"18.00","month_3":0,"12_month":"38.00","month_12":0,"createtime":0,"numb":1},"noble_privilege":[{"id":1,"title":"查看访客","image":"http://xiangta.zzmzrj.com/mapi/public/upload/e74c432e5ca1ab44ae37113ff77604f.png","greyimage":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","createtime":0,"tpic":"http://xiangta.zzmzrj.com/mapi/public/upload/e74c432e5ca1ab44ae37113ff77604f.png","tpizz":1},{"id":2,"title":"贵族专属礼物","image":"http://xiangta.zzmzrj.com/mapi/public/upload/e74c432e5ca1ab44ae37113ff77604f.png","greyimage":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","createtime":0,"tpic":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","tpizz":0},{"id":3,"title":"贵族勋章","image":"http://xiangta.zzmzrj.com/mapi/public/upload/e74c432e5ca1ab44ae37113ff77604f.png","greyimage":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","createtime":0,"tpic":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","tpizz":0},{"id":4,"title":"贵族资料卡","image":"http://xiangta.zzmzrj.com/mapi/public/upload/e74c432e5ca1ab44ae37113ff77604f.png","greyimage":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","createtime":0,"tpic":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","tpizz":0},{"id":5,"title":"畅通无阻","image":"http://xiangta.zzmzrj.com/mapi/public/upload/e74c432e5ca1ab44ae37113ff77604f.png","greyimage":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","createtime":0,"tpic":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","tpizz":0},{"id":6,"title":"开通广播","image":"http://xiangta.zzmzrj.com/mapi/public/upload/e74c432e5ca1ab44ae37113ff77604f.png","greyimage":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","createtime":0,"tpic":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","tpizz":0}],"cost":[{"month":1,"price":"8.00","diamonds":0},{"month":3,"price":"18.00","diamonds":0},{"month":12,"price":"38.00","diamonds":0}]}
     */

    private int code;
    private String msg;
    /**
     * nob_info : {"id":1,"title":"骑士","iamge":"http://xiangta.zzmzrj.com/mapi/public/upload/20210302/20210302183711D7fV-hrkkwei0552408.jpg","privilege":"1","1_month":"8.00","month_1":0,"3_month":"18.00","month_3":0,"12_month":"38.00","month_12":0,"createtime":0,"numb":1}
     * noble_privilege : [{"id":1,"title":"查看访客","image":"http://xiangta.zzmzrj.com/mapi/public/upload/e74c432e5ca1ab44ae37113ff77604f.png","greyimage":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","createtime":0,"tpic":"http://xiangta.zzmzrj.com/mapi/public/upload/e74c432e5ca1ab44ae37113ff77604f.png","tpizz":1},{"id":2,"title":"贵族专属礼物","image":"http://xiangta.zzmzrj.com/mapi/public/upload/e74c432e5ca1ab44ae37113ff77604f.png","greyimage":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","createtime":0,"tpic":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","tpizz":0},{"id":3,"title":"贵族勋章","image":"http://xiangta.zzmzrj.com/mapi/public/upload/e74c432e5ca1ab44ae37113ff77604f.png","greyimage":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","createtime":0,"tpic":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","tpizz":0},{"id":4,"title":"贵族资料卡","image":"http://xiangta.zzmzrj.com/mapi/public/upload/e74c432e5ca1ab44ae37113ff77604f.png","greyimage":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","createtime":0,"tpic":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","tpizz":0},{"id":5,"title":"畅通无阻","image":"http://xiangta.zzmzrj.com/mapi/public/upload/e74c432e5ca1ab44ae37113ff77604f.png","greyimage":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","createtime":0,"tpic":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","tpizz":0},{"id":6,"title":"开通广播","image":"http://xiangta.zzmzrj.com/mapi/public/upload/e74c432e5ca1ab44ae37113ff77604f.png","greyimage":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","createtime":0,"tpic":"http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png","tpizz":0}]
     * cost : [{"month":1,"price":"8.00","diamonds":0},{"month":3,"price":"18.00","diamonds":0},{"month":12,"price":"38.00","diamonds":0}]
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

    public static class DataBean implements Serializable{
        /**
         * id : 1
         * title : 骑士
         * iamge : http://xiangta.zzmzrj.com/mapi/public/upload/20210302/20210302183711D7fV-hrkkwei0552408.jpg
         * privilege : 1
         * 1_month : 8.00
         * month_1 : 0
         * 3_month : 18.00
         * month_3 : 0
         * 12_month : 38.00
         * month_12 : 0
         * createtime : 0
         * numb : 1
         */

        private NobInfoBean nob_info;
        /**
         * id : 1
         * title : 查看访客
         * image : http://xiangta.zzmzrj.com/mapi/public/upload/e74c432e5ca1ab44ae37113ff77604f.png
         * greyimage : http://xiangta.zzmzrj.com/mapi/public/upload/42b28b0f047678cde343027ad233d06.png
         * createtime : 0
         * tpic : http://xiangta.zzmzrj.com/mapi/public/upload/e74c432e5ca1ab44ae37113ff77604f.png
         * tpizz : 1
         */

        private List<NoblePrivilegeBean> noble_privilege;
        /**
         * month : 1
         * price : 8.00
         * diamonds : 0
         */

        private List<CostBean> cost;

        public NobInfoBean getNob_info() {
            return nob_info;
        }

        public void setNob_info(NobInfoBean nob_info) {
            this.nob_info = nob_info;
        }

        public List<NoblePrivilegeBean> getNoble_privilege() {
            return noble_privilege;
        }

        public void setNoble_privilege(List<NoblePrivilegeBean> noble_privilege) {
            this.noble_privilege = noble_privilege;
        }

        public List<CostBean> getCost() {
            return cost;
        }

        public void setCost(List<CostBean> cost) {
            this.cost = cost;
        }

        public static class NobInfoBean implements Serializable{
            private int id;
            private String title;
            private String iamge;
            private String privilege;
            @SerializedName("1_month")
            private String _$1_month;
            private int month_1;
            @SerializedName("3_month")
            private String _$3_month;
            private int month_3;
            @SerializedName("12_month")
            private String _$12_month;
            private int month_12;
            private int createtime;
            private int numb;

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

            public String getIamge() {
                return iamge;
            }

            public void setIamge(String iamge) {
                this.iamge = iamge;
            }

            public String getPrivilege() {
                return privilege;
            }

            public void setPrivilege(String privilege) {
                this.privilege = privilege;
            }

            public String get_$1_month() {
                return _$1_month;
            }

            public void set_$1_month(String _$1_month) {
                this._$1_month = _$1_month;
            }

            public int getMonth_1() {
                return month_1;
            }

            public void setMonth_1(int month_1) {
                this.month_1 = month_1;
            }

            public String get_$3_month() {
                return _$3_month;
            }

            public void set_$3_month(String _$3_month) {
                this._$3_month = _$3_month;
            }

            public int getMonth_3() {
                return month_3;
            }

            public void setMonth_3(int month_3) {
                this.month_3 = month_3;
            }

            public String get_$12_month() {
                return _$12_month;
            }

            public void set_$12_month(String _$12_month) {
                this._$12_month = _$12_month;
            }

            public int getMonth_12() {
                return month_12;
            }

            public void setMonth_12(int month_12) {
                this.month_12 = month_12;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public int getNumb() {
                return numb;
            }

            public void setNumb(int numb) {
                this.numb = numb;
            }
        }

        public static class NoblePrivilegeBean implements Serializable {
            private int id;
            private String title;
            private String image;
            private String greyimage;
            private int createtime;
            private String tpic;
            private int tpizz;

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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getGreyimage() {
                return greyimage;
            }

            public void setGreyimage(String greyimage) {
                this.greyimage = greyimage;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public String getTpic() {
                return tpic;
            }

            public void setTpic(String tpic) {
                this.tpic = tpic;
            }

            public int getTpizz() {
                return tpizz;
            }

            public void setTpizz(int tpizz) {
                this.tpizz = tpizz;
            }
        }

        public static class CostBean implements Serializable{
            private int month;
            private String price;
            private int diamonds;

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getDiamonds() {
                return diamonds;
            }

            public void setDiamonds(int diamonds) {
                this.diamonds = diamonds;
            }
        }
    }
}
