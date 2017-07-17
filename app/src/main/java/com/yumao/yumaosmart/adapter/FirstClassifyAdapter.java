package com.yumao.yumaosmart.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.inter.OnItemClickListener;
import com.yumao.yumaosmart.utils.UiUtilities;

import java.util.List;

/**
 *
 * 首页Fragment里面的分类RecyclerView具体实现
 */

public class FirstClassifyAdapter extends RecyclerView.Adapter<FirstClassifyAdapter.MyViewholder> implements View.OnClickListener {

    private Context mContext;
    private List<String> mClassifyName ;//分类名称
    private List<Boolean> mContainsList ;//分类是否显示
    private List<Integer> mImageList; //显示图标
    private List<Integer> mImageUnList; //不显示图标

    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener = null;


    public FirstClassifyAdapter() {
    }

    public FirstClassifyAdapter(Context context, List<Integer> imageList,List<Integer> imageUnList,List<String> classifyName,List<Boolean> containsList ){
        mContext = context ;
        mImageList = imageList ;
        mImageUnList =imageUnList;
        mClassifyName = classifyName;
        mContainsList = containsList;
        mInflater= LayoutInflater.from(context);

    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_first_classify,parent,false);

        MyViewholder viewholder = new MyViewholder(view);

        //将创建的view注册点击事件
        view.setOnClickListener(this);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {

        // holder.mIconIv.setImageResource(mImageList.get(position));

        holder.mName.setText(mClassifyName.get(position));

       if (mContainsList.get(position)){
            holder.mIconIv.setImageResource(mImageList.get(position));
        }else{
           holder.mIconIv.setImageResource(mImageUnList.get(position));
        }

        //将position保存在itemView的tag中,以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    @Override
    public void onClick(View v) {
        //使用getTag方法获取position
        if(mOnItemClickListener !=null){
            mOnItemClickListener.onItenClick(v,(int)v.getTag());
        }
    }

    public class MyViewholder extends RecyclerView.ViewHolder{

        public TextView mName;
        public ImageView mIconIv;


        public MyViewholder(View itemView) {
            super(itemView);
            mIconIv = (ImageView) itemView.findViewById(R.id.iv_first_rv_classify);
            mName = (TextView) itemView.findViewById(R.id.tv_first_rv_classify);


            DisplayMetrics dm = new DisplayMetrics();
            dm = UiUtilities.getContex().getResources().getDisplayMetrics();
            float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
            int densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
            int screenWidth = dm.widthPixels; // 屏幕宽（像素，如：3200px）
            int screenHeight = dm.heightPixels; // 屏幕高（像素，如：1280px）

            ViewGroup.LayoutParams params = itemView.getLayoutParams();
            params.width = screenWidth/4;

            itemView.setLayoutParams(params);
        }

    }

    public void setOnItemClickListener(OnItemClickListener listener){

        this.mOnItemClickListener = listener ;
    }
}
