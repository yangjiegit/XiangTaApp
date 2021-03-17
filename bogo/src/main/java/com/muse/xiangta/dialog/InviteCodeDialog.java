package com.muse.xiangta.dialog;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.drawable.BGDrawable;
import com.muse.xiangta.json.JsonRequestBase;
import com.muse.xiangta.json.JsonRequestIsFullInviteCode;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.modle.UserModel;
import com.muse.xiangta.utils.BGViewUtil;
import com.muse.xiangta.utils.StringUtils;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 魏鹏 on 2018/3/13.
 * email:1403102936@qq.com
 * 山东布谷鸟网络科技有限公司著
 */

public class InviteCodeDialog extends BGDialogBase implements View.OnClickListener {

    private EditText mEtCode;
    private TextView mTvLeftBtn;
    private TextView mTvRightBtn;


    public InviteCodeDialog(@NonNull Context context) {
        super(context, R.style.dialogBlackBg);

        init();
    }

    private void init() {
        setContentView(R.layout.dialog_invite_code);
        BGViewUtil.setBackgroundDrawable(getContentView(), new BGDrawable().color(Color.WHITE).cornerAll(30));
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        padding(50, 0, 50, 0);

        initView();
        initData();
    }

    private void initData() {
    }

    private void initView() {

        mEtCode = findViewById(R.id.et_code);
        mTvLeftBtn = findViewById(R.id.tv_left);
        mTvRightBtn = findViewById(R.id.tv_right);
        mTvRightBtn.setOnClickListener(this);
        mTvLeftBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.tv_left:

                requestSubmitInviteCode(0);
                break;
            case R.id.tv_right:

                requestSubmitInviteCode(1);
                break;
            default:
                break;
        }
    }

    //提交信息
    private void requestSubmitInviteCode(int type) {

        String inviteCode = mEtCode.getText().toString();
        if (type == 1) {
            if (TextUtils.isEmpty(inviteCode)) {

                ToastUtils.showLong("邀请码不能为空");
                return;
            }
        }
        showLoadingDialog("正在提交验证码");

        UserModel userModel = SaveData.getInstance().getUserInfo();
        Api.doRequestSubmitInviteCode(userModel.getId(), userModel.getToken(), inviteCode, type, new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {
                hideLoadingDialog();
                JsonRequestBase jsonObj = JsonRequestBase.getJsonObj(s, JsonRequestBase.class);
                if (jsonObj.getCode() == 1) {
                    ToastUtils.showLong("提交成功");
                    dismiss();
                } else {
                    ToastUtils.showLong(jsonObj.getMsg());
                }
            }
        });
    }

    //检查邀请码填写状态
    public static void checkInviteCode(final Context context) {

        UserModel userModel = SaveData.getInstance().getUserInfo();
        Api.doCheckIsFullInviteCode(userModel.getId(), userModel.getToken(), new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {

                JsonRequestIsFullInviteCode jsonObj
                        = (JsonRequestIsFullInviteCode) JsonRequestBase.getJsonObj(s, JsonRequestIsFullInviteCode.class);
                if (jsonObj.getCode() == 1 && StringUtils.toInt(jsonObj.getIs_full()) == 0) {

                    InviteCodeDialog dialog = new InviteCodeDialog(context);
                    dialog.show();
                }
            }
        });
    }


}
