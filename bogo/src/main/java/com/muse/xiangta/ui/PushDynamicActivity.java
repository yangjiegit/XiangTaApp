package com.muse.xiangta.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.muse.xiangta.CuckooApplication;
import com.muse.xiangta.LiveConstant;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.FullyGridLayoutManager;
import com.muse.xiangta.adapter.GridImageAdapter;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.audiorecord.AudioPlaybackManager;
import com.muse.xiangta.audiorecord.AudioRecordJumpUtil;
import com.muse.xiangta.audiorecord.entity.AudioEntity;
import com.muse.xiangta.audiorecord.util.PaoPaoTips;
import com.muse.xiangta.audiorecord.view.CommonSoundItemView;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.event.EventBusConfig;
import com.muse.xiangta.event.RefreshMessageEvent;
import com.muse.xiangta.event.VoiceRecordEvent;
import com.muse.xiangta.helper.ImageUtil;
import com.muse.xiangta.json.JsonRequestBase;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.utils.CuckooQiniuFileUploadUtils;
import com.muse.xiangta.utils.StringUtils;
import com.muse.xiangta.utils.Utils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.callback.StringCallback;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class PushDynamicActivity extends BaseActivity {

    @BindView(R.id.voice_hint)
    TextView mBtnVoiceRecord;

    @BindView(R.id.bt_hint)
    TextView mBtnVideoRecord;

    @BindView(R.id.et_input)
    EditText mEtInput;

    @BindView(R.id.tv_mark)
    TextView mark;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.ll_love)
    LinearLayout ll_love;

    @BindView(R.id.btn_voice_record)
    LinearLayout btn_voice_record;

    @BindView(R.id.pp_sound_item_view)
    CommonSoundItemView soundItemView;

    @BindView(R.id.ll_title1)
    LinearLayout ll_title1;
    @BindView(R.id.ll_title2)
    LinearLayout ll_title2;
    @BindView(R.id.ll_title3)
    LinearLayout ll_title3;
    @BindView(R.id.ll_title4)
    LinearLayout ll_title4;
    @BindView(R.id.iv_title1)
    ImageView iv_title1;
    @BindView(R.id.iv_title2)
    ImageView iv_title2;
    @BindView(R.id.iv_title3)
    ImageView iv_title3;
    @BindView(R.id.iv_title4)
    ImageView iv_title4;

    private List<ImageView> mImageList = new ArrayList<>();
    private int[] image1 = {R.mipmap.img_aiqing_1_1, R.mipmap.img_aiqing_2_1,
            R.mipmap.img_aiqing_3_1, R.mipmap.img_aiqing_4_1};
    private int[] image2 = {R.mipmap.img_aiqing_1_2, R.mipmap.img_aiqing_2_2,
            R.mipmap.img_aiqing_3_2, R.mipmap.img_aiqing_4_2};

    @BindView(R.id.title)
    View title;

    @BindView(R.id.location)
    CheckBox location;

    private GridImageAdapter adapter;

    private boolean hasVoiceFile = false;
    private String voiceFilePath = "";
    private List<LocalMedia> selectList = new ArrayList<>();
    private int maxSelectNum = 9;

    private int selectType = PictureMimeType.ofImage();

    /**
     * 1 ?????? 0??????
     */
    private int fileType = 0;


    private String uploadVideoUrl = "";
    private String uploadVideoThmbUrl = "";
    private String uploadAudoUrl = "";
    private List<String> uploadImgUrlList = new ArrayList<>();
    private String content;

    private int mDuration;
    private CuckooQiniuFileUploadUtils cuckooQiniuFileUploadUtils;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_push_dynamic;
    }


    private String city = "";

    private int type;
    private String title1, is_love = "1";

    @Override
    protected void initView() {
        QMUIStatusBarHelper.translucent(this); // ??????????????????
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        Utils.initTransP(title);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        recyclerView.setAdapter(adapter);
        city = CuckooApplication.getInstances().getLocation().get("city");
        location.setText("???????????? " + city);
    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type", 0);

        if (type == 1) {
            btn_voice_record.setVisibility(View.GONE);
            ll_love.setVisibility(View.VISIBLE);
            title1 = "?????????";
        } else {
            btn_voice_record.setVisibility(View.VISIBLE);
            ll_love.setVisibility(View.GONE);
        }

        mImageList.clear();
        mImageList.add(iv_title1);
        mImageList.add(iv_title2);
        mImageList.add(iv_title3);
        mImageList.add(iv_title4);

        ll_title1.setOnClickListener(this);
        ll_title2.setOnClickListener(this);
        ll_title3.setOnClickListener(this);
        ll_title4.setOnClickListener(this);

        cuckooQiniuFileUploadUtils = new CuckooQiniuFileUploadUtils();
    }

    @Override
    protected void initPlayerDisplayData() {

    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            //????????????????????? ????????? ??????
            deleteVoiceFile();

            //????????? ??????
            PictureSelector.create(PushDynamicActivity.this)
                    .openGallery(selectType)
                    .maxSelectNum(maxSelectNum)
                    .previewVideo(true)
                    .recordVideoSecond(60)
                    .forResult(PictureConfig.CHOOSE_REQUEST);
        }
    };

    @OnClick({R.id.rl_input, R.id.btn_voice_record, R.id.tv_push, R.id.iv_cancel, R.id.btn_video_record
            , R.id.ll_title1, R.id.ll_title2, R.id.ll_title3, R.id.ll_title4})
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_title1:
                title1 = "?????????";
                setTitleColor(0);
                break;
            case R.id.ll_title2:
                title1 = "?????????";
                setTitleColor(1);
                break;
            case R.id.ll_title3:
                title1 = "?????????";
                setTitleColor(2);
                break;
            case R.id.ll_title4:
                title1 = "?????????";
                setTitleColor(3);
                break;
            case R.id.rl_input:
                KeyboardUtils.showSoftInput(mEtInput);
                break;
            case R.id.iv_cancel:
                finish();
                break;
            case R.id.tv_push:
                clickPushDynamic();
                break;
            case R.id.btn_voice_record:
                //??????????????? ???????????????/??????
                cleanSelectVideo();

                //????????????
                clickRecrodVoice();
                break;
            case R.id.btn_video_record:
                clickSelectVideo();
                break;

