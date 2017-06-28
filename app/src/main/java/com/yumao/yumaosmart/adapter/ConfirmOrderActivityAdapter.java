package com.yumao.yumaosmart.adapter;

import android.content.Context;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.mode.ShoppingCardMode;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by kk on 2017/4/10.
 */
public class ConfirmOrderActivityAdapter extends CommonAdapter<ShoppingCardMode> {


    public ConfirmOrderActivityAdapter(Context context, int layoutId, List<ShoppingCardMode> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, ShoppingCardMode item, int position) {
        viewHolder.setText(R.id.tv_item_activity_confirm_order_name, item.getProduct().getName());
        viewHolder.setText(R.id.tv_item_activity_confirm_order_amount,String.valueOf("×"+item.getQuantity()));
        viewHolder.setText(R.id.tv_item_activity_confirm_order_price, String.valueOf("￥"+item.getProduct().getPrice()));
    }
}
