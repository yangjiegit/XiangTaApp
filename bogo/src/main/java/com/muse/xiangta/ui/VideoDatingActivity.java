package com.muse.xiangta.ui;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.modle.VideoBean;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

public class VideoDatingActivity extends BaseActivity {

    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.sw_refresh)
    SwipeRefreshLayout sw_refresh;
    @BindView(R.id.iv_back)
    ImageView iv_back;

    private int page;
    private int limit;

    private List<VideoBean.DataBean> mList = new ArrayList<>();
    private CommonRecyclerViewAdapter<VideoBean.DataBean> mAdapter;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_video_dating;
    }

    @Override
    protected void initView() {
        iv_back.setOnClickListener(this);
    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        rv_data.setLayoutManager(new GridLayoutManager(this, 2));

        initRecyclerView();

        page = 1;
        limit = 10;
        getUserVideoData(page, limit);

        sw_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                limit = 10;
                mAdapter.loadMoreComplete(false);
                getUserVideoData(page, limit);
            }
        });
    }

    private void getUserVideoData(final int page, int limit) {
        Api.userVideo(uId, uToken, String.valueOf(page), String.valueOf(limit), new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker    " + s);
                sw_refresh.setRefreshing(false);
                if (!StringUtils.isEmpty(s)) {
                    VideoBean videoBean = new Gson().fromJson(s, VideoBean.class);
                    List<VideoBean.DataBean> list = new ArrayList<>();
                    if (null != videoBean.getData()) {
                        if (null != videoBean.getData() &&
                                videoBean.getData().size() > 0) {
                            for (int i = 0; i < videoBean.getData().size(); i++) {
                                list.add(videoBean.getData().get(i));
                            }
                        }
                    }
                    if (page == 1) {
                        mAdapter.refreshDatas(list, true);
                    } else {
                        mAdapter.refreshDatas(list, false);
                    }
                }
            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new CommonRecyclerViewAdapter<VideoBean.DataBean>(this, mList) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, VideoBean.DataBean entity, int position) {
                GlideImgManager.loadImage(VideoDatingActivity.this,
                        entity.getImg(), (ImageView) holder.getView(R.id.iv_head));
                holder.setText(R.id.tv_content, entity.getUser_nickname());
                holder.setText(R.id.tv_age, entity.getAge() + "岁");
//                holder.setText(R.id.tv_address,entity.get);
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_video;
            }
        };

        rv_data.setAdapter(mAdapter);

        mAdapter.setLoadMore(true);

        mAdapter.setOnLoadMoreListener(new CommonRecyclerViewAdapter.OnLoadMoreListener() {
            @Override
            public void onloadmore() {
                ++page;
                limit = 10;
                getUserVideoData(page, limit);
            }
        });
    }

    @Override
    protected void initPlayerDisplayData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
