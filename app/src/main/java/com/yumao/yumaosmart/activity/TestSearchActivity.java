package com.yumao.yumaosmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.constant.Constant;
import com.yumao.yumaosmart.utils.LogUtils;
import com.yumao.yumaosmart.utils.SPUtils;
import com.yumao.yumaosmart.widgit.CSearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yumao.yumaosmart.base.MyApplication.mContext;

public class TestSearchActivity extends AppCompatActivity {

    List<String> bb = Arrays.asList("a", "ab", "abc", "bc", "bcd", "ac");
    @BindView(R.id.search_view)
    CSearchView mSearchView;
    @BindView(R.id.activity_test_search)
    LinearLayout mActivityTestSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_search);
        ButterKnife.bind(this);
        initSearch();
    }


    private void initSearch() {
        mSearchView.setOnSearchViewListener(new CSearchView.OnSearchViewListener() {
            @Override
            public void clean() {
                clearHistory(); //清空搜索数据
            }

            @Override
            public void hasFocus() {  //获得光标
                showHistory();
            }

            @Override
            public void onSearch(String searchText) {
                if (searchText != null) {
                    saveSearchHistory(searchText);
                    LogUtils.d("搜索结果:" + searchText);
                    // TODO: 7/25/2017 跳转到搜索界面进行搜索

                    Intent intent = new Intent(TestSearchActivity.this, SearchViewActivity.class);
                    intent.putExtra(Constant.SEARCH_RESULT, searchText);
                    startActivity(intent);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    //searchData(s.toString());
                } else {
                    showHistory();
                }

            }

            @Override
            public void cancel() {
                mSearchView.setVisibility(View.INVISIBLE);
                //mTitleBar.setVisibility(View.VISIBLE);
                mSearchView.hideView();

            }

            @Override
            public void onItemDel(int position) {
                removeSearchHistory(position);
                if (getSearchHistory() != null && getSearchHistory().size() == 0) {
                    //mFlContent.setVisibility(View.INVISIBLE);
                }
            }


        });
    }

    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    List<String> list = (List<String>) msg.obj;
                    mSearchView.updata(list);
                    break;
                default:
                    break;
            }
            return true;
        }
    });

    private void searchData(final String s) {
        final List<String> lists = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String s1 = s.toString();
                for (String s2 : bb) {
                    if (s2.contains(s1)) {
                        lists.add(s2);
                    }
                }
                Message message = new Message();
                message.what = 0;
                message.obj = lists;

                mHandler.sendMessage(message);

            }
        }).start();
    }


    public final static String SEARCH_HISTORY = "search_history";

    // 调用
    public void showHistory() {
        if (getSearchHistory() == null) {
                    LogUtils.d("showHistory");
        } else {
            mSearchView.showHistory(getSearchHistory());
            // mFlContent.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 获取历史搜索数据
     *
     * @return
     */
    public List<String> getSearchHistory() {
        // String searchHistory = (String) SPUtils.get(mContext, SEARCH_HISTORY, "");
        String searchHistory = SPUtils.getString(mContext, SEARCH_HISTORY, "");
        return TextUtils.isEmpty(searchHistory) ? null : new Gson().fromJson(searchHistory, SearchHistoryBean.class).history;
    }

    /**
     * 单个添加保存搜索数据
     *
     * @param newSearch
     */
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
        // SPUtils.put(mContext, SEARCH_HISTORY, json);
        SPUtils.putString(mContext, SEARCH_HISTORY, json);
    }

    /**
     * 移除单个搜索数据
     *
     * @param index
     */
    public void removeSearchHistory(int index) {
        List<String> searchHistory = getSearchHistory();
        if (searchHistory == null || searchHistory.size() == 0) {
            return;
        }
        if (searchHistory.size() < index) {
            return;
        }
        if (searchHistory.size() == 1) {
            clearHistory();
        } else {
            searchHistory.remove(index);
            SearchHistoryBean searchHistoryBean = new SearchHistoryBean();
            searchHistoryBean.history = searchHistory;
            String json = new Gson().toJson(searchHistoryBean);
            // SPUtils.put(mContext, SEARCH_HISTORY, json);
            SPUtils.putString(mContext, SEARCH_HISTORY, json);
        }
    }

    /**
     * 清空搜索数据
     */
    public void clearHistory() {
        //SPUtils.put(mContext, SEARCH_HISTORY, "");
        SPUtils.putString(mContext, SEARCH_HISTORY, "");
    }


    class SearchHistoryBean {
        public List<String> history;
    }
}
