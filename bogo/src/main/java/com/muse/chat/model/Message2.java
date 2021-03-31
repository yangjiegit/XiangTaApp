package com.muse.chat.model;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.muse.chat.adapter.ChatAdapter2;
import com.muse.chat.ui.ChatActivity2;
import com.muse.chat.utils.TimeUtil;
import com.muse.xiangta.R;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.modle.UserModel;
import com.muse.xiangta.utils.Utils;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageStatus;
import com.tencent.imsdk.ext.message.TIMMessageExt;

/**
 * 消息数据基类
 */
public abstract class Message2 {

    protected final String TAG = "Message";

    TIMMessage message;

    private String name;

    private String avatar;

    public boolean hasTime;

    /**
     * 消息描述信息
     */
    private String desc;


    public TIMMessage getMessage() {

        return message;
    }

    public void setSendData(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }


    /**
     * 显示消息
     *
     * @param viewHolder 界面样式
     * @param context    显示消息的上下文
     */
    public abstract void showMessage(ChatAdapter2.ViewHolder viewHolder, Context context);

    /**
     * 获取显示气泡
     *
     * @param viewHolder 界面样式
     */
    public RelativeLayout getBubbleView(ChatAdapter2.ViewHolder viewHolder) {
        viewHolder.systemMessage.setVisibility(hasTime ? View.VISIBLE : View.GONE);
        viewHolder.systemMessage.setText(TimeUtil.getChatTimeStr(message.timestamp()));
        showDesc(viewHolder);
        if (message.isSelf()) {
            viewHolder.leftPanel.setVisibility(View.GONE);
            viewHolder.rightPanel.setVisibility(View.VISIBLE);
            viewHolder.rightMessage.setBackgroundResource(R.drawable.bg_bubble_blue);
            return viewHolder.rightMessage;
        } else {
            viewHolder.leftPanel.setVisibility(View.VISIBLE);
            viewHolder.rightPanel.setVisibility(View.GONE);
            viewHolder.leftMessage.setBackgroundResource(R.drawable.bg_bubble_gray);
            return viewHolder.leftMessage;
        }

    }

    /**
     * 获取显示气泡
     *
     * @param viewHolder 界面样式
     */
    public void setSenderUserInfo(final ChatAdapter2.ViewHolder viewHolder, final Context context, UserModel userModel) {

        if (message.isSelf()) {
            viewHolder.sender.setText(SaveData.getInstance().getUserInfo().getUser_nickname());
            Utils.loadHttpImg(SaveData.getInstance().getUserInfo().getAvatar(), viewHolder.rightAvatar);
        } else {
            viewHolder.sender.setText(name);
            Utils.loadHttpImg(avatar, viewHolder.leftAvatar);
        }

//        viewHolder.sender.setText(userModel.getUser_nickname());
//        if (message.isSelf()){
//            Utils.loadHttpImg(userModel.getAvatar(),viewHolder.rightAvatar);
//            return viewHolder.rightMessage;
//        }else{
//
//            Utils.loadHttpImg(userModel.getAvatar(),viewHolder.leftAvatar);
//            return viewHolder.leftMessage;
//        }

//
//        TIMFriendshipManager.getInstance().getUsersProfile(userIds, new TIMValueCallBack<List<TIMUserProfile>>() {
//            @Override
//            public void onError(int i, String s) {
//
//            }
//
//            @Override
//            public void onSuccess(List<TIMUserProfile> timUserProfiles) {
//                LogUtils.d(timUserProfiles);
//                if(timUserProfiles.size() != 0){
//                    viewHolder.sender.setText(timUserProfiles.get(0).getNickName());
//                    Utils.loadHttpIconImg(context,timUserProfiles.get(0).getFaceUrl(),(message.isSelf() ? viewHolder.rightAvatar : viewHolder.leftAvatar),0);
//                }
//            }
//        });

    }

    /**
     * 显示消息状态
     *
     * @param viewHolder 界面样式
     */
    public void showStatus(ChatAdapter2.ViewHolder viewHolder) {
        switch (message.status()) {
            case Sending:
                viewHolder.error.setVisibility(View.GONE);
                viewHolder.sending.setVisibility(View.VISIBLE);
                break;
            case SendSucc:
                viewHolder.error.setVisibility(View.GONE);
                viewHolder.sending.setVisibility(View.GONE);
                break;
            case SendFail:
                viewHolder.error.setVisibility(View.VISIBLE);
                viewHolder.sending.setVisibility(View.GONE);
                viewHolder.leftPanel.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 判断是否是自己发的
     */
    public boolean isSelf() {
        return message.isSelf();
    }

    /**
     * 获取消息摘要
     */
    public abstract String getSummary();

    String getRevokeSummary() {
        if (message.status() == TIMMessageStatus.HasRevoked) {
            return getSender() + "撤回了一条消息";
        }
        return null;
    }

    /**
     * 保存消息或消息文件
     */
    public abstract void save();


    /**
     * 删除消息
     */
    public void remove() {
        TIMMessageExt ext = new TIMMessageExt(message);
        ext.remove();
    }


    /**
     * 是否需要显示时间获取
     */
    public boolean getHasTime() {
        return hasTime;
    }


    /**
     * 是否需要显示时间设置
     *
     * @param message 上一条消息
     */
    public void setHasTime(TIMMessage message) {
        if (message == null) {
            hasTime = true;
            return;
        }
        hasTime = this.message.timestamp() - message.timestamp() > 300;
    }


    /**
     * 消息是否发送失败
     */
    public boolean isSendFail() {
        return message.status() == TIMMessageStatus.SendFail;
    }

    /**
     * 清除气泡原有数据
     */
    protected void clearView(ChatAdapter2.ViewHolder viewHolder) {
        getBubbleView(viewHolder).removeAllViews();
        getBubbleView(viewHolder).setOnClickListener(null);
    }

    /**
     * 显示撤回的消息
     */
    boolean checkRevoke(ChatAdapter2.ViewHolder viewHolder) {
        if (message.status() == TIMMessageStatus.HasRevoked) {
            viewHolder.leftPanel.setVisibility(View.GONE);
            viewHolder.rightPanel.setVisibility(View.GONE);
            viewHolder.systemMessage.setVisibility(View.VISIBLE);
            viewHolder.systemMessage.setText(getSummary());
            return true;
        }
        return false;
    }

    /**
     * 获取发送者
     */
    public String getSender() {
        if (message.getSender() == null) return "";
        return message.getSender();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    private void showDesc(ChatAdapter2.ViewHolder viewHolder) {

        if (desc == null || desc.equals("")) {
            viewHolder.rightDesc.setVisibility(View.GONE);
        } else {
            viewHolder.rightDesc.setVisibility(View.VISIBLE);
            viewHolder.rightDesc.setText(desc);
        }
    }
}
