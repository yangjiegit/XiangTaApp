package com.muse.xiangta.wxpay;

import android.app.Activity;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ToastUtils;
import com.muse.xiangta.modle.ConfigModel;
import com.muse.xiangta.modle.Pay4Bean;
import com.muse.xiangta.modle.Pay4Bean2;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2016/4/14.
 */
public class WChatPayService {
    IWXAPI msgApi;

    private Activity context;

    public WChatPayService(Activity context) {
        this.context = context;
        // 将该app注册到微信
        msgApi = WXAPIFactory.createWXAPI(context, null);
        msgApi.registerApp("wx9d720ef784131129");
    }


    public void callWxPay(Pay4Bean2 pay4Bean) {
        Pay4Bean2.PayBean.PayInfoBean payInfoBean = pay4Bean.getPay().getPay_info();
        PayReq request = new PayReq();
        request.appId = "wx9d720ef784131129";
        request.partnerId = payInfoBean.getPartnerid();
        request.prepayId = payInfoBean.getPrepayid();
        request.packageValue = "Sign=WXPay";
        request.nonceStr = payInfoBean.getNoncestr();
        request.timeStamp = String.valueOf(payInfoBean.getTimestamp());
        request.sign = payInfoBean.getSign();
        msgApi.sendReq(request);
    }
}
