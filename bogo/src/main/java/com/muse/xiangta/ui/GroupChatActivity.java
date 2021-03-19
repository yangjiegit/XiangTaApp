package com.muse.xiangta.ui;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.chat.ui.ChatActivity;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.modle.GroupBean;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;
import com.tencent.imsdk.TIMConversationType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

public class GroupChatActivity extends BaseActivity {

    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.sw_view)
    SwipeRefreshLayout sw_view;

    private List<GroupBean.DataBean> mList = new ArrayList<>();
    private CommonRecyclerViewAdapter<GroupBean.DataBean> mAdapter;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_group_chat;
    }

    @Override
    protected void initView() {
        rv_data.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        initRecyclerView();

        getGroupListData();

        sw_view.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getGroupListData();
            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new CommonRecyclerViewAdapter<GroupBean.DataBean>(this, mList) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, GroupBean.DataBean entity, int position) {
                GlideImgManager.glideLoader(GroupChatActivity.this, entity.getPic(),
                        (ImageView) holder.getView(R.id.iv_head), 1);
                holder.setText(R.id.tv_name, entity.getName());
                holder.setText(R.id.tv_number, entity.getMemberNum() + "人");
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_group;
            }
        };

        rv_data.setAdapter(mAdapter);

        mAdapter.setOnRecyclerViewItemClickListener(new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                qunliao(String.valueOf(mList.get(position).getGroupid()));
            }
        });
    }

    private void qunliao(final String id) {
        final String uid = SaveData.getInstance().getId();
        final String token = SaveData.getInstance().getToken();

        Api.addGroupMember(uid, token, id, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker    " + s);
                ChatActivity.navToChat(GroupChatActivity.this,
                        id, "默认房间", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2710535566,3134134619&fm=26&gp=0.jpg",
                        1, "1", 1, 1, 1, TIMConversationType.Group);
            }
        });
    }

    private void getGroupListData() {
        Api.groupList(uId, uToken, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker     " + s);
                sw_view.setRefreshing(false);
                mList.clear();
                if (!StringUtils.isEmpty(s)) {
                    GroupBean groupBean = new Gson().fromJson(s, GroupBean.class);
                    if (null != groupBean) {
                        if (groupBean.getData().size() > 0) {
                            for (int i = 0; i < groupBean.getData().size(); i++) {
                                mList.add(groupBean.getData().get(i));
                            }
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
