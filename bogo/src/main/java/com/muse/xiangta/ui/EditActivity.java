package com.muse.xiangta.ui;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.maning.imagebrowserlibrary.utils.StatusBarUtil;
import com.muse.xiangta.LiveConstant;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.FullyGridLayoutManager;
import com.muse.xiangta.adapter.GridImageAdapter;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.api.ApiUtils;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.helper.ImageUtil;
import com.muse.xiangta.inter.JsonCallback;
import com.muse.xiangta.json.jsonmodle.UserData;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.ui.view.LastInputEditText;
import com.muse.xiangta.utils.CuckooQiniuFileUploadUtils;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;
import com.muse.xiangta.utils.Utils;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.NumberPicker;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;


/**
 * ??????????????????
 */
public class EditActivity extends BaseActivity {

    private RecyclerView redactRecycler;
    private CircleImageView headImg;
    private TextView redactNameText, redactSexText;
    private GridImageAdapter redactAdapter;
    private RelativeLayout mRlChangeNameLayout;
    @BindView(R.id.redact_shengao)
    RelativeLayout redact_shengao;
    @BindView(R.id.shengao_tv)
    TextView shengao_tv;
    @BindView(R.id.redact_tizhong)
    RelativeLayout redact_tizhong;
    @BindView(R.id.tizhong_tv)
    TextView tizhong_tv;
    @BindView(R.id.redact_xingzuo)
    RelativeLayout redact_xingzuo;
    @BindView(R.id.xingzuo_tv)
    TextView xingzuo_tv;
    @BindView(R.id.redact_jieshao)
    RelativeLayout redact_jieshao;
    @BindView(R.id.jieshao_tv)
    TextView jieshao_tv;
    @BindView(R.id.redact_biaoqian)
    RelativeLayout redact_biaoqian;
    @BindView(R.id.biaoqian_tv)
    TextView biaoqian_tv;
    @BindView(R.id.xueli_tv)
    TextView xueli_tv;
    @BindView(R.id.zhiye_tv)
    TextView zhiye_tv;
    @BindView(R.id.qinggan_tv)
    TextView qinggan_tv;
    @BindView(R.id.neixin_et)
    LastInputEditText neixin_et;
    @BindView(R.id.iv_fengmian)
    ImageView iv_fengmian;
    @BindView(R.id.rl_yinpin)
    RelativeLayout rl_yinpin;
    @BindView(R.id.tv_yinpin)
    TextView tv_yinpin;
    @BindView(R.id.juzhu_tv)
    TextView juzhu_tv;
    @BindView(R.id.tongju_tv)
    TextView tongju_tv;
    @BindView(R.id.yuehui_tv)
    TextView yuehui_tv;
    @BindView(R.id.goufang_tv)
    TextView goufang_tv;
    @BindView(R.id.gouche_tv)
    TextView gouche_tv;
    @BindView(R.id.age_tv)
    TextView age_tv;

    private List<String> ageList = new ArrayList<>();
    private File headImgFile;//????????????
    private ArrayList<File> filesByAll = new ArrayList<>();//??????????????????
    private ArrayList<UserData.DataBean.ImgBean> imgLoader = new ArrayList<>();//????????????????????????
    private int sex = 0;//??????

    //????????????
    private boolean isAlterSex = false;//????????????????????????
    private boolean isAddPlus = false;//????????????+???

    private ArrayList<String> path = new ArrayList<>();//???????????????????????????

    private int selectImageType = 0;
    private TextView signTv;
    private RelativeLayout signRl;
    private String sign;

    private CuckooQiniuFileUploadUtils cuckooQiniuFileUploadUtils;
    private int selectType = PictureMimeType.ofImage();
    private List<LocalMedia> selectList = new ArrayList<>();

    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    private QMUICommonListItemView itemHeight;
    private QMUICommonListItemView itemWeight;
    private QMUICommonListItemView itemConstellation;
    private QMUICommonListItemView itemIntroduce;
    private QMUICommonListItemView itemImageLabel;
    private String heightStr;
    private String weightStr;
    private String albleIdArr;
    private String uploadVideoUrl = "";
    private String uploadImageUrl = "";

    @Override
    protected Context getNowContext() {
        return EditActivity.this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_player_redact;
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestUserData();
    }

