package com.muse.xiangta.modle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PrivacySettingsBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : {"private":{"conceal_wealth_charm_thumbs_up":{"id":5,"code":"conceal_wealth_charm_thumbs_up","title":"隐藏自己的财富、魅力等级和获赞数","val":0,"desc":"","status":0,"group":"private","uid":0},"conceal_gifts":{"id":6,"code":"conceal_gifts","title":"隐藏自己的礼物","val":0,"desc":"","status":0,"group":"private","uid":0},"conceal_my_protector":{"id":7,"code":"conceal_my_protector","title":"隐藏自己的守护","val":0,"desc":"","status":0,"group":"private","uid":0}},"charge_rule":{"voice_chat":[0,10,20,30,40],"video_chat":[0,20,40,60,80],"message_chat":[0,5,10,15,20]}}
     */

    private int code;
    private String msg;
    /**
     * private : {"conceal_wealth_charm_thumbs_up":{"id":5,"code":"conceal_wealth_charm_thumbs_up","title":"隐藏自己的财富、魅力等级和获赞数","val":0,"desc":"","status":0,"group":"private","uid":0},"conceal_gifts":{"id":6,"code":"conceal_gifts","title":"隐藏自己的礼物","val":0,"desc":"","status":0,"group":"private","uid":0},"conceal_my_protector":{"id":7,"code":"conceal_my_protector","title":"隐藏自己的守护","val":0,"desc":"","status":0,"group":"private","uid":0}}
     * charge_rule : {"voice_chat":[0,10,20,30,40],"video_chat":[0,20,40,60,80],"message_chat":[0,5,10,15,20]}
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
         * conceal_wealth_charm_thumbs_up : {"id":5,"code":"conceal_wealth_charm_thumbs_up","title":"隐藏自己的财富、魅力等级和获赞数","val":0,"desc":"","status":0,"group":"private","uid":0}
         * conceal_gifts : {"id":6,"code":"conceal_gifts","title":"隐藏自己的礼物","val":0,"desc":"","status":0,"group":"private","uid":0}
         * conceal_my_protector : {"id":7,"code":"conceal_my_protector","title":"隐藏自己的守护","val":0,"desc":"","status":0,"group":"private","uid":0}
         */

        @SerializedName("private")
        private PrivateBean privateX;
        private ChargeRuleBean charge_rule;

        public PrivateBean getPrivateX() {
            return privateX;
        }

        public void setPrivateX(PrivateBean privateX) {
            this.privateX = privateX;
        }

        public ChargeRuleBean getCharge_rule() {
            return charge_rule;
        }

        public void setCharge_rule(ChargeRuleBean charge_rule) {
            this.charge_rule = charge_rule;
        }

        public static class PrivateBean implements Serializable{
            /**
             * id : 5
             * code : conceal_wealth_charm_thumbs_up
             * title : 隐藏自己的财富、魅力等级和获赞数
             * val : 0
             * desc :
             * status : 0
             * group : private
             * uid : 0
             */

            private ConcealWealthCharmThumbsUpBean conceal_wealth_charm_thumbs_up;
            /**
             * id : 6
             * code : conceal_gifts
             * title : 隐藏自己的礼物
             * val : 0
             * desc :
             * status : 0
             * group : private
             * uid : 0
             */

            private ConcealGiftsBean conceal_gifts;
            /**
             * id : 7
             * code : conceal_my_protector
             * title : 隐藏自己的守护
             * val : 0
             * desc :
             * status : 0
             * group : private
             * uid : 0
             */

            private ConcealMyProtectorBean conceal_my_protector;

            public ConcealWealthCharmThumbsUpBean getConceal_wealth_charm_thumbs_up() {
                return conceal_wealth_charm_thumbs_up;
            }

            public void setConceal_wealth_charm_thumbs_up(ConcealWealthCharmThumbsUpBean conceal_wealth_charm_thumbs_up) {
                this.conceal_wealth_charm_thumbs_up = conceal_wealth_charm_thumbs_up;
            }

            public ConcealGiftsBean getConceal_gifts() {
                return conceal_gifts;
            }

            public void setConceal_gifts(ConcealGiftsBean conceal_gifts) {
                this.conceal_gifts = conceal_gifts;
            }

            public ConcealMyProtectorBean getConceal_my_protector() {
                return conceal_my_protector;
            }

            public void setConceal_my_protector(ConcealMyProtectorBean conceal_my_protector) {
                this.conceal_my_protector = conceal_my_protector;
            }

            public static class ConcealWealthCharmThumbsUpBean implements Serializable{
                private int id;
                private String code;
                private String title;
                private int val;
                private String desc;
                private int status;
                private String group;
                private int uid;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getVal() {
                    return val;
                }

                public void setVal(int val) {
                    this.val = val;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getGroup() {
                    return group;
                }

                public void setGroup(String group) {
                    this.group = group;
                }

                public int getUid() {
                    return uid;
                }

                public void setUid(int uid) {
                    this.uid = uid;
                }
            }

            public static class ConcealGiftsBean implements Serializable{
                private int id;
                private String code;
                private String title;
                private int val;
                private String desc;
                private int status;
                private String group;
                private int uid;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getVal() {
                    return val;
                }

                public void setVal(int val) {
                    this.val = val;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getGroup() {
                    return group;
                }

                public void setGroup(String group) {
                    this.group = group;
                }

                public int getUid() {
                    return uid;
                }

                public void setUid(int uid) {
                    this.uid = uid;
                }
            }

            public static class ConcealMyProtectorBean implements Serializable{
                private int id;
                private String code;
                private String title;
                private int val;
                private String desc;
                private int status;
                private String group;
                private int uid;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getVal() {
                    return val;
                }

                public void setVal(int val) {
                    this.val = val;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getGroup() {
                    return group;
                }

                public void setGroup(String group) {
                    this.group = group;
                }

                public int getUid() {
                    return uid;
                }

                public void setUid(int uid) {
                    this.uid = uid;
                }
            }
        }

        public static class ChargeRuleBean implements Serializable{
            private List<Integer> voice_chat;
            private List<Integer> video_chat;
            private List<Integer> message_chat;

            public List<Integer> getVoice_chat() {
                return voice_chat;
            }

            public void setVoice_chat(List<Integer> voice_chat) {
                this.voice_chat = voice_chat;
            }

            public List<Integer> getVideo_chat() {
                return video_chat;
            }

            public void setVideo_chat(List<Integer> video_chat) {
                this.video_chat = video_chat;
            }

            public List<Integer> getMessage_chat() {
                return message_chat;
            }

            public void setMessage_chat(List<Integer> message_chat) {
                this.message_chat = message_chat;
            }
        }
    }
}
