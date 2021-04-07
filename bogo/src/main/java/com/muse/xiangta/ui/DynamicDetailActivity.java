package com.muse.xiangta.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.muse.xiangta.R;
import com.muse.xiangta.adapter.DynamicCommonAdapter;
import com.muse.xiangta.adapter.DynamicImgAdapter;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.audiorecord.entity.AudioEntity;
import com.muse.xiangta.audiorecord.view.CommonSoundItemView;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.event.RefreshMessageEvent;
import com.muse.xiangta.json.JsonRequestBase;
import com.muse.xiangta.json.JsonRequestDoGetDynamicCommonList;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.modle.DynamicCommonListModel;
import com.muse.xiangta.modle.DynamicListModel;
import com.muse.xiangta.modle.UserModel;
import com.muse.xiangta.ui.common.Common;
import com.muse.xiangta.utils.StringUtils;
import com.muse.xiangta.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;

public class DynamicDetailActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, View.OnClickListener {

    public static final String DYNAMIC_DATA = "DYNAMIC_DATA";

    private TextView tv_name;
    private TextView tv_time;
    private TextView tv_content;
    private ImageView iv_sex;

    private CircleImageView iv_avatar;
    private RecyclerView rv_photo_list;
    private CommonSoundItemView pp_sound_item_view;

    @BindView(R.id.rv_content_list)
    RecyclerView rv_common_list;

    @BindView(R.id.et_input)
    EditText et_input;

    RelativeLayout videoplayer_rl;

