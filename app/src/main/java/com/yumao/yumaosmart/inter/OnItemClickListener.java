package com.yumao.yumaosmart.inter;

import android.view.View;

/**
 * Created by kk on 2017/7/6.
 * 个人中心的recyclerview的点击事件
 *
 */

public interface OnItemClickListener {
    //点击事件
    void onItenClick(View view ,int position);

    //长按事件
    void onItemLongCllick(View view ,int position);
}
