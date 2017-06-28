package com.yumao.yumaosmart.adapter;

import android.content.Context;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.bean.OrderListBean;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by kk on 2017/3/10.
 */

public class OrderListDataAdapter extends CommonAdapter<OrderListBean> {
    public OrderListDataAdapter(Context context, int layoutId, List<OrderListBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, OrderListBean item, int position) {
        viewHolder.setText(R.id.tv_item_orderlist_serialnumber, item.getOrderlistSerialNumber());
        viewHolder.setText(R.id.tv_item_orderlist_orderstate, item.getOrderlistState());
        viewHolder.setText(R.id.tv_item_orderlist_goods_mallname,item.getMallName());
        viewHolder.setText(R.id.tv_item_orderlist_goods_detail, item.getGoodsDetail());
        viewHolder.setText(R.id.tv_item_orderlist_goods_price, item.getGoodsPrice());
        viewHolder.setText(R.id.tv_item_orderlist_total, item.getGoodsTotal());
        viewHolder.setChecked(R.id.rbtn_item_orderlist_isselected, item.isSelected());
        viewHolder.setText(R.id.item_orderlist_goodsnumber, item.getGoodsNumber());
        viewHolder.setImageResource(R.id.iv_item_orderlist_goods_logo, Integer.parseInt(item.getLogoRes()));
        viewHolder.setImageResource(R.id.iv_item_orderlist_goods,Integer.parseInt(item.getGoodsPictureRes()));

    }
}
