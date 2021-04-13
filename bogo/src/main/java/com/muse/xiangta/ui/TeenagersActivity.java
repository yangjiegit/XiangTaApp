package com.muse.xiangta.ui;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;

import com.muse.xiangta.R;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.ui.view.LastInputEditText;
import com.muse.xiangta.utils.SPHelper;
import com.muse.xiangta.utils.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class TeenagersActivity extends BaseActivity {

    @BindView(R.id.et_pass)
    LastInputEditText et_pass;


    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_teenagers;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @OnClick(R.id.tv_comm)
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_comm:
                if (StringUtils.isEmpty(et_pass.getText().toString().trim())) {
                    showToastMsg("密码不能为空");
                    return;
                } else {
                    if (et_pass.getText().toString().trim().equals(
                            SPHelper.getString(TeenagersActivity.this, "pass")
                    )) {
                        //解除青少年模式
                        finish();
                    }
                }
                break;
        }
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 按下键盘上返回按钮
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private boolean isCosumenBackKey() {
        // 这儿做返回键的控制，如果自己处理返回键逻辑就返回true，如果返回false,代表继续向下传递back事件，由系统去控制
        return true;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