    @Override
    protected void initView() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.admin_color), 0);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        redactRecycler = findViewById(R.id.redact_recycler);
        headImg = findViewById(R.id.head_img);
        redactNameText = findViewById(R.id.redact_nameText);
        redactSexText = findViewById(R.id.redact_sextext);
        mRlChangeNameLayout = findViewById(R.id.redact_name);

        //????????????
        signTv = findViewById(R.id.redact_sign_tv);
        signRl = findViewById(R.id.redact_sign);
        signRl.setOnClickListener(this);


        for (int i = 18; i < 81; i++) {
            ageList.add(String.valueOf(i));
        }

        itemHeight = groupListView.createItemView(getString(R.string.height));
        itemHeight.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        itemHeight.setId(R.id.auth_user_height);
        itemHeight.getDetailTextView().setVisibility(View.VISIBLE);
        itemHeight.getDetailTextView().setHint(R.string.edit_input_height);


        itemWeight = groupListView.createItemView(getString(R.string.weight));
        itemWeight.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        itemWeight.setId(R.id.auth_user_weight);
        itemWeight.getDetailTextView().setVisibility(View.VISIBLE);
        itemWeight.getDetailTextView().setHint(R.string.edit_input_weight);

        itemConstellation = groupListView.createItemView(getString(R.string.constellation));
        itemConstellation.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        itemConstellation.setId(R.id.auth_user_constellation);
        itemConstellation.getDetailTextView().setVisibility(View.VISIBLE);
        itemConstellation.getDetailTextView().setHint(R.string.edit_auth_set_constellation);

        itemIntroduce = groupListView.createItemView(getString(R.string.edit_introduce));
        itemIntroduce.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        itemIntroduce.setId(R.id.auth_introduce);
        itemIntroduce.getDetailTextView().setVisibility(View.VISIBLE);
        itemIntroduce.getDetailTextView().setHint(R.string.edit_auth_set_introduce);

        itemImageLabel = groupListView.createItemView(getString(R.string.edit_auth_image_label));
        itemImageLabel.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        itemImageLabel.setId(R.id.auth_image_label);
        itemImageLabel.getDetailTextView().setVisibility(View.VISIBLE);
        itemImageLabel.getDetailTextView().setHint(R.string.edit_auth_set_image_label);


        QMUIGroupListView.Section section = QMUIGroupListView.newSection(this)
                .addItemView(itemHeight, this)
                .addItemView(itemWeight, this)
                .addItemView(itemConstellation, this)
                .addItemView(itemIntroduce, this)
                .addItemView(itemImageLabel, this);

        section.addTo(groupListView);

        initAdapter();
    }

    @Override
    protected void initSet() {
        getTopBar().setBackgroundResource(R.color.white);
        getTopBar().setTitle(getString(R.string.edit_info)).setTextColor(getNowContext().getResources().getColor(R.color.black));
        Button button = getTopBar().addRightTextButton(getString(R.string.save), R.id.redact_savebtn);
        button.setTextColor(getNowContext().getResources().getColor(R.color.black));
        button.setOnClickListener(this);
        //getTopBar().addLeftImageButton(R.drawable.icon_back_black, R.id.redact_backbtn).setOnClickListener(this);
        setOnclickListener(R.id.head_img, R.id.redact_name, R.id.redact_sex);

    }

    @Override
    protected boolean hasTopBar() {
        return true;
    }

    /**
     * ???????????????
     */
    private void initAdapter() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        redactRecycler.setLayoutManager(manager);

        redactAdapter = new GridImageAdapter(this, onAddPicClickListener);
        redactAdapter.setList(selectList);
        redactAdapter.setSelectMax(6);
        redactRecycler.setAdapter(redactAdapter);
    }

    @Override
    protected void initData() {
        uId = SaveData.getInstance().getId();
        uToken = SaveData.getInstance().getToken();

        cuckooQiniuFileUploadUtils = new CuckooQiniuFileUploadUtils();
        //???????????????
        requestUserData();
    }

    @Override
    protected void initPlayerDisplayData() {
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {

            doSelectImage(1);

        }
    };

    @OnClick({R.id.redact_shengao, R.id.redact_tizhong, R.id.redact_xingzuo,
            R.id.redact_biaoqian, R.id.redact_jieshao, R.id.redact_zhiye, R.id.redact_xueli
            , R.id.redact_qinggan, R.id.redact_xuanyan, R.id.redact_fengmian,
            R.id.redact_yundong, R.id.redact_yinyue, R.id.redact_meishi, R.id.redact_dianying,
            R.id.redact_shuji, R.id.redact_lvxing, R.id.redact_juzhu, R.id.redact_tongju,
            R.id.redact_yuehui, R.id.redact_goufang, R.id.redact_gouche, R.id.redact_age})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = new Intent(this, HobbyActivity.class);
        switch (v.getId()) {
            case R.id.redact_age:
                onAgePicker();
                break;
            case R.id.redact_juzhu:
                //????????????
                onJuzhuPicker();
                break;
            case R.id.redact_tongju:
                //????????????
                onTongjuPicker();
                break;
            case R.id.redact_yuehui:
                //????????????
                onYuehuiPicker();
                break;
            case R.id.redact_goufang:
                //????????????
                onGoufangPicker();
                break;
            case R.id.redact_gouche:
                //????????????
                onGouchePicker();
                break;
            case R.id.redact_yundong:
                //??????
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
            case R.id.redact_yinyue:
                //??????
                intent.putExtra("type", 2);
                startActivity(intent);
                break;
            case R.id.redact_meishi:
                //??????
                intent.putExtra("type", 3);
                startActivity(intent);
                break;
            case R.id.redact_dianying:
                //??????
                intent.putExtra("type", 4);
                startActivity(intent);
                break;
            case R.id.redact_shuji:
                //??????
                intent.putExtra("type", 5);
                startActivity(intent);
                break;
            case R.id.redact_lvxing:
                //??????
                intent.putExtra("type", 6);
                startActivity(intent);
                break;
            case R.id.redact_xuanyan:
                //????????????
                startActivity(new Intent(EditActivity.this, DeclarationActivity.class));
                break;
            case R.id.redact_fengmian:
                //????????????
                clickSelectVideo();
                break;
            case R.id.redact_qinggan:
                //??????
                onEmotionPicker();
                break;
            case R.id.redact_xueli:
                //??????
                onEducationPicker();
                break;
            case R.id.redact_zhiye:
                //??????
                onOccupationPicker();
                break;
            case R.id.redact_backbtn:
                finish();
                break;
            case R.id.redact_savebtn:
                sign = signTv.getText().toString().trim();
                uploadPageImg();
                break;
            case R.id.head_img:
                doChangeHead();
                break;
            case R.id.redact_name:
                showDialogEdNicknameText();
                break;
            case R.id.redact_sex:
                showDialogSex();
                break;
            case R.id.redact_sign:
                showDialogSignEdText();
                break;
            case R.id.redact_tizhong://??????
                onNumberWeightPicker();
                break;
            case R.id.redact_shengao://??????
                onNumberHeightPicker();
                break;
            case R.id.auth_user_nickname:
                clickEditUserNickname();
                break;
            case R.id.auth_bind_phone:
                clickBindPhone();
                break;
            case R.id.redact_xingzuo://??????
                onConstellationPicker();
                break;
            case R.id.auth_self_label:
                clickEditSelfSign();
                break;
            case R.id.redact_jieshao://????????????
                clickEditSelfIntroduce();
                break;
            case R.id.redact_biaoqian://??????
                clickSelectLabel();
                break;
            default:
                break;
        }
    }

    private void clickSelectVideo() {
        PictureSelector.create(EditActivity.this)
                .openGallery(2)
                .maxSelectNum(1)
                .previewVideo(true)
                .recordVideoSecond(60)
                .forResult(20);
    }
    //////////////////////////////////////????????????//////////////////////////////////////////////////

    /**
     * ??????????????????
     *
     * @return
     */
    private void requestUserData() {

        Api.getUserDataAtCompile(uId, uToken, new JsonCallback() {
            @Override
            public Context getContextToJson() {
                return getNowContext();
            }

            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (!StringUtils.isEmpty(s)) {
                    UserData userData = new Gson().fromJson(s, UserData.class);
                    if (userData.getCode() == 1) {
                        if (null != headImgFile) {
                            headImg.setImageBitmap(ImageUtil.getBitmapByPath(headImgFile.getAbsolutePath()));
                        } else {
                            GlideImgManager.glideLoader(EditActivity.this,
                                    userData.getData().getAvatar(), headImg, 0);
                        }
                        redactNameText.setText(userData.getData().getUser_nickname());
                        if (userData.getData().getSex() == 0) {
                            isAlterSex = true;
                        }
                        if (!StringUtils.isEmpty(userData.getData().getSign())) {
                            signTv.setText(userData.getData().getSign());
                        } else {
                            signTv.setText("????????????????????????");
                        }
                        redactSexText.setText(Utils.getSex(userData.getData().getSex()));
                        sex = userData.getData().getSex();
                        imgLoader.clear();
                        imgLoader.addAll(userData.getData().getImg());
                        if (ApiUtils.isTrueUrl(userData.getData().getAvatar())) {
                            ApiUtils.getUrlBitmapToSD(userData.getData().getAvatar(), "headImage");
                        }
                        isAddPlus = false;
                        if (StringUtils.toInt(userData.getData().getIs_change_name()) != 1) {
                            mRlChangeNameLayout.setEnabled(false);
                        }
                        age_tv.setText(userData.getData().getAge());
                        juzhu_tv.setText(userData.getData().getJuzhuqingkuang());
                        tongju_tv.setText(userData.getData().getHuanqiantongju());
                        yuehui_tv.setText(userData.getData().getJieshouyuehui());
                        goufang_tv.setText(userData.getData().getShifougoufang());
                        gouche_tv.setText(userData.getData().getShifougouche());

                        zhiye_tv.setText(userData.getData().getOccupation());
                        xueli_tv.setText(userData.getData().getEducation());
                        qinggan_tv.setText(userData.getData().getLove_status());
                        neixin_et.setText(userData.getData().getOverlapping_sound());
                        itemHeight.setDetailText(userData.getData().getHeight() + "cm");
                        shengao_tv.setText(userData.getData().getHeight() + "cm");
                        itemWeight.setDetailText(userData.getData().getWeight() + "kg");
                        tizhong_tv.setText(userData.getData().getWeight() + "kg");
                        uploadImageUrl = userData.getData().getVideo_img();
                        uploadVideoUrl = userData.getData().getVideo_url();

                        GlideImgManager.glideLoader(EditActivity.this,
                                userData.getData().getVideo_img(), iv_fengmian, 1);

                        if (userData.getData().getDeclaration_length() == 0) {
                            rl_yinpin.setVisibility(View.GONE);
                        } else {
                            rl_yinpin.setVisibility(View.VISIBLE);
                            tv_yinpin.setText(userData.getData().getDeclaration_length() + "\"");
                        }

//                    itemConstellation.setDetailText(userData.getConstellation());??????
                        xingzuo_tv.setText(userData.getData().getConstellation());
//                    itemIntroduce.setDetailText(userData.getIntroduce());????????????
                        if (StringUtils.isEmpty(jieshao_tv.getText().toString().trim())) {
                            jieshao_tv.setText(userData.getData().getIntroduce());
                        }
//                    itemImageLabel.setDetailText(userData.getImage_label());//??????
                        biaoqian_tv.setText(userData.getData().getImage_label());
                        selectList.clear();
                        //????????????&&????????????
                        for (UserData.DataBean.ImgBean url : userData.getData().getImg()) {
                            LocalMedia localMedia = new LocalMedia();
                            localMedia.setPath(url.getImg());
                            selectList.add(localMedia);
                        }
                        redactAdapter.setNewList(selectList);
                        redactAdapter.notifyDataSetChanged();
                    } else {
                        showToastMsg(userData.getMsg());
                    }
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                finishNow();//??????????????????,??????????????????
            }
        });
    }

    private List<String> fileUrlList = new ArrayList<>();

    //???????????????
    private void uploadPageImg() {

        cuckooQiniuFileUploadUtils.uploadFileLocalMedia(LiveConstant.AVATAR_DIR, redactAdapter.getList(), new CuckooQiniuFileUploadUtils.CuckooQiniuFileUploadCallback() {
            @Override
            public void onUploadFileSuccess(List<String> list) {
                fileUrlList.clear();
                fileUrlList.addAll(list);

                if (headImgFile != null && headImgFile.exists()) {
                    List<File> headFile = new ArrayList<>();
                    headFile.add(headImgFile);
                    //????????????
                    cuckooQiniuFileUploadUtils.uploadFile(LiveConstant.AVATAR_DIR, headFile, new CuckooQiniuFileUploadUtils.CuckooQiniuFileUploadCallback() {
                        @Override
                        public void onUploadFileSuccess(List<String> avatar) {
                            saveUserData(fileUrlList, avatar.get(0));
                        }
                    });
                } else {
                    saveUserData(fileUrlList, null);
                }
            }
        });
    }

    /**
     * ????????????????????????
     */
    private void saveUserData(List<String> pageImgList, String avatar) {

        String height = itemHeight.getDetailText().toString();
        String weight = itemWeight.getDetailText().toString();

        if (height.contains("cm")) {
            heightStr = height.substring(0, height.length() - 2);
        } else {
            heightStr = height;
        }

        if (weight.contains("kg")) {
            weightStr = weight.substring(0, weight.length() - 2);

        } else {
            weightStr = weight;
        }

        showLoadingDialog(getString(R.string.loading_upload_data));
        Api.saveUserDataAtCompile(
                heightStr,
                weightStr,
//                itemConstellation.getDetailText().toString(),
                xingzuo_tv.getText().toString().trim(),
//                itemIntroduce.getDetailText().toString(),
                jieshao_tv.getText().toString().trim(),
//                itemImageLabel.getDetailText().toString(),
                biaoqian_tv.getText().toString().trim(),
                "",
                uId,
                uToken,
                redactNameText.getText().toString(),
                avatar,
                sex,
                zhiye_tv.getText().toString().trim(),
                xueli_tv.getText().toString().trim(),
                qinggan_tv.getText().toString().trim(),
                neixin_et.getText().toString().trim(),
                uploadImageUrl, uploadVideoUrl,
                pageImgList,
                sign,
                juzhu_tv.getText().toString().trim(),
                tongju_tv.getText().toString().trim(),
                yuehui_tv.getText().toString().trim(),
                goufang_tv.getText().toString().trim(),
                gouche_tv.getText().toString().trim(),
                age_tv.getText().toString().trim(),

                new JsonCallback() {
                    @Override
                    public Context getContextToJson() {
                        return getNowContext();
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        hideLoadingDialog();
                        tipD = new QMUITipDialog.Builder(getNowContext())
                                .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                                .setTipWord(getString(R.string.submit_success))
                                .create();
                        showThenDialog(tipD);
                        //??????????????????
                        requestUserData();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        hideLoadingDialog();
                    }
                });
    }

    /**
     * ??????????????????????????????
     */
    private void doSelectImage(int type) {
        selectImageType = type;

        int maxCount;
        if (selectImageType == 0) {
            maxCount = 1;
        } else {
            maxCount = 5;
        }

        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(maxCount)
                .isCamera(false)
                .enableCrop(true)// ???????????? true or false
                .hideBottomControls(false)// ????????????uCrop??????????????????????????? true or false
                .freeStyleCropEnabled(true)// ???????????????????????? true or false
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }


    private static final int REQUEST_CODE = 1;
    public static final int RESULT_SELF_INTRODUCE_CODE = 3;
    public static final int RESULT_SELF_SIGN_CODE = 2;

    public void onNumberHeightPicker() {
        NumberPicker picker = new NumberPicker(this);
        picker.setWidth(picker.getScreenWidthPixels());
        picker.setCycleDisable(false);
        picker.setDividerVisible(false);
        picker.setOffset(2);//?????????
        picker.setRange(145, 200, 1);//????????????
        picker.setSelectedItem(172);
        picker.setLabel("??????");
        picker.setOnNumberPickListener(new NumberPicker.OnNumberPickListener() {
            @Override
            public void onNumberPicked(int index, Number item) {
                itemHeight.setDetailText(item.toString() + "cm");
                shengao_tv.setText(item.toString() + "cm");
            }
        });
        picker.show();
    }

    public void onNumberWeightPicker() {
        NumberPicker picker = new NumberPicker(this);
        picker.setWidth(picker.getScreenWidthPixels());
        picker.setCycleDisable(false);
        picker.setDividerVisible(false);
        picker.setOffset(2);//?????????
        picker.setRange(40, 100, 1);//????????????
        picker.setSelectedItem(172);
        picker.setLabel("??????");
        picker.setOnNumberPickListener(new NumberPicker.OnNumberPickListener() {
            @Override
            public void onNumberPicked(int index, Number item) {
                itemWeight.setDetailText(item.toString() + "kg");
                tizhong_tv.setText(item.toString() + "kg");
            }
        });
        picker.show();
    }

    private void clickBindPhone() {

        Intent intent = new Intent(this, CuckooAuthPhoneActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void clickSelectLabel() {
        Intent intent = new Intent(this, CuckooSelectLabelActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void clickEditSelfIntroduce() {
        Intent intent = new Intent(this, CuckooAuthEditBodyActivity.class);
        intent.putExtra("RESULT_CODE", RESULT_SELF_INTRODUCE_CODE);
        intent.putExtra(CuckooAuthEditBodyActivity.TITLE_LABEL, getString(R.string.edit_auth_self_introduce));
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void clickEditSelfSign() {

        Intent intent = new Intent(this, CuckooAuthEditBodyActivity.class);
        intent.putExtra("RESULT_CODE", RESULT_SELF_SIGN_CODE);
        intent.putExtra(CuckooAuthEditBodyActivity.TITLE_LABEL, getString(R.string.edit_auth_self_sign));
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void clickEditUserNickname() {
        Intent intent = new Intent(this, CuckooAuthUserNicknameActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void onJuzhuPicker() {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("??????");
        OptionPicker picker = new OptionPicker(this,
                isChinese ? new String[]{
                        "?????????????????????", "????????????", "????????????", "?????????", "???????????????"
                } : new String[]{
                        "?????????????????????", "????????????", "????????????", "?????????", "???????????????"
                });

        picker.setCycleDisable(true);//???????????????
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(30);
        picker.setTopLineColor(getResources().getColor(R.color.line_color));
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "?????????" : "Please pick");
        picker.setTitleTextColor(getResources().getColor(R.color.picker_color));
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(getResources().getColor(R.color.picker_color));
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(getResources().getColor(R.color.picker_color));
        picker.setSubmitTextSize(13);
        picker.setTextColor(getResources().getColor(R.color.picker_color), 0xFF999999);
        WheelView.DividerConfig config = new WheelView.DividerConfig();
        config.setColor(getResources().getColor(R.color.picker_color));//?????????
        config.setAlpha(140);//????????????
        config.setRatio((float) (1.0 / 8.0));//?????????
        picker.setDividerConfig(config);
        picker.setBackgroundColor(0xFFEEEEEE);
        //picker.setSelectedItem(isChinese ? "?????????" : "Virgo");
        picker.setSelectedIndex(7);
        picker.setCanceledOnTouchOutside(true);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
//                itemConstellation.setDetailText(item);
                juzhu_tv.setText(item);
            }
        });
        picker.show();
    }

    public void onAgePicker() {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("??????");
        OptionPicker picker = new OptionPicker(this,
                isChinese ? ageList.toArray(new String[ageList.size()]) :
                        ageList.toArray(new String[ageList.size()]));

        picker.setCycleDisable(true);//???????????????
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(30);
        picker.setTopLineColor(getResources().getColor(R.color.line_color));
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "?????????" : "Please pick");
        picker.setTitleTextColor(getResources().getColor(R.color.picker_color));
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(getResources().getColor(R.color.picker_color));
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(getResources().getColor(R.color.picker_color));
        picker.setSubmitTextSize(13);
        picker.setTextColor(getResources().getColor(R.color.picker_color), 0xFF999999);
        WheelView.DividerConfig config = new WheelView.DividerConfig();
        config.setColor(getResources().getColor(R.color.picker_color));//?????????
        config.setAlpha(140);//????????????
        config.setRatio((float) (1.0 / 8.0));//?????????
        picker.setDividerConfig(config);
        picker.setBackgroundColor(0xFFEEEEEE);
        //picker.setSelectedItem(isChinese ? "?????????" : "Virgo");
        picker.setSelectedIndex(7);
        picker.setCanceledOnTouchOutside(true);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
//                itemConstellation.setDetailText(item);
                age_tv.setText(item);
            }
        });
        picker.show();
    }

    public void onTongjuPicker() {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("??????");
        OptionPicker picker = new OptionPicker(this,
                isChinese ? new String[]{
                        "??????", "?????????"
                } : new String[]{
                        "??????", "?????????"
                });

        picker.setCycleDisable(true);//???????????????
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(30);
        picker.setTopLineColor(getResources().getColor(R.color.line_color));
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "?????????" : "Please pick");
        picker.setTitleTextColor(getResources().getColor(R.color.picker_color));
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(getResources().getColor(R.color.picker_color));
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(getResources().getColor(R.color.picker_color));
        picker.setSubmitTextSize(13);
        picker.setTextColor(getResources().getColor(R.color.picker_color), 0xFF999999);
        WheelView.DividerConfig config = new WheelView.DividerConfig();
        config.setColor(getResources().getColor(R.color.picker_color));//?????????
        config.setAlpha(140);//????????????
        config.setRatio((float) (1.0 / 8.0));//?????????
        picker.setDividerConfig(config);
        picker.setBackgroundColor(0xFFEEEEEE);
        //picker.setSelectedItem(isChinese ? "?????????" : "Virgo");
        picker.setSelectedIndex(7);
        picker.setCanceledOnTouchOutside(true);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
