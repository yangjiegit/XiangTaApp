package com.muse.xiangta.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseListFragment;
import com.muse.xiangta.modle.InviteMenBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

public class InviteBoyPager extends BaseListFragment<InviteMenBean.DataBean> {
    private InviteBoyHeaderView header;

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_invite_boy, container, false);
    }

    boolean isBoy = true;

    public InviteBoyPager setType(boolean boy) {
        isBoy = boy;
        return this;
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        header = new InviteBoyHeaderView(getContext());
        baseQuickAdapter.addHeaderView(header);
        baseQuickAdapter.notifyDataSetChanged();
    }

    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        return new BaseQuickAdapter<InviteMenBean.DataBean, BaseViewHolder>(R.layout.extension_item) {
            @Override
            protected void convert(BaseViewHolder helper, InviteMenBean.DataBean item) {
                helper.setText(R.id.left, item.getUser_nickname())
                        .setText(R.id.right, item.getRegistered())
                        .setText(R.id.center, item.getMoney());
            }
        };
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }


    @Override
    protected void requestGetData(boolean isCache) {
        super.requestGetData(isCache);
        Api.getRewardSex(isBoy, "", new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                InviteMenBean bean = new Gson().fromJson(s, InviteMenBean.class);
                InviteMenBean.DataBean dataBean = new InviteMenBean.DataBean();
                dataBean.setMoney("100");
                dataBean.setRegistered("60");
                dataBean.setUser_nickname("xiaomi");
                // bean.getData().add(dataBean);
                if (bean.getCode() == 1) {
                    onLoadDataResult(bean.getData());
                    if (isBoy)
                        header.boy(bean.getCount(), bean.getMoney_sum(), bean.getRegistered_sum());
                    else
                        header.girl(bean.getCount(), bean.getMoney_sum(), bean.getRegistered_sum());
                }
            }
        });
    }


}
