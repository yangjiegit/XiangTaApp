package com.muse.xiangta.ui;

import android.content.Context;
import android.view.View;

import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;

import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class RedEnvelopesDetailsActivity extends BaseActivity {


    private String red_envelope_id;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_red_envelopes_details;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        red_envelope_id = getIntent().getStringExtra("red_envelope_id");

        Api.red_envelope_detail(uId, uToken, red_envelope_id, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

            }
        });
    }

    @OnClick(R.id.iv_back)
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
