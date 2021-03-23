package com.muse.xiangta.ui;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.modle.GoldcoinLogBean;
import com.muse.xiangta.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

public class ExchangeRecordActivity extends BaseActivity {

    @BindView(R.id.sw_layout)
    SwipeRefreshLayout sw_layout;
    @BindView(R.id.rv_data)
    RecyclerView rv_data;

    private int page = 1;

    private List<GoldcoinLogBean.DataBean.ListBean> mList = new ArrayList<>();
    private CommonRecyclerViewAdapter<GoldcoinLogBean.DataBean.ListBean> mAdapter;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_exchange_record;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        initRecyclerView();

        page = 1;
        getGoldcoinLogData(page);
    }

    private void getGoldcoinLogData(int page) {
        Api.getGoldcoinLog(uId, uToken, String.valueOf(page), new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker    " + s);
                if (!StringUtils.isEmpty(s)) {
                    GoldcoinLogBean goldcoinLogBean = new Gson().fromJson(s, GoldcoinLogBean.class);
                    if (null != goldcoinLogBean) {
                        if (null != goldcoinLogBean.getData() &&
                                null != goldcoinLogBean.getData().getList() &&
                                goldcoinLogBean.getData().getList().size() > 0) {
                            for (int i = 0; i < goldcoinLogBean.getData().getList().size(); i++) {
                                mList.add(goldcoinLogBean.getData().getList().get(i));
                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }

    private void initRecyclerView() {
        rv_data.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new CommonRecyclerViewAdapter<GoldcoinLogBean.DataBean.ListBean>(this, mList) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, GoldcoinLogBean.DataBean.ListBean entity, int position) {
                holder.setText(R.id.tv_title, entity.getTitle());
                holder.setText(R.id.tv_need_money, entity.getNeed_money());
                holder.setText(R.id.tv_time, entity.getAddtime());
                holder.setText(R.id.tv_money, entity.getMemo() + "");
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_goldcoin_log;
            }
        };

        rv_data.setAdapter(mAdapter);
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
