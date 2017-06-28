package com.yumao.yumaosmart.delegate;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.pickerview.TimePickerView;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.activity.AddressActivity;
import com.yumao.yumaosmart.activity.PhoneNumberActivity;
import com.yumao.yumaosmart.activity.SweetNameActivity;
import com.yumao.yumaosmart.activity.TestIdentity;
import com.yumao.yumaosmart.adapter.GenderDialogAdapter;
import com.yumao.yumaosmart.bean.MyMaterialOtherBean;
import com.yumao.yumaosmart.event.BirthDayEvent;
import com.yumao.yumaosmart.event.GenderEvent;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by kk on 2017/3/6.
 */

public class MyMaterialOtherDelegate implements ItemViewDelegate<DisPlay> {
    private static final String TAG = "MyMaterialOtherDelegate";
    private Activity mActivity;
    private List<String> mListGender= new ArrayList();


    public TimePickerView pvTime;
    public MyMaterialOtherDelegate(Activity activity) {
        mActivity =  activity;
        mListGender.add("男");
        mListGender.add("女");
        mListGender.add("保密");

    }

    private ViewHolder mHolder;
    private ImageView mTouxiang;
    private View mConvertView;
    private MyMaterialOtherBean mOtherBean;

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_material_other;
    }

    @Override
    public boolean isForViewType(DisPlay item, int position) {
        return item instanceof MyMaterialOtherBean;
    }

    @Override
    public void convert(final ViewHolder holder, DisPlay disPlay, final int position) {
        mHolder = holder;

        mConvertView = holder.getConvertView();
        mConvertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
//昵称
                    case 1:


                        mActivity.startActivity(new Intent(mActivity,SweetNameActivity.class));
                        break;
    //电话
                    case 2:
                        mActivity.startActivity(new Intent(mActivity,PhoneNumberActivity.class));
                        break;
// 地址
                    case 3:
                        mActivity.startActivity(new Intent(mActivity,AddressActivity.class));
                        break;
// 性别
                    case 4:
                        handgender();

                        break;
//  生日
                    case 5:
                        handBirthDay();
                        break;
// 我的推荐码
                    case 6:

                        break;
// 实名认证
                    case 7:
                    mActivity.startActivity(new Intent(mActivity,TestIdentity.class));
                        break;
//重置密码
                    case 8:

                        break;


                }
            }



        });


        mOtherBean = (MyMaterialOtherBean) disPlay;

        holder.setText(R.id.tv_mymaterial_other_title, mOtherBean.getTitle());
        holder.setText(R.id.tv_my_material_other_content, mOtherBean.getContent());




    }

    private void handgender() {


        DialogPlus dialog = DialogPlus.newDialog(mActivity)
                .setAdapter(new GenderDialogAdapter(mActivity,R.layout.item_material_gender_dialog, mListGender))
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                        switch (position) {

                            case 0:
                            case 1:
                                String gender = (String) item;
                                if ("男".equals(gender)) {
                                    UiUtilities.getUser().setGender("M");
                                } else if ("女".equals(gender)) {
                                UiUtilities.getUser().setGender("F");
                                } else {
                                    UiUtilities.getUser().setGender("0");
                                    gender = "保密";
                                }

                                EventBus.getDefault().post(new GenderEvent(gender,4));
                                dialog.dismiss();
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


    private void handBirthDay() {
        pvTime = new TimePickerView.Builder(mActivity, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String time = format.format(date);
                UiUtilities.getUser().setDate_of_birth(time);
               EventBus.getDefault().post(new BirthDayEvent(time,5));
            }

        })
                .setLabel("年", "月", "日", "", "", "")
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)//default is all
                .setCancelText("取消")
                .setSubmitText("确定")
                .setContentSize(18)
                .setTitleSize(20)
                .setTitleText("出生日期")
                .build();
        pvTime.show();
    }



}
