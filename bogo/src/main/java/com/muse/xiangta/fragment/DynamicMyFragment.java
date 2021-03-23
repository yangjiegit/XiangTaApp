package com.muse.xiangta.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.DynamicAdapter;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseListFragment;
import com.muse.xiangta.event.RefreshMessageEvent;
import com.muse.xiangta.json.JsonRequestBase;
import com.muse.xiangta.json.JsonRequestDoGetDynamicList;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.modle.DynamicListModel;
import com.muse.xiangta.ui.DynamicDetailActivity;
import com.muse.xiangta.ui.common.Common;
import com.muse.xiangta.utils.DialogHelp;
import com.muse.xiangta.utils.StringUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 动态 关注
 */
public class DynamicMyFragment extends BaseListFragment<DynamicListModel> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    public static DynamicMyFragment getInstance(String toUserId) {
        DynamicMyFragment dynamicMyFragment = new DynamicMyFragment();
        Bundle bundle = new Bundle();
        dynamicMyFragment.setArguments(bundle);
        return dynamicMyFragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
//        toUserId = getArguments().getString(TO_USER_ID);
    }

    @Override
    protected void initSet(View view) {

    }

    @Override
    protected void initDisplayData(View view) {

    }

    @Override
    protected void initDate(View view) {
        super.initDate(view);
        page = 1;
        requestGetData(true);
    }

    @Override
    protected boolean isRegEvent() {
        return true;
    }

    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        return new DynamicAdapter(getContext(), dataList);
    }

    @Override
    protected void requestGetData(boolean isCache) {
        Api.doRequestGetMyDynamicList(SaveData.getInstance().getId(), SaveData.getInstance().getToken(), uId, page, new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {
                JsonRequestDoGetDynamicList data = (JsonRequestDoGetDynamicList) JsonRequestBase.getJsonObj(s, JsonRequestDoGetDynamicList.class);
                if (StringUtils.toInt(data.getCode()) == 1) {
                    onLoadDataResult(data.getList());
                } else {
                    mSwRefresh.setRefreshing(false);
                    ToastUtils.showLong(data.getMsg());
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
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(getContext(), DynamicDetailActivity.class);
        intent.putExtra(DynamicDetailActivity.DYNAMIC_DATA, dataList.get(position));
        startActivity(intent);
    }

    @Override
    public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
        if (view.getId() == R.id.item_iv_like_count) {
            Api.doRequestDynamicLike(SaveData.getInstance().getId(), SaveData.getInstance().getToken(), dataList.get(position).getId(), new StringCallback() {

                @Override
                public void onSuccess(String s, Call call, Response response) {

                    JsonRequestBase data = JsonRequestBase.getJsonObj(s, JsonRequestBase.class);
                    if (StringUtils.toInt(data.getCode()) == 1) {
                        if (StringUtils.toInt(dataList.get(position).getIs_like()) == 1) {
                            dataList.get(position).setIs_like("0");
                            dataList.get(position).decLikeCount(1);
                        } else {
                            dataList.get(position).setIs_like("1");
                            dataList.get(position).plusLikeCount(1);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
            });
        } else if (view.getId() == R.id.item_del) {

            DialogHelp.getConfirmDialog(getContext(), "确定要删除动态？", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    clickDelDynamic(position);
                }
            }).show();
        } else if (view.getId() == R.id.item_tv_chat) {
            Common.startPrivatePage(getContext(), dataList.get(position).getUserInfo().getId());
        } else if (view.getId() == R.id.item_iv_avatar) {
            Common.jumpUserPage(getContext(), dataList.get(position).getUserInfo().getId());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefreshMessageEvent messageEvent) {
        if (messageEvent.getMessage().equals("refresh_dynamic_list")) {
            page = 1;
            requestGetData(false);
        }
    }

    private void clickDelDynamic(final int position) {

        showLoadingDialog("正在操作...");
        Api.doRequestDelDynamic(SaveData.getInstance().getId(), SaveData.getInstance().getToken(), dataList.get(position).getId(), new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {

                hideLoadingDialog();
                JsonRequestBase data = JsonRequestBase.getJsonObj(s, JsonRequestBase.class);
                if (StringUtils.toInt(data.getCode()) == 1) {

                    dataList.remove(position);
                    baseQuickAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                hideLoadingDialog();
            }
        });
    }
}