//                itemConstellation.setDetailText(item);
                tongju_tv.setText(item);
            }
        });
        picker.show();
    }

    public void onYuehuiPicker() {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("??????");
        OptionPicker picker = new OptionPicker(this,
                isChinese ? new String[]{
                        "???", "???"
                } : new String[]{
                        "???", "???"
                });

        picker.setCycleDisable(true);//???????????????
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(30);
        picker.setTopLineColor(getResources().getColor(R.color.line_color));
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "?????????" : "Please pick");
        picker.setTitleTextColor(getResources().getColor(R.color.picker_color));
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(getResources().getColor(R.color.picker_color));
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(getResources().getColor(R.color.picker_color));
        picker.setSubmitTextSize(13);
        picker.setTextColor(getResources().getColor(R.color.picker_color), 0xFF999999);
        WheelView.DividerConfig config = new WheelView.DividerConfig();
        config.setColor(getResources().getColor(R.color.picker_color));//?????????
        config.setAlpha(140);//????????????
        config.setRatio((float) (1.0 / 8.0));//?????????
        picker.setDividerConfig(config);
        picker.setBackgroundColor(0xFFEEEEEE);
        //picker.setSelectedItem(isChinese ? "?????????" : "Virgo");
        picker.setSelectedIndex(7);
        picker.setCanceledOnTouchOutside(true);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
