package com.muse.xiangta.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CommonRecyclerViewAdapter;
import com.muse.xiangta.adapter.CommonRecyclerViewHolder;
import com.muse.xiangta.base.BaseFragment;
import com.muse.xiangta.modle.MyGradeBean;

import java.util.ArrayList;
import java.util.List;

public class MyGradeFragment extends BaseFragment {

    private RecyclerView rv_view;

    private int type;

    private List<MyGradeBean> mMyGradeList = new ArrayList<>();
    private CommonRecyclerViewAdapter<MyGradeBean> mAdapter;

    private String[] name1 = {"收礼物", "语音视频呼叫", "语音速配", "私信聊天", "头像被赞", "动态被赞或评论",
            "交友宣言被赞", "照片被赞"};
    private String[] name2 = {"收礼物", "守护TA", "语音视频呼叫", "语音速配", "私信聊天"};
    private int[] image1 = {R.mipmap.icon_slw, R.mipmap.icon_dh, R.mipmap.icon_supei, R.mipmap.icon_sixin,
            R.mipmap.icon_zan, R.mipmap.icon_pinglun, R.mipmap.icon_jiaoyou, R.mipmap.icon_zhaop};
    private int[] image2 = {R.mipmap.icon_slw, R.mipmap.icon_jiaoyou, R.mipmap.icon_dh, R.mipmap.icon_supei,
            R.mipmap.icon_pinglun};

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_my_grade, null);
    }

    @Override
    protected void initView(View view) {
        rv_view = view.findViewById(R.id.rv_view);

        rv_view.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }

    public static MyGradeFragment getInstance(int type) {
        MyGradeFragment fragment = new MyGradeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initDate(View view) {
        type = getArguments().getInt("type");

        initRecyclerView();
    }


    private void initRecyclerView() {
        if (type == 1) {
            for (int i = 0; i < name1.length; i++) {
                MyGradeBean myGradeBean = new MyGradeBean();
                myGradeBean.setName(name1[i]);
                myGradeBean.setImage(image1[i]);
                mMyGradeList.add(myGradeBean);
            }
        } else {
            for (int i = 0; i < name2.length; i++) {
                MyGradeBean myGradeBean = new MyGradeBean();
                myGradeBean.setName(name2[i]);
                myGradeBean.setImage(image2[i]);
                mMyGradeList.add(myGradeBean);
            }
        }

        mAdapter = new CommonRecyclerViewAdapter<MyGradeBean>(getContext(), mMyGradeList) {
            @Override
            public void convert(CommonRecyclerViewHolder holder, MyGradeBean entity, int position) {
                ImageView iv_image = holder.getView(R.id.iv_image);
                iv_image.setImageResource(entity.getImage());
                TextView tv_name = holder.getView(R.id.tv_name);
                tv_name.setText(entity.getName());
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item_my_grade;
            }
        };

        rv_view.setAdapter(mAdapter);
    }

    @Override
    protected void initSet(View view) {

    }

    @Override
    protected void initDisplayData(View view) {

    }
}
