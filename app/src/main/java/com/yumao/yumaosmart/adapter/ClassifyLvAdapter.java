package com.yumao.yumaosmart.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.fragment.ClassifyFragment;
import com.yumao.yumaosmart.mode.ClassiFyMode;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by kk on 2017/3/3.
 */

public class ClassifyLvAdapter extends CommonAdapter<ClassiFyMode> {
    private  Fragment mFragment;
    private int mId;

    //分类Fragment页面ListView
    public ClassifyLvAdapter(Context context, int layoutId, List<ClassiFyMode> datas, Fragment fragment) {
        super(context, layoutId, datas);
            mFragment=fragment;
    }

    @Override
    protected void convert(ViewHolder holder, final ClassiFyMode s, final int position) {
        holder.setText(R.id.tv_classify_title, s.getName());
        holder.setOnClickListener(R.id.tv_classify_title, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mId =   s.getId();
                v.setSelected(true);


                ClassifyFragment fragment = (ClassifyFragment) mFragment;
                fragment.initClassifyContent(position);
            }
        });
    }
}
