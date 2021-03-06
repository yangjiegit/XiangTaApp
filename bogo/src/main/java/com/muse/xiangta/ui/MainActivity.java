package com.muse.xiangta.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.muse.xiangta.CuckooApplication;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.FragAdapter;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.base.GlobContents;
import com.muse.xiangta.dialog.AppUpdateDialog;
import com.muse.xiangta.event.CuckooOnLoginTimSuccessEvent;
import com.muse.xiangta.event.EImOnNewMessages;
import com.muse.xiangta.event.LocalEvent;
import com.muse.xiangta.fragment.DynamicFragment;
import com.muse.xiangta.fragment.MsgFragment;
import com.muse.xiangta.fragment.RecommendFragment;
import com.muse.xiangta.fragment.UserPageFragment;
import com.muse.xiangta.json.JsonGetMsgPage;
import com.muse.xiangta.json.JsonRequestBase;
import com.muse.xiangta.manage.RequestConfig;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.modle.ConfigModel;
import com.muse.xiangta.modle.custommsg.CustomMsg;
import com.muse.xiangta.modle.custommsg.CustomMsgVideoCall;
import com.muse.xiangta.utils.CuckooSharedPreUtil;
import com.muse.xiangta.utils.IMUtils;
import com.muse.xiangta.utils.SPHelper;
import com.muse.xiangta.utils.StringUtils;
import com.muse.xiangta.utils.UIHelp;
import com.muse.xiangta.utils.UserOnlineHeartUtils;
import com.muse.xiangta.widget.NoScrollViewPager;
import com.lzy.okgo.callback.StringCallback;
import com.maning.imagebrowserlibrary.utils.StatusBarUtil;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.tillusory.sdk.TiSDK;
import okhttp3.Call;
import okhttp3.Response;

/**
 * ??????
 */

public class MainActivity extends BaseActivity implements PermissionUtils.OnPermissionListener {

    @BindView(R.id.rl_bottom)
    RelativeLayout rl_bottom;

    private TextView unReadMsg;
    //??????
    private NoScrollViewPager mainViewPage;
    private QMUITabSegment mainTabSegment;

    //??????
    private List<Fragment> fragmentList;

