package com.muse.xiangta.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.ApiConstantDefine;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseFragment;
import com.muse.xiangta.helper.SelectResHelper;
import com.muse.xiangta.json.JsonGetIsAuth;
import com.muse.xiangta.json.JsonRequestBase;
import com.muse.xiangta.json.jsonmodle.UserCenterData;
import com.muse.xiangta.manage.RequestConfig;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.modle.ConfigModel;
import com.muse.xiangta.modle.UserModel;
import com.muse.xiangta.ui.CarActivity;
import com.muse.xiangta.ui.CuckooAuthFormActivity;
import com.muse.xiangta.ui.DynamicActivity;
import com.muse.xiangta.ui.EditActivity;
import com.muse.xiangta.ui.FamilyActivity;
import com.muse.xiangta.ui.GuardActivity;
import com.muse.xiangta.ui.InviteActivityNew;
import com.muse.xiangta.ui.MyGradeActivity;
import com.muse.xiangta.ui.MyMessageActivity;
import com.muse.xiangta.ui.NobleActivity;
import com.muse.xiangta.ui.PrivatePhotoActivity;
import com.muse.xiangta.ui.ProfitActivity;
import com.muse.xiangta.ui.RechargeVipActivity;
import com.muse.xiangta.ui.RewardActivity;
import com.muse.xiangta.ui.SettingActivity;
import com.muse.xiangta.ui.ShortVideoActivity;
import com.muse.xiangta.ui.ToJoinActivity;
import com.muse.xiangta.ui.VideoAuthActivity;
import com.muse.xiangta.ui.WealthActivity;
import com.muse.xiangta.ui.WealthDetailedActivity;
import com.muse.xiangta.ui.WebViewActivity;
import com.muse.xiangta.ui.common.Common;
import com.muse.xiangta.ui.common.LoginUtils;
import com.muse.xiangta.utils.DialogHelp;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.SharedPreferencesUtils;
import com.muse.xiangta.utils.StringUtils;
import com.muse.xiangta.utils.UIHelp;
import com.muse.xiangta.utils.Utils;
import com.muse.xiangta.widget.BGLevelTextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 我的
 */
public class UserPageFragment extends BaseFragment {
    private RelativeLayout user_page_my_user_page;
    private BGLevelTextView tv_level;
    private ImageView iv_huizhang;
    private Dialog radioDialog;//分成比例dialog

    //显示数据
    private CircleImageView userImg;//用户头像
    private TextView userName;//用户名
    private ImageView userIsVerify;//用户是否验证图标

    private TextView aboutNumber;//关注人数
    private TextView fansNumber;//粉丝数
    private TextView tv_reward, tv_profit;

    private RelativeLayout ll_guard, ll_grade, ll_noble, ll_car;
    private RelativeLayout ll_family;
    private RelativeLayout ll_video_auth;
    private RelativeLayout ll_short_video;
    private RelativeLayout ll_private_photo;
    private RelativeLayout ll_invite;
    private RelativeLayout ll_new_guide;
    private RelativeLayout ll_cooperation;
    private RelativeLayout ll_setting;
    private RelativeLayout ll_level;
    private RelativeLayout ll_buyVip;
    private RelativeLayout ll_switch_disturb;

    @BindView(R.id.tv_user_page_id)
    TextView tv_user_page_id;

    @BindView(R.id.iv_switch_disturb)
    ImageView iv_switch_disturb;

    @BindView(R.id.ll_emcee_menu)
    LinearLayout ll_emcee_menu;

    @BindView(R.id.ll_buy_vip)
    RelativeLayout ll_buy_vip;

    @BindView(R.id.ll_guild)
    RelativeLayout llGuide;

    @BindView(R.id.ll_beauty_setting)
    RelativeLayout ll_beauty_setting;

