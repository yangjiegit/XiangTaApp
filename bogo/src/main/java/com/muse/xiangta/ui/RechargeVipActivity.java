package com.muse.xiangta.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.ToastUtils;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.PayWayListAdapter;
import com.muse.xiangta.adapter.RechargeVipListAdapter;
import com.muse.xiangta.adapter.VipDetailsAdapter;
import com.muse.xiangta.alipay.AlipayService;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.json.JsonRequestBase;
import com.muse.xiangta.json.JsonRequestRecharge;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.modle.Pay4Bean;
import com.muse.xiangta.modle.RechargeVipBean;
import com.muse.xiangta.modle.VipDetailsModel;
import com.muse.xiangta.paypal.PayPalHandle;
import com.muse.xiangta.ui.dialog.PayWayDialog;
import com.muse.xiangta.utils.StringUtils;
import com.muse.xiangta.utils.Utils;
import com.muse.xiangta.wxpay.WChatPayService;
import com.lzy.okgo.callback.StringCallback;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;

public class RechargeVipActivity extends BaseActivity implements View.OnClickListener, RechargeVipListAdapter.ItemClickListener, PayWayListAdapter.ItemClickListener {

    @BindView(R.id.iv_avatar)
    CircleImageView ivAvatar;

    @BindView(R.id.rechange_vip_name)
    TextView rechangeVipName;

    @BindView(R.id.user_info_rl)
    RelativeLayout userInfoRl;

    @BindView(R.id.top_tab_ll)
    LinearLayout topTabLl;

    @BindView(R.id.rechange_Vip_rv)
    RecyclerView vipListRv;

    @BindView(R.id.vip_state)
    TextView vipState;


    private RecyclerView payWayRv;

    private RecyclerView rvContentListVipDetailsView;

    private int PayId;
    private static final String TAG = "RechargeVipActivity";

    private List<RechargeVipBean.PayListBean> pay_list = new ArrayList<>();
    private List<RechargeVipBean.VipRuleBean> vip_rule = new ArrayList<>();
    private String vip_time;
    private PayWayListAdapter payWayListAdaper;
    private RechargeVipListAdapter rechargeVipListAdapter;

    private List<VipDetailsModel> detailsModelList = new ArrayList<>();
    private VipDetailsAdapter vipDetailsAdapter;

    @BindView(R.id.title_all)
    View title;

    private PayWayDialog pay_dialog;

    @Override
    protected void initPlayerDisplayData() {

    }

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.recharge_vip_activity;
    }

    @Override
    protected void initSet() {
        getTopBar().setTitle("????????????");
    }

    @Override
    protected void initData() {

        requestGetData();
    }

    @Override
    protected void initView() {

        Utils.initTransTitleBar(title,"????????????",this);
       // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        pay_dialog = new PayWayDialog(this);
        payWayRv = pay_dialog.getList();

        LinearLayoutManager payWayManger = new LinearLayoutManager(this);
        payWayManger.setOrientation(LinearLayoutManager.HORIZONTAL);
        payWayRv.setLayoutManager(new GridLayoutManager(this,3));

        vipListRv.setLayoutManager(new GridLayoutManager(this,3));

        rechargeVipListAdapter = new RechargeVipListAdapter(vip_rule);
        vipListRv.setAdapter(rechargeVipListAdapter);

        rechargeVipListAdapter.setItemClickListener(RechargeVipActivity.this);

        rvContentListVipDetailsView = findViewById(R.id.vip_details_recy);
        rvContentListVipDetailsView.setLayoutManager(new GridLayoutManager(getNowContext(),4));

        vipDetailsAdapter = new VipDetailsAdapter(detailsModelList);
        rvContentListVipDetailsView.setAdapter(vipDetailsAdapter);

        //?????? ??????
        Utils.loadUserIcon(RechargeVipActivity.this, Utils.getCompleteImgUrl(SaveData.getInstance().getUserInfo().getAvatar()), ivAvatar);
        rechangeVipName.setText(SaveData.getInstance().getUserInfo().getUser_nickname());

    }


    @Override
    public void onClick(View view) {
    }


    private void requestGetData() {
        Api.getVipData(uId, uToken, new StringCallback() {

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
            }

            @Override
            public void onSuccess(String s, Call call, Response response) {
                RechargeVipBean baseCommonBean = JSON.parseObject(s, RechargeVipBean.class);

                if (baseCommonBean.getCode() == 1) {
                    vip_time = baseCommonBean.getVip_time();
                    pay_list = baseCommonBean.getPay_list();
                    vip_rule.clear();
                    vip_rule.addAll(baseCommonBean.getVip_rule());

                    if (StringUtils.toInt(vip_time) > 0) {
                        vipState.setText("????????????" + vip_time + "???");
                    } else {
                        vipState.setText("???????????????vip");
                    }

                    payWayListAdaper = new PayWayListAdapter(RechargeVipActivity.this, pay_list);
                    payWayRv.setAdapter(payWayListAdaper);

                    //??????????????????????????????id
                    if (pay_list.size() > 0) {
                        PayId = pay_list.get(0).getId();

                    }
                    payWayListAdaper.setPayWayItemClickListener(RechargeVipActivity.this);

                    rechargeVipListAdapter.notifyDataSetChanged();

                    detailsModelList.clear();
                    detailsModelList.addAll(baseCommonBean.getDetail_list());
                    vipDetailsAdapter.notifyDataSetChanged();
                } else {
                    ToastUtils.showShort(baseCommonBean.getMsg());
                }
            }
        });
    }

    /**
     * ????????????
     *
     * @param position
     * @param vipRuleBean
     */
    RechargeVipBean.VipRuleBean vipRuleBean;
    @Override
    public void onItemClickListener(int position, RechargeVipBean.VipRuleBean vipRuleBean) {
        this.vipRuleBean = vipRuleBean;
        pay_dialog.show();
    }


    public void toBuy(){
        showLoadingDialog(getString(R.string.loading_now_submit_order));
        int rid = vipRuleBean.getId();
        Api.selectToPay(SaveData.getInstance().id, SaveData.getInstance().token, rid + "", PayId + "", new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {
                hideLoadingDialog();

                Pay4Bean jsonObj = (Pay4Bean) JsonRequestBase.getJsonObj(s, Pay4Bean.class);
                if (jsonObj.getCode() == 1) {
                    urlPay(jsonObj);
                }

                /*JsonRequestRecharge jsonObj = (JsonRequestRecharge) JsonRequestBase.getJsonObj(s, JsonRequestRecharge.class);
                if (jsonObj.getCode() == 1) {

                    payService(jsonObj);
                } else {
                    ToastUtils.showShort(jsonObj.getMsg());
                }*/
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                hideLoadingDialog();
            }
        });
    }

    private void urlPay(Pay4Bean jsonObj) {
        Utils.openWeb(RechargeVipActivity.this, jsonObj.getPay().getPay_info().getReturn_msg());
    }

