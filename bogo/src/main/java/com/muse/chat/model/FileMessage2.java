package com.muse.chat.model;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import android.widget.Toast;

import com.muse.chat.adapter.ChatAdapter2;
import com.muse.chat.utils.FileUtil;
import com.muse.xiangta.CuckooApplication;
import com.muse.xiangta.R;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMFileElem;
import com.tencent.imsdk.TIMGroupTipsElem;
import com.tencent.imsdk.TIMMessage;

/**
 * 文件消息
 */
public class FileMessage2 extends Message2 {


    public FileMessage2(TIMMessage message) {
        this.message = message;
    }

    public FileMessage2(String filePath) {
        message = new TIMMessage();
        TIMFileElem elem = new TIMFileElem();
        elem.setPath(filePath);
        elem.setFileName(filePath.substring(filePath.lastIndexOf("/") + 1));
        message.addElement(elem);
    }


    /**
     * 显示消息
     *
     * @param viewHolder 界面样式
     * @param context    显示消息的上下文
     */
    @Override
    public void showMessage(final ChatAdapter2.ViewHolder viewHolder, final Context context) {
        clearView(viewHolder);
        if (checkRevoke(viewHolder)) return;

        setSenderUserInfo(viewHolder, context, null);

        TIMGroupTipsElem e = (TIMGroupTipsElem) message.getElement(0);
        TextView tv = new TextView(CuckooApplication.getInstances());
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        tv.setTextColor(CuckooApplication.getInstances().getResources().getColor(isSelf() ? R.color.white : R.color.black));
        tv.setText(e.getGroupName());
        getBubbleView(viewHolder).addView(tv);
        showStatus(viewHolder);
    }

    /**
     * 获取消息摘要
     */
    @Override
    public String getSummary() {
        String str = getRevokeSummary();
        if (str != null) return str;
        return CuckooApplication.getInstances().getString(R.string.summary_file);
    }

    /**
     * 保存消息或消息文件
     */
    @Override
    public void save() {
        if (message == null) return;
        final TIMFileElem e = (TIMFileElem) message.getElement(0);
        String[] str = e.getFileName().split("/");
        String filename = str[str.length - 1];
        if (FileUtil.isFileExist(filename, Environment.DIRECTORY_DOWNLOADS)) {
            Toast.makeText(CuckooApplication.getInstances(), CuckooApplication.getInstances().getString(R.string.save_exist), Toast.LENGTH_SHORT).show();
            return;
        }

        e.getToFile(FileUtil.getCacheFilePath(filename), new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                Log.e(TAG, "getFile failed. code: " + i + " errmsg: " + s);
            }

            @Override
            public void onSuccess() {

            }
        });

    }
}
