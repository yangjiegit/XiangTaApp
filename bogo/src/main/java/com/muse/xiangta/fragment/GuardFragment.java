package com.muse.xiangta.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseFragment;
import com.muse.xiangta.modle.MessageBean;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class GuardFragment extends BaseFragment {

    private int type, page;

    private RecyclerView rv_data;
    private SwipeRefreshLayout sw_refresh;
    private List<MessageBean.DataBean> mList = new ArrayList<>();
    private CommonRecyclerViewAdapter<MessageBean.DataBean> mAdapter;

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_guard, container, false);
    }

    @Override
    protected void initView(View view) {
        rv_data = view.findViewById(R.id.rv_data);
        sw_refresh = view.findViewById(R.id.sw_refresh);

        rv_data.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public static GuardFragment getInstance(int type, int page) {
        GuardFragment fragment = new GuardFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putInt("page", page);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void initDate(View view) {

        type = getArguments().getInt("type");
        page = getArguments().getInt("page");

        initRecyclerView();

        getUserListsData(type, page);

        sw_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.loadMoreComplete(false);
                page = 1;
                getUserListsData(type, page);
            }
        });
    }

    private void getUserListsData(int type, final int page) {
        Api.UserLists(uId, uToken, String.valueOf(page), String.valueOf(type), new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker    " + s);
                sw_refresh.setRefreshing(false);
                if (!StringUtils.isEmpty(s)) {
                    MessageBean messageBean = new Gson().fromJson(s, MessageBean.class);
                    List<MessageBean.DataBean> list = new ArrayList<>();
                    if (null != messageBean.getData() &&
                            messageBean.getData().size() > 0) {
                        for (int i = 0; i < messageBean.getData().size(); i++) {
                            list.add(messageBean.getData().get(i));
                        }
                    }
                    if (page == 1) {
                        mAdapter.refreshDatas(list, true);
                    } else {
                        mAdapter.refreshDatas(list, false);
                    }
//                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new CommonRecyclerViewAdapter<MessageBean.DataBean>(getContext(), mList) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, MessageBean.DataBean entity, int position) {
                GlideImgManager.glideLoader(getContext(), entity.getAvatar(), (ImageView) holder.getView(R.id.iv_head), 0);
                holder.setText(R.id.pagemsg_view_name, entity.getUser_nickname());
                holder.setText(R.id.tv_address, entity.getAddress());
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_shouhu;
            }
        };

        rv_data.setAdapter(mAdapter);
    }

    @Override
    protected void initSet(View view) {

    }

    @Override
    protected void initDisplayData(View view) {

    }
}
