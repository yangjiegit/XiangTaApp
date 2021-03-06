package com.tencent.qcloud.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.qcloud.presentation.viewfeatures.ChatView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 聊天界面输入控件
 */
public class ChatInput extends RelativeLayout implements TextWatcher, View.OnClickListener {

    private static final String TAG = "ChatInput";

    private ImageButton btnKeyboard, btnVoice;
    private EditText editText;
    private boolean isSendVisible, isHoldVoiceBtn, isEmoticonReady;
    private InputMode inputMode = InputMode.NONE;
    private ChatView chatView;
    private LinearLayout morePanel, textPanel;
    private TextView voicePanel;
    private LinearLayout emoticonPanel;
    private final int REQUEST_CODE_ASK_PERMISSIONS = 100;
    private float startY;
    private boolean mCancelSend;

    public ChatInput(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.chat_input, this);
        initView();
    }


    private void initView() {
        textPanel = (LinearLayout) findViewById(R.id.text_panel);
        morePanel = (LinearLayout) findViewById(R.id.morePanel);//功能列表
        btnVoice = (ImageButton) findViewById(R.id.btn_voice);
        setSendBtn();
        btnKeyboard = (ImageButton) findViewById(R.id.btn_keyboard);
        btnKeyboard.setOnClickListener(this);
        voicePanel = (TextView) findViewById(R.id.voice_panel);
        voicePanel.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startY = event.getY();
                        isHoldVoiceBtn = true;
                        updateVoiceView();
                        break;
                    case MotionEvent.ACTION_UP:
                        float endY = event.getY();
                        isHoldVoiceBtn = false;
                        updateVoiceView();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        float moveY = event.getY();
                        int instance = (int) Math.abs((moveY - startY));
                        if (instance > 100) {
                            changeAudioDialogCancel(true);
                        } else {
                            changeAudioDialogCancel(false);
                        }
                        break;
                }
                return true;
            }
        });
        editText = (EditText) findViewById(R.id.input);
        editText.addTextChangedListener(this);
        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    updateView(InputMode.TEXT);
                }
            }
        });
        isSendVisible = editText.getText().length() != 0;
        emoticonPanel = (LinearLayout) findViewById(R.id.emoticonPanel);


        findViewById(R.id.iv_image).setOnClickListener(this);
        findViewById(R.id.iv_si).setOnClickListener(this);
        findViewById(R.id.iv_video).setOnClickListener(this);
        findViewById(R.id.iv_hong).setOnClickListener(this);
        findViewById(R.id.iv_liwu).setOnClickListener(this);
        findViewById(R.id.iv_cai).setOnClickListener(this);
        findViewById(R.id.iv_shai).setOnClickListener(this);
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btnEmoticon).setOnClickListener(this);
        findViewById(R.id.btn_send).setOnClickListener(this);
        findViewById(R.id.btn_voice).setOnClickListener(this);

    }


    public void setSex(int sex) {
        if (sex == 1) {//判断私照
//            imgLl.setVisibility(GONE);
        } else {
//            imgLl.setVisibility(GONE);
        }
    }

    private void changeAudioDialogCancel(boolean cancel) {
        if (mCancelSend == cancel)
            return;

        chatView.cancelSendVoice(mCancelSend);

        mCancelSend = cancel;
    }


    private void updateView(InputMode mode) {
        if (mode == inputMode) return;
        leavingCurrentState();
        switch (inputMode = mode) {
            case MORE:
                morePanel.setVisibility(VISIBLE);
                break;
            case TEXT:
                if (editText.requestFocus()) {
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                }
                break;
            case VOICE:
                voicePanel.setVisibility(VISIBLE);
                textPanel.setVisibility(GONE);
                btnVoice.setVisibility(GONE);
                btnKeyboard.setVisibility(VISIBLE);
                break;
            case EMOTICON:
                if (!isEmoticonReady) {
                    prepareEmoticon();
                }
                emoticonPanel.setVisibility(VISIBLE);
                break;
        }
    }

    private void leavingCurrentState() {
        switch (inputMode) {
            case TEXT:
                View view = ((Activity) getContext()).getCurrentFocus();
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                editText.clearFocus();
                break;
            case MORE:
                morePanel.setVisibility(GONE);
                break;
            case VOICE:
                voicePanel.setVisibility(GONE);
                textPanel.setVisibility(VISIBLE);
                btnVoice.setVisibility(VISIBLE);
                btnKeyboard.setVisibility(GONE);
                break;
            case EMOTICON:
                emoticonPanel.setVisibility(GONE);
        }
    }


    private void updateVoiceView() {
        if (isHoldVoiceBtn) {
            voicePanel.setText(getResources().getString(R.string.chat_release_send));
            voicePanel.setBackground(getResources().getDrawable(R.drawable.btn_voice_pressed));
            chatView.startSendVoice();
        } else {
            voicePanel.setText(getResources().getString(R.string.chat_press_talk));
            voicePanel.setBackground(getResources().getDrawable(R.drawable.btn_voice_normal));
            chatView.endSendVoice();
        }
    }


    /**
     * 关联聊天界面逻辑
     */
    public void setChatView(ChatView chatView) {
        this.chatView = chatView;
    }

    /**
     * This method is called to notify you that, within <code>s</code>,
     * the <code>count</code> characters beginning at <code>start</code>
     * are about to be replaced by new text with length <code>after</code>.
     * It is an error to attempt to make changes to <code>s</code> from
     * this callback.
     *
     * @param s
     * @param start
     * @param count
     * @param after
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    /**
     * This method is called to notify you that, within <code>s</code>,
     * the <code>count</code> characters beginning at <code>start</code>
     * have just replaced old text that had length <code>before</code>.
     * It is an error to attempt to make changes to <code>s</code> from
     * this callback.
     *
     * @param s
     * @param start
     * @param before
     * @param count
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        isSendVisible = s != null && s.length() > 0;
        setSendBtn();
        if (isSendVisible) {
            chatView.sending();
        }
    }

    /**
     * This method is called to notify you that, somewhere within
     * <code>s</code>, the text has been changed.
     * It is legitimate to make further changes to <code>s</code> from
     * this callback, but be careful not to get yourself into an infinite
     * loop, because any changes you make will cause this method to be
     * called again recursively.
     * (You are not told where the change took place because other
     * afterTextChanged() methods may already have made other changes
     * and invalidated the offsets.  But if you need to know here,
     * you can use {@link Spannable#setSpan} in {@link #onTextChanged}
     * to mark your place and then look up from here where the span
     * ended up.
     *
     * @param s
     */
    @Override
    public void afterTextChanged(Editable s) {

    }

    private void setSendBtn() {
        if (isSendVisible) {
            //btnAdd.setVisibility(GONE);
            //btnSend.setVisibility(VISIBLE);
        } else {
            //btnAdd.setVisibility(VISIBLE);
            //btnSend.setVisibility(GONE);
        }
    }

    private void prepareEmoticon() {
        if (emoticonPanel == null) return;
        for (int i = 0; i < 5; ++i) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f));
            for (int j = 0; j < 7; ++j) {
                try {
                    AssetManager am = getContext().getAssets();
                    final int index = 7 * i + j;
                    InputStream is = am.open(String.format("emoticon/%d.png", index));
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    Matrix matrix = new Matrix();
                    int width = bitmap.getWidth();
                    int height = bitmap.getHeight();
                    matrix.postScale(1.0f, 1.0f);
                    final Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                            width, height, matrix, true);
                    ImageView image = new ImageView(getContext());
                    image.setImageBitmap(resizedBitmap);
                    image.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
                    linearLayout.addView(image);
                    image.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String content = String.valueOf(index);
                            SpannableString str = new SpannableString(String.valueOf(index));
                            ImageSpan span = new ImageSpan(getContext(), resizedBitmap, ImageSpan.ALIGN_BASELINE);
                            str.setSpan(span, 0, content.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            editText.append(str);
                        }
                    });
                    is.close();
                } catch (IOException e) {

                }

            }
            emoticonPanel.addView(linearLayout);
        }
        isEmoticonReady = true;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Activity activity = (Activity) getContext();
        int id = v.getId();
        //视频    礼物
        if (v.getId() == R.id.iv_video || v.getId() == R.id.iv_liwu || v.getId() == R.id.iv_si
                || v.getId() == R.id.iv_hong || v.getId() == R.id.iv_cai || v.getId() == R.id.iv_shai) {
            chatView.onAction(v.getId());
            return;
        }
        if (id == R.id.btn_send) {
            chatView.sendText();
        }
        if (id == R.id.btn_add) {
            updateView(inputMode == InputMode.MORE ? InputMode.TEXT : InputMode.MORE);
        }
