package com.muse.xiangta.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseFragment;
import com.muse.xiangta.modle.LoveBean;
import com.muse.xiangta.ui.LoveDetailsActivity;
import com.muse.xiangta.ui.PushDynamicActivity;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

public class LoveFragment extends BaseFragment {

    @BindView(R.id.iv_fabu)
    ImageView iv_fabu;
    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.sw_refresh)
    SwipeRefreshLayout sw_refresh;
    @BindView(R.id.ll_quanbu)
    LinearLayout ll_quanbu;
    @BindView(R.id.ll_jingxuan)
    LinearLayout ll_jingxuan;
    @BindView(R.id.tv_quanbu)
    TextView tv_quanbu;
    @BindView(R.id.tv_jingxuan)
    TextView tv_jingxuan;
    @BindView(R.id.view_quanbu)
    View view_quanbu;
    @BindView(R.id.view_jingxuan)
    View view_jingxuan;

    private String love;
    private int page;

    private List<LoveBean.DataBean.ListBean> mList = new ArrayList<>();
    private CommonRecyclerViewAdapter<LoveBean.DataBean.ListBean> mAdapter;


    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_love, container, false);
    }

    @Override
    protected void initView(View view) {
        iv_fabu.setOnClickListener(this);
    }


    @Override
    protected void initDate(View view) {
        love = "love";
        page = 1;

        initRecyclerView();

        ll_quanbu.setOnClickListener(this);
        ll_jingxuan.setOnClickListener(this);

        getLoveData();

        sw_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getLoveData();
            }
        });
    }

    private void initRecyclerView() {
        rv_data.setLayoutManager(new GridLayoutManager(getContext(), 2));

        mAdapter = new CommonRecyclerViewAdapter<LoveBean.DataBean.ListBean>(getContext(), mList) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, LoveBean.DataBean.ListBean entity, int position) {
                if (entity.getThumbnailPicUrls().size() > 0) {
                    GlideImgManager.glideLoader(getContext(), entity.getThumbnailPicUrls().get(0),
                            (ImageView) holder.getView(R.id.iv_head), 1);
                } else {
                    ((ImageView) holder.getView(R.id.iv_head)).setImageResource(R.mipmap.ic_launcher);
                }
                GlideImgManager.glideLoader(getContext(), entity.getUserInfo().getAvatar(),
                        (ImageView) holder.getView(R.id.iv_head2), 1);
                holder.setText(R.id.tv_content, entity.getMsg_content());
                holder.setText(R.id.tv_name, entity.getUserInfo().getUser_nickname());
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_love;
            }
        };

        rv_data.setAdapter(mAdapter);

        mAdapter.setLoadMore(true);

        mAdapter.setOnLoadMoreListener(new CommonRecyclerViewAdapter.OnLoadMoreListener() {
            @Override
            public void onloadmore() {
                ++page;
                getLoveData();
            }
        });

        mAdapter.setOnRecyclerViewItemClickListener(new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                startActivity(new Intent(getContext(), LoveDetailsActivity.class).putExtra("data",
                        mList.get(position)));
            }
        });
    }

    private void getLoveData() {
        Api.LoveList(uId, uToken, love, String.valueOf(page), new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker  123344444  " + s);
                if (page == 1) {
                    mList.clear();
                }
                sw_refresh.setRefreshing(false);
                if (!StringUtils.isEmpty(s)) {
                    LoveBean loveBean = new Gson().fromJson(s, LoveBean.class);
                    List<LoveBean.DataBean.ListBean> list = new ArrayList<>();
                    if (null != loveBean.getData()) {
                        if (null != loveBean.getData().getList() &&
                                loveBean.getData().getList().size() > 0) {
                            for (int i = 0; i < loveBean.getData().getList().size(); i++) {
                                list.add(loveBean.getData().getList().get(i));
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
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                Log.d("ret", "joker    爱情故事请求失败");
            }
        });
    }

    @Override
    protected void initSet(View view) {

    }

    @Override
    protected void initDisplayData(View view) {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_fabu:
                startActivity(new Intent(getContext(), PushDynamicActivity.class)
                        .putExtra("type", 1));
                break;
            case R.id.ll_quanbu:
                tv_quanbu.setTextColor(getResources().getColor(R.color.message_check_true));
                tv_jingxuan.setTextColor(getResources().getColor(R.color.black));
                view_quanbu.setVisibility(View.VISIBLE);
                view_jingxuan.setVisibility(View.INVISIBLE);
                love = "love";
                page = 1;
                getLoveData();
                break;
            case R.id.ll_jingxuan:
                tv_jingxuan.setTextColor(getResources().getColor(R.color.message_check_true));
                tv_quanbu.setTextColor(getResources().getColor(R.color.black));
                view_jingxuan.setVisibility(View.VISIBLE);
                view_quanbu.setVisibility(View.INVISIBLE);
                love = "selectedlove";
                page = 1;
                getLoveData();
                break;
        }
    }
}
