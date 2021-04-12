package com.muse.chat.model;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.muse.chat.adapter.ChatAdapter;
import com.muse.chat.utils.TimeUtil;
import com.muse.xiangta.BuildConfig;
import com.muse.xiangta.CuckooApplication;
import com.muse.xiangta.ICustomMsg;
import com.muse.xiangta.LiveConstant;
import com.muse.xiangta.R;
import com.muse.xiangta.event.EventChatClickPrivateImgMessage;
import com.muse.xiangta.event.EventChatClickPrivateRedEnvelopesMessage;
import com.muse.xiangta.modle.UserModel;
import com.muse.xiangta.modle.custommsg.CustomMsg;
import com.muse.xiangta.modle.custommsg.CustomMsgDice;
import com.muse.xiangta.modle.custommsg.CustomMsgGuessing;
import com.muse.xiangta.modle.custommsg.CustomMsgPrivateGift;
import com.muse.xiangta.modle.custommsg.CustomMsgPrivatePhoto;
import com.muse.xiangta.modle.custommsg.CustomMsgRedEnvelopes;
import com.muse.xiangta.utils.Utils;
import com.tencent.imsdk.TIMCustomElem;
import com.tencent.imsdk.TIMMessage;

import org.greenrobot.eventbus.EventBus;

/**
 * 自定义消息
 */
public class CustomMessage extends Message {

    private String TAG = getClass().getSimpleName();

    private int type;

    private CustomMsg customMsg;

    private ImageView iv_cai, iv_dong;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    iv_dong.setVisibility(View.GONE);
                    iv_cai.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    public CustomMessage(TIMMessage message) {
        this.message = message;
    }

