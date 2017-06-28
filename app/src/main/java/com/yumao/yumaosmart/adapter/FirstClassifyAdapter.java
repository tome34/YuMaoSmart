package com.yumao.yumaosmart.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.activity.FirstClassifyDetail;
import com.yumao.yumaosmart.bean.ClassifyBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 *
 * 首页Fragment里面的分类RecyclerView具体实现
 */

public class FirstClassifyAdapter extends CommonAdapter<ClassifyBean> implements View.OnClickListener {

       private Activity mActivity ;
    public FirstClassifyAdapter(Context context, int layoutId, List<ClassifyBean> datas,Activity activity) {
        super(context, layoutId, datas);
        this.mActivity=activity;
    }

    @Override
    protected void convert(ViewHolder holder, ClassifyBean classifyBean, int position) {

        holder.setImageResource(R.id.iv_first_rv_classify,Integer.parseInt(classifyBean.getResid()));
        holder.setText(R.id.tv_first_rv_classify, classifyBean.getName());
        holder.setOnClickListener(R.id.iv_first_rv_classify, this);
    }

//    首页分类点击事件
    @Override
    public void onClick(View v) {
        mActivity.startActivity(new Intent(mActivity,FirstClassifyDetail.class));
    }
}
