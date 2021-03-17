package com.muse.xiangta.ui;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.audiorecord.util.StringUtil;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.fragment.MyGradeFragment;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.modle.LevelBean;
import com.muse.xiangta.utils.GlideImgManager;
import com.muse.xiangta.utils.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class MyGradeActivity extends BaseActivity {

    @BindView(R.id.tv_text1)
    TextView tv_text1;
    @BindView(R.id.tv_text2)
    TextView tv_text2;
    @BindView(R.id.tv_text_str1)
    TextView tv_text_str1;
    @BindView(R.id.tv_text_str2)
    TextView tv_text_str2;
    @BindView(R.id.tv_jingyan)
    TextView tv_jingyan;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.tv_meili)
    TextView tv_meili;
    @BindView(R.id.tv_caifu)
    TextView tv_caifu;

    private LevelBean levelBean;

    private int type;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_grade;
    }

    @Override
    protected void initView() {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fl_layout, MyGradeFragment.getInstance(1)).commit();

        tv_caifu.setOnClickListener(this);
        tv_meili.setOnClickListener(this);
    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        type = 1;
        getLevelData();
    }

    private void getLevelData() {
        Api.getLevel(uId, uToken, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker    " + s);
                if (!StringUtils.isEmpty(s)) {
                    levelBean = new Gson().fromJson(s, LevelBean.class);
                    if (levelBean.getCode() == 1) {
                        //成功
                        GlideImgManager.glideLoader(MyGradeActivity.this,
                                levelBean.getData().getAvatar(), iv_head, 0);
                        setLeveData();
                    }
                }
            }
        });
    }

    private void setLeveData() {
        if (type == 1) {
            tv_text1.setText(levelBean.getData().getGlamour() + "");
            tv_text2.setText(levelBean.getData().getGlamour_level_id() + "");
            tv_text_str1.setText("魅力值");
            tv_text_str2.setText("魅力等级");
            LevelBean.DataBean.WealthLevelDataBean data = levelBean.getData().getWealth_level_data();
            tv_jingyan.setText("距离升级还差:" +
                    (Integer.valueOf(data.getMax_value()) - Integer.valueOf(data.getMin_value())));
        } else {
            tv_text1.setText(levelBean.getData().getWealth() + "");
            tv_text2.setText(levelBean.getData().getWealth_level_id() + "");
            tv_text_str1.setText("财富值");
            tv_text_str2.setText("财富等级");
            LevelBean.DataBean.GlamourLevelDataBean data = levelBean.getData().getGlamour_level_data();
            tv_jingyan.setText("距离升级还差:" +
                    (Integer.valueOf(data.getMax_value()) - Integer.valueOf(data.getMin_value())));
        }
    }


    @Override
    protected void initPlayerDisplayData() {

    }

    @OnClick(R.id.iv_back)
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_caifu:
                type = 2;
                tv_meili.setTextSize(12);
                tv_caifu.setTextSize(14);
                setLeveData();
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.fl_layout, MyGradeFragment.getInstance(type)).commit();
                break;
            case R.id.tv_meili:
                type = 1;
                tv_meili.setTextSize(14);
                tv_caifu.setTextSize(12);
                setLeveData();
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.fl_layout, MyGradeFragment.getInstance(type)).commit();
                break;
        }
    }
}
