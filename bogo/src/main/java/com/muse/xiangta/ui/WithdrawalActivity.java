package com.muse.xiangta.ui;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;

import com.muse.xiangta.R;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.ui.view.LastInputEditText;
import com.muse.xiangta.utils.StringUtils;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.wechat.utils.WXMediaMessage;
import cn.sharesdk.wechat.utils.WXTextObject;

public class WithdrawalActivity extends BaseActivity {

    private int is_check = -1;

    @BindView(R.id.iv_zhifubao)
    ImageView iv_zhifubao;
    @BindView(R.id.iv_weixin)
    ImageView iv_weixin;
    @BindView(R.id.et_text)
    LastInputEditText et_text;

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
        et_text.addTextChangedListener(new TextWatcher() {
            int l = 0;////////记录字符串被删除字符之前，字符串的长度
            int location = 0;//记录光标的位置

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
                l = s.length();
                location = et_text.getSelectionStart();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                if (s.toString().startsWith("0")) {
                    et_text.setText("1");
                    et_text.setSelection(1);
                }
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.ll_zhifubao, R.id.ll_weixin, R.id.tv_comm})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_zhifubao:
                is_check = 1;
                iv_zhifubao.setImageResource(R.mipmap.img_check_1);
                iv_weixin.setImageResource(R.mipmap.img_check_2);
                break;
            case R.id.ll_weixin:
                is_check = 2;
                iv_zhifubao.setImageResource(R.mipmap.img_check_2);
                iv_weixin.setImageResource(R.mipmap.img_check_1);
                break;
            case R.id.tv_comm:
                //提交
                if (StringUtils.isEmpty(et_text.getText().toString().trim())) {
                    showToastMsg("提现金额不能为空");
                    return;
                } else if (is_check == -1) {
                    showToastMsg("请选择提现方式");
                    return;
                } else {
                    if (is_check == 2) {

                    }
                }
                break;
        }
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
