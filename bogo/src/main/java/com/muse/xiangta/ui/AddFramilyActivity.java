package com.muse.xiangta.ui;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.audiorecord.util.StringUtil;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.json.JsonDoRequestGetOssInfo;
import com.muse.xiangta.json.JsonRequestBase;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.ui.view.LastInputEditText;
import com.muse.xiangta.utils.CuckooQiniuFileUploadUtils;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class AddFramilyActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.et_name)
    LastInputEditText et_name;
    @BindView(R.id.et_content)
    LastInputEditText et_content;
    @BindView(R.id.tv_number)
    TextView tv_number;

    private List<File> mFileList = new ArrayList<>();


    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_add_framily;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.iv_back, R.id.iv_head, R.id.tv_add})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_head:
                doSelectImage();
                break;
            case R.id.tv_add:
                if (mFileList.size() == 0) {
                    showToastMsg("请选择家族头像");
                    return;
                } else if (StringUtils.isEmpty(et_name.getText().toString().trim())) {
                    showToastMsg("请填写家族名称");
                    return;
                } else if (StringUtils.isEmpty(et_content.getText().toString().trim())) {
                    showToastMsg("请填写家族宣言");
                    return;
                } else if (mFileList.size() > 0 &&
                        !StringUtils.isEmpty(et_name.getText().toString().trim()) &&
                        !StringUtils.isEmpty(et_content.getText().toString().trim())) {
                    getQiNiuToKen(mFileList);
                }
                break;
        }
    }


    /**
     * 执行选择图片添加操作
     */
    private void doSelectImage() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .enableCrop(true)// 是否裁剪 true or false
                .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null == data) {
            return;
        }
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    if (!StringUtils.isEmpty(PictureSelector.obtainMultipleResult(data).get(0).getCutPath())) {
                        String path = PictureSelector.obtainMultipleResult(data).get(0).getCutPath();
                        mFileList.clear();
                        mFileList.add(new File(path));
                        GlideImgManager.glideLoaderFile(
                                AddFramilyActivity.this, new File(path),
                                iv_head, 1
                        );
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void getQiNiuToKen(final List<File> files) {
        Api.doRequestGetOSSInfo(SaveData.getInstance().getId(), SaveData.getInstance().getToken(), new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                JsonDoRequestGetOssInfo jsonDoRequestGetOssInfo = (JsonDoRequestGetOssInfo) JsonRequestBase.getJsonObj(s, JsonDoRequestGetOssInfo.class);
                new CuckooQiniuFileUploadUtils().uploadFile(jsonDoRequestGetOssInfo, "img/", files,
                        new CuckooQiniuFileUploadUtils.CuckooQiniuFileUploadCallback() {
                            @Override
                            public void onUploadFileSuccess(List<String> fileUrlList) {
                                Log.d("ret", "joker 图片地址   " + fileUrlList.get(0));
                                if (null != fileUrlList && fileUrlList.size() > 0) {
                                    createFamily(fileUrlList.get(0));
                                }
                            }
                        });
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
            }
        });
    }

    private void createFamily(String s) {
        Api.createFamily(uId, uToken, s, et_name.getText().toString().trim(),
                et_content.getText().toString().trim(), new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if (!StringUtils.isEmpty(s)) {
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                int code = jsonObject.getInt("code");
                                if (code == 1) {
                                    showToastMsg("添加家族成功");
                                    finish();
                                } else {
                                    showToastMsg(jsonObject.getString("msg"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tv_number.setText(editable.length() + "/100");
            }
        });
    }

    @Override
    protected void initPlayerDisplayData() {

    }


}
