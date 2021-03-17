package com.muse.xiangta.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.audiorecord.util.StringUtil;
import com.muse.xiangta.base.BaseFragment;
import com.muse.xiangta.modle.NobleBean;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

public class NobleFragment extends BaseFragment {

    @BindView(R.id.tv_text)
    TextView tv_text;
    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.rv_data2)
    RecyclerView rv_data2;

    private int id;

    private List<NobleBean.DataBean.NoblePrivilegeBean> mList = new ArrayList<>();
    private CommonRecyclerViewAdapter<NobleBean.DataBean.NoblePrivilegeBean> mAdapter;
    private List<NobleBean.DataBean.CostBean> mList2 = new ArrayList<>();
    private CommonRecyclerViewAdapter<NobleBean.DataBean.CostBean> mAdapter2;

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_noble, null);
    }

    public static NobleFragment getInstance(int type) {
        NobleFragment fragment = new NobleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        rv_data.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rv_data2.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }

    @Override
    protected void initDate(View view) {
        id = getArguments().getInt("id");

        getNobleData(id);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter = new CommonRecyclerViewAdapter<NobleBean.DataBean.NoblePrivilegeBean>
                (getContext(), mList) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, NobleBean.DataBean.NoblePrivilegeBean entity, int position) {
                ImageView iv_image = holder.getView(R.id.iv_image);
                TextView tv_text = holder.getView(R.id.tv_text);

                tv_text.setText(entity.getTitle());
                GlideImgManager.loadImage(getContext(), entity.getTpic(), iv_image);
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_noble_details;
            }
        };

        rv_data.setAdapter(mAdapter);

        mAdapter2 = new CommonRecyclerViewAdapter<NobleBean.DataBean.CostBean>(getContext(), mList2) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, NobleBean.DataBean.CostBean entity, int position) {
                holder.setText(R.id.tv_month, entity.getMonth() + "");
                holder.setText(R.id.tv_price, entity.getPrice() + "元");

                TextView tv_zeng = holder.getView(R.id.tv_zeng);
                if (entity.getDiamonds() == 0) {
                    tv_zeng.setVisibility(View.GONE);
                } else {
                    tv_zeng.setVisibility(View.VISIBLE);
                    tv_zeng.setText("送" + entity.getDiamonds() + "钻石");
                }
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_noble_details2;
            }
        };

        rv_data2.setAdapter(mAdapter2);
    }

    private void getNobleData(int id) {
        Api.getNoble(uId, uToken, String.valueOf(id), new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker     " + s);
                if (!StringUtils.isEmpty(s)) {
                    NobleBean nobleBean = new Gson().fromJson(s, NobleBean.class);
                    if (nobleBean.getCode() == 1) {
                        if (null != nobleBean.getData().getNoble_privilege() &&
                                nobleBean.getData().getNoble_privilege().size() > 0) {
                            for (int i = 0; i < nobleBean.getData().getNoble_privilege().size(); i++) {
                                mList.add(nobleBean.getData().getNoble_privilege().get(i));
                            }
                            tv_text.setText("拥有特权" + nobleBean.getData().getNob_info().getNumb() + "/" +
                                    mList.size());
                            mAdapter.notifyDataSetChanged();
                        }

                        if (null != nobleBean.getData().getCost() &&
                                nobleBean.getData().getCost().size() > 0) {
                            for (int i = 0; i < nobleBean.getData().getCost().size(); i++) {
                                mList2.add(nobleBean.getData().getCost().get(i));
                            }
                            mAdapter2.notifyDataSetChanged();
                        }
                    }
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
