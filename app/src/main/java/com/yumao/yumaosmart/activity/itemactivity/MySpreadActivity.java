package com.yumao.yumaosmart.activity.itemactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.base.BaseItemActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MySpreadActivity extends BaseItemActivity {

    @BindView(R.id.rel_activity_spread_erweima_my)
    RelativeLayout mRelActivitySpreadErweimaMy;
    @BindView(R.id.rel_activity_spread_recommend)
    RelativeLayout mRelActivitySpreadRecommend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_spread);
        initToobar(getString(R.string.title_myspread));
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rel_activity_spread_erweima_my, R.id.rel_activity_spread_recommend})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rel_activity_spread_erweima_my:
                startActivity(new Intent(this,MyErWeiMaActivity.class));
                break;
            case R.id.rel_activity_spread_recommend:
                startActivity(new Intent(this,RecommendErWeiMaActivity.class));
                break;
        }
    }
}
