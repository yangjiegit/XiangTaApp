package com.muse.xiangta.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseFragment;
import com.muse.xiangta.modle.ChargeBean;
import com.muse.xiangta.modle.SettingBean;
import com.muse.xiangta.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;
import okhttp3.Call;
import okhttp3.Response;

public class VideoChargeSetFragment extends BaseFragment {

    @BindView(R.id.tv_message)
    TextView tv_message;
    @BindView(R.id.tv_voice)
    TextView tv_voice;
    @BindView(R.id.tv_video)
    TextView tv_video;
    @BindView(R.id.iv_voice_check)
    ImageView iv_voice_check;
    @BindView(R.id.iv_video_check)
    ImageView iv_video_check;

    private SettingBean mSettingBean;
    private List<ChargeBean> mList = new ArrayList<>();

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.video_charge, container, false);
    }

    @Override
    protected void initView(View view) {

    }


    @Override
    protected void initDate(View view) {
        getSettingData();
    }

    private void getSettingData() {
        Api.getSetting(uId, uToken, "charge", new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker    " + s);
                if (!StringUtils.isEmpty(s)) {
                    mSettingBean = new Gson().fromJson(s, SettingBean.class);

                    setSettingData();
                }
            }
        });
    }

    private void setSettingData() {
        tv_message.setText(mSettingBean.getData().getCharge().getMessage_chat().getVal() + "钻石/条");
        tv_voice.setText(mSettingBean.getData().getCharge().getVoice_chat().getVal() + "钻石/分钟");
        tv_video.setText(mSettingBean.getData().getCharge().getVideo_chat().getVal() + "钻石/分钟");

        ChargeBean bean1 = new ChargeBean();
        bean1.setCode("voice_chat");
        bean1.setStatus(mSettingBean.getData().getCharge().getVoice_chat().getStatus());
        bean1.setVal(mSettingBean.getData().getCharge().getVoice_chat().getVal());
        ChargeBean bean2 = new ChargeBean();
        bean2.setCode("video_chat");
        bean2.setStatus(mSettingBean.getData().getCharge().getVideo_chat().getStatus());
        bean2.setVal(mSettingBean.getData().getCharge().getVideo_chat().getVal());
        ChargeBean bean3 = new ChargeBean();
        bean3.setCode("message_chat");
        bean3.setStatus(mSettingBean.getData().getCharge().getMessage_chat().getStatus());
        bean3.setVal(mSettingBean.getData().getCharge().getMessage_chat().getVal());
        mList.add(bean1);
        mList.add(bean2);
        mList.add(bean3);

        if (mSettingBean.getData().getCharge().getVoice_chat().getStatus() == 0) {
            iv_voice_check.setImageResource(R.mipmap.iv_check2);
        } else {
            iv_voice_check.setImageResource(R.mipmap.iv_check1);
        }

        if (mSettingBean.getData().getCharge().getVideo_chat().getStatus() == 0) {
            iv_video_check.setImageResource(R.mipmap.iv_check2);
        } else {
            iv_video_check.setImageResource(R.mipmap.iv_check1);
        }
    }

    @OnClick({R.id.iv_voice_check, R.id.iv_video_check, R.id.rl_voice, R.id.rl_video,
            R.id.rl_message_val, R.id.rl_voice_val, R.id.rl_video_val, R.id.tv_comm})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_comm:
                setComm();
                break;
            case R.id.rl_message_val:
                dialog(2);
                break;
            case R.id.rl_voice_val:
                dialog(0);
                break;
            case R.id.rl_video_val:
                dialog(1);
                break;
            case R.id.rl_voice:
            case R.id.iv_voice_check:
                if (mSettingBean.getData().getCharge().getVoice_chat().getStatus() == 1) {
                    iv_voice_check.setImageResource(R.mipmap.iv_check2);
                    mSettingBean.getData().getCharge().getVoice_chat().setStatus(0);
                    mList.get(0).setStatus(Integer.valueOf(0));
                } else {
                    iv_voice_check.setImageResource(R.mipmap.iv_check1);
                    mSettingBean.getData().getCharge().getVoice_chat().setStatus(1);
                    mList.get(0).setStatus(Integer.valueOf(1));
                }
                break;
            case R.id.rl_video:
            case R.id.iv_video_check:
                if (mSettingBean.getData().getCharge().getVideo_chat().getStatus() == 1) {
                    iv_video_check.setImageResource(R.mipmap.iv_check2);
                    mSettingBean.getData().getCharge().getVideo_chat().setStatus(0);
                    mList.get(1).setStatus(Integer.valueOf(0));
                } else {
                    iv_video_check.setImageResource(R.mipmap.iv_check1);
                    mSettingBean.getData().getCharge().getVideo_chat().setStatus(1);
                    mList.get(1).setStatus(Integer.valueOf(1));
                }
                break;
        }

    }


    private void dialog(int type) {
        OptionPicker picker = null;
        switch (type) {
            case 0:
                picker = new OptionPicker(getActivity(),
                        mSettingBean.getData().getCharge_rule().getMessage_chat().toArray
                                (new String[mSettingBean.getData().getCharge_rule().getMessage_chat().size()]));
                break;
            case 1:
                picker = new OptionPicker(getActivity(),
                        mSettingBean.getData().getCharge_rule().getVoice_chat().toArray
                                (new String[mSettingBean.getData().getCharge_rule().getVoice_chat().size()]));
                break;
            case 2:
                picker = new OptionPicker(getActivity(),
                        mSettingBean.getData().getCharge_rule().getVideo_chat().toArray
                                (new String[mSettingBean.getData().getCharge_rule().getVideo_chat().size()]));
                break;
        }
        picker.setCycleDisable(true);//不禁用循环
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(45);
        picker.setTopLineColor(getResources().getColor(R.color.line_color));
        picker.setTopLineHeight(1);
        picker.setTitleText("请选择");
        picker.setTitleTextColor(getResources().getColor(R.color.black));
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(getResources().getColor(R.color.black));
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(getResources().getColor(R.color.black));
        picker.setSubmitTextSize(13);
        picker.setTextColor(getResources().getColor(R.color.black), 0xFF999999);
        WheelView.DividerConfig config = new WheelView.DividerConfig();
        config.setColor(getResources().getColor(R.color.transparent));//线颜色
        config.setAlpha(1);//线透明度
        config.setRatio((float) (1.0 / 8.0));//线比率
        picker.setDividerConfig(config);
        picker.setBackgroundColor(0xFFEEEEEE);
        //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
        picker.setSelectedIndex(7);
        picker.setCanceledOnTouchOutside(true);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                switch (type) {
                    case 0:
                        mList.get(type).setVal(Integer.valueOf(item));
                        tv_voice.setText(item + "钻石/分钟");
                        mSettingBean.getData().getCharge().getVoice_chat().setVal(Integer.valueOf(item));
                        break;
                    case 1:
                        mList.get(type).setVal(Integer.valueOf(item));
                        tv_video.setText(item + "钻石/分钟");
                        mSettingBean.getData().getCharge().getVideo_chat().setVal(Integer.valueOf(item));
                        break;
                    case 2:
                        mList.get(type).setVal(Integer.valueOf(item));
                        tv_message.setText(item + "钻石/条");
                        mSettingBean.getData().getCharge().getMessage_chat().setVal(Integer.valueOf(item));
                        break;
                }

            }
        });
        picker.show();

    }

    @Override
    protected void initSet(View view) {

    }

    @Override
    protected void initDisplayData(View view) {

    }

    public void setComm() {
        Gson gson = new Gson();
        String charge = gson.toJson(mList);

        Api.setDiySetting(uId, uToken, "charge", charge, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker      " + s);
                try {
                    int code = new JSONObject(s).getInt("code");
                    if (code == 1) {
                        showToastMsg(getContext(), "修改成功");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }
}
