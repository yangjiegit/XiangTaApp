package com.muse.xiangta.ui;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;

import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.ui.view.LastInputEditText;
import com.muse.xiangta.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import okhttp3.Call;
import okhttp3.Response;

public class WithdrawalActivity extends BaseActivity {

    private int is_check = -1;

    @BindView(R.id.iv_zhifubao)
    ImageView iv_zhifubao;
    @BindView(R.id.iv_weixin)
    ImageView iv_weixin;
    @BindView(R.id.et_text)
    LastInputEditText et_text;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_withdrawal;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        et_text.addTextChangedListener(new TextWatcher() {
            int l = 0;////////记录字符串被删除字符之前，字符串的长度
            int location = 0;//记录光标的位置

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
                l = s.length();
                location = et_text.getSelectionStart();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                if (s.toString().startsWith("0")) {
                    et_text.setText("1");
                    et_text.setSelection(1);
                }
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.ll_zhifubao, R.id.ll_weixin, R.id.tv_comm})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_zhifubao:
                is_check = 1;
                iv_zhifubao.setImageResource(R.mipmap.img_check_1);
                iv_weixin.setImageResource(R.mipmap.img_check_2);
                break;
            case R.id.ll_weixin:
                is_check = 2;
                iv_zhifubao.setImageResource(R.mipmap.img_check_2);
                iv_weixin.setImageResource(R.mipmap.img_check_1);
                break;
            case R.id.tv_comm:
                //提交
                if (StringUtils.isEmpty(et_text.getText().toString().trim())) {
                    showToastMsg("提现金额不能为空");
                    return;
                } else if (is_check == -1) {
                    showToastMsg("请选择提现方式");
                    return;
                } else {
                    if (is_check == 2) {
                        withdrawData("wx");
                    }
                }
                break;
        }
    }

    private void withdrawData(String type) {
        Api.withdraw(uId, uToken, type, et_text.getText().toString().trim(), new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (!StringUtils.isEmpty(s)) {
                    try {
                        int code = new JSONObject(s).getInt("code");
                        if (code == 1) {
                            int code2 = new JSONObject(s).getJSONObject("data").getInt("code");
                            if (code2 == 2) {
                                clickWeChat();
                            } else if (code2 == 3) {
                                //支付宝授权

                            } else {
                                showToastMsg("提现成功");
                                finish();
                            }
                        } else {
                            showToastMsg(new JSONObject(s).getString("msg"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    private void clickWeChat() {
        Platform plat = ShareSDK.getPlatform(Wechat.NAME);
        //执行登录，登录后在回调里面获取用户资料
        plat.showUser(null);
        plat.SSOSetting(false);  //设置false表示使用SSO授权方式
        plat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int action, HashMap<String, Object> hashMap) {

                //用户资源都保存到res
                //通过打印res数据看看有哪些数据是你想要的
                if (action == Platform.ACTION_USER_INFOR) {
                    final PlatformDb platDB = platform.getDb();//获取数平台数据DB
                    //通过DB获取各种数据
                    platDB.getUserId();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            doPlatLogin(platDB.getUserId(), 3);
                        }
                    });
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
        plat.removeAccount(true);
    }

    //三方授权登录
    private void doPlatLogin(final String platId, final int loginway) {
        Api.bindWx(uId, uToken, platId, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (!StringUtils.isEmpty(s)) {
                    try {
                        int code = new JSONObject(s).getInt("code");
                        if (code == 1) {
                            showToastMsg("绑定成功");
                            withdrawData("wx");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    @Override
    protected void initPlayerDisplayData() {

    }


}
