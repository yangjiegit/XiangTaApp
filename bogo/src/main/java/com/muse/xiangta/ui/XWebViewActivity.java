package com.muse.xiangta.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import com.muse.xiangta.R;
import com.muse.xiangta.widget.X5WebView;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XWebViewActivity extends AppCompatActivity {

    private String TAG = "XWebViewActivity";
    @BindView(R.id.view_group)
    ViewGroup mWebviewGroup;
    private X5WebView mX5WebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xweb_view);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        String url = getIntent().getStringExtra("url");
        Log.e(TAG, "init: "+url );
        mX5WebView = new X5WebView(this, null);
        mWebviewGroup.addView(mX5WebView);
        mX5WebView.loadUrl(url);
        mX5WebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {

                if (url == null) return false;

                try {
                    if (url.startsWith("http:") || url.startsWith("https:")) {
                        return true;
                    }  else {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }

                } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash) 返回true
                    return true;
                }

            }
        });
    }
}
