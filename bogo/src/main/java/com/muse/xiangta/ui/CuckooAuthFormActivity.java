package com.muse.xiangta.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.muse.xiangta.LiveConstant;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.json.JsonRequestBase;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.modle.ConfigModel;
import com.muse.xiangta.utils.AddressPickTask;
import com.muse.xiangta.utils.CuckooQiniuFileUploadUtils;
import com.muse.xiangta.utils.FileUtil;
import com.muse.xiangta.utils.StringUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.NumberPicker;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;
import okhttp3.Call;
import okhttp3.Response;

public class CuckooAuthFormActivity extends BaseActivity {


    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;

    @BindView(R.id.rl_page_2)
    RelativeLayout rl_page_2;

    @BindView(R.id.view_from)
    ScrollView view_from;

    @BindView(R.id.tv_qq)
    TextView tv_qq;

    @BindView(R.id.iv_id_card_1)
    ImageView iv_id_card_1;

    @BindView(R.id.iv_id_card_2)
    ImageView iv_id_card_2;

    @BindView(R.id.rl_success)
    LinearLayout rl_success;

    @BindView(R.id.success_tv)
    TextView tv_success;

    private QMUICommonListItemView itemConstellation;
    private QMUICommonListItemView itemCity;
    private QMUICommonListItemView itemHeight;
    private QMUICommonListItemView itemWeight;

    private static final int REQUEST_CODE = 1;

    public static final int RESULT_NICKNAME_CODE = 1;
    public static final int RESULT_SELF_SIGN_CODE = 2;
    public static final int RESULT_SELF_INTRODUCE_CODE = 3;
    public static final int RESULT_SELF_LABEL = 4;
    public static final int RESULT_PHONE = 5;

    public static final String USER_NICKNAME = "USER_NICKNAME";
    public static final String USER_BODY = "USER_BODY";
    public static final String USER_LABEL = "USER_LABEL";
    public static final String USER_PHONE = "USER_PHONE";

    public static final String STATUS = "STATUS";

    private QMUICommonListItemView itemNickname;
    private QMUICommonListItemView itemSelfLabel;
    private QMUICommonListItemView itemIntroduce;
    private QMUICommonListItemView itemImageLabel;
    private QMUICommonListItemView itemPhone;

