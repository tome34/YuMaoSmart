package com.yumao.yumaosmart.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.activity.GoodsDetailActivity;
import com.yumao.yumaosmart.mode.FirstClassifyDetailMode;
import com.yumao.yumaosmart.utils.NumberGenerate;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by kk on 2017/3/17.
 */

public class FirstClassifyDetailAdapter extends CommonAdapter<FirstClassifyDetailMode> {

    private ImageView mIv;
    private int mPrice;
    private String mThumb;
    private View mConvertView;
    private Activity mActivity;
    private int mId;

    public FirstClassifyDetailAdapter(Context context, int layoutId, List<FirstClassifyDetailMode> datas,Activity activity) {
        super(context, layoutId, datas);
        mActivity=activity;
    }

    @Override
    protected void convert(ViewHolder holder, FirstClassifyDetailMode firstClassifyDetailBean, int position) {

        mPrice = firstClassifyDetailBean.getPrice();
        mThumb = firstClassifyDetailBean.getThumb();
        mConvertView = holder.getConvertView();
        mIv = (ImageView) mConvertView.findViewById(R.id.iv_item_activity_first_classif_detail_main);
        Picasso.with(UiUtilities.getContex()).load(mThumb).resize(600,600).into(mIv);
        holder.setText(R.id.tv_item_activity_first_classif_detail_name,firstClassifyDetailBean.getName());
        holder.setText(R.id.tv_item_activity_first_classif_detail_num,
                NumberGenerate.generate(firstClassifyDetailBean.getPrice(),
                        firstClassifyDetailBean.getProduct_cost(),mId));
        mId = firstClassifyDetailBean.getId();
        mIv.setTag(R.id.iv_item_activity_first_classif_detail_main, Integer.valueOf(mId));
        holder.setText(R.id.tv_item_activity_first_classif_detail_price, String.valueOf("￥"+mPrice));
        holder.setOnClickListener(R.id.iv_item_activity_first_classif_detail_main, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer tag = (Integer) v.getTag(v.getId());
                jumpToGoodsDetail(tag);

            }
        });
    }
    private void jumpToGoodsDetail(int goodsId) {
        Intent intent = new Intent();
        intent.putExtra("goodsid", goodsId);
        intent.setClass(mActivity, GoodsDetailActivity.class);
        mActivity.startActivity(intent);

    }
}
