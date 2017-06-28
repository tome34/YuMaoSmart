package com.yumao.yumaosmart.adapter;

import android.content.Context;

import com.yumao.yumaosmart.R;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by kk on 2017/3/18.
 */

public class BrandStoreAdapter extends CommonAdapter<String> {
    public BrandStoreAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        viewHolder.setImageResource(R.id.iv_item_fragment_brand_store, Integer.parseInt(item));
    }
}
