package com.muse.xiangta.ui;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.muse.xiangta.R;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.fragment.MyMessageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyMessageActivity extends BaseActivity {

    @BindView(R.id.ll_miyou)
    LinearLayout ll_miyou;
    @BindView(R.id.ll_guanzhu)
    LinearLayout ll_guanzhu;
    @BindView(R.id.ll_fensi)
    LinearLayout ll_fensi;
    @BindView(R.id.ll_haoyou)
    LinearLayout ll_haoyou;

    @BindView(R.id.tv_miyou)
    TextView tv_miyou;
    @BindView(R.id.tv_guanzhu)
    TextView tv_guanzhu;
    @BindView(R.id.tv_fensi)
    TextView tv_fensi;
    @BindView(R.id.tv_haoyou)
    TextView tv_haoyou;

    @BindView(R.id.view_miyou)
    View view_miyou;
    @BindView(R.id.view_guanzhu)
    View view_guanzhu;
    @BindView(R.id.view_fensi)
    View view_fensi;
    @BindView(R.id.view_haoyou)
    View view_haoyou;

    private int page;
    private int type;

    private List<TextView> mTextViewList = new ArrayList<>();
    private List<View> mViewList = new ArrayList<>();

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_message;
    }

    @Override
    protected void initView() {
        ll_miyou.setOnClickListener(this);
        ll_fensi.setOnClickListener(this);
        ll_guanzhu.setOnClickListener(this);
        ll_haoyou.setOnClickListener(this);
    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        mTextViewList.clear();
        mTextViewList.add(tv_miyou);
        mTextViewList.add(tv_fensi);
        mTextViewList.add(tv_guanzhu);
        mTextViewList.add(tv_haoyou);
        mViewList.clear();
        mViewList.add(view_miyou);
        mViewList.add(view_fensi);
        mViewList.add(view_guanzhu);
        mViewList.add(view_haoyou);

        page = 1;
        type = getIntent().getIntExtra("type", 0);

        switch (type) {
            case 1:
                setTextViewColor(type);
                break;
            case 2:
                setTextViewColor(type);
                break;
            case 3:
                setTextViewColor(type);
                break;
            case 4:
                setTextViewColor(type);
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_layout,
                MyMessageFragment.getInstance(type, page)).commit();
    }

    private void setTextViewColor(int type) {
        for (int i = 0; i < mTextViewList.size(); i++) {
            if (i == (type - 1)) {
                mTextViewList.get((type - 1)).setTextColor(getResources().getColor(R.color.message_check_true));
                mViewList.get((type - 1)).setVisibility(View.VISIBLE);
            } else {
                mTextViewList.get(i).setTextColor(getResources().getColor(R.color.black));
                mViewList.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    @OnClick(R.id.iv_back)
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_miyou:
                type = 1;
                page = 1;
                setTextViewColor(type);
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_layout,
                        MyMessageFragment.getInstance(type, page)).commit();
                break;
            case R.id.ll_fensi:
                type = 2;
                page = 1;
                setTextViewColor(type);
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_layout,
                        MyMessageFragment.getInstance(type, page)).commit();
                break;
            case R.id.ll_guanzhu:
                type = 3;
                page = 1;
                setTextViewColor(type);
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_layout,
                        MyMessageFragment.getInstance(type, page)).commit();
                break;
            case R.id.ll_haoyou:
                type = 4;
                page = 1;
                setTextViewColor(type);
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_layout,
                        MyMessageFragment.getInstance(type, page)).commit();
                break;
        }
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
