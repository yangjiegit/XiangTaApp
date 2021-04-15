package com.muse.xiangta.modle;

import java.io.Serializable;
import java.util.List;

public class AristocraticPrivilegeBean implements Serializable {

    /**
     * code : 1
     * msg :
     * data : {"noble":{"conceal_location":{"id":1,"code":"conceal_location","title":"隐藏位置","val":0,"desc":"","status":0,"group":"noble","uid":0},"conceal_visit":{"id":2,"code":"conceal_visit","title":"隐藏访问，不留访客记录","val":0,"desc":"","status":0,"group":"noble","uid":0},"conceal_enter":{"id":3,"code":"conceal_enter","title":"进场隐身","val":0,"desc":"","status":0,"group":"noble","uid":0},"conceal_protector":{"id":4,"code":"conceal_protector","title":"守护者隐身","val":0,"desc":"","status":0,"group":"noble","uid":0}},"charge_rule":{"voice_chat":[0,10,20,30,40],"video_chat":[0,20,40,60,80],"message_chat":[0,5,10,15,20]}}
     */

    private int code;
    private String msg;
    /**
     * noble : {"conceal_location":{"id":1,"code":"conceal_location","title":"隐藏位置","val":0,"desc":"","status":0,"group":"noble","uid":0},"conceal_visit":{"id":2,"code":"conceal_visit","title":"隐藏访问，不留访客记录","val":0,"desc":"","status":0,"group":"noble","uid":0},"conceal_enter":{"id":3,"code":"conceal_enter","title":"进场隐身","val":0,"desc":"","status":0,"group":"noble","uid":0},"conceal_protector":{"id":4,"code":"conceal_protector","title":"守护者隐身","val":0,"desc":"","status":0,"group":"noble","uid":0}}
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
         * conceal_location : {"id":1,"code":"conceal_location","title":"隐藏位置","val":0,"desc":"","status":0,"group":"noble","uid":0}
         * conceal_visit : {"id":2,"code":"conceal_visit","title":"隐藏访问，不留访客记录","val":0,"desc":"","status":0,"group":"noble","uid":0}
         * conceal_enter : {"id":3,"code":"conceal_enter","title":"进场隐身","val":0,"desc":"","status":0,"group":"noble","uid":0}
         * conceal_protector : {"id":4,"code":"conceal_protector","title":"守护者隐身","val":0,"desc":"","status":0,"group":"noble","uid":0}
         */

        private NobleBean noble;
        private ChargeRuleBean charge_rule;

        public NobleBean getNoble() {
            return noble;
        }

        public void setNoble(NobleBean noble) {
            this.noble = noble;
        }

        public ChargeRuleBean getCharge_rule() {
            return charge_rule;
        }

        public void setCharge_rule(ChargeRuleBean charge_rule) {
            this.charge_rule = charge_rule;
        }

        public static class NobleBean implements Serializable{
            /**
             * id : 1
             * code : conceal_location
             * title : 隐藏位置
             * val : 0
             * desc :
             * status : 0
             * group : noble
             * uid : 0
             */

            private ConcealLocationBean conceal_location;
            /**
             * id : 2
             * code : conceal_visit
             * title : 隐藏访问，不留访客记录
             * val : 0
             * desc :
             * status : 0
             * group : noble
             * uid : 0
             */

            private ConcealVisitBean conceal_visit;
            /**
             * id : 3
             * code : conceal_enter
             * title : 进场隐身
             * val : 0
             * desc :
             * status : 0
             * group : noble
             * uid : 0
             */

            private ConcealEnterBean conceal_enter;
            /**
             * id : 4
             * code : conceal_protector
             * title : 守护者隐身
             * val : 0
             * desc :
             * status : 0
             * group : noble
             * uid : 0
             */

            private ConcealProtectorBean conceal_protector;

            public ConcealLocationBean getConceal_location() {
                return conceal_location;
            }

            public void setConceal_location(ConcealLocationBean conceal_location) {
                this.conceal_location = conceal_location;
            }

            public ConcealVisitBean getConceal_visit() {
                return conceal_visit;
            }

            public void setConceal_visit(ConcealVisitBean conceal_visit) {
                this.conceal_visit = conceal_visit;
            }

            public ConcealEnterBean getConceal_enter() {
                return conceal_enter;
            }

            public void setConceal_enter(ConcealEnterBean conceal_enter) {
                this.conceal_enter = conceal_enter;
            }

            public ConcealProtectorBean getConceal_protector() {
                return conceal_protector;
            }

            public void setConceal_protector(ConcealProtectorBean conceal_protector) {
                this.conceal_protector = conceal_protector;
            }

            public static class ConcealLocationBean implements Serializable{
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

            public static class ConcealVisitBean implements Serializable{
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

            public static class ConcealEnterBean implements Serializable{
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

            public static class ConcealProtectorBean implements Serializable{
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

        public static class ChargeRuleBean implements Serializable {
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
