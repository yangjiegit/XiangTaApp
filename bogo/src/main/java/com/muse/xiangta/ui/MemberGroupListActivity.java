package com.muse.xiangta.ui;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

public class MemberGroupListActivity extends BaseActivity {

    @BindView(R.id.sw_refresh)
    SwipeRefreshLayout sw_refresh;
    @BindView(R.id.rv_data)
    RecyclerView rv_data;

    private String family_id;
    private int page = 1;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_member_group_list;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        initRecyclerView();

        family_id = getIntent().getStringExtra("family_id");
        page = 1;
        getMemberListData(page);
    }

    private void initRecyclerView() {
        rv_data.setLayoutManager(new LinearLayoutManager(this));


    }

    private void getMemberListData(int page) {
        Api.getMemberList(uId, uToken, family_id, String.valueOf(page), "10", new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker     " + s);
            }
        });
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
