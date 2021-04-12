package com.muse.xiangta.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.chat.model.RedEnvelopeDetailBean;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class RedEnvelopesDetailsActivity extends BaseActivity {


    private String red_envelope_id;
    private RedEnvelopeDetailBean redEnvelopeDetailBean;

    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.tv_number)
    TextView tv_number;
    @BindView(R.id.tv_title_number)
    TextView tv_title_number;
    @BindView(R.id.rv_data)
    RecyclerView rv_data;

    private List<RedEnvelopeDetailBean.DataBean.HistoryBean> mList = new ArrayList<>();
    private CommonRecyclerViewAdapter<RedEnvelopeDetailBean.DataBean.HistoryBean> mAdapter;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_red_envelopes_details;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        red_envelope_id = getIntent().getStringExtra("red_envelope_id");

        initRecyclerView();

        Api.red_envelope_detail(uId, uToken, red_envelope_id, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (!StringUtils.isEmpty(s)) {
                    redEnvelopeDetailBean = new Gson().fromJson(s, RedEnvelopeDetailBean.class);

                    if (null != redEnvelopeDetailBean.getData().getHistory()) {
                        for (int i = 0; i < redEnvelopeDetailBean.getData().getHistory().size(); i++) {
                            mList.add(redEnvelopeDetailBean.getData().getHistory().get(i));
                        }
                        mAdapter.notifyDataSetChanged();
                    }

                    GlideImgManager.glideLoader(RedEnvelopesDetailsActivity.this,
                            redEnvelopeDetailBean.getData().getFrom_user().getAvatar(), iv_head, 0);

                    tv_number.setText(redEnvelopeDetailBean.getData().getAmount() + "钻石");
                    tv_title_number.setText(redEnvelopeDetailBean.getData().getTotal_count() + "个红包，剩余" +
                            redEnvelopeDetailBean.getData().getCount() + "个");
                }
            }
        });
    }

    private void initRecyclerView() {
        rv_data.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new CommonRecyclerViewAdapter<RedEnvelopeDetailBean.DataBean.HistoryBean>(this, mList) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, RedEnvelopeDetailBean.DataBean.HistoryBean entity, int position) {
                GlideImgManager.glideLoader(RedEnvelopesDetailsActivity.this,
                        entity.getTo_user().getAvatar(), holder.getView(R.id.iv_head), 0);
                holder.setText(R.id.tv_name, entity.getTo_user().getUser_nickname());
                holder.setText(R.id.tv_time, entity.getCreate_time());
                holder.setText(R.id.tv_number, entity.getAmount() + "钻石");
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_hongbao;
            }
        };

        rv_data.setAdapter(mAdapter);
    }

    @OnClick(R.id.iv_back)
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
