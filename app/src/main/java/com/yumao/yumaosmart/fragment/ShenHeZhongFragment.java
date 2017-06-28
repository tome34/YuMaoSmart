package com.yumao.yumaosmart.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.ShenHeZhongAdapter;
import com.yumao.yumaosmart.bean.GoodsUploadBean;
import com.yumao.yumaosmart.utils.UiUtilities;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kk on 2017/3/22.
 */

public class ShenHeZhongFragment extends Fragment {

    @BindView(R.id.lv_fragment_shenhezhong)
    ListView mLvFragmentShenhezhong;
    private View mFragmentView;
    private Activity mActivity;
    private List<GoodsUploadBean> mList;
    private GoodsUploadBean mGoodsUploadBean;
    private ShenHeZhongAdapter mShenhezhongAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentView = View.inflate(UiUtilities.getContex(), R.layout.layout_fragment_shenhezhong, null);

        ButterKnife.bind(this, mFragmentView);
        return mFragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initData();
        initView();
        initListenner();
    }

    private void initView() {
        mShenhezhongAdapter = new ShenHeZhongAdapter(getActivity(), R.layout.item_fragment_weishangjia, mList);
        mLvFragmentShenhezhong.setAdapter(mShenhezhongAdapter);
    }

    private void initListenner() {
    }

    private void init() {

    }

    private void initData() {
        if (mList == null) {
            mList = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                mGoodsUploadBean = new GoodsUploadBean();
                mGoodsUploadBean.setName("S925银镶翡翠吊坠(A货)");
                mGoodsUploadBean.setSerialNum("E232232434");
                mGoodsUploadBean.setTime("2017.1.12-12:23:24");
                mGoodsUploadBean.setPrice("￥ 1123");
                mGoodsUploadBean.setReason("货物不符合要求");
                mGoodsUploadBean.setPictureRes(String.valueOf(R.mipmap.activity_first_classify_detail_main_pic));
                mGoodsUploadBean.setSelected(true);

                mList.add(mGoodsUploadBean);
            }
        }

    }
}
