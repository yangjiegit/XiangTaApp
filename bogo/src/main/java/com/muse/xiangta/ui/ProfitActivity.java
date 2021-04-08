package com.muse.xiangta.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class ProfitActivity extends BaseActivity {

    @BindView(R.id.tv_income)
    TextView tv_income;
    @BindView(R.id.tv_user_money)
    TextView tv_user_money;
    @BindView(R.id.tv_coin)
    TextView tv_coin;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_profit;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        getShowUserGoldcoinData();
    }

    @Override
    protected void initData() {

    }

    private void getShowUserGoldcoinData() {
        Api.showUserGoldcoin(uId, uToken, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker     " + s);
                if (!StringUtils.isEmpty(s)) {
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        if (jsonObject.getInt("code") == 1) {
                            String income = jsonObject.getJSONObject("data").getString("income");
                            String user_money = jsonObject.getJSONObject("data").getString("user_money");
                            String coin = jsonObject.getJSONObject("data").getString("coin");

                            tv_income.setText(income);
                            tv_user_money.setText(user_money);
                            tv_coin.setText(coin);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @OnClick({R.id.ll_mingxi, R.id.ll_jilu, R.id.tv_comm, R.id.ll_zhichu, R.id.iv_back, R.id.tv_tixian})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_tixian:
                //提现
                startActivity(new Intent(ProfitActivity.this, WithdrawalActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_mingxi:
                startActivity(new Intent(ProfitActivity.this, WealthDetailedActivity.class)
                        .putExtra("type", 0));
                break;
            case R.id.ll_zhichu:
                startActivity(new Intent(ProfitActivity.this, WealthDetailedActivity.class)
                        .putExtra("type", 1));
                break;
            case R.id.ll_jilu:
                startActivity(new Intent(ProfitActivity.this, ExchangeRecordActivity.class));
                break;
            case R.id.tv_comm:
                //金币兑换
                startActivity(new Intent(ProfitActivity.this, GoldExchangeActivity.class));
                break;
        }
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
