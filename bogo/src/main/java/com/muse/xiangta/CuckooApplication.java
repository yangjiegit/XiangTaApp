package com.muse.xiangta;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.LogUtils;
import com.faceunity.nama.FURenderer;
import com.fm.openinstall.OpenInstall;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.dao.DaoMaster;
import com.muse.xiangta.dao.DaoSession;
import com.muse.xiangta.event.EImOnNewMessages;
import com.muse.xiangta.event.LocalEvent;
import com.muse.xiangta.helper.ContentUtils;
import com.muse.xiangta.inter.MsgDialogClick;
import com.muse.xiangta.manage.AppConfig;
import com.muse.xiangta.manage.AppManager;
import com.muse.xiangta.manage.JsonDataManage;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.modle.custommsg.MsgModel;
import com.muse.xiangta.modle.custommsg.TIMMsgModel;
import com.muse.xiangta.ui.MainActivity;
import com.muse.xiangta.ui.RegisterSelectActivity;
import com.muse.xiangta.utils.CuckooLifecycleHandler;
import com.muse.xiangta.utils.CuckooSharedPreUtil;
import com.muse.xiangta.utils.SDHandlerManager;
import com.muse.xiangta.utils.Utils;
import com.qmuiteam.qmui.BuildConfig;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.tencent.imsdk.TIMConnListener;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMGroupReceiveMessageOpt;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMOfflinePushListener;
import com.tencent.imsdk.TIMOfflinePushNotification;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserStatusListener;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

import cn.jiguang.verifysdk.api.JVerificationInterface;
import cn.jiguang.verifysdk.api.RequestCallback;
import io.agora.capture.video.camera.CameraVideoManager;
import io.agora.rtc.RtcEngine;

import static com.tencent.imsdk.TIMElemType.Custom;
import static com.tencent.imsdk.TIMElemType.Text;
import static com.tencent.qalsdk.QALBroadcastReceiver.tag;

/**
 * ?????????????????????
 * Created by wp on 2018/1/5 0005.
 */

public class CuckooApplication extends Application {
    private static CuckooApplication mInstance;
    private static int mMainThreadId;
    private static Thread mMainThread;
    private static Handler mMainHandler;

    //--------
    private CameraVideoManager mVideoManager;
    private RtcEngine mRtcEngine;
    private RtcEngineEventHandlerProxy mRtcEventHandler;
    //--------


    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static CuckooApplication instances;
    //??????AMapLocationClient?????????
    public AMapLocationClient mLocationClient = null;
    //???????????????????????????
    public AMapLocationListener mLocationListener;
    //??????AMapLocationClientOption??????
    public AMapLocationClientOption mLocationOption = null;
    private String lat;
    private String lng;
    private String city = "";

    private TIMMsgModel pushVideoCallMessage;
    private boolean isInVideoCallWait;
    private boolean isInPrivateChatPage;

    private boolean isRefreshLocal;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public static CuckooApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        mInstance = this;

        //--------
        initRtcEngine();
        initVideoCaptureAsync();
        //--------

        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainHandler = new Handler();

        initJiGuang();
        initTbs();
        //????????????
        initOpenInstall();
        initUmeng();
        //????????????????????????greenDao
        initDatabase();
        //??????????????????SDK
        initTim();
        //???????????????????????????
        initOkGo();
        //?????????????????????
        initAmap();
        //?????????utils
        com.blankj.utilcode.util.Utils.init(this);
        initLanguage();

