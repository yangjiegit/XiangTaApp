package com.muse.xiangta.fragment;

import android.os.Bundle;
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
import com.muse.xiangta.ui.common.Common;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

public class MyMessageFragment extends BaseFragment {

    private int type, page;

    @BindView(R.id.rv_data)
    RecyclerView rv_data;

    private List<MessageBean.DataBean> mList = new ArrayList<>();
    private CommonRecyclerViewAdapter<MessageBean.DataBean> mAdapter;

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_my_message, null);
    }

    public static MyMessageFragment getInstance(int type, int page) {
        MyMessageFragment fragment = new MyMessageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putInt("page", page);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void initView(View view) {
        rv_data.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initDate(View view) {
        type = getArguments().getInt("type");
        page = getArguments().getInt("page");

        initRecyclerView();

        Api.UserLists(uId, uToken, String.valueOf(page), String.valueOf(type), new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker    " + s);
                if (!StringUtils.isEmpty(s)) {
                    MessageBean messageBean = new Gson().fromJson(s, MessageBean.class);
                    if (null != messageBean.getData() &&
                            messageBean.getData().size() > 0) {
                        for (int i = 0; i < messageBean.getData().size(); i++) {
                            mList.add(messageBean.getData().get(i));
                        }
                    }
                    mAdapter.notifyDataSetChanged();
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
                return R.layout.item_my_message;
            }
        };

        rv_data.setAdapter(mAdapter);

        mAdapter.setOnRecyclerViewItemClickListener(new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Common.jumpUserPage(getContext(), String.valueOf(mList.get(position).getId()));
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
