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
import com.muse.xiangta.modle.CarListBean;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class CarActivity extends BaseActivity {

    @BindView(R.id.rv_data1)
    RecyclerView rv_data1;
    @BindView(R.id.rv_data2)
    RecyclerView rv_data2;

    private List<List<CarListBean.DataBean.ClassicBean>> mList1 = new ArrayList<>();
    private CommonRecyclerViewAdapter<List<CarListBean.DataBean.ClassicBean>> mAdapter1;
    private List<List<CarListBean.DataBean.LuxuryBean>> mList2 = new ArrayList<>();
    private CommonRecyclerViewAdapter<List<CarListBean.DataBean.LuxuryBean>> mAdapter2;


    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_car;
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

        getCarListData();
    }

    private void initRecyclerView() {
        rv_data1.setLayoutManager(new LinearLayoutManager(this));
        rv_data2.setLayoutManager(new LinearLayoutManager(this));

        mAdapter1 = new CommonRecyclerViewAdapter<List<CarListBean.DataBean.ClassicBean>>(this, mList1) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, List<CarListBean.DataBean.ClassicBean> entity, int position) {
                ImageView iv_image1 = holder.getView(R.id.iv_image1);
                ImageView iv_image2 = holder.getView(R.id.iv_image2);
                TextView tv_text1 = holder.getView(R.id.tv_text1);
                TextView tv_text2 = holder.getView(R.id.tv_text2);
                TextView tv_month1 = holder.getView(R.id.tv_month1);
                TextView tv_month2 = holder.getView(R.id.tv_month2);
                if (entity.size() > 1) {
                    GlideImgManager.loadImage(CarActivity.this, entity.get(0).getImage(), iv_image1);
                    GlideImgManager.loadImage(CarActivity.this, entity.get(1).getImage(), iv_image2);
                    tv_text1.setText(entity.get(0).getTitle());
                    tv_text2.setText(entity.get(1).getTitle());
                    tv_month1.setVisibility(View.VISIBLE);
                    tv_month2.setVisibility(View.VISIBLE);
                    tv_month1.setText(entity.get(0).getMonth_1() + "元");
                    tv_month2.setText(entity.get(1).getMonth_1() + "元");
                } else if (entity.size() == 1) {
                    GlideImgManager.loadImage(CarActivity.this, entity.get(0).getImage(), iv_image1);
                    tv_text1.setText(entity.get(0).getTitle());
                    tv_month1.setVisibility(View.VISIBLE);
                    tv_month2.setVisibility(View.GONE);
                    tv_month1.setText(entity.get(0).getMonth_1() + "元");
                } else {
                    tv_month1.setVisibility(View.GONE);
                    tv_month2.setVisibility(View.GONE);
                }

                iv_image1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (entity.size() == 1 || entity.size() > 1) {
                            startActivity(new Intent(CarActivity.this, BuyMountActivity.class)
                                    .putExtra("id", entity.get(0).getId() + ""));
                        }
                    }
                });

                iv_image2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (entity.size() > 1) {
                            startActivity(new Intent(CarActivity.this, BuyMountActivity.class)
                                    .putExtra("id", entity.get(1).getId() + ""));
                        }
                    }
                });


            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_car;
            }
        };

        rv_data1.setAdapter(mAdapter1);

        mAdapter2 = new CommonRecyclerViewAdapter<List<CarListBean.DataBean.LuxuryBean>>(this, mList2) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, List<CarListBean.DataBean.LuxuryBean> entity, int position) {
                ImageView iv_image1 = holder.getView(R.id.iv_image1);
                ImageView iv_image2 = holder.getView(R.id.iv_image2);
                TextView tv_text1 = holder.getView(R.id.tv_text1);
                TextView tv_text2 = holder.getView(R.id.tv_text2);
                TextView tv_month1 = holder.getView(R.id.tv_month1);
                TextView tv_month2 = holder.getView(R.id.tv_month2);

                if (entity.size() > 1) {
                    GlideImgManager.loadImage(CarActivity.this, entity.get(0).getImage(), iv_image1);
                    GlideImgManager.loadImage(CarActivity.this, entity.get(1).getImage(), iv_image2);
                    tv_text1.setText(entity.get(0).getTitle());
                    tv_text2.setText(entity.get(1).getTitle());
                    tv_month1.setVisibility(View.VISIBLE);
                    tv_month2.setVisibility(View.VISIBLE);
                    tv_month1.setText(entity.get(0).getMonth_1() + "元");
                    tv_month2.setText(entity.get(1).getMonth_1() + "元");
                } else if (entity.size() == 1) {
                    GlideImgManager.loadImage(CarActivity.this, entity.get(0).getImage(), iv_image1);
                    tv_text1.setText(entity.get(0).getTitle());
                    tv_month1.setVisibility(View.VISIBLE);
                    tv_month2.setVisibility(View.GONE);
                    tv_month1.setText(entity.get(0).getMonth_1() + "元");
                } else {
                    tv_month1.setVisibility(View.GONE);
                    tv_month2.setVisibility(View.GONE);
                }

                iv_image1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (entity.size() == 1 || entity.size() > 1) {
                            startActivity(new Intent(CarActivity.this, BuyMountActivity.class)
                                    .putExtra("id", entity.get(0).getId() + ""));
                        }
                    }
                });

                iv_image2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (entity.size() > 1) {
                            startActivity(new Intent(CarActivity.this, BuyMountActivity.class)
                                    .putExtra("id", entity.get(1).getId() + ""));
                        }
                    }
                });
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_car2;
            }
        };

        rv_data2.setAdapter(mAdapter2);

        mAdapter2.setOnRecyclerViewItemClickListener(new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                startActivity(new Intent(CarActivity.this, BuyMountActivity.class));
            }
        });
    }

    private void getCarListData() {
        Api.CarList(uId, uToken, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker    " + s);
                if (!StringUtils.isEmpty(s)) {
                    CarListBean carListBean = new Gson().fromJson(s, CarListBean.class);
                    if (null != carListBean) {
                        if (null != carListBean.getData().getClassic() &&
                                carListBean.getData().getClassic().size() > 0) {
                            for (int i = 0; i < carListBean.getData().getClassic().size(); i++) {
                                mList1.add(carListBean.getData().getClassic().get(i));
                            }
                            mAdapter1.notifyDataSetChanged();
                        }
                        if (null != carListBean.getData().getLuxury() &&
                                carListBean.getData().getLuxury().size() > 0) {
                            for (int i = 0; i < carListBean.getData().getLuxury().size(); i++) {
                                mList2.add(carListBean.getData().getLuxury().get(i));
                            }
                            mAdapter2.notifyDataSetChanged();
                        }
                    }
                }
            }
        });


    }

    @Override
    protected void initPlayerDisplayData() {

    }

    @OnClick({R.id.iv_back, R.id.tv_my_car})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_my_car:
                startActivity(new Intent(CarActivity.this, MyCarActivity.class));
                break;
        }
    }
}
