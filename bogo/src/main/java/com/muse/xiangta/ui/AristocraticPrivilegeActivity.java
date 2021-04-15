package com.muse.xiangta.ui;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.modle.AristocraticPrivilegeBean;
import com.muse.xiangta.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class AristocraticPrivilegeActivity extends BaseActivity {

    @BindView(R.id.iv_check1)
    ImageView iv_check1;
    @BindView(R.id.iv_check2)
    ImageView iv_check2;
    @BindView(R.id.iv_check3)
    ImageView iv_check3;

    private AristocraticPrivilegeBean aristocraticPrivilegeBean;
    private AristocraticPrivilegeBean.DataBean.NobleBean.ConcealLocationBean concealLocationBean;
    private AristocraticPrivilegeBean.DataBean.NobleBean.ConcealEnterBean concealEnterBean;
    private AristocraticPrivilegeBean.DataBean.NobleBean.ConcealProtectorBean concealProtectorBean;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_aristocratic_privilege;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @OnClick({R.id.iv_back, R.id.ll_check1, R.id.ll_check2, R.id.ll_check3})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_check1:
                if (concealLocationBean.getStatus() == 0) {
                    concealLocationBean.setStatus(1);
                    iv_check1.setImageResource(R.mipmap.iv_check1);
                } else {
                    concealLocationBean.setStatus(0);
                    iv_check1.setImageResource(R.mipmap.iv_check2);
                }
                setDiySetting(concealLocationBean.getCode(),
                        concealLocationBean.getStatus(), "noble");
                break;
            case R.id.ll_check2:
                if (concealEnterBean.getStatus() == 0) {
                    concealEnterBean.setStatus(1);
                    iv_check2.setImageResource(R.mipmap.iv_check1);
                } else {
                    concealEnterBean.setStatus(0);
                    iv_check2.setImageResource(R.mipmap.iv_check2);
                }
                setDiySetting(concealEnterBean.getCode(),
                        concealEnterBean.getStatus(), "noble");
                break;
            case R.id.ll_check3:
                if (concealProtectorBean.getStatus() == 0) {
                    concealProtectorBean.setStatus(1);
                    iv_check3.setImageResource(R.mipmap.iv_check1);
                } else {
                    concealProtectorBean.setStatus(0);
                    iv_check3.setImageResource(R.mipmap.iv_check2);
                }
                setDiySetting(concealProtectorBean.getCode(),
                        concealProtectorBean.getStatus(), "noble");
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

    @Override
    protected void initData() {
        getSetting();
    }

    private void getSetting() {
        Api.getSetting(uId, uToken, "noble", new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (!StringUtils.isEmpty(s)) {
                    aristocraticPrivilegeBean = new Gson().fromJson(s, AristocraticPrivilegeBean.class);
                    concealLocationBean = aristocraticPrivilegeBean.getData().getNoble().getConceal_location();
                    concealEnterBean = aristocraticPrivilegeBean.getData().getNoble().getConceal_enter();
                    concealProtectorBean = aristocraticPrivilegeBean.getData().getNoble().getConceal_protector();

                    if (concealLocationBean.getStatus() == 0) {
                        iv_check1.setImageResource(R.mipmap.iv_check2);
                    } else {
                        iv_check1.setImageResource(R.mipmap.iv_check1);
                    }
                    if (concealEnterBean.getStatus() == 0) {
                        iv_check2.setImageResource(R.mipmap.iv_check2);
                    } else {
                        iv_check2.setImageResource(R.mipmap.iv_check1);
                    }
                    if (concealProtectorBean.getStatus() == 0) {
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
