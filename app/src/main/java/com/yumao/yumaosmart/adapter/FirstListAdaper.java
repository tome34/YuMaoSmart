package com.yumao.yumaosmart.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.activity.FirstClassifyDetail;
import com.yumao.yumaosmart.activity.GoodsDetailActivity;
import com.yumao.yumaosmart.mode.ProductsMode;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by kk on 2017/3/1.
 * 首页列表
 */

public class FirstListAdaper extends CommonAdapter<ProductsMode> {


    private ViewHolder mViewHolder;
    private int  goodsIdLeft;
    private Activity mActivity;
    private int  goodsIdMid;
    private int  goodsIdRight;
    private ProductsMode.CategoryBean mCategory;
    private List<ProductsMode.ProductsBean> mProducts;
    private ProductsMode.ProductsBean mLeftProductsBean;
    private ProductsMode.ProductsBean mMidProductsBean;
    private ProductsMode.ProductsBean mRightProductsBean;
    private  int [] titleRes={R.mipmap.first_list_title_feicui,R.mipmap.first_list_title_feicui,
            R.mipmap.first_list_title_zuanshi,
            R.mipmap.first_list_title_caibao,
            R.mipmap.first_list_title_huangbojin,
            R.mipmap.first_list_title_zhenzhu,
            R.mipmap.first_list_title_yinshi,
            R.mipmap.first_list_title_hetianyu

    };

    private ImageView mIvLeft;
    private View mConvertView;
    private ImageView mIvMid;
    private ImageView mIvRight;
    private ImageView mIvTitle;

    public FirstListAdaper(Context context, int layoutId, List<ProductsMode> datas, Activity activity) {
        super(context, layoutId, datas);
        this.mActivity=activity;
    }
//        首页列表数据绑定具体实现
    @Override
    protected void convert(ViewHolder viewHolder, ProductsMode item, int position) {
        mViewHolder = viewHolder;
        mConvertView = viewHolder.getConvertView();
        mIvLeft = (ImageView) mConvertView.findViewById(R.id.iv_first_list_left);
        mIvMid = (ImageView) mConvertView.findViewById(R.id.iv_first_list_mid);
        mIvRight = (ImageView) mConvertView.findViewById(R.id.iv_first_list_right);
        mIvTitle = (ImageView) mConvertView.findViewById(R.id.iv_first_list_title);
        mProducts = item.getProducts();
        mCategory = item.getCategory();
        mLeftProductsBean = mProducts.get(0);

        mMidProductsBean = mProducts.get(1);
        mRightProductsBean = mProducts.get(2);
       Picasso.with(mActivity).load(mLeftProductsBean.getThumb()).resize(600,600).into(mIvLeft);
       Picasso.with(mActivity).load(mMidProductsBean.getThumb()).resize(600,600).into(mIvMid);
       Picasso.with(mActivity).load(mRightProductsBean.getThumb()).resize(600,600).into(mIvRight);
        goodsIdLeft=mLeftProductsBean.getId();

        goodsIdMid=mMidProductsBean.getId();
         goodsIdRight=mRightProductsBean.getId();
        mIvLeft.setTag(R.id.iv_first_list_left,goodsIdLeft);
        mIvMid.setTag(R.id.iv_first_list_mid,goodsIdMid);
        mIvRight.setTag(R.id.iv_first_list_right,goodsIdRight);
        mIvTitle.setTag(R.id.iv_first_list_title,mCategory.getId());
        viewHolder.setImageResource(R.id.iv_first_list_title, titleRes[position]);
        viewHolder.setText(R.id.tv_first_list_left_name, mLeftProductsBean.getName());
        viewHolder.setText(R.id.tv_first_list_left_price, mLeftProductsBean.getPrice());
        viewHolder.setText(R.id.tv_first_list_mid_name,mMidProductsBean.getName());
        viewHolder.setText(R.id.tv_first_list_mid_price, mMidProductsBean.getPrice());
        viewHolder.setText(R.id.tv_first_list_right_name, mRightProductsBean.getName());
        viewHolder.setText(R.id.tv_first_list_right_price, mRightProductsBean.getPrice());
        initItemClick();
    }
//首页列表的点击事件
private void initItemClick() {
    mViewHolder.setOnClickListener(R.id.iv_first_list_title, new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = (int) v.getTag(v.getId());
            Intent intent = new Intent();
            intent.putExtra("mCategoryId", tag);
            intent.setClass(mActivity, FirstClassifyDetail.class);
            mActivity.startActivity(intent);
        }
    });
    mViewHolder.setOnClickListener(R.id.iv_first_list_left, new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = (int) v.getTag(v.getId());
            jumpToGoodsDetail(tag);
        }
    });
    mViewHolder.setOnClickListener(R.id.iv_first_list_mid, new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = (int) v.getTag(v.getId());
            jumpToGoodsDetail(tag);
        }
    });
    mViewHolder.setOnClickListener(R.id.iv_first_list_right, new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = (int) v.getTag(v.getId());
            jumpToGoodsDetail(tag);
        }
    });


    }
//跳转到商品详情
    private void jumpToGoodsDetail(int goodsId) {
        Intent intent = new Intent();
        intent.putExtra("goodsid", goodsId);
        intent.setClass(mActivity, GoodsDetailActivity.class);
        mActivity.startActivity(intent);

    }

}
