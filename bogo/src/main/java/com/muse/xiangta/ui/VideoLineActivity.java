package com.muse.xiangta.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.faceunity.nama.FURenderer;
import com.faceunity.nama.ui.FaceUnityView;
import com.faceunity.nama.utils.CameraUtils;
import com.lzy.okgo.callback.StringCallback;
import com.muse.chat.model.CustomMessage;
import com.muse.chat.model.Message;
import com.muse.tisdk.VideoPreProcessing;
import com.muse.xiangta.CuckooApplication;
import com.muse.xiangta.LiveConstant;
import com.muse.xiangta.PreprocessorFaceUnity;
import com.muse.xiangta.R;
import com.muse.xiangta.RtcEngineEventHandler;
import com.muse.xiangta.RtcVideoConsumer;
import com.muse.xiangta.adapter.GiftInfoAdapter;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity2;
import com.muse.xiangta.business.CuckooVideoLineTimeBusiness;
import com.muse.xiangta.dialog.GiftBottomDialog;
import com.muse.xiangta.dialog.InputTextDialog;
import com.muse.xiangta.event.EImOnCloseVideoLine;
import com.muse.xiangta.event.EImOnNewMessages;
import com.muse.xiangta.event.EImOnPrivateMessage;
import com.muse.xiangta.event.EImVideoCallEndMessages;
import com.muse.xiangta.inter.JsonCallback;
import com.muse.xiangta.json.JsonRequest;
import com.muse.xiangta.json.JsonRequestBase;
import com.muse.xiangta.json.JsonRequestDoPrivateSendGif;
import com.muse.xiangta.json.JsonRequestTarget;
import com.muse.xiangta.json.JsonRequestVideoEndInfo;
import com.muse.xiangta.json.jsonmodle.TargetUserData;
import com.muse.xiangta.manage.RequestConfig;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.modle.ConfigModel;
import com.muse.xiangta.modle.GiftAnimationModel;
import com.muse.xiangta.modle.UserChatData;
import com.muse.xiangta.modle.UserModel;
import com.muse.xiangta.modle.custommsg.CustomMsg;
import com.muse.xiangta.modle.custommsg.CustomMsgPrivateGift;
import com.muse.xiangta.modle.custommsg.TIMMsgModel;
import com.muse.xiangta.ui.common.Common;
import com.muse.xiangta.utils.BGTimedTaskManage;
import com.muse.xiangta.utils.DialogHelp;
import com.muse.xiangta.utils.StringUtils;
import com.muse.xiangta.utils.Utils;
import com.muse.xiangta.utils.im.IMHelp;
import com.muse.xiangta.widget.CircleTextProgressbar;
import com.muse.xiangta.widget.GiftAnimationContentView;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMValueCallBack;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

import butterknife.BindView;
import butterknife.OnClick;
import cn.tillusory.sdk.TiSDKManager;
import cn.tillusory.tiui.TiPanelLayout;
import de.hdodenhof.circleimageview.CircleImageView;
import io.agora.capture.video.camera.CameraVideoManager;
import io.agora.capture.video.camera.Constant;
import io.agora.capture.video.camera.VideoCapture;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoEncoderConfiguration;
import okhttp3.Call;
import okhttp3.Response;

/**
 * ???????????????????????????????????????
 * ??????????????????
 */

public class VideoLineActivity extends BaseActivity2 implements SensorEventListener, RtcEngineEventHandler, GiftBottomDialog.DoSendGiftListen, CuckooVideoLineTimeBusiness.VideoLineTimeBusinessCallback, InputTextDialog.SendMsgListener {

    public static final String IS_BE_CALL = "IS_BE_CALL";
    public static final String IS_NEED_CHARGE = "IS_NEED_CHARGE";
    public static final String VIDEO_DEDUCTION = "VIDEO_DEDUCTION";
    public static final String CALL_TYPE = "CALL_TYPE";
    public static final String CALL_USER_DATA = "CALL_USER_DATA";
    public static final String VIDEO_PX = "VIDEO_PX";

    //????????????
    public static final String FREE_TIME = "FREE_TIME";

    //????????????
    @BindView(R.id.close_video_chat)
    ImageView closeVideo;

    //???????????????
    @BindView(R.id.videochat_switch)
    ImageView cutCamera;

