package com.yumao.yumaosmart.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.Toast;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.ClassifyLvAdapter;
import com.yumao.yumaosmart.adapter.ClassifyRvAdapter;
import com.yumao.yumaosmart.base.TitleFragment;
import com.yumao.yumaosmart.bean.ClassifyBean;
import com.yumao.yumaosmart.callback.CategoriesCallback;
import com.yumao.yumaosmart.callback.CategoriesContentCallback;
import com.yumao.yumaosmart.mode.CategoriesContentMode;
import com.yumao.yumaosmart.mode.ClassiFyMode;
import com.yumao.yumaosmart.utils.UiUtilities;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * Created by kk on 2017/2/24.
 */

public class ClassifyFragment extends TitleFragment {
    @BindView(R.id.rv_classify)
    RecyclerView mRvClassify;
    @BindView(R.id.lv_classify)
    ListView mLvClassify;

    private List<ClassifyBean> mContentData = new ArrayList<>();


    private ClassifyBean mClassifyBean;
    private ClassifyLvAdapter mClassifyLvAdapter;
    private ClassifyRvAdapter mClassifyRvAdapter;
    private List<ClassiFyMode> mTitleResponse;
    private int mPostion = 0;


    //    分类Fragment右边数据
    public void initClassifyContent(int postion) {
//        mClassifybean1 = new ClassifyBean(String.valueOf(R.mipmap.classify_guajian), "挂件");
//        mClassifybean2 = new ClassifyBean(String.valueOf(R.mipmap.classify_shouzhuo), "手镯");
//        mClassifybean3 = new ClassifyBean(String.valueOf(R.mipmap.classify_bawan), "手玩");
//        mClassifybean4 = new ClassifyBean(String.valueOf(R.mipmap.classify_jiemian), "戒面");
//        mClassifybean5 = new ClassifyBean(String.valueOf(R.mipmap.classify_xiangshishiping), "镶嵌饰品");
//        mClassifybean6 = new ClassifyBean(String.valueOf(R.mipmap.classify_other), "其他");
//      mContentData.add(mClassifybean1);
//      mContentData.add(mClassifybean2);
//      mContentData.add(mClassifybean3);
//      mContentData.add(mClassifybean4);
//      mContentData.add(mClassifybean5);
////
        int classifyId = getId(postion);


        OkHttpUtils
                .get()
                .url("https://test-dist.yumao168.com/api/categories/" + classifyId)
                .build()
                .execute(new CategoriesContentCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), "连接超时", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(CategoriesContentMode response, int id) {
                        List<CategoriesContentMode.ChildrenBeanX> children = response.getChildren();
                        setContent(children);
                    }
                });

    }

    private void setContent(List mode) {
        mRvClassify.setLayoutManager(new GridLayoutManager(UiUtilities.getContex(), 3));
        mClassifyRvAdapter = new ClassifyRvAdapter(UiUtilities.getContex(), R.layout.item_classify_content, mode, getActivity());
        mRvClassify.setAdapter(mClassifyRvAdapter);

    }

    private void initClassifyTitle(List<ClassiFyMode> data) {
        mClassifyLvAdapter = new ClassifyLvAdapter(UiUtilities.getContex(), R.layout.item_classify, data, this);
        mLvClassify.setAdapter(mClassifyLvAdapter);
        mLvClassify.setDividerHeight(10);
    }

    @Override
    protected void initData() {
        super.initData();
        OkHttpUtils
                .get()
                .url("https://test-dist.yumao168.com/api/categories")
                .build()
                .execute(new CategoriesCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), "连接超时", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(List<ClassiFyMode> response, int id) {
                        mTitleResponse = response;
                        initData(mTitleResponse);

                        initClassifyContent(mPostion);
                    }
                });
    }

    private void initData(List<ClassiFyMode> response) {
        initClassifyTitle(response);
    }

    public int getId(int postion) {

        return mTitleResponse.get(postion).getId();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_clasify;
    }

    @Override
    protected int getTitleRes() {
        return R.string.clasify_title;
    }
}
