package com.yumao.yumaosmart.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.activity.MyMaterialActivity;
import com.yumao.yumaosmart.activity.itemactivity.MyEmployeeActivity;
import com.yumao.yumaosmart.activity.itemactivity.ServiceCenterActivity;
import com.yumao.yumaosmart.activity.itemactivity.MyPularityActvity;
import com.yumao.yumaosmart.activity.itemactivity.MySpreadActivity;
import com.yumao.yumaosmart.activity.itemactivity.MyStorsActivity;
import com.yumao.yumaosmart.activity.itemactivity.MyVipActivity;
import com.yumao.yumaosmart.activity.itemactivity.StoresMaterialActivity;
import com.yumao.yumaosmart.activity.itemactivity.VIPActivity;
import com.yumao.yumaosmart.bean.PersonnalBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by kk on 2017/3/6.
 */

public class PersonnalcenterAdapter extends CommonAdapter<PersonnalBean>  {
    private ViewHolder mHolder;
    private int mPostion;
    private Class nameArr[] = {MyMaterialActivity.class,MyEmployeeActivity.class,VIPActivity.class,
            MyPularityActvity.class,MySpreadActivity.class,
    MyStorsActivity.class,MyVipActivity.class,StoresMaterialActivity.class,ServiceCenterActivity.class};
    private Context mActivity;
    public PersonnalcenterAdapter(Context context, int layoutId, List<PersonnalBean> datas , Activity activity) {
        super(context, layoutId, datas);
        mActivity=activity;

    }

    @Override
    protected void convert(ViewHolder holder, PersonnalBean personnalBean, int position) {
        mHolder = holder;
        mPostion = position;
        holder.setImageResource(R.id.iv_personnal_item, Integer.parseInt(personnalBean.getBackSrc()));
        holder.setText(R.id.tv_personnal_item, personnalBean.getName());
        initListenner(mPostion);

    }

    private void initListenner(final int postion) {

        View convertView = mHolder.getConvertView();
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.startActivity(new Intent(mActivity,nameArr[postion]));

                }
            });
    }





}
