package com.muse.xiangta.modle;

import java.io.Serializable;
import java.util.List;

public class GroupChatBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : [{"id":1,"groupid":"@TGS#2D4K7XAHZ","name":"天上人间","pic":"http://xiangta.zzmzrj.com/public/image/aafc9bee32b68e3c71d04802fb2496d.jpg","memberNum":3},{"id":2,"groupid":"@TGS#2PUK7XAHI","name":"一起脱单","pic":"http://xiangta.zzmzrj.com/public/image/ad5dfb143107cc2ee00127a91332b6a.jpg","memberNum":3},{"id":3,"groupid":"@TGS#2QGH7XAHX","name":"恋爱星球","pic":"http://xiangta.zzmzrj.com/public/image/b193f3ab86e06b7c28c93bf0e32cf7e.jpg","memberNum":4},{"id":4,"groupid":"@TGS#3KGIVYAHU","name":"Hello World!","pic":"http://xiangta.zzmzrj.com/public/image/aafc9bee32b68e3c71d04802fb2496d.jpg","memberNum":3}]
     */

    private int code;
    private String msg;
    /**
     * id : 1
     * groupid : @TGS#2D4K7XAHZ
     * name : 天上人间
     * pic : http://xiangta.zzmzrj.com/public/image/aafc9bee32b68e3c71d04802fb2496d.jpg
     * memberNum : 3
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private int id;
        private String groupid;
        private String name;
        private String pic;
        private int memberNum;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGroupid() {
            return groupid;
        }

        public void setGroupid(String groupid) {
            this.groupid = groupid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getMemberNum() {
            return memberNum;
        }

        public void setMemberNum(int memberNum) {
            this.memberNum = memberNum;
        }
    }
}
