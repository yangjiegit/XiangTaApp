package com.muse.xiangta.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.muse.xiangta.R;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.base.BaseFragment;
import com.muse.xiangta.modle.BindInfoBean;
import com.muse.xiangta.modle.HintBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Response;

public class WealthBindFragment extends BaseFragment {

    Unbinder unbinder;

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_bind_pay, container, false);
    }

    @Override
    protected void initView(View view) {

    }

    @BindView(R.id.ck)
    RadioGroup group;

    @Override
    protected void initDate(View view) {
        Api.getAccountBind(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                log("绑定详情:" + s);
                BindInfoBean bean = null;
                try {
                    bean = new Gson().fromJson(s, BindInfoBean.class);
                } catch (Exception e) {

                }
                if (bean == null) return;
                ali.setText(bean.getData().getPay());
                wx.setText(bean.getData().getWx());
                name.setText(bean.getData().getName());
                group.check("1".equals(bean.getData().getType()) ? R.id.ck_zfb : R.id.ck_wx);
            }
        });
    }

    /*@Override
    protected void initDate(View view) {
        Api.getAccountBind(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                log("绑定详情:" + s);
                BindInfoBean bean = null;
                try {
                    bean = new Gson().fromJson(s, BindInfoBean.class);
                } catch (Exception e) {

                }
                if(bean==null)return;

                bank_account.setText(bean.getData().getBank_account());
                bank_cardno.setText(bean.getData().getBank_cardno());
                bank_name.setText(bean.getData().getBank_name());
                bank_addr.setText(bean.getData().getBank_addr());
            }
        });
    }*/

    @Override
    protected void initSet(View view) {

    }

    @Override
    protected void initDisplayData(View view) {

    }

    //      ee
    @BindView(R.id.ali)
    EditText ali;
    @BindView(R.id.wx)
    EditText wx;
    @BindView(R.id.name)
    EditText name;

    String type;

    @OnClick(R.id.to_drawing)
    public void onT() {
        switch (group.getCheckedRadioButtonId()) {
            case R.id.ck_zfb:
                type = "1";
                break;
            case R.id.ck_wx:
                type = "2";
                break;
        }
        Api.bindAccount(type, wx.getText().toString(), ali.getText().toString(), name.getText().toString(), new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                HintBean hint = new Gson().fromJson(s, HintBean.class);
                ToastUtils.showShort(hint.getMsg());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /*@BindView(R.id.bank_account)
    EditText bank_account;
    @BindView(R.id.bank_cardno)
    EditText bank_cardno;
    @BindView(R.id.bank_name)
    EditText bank_name;
    @BindView(R.id.bank_addr)
    EditText bank_addr;

    String type;
    @OnClick(R.id.to_drawing)
    public void onT() {
        Api.bindBank(bank_account.getText().toString(),bank_cardno.getText().toString(), bank_name.getText().toString(), bank_addr.getText().toString(), new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                HintBean hint = new Gson().fromJson(s, HintBean.class);
                ToastUtils.showShort(hint.getMsg());
            }
        });
    }*/
}
