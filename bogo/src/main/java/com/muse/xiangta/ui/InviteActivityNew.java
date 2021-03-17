package com.muse.xiangta.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.dialog.CuckooShareDialog;
import com.muse.xiangta.manage.AppConfig;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.modle.ConfigModel;
import com.muse.xiangta.modle.DrawalBean;
import com.muse.xiangta.ui.dialog.InviteShareDialog;
import com.muse.xiangta.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;


public class InviteActivityNew extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener {

    private TextView mTvInviteHelp;

    public static void start(Context c) {
        Intent intent = new Intent(c, InviteActivityNew.class);
        c.startActivity(intent);
    }


    @Override
    protected Context getNowContext() {
        return null;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_invite_n;
    }

    @BindView(R.id.title_all)
    View title;

    @Override
    protected void initView() {
        Utils.initTransTitleBar(title, "分成计划", this);
        mTvInviteHelp = findViewById(R.id.tv_invite_help);
        mTvInviteHelp.setText(ConfigModel.getInitData().getInvitation_withdrawal_rules());
    }


    CuckooShareDialog shareDialog;

    public void toShared() {
        if (shareDialog == null) {
            shareDialog = new CuckooShareDialog(this);
            shareDialog.setImg(SaveData.getInstance().getUserInfo().getAvatar());
            shareDialog.setShareUrl(AppConfig.MAIN_URL + "/mapi/public/index.php/api/download_api/index?invite_code=" + SaveData.getInstance().getId());
        }
        shareDialog.show();
    }

    @OnClick({R.id.tgmx, R.id.tx})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.tgmx:
                InviteDetailedActivity.start(this);
                break;
            case R.id.tx:
                WealthDetailedActivity.start(this, WealthDetailedActivity.TINVITE_YPE_RECHARGE);
                break;
        }
    }

    @Override
    protected void initSet() {

    }


    @BindView(R.id.ye)
    TextView ye;

    @Override
    protected void initData() {
        Api.getWithdrawal(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                DrawalBean bean = new Gson().fromJson(s, DrawalBean.class);
                ye.setText(bean.getData().getInvitation_coin());
            }
        });

    }

    @Override
    protected void initPlayerDisplayData() {

    }

    @OnClick({R.id.to_shared})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.to_shared:
                clickShare();
//                Intent intent1 = new Intent(this, PosterActivity.class);
//                startActivity(intent1);
                break;
        }
    }

    private void clickShare() {
//        CuckooShareDialog shareDialog = new CuckooShareDialog(this);
//        shareDialog.setImg(SaveData.getInstance().getUserInfo().getAvatar());
//        shareDialog.setShareUrl(Utils.getShareExtensionUrl());
//        shareDialog.show();
        new InviteShareDialog(this).show();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
