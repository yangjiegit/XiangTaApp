package com.muse.xiangta.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.maning.imagebrowserlibrary.utils.StatusBarUtil;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.modle.RankBean;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 群聊排行榜
 */
public class RankingListActivity extends BaseActivity {

    @BindView(R.id.tv_caifu)
    TextView tv_caifu;
    @BindView(R.id.tv_meili)
    TextView tv_meili;
    @BindView(R.id.tv_enai)
    TextView tv_enai;
    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.tv_name1)
    TextView tv_name1;
    @BindView(R.id.tv_name2)
    TextView tv_name2;
    @BindView(R.id.tv_name3)
    TextView tv_name3;
    @BindView(R.id.tv_caifu1)
    TextView tv_caifu1;
    @BindView(R.id.tv_caifu2)
    TextView tv_caifu2;
    @BindView(R.id.tv_caifu3)
    TextView tv_caifu3;
    @BindView(R.id.tv_name_id1)
    TextView tv_name_id1;
    @BindView(R.id.tv_name_id2)
    TextView tv_name_id2;
    @BindView(R.id.tv_name_id3)
    TextView tv_name_id3;
    @BindView(R.id.iv_head1)
    ImageView iv_head1;
    @BindView(R.id.iv_head2)
    ImageView iv_head2;
    @BindView(R.id.iv_head3)
    ImageView iv_head3;


    private int type = 1;
    private String family_id;

    private List<RankBean.DataBean.OthersBean> mList = new ArrayList<>();
    private CommonRecyclerViewAdapter<RankBean.DataBean.OthersBean> mAdapter;

    private List<ImageView> mImageList = new ArrayList<>();
    private List<TextView> mTextView1 = new ArrayList<>();
    private List<TextView> mTextView2 = new ArrayList<>();
    private List<TextView> mTextView3 = new ArrayList<>();

    private RankBean mRankBean;

    @Override

    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_ranking_list;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.FF7DA5), 0);
        StatusBarUtil.setLightMode(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        tv_caifu.setTextSize(18);
        tv_meili.setTextSize(15);
    }

    @Override
    protected void initSet() {

    }

    @OnClick({R.id.iv_back, R.id.tv_caifu, R.id.tv_meili, R.id.tv_enai})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_caifu:
                tv_caifu.setTextSize(18);
                tv_meili.setTextSize(15);
                type = 1;
                mList.clear();
                initRankList();
                break;
            case R.id.tv_meili:
                tv_caifu.setTextSize(15);
                tv_meili.setTextSize(18);
                type = 2;
                mList.clear();
                initRankList();
                break;
            case R.id.tv_enai:

                break;
        }
    }

    @Override
    protected void initData() {
        family_id = getIntent().getStringExtra("family_id");
        mImageList.clear();
        mImageList.add(iv_head1);
        mImageList.add(iv_head2);
        mImageList.add(iv_head3);

        mTextView1.clear();
        mTextView1.add(tv_name1);
        mTextView1.add(tv_name2);
        mTextView1.add(tv_name3);

        mTextView2.clear();
        mTextView2.add(tv_name_id1);
        mTextView2.add(tv_name_id2);
        mTextView2.add(tv_name_id3);

        mTextView3.clear();
        mTextView3.add(tv_caifu1);
        mTextView3.add(tv_caifu2);
        mTextView3.add(tv_caifu3);


        initRecyclerView();

        initRankList();
    }

    private void initRecyclerView() {
        rv_data.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new CommonRecyclerViewAdapter<RankBean.DataBean.OthersBean>(this, mList) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, RankBean.DataBean.OthersBean entity, int position) {
                GlideImgManager.glideLoader(RankingListActivity.this,
                        entity.getAvatar(), holder.getView(R.id.iv_head), 0);
                holder.setText(R.id.tv_name, entity.getUser_nickname());
                holder.setText(R.id.tv_age, entity.getAge() + "");
                if (type == 1) {
                    //魅力
                    holder.setText(R.id.tv_leve, "LV" + entity.getGlamour());
                } else {
                    holder.setText(R.id.tv_leve, "LV" + entity.getWealth());
                }
                if (type == 1) {
                    holder.setText(R.id.tv_type, "财富");
                    holder.setText(R.id.tv_zuan, entity.getWealth() + "");
                } else {
                    holder.setText(R.id.tv_type, "魅力");
                    holder.setText(R.id.tv_zuan, entity.getGlamour() + "");
                }
                holder.setText(R.id.tv_age, entity.getAge() + "");

                TextView tv_age = holder.getView(R.id.tv_age);

                Drawable nan = getResources().getDrawable(R.mipmap.img_nan_1);
                // 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
                nan.setBounds(0, 0, nan.getMinimumWidth(), nan.getMinimumHeight());
                Drawable nv = getResources().getDrawable(R.mipmap.img_nv_1);
                // 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
                nv.setBounds(0, 0, nv.getMinimumWidth(), nv.getMinimumHeight());
                if (entity.getSex() == 1) {
                    tv_age.setCompoundDrawables(nan, null, null, null); //设置左图标
                    tv_age.setBackgroundResource(R.drawable.bg_main_lan25);
                } else {
                    tv_age.setCompoundDrawables(nv, null, null, null); //设置左图标
                    tv_age.setBackgroundResource(R.drawable.bg_main_red);
                }

            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_rank;
            }
        };

        rv_data.setAdapter(mAdapter);
    }

    private void initRankList() {
        Api.rankList(uId, uToken, String.valueOf(type), family_id, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker     " + s);
                if (!StringUtils.isEmpty(s)) {
                    mRankBean = new Gson().fromJson(s, RankBean.class);
                    if (null != mRankBean && null != mRankBean.getData()) {
                        if (null != mRankBean.getData().getTop_3() &&
                                mRankBean.getData().getTop_3().size() > 0) {
                            for (int i = 0; i < mRankBean.getData().getTop_3().size(); i++) {
                                GlideImgManager.glideLoader(
                                        RankingListActivity.this,
                                        mRankBean.getData().getTop_3().get(i).getAvatar(),
                                        mImageList.get(i), 0
                                );
                                mTextView1.get(i).setText(mRankBean.getData().
                                        getTop_3().get(i).getUser_nickname());
                                mTextView2.get(i).setText("ID:" + mRankBean.getData().
                                        getTop_3().get(i).getUid() + "");
                                if (type == 1) {
                                    mTextView3.get(i).setText("财富值:" + mRankBean.getData().
                                            getTop_3().get(i).getWealth() + "");
                                } else {
                                    mTextView3.get(i).setText("魅力值:" + mRankBean.getData().
                                            getTop_3().get(i).getWealth() + "");
                                }
                            }
                            if (null != mRankBean.getData().getOthers() &&
                                    mRankBean.getData().getOthers().size() > 0) {
                                for (int i = 0; i < mRankBean.getData().getOthers().size(); i++) {
                                    mList.add(mRankBean.getData().getOthers().get(i));
                                }
                                mAdapter.notifyDataSetChanged();
                            }
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
