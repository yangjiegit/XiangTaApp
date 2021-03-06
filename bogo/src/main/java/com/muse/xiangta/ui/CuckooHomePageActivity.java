package com.muse.xiangta.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.FragAdapter;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.cloudface.FaceVerifyDemoActivity;
import com.muse.xiangta.fragment.CuckooHomePageUserInfoFragment2;
import com.muse.xiangta.fragment.DynamicMyFragment2;
import com.muse.xiangta.helper.SelectResHelper;
import com.muse.xiangta.inter.JsonCallback;
import com.muse.xiangta.json.JsonRequestBase;
import com.muse.xiangta.json.jsonmodle.TargetUserData2;
import com.muse.xiangta.manage.RequestConfig;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.modle.CanShowContactBean;
import com.muse.xiangta.modle.ConfigModel;
import com.muse.xiangta.modle.HomePageImgBean;
import com.muse.xiangta.ui.common.Common;
import com.muse.xiangta.ui.dialog.QuickDialog;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;
import com.muse.xiangta.utils.Utils;
import com.muse.xiangta.utils.im.IMHelp;
import com.muse.xiangta.widget.NoScrollViewPager;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.stx.xhb.xbanner.XBanner;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.sns.TIMFriendResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;

/***
 * ??????????????????
 */
