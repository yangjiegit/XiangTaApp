package com.muse.xiangta.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.base.BaseFragment;
import com.muse.xiangta.json.FamilyBean;
import com.muse.xiangta.ui.FamilyDetailsActivity;
import com.muse.xiangta.utils.GlideImgManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FamilyFragment extends BaseFragment {

    RecyclerView rv_data;

    private List<FamilyBean.DataBean> mList = new ArrayList<>();
    private CommonRecyclerViewAdapter<FamilyBean.DataBean> mAdapter;

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_family, container, false);
    }

    public static FamilyFragment getInstance(List<FamilyBean.DataBean> data) {
        FamilyFragment dynamicMyFragment = new FamilyFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", (Serializable) data);
        dynamicMyFragment.setArguments(bundle);
        return dynamicMyFragment;
    }

    @Override
    protected void initView(View view) {
        rv_data = view.findViewById(R.id.rv_data);

        rv_data.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initDate(View view) {
        mList.addAll((Collection<? extends FamilyBean.DataBean>) getArguments().getSerializable("data"));

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

                tv_dashan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
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
                startActivity(new Intent(
                        getContext(), FamilyDetailsActivity.class
                ).putExtra("data", mList.get(position)));
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
