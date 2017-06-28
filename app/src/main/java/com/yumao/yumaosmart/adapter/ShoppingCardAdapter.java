package com.yumao.yumaosmart.adapter;


import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.bean.ShoppingCardbean;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by kk on 2017/3/3.
 */

public class ShoppingCardAdapter extends CommonAdapter<ShoppingCardbean> {
        private  ViewHolder mViewHolder;
    public ShoppingCardAdapter(Context context, int layoutId, List<ShoppingCardbean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, ShoppingCardbean item, int position) {
        mViewHolder=viewHolder;
        viewHolder.setText(R.id.tv_shopping_card_storename, item.getStoreName());
        viewHolder.setText(R.id.tv_shopping_goods_detail, item.getGoodsName());
        viewHolder.setText(R.id.tv_shopping_price, item.getPrice());
        viewHolder.setText(R.id.et_shopping_card_edit, item.getNum());
        viewHolder.setImageResource(R.id.iv_shopping_card_goods, Integer.parseInt(item.getGoodsRes()));
        initListenner();
    }

    private void initListenner() {
        mViewHolder.setOnClickListener(R.id.iv_shopping_card_goods, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "购物车条目被点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
