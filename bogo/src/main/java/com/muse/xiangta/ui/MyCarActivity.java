package com.muse.xiangta.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.modle.MyCarBean;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

public class MyCarActivity extends BaseActivity {

    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    private List<List<MyCarBean.DataBean.ClassicBean>> mList = new ArrayList<>();
    private CommonRecyclerViewAdapter<List<MyCarBean.DataBean.ClassicBean>> mAdapter;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_mycar;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        initRecyclerView();

        getMyCarData();
    }

    private void getMyCarData() {
        Api.mycar(uId, uToken, uId, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker     " + s);
                mList.clear();
                if (!StringUtils.isEmpty(s)) {
                    MyCarBean myCarBean = new Gson().fromJson(s, MyCarBean.class);
                    if (null != myCarBean) {
                        if (null != myCarBean.getData() &&
                                null != myCarBean.getData().getClassic() &&
                                myCarBean.getData().getClassic().size() > 0) {
                            for (int i = 0; i < myCarBean.getData().getClassic().size(); i++) {
                                mList.add(myCarBean.getData().getClassic().get(i));
                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }

    private void initRecyclerView() {
        rv_data.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new CommonRecyclerViewAdapter<List<MyCarBean.DataBean.ClassicBean>>(this, mList) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, List<MyCarBean.DataBean.ClassicBean> entity, int position) {
                ImageView iv_image1 = holder.getView(R.id.iv_image1);
                ImageView iv_image2 = holder.getView(R.id.iv_image2);
                TextView tv_text1 = holder.getView(R.id.tv_text1);
                TextView tv_text2 = holder.getView(R.id.tv_text2);
                TextView tv_month1 = holder.getView(R.id.tv_month1);
                TextView tv_month2 = holder.getView(R.id.tv_month2);
                if (entity.size() > 1) {
                    GlideImgManager.loadImage(MyCarActivity.this, entity.get(0).getImage(), iv_image1);
                    GlideImgManager.loadImage(MyCarActivity.this, entity.get(1).getImage(), iv_image2);
                    tv_text1.setText(entity.get(0).getTitle());
                    tv_text2.setText(entity.get(1).getTitle());
//                    tv_month1.setVisibility(View.VISIBLE);
//                    tv_month2.setVisibility(View.VISIBLE);
                    if (entity.get(0).getStatus() == 0) {
                        //没有骑
                        tv_month1.setVisibility(View.GONE);
                    } else if (entity.get(1).getStatus() == 0) {
                        //没有骑
                        tv_month2.setVisibility(View.GONE);
                    } else if (entity.get(0).getStatus() == 1) {
                        //骑
                        tv_month1.setVisibility(View.VISIBLE);
                        tv_month1.setText("已选中");
                    } else if (entity.get(1).getStatus() == 1) {
                        //骑
                        tv_month2.setVisibility(View.VISIBLE);
                        tv_month2.setText("已选中");
                    }
                } else if (entity.size() == 1) {
                    GlideImgManager.loadImage(MyCarActivity.this, entity.get(0).getImage(), iv_image1);
                    tv_text1.setText(entity.get(0).getTitle());
                    tv_month1.setVisibility(View.VISIBLE);
                    tv_month2.setVisibility(View.GONE);
                    if (entity.get(0).getStatus() == 0) {
                        tv_month1.setVisibility(View.GONE);
                    } else {
                        tv_month1.setVisibility(View.VISIBLE);
                        tv_month1.setText("已选中");
                    }
                }
                iv_image1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (entity.size() == 1 || entity.size() > 1) {
//                            startActivity(new Intent(MyCarActivity.this, BuyMountActivity.class)
//                                    .putExtra("id", entity.get(0).getId() + ""));
                            getQiCarData(entity.get(0).getId());
                        }
                    }
                });

                iv_image2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (entity.size() > 1) {
//                            startActivity(new Intent(MyCarActivity.this, BuyMountActivity.class)
//                                    .putExtra("id", entity.get(1).getId() + ""));
                            getQiCarData(entity.get(1).getId());
                        }
                    }
                });
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_my_car;
            }
        };

        rv_data.setAdapter(mAdapter);
    }

    private void getQiCarData(int id) {
        Api.qicar(uId, uToken, String.valueOf(id), new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker      " + s);
                getMyCarData();
            }
        });
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
