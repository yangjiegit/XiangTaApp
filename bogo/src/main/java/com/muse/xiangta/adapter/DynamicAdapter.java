package com.muse.xiangta.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.muse.xiangta.R;
import com.muse.xiangta.audiorecord.entity.AudioEntity;
import com.muse.xiangta.audiorecord.view.CommonSoundItemView;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.modle.DynamicListModel;
import com.muse.xiangta.modle.UserModel;
import com.muse.xiangta.ui.CuckooSmallVideoPlayerActivity;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;
import com.muse.xiangta.utils.Utils;
import com.muse.xiangta.widget.BGLevelTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class DynamicAdapter extends BaseQuickAdapter<DynamicListModel, BaseViewHolder> {

    private Context mContext;


    public DynamicAdapter(Context context, @Nullable List<DynamicListModel> data) {
        super(R.layout.item_dynamic, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final DynamicListModel item) {
        TextView location = helper.getView(R.id.location);
        ImageView iv_vip = helper.getView(R.id.iv_vip);
        TextView tv_live = helper.getView(R.id.tv_live);

        tv_live.setText("lv:" + item.getUserInfo().getLevel());

        if (StringUtils.isEmpty(item.getUserInfo().getNoble())) {
            iv_vip.setVisibility(View.GONE);
        } else {
            iv_vip.setVisibility(View.VISIBLE);
            GlideImgManager.loadImage(mContext, item.getUserInfo().getNoble(), iv_vip);
        }

        if (!TextUtils.isEmpty(item.getCity())) {
            location.setVisibility(View.VISIBLE);
            location.setText(item.getCity());
        } else {
            location.setVisibility(View.GONE);
            location.setText(item.getCity());
        }

        ImageView iv_sex = helper.getView(R.id.iv_sex);

        if (item.getUserInfo().getSex() == 1) {
            tv_live.setBackgroundResource(R.drawable.bg_main_nan);
            iv_sex.setImageResource(R.mipmap.img_xingbienan2);
        } else {
            tv_live.setBackgroundResource(R.drawable.bg_main_nv);
            iv_sex.setImageResource(R.mipmap.img_xingbie1);
        }


        UserModel userInfo = item.getUserInfo();

        TextView content = helper.getView(R.id.item_tv_content);
        if (!TextUtils.isEmpty(item.getMsg_content())) {
            content.setVisibility(View.VISIBLE);
            content.setText(item.getMsg_content());
        } else {
            content.setVisibility(View.GONE);
        }

        if (userInfo != null) {
            helper.setText(R.id.item_tv_name, userInfo.getUser_nickname());
        } else {
            helper.setText(R.id.item_tv_name, "");
        }

        helper.setText(R.id.item_tv_time, item.getPublish_time());
        //回复
        helper.setText(R.id.item_tv_common_count, item.getComment_count());
        //点赞
        helper.setText(R.id.item_tv_like_count, item.getLike_count());

        RecyclerView rv = helper.getView(R.id.rv_photo_list);
        RelativeLayout video = helper.getView(R.id.videoplayer);
        ImageView videoPlayerIv = helper.getView(R.id.videoplayer_iv);

        //视频动态
        if (!TextUtils.isEmpty(item.getVideo_url())) {

            rv.setVisibility(View.GONE);
            video.setVisibility(View.VISIBLE);

            video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(mContext, CuckooSmallVideoPlayerActivity.class);
                    intent.putExtra("VIDEO_URL", item.getVideo_url());
                    intent.putExtra("COVER_URL", item.getCover_url());
                    mContext.startActivity(intent);

                }
            });
            Utils.loadCropRadius(item.getCover_url(), videoPlayerIv, 5);

        } else {
            video.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
            rv.setLayoutManager(new GridLayoutManager(mContext, 3));
            DynamicImgAdapter dynamicImgAdapter = new DynamicImgAdapter(mContext, item);
            rv.setAdapter(dynamicImgAdapter);
        }


        if (StringUtils.toInt(item.getIs_audio()) == 1) {
            helper.setGone(R.id.pp_sound_item_view, true);
        } else {
            helper.setGone(R.id.pp_sound_item_view, false);
        }

        CommonSoundItemView commonSoundItemView = helper.getView(R.id.pp_sound_item_view);
        AudioEntity audioEntity = new AudioEntity();
        audioEntity.setUrl(item.getAudio_file());
        if (!TextUtils.isEmpty(item.getDuration())) {
            audioEntity.setDuration(Integer.parseInt(item.getDuration()));
        }
        commonSoundItemView.setSoundData(audioEntity);
        if (userInfo != null) {
            Utils.loadHttpIconImg(mContext, userInfo.getAvatar(), (ImageView) helper.getView(R.id.item_iv_avatar), 0);
        } else {
            Utils.loadHttpIconImg(mContext, "", (ImageView) helper.getView(R.id.item_iv_avatar), 0);
        }

        //点赞
        helper.addOnClickListener(R.id.item_iv_like_count);

        if (StringUtils.toInt(item.getIs_like()) == 1) {
            helper.setBackgroundRes(R.id.item_iv_like_count, R.mipmap.ic_dynamic_thumbs_up_s);
        } else {
            helper.setBackgroundRes(R.id.item_iv_like_count, R.mipmap.ic_dynamic_thumbs_up_n);
        }

        if (StringUtils.toInt(item.getUid()) == StringUtils.toInt(SaveData.getInstance().getId())) {
            helper.setGone(R.id.item_del, true);
        } else {
            helper.setGone(R.id.item_del, false);
        }

        BGLevelTextView level = helper.getView(R.id.tv_level);
        if (item.getUserInfo() != null) {
            level.setVisibility(View.GONE);
            level.setLevelInfo(item.getUserInfo().getSex(), item.getUserInfo().getLevel());
        } else {
            level.setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.item_tv_chat);
        helper.addOnClickListener(R.id.item_del);
        helper.addOnClickListener(R.id.item_iv_avatar);

    }


    private OnImgClickListener onImgClickListener;

    public void setOnImgClickListener(OnImgClickListener listener) {
        onImgClickListener = listener;
    }

    public interface OnImgClickListener {
        void onItemClickListener(String imgUrl, String pid);
    }
}