    //??????????????????
    @BindView(R.id.videochat_voice)
    ImageView isSoundOut;

    //????????????
    @BindView(R.id.videochat_gift)
    ImageView videoGift;

    //????????????
    @BindView(R.id.ll_gift_content)
    GiftAnimationContentView mGiftAnimationContentView;

    //????????????
    @BindView(R.id.videochat_unit_price)
    TextView tv_chat_unit_price;

    //??????????????????
    @BindView(R.id.videochat_timer)
    Chronometer videoChatTimer;

    //????????????
    @BindView(R.id.this_player_img)
    CircleImageView headImage;

    //?????????
    @BindView(R.id.this_player_number)
    TextView thisPlayerNumber;

    //??????
    @BindView(R.id.this_player_name)
    TextView nickName;

    //????????????
    @BindView(R.id.this_player_loveme)
    TextView tv_follow;

    @BindView(R.id.tv_time_info)
    TextView tv_time_info;

    @BindView(R.id.tv_reward)
    TextView tv_reward;

    @BindView(R.id.user_coin)
    TextView tv_userCoin;

    @BindView(R.id.lv_live_room)
    RecyclerView giftInfoRv;

    @BindView(R.id.root_view)
    RelativeLayout root_view;

    @BindView(R.id.progress_bar_time)
    CircleTextProgressbar progress_bar_time;

    @BindView(R.id.iv_open_beauty)
    ImageView iv_open_beauty;

    private ImageView iv_close_camera;
    private ImageView iv_video_chat_lucky_corn;

    //??????????????????
    private boolean isNeedCharge = false;

    private VideoCanvas smallVideoCanvas;
    private VideoCanvas bigVideoCanvas;

    private GiftBottomDialog giftBottomDialog;

    private BGTimedTaskManage getVideoTimeInfoTask;
    private CuckooVideoLineTimeBusiness cuckooVideoLineTimeBusiness;

    private List<String> guardInfoList = new ArrayList<>();

    private GiftInfoAdapter giftInfoAdapter;

    private int videoUid;
    private int videoViewStatus = 1;
    private TIMConversation conversation;

    //??????????????????
    private String videoDeduction;
    private String video_px;
    private int callType;


    private UserChatData chatData;
    private boolean isOpenCamera = true;

    // --- boGoBeauty start ---
    private TiSDKManager tiSDKManager;
    private VideoPreProcessing mVideoPreProcessing;
    private InputTextDialog inputTextDialog;

    // --- boGoBeauty end ---


    @Override
    protected Context getNowContext() {
        return VideoLineActivity.this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_video_chat;
    }

    @BindView(R.id.top_holder)
    View top;

    @BindView(R.id.tl_view)
    TiPanelLayout tl_view;

    /**
     * ??????
     */
    private final static String TAG = VideoLineActivity.class.getSimpleName();

    private boolean isMeiYan = false;

    private static final int CAPTURE_WIDTH = 1280;
    private static final int CAPTURE_HEIGHT = 720;
    private static final int CAPTURE_FRAME_RATE = 24;

    private CameraVideoManager mVideoManager;
    private FURenderer mFURenderer;
    private FrameLayout mRemoteViewContainer;
    private TextView mTrackingText;

    private FaceUnityView faceUnityView;
    private int mRemoteUid = -1;
    private boolean mFinished = false;
    private int mCameraFace = FURenderer.CAMERA_FACING_FRONT;
    private SensorManager mSensorManager;


    private RtcEngine mRtcEngine;

    /**
     * ??????
     */

