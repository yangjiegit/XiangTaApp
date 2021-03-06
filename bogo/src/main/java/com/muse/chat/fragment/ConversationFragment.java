package com.muse.chat.fragment;


import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.muse.chat.adapter.ConversationRvAdapter;
import com.muse.chat.model.Conversation;
import com.muse.chat.model.MessageFactory;
import com.muse.chat.model.NomalConversation;
import com.muse.chat.utils.PushUtil;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.json.JsonRequestGetConversationUserInfo;
import com.muse.xiangta.manage.RequestConfig;
import com.muse.xiangta.modle.ConfigModel;
import com.muse.xiangta.modle.UserModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.lzy.okgo.callback.StringCallback;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.ext.group.TIMGroupCacheInfo;
import com.tencent.imsdk.ext.group.TIMGroupPendencyItem;
import com.tencent.imsdk.ext.sns.TIMFriendFutureItem;
import com.tencent.qcloud.presentation.presenter.ConversationPresenter;
import com.tencent.qcloud.presentation.presenter.FriendshipManagerPresenter;
import com.tencent.qcloud.presentation.presenter.GroupManagerPresenter;
import com.tencent.qcloud.presentation.viewfeatures.ConversationView;
import com.tencent.qcloud.presentation.viewfeatures.FriendshipMessageView;
import com.tencent.qcloud.presentation.viewfeatures.GroupManageMessageView;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
/**
 * ??????????????????
 */
public class ConversationFragment extends Fragment implements ConversationView, FriendshipMessageView, GroupManageMessageView {

    private final String TAG = "ConversationFragment";

    private View view;
    private List<Conversation> conversationList = new LinkedList<>();
    private ConversationRvAdapter adapter;
    private RecyclerView listView;
    private ConversationPresenter presenter;
    private FriendshipManagerPresenter friendshipManagerPresenter;
    private GroupManagerPresenter groupManagerPresenter;
    //    private List<String> groupList;
    //private FriendshipConversation friendshipConversation;
    //private GroupManageConversation groupManageConversation;

    /**
     * ?????????????????????????????????   ???????????????
     */
    private boolean hasInfo = false;

    private boolean isRefreshSuccess = true;
    private int requestCode = 0;

    private String nowGetInfoIds = "";