//            case R.id.iv_add_img:
//
//
//                PictureSelector.create(this)
//                        .openGallery(PictureMimeType.ofImage())
//                        .maxSelectNum(9)
//                        .forResult(PictureConfig.CHOOSE_REQUEST);
//                break;

            default:
                break;
        }
    }

    private void clickRecrodVoice() {
        if (hasVoiceFile) {
            deleteVoiceFile();
            return;
        }

        AudioRecordJumpUtil.startRecordAudio(PushDynamicActivity.this);
    }

    /**
     * ???????????? ????????????
     */
    private void deleteVoiceFile() {
        File tempFile = new File(voiceFilePath);
        if (tempFile.exists()) {
            tempFile.delete();
        }
        hasVoiceFile = false;
        soundItemView.setVisibility(View.GONE);
        mBtnVoiceRecord.setText("????????????");
        AudioPlaybackManager.getInstance().stopAudio();
    }

    /**
     * ?????????????????????????????? ??????
     */
    private void clickSelectVideo() {
        String trim = mBtnVideoRecord.getText().toString().trim();
        if ("????????????".equals(trim)) {
            mBtnVideoRecord.setText("????????????");
            mark.setText("?????????????????????1???????????????????????????300???");
            selectType = PictureMimeType.ofVideo();
            selectList.clear();

            maxSelectNum = 1;

        } else {
            mBtnVideoRecord.setText("????????????");
            mark.setText("?????????????????????9???????????????????????????300???");
            selectType = PictureMimeType.ofImage();
            selectList.clear();

            maxSelectNum = 9;
        }

        adapter.setSelectMax(maxSelectNum);
        adapter.setList(selectList);
        adapter.notifyDataSetChanged();
    }

    /**
     * ????????????????????????
     */
    private void cleanSelectVideo() {
        String trim = mBtnVideoRecord.getText().toString().trim();
        if ("????????????".equals(trim)) {

            mBtnVideoRecord.setText("????????????");
            mark.setText("?????????????????????9???????????????????????????300???");
            selectType = PictureMimeType.ofImage();
            selectList.clear();

            maxSelectNum = 9;
        } else {
            mBtnVideoRecord.setText("????????????");
            mark.setText("?????????????????????1???????????????????????????300???");
            selectType = PictureMimeType.ofVideo();
            selectList.clear();

            maxSelectNum = 1;


        }

        adapter.setSelectMax(maxSelectNum);
        adapter.setList(selectList);
        adapter.notifyDataSetChanged();
    }

    //????????????
    private void clickPushDynamic() {
        content = mEtInput.getText().toString();
        if (TextUtils.isEmpty(content)) {
            ToastUtils.showLong("?????????????????????");
            return;
        }

        //???????????? ??????????????????
        showLoadingDialog(getString(R.string.loading_upload_info));

        //???????????? ?????????
        if (hasVoiceFile) {
            uploadVoiceFile();
        } else {
            //???????????? ??????
            uploadImgAndVideo();
        }

    }

    private void toPush() {
        showLoadingDialog("????????????...");
        Api.doRequestPushDynamic(city, SaveData.getInstance().getId(), SaveData.getInstance().getToken(),
                content,
                hasVoiceFile ? 1 : 0,
                uploadImgUrlList, uploadVideoUrl, uploadAudoUrl, fileType, uploadVideoThmbUrl, mDuration,
                title1, is_love, new StringCallback() {

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        hideLoadingDialog();
                        JsonRequestBase data = JsonRequestBase.getJsonObj(s, JsonRequestBase.class);
                        if (StringUtils.toInt(data.getCode()) == 1) {
                            ToastUtils.showLong("???????????????");
                            EventBus.getDefault().post(new RefreshMessageEvent("refresh_dynamic_list"));
                            finish();
                        } else {
                            ToastUtils.showLong(data.getMsg());
                        }

                    }
                });
    }

    private void uploadImgAndVideo() {

        uploadImgUrlList.clear();

        if (selectType == PictureMimeType.ofImage()) {
            fileType = 0;

            //?????????????????????
            if (selectList.size() == 0) {
                //??????
                toPush();
                return;
            }

            cuckooQiniuFileUploadUtils.uploadFileLocalMedia(LiveConstant.VIDEO_COVER_IMG_DIR, selectList, new CuckooQiniuFileUploadUtils.CuckooQiniuFileUploadCallback() {
                @Override
                public void onUploadFileSuccess(List<String> fileUrlList) {
                    hideLoadingDialog();
                    if (fileUrlList.size() == selectList.size()) {
                        uploadImgUrlList.addAll(fileUrlList);
                        //??????
                        toPush();
                    } else {
                        ToastUtils.showLong("?????????????????????");
                    }
                }
            });

        } else {
            fileType = 1;

            //?????????????????????
            if (selectList.size() == 0) {
                //??????
                toPush();
                return;
            }

            //????????????
            MediaMetadataRetriever media = new MediaMetadataRetriever();
            media.setDataSource(selectList.get(0).getPath());// videoPath ?????????????????????
            Bitmap bitmap = media.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
            File saveFile = ImageUtil.getSaveFile(bitmap, String.valueOf(System.currentTimeMillis()));

            List<File> thumbList = new ArrayList<>();
            thumbList.add(new File(saveFile.getPath()));

            //????????????
            cuckooQiniuFileUploadUtils.uploadFile(LiveConstant.VIDEO_COVER_IMG_DIR, thumbList, new CuckooQiniuFileUploadUtils.CuckooQiniuFileUploadCallback() {
                @Override
                public void onUploadFileSuccess(List<String> fileUrlList) {

                    if (fileUrlList.size() > 0) {
                        uploadVideoThmbUrl = fileUrlList.get(0);
                        //????????????
                        uploadVideo();
                    } else {
                        hideLoadingDialog();
                        ToastUtils.showLong("?????????????????????");
                    }
                }
            });

        }
    }

    //????????????
    private void uploadVoiceFile() {

        List<File> fileList = new ArrayList<>();
        fileList.add(new File(voiceFilePath));
        cuckooQiniuFileUploadUtils.uploadFile(LiveConstant.AUDIO_DIR, fileList, new CuckooQiniuFileUploadUtils.CuckooQiniuFileUploadCallback() {
            @Override
            public void onUploadFileSuccess(List<String> fileUrlList) {
                hideLoadingDialog();
                if (fileUrlList.size() > 0) {
                    uploadAudoUrl = fileUrlList.get(0);
                    //???????????? ??????
                    uploadImgAndVideo();
                } else {
                    ToastUtils.showLong("???????????????????????????");
                }
            }
        });
    }

    //????????????
    private void uploadVideo() {

        cuckooQiniuFileUploadUtils.uploadFileLocalMedia(LiveConstant.VIDEO_DIR, selectList, new CuckooQiniuFileUploadUtils.CuckooQiniuFileUploadCallback() {
            @Override
            public void onUploadFileSuccess(List<String> fileUrlList) {
                hideLoadingDialog();
                if (fileUrlList.size() > 0) {
                    uploadVideoUrl = fileUrlList.get(0);
                    //??????
                    toPush();
                } else {
                    ToastUtils.showLong("???????????????????????????");
                }
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(VoiceRecordEvent mainThreadEvent) {
        if (mainThreadEvent.getWhat() == EventBusConfig.SOUND_FEED_RECORD_FINISH) {
            Object soundPath = mainThreadEvent.getObj();
            if (soundPath != null && soundPath instanceof String) {
                String path = (String) soundPath;
                voiceFilePath = path;
                AudioEntity entity = new AudioEntity();
                entity.setUrl(path);
                int duration = AudioPlaybackManager.getDuration(path);
                if (duration <= 0) {
                    //PPLog.d(TAG, "duration <= 0");
                    PaoPaoTips.showDefault(this, "?????????");

                    File tempFile = new File(path);
                    if (tempFile.exists()) {
                        tempFile.delete();
                        return;
                    }
                } else {
                    entity.setDuration(duration / 1000);
                    mDuration = duration / 1000;
                    mBtnVoiceRecord.setText("????????????");
                    soundItemView.setSoundData(entity);
                    soundItemView.setVisibility(View.VISIBLE);
                    hasVoiceFile = true;
                    //PPLog.d(TAG, "soundPath:" + path);
                }
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // ????????????????????????
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // ?????? LocalMedia ??????????????????path
                    // 1.media.getPath(); ?????????path
                    // 2.media.getCutPath();????????????path????????????media.isCut();?????????true
                    // 3.media.getCompressPath();????????????path????????????media.isCompressed();?????????true
                    // ????????????????????????????????????????????????????????????????????????????????????
                    for (LocalMedia media : selectList) {
                        Log.i("??????-----???", media.getPath());
                    }
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    public void setTitleColor(int type) {
        for (int i = 0; i < mImageList.size(); i++) {
            if (i == type) {
                mImageList.get(i).setImageResource(image2[i]);
            } else {
                mImageList.get(i).setImageResource(image1[i]);
            }
        }
    }
}
