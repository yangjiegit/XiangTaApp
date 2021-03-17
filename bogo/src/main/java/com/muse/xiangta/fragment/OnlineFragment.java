package com.muse.xiangta.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.muse.xiangta.adapter.recycler.RecyclerRecommendAdapter;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseListFragment;
import com.muse.xiangta.json.JsonRequestsTarget;
import com.muse.xiangta.json.jsonmodle.TargetUserData;
import com.muse.xiangta.ui.common.Common;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;


/**
 * 在线
 */

public class OnlineFragment extends BaseListFragment<TargetUserData> {

    @Override
    protected void initDate(View view) {

    }

    @Override
    public void fetchData() {
        requestGetData(false);
    }

    @Override
    protected void initSet(View view) {

    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManage() {
        return new GridLayoutManager(getContext(),2);
    }

    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        return new RecyclerRecommendAdapter(getContext(),dataList);
    }

    @Override
    protected void initDisplayData(View view) {
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    protected void requestGetData(boolean isCache) {
        Api.getOnlineUserList(
                uId,
                uToken,
                page,
                new StringCallback() {

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        JsonRequestsTarget requestObj = JsonRequestsTarget.getJsonObj(s);
                        if (requestObj.getCode() == 1){
                            onLoadDataResult(requestObj.getData());
                        }else{
                            onLoadDataError();
                            showToastMsg(getContext(),requestObj.getMsg());
                        }
                    }
                }
        );
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        Common.jumpUserPage(getContext(),dataList.get(position).getId());
    }
}
