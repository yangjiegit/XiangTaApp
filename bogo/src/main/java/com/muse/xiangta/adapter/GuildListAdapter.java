package com.muse.xiangta.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.muse.xiangta.R;
import com.muse.xiangta.modle.GuildModel;
import com.muse.xiangta.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class GuildListAdapter extends BaseQuickAdapter<GuildModel, BaseViewHolder> {
    public GuildListAdapter(@Nullable List<GuildModel> data) {
        super(R.layout.item_guild_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GuildModel item) {
        helper.setText(R.id.item_tv_name, item.getName());
        helper.setText(R.id.item_tv_num, item.getNum() + "人");
        Utils.loadHttpImg(item.getLogo(), (ImageView) helper.getView(R.id.iv_logo));
    }
}