public class CuckooHomePageActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.view_pager)
    NoScrollViewPager viewPager;

    @BindView(R.id.tv_user_id)
    TextView tv_user_id;

    @BindView(R.id.ll_renzheng)
    LinearLayout ll_renzheng;

    @BindView(R.id.iv_renzheng)
    ImageView iv_renzheng;

    @BindView(R.id.tv_renzheng)
    TextView tv_renzheng;

    @BindView(R.id.rl_guardian)
    RelativeLayout rl_guardian;

    @BindView(R.id.tv_text_money_minute)
    TextView textChatMoney;

    @BindView(R.id.tv_voice_money_minute)
    TextView voiceChatMoney;

    @BindView(R.id.iv_vip)
    ImageView iv_vip;

    @BindView(R.id.iv_head)
    ImageView iv_head;

    @BindView(R.id.tv_video_money_minute)
    TextView videoChatMoney;

    @BindView(R.id.iv_v)
    ImageView iv_v;

    @BindView(R.id.iv_auth_status_tv)
    TextView iv_auth_statusTv;

    @BindView(R.id.iv_level_sex)
    ImageView iv_level_sex;

    @BindView(R.id.topbar)
    View top;

    @BindView(R.id.home_page_auth_ll)
    LinearLayout home_page_auth_ll;

    @BindView(R.id.home_page_level_ll)
    LinearLayout home_page_level_ll;

    @BindView(R.id.home_page_local_ll)
    LinearLayout home_page_local_ll;

    @BindView(R.id.hoem_page_rank_iv)
    ImageView rankIv;

    @BindView(R.id.chat_attribute_card_view)
    LinearLayout chatCardView;

    @BindView(R.id.userpage_img)
    CircleImageView cardCircleView;

    @BindView(R.id.top_title_tv)
    TextView cardNameTv;

    @BindView(R.id.tv_btn_info_view)
    View infoView;

    @BindView(R.id.tv_btn_video_view)
    View videoView;

    @BindView(R.id.tv_btn_img_view)
    View imgView;

    @BindView(R.id.contact_rl)
    LinearLayout ll_contact_rl;

    @BindView(R.id.tv_guanzhu)
    TextView tv_guanzhu;

    private FragAdapter mInfoTabFragAdapter;
    private String targetUserId;//????????????id
    private TargetUserData2 targetUserData;//????????????????????????

    //?????????

    private ArrayList<HomePageImgBean> rollPath = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList();

    private QuickDialog rankDialog;

    private ImageView iv_sex;
    private TextView userNickname; //??????player??????
    private TextView userTimeText; //??????x??????
    private TextView userGoodText;//??????
    private TextView userLocationText;//??????player??????
    private TextView fansNumber;//????????????
    private TextView listBarGiftText;//????????????
    //    private ImageView userIsonLine;//??????????????????
    private ImageView iv_auth_status;//????????????
    private XBanner homePageWallpaper;//????????????
    private ImageView userLoveMe;//????????????player

    private Dialog menuDialog;
    private TextView infoTv;
    private TextView videoTv, dynamicTv;
    private LinearLayout chatLl;
    private TextView myPrivateImg;

    private int[] actionMenuIds;


    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_cuckoo_home_page;
    }


    @Override
    protected void initView() {

        QMUIStatusBarHelper.translucent(this); // ??????????????????
        Utils.initTransP(top);
//        userIsonLine = findViewById(R.id.userinfo_bar_isonLine);
        iv_auth_status = findViewById(R.id.iv_auth_status);
        userNickname = findViewById(R.id.userinfo_bar_userid);
        userTimeText = findViewById(R.id.userinfo_bar_time_text);
        userGoodText = findViewById(R.id.userinfo_bar_good_text);
        userLocationText = findViewById(R.id.userinfo_bar_location_text);
        fansNumber = findViewById(R.id.fans_number);
        listBarGiftText = findViewById(R.id.list_bar_gift_text);
        userLoveMe = findViewById(R.id.userinfo_bar_loveme);
        userLoveMe.bringToFront();
        homePageWallpaper = findViewById(R.id.home_page_wallpaper);
        infoTv = findViewById(R.id.tv_btn_info);
        videoTv = findViewById(R.id.tv_btn_video);
        chatLl = findViewById(R.id.chat_ll);
        dynamicTv = findViewById(R.id.tv_btn_dynamic);
        myPrivateImg = findViewById(R.id.tv_btn_img);
        iv_sex = findViewById(R.id.iv_sex);

        initXbanner();

        viewPager.setNoScroll(true);

        selectItem(15, true, R.color.admin_color, 13, false, R.color.black, 13, false, R.color.black, 13, false, R.color.black);
    }


    private void initXbanner() {
        //?????????????????????
        homePageWallpaper.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                ImageView tvContent = (ImageView) view.findViewById(R.id.custom_imageview_layout);
                HomePageImgBean bannerImgBean = (HomePageImgBean) model;
                Utils.loadHttpImg(bannerImgBean.getUrl(), tvContent);

            }
        });

    }


    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        targetUserId = getIntent().getStringExtra("str");
        requestTargetUserData();
    }

    @Override
    protected void initPlayerDisplayData() {

    }

    @OnClick({R.id.hoem_page_rank_iv, R.id.list_bar_gift_text, R.id.rl_guardian, R.id.rl_voice_call, R.id.tv_btn_info, R.id.tv_btn_video, R.id.float_back, R.id.float_meun,
            R.id.ll_chat, R.id.ll_gift, R.id.rl_call, R.id.userinfo_bar_loveme, R.id.tv_btn_img, R.id.tv_btn_dynamic, R.id.home_page_qq_contact_ll, R.id.home_page_wechat_contact_ll
            , R.id.home_page_phone_contact_ll, R.id.ll_tonghua, R.id.ll_sixin, R.id.ll_liwu, R.id.tv_dashan, R.id.tv_guanzhu, R.id.iv_shouhu, R.id.ll_renzheng})
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_renzheng:
                if (uId.equals(targetUserId)) {
//                    Intent intent1 = new Intent(CuckooHomePageActivity.this, CuckooAuthFormActivity.class);
//                    intent1.putExtra(CuckooAuthFormActivity.STATUS, StringUtils.toInt(targetUserData.getData().getIs_auth()));
//                    startActivity(intent1);
                    startActivityForResult(new Intent(CuckooHomePageActivity.this,
                            FaceVerifyDemoActivity.class), 20);
                }
                break;
            case R.id.tv_guanzhu:
                loveThisPlayer();
                break;
            case R.id.ll_tonghua:
                //??????
                dialog();
                break;
            case R.id.userinfo_bar_loveme://??????
                loveThisPlayer();
                break;
            case R.id.tv_btn_info:
                selectItem(15, true, R.color.admin_color, 13, false, R.color.black, 13, false, R.color.black, 13, false, R.color.black);
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv_btn_video:
                selectItem(13, false, R.color.black, 15, true, R.color.admin_color, 13, false, R.color.black, 13, false, R.color.black);
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv_btn_img:
                selectItem(13, false, R.color.black, 13, false, R.color.black, 15, true, R.color.admin_color, 13, false, R.color.black);
                viewPager.setCurrentItem(2);
                break;
            case R.id.tv_btn_dynamic:
                selectItem(13, false, R.color.black, 13, false, R.color.black, 13, false, R.color.black, 15, true, R.color.admin_color);
                viewPager.setCurrentItem(1);
                break;
            //???????????????
            case R.id.hoem_page_rank_iv://??????
            case R.id.iv_shouhu:
