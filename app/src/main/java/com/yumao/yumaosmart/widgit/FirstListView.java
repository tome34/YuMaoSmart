package com.yumao.yumaosmart.widgit;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by kk on 2017/3/3.
 */

public class FirstListView extends ListView {
    public FirstListView(Context context) {
        this(context, null);
    }

    public FirstListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FirstListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,

                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
