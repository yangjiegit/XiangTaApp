package com.muse.xiangta.ui;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.ToastUtils;
import com.fm.openinstall.OpenInstall;
import com.fm.openinstall.listener.AppInstallAdapter;
import com.fm.openinstall.model.AppData;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.api.ApiUtils;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.inter.JsonCallback;
import com.muse.xiangta.json.JsonRequestUserBase;
import com.muse.xiangta.modle.CuckooOpenInstallModel;
import com.muse.xiangta.ui.common.LoginUtils;
import com.muse.xiangta.utils.StringUtils;
import com.muse.xiangta.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/***
 * 绑定手机号
 */
public class BindPhoneActivity extends BaseActivity {

    @BindView(R.id.et_mobile)
    EditText et_mobile;

    @BindView(R.id.et_code)
    EditText et_code;

    @BindView(R.id.tv_send_code)
    TextView tv_send_code;

    private String uuid, open_id;


    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bind_phone;
    }

    @Override
    protected void initView() {
        getTopBar().setTitle("绑定手机号");
//        String state = getIntent().getStringExtra("state");

        //0 不强制绑定  1强制绑定
//        if (!"1".equals(state)) {
//            Button rightBtn = getTopBar().addRightTextButton("跳过", R.id.right_btn);
//            rightBtn.setTextColor(getResources().getColor(R.color.black));
//            rightBtn.setOnClickListener(this);
//        }

    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        uuid = Utils.getUniquePsuedoID();
        open_id = getIntent().getStringExtra("open_id");
    }

    @OnClick({R.id.tv_send_code, R.id.btn_submit})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_btn:
                //跳转页面
                ToastUtils.showLong(R.string.login_success);

                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                break;

            //发送验证码
            case R.id.tv_send_code:
                clickSendCode();
                break;

            //绑定
            case R.id.btn_submit:
                clickDoLogin();
                break;
            default:
                break;
        }
    }


    private void clickDoLogin() {
        if (!tv_send_code.getText().toString().equals("")) {
            bindPhone(et_mobile.getText().toString(), et_code.getText().toString());
        } else {
            showToastMsg(getString(R.string.mobile_login_code_not_empty));
        }
    }

    private void bindPhone(String mobile, String code) {
        Api.chkCode(mobile, code, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (!StringUtils.isEmpty(s)) {
                    try {
                        int code = new JSONObject(s).getInt("code");
                        if (code == 1) {
                            doPlatLogin(open_id, 4);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
            }
        });
    }


    //发送验证码
    private void clickSendCode() {

        if (Utils.isMobile(et_mobile.getText().toString())) {
            sendCode(et_mobile.getText().toString());
            tv_send_code.setEnabled(false);

            new CountDownTimer(60 * 1000, 1000) {

                @Override
                public void onTick(long l) {
                    tv_send_code.setText("（" + (l / 1000) + "）");
                }

                @Override
                public void onFinish() {
                    tv_send_code.setText("发送验证码");
                    tv_send_code.setEnabled(true);
                }
            }.start();

        } else {
            showToastMsg(getString(R.string.mobile_login_mobile_error));
        }
    }

    /**
     * 发送验证码
     */
    private void sendCode(String str) {
        Api.sendCodeByRegister(str, new JsonCallback() {
            @Override
            public Context getContextToJson() {
                return getNowContext();
            }

            @Override
            public void onSuccess(String s, Call call, Response response) {
                super.onSuccess(s, call, response);
                showToastMsg(ApiUtils.getJsonObj2(s).getString("msg"));
            }
        });
    }


    @Override
    protected void initPlayerDisplayData() {

    }

    @Override
    protected boolean hasTopBar() {
        return true;
    }


    //三方授权登录
    private void doPlatLogin(final String platId, final int loginway) {
        showLoadingDialog(getString(R.string.loading_login));
        //获取OpenInstall安装数据
        OpenInstall.getInstall(new AppInstallAdapter() {
            @Override
            public void onInstall(AppData appData) {
                //获取渠道数据
                String channelCode = appData.getChannel();
                //获取自定义数据
                String bindData = appData.getData();

                String inviteCode = "";
                String agent = "";
                if (!TextUtils.isEmpty(bindData)) {
                    CuckooOpenInstallModel data = JSON.parseObject(bindData, CuckooOpenInstallModel.class);
                    inviteCode = data.getInvite_code();
                    agent = data.getAgent();
                }
                //判断是否绑定手机号
                String finalInviteCode = inviteCode;
                String finalAgent = agent;
                Api.doPlatAuthLogin1(et_mobile.getText().toString().trim(),platId, finalInviteCode, finalAgent, uuid, loginway, new JsonCallback() {
                    @Override
                    public Context getContextToJson() {
                        return getNowContext();
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        hideLoadingDialog();
                        JsonRequestUserBase requestObj = JsonRequestUserBase.getJsonObj(s);
                        if (requestObj.getCode() == 1) {
                            //是否完善资料
                            if (requestObj.getData().getIs_reg_perfect() == 1) {
                                LoginUtils.doLogin(BindPhoneActivity.this, requestObj.getData());
                            } else {
                                Intent intent = new Intent(getNowContext(), PerfectRegisterInfoActivity.class);
                                intent.putExtra(PerfectRegisterInfoActivity.USER_LOGIN_INFO, requestObj.getData());
                                startActivity(intent);
                                finish();
                            }
                        }
                        showToastMsg(requestObj.getMsg());
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        hideLoadingDialog();
                    }
                });
                Log.d("OpenInstall", "getInstall : installData = " + appData.toString());
            }
        });


    }

}