    private static final int REQUEST_PERMISSION = 0;
    private AppUpdateDialog appUpdateDialog;
    private String pushData;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);
        mainViewPage = findViewById(R.id.main_view_page);
        mainTabSegment = findViewById(R.id.main_tab_segment);

        //????????????View
        initUnReadMessageView();

        addTabAndViewPage();

        if (!StringUtils.isEmpty(SPHelper.getString(MainActivity.this, "pass"))) {
            startActivity(new Intent(MainActivity.this, TeenagersActivity.class));
        }
    }

    private void initUnReadMessageView() {
        int itemWidth = ScreenUtils.getScreenWidth() / 4;
        int padding = ConvertUtils.dp2px(1) * 2;
        unReadMsg = new TextView(this);
        unReadMsg.setVisibility(View.GONE);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ConvertUtils.dp2px(15), ConvertUtils.dp2px(15));
        params.setMargins(itemWidth * 2 + itemWidth / 4 * 3, 0, 0, 0);
        unReadMsg.setPadding(padding, padding, padding, padding);
        unReadMsg.setGravity(Gravity.CENTER);
        unReadMsg.setTextSize(ConvertUtils.dp2px(3));
        unReadMsg.setBackgroundResource(R.drawable.bg_un_read_msg);
        unReadMsg.setTextColor(getResources().getColor(R.color.white));
        unReadMsg.setLayoutParams(params);
        rl_bottom.addView(unReadMsg);
    }


    private Handler handler = new Handler();

    private Runnable task = new Runnable() {
        public void run() {
            // TODOAuto-generated method stub
            handler.postDelayed(this, 20 * 1000);//??????????????????????????????5???
            //?????????????????????
            CuckooApplication.getInstance().initAmap();
        }
    };

    @Override
    protected void initData() {

        handler.post(task);//????????????
        //???????????????
//        InviteCodeDialog.checkInviteCode(this);

        //????????????
        if (GlobContents.getInstance().isShowDialog) {
            //????????????????????????
            UIHelp.checkOpenSignDialog(this);
        }

//        if (ConfigModel.getInitData().getIs_force_upgrade() != 1) {
//            showUpdateDialog();
//        }

        //??????????????????
        showSystemMsg();

        //???????????????
        UserOnlineHeartUtils.getInstance().startHeartTime();

        if (!TextUtils.isEmpty(ConfigModel.getInitData().getBogokj_beauty_sdk_key())) {
            //???????????????????????????SDK
            TiSDK.init(ConfigModel.getInitData().getBogokj_beauty_sdk_key(), this);
        }

        checkUpPermission();
        //??????????????????
        checkBackVideoMsg();

    }

    @Override
    protected void initPlayerDisplayData() {

    }

    @Override
    protected void initSet() {

//        ??????viewPage???????????????
//        mainViewPage.setOffscreenPageLimit(0);
        //??????adapter
        mainViewPage.setAdapter(new FragAdapter(getSupportFragmentManager(), fragmentList));
        //??????????????????
        mainTabSegment.setTabTextSize(ConvertUtils.dp2px(12));
        //?????? Tab ????????????????????????
        mainTabSegment.setDefaultNormalColor(getResources().getColor(R.color.b0b0b0));
        //?????? Tab ????????????????????????
        mainTabSegment.setDefaultSelectedColor(getResources().getColor(R.color.black));
        //??????viewPage
        mainTabSegment.setupWithViewPager(mainViewPage, false);

    }


    //?????????ViewPage???tab
    private void addTabAndViewPage() {

        //???????????????fragment
        fragmentList = new ArrayList<>();
        fragmentList.add(new RecommendFragment());
        fragmentList.add(new DynamicFragment());
        fragmentList.add(new MsgFragment());
        fragmentList.add(new UserPageFragment());

        //??????tab
        mainTabSegment.addTab(
                new QMUITabSegment.Tab(
                        getResources().getDrawable(R.mipmap.main_home_1),
                        getResources().getDrawable(R.mipmap.main_home_2),
                        getString(R.string.index),
                        false,
                        true));

        if (StringUtils.toInt(ConfigModel.getInitData().getOpen_video_chat()) == 1) {
            mainTabSegment.addTab(
                    new QMUITabSegment.Tab(
                            getResources().getDrawable(R.mipmap.main_home_1),
                            getResources().getDrawable(R.mipmap.main_home_2),
                            null,
                            false,
                            true));
        }


        mainTabSegment.addTab(
                new QMUITabSegment.Tab(
                        getResources().getDrawable(R.mipmap.main_dt1),
                        getResources().getDrawable(R.mipmap.main_dt2),
                        getString(R.string.dynamic),
                        false,
                        true));


        mainTabSegment.addTab(
                new QMUITabSegment.Tab(
                        getResources().getDrawable(R.mipmap.main_message1),
                        getResources().getDrawable(R.mipmap.main_message2),
                        getString(R.string.message),
                        false,
                        true));

        mainTabSegment.addTab(
                new QMUITabSegment.Tab(
                        getResources().getDrawable(R.mipmap.main_my1),
                        getResources().getDrawable(R.mipmap.main_my2),
                        getString(R.string.me),
                        false,
                        true));
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLocalEvent(LocalEvent var1) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewMsgEventThread(EImOnNewMessages var1) {
        initUnReadMessage();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginTimSuccess(CuckooOnLoginTimSuccessEvent var1) {
        initUnReadMessage();
    }


    //?????????????????????
    private void initUnReadMessage() {

        getSystemUnReadMsgCount();

    }

    private void getSystemUnReadMsgCount() {
        Api.getMsgPageInfo(SaveData.getInstance().getId(), SaveData.getInstance().getToken(), new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {
                JsonGetMsgPage unReadSystemMsg = (JsonGetMsgPage) JsonRequestBase.getJsonObj(s, JsonGetMsgPage.class);
                if (unReadSystemMsg.getCode() == 1) {

                    int count = IMUtils.getIMUnReadMessageCount();
                    int allMsgCount = count + unReadSystemMsg.getSum() + unReadSystemMsg.getUn_handle_subscribe_num();
                    if (allMsgCount > 0) {
                        unReadMsg.setVisibility(View.VISIBLE);
                        unReadMsg.setText(String.valueOf(allMsgCount));
                    } else {
                        unReadMsg.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    //????????????
    private void showSystemMsg() {
        int isShowSystemMsg = CuckooSharedPreUtil.getInt(this, "IS_SHOW_SYSTEM_MSG");

        if (!TextUtils.isEmpty(RequestConfig.getConfigObj().getMainSystemMessage()) && isShowSystemMsg == 0) {

            new QMUIDialog.MessageDialogBuilder(this)
                    .setTitle(getString(R.string.system_notice))
                    .setMessage(RequestConfig.getConfigObj().getMainSystemMessage())
                    .addAction(0, "????????????", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                        @Override
                        public void onClick(QMUIDialog dialog, int index) {
                            CuckooSharedPreUtil.put(MainActivity.this, "IS_SHOW_SYSTEM_MSG", 1);
                            dialog.dismiss();
                        }
                    })
                    .show();
        }
    }


    private void showUpdateDialog() {
        if (appUpdateDialog != null) {
            appUpdateDialog.dismiss();
        }

        appUpdateDialog = AppUpdateDialog.checkUpdate(this);
    }


    @Override
    public void onPermissionGranted() {

    }

    @Override
    public void onPermissionDenied(String[] deniedPermissions) {
        ToastUtils.showLong(R.string.not_have_permission);
    }

    @Override
    public void onBackPressed() {
        //????????????

        Intent intent = new Intent();
        // ???Intent??????Action???Category??????
        intent.setAction(Intent.ACTION_MAIN);// "android.intent.action.MAIN"
        intent.addCategory(Intent.CATEGORY_HOME); //"android.intent.category.HOME"
        startActivity(intent);


    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ConfigModel.getInitData().getIs_force_upgrade() == 1) {
            showUpdateDialog();
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //?????????????????????
        initUnReadMessage();

        pushData = getIntent().getStringExtra("push_data");
        if (pushData != null && !TextUtils.isEmpty(pushData)) {
            getIntent().putExtra("push_data", "");
            Intent intent = new Intent(this, CuckooVideoCallListActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserOnlineHeartUtils.getInstance().stopHeartTime();
    }

    private void checkUpPermission() {
        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
        PermissionUtils.requestPermissions(this, REQUEST_PERMISSION, permission, this);

        boolean isOpenNotification = NotificationManagerCompat.from(this).areNotificationsEnabled();
        if (!isOpenNotification) {
            new QMUIDialog.MessageDialogBuilder(this)
                    .setTitle("??????")
                    .setMessage("???????????????????????????????????????????????????????????????")
                    .addAction(0, "??????", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                        @Override
                        public void onClick(QMUIDialog dialog, int index) {
                            Intent localIntent = new Intent();
                            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            if (Build.VERSION.SDK_INT >= 9) {
                                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                localIntent.setData(Uri.fromParts("package", MainActivity.this.getPackageName(), null));
                            } else if (Build.VERSION.SDK_INT <= 8) {
                                localIntent.setAction(Intent.ACTION_VIEW);

                                localIntent.setClassName("com.android.settings",
                                        "com.android.settings.InstalledAppDetails");

                                localIntent.putExtra("com.android.settings.ApplicationPkgName",
                                        MainActivity.this.getPackageName());
                            }
                            startActivity(localIntent);
                            dialog.dismiss();
                        }
                    })
                    .show();
        }
    }

    private void checkBackVideoMsg() {
        if (CuckooApplication.getInstances().getPushVideoCallMessage() != null) {
            CustomMsg customMsg = CuckooApplication.getInstances().getPushVideoCallMessage().getCustomMsg();
            final CustomMsgVideoCall customMsgVideoCall = (CustomMsgVideoCall) customMsg;
            Api.doCheckVideoCallExits(customMsgVideoCall.getChannel(), new StringCallback() {

                @Override
                public void onSuccess(String s, Call call, Response response) {
                    JsonRequestBase data = JsonRequestBase.getJsonObj(s, JsonRequestBase.class);
                    if (data.getCode() == 1) {
                        Intent intent = new Intent(MainActivity.this, CuckooVideoCallWaitActivity.class);
                        intent.putExtra(CuckooVideoCallWaitActivity.CALL_TYPE, customMsgVideoCall.getCall_type());
                        intent.putExtra(CuckooVideoCallWaitActivity.CALL_USER_INFO, customMsgVideoCall.getSender());
                        intent.putExtra(CuckooVideoCallWaitActivity.CHANNEL_ID, customMsgVideoCall.getChannel());
                        intent.putExtra(CuckooVideoCallWaitActivity.IS_USE_FREE, customMsgVideoCall.getIs_free());
                        startActivity(intent);
                        CuckooApplication.getInstances().setPushVideoCallMessage(null);
                    }
                }
            });
        }
    }


}
