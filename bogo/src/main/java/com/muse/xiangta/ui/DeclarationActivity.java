package com.muse.xiangta.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.CuckooApplication;
import com.muse.xiangta.LiveConstant;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.audiorecord.IRecordAudioListener;
import com.muse.xiangta.audiorecord.util.Cons;
import com.muse.xiangta.audiorecord.util.PaoPaoTips;
import com.muse.xiangta.audiorecord.util.PermissionUtil;
import com.muse.xiangta.audiorecord.util.StringUtil;
import com.muse.xiangta.audiorecord.view.LineWaveVoiceView;
import com.muse.xiangta.audiorecord.view.RecordAudioView;
import com.muse.xiangta.event.EventBusConfig;
import com.muse.xiangta.event.VoiceRecordEvent;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.modle.DeclarationBean;
import com.muse.xiangta.utils.CuckooQiniuFileUploadUtils;
import com.muse.xiangta.utils.StringUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Response;

public class DeclarationActivity extends FragmentActivity implements
        IRecordAudioListener, View.OnClickListener, RecordAudioView.IRecordAudioListener {

    private static final String TAG = "AudioRecordActivity";
    public static final String KEY_ENTER_RECORD_AUDIO_ENTITY = "enter_record_audio";
    public static final String KEY_AUDIO_BUNDLE = "audio_bundle";
    public static final long DEFAULT_MAX_RECORD_TIME = 600000;
    public static final long DEFAULT_MIN_RECORD_TIME = 2000;
    protected static final int DEFAULT_MIN_TIME_UPDATE_TIME = 1000;

    private TextView view2, view3;
    private RecordAudioView recordAudioView;
    private String audioFileName;
    //    private TextView ivClose;
    private TextView tvRecordTips;
    private LinearLayout layoutCancelView;
    private String[] recordStatusDescription;
    private long maxRecordTime = DEFAULT_MAX_RECORD_TIME;
    private long minRecordTime = DEFAULT_MIN_RECORD_TIME;
    private Timer timer;
    private TimerTask timerTask;
    private Handler mainHandler;
    private long recordTotalTime;
    //    private EnterRecordAudioEntity entity;
    private View contentView;
    private View recordAudioContent;
    private TextView tvReplyName;
    private LineWaveVoiceView mHorVoiceView;
    //    private View emptyView;
    private CuckooQiniuFileUploadUtils cuckooQiniuFileUploadUtils;
    private String uploadAudoUrl = "";

    private String registerid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String registerID = "";

//        overridePendingTransition(R.anim.pp_bottom_in, R.anim.pp_bottom_out);
        setContentView(R.layout.activity_declaration);

        QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        cuckooQiniuFileUploadUtils = new CuckooQiniuFileUploadUtils();

        getDeclarationData();

        recordAudioView = (RecordAudioView) findViewById(R.id.iv_recording);
        recordAudioView.setRecordAudioListener(this);
//        ivClose = (TextView) findViewById(R.id.close_record);
//        ivClose.setOnClickListener(this);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        view3.setOnClickListener(this);
        tvRecordTips = (TextView) findViewById(R.id.record_tips);
        layoutCancelView = (LinearLayout) findViewById(R.id.pp_layout_cancel);
        contentView = findViewById(R.id.record_content);
        recordAudioContent = findViewById(R.id.layout_record_audio);
        mHorVoiceView = (LineWaveVoiceView) findViewById(R.id.horvoiceview);
//        emptyView = findViewById(R.id.audio_empty_layout);
//        emptyView.setOnClickListener(this);
        recordStatusDescription = new String[]{
                "按住录音",
                "上滑取消"
        };
        mainHandler = new Handler();
//        Bundle bundle = getIntent().getBundleExtra(KEY_AUDIO_BUNDLE);
//        entity = (EnterRecordAudioEntity) bundle.getSerializable(KEY_ENTER_RECORD_AUDIO_ENTITY);
    }

    private void getDeclarationData() {
//        uId = SaveData.getInstance().getId();
//        uToken = SaveData.getInstance().getToken();
        Api.getDeclarationText(SaveData.getInstance().getId(), SaveData.getInstance().getToken(),
                new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d("ret", "joker  爱情宣言文本    " + s);
                        if (!StringUtils.isEmpty(s)) {
                            DeclarationBean declarationBean = new Gson().fromJson(s, DeclarationBean.class);
                            if (!StringUtils.isEmpty(declarationBean.getData().getText())) {
                                view2.setText(declarationBean.getData().getText());
                            }
                        }
                    }
                });
    }

    @Override
    public boolean onRecordPrepare() {
        //检查录音权限
        if (!PermissionUtil.hasSelfPermission(this, Manifest.permission.RECORD_AUDIO)) {
            String[] pp = new String[]{
                    Manifest.permission.RECORD_AUDIO
            };
            ActivityCompat.requestPermissions(this, pp, Cons.PERMISSIONS_REQUEST_AUDIO);
            return false;
        }
        return true;
    }

    @Override
    public String onRecordStart() {
        recordTotalTime = 0;
        initTimer();
        timer.schedule(timerTask, 0, DEFAULT_MIN_TIME_UPDATE_TIME);
        audioFileName = CuckooApplication.getInstances().getExternalCacheDir() + File.separator + createAudioName();
        mHorVoiceView.startRecord();
        return audioFileName;
    }

    @Override
    public boolean onRecordStop() {
        if (recordTotalTime >= minRecordTime) {
            timer.cancel();
            //录制完成

//            onBackPressed();
            //录制完成发送EventBus通知
//            switch (entity.getSourceType()) {
//                case AUDIO_FEED:
            VoiceRecordEvent event1 = new VoiceRecordEvent(EventBusConfig.SOUND_FEED_RECORD_FINISH, audioFileName);
            event1.getObj();
            dialog(event1.getObj());
//                    break;
//                default:
//                    break;
//            }
        } else {
            onRecordCancel();
        }
        return false;
    }

    private void dialog(final Object object) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.show();  //注意：必须在window.setContentView之前show
        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_luyin);

        Log.d("ret", "joker    记录时间=" + recordTotalTime);
        //点击确定按钮让对话框消失
        TextView tv_comm2 = dialog.findViewById(R.id.tv_comm2);
        tv_comm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDeclarationData(object);
            }
        });
        TextView tv_comm1 = dialog.findViewById(R.id.tv_comm1);
        tv_comm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void saveDeclarationData(Object object) {
        List<File> fileList = new ArrayList<>();
        fileList.add(new File(object.toString()));
        Log.d("ret", "joker    录音地址===" + object.toString());
        cuckooQiniuFileUploadUtils.uploadFile(LiveConstant.AUDIO_DIR, fileList, new CuckooQiniuFileUploadUtils.CuckooQiniuFileUploadCallback() {
            @Override
            public void onUploadFileSuccess(List<String> fileUrlList) {
                if (fileUrlList.size() > 0) {
                    uploadAudoUrl = fileUrlList.get(0);
                    //上传视频 图片
                    Log.d("ret", "joker   上传完成的录音地址==" + uploadAudoUrl);
                    Api.saveDeclaration(SaveData.getInstance().getId(), SaveData.getInstance().getToken(),
                            (recordTotalTime / 1000) + "", uploadAudoUrl, new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    Log.d("ret", "joker   调用交友宣言接口 完成 成功");
                                    if (!StringUtils.isEmpty(s)) {
                                        JSONObject jsonObject = null;
                                        try {
                                            jsonObject = new JSONObject(s);
                                            int code = jsonObject.getInt("code");
                                            if (code == 1) {
                                                Toast.makeText(DeclarationActivity.this,
                                                        "上传成功", Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }
                            });
                } else {
                    ToastUtils.showLong("上传音频文件失败！");
                }
            }
        });
    }

    @Override
    public boolean onRecordCancel() {
        if (timer != null) {
            timer.cancel();
        }
        updateCancelUi();
        return false;
    }

    private void updateCancelUi() {
        mHorVoiceView.setVisibility(View.INVISIBLE);
        tvRecordTips.setVisibility(View.VISIBLE);
        layoutCancelView.setVisibility(View.INVISIBLE);
        tvRecordTips.setText(recordStatusDescription[0]);
        mHorVoiceView.stopRecord();
        deleteTempFile();
    }

    private void deleteTempFile() {
        //取消录制后删除文件
        if (audioFileName != null) {
            File tempFile = new File(audioFileName);
            if (tempFile.exists()) {
                tempFile.delete();
            }
        }
    }

    /**
     * 上划取消
     */
    @Override
    public void onSlideTop() {
        mHorVoiceView.setVisibility(View.INVISIBLE);
        tvRecordTips.setVisibility(View.INVISIBLE);
        layoutCancelView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFingerPress() {
        mHorVoiceView.setVisibility(View.VISIBLE);
        tvRecordTips.setVisibility(View.VISIBLE);
        tvRecordTips.setText(recordStatusDescription[1]);
        layoutCancelView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (permissions == null || permissions.length <= 0
                || grantResults == null || grantResults.length <= 0) {
            return;
        }
        boolean isGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
        if (isGranted) {
            //暂时先弹出提示,用户需要再次按下语音键
            PaoPaoTips.showDefault(this, "请再次按下语音键");
        } else {
            PaoPaoTips.showDefault(this, "您拒绝了录音权限,请在设置中打开");
        }
        updateCancelUi();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.close_record:
//                onBackPressed();
//                break;
//            case R.id.audio_empty_layout:
//                onBackPressed();
//                break;
            case R.id.view3:
                getDeclarationData();
                break;
        }
    }

    /**
     * 初始化计时器用来更新倒计时
     */
    private void initTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //每隔1000毫秒更新一次ui
                        recordTotalTime += 1000;
                        updateTimerUI();
                    }
                });
            }
        };
    }

    private void updateTimerUI() {
        if (recordTotalTime >= maxRecordTime) {
            recordAudioView.invokeStop();
        } else {
            String string = String.format(" 倒计时 %s ", StringUtil.formatRecordTime(recordTotalTime, maxRecordTime));
            mHorVoiceView.setText(string);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
//        overridePendingTransition(R.anim.pp_bottom_in, R.anim.pp_bottom_out);
    }

    public String createAudioName() {
        long time = System.currentTimeMillis();
        String fileName = UUID.randomUUID().toString() + time + ".aac";
        return fileName;
    }

}