//                itemConstellation.setDetailText(item);
                yuehui_tv.setText(item);
            }
        });
        picker.show();
    }

    public void onGoufangPicker() {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("??????");
        OptionPicker picker = new OptionPicker(this,
                isChinese ? new String[]{
                        "?????????", "????????????", "?????????"
                } : new String[]{
                        "?????????", "????????????", "?????????"
                });

        picker.setCycleDisable(true);//???????????????
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(30);
        picker.setTopLineColor(getResources().getColor(R.color.line_color));
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "?????????" : "Please pick");
        picker.setTitleTextColor(getResources().getColor(R.color.picker_color));
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(getResources().getColor(R.color.picker_color));
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(getResources().getColor(R.color.picker_color));
        picker.setSubmitTextSize(13);
        picker.setTextColor(getResources().getColor(R.color.picker_color), 0xFF999999);
        WheelView.DividerConfig config = new WheelView.DividerConfig();
        config.setColor(getResources().getColor(R.color.picker_color));//?????????
        config.setAlpha(140);//????????????
        config.setRatio((float) (1.0 / 8.0));//?????????
        picker.setDividerConfig(config);
        picker.setBackgroundColor(0xFFEEEEEE);
        //picker.setSelectedItem(isChinese ? "?????????" : "Virgo");
        picker.setSelectedIndex(7);
        picker.setCanceledOnTouchOutside(true);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
