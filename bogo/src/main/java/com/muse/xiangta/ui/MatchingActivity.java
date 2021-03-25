package com.muse.xiangta.ui;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.ui.common.Common;
import com.muse.xiangta.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import pl.droidsonroids.gif.GifImageView;

public class MatchingActivity extends BaseActivity {

    @BindView(R.id.gv_image)
    GifImageView gv_image;
    @BindView(R.id.fl_layout)
    FrameLayout fl_layout;

    private String type;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_matching;
    }

    @Override
    protected void initView() {
        fl_layout.setOnClickListener(this);
    }

    @OnClick(R.id.iv_back)
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.fl_layout:
                getVideoCallData();
                break;
        }
    }

    private void getVideoCallData() {
        Api.videoCall(uId, uToken, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker   匹配数据 " + s);
                if (!StringUtils.isEmpty(s)) {
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        String emcee_id = jsonObject.getString("emcee_id");
                        if (type.equals("1")) {
                            callVoice(emcee_id);
                        } else {
                            callThisPlayer(emcee_id);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void callThisPlayer(String emcee_id) {
        Log.d("ret", "joker   通话人的id====" + emcee_id);
        showToastMsg(getString(R.string.now_call));
        Common.callVideo(this, emcee_id, 0);
    }

    private void callVoice(String emcee_id) {
        Log.d("ret", "joker   通话人的id====" + emcee_id);
        showToastMsg(getString(R.string.now_call));
        Common.callVideo(this, emcee_id, 1);
    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        type = getIntent().getStringExtra("type");
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
