package com.muse.xiangta.adapter.recycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.muse.xiangta.R;
import com.muse.xiangta.json.jsonmodle.TargetUserData;
import com.muse.xiangta.json.jsonmodle.TargetUserData2;
import com.muse.xiangta.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by weipeng on 2018/2/9.
 */

public class RecycleUserHomePhotoAdapter extends BaseQuickAdapter<TargetUserData2.DataBean,BaseViewHolder> {

    private Context context;//上下文

    public RecycleUserHomePhotoAdapter(Context context, @Nullable List<TargetUserData2.DataBean> data) {
        super(R.layout.item_user_home_photo,data);

        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TargetUserData2.DataBean item) {

        //加载模糊图片
//        Utils.loadHttpImg(context,Utils.getCompleteImgUrl(item.getImg()), (ImageView) helper.getView(R.id.item_iv_photo_icon));
    }
}
