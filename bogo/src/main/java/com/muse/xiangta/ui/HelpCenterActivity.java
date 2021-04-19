package com.muse.xiangta.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.chat.model.HelpIndexBean;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class HelpCenterActivity extends BaseActivity {

    @BindView(R.id.iv_title)
    ImageView iv_title;
    @BindView(R.id.rv_data1)
    RecyclerView rv_data1;
    @BindView(R.id.rv_data2)
    RecyclerView rv_data2;

    private List<HelpIndexBean.DataBean.HelpBean> mList = new ArrayList<>();
    private CommonRecyclerViewAdapter<HelpIndexBean.DataBean.HelpBean> mAdapter;

    private List<HelpIndexBean.DataBean.HotHelpBean> mList2 = new ArrayList<>();
    private CommonRecyclerViewAdapter<HelpIndexBean.DataBean.HotHelpBean> mAdapter2;

    private HelpIndexBean helpIndexBean;


    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_help_center;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.iv_back, R.id.iv_title})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_title:
//                String intStr1 = "12";
//                String url1 = "http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/" + intStr1 + ".html";
                startActivity(new Intent(this, XieYiActivity.class)
                        .putExtra("title", "绿色公约").putExtra("url", helpIndexBean.getData().getGongyue().getUrl()));
                break;
        }
    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        initRecyclerView();

        helpIndexData();
    }

    private void initRecyclerView() {
        rv_data1.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new CommonRecyclerViewAdapter<HelpIndexBean.DataBean.HelpBean>(this, mList) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, HelpIndexBean.DataBean.HelpBean entity, int position) {
                holder.setText(R.id.tv_text, entity.getTitle());
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_help_center;
            }
        };

        rv_data1.setAdapter(mAdapter);

        mAdapter.setOnRecyclerViewItemClickListener(new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
//                XWebViewActivity
                startActivity(new Intent(HelpCenterActivity.this,
                        XWebViewActivity.class).putExtra("url", mList.get(position).getUrl()));
            }
        });

        rv_data2.setLayoutManager(new LinearLayoutManager(this));

        mAdapter2 = new CommonRecyclerViewAdapter<HelpIndexBean.DataBean.HotHelpBean>(this, mList2) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, HelpIndexBean.DataBean.HotHelpBean entity, int position) {
                holder.setText(R.id.tv_text, entity.getTitle());
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_help_center;
            }
        };

        rv_data2.setAdapter(mAdapter2);

        mAdapter2.setOnRecyclerViewItemClickListener(new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
//                XWebViewActivity
                startActivity(new Intent(HelpCenterActivity.this,
                        XWebViewActivity.class).putExtra("url", mList2.get(position).getUrl()));
            }
        });

    }

    private void helpIndexData() {
        Api.helpIndex(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (!StringUtils.isEmpty(s)) {
                    helpIndexBean = new Gson().fromJson(s, HelpIndexBean.class);
//                    GlideImgManager.loadImage(HelpCenterActivity.this
//                            , helpIndexBean.getData().getGongyue().getUrl(), iv_title);

                    if (helpIndexBean.getData().getHelp().size() > 0) {
                        for (int i = 0; i < helpIndexBean.getData().getHelp().size(); i++) {
                            mList.add(helpIndexBean.getData().getHelp().get(i));
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                    if (helpIndexBean.getData().getHot_help().size() > 0) {
                        for (int i = 0; i < helpIndexBean.getData().getHot_help().size(); i++) {
                            mList2.add(helpIndexBean.getData().getHot_help().get(i));
                        }
                        mAdapter2.notifyDataSetChanged();
                    }

                }
            }
        });
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
