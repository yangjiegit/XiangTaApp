package com.muse.xiangta.adapter.recycler;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.muse.xiangta.R;
import com.muse.xiangta.api.ApiUtils;
import com.muse.xiangta.audiorecord.AudioPlaybackManager;
import com.muse.xiangta.json.jsonmodle.TargetUserData;
import com.muse.xiangta.modle.ConfigModel;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;
import com.muse.xiangta.utils.Utils;
import com.muse.xiangta.widget.BGLevelTextView;

import java.io.IOException;
import java.util.List;

/**
 * RecyclerView-recommendPage适配器
 * Created by wp on 2017/12/28 0028.
 */
public class RecyclerRecommendAdapter extends BaseQuickAdapter<TargetUserData, BaseViewHolder> {

    private Context context;
    protected AnimationDrawable animationDrawable;

    //构造方法,用于传入数据参数
    public RecyclerRecommendAdapter(Context context, @Nullable List<TargetUserData> data) {
        super(R.layout.adapter_user, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TargetUserData item) {
        TextView pagemsg_view_sign = helper.getView(R.id.pagemsg_view_sign);
        RelativeLayout rl_yinpin = helper.getView(R.id.rl_yinpin);
        ImageView iv_vip = helper.getView(R.id.iv_vip);

        //初始化数据显示
        if (ApiUtils.isTrueUrl(item.getAvatar())) {
            Utils.loadImg(Utils.getCompleteImgUrl(item.getAvatar()), (ImageView) helper.getView(R.id.pagemsg_background));
        }

        ImageView iv_v = helper.getView(R.id.iv_v);
        if (item.getIs_auth() == 1) {
            iv_v.setVisibility(View.VISIBLE);
        } else {
            iv_v.setVisibility(View.GONE);
        }

        helper.setText(R.id.tv_address, item.getCity());
        helper.setImageResource(R.id.pagemsg_view_dian, StringUtils.toInt(item.getIs_online()) == 1 ? R.mipmap.on_line : R.mipmap.not_online);
        helper.setImageResource(R.id.pagemsg_view_isvip, StringUtils.toInt(item.getIs_vip()) == 1 ? R.mipmap.vip_image_bac : 0);
        helper.setText(R.id.pagemsg_view_name, item.getUser_nickname());

        if (!StringUtils.isEmpty(item.getNob())) {
            iv_vip.setVisibility(View.VISIBLE);
            GlideImgManager.loadImage(context, item.getNob(), iv_vip);
        } else {
            iv_vip.setVisibility(View.GONE);
        }

        if (StringUtils.toInt(item.getSex()) == 2) {
            helper.setText(R.id.pagemsg_view_nice, item.getCharging_coin() + ConfigModel.getInitData().getCurrency_name() + "/分钟");
        }

        helper.setText(R.id.tv_age, item.getAge() + "岁·" + item.getHeight() + "cm");

        if (item.getDeclaration_type().equals("1")) {
            pagemsg_view_sign.setVisibility(View.GONE);
            rl_yinpin.setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_yinpin, item.getDeclaration_length() + "\"");
        } else {
            //没有音频 显示签名
            pagemsg_view_sign.setVisibility(View.VISIBLE);
            rl_yinpin.setVisibility(View.GONE);
            if (!TextUtils.isEmpty(item.getSign())) {
                helper.setText(R.id.pagemsg_view_sign, item.getSign());
            } else {
                helper.setText(R.id.pagemsg_view_sign, "暂未设置签名");
            }
        }
        animationDrawable = new AnimationDrawable();

        rl_yinpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //从网路加载音乐
                try {
                    AudioPlaybackManager.getInstance().playAudio(item.getDeclaration(), new AudioPlaybackManager.OnPlayingListener() {
                        @Override
                        public void onStart() {
                            animationDrawable.start();
                        }

                        @Override
                        public void onStop() {
                            animationDrawable.stop();
                        }

                        @Override
                        public void onComplete() {
                            animationDrawable.stop();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        ((BGLevelTextView) helper.getView(R.id.tv_level)).setLevelInfo(item.getSex(), item.getLevel());
    }

}