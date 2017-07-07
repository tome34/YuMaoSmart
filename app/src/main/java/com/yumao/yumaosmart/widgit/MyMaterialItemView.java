package com.yumao.yumaosmart.widgit;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yumao.yumaosmart.R;


/**
 * Created by kk on 2017/6/28.
 */

public class MyMaterialItemView extends RelativeLayout {

    private ImageView ivToggle ;

    // 使用代码创建View的时候使用
    public MyMaterialItemView(Context context) {
        super(context,null);
    }

    // 用在xml文件中使用View的时候
    public MyMaterialItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 加载一个布局,挂载视图
        View.inflate(context, R.layout.item_material_other,this);

        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.MyMaterialItem);

        //设置标题文本
        CharSequence text = ta.getText(R.styleable.MyMaterialItem_materialText);
        TextView tvText = (TextView)findViewById(R.id.tv_mymaterial_other_title);
        tvText.setText(text);

        //设置内容文本
        CharSequence text2 = ta.getText(R.styleable.MyMaterialItem_materialText2);
        TextView tvText2 = (TextView)findViewById(R.id.tv_my_material_other_content);
        tvText2.setText(text2);

        //设置图标是否显示
        boolean flag = ta.getBoolean(R.styleable.MyMaterialItem_materialToggle,true);
        ivToggle = (ImageView)findViewById(R.id.iv_mymaterial);
        ivToggle.setVisibility(flag ? View.VISIBLE :View.GONE);

        //释放资源
        ta.recycle();
    }
}
