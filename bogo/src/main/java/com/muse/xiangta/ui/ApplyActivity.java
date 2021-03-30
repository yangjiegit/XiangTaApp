package com.muse.xiangta.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.modle.ApplyBean;
import com.muse.xiangta.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class ApplyActivity extends BaseActivity {

    @BindView(R.id.tv_check1)
    TextView tv_check1;
    @BindView(R.id.tv_check2)
    TextView tv_check2;
    @BindView(R.id.tv_check3)
    TextView tv_check3;
    @BindView(R.id.tv_check4)
    TextView tv_check4;

    private ApplyBean mApplyBean;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_apply;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        getApplyInfoData();
    }

    @Override
    protected void initData() {

    }

    private void getApplyInfoData() {
        Api.applyInfo(uId, uToken, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker      " + s);
                if (!StringUtils.isEmpty(s)) {
                    mApplyBean = new Gson().fromJson(s, ApplyBean.class);
                    if (null != mApplyBean.getData()) {
                        switch (mApplyBean.getData().getCover_video()) {
                            case 0:
                                tv_check3.setText("审核中");
                                break;
                            case 1:
                                tv_check3.setText("已通过");
                                break;
                            case 2:
                                tv_check3.setText("未提交或未通过");
                                break;
                        }
                        switch (mApplyBean.getData().getIs_auth()) {
                            case 0:
                                tv_check2.setText("审核中");
                                break;
                            case 1:
                                tv_check2.setText("已通过");
                                break;
                            case 2:
                                tv_check2.setText("未提交或未通过");
                                break;
                        }
                        switch (mApplyBean.getData().getHas_avatar()) {
                            case 0:
                                tv_check1.setText("审核中");
                                break;
                            case 1:
                                tv_check1.setText("已通过");
                                break;
                            case 2:
                                tv_check1.setText("未提交或未通过");
                                break;
                        }
                        switch (mApplyBean.getData().getNeed_perfect_pictures()) {
                            case 0:
                                tv_check4.setText("审核中");
                                break;
                            case 1:
                                tv_check4.setText("已通过");
                                break;
                            case 2:
                                tv_check4.setText("未提交或未通过");
                                break;
                        }
                    }
                }
            }
        });
    }

    @OnClick({R.id.tv_comm, R.id.rl_check1, R.id.rl_check2, R.id.rl_check3, R.id.rl_check4})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_comm:
                //提交
                if (mApplyBean.getData().getIs_auth() == 1 &&
                        mApplyBean.getData().getNeed_perfect_pictures() == 1 &&
                        mApplyBean.getData().getHas_avatar() == 1 &&
                        mApplyBean.getData().getCover_video() == 1) {
                    applyData();
                }
                break;
            case R.id.rl_check1:
            case R.id.rl_check2:
            case R.id.rl_check3:
            case R.id.rl_check4:
                startActivity(new Intent(this, EditActivity.class));
                break;
        }
    }

    private void applyData() {
        Api.apply(uId, uToken, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (!StringUtils.isEmpty(s)) {
                    try {
                        int code = new JSONObject(s).getInt("code");
                        if (code == 1) {
                            showToastMsg("申请入驻成功");
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
