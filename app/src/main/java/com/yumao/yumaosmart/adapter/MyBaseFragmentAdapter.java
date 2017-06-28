package com.yumao.yumaosmart.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by kk on 2017/2/24.
 */

public class MyBaseFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList;


    public MyBaseFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);

    }


    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
