package com.yumao.yumaosmart.adapter;

import android.content.Context;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.bean.GoodsUploadBean;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by kk on 2017/3/22.
 */

public class WeiShangjiaAdapter extends CommonAdapter<GoodsUploadBean> {

    public WeiShangjiaAdapter(Context context, int layoutId, List<GoodsUploadBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, GoodsUploadBean item, int position) {
        viewHolder.setImageResource(R.id.iv_item_fragment_yishangjia, Integer.parseInt(item.getPictureRes()));
        viewHolder.setChecked(R.id.rbtn_item_fragment_yishangjia, item.isSelected());
        viewHolder.setText(R.id.tv_item_fragment_yishangjia_name, item.getName());
        viewHolder.setText(R.id.tv_item_fragment_yishangjia_num, item.getSerialNum());
        viewHolder.setText(R.id.tv_item_fragment_yishangjia_price, item.getPrice());
        viewHolder.setText(R.id.tv_item_fragment_yishangjia_reason, item.getReason());
        viewHolder.setText(R.id.tv_item_fragment_yishangjia_time, item.getTime());
    }
}
