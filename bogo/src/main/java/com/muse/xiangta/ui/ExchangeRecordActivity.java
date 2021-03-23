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

public class ExchangeRecordActivity extends BaseActivity {

    @BindView(R.id.sw_layout)
    SwipeRefreshLayout sw_layout;
    @BindView(R.id.rv_data)
    RecyclerView rv_data;

    private int page = 1;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_exchange_record;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        page = 1;
        getGoldcoinLogData(page);

        initRecyclerView();
    }

    private void getGoldcoinLogData(int page) {
        Api.getGoldcoinLog(uId, uToken, String.valueOf(page), new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker    " + s);
            }
        });
    }

    private void initRecyclerView() {
        rv_data.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
