package com.muse.xiangta.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.json.FamilyBean;
import com.muse.xiangta.modle.MemberItemBean;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class FamilyDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_image)
    ImageView iv_image;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_age)
    TextView tv_age;
    @BindView(R.id.tv_number)
    TextView tv_number;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.rl_data)
    RelativeLayout rl_data;
    @BindView(R.id.tv_comm)
    TextView tv_comm;
    @BindView(R.id.tv_name_number)
    TextView tv_name_number;
    @BindView(R.id.iv_sm)
    ImageView iv_sm;

    private FamilyBean.DataBean mData;

    private int page = 1;
    private int limit = 10;

    private List<MemberItemBean.DataBean> mList = new ArrayList<>();
    private CommonRecyclerViewAdapter<MemberItemBean.DataBean> mAdapter;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_family_details;
    }

    @Override
    protected void initView() {
        tv_comm.setOnClickListener(this);
    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        mData = (FamilyBean.DataBean) getIntent().getSerializableExtra("data");
        if (mData.getIs_join() == 2) {
            tv_comm.setText("待审核");
        } else if (mData.getIs_join() == 1) {
            tv_comm.setText("退出家族");
        } else {
            tv_comm.setText("申请加入");
        }

        initRecyclerView();
        setData();
    }

    private void initRecyclerView() {
        rv_data.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));

        mAdapter = new CommonRecyclerViewAdapter<MemberItemBean.DataBean>(this, mList) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, MemberItemBean.DataBean entity, int position) {
                GlideImgManager.glideLoader(FamilyDetailsActivity.this, entity.getUser().getAvatar()
                        , holder.getView(R.id.iv_head), 1);
                holder.setText(R.id.tv_name, entity.getUser().getUser_nickname());
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_member;
            }
        };

        rv_data.setAdapter(mAdapter);

        mAdapter.setOnRecyclerViewItemClickListener(new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                startActivity(new Intent(FamilyDetailsActivity.this, MemberListActivity.class)
                        .putExtra("family_id", String.valueOf(mData.getFamily_id()))
                        .putExtra("id", String.valueOf(mData.getOwner().getId())));
            }
        });
    }

    private void setData() {
        GlideImgManager.loadImage(this, mData.getFamily_cover(), iv_image);

        tv_content.setText(mData.getDescription().getDescription());

        GlideImgManager.glideLoader(this, mData.getOwner().getAvatar(), iv_head, 1);

        tv_name.setText(mData.getOwner().getUser_nickname());

        tv_age.setText(mData.getOwner().getAge() + "");

        tv_number.setText("今日活跃度:");

        page = 1;
        getMemberListData(String.valueOf(page));

    }

    private void getMemberListData(String page) {
        Api.getMemberList(uId, uToken, String.valueOf(mData.getFamily_id()), page, String.valueOf(limit), new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker    " + s);
                if (!StringUtils.isEmpty(s)) {
                    MemberItemBean memberItemBean = new Gson().fromJson(s, MemberItemBean.class);
                    if (null != memberItemBean.getData() &&
                            memberItemBean.getData().size() > 0) {
                        for (int i = 0; i < memberItemBean.getData().size(); i++) {
                            mList.add(memberItemBean.getData().get(i));
                        }
                        mAdapter.notifyDataSetChanged();
                        tv_name_number.setText("家族成员(" + mList.size() + ")");
                    }
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                Log.d("ret", "joker    家族成员" + e.toString());
            }
        });
    }

    @Override
    protected void initPlayerDisplayData() {

    }

    @OnClick({R.id.iv_back, R.id.iv_sm})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_sm:
                //家族说明
                String intStr1 = "14";
                String url1 = "http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/" + intStr1 + ".html";
                startActivity(new Intent(this, XieYiActivity.class)
                        .putExtra("title", "活跃度说明").putExtra("url", url1));
                break;
            case R.id.tv_comm:
                if (mData.getIs_join() == 1) {
                    quit();
                } else if (mData.getIs_join() == 0) {
                    joinData();
                }
                break;
        }
    }

    private void quit() {
        Api.quit(uId, uToken, String.valueOf(mData.getFamily_id()), new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (!StringUtils.isEmpty(s)) {
                    try {
                        int code = new JSONObject(s).getInt("code");
                        if (code == 1) {
                            showToastMsg("退出成功");
                            setResult(50);
                            finish();
                        } else {
                            showToastMsg(new JSONObject(s).getString("msg"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    private void joinData() {
        Api.join(uId, uToken, String.valueOf(mData.getFamily_id()), mData.getGroup_id(), new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (!StringUtils.isEmpty(s)) {
                    try {
                        int code = new JSONObject(s).getInt("code");
                        if (code == 1) {
                            showToastMsg("申请成功");
                            finish();
                        } else {
                            showToastMsg(new JSONObject(s).getString("msg"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
