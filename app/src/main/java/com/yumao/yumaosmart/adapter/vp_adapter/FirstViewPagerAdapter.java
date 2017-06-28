package com.yumao.yumaosmart.adapter.vp_adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by kk on 2017/3/30.
 */

public class FirstViewPagerAdapter extends PagerAdapter {


    private List<ImageView> mViews;

    public FirstViewPagerAdapter(List<ImageView> views) {

        mViews = views;
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = mViews.get(position);
        container.addView(imageView);
        return imageView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

         container.removeView((View) object);
    }
}