//                showContribution();
                startActivity(new Intent(CuckooHomePageActivity.this, UserGuardRankActivity.class)
                        .putExtra("TO_USER_ID", targetUserId));
                break;
            case R.id.float_back:
                finish();
                break;
            //??????
            case R.id.float_meun:
                if (targetUserData == null) {
                    return;
                }
                showFloatMeun();
                break;
            //????????????
            case R.id.edit_mine:
                Intent intent = new Intent(this, EditActivity.class);
                startActivityForResult(intent, 20);
                menuDialog.dismiss();
                break;
            //?????????????????????
            case R.id.join_black_list:
                clickBlack();
                menuDialog.dismiss();
                break;
            //?????????????????????
            case R.id.report_this_user:
                clickReport();
                menuDialog.dismiss();
                break;
            //????????????
            case R.id.dialog_dis:
                menuDialog.dismiss();
                break;
            case R.id.ll_chat://??????
            case R.id.ll_sixin:
            case R.id.ll_liwu:
            case R.id.tv_dashan:
                showChatPage(false);
                break;
            case R.id.ll_gift:
                showChatPage(true);
                break;
            //????????????
            case R.id.rl_call:
                callThisPlayer();
                break;
            case R.id.rl_voice_call:
                callVoice();
                break;
            case R.id.rl_guardian:
                openGuardianPage();
                break;

            case R.id.list_bar_gift_text:
                Intent intentGift = new Intent(this, CuckooGiftCabinetGiftListActivity.class);
                intentGift.putExtra(CuckooGiftCabinetGiftListActivity.TO_USER_ID, targetUserId);
                startActivity(intentGift);
                break;

            //?????? qq WeChat ?????????
            case R.id.home_page_qq_contact_ll:

                isCanShowContact("qq");
                break;
            case R.id.home_page_phone_contact_ll:
                isCanShowContact("phone");
                break;
            case R.id.home_page_wechat_contact_ll:
                isCanShowContact("wx");
                break;

            default:
                break;
        }
    }


    private void dialog2(int type) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.show();  //??????????????????window.setContentView??????show
        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_koufei);
        // ????????? ???????????????????????????????????????????????????
