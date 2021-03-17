package com.muse.xiangta.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.muse.xiangta.BuildConfig;
import com.muse.xiangta.CuckooApplication;
import com.muse.xiangta.R;
import com.muse.xiangta.modle.UserModel;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class CuckooGuardianOrderListAdapter extends BaseQuickAdapter<UserModel, BaseViewHolder> {
    public CuckooGuardianOrderListAdapter(@Nullable List<UserModel> data) {
        super(R.layout.item_guardian_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserModel item) {
//        Utils.loadHttpImg(item.getAvatar(), (ImageView) helper.getView(R.id.iv_avatar));
//        CuckooApplication.getInstances()
        GlideImgManager.glideLoader(CuckooApplication.getInstances(), BuildConfig.SERVER_URL + item.getAvatar(), (ImageView) helper.getView(R.id.iv_avatar), 1);

    }
}