//                itemConstellation.setDetailText(item);
                goufang_tv.setText(item);
            }
        });
        picker.show();
    }

    public void onGouchePicker() {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("??????");
        OptionPicker picker = new OptionPicker(this,
                isChinese ? new String[]{
                        "????????????????????????", "?????????????????????", "????????????????????????", "????????????"
                } : new String[]{
                        "????????????????????????", "?????????????????????", "????????????????????????", "????????????"
                });

        picker.setCycleDisable(true);//???????????????
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(30);
        picker.setTopLineColor(getResources().getColor(R.color.line_color));
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "?????????" : "Please pick");
        picker.setTitleTextColor(getResources().getColor(R.color.picker_color));
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(getResources().getColor(R.color.picker_color));
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(getResources().getColor(R.color.picker_color));
        picker.setSubmitTextSize(13);
        picker.setTextColor(getResources().getColor(R.color.picker_color), 0xFF999999);
        WheelView.DividerConfig config = new WheelView.DividerConfig();
        config.setColor(getResources().getColor(R.color.picker_color));//?????????
        config.setAlpha(140);//????????????
        config.setRatio((float) (1.0 / 8.0));//?????????
        picker.setDividerConfig(config);
        picker.setBackgroundColor(0xFFEEEEEE);
        //picker.setSelectedItem(isChinese ? "?????????" : "Virgo");
        picker.setSelectedIndex(7);
        picker.setCanceledOnTouchOutside(true);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
