package com.muse.xiangta.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.chat.ui.ChatActivity;
import com.muse.chat.ui.ChatActivity2;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseFragment;
import com.muse.xiangta.json.FamilyBean;
import com.muse.xiangta.ui.FamilyDetailsActivity;
import com.muse.xiangta.ui.GroupChatActivity;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;
import com.tencent.imsdk.TIMConversationType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class FamilyFragment extends BaseFragment {

    private RecyclerView rv_data;
    private SwipeRefreshLayout sw_refresh;
    private int type;
    private List<FamilyBean.DataBean> mList = new ArrayList<>();
    private CommonRecyclerViewAdapter<FamilyBean.DataBean> mAdapter;

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_family, container, false);
    }

    public static FamilyFragment getInstance(List<FamilyBean.DataBean> data, int type) {
        FamilyFragment dynamicMyFragment = new FamilyFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", (Serializable) data);
        bundle.putInt("type", type);
        dynamicMyFragment.setArguments(bundle);
        return dynamicMyFragment;
    }

    @Override
    protected void initView(View view) {
        rv_data = view.findViewById(R.id.rv_data);
        sw_refresh = view.findViewById(R.id.sw_refresh);

        rv_data.setLayoutManager(new LinearLayoutManager(getContext()));

        sw_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                int page = 1;
                getFamilyListData(type, page);
            }
        });
    }

    private void getFamilyListData(int type, int page) {
        Api.familyList(uId, uToken, String.valueOf(type),
                String.valueOf(page), String.valueOf(10), new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d("ret", "joker    " + s);
                        sw_refresh.setRefreshing(false);
                        if (!StringUtils.isEmpty(s)) {
                            FamilyBean familyBean = new Gson().fromJson(s, FamilyBean.class);
                            if (page == 1) {
                                mList.clear();
                                if (null != familyBean &&
                                        null != familyBean.getData() &&
                                        familyBean.getData().size() > 0) {
                                    for (int i = 0; i < familyBean.getData().size(); i++) {
                                        mList.add(familyBean.getData().get(i));
                                    }
//                                    getSupportFragmentManager().beginTransaction().
//                                            replace(R.id.fl_layout,
//                                                    FamilyFragment.getInstance(mList)).commit();
                                    mAdapter.notifyDataSetChanged();
                                } else {
                                    mList.clear();
//                                    getSupportFragmentManager().beginTransaction().
//                                            replace(R.id.fl_layout,
//                                                    FamilyFragment.getInstance(mList)).commit();
                                    mAdapter.notifyDataSetChanged();
                                }
                            }
                        } else {
                            mList.clear();
//                            getSupportFragmentManager().beginTransaction().
//                                    replace(R.id.fl_layout,
//                                            FamilyFragment.getInstance(mList)).commit();
                            mAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
//                        showToastMsg(e.toString());
                    }
                });
    }

    @Override
    protected void initDate(View view) {
        mList.addAll((Collection<? extends FamilyBean.DataBean>) getArguments().getSerializable("data"));
        type = getArguments().getInt("type", 0);

        initRecyclerView();
    }

    private void initRecyclerView() {

        mAdapter = new CommonRecyclerViewAdapter<FamilyBean.DataBean>(getContext(), mList) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, FamilyBean.DataBean entity, int position) {
                GlideImgManager.glideLoader(
                        getContext(), entity.getFamily_cover(),
                        (ImageView) holder.getView(R.id.iv_head), 1
                );
                holder.setText(R.id.tv_name, entity.getFamily_name());
                holder.setText(R.id.tv_number, entity.getFamily_people() + "人"
                        + "  " + "活跃度:" + entity.getFamily_activation());
                holder.setText(R.id.tv_content,
                        entity.getDescription().getDescription());

                TextView tv_dashan = holder.getView(R.id.tv_dashan);

                if (entity.getIs_join() == 1) {
                    tv_dashan.setVisibility(View.GONE);
                } else {
                    tv_dashan.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_jiazu;
            }
        };

        rv_data.setAdapter(mAdapter);

        mAdapter.setOnRecyclerViewItemClickListener(new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (mList.get(position).getIs_join() == 1) {
                    //已加入了家族
                    ChatActivity2.navToChat(getContext(),
                            mList.get(position).getGroup_id(), mList.get(position).getFamily_name(),
                            TIMConversationType.Group);
                } else {
                    startActivity(new Intent(
                            getContext(), FamilyDetailsActivity.class
                    ).putExtra("data", mList.get(position)));
                }
            }
        });
    }

    @Override
    protected void initSet(View view) {

    }

    @Override
    protected void initDisplayData(View view) {

    }
}
