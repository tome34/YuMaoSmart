package com.yumao.yumaosmart.adapter;

import android.content.Context;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.bean.MyPopularityBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by kk on 2017/3/15.
 *  viewHolder.setText(R.id.tv_item_fragment_mypopularity_favour, String.valueOf("+"+homeBean.getFavour()));
 viewHolder.setText(R.id.tv_item_fragment_mypopularity_petname, homeBean.getPetName());
 viewHolder.setText(R.id.tv_item_fragment_mypopularity_time, homeBean.getTime());
 */

public class MyPopularityAdapter extends CommonAdapter<MyPopularityBean> {

    public MyPopularityAdapter(Context context, int layoutId, List<MyPopularityBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, MyPopularityBean myPopularityBean, int position) {
        holder.setText(R.id.tv_item_fragment_mypopularity_favour, String.valueOf("+"+myPopularityBean.getFavour()));
        holder.setText(R.id.tv_item_fragment_mypopularity_petname, myPopularityBean.getPetName());
        holder.setText(R.id.tv_item_fragment_mypopularity_time, myPopularityBean.getTime());
        holder.setImageResource(R.id.iv_item_fragment_my_popularity, Integer.parseInt(myPopularityBean.getTouXiangRes()));
    }
}
