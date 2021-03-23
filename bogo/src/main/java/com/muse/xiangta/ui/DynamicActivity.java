package com.muse.xiangta.ui;

import android.content.Context;

import com.muse.xiangta.R;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.fragment.DynamicMyFragment;

public class DynamicActivity extends BaseActivity {


    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_dynamic;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_layout, new DynamicMyFragment()).commit();
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
