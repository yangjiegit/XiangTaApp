package com.muse.xiangta.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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

    private String family_id, id;
    private int page = 1;
    private int limit = 10;

    @BindView(R.id.sw_refresh)
    SwipeRefreshLayout sw_refresh;
    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.tv_sq)
    TextView tv_sq;

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
    protected void onStart() {
        super.onStart();

        page = 1;
        getMemberListData(page);
    }

    @Override
    protected void initData() {
        family_id = getIntent().getStringExtra("family_id");
        id = getIntent().getStringExtra("id");

        if (!StringUtils.isEmpty(id)) {
            if (id.equals(uId)) {
                //?????????
                tv_sq.setVisibility(View.VISIBLE);
            } else {
                tv_sq.setVisibility(View.GONE);
            }
        } else {
            tv_sq.setVisibility(View.GONE);
        }

        initRecyclerView();

        page = 1;
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
                ImageView iv_v = holder.getView(R.id.iv_v);
                if (entity.getUser().getIs_auth() == 0) {
                    iv_v.setVisibility(View.GONE);
                } else {
                    iv_v.setVisibility(View.VISIBLE);
                }
                TextView tv_age = holder.getView(R.id.tv_age);

                Drawable nan = getResources().getDrawable(R.mipmap.img_nan_1);
                // ??????setCompoundDrawables??????????????????Drawable.setBounds()??????,?????????????????????
                nan.setBounds(0, 0, nan.getMinimumWidth(), nan.getMinimumHeight());

                Drawable nv = getResources().getDrawable(R.mipmap.img_nv_1);
                // ??????setCompoundDrawables??????????????????Drawable.setBounds()??????,?????????????????????
                nv.setBounds(0, 0, nv.getMinimumWidth(), nv.getMinimumHeight());

                if (entity.getUser().getSex() == 1) {
                    tv_age.setBackgroundResource(R.drawable.bg_main_lan_1);
                    tv_age.setCompoundDrawables(nan, null, null, null); //???????????????
                } else {
                    tv_age.setBackgroundResource(R.drawable.bg_main_red_1);
                    tv_age.setCompoundDrawables(nv, null, null, null); //???????????????
                }
                ImageView iv_vip = holder.getView(R.id.iv_vip);

                if (StringUtils.isEmpty(entity.getUser().getNoble())) {
                    iv_vip.setVisibility(View.GONE);
                } else {
                    iv_vip.setVisibility(View.VISIBLE);
                    GlideImgManager.loadImage(MemberListActivity.this,
                            entity.getUser().getNoble(), iv_vip);
                }

                if (!StringUtils.isEmpty(entity.getUser_activation())) {
                    holder.setText(R.id.tv_number, "???????????????:" + entity.getUser_activation());
                } else {
                    holder.setText(R.id.tv_number, "???????????????:0");
                }
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_member_list;
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

    @OnClick({R.id.iv_back, R.id.tv_sq})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_sq:
                //????????????
                startActivity(new Intent(this, ApplicationListActivity.class)
                        .putExtra("family_id", family_id));
                break;
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
                if (page == 1) {
                    mList.clear();
                }
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
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
