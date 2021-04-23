package com.muse.chat.ui;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.widget.CardView;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.lzy.okgo.callback.StringCallback;
import com.maning.imagebrowserlibrary.utils.StatusBarUtil;
import com.muse.chat.adapter.ChatAdapter2;
import com.muse.chat.model.CustomMessage;
import com.muse.chat.model.FileMessage;
import com.muse.chat.model.FileMessage2;
import com.muse.chat.model.ImageMessage;
import com.muse.chat.model.ImageMessage2;
import com.muse.chat.model.Message;
import com.muse.chat.model.Message2;
import com.muse.chat.model.MessageFactory2;
import com.muse.chat.model.TextMessage;
import com.muse.chat.model.UGCMessage;
import com.muse.chat.model.VideoMessage;
import com.muse.chat.model.VoiceMessage;
import com.muse.chat.utils.FileUtil;
import com.muse.chat.utils.MediaUtil;
import com.muse.chat.utils.RecorderUtil;
import com.muse.xiangta.CuckooApplication;
import com.muse.xiangta.LiveConstant;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.dialog.CuckooRewardCoinDialog;
import com.muse.xiangta.dialog.GiftBottomDialog;
import com.muse.xiangta.event.EventChatClickPrivateImgMessage;
import com.muse.xiangta.event.EventChatClickPrivateRedEnvelopesMessage;
import com.muse.xiangta.inter.JsonCallback;
import com.muse.xiangta.json.FamilyBean;
import com.muse.xiangta.json.JsonRequest;
import com.muse.xiangta.json.JsonRequestDoPrivateSendGif;
import com.muse.xiangta.json.JsonRequestDoPrivateSendGuessing;
import com.muse.xiangta.json.JsonRequestDoPrivateSendRedEnvelopes;
import com.muse.xiangta.json.JsonRequestDoPrivateSendSpecialEffects;
import com.muse.xiangta.json.jsonmodle.UserCenterData;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.modle.custommsg.CustomMsg;
import com.muse.xiangta.modle.custommsg.CustomMsgDice;
import com.muse.xiangta.modle.custommsg.CustomMsgGuessing;
import com.muse.xiangta.modle.custommsg.CustomMsgPrivateGift;
import com.muse.xiangta.modle.custommsg.CustomMsgPrivatePhoto;
import com.muse.xiangta.modle.custommsg.CustomMsgRedEnvelopes;
import com.muse.xiangta.modle.custommsg.CustomMsgSpecialEffects;
import com.muse.xiangta.modle.custommsg.InputListenerMsgText;
import com.muse.xiangta.ui.FamilyDetailsActivity;
import com.muse.xiangta.ui.MemberGroupListActivity;
import com.muse.xiangta.ui.MemberListActivity;
import com.muse.xiangta.ui.PrivatePhotoActivity;
import com.muse.xiangta.ui.RankingListActivity;
import com.muse.xiangta.ui.RedEnvelopesDetailsActivity;
import com.muse.xiangta.ui.common.Common;
import com.muse.xiangta.ui.view.LastInputEditText;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.SPHelper;
import com.muse.xiangta.utils.Utils;
import com.qmuiteam.qmui.BuildConfig;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMCustomElem;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageStatus;
import com.tencent.imsdk.ext.message.TIMMessageDraft;
import com.tencent.imsdk.ext.message.TIMMessageExt;
import com.tencent.imsdk.ext.message.TIMMessageLocator;
import com.tencent.qcloud.presentation.presenter.ChatPresenter;
import com.tencent.qcloud.presentation.viewfeatures.ChatView;
import com.tencent.qcloud.ui.ChatInput2;
import com.tencent.qcloud.ui.TemplateTitle;
import com.tencent.qcloud.ui.VoiceSendingView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class ChatActivity2 extends BaseActivity implements ChatView, View.OnClickListener, GiftBottomDialog.DoSendGiftListen, ChatAdapter2.OnChatChildrenItemListen {
    @BindView(R.id.chat_attribute_card_view)
    CardView attributeCv;

    private FrameLayout fl_layout;

    public static final int SEND_TEXT_MESSAGE = 1;
    public static final int SEND_VOICE_MESSAGE = 2;
    public static final int SEND_IMAGE_MESSAGE = 3;
    public static final int SEND_FILE_MESSAGE = 4;

    private List<Message2> messageList = new ArrayList<>();
    private ChatAdapter2 adapter;
    private ListView listView;
    private ChatPresenter presenter;
    private ChatInput2 input;
    private GiftBottomDialog giftBottomDialog;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int IMAGE_STORE = 200;
    private static final int FILE_CODE = 300;
    private static final int IMAGE_PREVIEW = 400;
    private static final int VIDEO_RECORD = 500;

    private List<CustomMsgSpecialEffects> mMsgList = new ArrayList<>();

    public static final int RESULT_SELECT_PRIVATE_PHOTO = 0x11;
    private Uri fileUri;
    private VoiceSendingView voiceSendingView;
    private String identify;
    private String family_id;
    private String userName;
    private String identifyStr = "";
    private RecorderUtil recorder = new RecorderUtil();
    private TIMConversationType type;
    private FamilyBean.DataBean dataBean;
    private String titleStr;
    private Handler myFirstHandler = new Handler();

    private String[] caiquanStr = {"ysh_chat_shitou", "ysh_chat_jiandao", "ysh_chat_bu"};
    private String[] shaiziStr = {"ysh_chat_dian1", "ysh_chat_dian2", "ysh_chat_dian3", "ysh_chat_dian4", "ysh_chat_dian5", "ysh_chat_dian6"};

    private TemplateTitle title;

    private Runnable runnable;
    private Handler handler = new Handler();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 10:
                    fl_layout.removeAllViews();
                    fl_layout.setVisibility(View.GONE);
                    break;
            }
        }
    };

    public static void navToChat(Context context, String identify, String userName, TIMConversationType type) {
        Intent intent = new Intent(context, ChatActivity2.class);
        intent.putExtra("identify", identify);
        intent.putExtra("user_nickname", userName);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    public static void navToChat(Context context, String identify, String userName, TIMConversationType type
            , String family_id) {
        Intent intent = new Intent(context, ChatActivity2.class);
        intent.putExtra("identify", identify);
        intent.putExtra("user_nickname", userName);
        intent.putExtra("type", type);
        intent.putExtra("family_id", family_id);
        context.startActivity(intent);
    }

    public static void navToChat(Context context, String identify, String userName, TIMConversationType type
            , FamilyBean.DataBean dataBean) {
        Intent intent = new Intent(context, ChatActivity2.class);
        intent.putExtra("identify", identify);
        intent.putExtra("user_nickname", userName);
        intent.putExtra("type", type);
        intent.putExtra("dataBean", dataBean);
        context.startActivity(intent);
    }


    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_chat2;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);
        StatusBarUtil.setLightMode(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        identify = getIntent().getStringExtra("identify");
        userName = getIntent().getStringExtra("user_nickname");
        type = (TIMConversationType) getIntent().getSerializableExtra("type");
        if (!StringUtils.isEmpty(getIntent().getStringExtra("family_id"))) {
            family_id = getIntent().getStringExtra("family_id");
        }
        if (null != getIntent().getSerializableExtra("dataBean")) {
            dataBean = (FamilyBean.DataBean) getIntent().getSerializableExtra("dataBean");
        }

        if (null != dataBean) {
            //家族发消息
            activation("1");
        }

        fl_layout = findViewById(R.id.fl_layout);

        input = (ChatInput2) findViewById(R.id.input_panel);
        input.setChatView(this);
        input.setSex(SaveData.getInstance().getUserInfo().getSex());
//        input.setCoinData(StringUtils.toInt(payCoin), RequestConfig.getConfigObj().getCurrency());

        presenter = new ChatPresenter(this, identify, type);
        presenter.start();

        adapter = new ChatAdapter2(this, R.layout.item_message, messageList);
        adapter.setChildrenListen(this);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        input.setInputMode(ChatInput2.InputMode.NONE);
                        break;
                }
                return false;
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            private int firstItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && firstItem == 0) {
                    //如果拉到顶端读取更多消息
//                    presenter.getMessage(messageList.size() > 0 ? messageList.get(0).getMessage() : null);

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                firstItem = firstVisibleItem;
            }
        });
        registerForContextMenu(listView);
        title = (TemplateTitle) findViewById(R.id.chat_title);
        title.setTitleBackgroundColor(getResources().getColor(R.color.white));

        switch (type) {
            case C2C:
                title.setMoreImg(R.drawable.ic_chat_user_page);
                title.setMoreImgAction(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Common.jumpUserPage(ChatActivity2.this, identify);
                    }
                });
                title.setTitleText(userName);
                break;
            case Group:
                if (null != dataBean) {
                    //是家族
                    title.setMoreImg(R.mipmap.img_message_1);
                    title.setMoreImgAction(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivityForResult(new Intent(
                                    ChatActivity2.this, FamilyDetailsActivity.class
                            ).putExtra("data", dataBean), 20);
                        }
                    });
                } else {
                    title.setMoreImg(R.mipmap.img_message_1);
                    title.setMoreImg2(R.mipmap.img_message_phb);
                    title.setMoreImgAction(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //跳转
                            if (!StringUtils.isEmpty(family_id)) {
                                startActivity(new Intent(ChatActivity2.this, MemberGroupListActivity.class)
                                        .putExtra("family_id", family_id));
                            }
                        }
                    });

                    title.setMoreImgAction2(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //排行榜
                            startActivity(new Intent(ChatActivity2.this, RankingListActivity.class)
                                    .putExtra("family_id", family_id));
                        }
                    });
                }
                title.setTitleText(userName);
                break;

        }
        voiceSendingView = (VoiceSendingView) findViewById(R.id.voice_sending);

        CuckooApplication.getInstances().setInPrivateChatPage(true);

        Approach();

        runnable = new Runnable() {
            @Override
            public void run() {
                setMegData();
                handler.postDelayed(this, 6000);                             //相当于定时器，每隔2s执行一次该线程
            }
        };
        handler.post(runnable);
    }

    private void setMegData() {
        if (mMsgList.size() > 0) {
            if (mMsgList.get(mMsgList.size() - 1).getGroupID().equals(identify)) {
                //来一条数据 添加一条
                fl_layout.setVisibility(View.VISIBLE);
                View view = LayoutInflater.from(ChatActivity2.this).inflate(R.layout.fl_donghua, null);
                ImageView iv_gif = view.findViewById(R.id.iv_gif);
                ImageView iv_bg = view.findViewById(R.id.iv_bg);
                ImageView iv_head = view.findViewById(R.id.iv_head);
                TextView tv_name = view.findViewById(R.id.tv_name);
                FrameLayout fl_name = view.findViewById(R.id.fl_name);
                Glide.with(ChatActivity2.this).asGif().
                        load(mMsgList.get(mMsgList.size() - 1).getSelfcar()).into(iv_gif);
                Glide.with(ChatActivity2.this).asGif().
                        load("http://xta.zzmzrj.com/%E8%BF%9B%E5%9C%BA1.gif").into(iv_bg);
                GlideImgManager.glideLoader(ChatActivity2.this,
                        mMsgList.get(mMsgList.size() - 1).getProp_icon()
                        , iv_head, 0);
                tv_name.setText(mMsgList.get(mMsgList.size() - 1).getTo_msg() + ":来了");
                int kuan = getWindowManager().getDefaultDisplay().getWidth();
                int kuan2 = fl_name.getWidth();
                int kuan3 = kuan2 - kuan;
                ObjectAnimator.ofFloat(fl_name, "translationX", kuan3 + 350
                ).setDuration(2000).start();
                fl_layout.addView(view);
                mMsgList.remove(mMsgList.size() - 1);
                android.os.Message msg = new android.os.Message();
                msg.what = 10;
                mHandler.sendMessageDelayed(msg, 4000);
            }
        }
    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {

    }

    private void Approach() {
        UserCenterData userCenterData = (UserCenterData) SPHelper.getObjectFromShare(ChatActivity2.this, "user_bean");
        JsonRequestDoPrivateSendSpecialEffects.SendBean sendBean = new JsonRequestDoPrivateSendSpecialEffects.SendBean();
        sendBean.setTo_user_id(uId);
        sendBean.setTo_msg(userCenterData.getData().getUser_nickname());
        sendBean.setProp_icon(userCenterData.getData().getAvatar());
        sendBean.setSelfcar(userCenterData.getData().getCar());
        sendBean.setGroupID(identify);
        CustomMsgSpecialEffects customMsgSpecialEffects = new CustomMsgSpecialEffects();
        customMsgSpecialEffects.fillData(sendBean);
        Message message = new CustomMessage(customMsgSpecialEffects, LiveConstant.CustomMsgType.MSG_APPROACH);
        presenter.sendMessage(message.getMessage());
    }

    @Override
    protected void initPlayerDisplayData() {

    }


    @Override
    protected void onPause() {
        super.onPause();
        //退出聊天界面时输入框有内容，保存草稿
        if (input.getText().length() > 0) {
            TextMessage message = new TextMessage(input.getText());
            presenter.saveDraft(message.getMessage());
        } else {
            presenter.saveDraft(null);
        }
//        RefreshEvent.getInstance().onRefresh();
        presenter.readMessages();
        MediaUtil.getInstance().stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.stop();
        CuckooApplication.getInstances().setInPrivateChatPage(false);
    }


    /**
     * 显示消息
     *
     * @param message
     */
    @Override
    public void showMessage(TIMMessage message) {
        List<TIMElem> elems = new ArrayList<>();
        if (message != null) {
            for (int i = 0; i < message.getElementCount(); i++) {
                elems.add(message.getElement(i));
            }

        }

        //解析出来实体类不为空就是输入中那些消息
        SpannableStringBuilder string = getString(elems, this);
        InputListenerMsgText baseCommonBean = JSON.parseObject(string.toString(), InputListenerMsgText.class);

        //输入中，输入完
        if (baseCommonBean != null) {
            if ("EIMAMSG_InputStatus_Ing".equals(baseCommonBean.getActionParam())) {
                title.setTitleText(userName + " (正在输入中...)");
                return;
            } else if ("EIMAMSG_InputStatus_End".equals(baseCommonBean.getActionParam())) {
                title.setTitleText(userName);
                return;
            }
        } else {
            title.setTitleText(userName);
        }

        if (message == null) {
            adapter.notifyDataSetChanged();
        } else {
            Log.d("ret", "joker    接受到的消息 ");
            CustomMsg customMsg = parseToModel(message, CustomMsg.class);
//            customMsg.getType() != LiveConstant.CustomMsgType.MSG_APPROACH
            if (message.getElement(0).getType() != TIMElemType.GroupTips
            ) {
                if (null != customMsg) {
                    if (customMsg.getType() != LiveConstant.CustomMsgType.MSG_APPROACH) {
                        Message2 mMessage = MessageFactory2.getMessage(message);
                        if (null != message.getSenderGroupMemberProfile()) {
                            String name = message.getSenderGroupMemberProfile().getUser();
                            Api.getUserHomePageInfo(name, uId, uToken, new JsonCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    super.onSuccess(s, call, response);
                                    Log.d("ret", "joker     查询数据==" + s);
                                    if (!StringUtils.isEmpty(s)) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(s);
                                            JSONObject jsonData = jsonObject.getJSONObject("data");
                                            String name = jsonData.getString("user_nickname");
                                            String avatar = jsonData.getString("avatar");
                                            mMessage.setSendData(name, avatar);
                                            adapter.notifyDataSetChanged();
                                            Log.d("ret", "joker     发送者名字===" + name + "     头像" + avatar);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                @Override
                                public Context getContextToJson() {

                                    return null;
                                }
                            });
                        }


                        if (mMessage != null) {
                            if (messageList.size() == 0) {
                                mMessage.setHasTime(null);
                            } else {
                                mMessage.setHasTime(messageList.get(messageList.size() - 1).getMessage());
                            }
                            messageList.add(mMessage);
                            adapter.notifyDataSetChanged();
                            listView.setSelection(adapter.getCount() - 1);

                        }
                    } else {
                        //TODO 进场特效
                        CustomMsgSpecialEffects customMsgSpecialEffects = parseToModel(message, CustomMsgSpecialEffects.class);
                        if (!customMsgSpecialEffects.getTo_user_id().equals(uId)) {
                            mMsgList.add(customMsgSpecialEffects);
                        }
                    }
                } else {
                    Message2 mMessage = MessageFactory2.getMessage(message);
                    if (null != message.getSenderGroupMemberProfile()) {
                        String name = message.getSenderGroupMemberProfile().getUser();
                        Api.getUserHomePageInfo(name, uId, uToken, new JsonCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                super.onSuccess(s, call, response);
                                Log.d("ret", "joker     查询数据==" + s);
                                if (!StringUtils.isEmpty(s)) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(s);
                                        JSONObject jsonData = jsonObject.getJSONObject("data");
                                        String name = jsonData.getString("user_nickname");
                                        String avatar = jsonData.getString("avatar");
                                        mMessage.setSendData(name, avatar);
                                        adapter.notifyDataSetChanged();
                                        Log.d("ret", "joker     发送者名字===" + name + "     头像" + avatar);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public Context getContextToJson() {

                                return null;
                            }
                        });
                    }


                    if (mMessage != null) {
                        if (messageList.size() == 0) {
                            mMessage.setHasTime(null);
                        } else {
                            mMessage.setHasTime(messageList.get(messageList.size() - 1).getMessage());
                        }
                        messageList.add(mMessage);
                        adapter.notifyDataSetChanged();
                        listView.setSelection(adapter.getCount() - 1);

                    }
                }
            }
        }

    }


    public static SpannableStringBuilder getString(List<TIMElem> elems, Context context) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        for (int i = 0; i < elems.size(); ++i) {
            switch (elems.get(i).getType()) {
                case Custom:
                    TIMCustomElem textElem = (TIMCustomElem) elems.get(i);
                    String str = new String(textElem.getData());
                    stringBuilder.append(str);
                    break;
            }

        }
        return stringBuilder;
    }


    /**
     * 显示消息
     *
     * @param messages
     */
    @Override
    public void showMessage(List<TIMMessage> messages) {
        int newMsgNum = 0;
        if (messages.size() > 0) {
            for (int i = 0; i < messages.size(); ++i) {
                CustomMsg customMsg = parseToModel(messages.get(i), CustomMsg.class);
                if (messages.get(i).getElement(0).getType() != TIMElemType.GroupTips) {
                    if (null != customMsg) {
                        if (customMsg.getType() != LiveConstant.CustomMsgType.MSG_APPROACH) {
                            Message2 mMessage = MessageFactory2.getMessage(messages.get(i));
                            if (null != messages.get(i).getSenderGroupMemberProfile()) {
                                String name = messages.get(i).getSenderGroupMemberProfile().getUser();
                                Api.getUserHomePageInfo(name, uId, uToken, new JsonCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        super.onSuccess(s, call, response);
                                        Log.d("ret", "joker     查询数据==" + s);
                                        if (!StringUtils.isEmpty(s)) {
                                            try {
                                                JSONObject jsonObject = new JSONObject(s);
                                                JSONObject jsonData = jsonObject.getJSONObject("data");
                                                String name = jsonData.getString("user_nickname");
                                                String avatar = jsonData.getString("avatar");
                                                mMessage.setSendData(name, avatar);
                                                adapter.notifyDataSetChanged();
                                                Log.d("ret", "joker     发送者名字===" + name + "     头像" + avatar);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }

                                    @Override
                                    public Context getContextToJson() {

                                        return null;
                                    }
                                });
                            }

                            if (mMessage == null || messages.get(i).status() == TIMMessageStatus.HasDeleted) {
                                continue;
                            }
                    /*if (mMessage instanceof CustomMessage && LiveConstant.mapCustomMsgClass.get(((CustomMessage)mMessage).getType()) == null){
                        continue;
                    }*/
                            ++newMsgNum;
                            if (i != messages.size() - 1) {
                                mMessage.setHasTime(messages.get(i + 1));
                                Log.d("ret", "joker     发送者   " + mMessage.getSender());
                                messageList.add(0, mMessage);
                            } else {
                                mMessage.setHasTime(null);
                                Log.d("ret", "joker     发送者   " + mMessage.getSender());
                                messageList.add(0, mMessage);
                            }
                        }
                    } else {
                        Message2 mMessage = MessageFactory2.getMessage(messages.get(i));
                        if (null != messages.get(i).getSenderGroupMemberProfile()) {
                            String name = messages.get(i).getSenderGroupMemberProfile().getUser();
                            Api.getUserHomePageInfo(name, uId, uToken, new JsonCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    super.onSuccess(s, call, response);
                                    Log.d("ret", "joker     查询数据==" + s);
                                    if (!StringUtils.isEmpty(s)) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(s);
                                            JSONObject jsonData = jsonObject.getJSONObject("data");
                                            String name = jsonData.getString("user_nickname");
                                            String avatar = jsonData.getString("avatar");
                                            mMessage.setSendData(name, avatar);
                                            adapter.notifyDataSetChanged();
                                            Log.d("ret", "joker     发送者名字===" + name + "     头像" + avatar);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                @Override
                                public Context getContextToJson() {

                                    return null;
                                }
                            });
                        }

                        if (mMessage == null || messages.get(i).status() == TIMMessageStatus.HasDeleted) {
                            continue;
                        }
                    /*if (mMessage instanceof CustomMessage && LiveConstant.mapCustomMsgClass.get(((CustomMessage)mMessage).getType()) == null){
                        continue;
                    }*/
                        ++newMsgNum;
                        if (i != messages.size() - 1) {
                            mMessage.setHasTime(messages.get(i + 1));
                            Log.d("ret", "joker     发送者   " + mMessage.getSender());
                            messageList.add(0, mMessage);
                        } else {
                            mMessage.setHasTime(null);
                            Log.d("ret", "joker     发送者   " + mMessage.getSender());
                            messageList.add(0, mMessage);
                        }
                    }
                }
            }
        }
        adapter.notifyDataSetChanged();
        listView.setSelection(newMsgNum);
    }

    @Override
    public void showRevokeMessage(TIMMessageLocator timMessageLocator) {
        for (Message2 msg : messageList) {
            TIMMessageExt ext = new TIMMessageExt(msg.getMessage());
            if (ext.checkEquals(timMessageLocator)) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * 清除所有消息，等待刷新
     */
    @Override
    public void clearAllMessage() {
        messageList.clear();
    }

    /**
     * 发送消息成功
     *
     * @param message 返回的消息
     */
    @Override
    public void onSendMessageSuccess(TIMMessage message) {
        showMessage(message);
    }

    /**
     * 发送消息失败
     *
     * @param code 返回码
     * @param desc 返回描述
     */
    @Override
    public void onSendMessageFail(int code, String desc, TIMMessage message) {
        long id = message.getMsgUniqueId();
        for (Message2 msg : messageList) {
            if (msg.getMessage().getMsgUniqueId() == id) {
                switch (code) {
                    case 80001:
                        //发送内容包含敏感词
                        msg.setDesc(getString(R.string.chat_content_bad));
                        adapter.notifyDataSetChanged();
                        break;
                }
            }
        }

        adapter.notifyDataSetChanged();

    }

    /**
     * 发送图片消息
     */
    @Override
    public void sendImage() {
        if (!checkSendMessage(SEND_IMAGE_MESSAGE)) {
            return;
        }

        if (null != dataBean) {
            //家族发消息
            activation("2");
        }

        doSwitchMessageSend(SEND_IMAGE_MESSAGE);
    }

    /**
     * 发送照片消息
     */
    @Override
    public void sendPhoto() {
        Intent intent_photo = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent_photo.resolveActivity(getPackageManager()) != null) {
            File tempFile = FileUtil.getTempFile(FileUtil.FileType.IMG);
            if (tempFile != null) {
                fileUri = Uri.fromFile(tempFile);
            }
            intent_photo.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(intent_photo, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    //发送私照
    private void sendPrivateImg(String imgId, String imgUrl) {

        CustomMsgPrivatePhoto img = new CustomMsgPrivatePhoto();
        img.setId(imgId);
        img.setImg(imgUrl);

        Message message = new CustomMessage(img, LiveConstant.CustomMsgType.MSG_PRIVATE_GIFT);
        presenter.sendMessage(message.getMessage());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventClickPrivateRedEnvelopes(EventChatClickPrivateRedEnvelopesMessage var1) {
        Api.red_envelope_receive(uId, uToken, var1.getId(), new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (!com.muse.xiangta.utils.StringUtils.isEmpty(s)) {
                    try {
                        int code = new JSONObject(s).getInt("code");
                        if (code == 1) {
                            //领取成功
//                            跳转到红包详情
                            startActivity(new Intent(ChatActivity2.this, RedEnvelopesDetailsActivity.class)
                                    .putExtra("red_envelope_id", var1.getId()));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 发送文本消息
     */
    @Override
    public void sendText() {
        if (TextUtils.isEmpty(input.getText())) {
            showToast("发送内容不能为空!");
            return;
        }
        if (!Utils.dirtyWordFilter(input.getText().toString())) {
            showToast("发送内容包含敏感词汇!");
            return;
        }
        if (!checkSendMessage(SEND_TEXT_MESSAGE)) {
            return;
        }
        if (null != dataBean) {
            //家族发消息
            activation("2");
        }

        doSwitchMessageSend(SEND_TEXT_MESSAGE);
    }

    private void activation(String type) {
        Api.activation(uId, uToken, type, String.valueOf(dataBean.getFamily_id()), new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

            }
        });
    }

    /**
     * 发送文件
     */
    @Override
    public void sendFile() {
        if (!checkSendMessage(SEND_FILE_MESSAGE)) {
            return;
        }
        doSwitchMessageSend(SEND_FILE_MESSAGE);
    }


    /**
     * 开始发送语音消息
     */
    @Override
    public void startSendVoice() {

        voiceSendingView.setVisibility(View.VISIBLE);
        voiceSendingView.showRecording();
        recorder.startRecording();

    }

    /**
     * 结束发送语音消息
     */
    @Override
    public void endSendVoice() {
        voiceSendingView.release();
        voiceSendingView.setVisibility(View.GONE);
        recorder.stopRecording();
        if (mCancelSend) {
            if (recorder.getTimeInterval() < 1) {
                Toast.makeText(this, getResources().getString(R.string.chat_audio_too_short), Toast.LENGTH_SHORT).show();
            } else if (recorder.getTimeInterval() > 60) {
                Toast.makeText(this, getResources().getString(R.string.chat_audio_too_long), Toast.LENGTH_SHORT).show();
            } else {

                if (!checkSendMessage(SEND_VOICE_MESSAGE)) {
                    return;
                }
                if (null != dataBean) {
                    //家族发消息
                    activation("2");
                }
                doSwitchMessageSend(SEND_VOICE_MESSAGE);
            }
        }

    }

    /**
     * 发送小视频消息
     *
     * @param fileName 文件名
     */
    @Override
    public void sendVideo(String fileName) {
        Message message = new VideoMessage(fileName);
        presenter.sendMessage(message.getMessage());
    }

    private boolean mCancelSend = true;

    /**
     * 结束发送语音消息
     */
    @Override
    public void cancelSendVoice(boolean mCancelSend) {
        this.mCancelSend = mCancelSend;
    }

    //根据消息类型发送消息
    private void doSwitchMessageSend(int sendType) {

        switch (sendType) {
            case SEND_TEXT_MESSAGE: {
                Message message = new TextMessage(input.getText());
                presenter.sendMessage(message.getMessage());
                input.setText("");
                break;
            }
            case SEND_VOICE_MESSAGE: {
                Message message = new VoiceMessage(recorder.getTimeInterval(), recorder.getFilePath());
                presenter.sendMessage(message.getMessage());
                break;
            }
            case SEND_FILE_MESSAGE: {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, FILE_CODE);
                break;
            }
            case SEND_IMAGE_MESSAGE: {
                Intent intent_album = new Intent("android.intent.action.GET_CONTENT");
                intent_album.setType("image/*");
                startActivityForResult(intent_album, IMAGE_STORE);
                break;
            }

        }
    }

    //检测发送消息条件是否满足
    private boolean checkSendMessage(final int sendType) {

//        if (isPay == 1 && StringUtils.toInt(payCoin) != 0) {
//
//            //获取是否显示弹窗
//            boolean canshow = (boolean) SharedPreferencesUtils.getParam(this, "canShowDialog", true);
//
//            if (canshow) {
//
//                new MaterialDialog.Builder(this)
//                        .content("是否花费" + payCoin + RequestConfig.getConfigObj().getCurrency() + "发送付费消息？")
//                        .positiveText(R.string.agree)
//                        .negativeText(R.string.disagree)
//                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                            @Override
//                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                //弹窗弹出后置为false
//                                SharedPreferencesUtils.setParam(ChatActivity2.this, "canShowDialog", false);
//
//                                toChat(sendType);
//                            }
//                        })
//                        .show();
//
//                return false;
//            } else {
//
//                toChat(sendType);
//
//                return false;
//            }
//
//        } else if (sex == 2 && isAuth != 1) {

//            DialogHelp.getMessageDialog(ChatActivity2.this, "女性需要认证才可以发送消息").show();
//            return false;
//        }

        return true;
    }

    private void toChat(final int sendType) {
//        Api.doRequestChatPay(SaveData.getInstance().getId(), SaveData.getInstance().getToken(), identify, new StringCallback() {
//            @Override
//            public void onSuccess(String s, Call call, Response response) {
//
//                JsonRequestPrivateChatPay data = (JsonRequestPrivateChatPay) JsonRequestBase.getJsonObj(s, JsonRequestPrivateChatPay.class);
//                if (data.getCode() == 1) {
        doSwitchMessageSend(sendType);
//                } else if (data.getCode() == 10002) {
//                    Common.showRechargeDialog(ChatActivity.this, "余额不足，请先充值！");
//                } else {
//                    ToastUtils.showShort(data.getMsg());
//                }
//            }
//        });
    }


    /**
     * 正在发送
     */
    @Override
    public void sending() {
        if (type == TIMConversationType.C2C) {
            //Message message = new CustomMessage(CustomMessage.Type.TYPING);
            //presenter.sendOnlineMessage(message.getMessage());
        }
    }

    /**
     * 显示草稿
     */
    @Override
    public void showDraft(TIMMessageDraft draft) {
        input.getText().append(TextMessage.getString(draft.getElems(), this));
    }

    @Override
    public void videoAction() {

        //  录制视频
        //Intent intent = new Intent(this, TCVideoRecordActivity.class);
        //startActivityForResult(intent, VIDEO_RECORD);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAction(int id) {
        if (id == R.id.iv_liwu) {//礼物
            clickShowGift();
        } else if (id == R.id.iv_si) {
            //选择私照发送
            clickSelectPrivatePhoto();
        } else if (id == R.id.iv_hong) {
            //红包
            dialog_hongbao();
        } else if (id == R.id.iv_cai) {
            //猜拳
            caiquan();
        } else if (id == R.id.iv_shai) {
            //筛子
            shaizi();
        }
    }

    private void shaizi() {
        Random rand = new Random();
        int suiji = rand.nextInt(5);
        Log.d("ret", "joker    随机数==" + rand.nextInt(5));
        JsonRequestDoPrivateSendGuessing.SendBean sendBean = new JsonRequestDoPrivateSendGuessing.SendBean();
        sendBean.setGifimg("");
        sendBean.setStaticimg(shaiziStr[suiji]);
        CustomMsgDice customMsgGuessing = new CustomMsgDice();
        customMsgGuessing.fillData(sendBean);
        Message message = new CustomMessage(customMsgGuessing, LiveConstant.CustomMsgType.CY_CHAT_SHAIZI);
        presenter.sendMessage(message.getMessage());

        if (null != dataBean) {
            //家族发消息
            activation("2");
        }
    }

    private void caiquan() {
        Random rand = new Random();
        int suiji = rand.nextInt(3);
        Log.d("ret", "joker    随机数==" + rand.nextInt(3));
        JsonRequestDoPrivateSendGuessing.SendBean sendBean = new JsonRequestDoPrivateSendGuessing.SendBean();
        sendBean.setGifimg("");
        sendBean.setStaticimg(caiquanStr[suiji]);
        CustomMsgGuessing customMsgGuessing = new CustomMsgGuessing();
        customMsgGuessing.fillData(sendBean);
        Message message = new CustomMessage(customMsgGuessing, LiveConstant.CustomMsgType.CY_CHAT_CAIQUAN);
        presenter.sendMessage(message.getMessage());

        if (null != dataBean) {
            //家族发消息
            activation("2");
        }
    }

    private void dialog_hongbao() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.show();  //注意：必须在window.setContentView之前show
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_hongbao2);
        // 这样子 第二和第三个按钮的空隙才会显示出来
//        window.setGravity(Gravity.BOTTOM);//这个也很重要，将弹出菜单的位置设置为底部
        window.setWindowAnimations(R.style.animation_bottom_menu);//菜单进入和退出屏幕的动画，实现了上下滑动的动画效果
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);//设置菜单的尺寸

        FrameLayout fl_comm = dialog.findViewById(R.id.fl_comm);//发红包
        LastInputEditText et_title = dialog.findViewById(R.id.et_title);//标题
        LastInputEditText et_number = dialog.findViewById(R.id.et_number);//钻石数量
        LastInputEditText et_number2 = dialog.findViewById(R.id.et_number2);//红包数量

        fl_comm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StringUtils.isEmpty(et_number.getText().toString().trim())
                        && (Integer.valueOf(et_number.getText().toString().trim()) > 100)) {
                    showToast("请输入有效钻石金额");
                    return;
                } else if (StringUtils.isEmpty(et_number2.getText().toString().trim())) {
                    showToast("请输入有效红包数量");
                    return;
                } else {
                    if (null != dataBean) {
                        //家族发消息
                        activation("4");
                    }


                    if (com.muse.xiangta.utils.StringUtils.isEmpty(et_title.getText().toString().trim())) {
                        distribute("恭喜发财",
                                et_number.getText().toString().trim(), et_number2.getText().toString().trim(), dialog);
                    } else {
                        distribute(et_title.getText().toString().trim(),
                                et_number.getText().toString().trim(), et_number2.getText().toString().trim(), dialog);
                    }
                }
            }
        });
    }

    private void distribute(final String title, String amount, String count, AlertDialog dialog) {
        Api.distribute2(uId, uToken, title, amount, count, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (!com.muse.xiangta.utils.StringUtils.isEmpty(s)) {
                    try {
                        int code = new JSONObject(s).getInt("code");
                        if (code == 1) {
                            String red_envelope_id = new JSONObject(s).getJSONObject("data").getString("red_envelope_id");
                            JsonRequestDoPrivateSendRedEnvelopes.SendBean sendBean = new JsonRequestDoPrivateSendRedEnvelopes.SendBean();
                            sendBean.setRpID(red_envelope_id);
                            sendBean.setRptit(title);
                            sendBean.setRpnum(amount);
                            sendBean.setRppnum(count);
                            sendBean.setRptype("2");
                            CustomMsgRedEnvelopes customMsgRedEnvelopes = new CustomMsgRedEnvelopes();
                            customMsgRedEnvelopes.fillData(sendBean);
                            Message message = new CustomMessage(customMsgRedEnvelopes, LiveConstant.CustomMsgType.MSG_ALL_RED_ENVELOPES);
                            presenter.sendMessage(message.getMessage());
                            if (null != dialog) {
                                dialog.dismiss();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Message2 message = messageList.get(info.position);
        menu.add(0, 1, Menu.NONE, getString(R.string.chat_del));
        if (message.isSendFail()) {
            menu.add(0, 2, Menu.NONE, getString(R.string.chat_resend));
        } else if (message.getMessage().isSelf()) {
            menu.add(0, 4, Menu.NONE, getString(R.string.chat_pullback));
        }
        if (message instanceof ImageMessage2 || message instanceof FileMessage2) {
            menu.add(0, 3, Menu.NONE, getString(R.string.chat_save));
        }
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Message2 message = messageList.get(info.position);
        switch (item.getItemId()) {
            case 1:
                message.remove();
                messageList.remove(info.position);
                adapter.notifyDataSetChanged();
                break;
            case 2:
                messageList.remove(message);
                presenter.sendMessage(message.getMessage());
                break;
            case 3:
                message.save();
                break;
            case 4:
                presenter.revokeMessage(message.getMessage());
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 88) {
            identifyStr = data.getExtras().getString("id");
            Log.d("ret", "joker   返回的id===" + identifyStr);
            if (giftBottomDialog == null) {
                giftBottomDialog = new GiftBottomDialog(this, identifyStr);
                giftBottomDialog.hideMenu();
                giftBottomDialog.setDoSendGiftListen(this);
            }
            giftBottomDialog.show();
        }
        if (resultCode == 50) {
            finish();
        }
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK && fileUri != null) {
                showImagePreview(fileUri.getPath());
            }
        } else if (requestCode == IMAGE_STORE) {
            if (resultCode == RESULT_OK && data != null) {
                showImagePreview(FileUtil.getFilePath(this, data.getData()));
            }

        } else if (requestCode == FILE_CODE) {
            if (resultCode == RESULT_OK) {
                sendFile(FileUtil.getFilePath(this, data.getData()));
            }
        } else if (requestCode == IMAGE_PREVIEW) {
            if (resultCode == RESULT_OK) {
                boolean isOri = data.getBooleanExtra("isOri", false);
                String path = data.getStringExtra("path");
                File file = new File(path);
                if (file.exists()) {
                    final BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(path, options);
                    if (file.length() == 0 && options.outWidth == 0) {
                        Toast.makeText(this, getString(R.string.chat_file_not_exist), Toast.LENGTH_SHORT).show();
                    } else {
                        if (file.length() > 1024 * 1024 * 10) {
                            Toast.makeText(this, getString(R.string.chat_file_too_large), Toast.LENGTH_SHORT).show();
                        } else {
                            Message message = new ImageMessage(path, isOri);
                            presenter.sendMessage(message.getMessage());
                        }
                    }
                } else {
                    Toast.makeText(this, getString(R.string.chat_file_not_exist), Toast.LENGTH_SHORT).show();
                }
            }
        } else if (requestCode == VIDEO_RECORD) {
            if (resultCode == RESULT_OK) {
                String videoPath = data.getStringExtra("videoPath");
                String coverPath = data.getStringExtra("coverPath");
                long duration = data.getLongExtra("duration", 0);
                Message message = new UGCMessage(videoPath, coverPath, duration);
                presenter.sendMessage(message.getMessage());
            }
        } else if (requestCode == RESULT_SELECT_PRIVATE_PHOTO) {
            if (resultCode == RESULT_OK) {
                sendPrivateImg(data.getStringExtra("img_id"), data.getStringExtra("img_url"));

                if (null != dataBean) {
                    //家族发消息
                    activation("2");
                }
            }
        }

    }


    private void showImagePreview(String path) {
        if (path == null) return;
        Intent intent = new Intent(this, ImagePreviewActivity.class);
        intent.putExtra("path", path);
        startActivityForResult(intent, IMAGE_PREVIEW);
    }

    private void sendFile(String path) {
        if (path == null) return;
        File file = new File(path);
        if (file.exists()) {
            if (file.length() > 1024 * 1024 * 10) {
                Toast.makeText(this, getString(R.string.chat_file_too_large), Toast.LENGTH_SHORT).show();
            } else {
                Message message = new FileMessage(path);
                presenter.sendMessage(message.getMessage());
            }
        } else {
            Toast.makeText(this, getString(R.string.chat_file_not_exist), Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 将标题设置为对象名称
     */
    private Runnable resetTitle = new Runnable() {
        @Override
        public void run() {
            TemplateTitle title = (TemplateTitle) findViewById(R.id.chat_title);
            title.setTitleBackgroundColor(getResources().getColor(R.color.white));
            title.setTitleText(titleStr);
        }
    };

    @OnClick({R.id.chat_attribute_tv, R.id.chat_attribute_close_iv})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.chat_attribute_tv:
                requestFollow();
                break;
            case R.id.chat_attribute_close_iv:
                attributeCv.setVisibility(View.GONE);
                break;

            default:
                break;
        }
    }

    private void requestFollow() {
        Api.doLoveTheUser(
                identify,
                uId,
                uToken,
                new JsonCallback() {
                    @Override
                    public Context getContextToJson() {
                        return getNowContext();
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        JsonRequest requestObj = JsonRequest.getJsonObj(s);
                        if (requestObj.getCode() == 1) {
                            attributeCv.setVisibility(View.GONE);
                            showToastMsg(getString(R.string.follow_success));
                        } else {
                            ToastUtils.showLong(requestObj.getMsg());
                        }
                    }
                }
        );
    }

    //点击打赏礼物
    private void clickShowGift() {
        //先选择人员
        if (null != dataBean) {
            //是家族
            startActivityForResult(new Intent(ChatActivity2.this, MemberListActivity.class)
                    .putExtra("family_id", String.valueOf(dataBean.getFamily_id())), 88);
        } else {
            startActivityForResult(new Intent(ChatActivity2.this, MemberGroupListActivity.class)
                    .putExtra("family_id", family_id), 88);
        }

//        if (giftBottomDialog == null) {
//            giftBottomDialog = new GiftBottomDialog(this, identify);
//            giftBottomDialog.hideMenu();
//            giftBottomDialog.setDoSendGiftListen(this);
//        }
//        giftBottomDialog.show();
    }

    //点击打赏金币
    private void clickReward() {
        new CuckooRewardCoinDialog(this).show();
    }

    //选择私照
    private void clickSelectPrivatePhoto() {

        Intent intent = new Intent(this, PrivatePhotoActivity.class);
        intent.putExtra(PrivatePhotoActivity.USER_ID, SaveData.getInstance().getId());
        intent.putExtra(PrivatePhotoActivity.USER_NAME, userName);
        intent.putExtra(PrivatePhotoActivity.ACTION_TYPE, 1);
        startActivityForResult(intent, RESULT_SELECT_PRIVATE_PHOTO);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventClickPrivateImg(EventChatClickPrivateImgMessage var1) {
        Common.requestSelectPic(this, var1.getId());
    }

    /**
     * 送礼物
     *
     * @param sendGif
     */
    @Override
    public void onSuccess(JsonRequestDoPrivateSendGif sendGif) {

        CustomMsgPrivateGift gift = new CustomMsgPrivateGift();
        gift.fillData(sendGif.getSend());
        Message message = new CustomMessage(gift, LiveConstant.CustomMsgType.MSG_PRIVATE_GIFT);
        presenter.sendMessage(message.getMessage());

        if (null != dataBean) {
            //家族发消息
            activation("3");
        }
        //giftBottomDialog.dismiss();
    }


    @Override
    public void onChildrenClick(int id, int position) {
        switch (id) {
            case R.id.leftAvatar:
                String idStr = messageList.get(position).getSender();
                Common.jumpUserPage(this, idStr);
                break;
            case R.id.rightAvatar:
                Common.jumpUserPage(this, SaveData.getInstance().getId());
                break;
            default:
                break;
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getAvatar() {
        return "";
    }

    public <T extends CustomMsg> T parseToModel(TIMMessage message, Class<T> clazz) {
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
