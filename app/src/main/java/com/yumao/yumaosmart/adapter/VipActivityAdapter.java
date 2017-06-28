package com.yumao.yumaosmart.adapter;

import android.content.Context;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.bean.VipOrderListBean;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by kk on 2017/3/14.
 */

public class VipActivityAdapter extends CommonAdapter<VipOrderListBean> {
    public VipActivityAdapter(Context context, int layoutId, List<VipOrderListBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, VipOrderListBean item, int position) {
        viewHolder.setText(R.id.tv_item_viporderlist_serialnumber, item.getOrderSerialnum());
        viewHolder.setText(R.id.tv_item_viporderlist_orderstate, item.getOrderState());
        viewHolder.setImageResource(R.id.iv_item_viporderlist_touxiang, Integer.parseInt(item.getTouXiangRes()));
        viewHolder.setText(R.id.tv_item_viporderlist_petname, item.getPetName());
        viewHolder.setText(R.id.tv_item_viporderlist_ordertime, item.getOrderTime());
        viewHolder.setText(R.id.tv_item_viporderlist_totalmoney, item.getOrderTotalMoney());
        viewHolder.setText(R.id.tv_item_viporderlist_citypartner, item.getCityPartner());
        viewHolder.setText(R.id.tv_item_viporderlist_centerpartner,item.getCenterPartner());
    }
}
