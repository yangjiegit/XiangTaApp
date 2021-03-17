package com.muse.xiangta.modle;

import java.io.Serializable;
import java.util.List;

public class CarListBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : {"classic":[[{"id":1,"type":0,"title":"哈雷摩托","image":"http://xiangta.zzmzrj.com/mapi/public/upload/20210302/ff7ea9e71a5d15c103ffb369b2bc80e9@3x.png","dongimage":"0","month_1":"8.00","month_3":"18.00","month_12":"38.00","createtime":0},{"id":2,"type":0,"title":"宝马","image":"http://xiangta.zzmzrj.com/mapi/public/upload/20210302/76639a26fb78155903b739fadaeec57c@3x.png","dongimage":"0","month_1":"18.00","month_3":"38.00","month_12":"88.00","createtime":0}]],"luxury":[[{"id":3,"type":1,"title":"兰博基尼","image":"http://xiangta.zzmzrj.com/mapi/public/upload/20210302/08bf1d2031d14f5a761ce78a35cb3e65@3x.png","dongimage":"0","month_1":"68.00","month_3":"128.00","month_12":"298.00","createtime":0},{"id":4,"type":1,"title":"布加迪","image":"http://xiangta.zzmzrj.com/mapi/public/upload/20210302/08bf1d2031d14f5a761ce78a35cb3e65@3x.png","dongimage":"0","month_1":"158.00","month_3":"338.00","month_12":"688.00","createtime":0}],[{"id":5,"type":1,"title":"劳斯莱斯","image":"http://xiangta.zzmzrj.com/mapi/public/upload/20210302/08bf1d2031d14f5a761ce78a35cb3e65@3x.png","dongimage":"0","month_1":"388.00","month_3":"788.00","month_12":"1688.00","createtime":0}]]}
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
         * id : 1
         * type : 0
         * title : 哈雷摩托
         * image : http://xiangta.zzmzrj.com/mapi/public/upload/20210302/ff7ea9e71a5d15c103ffb369b2bc80e9@3x.png
         * dongimage : 0
         * month_1 : 8.00
         * month_3 : 18.00
         * month_12 : 38.00
         * createtime : 0
         */

        private List<List<ClassicBean>> classic;
        /**
         * id : 3
         * type : 1
         * title : 兰博基尼
         * image : http://xiangta.zzmzrj.com/mapi/public/upload/20210302/08bf1d2031d14f5a761ce78a35cb3e65@3x.png
         * dongimage : 0
         * month_1 : 68.00
         * month_3 : 128.00
         * month_12 : 298.00
         * createtime : 0
         */

        private List<List<LuxuryBean>> luxury;

        public List<List<ClassicBean>> getClassic() {
            return classic;
        }

        public void setClassic(List<List<ClassicBean>> classic) {
            this.classic = classic;
        }

        public List<List<LuxuryBean>> getLuxury() {
            return luxury;
        }

        public void setLuxury(List<List<LuxuryBean>> luxury) {
            this.luxury = luxury;
        }

        public static class ClassicBean implements Serializable{
            private int id;
            private int type;
            private String title;
            private String image;
            private String dongimage;
            private String month_1;
            private String month_3;
            private String month_12;
            private int createtime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
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

            public String getMonth_1() {
                return month_1;
            }

            public void setMonth_1(String month_1) {
                this.month_1 = month_1;
            }

            public String getMonth_3() {
                return month_3;
            }

            public void setMonth_3(String month_3) {
                this.month_3 = month_3;
            }

            public String getMonth_12() {
                return month_12;
            }

            public void setMonth_12(String month_12) {
                this.month_12 = month_12;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }
        }

        public static class LuxuryBean implements Serializable{
            private int id;
            private int type;
            private String title;
            private String image;
            private String dongimage;
            private String month_1;
            private String month_3;
            private String month_12;
            private int createtime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
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

            public String getMonth_1() {
                return month_1;
            }

            public void setMonth_1(String month_1) {
                this.month_1 = month_1;
            }

            public String getMonth_3() {
                return month_3;
            }

            public void setMonth_3(String month_3) {
                this.month_3 = month_3;
            }

            public String getMonth_12() {
                return month_12;
            }

            public void setMonth_12(String month_12) {
                this.month_12 = month_12;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }
        }
    }
}
