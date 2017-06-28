package com.yumao.yumaosmart.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.activity.FirstClassifyDetail;
import com.yumao.yumaosmart.mode.CategoriesContentMode;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 分类页面RecyclerView
 */

public class ClassifyRvAdapter extends CommonAdapter<CategoriesContentMode.ChildrenBeanX> {
//    分类Fragment实现
    private ViewHolder mViewHolder;
    private ImageView mIvContent;
    private View mConvertView;
    private int mId;
    private Activity mActivity;


    public ClassifyRvAdapter(Context context, int layoutId, List<CategoriesContentMode.ChildrenBeanX> datas, Activity activity) {
        super(context, layoutId, datas);
        mActivity = activity;
    }

    @Override
    protected void convert(ViewHolder holder, CategoriesContentMode.ChildrenBeanX classifyBean, int position) {
        mViewHolder=holder;
        mConvertView = mViewHolder.getConvertView();
        mIvContent = (ImageView) mConvertView.findViewById(R.id.iv_classify_content);
        Picasso.with(UiUtilities.getContex()).load(classifyBean.getPicture()).resize(600,600).into(mIvContent);

        holder.setText(R.id.tv_classify_content, classifyBean.getName());
        mId = classifyBean.getId();


        mIvContent.setTag(R.id.iv_classify_content,Integer.valueOf(mId));
        initListenner();
    }

//跳到分类详情页面

    private void initListenner() {
        mViewHolder.setOnClickListener(R.id.iv_classify_content, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int  tag = (int) v.getTag(v.getId());
                Intent intent = new Intent();
                intent.putExtra("mCategoryId",tag);
                intent.setClass(mActivity, FirstClassifyDetail.class);
                mActivity.startActivity(intent);

            }
        });
    }

}
