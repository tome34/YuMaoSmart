package com.yumao.yumaosmart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.utils.LogUtils;

import java.util.List;

/**
 * Created by kk on 2017/7/12.
 */

public class GoodsDetailSpAdapter extends RecyclerView.Adapter<GoodsDetailSpAdapter.MyViewholder>  {

    private Context mContext;
    private List<String> mDetailStringList; //商品参数 中文key

    private LayoutInflater mInflater;


    public GoodsDetailSpAdapter() {
    }

    public GoodsDetailSpAdapter(Context context, List<String> detailStringList){
        mContext = context ;
        mDetailStringList = detailStringList;
        mInflater= LayoutInflater.from(context);

    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_goods_details_sp,parent,false);

        MyViewholder viewholder = new MyViewholder(view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {

       // holder.mIconIv.setImageResource(mImageList.get(position));
       //holder.title.setText();
        String s = mDetailStringList.get(position);
        String[] strArr = s.split(":");
            LogUtils.d("tag",strArr[0]+" "+strArr[1]);
        //去掉字符串的双引号
        String ss = strArr[0].replace("\"","").replace("\"","");
        String sss = strArr[1].replace("\"","").replace("\"","");
        holder.title.setText(ss+":");
        holder.contant.setText(sss);


        //holder.title.setText();

        //将position保存在itemView的tag中,以便点击时进行获取
       // holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mDetailStringList.size();
    }


    public class MyViewholder extends RecyclerView.ViewHolder{

        public TextView title;
        public TextView contant;


        public MyViewholder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.item_goods_details_title_tv);
            contant = (TextView) itemView.findViewById(R.id.item_goods_details_constan_tv);



        }

    }

}