//    private void payService(JsonRequestRecharge jsonObj) {
//
//        if (StringUtils.toInt(jsonObj.getPay().getIs_wap()) == 1) {
//            //????????????????????????
//            Utils.openWeb(this, jsonObj.getPay().getPost_url());
//            return;
//        }
//
//        int type = StringUtils.toInt(jsonObj.getPay().getType());
//        if (type == 1) {
//
//            AlipayService alipayService = new AlipayService(this);
//            alipayService.payV2(jsonObj.getPay().getPay_info());
//        } else {
//            WChatPayService alipayService = new WChatPayService(this);
//            alipayService.callWxPay(JSON.parseObject(jsonObj.getPay().getPay_info()));
//        }
//
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm =
                    data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                try {
                    //???????????????PayPal????????????json??????????????????????????????????????????????????????????????????
                    //??????????????? confirm.toJSONObject() ????????????????????????
                    showLoadingDialog("????????????????????????...");
                    //??????????????????????????????????????????????????????????????????????????????????????????
                    PayPalHandle.getInstance().confirmPayResult(RechargeVipActivity.this,
                            requestCode, resultCode, data, PayId + "", new PayPalHandle.DoResult() {

                                @Override
                                public void confirmSuccess() {
                                    hideLoadingDialog();
                                    ToastUtils.showLong("???????????????");
                                }

                                @Override
                                public void confirmNetWorkError() {
                                    hideLoadingDialog();
                                }

                                @Override
                                public void customerCanceled() {
                                    hideLoadingDialog();
                                    ToastUtils.showLong("???????????????");
                                }

                                @Override
                                public void confirmFuturePayment() {

                                    hideLoadingDialog();
                                }

                                @Override
                                public void invalidPaymentConfiguration() {

                                    hideLoadingDialog();
                                }
                            });
                } catch (Exception e) {
                    Log.e(TAG, "an extremely unlikely failure occurred: ", e);
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i(TAG, "The user canceled.");
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Log.i(TAG, "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
        }
    }



    /**
     * ????????????????????????
     *
     * @param position
     * @param paywayList
     */
    @Override
    public void onItemClickListener(int position, RechargeVipBean.PayListBean paywayList) {
        payWayListAdaper.setSelectPosi(position);
        payWayListAdaper.notifyDataSetChanged();
        PayId = pay_list.get(position).getId();
        pay_dialog.hide();
        toBuy();
    }
}
