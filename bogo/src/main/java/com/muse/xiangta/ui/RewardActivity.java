package com.muse.xiangta.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.modle.TaskListBean;
import com.muse.xiangta.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class RewardActivity extends BaseActivity {

    @BindView(R.id.rv_view1)
    RecyclerView rv_view1;
    @BindView(R.id.rv_view2)
    RecyclerView rv_view2;

    private List<TaskListBean.DataBean.NewBean> mList1 = new ArrayList<>();
    private CommonRecyclerViewAdapter<TaskListBean.DataBean.NewBean> mAdapter1;
    private List<TaskListBean.DataBean.DailyBean> mList2 = new ArrayList<>();
    private CommonRecyclerViewAdapter<TaskListBean.DataBean.DailyBean> mAdapter2;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_reward;
    }

    @Override
    protected void initView() {

        rv_view1.setLayoutManager(new LinearLayoutManager(this));
        rv_view2.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        getTaskListData();

        initRecyclerView1();
        initRecyclerView2();
    }

    private void getTaskListData() {
        Api.getTaskList(uId, uToken, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker    任务奖励数据" + s);
                if (!StringUtils.isEmpty(s)) {
                    TaskListBean taskListBean = new Gson().fromJson(s, TaskListBean.class);
                    if (null != taskListBean) {
                        if (null != taskListBean.getData()) {
                            if (null != taskListBean.getData().getNewX() &&
                                    taskListBean.getData().getNewX().size() > 0) {
                                for (int i = 0; i < taskListBean.getData().getNewX().size(); i++) {
                                    mList1.add(taskListBean.getData().getNewX().get(i));
                                }
                                mAdapter1.notifyDataSetChanged();
                            }
                            if (null != taskListBean.getData().getDaily() &&
                                    taskListBean.getData().getDaily().size() > 0) {
                                for (int i = 0; i < taskListBean.getData().getDaily().size(); i++) {
                                    mList2.add(taskListBean.getData().getDaily().get(i));
                                }
                                mAdapter2.notifyDataSetChanged();
                            }
                        }
                    }
                }
            }
        });
    }

    private void initRecyclerView1() {

        mAdapter1 = new CommonRecyclerViewAdapter<TaskListBean.DataBean.NewBean>(this, mList1) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, TaskListBean.DataBean.NewBean entity, int position) {
                holder.setText(R.id.tv_name, entity.getTitle());
                holder.setText(R.id.tv_number, "+" + entity.getNum() + "钻石");
                holder.setText(R.id.tv_content, entity.getDetails());
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_jiangli;
            }
        };

        rv_view1.setAdapter(mAdapter1);
    }

    private void initRecyclerView2() {

        mAdapter2 = new CommonRecyclerViewAdapter<TaskListBean.DataBean.DailyBean>(this, mList2) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, TaskListBean.DataBean.DailyBean entity, int position) {
                holder.setText(R.id.tv_name, entity.getTitle());
                holder.setText(R.id.tv_number, "+" + entity.getNum() + "钻石");
                holder.setText(R.id.tv_content, entity.getDetails());
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_jiangli;
            }
        };

        rv_view2.setAdapter(mAdapter2);
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
