package com.muse.xiangta.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseFragment;
import com.muse.xiangta.modle.HintBean;
import com.muse.xiangta.modle.VideoChargeBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

public class VideoChargeSetFragment extends BaseFragment {


    @BindView(R.id.list)
    RecyclerView list;

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.video_charge, container, false);
    }

    @Override
    protected void initView(View view) {
        list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        Api.getFee(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                log("视频收费设置:" + s);
                VideoChargeBean bean = new Gson().fromJson(s, VideoChargeBean.class);
                show(bean.getData());
            }
        });
    }


    int nowSel = -1;
    BaseQuickAdapter adapter;

    public void show(final List<VideoChargeBean.DataBean> l) {
        list.setAdapter(adapter = new BaseQuickAdapter<VideoChargeBean.DataBean, BaseViewHolder>(R.layout.item_video_chare, l) {
            @Override
            protected void convert(BaseViewHolder helper, VideoChargeBean.DataBean item) {
                helper.setText(R.id.left, item.getName())
                        .setText(R.id.center, item.getCoin() + "钻石/分钟");
                View check = helper.getView(R.id.right);
                if (nowSel == -1) {
                    if ("1".equals(item.getType())) {
                        check.setBackgroundResource(R.drawable.charge_sel);
                        nowSel = helper.getAdapterPosition();
                    } else {
                        check.setBackgroundResource(R.drawable.charge_un);
                    }
                } else {
                    if (helper.getAdapterPosition() == nowSel)
                        check.setBackgroundResource(R.drawable.charge_sel);
                    else check.setBackgroundResource(R.drawable.charge_un);
                }
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                nowSel = position;
                adapter.notifyDataSetChanged();
                set(l.get(position).getId());
            }
        });
    }

    private void set(String id) {
        Api.setFee(id, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                HintBean hint = new Gson().fromJson(s, HintBean.class);
                ToastUtils.showShort(hint.getMsg());
            }
        });
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
}
