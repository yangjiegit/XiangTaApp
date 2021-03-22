package com.muse.xiangta.ui;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.modle.LoveBean;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LoveDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.iv_sex)
    ImageView iv_sex;
    @BindView(R.id.iv_leve)
    ImageView iv_leve;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.tv_content2)
    TextView tv_content2;
    @BindView(R.id.rv_image)
    RecyclerView rv_image;
    @BindView(R.id.videoplayer)
    ImageView videoplayer;

    private LoveBean.DataBean.ListBean mData;

    private List<String> mImageList = new ArrayList<>();
    private CommonRecyclerViewAdapter<String> mAdapter;


    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_love_details;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        mData = (LoveBean.DataBean.ListBean) getIntent().getSerializableExtra("data");

        initRecyclerView();

        setData();
    }

    private void initRecyclerView() {
        rv_image.setLayoutManager(new GridLayoutManager(this, 2));

        mAdapter = new CommonRecyclerViewAdapter<String>(this, mImageList) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, String entity, int position) {
                GlideImgManager.glideLoader(LoveDetailsActivity.this, entity,
                        holder.getView(R.id.iv_image), 1);
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_image;
            }
        };

        rv_image.setAdapter(mAdapter);
    }

    private void setData() {
        GlideImgManager.glideLoader(LoveDetailsActivity.this, mData.getUserInfo().getAvatar(), iv_head, 0);
        tv_name.setText(mData.getUserInfo().getUser_nickname());
        if (mData.getUserInfo().getSex() == 1) {//男
            iv_sex.setImageResource(R.mipmap.img_xingbienan2);
        } else {
            iv_sex.setImageResource(R.mipmap.img_xingbie1);
        }
        tv_content.setText("我们" + mData.getTitle());
        tv_content2.setText(mData.getMsg_content());

        if (StringUtils.isEmpty(mData.getVideo_url())) {
            //为空 就是非视频
            rv_image.setVisibility(View.VISIBLE);
            videoplayer.setVisibility(View.GONE);

            if (null != mData.getOriginalPicUrls() &&
                    mData.getOriginalPicUrls().size() > 0) {
                for (int i = 0; i < mData.getOriginalPicUrls().size(); i++) {
                    mImageList.add(mData.getOriginalPicUrls().get(i));
                }
                mAdapter.notifyDataSetChanged();
            }

        } else {
            rv_image.setVisibility(View.GONE);
            videoplayer.setVisibility(View.VISIBLE);

        }
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
