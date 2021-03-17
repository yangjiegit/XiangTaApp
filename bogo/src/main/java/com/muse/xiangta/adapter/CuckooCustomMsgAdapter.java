package com.muse.xiangta.adapter;

import android.support.annotation.Nullable;

import com.muse.xiangta.R;
import com.muse.xiangta.modle.CustomMsgModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class CuckooCustomMsgAdapter extends BaseQuickAdapter<CustomMsgModel, BaseViewHolder> {
    public CuckooCustomMsgAdapter(@Nullable List<CustomMsgModel> data) {
        super(R.layout.item_custom_msg,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomMsgModel item) {
        helper.setText(R.id.tv_custom_msg,item.getMsg());
    }
}
