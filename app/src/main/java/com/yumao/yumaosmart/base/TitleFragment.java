package com.yumao.yumaosmart.base;

import android.view.View;
import android.view.ViewStub;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.widgit.TitleBar;

import butterknife.BindView;


/**
 * Created by cheegon on 3/18/2017.
 */

public abstract class TitleFragment extends BFragment {
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_title;
    }

    @Override
    protected void onBindViewBefore(View rootView) {
        super.onBindViewBefore(rootView);
        ViewStub stub = (ViewStub) rootView.findViewById(R.id.lay_content);
        stub.setLayoutResource(getContentLayoutId());
        stub.inflate();
      /*  FrameLayout viewById = (FrameLayout) rootView.findViewById(R.id.lay_content);
        View inflate = View.inflate(mContext, getContentLayoutId(), null);
        viewById.addView(inflate);*/
    }

    /**
     * 獲取內容佈局
     *
     * @return
     */
    protected abstract int getContentLayoutId();

    @Override
    protected void initWidget(View rootView) {
        super.initWidget(rootView);
        mTitleBar.setTitle(getTitleRes());
        mTitleBar.setIcon(getIconRes());
        mTitleBar.setIconOnClickListener(getIconClickListener());
    }

    /**
     * 獲取headbar的圖片的監聽器
     *
     * @return
     */
    protected View.OnClickListener getIconClickListener() {
        return null;
    }

    /**
     * 獲取headbar的圖片
     *
     * @return
     */
    protected int getIconRes() {
        return 0;
    }

    /**
     * 獲取headbar的標題
     *
     * @return
     */
    protected abstract int getTitleRes();

}
