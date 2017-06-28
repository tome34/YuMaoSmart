package com.yumao.yumaosmart.delegate;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.View;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.TouXiangDialogAdapter;
import com.yumao.yumaosmart.bean.MyMaterialTouxiangBean;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;


/**
 * Created by kk on 2017/3/6.
 */

public class MyMaterialTouxiangDelegate implements ItemViewDelegate<DisPlay> {
    public static final int REQUEST_CODE =0 ;
    private Activity mActivity;
    private ArrayList<String> mList ;


    public MyMaterialTouxiangDelegate(Activity activity) {
        mActivity = activity;
        mList = new ArrayList<>();
        mList.add("拍照");
        mList.add("从相册选择");
        mList.add("取消");

    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_material_touxiang;
    }

    @Override
    public boolean isForViewType(DisPlay item, int position) {
        return item instanceof MyMaterialTouxiangBean;
    }

//    我的资料顶部头像条目的点击事件处理
    @Override
    public void convert(ViewHolder holder, DisPlay disPlay, int position) {
        View convertView = holder.getConvertView();
        convertView.setOnClickListener(new View.OnClickListener() {
            // 个人中心图标点击事件
            @Override
            public void onClick(View v) {
                DialogPlus dialog = DialogPlus.newDialog(mActivity)
                        .setAdapter(new TouXiangDialogAdapter(mActivity,R.layout.item_material_touxiang_dialog,mList))
                        .setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                                switch (position) {

                                    case 0:
                                        Intent cameraIntent = new Intent(
                                                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                        mActivity.startActivity(cameraIntent);
                                        break;
//
                                    case 1:
                                        Intent intent = new Intent();
                                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                                        intent.setType("image/*");
                                        //根据版本号不同使用不同的Action
                                        if (Build.VERSION.SDK_INT <19) {
                                            intent.setAction(Intent.ACTION_GET_CONTENT);
                                        }else {
                                            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                                        }
//                                        mActivity.startActivityForResult(intent, REQUEST_CODE);

                                        break;
                                    case 2:

                                        dialog.dismiss();
                                        break;

                                }
                            }
                        })
                        .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                    dialog.show();


            }
        });

        MyMaterialTouxiangBean touxiangBean = (MyMaterialTouxiangBean) disPlay;
        holder.setImageResource(R.id.iv_my_material_touxiang, Integer.parseInt(touxiangBean.getTouxiangRes()));
        holder.setText(R.id.tv_mymaterial_title, touxiangBean.getTitle());
    }

}
