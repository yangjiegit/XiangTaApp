package com.muse.xiangta.ui;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.muse.xiangta.R;
import com.muse.xiangta.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class XieYiActivity extends BaseActivity {

    @BindView(R.id.wv_view)
    WebView wv_view;
    @BindView(R.id.tv_title)
    TextView tv_title;

    private String url = "", title = "";

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_xie_yi;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");

        tv_title.setText(title);

        wv_view.loadUrl(url);
        //系统默认会通过手机浏览器打开网页，为了能够直接通过WebView显示网页，则必须设置
        wv_view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //使用WebView加载显示url
                view.loadUrl(url);
                //返回true
                return true;
            }
        });
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
        }
    }
}
