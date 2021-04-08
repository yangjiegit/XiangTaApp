package com.muse.xiangta.ui;

import android.content.Context;
import android.view.View;

import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.ui.view.LastInputEditText;
import com.muse.xiangta.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class ZhiFuBaoActivity extends BaseActivity {


    @BindView(R.id.et_name)
    LastInputEditText et_name;
    @BindView(R.id.et_code)
    LastInputEditText et_code;


    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_zhifubao;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @OnClick({R.id.iv_back, R.id.tv_comm})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_comm:
                if (StringUtils.isEmpty(et_name.getText().toString().trim())) {
                    showToastMsg("支付宝姓名不可为空");
                    return;
                } else if (StringUtils.isEmpty(et_code.getText().toString().trim())) {
                    showToastMsg("支付宝账号不可为空");
                    return;
                } else {
                    bindAli();
                }
                break;
        }
    }

    private void bindAli() {
        Api.bindAli(uId, uToken, et_code.getText().toString().trim(), et_name.getText().toString().trim(), new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (!StringUtils.isEmpty(s)) {
                    try {
                        int code = new JSONObject(s).getInt("code");
                        if (code == 1) {
                            showToastMsg("绑定成功");
                            setResult(40);
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