//        if (id == R.id.iv_add_img) { //拍照
//            if (activity != null && requestCamera(activity)) {
////                chatView.sendPhoto();
//                Toast.makeText(getContext(), "该功能尚未开放", Toast.LENGTH_SHORT).show();
//            }
//        }
        if (id == R.id.iv_image) {//图片
            if (activity != null && requestStorage(activity)) {
                chatView.sendImage();
            }
        }
        if (id == R.id.btn_voice) {//语音消息
            if (activity != null && requestAudio(activity)) {
                updateView(InputMode.VOICE);
            }
        }
        if (id == R.id.btn_keyboard) {//语音文字切换
            updateView(InputMode.TEXT);
        }
//        if (id == R.id.btn_video) {//小视频
//            if (getContext() instanceof FragmentActivity) {
//                FragmentActivity fragmentActivity = (FragmentActivity) getContext();
//                if (requestVideo(fragmentActivity)) {
////                    VideoInputDialog.show(fragmentActivity.getSupportFragmentManager());
//                    if (requestRtmp()) {
//                        chatView.videoAction();
//                    } else {
//                        Toast.makeText(activity, "系统版本太低", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            }
//        }
        if (id == R.id.btnEmoticon) {//表情
            updateView(inputMode == InputMode.EMOTICON ? InputMode.TEXT : InputMode.EMOTICON);
        }
