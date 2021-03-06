package com.muse.xiangta.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
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
import com.muse.chat.ui.ChatActivity;
import com.muse.xiangta.ApiConstantDefine;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseFragment;
import com.muse.xiangta.cloudface.FaceVerifyDemoActivity;
import com.muse.xiangta.helper.SelectResHelper;
import com.muse.xiangta.json.JsonGetIsAuth;
import com.muse.xiangta.json.JsonRequestBase;
import com.muse.xiangta.json.JsonRequestDoPrivateChat;
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
import com.tencent.imsdk.TIMConversationType;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * ??????
 */
public class UserPageFragment extends BaseFragment {
    private RelativeLayout user_page_my_user_page;
    private BGLevelTextView tv_level;
    private ImageView iv_huizhang;
    private Dialog radioDialog;//????????????dialog

    //????????????
    private CircleImageView userImg;//????????????
    private TextView userName;//?????????
    private ImageView userIsVerify;//????????????????????????
    private ImageView iv_sex;
    private TextView aboutNumber;//????????????
    private TextView fansNumber;//?????????
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

    private UserCenterData userCenterData;//??????????????????????????????

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
        iv_sex = view.findViewById(R.id.iv_sex);

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

        //????????????
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
            R.id.ll_car, R.id.ll_haoyou, R.id.ll_miyou, R.id.tv_chongzhi, R.id.ll_dynamic, R.id.iv_yaoqing,
            R.id.iv_kefu})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_kefu:
                Api.doPrivateChat(uId, uToken, RequestConfig.getConfigObj().getCustom_service_user().getUser_id(), new StringCallback() {

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("doPrivateChat", s);
//                        tipD.dismiss();
                        JsonRequestDoPrivateChat jsonObj =
                                (JsonRequestDoPrivateChat) JsonRequestBase.getJsonObj(s, JsonRequestDoPrivateChat.class);
                        if (jsonObj.getCode() == 1) {

                            if (jsonObj.getUser_info() == null) {
                                return;
                            }
                            ChatActivity.navToChat(getContext(), jsonObj.getUser_info().getId(), jsonObj.getUser_info().getUser_nickname()
                                    , jsonObj.getUser_info().getAvatar(), jsonObj.getIs_pay(), jsonObj.getPay_coin()
                                    , jsonObj.getSex(), jsonObj.getIs_auth(), jsonObj.getFollow()
                                    , TIMConversationType.C2C);
                        } else {
                            ToastUtils.showLong(jsonObj.getMsg());
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        tipD.dismiss();
                        Log.e("doPrivateChat", e.toString());
                    }
                });
                break;
            case R.id.iv_yaoqing:
