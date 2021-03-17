package com.muse.xiangta.fragment;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.muse.xiangta.adapter.recycler.RecyclerNearAdapter;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseListFragment;
import com.muse.xiangta.inter.JsonCallback;
import com.muse.xiangta.json.JsonRequestBase;
import com.muse.xiangta.json.JsonRequestsPeople;
import com.muse.xiangta.json.jsonmodle.TargetUserData;
import com.muse.xiangta.ui.common.Common;
import com.chad.library.adapter.base.BaseQuickAdapter;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 同城
 */
public class SameCityFragment extends BaseListFragment<TargetUserData> {
    @Override
    protected void initDate(View view) {

    }

    @Override
    public void fetchData() {

        //加载数据源
        requestGetData(false);
    }

    @Override
    protected void initSet(View view) {

    }

    @Override
    protected void initDisplayData(View view) {

    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManage() {
        return new GridLayoutManager(getContext(), 2);
    }

    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        return new RecyclerNearAdapter(getContext(), dataList);
    }

    @Override
    protected void requestGetData(boolean isCache) {
        Api.getNearPeoplePageList(
                uId,
                uToken,
                page,
                new JsonCallback() {
                    @Override
                    public Context getContextToJson() {
                        return getContext();
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("samecity",s);
                        JsonRequestsPeople requestObj = (JsonRequestsPeople) JsonRequestBase.getJsonObj(s, JsonRequestsPeople.class);
                        if (requestObj.getCode() == 1) {
                            onLoadDataResult(requestObj.getData());
                        } else {
                            onLoadDataError();
                            showToastMsg(getContext(), requestObj.getMsg());
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        onLoadDataError();
                        Log.e("samecity",e.toString());
                    }
                }
        );
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        Common.jumpUserPage(getContext(), dataList.get(position).getId());
    }
}
