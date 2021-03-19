package com.muse.xiangta.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.Constants;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.recycler.RecyclerRecommendAdapter;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseListFragment;
import com.muse.xiangta.inter.JsonCallback;
import com.muse.xiangta.json.JsonRequestOneKeyCall;
import com.muse.xiangta.json.JsonRequestUserCenterInfo;
import com.muse.xiangta.json.JsonRequestsImage;
import com.muse.xiangta.json.JsonRequestsTarget;
import com.muse.xiangta.json.jsonmodle.TargetUserData;
import com.muse.xiangta.json.jsonmodle.UserCenterData;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.modle.BannerImgBean;
import com.muse.xiangta.modle.ConfigModel;
import com.muse.xiangta.modle.OnKeyCallBean;
import com.muse.xiangta.modle.UserModel;
import com.muse.xiangta.ui.CuckooAuthFormActivity;
import com.muse.xiangta.ui.DeclarationActivity;
import com.muse.xiangta.ui.FUChatActivity;
import com.muse.xiangta.ui.GroupChatActivity;
import com.muse.xiangta.ui.MatchingActivity;
import com.muse.xiangta.ui.VideoAuthActivity;
import com.muse.xiangta.ui.VideoDatingActivity;
import com.muse.xiangta.ui.common.Common;
import com.muse.xiangta.utils.StringUtils;
import com.muse.xiangta.utils.Utils;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;


/**
 * 推荐
 */

public class RecommendFragment extends BaseListFragment<TargetUserData> {

    //轮播图
    //private XBanner recommendRoll;
    //头部布局
    private View rollView;

    private FrameLayout fl_supei, fl_yuyin, fl_yuehui, fl_qunliao;
    private ImageView call_icon1;
    private ImageView call_icon2;
    private TextView call_num;

    private ArrayList<BannerImgBean> rollImg = new ArrayList<>();
    private RelativeLayout emptyLayout;
    //    private RecyclerView mBannerRv;


    private UserCenterData userCenterData;//个人中心接口返回信息

    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        return new RecyclerRecommendAdapter(getContext(), dataList);
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManage() {
        return new LinearLayoutManager(getContext());
    }

    @Override
    protected void initDate(View view) {
        requestUserData();//服务端请求用户数据并设置到页面
    }

