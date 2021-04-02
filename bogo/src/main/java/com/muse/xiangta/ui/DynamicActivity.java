package com.muse.xiangta.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.muse.xiangta.R;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.fragment.DynamicMyFragment;

import butterknife.OnClick;

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

    @OnClick(R.id.tv_fabu)
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_fabu:
                Intent intent = new Intent(DynamicActivity.this, PushDynamicActivity.class);
                intent.putExtra("type", 2);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void initData() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_layout, new DynamicMyFragment()).commit();
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
