package com.muse.xiangta.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.alipay.AlipayService;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseFragment;
import com.muse.xiangta.json.JsonRequestBase;
import com.muse.xiangta.json.JsonRequestGetRechargeRule;
import com.muse.xiangta.json.JsonRequestRecharge;
import com.muse.xiangta.modle.ConfigModel;
import com.muse.xiangta.modle.Pay4Bean2;
import com.muse.xiangta.modle.PayMenuModel;
import com.muse.xiangta.modle.RechargeRuleModel;
import com.muse.xiangta.paypal.PayPalHandle;
import com.muse.xiangta.wxpay.WChatPayService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class WealthRechargeFragment extends BaseFragment {


    @BindView(R.id.recharge_list)
    RecyclerView recy_recharge;
    @BindView(R.id.recharge_payway)
    RecyclerView recy_payway;
    @BindView(R.id.zs_coin1)
    TextView zs1;
    @BindView(R.id.zs_coin2)
    TextView zs2;
    @BindView(R.id.view1)
    TextView view1;
    @BindView(R.id.view2)
    TextView view2;

    private BaseQuickAdapter rechageAdapter;
    private String TAG = "pay";
    private String rid;
    private String pid;


    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_recharge, container, false);
    }


    int nowSelRecharge = -1;
    int nowSelPayWay = -1;

    @Override
    protected void initView(View view) {
        recy_payway.setItemViewCacheSize(0);
        recy_payway.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recy_recharge.setLayoutManager(new GridLayoutManager(getContext(), 3));
        String[] list = new String[6];
        recy_recharge.setAdapter(rechageAdapter = new BaseQuickAdapter<RechargeRuleModel, BaseViewHolder>(R.layout.recharge_buy_item, mRechargeRuleDataList) {
            @Override
            protected void convert(BaseViewHolder helper, RechargeRuleModel item) {
                helper.setText(R.id.top, item.getFormatCoin());
                helper.setText(R.id.bottom, "¥" + item.getMoney());

                if (nowSelRecharge == helper.getAdapterPosition()) {
                    helper.setVisible(R.id.sel_icon, true);
                    helper.getView(R.id.bg).setBackgroundResource(R.drawable.bg_guardbuy_item);
                } else {
                    helper.setVisible(R.id.sel_icon, false);
                    helper.getView(R.id.bg).setBackgroundResource(R.drawable.bg_guardbuy_item_y);
                }
            }
        });
        rechageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                nowSelRecharge = position;
                if (!StringUtils.isEmpty(mRechargeRuleDataList.get(position).getName())) {
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.VISIBLE);
                } else {
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.GONE);
                }
                zs1.setText("赠送" + mRechargeRuleDataList.get(position).getGive() + getResources().getString(R.string.company));
                zs2.setText(mRechargeRuleDataList.get(position).getName());
                adapter.notifyDataSetChanged();
            }
        });


        recy_payway.setAdapter(pay_adapter = new BaseQuickAdapter<PayMenuModel, BaseViewHolder>(R.layout.vip_details_item, mRechargePayMenuDataList) {
            @Override
            protected void convert(BaseViewHolder helper, PayMenuModel item) {
                helper.setTextColor(R.id.text, Color.parseColor("#646464"));
                helper.setText(R.id.text, item.getPay_name());
                com.muse.xiangta.utils.Utils.loadUserIcon(item.getIcon(), (ImageView) helper.getView(R.id.icon));
                if (nowSelPayWay == helper.getAdapterPosition()) {
                    helper.getView(R.id.bg).setBackgroundResource(R.drawable.bg_guardbuy_item_y);
                } else {
                    helper.getView(R.id.bg).setBackgroundColor(getResources().getColor(R.color.white));
                }
            }
        });
        pay_adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                nowSelPayWay = position;
                adapter.notifyDataSetChanged();
            }
        });

        requestData();
    }

    BaseQuickAdapter pay_adapter;
    private List<RechargeRuleModel> mRechargeRuleDataList = new ArrayList<>();
    private List<PayMenuModel> mRechargePayMenuDataList = new ArrayList<>();

    //获取充值页面数据
    private void requestData() {

        Api.doRequestGetChargeRule(uId, uToken, new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {

                JsonRequestGetRechargeRule jsonObj =
                        (JsonRequestGetRechargeRule) JsonRequestBase.getJsonObj(s, JsonRequestGetRechargeRule.class);

                if (jsonObj.getCode() == 1) {
                    //支付方式默认选中第一个
                    mRechargeRuleDataList.clear();
                    mRechargeRuleDataList.addAll(jsonObj.getList());
                    rid = mRechargeRuleDataList.get(0).getId();
                    nowSelPayWay = 0;

                    //充值规则
                    mRechargePayMenuDataList.clear();
                    mRechargePayMenuDataList.addAll(jsonObj.getPay_list());

                    pay_adapter.notifyDataSetChanged();
                    rechageAdapter.notifyDataSetChanged();
                } else {
                    ToastUtils.showShort(jsonObj.getMsg());
                }

            }
        });
    }


    @OnClick(R.id.to_pay)
    public void startPay() {
        if (mRechargeRuleDataList.size() == 0 || nowSelRecharge == -1) {
            ToastUtils.showShort(getString(R.string.please_chose_recharge_rule));
            return;
        }
        if (mRechargePayMenuDataList.size() == 0 || nowSelPayWay == -1) {
            ToastUtils.showShort(getString(R.string.please_chose_recharge_type));
            return;
        }
        showLoadingDialog(getString(R.string.loading_now_submit_order));
        RechargeRuleModel rechargeRuleModel = mRechargeRuleDataList.get(nowSelRecharge);
        rid = rechargeRuleModel.getId();
        pid = mRechargePayMenuDataList.get(nowSelPayWay).getId();
        Api.doRequestCharge(uId, uToken, rid, pid, new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {
                hideLoadingDialog();
                if (nowSelPayWay == 0) {
                    Pay4Bean2 pay4Bean2 = new Gson().fromJson(s, Pay4Bean2.class);
                    if (pay4Bean2.getCode() == 1) {
                        urlPay(pay4Bean2);
                    }
                } else {
                    AlipayService alipayService = new AlipayService(getActivity());
                    try {
                        alipayService.payV2(new JSONObject(s).getJSONObject("pay").getString("pay_info"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                hideLoadingDialog();
            }
        });

    }

    private void urlPay(Pay4Bean2 pay4Bean2) {
        WChatPayService alipayService = new WChatPayService(getActivity());
        alipayService.callWxPay(pay4Bean2);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm =
                    data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                try {
                    Log.i(TAG, confirm.toJSONObject().toString(4));
                    Log.i(TAG, confirm.getPayment().toJSONObject().toString(4));
                    //这里可以把PayPal带回来的json数据传给服务器以确认你的款项是否收到或者收全
                    //可以直接把 confirm.toJSONObject() 这个带给服务器，
                    showLoadingDialog("正在获取支付结果...");
                    //得到服务器返回的结果，你就可以跳转成功页面或者做相应的处理了
                    PayPalHandle.getInstance().confirmPayResult(getContext(),
                            requestCode, resultCode, data, mRechargeRuleDataList.get(nowSelRecharge).getId(), new PayPalHandle.DoResult() {

                                @Override
                                public void confirmSuccess() {
                                    hideLoadingDialog();
                                    ToastUtils.showLong("支付成功！");
                                }

                                @Override
                                public void confirmNetWorkError() {
                                    hideLoadingDialog();
                                }

                                @Override
                                public void customerCanceled() {
                                    hideLoadingDialog();
                                    ToastUtils.showLong("取消支付！");
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
                } catch (JSONException e) {
                    Log.e(TAG, "an extremely unlikely failure occurred: ", e);
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i(TAG, "The user canceled.");
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Log.i(
                    TAG,
                    "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (ConfigModel.getInitData().getOpen_pay_pal() == 1) {
            PayPalHandle.getInstance().stopPayPalService(getContext());
        }
    }


    @Override
    protected void initDate(View view) {

    }

    @Override
    protected void initSet(View view) {

    }

    @Override
    protected void initDisplayData(View view) {

    }

}
