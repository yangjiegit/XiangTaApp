package com.muse.chat.model;


import com.tencent.imsdk.TIMMessage;

/**
 * 消息工厂
 */
public class MessageFactory2 {

    private MessageFactory2() {
    }


    /**
     * 消息工厂方法
     */
    public static Message2 getMessage(TIMMessage message) {
        switch (message.getElement(0).getType()) {
            case Text:
            case Face:
                return new TextMessage2(message);
            case Image:
                return new ImageMessage2(message);
            case Sound:
                return new VoiceMessage2(message);
            case Video:
                return new VideoMessage2(message);
            case GroupTips:
                //return new GroupTipMessage(message);
            case File:
                return new FileMessage2(message);
            case Custom:
                return new CustomMessage2(message);
            case UGC:
                return new UGCMessage2(message);
            default:
                return null;
        }
    }


}
