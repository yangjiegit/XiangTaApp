package com.muse.xiangta.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.fragment.FamilyFragment;
import com.muse.xiangta.json.FamilyBean;
import com.muse.xiangta.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class FamilyActivity extends BaseActivity {

    @BindView(R.id.fl_layout)
    FrameLayout fl_layout;
    @BindView(R.id.ll_tongcheng)
    LinearLayout ll_tongcheng;
    @BindView(R.id.ll_fujin)
    LinearLayout ll_fujin;
    @BindView(R.id.ll_remen)
    LinearLayout ll_remen;
    @BindView(R.id.ll_ri)
    LinearLayout ll_ri;
    @BindView(R.id.ll_zong)
    LinearLayout ll_zong;
    @BindView(R.id.ll_new)
    LinearLayout ll_new;

    @BindView(R.id.iv_add_family)
    ImageView iv_add_family;

    @BindView(R.id.tv_tongcheng)
    TextView tv_tongcheng;
    @BindView(R.id.tv_fujin)
    TextView tv_fujin;
    @BindView(R.id.tv_remen)
    TextView tv_remen;
    @BindView(R.id.tv_ri)
    TextView tv_ri;
    @BindView(R.id.tv_zong)
    TextView tv_zong;
    @BindView(R.id.tv_new)
    TextView tv_new;

    @BindView(R.id.view_tongcheng)
    View view_tongcheng;
    @BindView(R.id.view_fujin)
    View view_fujin;
    @BindView(R.id.view_remen)
    View view_remen;
    @BindView(R.id.view_ri)
    View view_ri;
    @BindView(R.id.view_zong)
    View view_zong;
    @BindView(R.id.view_new)
    View view_new;

    private int type, page, limit;

    private List<TextView> mTextViewList = new ArrayList<>();
    private List<View> mViewList = new ArrayList<>();

    private List<FamilyBean.DataBean> mList = new ArrayList<>();

    @Override

    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_family;
    }

    @Override
    protected void initView() {
        mTextViewList.clear();
        mViewList.clear();
        mTextViewList.add(tv_tongcheng);
        mTextViewList.add(tv_fujin);
        mTextViewList.add(tv_remen);
        mTextViewList.add(tv_ri);
        mTextViewList.add(tv_zong);
        mTextViewList.add(tv_new);

        mViewList.add(view_tongcheng);
        mViewList.add(view_fujin);
        mViewList.add(view_remen);
        mViewList.add(view_ri);
        mViewList.add(view_zong);
        mViewList.add(view_new);

        iv_add_family.setOnClickListener(this);
        ll_tongcheng.setOnClickListener(this);
        ll_fujin.setOnClickListener(this);
        ll_remen.setOnClickListener(this);
        ll_ri.setOnClickListener(this);
        ll_zong.setOnClickListener(this);
        ll_new.setOnClickListener(this);
    }

    @OnClick(R.id.iv_back)
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_add_family:
                startActivityForResult(new Intent(this, AddFramilyActivity.class)
                        , 20);
                break;
            case R.id.ll_tongcheng:
                setColor(0);
                type = 1;
                page = 1;
                getFamilyListData(type, page);
                break;
            case R.id.ll_fujin:
                setColor(1);
                type = 2;
                page = 1;
                getFamilyListData(type, page);
                break;
            case R.id.ll_remen:
                setColor(2);
                type = 3;
                page = 1;
                getFamilyListData(type, page);
                break;
            case R.id.ll_ri:
                setColor(3);
                type = 4;
                page = 1;
                getFamilyListData(type, page);
                break;
            case R.id.ll_zong:
                setColor(4);
                type = 5;
                page = 1;
                getFamilyListData(type, page);
                break;
            case R.id.ll_new:
                setColor(5);
                type = 6;
                page = 1;
                getFamilyListData(type, page);
                break;
        }
    }

    public void setColor(int position) {
        for (int i = 0; i < mTextViewList.size(); i++) {
            if (position == i) {
                mTextViewList.get(i).setTextColor(getResources().getColor(R.color.message_check_true));
                mViewList.get(i).setVisibility(View.VISIBLE);
            } else {
                mTextViewList.get(i).setTextColor(getResources().getColor(R.color.black));
                mViewList.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        type = 1;
        page = 1;
        limit = 10;
        getFamilyListData(type, page);
    }

    private void getFamilyListData(int type, final int page) {
        Api.familyList(uId, uToken, String.valueOf(type),
                String.valueOf(page), String.valueOf(limit), new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d("ret", "joker    " + s);
                        if (!StringUtils.isEmpty(s)) {
                            FamilyBean familyBean = new Gson().fromJson(s, FamilyBean.class);
                            if (page == 1) {
                                mList.clear();
                                if (null != familyBean &&
                                        null != familyBean.getData() &&
                                        familyBean.getData().size() > 0) {
                                    for (int i = 0; i < familyBean.getData().size(); i++) {
                                        mList.add(familyBean.getData().get(i));
                                    }
                                    getSupportFragmentManager().beginTransaction().
                                            replace(R.id.fl_layout,
                                                    FamilyFragment.getInstance(mList)).commit();
                                } else {
                                    mList.clear();
                                    getSupportFragmentManager().beginTransaction().
                                            replace(R.id.fl_layout,
                                                    FamilyFragment.getInstance(mList)).commit();
                                }
                            }
                        } else {
                            mList.clear();
                            getSupportFragmentManager().beginTransaction().
                                    replace(R.id.fl_layout,
                                            FamilyFragment.getInstance(mList)).commit();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        showToastMsg(e.toString());
                    }
                });
    }

    @Override
    protected void initPlayerDisplayData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 30) {
            page = 1;
            getFamilyListData(type, page);
        }
    }
}
