package com.muse.xiangta.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.muse.xiangta.R;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.fragment.MyMessageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GuardActivity extends BaseActivity {

    @BindView(R.id.fl_layout)
    FrameLayout fl_layout;
    @BindView(R.id.tv_wo)
    TextView tv_wo;
    @BindView(R.id.tv_ta)
    TextView tv_ta;
    @BindView(R.id.view_wo)
    View view_wo;
    @BindView(R.id.view_ta)
    View view_ta;

    private List<TextView> mTextViewList = new ArrayList<>();
    private List<View> mViewList = new ArrayList<>();

    private int page;
    private int type;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_guard;
    }

    @Override
    protected void initView() {

        page = 1;
        type = 5;
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_layout,
                MyMessageFragment.getInstance(type, page)).commit();
    }

    @Override
    protected void initSet() {

    }

    @OnClick({R.id.ll_wo, R.id.ll_ta, R.id.iv_web})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_web:
                String intStr = "17";
                String url = "http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/" + intStr + ".html";
                startActivity(new Intent(this, XieYiActivity.class)
                        .putExtra("title", "守护规则").putExtra("url", url));
                break;
            case R.id.ll_wo:
                setColor(0);
                page = 1;
                type = 5;
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_layout,
                        MyMessageFragment.getInstance(type, page)).commit();
                break;
            case R.id.ll_ta:
                setColor(1);
                page = 1;
                type = 6;
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_layout,
                        MyMessageFragment.getInstance(type, page)).commit();
                break;
        }
    }

    @Override
    protected void initData() {
        mTextViewList.clear();
        mViewList.clear();

        mTextViewList.add(tv_wo);
        mTextViewList.add(tv_ta);

        mViewList.add(view_wo);
        mViewList.add(view_ta);


    }

    public void setColor(int position) {
        for (int i = 0; i < mTextViewList.size(); i++) {
            if (i == position) {
                mTextViewList.get(i).setTextColor(getResources().getColor(R.color.message_check_true));
                mViewList.get(i).setVisibility(View.VISIBLE);
            } else {
                mTextViewList.get(i).setTextColor(getResources().getColor(R.color.black));
                mViewList.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
