package com.muse.xiangta.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.muse.xiangta.R;
import com.muse.xiangta.modle.GuildUserModel;
import com.muse.xiangta.utils.Utils;
import com.muse.xiangta.widget.BGLevelTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class CuckooGuildUserManageAdapter extends BaseQuickAdapter<GuildUserModel, BaseViewHolder> {
    public CuckooGuildUserManageAdapter(@Nullable List<GuildUserModel> data) {
        super(R.layout.item_guild_user_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GuildUserModel item) {
        helper.setText(R.id.item_tv_name, item.getUser_nickname());
        Utils.loadHttpImg(item.getAvatar(), (ImageView) helper.getView(R.id.item_iv_avatar));
        BGLevelTextView levelTextView = helper.getView(R.id.iv_level);
        levelTextView.setLevelInfo(2, String.valueOf(item.getLevel()));

        helper.addOnClickListener(R.id.item_btn_agree);
        helper.addOnClickListener(R.id.item_btn_refuse);
        helper.setText(R.id.item_tv_income_total,item.getIncome_total());
    }
}
