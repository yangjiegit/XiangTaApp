package com.muse.xiangta.adapter.recycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.muse.xiangta.BuildConfig;
import com.muse.xiangta.R;
import com.muse.xiangta.json.jsonmodle.TargetUserData;
import com.muse.xiangta.json.jsonmodle.TargetUserData2;
import com.muse.xiangta.manage.AppConfig;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by weipeng on 2018/2/9.
 */

public class RecycleUserHomeGiftAdapter extends BaseQuickAdapter<TargetUserData2.DataBean.GiftBean, BaseViewHolder> {

    private Context context;//上下文

    public RecycleUserHomeGiftAdapter(Context context, @Nullable List<TargetUserData2.DataBean.GiftBean> data) {
        super(R.layout.item_user_home_gift, data);

        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TargetUserData2.DataBean.GiftBean item) {
        //加载礼物图片
        GlideImgManager.glideLoader(context, BuildConfig.SERVER_URL + item.getImg(), (ImageView) helper.getView(R.id.item_iv_gift_icon), 1);

    }
}
