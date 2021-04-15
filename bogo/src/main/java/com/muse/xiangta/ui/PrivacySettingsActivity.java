package com.muse.xiangta.ui;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.modle.ChargeBean;
import com.muse.xiangta.modle.PrivacySettingsBean;
import com.muse.xiangta.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class PrivacySettingsActivity extends BaseActivity {

    @BindView(R.id.iv_check1)
    ImageView iv_check1;
    @BindView(R.id.iv_check2)
    ImageView iv_check2;
    @BindView(R.id.iv_check3)
    ImageView iv_check3;

    private PrivacySettingsBean privacySettingsBean;
    private PrivacySettingsBean.DataBean.PrivateBean.ConcealWealthCharmThumbsUpBean concealWealthCharmThumbsUpBean;
    private PrivacySettingsBean.DataBean.PrivateBean.ConcealGiftsBean concealGiftsBean;
    private PrivacySettingsBean.DataBean.PrivateBean.ConcealMyProtectorBean concealMyProtectorBean;

    private List<ChargeBean> mList = new ArrayList<>();

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_privacy_settings;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @OnClick({R.id.iv_back, R.id.ll_check1, R.id.ll_check2, R.id.ll_check3
//            , R.id.tv_comm
    })
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
//            case R.id.tv_comm:
//                setDiySetting();
//                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_check1:
                if (concealWealthCharmThumbsUpBean.getStatus() == 0) {
                    concealWealthCharmThumbsUpBean.setStatus(1);
                    iv_check1.setImageResource(R.mipmap.iv_check1);
                } else {
                    concealWealthCharmThumbsUpBean.setStatus(0);
                    iv_check1.setImageResource(R.mipmap.iv_check2);
                }
                setDiySetting(concealWealthCharmThumbsUpBean.getCode(),
                        concealWealthCharmThumbsUpBean.getStatus(), "private");
                break;
            case R.id.ll_check2:
                if (concealGiftsBean.getStatus() == 0) {
                    concealGiftsBean.setStatus(1);
                    iv_check2.setImageResource(R.mipmap.iv_check1);
                } else {
                    concealGiftsBean.setStatus(0);
                    iv_check2.setImageResource(R.mipmap.iv_check2);
                }
                setDiySetting(concealGiftsBean.getCode(),
                        concealGiftsBean.getStatus(), "private");
                break;
            case R.id.ll_check3:
                if (concealMyProtectorBean.getStatus() == 0) {
                    concealMyProtectorBean.setStatus(1);
                    iv_check3.setImageResource(R.mipmap.iv_check1);
                } else {
                    concealMyProtectorBean.setStatus(0);
                    iv_check3.setImageResource(R.mipmap.iv_check2);
                }
                setDiySetting(concealMyProtectorBean.getCode(),
                        concealMyProtectorBean.getStatus(), "private");
                break;
        }
    }

    private void setDiySetting(String code, int status, String aPrivate) {
        Api.setDiySetting2(uId, uToken, code, String.valueOf(status), aPrivate, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                try {
                    int code = new JSONObject(s).getInt("code");
                    if (code == 1) {
                        showToastMsg("设置成功");
                        getSetting();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


//    private void setDiySetting() {
//        ChargeBean bean1 = new ChargeBean();
//        bean1.setCode(concealWealthCharmThumbsUpBean.getCode());
//        bean1.setStatus(concealWealthCharmThumbsUpBean.getStatus());
//        bean1.setVal(concealWealthCharmThumbsUpBean.getVal());
//        ChargeBean bean2 = new ChargeBean();
//        bean2.setCode(concealGiftsBean.getCode());
//        bean2.setStatus(concealGiftsBean.getStatus());
//        bean2.setVal(concealGiftsBean.getVal());
//        ChargeBean bean3 = new ChargeBean();
//        bean3.setCode(concealMyProtectorBean.getCode());
//        bean3.setStatus(concealMyProtectorBean.getStatus());
//        bean3.setVal(concealMyProtectorBean.getVal());
//        mList.add(bean1);
//        mList.add(bean2);
//        mList.add(bean3);
//
//
//        Gson gson = new Gson();
//        String charge = gson.toJson(mList);
//
//        Api.setDiySetting(uId, uToken, "private", charge, new StringCallback() {
//            @Override
//            public void onSuccess(String s, Call call, Response response) {
//                Log.d("ret", "joker      " + s);
//                try {
//                    int code = new JSONObject(s).getInt("code");
//                    if (code == 1) {
//                        showToastMsg("修改成功");
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//    }

    @Override
    protected void initData() {
        getSetting();
    }

    private void getSetting() {
        Api.getSetting(uId, uToken, "private", new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (!StringUtils.isEmpty(s)) {
                    privacySettingsBean = new Gson().fromJson(s, PrivacySettingsBean.class);
                    concealWealthCharmThumbsUpBean = privacySettingsBean.getData().getPrivateX().getConceal_wealth_charm_thumbs_up();
                    if (concealWealthCharmThumbsUpBean.getStatus() == 0) {
                        iv_check1.setImageResource(R.mipmap.iv_check2);
                    } else {
                        iv_check1.setImageResource(R.mipmap.iv_check1);
                    }
                    concealGiftsBean = privacySettingsBean.getData().getPrivateX().getConceal_gifts();
                    if (concealGiftsBean.getStatus() == 0) {
                        iv_check2.setImageResource(R.mipmap.iv_check2);
                    } else {
                        iv_check2.setImageResource(R.mipmap.iv_check1);
                    }
                    concealMyProtectorBean = privacySettingsBean.getData().getPrivateX().getConceal_my_protector();
                    if (concealMyProtectorBean.getStatus() == 0) {
                        iv_check3.setImageResource(R.mipmap.iv_check2);
                    } else {
                        iv_check3.setImageResource(R.mipmap.iv_check1);
                    }
                }
            }
        });
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
