package com.muse.xiangta.modle.custommsg;

import com.muse.xiangta.LiveConstant;

public class CustomMsgCloseVideo extends CustomMsg {
    private String msg_content;

    public CustomMsgCloseVideo()
    {
        super();
        setType(LiveConstant.CustomMsgType.MSG_CLOSE_VIDEO_LINE);
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }
}