    private UserCenterData userCenterData;//个人中心接口返回信息

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_userpage, container, false);
    }

    @Override
    protected void initView(View view) {
        userImg = view.findViewById(R.id.userpage_img);
        userName = view.findViewById(R.id.userpage_nickname);
        userIsVerify = view.findViewById(R.id.userpage_is_auth);
        aboutNumber = view.findViewById(R.id.love_number);
        fansNumber = view.findViewById(R.id.fans_number);
        tv_reward = view.findViewById(R.id.tv_reward);
        tv_profit = view.findViewById(R.id.tv_profit);
        iv_huizhang = view.findViewById(R.id.iv_huizhang);

        ll_car = view.findViewById(R.id.ll_car);
        ll_noble = view.findViewById(R.id.ll_noble);
        ll_grade = view.findViewById(R.id.ll_grade);
        ll_guard = view.findViewById(R.id.ll_guard);
        ll_family = view.findViewById(R.id.ll_family);
        ll_video_auth = view.findViewById(R.id.ll_video_auth);
        ll_short_video = view.findViewById(R.id.ll_short_video);
        ll_private_photo = view.findViewById(R.id.ll_private_photo);
        ll_invite = view.findViewById(R.id.ll_invite);
        ll_new_guide = view.findViewById(R.id.ll_new_guide);
        ll_cooperation = view.findViewById(R.id.ll_cooperation);
        ll_setting = view.findViewById(R.id.ll_setting);
        ll_level = view.findViewById(R.id.ll_level);
        ll_switch_disturb = view.findViewById(R.id.ll_switch_disturb);
        ll_buyVip = view.findViewById(R.id.ll_buy_vip);
        ll_buyVip.setOnClickListener(this);
        ll_family.setOnClickListener(this);
        tv_reward.setOnClickListener(this);
        ll_guard.setOnClickListener(this);
        ll_grade.setOnClickListener(this);
        tv_profit.setOnClickListener(this);
        ll_noble.setOnClickListener(this);
        ll_car.setOnClickListener(this);

        user_page_my_user_page = view.findViewById(R.id.userpage_myuserpage);
        tv_level = view.findViewById(R.id.tv_level);

    }

    @Override
    protected void initDate(View view) {

    }

    @Override
    protected void initSet(View view) {

        tv_user_page_id.setText(String.format(Locale.CHINA, "ID: %s", SaveData.getInstance().getId()));

        setOnclickListener(view, R.id.count_love_layout, R.id.count_fans_layout, R.id.count_divide_layout,
                R.id.ll_video_auth, R.id.ll_short_video, R.id.ll_private_photo, R.id.ll_invite, R.id.ll_new_guide,
                R.id.ll_cooperation, R.id.ll_setting, R.id.ll_level, R.id.ll_guild);

        user_page_my_user_page.setOnClickListener(this);

        if (StringUtils.toInt(ConfigModel.getInitData().getOpen_invite()) == 1) {
            ll_invite.setVisibility(View.VISIBLE);
        }

//        ll_switch_disturb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                clickChangeDoNotDisturbStatus(!userCenterData.isOpenDoNotDisturb());
//            }
//        });

        //显示美颜
        if (!TextUtils.isEmpty(ConfigModel.getInitData().getBogokj_beauty_sdk_key())
                && SaveData.getInstance().getUserInfo().getSex() == 2) {
            ll_beauty_setting.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initDisplayData(View view) {
    }


    @OnClick({R.id.ll_wallet, R.id.iv_user_center_sign, R.id.ll_beauty_setting, R.id.ll_family
            , R.id.tv_reward, R.id.ll_guard, R.id.ll_grade, R.id.tv_profit, R.id.ll_noble,
            R.id.ll_car, R.id.ll_haoyou, R.id.ll_miyou, R.id.tv_chongzhi, R.id.ll_dynamic})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_dynamic://我的动态
                startActivity(new Intent(getContext(), DynamicActivity.class));
                break;
            case R.id.tv_chongzhi:
                //充值
                WealthDetailedActivity.start(getContext(), WealthDetailedActivity.TYPE_RECHARGE);
                break;
            case R.id.ll_car://座驾
                startActivity(new Intent(getContext(), CarActivity.class));
                break;
            case R.id.ll_noble://贵族
                startActivity(new Intent(getContext(), NobleActivity.class));
                break;
            case R.id.tv_profit://收益
                startActivity(new Intent(getContext(), ProfitActivity.class));
                break;
            case R.id.ll_grade:
                startActivity(new Intent(getContext(), MyGradeActivity.class));
                break;
            case R.id.ll_guard://守护
                startActivity(new Intent(getContext(), GuardActivity.class));
                break;
            case R.id.ll_family:
                //家族
                startActivity(new Intent(getContext(), FamilyActivity.class));
                break;
            case R.id.tv_reward:
                //任务奖励
                startActivity(new Intent(getContext(), RewardActivity.class));
                break;
            //财富
            case R.id.ll_wallet://钱包
                goMoneyPage();
                break;
            case R.id.ll_video_auth:
                clickVideoAuth();
                break;
            case R.id.ll_short_video:
                //小视频
                ShortVideoActivity.startShortVideoActivity(getContext());
                break;
            case R.id.ll_private_photo:
                //私照
                PrivatePhotoActivity.startPrivatePhotoActivity(getContext(), uId, "", 0);
                break;
            case R.id.ll_level:
                //我的等级
                //WebViewActivity.openH5Activity(getContext(), true, getString(R.string.my_level), RequestConfig.getConfigObj().getMyLevelUrl());
                WealthDetailedActivity.start(getContext(), WealthDetailedActivity.TYPE_MY_LEVEL);
                break;
            case R.id.ll_new_guide:
                //新手引导
                WebViewActivity.openH5Activity(getContext(), false, getString(R.string.novice_guide), RequestConfig.getConfigObj().getNewBitGuideUrl());
                break;
            case R.id.ll_setting:
                //设置
                Intent intent = new Intent(getContext(), SettingActivity.class);
                if (userCenterData != null) {
                    intent.putExtra("state", userCenterData.getData().getUser_auth_status());
                    intent.putExtra("sex", userCenterData.getData().getSex());
                }
                getContext().startActivity(intent);
                break;
            case R.id.ll_cooperation:
                startActivity(new Intent(getContext(), ToJoinActivity.class));
                break;
            case R.id.ll_invite:
                //邀请好友
                InviteActivityNew.start(getContext());
                break;
            case R.id.mine_ed:
                goActivity(getContext(), EditActivity.class);
                break;
            //个人主页
            case R.id.userpage_myuserpage:
                goMyUserPage();
                break;
            //密友
            case R.id.ll_miyou:
                goMsgListPage(1);
                break;
            //粉丝
            case R.id.count_fans_layout:
                goMsgListPage(2);
                break;
            //关注
            case R.id.count_love_layout:
                goMsgListPage(3);
                break;
            //好友
            case R.id.ll_haoyou:
                goMsgListPage(4);
                break;
            case R.id.count_divide_layout:
                //showDialogRatio();
                break;
            case R.id.dialog_close:
                radioDialog.dismiss();
                break;
            case R.id.ll_buy_vip:
                startActivity(new Intent(getContext(), RechargeVipActivity.class));
                //WebViewActivity.openH5Activity(getContext(), true, getString(R.string.vip), ConfigModel.getInitData().getApp_h5().getVip_url());
                break;
            case R.id.ll_guild:
                clickGuild();
                break;
            case R.id.iv_user_center_sign:
                WebViewActivity.openH5Activity(getContext(), true, getString(R.string.sign), ConfigModel.getInitData().getApp_h5().getSign_in());
                //intent = new Intent(getContext(), FriendsActivity.class);
                //startActivity(intent);
                break;
            //美颜设置
            case R.id.ll_beauty_setting:
                UIHelp.showBeautySettingPage(getContext());
                break;
            default:
                break;
        }
    }

    //工会
    private void clickGuild() {
        DialogHelp.getSelectDialog(getContext(), new String[]{"申请公会"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    UIHelp.showGuildList(getContext());
                }
                if (i == 2 && userCenterData != null && userCenterData.getData().getIs_president() != 1) {
                    ToastUtils.showLong("没有创建公会！");
                }
            }
        }).show();
    }


    //视频认证
    private void clickVideoAuth() {
        if (userCenterData == null) {
            return;
        }

        if (ConfigModel.getInitData().getAuth_type() == 1) {
            if (StringUtils.toInt(userCenterData.getData().getUser_auth_status()) == 0) {
                ToastUtils.showLong("正在等待审核！");
                return;
            }
            Intent intent = new Intent(getContext(), VideoAuthActivity.class);
            intent.putExtra(CuckooAuthFormActivity.STATUS, StringUtils.toInt(userCenterData.getData().getUser_auth_status()));
            startActivity(intent);
        } else {
            Intent intent = new Intent(getContext(), CuckooAuthFormActivity.class);
            intent.putExtra(CuckooAuthFormActivity.STATUS, StringUtils.toInt(userCenterData.getData().getUser_auth_status()));
            startActivity(intent);
        }
    }

    /**
     * 显示分成比例对话框
     */
    private void showDialogRatio() {
        radioDialog = showViewDialog(getContext(), R.layout.dialog_ratio_view, new int[]{R.id.dialog_close, R.id.dialog_left_btn, R.id.dialog_right_btn});
        TextView text = radioDialog.findViewById(R.id.radio_radio_text);
        text.setText(userCenterData.getData().getSplit());
    }


    //修改免打扰状态
    private void clickChangeDoNotDisturbStatus(final boolean b) {
        int type = b ? 1 : 2;
        Api.doRequestSetDoNotDisturb(type, SaveData.getInstance().getId(), SaveData.getInstance().getToken(), new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {

                JsonRequestBase jsonObj = JsonRequestBase.getJsonObj(s, JsonRequestBase.class);
                if (jsonObj.getCode() == 1) {

                    UserModel userModel = SaveData.getInstance().getUserInfo();
//                    userModel.setIs_open_do_not_disturb(b ? "1" : "0");
                    SaveData.getInstance().saveData(userModel);

//                    userCenterData.setIs_open_do_not_disturb(userModel.getIs_open_do_not_disturb());
//                    if (StringUtils.toInt(userModel.getIs_open_do_not_disturb()) == 1) {
//                        iv_switch_disturb.setImageResource(R.mipmap.me_icon_disturb_off);
//                    } else {
//                        iv_switch_disturb.setImageResource(R.mipmap.me_icon_disturb_on);
//                    }

                } else {
                    showToastMsg(getContext(), jsonObj.getMsg());
                }
            }
        });
    }

    /**
     * 刷新用户资料页面显示
     */
    private void refreshUserData() {

        Utils.loadHeadHttpImg(getContext(), Utils.getCompleteImgUrl(userCenterData.getData().getAvatar()), userImg);

        userName.setText(userCenterData.getData().getUser_nickname());
//        tv_level.setLevelInfo(userCenterData.getSex(), userCenterData.getLevel());
        //是否认证标识
        userIsVerify.setImageResource(SelectResHelper.getAttestationRes(StringUtils.toInt(userCenterData.getData().getUser_auth_status())));
        aboutNumber.setText(userCenterData.getData().getAttention_all() + "");
        fansNumber.setText(userCenterData.getData().getAttention_fans() + "");

        if (!StringUtils.isEmpty(userCenterData.getData().getNoble())) {
            GlideImgManager.loadImage(getContext(), userCenterData.getData().getNoble(), iv_huizhang);
        }

        if (StringUtils.toInt(userCenterData.getData().getIs_open_do_not_disturb()) == 1) {
            iv_switch_disturb.setImageResource(R.mipmap.me_icon_disturb_off);
        } else {
            iv_switch_disturb.setImageResource(R.mipmap.me_icon_disturb_on);
        }

        if (userCenterData.getData().getSex() == 2) {
            ll_emcee_menu.setVisibility(View.GONE);
            llGuide.setVisibility(View.GONE);
        }

        UserModel userModel = SaveData.getInstance().getUserInfo();
        userModel.setSex(userCenterData.getData().getSex());
//        userModel.setLevel(userCenterData.getLevel());
        userModel.setUser_nickname(userCenterData.getData().getUser_nickname());
        SaveData.getInstance().saveData(userModel);

//        if (!userCenterData.isMan()) {
//            userIsVerify.setVisibility(View.VISIBLE);
//            ll_video_auth.setVisibility(View.GONE);
//            ll_buy_vip.setVisibility(View.GONE);
//        } else {
//            userIsVerify.setVisibility(View.GONE);
//            ll_video_auth.setVisibility(View.GONE);
//            ll_buy_vip.setVisibility(View.GONE);
//        }
    }

    /**
     * 服务端请求用户数据
     */
    private void requestUserData() {
        Api.getUserDataByMe(
                uId,
                uToken,
                new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if (!StringUtils.isEmpty(s)) {
                            userCenterData = new Gson().fromJson(s, UserCenterData.class);
                            if (userCenterData.getCode() == 1) {
                                UserModel userModel = SaveData.getInstance().getUserInfo();
                                userModel.setAvatar(userCenterData.getData().getAvatar());
                                userModel.setUser_nickname(userCenterData.getData().getUser_nickname());
                                SaveData.getInstance().saveData(userModel);
                                //这里只有实名了的女性 预约我的  其他 我的预约
                                if (userCenterData.getData().getSex() == 2 && StringUtils.toInt(userCenterData.getData().getUser_auth_status()) == 1) {
                                    SharedPreferencesUtils.setParam(getContext(), "AccountNature", "anchor");
                                } else {
                                    SharedPreferencesUtils.setParam(getContext(), "AccountNature", "boss");
                                }
                                refreshUserData();
                            } else if (userCenterData.getCode() == ApiConstantDefine.ApiCode.LOGIN_INFO_ERROR) {
                                new MaterialDialog.Builder(getContext())
                                        .content("您的账号已经在其他手机登陆，即将退出此账号")
                                        .cancelable(false)
                                        .positiveText(R.string.agree_ok)
                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                                            @Override
                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                doLogout();

                                            }
                                        })
                                        .show();


                            } else {
                                showToastMsg(getContext(), userCenterData.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        ToastUtils.showLong("刷新用户数据失败!");
                    }
                }
        );
    }


    /**
     * 前往消息列表页面##0关注页--1粉丝页
     */
    private void goMsgListPage(int type) {
        startActivity(new Intent(getContext(), MyMessageActivity.class)
                .putExtra("type", type));
    }

    /**
     * 执行购买聊币操作
     *
     * @param money 购买的数量(单位/元[人民币])
     */
    private void goBuyMoney(int money) {
        showToastMsg(getContext(), getString(R.string.buy) + money * 100 + currency);
    }

    /**
     * 跳转到个人主页
     */
    private void goMyUserPage() {
        Common.jumpUserPage(getContext(), uId);
    }

    /**
     * 跳转到个人财富主页
     */
    private void goMoneyPage() {
        Api.doRequestGetIsAuth(uId, uToken, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                JsonGetIsAuth data = (JsonGetIsAuth) JsonRequestBase.getJsonObj(s, JsonGetIsAuth.class);
                if (data.getIs_auth() == 1) {
                    //我的钱包
                    //UIHelp.showIncomePage(getContext());
                    WealthActivity.startWealthActivity(getContext());
                } else {
                    WealthActivity.startWealthActivity(getContext());
                }
            }
        });
        // UIHelp.showIncomePage(getContext());
    }

    /**
     * 退出/注销方法
     */
    private void doLogout() {
        LoginUtils.doLoginOut(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        requestUserData();//服务端请求用户数据并设置到页面
    }
}