//                itemConstellation.setDetailText(item);
                gouche_tv.setText(item);
            }
        });
        picker.show();
    }


    public void onConstellationPicker() {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("??????");
        OptionPicker picker = new OptionPicker(this,
                isChinese ? new String[]{
                        "?????????", "?????????", "?????????", "?????????", "?????????", "?????????",
                        "?????????", "?????????", "?????????", "?????????", "?????????", "?????????"
                } : new String[]{
                        "Aquarius", "Pisces", "Aries", "Taurus", "Gemini", "Cancer",
                        "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn"
                });

        picker.setCycleDisable(false);//???????????????
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(30);
        picker.setTopLineColor(getResources().getColor(R.color.line_color));
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "?????????" : "Please pick");
        picker.setTitleTextColor(getResources().getColor(R.color.picker_color));
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(getResources().getColor(R.color.picker_color));
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(getResources().getColor(R.color.picker_color));
        picker.setSubmitTextSize(13);
        picker.setTextColor(getResources().getColor(R.color.picker_color), 0xFF999999);
        WheelView.DividerConfig config = new WheelView.DividerConfig();
        config.setColor(getResources().getColor(R.color.picker_color));//?????????
        config.setAlpha(140);//????????????
        config.setRatio((float) (1.0 / 8.0));//?????????
        picker.setDividerConfig(config);
        picker.setBackgroundColor(0xFFEEEEEE);
        //picker.setSelectedItem(isChinese ? "?????????" : "Virgo");
        picker.setSelectedIndex(7);
        picker.setCanceledOnTouchOutside(true);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
//                itemConstellation.setDetailText(item);
                xingzuo_tv.setText(item);
            }
        });
        picker.show();
    }

    public void onEducationPicker() {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("??????");
        OptionPicker picker = new OptionPicker(this,
                isChinese ? new String[]{
                        "??????", "??????", "??????", "??????", "??????", "??????",
                        "?????????", "??????", "??????"
                } : new String[]{
                        "??????", "??????", "??????", "??????", "??????", "??????",
                        "?????????", "??????", "??????"
                });

        picker.setCycleDisable(false);//???????????????
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(30);
        picker.setTopLineColor(getResources().getColor(R.color.line_color));
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "?????????" : "Please pick");
        picker.setTitleTextColor(getResources().getColor(R.color.picker_color));
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(getResources().getColor(R.color.picker_color));
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(getResources().getColor(R.color.picker_color));
        picker.setSubmitTextSize(13);
        picker.setTextColor(getResources().getColor(R.color.picker_color), 0xFF999999);
        WheelView.DividerConfig config = new WheelView.DividerConfig();
        config.setColor(getResources().getColor(R.color.picker_color));//?????????
        config.setAlpha(140);//????????????
        config.setRatio((float) (1.0 / 8.0));//?????????
        picker.setDividerConfig(config);
        picker.setBackgroundColor(0xFFEEEEEE);
        //picker.setSelectedItem(isChinese ? "?????????" : "Virgo");
        picker.setSelectedIndex(7);
        picker.setCanceledOnTouchOutside(true);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
