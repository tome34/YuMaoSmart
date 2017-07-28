package com.yumao.yumaosmart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.inter.OnItemClickListener;
import com.yumao.yumaosmart.manager.CartManager;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.UiUtilities;

import java.util.List;

/**
 * Created by kk on 2017/7/12.
 */

public class LanMujiangXuanAdapter extends RecyclerView.Adapter<LanMujiangXuanAdapter.MyViewholder> implements View.OnClickListener {

    private Context mContext;
    List<String> mImageList ; //栏目精选图片集合
    List<String> mTiltisList ; //栏目精选标题集合
    List<Integer> mPriceList ;  //产品价格
    List<Integer> mResalePriceList ; //产品的转售价格,以转卖价优先
    List<String> mNumberList ;//产品编号

    List<Integer> mProductIdList;     //产品id

    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener = null;


    public LanMujiangXuanAdapter() {
    }

    public LanMujiangXuanAdapter(Context context, List<String> imageList,List<String> tiltisList,List<Integer> resalePriceList,List<Integer> priceList ,List<String> number
    ,List<Integer> productIdList){
        mContext = context ;
        mImageList = imageList ;
        mTiltisList = tiltisList;
        mResalePriceList = resalePriceList;
        mPriceList = priceList ;
        mNumberList = number ;

        mProductIdList = productIdList ;
        mInflater= LayoutInflater.from(context);

    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_activity_first_detail,parent,false);

        MyViewholder viewholder = new MyViewholder(view);

        //将创建的view注册点击事件
        view.setOnClickListener(this);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, final int position) {

       // holder.mIconIv.setImageResource(mImageList.get(position));
        Picasso.with(mContext).load(mImageList.get(position)).into(holder.mIconIv);
        holder.mName.setText(mTiltisList.get(position));
       // holder.mNumber.setText();
        if (mResalePriceList.get(position) ==0){
            holder.mPrice.setText(mPriceList.get(position)+"元");
        }else{
            holder.mPrice.setText(mResalePriceList.get(position)+"元");
        }

        holder.mNumber.setText("编号: "+mNumberList.get(position));

        holder.mToShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartManager.getInstance().postCartItem(mProductIdList.get(position),1);
                    LogUtils.d("加入购物车");
            }
        });

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
        public TextView mPrice;
        public ImageView mIconIv;
        public TextView mNumber;
        private final LinearLayout mToShopping;

        public MyViewholder(View itemView) {
            super(itemView);
            mIconIv = (ImageView) itemView.findViewById(R.id.iv_item_activity_first_classif_detail_main);
            mName = (TextView) itemView.findViewById(R.id.tv_item_activity_first_classif_detail_name);
            mPrice = (TextView) itemView.findViewById(R.id.tv_item_activity_first_classif_detail_price);
            mNumber = (TextView) itemView.findViewById(R.id.tv_item_activity_first_classif_detail_numtitle);
            mToShopping = (LinearLayout) itemView.findViewById(R.id.item_classify_detail__shopping);


            DisplayMetrics dm = new DisplayMetrics();
            dm = UiUtilities.getContex().getResources().getDisplayMetrics();
            float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
            int densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
            int screenWidth = dm.widthPixels; // 屏幕宽（像素，如：3200px）
            int screenHeight = dm.heightPixels; // 屏幕高（像素，如：1280px）

            ViewGroup.LayoutParams params = mIconIv.getLayoutParams();
            params.width = screenWidth/2;
            params.height = screenWidth/2;
            mIconIv.setLayoutParams(params);
        }

    }

    public void setOnItemClickListener(OnItemClickListener listener){

        this.mOnItemClickListener = listener ;
    }
}
