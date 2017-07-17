package com.yumao.yumaosmart.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.yumao.yumaosmart.activity.FirstClassifyDetail;
import com.yumao.yumaosmart.activity.GoodsDetailActivity;
import com.yumao.yumaosmart.activity.LanMujingxuanActivity;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.inter.OnItemClickListener;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.UiUtilities;

import java.util.List;

/**
 * Created by kk on 2017/7/13.
 */

public class FirstListRvAdaper extends RecyclerView.Adapter<FirstListRvAdaper.MyViewholder>  {

    private Context mContext;
    private List<String> mPictureImage ;   //栏目大图
    private List<Integer> mPictureImageLocal ;   //本地栏目大图
   // private List<String> mProductName ;   //产品名称
   // private List<Integer> mResalePrice ;   //产品的转售价
    //private List<Integer> mPrice ;   //产品的销售价
   // private List<String> mProductImage ;   //产品的图片

    private List<List<Integer>> mProductIdList; //产品id集合
    private List<List<String>> mProductNameList ;   //产品名称集合
    private List<List<Integer>> mPriceList ;   //产品的销售价集合
    private List<List<Integer>> mResalePriceList ;   //产品的转售价集合
    private List<List<String>> mProductImageList ; //每个item的产品图片集合

    private List<Integer> mItemSize ;      //条目图片的大小
    private int mPictureImageLenght ;

    //处理点击事件的传递数据
    private List<Integer> mImageVsid;    //栏目精选列表id
    private List<String> mImageLMList ;    //列表栏目大图
    private Intent mIntent;
    private List<Integer> mIdList; //分类的id
    private int mVId;               //门店的id
    //private List<Integer> mProductIdList; //产品id

    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener = null;


    public FirstListRvAdaper() {
    }

    public FirstListRvAdaper(Context context, List<String> pictureImage,List<Integer> itemSize,
                             List<List<String>> productNameList,List<List<Integer>> resalePriceList,List<List<Integer>> priceList,List<List<String>> productImageList, int PictureImageLenght,List<Integer> pictureImageLocal
    ,List<Integer> imageVsid,List<String> imageLMList ,int vid ,List<Integer> idList ,List<List<Integer>> productIdList){

        mContext = context ;
        mPictureImage = pictureImage;
        mPictureImageLocal = pictureImageLocal;
        mProductNameList = productNameList ;
        mItemSize = itemSize;
        mResalePriceList = resalePriceList;
        mPriceList = priceList;
        mProductImageList = productImageList;

        mPictureImageLenght = PictureImageLenght;

        mImageVsid = imageVsid;            //栏目精选分页id
        mImageLMList = imageLMList;  //栏目精选自定义大图
        mVId = vid ;    //门店的id
        mIdList = idList ;  //分类的id
        mProductIdList = productIdList; //产品id

        mInflater= LayoutInflater.from(context);

            LogUtils.d("tag","首页列表数量:"+mProductImageList.size());
        for (int i = 0 ;i<mProductImageList.size();i++){
                LogUtils.d("tag","首页列表数据2:"+mProductImageList.get(i).size());
            for (int j =0 ;j < mProductImageList.get(i).size();j++){
                LogUtils.d("tag","首页列表图:"+mProductImageList.get(i).get(j));
            }
        }


    }

