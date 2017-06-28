package com.yumao.yumaosmart.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.FirstClassifyDetailAdapter;
import com.yumao.yumaosmart.callback.CategoriesContentCallback;
import com.yumao.yumaosmart.callback.FirstClassifyDetailCallback;
import com.yumao.yumaosmart.mode.CategoriesContentMode;
import com.yumao.yumaosmart.mode.FirstClassifyDetailMode;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.widget.CustomRadioGroup;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class FirstClassifyDetail extends AppCompatActivity {

    @BindView(R.id.btn_first_classify_detail_update)
    RadioButton mBtnFirstClassifyDetailUpdate;
    @BindView(R.id.btn_first_classify_detail_price)
    RadioButton mBtnFirstClassifyDetailPrice;
    @BindView(R.id.btn_first_classify_detail_choose)
    RadioButton mBtnFirstClassifyDetailChoose;
    @BindView(R.id.radiogroup_first_classify_detail)
    RadioGroup mRadiogroupFirstClassifyDetail;

    @BindView(R.id.sv)
    ScrollView mSv;

    @BindView(R.id.activity_first_classify_detail)
    LinearLayout mActivityFirstClassifyDetail;
    @BindView(R.id.gv_first_classify_detail)
    GridView mGvFirstClassifyDetail;

    private String mText;
    private List<String> mTextList = new ArrayList<>();

    private List<String> categry = new ArrayList<>();
    private CustomRadioGroup mCustomRadioGroup;
    private List mData = new ArrayList();
    private int mCategoryId;
    private int mPotion =0;
    private Integer mId;
    private RadioButton mRadioButton;
    private List<CategoriesContentMode.ChildrenBeanX> mChildren;
    private CategoriesContentMode.ChildrenBeanX mChildrenBeanX;
    private Map<String, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_classify_detail);
        ButterKnife.bind(this);
        initStatusBar();
        setCategoryId();
        init();
    }
//初始化mPostion和mCategoryId
    private void setCategoryId() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mCategoryId = extras.getInt("mCategoryId");


    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    private void initData(List<CategoriesContentMode.ChildrenBeanX> data) {


    }

    private void initContent(List<CategoriesContentMode.ChildrenBeanX> data) {
            map.clear();

        if (mPotion == 0) {

        } else {
            mChildrenBeanX = data.get(mPotion-1);
            mId = mChildrenBeanX.getId();

            map.put("category_id", String.valueOf(mId));
        }

        map.put("page", String.valueOf(1));
        map.put("limit", String.valueOf(20));
        map.put("sort_by", "id");
        map.put("order", "DESC");
        OkHttpUtils
                .get()
                .url("https://dist.yumao168.com/api/products")
                .params(map)
                .build()
                .execute(new FirstClassifyDetailCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(List<FirstClassifyDetailMode> response, int id) {

                        initView(response);
                    }
                });


    }

    private void initView(List<FirstClassifyDetailMode> data) {
        mGvFirstClassifyDetail.setNumColumns(2);
        mGvFirstClassifyDetail.setAdapter(new FirstClassifyDetailAdapter(this, R.layout.item_activity_first_detail, data,this));
        mGvFirstClassifyDetail.setFocusable(false);
    }

    private void init() {
        initRadioGroup();
    }

    private void setSpacing(CustomRadioGroup cg, int widthdp, int heightdp) {
        cg.setHorizontalSpacing(widthdp);
        cg.setVerticalSpacing(heightdp);

    }

    private void initRadioGroup() {
        setDataForRadioGroup();
        mRadiogroupFirstClassifyDetail.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                group.check(checkedId);


            }
        });
        mCustomRadioGroup = (CustomRadioGroup) findViewById(R.id.customRadioGroup);
        setSpacing(mCustomRadioGroup, 12, 8);

        mCustomRadioGroup.setListener(new CustomRadioGroup.OnclickListener() {
            @Override
            public void OnText(String text) {
                mText = text;
                mPotion = mTextList.indexOf(text);
                if (mChildren != null) {
                    initContent(mChildren);
                }

            }
        });


    }

    //    初始化RadioGroup,请求网络 获取去RadioButton显示数据,完了更新内容的视图
    private void setDataForRadioGroup() {
        OkHttpUtils
                .get()
                .url("https://dist.yumao168.com/api/categories/" + mCategoryId)
                .build()
                .execute(new CategoriesContentCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(FirstClassifyDetail.this, "网络连接失败", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(CategoriesContentMode response, int id) {
                        mChildren = response.getChildren();
                        initData(mChildren);
                        for (int i = 0; i < mChildren.size() + 1; i++) {
                            mRadioButton = (RadioButton) FirstClassifyDetail.this.getLayoutInflater().inflate(R.layout.radiobutton_addcart, null);
                            if (i == 0) {
                                mRadioButton.setText("所有");
                                mTextList.add("所有");
                                mCustomRadioGroup.addView(mRadioButton);
                                mRadioButton.setChecked(true);
                            } else {
                                mRadioButton.setText(mChildren.get(i - 1).getName());
                                mTextList.add(mChildren.get(i - 1).getName());
                                mCustomRadioGroup.addView(mRadioButton);

                            }


                        }

                        initContent(mChildren);
                    }
                });

    }


}