        registerActivityLifecycleCallbacks(new CuckooLifecycleHandler());
        //registerActivityLifecycleCallbacks(new ActivityHelper());
        closeAndroidPDialog();
    }

    private void initJiGuang() {
        JVerificationInterface.init(this, 5000, new RequestCallback<String>() {
            @Override
            public void onResult(int code, String msg) {
                Log.d("ret", "joker       code = " + code + " msg = " + msg);
            }
        });
    }

    public RtcEngine initRtcEngine() {
        String appId = getString(R.string.agora_app_id);
        if (TextUtils.isEmpty(appId)) {
            throw new RuntimeException("NEED TO use your App ID, get your own ID at https://dashboard.agora.io/");
        }

        mRtcEventHandler = new RtcEngineEventHandlerProxy();
        try {
            mRtcEngine = RtcEngine.create(this, appId, mRtcEventHandler);
            mRtcEngine.setChannelProfile(io.agora.rtc.Constants.CHANNEL_PROFILE_LIVE_BROADCASTING);
        } catch (Exception e) {
            throw new RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e));
        }
        return mRtcEngine;
    }

    private void initVideoCaptureAsync() {
        new Thread(() -> {
            Context application = getApplicationContext();
            FURenderer.setup(application);
            mVideoManager = new CameraVideoManager(application,
                    new PreprocessorFaceUnity(application));
        }).start();
    }

    public RtcEngine rtcEngine() {
        return mRtcEngine;
    }

    public void rtcEngineNull() {
        mRtcEngine = null;
    }

    public void addRtcHandler(RtcEngineEventHandler handler) {
        mRtcEventHandler.addEventHandler(handler);
    }

    public void removeRtcHandler(RtcEngineEventHandler handler) {
        mRtcEventHandler.removeEventHandler(handler);
    }

    public CameraVideoManager videoManager() {
        return mVideoManager;
    }


    private void initTbs() {
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                Log.e("CuckooApplication", "onCoreInitFinished: ??????x5");
            }

            @Override
            public void onViewInitFinished(boolean b) {
                Log.e("CuckooApplication", "onViewInitFinished: " + b);
            }
        });
    }


    private void closeAndroidPDialog() {
        try {
            Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            Class cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * ??????okgo
     */
    private void initOkGo() {
//        //---------??????????????????????????????,????????????????????????,?????????????????????,???????????????,??????????????????-------------//
        HttpParams params = new HttpParams();
        params.put("os", "Android");     //param????????????,?????????,??????????????????
        params.put("sdk_version", Build.VERSION.SDK_INT);
        params.put("app_version", BuildConfig.VERSION_CODE);
        params.put("brand", android.os.Build.BRAND);//????????????
        params.put("model", android.os.Build.MODEL);//????????????
        //?????????okgo
        OkGo.init(this);
        OkGo.getInstance()
                .debug("BOGO_HTTP", Level.INFO, true)
                .addCommonParams(params);   //????????????????????????;
    }

    //??????????????????
    private void initLanguage() {
        String language = "zh_simple";
        String languageLocal = CuckooSharedPreUtil.getString(this, "LANGUAGE");
        if (!TextUtils.isEmpty(languageLocal)) {
            language = languageLocal;
        }

        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();

        if (language.equals("zh_simple")) {
            config.locale = Locale.SIMPLIFIED_CHINESE;
            CuckooSharedPreUtil.put(this, "LANGUAGE", "zh_simple");
        } else if (language.equals("zh_traditional")) {
            config.locale = Locale.TRADITIONAL_CHINESE;
            CuckooSharedPreUtil.put(this, "LANGUAGE", "zh_traditional");
        } else {
            config.locale = Locale.getDefault();
        }

        resources.updateConfiguration(config, dm);
    }

    ////////////////////////////////////////??????OpenInstallSDK//////////////////////////////////////////////
    private void initOpenInstall() {
        if (Utils.isMainProcess(this)) {
            OpenInstall.init(this);
        }
    }

    ////////////////////////////////////////????????????SDK//////////////////////////////////////////////
    private void initUmeng() {

        UMConfigure.init(this, getResources().getString(R.string.umeng_appkey), "buguniao", UMConfigure.DEVICE_TYPE_PHONE, getResources().getString(R.string.umeng_message_secret));
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);

        // ????????????SDK????????????
        UMConfigure.setLogEnabled(true);
        PushAgent mPushAgent = PushAgent.getInstance(this);

        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void launchApp(Context context, UMessage msg) {
                super.launchApp(context, msg);
                //Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
            }

            @Override
            public void openUrl(Context context, UMessage msg) {
                super.openUrl(context, msg);
            }

            @Override
            public void openActivity(Context context, UMessage msg) {
                super.openActivity(context, msg);
            }

            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                //Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();

                JSONObject pushObj = JSON.parseObject(msg.custom);

                switch (pushObj.getInteger("action")) {

                    case LiveConstant.PushType.VIDEO_CALL:
                        Intent intent = new Intent(getInstances(), MainActivity.class);
                        intent.putExtra("push_data", pushObj.getString("custom_data"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;

                    default:
                        break;
                }
            }

        };

        mPushAgent.setNotificationClickHandler(notificationClickHandler);
        mPushAgent.setDisplayNotificationNumber(0);
        //??????????????????
        mPushAgent.setMuteDurationSeconds(0);
        //?????????????????????
        mPushAgent.setNoDisturbMode(0, 0, 0, 0);
        //?????????????????????????????????register???????????????????????????
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //?????????????????????device token
                LogUtils.i("??????????????????");
            }

            @Override
            public void onFailure(String s, String s1) {
                LogUtils.i("??????????????????:" + s);

            }
        });

        mPushAgent.setNotificaitonOnForeground(false);
    }

    ////////////////////////////////////////??????????????????//////////////////////////////////////////////
    public void initAmap() {
        ///????????????
        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (null != aMapLocation) {
                    if (aMapLocation.getErrorCode() == 0) {
                        //??????????????????amapLocation?????????????????????
                    } else {
                        //???????????????????????????ErrCode????????????????????????????????????????????????errInfo???????????????????????????????????????
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }
                }

                lat = String.valueOf(aMapLocation.getLatitude());//????????????
                lng = String.valueOf(aMapLocation.getLongitude());//????????????
                city = aMapLocation.getCity();

                Log.d("ret", "joker    ??????   ");

                LocalEvent event = new LocalEvent();
                EventBus.getDefault().post(event);

                if (SaveData.getInstance().isLogin && !isRefreshLocal) {
                    isRefreshLocal = true;
                    Log.e("doRefreshCity", city + "=" + lat + "=" + lng);
                    Api.doRefreshCity(SaveData.getInstance().getId(), city, lat, lng, null);
                }

                mLocationClient.stopLocation();
                //LogUtils.i(city);
            }
        };
        //???????????????
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //????????????????????????
        mLocationClient.setLocationListener(mLocationListener);
        //?????????AMapLocationClientOption??????
        mLocationOption = new AMapLocationClientOption();
        //???????????????????????????
        mLocationOption.setOnceLocation(true);
        mLocationOption.setMockEnable(true);
        //??????????????????,????????????,?????????2000ms
        mLocationOption.setInterval(2000);
        //??????????????????????????????????????????
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    ////////////////////////////////////////???????????????SDK//////////////////////////////////////////////
    private void initTim() {

        ContentUtils.TxContent.SDK_APPID = getResources().getInteger(R.integer.tencent_sdk_app_id);
        if (MsfSdkUtils.isMainProcess(this)) {
            TIMManager.getInstance().setOfflinePushListener(new TIMOfflinePushListener() {
                @Override
                public void handleNotification(TIMOfflinePushNotification notification) {
                    if (notification.getGroupReceiveMsgOpt() == TIMGroupReceiveMessageOpt.ReceiveAndNotify) {
                        //??????????????????????????????
                        notification.doNotify(getApplicationContext(), R.mipmap.ic_launcher);
                        LogUtils.i("??????????????????");
                    }
                }
            });
            initTimConfig();//?????????????????????????????????
            //?????????SDK
            TIMSdkConfig config = new TIMSdkConfig(ContentUtils.TxContent.SDK_APPID);
            config.enableLogPrint(true).setLogLevel(TIMLogLevel.INFO);
            TIMManager.getInstance().init(getApplicationContext(), config);

        }
    }


    /**
     * ?????????????????????????????????
     */
    private void initTimConfig() {

        TIMUserConfig timUserConfig = new TIMUserConfig();
        //???????????????????????????????????????
        timUserConfig.setUserStatusListener(new TIMUserStatusListener() {
            @Override
            public void onForceOffline() {
                //????????????????????????
                doAgainLogin("????????????", "?????????????????????????????????,???????????????!");
            }

            @Override
            public void onUserSigExpired() {
                //????????????????????????????????????userSig????????????SDK
                doAgainLogin("????????????", "????????????????????????,???????????????????????????????????????!");
            }
        });
        //?????????????????????????????????
        timUserConfig.setConnectionListener(new TIMConnListener() {
            @Override
            public void onConnected() {
                //????????????
                Log.i(tag, "onConnected");
            }

            @Override
            public void onDisconnected(int code, String desc) {
                //????????????
                //????????????????????????code???????????????desc????????????????????????????????????
                //?????????code???????????????????????????
                Log.i(tag, "onDisconnected");
            }

            @Override
            public void onWifiNeedAuth(String name) {
                //??????????????????
                Log.i(tag, "onWifiNeedAuth");
            }
        });

        //??????????????????
        TIMManager.getInstance().addMessageListener(new TIMMessageListener() {

            private void postNewMessage(MsgModel msgModel) {
                EImOnNewMessages event = new EImOnNewMessages();
                event.msg = msgModel;
                EventBus.getDefault().post(event);

            }

            @Override
            public boolean onNewMessages(final List<TIMMessage> list) {
                LogUtils.i("msg?????????" + list.size());

//                List<TIMMessage> mList=new ArrayList<>();
//                for (int i = 0; i < list.size(); i++) {
//                    if(list.get(i).getConversation().getType().name().equals("C2C")){
//                        mList.add(list.get(i));
//                    }
//                }
//                list.clear();
//                list.addAll(mList);

                if (list != null) {
                    SDHandlerManager.getBackgroundHandler().post(new Runnable() {
                        @Override
                        public void run() {

                            //???????????????????????????,???????????????????????????
                            //LogUtils.i("??????Application????????????--------------------------->" + list.toString());
                            //???????????????????????????,???????????????????????????
                            for (TIMMessage message : list) {

                                TIMElemType lastType = null;
                                for (int i = 0; i < message.getElementCount(); ++i) {

                                    TIMElem elem = message.getElement(i);
                                    Log.d("ret", "joker      ??????  " + message.getSender());
                                    LogUtils.i("msg?????????" + message.getSender());
//                                    if (message.getSender().equals("admin")) {
//                                        return;
//                                    }


                                    TIMElemType type = message.getElement(i).getType();
                                    //?????????????????????CustomMsgText ??????
                                    if (lastType == Text && type == Custom) {
                                        continue;
                                    }
                                    lastType = type;


                                    if (AppConfig.DEBUG) {
                                        TIMConversation conversation = message.getConversation();

                                    }
                                    TIMConversation conversation = message.getConversation();
                                    if (conversation.getType().toString().equals("System")) {
                                        continue;
                                    }

                                    boolean post = true;
                                    final TIMMsgModel msgModel = new TIMMsgModel(message, true);

                                    if (msgModel.getCustomMsgType() == LiveConstant.CustomMsgType.MSG_VIDEO_LINE_CALL
                                            || msgModel.getCustomMsgType() == LiveConstant.CustomMsgType.MSG_VIDEO_LINE_CALL_END
                                            || msgModel.getCustomMsgType() == LiveConstant.CustomMsgType.MSG_VIDEO_LINE_CALL_REPLY
                                            || msgModel.getCustomMsgType() == LiveConstant.CustomMsgType.MSG_CLOSE_VIDEO_LINE) {

                                        post = false;
                                    }

                                    if (post) {
                                        postNewMessage(msgModel);
                                    }
                                }
                            }


                        }
                    });
                }

                return false;
            }


        });
    }

    ////////////////////////////////////////////??????greenDao/////////////////////////////////////////
    private void initDatabase() {
        // ?????? DaoMaster ???????????? DevOpenHelper????????????????????????????????? SQLiteOpenHelper ?????????
        // ?????????????????????????????????????????????????????????CREATE TABLE???????????? SQL ??????????????? greenDAO ?????????????????????
        // ?????????????????? DaoMaster.DevOpenHelper ???????????????????????????????????????????????????????????????????????????????????????
        // ???????????????????????????????????????????????????????????????????????????????????????????????????
        mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = mHelper.getWritableDatabase();
        // ????????????????????????????????? DaoMaster??????????????? Session ????????????????????????????????????
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();

        //?????????json????????????
        JsonDataManage.init(this);
        //???????????????????????????
        SaveData.init(this);
    }

    /**
     * ??????DaoSession
     *
     * @return DaoSession??????
     */
    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    /**
     * ?????????????????????????????????
     *
     * @return SQLiteDatabase
     */
    public SQLiteDatabase getDb() {
        return db;
    }


    /**
     * ???????????????
     *
     * @return HashMap<String, String>
     */
    public HashMap<String, String> getLocation() {
        HashMap<String, String> location = new HashMap<>();
        //????????????
        mLocationClient.startLocation();
        location.put("lat", lat);//??????
        location.put("lng", lng);//??????
        location.put("city", city);//??????
        return location;
    }

    /**
     * ????????????????????????
     *
     * @param title ???????????????
     * @param msg   ????????????
     */
    private void doAgainLogin(String title, String msg) {
        Utils.showMsgDialog(getApplicationContext(),
                title,
                msg,
                new MsgDialogClick() {
                    @Override
                    public void doYes(QMUIDialog dialog, int index) {
                        Utils.goActivity(getApplicationContext(), RegisterSelectActivity.class).finish();//????????????
                    }

                    @Override
                    public void doNo(QMUIDialog dialog, int index) {
                        AppManager.getAppManager().appExit(getApplicationContext());//??????
                    }
                });
    }

    public TIMMsgModel getPushVideoCallMessage() {
        return pushVideoCallMessage;
    }

    public void setPushVideoCallMessage(TIMMsgModel pushVideoCallMessage) {
        this.pushVideoCallMessage = pushVideoCallMessage;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }


    /**
     * ?????????????????????.???????????????????????????
     *
     * @return RabbitApplication
     */
    public static CuckooApplication getInstances() {
        return instances;
    }

    public boolean isInVideoCallWait() {
        return isInVideoCallWait;
    }

    public void setInVideoCallWait(boolean inVideoCallWait) {
        isInVideoCallWait = inVideoCallWait;
    }

    public boolean isInPrivateChatPage() {
        return isInPrivateChatPage;
    }

    public void setInPrivateChatPage(boolean inPrivateChatPage) {
        isInPrivateChatPage = inPrivateChatPage;
    }

    /**
     * ???????????????ID
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static Handler getMainHandler() {
        return mMainHandler;
    }


}