    private DynamicCommonAdapter dynamicCommonAdapter;
    private DynamicListModel dynamicListModel;
    private int page = 1;
    private List<DynamicCommonListModel> list = new ArrayList<>();
    private View headView;
    private ImageView video;
    private TextView location;


    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_dynamic_detail;
    }

    @Override
    protected void initView() {
        getTopBar().setTitle("动态详情");
        getTopBar().setBackgroundColor(getResources().getColor(R.color.admin_color));

        et_input.setFocusable(true);
        et_input.setFocusableInTouchMode(true);
        showInputTips(et_input);

        initData();
    }

    private void showInputTips(EditText et_text) {
        et_text.setFocusable(true);
        et_text.setFocusableInTouchMode(true);
        et_text.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager) et_text.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(et_text, 0);
    }

    @Override
    protected void initSet() {

    }

    @Override
    protected boolean hasTopBar() {
        return true;
    }

    protected void initData() {

        dynamicListModel = getIntent().getParcelableExtra(DYNAMIC_DATA);

        headView = LayoutInflater.from(this).inflate(R.layout.dynamic_datail_layout, null);

        tv_name = headView.findViewById(R.id.item_tv_name);
        iv_sex = headView.findViewById(R.id.iv_sex);
        tv_content = headView.findViewById(R.id.item_tv_content);
        tv_time = headView.findViewById(R.id.item_tv_time);
        iv_avatar = headView.findViewById(R.id.item_iv_avatar);
        iv_avatar.setOnClickListener(this);
        pp_sound_item_view = headView.findViewById(R.id.pp_sound_item_view);
        videoplayer_rl = headView.findViewById(R.id.videoplayer_rl);
        video = headView.findViewById(R.id.videoplayer);
        rv_photo_list = headView.findViewById(R.id.rv_photo_list);
        location = headView.findViewById(R.id.location);


        TextView tv_common_count = headView.findViewById(R.id.item_tv_common_count);
        TextView item_tv_like_count = headView.findViewById(R.id.item_tv_like_count);
        tv_common_count.setText(dynamicListModel.getComment_count());
        item_tv_like_count.setText(dynamicListModel.getLike_count());

        changeLikeStatus();

        item_tv_like_count.setOnClickListener(this);

        UserModel userInfo = dynamicListModel.getUserInfo();
        if (userInfo != null) {
            tv_name.setText(userInfo.getUser_nickname());
            Utils.loadHttpIconImg(this, userInfo.getAvatar(), iv_avatar, 0);

        }

        if (dynamicListModel.getUserInfo().getSex() == 1) {
            iv_sex.setImageResource(R.mipmap.img_xingbienan2);
        } else {
            iv_sex.setImageResource(R.mipmap.img_xingbie1);
        }

        tv_content.setText(dynamicListModel.getMsg_content());

        tv_time.setText(dynamicListModel.getPublish_time());

        if (!TextUtils.isEmpty(dynamicListModel.getCity())) {
            location.setVisibility(View.VISIBLE);
            location.setText(dynamicListModel.getCity());
        } else {
            location.setVisibility(View.VISIBLE);

        }


        AudioEntity audioEntity = new AudioEntity();
        audioEntity.setUrl(dynamicListModel.getAudio_file());
        pp_sound_item_view.setSoundData(audioEntity);

        //视频动态
        if (!TextUtils.isEmpty(dynamicListModel.getVideo_url())) {
            videoplayer_rl.setVisibility(View.VISIBLE);
            rv_photo_list.setVisibility(View.GONE);
            video.setVisibility(View.VISIBLE);

            Utils.loadHttpImg(this, dynamicListModel.getCover_url(), video, 0);

            video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(DynamicDetailActivity.this, CuckooSmallVideoPlayerActivity.class);
                    intent.putExtra("VIDEO_URL", dynamicListModel.getVideo_url());
                    intent.putExtra("COVER_URL", dynamicListModel.getCover_url());
                    startActivity(intent);

                }
            });

        } else {
            videoplayer_rl.setVisibility(View.GONE);
            video.setVisibility(View.GONE);
            rv_photo_list.setVisibility(View.VISIBLE);

            rv_photo_list.setLayoutManager(new GridLayoutManager(this, 3));
            rv_photo_list.setAdapter(new DynamicImgAdapter(this, dynamicListModel));
        }


        if (StringUtils.toInt(dynamicListModel.getIs_audio()) == 1) {
            pp_sound_item_view.setVisibility(View.VISIBLE);
        } else {
            pp_sound_item_view.setVisibility(View.GONE);
        }

        rv_common_list.setLayoutManager(new LinearLayoutManager(this));
        dynamicCommonAdapter = new DynamicCommonAdapter(list);
        dynamicCommonAdapter.addHeaderView(headView);
        dynamicCommonAdapter.setOnLoadMoreListener(this, rv_common_list);
        dynamicCommonAdapter.disableLoadMoreIfNotFullPage();
        dynamicCommonAdapter.notifyDataSetChanged();


        requestGetData();


    }

    @Override
    protected void initPlayerDisplayData() {

    }


    private void requestGetData() {

        Api.doRequestGetDynamicCommonList(dynamicListModel.getId(), SaveData.getInstance().getId(), SaveData.getInstance().getToken(), page, new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {

                JsonRequestDoGetDynamicCommonList data = (JsonRequestDoGetDynamicCommonList) JsonRequestBase.getJsonObj(s, JsonRequestDoGetDynamicCommonList.class);
                if (StringUtils.toInt(data.getCode()) == 1) {

                    if (page == 1) {
                        list.clear();
                    }

                    if (data.getList().size() <= 20) {
                        dynamicCommonAdapter.loadMoreEnd();
                        dynamicCommonAdapter.setEnableLoadMore(false);
                    } else {
                        dynamicCommonAdapter.loadMoreComplete();
                    }

                    if (dynamicCommonAdapter == null) {
                        dynamicCommonAdapter = new DynamicCommonAdapter(list);
                    } else {
                        list.addAll(data.getList());
                    }

                    rv_common_list.setAdapter(dynamicCommonAdapter);

//                    dynamicCommonAdapter.notifyDataSetChanged();
                } else {

                    ToastUtils.showLong(data.getMsg());
                }
            }
        });
    }


    @OnClick({R.id.btn_publish_common})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_iv_avatar:
                Common.jumpUserPage(DynamicDetailActivity.this, dynamicListModel.getUserInfo().getId());
                break;
            case R.id.btn_publish_common:

                clickPublishCommon();
                break;

            case R.id.item_iv_like_ll:
                clickLike();
                break;
            default:
                break;
        }
    }

    private void changeLikeStatus() {
        TextView item_tv_like_count = headView.findViewById(R.id.item_tv_like_count);
        headView.findViewById(R.id.item_iv_like_ll).setOnClickListener(this);
        ImageView like_iv = headView.findViewById(R.id.item_iv_like_count);

        if (StringUtils.toInt(dynamicListModel.getIs_like()) == 1) {
            like_iv.setBackgroundResource(R.mipmap.ic_dynamic_thumbs_up_s);
        } else {
            like_iv.setBackgroundResource(R.mipmap.ic_dynamic_thumbs_up_n);
        }

        item_tv_like_count.setText(dynamicListModel.getLike_count());
    }

    private void clickLike() {
        Api.doRequestDynamicLike(SaveData.getInstance().getId(), SaveData.getInstance().getToken(), dynamicListModel.getId(), new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {

                JsonRequestBase data = JsonRequestBase.getJsonObj(s, JsonRequestBase.class);
                if (StringUtils.toInt(data.getCode()) == 1) {
                    if (StringUtils.toInt(dynamicListModel.getIs_like()) == 1) {
                        dynamicListModel.setIs_like("0");
                        dynamicListModel.decLikeCount(1);
                    } else {
                        dynamicListModel.setIs_like("1");
                        dynamicListModel.plusLikeCount(1);
                    }

                    changeLikeStatus();
                }
            }
        });
    }

    private void clickPublishCommon() {

        String msgContent = et_input.getText().toString();
        if (TextUtils.isEmpty(msgContent)) {
            ToastUtils.showLong("请输入内容！");
            return;
        }

        Api.doRequestPublishCommon(SaveData.getInstance().getId(), SaveData.getInstance().getToken(), msgContent, dynamicListModel.getId(), new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {

                JsonRequestBase data = JsonRequestBase.getJsonObj(s, JsonRequestBase.class);
                if (StringUtils.toInt(data.getCode()) == 1) {
                    ToastUtils.showLong("发布成功！");
                    et_input.setText("");
                    page = 1;
                    requestGetData();
                } else {
                    ToastUtils.showLong(data.getMsg());
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        requestGetData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        EventBus.getDefault().post(new RefreshMessageEvent("refresh_dynamic_list"));
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