    private void requestUserData() {
        Api.getUserDataByMe(
                uId,
                uToken,
                new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        JsonRequestUserCenterInfo jsonRequestUserCenterInfo = JsonRequestUserCenterInfo.getJsonObj(s);
                        if (jsonRequestUserCenterInfo.getCode() == 1) {
                            userCenterData = jsonRequestUserCenterInfo.getData();
                            UserModel userModel = SaveData.getInstance().getUserInfo();
//                            userModel.setIs_open_do_not_disturb(userCenterData.getIs_open_do_not_disturb());
                            userModel.setAvatar(userCenterData.getAvatar());
                            userModel.setUser_nickname(userCenterData.getUser_nickname());
                            SaveData.getInstance().saveData(userModel);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        ToastUtils.showLong("刷新用户数据失败!");
                    }
                }
        );
    }

    @Override
    public boolean canHasEmptyLayout() {
        return false;
    }

    @Override
    protected void initSet(View view) {

        //引入头部信息
        rollView = LayoutInflater.from(getContext()).inflate(R.layout.view_roll_page, null);
        call_icon1 = rollView.findViewById(R.id.icon_l);
        call_icon2 = rollView.findViewById(R.id.icon_r);
        call_num = rollView.findViewById(R.id.call_num);
        fl_supei = rollView.findViewById(R.id.fl_supei);
        fl_yuyin = rollView.findViewById(R.id.fl_yuyin);
        fl_yuehui = rollView.findViewById(R.id.fl_yuehui);
        fl_qunliao = rollView.findViewById(R.id.fl_qunliao);

        fl_qunliao.setOnClickListener(this);
        fl_yuehui.setOnClickListener(this);
        fl_supei.setOnClickListener(this);
        fl_yuyin.setOnClickListener(this);

        emptyLayout = rollView.findViewById(R.id.recommend_empty_layout);

        if (SaveData.getInstance().getUserInfo().getSex() == 1) {
            //男号
            rollView.findViewById(R.id.boy_header).setVisibility(View.GONE);
            rollView.findViewById(R.id.call_one).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickOneKeyCall();
                }
            });
            Log.i("性别", "initSet: 男");

        } else {
            rollView.findViewById(R.id.boy_header).setVisibility(View.GONE);
            Log.i("性别", "initSet: 女");

        }

        //添加头部
        baseQuickAdapter.addHeaderView(rollView);
        baseQuickAdapter.notifyDataSetChanged();


    }


    @Override
    protected void initDisplayData(View view) {

    }


    @Override
    public void fetchData() {
        //加载数据源
        requestGetData(false);

        //加载推荐页轮播图
        requestRecommendRoll();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fl_qunliao:
                startActivity(new Intent(getContext(), GroupChatActivity.class));
//                Intent intent1 = new Intent(getContext(), FUChatActivity.class);
//                intent1.putExtra(Constants.ACTION_KEY_ROOM_NAME, "1234");
//                startActivity(intent1);
                break;
            case R.id.fl_yuehui:
                startActivity(new Intent(getContext(), VideoDatingActivity.class));
                break;
            case R.id.fl_supei:
                clickVideoAuth();
                break;
            case R.id.fl_yuyin:
                if (null == userCenterData) {
                    return;
                }
                if (userCenterData.getIs_auth() == 0) {
                    Intent intent = new Intent(getContext(), VideoAuthActivity.class);
                    intent.putExtra(CuckooAuthFormActivity.STATUS, StringUtils.toInt(userCenterData.getUser_auth_status()));
                    startActivity(intent);
                } else {
                    if (userCenterData.getHas_declaration() == 0) {
                        //提示录制宣言
                        dialog();
                    } else {
                        startActivity(new Intent(getContext(), MatchingActivity.class)
                                .putExtra("type", "2"));
                    }
                }
                break;
            default:
                break;
        }
    }

    private void dialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
        dialog.show();  //注意：必须在window.setContentView之前show
        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_yuyin);
        //点击确定按钮让对话框消失
        TextView tv_luzhi = dialog.findViewById(R.id.tv_luzhi);
        tv_luzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                startActivity(new Intent(getContext(), DeclarationActivity.class));
            }
        });

    }

    //认证
    private void clickVideoAuth() {
        if (userCenterData == null) {
            return;
        }
        if (userCenterData.getIs_auth() == 0) {
            Intent intent = new Intent(getContext(), VideoAuthActivity.class);
            intent.putExtra(CuckooAuthFormActivity.STATUS, StringUtils.toInt(userCenterData.getUser_auth_status()));
            startActivity(intent);
        } else {//认证成功
            startActivity(new Intent(getContext(), MatchingActivity.class)
                    .putExtra("type", "1"));
        }
    }

    //一键约爱
    private void clickOneKeyCall() {

        showLoadingDialog("正在接通...");
        Api.doRequestOneKeyCall(
                SaveData.getInstance().getId(),
                SaveData.getInstance().getToken(),
                new StringCallback() {

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        hideLoadingDialog();

                        JsonRequestOneKeyCall requestObj = (JsonRequestOneKeyCall) JsonRequestOneKeyCall.getJsonObj(s, JsonRequestOneKeyCall.class);
                        if (requestObj.getCode() == 1) {
                            Common.callVideo(getContext(), requestObj.getEmcee_id(), 0);
                        } else {
                            LogUtils.i("拨打电话error:" + requestObj.getMsg());
                            ToastUtils.showLong(requestObj.getMsg());
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        hideLoadingDialog();
                    }
                }

        );
    }


    /**
     * 加载轮播图
     */
    private void requestRecommendRoll() {
        Api.getRollImage(
                uId,
                uToken,
                "1",
                new JsonCallback() {
                    @Override
                    public Context getContextToJson() {
                        return getContext();
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        JsonRequestsImage requestObj = JsonRequestsImage.getJsonObj(s);
                        if (requestObj.getCode() == 1) {
                            rollImg.clear();
                            rollImg.addAll(requestObj.getData());
//                            recommendRoll.setBannerData(R.layout.banner_custom_layout, rollImg);
//                            recommendRoll.startAutoPlay();
                        }
                    }
                }
        );

        Api.getOneKeyCallInfo(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.i("一键约爱", "onSuccess: " + s);
                OnKeyCallBean bean = new Gson().fromJson(s, OnKeyCallBean.class);
                if (bean == null || bean.getData() == null || bean.getData().size() == 0) return;
                Utils.loadUserIcon(bean.getData().get(0).getAvatar(), call_icon1);
                if (bean.getData().size() > 1)
                    Utils.loadUserIcon(bean.getData().get(1).getAvatar(), call_icon2);
                call_num.setText("10");
            }
        });
    }


    @Override
    protected void requestGetData(boolean isCache) {
        final int userHeadWidth = ConvertUtils.dp2px(30);
        Api.getRecommendUserList(
                uId,
                uToken,
                page,
                new StringCallback() {

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("getRecommendUserList", s);
                        JsonRequestsTarget requestObj = JsonRequestsTarget.getJsonObj(s);
                        if (requestObj.getCode() == 1) {
                            call_num.setText(requestObj.getOnline_emcee_count());

                            if (page == 1 && requestObj.getData() != null && requestObj.getData().size() == 0) {
                                emptyLayout.setVisibility(View.VISIBLE);
                            } else {
                                emptyLayout.setVisibility(View.GONE);
                            }
                            onLoadDataResult(requestObj.getData());
                        } else {
                            onLoadDataError();
                            showToastMsg(getContext(), requestObj.getMsg());
                        }
                    }
                }
        );
    }


    @Override
    public void onResume() {
        super.onResume();
        requestRecommendRoll();

    }


    @Override
    public void onStop() {
        super.onStop();
//        recommendRoll.stopAutoPlay();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        Common.jumpUserPage(getContext(), dataList.get(position).getId());
    }
}