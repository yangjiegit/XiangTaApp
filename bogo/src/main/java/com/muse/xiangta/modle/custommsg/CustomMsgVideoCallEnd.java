package com.muse.xiangta.modle.custommsg;


import com.muse.xiangta.LiveConstant;

public class CustomMsgVideoCallEnd extends CustomMsg
{
    public CustomMsgVideoCallEnd()
    {
        super();
        setType(LiveConstant.CustomMsgType.MSG_VIDEO_LINE_CALL_END);
    }

}
