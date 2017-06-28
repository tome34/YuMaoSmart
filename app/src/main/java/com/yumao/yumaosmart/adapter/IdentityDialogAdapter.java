package com.yumao.yumaosmart.adapter;

import android.content.Context;

import com.yumao.yumaosmart.R;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by kk on 2017/3/9.
 */

public class IdentityDialogAdapter extends CommonAdapter<String>{
    public IdentityDialogAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        viewHolder.setText(R.id.tv_dialog_touxiang_material, item);
    }
}
