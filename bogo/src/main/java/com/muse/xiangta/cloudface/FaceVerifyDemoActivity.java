package com.muse.xiangta.cloudface;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.utils.StringUtils;
import com.webank.facelight.api.WbCloudFaceContant;
import com.webank.facelight.api.WbCloudFaceVerifySdk;
import com.webank.facelight.api.listeners.WbCloudFaceVerifyLoginListener;
import com.webank.facelight.api.listeners.WbCloudFaceVerifyResultListener;
import com.webank.facelight.api.result.WbFaceError;
import com.webank.facelight.api.result.WbFaceVerifyResult;
import com.webank.facelight.process.FaceVerifyStatus;
import com.webank.mbank.wehttp2.WeLog;
import com.webank.mbank.wehttp2.WeOkHttp;
import com.webank.mbank.wehttp2.WeReq;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class FaceVerifyDemoActivity extends BaseActivity {
    private static final String TAG = "FaceVerifyDemoActivity";
    private static final int SETTING_ACTIVITY = 2;

    private Button faceVerifyReflect;

    private EditText nameEt;
    private EditText idNoEt;
    private TextView sitEnv;
    private ImageView settingIv;

    private ProgressDialog progressDlg;

    private boolean isShowSuccess;
    private boolean isShowFail;
    private boolean isRecordVideo;
    private boolean isEnableCloseEyes;
    private boolean isPlayVoice;
    private boolean isIpv6;
    private String color;

    private AppHandler appHandler;
    private SignUseCase signUseCase;

    private String name;
    private String id;
    private String order;

    //此处为demo模拟，请输入标识唯一用户的userId
    private String userId = "IDAQwW1v" + System.currentTimeMillis();
    //此处为demo模拟，请输入32位随机数
    private String nonce = "";
    //此处为demo使用，由合作方提供包名申请，统一下发
    private String keyLicence = "ixOR6Ghbjzhju5qSUnN9H3mq6ASgrhvHwFxvoATAql9enHfe4d5ujdBqLZUgfBFIV/9fqwSdKPnWzBF8+VM2K8uo4aoJQW+KhfM2NJEr4Pl/qjFMkRIVpdAXk94nXlgcwbAyp6FjZk8hfC6RmymGWxTITY01HBsOESAuops1QuNFqkICLCJ43srQK0BdGDpWaDYFbolM/1NBiHY4lyARw045It8PyJYIHhzU/VmAF9a+HHcbSLws/jKWj51wrqLJTfCj0yFBRgDOxxkwPhyqNTvjPCovuj8RAbKWWsqRHxIHAO8E48IpkhwaiBojh7Vxs0EzDcdCy680FYueNshsSg==";
    private String compareType;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        order = "testReflect" + System.currentTimeMillis();
        appHandler = new AppHandler(this);
        signUseCase = new SignUseCase(appHandler);

        for (int i = 0; i < 32; i++) {
            nonce += String.valueOf((int) (Math.random() * 10));
        }

        initViews();
        initHttp();
        setListeners();

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                8);
    }

    @Override
    protected void initPlayerDisplayData() {

    }


    private void initViews() {
        initProgress();

        faceVerifyReflect = (Button) findViewById(R.id.faceVerifyLight);
        nameEt = (EditText) findViewById(R.id.et_name);
        idNoEt = (EditText) findViewById(R.id.et_idNo);

        settingIv = (ImageView) findViewById(R.id.wbcf_demo_setting);
        sitEnv = (TextView) findViewById(R.id.sit_env_logo);
    }

    private void initProgress() {
        if (progressDlg != null) {
            progressDlg.dismiss();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            progressDlg = new ProgressDialog(this);
        } else {
            progressDlg = new ProgressDialog(this);
            progressDlg.setInverseBackgroundForced(true);
        }
        progressDlg.setMessage("加载中...");
        progressDlg.setIndeterminate(true);
        progressDlg.setCanceledOnTouchOutside(false);
        progressDlg.setCancelable(true);
        progressDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDlg.setCancelable(false);
    }

    private void setListeners() {
        sitEnv.setText("4.0.0");

        //默认选择黑色模式
        color = WbCloudFaceContant.BLACK;
        //默认展示成功/失败页面
        isShowSuccess = false;
        isShowFail = false;
        //默认录制视频
        isRecordVideo = true;
        //默认播放提示语
        isPlayVoice = true;
        //默认不检测闭眼
        isEnableCloseEyes = false;
        //默认不是ipv6
        isIpv6 = false;
        //设置选择的比对类型  默认为公安网纹图片对比
        //公安网纹图片比对 WbCloudFaceContant.ID_CRAD
        //仅活体检测  WbCloudFaceContant.NONE
        //默认公安网纹图片对比
        compareType = WbCloudFaceContant.ID_CARD;

        faceVerifyReflect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                checkOnId(AppHandler.DATA_MODE_DESENSE);
                if (StringUtils.isEmpty(nameEt.getText().toString().trim())) {
                    showToastMsg("姓名不可为空");
                    return;
                } else if (StringUtils.isEmpty(idNoEt.getText().toString().trim())) {
                    showToastMsg("身份证不可为空");
                    return;
                } else {
                    getOauthInfo();
                }
            }
        });
    }

    private void getOauthInfo() {
        Api.getOauthInfo(uId, uToken, nonce, userId, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (!StringUtils.isEmpty(s)) {
                    try {
                        String sign = new JSONObject(s).getJSONObject("data").getString("sign");
                        if (!StringUtils.isEmpty(sign)) {
                            Api.getFaceId(uId, uToken,
                                    userId, order, nameEt.getText().toString().trim(),
                                    idNoEt.getText().toString().trim(), sign, new StringCallback() {
                                        @Override
                                        public void onSuccess(String s, Call call, Response response) {
                                            try {
                                                String face_id = new JSONObject(s).getJSONObject("data").getString("face_id");
                                                openCloudFaceService(FaceVerifyStatus.Mode.GRADE, "IDAQwW1v", order, sign, face_id);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    });
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private WeOkHttp myOkHttp = new WeOkHttp();

    private void initHttp() {
        //拿到OkHttp的配置对象进行配置
        //WeHttp封装的配置
        myOkHttp.config()
                //配置超时,单位:s
                .timeout(20, 20, 20)
                //添加PIN
                .log(WeLog.Level.BODY);
    }

    public void getFaceId(final FaceVerifyStatus.Mode mode, final String sign) {
        Log.d(TAG, "start getFaceId");

        final String order = "testReflect" + System.currentTimeMillis();
        final String appId = "IDAQwW1v";

        if (compareType.equals(WbCloudFaceContant.NONE)) {
            Log.d(TAG, "仅活体检测不需要faceId，直接拉起sdk");
            openCloudFaceService(mode, appId, order, sign, "");
            return;
        }

        String url = "https://idasc.webank.com/api/server/getfaceid";

        Log.d(TAG, "get faceId url=" + url);

        GetFaceId.GetFaceIdParam param = new GetFaceId.GetFaceIdParam();
        param.orderNo = order;
        param.webankAppId = appId;
        param.version = "1.0.0";
        param.userId = userId;
        param.sign = sign;
        if (compareType.equals(WbCloudFaceContant.ID_CARD)) {
            Log.d(TAG, "身份证对比" + url);
            param.name = name;
            param.idNo = id;
        }

        GetFaceId.requestExec(myOkHttp, url, param, new WeReq.Callback<GetFaceId.GetFaceIdResponse>() {
            @Override
            public void onStart(WeReq weReq) {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onFailed(WeReq weReq, WeReq.ErrType errType, int i, String s, IOException e) {
                progressDlg.dismiss();
                Log.d(TAG, "faceId请求失败:code=" + i + ",message=" + s);
                Toast.makeText(FaceVerifyDemoActivity.this, "登录异常(faceId请求失败:code=" + i + ",message=" + s + ")", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSuccess(WeReq weReq, GetFaceId.GetFaceIdResponse getFaceIdResponse) {
                if (getFaceIdResponse != null) {
                    String code = getFaceIdResponse.code;
                    if (code.equals("0")) {
                        GetFaceId.Result result = getFaceIdResponse.result;
                        if (result != null) {
                            String faceId = result.faceId;
                            if (!TextUtils.isEmpty(faceId)) {
                                Log.d(TAG, "faceId请求成功:" + faceId);
                                openCloudFaceService(mode, appId, order, sign, faceId);
                            } else {
                                progressDlg.dismiss();
                                Log.e(TAG, "faceId为空");
                                Toast.makeText(FaceVerifyDemoActivity.this, "登录异常(faceId为空)", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            progressDlg.dismiss();
                            Log.e(TAG, "faceId请求失败:getFaceIdResponse result is null.");
                            Toast.makeText(FaceVerifyDemoActivity.this, "登录异常(faceId请求失败:getFaceIdResponse result is null)", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        progressDlg.dismiss();
                        Log.e(TAG, "faceId请求失败:code=" + code + "msg=" + getFaceIdResponse.msg);
                        Toast.makeText(FaceVerifyDemoActivity.this, "登录异常(faceId请求失败:code=" + code + "msg=" + getFaceIdResponse.msg + ")", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDlg.dismiss();
                    Log.e(TAG, "faceId请求失败:getFaceIdResponse is null.");
                    Toast.makeText(FaceVerifyDemoActivity.this, "登录异常(faceId请求失败:getFaceIdResponse is null)", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void checkOnId(String mode) {
        String appId = "TIDA0001";

        if (compareType.equals(WbCloudFaceContant.ID_CARD)) {
            name = nameEt.getText().toString().trim();
            id = idNoEt.getText().toString().trim();
            if (name != null && name.length() != 0) {
                if (id != null && id.length() != 0) {
                    if (id.contains("x")) {
                        id = id.replace('x', 'X');
                    }

                    IdentifyCardValidate vali = new IdentifyCardValidate();
                    String msg = vali.validate_effective(id);
                    if (msg.equals(id)) {
                        Log.i(TAG, "Param right!");
                        Log.i(TAG, "Called Face Verify Sdk MODE=" + mode);
                        progressDlg.show();
                        signUseCase.execute(mode, appId, userId, nonce);
                    } else {
                        Toast.makeText(FaceVerifyDemoActivity.this, "用户证件号错误", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    Toast.makeText(FaceVerifyDemoActivity.this, "用户证件号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
            } else {
                Toast.makeText(FaceVerifyDemoActivity.this, "用户姓名不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            //自带源和活体对比不检测姓名 身份证
            Log.i(TAG, "No need check Param!");
            name = "";
            id = "";
            Log.i(TAG, "Called Face Verify Sdk!" + mode);
            progressDlg.show();
            signUseCase.execute(mode, appId, userId, nonce);
        }
    }

    //拉起刷脸sdk
    public void openCloudFaceService(FaceVerifyStatus.Mode mode, String appId, String order, String sign, String faceId) {
        Log.d(TAG, "openCloudFaceService");
        Bundle data = new Bundle();
        WbCloudFaceVerifySdk.InputData inputData = new WbCloudFaceVerifySdk.InputData(
                faceId,
                order,
                appId,
                "1.0.0",
                nonce,
                userId,
                sign,
                mode,
                keyLicence);

        data.putSerializable(WbCloudFaceContant.INPUT_DATA, inputData);
        //是否展示刷脸成功页面，默认不展示
        data.putBoolean(WbCloudFaceContant.SHOW_SUCCESS_PAGE, isShowSuccess);
        //是否展示刷脸失败页面，默认不展示
        data.putBoolean(WbCloudFaceContant.SHOW_FAIL_PAGE, isShowFail);
        //颜色设置,sdk内置黑色和白色两种模式，默认黑色
        //如果客户想定制自己的皮肤，可以传入WbCloudFaceContant.CUSTOM模式,此时可以配置ui里各种元素的色值
        //定制详情参考app/res/colors.xml文件里各个参数
        data.putString(WbCloudFaceContant.COLOR_MODE, color);
        //是否需要录制上传视频 默认需要
        data.putBoolean(WbCloudFaceContant.VIDEO_UPLOAD, isRecordVideo);
        //是否使用ipv6网络
        data.putBoolean(WbCloudFaceContant.IS_IPV6, isIpv6);
        //是否开启闭眼检测，默认不开启
        data.putBoolean(WbCloudFaceContant.ENABLE_CLOSE_EYES, isEnableCloseEyes);
        //是否播放提示音，默认播放
        data.putBoolean(WbCloudFaceContant.PLAY_VOICE, isPlayVoice);
        //设置选择的比对类型  默认为公安网纹图片对比
        //公安网纹图片比对 WbCloudFaceContant.ID_CRAD
        //自带比对源比对  WbCloudFaceContant.SRC_IMG
        //仅活体检测  WbCloudFaceContant.NONE
        //默认公安网纹图片比对
        data.putString(WbCloudFaceContant.COMPARE_TYPE, compareType);

        data.putBoolean(WbCloudFaceContant.IS_ENABLE_LOG, true);

        Log.d(TAG, "WbCloudFaceVerifySdk initSdk");
        WbCloudFaceVerifySdk.getInstance().initSdk(FaceVerifyDemoActivity.this, data, new WbCloudFaceVerifyLoginListener() {
            @Override
            public void onLoginSuccess() {
                //登录sdk成功
                Log.i(TAG, "onLoginSuccess");
                progressDlg.dismiss();

                //拉起刷脸页面
                WbCloudFaceVerifySdk.getInstance().startWbFaceVerifySdk(FaceVerifyDemoActivity.this, new WbCloudFaceVerifyResultListener() {
                    @Override
                    public void onFinish(WbFaceVerifyResult result) {
                        //得到刷脸结果
                        if (result != null) {
                            if (result.isSuccess()) {
                                Log.d(TAG, "刷脸成功! Sign=" + result.getSign() + "; liveRate=" + result.getLiveRate() +
                                        "; similarity=" + result.getSimilarity() + "userImageString=" + result.getUserImageString());
                                if (!isShowSuccess) {
//                                    Toast.makeText(FaceVerifyDemoActivity.this, "刷脸成功", Toast.LENGTH_SHORT).show();

                                    Api.authPassed(uId, uToken, nameEt.getText().toString().trim(),
                                            idNoEt.getText().toString().trim(), new StringCallback() {
                                                @Override
                                                public void onSuccess(String s, Call call, Response response) {
                                                    showToastMsg("认证成功");
                                                    finish();
                                                }
                                            });
                                }
                            } else {
                                WbFaceError error = result.getError();
                                if (error != null) {
                                    Log.d(TAG, "刷脸失败！domain=" + error.getDomain() + " ;code= " + error.getCode()
                                            + " ;desc=" + error.getDesc() + ";reason=" + error.getReason());
                                    if (error.getDomain().equals(WbFaceError.WBFaceErrorDomainCompareServer)) {
                                        Log.d(TAG, "对比失败，liveRate=" + result.getLiveRate() +
                                                "; similarity=" + result.getSimilarity());
                                    }
                                    if (!isShowSuccess) {
                                        Toast.makeText(FaceVerifyDemoActivity.this, "刷脸失败!" + error.getDesc(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Log.e(TAG, "sdk返回error为空！");
                                }
                            }
                        } else {
                            Log.e(TAG, "sdk返回结果为空！");
                        }
                        //测试用代码
                        //不管刷脸成功失败，只要结束了，自带对比和活体检测都更新userId
                        if (!compareType.equals(WbCloudFaceContant.ID_CARD)) {
                            Log.d(TAG, "更新userId");
                            userId = "WbFaceVerifyREF" + System.currentTimeMillis();
                        }
                    }
                });
            }

            @Override
            public void onLoginFailed(WbFaceError error) {
                //登录失败
                Log.i(TAG, "onLoginFailed!");
                progressDlg.dismiss();
                if (error != null) {
                    Log.d(TAG, "登录失败！domain=" + error.getDomain() + " ;code= " + error.getCode()
                            + " ;desc=" + error.getDesc() + ";reason=" + error.getReason());
                    if (error.getDomain().equals(WbFaceError.WBFaceErrorDomainParams)) {
                        Toast.makeText(FaceVerifyDemoActivity.this, "传入参数有误！" + error.getDesc(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(FaceVerifyDemoActivity.this, "登录刷脸sdk失败！" + error.getDesc(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e(TAG, "sdk返回error为空！");
                }
            }
        });
    }

    public void hideLoading() {
        if (progressDlg != null) {
            progressDlg.dismiss();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SETTING_ACTIVITY) {
            isShowSuccess = data.getBooleanExtra(WbCloudFaceContant.SHOW_SUCCESS_PAGE, true);
            isShowFail = data.getBooleanExtra(WbCloudFaceContant.SHOW_FAIL_PAGE, true);
            isRecordVideo = data.getBooleanExtra(WbCloudFaceContant.VIDEO_UPLOAD, false);
            isEnableCloseEyes = data.getBooleanExtra(WbCloudFaceContant.ENABLE_CLOSE_EYES, false);
            isPlayVoice = data.getBooleanExtra(WbCloudFaceContant.PLAY_VOICE, true);
            compareType = data.getStringExtra(WbCloudFaceContant.COMPARE_TYPE);
            color = data.getStringExtra(WbCloudFaceContant.COLOR_MODE);
            isIpv6 = data.getBooleanExtra(WbCloudFaceContant.IS_IPV6, false);


            if (compareType.equals(WbCloudFaceContant.NONE)) {
                nameEt.setText("");
                idNoEt.setText("");
            }
        }
    }
}
