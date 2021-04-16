package com.muse.xiangta.ui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.muse.xiangta.R;
import com.muse.xiangta.base.BaseActivity;
import com.muse.xiangta.manage.RequestConfig;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_code)
    TextView tv_code;

    @Override
    protected Context getNowContext() {
        return this;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_aboutus;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @OnClick({R.id.iv_back, R.id.ll_xieyi, R.id.ll_yinsi, R.id.ll_dianhua})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_xieyi:
                String intStr1 = "12";
                String url1 = "http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/" + intStr1 + ".html";
                startActivity(new Intent(this, XieYiActivity.class)
                        .putExtra("title", "用户协议").putExtra("url", url1));
                break;
            case R.id.ll_yinsi:
                String intStr = "7";
                String url = "http://xiangta.zzmzrj.com/admin/public/index.php/page/article/index/id/" + intStr + ".html";
                startActivity(new Intent(this, XieYiActivity.class)
                        .putExtra("title", "隐私协议").putExtra("url", url));
                break;
            case R.id.ll_dianhua:
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tv_phone.getText().toString().trim()));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
                break;

        }
    }

    @Override
    protected void initData() {
        tv_phone.setText(RequestConfig.getConfigObj().getCustom_service_phone() + "");

        tv_code.setText("V" + StringgetAppVersionName());
    }


    /**
     * 获取版本号
     *
     * @return 版本号
     */

    public String StringgetAppVersionName() {
        String versionName = "";
        try {
            PackageManager pm = getPackageManager();
            PackageInfo p1 = pm.getPackageInfo(getPackageName(), 0);
            versionName = p1.versionName;
            if (TextUtils.isEmpty(versionName) || versionName.length() <= 0) {
                return "";
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }


    @Override
    protected void initPlayerDisplayData() {

    }

}
