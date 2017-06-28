package com.yumao.yumaosmart.adapter;

import android.content.Context;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.bean.MyVipBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by kk on 2017/3/15.
 */

public class MyVipAdapter extends CommonAdapter<MyVipBean> {

    public MyVipAdapter(Context context, int layoutId, List<MyVipBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, MyVipBean myVipBean, int position) {
        holder.setImageResource(R.id.iv_item_activity_my_vip, Integer.parseInt(myVipBean.getTouXiangRes()));
        holder.setText(R.id.tv_item_activity_my_vip_petname, myVipBean.getPetName());
        holder.setText(R.id.tv_item_activity_my_vip_time, myVipBean.getEnterTime());
        holder.setText(R.id.tv_item_activity_my_vip_num, myVipBean.getOrderNum());
    }
}
