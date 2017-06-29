package com.yumao.yumaosmart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yumao.yumaosmart.R;

import java.util.List;


/**
 * Created by kk on 2017/3/6.
 */

public class PersonnalcenterAdapter extends RecyclerView.Adapter<PersonnalcenterAdapter.MyViewHolder> {


    private List<String> mNameData ;
    private List<Integer> mIconData ;
    private Context mContext;
    private LayoutInflater mInflater;

    public PersonnalcenterAdapter() {

    }

    public PersonnalcenterAdapter(Context context, List<String> mdata ,List<Integer> mdata2){
        mNameData = mdata ;
        mIconData = mdata2 ;
        mContext = context;
        mInflater=LayoutInflater.from(context);

    }

    //item显示类型
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_personnal_rv,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    //数据的绑定显示
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.item_name.setText(mNameData.get(position));
        holder.item_icon.setImageResource(mIconData.get(position));
    }

    //总数
    @Override
    public int getItemCount() {
        return mNameData.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView item_name;
        public ImageView item_icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            item_name = (TextView)itemView.findViewById(R.id.tv_personnal_item);
            item_icon = (ImageView) itemView.findViewById(R.id.iv_personnal_item);
        }
    }
}


