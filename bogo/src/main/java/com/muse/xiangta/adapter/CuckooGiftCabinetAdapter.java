package com.muse.xiangta.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.muse.xiangta.R;
import com.muse.xiangta.modle.CuckooGiftCabinetModel;
import com.muse.xiangta.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class CuckooGiftCabinetAdapter extends BaseQuickAdapter<CuckooGiftCabinetModel, BaseViewHolder> {
    public CuckooGiftCabinetAdapter(@Nullable List<CuckooGiftCabinetModel> data) {
        super(R.layout.item_gift_list_cabinet,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CuckooGiftCabinetModel item) {

        Utils.loadHttpImg(item.getImg(), (ImageView) helper.getView(R.id.item_iv_gift_icon));
        helper.setText(R.id.item_tv_name, item.getName());
        helper.setText(R.id.item_tv_count, "X" + item.getGift_count());
    }
}
