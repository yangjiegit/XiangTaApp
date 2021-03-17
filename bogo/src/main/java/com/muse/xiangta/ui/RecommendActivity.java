package com.muse.xiangta.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.muse.xiangta.R;
import com.muse.xiangta.adapter.RecommendAdapter;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.inter.JsonCallback;
import com.muse.xiangta.json.JsonNormal;
import com.muse.xiangta.json.RecommendBean;
import com.muse.xiangta.modle.UserModel;
import com.muse.xiangta.ui.common.LoginUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 为您推荐 lf
 */
public class RecommendActivity extends BaseActivity {

    private RecyclerView recommendRv;
    private Button commendBtn;
    private RecommendAdapter recommendAdaper;
    private UserModel userModel;

    @Override
    protected Context getNowContext() {
        return RecommendActivity.this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_recommend;
    }

    @Override
    protected void initView() {

        getTopBar().setTitle("为您推荐");
        userModel = getIntent().getParcelableExtra("userModel");

        recommendRv = findViewById(R.id.rv_content_list);
        commendBtn = findViewById(R.id.commend_btn);

        LinearLayoutManager layoutManage = new LinearLayoutManager(this);
        recommendRv.setLayoutManager(layoutManage);

        commendBtn.setOnClickListener(this);
    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        requestUserData();
    }


    private void requestUserData() {
        Api.getRecommedListData(
                userModel.getId(),
                userModel.getToken(),
                new JsonCallback() {

                    @Override
                    public Context getContextToJson() {
                        return getNowContext();
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        RecommendBean requestObj = (RecommendBean) RecommendBean.getJsonObj(s, RecommendBean.class);

                        if (requestObj.getCode() == 1) {
                            List<RecommendBean.RecommendChildBean> list = requestObj.getList();

                            recommendAdaper = new RecommendAdapter(RecommendActivity.this, list);
                            recommendRv.setAdapter(recommendAdaper);

                        } else {
                            showToastMsg("获取当前视频主播信息:" + requestObj.getMsg());
                        }
                    }
                }
        );
    }

    @OnClick({R.id.btn_jump})
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_jump:

                LoginUtils.doLogin(getNowContext(), userModel);
                break;
            case R.id.commend_btn:
                String commengStr = "";
                List<String> liveerList = new ArrayList<>();
                List<RecommendBean.RecommendChildBean> dataList = recommendAdaper.getDataList();
                for (int i = 0; i < dataList.size(); i++) {
                    if (dataList.get(i).isChecked()) {
                        liveerList.add(dataList.get(i).getId() + "");

                    }
                }

                for (int i = 0; i < liveerList.size(); i++) {
                    if (i == liveerList.size() - 1) {
                        commengStr += liveerList.get(i);
                    } else {
                        commengStr += liveerList.get(i) + "&";
                    }
                }

                comment(commengStr);
                break;

            default:
                break;
        }

    }

    private void comment(String dataList) {

        if (TextUtils.isEmpty(dataList)) {
            LoginUtils.doLogin(getNowContext(), userModel);
            return;
        }

        Api.getCommendRecommedListData(
                dataList,
                userModel.getId(),
                userModel.getToken(),
                new JsonCallback() {

                    @Override
                    public Context getContextToJson() {
                        return getNowContext();
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        JsonNormal requestObj = JsonNormal.getJsonObj(s);
                        if (requestObj.getCode() == 1) {
                            showToastMsg("一键关注成功");
                        } else {
                            showToastMsg("关注出现了点问题");
                        }
                        LoginUtils.doLogin(getNowContext(), userModel);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                }
        );
    }

    @Override
    protected void initPlayerDisplayData() {

    }

    @Override
    protected boolean hasTopBar() {
        return false;
    }
}
