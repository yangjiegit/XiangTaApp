package com.muse.xiangta.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.fragment.NobleFragment;
import com.muse.xiangta.modle.NobleBean;
import com.muse.xiangta.modle.NobleTitleBean;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class NobleActivity extends BaseActivity {

    @BindView(R.id.rv_title)
    RecyclerView rv_title;
    @BindView(R.id.iv_title)
    ImageView iv_title;
    private int mPosition;
    private List<NobleTitleBean.DataBean> mList = new ArrayList<>();
    private CommonRecyclerViewAdapter<NobleTitleBean.DataBean> mAdapter;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_noble;
    }

    @Override
    protected void initView() {
        rv_title.setLayoutManager(new LinearLayoutManager(
                this, LinearLayout.HORIZONTAL, false));
    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        mPosition = 0;

        initRecyclerView();

        getNobleListData();
    }

    private void initRecyclerView() {
        mAdapter = new CommonRecyclerViewAdapter<NobleTitleBean.DataBean>(this, mList) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, NobleTitleBean.DataBean entity, int position) {
                TextView tvText = holder.getView(R.id.tvText);

                tvText.setText(entity.getTitle());

                if (entity.isCheck() == true) {
                    tvText.setBackgroundResource(R.drawable.text_bg_zi);
                } else {
                    tvText.setBackgroundResource(R.drawable.text_bg_tm);
                }
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_noblelist;
            }
        };

        rv_title.setAdapter(mAdapter);

        mAdapter.setOnRecyclerViewItemClickListener(new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                mPosition = position;
                for (int i = 0; i < mList.size(); i++) {
                    if (i == mPosition) {
                        mList.get(mPosition).setCheck(true);
                    } else {
                        mList.get(i).setCheck(false);
                    }
                }
                mAdapter.notifyDataSetChanged();
                getNobleData(String.valueOf(mList.get(mPosition).getId()));
            }
        });
    }

    private void getNobleListData() {
        Api.getNobleList(uId, uToken, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker    " + s);
                if (!StringUtils.isEmpty(s)) {
                    NobleTitleBean nobleBean = new Gson().fromJson(s, NobleTitleBean.class);
                    if (nobleBean.getCode() == 1) {
                        if (null != nobleBean.getData() &&
                                nobleBean.getData().size() > 0) {
                            for (int i = 0; i < nobleBean.getData().size(); i++) {
                                if (i == 0) {
                                    nobleBean.getData().get(i).setCheck(true);
                                } else {
                                    nobleBean.getData().get(i).setCheck(false);
                                }
                                mList.add(nobleBean.getData().get(i));
                            }
                        }
                        mAdapter.notifyDataSetChanged();

                        getNobleData(String.valueOf(mList.get(mPosition).getId()));

                    }
                }
            }
        });
    }

    private void getNobleData(String id) {
        Api.getNoble(uId, uToken, id, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
//                Log.d("ret", "joker     " + s);
                if (!StringUtils.isEmpty(s)) {
                    NobleBean nobleBean = new Gson().fromJson(s, NobleBean.class);
                    if (nobleBean.getCode() == 1) {
                        GlideImgManager.loadImage(NobleActivity.this,
                                nobleBean.getData().getNob_info().getIamge(), iv_title);
                    }

                    getSupportFragmentManager().beginTransaction().replace(
                            R.id.fl_layout, NobleFragment.getInstance(mList.get(mPosition).getId())).commit();
                }
            }
        });
    }

    @Override
    protected void initPlayerDisplayData() {

    }

    @OnClick(R.id.iv_back)
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