//        window.setGravity(Gravity.BOTTOM);//????????????????????????????????????????????????????????????
        window.setWindowAnimations(R.style.animation_bottom_menu);//???????????????????????????????????????????????????????????????????????????
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);//?????????????????????

        TextView tv_text = dialog.findViewById(R.id.tv_text);

        TextView tv_comm1 = dialog.findViewById(R.id.tv_comm1);
        TextView tv_comm2 = dialog.findViewById(R.id.tv_comm2);

        tv_comm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        tv_comm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type == 1) {
                    callVoice();
                } else {
                    callThisPlayer();
                }
                dialog.dismiss();
            }
        });

        Api.getUserChargeInfo(uId, uToken, targetUserId, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (!StringUtils.isEmpty(s)) {
                    try {
                        int code = new JSONObject(s).getInt("code");
                        if (code == 1) {
                            JSONObject jsonObject = new JSONObject(s).getJSONObject("data");
                            String voice_chat = jsonObject.getString("voice_chat");
                            String video_chat = jsonObject.getString("video_chat");
                            if (type == 1) {
                                //??????
//                                ???????????????????????????%@??????/??????????????????????????????
                                tv_text.setText("???????????????????????????" + voice_chat + "??????/???????????????????????????");
                            } else {
                                tv_text.setText("???????????????????????????" + video_chat + "??????/???????????????????????????");
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    private void dialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.show();  //??????????????????window.setContentView??????show
        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_tonghua);
        // ????????? ???????????????????????????????????????????????????
        window.setGravity(Gravity.BOTTOM);//????????????????????????????????????????????????????????????
        window.setWindowAnimations(R.style.animation_bottom_menu);//???????????????????????????????????????????????????????????????????????????
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);//?????????????????????

        TextView tv_yuyin = dialog.findViewById(R.id.tv_yuyin);
        TextView tv_shipin = dialog.findViewById(R.id.tv_shipin);
        TextView tv_quxiao = dialog.findViewById(R.id.tv_quxiao);

        tv_yuyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                callVoice();
//                dialog.dismiss();
                dialog2(1);
                dialog.dismiss();
            }
        });

        tv_shipin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                callThisPlayer();
