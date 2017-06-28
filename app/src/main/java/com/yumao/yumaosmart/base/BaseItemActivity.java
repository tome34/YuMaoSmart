package com.yumao.yumaosmart.base;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.yumao.yumaosmart.R;

/**
 * Created by kk on 2017/3/20.
 */

public class BaseItemActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void initToobar(String title) {
        Toolbar tb = getToolBar();
        setSupportActionBar(tb);
        tb.setNavigationIcon(R.mipmap.back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView tv = (TextView) findViewById(R.id.tv_titlle);
        tv.setText(title);
        initStatusBar();
    }

    public Toolbar getToolBar() {
        return (Toolbar) findViewById(R.id.my_toolbar);
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }


}
