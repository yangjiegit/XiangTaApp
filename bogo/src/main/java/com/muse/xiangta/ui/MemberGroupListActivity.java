package com.muse.xiangta.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.modle.GroupMemberBean;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class MemberGroupListActivity extends BaseActivity {

    @BindView(R.id.sw_refresh)
    SwipeRefreshLayout sw_refresh;
    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.tv_nv)
    TextView tv_nv;
    @BindView(R.id.tv_nan)
    TextView tv_nan;


    private String family_id;
    private int page = 1;
    private String sex = "2";

    private List<GroupMemberBean.DataBean> mList = new ArrayList<>();
    private CommonRecyclerViewAdapter<GroupMemberBean.DataBean> mAdapter;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_member_group_list;
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

        family_id = getIntent().getStringExtra("family_id");
        page = 1;
        getMemberListData(page);
    }

    private void initRecyclerView() {
        rv_data.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new CommonRecyclerViewAdapter<GroupMemberBean.DataBean>(this, mList) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, GroupMemberBean.DataBean entity, int position) {
                GlideImgManager.glideLoader(MemberGroupListActivity.this,
                        entity.getAvatar(), holder.getView(R.id.iv_head), 0);
                holder.setText(R.id.tv_name, entity.getUser_nickname());
                ImageView iv_sex = holder.getView(R.id.iv_sex);
                ImageView iv_renzheng = holder.getView(R.id.iv_renzheng);
                if (entity.getSex() == 1) {
                    iv_sex.setImageResource(R.mipmap.img_xingbienan2);
                } else {
                    iv_sex.setImageResource(R.mipmap.img_xingbie1);
                }
                if (entity.getIs_auth() == 1) {
                    iv_renzheng.setVisibility(View.VISIBLE);
                } else {
                    iv_renzheng.setVisibility(View.GONE);
                }
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_group_member;
            }
        };

        rv_data.setAdapter(mAdapter);

        mAdapter.setOnRecyclerViewItemClickListener(new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(mList.get(position).getUid()));
                intent.putExtras(bundle);
                setResult(88, intent);
                finish();
            }
        });
    }

    private void getMemberListData(int page) {
        Api.getGroupMemberList(uId, uToken, family_id, String.valueOf(page), sex, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker     " + s);
                if (!StringUtils.isEmpty(s)) {
                    GroupMemberBean groupMemberBean = new Gson().fromJson(s, GroupMemberBean.class);
                    if (null != groupMemberBean &&
                            null != groupMemberBean.getData() &&
                            groupMemberBean.getData().size() > 0) {
                        for (int i = 0; i < groupMemberBean.getData().size(); i++) {
                            mList.add(groupMemberBean.getData().get(i));
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_nv, R.id.tv_nan})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_nv:
                page = 1;
                sex = "2";
                mList.clear();
                getMemberListData(page);
                tv_nv.setTextColor(getResources().getColor(R.color.message_check_true));
                tv_nan.setTextColor(getResources().getColor(R.color.ffbdbd));
                break;
            case R.id.tv_nan:
                page = 1;
                sex = "1";
                mList.clear();
                getMemberListData(page);
                tv_nv.setTextColor(getResources().getColor(R.color.ffbdbd));
                tv_nan.setTextColor(getResources().getColor(R.color.message_check_true));
                break;
        }
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