    @Override
    public FirstListRvAdaper.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_first_list,parent,false);

        final FirstListRvAdaper.MyViewholder viewholder = new FirstListRvAdaper.MyViewholder(view);

        //将创建的view注册点击事件
       // view.setOnClickListener(this);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(FirstListRvAdaper.MyViewholder holder, final int position) {

        // holder.mIconIv.setImageResource(mImageList.get(position));
            LogUtils.d("长度:"+mPictureImageLenght);
        if (position<mPictureImageLenght){
            Picasso.with(mContext).load(mPictureImage.get(position)).into(holder.mTitleImage);

            holder.mTitleImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        LogUtils.d("栏目大图:"+position);
                    mIntent = new Intent(mContext, LanMujingxuanActivity.class);
                    //   LogUtils.d("tag","id:"+imageId.get(position));
                    mIntent.putExtra("vsid", mImageVsid.get(position));
                    mIntent.putExtra("BannerImage", mImageLMList.get(position));
                    mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(mIntent);

                }
            });
        }else{
                LogUtils.d("hahah"+position);
            holder.mTitleImage.setImageResource(mPictureImageLocal.get(position-mPictureImageLenght));

            holder.mTitleImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.d("本地大图:"+position);
                    mIntent = new Intent(UiUtilities.getContex(), FirstClassifyDetail.class);
                    mIntent.putExtra(Constant.CATEGORY_ID, mIdList.get(position-mPictureImageLenght));  //分类的id
                    mIntent.putExtra("vid", mVId);//门店id
                    mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(mIntent);
                }
            });
        }


        if (mItemSize.get(position)==0){
            return;
        }else if (mItemSize.get(position)==1){
            Picasso.with(mContext).load(mProductImageList.get(position).get(0)).into(holder.mLeftImage);
            holder.mLeftName.setText(mProductNameList.get(position).get(0));
            holder.mLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        LogUtils.d("左图:"+position);
                    mIntent = new Intent(UiUtilities.getContex() ,GoodsDetailActivity.class);
                    mIntent.putExtra(Constant.PRODUCT_ID,mProductIdList.get(position).get(0));  //产品id
                    mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(mIntent);

                }
            });
            if (mResalePriceList.get(position).get(0) == 0){
                holder.mLeftPrice.setText(mPriceList.get(position).get(0)+"元");
            }else {
                holder.mLeftPrice.setText(mResalePriceList.get(position).get(0) + "元");
            }
        }else if (mItemSize.get(position)==2){
            Picasso.with(mContext).load(mProductImageList.get(position).get(0)).into(holder.mLeftImage);
            holder.mLeftName.setText(mProductNameList.get(position).get(0));
            holder.mLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.d("左图:"+position);
                    mIntent = new Intent(UiUtilities.getContex() ,GoodsDetailActivity.class);
                    mIntent.putExtra(Constant.PRODUCT_ID,mProductIdList.get(position).get(0));  //产品id
                    mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(mIntent);

                }
            });
            if (mResalePriceList.get(position).get(0) == 0){
                holder.mLeftPrice.setText(mPriceList.get(position).get(0)+"元");
            }else {
                holder.mLeftPrice.setText(mResalePriceList.get(position).get(0) + "元");
            }

            Picasso.with(mContext).load(mProductImageList.get(position).get(1)).into(holder.mMidImage);
            holder.mMidName.setText(mProductNameList.get(position).get(1));
            holder.mMid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.d("中图:"+position);
                    mIntent = new Intent(UiUtilities.getContex() ,GoodsDetailActivity.class);
                    mIntent.putExtra(Constant.PRODUCT_ID,mProductIdList.get(position).get(1));  //产品id
                    mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(mIntent);

                }
            });
            if (mResalePriceList.get(position).get(1) == 0){
                holder.mMidPrice.setText(mPriceList.get(position).get(1)+"元");
            }else {
                holder.mMidPrice.setText(mResalePriceList.get(position).get(1) + "元");
            }

        }else if(mItemSize.get(position)==3) {
            Picasso.with(mContext).load(mProductImageList.get(position).get(0)).into(holder.mLeftImage);
            holder.mLeftName.setText(mProductNameList.get(position).get(0));
            holder.mLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.d("左图:"+position);
                    mIntent = new Intent(UiUtilities.getContex() ,GoodsDetailActivity.class);
                    mIntent.putExtra(Constant.PRODUCT_ID,mProductIdList.get(position).get(0));  //产品id
                    mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(mIntent);

                }
            });
            if (mResalePriceList.get(position).get(0) == 0){
                holder.mLeftPrice.setText(mPriceList.get(position).get(0)+"元");
            }else {
                holder.mLeftPrice.setText(mResalePriceList.get(position).get(0) + "元");
            }

            Picasso.with(mContext).load(mProductImageList.get(position).get(1)).into(holder.mMidImage);
            holder.mMidName.setText(mProductNameList.get(position).get(1));
            holder.mMid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.d("中图:"+position);
                    mIntent = new Intent(UiUtilities.getContex() ,GoodsDetailActivity.class);
                    mIntent.putExtra(Constant.PRODUCT_ID,mProductIdList.get(position).get(1));  //产品id
                    mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(mIntent);

                }
            });
            if (mResalePriceList.get(position).get(1) == 0){
                holder.mMidPrice.setText(mPriceList.get(position).get(1)+"元");
            }else {
                holder.mMidPrice.setText(mResalePriceList.get(position).get(1) + "元");
            }

            Picasso.with(mContext).load(mProductImageList.get(position).get(2)).into(holder.mRightImage);
            holder.mRightName.setText(mProductNameList.get(position).get(2));
            holder.mRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.d("右图:"+position);
                    mIntent = new Intent(UiUtilities.getContex() ,GoodsDetailActivity.class);
                    mIntent.putExtra(Constant.PRODUCT_ID,mProductIdList.get(position).get(2));  //产品id
                    mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(mIntent);

                }
            });
            if (mResalePriceList.get(position).get(2) == 0){
                holder.mRightPrice.setText(mPriceList.get(position).get(2)+"元");
            }else{
            holder.mRightPrice.setText(mResalePriceList.get(position).get(2)+"元");
            }
        }else{
                LogUtils.d("列表数据有问题");
        }


        //将position保存在itemView的tag中,以便点击时进行获取
        //holder.itemView.setTag(position);
        holder.mTitleImage.setTag(position);
        holder.mLeft.setTag(position);
        holder.mMid.setTag(position);
        holder.mRight.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mPictureImage.size()+mPictureImageLocal.size();
    }



    public class MyViewholder extends RecyclerView.ViewHolder  {


        public ImageView mTitleImage;

        public ImageView mLeftImage ;
        public ImageView mMidImage ;
        public ImageView mRightImage ;

        public TextView mLeftName;
        public TextView mMidName;
        public TextView mRightName;

        public TextView mLeftPrice;
        public TextView mMidPrice;
        public TextView mRightPrice;

        //条目点击事件
        public LinearLayout mLeft;
        public LinearLayout mMid;
        public LinearLayout mRight;

        public MyViewholder(View itemView) {
            super(itemView);
            mTitleImage = (ImageView) itemView.findViewById(R.id.iv_first_list_title);

            mLeftImage = (ImageView) itemView.findViewById(R.id.iv_first_list_left);
            mMidImage = (ImageView) itemView.findViewById(R.id.iv_first_list_mid);
            mRightImage = (ImageView) itemView.findViewById(R.id.iv_first_list_right);

            mLeftName = (TextView) itemView.findViewById(R.id.tv_first_list_left_name);
            mMidName = (TextView) itemView.findViewById(R.id.tv_first_list_mid_name);
            mRightName = (TextView) itemView.findViewById(R.id.tv_first_list_right_name);

            mLeftPrice = (TextView) itemView.findViewById(R.id.tv_first_list_left_price);
            mMidPrice= (TextView) itemView.findViewById(R.id.tv_first_list_mid_price);
            mRightPrice = (TextView) itemView.findViewById(R.id.tv_first_list_right_price);

            //点击事件
            mLeft = (LinearLayout) itemView.findViewById(R.id.layout_first_list_left);
            mMid = (LinearLayout) itemView.findViewById(R.id.layout_first_list_mid);
            mRight = (LinearLayout) itemView.findViewById(R.id.layout_first_list_right);

            DisplayMetrics dm = new DisplayMetrics();
            dm = UiUtilities.getContex().getResources().getDisplayMetrics();
            float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
            int densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
            int screenWidth = dm.widthPixels; // 屏幕宽（像素，如：3200px）
            int screenHeight = dm.heightPixels; // 屏幕高（像素，如：1280px）

            ViewGroup.LayoutParams params = mLeftImage.getLayoutParams();
            params.width = screenWidth/3;
            params.height = screenWidth/3;

            mLeftImage.setLayoutParams(params);
        }

    }


}
