package com.muse.xiangta.fragment;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.library.flowlayout.FlowLayoutManager;
import com.library.flowlayout.SpaceItemDecoration;
import com.muse.xiangta.BuildConfig;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.base.BaseFragment;
import com.muse.xiangta.json.jsonmodle.TargetUserData2;
import com.muse.xiangta.modle.UserModel;
import com.muse.xiangta.utils.GlideImgManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 用户主页资料
 */
public class CuckooHomePageUserInfoFragment2 extends BaseFragment {

    @BindView(R.id.rv_view1)
    RecyclerView rv_view1;
    @BindView(R.id.rv_view2)
    RecyclerView rv_view2;
    @BindView(R.id.rv_view3)
    RecyclerView rv_view3;
    @BindView(R.id.rv_view4)
    RecyclerView rv_view4;
    @BindView(R.id.tv_shouhu)
    TextView tv_shouhu;

    public static final String TO_USER_ID = "TO_USER_ID";
    private TargetUserData2 targetUserData2;
    //    private String toUserId;
    private int page = 1;
    private int[] color_bg = {R.drawable.button_color_1, R.drawable.button_color_2,
            R.drawable.button_color_3, R.drawable.button_color_4};
    private List<String> mList1 = new ArrayList<>();
    private CommonRecyclerViewAdapter<String> mAdapter1;
    private List<String> mList2 = new ArrayList<>();
    private CommonRecyclerViewAdapter<String> mAdapter2;
    private List<TargetUserData2.DataBean.GuardianUserListBean> mList3 = new ArrayList<>();
    private CommonRecyclerViewAdapter<TargetUserData2.DataBean.GuardianUserListBean> mAdapter3;
    private List<TargetUserData2.DataBean.GiftBean> mList4 = new ArrayList<>();
    private CommonRecyclerViewAdapter<TargetUserData2.DataBean.GiftBean> mAdapter4;

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_cuckoo_home_page_user_info2, null);
    }

    public static CuckooHomePageUserInfoFragment2 getInstance(TargetUserData2 targetUserData2) {
        CuckooHomePageUserInfoFragment2 cuckooHomePageUserInfoFragment = new CuckooHomePageUserInfoFragment2();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", targetUserData2);
        cuckooHomePageUserInfoFragment.setArguments(bundle);
        return cuckooHomePageUserInfoFragment;
    }


    @Override
    protected void initView(View view) {
        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        //设置每一个item间距
        rv_view1.addItemDecoration(new SpaceItemDecoration(dp2px(1)));
        rv_view1.setLayoutManager(flowLayoutManager);

        FlowLayoutManager flowLayoutManager2 = new FlowLayoutManager();
        rv_view2.addItemDecoration(new SpaceItemDecoration(dp2px(1)));
        rv_view2.setLayoutManager(flowLayoutManager2);

        rv_view3.setLayoutManager(new GridLayoutManager(getContext(), 5));

        rv_view4.setLayoutManager(new GridLayoutManager(getContext(), 5));
    }

    @Override
    protected void initDate(View view) {
//        toUserId = getArguments().getString(TO_USER_ID);
        targetUserData2 = (TargetUserData2) getArguments().getSerializable("data");
//        requestGetInfo();
        initRecyclerView1();
        initRecyclerView2();
        initRecyclerView3();
        initRecyclerView4();
    }

    private void initRecyclerView4() {
        mList4.clear();
        if (null != targetUserData2.getData()) {
            for (int i = 0; i < targetUserData2.getData().getGift().size(); i++) {
                mList4.add(targetUserData2.getData().getGift().get(i));
            }
        }

        mAdapter4 = new CommonRecyclerViewAdapter<TargetUserData2.DataBean.GiftBean>(getContext(), mList4) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, TargetUserData2.DataBean.GiftBean entity, int position) {
                GlideImgManager.glideLoader(getContext(),
                        BuildConfig.SERVER_URL + entity.getImg(), (ImageView) holder.getView(R.id.iv_image), 0);
                holder.setText(R.id.tv_number, "x" + entity.getGift_count());
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_text4;
            }
        };

        rv_view4.setAdapter(mAdapter4);
    }

    private void initRecyclerView3() {
        mList3.clear();
        if (null != targetUserData2.getData()) {
            for (int i = 0; i < targetUserData2.getData().getGuardian_user_list().size(); i++) {
                mList3.add(targetUserData2.getData().getGuardian_user_list().get(i));
            }
            if (mList3.size() > 0) {
                tv_shouhu.setText("她的守护(" + mList3.size() + "人)");
            } else {
                tv_shouhu.setText("她的守护");
            }
        }

        mAdapter3 = new CommonRecyclerViewAdapter<TargetUserData2.DataBean.GuardianUserListBean>(getContext(), mList3) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, TargetUserData2.DataBean.GuardianUserListBean entity, int position) {
                GlideImgManager.glideLoader(getContext(),
                        BuildConfig.SERVER_URL + entity.getAvatar(), (ImageView) holder.getView(R.id.iv_head), 0);
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_text3;
            }
        };

        rv_view3.setAdapter(mAdapter3);
    }

    private void initRecyclerView2() {
        mList2.clear();
//        if (null != targetUserData2.getData()) {
//            for (int i = 0; i < targetUserData2.getData().getRemarks().size(); i++) {
//                mList2.add(targetUserData2.getData().getRemarks().get(i));
//            }
//        }

        mAdapter2 = new CommonRecyclerViewAdapter<String>(getContext(), mList2) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, String entity, int position) {
                TextView tvText = holder.getView(R.id.tvText);
                tvText.setText(entity);
                final long l = System.currentTimeMillis();
                final int i = (int) (l % 3);
                tvText.setBackgroundResource(color_bg[i]);
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_text1;
            }
        };

        rv_view2.setAdapter(mAdapter2);
    }

    private void initRecyclerView1() {
        mList1.clear();
        if (null != targetUserData2.getData()) {
            for (int i = 0; i < targetUserData2.getData().getInformation().size(); i++) {
                mList1.add(targetUserData2.getData().getInformation().get(i));
            }
        }

        mAdapter1 = new CommonRecyclerViewAdapter<String>(getContext(), mList1) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, String entity, int position) {
                holder.setText(R.id.tvText, entity);
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_text2;
            }
        };

        rv_view1.setAdapter(mAdapter1);
    }

    @Override
    protected void initSet(View view) {

    }

    @Override
    protected void initDisplayData(View view) {

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);

    }

    private int dp2px(float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }
}
