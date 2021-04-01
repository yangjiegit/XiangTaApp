package com.muse.xiangta.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.GlobContents;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.modle.ConfigModel;
import com.muse.xiangta.modle.SignBean;
import com.muse.xiangta.modle.UserChatData;
import com.muse.xiangta.ui.CuckooChangeUserRatioActivity;
import com.muse.xiangta.ui.CuckooCustomMsgActivity;
import com.muse.xiangta.ui.CuckooGuildApplyListActivity;
import com.muse.xiangta.ui.CuckooGuildCreateActivity;
import com.muse.xiangta.ui.CuckooGuildListActivity;
import com.muse.xiangta.ui.CuckooGuildManageActivity;
import com.muse.xiangta.ui.CuckooGuildUserManageActivity;
import com.muse.xiangta.ui.CuckooSelectIncomeLogActivity;
import com.muse.xiangta.ui.CuckooSettingBeautyActivity;
import com.muse.xiangta.ui.CuckooVoiceCallActivity;
import com.muse.xiangta.ui.DialogH5Activity;
import com.muse.xiangta.ui.InviteActivityNew;
import com.muse.xiangta.ui.VideoLineActivity;
import com.muse.xiangta.ui.WebViewActivity;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

public class UIHelp {

    /**
     * @param callType       视频或音频
     * @param flag 是否是需要扣费
     * @param resolvingPower 视频的分辨率
     * @param userChatData   用户信息
     * @param videoDeduction 价格
     * @param freeTime       免费时长（s）
     * @dw 跳转视频通话或者音频通话页面
     */
    public static void startVideoLineActivity(Context context, int callType, String resolvingPower, boolean flag, String videoDeduction, int freeTime, UserChatData userChatData) {

        Intent intent;

        if (callType == 0) {
            intent = new Intent(context, VideoLineActivity.class);
        } else {
            intent = new Intent(context, CuckooVoiceCallActivity.class);
        }
        intent.putExtra(VideoLineActivity.CALL_USER_DATA, userChatData);
        intent.putExtra(VideoLineActivity.VIDEO_PX, resolvingPower);
        intent.putExtra(VideoLineActivity.IS_NEED_CHARGE, flag);
        intent.putExtra(VideoLineActivity.IS_BE_CALL, true);
        intent.putExtra(VideoLineActivity.VIDEO_DEDUCTION, videoDeduction);
        intent.putExtra(VideoLineActivity.CALL_TYPE, callType);
        intent.putExtra(VideoLineActivity.FREE_TIME, freeTime);
        context.startActivity(intent);
    }

    public static void showGuildCreateActivity(Context context) {
        Intent intent = new Intent(context, CuckooGuildCreateActivity.class);
        context.startActivity(intent);
    }

    public static void showGuildList(Context context) {
        Intent intent = new Intent(context, CuckooGuildListActivity.class);
        context.startActivity(intent);
    }

    public static void showGuildManageActivity(Context context) {
        Intent intent = new Intent(context, CuckooGuildManageActivity.class);
        context.startActivity(intent);
    }

    public static void showGuildUserManage(Context context, String guildId) {
        Intent intent = new Intent(context, CuckooGuildUserManageActivity.class);
        intent.putExtra(CuckooGuildUserManageActivity.GUILD_ID, guildId);
        context.startActivity(intent);
    }

    public static void showGuildApplyUserManage(Context context, String guildId) {
        Intent intent = new Intent(context, CuckooGuildApplyListActivity.class);
        intent.putExtra(CuckooGuildUserManageActivity.GUILD_ID, guildId);
        context.startActivity(intent);
    }

    public static void showChangeUserRatioPage(Context context, int id) {
        Intent intent = new Intent(context, CuckooChangeUserRatioActivity.class);
        intent.putExtra(CuckooChangeUserRatioActivity.USER_ID, id);
        context.startActivity(intent);
    }

    public static void showSelectIncomeLog(Context context) {
        Intent intent = new Intent(context, CuckooSelectIncomeLogActivity.class);
        context.startActivity(intent);
    }

    public static void showIncomePage(Context context) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("title", "收益");
        intent.putExtra("url", ConfigModel.getInitData().getApp_h5().getUser_withdrawal());
        intent.putExtra("is_token", true);
        intent.putExtra(WebViewActivity.IS_INCOME, true);
        context.startActivity(intent);
    }

    public static void showInviteActivity(Context context) {
        Intent intent = new Intent(context, InviteActivityNew.class);
        context.startActivity(intent);
    }

    public static void showCustomMsg(Context context) {
        Intent intent = new Intent(context, CuckooCustomMsgActivity.class);
        context.startActivity(intent);
    }

    public static void showBeautySettingPage(Context context) {
        Intent intent = new Intent(context, CuckooSettingBeautyActivity.class);
        context.startActivity(intent);
    }

    public static void checkOpenSignDialog(final Context context) {
        int is_open_sign_in = ConfigModel.getInitData().getIs_open_sign_in();
        //0 关闭 1开启
        if (is_open_sign_in == 1) {
            Api.getSignInfo(SaveData.getInstance().getId(), SaveData.getInstance().getToken(), new StringCallback() {
                @Override
                public void onSuccess(String s, Call call, Response response) {
                    Log.e("getSignInfo", s);
                    SignBean bean = new Gson().fromJson(s, SignBean.class);
                    if (bean.getCode() == 1) {
                        //"0未签到 1已签到 2签到奖励已关闭"
                        int is_sign_in = bean.getIs_sign_in();
                        if (is_sign_in == 0) {
                            Intent intent = new Intent(context, DialogH5Activity.class);
                            intent.putExtra("uri", ConfigModel.getInitData().getApp_h5().getSign_in());
                            context.startActivity(intent);
                        }

                        GlobContents.getInstance().isShowDialog = false;
                    } else {
                        ToastUtils.showShort(bean.getMsg());
                    }
                }

                @Override
                public void onError(Call call, Response response, Exception e) {
                    super.onError(call, response, e);
                    Log.e("getSignInfo", e.toString());
                }
            });

        }
    }
}
