package com.yumao.yumaosmart.adapter;

import android.content.Context;

import com.yumao.yumaosmart.R;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by kk on 2017/3/7.
 */

public class TouXiangDialogAdapter extends CommonAdapter<String> {
    public TouXiangDialogAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
        holder.setText(R.id.tv_dialog_touxiang_material, s);
    }
}