    private int status = -1;
    private int selectCardNum = 1;
    private String idCardIdImgFile1, idCardIdImgFile2;
    private String uploadFileIdCardImgUrl1, uploadFileIdCardImgUrl2;
    private CuckooQiniuFileUploadUtils cuckooQiniuFileUploadUtils;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_cuckoo_auth_form;
    }

    @Override
    protected void initView() {
        QMUIStatusBarHelper.translucent(this); // ??????????????????
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        QMUITopBar topBar = findViewById(R.id.qmui_top_bar);
        topBar.setTitle(getString(R.string.edit_auth_info));
        topBar.addLeftImageButton(R.drawable.icon_back_black, R.id.all_backbtn).setOnClickListener(this);


        itemNickname = groupListView.createItemView("??????");
        itemNickname.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        itemNickname.setId(R.id.auth_user_nickname);
        itemNickname.getDetailTextView().setVisibility(View.VISIBLE);
        itemNickname.getDetailTextView().setHint("???????????????");

//        itemNickname = groupListView.createItemView(getString(R.string.nickname));
//        itemNickname.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
//        itemNickname.setId(R.id.auth_user_nickname);
//        itemNickname.getDetailTextView().setVisibility(View.VISIBLE);
//        itemNickname.getDetailTextView().setHint(R.string.auth_input_nickname);

//        itemPhone = groupListView.createItemView(getString(R.string.mobile));
//        itemPhone.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
//        itemPhone.setId(R.id.auth_bind_phone);
//        itemPhone.getDetailTextView().setVisibility(View.VISIBLE);
//        itemPhone.getDetailTextView().setHint(R.string.auth_input_mobile);
//
//
//        itemHeight = groupListView.createItemView(getString(R.string.height));
//        itemHeight.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
//        itemHeight.setId(R.id.auth_user_height);
//        itemHeight.getDetailTextView().setVisibility(View.VISIBLE);
//        itemHeight.getDetailTextView().setHint(R.string.edit_input_height);
//
//
//        itemWeight = groupListView.createItemView(getString(R.string.weight));
//        itemWeight.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
//        itemWeight.setId(R.id.auth_user_weight);
//        itemWeight.getDetailTextView().setVisibility(View.VISIBLE);
//        itemWeight.getDetailTextView().setHint(R.string.edit_input_weight);
//
//        itemConstellation = groupListView.createItemView(getString(R.string.constellation));
//        itemConstellation.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
//        itemConstellation.setId(R.id.auth_user_constellation);
//        itemConstellation.getDetailTextView().setVisibility(View.VISIBLE);
//        itemConstellation.getDetailTextView().setHint(R.string.edit_auth_set_constellation);
//
//        itemCity = groupListView.createItemView(getString(R.string.edit_auth_city));
//        itemCity.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
//        itemCity.setId(R.id.auth_city);
//        itemCity.getDetailTextView().setVisibility(View.VISIBLE);
//        itemCity.getDetailTextView().setHint(R.string.edit_auth_set_city);
//
//        itemIntroduce = groupListView.createItemView(getString(R.string.edit_introduce));
//        itemIntroduce.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
//        itemIntroduce.setId(R.id.auth_introduce);
//        itemIntroduce.getDetailTextView().setVisibility(View.VISIBLE);
//        itemIntroduce.getDetailTextView().setHint(R.string.edit_auth_set_introduce);
//
//        itemImageLabel = groupListView.createItemView(getString(R.string.edit_auth_image_label));
//        itemImageLabel.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
//        itemImageLabel.setId(R.id.auth_image_label);
//        itemImageLabel.getDetailTextView().setVisibility(View.VISIBLE);
//        itemImageLabel.getDetailTextView().setHint(R.string.edit_auth_set_image_label);
//
//
        //       itemSelfLabel = groupListView.createItemView(getString(R.string.edit_auth_self_label));
//        itemSelfLabel.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
//        itemSelfLabel.setId(R.id.auth_self_label);
//        itemSelfLabel.getDetailTextView().setVisibility(View.VISIBLE);
//        itemSelfLabel.getDetailTextView().setHint(R.string.edit_auth_set_self_label);


        QMUIGroupListView.Section section = QMUIGroupListView.newSection(this)
                .addItemView(itemNickname, this);
//                .addItemView(itemPhone, this)
//                .addItemView(itemHeight, this)
//                .addItemView(itemWeight, this)
//                .addItemView(itemConstellation, this)
//                .addItemView(itemCity, this)
//                .addItemView(itemIntroduce, this)
//                .addItemView(itemImageLabel, this)
//                .addItemView(itemSelfLabel, this);
        section.addTo(groupListView);


        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSubmit();
            }
        });
    }

    @Override
    protected void initSet() {
        //????????????
        if (status == 1) {
            rl_success.setVisibility(View.VISIBLE);
            rl_page_2.setVisibility(View.GONE);
            view_from.setVisibility(View.GONE);

            tv_success.setText("???????????????????????????");
        } else if (status == 0) {
            //?????????
//            rl_success.setVisibility(View.VISIBLE);
//            rl_page_2.setVisibility(View.GONE);
//            view_from.setVisibility(View.GONE);

//            tv_success.setText("?????????");
        }

        tv_qq.setText(ConfigModel.getInitData().getCustom_service_qq());
    }

    @Override
    protected void initData() {
        status = getIntent().getIntExtra(STATUS, 0);
        cuckooQiniuFileUploadUtils = new CuckooQiniuFileUploadUtils();

    }

    @Override
    protected void initPlayerDisplayData() {

    }


    private void clickSubmit() {
        String nickname = itemNickname.getDetailText().toString();
//        String phone = itemPhone.getDetailText().toString();
//        String height = itemHeight.getDetailText().toString();
//        String weight = itemWeight.getDetailText().toString();
//        String constellation = itemConstellation.getDetailText().toString();
//        String city = itemCity.getDetailText().toString();
//        String introduce = itemIntroduce.getDetailText().toString();
//        String imageLabel = itemImageLabel.getDetailText().toString();
//        String selfLabel = itemSelfLabel.getDetailText().toString();

        if (TextUtils.isEmpty(nickname)) {
            showToastMsg(getString(R.string.nickname_not_emtpy));
            return;
        }

//        if (TextUtils.isEmpty(phone) || phone.length() != 11) {
//            showToastMsg(getString(R.string.edit_auth_tips_input_mobile));
//            return;
//        }
//
//        if (StringUtils.toInt(height) == 0) {
//            showToastMsg(getString(R.string.edit_auth_tips_select_height));
//            return;
//        }
//
//        if (StringUtils.toInt(weight) == 0) {
//            showToastMsg(getString(R.string.edit_auth_tips_select_weight));
//            return;
//        }
//
//        if (TextUtils.isEmpty(constellation)) {
//            showToastMsg(getString(R.string.edit_auth_tips_select_constellation));
//            return;
//        }
//
//        if (TextUtils.isEmpty(city)) {
//            showToastMsg(getString(R.string.edit_auth_tips_inout_city));
//            return;
//        }
//
//        if (TextUtils.isEmpty(introduce)) {
//            showToastMsg(getString(R.string.edit_auth_tips_input_introduce));
//            return;
//        }
//
//        if (TextUtils.isEmpty(imageLabel)) {
//            showToastMsg(getString(R.string.edit_auth_tips_inout_image_label));
//            return;
//        }
//
//        if (TextUtils.isEmpty(selfLabel)) {
//            showToastMsg(getString(R.string.edit_auth_tips_input_select_label));
//            return;
//        }

        if (idCardIdImgFile1 == null || !new File(idCardIdImgFile1).exists()) {
            showToastMsg(getString(R.string.edit_auth_tips_id_card_1));
            return;
        }

        if (idCardIdImgFile2 == null || !new File(idCardIdImgFile2).exists()) {
            showToastMsg(getString(R.string.edit_auth_tips_id_card_2));
            return;
        }

        getUploadOssSign();

    }

    private void getUploadOssSign() {
        showLoadingDialog(getString(R.string.loading_upload_info));

        List<File> cardImgList = new ArrayList<>();
        cardImgList.add(new File(idCardIdImgFile1));
        cardImgList.add(new File(idCardIdImgFile2));
        cuckooQiniuFileUploadUtils.uploadFile(LiveConstant.AUTH_IMG_DIR, cardImgList, new CuckooQiniuFileUploadUtils.CuckooQiniuFileUploadCallback() {
            @Override
            public void onUploadFileSuccess(List<String> fileUrlList) {
                if (fileUrlList.size() > 1) {
                    hideLoadingDialog();
                    uploadFileIdCardImgUrl1 = fileUrlList.get(0);
                    uploadFileIdCardImgUrl2 = fileUrlList.get(1);
                    submitAuthInfo();
                } else {
                    ToastUtils.showLong("????????????????????????????????????");
                    hideLoadingDialog();
                }
            }
        });
    }

    private void submitAuthInfo() {

        String nickname = itemNickname.getDetailText().toString();
//        String phone = itemPhone.getDetailText().toString();
//        String height = itemHeight.getDetailText().toString();
//        String weight = itemWeight.getDetailText().toString();
//        String constellation = itemConstellation.getDetailText().toString();
//        String city = itemCity.getDetailText().toString();
//        String introduce = itemIntroduce.getDetailText().toString();
//        String imageLabel = itemImageLabel.getDetailText().toString();
//        String selfLabel = itemSelfLabel.getDetailText().toString();

        HttpParams params = new HttpParams();
        params.put("nickname", nickname);
//        params.put("phone", phone);
//        params.put("height", height);
//        params.put("weight", weight);
//        params.put("constellation", constellation);
//        params.put("city", city);
//        params.put("introduce", introduce);
//        params.put("image_label", imageLabel);
//        params.put("self_label", selfLabel);
        params.put("uid", SaveData.getInstance().getId());
        params.put("token", SaveData.getInstance().getToken());
        params.put("auth_id_card_img_url1", uploadFileIdCardImgUrl1);
        params.put("auth_id_card_img_url2", uploadFileIdCardImgUrl2);

        showLoadingDialog("???????????????????????????");
        Log.e("submitAuthInfo", params.toString());
        Api.doRequestSubmitAuthInfo(params, new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e("submitAuthInfo", s);
                hideLoadingDialog();
                JsonRequestBase data = JsonRequestBase.getJsonObj(s, JsonRequestBase.class);
                if (StringUtils.toInt(data.getCode()) == 1) {
                    finish();
                }
                showToastMsg("????????????");
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                hideLoadingDialog();
                Log.e("submitAuthInfo", e.toString());
            }
        });
    }


    @OnClick({R.id.btn_know, R.id.iv_id_card_1, R.id.iv_id_card_2, R.id.btn_back})
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.all_backbtn:
                finish();
                break;

            case R.id.btn_back:
                finish();
                break;
            case R.id.iv_id_card_1:
                selectCardNum = 1;
                clickSelectIdCardImg();
                break;

            case R.id.iv_id_card_2:
                selectCardNum = 2;
                clickSelectIdCardImg();
                break;
            case R.id.btn_know:

                finish();
                break;
            case R.id.auth_user_weight:

                onNumberWeightPicker();
                break;
            case R.id.auth_user_height:

                onNumberHeightPicker();
                break;
            case R.id.auth_user_nickname:

                clickEditUserNickname();
                break;
            case R.id.auth_bind_phone:

                clickBindPhone();
                break;
            case R.id.auth_user_constellation:

                onConstellationPicker();
                break;
            case R.id.auth_city:

                onAddressPicker();
                break;
            case R.id.auth_self_label:

                clickEditSelfSign();
                break;
            case R.id.auth_introduce:

                clickEditSelfIntroduce();
                break;
            case R.id.auth_image_label:
                clickSelectLabel();
                break;
        }
    }

    private void clickSelectIdCardImg() {

        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .forResult(PictureConfig.CHOOSE_REQUEST);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {

            if (resultCode == RESULT_NICKNAME_CODE) {
                String name = data.getStringExtra(USER_NICKNAME);
                itemNickname.setDetailText(name);
            }

            if (resultCode == RESULT_SELF_SIGN_CODE) {
                String name = data.getStringExtra(USER_BODY);
                itemSelfLabel.setDetailText(name);
            }

            if (resultCode == RESULT_SELF_INTRODUCE_CODE) {
                String name = data.getStringExtra(USER_BODY);
                itemIntroduce.setDetailText(name);
            }

            if (resultCode == RESULT_SELF_LABEL) {
                String name = data.getStringExtra(USER_LABEL);
                itemImageLabel.setDetailText(name);
            }

            if (resultCode == RESULT_PHONE) {
                String name = data.getStringExtra(USER_PHONE);
                itemPhone.setDetailText(name);
            }
        }

        // ????????????????????????
        if (requestCode == PictureConfig.CHOOSE_REQUEST) {

            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            if (selectList.size() > 0) {
                Bitmap bitmap = BitmapFactory.decodeFile(selectList.get(0).getPath());
                if (selectCardNum == 1) {
                    boolean canshow = isCanshow(selectList.get(0).getPath());
                    if (canshow) {
                        idCardIdImgFile1 = selectList.get(0).getPath();
                        iv_id_card_1.setImageBitmap(bitmap);
                    } else {
                        ToastUtils.showShort("??????????????????6M??????????????????");
                    }

                } else {
                    boolean canshow = isCanshow(selectList.get(0).getPath());
                    if (canshow) {
                        idCardIdImgFile2 = selectList.get(0).getPath();
                        iv_id_card_2.setImageBitmap(bitmap);
                    } else {
                        ToastUtils.showShort("??????????????????6M??????????????????");
                    }

                }
            }
        }
    }

    /**
     * ??????????????????????????????????????????
     *
     * @param idCardIdImgFile
     */
    private boolean isCanshow(String idCardIdImgFile) {
        long fileSize = FileUtil.getFileSize(idCardIdImgFile);

        if (fileSize > (1024 * 1024 * 6)) {
            return false;
        } else {
            return true;
        }

    }

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
                itemHeight.setDetailText(item.toString());
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
                itemWeight.setDetailText(item.toString());
            }
        });
        picker.show();
    }

    public void onAddressPicker() {

        AddressPickTask task = new AddressPickTask(this);
        task.setHideProvince(false);
        task.setHideCounty(true);
        task.setCallback(new AddressPickTask.Callback() {
            @Override
            public void onAddressInitFailed() {
                showToastMsg("?????????????????????");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                if (county == null) {
                    itemCity.setDetailText(province.getAreaName() + "-" + city.getAreaName());
                } else {
                    itemCity.setDetailText(province.getAreaName() + "-" + city.getAreaName() + "-" + county.getAreaName());
                }
            }
        });
        task.execute("??????", "??????");
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
                itemConstellation.setDetailText(item);
            }
        });
        picker.show();
    }

    @Override
    protected boolean hasTopBar() {
        return false;
    }
}
