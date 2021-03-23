package com.muse.xiangta.modle;

import java.io.Serializable;
import java.util.List;

public class MyCarBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : {"classic":[[{"id":32,"order_id":"1616497309102597","car_id":2,"month":1,"money":"280.00","user_id":102597,"is_payment":1,"createtime":1616497309,"endtime":1619175709,"type":0,"pay_type_id":0,"status":0,"is_goldcoin":1,"addtime":1616497309,"title":"宝马","image":"http://xiangta.zzmzrj.com/mapi/public/upload/20210302/76639a26fb78155903b739fadaeec57c@3x.png","dongimage":""}]]}
     */

    private int code;
    private String msg;
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
        /**
         * id : 32
         * order_id : 1616497309102597
         * car_id : 2
         * month : 1
         * money : 280.00
         * user_id : 102597
         * is_payment : 1
         * createtime : 1616497309
         * endtime : 1619175709
         * type : 0
         * pay_type_id : 0
         * status : 0
         * is_goldcoin : 1
         * addtime : 1616497309
         * title : 宝马
         * image : http://xiangta.zzmzrj.com/mapi/public/upload/20210302/76639a26fb78155903b739fadaeec57c@3x.png
         * dongimage :
         */

        private List<List<ClassicBean>> classic;

        public List<List<ClassicBean>> getClassic() {
            return classic;
        }

        public void setClassic(List<List<ClassicBean>> classic) {
            this.classic = classic;
        }

        public static class ClassicBean implements Serializable {
            private int id;
            private String order_id;
            private int car_id;
            private int month;
            private String money;
            private int user_id;
            private int is_payment;
            private int createtime;
            private int endtime;
            private int type;
            private int pay_type_id;
            private int status;
            private int is_goldcoin;
            private int addtime;
            private String title;
            private String image;
            private String dongimage;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public int getCar_id() {
                return car_id;
            }

            public void setCar_id(int car_id) {
                this.car_id = car_id;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getIs_payment() {
                return is_payment;
            }

            public void setIs_payment(int is_payment) {
                this.is_payment = is_payment;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public int getEndtime() {
                return endtime;
            }

            public void setEndtime(int endtime) {
                this.endtime = endtime;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getPay_type_id() {
                return pay_type_id;
            }

            public void setPay_type_id(int pay_type_id) {
                this.pay_type_id = pay_type_id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getIs_goldcoin() {
                return is_goldcoin;
            }

            public void setIs_goldcoin(int is_goldcoin) {
                this.is_goldcoin = is_goldcoin;
            }

            public int getAddtime() {
                return addtime;
            }

            public void setAddtime(int addtime) {
                this.addtime = addtime;
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

            public String getDongimage() {
                return dongimage;
            }

            public void setDongimage(String dongimage) {
                this.dongimage = dongimage;
            }
        }
    }
}
