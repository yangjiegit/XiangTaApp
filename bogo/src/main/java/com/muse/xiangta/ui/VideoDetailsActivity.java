package com.muse.xiangta.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.muse.xiangta.R;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.modle.VideoBean;
import com.muse.xiangta.utils.GlideImgManager;

import butterknife.BindView;
import butterknife.OnClick;

public class VideoDetailsActivity extends BaseActivity {

    @BindView(R.id.videoView)
    VideoView videoView;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_age)
    TextView tv_age;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.iv_head)
    ImageView iv_head;

    private VideoBean.DataBean mData;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_video_details;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        mData = (VideoBean.DataBean) getIntent().getSerializableExtra("data");

        videoView.setVideoPath(mData.getVideo_url());

        tv_name.setText(mData.getUser_nickname());

        Drawable imgnan = getResources().getDrawable(R.mipmap.img_nan_1);
// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
        imgnan.setBounds(0, 0, imgnan.getMinimumWidth(), imgnan.getMinimumHeight());

        Drawable imgnv = getResources().getDrawable(R.mipmap.img_nv_1);
// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
        imgnv.setBounds(0, 0, imgnv.getMinimumWidth(), imgnv.getMinimumHeight());
        if (mData.getSex() == 1) {
            tv_age.setCompoundDrawables(imgnan, null, null, null); //设置左图标
        } else {
            tv_age.setCompoundDrawables(imgnv, null, null, null); //设置左图标
        }

        tv_age.setText(mData.getAge() + "");

        tv_content.setText(mData.getOverlapping_sound());

        GlideImgManager.glideLoader(VideoDetailsActivity.this,
                mData.getImg(), iv_head, 0);

        //创建MediaController对象
        MediaController mediaController = new MediaController(this);

        mediaController.setVisibility(View.GONE);        //隐藏进度条

        //VideoView与MediaController建立关联
        videoView.setMediaController(mediaController);

        //让VideoView获取焦点
        videoView.requestFocus();

        videoView.start();


        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
                    /*
                    错误常数
                    MEDIA_ERROR_IO
                    文件不存在或错误，或网络不可访问错误
                    值: -1004 (0xfffffc14)
                    MEDIA_ERROR_MALFORMED
                    流不符合有关标准或文件的编码规范
                    值: -1007 (0xfffffc11)
                    MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK
                    视频流及其容器不适用于连续播放视频的指标（例如：MOOV原子）不在文件的开始.
                    值: 200 (0x000000c8)
                    MEDIA_ERROR_SERVER_DIED
                    媒体服务器挂掉了。此时，程序必须释放MediaPlayer 对象，并重新new 一个新的。
                    值: 100 (0x00000064)
                    MEDIA_ERROR_TIMED_OUT
                    一些操作使用了过长的时间，也就是超时了，通常是超过了3-5秒
                    值: -110 (0xffffff92)
                    MEDIA_ERROR_UNKNOWN
                    未知错误
                    值: 1 (0x00000001)
                    MEDIA_ERROR_UNSUPPORTED
                    比特流符合相关编码标准或文件的规格，但媒体框架不支持此功能
                    值: -1010 (0xfffffc0e)
                    what int: 标记的错误类型:
                        MEDIA_ERROR_UNKNOWN
                        MEDIA_ERROR_SERVER_DIED
                    extra int: 标记的错误类型.
                        MEDIA_ERROR_IO
                        MEDIA_ERROR_MALFORMED
                        MEDIA_ERROR_UNSUPPORTED
                        MEDIA_ERROR_TIMED_OUT
                        MEDIA_ERROR_SYSTEM (-2147483648) - low-level system error.
                    * */
                if (what == MediaPlayer.MEDIA_ERROR_SERVER_DIED) {
                    //媒体服务器挂掉了。此时，程序必须释放MediaPlayer 对象，并重新new 一个新的。
                    showToastMsg("网络服务错误");
                } else if (what == MediaPlayer.MEDIA_ERROR_UNKNOWN) {
                    if (extra == MediaPlayer.MEDIA_ERROR_IO) {
                        //文件不存在或错误，或网络不可访问错误
                        showToastMsg("网络文件错误");
                    } else if (extra == MediaPlayer.MEDIA_ERROR_TIMED_OUT) {
                        //超时
                        showToastMsg("网络超时");
                    }
                } else {
                    //监听视频播放完的代码
                    videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mPlayer) {
                            // TODO Auto-generated method stub
                            mPlayer.start();
                            mPlayer.setLooping(true);
                        }
                    });
                }
                return false;
            }
        });

    }

    @OnClick(R.id.iv_back)
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected void initPlayerDisplayData() {

    }
}
