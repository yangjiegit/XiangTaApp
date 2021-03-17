package com.muse.xiangta.fragment;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.muse.xiangta.adapter.recycler.RecyclerRecommendAdapter;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseListFragment;
import com.muse.xiangta.json.JsonRequestsTarget;
import com.muse.xiangta.json.jsonmodle.TargetUserData;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.ui.common.Common;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 首页关注列表
 */
public class IndexTabAttentionFragment extends BaseListFragment<TargetUserData> {

    @Override
    protected void initDate(View view) {

    }

    @Override
    protected void initSet(View view) {

    }

    @Override
    protected void initDisplayData(View view) {

    }

    @Override
    protected void requestGetData(boolean isCache) {

        Api.doRequestGetAttentionList(SaveData.getInstance().getId(),page,new StringCallback(){

            @Override
            public void onSuccess(String s, Call call, Response response) {
                mSwRefresh.setRefreshing(false);

                JsonRequestsTarget requestObj = JsonRequestsTarget.getJsonObj(s);
                if (requestObj.getCode() == 1){
                    onLoadDataResult(requestObj.getData());
                }else{
                    showToastMsg(getContext(),requestObj.getMsg());
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                mSwRefresh.setRefreshing(false);

            }
        });
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManage() {
        return new GridLayoutManager(getContext(),2);
    }

    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        RecyclerRecommendAdapter ada = new RecyclerRecommendAdapter(getContext(), dataList);
        return ada;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        Common.jumpUserPage(getContext(),dataList.get(position).getId());
    }
}
