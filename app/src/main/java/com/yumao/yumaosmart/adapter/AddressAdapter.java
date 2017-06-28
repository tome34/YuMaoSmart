package com.yumao.yumaosmart.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.activity.MyAddressActivity;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.event.MyAddressEvent;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by kk on 2017/3/8.
 */

public class AddressAdapter extends CommonAdapter<MyAddressEvent> {

    private View mConvertView;

    public void setOndelete(OnDeleteAddressListenner ondelete) {
        this.ondelete = ondelete;
    }

    private OnDeleteAddressListenner ondelete;
    private Activity mActivity;

    public AddressAdapter(Context context, int layoutId, List<MyAddressEvent> datas, Activity activity) {
        super(context, layoutId, datas);

        mActivity = activity;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final MyAddressEvent item, int position) {
        viewHolder.setText(R.id.tv_item_activity_address_name, item.getName());
        viewHolder.setText(R.id.tv_item_activity_address_detailaddress,item.getArea()+item.getDetailAddress());
        viewHolder.setText(R.id.tv_item_activity_address_phonenum, item.getPhoneNum());
        viewHolder.setChecked(R.id.rbtn_item_activity_address_isdefault, item.isDefaultAddress());
        mConvertView = viewHolder.getConvertView();
        mConvertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EditAddress(item);
            }
        });
        viewHolder.setOnClickListener(R.id.iv_item_activity_address_bianji, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditAddress(item);
            }
        });
        viewHolder.setOnClickListener(R.id.iv_item_activity_address_shanchu, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    deleteAddress(item);
            }
        });
    }

    private void deleteAddress(final MyAddressEvent item) {
        OkHttpUtils
                .delete()
                .url(Constant.BASE_URL+"customers/"+ UiUtilities.getUser().getId()+"/addresses/"+item.getAid())
                .addHeader("X-API-TOKEN", UiUtilities.getUser().getToken())
                .build()
                .execute(new Callback() {
            @Override
            public Object parseNetworkResponse(Response response, int id) throws Exception {
                return null;
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(Object response, int id) {
                if (ondelete != null) {
                    ondelete.onDeleteAddress(item);
                    LogUtils.d("删除成功");
                }


            }
        });
    }

    public  interface OnDeleteAddressListenner{
        void onDeleteAddress(MyAddressEvent item);

    };

    private void EditAddress(MyAddressEvent item) {
        Intent intent = new Intent();
        intent.setClass(mActivity, MyAddressActivity.class);
        intent.putExtra("EditAddress", item);
        mActivity.startActivity(intent);
    }
}
