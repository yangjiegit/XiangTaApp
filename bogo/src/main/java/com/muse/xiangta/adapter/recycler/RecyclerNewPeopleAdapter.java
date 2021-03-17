package com.muse.xiangta.adapter.recycler;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.muse.xiangta.CuckooApplication;
import com.muse.xiangta.R;
import com.muse.xiangta.api.ApiUtils;
import com.muse.xiangta.helper.SelectResHelper;
import com.muse.xiangta.json.jsonmodle.NewPeople;
import com.muse.xiangta.utils.StringUtils;
import com.muse.xiangta.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * RecyclerView-new people page适配器
 */
public class RecyclerNewPeopleAdapter extends BaseQuickAdapter<NewPeople,BaseViewHolder> {


    public RecyclerNewPeopleAdapter(@Nullable List<NewPeople> data) {
        super(R.layout.adapter_newpeople_list,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewPeople newPeople) {

        //数据绑定
        if (ApiUtils.isTrueUrl(newPeople.getAvatar())){
            Utils.loadHttpImg(CuckooApplication.getInstances(),Utils.getCompleteImgUrl(newPeople.getAvatar()), (ImageView) helper.getView(R.id.people_img));
        }

        helper.setBackgroundRes(R.id.newpeople_bar_isonLine,SelectResHelper.getOnLineRes(StringUtils.toInt(newPeople.getIs_online())));
        helper.setText(R.id.newpeople_bar_title,newPeople.getUser_nickname());
        helper.setText(R.id.newpeople_bar_location_text,newPeople.getAddress());

        if(StringUtils.toInt(newPeople.getSex()) == 1){
            helper.setBackgroundRes(R.id.tv_level,R.drawable.bg_org_num);
            helper.setText(R.id.tv_level,"V " + newPeople.getLevel());
        }else{
            helper.setBackgroundRes(R.id.tv_level,R.drawable.bg_main_color_num);
            helper.setText(R.id.tv_level,"M " + newPeople.getLevel());
        }
    }


}