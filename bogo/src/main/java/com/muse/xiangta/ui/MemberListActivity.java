package com.muse.xiangta.ui;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.modle.MemberItemBean;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class MemberListActivity extends BaseActivity {

    private String family_id;
    private int page = 1;
    private int limit = 10;

    @BindView(R.id.sw_refresh)
    SwipeRefreshLayout sw_refresh;
    @BindView(R.id.rv_data)
    RecyclerView rv_data;

    private List<MemberItemBean.DataBean> mList = new ArrayList<>();
    private CommonRecyclerViewAdapter<MemberItemBean.DataBean> mAdapter;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_member_list;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        family_id = getIntent().getStringExtra("family_id");

        initRecyclerView();

        getMemberListData(page);

        sw_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getMemberListData(page);
            }
        });
    }

    private void initRecyclerView() {
        rv_data.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new CommonRecyclerViewAdapter<MemberItemBean.DataBean>(this, mList) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, MemberItemBean.DataBean entity, int position) {
                GlideImgManager.glideLoader(MemberListActivity.this,
                        entity.getUser().getAvatar(), holder.getView(R.id.iv_head), 1);
                holder.setText(R.id.tv_name, entity.getUser().getUser_nickname());
                holder.setText(R.id.tv_age, entity.getUser().getAge() + "");
                holder.setText(R.id.tv_address, entity.getUser().getAddress());
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_member_list;
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

    private void getMemberListData(int page) {
        Api.getMemberList(uId, uToken, family_id, String.valueOf(page), String.valueOf(limit), new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker    " + s);
                sw_refresh.setRefreshing(false);
                if (!StringUtils.isEmpty(s)) {
                    MemberItemBean memberItemBean = new Gson().fromJson(s, MemberItemBean.class);
                    List<MemberItemBean.DataBean> list = new ArrayList<>();
                    if (null != memberItemBean.getData() &&
                            memberItemBean.getData().size() > 0) {
                        for (int i = 0; i < memberItemBean.getData().size(); i++) {
                            list.add(memberItemBean.getData().get(i));
                        }
                        if (page == 1) {
                            mAdapter.refreshDatas(list, true);
                        } else {
                            mAdapter.refreshDatas(list, false);
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
