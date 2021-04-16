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
import com.muse.xiangta.fragment.DynamicNotificationFragment;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class DynamicNotificationActivity extends BaseActivity {

    private int page = 1;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_dynamic_notification;
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

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_layout,
                new DynamicNotificationFragment()).commit();

    }


    @Override
    protected void initPlayerDisplayData() {

    }
}