//                itemConstellation.setDetailText(item);
                xueli_tv.setText(item);
            }
        });
        picker.show();
    }

    public void onOccupationPicker() {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("??????");
        OptionPicker picker = new OptionPicker(this,
                isChinese ? new String[]{
                        "??????/??????", "??????/??????", "????????????", "??????/??????/??????", "?????????", "????????????",
                        "????????????", "??????/??????/??????", "??????/?????????", "??????/??????", "??????/??????/??????", "??????/??????"
                } : new String[]{
                        "??????/??????", "??????/??????", "????????????", "??????/??????/??????", "?????????", "????????????",
                        "????????????", "??????/??????/??????", "??????/?????????", "??????/??????", "??????/??????/??????", "??????/??????"
                });

        picker.setCycleDisable(false);//???????????????
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(30);
        picker.setTopLineColor(getResources().getColor(R.color.line_color));
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "?????????" : "Please pick");
        picker.setTitleTextColor(getResources().getColor(R.color.picker_color));
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(getResources().getColor(R.color.picker_color));
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(getResources().getColor(R.color.picker_color));
        picker.setSubmitTextSize(13);
        picker.setTextColor(getResources().getColor(R.color.picker_color), 0xFF999999);
        WheelView.DividerConfig config = new WheelView.DividerConfig();
        config.setColor(getResources().getColor(R.color.picker_color));//?????????
        config.setAlpha(140);//????????????
        config.setRatio((float) (1.0 / 8.0));//?????????
        picker.setDividerConfig(config);
        picker.setBackgroundColor(0xFFEEEEEE);
        //picker.setSelectedItem(isChinese ? "?????????" : "Virgo");
        picker.setSelectedIndex(7);
        picker.setCanceledOnTouchOutside(true);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
//                itemConstellation.setDetailText(item);
                zhiye_tv.setText(item);
            }
        });
        picker.show();
    }

    public void onEmotionPicker() {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("??????");
        OptionPicker picker = new OptionPicker(this,
                isChinese ? new String[]{
                        "??????", "?????????", "??????", "??????", "??????", "??????"
                } : new String[]{
                        "??????", "?????????", "??????", "??????", "??????", "??????"
                });

        picker.setCycleDisable(false);//???????????????
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(30);
        picker.setTopLineColor(getResources().getColor(R.color.line_color));
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "?????????" : "Please pick");
        picker.setTitleTextColor(getResources().getColor(R.color.picker_color));
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(getResources().getColor(R.color.picker_color));
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(getResources().getColor(R.color.picker_color));
        picker.setSubmitTextSize(13);
        picker.setTextColor(getResources().getColor(R.color.picker_color), 0xFF999999);
        WheelView.DividerConfig config = new WheelView.DividerConfig();
        config.setColor(getResources().getColor(R.color.picker_color));//?????????
        config.setAlpha(140);//????????????
        config.setRatio((float) (1.0 / 8.0));//?????????
        picker.setDividerConfig(config);
        picker.setBackgroundColor(0xFFEEEEEE);
        //picker.setSelectedItem(isChinese ? "?????????" : "Virgo");
        picker.setSelectedIndex(7);
        picker.setCanceledOnTouchOutside(true);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
