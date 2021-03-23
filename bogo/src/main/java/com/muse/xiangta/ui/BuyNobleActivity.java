package com.muse.xiangta.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.alipay.AlipayService;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.json.JsonRequestBase;
import com.muse.xiangta.json.JsonRequestGetRechargeRule;
import com.muse.xiangta.modle.Pay4Bean2;
import com.muse.xiangta.modle.PayMenuModel;
import com.muse.xiangta.utils.StringUtils;
import com.muse.xiangta.utils.Utils;
import com.muse.xiangta.wxpay.WChatPayService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class BuyNobleActivity extends BaseActivity {

    private List<PayMenuModel> mRechargePayMenuDataList = new ArrayList<>();
    private CommonRecyclerViewAdapter<PayMenuModel> mAdapter;
    private int nowSelPayWay = 0;
    @BindView(R.id.rv_data)
    RecyclerView rv_data;

    private String id, pid, month;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_buy_mount;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        month = getIntent().getStringExtra("month");
        initRecyclerView();
        getPaymentMethodData();

    }

    private void initRecyclerView() {
        rv_data.setLayoutManager(new GridLayoutManager(this, 3));

        mAdapter = new CommonRecyclerViewAdapter<PayMenuModel>(this, mRechargePayMenuDataList) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, PayMenuModel entity, int position) {
                ((TextView) holder.getView(R.id.text)).setTextColor(Color.parseColor("#646464"));
                holder.setText(R.id.text, entity.getPay_name());
                Utils.loadUserIcon(entity.getIcon(), (ImageView) holder.getView(R.id.icon));
                if (nowSelPayWay == holder.getAdapterPosition()) {
                    ((LinearLayout) holder.getView(R.id.bg)).setBackgroundResource(R.drawable.bg_guardbuy_item_y);
                } else {
                    ((LinearLayout) holder.getView(R.id.bg)).setBackgroundColor(getResources().getColor(R.color.white));
                }
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.vip_details_item;
            }
        };

        rv_data.setAdapter(mAdapter);

        mAdapter.setOnRecyclerViewItemClickListener(new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                nowSelPayWay = position;
                pid = mRechargePayMenuDataList.get(position).getId();
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getPaymentMethodData() {
        Api.doRequestGetChargeRule(uId, uToken, new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {
                JsonRequestGetRechargeRule jsonObj =
                        (JsonRequestGetRechargeRule) JsonRequestBase.getJsonObj(s, JsonRequestGetRechargeRule.class);
                mRechargePayMenuDataList.clear();
                mRechargePayMenuDataList.addAll(jsonObj.getPay_list());
                pid = mRechargePayMenuDataList.get(nowSelPayWay).getId();
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick(R.id.to_pay)
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.to_pay:
                buyMountData();
                break;
        }
    }

    private void buyMountData() {
        Api.buyNobility(uId, uToken, uId, id, month, pid, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (!StringUtils.isEmpty(s)) {
                    if (nowSelPayWay == 0) {
                        Pay4Bean2 pay4Bean2 = new Gson().fromJson(s, Pay4Bean2.class);
                        if (pay4Bean2.getCode() == 1) {
                            urlPay(pay4Bean2);
                        }
                    } else {
                        AlipayService alipayService = new AlipayService(BuyNobleActivity.this);
                        try {
                            alipayService.payV2(new JSONObject(s).getJSONObject("pay").getString("pay_info"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    private void urlPay(Pay4Bean2 pay4Bean2) {
        WChatPayService alipayService = new WChatPayService(this);
        alipayService.callWxPay(pay4Bean2);
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