//        if (id == R.id.iv_hong) {
//            Toast.makeText(getContext(), "该功能尚未开放", Toast.LENGTH_SHORT).show();
//        }
//        if (id == R.id.btn_file) {//文件
//            chatView.sendFile();
//        }
    }


    /**
     * 获取输入框文字
     */
    public Editable getText() {
        return editText.getText();
    }

    /**
     * 设置输入框文字
     */
    public void setText(String text) {
        editText.setText(text);
    }


    /**
     * 设置输入模式
     */
    public void setInputMode(InputMode mode) {
        updateView(mode);
    }

    /**
     * 设置coin数据
     *
     * @param coin
     */
    public void setCoinData(int coin, String name) {
        if (coin != 0) {
//            editText.setHint(coin + name + "/次");
        }
    }


    public enum InputMode {
        TEXT,
        VOICE,
        EMOTICON,
        MORE,
        VIDEO,
        NONE,
    }

    private boolean requestRtmp() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    private boolean requestVideo(Activity activity) {
        if (afterM()) {
            final List<String> permissionsList = new ArrayList<>();
            if ((activity.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.CAMERA);
            if ((activity.checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.RECORD_AUDIO);
            if (permissionsList.size() != 0) {
                activity.requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                        REQUEST_CODE_ASK_PERMISSIONS);
                return false;
            }
            int hasPermission = activity.checkSelfPermission(Manifest.permission.CAMERA);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{Manifest.permission.CAMERA},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return false;
            }
        }
        return true;
    }

    private boolean requestCamera(Activity activity) {
        if (afterM()) {
            int hasPermission = activity.checkSelfPermission(Manifest.permission.CAMERA);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{Manifest.permission.CAMERA},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return false;
            }
        }
        return true;
    }

    private boolean requestAudio(Activity activity) {
        if (afterM()) {
            int hasPermission = activity.checkSelfPermission(Manifest.permission.RECORD_AUDIO);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return false;
            }
        }
        return true;
    }

    private boolean requestStorage(Activity activity) {
        if (afterM()) {
            int hasPermission = activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return false;
            }
        }
        return true;
    }

    private boolean afterM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

}
