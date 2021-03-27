package com.muse.xiangta.modle;

import java.io.Serializable;
import java.util.List;

public class SettingBean implements Serializable {
    /**
     * code : 1
     * msg :
     * data : {"charge":{"voice_chat":{"id":8,"code":"voice_chat","title":"语音收费","val":5,"desc":"","status":1,"group":"charge","uid":0},"video_chat":{"id":9,"code":"video_chat","title":"视频收费","val":5,"desc":"","status":1,"group":"charge","uid":0},"message_chat":{"id":10,"code":"message_chat","title":"消息收费","val":1,"desc":"","status":1,"group":"charge","uid":0}},"charge_rule":{"voice_chat":[0,20,30,50],"video_chat":[0,20,30,50],"message_chat":[0,20,30,50]}}
     */

    private int code;
    private String msg;
    /**
     * charge : {"voice_chat":{"id":8,"code":"voice_chat","title":"语音收费","val":5,"desc":"","status":1,"group":"charge","uid":0},"video_chat":{"id":9,"code":"video_chat","title":"视频收费","val":5,"desc":"","status":1,"group":"charge","uid":0},"message_chat":{"id":10,"code":"message_chat","title":"消息收费","val":1,"desc":"","status":1,"group":"charge","uid":0}}
     * charge_rule : {"voice_chat":[0,20,30,50],"video_chat":[0,20,30,50],"message_chat":[0,20,30,50]}
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
        /**
         * voice_chat : {"id":8,"code":"voice_chat","title":"语音收费","val":5,"desc":"","status":1,"group":"charge","uid":0}
         * video_chat : {"id":9,"code":"video_chat","title":"视频收费","val":5,"desc":"","status":1,"group":"charge","uid":0}
         * message_chat : {"id":10,"code":"message_chat","title":"消息收费","val":1,"desc":"","status":1,"group":"charge","uid":0}
         */

        private ChargeBean charge;
        private ChargeRuleBean charge_rule;

        public ChargeBean getCharge() {
            return charge;
        }

        public void setCharge(ChargeBean charge) {
            this.charge = charge;
        }

        public ChargeRuleBean getCharge_rule() {
            return charge_rule;
        }

        public void setCharge_rule(ChargeRuleBean charge_rule) {
            this.charge_rule = charge_rule;
        }

        public static class ChargeBean implements Serializable {
            /**
             * id : 8
             * code : voice_chat
             * title : 语音收费
             * val : 5
             * desc :
             * status : 1
             * group : charge
             * uid : 0
             */

            private VoiceChatBean voice_chat;
            /**
             * id : 9
             * code : video_chat
             * title : 视频收费
             * val : 5
             * desc :
             * status : 1
             * group : charge
             * uid : 0
             */

            private VideoChatBean video_chat;
            /**
             * id : 10
             * code : message_chat
             * title : 消息收费
             * val : 1
             * desc :
             * status : 1
             * group : charge
             * uid : 0
             */

            private MessageChatBean message_chat;

            public VoiceChatBean getVoice_chat() {
                return voice_chat;
            }

            public void setVoice_chat(VoiceChatBean voice_chat) {
                this.voice_chat = voice_chat;
            }

            public VideoChatBean getVideo_chat() {
                return video_chat;
            }

            public void setVideo_chat(VideoChatBean video_chat) {
                this.video_chat = video_chat;
            }

            public MessageChatBean getMessage_chat() {
                return message_chat;
            }

            public void setMessage_chat(MessageChatBean message_chat) {
                this.message_chat = message_chat;
            }

            public static class VoiceChatBean implements Serializable {
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

            public static class VideoChatBean implements Serializable {
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

            public static class MessageChatBean implements Serializable {
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
            private List<String> voice_chat;
            private List<String> video_chat;
            private List<String> message_chat;

            public List<String> getVoice_chat() {
                return voice_chat;
            }

            public void setVoice_chat(List<String> voice_chat) {
                this.voice_chat = voice_chat;
            }

            public List<String> getVideo_chat() {
                return video_chat;
            }

            public void setVideo_chat(List<String> video_chat) {
                this.video_chat = video_chat;
            }

            public List<String> getMessage_chat() {
                return message_chat;
            }

            public void setMessage_chat(List<String> message_chat) {
                this.message_chat = message_chat;
            }
        }
    }
}
