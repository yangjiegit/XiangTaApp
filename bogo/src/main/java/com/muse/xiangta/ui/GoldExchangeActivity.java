package com.muse.xiangta.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.modle.GoldcoinBean;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

public class GoldExchangeActivity extends BaseActivity {

    @BindView(R.id.tv_jinbi)
    TextView tv_jinbi;
    @BindView(R.id.rv_data)
    RecyclerView rv_data;

    private List<GoldcoinBean.DataBean.ListBean> mList = new ArrayList<>();
    private CommonRecyclerViewAdapter<GoldcoinBean.DataBean.ListBean> mAdapter;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_gold_exchange;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        getGoldcoinListData();

        initRecyclerView();
    }

    private void initRecyclerView() {
        rv_data.setLayoutManager(new GridLayoutManager(this, 2));

        mAdapter = new CommonRecyclerViewAdapter<GoldcoinBean.DataBean.ListBean>(this, mList) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, GoldcoinBean.DataBean.ListBean entity, int position) {
                GlideImgManager.loadImage(GoldExchangeActivity.this,
                        entity.getImage(), holder.getView(R.id.iv_image));
                holder.setText(R.id.tv_zuanshi, entity.getName());
                holder.setText(R.id.tv_jinbi, entity.getNeed_money_title());
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_gold_exchange;
            }
        };

        rv_data.setAdapter(mAdapter);

        mAdapter.setOnRecyclerViewItemClickListener(new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                dialog(mList.get(position));
            }
        });
    }

    private void dialog(GoldcoinBean.DataBean.ListBean data) {
        final AlertDialog dialog = new AlertDialog.Builder(GoldExchangeActivity.this).create();
        dialog.show();  //注意：必须在window.setContentView之前show
        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_jinbi);
        //点击确定按钮让对话框消失
        TextView tv_text = dialog.findViewById(R.id.tv_text);

        tv_text.setText("确认花费" + data.getNeed_money_title() + "兑换" + data.getName() + "吗？");
        TextView tv_comm1 = dialog.findViewById(R.id.tv_comm1);
        TextView tv_comm2 = dialog.findViewById(R.id.tv_comm2);
        tv_comm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                goldcoinExchangeData(data.getId() + "");
            }
        });

        tv_comm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void goldcoinExchangeData(String id) {
        Api.goldcoinExchange(uId, uToken, id, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (!StringUtils.isEmpty(s)) {
                    try {
                        int code = new JSONObject(s).getInt("code");
                        if (code == 1) {
                            showToastMsg(new JSONObject(s).getString("msg"));
                            getGoldcoinListData();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    private void getGoldcoinListData() {
        Api.getGoldcoinList(uId, uToken, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker     " + s);
                if (!StringUtils.isEmpty(s)) {
                    GoldcoinBean goldcoinBean = new Gson().fromJson(s, GoldcoinBean.class);
                    tv_jinbi.setText(goldcoinBean.getData().getIncome() + "");
                    if (null != goldcoinBean) {
                        if (null != goldcoinBean.getData() &&
                                null != goldcoinBean.getData().getList() &&
                                goldcoinBean.getData().getList().size() > 0) {
                            for (int i = 0; i < goldcoinBean.getData().getList().size(); i++) {
                                mList.add(goldcoinBean.getData().getList().get(i));
                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
