package com.muse.xiangta.ui;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class ApplicationListActivity extends BaseActivity {

    @BindView(R.id.sw_refresh)
    SwipeRefreshLayout sw_refresh;
    @BindView(R.id.rv_data)
    RecyclerView rv_data;


    private int page = 1;
    private int limit = 10;
    private String family_id;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_application_list;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

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
    protected void initData() {
        family_id = getIntent().getStringExtra("family_id");

        initRecyclerView();
    }

    private void initRecyclerView() {
        rv_data.setLayoutManager(new LinearLayoutManager(this));

        page = 1;
        getAuditMemberListData(page, limit);
    }

    private void getAuditMemberListData(int page, int limit) {
        Api.getAuditMemberList(uId, uToken, family_id, String.valueOf(page), String.valueOf(limit), new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

            }
        });
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
