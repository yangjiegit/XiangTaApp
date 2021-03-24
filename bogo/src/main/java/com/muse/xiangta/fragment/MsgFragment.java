package com.muse.xiangta.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.chat.fragment.ConversationFragment;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseFragment;
import com.muse.xiangta.event.EImOnNewMessages;
import com.muse.xiangta.json.JsonGetMsgPage;
import com.muse.xiangta.json.JsonRequestBase;
import com.muse.xiangta.manage.RequestConfig;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.modle.MsgInfoBean;
import com.muse.xiangta.ui.CuckooSubscribeActivity;
import com.muse.xiangta.ui.WebViewActivity;
import com.muse.xiangta.utils.StringUtils;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 消息
 * Created by weipeng on 2017/12/28 0028.
 */
public class MsgFragment extends BaseFragment {

    @BindView(R.id.system_msg_count_tv)
    TextView sysyemMsgCountTv;

    @BindView(R.id.subscribe_msg_count_tv)
    TextView subscribeMsgCountTv;

    @BindView(R.id.tv_text1)
    TextView tv_text1;
    @BindView(R.id.tv_text2)
    TextView tv_text2;
    @BindView(R.id.tv_text3)
    TextView tv_text3;

    //功能
    private QMUITopBar msgQMUITopBar;
    private RecyclerView mRecyclerView;
    //RecyclerView的grid视图
    private GridLayoutManager mLayoutManager;

    private MsgInfoBean msgInfoBean;

    private ConversationFragment conversationFragment;

    //////////////////////////////////////////初始化操作//////////////////////////////////////////////
    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.view_msg_page, container, false);
    }

    @Override
    protected void initView(View view) {
        msgQMUITopBar = view.findViewById(R.id.msg_page_topBar);
        msgQMUITopBar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        mRecyclerView = view.findViewById(R.id.mRecyclerView);

    }

    @Override
    protected void initDate(View view) {
        getMsgDescInfoData();
    }

    private void getMsgDescInfoData() {
        Api.getMsgDescInfo(uId, uToken, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.d("ret", "joker      " + s);
                if (!StringUtils.isEmpty(s)) {
                    msgInfoBean = new Gson().fromJson(s, MsgInfoBean.class);
                    if (null != msgInfoBean &&
                            null != msgInfoBean.getData()) {
                        tv_text1.setText(msgInfoBean.getData().getSystem());
                        tv_text2.setText(msgInfoBean.getData().getGroup());
                        tv_text3.setText(msgInfoBean.getData().getFamily());
                    }
                }
            }
        });
    }

    @Override
    protected void initSet(View view) {

        //设置列表
        mLayoutManager = new GridLayoutManager(getContext(), 1);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //设置topBar
        msgQMUITopBar.setFitsSystemWindows(true);
        msgQMUITopBar.setTitle(getString(R.string.message));

        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (conversationFragment == null) {
            conversationFragment = new ConversationFragment();
        }
        ft.replace(R.id.fragment, conversationFragment);
        ft.commit();

    }

    @Override
    protected void initDisplayData(View view) {

    }

    ////////////////////////////////////////////监听方法处理//////////////////////////////////////////
    @Override
    public void onClick(View v) {

    }


    ////////////////////////////////////////////本地工具方法//////////////////////////////////////////


    @OnClick({R.id.left, R.id.right, R.id.rl_xitong, R.id.rl_qunliao, R.id.rl_jiazu})
    public void top(View v) {
        switch (v.getId()) {
            case R.id.left:
                //系统消息
                WebViewActivity.openH5Activity(getContext(), true, "系统消息", RequestConfig.getConfigObj().getSystemMessage());
                break;
            case R.id.right:
                goActivity(getContext(), CuckooSubscribeActivity.class);
                break;
            case R.id.rl_xitong:
                WebViewActivity.openH5Activity(getContext(), true, "系统消息", RequestConfig.getConfigObj().getSystemMessage());
                break;
            case R.id.rl_qunliao:

                break;
            case R.id.rl_jiazu:

                break;
        }
    }


    private void getSystemUnReadMsgCount() {
        Api.getMsgPageInfo(SaveData.getInstance().getId(), SaveData.getInstance().getToken(), new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {
                JsonGetMsgPage unReadSystemMsg = (JsonGetMsgPage) JsonRequestBase.getJsonObj(s, JsonGetMsgPage.class);
                if (unReadSystemMsg.getCode() == 1) {

                    if (unReadSystemMsg.getSum() > 0) {
                        sysyemMsgCountTv.setVisibility(View.VISIBLE);
                        sysyemMsgCountTv.setText(unReadSystemMsg.getSum() + "");
                    } else {
                        sysyemMsgCountTv.setVisibility(View.GONE);
                    }

                    if (unReadSystemMsg.getUn_handle_subscribe_num() > 0) {
                        subscribeMsgCountTv.setVisibility(View.VISIBLE);
                        subscribeMsgCountTv.setText(unReadSystemMsg.getUn_handle_subscribe_num() + "");
                    } else {
                        subscribeMsgCountTv.setVisibility(View.GONE);
                    }

                }
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewMsgEventThread(EImOnNewMessages var1) {
        //getUnReadMsg();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //getUnReadMsg();
    }

    @Override
    public void onResume() {
        super.onResume();
        //getUnReadMsg();
        getSystemUnReadMsgCount();
    }

    @Override
    protected boolean isRegEvent() {
        return true;
    }
}