//                showToastMsg(getContext(), "?????????????????????");
                InviteActivityNew.start(getContext());
                break;
            case R.id.ll_dynamic://????????????
                startActivity(new Intent(getContext(), DynamicActivity.class));
                break;
            case R.id.tv_chongzhi:
                //??????
                WealthDetailedActivity.start(getContext(), WealthDetailedActivity.TYPE_RECHARGE);
                break;
            case R.id.ll_car://??????
                startActivity(new Intent(getContext(), CarActivity.class));
                break;
            case R.id.ll_noble://??????
                startActivity(new Intent(getContext(), NobleActivity.class));
                break;
            case R.id.tv_profit://??????
                startActivity(new Intent(getContext(), ProfitActivity.class));
                break;
            case R.id.ll_grade:
                startActivity(new Intent(getContext(), MyGradeActivity.class));
                break;
            case R.id.ll_guard://??????
                startActivity(new Intent(getContext(), GuardActivity.class));
                break;
            case R.id.ll_family:
                //??????
                startActivity(new Intent(getContext(), FamilyActivity.class));
                break;
            case R.id.tv_reward:
                //????????????
                startActivity(new Intent(getContext(), RewardActivity.class));
                break;
            //??????
            case R.id.ll_wallet://??????
                goMoneyPage();
                break;
            case R.id.ll_video_auth:
                clickVideoAuth();
                break;
            case R.id.ll_short_video:
                //?????????
                ShortVideoActivity.startShortVideoActivity(getContext());
                break;
            case R.id.ll_private_photo:
                //??????
                PrivatePhotoActivity.startPrivatePhotoActivity(getContext(), uId, "", 0);
                break;
            case R.id.ll_level:
                //????????????
                //WebViewActivity.openH5Activity(getContext(), true, getString(R.string.my_level), RequestConfig.getConfigObj().getMyLevelUrl());
                WealthDetailedActivity.start(getContext(), WealthDetailedActivity.TYPE_MY_LEVEL);
                break;
            case R.id.ll_new_guide:
                //????????????
                WebViewActivity.openH5Activity(getContext(), false, getString(R.string.novice_guide), RequestConfig.getConfigObj().getNewBitGuideUrl());
                break;
            case R.id.ll_setting:
                //??????
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
                //????????????
                InviteActivityNew.start(getContext());
                break;
            case R.id.mine_ed:
                goActivity(getContext(), EditActivity.class);
                break;
            //????????????
            case R.id.userpage_myuserpage:
                goMyUserPage();
                break;
            //??????
            case R.id.ll_miyou:
                goMsgListPage(1);
                break;
            //??????
            case R.id.count_fans_layout:
                goMsgListPage(2);
                break;
            //??????
            case R.id.count_love_layout:
                goMsgListPage(3);
                break;
            //??????
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
            //????????????
            case R.id.ll_beauty_setting:
                UIHelp.showBeautySettingPage(getContext());
                break;
            default:
                break;
        }
    }

    //??????
    private void clickGuild() {
        DialogHelp.getSelectDialog(getContext(), new String[]{"????????????"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    UIHelp.showGuildList(getContext());
                }
                if (i == 2 && userCenterData != null && userCenterData.getData().getIs_president() != 1) {
                    ToastUtils.showLong("?????????????????????");
                }
            }
        }).show();
    }


    //????????????
    private void clickVideoAuth() {
        if (userCenterData == null) {
            return;
        }

        if (ConfigModel.getInitData().getAuth_type() == 1) {
            if (StringUtils.toInt(userCenterData.getData().getUser_auth_status()) == 0) {
                ToastUtils.showLong("?????????????????????");
                return;
            }
            Intent intent = new Intent(getContext(), VideoAuthActivity.class);
            intent.putExtra(CuckooAuthFormActivity.STATUS, StringUtils.toInt(userCenterData.getData().getUser_auth_status()));
            startActivity(intent);
        } else {
//            Intent intent = new Intent(getContext(), CuckooAuthFormActivity.class);
//            intent.putExtra(CuckooAuthFormActivity.STATUS, StringUtils.toInt(userCenterData.getData().getUser_auth_status()));
//            startActivity(intent);
            startActivityForResult(new Intent(getContext(), FaceVerifyDemoActivity.class), 80);
        }
    }

    /**
     * ???????????????????????????
     */
    private void showDialogRatio() {
        radioDialog = showViewDialog(getContext(), R.layout.dialog_ratio_view, new int[]{R.id.dialog_close, R.id.dialog_left_btn, R.id.dialog_right_btn});
        TextView text = radioDialog.findViewById(R.id.radio_radio_text);
        text.setText(userCenterData.getData().getSplit());
    }


    //?????????????????????
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
     * ??????????????????????????????
     */
    private void refreshUserData() {

        Utils.loadHeadHttpImg(getContext(), Utils.getCompleteImgUrl(userCenterData.getData().getAvatar()), userImg);

        userName.setText(userCenterData.getData().getUser_nickname());
//        tv_level.setLevelInfo(userCenterData.getSex(), userCenterData.getLevel());
        //??????????????????
        userIsVerify.setImageResource(SelectResHelper.getAttestationRes(StringUtils.toInt(userCenterData.getData().getUser_auth_status())));
        aboutNumber.setText(userCenterData.getData().getAttention_all() + "");
        fansNumber.setText(userCenterData.getData().getAttention_fans() + "");

        if (!StringUtils.isEmpty(userCenterData.getData().getNoble())) {
            iv_huizhang.setVisibility(View.VISIBLE);
            GlideImgManager.loadImage(getContext(), userCenterData.getData().getNoble(), iv_huizhang);
        } else {
            iv_huizhang.setVisibility(View.GONE);
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

        if (userCenterData.getData().getSex() == 1) {
            iv_sex.setImageResource(R.mipmap.img_xingbienan2);
        } else {
            iv_sex.setImageResource(R.mipmap.img_xingbie1);
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
     * ???????????????????????????
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
                                //?????????????????????????????? ????????????  ?????? ????????????
                                if (userCenterData.getData().getSex() == 2 && StringUtils.toInt(userCenterData.getData().getUser_auth_status()) == 1) {
                                    SharedPreferencesUtils.setParam(getContext(), "AccountNature", "anchor");
                                } else {
                                    SharedPreferencesUtils.setParam(getContext(), "AccountNature", "boss");
                                }
                                refreshUserData();
                            } else if (userCenterData.getCode() == ApiConstantDefine.ApiCode.LOGIN_INFO_ERROR) {
                                new MaterialDialog.Builder(getContext())
                                        .content("???????????????????????????????????????????????????????????????")
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
                        ToastUtils.showLong("????????????????????????!");
                    }
                }
        );
    }


    /**
     * ????????????????????????##0?????????--1?????????
     */
    private void goMsgListPage(int type) {
        startActivity(new Intent(getContext(), MyMessageActivity.class)
                .putExtra("type", type));
    }

    /**
     * ????????????????????????
     *
     * @param money ???????????????(??????/???[?????????])
     */
    private void goBuyMoney(int money) {
        showToastMsg(getContext(), getString(R.string.buy) + money * 100 + currency);
    }

    /**
     * ?????????????????????
     */
    private void goMyUserPage() {
        Common.jumpUserPage(getContext(), uId);
    }

    /**
     * ???????????????????????????
     */
    private void goMoneyPage() {
        Api.doRequestGetIsAuth(uId, uToken, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                JsonGetIsAuth data = (JsonGetIsAuth) JsonRequestBase.getJsonObj(s, JsonGetIsAuth.class);
                if (data.getIs_auth() == 1) {
                    //????????????
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
     * ??????/????????????
     */
    private void doLogout() {
        LoginUtils.doLoginOut(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        requestUserData();//?????????????????????????????????????????????
    }
}