    @Override
    protected void initView() {
        chatData = getIntent().getParcelableExtra(CALL_USER_DATA);
        video_px = getIntent().getStringExtra(VIDEO_PX);

        QMUIStatusBarHelper.translucent(this);
        Utils.initTransP(top);

        iv_close_camera = findViewById(R.id.iv_close_camera);
        iv_video_chat_lucky_corn = findViewById(R.id.iv_video_chat_lucky_corn);

        iv_video_chat_lucky_corn.setOnClickListener(this);
        iv_open_beauty.setOnClickListener(this);

        // ????????????????????????
        progress_bar_time.setProgressColor(getResources().getColor(R.color.admin_color));
        // ???????????????????????????
        progress_bar_time.setOutLineColor(getResources().getColor(R.color.transparent));

        mGiftAnimationContentView.startHandel();

        //????????????
        callType = getIntent().getIntExtra(CALL_TYPE, 0);
        //??????????????????
        videoDeduction = getIntent().getStringExtra(VIDEO_DEDUCTION);
        tv_chat_unit_price.setText(String.format(Locale.getDefault(), "%s%s/??????", videoDeduction, RequestConfig.getConfigObj().getCurrency()));

        videoChatTimer.setTextColor(getResources().getColor(R.color.white));
        String msgAlert = ConfigModel.getInitData().getVideo_call_msg_alert();
        if (!TextUtils.isEmpty(msgAlert)) {
            ToastUtils.showLong(msgAlert);
        }

        tv_time_info.setText(String.format(Locale.CHINA, "????????????:%s", videoDeduction));
        tv_reward.setText("????????????:");
        tv_userCoin.setText("????????????:");


        //????????????
        giftInfoRv.setLayoutManager(new LinearLayoutManager(this));
        giftInfoAdapter = new GiftInfoAdapter(guardInfoList, this);
        giftInfoRv.setAdapter(giftInfoAdapter);
    }

    private void initUI() {
        initRemoteViewLayout();
    }

    private void initRemoteViewLayout() {
        mRemoteViewContainer = findViewById(R.id.remote_video_view);
    }

    private void initRoom() {
        initVideoModule();
        if (null == mRtcEngine) {
            mRtcEngine = CuckooApplication.getInstance().initRtcEngine();
            mRtcEngine.enableVideo();
            mRtcEngine.setVideoSource(new RtcVideoConsumer());
        } else {
            mRtcEngine.setVideoSource(new RtcVideoConsumer());
        }
        joinChannel();
    }

