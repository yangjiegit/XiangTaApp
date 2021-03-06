package com.muse.xiangta.fragment;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muse.xiangta.R;
import com.muse.xiangta.adapter.recycler.RecyclerRecommendAdapter;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseFragment;
import com.muse.xiangta.json.JsonRequestBase;
import com.muse.xiangta.json.JsonRequestDoGetStarEmceeList;
import com.muse.xiangta.json.jsonmodle.TargetUserData;
import com.muse.xiangta.ui.common.Common;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 星级
 */
public class StarLevelListFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {

    public static final String LEVEL_ID = "LEVEL_ID";

    private RecyclerView mRvContentList;
    private SwipeRefreshLayout mSwRefresh;

    private String levelId;
    private List<TargetUserData> list = new ArrayList<>();
    private RecyclerRecommendAdapter recommendAdapter;
    private int page = 1;

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_level_name, container,false);
    }

    @Override
    protected void initView(View view) {
        mRvContentList = view.findViewById(R.id.rv_content_list);
        mSwRefresh = view.findViewById(R.id.sw_refresh);
        mSwRefresh.setOnRefreshListener(this);

        mRvContentList.setLayoutManager(new GridLayoutManager(getContext(),2));

        recommendAdapter = new RecyclerRecommendAdapter(getContext(),list);
        mRvContentList.setAdapter(recommendAdapter);
        recommendAdapter.setOnLoadMoreListener(this,mRvContentList);
        recommendAdapter.disableLoadMoreIfNotFullPage();
        //设置适配器控件点击监听
        recommendAdapter.setOnItemClickListener(this);

        levelId = getArguments().getString(LEVEL_ID);
    }

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
    public void fetchData() {
        requestData();
    }

    private void requestData() {

        Api.doRequestGetStarEmceeList(levelId,page,new StringCallback(){

            @Override
            public void onSuccess(String s, Call call, Response response) {

                mSwRefresh.setRefreshing(false);
                JsonRequestDoGetStarEmceeList requestObj = (JsonRequestDoGetStarEmceeList) JsonRequestBase.getJsonObj(s,JsonRequestDoGetStarEmceeList.class);
                if (requestObj.getCode() == 1){
                    if(page == 1){
                        list.clear();
                    }
                    list.addAll(requestObj.getData());

                    if(requestObj.getData().size() == 0){
                        recommendAdapter.loadMoreEnd();
                    }else{
                        recommendAdapter.loadMoreComplete();
                    }
                    recommendAdapter.notifyDataSetChanged();
                }else{
                    recommendAdapter.loadMoreEnd();
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
    public void onRefresh() {
        page = 1;
        requestData();
    }

    @Override
    public void onLoadMoreRequested() {
        page ++;
        requestData();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        Common.jumpUserPage(getContext(),list.get(position).getId());
    }
}
