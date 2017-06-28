package com.yumao.yumaosmart.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by kk on 2017/3/18.
 */

public class GoodsDetailActivityAdapter extends CommonAdapter<String> {

    private View mConvertView;
    private ImageView mItemIv;

    public GoodsDetailActivityAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        mConvertView = viewHolder.getConvertView();
        mItemIv = (ImageView) mConvertView.findViewById(R.id.iv_item_activity_goods_detail);

        Picasso.with(UiUtilities.getContex()).load(item).resize(600,600).into(mItemIv);
    }
}
