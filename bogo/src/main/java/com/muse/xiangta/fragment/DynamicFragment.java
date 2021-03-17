package com.muse.xiangta.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.muse.xiangta.R;
import com.muse.xiangta.adapter.FragAdapter;
import com.muse.xiangta.api.Api;
import com.muse.xiangta.audiorecord.AudioPlaybackManager;
import com.muse.xiangta.base.BaseFragment;
import com.muse.xiangta.json.JsonGetIsAuth;
import com.muse.xiangta.json.JsonRequestBase;
import com.muse.xiangta.manage.SaveData;
import com.muse.xiangta.ui.PushDynamicActivity;
import com.lzy.okgo.callback.StringCallback;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUIViewPager;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 动态
 */
public class DynamicFragment extends BaseFragment {

    private QMUIViewPager rollViewViewpage;
    private QMUITabSegment dynamicTablayout;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private FragAdapter mDynamicFragAdapter;
    private ImageView iv_fabu;
//    private ImageView dynamicIv;

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_dynamic_layout, container, false);
    }

    @Override
    protected void initView(View view) {

        iv_fabu = view.findViewById(R.id.iv_fabu);
        dynamicTablayout = view.findViewById(R.id.tabLayout);
        rollViewViewpage = view.findViewById(R.id.roll_view_viewpage);

        iv_fabu.setOnClickListener(this);
//        dynamicIv = view.findViewById(R.id.iv_dynamic);
        //发动态
//        dynamicIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                clickPushDynamic();
//            }
//        });
    }

    @Override
    protected void initDate(View view) {
        fragmentList = new ArrayList();
        titleList = new ArrayList();

        fragmentList.add(new DynamicNearFragment());//附近  同城
        fragmentList.add(new DynamicRecommeFragment());//推荐  全部
        fragmentList.add(new DynamicAttentionFragment());//关注  关注
        fragmentList.add(new LoveFragment());//我的  爱情故事

        titleList.add("同城");//推荐  同城
        titleList.add("全部");//附近  全部
        titleList.add("关注");//关注  关注
        titleList.add("爱情故事");//我的  爱情故事

    }

    @Override
    protected void initSet(View view) {

        rollViewViewpage.setOffscreenPageLimit(3);
        mDynamicFragAdapter = new FragAdapter(getChildFragmentManager(), fragmentList);
        mDynamicFragAdapter.setTitleList(titleList);
        rollViewViewpage.setAdapter(mDynamicFragAdapter);


        dynamicTablayout.setTabTextSize(getResources().getDimensionPixelSize(R.dimen.home_tab_text));
        dynamicTablayout.setDefaultSelectedColor(getResources().getColor(R.color.main_tab_sel));
        dynamicTablayout.setDefaultNormalColor(getResources().getColor(R.color.main_tab_unsel));
        dynamicTablayout.setupWithViewPager(rollViewViewpage);
        dynamicTablayout.setHasIndicator(true);

        rollViewViewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //滑动监听
                if (position == 3) {
                    iv_fabu.setVisibility(View.GONE);
                } else {
                    iv_fabu.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //发布动态
    private void clickPushDynamic() {

        Api.doRequestGetIsAuth(SaveData.getInstance().getId(), SaveData.getInstance().getToken(), new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {
                JsonGetIsAuth data = (JsonGetIsAuth) JsonRequestBase.getJsonObj(s, JsonGetIsAuth.class);
                if (data.getCode() == 1) {
                    if (data.getIs_auth() == 1) {
                        Intent intent = new Intent(getContext(), PushDynamicActivity.class);
                        intent.putExtra("type", 2);
                        startActivity(intent);
                    } else {
                        showToastMsg(getContext(), getResources().getString(R.string.notAuth_hint) + "动态");

                    }
                } else {
                    showToastMsg(getContext(), getResources().getString(R.string.notAuth_hint) + "动态");

                }
            }
        });
    }

    @Override
    protected void initDisplayData(View view) {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_fabu:
                Intent intent = new Intent(getContext(), PushDynamicActivity.class);
                intent.putExtra("type", 2);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        AudioPlaybackManager.getInstance().stopAudio();
    }
}
