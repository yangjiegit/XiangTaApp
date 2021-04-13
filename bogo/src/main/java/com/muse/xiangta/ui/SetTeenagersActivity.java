package com.muse.xiangta.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.muse.xiangta.R;
import com.muse.xiangta.base.BaseActivity;

import butterknife.OnClick;

public class SetTeenagersActivity extends BaseActivity {


    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_set_teenagers;
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

    @OnClick({R.id.tv_comm, R.id.iv_back})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_comm:
                startActivityForResult(new
                        Intent(this, SetTeenagersPassActivity.class), 20);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
