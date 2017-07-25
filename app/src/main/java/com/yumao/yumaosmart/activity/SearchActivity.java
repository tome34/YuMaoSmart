package com.yumao.yumaosmart.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.SPUtilsName;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yumao.yumaosmart.base.MyApplication.mContext;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.search_et_input)
    EditText mSearchEtInput;
    @BindView(R.id.search_iv_delete)
    ImageView mSearchIvDelete;
    @BindView(R.id.search_btn_back)
    Button mSearchBtnBack;
    @BindView(R.id.search_lv_tips)
    ListView mSearchLvTips;
    @BindView(R.id.activity_search)
    LinearLayout mActivitySearch;

    public final static String SEARCH_HISTORY = "search_history";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initData();
        initListener();

    }


    //初始化数据
    private void initData() {

        showHistory();
        mSearchEtInput.addTextChangedListener(new EditChangedListener());
        mSearchEtInput.setOnClickListener(this);



    }

    //监听事件
    private void initListener() {

    }

    //edit的监听
    private class EditChangedListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }


    // 调用
    public void showHistory() {
        if (getSearchHistory() == null) {
            // TODO: 7/24/2017 没有结果的时候处理

        } else {
            // TODO: 7/24/2017 有结果的时候,就是个list
            List<String> searchHistory = getSearchHistory();
                LogUtils.d("tag","历史记录:"+searchHistory);
        }

    }


    public List<String> getSearchHistory() {
        String searchHistory = (String) SPUtilsName.get(mContext, SEARCH_HISTORY, null);
        return searchHistory == null ? null : new Gson().fromJson(searchHistory, SearchHistoryBean.class).history;
    }

    public void saveSearchHistory(String newSearch) {
        boolean equals = false;
        List<String> strings = getSearchHistory() == null ? new ArrayList<String>() : getSearchHistory();
        for (int i = strings.size() - 1; i >= 0; i--) {
            equals = newSearch.equals(strings.get(i));
            if (equals) {
                strings.remove(i);
                strings.add(newSearch);
                break;
            }
        }
        if (!equals) {
            strings.add(newSearch);
        }
        SearchHistoryBean searchHistoryBean = new SearchHistoryBean();
        searchHistoryBean.history = strings;
        String json = new Gson().toJson(searchHistoryBean);
        SPUtilsName.put(mContext, SEARCH_HISTORY, json);
    }

    class SearchHistoryBean {
        public List<String> history;
    }

    @OnClick({R.id.search_et_input, R.id.search_btn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_et_input:
                break;
            case R.id.search_btn_back:
                break;
        }
    }
}
