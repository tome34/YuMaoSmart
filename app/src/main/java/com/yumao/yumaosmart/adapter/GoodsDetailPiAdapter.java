package com.yumao.yumaosmart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.inter.OnItemClickListener;
import com.yumao.yumaosmart.utils.UiUtilities;

import java.util.List;

/**
 * Created by kk on 2017/7/12.
 */

public class GoodsDetailPiAdapter extends RecyclerView.Adapter<GoodsDetailPiAdapter.MyViewholder>  {

    private Context mContext;
    private List<String> mPictures;  //banner图集合

    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener = null;


    public GoodsDetailPiAdapter() {
    }

    public GoodsDetailPiAdapter(Context context, List<String> pictures){
        mContext = context ;
        mPictures = pictures;
        mInflater= LayoutInflater.from(context);

    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_goods_details_pi,parent,false);

        MyViewholder viewholder = new MyViewholder(view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {

       // holder.mIconIv.setImageResource(mImageList.get(position));
      // holder.image.setImageResource();
        Picasso.with(mContext).load(mPictures.get(position)).into(holder.image);

        //将position保存在itemView的tag中,以便点击时进行获取
       // holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mPictures.size();
    }


    public class MyViewholder extends RecyclerView.ViewHolder{

        public ImageView image;



        public MyViewholder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.item_goods_details_iv);

            DisplayMetrics dm = new DisplayMetrics();
            dm = UiUtilities.getContex().getResources().getDisplayMetrics();
            float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
            int densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
            int screenWidth = dm.widthPixels; // 屏幕宽（像素，如：3200px）
            int screenHeight = dm.heightPixels; // 屏幕高（像素，如：1280px）

            ViewGroup.LayoutParams params = itemView.getLayoutParams();
            params.width = screenWidth;
            //params.height = screenWidth;
            itemView.setLayoutParams(params);


        }

    }

}
