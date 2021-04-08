package com.muse.xiangta.ui;

import android.content.Context;
import android.view.View;

import com.muse.xiangta.R;
import com.muse.xiangta.base.BaseActivity;

import butterknife.OnClick;

public class WithdrawalActivity extends BaseActivity {


    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_withdrawal;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {

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
