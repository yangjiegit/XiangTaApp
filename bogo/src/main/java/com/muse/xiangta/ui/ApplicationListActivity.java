package com.muse.xiangta.ui;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.modle.AuditMemberBean;
import com.muse.xiangta.ui.common.Common;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class ApplicationListActivity extends BaseActivity {

    @BindView(R.id.sw_refresh)
    SwipeRefreshLayout sw_refresh;
    @BindView(R.id.rv_data)
    RecyclerView rv_data;

    private List<AuditMemberBean.DataBean> mList = new ArrayList<>();
    private CommonRecyclerViewAdapter<AuditMemberBean.DataBean> mAdapter;

    private int page = 1;
    private int limit = 10;
    private String family_id;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_application_list;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

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
    protected void initData() {
        family_id = getIntent().getStringExtra("family_id");

        initRecyclerView();
    }

    private void initRecyclerView() {
        rv_data.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new CommonRecyclerViewAdapter<AuditMemberBean.DataBean>(this, mList) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, AuditMemberBean.DataBean entity, int position) {
                GlideImgManager.glideLoader(ApplicationListActivity.this,
                        entity.getUser().getAvatar(), holder.getView(R.id.iv_head), 0);
                holder.setText(R.id.tv_name, entity.getUser().getUser_nickname());

                ((TextView) holder.getView(R.id.tv_tongyi)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        familyAudit("1", String.valueOf(entity.getUser().getId()));
                    }
                });

                ((TextView) holder.getView(R.id.tv_jujue)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        familyAudit("0", String.valueOf(entity.getUser().getId()));
                    }
                });
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_audit_member;
            }
        };

        rv_data.setAdapter(mAdapter);

        mAdapter.setLoadMore(true);

        mAdapter.setOnLoadMoreListener(new CommonRecyclerViewAdapter.OnLoadMoreListener() {
            @Override
            public void onloadmore() {
                ++page;
                getAuditMemberListData(page, limit);
            }
        });

        mAdapter.setOnRecyclerViewItemClickListener(new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Common.jumpUserPage(ApplicationListActivity.this,
                        String.valueOf(mList.get(position).getUser().getId()));
            }
        });

        page = 1;
        getAuditMemberListData(page, limit);

        sw_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getAuditMemberListData(page, limit);
            }
        });
    }

    private void familyAudit(String status, String user_id) {
        Api.familyAudit(uId, uToken,
                family_id, user_id, status, new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if (!StringUtils.isEmpty(s)) {
                            try {
                                int code = new JSONObject(s).getInt("code");
                                if (code == 1) {
                                    showToastMsg(new JSONObject(s).getString("msg"));
                                    page = 1;
                                    getAuditMemberListData(page, limit);
                                }
                            } catch (Exception e) {

                            }
                        }
                    }
                });
    }

    private void getAuditMemberListData(int page, int limit) {
        Api.getAuditMemberList(uId, uToken, family_id, String.valueOf(page), String.valueOf(limit), new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                sw_refresh.setRefreshing(false);
                if (!StringUtils.isEmpty(s)) {
                    AuditMemberBean auditMemberBean = new Gson().fromJson(s, AuditMemberBean.class);
                    List<AuditMemberBean.DataBean> list = new ArrayList<>();
                    if (null != auditMemberBean && null != auditMemberBean.getData() &&
                            auditMemberBean.getData().size() > 0) {
                        for (int i = 0; i < auditMemberBean.getData().size(); i++) {
                            list.add(auditMemberBean.getData().get(i));
                        }
                        if (page == 1) {
                            mAdapter.refreshDatas(list, true);
                        } else {
                            mAdapter.refreshDatas(list, false);
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