    public ConversationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_conversation, container, false);
            listView = (RecyclerView) view.findViewById(R.id.list);
            listView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new ConversationRvAdapter(getActivity(), R.layout.item_conversation, conversationList);
            listView.setAdapter(adapter);

            friendshipManagerPresenter = new FriendshipManagerPresenter(this);
            groupManagerPresenter = new GroupManagerPresenter(this);
            presenter = new ConversationPresenter(this);
            presenter.getConversation();

            adapter.setOnItemClickListener(new ConversationRvAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    conversationList.get(position).navToDetail(getActivity());
                }
            });

            OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
                @Override
                public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                    LogUtils.i("onItemSwipeStart");
                }

                @Override
                public void clearView(RecyclerView.ViewHolder viewHolder, int position) {
                    LogUtils.i("clearView");

                }

                @Override
                public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int position) {
                    LogUtils.i("onItemSwiped");
                    //????????????Item??????????????????????????????
                    // ?????????????????????Item???????????????????????????Adapter???

                    NomalConversation conversation = (NomalConversation) conversationList.get(position);

                    if (conversation != null) {
                        if (presenter.delConversation(conversation.getType(), conversation.getIdentify())) {
                            conversationList.remove(conversation);
                            adapter = new ConversationRvAdapter(getActivity(), R.layout.item_conversation, conversationList);
                            listView.setAdapter(adapter);
                            adapter.setOnItemClickListener(new ConversationRvAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    conversationList.get(position).navToDetail(getActivity());
                                }
                            });
                            refresh();
                        }
                    }
                }

                @Override
                public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
                    LogUtils.i("onItemSwipeMoving");

                }
            };

            ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(adapter);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
            itemTouchHelper.attachToRecyclerView(listView);

            // ??????????????????
            adapter.enableSwipeItem();
            adapter.setOnItemSwipeListener(onItemSwipeListener);


        }


        return view;

    }


    /**
     * ??????????????????????????????
     *
     * @param conversationList //????????????
     */
    @Override
    public void initView(List<TIMConversation> conversationList) {

        this.conversationList.clear();

//        groupList = new ArrayList<>();

        for (TIMConversation item : conversationList) {
            switch (item.getType()) {
                case C2C:
                case Group:
                    if (!item.getPeer().equals(RequestConfig.getConfigObj().getGroupId())) {
                        //????????????
                        NomalConversation nomalConversation=new NomalConversation(item);
                        if(nomalConversation.getType()!=TIMConversationType.Group){
                            this.conversationList.add(new NomalConversation(item));
                        }
//                    groupList.add(item.getPeer());
                    }
                    break;
            }
        }

        friendshipManagerPresenter.getFriendshipLastMessage();
        groupManagerPresenter.getGroupManageLastMessage();

        getConversationInfo();
    }

    /**
     * ????????????????????????
     *
     * @param message ??????????????????
     */
    @Override
    public void updateMessage(TIMMessage message) {

        if (message == null) {
            adapter.notifyDataSetChanged();
            return;
        }
        if (message.getConversation().getType() == TIMConversationType.System) {
            groupManagerPresenter.getGroupManageLastMessage();
            return;
        }
        //if (MessageFactory.getMessage(message) instanceof CustomMessage) return;
        NomalConversation conversation = new NomalConversation(message.getConversation());
        Iterator<Conversation> iterator = conversationList.iterator();
        while (iterator.hasNext()) {
            Conversation c = iterator.next();
            if (conversation.equals(c)) {
                conversation = (NomalConversation) c;
                iterator.remove();
                break;
            }
        }
        conversation.setLastMessage(MessageFactory.getMessage(message));
        if (!conversation.getIdentify().equals(RequestConfig.getConfigObj().getGroupId())) {
            if(conversation.getType()!=TIMConversationType.Group){
                conversationList.add(conversation);
            }
        }
        Collections.sort(conversationList);
        refresh();
    }

    /**
     * ???????????????????????????
     */
    @Override
    public void updateFriendshipMessage() {
        friendshipManagerPresenter.getFriendshipLastMessage();
    }

    /**
     * ????????????
     *
     * @param identify
     */
    @Override
    public void removeConversation(String identify) {
        Iterator<Conversation> iterator = conversationList.iterator();
        while (iterator.hasNext()) {
            Conversation conversation = iterator.next();
            if (conversation.getIdentify() != null && conversation.getIdentify().equals(identify)) {
                iterator.remove();
                adapter.notifyDataSetChanged();
                return;
            }
        }
    }

    /**
     * ???????????????
     *
     * @param info
     */
    @Override
    public void updateGroupInfo(TIMGroupCacheInfo info) {
        for (Conversation conversation : conversationList) {
            if (conversation.getIdentify() != null && conversation.getIdentify().equals(info.getGroupInfo().getGroupId())) {
                adapter.notifyDataSetChanged();
                return;
            }
        }
    }

    /**
     * ??????
     */
    @Override
    public void refresh() {
//        Collections.sort(conversationList);
//        adapter.notifyDataSetChanged();

        if (isRefreshSuccess) {
            LogUtils.i("????????????????????????false" + isRefreshSuccess);
            isRefreshSuccess = false;

            String groupId = ConfigModel.getInitData().getGroup_id();
            StringBuilder ids = new StringBuilder();
            //??????????????????
            for (Conversation item : conversationList) {
                NomalConversation nomalConversation = (NomalConversation) item;
                if (!nomalConversation.getName().equals(groupId) && nomalConversation.getName().equals(nomalConversation.getIdentify())) {
                    ids.append(item.getIdentify()).append(",");
                }
            }

            LogUtils.i("nowGetInfoIds: " + nowGetInfoIds + "-----ids: " + ids.toString() + "-----TIME:" + System.currentTimeMillis());
            if (!TextUtils.isEmpty(ids.toString()) && !nowGetInfoIds.equals(ids.toString())) {
                requestCode = 1;
                nowGetInfoIds = ids.toString();
                //????????????????????????  ???????????????????????????????????????????????????????????????
                Api.doRequestConversationUserInfo(ids,"1", new StringCallback() {

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        JsonRequestGetConversationUserInfo jsonObj =
                                (JsonRequestGetConversationUserInfo) JsonRequestGetConversationUserInfo
                                        .getJsonObj(s, JsonRequestGetConversationUserInfo.class);
                        if (jsonObj.getCode() == 1) {

                            for (UserModel userModel : jsonObj.getList()) {
                                for (Conversation conversation : conversationList) {
                                    if (userModel.getId().equals(conversation.getIdentify())) {
                                        conversation.setAvatar(userModel.getAvatar());
                                        conversation.setName(userModel.getUser_nickname());
                                    }
                                }
                            }

                            //??????
                            //adapter.setData(conversationList);
                            adapter.notifyDataSetChanged();
                        }
                        nowGetInfoIds = "";
                        isRefreshSuccess = true;
                        requestCode = 0;

                        LogUtils.i("????????????????????????true" + isRefreshSuccess);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        isRefreshSuccess = true;
                        requestCode = 0;
                    }
                });
            } else {
                //adapter.setData(conversationList);
                adapter.notifyDataSetChanged();
                if (requestCode == 0) {
                    isRefreshSuccess = true;
                }
            }

        } else {
            //adapter.setData(conversationList);
            adapter.notifyDataSetChanged();
        }

        //if (getActivity() instanceof  HomeActivity)
        //((HomeActivity) getActivity()).setMsgUnread(getTotalUnreadNum() == 0);
    }


    //????????????
    @Override
    public void getConversationInfo() {
        refresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        PushUtil.getInstance().reset();
        refresh();
        LogUtils.i("onResume()????????????refresh()");
    }

    /**
     * ????????????????????????????????????????????????????????????
     *
     * @param message     ??????????????????
     * @param unreadCount ?????????
     */
    @Override
    public void onGetFriendshipLastMessage(TIMFriendFutureItem message, long unreadCount) {

    }

    /**
     * ????????????????????????????????????????????????????????????
     *
     * @param message ????????????
     */
    @Override
    public void onGetFriendshipMessage(List<TIMFriendFutureItem> message) {
        friendshipManagerPresenter.getFriendshipLastMessage();
    }

    /**
     * ????????????????????????????????????????????????
     *
     * @param message     ??????????????????
     * @param unreadCount ?????????
     */
    @Override
    public void onGetGroupManageLastMessage(TIMGroupPendencyItem message, long unreadCount) {

    }

    /**
     * ????????????????????????????????????
     *
     * @param message ?????????????????????
     */
    @Override
    public void onGetGroupManageMessage(List<TIMGroupPendencyItem> message) {

    }

    private long getTotalUnreadNum() {
        long num = 0;
        for (Conversation conversation : conversationList) {
            num += conversation.getUnreadNum();
        }
        return num;
    }

}
