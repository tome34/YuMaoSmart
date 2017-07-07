package com.yumao.yumaosmart.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseItemActivity;
import com.yumao.yumaosmart.bean.MyMaterialOtherBean;
import com.yumao.yumaosmart.bean.MyMaterialTouxiangBean;
import com.yumao.yumaosmart.delegate.DisPlay;
import com.yumao.yumaosmart.delegate.MyMaterialOtherDelegate;
import com.yumao.yumaosmart.delegate.MyMaterialTouxiangDelegate;
import com.yumao.yumaosmart.event.BirthDayEvent;
import com.yumao.yumaosmart.event.GenderEvent;
import com.yumao.yumaosmart.event.PhoneNumEvent;
import com.yumao.yumaosmart.event.SweetNameEvent;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kk on 2017/3/6.
 */

public class MyMaterialActivity extends BaseItemActivity {


    private RecyclerView mRvMyMaterial;
    private List<DisPlay> mList = new ArrayList<>();
    private MyMaterialOtherBean mOtherBean;
    private MyMaterialTouxiangBean mTouxiangBean;
    private MyMaterialTouxiangDelegate mTouxiangDelegate;
    private MyMaterialOtherDelegate mOtherDelegate;
    public MultiItemTypeAdapter<DisPlay> mMultiItemTypeAdapter;
    private String mSweetName;
    private int mPostion;
    private String mPhoneNum;
    private String mGender;
    private int mState;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymaterial);
        initToobar(getString(R.string.title_my_material));
        EventBus.getDefault().register(this);
        intData();
        initView();

    }




    private void initView() {

        mRvMyMaterial = (RecyclerView) findViewById(R.id.rv_my_material);
        mRvMyMaterial.setLayoutManager(new LinearLayoutManager(this));
        mMultiItemTypeAdapter = new MultiItemTypeAdapter<DisPlay>(UiUtilities.getContex(), mList);
        mTouxiangDelegate = new MyMaterialTouxiangDelegate(this);
        mOtherDelegate = new MyMaterialOtherDelegate(this);

        mMultiItemTypeAdapter.addItemViewDelegate(mTouxiangDelegate);
        mMultiItemTypeAdapter.addItemViewDelegate(mOtherDelegate);
        mRvMyMaterial.setAdapter(mMultiItemTypeAdapter);

    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void  onSweetNameEvent (SweetNameEvent e) {

        mPostion = e.getPostion();
        DisPlay disPlay = mList.get(mPostion);
       mOtherBean= (MyMaterialOtherBean) disPlay;
        mOtherBean.setContent(e.getSweetName());
        mList.remove(mPostion);
        mList.add(mPostion,mOtherBean);
        mMultiItemTypeAdapter.notifyDataSetChanged();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void  onPhoneNumEvent (PhoneNumEvent e) {
        mSweetName=e.getPhoneNum();
        mPostion = e.getPostion();
        DisPlay disPlay = mList.get(mPostion);
        mOtherBean= (MyMaterialOtherBean) disPlay;

        mOtherBean.setContent(e.getPhoneNum());
        mList.remove(mPostion);
        mList.add(mPostion,mOtherBean);
        mMultiItemTypeAdapter.notifyDataSetChanged();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGenderEvent(GenderEvent e){
        mPostion = e.getPostion();
        DisPlay disPlay = mList.get(mPostion);
        mOtherBean= (MyMaterialOtherBean) disPlay;
        mOtherBean.setContent(e.getGender());
        mList.remove(mPostion);
       mList.add(mPostion,mOtherBean);
    mMultiItemTypeAdapter.notifyDataSetChanged();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBirthDayEvent(BirthDayEvent e){
        mPostion = e.getPostion();
        DisPlay disPlay = mList.get(mPostion);
        mOtherBean= (MyMaterialOtherBean) disPlay;
        mOtherBean.setContent(e.getBirthDay());
        mList.remove(mPostion);
        mList.add(mPostion,mOtherBean);
        mMultiItemTypeAdapter.notifyDataSetChanged();
    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==MyMaterialTouxiangDelegate.REQUEST_CODE) {
//            if (null!=data) {
//                Uri uri = data.getData();
//                //根据需要，也可以加上Option这个参数
//
//                InputStream inputStream = null;
//                try {
//                    inputStream = getContentResolver().openInputStream(uri);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//            }
//        }
//    }
    //    初始化数据

    private void intData() {
        mTouxiangBean = new MyMaterialTouxiangBean();
        mTouxiangBean.setTitle("头像");

        mTouxiangBean.setTouxiangRes(String.valueOf(R.mipmap.first_page_person_icon_touxiang));

        mList.add(mTouxiangBean);

        mOtherBean = new MyMaterialOtherBean();
        mOtherBean.setContent(UiUtilities.getUser().getNick_name());
        mOtherBean.setTitle("昵称");
        mList.add(mOtherBean);

        mOtherBean = new MyMaterialOtherBean();
        mOtherBean.setContent(String.valueOf(UiUtilities.getUser().getPhone()));
        mOtherBean.setTitle("用户名/手机号");
        mList.add(mOtherBean);

        mOtherBean = new MyMaterialOtherBean();
        mOtherBean.setContent(UiUtilities.getUser().getVendor().getAddress());
        mOtherBean.setTitle("我的收货地址");
        mList.add(mOtherBean);

        mOtherBean = new MyMaterialOtherBean();
        mGender = UiUtilities.getUser().getGender();
        switch (mGender){

            case "M":
                   mOtherBean.setContent("男");
            break;
            case "F":
                   mOtherBean.setContent("女");
            break;
            case "0":
                   mOtherBean.setContent("保密");
            break;
        }
        mOtherBean.setTitle("性别");
        mList.add(mOtherBean);

        mOtherBean = new MyMaterialOtherBean();
        mOtherBean.setContent(UiUtilities.getUser().getDate_of_birth());
        mOtherBean.setTitle("生日");
        mList.add(mOtherBean);

        mOtherBean = new MyMaterialOtherBean();
        mOtherBean.setContent(String.valueOf(UiUtilities.getUser().getId()));
        mOtherBean.setContent(String.valueOf(UiUtilities.getUser().getId()));
        mOtherBean.setTitle("我的推荐码");
        mList.add(mOtherBean);

        mOtherBean = new MyMaterialOtherBean();
        //mState = UiUtilities.getUser().getState();
        switch (mState){

            case 1:
                mOtherBean.setContent("未审核");
            break;
            case 2:
                  mOtherBean.setContent("审核不通过");
            break;
            case 3:
                   mOtherBean.setContent("审核通过");
            break;
        }
        mOtherBean.setTitle("实名认证");
        mList.add(mOtherBean);

        mOtherBean = new MyMaterialOtherBean();
        mOtherBean.setTitle("重置密码");
        mList.add(mOtherBean);
        for (int i = 1; i < mList.size(); i++) {
          MyMaterialOtherBean   disPlay = (MyMaterialOtherBean) mList.get(i);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