//                dialog.dismiss();
                dialog2(2);
                dialog.dismiss();
            }
        });

        tv_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    private void isCanShowContact(final String type) {
        Api.getCanShowContactResult(targetUserId, type, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e("getCanShowContactResult", s);
                CanShowContactBean bean = new Gson().fromJson(s, CanShowContactBean.class);
                //????????????0
                if (bean.getCode() == 1) {
                    String price = bean.getPrice();
                    String number = bean.getNumber();


                    //?????????????????????????????????????????????
                    if (!TextUtils.isEmpty(price)) {
                        new MaterialDialog.Builder(CuckooHomePageActivity.this)
                                .content("??????????????????????????????????")
                                .positiveText(R.string.agree)
                                .negativeText(R.string.disagree)
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        toBuyContact(type);
                                    }
                                })
                                .show();
                    } else {
                        new MaterialDialog.Builder(CuckooHomePageActivity.this)
                                .content(type + ":" + number)
                                .positiveText(R.string.agree)
                                .negativeText(R.string.disagree)
                                .show();
                    }

                } else {
                    ToastUtils.showShort(bean.getMsg());
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                Log.e("getCanShowContactResult", e.toString());
            }
        });
    }

    /**
     * ??????????????????
     *
     * @param type
     */
    private void toBuyContact(final String type) {
        Api.toBuyContact(targetUserId, type, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e("toBuyContact", s);
                CanShowContactBean bean = new Gson().fromJson(s, CanShowContactBean.class);
                //????????????0
                if (bean.getCode() == 1) {
                    String number = bean.getNumber();
                    new MaterialDialog.Builder(CuckooHomePageActivity.this)
                            .content(type + ":" + number)
                            .positiveText(R.string.agree)
                            .negativeText(R.string.disagree)
                            .show();
                } else {
                    ToastUtils.showShort(bean.getMsg());
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                Log.e("toBuyContact", e.toString());
            }
        });
    }

    //????????????
    private void openGuardianPage() {
        WebViewActivity.openH5Activity(CuckooHomePageActivity.this, true, "??????", ConfigModel.getInitData().getApp_h5().getGuardian_list() + "?hostid=" + targetUserId);
    }

    private void selectItem(int infoTvSize, boolean infoTvStyle, int infoColor, int videoTvSize, boolean videoTvStyle, int videoColor, int myPrivateImgSize, boolean myPrivateImgStyle, int myPrivateImgColor, int myDynamicImgSize, boolean myDynamicImgStyle, int myDynamicImgColor) {
        infoTv.setTextSize(infoTvSize);
        TextPaint tp1 = infoTv.getPaint();
        tp1.setFakeBoldText(infoTvStyle);
        infoTv.setTextColor(getResources().getColor(infoColor));

        videoTv.setTextSize(videoTvSize);
        TextPaint tpv1 = videoTv.getPaint();
        tpv1.setFakeBoldText(videoTvStyle);
        videoTv.setTextColor(getResources().getColor(videoColor));

        myPrivateImg.setTextSize(myPrivateImgSize);
        TextPaint tpvImg = myPrivateImg.getPaint();
        tpvImg.setFakeBoldText(myPrivateImgStyle);
        myPrivateImg.setTextColor(getResources().getColor(myPrivateImgColor));


        dynamicTv.setTextSize(myDynamicImgSize);
        TextPaint tpvDy = dynamicTv.getPaint();
        tpvDy.setFakeBoldText(myDynamicImgStyle);
        dynamicTv.setTextColor(getResources().getColor(myDynamicImgColor));
    }

    //?????????player?????????
    private void callThisPlayer() {
        showToastMsg(getString(R.string.now_call));
        Common.callVideo(this, targetUserId, 0);
    }

    //??????????????????
    private void callVoice() {
        showToastMsg(getString(R.string.now_call));
        Common.callVideo(this, targetUserId, 1);
    }

    //??????????????????
    private void showChatPage(boolean isShowGift) {
        Common.startPrivatePage(this, targetUserId);
    }

    //????????????
    private void showFloatMeun() {

        //????????????????????????
        if (SaveData.getInstance().getId().equals(targetUserId)) {
            actionMenuIds = new int[]{R.id.edit_mine, R.id.join_black_list, R.id.report_this_user, R.id.dialog_dis};
            menuDialog = showButtomDialog(R.layout.dialog_float_meun, actionMenuIds, 20);
            menuDialog.findViewById(R.id.join_black_list).setVisibility(View.GONE);
            menuDialog.findViewById(R.id.report_this_user).setVisibility(View.GONE);
        } else {
            actionMenuIds = new int[]{R.id.join_black_list, R.id.report_this_user, R.id.dialog_dis};
            menuDialog = showButtomDialog(R.layout.dialog_float_meun_no_edit, actionMenuIds, 20);
        }


        TextView tv = menuDialog.findViewById(R.id.join_black_list);

        if (targetUserData.getData().getIs_black().equals("1")) {
            tv.setText(R.string.relieve_black);
        }
        menuDialog.show();

    }

    //???????????????
    private void showContribution() {
        //  Intent intent = new Intent(this, UserContribuionRankActivity.class);
        if (rankDialog == null) rankDialog = new QuickDialog(this).add("?????????").add("?????????")
                .addCallBack(new QuickDialog.CallBack() {
                    @Override
                    public void onClick(String item, int pos) {
                        Intent intent = null;
                        switch (pos) {
                            case 0:
                                intent = new Intent(CuckooHomePageActivity.this, UserContribuionRankActivity.class);
                                break;
                            case 1:
                                intent = new Intent(CuckooHomePageActivity.this, UserGuardRankActivity.class);
                                break;
                        }

                        intent.putExtra(UserContribuionRankActivity.TO_USER_ID, targetUserId);
                        startActivity(intent);
                    }
                });

        rankDialog.show();
    }

    //??????
    private void clickReport() {
        Intent intent = new Intent(this, ReportActivity.class);
        intent.putExtra(ReportActivity.REPORT_USER_ID, targetUserId);
        startActivity(intent);
    }


    /**
     * ???????????????????????????
     */
    private void initDisplayData() {
        initViewPagerData();
        rollPath.clear();

        if (Integer.valueOf(uId).equals(targetUserData.getData().getId())) {
            tv_guanzhu.setVisibility(View.GONE);
        } else {
            tv_guanzhu.setVisibility(View.VISIBLE);
        }

        if (targetUserData.getData().getIs_auth().equals("1")) {
            iv_v.setVisibility(View.VISIBLE);
        } else {
            iv_v.setVisibility(View.GONE);
        }

        tv_user_id.setText("ID:" + targetUserData.getData().getId() + "   |   ??????:"
                + targetUserData.getData().getAttention_fans() + "   |   " +
                targetUserData.getData().getAddress());
//        getIs_auth
        if (targetUserData.getData().getIs_auth().equals("0")) {
//            ll_renzheng.setVisibility(View.VISIBLE);
            if (uId.equals(targetUserId)) {
                ll_renzheng.setEnabled(true);
                tv_renzheng.setText("?????????");
                iv_renzheng.setVisibility(View.VISIBLE);
            } else {
                ll_renzheng.setEnabled(false);
                tv_renzheng.setText("?????????");
                iv_renzheng.setVisibility(View.GONE);
            }
        } else {
//            ll_renzheng.setVisibility(View.GONE);
            iv_renzheng.setVisibility(View.GONE);
            ll_renzheng.setEnabled(false);
            tv_renzheng.setText("?????????");
        }

        GlideImgManager.glideLoader(this, targetUserData.getData().getAvatar()
                , iv_head, 0);

        if (StringUtils.isEmpty(targetUserData.getData().getNoble())) {
            iv_vip.setVisibility(View.GONE);
        } else {
            iv_vip.setVisibility(View.VISIBLE);
            GlideImgManager.loadImage(this, targetUserData.getData().getNoble(), iv_vip);
        }

        if (StringUtils.toInt(targetUserData.getData().getIs_visible_bottom_btn()) == 0) {
            chatLl.setVisibility(View.GONE);
        } else {
            chatLl.setVisibility(View.VISIBLE);

            if (targetUserData.getData().getSex().equals("2")) {
                //?????????????????????
                TranslateAnimation showAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f);
                showAnim.setDuration(500);
                chatCardView.startAnimation(showAnim);
                chatCardView.setVisibility(View.VISIBLE);
            }

        }

        //?????????
        if (targetUserData.getData().getSex().equals("2")) {
            ll_contact_rl.setVisibility(View.GONE);

            rankIv.setVisibility(View.GONE);
            voiceChatMoney.setVisibility(View.VISIBLE);
            videoChatMoney.setVisibility(View.VISIBLE);

            //????????????????????????
            hideBtn(View.VISIBLE);

        } else {
            rankIv.setVisibility(View.GONE);
            voiceChatMoney.setVisibility(View.GONE);
            videoChatMoney.setVisibility(View.GONE);

            //????????????????????????
            hideBtn(View.GONE);
        }


        //????????????
        cardNameTv.setText(targetUserData.getData().getUser_nickname() + "");
        Utils.loadHttpImg(this, targetUserData.getData().getAvatar(), cardCircleView);


        iv_level_sex.setBackgroundResource(targetUserData.getData().getSex().equals("2") ? R.mipmap.woman_vip_bac : R.mipmap.man_vip_bac);
        //????????????????????????
        userLoveMe.setBackgroundResource(StringUtils.toInt(targetUserData.getData().getAttention()) == 1
                ? R.mipmap.followed_bat : R.mipmap.follow_bat);
        if (StringUtils.toInt(targetUserData.getData().getAttention()) == 1) {
            tv_guanzhu.setText("?????????");
        } else {
            tv_guanzhu.setText("+??????");
        }

        userNickname.setText(targetUserData.getData().getUser_nickname());