    private void initVideoModule() {
        mVideoManager = videoManager();
        mVideoManager.setCameraStateListener(new VideoCapture.VideoCaptureStateListener() {
            @Override
            public void onFirstCapturedFrame(int width, int height) {
                Log.i(TAG, "onFirstCapturedFrame: " + width + "x" + height);
            }

            @Override
            public void onCameraCaptureError(int error, String msg) {
                Log.i(TAG, "onCameraCaptureError: error:" + error + " " + msg);
                if (mVideoManager != null) {
                    // When there is a camera error, the capture should
                    // be stopped to reset the internal states.
                    mVideoManager.stopCapture();
                }
            }
        });

        mTrackingText = findViewById(R.id.iv_face_detect);
        faceUnityView = findViewById(R.id.fu_view);
        mFURenderer = ((PreprocessorFaceUnity) mVideoManager.getPreprocessor()).getFURenderer();
        faceUnityView.setModuleManager(mFURenderer);
        mFURenderer.setOnTrackStatusChangedListener(new FURenderer.OnTrackStatusChangedListener() {
            @Override
            public void onTrackStatusChanged(int type, int status) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTrackingText.setText(type == FURenderer.TRACK_TYPE_FACE ? R.string.toast_not_detect_face : R.string.toast_not_detect_face_or_body);
                        mTrackingText.setVisibility(status > 0 ? View.INVISIBLE : View.VISIBLE);
                    }
                });
            }
        });

        mVideoManager.setPictureSize(CAPTURE_WIDTH, CAPTURE_HEIGHT);
        mVideoManager.setFrameRate(CAPTURE_FRAME_RATE);
        mVideoManager.setFacing(Constant.CAMERA_FACING_FRONT);
        mVideoManager.setLocalPreviewMirror(Constant.MIRROR_MODE_AUTO);

        TextureView localVideo = findViewById(R.id.local_video_view);
        mVideoManager.setLocalPreview(localVideo);
    }

    @Override
    public void onStart() {
        super.onStart();
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        mVideoManager.startCapture();
        mFURenderer.queueEvent(new Runnable() {
            @Override
            public void run() {
                mFURenderer.onSurfaceCreated();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGiftAnimationContentView != null) {
            mGiftAnimationContentView.stopHandel();
        }
        if (!mFinished) {
            mVideoManager.stopCapture();
        }
        mSensorManager.unregisterListener(this);
    }


    @Override
    protected void initData() {
        //??????
        initUI();
        initRoom();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //??????

        //?????????????????????s???
        int freeTime = getIntent().getIntExtra(FREE_TIME, 0);

        //isBeCall = getIntent().getBooleanExtra(IS_BE_CALL, false);
        isNeedCharge = getIntent().getBooleanExtra(IS_NEED_CHARGE, false);

        // ????????????????????????????????????3000?????????
        progress_bar_time.setTimeMillis(freeTime * 1000);
        cuckooVideoLineTimeBusiness = new CuckooVideoLineTimeBusiness(this, isNeedCharge, freeTime, chatData.getUserModel().getId(), this);

        if (freeTime == 0) {
            progress_bar_time.setVisibility(View.GONE);
        } else {
            progress_bar_time.start();
        }

        if (isNeedCharge) {
            initBeCallView();
            initBeCallAction();
        } else {
            initCallView();
//            initCallAction();
        }

        conversation = TIMManager.getInstance().getConversation(TIMConversationType.C2C, chatData.getUserModel().getId());
        //????????????
        videoChatTimer.start();

        //??????????????????????????????
        getVideoTimeInfoTask = new BGTimedTaskManage();
        //????????????60s???????????????
        getVideoTimeInfoTask.setTime(10 * 6000);
        getVideoTimeInfoTask.setTimeTaskRunnable(new BGTimedTaskManage.BGTimeTaskRunnable() {
            @Override
            public void onRunTask() {
                requestGetVideoCallTimeInfo();
            }
        });

        getVideoTimeInfoTask.startRunnable(false);

        requestUserData();
    }


    @Override
    protected void initSet() {

    }


    /**
     * ?????????????????????
     */
    private void initBeCallAction() {

    }

    /**
     * ????????????????????????View?????????
     */
    private void initCallView() {
        tv_chat_unit_price.setVisibility(View.GONE);
        //?????????????????????
        tv_time_info.setVisibility(View.VISIBLE);
        tv_reward.setVisibility(View.VISIBLE);
        tv_userCoin.setVisibility(View.VISIBLE);
    }

    /**
     * ???????????????????????????View?????????
     */
    private void initBeCallView() {
        tv_chat_unit_price.setVisibility(View.GONE);
        //?????????????????????
        tv_time_info.setVisibility(View.GONE);
        tv_reward.setVisibility(View.GONE);
        tv_userCoin.setVisibility(View.GONE);
    }

    @Override
    protected void initPlayerDisplayData() {

    }

    /////////////////////////////////////////////??????????????????/////////////////////////////////////////
    @OnClick({R.id.iv_open_beauty, R.id.videochat_gift, R.id.videochat_screen, R.id.close_video_chat,
            R.id.videochat_switch, R.id.videochat_voice, R.id.iv_close_camera,
            R.id.iv_video_chat_lucky_corn, R.id.say_rl})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_open_beauty:
                if (isMeiYan == false) {
                    faceUnityView.setVisibility(View.VISIBLE);
                    isMeiYan = true;
                } else {
                    faceUnityView.setVisibility(View.GONE);
                    isMeiYan = false;
                }
//                tl_view.open();
                break;
            case R.id.videochat_gift:
                clickOpenGiftDialog();
                break;
            case R.id.videochat_screen:
                //??????
                break;
            case R.id.this_player_loveme:
                doLoveHer();
                break;
            case R.id.close_video_chat:
                logoutChat();
                break;
            case R.id.videochat_switch:
                doCutCamera();
                break;
            case R.id.videochat_voice:
                onLocalAudioMuteClicked();
                break;
            case R.id.this_player_img:
                Common.jumpUserPage(VideoLineActivity.this, chatData.getUserModel().getId());
                break;
            case R.id.video_chat_small:
//                switchVideoView();
                break;
            case R.id.iv_close_camera:
                closeCamera();
                break;
            case R.id.iv_video_chat_lucky_corn:
                Intent intent = new Intent(this, DialogH5Activity.class);
                intent.putExtra("uri", ConfigModel.getInitData().getApp_h5().getTurntable_url());
                startActivity(intent);
                break;
            //?????????
            case R.id.say_rl:
                changeEditStatus(true);
                break;
            default:
                break;
        }
    }


    //???????????????
    protected void changeEditStatus(boolean status) {
        if (status) {
            inputTextDialog = new InputTextDialog(this);
            inputTextDialog.show();
            inputTextDialog.setSendMsgListener(this);
//
        } else {

            if (inputTextDialog.isShowing()) {
                inputTextDialog.dismiss();
            }
        }

    }


    //???????????????
    private void closeCamera() {
        if (isOpenCamera) {
            //???????????????
            mRtcEngine.muteLocalVideoStream(true);
            iv_close_camera.setImageResource(R.mipmap.ic_close_camera);
            ToastUtils.showLong("??????????????????");
        } else {
            mRtcEngine.muteLocalVideoStream(false);
            iv_close_camera.setImageResource(R.mipmap.ic_open_camera);
            ToastUtils.showLong("??????????????????");
        }
        isOpenCamera = !isOpenCamera;
    }


    //?????????????????????????????????????????????--uid(uid?????????????????????)
    private void joinChannel() {
        mRtcEngine.setVideoEncoderConfiguration(new VideoEncoderConfiguration(
                VideoEncoderConfiguration.VD_640x360,
                VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_24,
                VideoEncoderConfiguration.STANDARD_BITRATE,
                VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT));
        mRtcEngine.setClientRole(io.agora.rtc.Constants.CLIENT_ROLE_BROADCASTER);
        mRtcEngine.enableLocalAudio(true);
        mRtcEngine.joinChannel(null, chatData.getChannelName(), null, 0);
    }

    //??????????????????
    public void onLocalAudioMuteClicked() {
        if (isSoundOut.isSelected()) {
            isSoundOut.setSelected(false);
            isSoundOut.setImageResource(R.drawable.icon_call_unmute);
        } else {
            isSoundOut.setSelected(true);
            isSoundOut.setImageResource(R.drawable.icon_call_muted);
        }
        mRtcEngine.muteLocalAudioStream(isSoundOut.isSelected());
    }

    /**
     * ????????????????????????
     */
    private void doCutCamera() {
        mVideoManager.switchCamera();
        mCameraFace = FURenderer.CAMERA_FACING_FRONT - mCameraFace;
        mFURenderer.onCameraChanged(mCameraFace, CameraUtils.getCameraOrientation(mCameraFace));
    }

    private void operationVideoAndAudio(boolean muted) {
        mRtcEngine.muteLocalAudioStream(muted);
        mRtcEngine.muteLocalVideoStream(muted);
        mRtcEngine.muteAllRemoteVideoStreams(muted);
        mRtcEngine.muteAllRemoteAudioStreams(muted);
    }

    //????????????
    private void leaveChannel() {
        if (rtcEngine() != null) {
            mRtcEngine.leaveChannel();
        }
    }

    //????????????
    private void hangUpVideo() {
        operationVideoAndAudio(true);
        showLoadingDialog(getString(R.string.loading_huang_up));
        cuckooVideoLineTimeBusiness.doHangUpVideo();
        if (getVideoTimeInfoTask != null) {
            getVideoTimeInfoTask.stopRunnable();
        }
    }

    //????????????
    private void clickOpenGiftDialog() {
        if (giftBottomDialog == null) {
            giftBottomDialog = new GiftBottomDialog(this, chatData.getUserModel().getId());
            giftBottomDialog.setType(1);
            giftBottomDialog.setChanelId(chatData.getChannelName());
            giftBottomDialog.setDoSendGiftListen(this);
        }

        if (!isNeedCharge) {
            giftBottomDialog.hideMenu();
        }

        giftBottomDialog.show();
    }


    //??????????????????
    private void pushGiftMsg(CustomMsgPrivateGift giftCustom) {

        GiftAnimationModel giftAnimationModel = new GiftAnimationModel();

        giftAnimationModel.setUserAvatar(giftCustom.getSender().getAvatar());
        giftAnimationModel.setUserNickname(giftCustom.getSender().getUser_nickname());
        giftAnimationModel.setMsg(giftCustom.getFrom_msg());
        giftAnimationModel.setGiftIcon(giftCustom.getProp_icon());

        if (mGiftAnimationContentView != null) {

            //????????????????????????
            if (!isNeedCharge) {
                String from_msg = giftCustom.getTo_msg();
                guardInfoList.add("????????????:" + from_msg);
            } else {
                String from_msg = giftCustom.getFrom_msg();
                guardInfoList.add("????????????:" + from_msg);
            }


            //????????????
            giftInfoAdapter.setData(guardInfoList);
            giftInfoAdapter.notifyDataSetChanged();
            mGiftAnimationContentView.addGift(giftAnimationModel);
        }
    }


    /**
     * ????????????
     */
    private void logoutChat() {

        DialogHelp.getConfirmDialog(this, getString(R.string.is_huang_call), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                hangUpVideo();
            }
        }).show();

    }

    /**
     * ??????????????????
     */
    private void doBalance() {
        hangUpVideo();
        ToastUtils.showShort(R.string.money_insufficient);
    }

    @Override
    protected void doLogout() {
        super.doLogout();
        leaveChannel();
        RtcEngine.destroy();
        CuckooApplication.getInstance().rtcEngineNull();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventVideoCallEndThread(EImVideoCallEndMessages var1) {

        LogUtils.i("?????????????????????????????????????????????:" + var1.msg.getCustomMsg().getSender().getUser_nickname());

        try {
            CustomMsg customMsg = var1.msg.getCustomMsg();
            showLiveLineEnd(1);

        } catch (Exception e) {
            LogUtils.i("???????????????????????????????????????????????????error" + e.getMessage());
        }

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPrivateGiftEvent(EImOnPrivateMessage var1) {

        pushGiftMsg(var1.customMsgPrivateGift);
        LogUtils.i("??????????????????????????????:" + var1.customMsgPrivateGift.getFrom_msg());

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCloseVideoEvent(EImOnCloseVideoLine var1) {

        //??????????????????????????????
        DialogHelp.getMessageDialog(this, var1.customMsgCloseVideo.getMsg_content()).show();
        hangUpVideo();

        LogUtils.i("??????????????????????????????:" + var1.customMsgCloseVideo.getMsg_content());
    }


    //?????????????????????????????????
    private void requestGetVideoCallTimeInfo() {

        Api.doRequestGetVideoCallTimeInfo(SaveData.getInstance().getId(), chatData.getChannelName(), new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {

                JsonRequestVideoEndInfo data = (JsonRequestVideoEndInfo) JsonRequestBase.getJsonObj(s, JsonRequestVideoEndInfo.class);
                if (StringUtils.toInt(data.getCode()) == 1) {
                    tv_time_info.setText("????????????:" + data.getVideo_call_total_coin());
                    tv_reward.setText("????????????:" + data.getGift_total_coin());
                    tv_userCoin.setText("????????????:" + data.getUser_coin());
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
            }
        });
    }


    /**
     * ??????????????????
     */
    private void doLoveHer() {
        Api.doLoveTheUser(
                chatData.getUserModel().getId(),
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
                            concealView(tv_follow);//??????????????????
                            showToastMsg("????????????!");
                        }
                    }
                }
        );
    }

    /**
     * ??????????????????????????????
     */
    private void requestUserData() {
        Api.getUserData(
                chatData.getUserModel().getId(),
                uId,
                uToken,
                new JsonCallback() {
                    @Override
                    public Context getContextToJson() {
                        return getNowContext();
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        JsonRequestTarget requestObj = JsonRequestTarget.getJsonObj(s);
                        if (requestObj.getCode() == 1) {

                            TargetUserData targetUserData = requestObj.getData();
                            Utils.loadHttpImg(VideoLineActivity.this, Utils.getCompleteImgUrl(targetUserData.getAvatar()), headImage);
                            nickName.setText(targetUserData.getUser_nickname());
                            thisPlayerNumber.setText(String.format(Locale.CHINA, "?????????%s", targetUserData.getAttention_all()));
                            tv_follow.setVisibility(StringUtils.toInt(targetUserData.getAttention()) == 0 ? View.VISIBLE : View.GONE);

                            requestGetVideoCallTimeInfo();
                        } else {
                            showToastMsg("??????????????????????????????:" + requestObj.getMsg());
                        }
                    }
                }
        );
    }


    @Override
    public void onBackPressed() {
        logoutChat();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }


    @Override
    protected void onPause() {
        super.onPause();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();


        if (cuckooVideoLineTimeBusiness != null) {
            cuckooVideoLineTimeBusiness.stop();
        }

        if (getVideoTimeInfoTask != null) {
            getVideoTimeInfoTask.stopRunnable();
        }
        leaveChannel();
        RtcEngine.destroy();
        CuckooApplication.getInstance().rtcEngineNull();

        if (DialogH5Activity.instance != null) {
            DialogH5Activity.instance.finish();
        }


        if (tiSDKManager != null) {
            tiSDKManager.destroy();
        }
    }

    /**
     * ????????????????????????
     */
    private void showLiveLineEnd(int isFabulous) {
        if (DialogH5Activity.instance != null) {
            DialogH5Activity.instance.finish();
        }
        Intent intent = new Intent(this, VideoLineEndActivity.class);
        intent.putExtra(VideoLineEndActivity.USER_HEAD, chatData.getUserModel().getAvatar());
        intent.putExtra(VideoLineEndActivity.USER_NICKNAME, chatData.getUserModel().getUser_nickname());
        intent.putExtra(VideoLineEndActivity.LIVE_LINE_TIME, videoChatTimer.getText());
        intent.putExtra(VideoLineEndActivity.LIVE_CHANNEL_ID, chatData.getChannelName());
        intent.putExtra(VideoLineEndActivity.IS_CALL_BE_USER, !isNeedCharge);
        intent.putExtra(VideoLineEndActivity.USER_ID, chatData.getUserModel().getId());
        intent.putExtra(VideoLineEndActivity.IS_FABULOUS, isFabulous);
        startActivity(intent);
        finish();
    }

    @Override
    public void onCallbackChargingSuccess() {

    }

    @Override
    public void onCallbackNotBalance() {
        doBalance();
    }

    @Override
    public void onCallbackCallRecordNotFount() {
        showToastMsg("?????????????????????");
        finishNow();
    }

    @Override
    public void onCallbackCallNotMuch(String msg) {
        DialogHelp.getConfirmDialog(VideoLineActivity.this, msg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RechargeActivity.startRechargeActivity(VideoLineActivity.this);
            }
        }).show();
    }

    @Override
    public void onCallbackEndVideo(String msg) {

        showToastMsg(msg);
        cuckooVideoLineTimeBusiness.doHangUpVideo();
    }

    @Override
    public void onHangUpVideoSuccess(int isFabulous) {
        hideLoadingDialog();
        showLiveLineEnd(isFabulous);
    }

    @Override
    public void onFreeTime(long time) {
        progress_bar_time.setText(String.valueOf(time));
    }

    @Override
    public void onFreeTimeEnd() {

        if (!isFinishing()) {
            progress_bar_time.setVisibility(View.GONE);

            DialogHelp.getConfirmDialog(this, "????????????????????????????????????????????????3????????????????????????", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    cuckooVideoLineTimeBusiness.charging();
                }
            }).show();

            //?????????????????????
            new CountDownTimer(3 * 1000, 1000) {

                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    //??????????????????
                    if (cuckooVideoLineTimeBusiness != null && !cuckooVideoLineTimeBusiness.isCharge()) {
                        hangUpVideo();
                    }
                }
            }.start();
        }
    }


    //????????????
    @Override
    public void onSuccess(JsonRequestDoPrivateSendGif sendGif) {

        final CustomMsgPrivateGift gift = new CustomMsgPrivateGift();
        gift.fillData(sendGif.getSend());
        Message message = new CustomMessage(gift, LiveConstant.CustomMsgType.MSG_PRIVATE_GIFT);
        conversation.sendMessage(message.getMessage(), new TIMValueCallBack<TIMMessage>() {
            @Override
            public void onError(int i, String s) {
                LogUtils.i("???????????????????????????????????????");
            }

            @Override
            public void onSuccess(TIMMessage timMessage) {

                pushGiftMsg(gift);

                //????????????????????????
                LogUtils.i("?????????????????????????????????SUCCESS");
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewMsgEventThread(EImOnNewMessages var1) {

        TIMMsgModel msgModel = (TIMMsgModel) var1.msg;
        TIMMessage timMessage = msgModel.getTimMessage();

        CustomMsg realCustomMsg = msgModel.parseToModel(CustomMsg.class);

        //????????????
        List<TIMElem> elems = new ArrayList<>();
        boolean hasText = false;
        if (timMessage != null) {
            for (int i = 0; i < timMessage.getElementCount(); ++i) {
                elems.add(timMessage.getElement(i));
                if (timMessage.getElement(i).getType() == TIMElemType.Text) {
                    hasText = true;
                } else {
                    hasText = false;
                }
            }

            SpannableStringBuilder stringBuilder = getString(elems, this);
            if (!hasText) {
                stringBuilder.insert(0, " ");
            }

            UserModel sender = realCustomMsg.getSender();
            String user_nickname = sender.getUser_nickname();

            if (mGiftAnimationContentView != null) {
                if (!TextUtils.isEmpty(stringBuilder.toString().trim())) {
                    guardInfoList.add(user_nickname + ":" + stringBuilder.toString());
                    //????????????
                    giftInfoAdapter.setData(guardInfoList);
                }

                giftInfoAdapter.notifyDataSetChanged();


            } else {
                Log.e("giveMeMsg", "mGiftAnimationContentView = null");
            }


        }
    }

    public static SpannableStringBuilder getString(List<TIMElem> elems, Context context) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        for (int i = 0; i < elems.size(); ++i) {
            switch (elems.get(i).getType()) {

                case Text:
                    TIMTextElem textElem = (TIMTextElem) elems.get(i);
                    Log.e("onSendMsgListener", textElem.getText());
                    stringBuilder.append(textElem.getText());
                    break;
            }

        }
        return stringBuilder;
    }


    /**
     * ????????????
     *
     * @param trim
     */
    @Override
    public void onSendMsgListener(String trim) {
        Log.e("onSendMsgListener", trim);
        IMHelp.sendTextCallMsg(trim, chatData.getUserModel().getId(), TIMConversationType.C2C, new TIMValueCallBack<TIMMessage>() {
            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onSuccess(TIMMessage timMessage) {
                TIMMsgModel timMsgModel = new TIMMsgModel(timMessage);
                EImOnNewMessages event = new EImOnNewMessages();
                event.msg = timMsgModel;
                EventBus.getDefault().post(event);
            }
        });

        changeEditStatus(false);

    }

    @Override
    public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
        Log.i(TAG, "onJoinChannelSuccess " + channel + " " + (uid & 0xFFFFFFFFL));
    }

    @Override
    public void onUserOffline(int uid, int reason) {
        runOnUiThread(this::onRemoteUserLeft);
    }


    private void onRemoteUserLeft() {
        mRemoteUid = -1;
        removeRemoteView();
    }

    private void removeRemoteView() {
        mRemoteViewContainer.removeAllViews();
    }


    @Override
    public void onUserJoined(int uid, int elapsed) {
        Log.i(TAG, "onUserJoined " + (uid & 0xFFFFFFFFL));
    }

    @Override
    public void onRemoteVideoStateChanged(int uid, int state, int reason, int elapsed) {
        Log.i(TAG, "onRemoteVideoStateChanged " + (uid & 0xFFFFFFFFL) + " " + state + " " + reason);
        if (mRemoteUid == -1 && state == io.agora.rtc.Constants.REMOTE_VIDEO_STATE_DECODING) {
            runOnUiThread(() -> {
                mRemoteUid = uid;
                setRemoteVideoView(uid);
            });
        }
    }

    private void setRemoteVideoView(int uid) {
        SurfaceView surfaceView = RtcEngine.CreateRendererView(this);
        mRtcEngine.setupRemoteVideo(new VideoCanvas(
                surfaceView, VideoCanvas.RENDER_MODE_HIDDEN, uid));
        mRemoteViewContainer.addView(surfaceView);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            if (Math.abs(x) > 3 || Math.abs(y) > 3) {
                if (Math.abs(x) > Math.abs(y)) {
                    mFURenderer.onDeviceOrientationChanged(x > 0 ? 0 : 180);
                } else {
                    mFURenderer.onDeviceOrientationChanged(y > 0 ? 90 : 270);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void finish() {
        mFinished = true;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        mFURenderer.queueEvent(new Runnable() {
            @Override
            public void run() {
                mFURenderer.onSurfaceDestroyed();
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mVideoManager.stopCapture();
        mRtcEngine.leaveChannel();
        super.finish();
    }
}
