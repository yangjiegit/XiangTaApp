package com.muse.xiangta.modle.custommsg;

import com.muse.xiangta.LiveConstant;
import com.muse.xiangta.json.JsonRequestDoPrivateSendGuessing;

/**
 * 猜拳消息
 */
public class CustomMsgGuessing extends CustomMsg {

    private String gifimg; // 动态图片地址
    private String staticimg; // 拳头点数

    public CustomMsgGuessing() {
        super();
        setType(LiveConstant.CustomMsgType.CY_CHAT_CAIQUAN);
    }

    public void fillData(JsonRequestDoPrivateSendGuessing.SendBean model) {
        if (model != null) {
            gifimg = model.getGifimg();
            staticimg = model.getStaticimg();
        }
    }

    public String getGifimg() {
        return gifimg;
    }

    public void setGifimg(String gifimg) {
        this.gifimg = gifimg;
    }

    public String getStaticimg() {
        return staticimg;
    }

    public void setStaticimg(String staticimg) {
        this.staticimg = staticimg;
    }

    @Override
    public String getConversationDesc() {
        return "[猜拳]";
    }

}
