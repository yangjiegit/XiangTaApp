package com.muse.xiangta.ui;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.fm.openinstall.OpenInstall;
import com.fm.openinstall.listener.AppInstallAdapter;
import com.fm.openinstall.model.AppData;
import com.maning.imagebrowserlibrary.utils.StatusBarUtil;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.api.ApiUtils;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.inter.JsonCallback;
import com.muse.xiangta.json.JsonRequestUserBase;
import com.muse.xiangta.modle.ConfigModel;
import com.muse.xiangta.modle.CuckooOpenInstallModel;
import com.muse.xiangta.ui.common.LoginUtils;
import com.muse.xiangta.utils.Utils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jiguang.verifysdk.api.AuthPageEventListener;
import cn.jiguang.verifysdk.api.JVerificationInterface;
import cn.jiguang.verifysdk.api.JVerifyUIConfig;
import cn.jiguang.verifysdk.api.LoginSettings;
import cn.jiguang.verifysdk.api.VerifyListener;
import cn.sharesdk.facebook.Facebook;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import okhttp3.Call;
import okhttp3.Response;

public class CuckooMobileLoginActivity extends BaseActivity {

    @BindView(R.id.tv_send_code)
    TextView tv_send_code;

    @BindView(R.id.et_mobile)
    EditText et_mobile;

    @BindView(R.id.et_code)
    EditText et_code;

    @BindView(R.id.ll_qq)
    RelativeLayout ll_qq;

    @BindView(R.id.ll_wechat)
    RelativeLayout ll_wechat;

    @BindView(R.id.ll_facebook)
    RelativeLayout ll_facebook;

    @BindView(R.id.iv_check)
    ImageView iv_check;

    private String uuid;

