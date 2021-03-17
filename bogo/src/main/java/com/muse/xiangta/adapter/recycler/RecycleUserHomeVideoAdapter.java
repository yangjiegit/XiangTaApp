package com.muse.xiangta.adapter.recycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.muse.xiangta.R;
import com.muse.xiangta.json.jsonmodle.VideoModel;
import com.muse.xiangta.utils.StringUtils;
import com.muse.xiangta.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by weipeng on 2018/2/9.
 */

public class RecycleUserHomeVideoAdapter extends BaseQuickAdapter<VideoModel,BaseViewHolder> {

    private Context context;//上下文

    public RecycleUserHomeVideoAdapter(Context context, @Nullable List<VideoModel> data) {
        super(R.layout.item_user_home_video,data);

        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoModel item) {

        //加载是视频封面图片
        Utils.loadHttpImg(context,Utils.getCompleteImgUrl(item.getImg()), (ImageView) helper.getView(R.id.item_iv_video_icon));
        helper.setVisible(R.id.tv_pay, StringUtils.toInt(item.getStatus()) == 2);
    }
}