//        userTimeText.setText(getString(R.string.call) + targetUserData.getCall()); TODO ??????
        userTimeText.setText("LV." + targetUserData.getData().getWealth_level() + "");
//        userGoodText.setVisibility(View.GONE); TODO ??????
        userGoodText.setText("LV." + targetUserData.getData().getGlamour_level() + "");
//        userGoodText.setText(getString(R.string.praise) + targetUserData.getEvaluation());

        if (!TextUtils.isEmpty(targetUserData.getData().getAddress())) {
            home_page_local_ll.setVisibility(View.VISIBLE);
            userLocationText.setText(targetUserData.getData().getAddress());
        } else {
            home_page_local_ll.setVisibility(View.GONE);
        }


        if (StringUtils.toInt(targetUserData.getData().getUser_status()) == 1) {
            videoChatMoney.setText(targetUserData.getData().getVideo_deduction() + RequestConfig.getConfigObj().getCurrency() + getString(R.string.minute));
            voiceChatMoney.setText(targetUserData.getData().getVoice_deduction() + RequestConfig.getConfigObj().getCurrency() + getString(R.string.minute));

        }

        int attestationResForSex = SelectResHelper.getAttestationResForSex(Integer.valueOf(targetUserData.getData().getSex()), StringUtils.toInt(targetUserData.getData().getUser_status()));
        if (attestationResForSex == 0) {
            home_page_auth_ll.setVisibility(View.GONE);
        } else {
            home_page_auth_ll.setVisibility(View.VISIBLE);
            iv_auth_status.setImageResource(attestationResForSex);
        }

        if (targetUserData.getData().getSex().equals("2")) {
            iv_auth_statusTv.setText(StringUtils.toInt(targetUserData.getData().getUser_status()) == 1 ? "?????????" : "?????????");
        } else {
            iv_auth_statusTv.setText(StringUtils.toInt(targetUserData.getData().getUser_status()) == 1 ? "?????????" : "");

        }