    private boolean check = false;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_cuckoo_mobile_login;
    }

    @Override
    protected void initView() {

        //QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getTopBar().setTitle(getString(R.string.login));
        StatusBarUtil.setColor(this, getResources().getColor(R.color.transparent), 0);
        //StatusBarUtil.setColor(this,getResources().getColor(R.color.white));
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        ConfigModel config = ConfigModel.getInitData();
        if (config.getOpen_login_qq() == 1) {
            ll_qq.setVisibility(View.VISIBLE);
        }

        if (config.getOpen_login_wx() == 1) {
            ll_wechat.setVisibility(View.VISIBLE);
        }

        if (config.getOpen_login_facebook() == 1) {
            ll_facebook.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        uuid = Utils.getUniquePsuedoID();
    }

    @Override
    protected void initPlayerDisplayData() {

    }

    @OnClick({R.id.ll_wechat, R.id.ll_qq, R.id.ll_facebook, R.id.tv_send_code, R.id.btn_submit
            , R.id.tv_xieyi, R.id.tv_login_type, R.id.iv_check, R.id.tv_yonghu})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_yonghu:
                String intStr1 = "12";
                String url1 = "http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/" + intStr1 + ".html";
                startActivity(new Intent(this, XieYiActivity.class)
                        .putExtra("title", "用户协议").putExtra("url", url1));
                break;
            case R.id.iv_check:
                if (check == false) {
                    check = true;
                    iv_check.setImageResource(R.mipmap.img_check_true);
                } else {
                    check = false;
                    iv_check.setImageResource(R.mipmap.img_check_false);
                }
                break;
            case R.id.tv_login_type:
                if (check == true) {
                    yijian();
                } else {
                    showToastMsg("请查看勾选协议");
                }
                break;
            case R.id.tv_send_code:
                clickSendCode();
                break;
            case R.id.btn_submit:
                if (check == true) {
                    clickDoLogin();
                } else {
                    showToastMsg("请查看勾选协议");
                }
                break;
            case R.id.ll_wechat:
                if (check == true) {
                    clickWeChat();
                } else {
                    showToastMsg("请查看勾选协议");
                }
                break;
            case R.id.ll_qq:
                clickQQ();
                break;
            case R.id.ll_facebook:
                clickFacebook();
                break;
            case R.id.tv_xieyi:
                String intStr = "7";
                String url = "http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/" + intStr + ".html";
                startActivity(new Intent(this, XieYiActivity.class)
                        .putExtra("title", "隐私协议").putExtra("url", url));
                break;
            default:
                break;
        }
    }

    private void yijian() {
//        JVerificationInterface.getToken(this, 5000, new VerifyListener() {
//            @Override
//            public void onResult(int code, String content, String operator) {
//                if (code == 2000) {
//                    Log.d("ret", "joker   token=" + content + ", operator=" + operator);
//                } else {
//                    Log.d("ret", "joker   code=" + code + ", message=" + content);
//                }
//            }
//        });
//        JVerificationInterface.preLogin(this, 5000, new PreLoginListener() {
//            @Override
//            public void onResult(final int code, final String content) {
//                Log.d("ret", "joker     [" + code + "]message=" + content);
//            }
//        });

        JVerifyUIConfig portrait = new JVerifyUIConfig.Builder()
                .setNavText("一键登录")
                .setLogBtnText("一键登录")
                .build();
        JVerificationInterface.setCustomUIWithConfig(portrait);

        LoginSettings settings = new LoginSettings();
        settings.setAutoFinish(true);//设置登录完成后是否自动关闭授权页
        settings.setTimeout(15 * 1000);//设置超时时间，单位毫秒。 合法范围（0，30000],范围以外默认设置为10000
        settings.setAuthPageEventListener(new AuthPageEventListener() {
            @Override
            public void onEvent(int cmd, String msg) {
                //do something...
            }
        });//设置授权页事件监听
        JVerificationInterface.loginAuth(this, settings, new VerifyListener() {
            @Override
            public void onResult(int code, String content, String operator) {
                if (code == 6000) {
                    Log.d("ret", "joker  code=" + code + ", token=" + content + " ,operator=" + operator);
                    //获取OpenInstall安装数据
                    Api.jgLogin(content, Utils.getUniquePsuedoID(), new JsonCallback() {
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
                                    LoginUtils.doLogin(CuckooMobileLoginActivity.this, requestObj.getData());
//                                finish();
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

                } else {
                    Log.d("ret", "joker  code=" + code + ", message=" + content);

                }
            }
        });


    }

    private void clickDoLogin() {

        if (!tv_send_code.getText().toString().equals("")) {
            doPhoneLogin(et_mobile.getText().toString(), et_code.getText().toString());
        } else {
            showToastMsg(getString(R.string.mobile_login_code_not_empty));
        }
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


    //手机登录
    private void doPhoneLogin(final String mobile, final String code) {

        showLoadingDialog(getString(R.string.loading_login));

        //获取OpenInstall安装数据
        OpenInstall.getInstall(new AppInstallAdapter() {
            @Override
            public void onInstall(AppData appData) {
                //获取渠道数据
                String channelCode = appData.getChannel();
                //获取自定义数据
                String bindData = appData.getData();
                //bindData = "{\"agent\":\"5001\"}";

                String inviteCode = "";
                String agent = "";
                if (!TextUtils.isEmpty(bindData)) {
                    CuckooOpenInstallModel data = JSON.parseObject(bindData, CuckooOpenInstallModel.class);
                    inviteCode = data.getInvite_code();
                    agent = data.getAgent();
                }

                Api.userLogin(mobile, code, inviteCode, agent, Utils.getUniquePsuedoID(), new JsonCallback() {
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
                                LoginUtils.doLogin(CuckooMobileLoginActivity.this, requestObj.getData());
//                                finish();
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
            }
        });

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
    protected boolean hasTopBar() {
        return false;
    }

    private void clickFacebook() {
        Platform plat = ShareSDK.getPlatform(Facebook.NAME);

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
                            doPlatLogin(platDB.getUserId(), 4);
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

    private void clickQQ() {

        Platform plat = ShareSDK.getPlatform(QQ.NAME);

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
                            doPlatLogin(platDB.getUserId(), 2);
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

                Api.doPlatAuthLogin(platId, inviteCode, agent, uuid, loginway, new JsonCallback() {
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

                                LoginUtils.doLogin(CuckooMobileLoginActivity.this, requestObj.getData());
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
