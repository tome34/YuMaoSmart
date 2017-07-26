package com.yumao.yumaosmart.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.activity.GoodsDetailActivity;
import com.yumao.yumaosmart.bean.Shopping;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.manager.CartManager;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.yumao.yumaosmart.widgit.CustomCarGoodsCounterView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by kk on 2017/7/25.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.MyViewholder> {

    private Context mContext ;

    private List<Integer> mCartId ;  //购物车id
    private List<Integer> mProductId ; //产品id
    private List<String> mProductName ; //产品名称
    private List<Integer> mResalePrice ; //转卖价格
    private List<Integer> mPrice ;       //价格
    private List<String> mThumbIv ;       //item图片
    private List<Integer> mQuantity;      //产品数量
    private List<Boolean> mCheckBoxList;

    private LayoutInflater mInflater;
    private Intent mIntent;
    public CheckInterface checkInterface;

    public ShoppingCartAdapter() {

    }

    public ShoppingCartAdapter(Context context ,List<Integer> cartId,List<Integer> productId,List<String> productName
     ,List<Integer> resalePrice ,List<Integer> price, List<String> thumbIv, List<Integer> quantity ,List<Boolean> checkBox) {
        mContext = context ;
        mCartId = cartId ;
        mProductId = productId;
        mProductName = productName;
        mResalePrice = resalePrice;
        mPrice = price;
        mThumbIv = thumbIv ;
        mQuantity = quantity ;
        mCheckBoxList = checkBox ;
        mInflater= LayoutInflater.from(context);
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_car_recyclerview,parent,false);

        MyViewholder viewholder = new MyViewholder(view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(final MyViewholder holder, final int position) {

        Picasso.with(mContext).load(mThumbIv.get(position)).into(holder.mIvIcon);
        //购物车图片点击事件
        holder.mIvIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(UiUtilities.getContex() ,GoodsDetailActivity.class);
                mIntent.putExtra(Constant.PRODUCT_ID,mProductId.get(position));  //产品id
                mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(mIntent);
            }
        });

        holder.mTvTitle.setText(mProductName.get(position));
        if (mResalePrice.get(position) !=0){
            holder.mTvPrice.setText(mResalePrice.get(position)+"元");
        }else {
            holder.mTvPrice.setText(mPrice.get(position)+"元");
        }

        //删除的点击事件
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "删除"+mCartId.get(position), Toast.LENGTH_SHORT).show();
                CartManager.getInstance().deleteCartItem(mCartId.get(position));
            }
        });

        holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Shopping> memoryAll = CartManager.getInstance().getMemoryAll();
                    LogUtils.d("内存数据:"+memoryAll);
                memoryAll.get(position).isChecked = !memoryAll.get(position).isChecked;

                holder.mCheckBox.setChecked(memoryAll.get(position).isChecked);


                //更新购物车条目
                CartManager.getInstance().updateGoodsCar(memoryAll.get(position));

                EventBus.getDefault().post(new String());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCartId.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder implements CustomCarGoodsCounterView.UpdateGoodsNumberListener {

        private final CheckBox mCheckBox;
        private final ImageView mIvIcon;
        private final TextView mTvTitle;
        private final TextView mTvPrice;
        private final TextView mDelete;
        private final CustomCarGoodsCounterView mCarGoodsCounter;

        public MyViewholder(View itemView) {
            super(itemView);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.check_box);
            mIvIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mTvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            mDelete = (TextView) itemView.findViewById(R.id.car_goods_delete);
            mCarGoodsCounter = (CustomCarGoodsCounterView) itemView.findViewById(R.id.car_goods_counter);


            mCarGoodsCounter.setUpdateGoodsNumberListener(this);
        }

        @Override
        public void updateGoodsNumber(int number) {

        }
    }

    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param position  元素位置
         * @param isChecked 元素选中与否
         */
        void checkGroup(int position, boolean isChecked);
    }
}