//        fansNumber.setText(getString(R.string.fans) + ":" + targetUserData.getAttention_fans()); TODO ??????
        fansNumber.setText(targetUserData.getData().getAttention_fans() + "");

        if (targetUserData.getData().getSex().equals("1")) {
            iv_sex.setImageResource(R.mipmap.img_xingbienan2);
        } else {
            iv_sex.setImageResource(R.mipmap.img_xingbie1);
        }

        //????????????
//        userIsonLine.setImageResource(StringUtils.toInt(targetUserData.getData().getIs_online()) == 1 ? R.mipmap.on_line : R.mipmap.not_online);

        if (targetUserData.getData().getImg() != null) {
            if (targetUserData.getData().getImg().size() == 0) {
                HomePageImgBean homePageImgBean = new HomePageImgBean();
                homePageImgBean.setUrl(Utils.getCompleteImgUrl(targetUserData.getData().getAvatar()));
                rollPath.add(homePageImgBean);
            }
        }

        if (targetUserData.getData().getSex().equals("2")) {
            if (!StringUtils.isEmpty(targetUserData.getData().getGift_count())) {
                if (!targetUserData.getData().getGift_count().equals("**")) {
                    listBarGiftText.setText(String.format(Locale.CHINA, "???????????????(%d)", Integer.valueOf(targetUserData.getData().getGift_count())));
                } else {
                    listBarGiftText.setText("???????????????(**)");
                }
            } else {
                listBarGiftText.setText("???????????????(0)");
            }
        } else {
            if (!StringUtils.isEmpty(targetUserData.getData().getGift_count())) {
                if (!targetUserData.getData().getGift_count().equals("**")) {
                    listBarGiftText.setText(String.format(Locale.CHINA, "???????????????(%d)", Integer.valueOf(targetUserData.getData().getGift_count())));
                } else {
                    listBarGiftText.setText("???????????????(**)");
                }
            } else {
                listBarGiftText.setText("???????????????(0)");
            }
        }


        homePageWallpaper.setBannerData(R.layout.banner_custom_layout, rollPath);
        homePageWallpaper.startAutoPlay();


    }

    private void initViewPagerData() {
        fragmentList.add(CuckooHomePageUserInfoFragment2.getInstance(targetUserData));
        fragmentList.add(DynamicMyFragment2.getInstance(targetUserId));

        viewPager.setOffscreenPageLimit(1);
        mInfoTabFragAdapter = new FragAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(mInfoTabFragAdapter);
        viewPager.setOnPageChangeListener(this);
    }

    private void hideBtn(int visible) {

    }

    //????????????player
    private void loveThisPlayer() {
        Api.doLoveTheUser(
                targetUserId,
                uId,
                uToken,
                new JsonCallback() {
                    @Override
                    public Context getContextToJson() {
                        return getNowContext();
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        super.onSuccess(s, call, response);
                        if (!StringUtils.isEmpty(s)) {
                            try {
                                int code = new JSONObject(s).getInt("code");
                                int follow = new JSONObject(s).getInt("follow");
                                if (code == 1) {
                                    if (follow == 1) {
                                        targetUserData.getData().setAttention("1");
                                    } else {
                                        targetUserData.getData().setAttention("0");
                                    }
                                    userLoveMe.setBackgroundResource(StringUtils.toInt(targetUserData.getData().getAttention()) == 1 ? R.mipmap.followed_bat : R.mipmap.follow_bat);
                                    if (StringUtils.toInt(targetUserData.getData().getAttention()) == 1) {
                                        tv_guanzhu.setText("?????????");
                                    } else {
                                        tv_guanzhu.setText("+??????");
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
    }


    /**
     * ??????????????????????????????
     */
    private void requestTargetUserData() {
        Api.getUserHomePageInfo(
                targetUserId,
                uId,
                uToken,
                new JsonCallback() {
                    @Override
                    public Context getContextToJson() {
                        return getNowContext();
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if (!StringUtils.isEmpty(s)) {
                            targetUserData = new Gson().fromJson(s, TargetUserData2.class);
                            if (targetUserData.getCode() == 1) {
                                initDisplayData();
                            }
                        }
                    }
                }
        );
    }


    //??????
    private void clickBlack() {

        showLoadingDialog(getString(R.string.loading_action));
        Api.doRequestBlackUser(uId, uToken, targetUserId, new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {

                hideLoadingDialog();
                JsonRequestBase jsonObj = JsonRequestBase.getJsonObj(s, JsonRequestBase.class);
                if (jsonObj.getCode() == 1) {
                    showToastMsg(getResources().getString(R.string.action_success));
                    if (targetUserData.getData().getIs_black().equals("1")) {
                        targetUserData.getData().setIs_black("0");
                        IMHelp.deleteBlackUser(targetUserId, new TIMValueCallBack<List<TIMFriendResult>>() {
                            @Override
                            public void onError(int i, String s) {
                                LogUtils.i("????????????????????????:" + s);
                            }

                            @Override
                            public void onSuccess(List<TIMFriendResult> timFriendResults) {
                                LogUtils.i("????????????????????????");
                            }
                        });
                    } else {
                        targetUserData.getData().setIs_black("1");
                        IMHelp.addBlackUser(targetUserId, new TIMValueCallBack<List<TIMFriendResult>>() {
                            @Override
                            public void onError(int i, String s) {
                                LogUtils.i("??????????????????:" + s);
                            }

                            @Override
                            public void onSuccess(List<TIMFriendResult> timFriendResults) {
                                LogUtils.i("??????????????????");
                            }
                        });
                    }
                } else {
                    showToastMsg(jsonObj.getMsg());
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                hideLoadingDialog();
            }
        });
    }

    /**
     * viewpager????????????
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                selectItem(15, true, R.color.admin_color, 13, false, R.color.black, 13, false, R.color.black, 13, false, R.color.black);
                viewPager.setCurrentItem(0);
                break;
//            case 1:
//                selectItem(13, false, R.color.black, 15, true, R.color.admin_color, 13, false, R.color.black, 13, false, R.color.black);
//                viewPager.setCurrentItem(1);
//                break;
//            case 2:
//                selectItem(13, false, R.color.black, 13, false, R.color.black, 15, true, R.color.admin_color, 13, false, R.color.black);
//                viewPager.setCurrentItem(2);
//                break;
            case 1:
                selectItem(13, false, R.color.black, 13, false, R.color.black, 13, false, R.color.black, 15, true, R.color.admin_color);
                viewPager.setCurrentItem(3);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onStop() {
        super.onStop();
        homePageWallpaper.stopAutoPlay();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 20:
                requestTargetUserData();
                break;
        }
    }
}
