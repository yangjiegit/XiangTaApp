package com.muse.xiangta.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.muse.xiangta.R;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.ui.view.LastInputEditText;
import com.muse.xiangta.utils.SPHelper;
import com.muse.xiangta.utils.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class SetTeenagersPassActivity extends BaseActivity {


    @BindView(R.id.et_pass1)
    LastInputEditText et_pass1;
    @BindView(R.id.et_pass2)
    LastInputEditText et_pass2;


    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_set_teenagerspass;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }


    @OnClick({R.id.iv_back, R.id.tv_comm})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_comm:
                if (StringUtils.isEmpty(et_pass1.getText().toString().trim())) {
                    showToastMsg("密码不可为空");
                    return;
                } else if (StringUtils.isEmpty(et_pass2.getText().toString().trim())) {
                    showToastMsg("确认密码不可为空");
                    return;
                } else if (!et_pass1.getText().toString().trim().equals(et_pass2.getText().toString().trim())) {
                    showToastMsg("两次密码不一致");
                    return;
                } else {
                    SPHelper.setString(this, "pass", et_pass1.getText().toString().trim());
                    setResult(40);
                    startActivity(new Intent(this, TeenagersActivity.class));
                }
                break;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
