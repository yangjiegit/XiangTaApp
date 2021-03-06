package com.muse.xiangta.ui;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.ToastUtils;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.CuckooVideoCallListAdapter;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.dialog.DoCallVideoWaitDialog;
import com.muse.xiangta.event.CuckooCallVideoEvent;
import com.muse.xiangta.json.JsonRequestBase;
import com.muse.xiangta.json.JsonRequestGetVideoCallListModel;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.modle.CuckooVideoCallListModel;
import com.muse.xiangta.modle.JsonDoGetVideoCallInfoModel;
import com.muse.xiangta.modle.custommsg.CustomMsgVideoCall;
import com.muse.xiangta.utils.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

public class CuckooVideoCallListActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.rv_content_list)
    RecyclerView rv_content_list;

    private List<CuckooVideoCallListModel> listModelList = new ArrayList<>();
    private CuckooVideoCallListAdapter cuckooVideoCallListAdapter;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_cuckoo_video_call_list;
    }

    @Override
    protected void initView() {

        rv_content_list.setLayoutManager(new LinearLayoutManager(this));
        cuckooVideoCallListAdapter = new CuckooVideoCallListAdapter(listModelList);
        rv_content_list.setAdapter(cuckooVideoCallListAdapter);
        cuckooVideoCallListAdapter.setOnItemClickListener(this);

    }

    @Override
    protected void initSet() {
        getTopBar().setVisibility(View.VISIBLE);
        getTopBar().setTitle("????????????");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initPlayerDisplayData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        requestGetList();
    }

    private void requestGetList() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Api.doRequestGetVideoCallList(SaveData.getInstance().getId(),SaveData.getInstance().getToken(),new StringCallback(){

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        JsonRequestGetVideoCallListModel data = (JsonRequestGetVideoCallListModel) JsonRequestBase.getJsonObj(s,JsonRequestGetVideoCallListModel.class);
                        if(StringUtils.toInt(data.getCode()) == 1){
                            listModelList.clear();
                            listModelList.addAll(data.getList());
                            cuckooVideoCallListAdapter.notifyDataSetChanged();
                        }else{
                            ToastUtils.showLong(data.getMsg());
                        }
                    }
                });
            }
        },500);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        showLoadingDialog("??????????????????...");
        Api.doGetVideoCallInfo(SaveData.getInstance().getId(),SaveData.getInstance().getToken(),listModelList.get(position).getUser_id(),new StringCallback(){

            @Override
            public void onSuccess(String s, Call call, Response response) {

                hideLoadingDialog();
                JsonDoGetVideoCallInfoModel data = (JsonDoGetVideoCallInfoModel) JsonRequestBase.getJsonObj(s,JsonDoGetVideoCallInfoModel.class);
                if(StringUtils.toInt(data.getCode()) == 1){

                    CustomMsgVideoCall videoCall = JSON.parseObject(data.getExt(), CustomMsgVideoCall.class);
                    DoCallVideoWaitDialog dialog = new DoCallVideoWaitDialog(CuckooVideoCallListActivity.this, videoCall.getSender(), videoCall.getChannel(), videoCall.getIs_free());
                    dialog.show();
                    dialog.setOnDialogClick(new DoCallVideoWaitDialog.OnDialogClick() {
                        @Override
                        public void onLeftClick() {
                            //requestGetList();
                        }

                        @Override
                        public void onRightClick() {
                            //finish();
                        }
                    });
                }else{
                    ToastUtils.showLong(data.getMsg());
                    requestGetList();
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                hideLoadingDialog();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventClickCallVideo(CuckooCallVideoEvent var1) {
        requestGetList();
    }

}