//                itemConstellation.setDetailText(item);
                qinggan_tv.setText(item);
            }
        });
        picker.show();
    }


    public static final int RESULT_NICKNAME_CODE = 1;
    public static final int RESULT_SELF_LABEL = 4;
    public static final int RESULT_PHONE = 5;
    public static final String USER_NICKNAME = "USER_NICKNAME";
    public static final String USER_BODY = "USER_BODY";
    public static final String USER_LABEL = "USER_LABEL";
    public static final String USER_PHONE = "USER_PHONE";

    public static final String STATUS = "STATUS";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_SELF_INTRODUCE_CODE) {
                String name = data.getStringExtra(USER_BODY);
                jieshao_tv.setText(name);
            }

            if (resultCode == RESULT_SELF_LABEL) {
                String lableName = data.getStringExtra(USER_LABEL);
                biaoqian_tv.setText(lableName);
            }
        }

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    if (selectImageType == 0) {
                        selectImageType = 0;
                        headImgFile = new File(PictureSelector.obtainMultipleResult(data).get(0).getCutPath());
                        headImg.setImageBitmap(ImageUtil.getBitmapByPath(headImgFile.getAbsolutePath()));
                    } else {
                        // ????????????????????????
                        selectList = PictureSelector.obtainMultipleResult(data);
                        //????????????
                        filesByAll.clear();
                        for (LocalMedia item : selectList) {
                            filesByAll.add(new File(item.getPath()));
                        }

                        redactAdapter.setList(selectList);
                        redactAdapter.notifyDataSetChanged();
                    }
                    break;
                case 20:
                    // ????????????????????????
                    selectList = PictureSelector.obtainMultipleResult(data);
                    for (LocalMedia media : selectList) {
                        Log.i("??????-----???", media.getPath());
                        MediaMetadataRetriever media1 = new MediaMetadataRetriever();
                        media1.setDataSource(media.getPath());// videoPath ?????????????????????
                        Bitmap bitmap = media1.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
                        iv_fengmian.setImageBitmap(bitmap);//?????????ImageView????????????

                        Uri uri = bitmap2uri(EditActivity.this, bitmap);
                        File file = uriToFile(uri, EditActivity.this);
                        Log.d("ret", "joker    ????????????file   " + file.getPath());
                        showLoadingDialog("?????????...");
                        uploadVideo(file);

                    }
                    break;
                default:
                    break;
            }
        }
    }


    /**
     * ????????????????????????
     *
     * @return true-false
     */
    private boolean isChangeHead() {
        return true;
    }

    /**
     * ?????????
     */
    private void doChangeHead() {
        if (isChangeHead()) {
            doSelectImage(0);
        } else {
            showDialogMsg(getString(R.string.head_img));
        }
    }

    /**
     * ????????????
     */
    private void showDialogSex() {
        if (isAlterSex) {
            showDialogChecked();
        } else {
            showToastMsg(getString(R.string.sex_not_change));
        }
    }

    /**
     * ???????????????????????????
     */
    private void showDialogMsg(String string) {
        new QMUIDialog.MessageDialogBuilder(getNowContext())
                .setTitle(string + getString(R.string.not_change))
                .setMessage("??????????????????" + string + "," + string + "???????????????????????????!")
                .addAction(0, "?????????", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * ???????????????????????????
     */
    private void showDialogEdNicknameText() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(getNowContext());
        builder.setTitle(getString(R.string.edit_nickname))
                .setPlaceholder("?????????30????????????????????????")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("?????????", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("????????????", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        if (text != null && text.length() > 6) {
                            showToastMsg("????????????????????????6????????????");
                            return;
                        }
                        if (text != null && text.length() > 0) {
                            //??????????????????
                            redactNameText.setText(text);
                            dialog.dismiss();
                        } else {
                            //?????????
                            showToastMsg("?????????????????????!");
                        }
                    }
                }).show();
    }

    /**
     * ???????????????????????????
     */
    private void showDialogSignEdText() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(getNowContext());
        builder.setTitle(getString(R.string.edit_sign))
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("?????????", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("????????????", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        if (text != null && text.length() > 15) {
                            showToastMsg("????????????????????????15????????????");
                            return;
                        }
                        if (text != null && text.length() > 0) {
                            //??????????????????
                            signTv.setText(text);
                            dialog.dismiss();
                        } else {
                            //?????????
                            showToastMsg("?????????????????????!");
                        }
                    }
                }).show();
    }

    /**
     * ?????????????????????
     */
    private void showDialogChecked() {
        final String[] items = new String[]{"???", "???"};
        new QMUIDialog.MenuDialogBuilder(getNowContext())
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        redactSexText.setText(items[which]);
                        sex = which + 1;
                        dialog.dismiss();
                    }
                })
                .show();
    }

    //????????????
    private void uploadVideo(File file) {
        cuckooQiniuFileUploadUtils.uploadFileLocalMedia(LiveConstant.VIDEO_DIR, selectList, new CuckooQiniuFileUploadUtils.CuckooQiniuFileUploadCallback() {
            @Override
            public void onUploadFileSuccess(List<String> fileUrlList) {
                hideLoadingDialog();
                if (fileUrlList.size() > 0) {
                    uploadVideoUrl = fileUrlList.get(0);
                    Log.d("ret", "joker     ====" + fileUrlList.get(0) + "      " + fileUrlList.size());
                    uploadImage(file);
                } else {
                    ToastUtils.showLong("???????????????????????????");
                }
            }
        });
    }

    //????????????
    private void uploadImage(File file) {
        List<File> list = new ArrayList<>();
        list.add(file);
        cuckooQiniuFileUploadUtils.uploadFile(LiveConstant.IMG_DIR, list, new CuckooQiniuFileUploadUtils.CuckooQiniuFileUploadCallback() {
            @Override
            public void onUploadFileSuccess(List<String> fileUrlList) {
//                hideLoadingDialog();
                if (fileUrlList.size() > 0) {
                    uploadImageUrl = fileUrlList.get(0);
                    Log.d("ret", "joker    ????????????" + uploadImageUrl);
                } else {
                    ToastUtils.showLong("???????????????????????????");
                }
            }
        });
    }


    /**
     * Bitmap?????????File
     */

    private Uri bitmap2uri(Context c, Bitmap b) {//c.getCacheDir()
        //   /Android/data/????????????/cache/1600739295328.jpg
        File path = new File(c.getExternalCacheDir() + File.separator + System.currentTimeMillis() + ".jpg");
        Log.d("ret", "joker      " + path.getAbsolutePath() + "     ===getAbsolutePath===" + path.getParent());
        try {
            OutputStream os = new FileOutputStream(path);
            b.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.close();
            return Uri.fromFile(path);
        } catch (Exception ignored) {
        }
        return null;
    }


    public static File uriToFile(Uri uri, Context context) {
        String path = null;
        if ("file".equals(uri.getScheme())) {
            path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = context.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=").append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA}, buff.toString(), null, null);
                int index = 0;
                int dataIdx = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cur.getInt(index);
                    dataIdx = cur.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    path = cur.getString(dataIdx);
                }
                cur.close();
                if (index == 0) {
                } else {
                    Uri u = Uri.parse("content://media/external/images/media/" + index);
                    System.out.println("temp uri is :" + u);
                }
            }
            if (path != null) {
                return new File(path);
            }
        } else if ("content".equals(uri.getScheme())) {
            // 4.2.2??????
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                path = cursor.getString(columnIndex);
            }
            cursor.close();

            return new File(path);
        } else {
            //Log.i(TAG, "Uri Scheme:" + uri.getScheme());
        }
        return null;
    }

}