    public CustomMessage(CustomMsg customMsg, int type) {
        message = new TIMMessage();

        switch (type) {
            case LiveConstant.CustomMsgType.MSG_PRIVATE_GIFT:
                message = customMsg.parseToTIMMessage(message);
                break;
            case LiveConstant.CustomMsgType.MSG_ALL_RED_ENVELOPES:
                message = customMsg.parseToTIMMessage(message);
                break;
            case LiveConstant.CustomMsgType.CY_CHAT_CAIQUAN:
                //猜拳
                message = customMsg.parseToTIMMessage(message);
                break;
            case LiveConstant.CustomMsgType.CY_CHAT_SHAIZI:
                //骰子
                message = customMsg.parseToTIMMessage(message);
                break;
            default:
                break;
        }
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    /**
     * 显示消息
     *
     * @param viewHolder 界面样式
     * @param context    显示消息的上下文
     */
    @Override
    public void showMessage(ChatAdapter.ViewHolder viewHolder, Context context) {

        CustomMsg customMsg = parseToModel(CustomMsg.class);

        if (customMsg != null) {
            type = customMsg.getType();

            Class realCustomMsgClass = LiveConstant.mapCustomMsgClass.get(customMsg.getType());
            if (realCustomMsgClass == null) {
                return;
            }
            CustomMsg realCustomMsg = parseToModel(realCustomMsgClass);
            setCustomMsg(realCustomMsg);

            switch (customMsg.getType()) {

                case LiveConstant.CustomMsgType.MSG_PRIVATE_GIFT:

                    CustomMsgPrivateGift customMsgPrivateGift = getCustomMsgReal();
                    if (customMsgPrivateGift != null) {
                        // 私聊消息类型
                        setPrivateMsgType(viewHolder, context, customMsgPrivateGift);
                    }
                    break;

                case LiveConstant.CustomMsgType.MSG_ALL_RED_ENVELOPES:
                    //红包
                    CustomMsgRedEnvelopes customMsgRedEnvelopes = getCustomMsgReal();
                    if (null != customMsgRedEnvelopes) {
                        setPrivateMsgRedEnvelopes(viewHolder, context, customMsgRedEnvelopes);
                    }
                    break;
                case LiveConstant.CustomMsgType.CY_CHAT_CAIQUAN://猜拳
                    CustomMsgGuessing customMsgGuessing = getCustomMsgReal();
                    if (null != customMsgGuessing) {
                        setPrivateMsgGuessing(viewHolder, context, customMsgGuessing);
                    }
                    break;
                case LiveConstant.CustomMsgType.CY_CHAT_SHAIZI://骰子
                    CustomMsgDice customMsgDice = getCustomMsgReal();
                    if (null != customMsgDice) {
                        setPrivateMsgDice(viewHolder, context, customMsgDice);
                    }
                    break;
                case LiveConstant.CustomMsgType.MSG_VIDEO_LINE_CALL:

                    setVideoLinMsgType(viewHolder, context, customMsg.getSender(), "拨打视频通话");
                    break;

                case LiveConstant.CustomMsgType.MSG_VIDEO_LINE_CALL_END:

                    setVideoLinMsgType(viewHolder, context, customMsg.getSender(), "结束视频通话");
                    break;

                case LiveConstant.CustomMsgType.MSG_VIDEO_LINE_CALL_REPLY:

                    LogUtils.i("MSG_VIDEO_LINE_CALL_REPLY");
                    setVideoLinMsgType(viewHolder, context, customMsg.getSender(), "视频通话回复");
                    break;
                case LiveConstant.CustomMsgType.MSG_PRIVATE_IMG:
                    CustomMsgPrivatePhoto customMsgPrivatePhoto = getCustomMsgReal();
                    if (customMsgPrivatePhoto != null) {
                        // 私聊消息类型
                        setPrivateImgMsgType(viewHolder, context, customMsgPrivatePhoto);
                    }
                    LogUtils.i("MSG_PRIVATE_IMG");
                    break;
                default:
                    LogUtils.i("default");
                    //viewHolder.llContent.setVisibility(View.GONE);
                    break;
            }
        }

    }

    //私照信息
    private void setPrivateImgMsgType(ChatAdapter.ViewHolder viewHolder, Context context, final CustomMsgPrivatePhoto customMsgPrivatePhoto) {

        clearView(viewHolder);
        if (checkRevoke(viewHolder)) {
            return;
        }

        viewHolder.systemMessage.setVisibility(hasTime ? View.VISIBLE : View.GONE);
        viewHolder.systemMessage.setText(TimeUtil.getChatTimeStr(message.timestamp()));

        ImageView iv = new ImageView(CuckooApplication.getInstances());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setLayoutParams(new ViewGroup.LayoutParams(ConvertUtils.dp2px(100), ConvertUtils.dp2px(100)));
        Utils.loadHttpImg(customMsgPrivatePhoto.getImg(), iv);
        if (message.isSelf()) {
            viewHolder.leftPanel.setVisibility(View.GONE);
            viewHolder.rightPanel.setVisibility(View.VISIBLE);
            viewHolder.rightMessage.addView(iv);

        } else {
            viewHolder.leftPanel.setVisibility(View.VISIBLE);
            viewHolder.rightPanel.setVisibility(View.GONE);
            viewHolder.leftMessage.addView(iv);
        }

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventChatClickPrivateImgMessage event = new EventChatClickPrivateImgMessage();
                event.setId(customMsgPrivatePhoto.getId());
                event.setImg(customMsgPrivatePhoto.getImg());
                EventBus.getDefault().post(event);
            }
        });
        setSenderUserInfo(viewHolder, context, customMsgPrivatePhoto.getSender());
        showStatus(viewHolder);
    }


    //一对一视频请求消息UI填充
    private void setVideoLinMsgType(ChatAdapter.ViewHolder viewHolder, Context context, UserModel userModel, String msg) {

        clearView(viewHolder);
        if (checkRevoke(viewHolder)) {
            return;
        }

        viewHolder.systemMessage.setVisibility(hasTime ? View.VISIBLE : View.GONE);
        viewHolder.systemMessage.setText(TimeUtil.getChatTimeStr(message.timestamp()));

        TextView tv = new TextView(CuckooApplication.getInstances());
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        tv.setPadding(10, 8, 10, 8);
        tv.setTextColor(CuckooApplication.getInstances().getResources().getColor(isSelf() ? R.color.admin_color : R.color.admin_color));
        if (message.isSelf()) {
            viewHolder.leftPanel.setVisibility(View.GONE);
            viewHolder.rightPanel.setVisibility(View.VISIBLE);
            tv.setText(msg);
            viewHolder.rightMessage.addView(tv);
            viewHolder.rightMessage.setBackgroundResource(R.drawable.bg_bubble_blue);
        } else {
            viewHolder.leftPanel.setVisibility(View.VISIBLE);
            viewHolder.rightPanel.setVisibility(View.GONE);
            tv.setText(msg);
            viewHolder.leftMessage.addView(tv);
            viewHolder.leftMessage.setBackgroundResource(R.drawable.bg_bubble_gray);
        }
        setSenderUserInfo(viewHolder, context, userModel);
        showStatus(viewHolder);
    }


    //私信送礼物UI填充数据
    private void setPrivateMsgType(ChatAdapter.ViewHolder viewHolder, Context context, CustomMsgPrivateGift customMsgPrivateGift) {
        clearView(viewHolder);
        if (checkRevoke(viewHolder)) {
            return;
        }

        viewHolder.systemMessage.setVisibility(hasTime ? View.VISIBLE : View.GONE);
        viewHolder.systemMessage.setText(TimeUtil.getChatTimeStr(message.timestamp()));


        if (message.isSelf()) {
            View view_private_msg_view = getView(R.layout.view_private_recived_gift_msg);
            ImageView iv_gift = view_private_msg_view.findViewById(R.id.iv_gift);
            TextView tv = view_private_msg_view.findViewById(R.id.tv_msg);
            initLayout(customMsgPrivateGift, iv_gift, tv);

            viewHolder.leftPanel.setVisibility(View.GONE);
            viewHolder.rightPanel.setVisibility(View.VISIBLE);
            tv.setText(customMsgPrivateGift.getFrom_msg());
            tv.setBackgroundResource(R.drawable.chat_send_gift_right_yellow_bac);

            viewHolder.rightMessage.setBackgroundResource(0);
            viewHolder.rightMessage.setPadding(0, 0, 0, 0);
            viewHolder.rightMessage.addView(view_private_msg_view);

        } else {
            View view_private_msg_view = getView(R.layout.view_private_send_gift_msg);
            ImageView iv_gift = view_private_msg_view.findViewById(R.id.iv_gift);
            TextView tv = view_private_msg_view.findViewById(R.id.tv_msg);
            initLayout(customMsgPrivateGift, iv_gift, tv);

            viewHolder.leftPanel.setVisibility(View.VISIBLE);
            viewHolder.rightPanel.setVisibility(View.GONE);
            tv.setText(customMsgPrivateGift.getTo_msg());
            tv.setBackgroundResource(R.drawable.chat_send_gift_left_white_bac);

            viewHolder.leftMessage.setBackgroundResource(0);
            viewHolder.leftMessage.setPadding(0, 0, 0, 0);
            viewHolder.leftMessage.addView(view_private_msg_view);
        }
        setSenderUserInfo(viewHolder, context, customMsgPrivateGift.getSender());
        showStatus(viewHolder);
    }

    private void setPrivateMsgRedEnvelopes(ChatAdapter.ViewHolder viewHolder, Context context, CustomMsgRedEnvelopes customMsgRedEnvelopes) {
        clearView(viewHolder);
        if (checkRevoke(viewHolder)) {
            return;
        }
        viewHolder.systemMessage.setVisibility(hasTime ? View.VISIBLE : View.GONE);
        viewHolder.systemMessage.setText(TimeUtil.getChatTimeStr(message.timestamp()));
        if (message.isSelf()) {
            View view_private_msg_view = getView(R.layout.view_private_send_red_envelopes);//发送
            FrameLayout fl_layout = view_private_msg_view.findViewById(R.id.fl_layout);
            TextView tv_title = view_private_msg_view.findViewById(R.id.tv_title);
            initLayoutRedEnvelopes(customMsgRedEnvelopes, tv_title);
            viewHolder.leftPanel.setVisibility(View.GONE);
            viewHolder.rightPanel.setVisibility(View.VISIBLE);

            fl_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventChatClickPrivateRedEnvelopesMessage event = new EventChatClickPrivateRedEnvelopesMessage();
                    event.setId(customMsgRedEnvelopes.getRpID());
                    EventBus.getDefault().post(event);
                }
            });


            viewHolder.rightMessage.setBackgroundResource(0);
            viewHolder.rightMessage.setPadding(0, 0, 0, 0);
            viewHolder.rightMessage.addView(view_private_msg_view);

        } else {
            View view_private_msg_view = getView(R.layout.view_private_recived_red_envelopes);//接受
            FrameLayout fl_layout = view_private_msg_view.findViewById(R.id.fl_layout);
            TextView tv_title = view_private_msg_view.findViewById(R.id.tv_title);
            initLayoutRedEnvelopes(customMsgRedEnvelopes, tv_title);

            viewHolder.leftPanel.setVisibility(View.VISIBLE);
            viewHolder.rightPanel.setVisibility(View.GONE);

            fl_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventChatClickPrivateRedEnvelopesMessage event = new EventChatClickPrivateRedEnvelopesMessage();
                    event.setId(customMsgRedEnvelopes.getRpID());
                    EventBus.getDefault().post(event);
                }
            });

            viewHolder.leftMessage.setBackgroundResource(0);
            viewHolder.leftMessage.setPadding(0, 0, 0, 0);
            viewHolder.leftMessage.addView(view_private_msg_view);
        }
        setSenderUserInfo(viewHolder, context, customMsgRedEnvelopes.getSender());
        showStatus(viewHolder);
    }

    private void setPrivateMsgGuessing(ChatAdapter.ViewHolder viewHolder, Context context, CustomMsgGuessing customMsgGuessing) {
        clearView(viewHolder);
        if (checkRevoke(viewHolder)) {
            return;
        }
        viewHolder.systemMessage.setVisibility(hasTime ? View.VISIBLE : View.GONE);
        viewHolder.systemMessage.setText(TimeUtil.getChatTimeStr(message.timestamp()));
        if (message.isSelf()) {
            View view_private_msg_view = getView(R.layout.view_private_send_guessing);//发送

            iv_cai = view_private_msg_view.findViewById(R.id.iv_cai);
            iv_dong = view_private_msg_view.findViewById(R.id.iv_dong);
            Glide.with(context).asGif().load(R.mipmap.img_caiquan_dong).listener(new RequestListener<GifDrawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                    Log.d("ret", "joker   动画播放完毕");
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            try {
                                sleep(2000);
                                android.os.Message msg = new android.os.Message();
                                msg.what = 1;
                                handler.sendMessage(msg);
//                                iv_dong.setVisibility(View.GONE);
//                                iv_cai.setVisibility(View.VISIBLE);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                    return false;
                }
            }).into(iv_dong);

            switch (customMsgGuessing.getStaticimg()) {
                case "ysh_chat_shitou":
                    iv_cai.setImageResource(R.mipmap.img_shitou);
                    break;
                case "ysh_chat_jiandao":
                    iv_cai.setImageResource(R.mipmap.img_jiandao);
                    break;
                case "ysh_chat_bu":
                    iv_cai.setImageResource(R.mipmap.img_bu);
                    break;
            }

            viewHolder.rightMessage.setBackgroundResource(0);
            viewHolder.rightMessage.setPadding(0, 0, 0, 0);
            viewHolder.rightMessage.addView(view_private_msg_view);
        } else {
            View view_private_msg_view = getView(R.layout.view_private_send_guessing);//接受

            iv_cai = view_private_msg_view.findViewById(R.id.iv_cai);
            iv_dong = view_private_msg_view.findViewById(R.id.iv_dong);
            Glide.with(context).asGif().load(R.mipmap.img_caiquan_dong).listener(new RequestListener<GifDrawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                    Log.d("ret", "joker   动画播放完毕");
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            try {
                                sleep(2000);
                                android.os.Message msg = new android.os.Message();
                                msg.what = 1;
                                handler.sendMessage(msg);
//                                iv_dong.setVisibility(View.GONE);
//                                iv_cai.setVisibility(View.VISIBLE);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                    return false;
                }
            }).into(iv_dong);

            switch (customMsgGuessing.getStaticimg()) {
                case "ysh_chat_shitou":
                    iv_cai.setImageResource(R.mipmap.img_shitou);
                    break;
                case "ysh_chat_jiandao":
                    iv_cai.setImageResource(R.mipmap.img_jiandao);
                    break;
                case "ysh_chat_bu":
                    iv_cai.setImageResource(R.mipmap.img_bu);
                    break;
            }

            viewHolder.leftMessage.setBackgroundResource(0);
            viewHolder.leftMessage.setPadding(0, 0, 0, 0);
            viewHolder.leftMessage.addView(view_private_msg_view);
        }
        setSenderUserInfo(viewHolder, context, customMsgGuessing.getSender());
        showStatus(viewHolder);
    }

    private void setPrivateMsgDice(ChatAdapter.ViewHolder viewHolder, Context context, CustomMsgDice customMsgDice) {
        clearView(viewHolder);
        if (checkRevoke(viewHolder)) {
            return;
        }
        viewHolder.systemMessage.setVisibility(hasTime ? View.VISIBLE : View.GONE);
        viewHolder.systemMessage.setText(TimeUtil.getChatTimeStr(message.timestamp()));
        if (message.isSelf()) {
            View view_private_msg_view = getView(R.layout.view_private_send_guessing);//发送

            iv_cai = view_private_msg_view.findViewById(R.id.iv_cai);
            iv_dong = view_private_msg_view.findViewById(R.id.iv_dong);
            Glide.with(context).asGif().load(R.mipmap.img_shai_dong).listener(new RequestListener<GifDrawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                    Log.d("ret", "joker   动画播放完毕");
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            try {
                                sleep(2000);
                                android.os.Message msg = new android.os.Message();
                                msg.what = 1;
                                handler.sendMessage(msg);
//                                iv_dong.setVisibility(View.GONE);
//                                iv_cai.setVisibility(View.VISIBLE);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                    return false;
                }
            }).into(iv_dong);

            switch (customMsgDice.getStaticimg()) {
                case "ysh_chat_dian1":
                    iv_cai.setImageResource(R.mipmap.img_shai1);
                    break;
                case "ysh_chat_dian2":
                    iv_cai.setImageResource(R.mipmap.img_shai2);
                    break;
                case "ysh_chat_dian3":
                    iv_cai.setImageResource(R.mipmap.img_shai3);
                    break;
                case "ysh_chat_dian4":
                    iv_cai.setImageResource(R.mipmap.img_shai4);
                    break;
                case "ysh_chat_dian5":
                    iv_cai.setImageResource(R.mipmap.img_shai5);
                    break;
                case "ysh_chat_dian6":
                    iv_cai.setImageResource(R.mipmap.img_shai6);
                    break;
            }

            viewHolder.rightMessage.setBackgroundResource(0);
            viewHolder.rightMessage.setPadding(0, 0, 0, 0);
            viewHolder.rightMessage.addView(view_private_msg_view);
        } else {
            View view_private_msg_view = getView(R.layout.view_private_send_guessing);//接受

            iv_cai = view_private_msg_view.findViewById(R.id.iv_cai);
            iv_dong = view_private_msg_view.findViewById(R.id.iv_dong);
            Glide.with(context).asGif().load(R.mipmap.img_shai_dong).listener(new RequestListener<GifDrawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                    Log.d("ret", "joker   动画播放完毕");
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            try {
                                sleep(2000);
                                android.os.Message msg = new android.os.Message();
                                msg.what = 1;
                                handler.sendMessage(msg);
//                                iv_dong.setVisibility(View.GONE);
//                                iv_cai.setVisibility(View.VISIBLE);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                    return false;
                }
            }).into(iv_dong);

            switch (customMsgDice.getStaticimg()) {
                case "ysh_chat_dian1":
                    iv_cai.setImageResource(R.mipmap.img_shai1);
                    break;
                case "ysh_chat_dian2":
                    iv_cai.setImageResource(R.mipmap.img_shai2);
                    break;
                case "ysh_chat_dian3":
                    iv_cai.setImageResource(R.mipmap.img_shai3);
                    break;
                case "ysh_chat_dian4":
                    iv_cai.setImageResource(R.mipmap.img_shai4);
                    break;
                case "ysh_chat_dian5":
                    iv_cai.setImageResource(R.mipmap.img_shai5);
                    break;
                case "ysh_chat_dian6":
                    iv_cai.setImageResource(R.mipmap.img_shai6);
                    break;
            }

            viewHolder.leftMessage.setBackgroundResource(0);
            viewHolder.leftMessage.setPadding(0, 0, 0, 0);
            viewHolder.leftMessage.addView(view_private_msg_view);
        }
        setSenderUserInfo(viewHolder, context, customMsgDice.getSender());
        showStatus(viewHolder);
    }

    private void initLayout(CustomMsgPrivateGift customMsgPrivateGift, ImageView iv_gift, TextView tv) {
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        tv.setTextColor(CuckooApplication.getInstances().getResources().getColor(isSelf() ? R.color.gray_black : R.color.gift_msg_color));
        Utils.loadHttpImg(customMsgPrivateGift.getProp_icon(), iv_gift);
    }

    private void initLayoutRedEnvelopes(CustomMsgRedEnvelopes customMsgRedEnvelopes, TextView tv) {
        tv.setText(customMsgRedEnvelopes.getRptit());
    }

    private View getView(int res) {
        return LayoutInflater.from(CuckooApplication.getInstances()).inflate(res, null);
    }


    public void setCustomMsg(CustomMsg customMsg) {
        this.customMsg = customMsg;
    }

    /**
     * 获取消息摘要
     */
    @Override
    public String getSummary() {
        String str = getRevokeSummary();
        if (str != null) return str;

        CustomMsg customMsg = parseToModel(CustomMsg.class);

        String result = "";
        if (customMsg != null) {
            type = customMsg.getType();

            Class realCustomMsgClass = LiveConstant.mapCustomMsgClass.get(customMsg.getType());
            if (realCustomMsgClass == null) {
                return result;
            }
            CustomMsg realCustomMsg = parseToModel(realCustomMsgClass);
            setCustomMsg(realCustomMsg);

            switch (customMsg.getType()) {

                case LiveConstant.CustomMsgType.MSG_PRIVATE_GIFT:

                    CustomMsgPrivateGift customMsgPrivateGift = getCustomMsgReal();
                    if (customMsgPrivateGift != null) {
                        // 私聊消息类型
                        result = "赠送礼物";
                    }
                    break;

                case LiveConstant.CustomMsgType.MSG_VIDEO_LINE_CALL:

                    result = "拨打视频通话";
                    break;

                case LiveConstant.CustomMsgType.MSG_VIDEO_LINE_CALL_END:

                    result = "结束视频通话";
                    break;

                case LiveConstant.CustomMsgType.MSG_VIDEO_LINE_CALL_REPLY:

                    result = "视频通话回复";
                    break;
                case LiveConstant.CustomMsgType.MSG_PRIVATE_IMG:
                    CustomMsgPrivatePhoto customMsgPrivatePhoto = getCustomMsgReal();
                    if (customMsgPrivatePhoto != null) {
                        // 私聊消息类型
                        result = "私照";
                    }
                    LogUtils.i("MSG_PRIVATE_IMG");
                    break;
                default:
                    LogUtils.i("default");
                    //viewHolder.llContent.setVisibility(View.GONE);
                    break;
            }
        }
        return result;
    }

    /**
     * 保存消息或消息文件
     */
    @Override
    public void save() {

    }


    public <T extends ICustomMsg> T getCustomMsgReal() {
        T t = null;
        try {
            return (T) getCustomMsg();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public CustomMsg getCustomMsg() {
        return customMsg;
    }

    public <T extends CustomMsg> T parseToModel(Class<T> clazz) {
        T model = null;
        String json = null;
        try {
            byte[] data = null;

            data = ((TIMCustomElem) message.getElement(0)).getData();

            json = new String(data, LiveConstant.DEFAULT_CHARSET);
            model = JSON.parseObject(json, clazz);

            if (BuildConfig.DEBUG) {
                LogUtils.i("parseToModel " + model.getType() + ":" + json);
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
                //LogUtils.e("(" + getConversationPeer() + ")parse msg error:" + e.toString() + ",json:" + json);
            }
        } finally {

        }
        return model;
    }


}
