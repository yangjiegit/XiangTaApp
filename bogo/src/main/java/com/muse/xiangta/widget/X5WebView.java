package com.muse.xiangta.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class X5WebView extends WebView {
    private final String TAG = "X5WebView";
    TextView title;
    private WebViewClient client = new WebViewClient() {
        /**
         * 防止加载网页时调起系统浏览器
         */
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };

    @SuppressLint("SetJavaScriptEnabled")
    public X5WebView(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
        this.setWebViewClient(client);
        // this.setWebChromeClient(chromeClient);
        // WebStorage webStorage = WebStorage.getInstance();
        initWebViewSettings();
        this.getView().setClickable(true);
    }

    private void initWebViewSettings() {
        WebSettings webSetting = this.getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
        // settings 的设计
        // Other Settings
//        webSetting.setSupportMultipleWindows(true);//设置支持多窗口

        webSetting.setSupportZoom(false);//设置支持缩放
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setUseWideViewPort(true);//将图片调整到适合WebView的大小
        webSetting.setDefaultTextEncodingName("utf-8");//设置默认字符编码
        webSetting.setLoadsImagesAutomatically(true);//设置图片自动加载
        // Other Settings
        webSetting.setJavaScriptEnabled(true);// 支持JS
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过js打开新的窗口
//        webSetting.setSupportMultipleWindows(true);//设置支持多窗口
        webSetting.setBuiltInZoomControls(false);// 设置支持缩放
        webSetting.setDomStorageEnabled(true);//使用localStorage则必须打开
        webSetting.setBlockNetworkImage(false);//  页面加载好以后，在放开图片
        webSetting.setNeedInitialFocus(false);// 禁止webview上面控件获取焦点(黄色边框)
        webSetting.setSupportMultipleWindows(false);
        //定位
        webSetting.setGeolocationEnabled(true);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        boolean ret = super.drawChild(canvas, child, drawingTime);

//        canvas.save();
//        Paint paint = new Paint();
//        paint.setColor(0x7fff0000);
//        paint.setTextSize(24.f);
//        paint.setAntiAlias(true);

        if (getX5WebViewExtension() != null) {
//            canvas.drawText(this.getContext().getPackageName() + "-pid:"
//                    + android.os.Process.myPid(), 10, 50, paint);
//            canvas.drawText(
//                    "X5  Core:" + QbSdk.getTbsVersion(this.getContext()), 10,
//                    100, paint);

            Log.e(TAG, "drawChild X5  Core:" + QbSdk.getTbsVersion(this.getContext()));
        } else {

//            canvas.drawText(this.getContext().getPackageName() + "-pid:"
//                    + android.os.Process.myPid(), 10, 50, paint);
//            canvas.drawText("Sys Core", 10, 100, paint);

            Log.e(TAG, "drawChild Sys  Core");
        }

//        canvas.drawText(Build.MANUFACTURER, 10, 150, paint);
//        canvas.drawText(Build.MODEL, 10, 200, paint);
//        canvas.restore();
        return ret;
    }

    public X5WebView(Context arg0) {
        super(arg0);
        setBackgroundColor(85621);
        initWebViewSettings();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " );
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " );
    }

    @Override
    public void resumeTimers() {
        super.resumeTimers();
        Log.e(TAG, "resumeTimers: " );
    }

    @Override
    public void pauseTimers() {
        super.pauseTimers();
        Log.e(TAG, "pauseTimers: " );
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "onMeasure: widthMeasureSpec-"+widthMeasureSpec+";widthMeasureSpec-"+heightMeasureSpec );
    }

}
