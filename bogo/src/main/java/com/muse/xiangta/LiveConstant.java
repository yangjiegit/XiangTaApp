package com.muse.xiangta;


import com.muse.xiangta.modle.custommsg.CustomMsgAllGift;
import com.muse.xiangta.modle.custommsg.CustomMsgCloseVideo;
import com.muse.xiangta.modle.custommsg.CustomMsgDice;
import com.muse.xiangta.modle.custommsg.CustomMsgGuessing;
import com.muse.xiangta.modle.custommsg.CustomMsgOpenVip;
import com.muse.xiangta.modle.custommsg.CustomMsgPrivateGift;
import com.muse.xiangta.modle.custommsg.CustomMsgPrivatePhoto;
import com.muse.xiangta.modle.custommsg.CustomMsgRedEnvelopes;
import com.muse.xiangta.modle.custommsg.CustomMsgSpecialEffects;
import com.muse.xiangta.modle.custommsg.CustomMsgVideoCall;
import com.muse.xiangta.modle.custommsg.CustomMsgVideoCallEnd;
import com.muse.xiangta.modle.custommsg.CustomMsgVideoCallReply;

import java.util.HashMap;

public class LiveConstant {

    //是否客户端推送
    public static boolean IS_CLIENT_PUSH_MESSAGE = true;

    public static class DATA_DEFINE {
        public static final int PAGE_COUNT = 20;
        public static final int MAX_SELECT_LABEL_NUM = 3;
        public static final int MAX_SELECT_SELF_LABEL_NUM = 2;
    }

    public static String AUTH_IMG_DIR = "auth_id_card/";
    public static String VIDEO_COVER_IMG_DIR = "video_cover_img/";
    public static String VIDEO_DIR = "video/";
    public static String AUDIO_DIR = "audio/";
    public static String AVATAR_DIR = "avatar/";
    public static String IMG_DIR = "img/";
    /**
     * sdk最大连麦数量
     */
    public static final int MAX_LINK_MIC_COUNT = 3;

    //public static final String LIVE_PRIVATE_KEY_TAG = SDBase64.decodeToString("8J+UkQ==");

    public static final String LIVE_HOT_CITY = "热门";

    public static final String LEVEL_SPAN_KEY = "level";

    public static final String DEFAULT_CHARSET = "UTF-8";

    public static final class LiveSdkTag {
        /**
         * 腾讯sdk日志tag
         */
        public static final String TAG_SDK_TENCENT = "tag_sdk_tencent";
        /**
         * 金山sdk日志tag
         */
        public static final String TAG_SDK_KSY = "tag_sdk_ksy";
    }

    public static final class LiveSdkType {
        /**
         * 腾讯sdk
         */
        public static final int TENCENT = 0;
        /**
         * 金山sdk
         */
        public static final int KSY = 1;
    }

    /**
     * 推流视频质量
     */
    public static final class VideoQualityType {
        /**
         * 标清
         */
        public static final int VIDEO_QUALITY_STANDARD = 0;
        /**
         * 高清
         */
        public static final int VIDEO_QUALITY_HIGH = 1;
        /**
         * 超清
         */
        public static final int VIDEO_QUALITY_SUPER = 2;
    }


    /**
     * 用于发送和接收自定义消息的类型判断
     */
    public static final class CustomMsgType {

        /**
         * 正常文字聊天消息
         */
        public static final int MSG_TEXT = 0;

        public static final int MSG_NONE = -1;
        /**
         * 私聊礼物消息
         */
        public static final int MSG_PRIVATE_GIFT = 23;

        /**
         * 一对一视频消息推送
         */
        public static final int MSG_VIDEO_LINE_CALL = 12;

        /**
         * 一对一视频消息回复推送
         */
        public static final int MSG_VIDEO_LINE_CALL_REPLY = 13;

        /**
         * 一对一视频消息结束推送
         */
        public static final int MSG_VIDEO_LINE_CALL_END = 14;

        /**
         * 私聊私照消息
         */
        public static final int MSG_PRIVATE_IMG = 24;
        /**
         * 关闭一对一视频消息
         */
        public static final int MSG_CLOSE_VIDEO_LINE = 25;

        /**
         * 全局礼物消息通知
         */
        public static final int MSG_ALL_GIFT = 777;
        /**
         * 全局开通VIP消息通知
         */
        public static final int MSG_ALL_OPEN_VIP = 778;

        public static final int MSG_ALL_RED_ENVELOPES = 800;//红包

        public static final int MSG_APPROACH = 801;//进场特效

        public static final int CY_CHAT_CAIQUAN = 802;//接受猜拳

        public static final int CY_CHAT_SHAIZI = 803;//接受骰子


    }

    public static final HashMap<Integer, Class<? extends ICustomMsg>> mapCustomMsgClass = new HashMap<>();

    static {
        //mapCustomMsgClass.put(CustomMsgType.MSG_TEXT, CustomMsgText.class);
        mapCustomMsgClass.put(CustomMsgType.MSG_PRIVATE_IMG, CustomMsgPrivatePhoto.class);
        mapCustomMsgClass.put(CustomMsgType.MSG_PRIVATE_GIFT, CustomMsgPrivateGift.class);
        mapCustomMsgClass.put(CustomMsgType.MSG_VIDEO_LINE_CALL, CustomMsgVideoCall.class);
        mapCustomMsgClass.put(CustomMsgType.MSG_VIDEO_LINE_CALL_REPLY, CustomMsgVideoCallReply.class);
        mapCustomMsgClass.put(CustomMsgType.MSG_VIDEO_LINE_CALL_END, CustomMsgVideoCallEnd.class);
        mapCustomMsgClass.put(CustomMsgType.MSG_CLOSE_VIDEO_LINE, CustomMsgCloseVideo.class);
        mapCustomMsgClass.put(CustomMsgType.MSG_ALL_GIFT, CustomMsgAllGift.class);
        mapCustomMsgClass.put(CustomMsgType.MSG_ALL_OPEN_VIP, CustomMsgOpenVip.class);
        mapCustomMsgClass.put(CustomMsgType.MSG_ALL_RED_ENVELOPES, CustomMsgRedEnvelopes.class);
        //猜拳
        mapCustomMsgClass.put(CustomMsgType.CY_CHAT_CAIQUAN, CustomMsgGuessing.class);
        //筛子
        mapCustomMsgClass.put(CustomMsgType.CY_CHAT_SHAIZI, CustomMsgDice.class);
        //特效
        mapCustomMsgClass.put(CustomMsgType.MSG_APPROACH, CustomMsgSpecialEffects.class);
    }

    /**
     * 私聊消息类型
     */
    public static final class PrivateMsgType {
        /**
         * 文字消息
         */
        public static final int MSG_TEXT_LEFT = 0;
        public static final int MSG_TEXT_RIGHT = 1;

        //语音
        public static final int MSG_VOICE_LEFT = 2;
        public static final int MSG_VOICE_RIGHT = 3;

        //图片
        public static final int MSG_IMAGE_LEFT = 4;
        public static final int MSG_IMAGE_RIGHT = 5;

        //礼物
        public static final int MSG_GIFT_LEFT = 6;
        public static final int MSG_GIFT_RIGHT = 7;

    }

    public static final class PushType {

        public static final int VIDEO_CALL = 12;
    }


}
