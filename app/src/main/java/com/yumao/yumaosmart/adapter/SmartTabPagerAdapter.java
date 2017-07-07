package com.yumao.yumaosmart.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yumao.yumaosmart.bean.SmartTabInfo;

import java.util.List;

/*
    PagerAdapter-->view
    FramgentPagerAdapter-->Fragment-->会缓存Framgent
    FragmentStatePagerAdapter-->Fragment-->会缓存FragmentState
 */
public class SmartTabPagerAdapter extends FragmentPagerAdapter {

    List<SmartTabInfo> mSmartTabInfos;
    Context            mContext;

    public SmartTabPagerAdapter(FragmentManager fm, List<SmartTabInfo> smartTabInfos, Context context) {
        super(fm);
        mSmartTabInfos = smartTabInfos;
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        SmartTabInfo smartTabInfo = mSmartTabInfos.get(position);
        Class clz = smartTabInfo.clz;
        Bundle args = smartTabInfo.args;

        //参数
//            Bundle args = new Bundle();
//            args.putString("args", "我是参数" + position);
           /* //创建Fragment
            NewsPagerFragment fragment = new NewsPagerFragment();
            //设置参数
            fragment.setArguments(args);*/

        //创建Fragment的同时传递参数
//            Class clz = NewsPagerFragment.class;
        Fragment fragment = Fragment.instantiate(mContext, clz.getName(), args);

        return fragment;
    }

    @Override
    public int getCount() {
        if (mSmartTabInfos != null) {
            return mSmartTabInfos.size();
        }
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
//            String title = mNewsTabTitleArr[position];
        SmartTabInfo smartTabInfo = mSmartTabInfos.get(position);
        return smartTabInfo.title;
    }
}