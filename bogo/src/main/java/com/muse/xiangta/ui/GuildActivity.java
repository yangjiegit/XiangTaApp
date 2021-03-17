package com.muse.xiangta.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.muse.xiangta.R;
import com.muse.xiangta.base.BaseActivity;

public class GuildActivity extends BaseActivity {


    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_guild;
    }

    @Override
    protected void initView() {
        startActivity(new Intent(GuildActivity.this, SplashActivity.class));
    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initPlayerDisplayData() {

    }

}
