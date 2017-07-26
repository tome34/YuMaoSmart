package com.yumao.yumaosmart.widgit;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.adapter.SearchViewAdapter;
import com.yumao.yumaosmart.utils.KeyboardUtils;
import com.yumao.yumaosmart.utils.UiUtilities;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:
 * Created by cheegon on 7/24/2017.
 */

public class CSearchView extends LinearLayout {
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    @BindView(R.id.iv_clear)
    ImageView mIvClear;
    @BindView(R.id.list_view)
    SwipeMenuListView mListView;
    @BindView(R.id.iv_rubbish)
    ImageView mIvRubbish;
    @BindView(R.id.ll_container)
    RelativeLayout mLlContainer;// 历史记录显示框
    @BindView(R.id.et_search)
    EditText mEtSearch;
    private Context mContext;
    List<String> mSearchHistory;
    private SearchViewAdapter mAdapter;
    private OnSearchViewListener mOnSearchViewListener;

    public CSearchView(Context context) {
        this(context, null);
    }

    public CSearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CSearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.search_view, this, true);
        ButterKnife.bind(this);
        mSearchHistory = new ArrayList<>();
        //初始化listview
        initListView();
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mOnSearchViewListener.afterTextChanged(s);
                if (TextUtils.isEmpty(s.toString())) {
                    mIvClear.setVisibility(View.INVISIBLE);

                } else {
                    mIvClear.setVisibility(View.VISIBLE);
                }
            }
        });

        mEtSearch.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //如果点击了软键盘的搜索
                if (keyCode == KeyEvent.KEYCODE_SEARCH||keyCode==KeyEvent.KEYCODE_ENTER) {
                    String trim = mEtSearch.getText().toString().trim();
                    mOnSearchViewListener.onSearch(trim);
                    // 隐藏软键盘
                    KeyboardUtils.hideSoftInput(mContext, mEtSearch);
                    mListView.setVisibility(View.INVISIBLE);
                    mLlContainer.setVisibility(View.INVISIBLE);
                  //  mEtSearch.setText("");
                }
                return false;
            }
        });

        //设置编辑光标
        mEtSearch.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v.getId() == R.id.et_search && hasFocus) {
                    mTvCancel.setVisibility(View.VISIBLE);  //取消按钮可见的
                    mOnSearchViewListener.hasFocus();
                }
            }
        });

    }

    private void initListView() {
        mAdapter = new SearchViewAdapter(mSearchHistory);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = mSearchHistory.get(mSearchHistory.size() - position - 1);
                mOnSearchViewListener.onSearch(s);
                // 隐藏软键盘
                KeyboardUtils.hideSoftInput(mContext, CSearchView.this);
                mListView.setVisibility(View.INVISIBLE);
                mLlContainer.setVisibility(View.INVISIBLE);
                // mEtSearch.setText(s);
            }
        });

        //侧滑删除
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext);
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                deleteItem.setWidth(dip2px(UiUtilities.getContex(),80f));
                deleteItem.setIcon(R.drawable.ic_delete);
                menu.addMenuItem(deleteItem);
            }
        };

        mListView.setMenuCreator(creator);
        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        int i = mSearchHistory.size() - position - 1;
                        mSearchHistory.remove(i);
                        mAdapter.notifyDataSetChanged();
                        mOnSearchViewListener.onItemDel(i);
                        if (mSearchHistory.size()==0) {
                            //mLlContainer.setVisibility(View.INVISIBLE);
                            mEtSearch.clearFocus();
                            KeyboardUtils.hideSoftInput(mContext, mEtSearch);
                        }
                        break;
                }
                return false;// false : close the menu; true : not close the menu
            }
        });
    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @OnClick({R.id.tv_cancel, R.id.iv_clear, R.id.iv_rubbish, R.id.ll_container})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:// 取消
                mOnSearchViewListener.cancel();

                break;
            case R.id.iv_clear:// 删除
                mEtSearch.setText("");
                break;
            case R.id.iv_rubbish:// 删除历史记录
               // mLlContainer.setVisibility(View.INVISIBLE);
                mSearchHistory.clear();
                mAdapter.notifyDataSetChanged();
                mOnSearchViewListener.clean();
                mEtSearch.clearFocus();
                KeyboardUtils.hideSoftInput(mContext, mEtSearch);
                break;

        }
    }

    public void setOnSearchViewListener(OnSearchViewListener onSearchViewListener) {

        mOnSearchViewListener = onSearchViewListener;
    }

    public void hideView() {
        KeyboardUtils.hideSoftInput(mContext, CSearchView.this);
        mTvCancel.setVisibility(View.INVISIBLE);
        mEtSearch.setText("");
        mIvClear.setVisibility(View.INVISIBLE);
        mListView.setVisibility(View.INVISIBLE);
        mEtSearch.clearFocus();
        mLlContainer.setVisibility(View.INVISIBLE);
    }

    /**
     * 更新关键字搜索
     *
     * @param list
     */
    public void updata(List<String> list) {
        mLlContainer.setVisibility(View.GONE);
        mListView.setVisibility(View.VISIBLE);
        mSearchHistory.clear();
        mSearchHistory.addAll(list);
        mAdapter.setType(1);
        mAdapter.notifyDataSetChanged();
    }

    public void showView() {
        mEtSearch.requestFocus();
        KeyboardUtils.showSoftInput(mEtSearch, mContext);
    }

    public interface OnSearchViewListener {

        void clean();  //清空搜索数据

        void hasFocus();  //hasFocus:用来判断视图是否获得了焦点

        void onSearch(String searchText);

        void afterTextChanged(Editable s);

        void cancel();//取消

        void onItemDel(int position);
    }


    public void showHistory(List<String> searchHistory) {
        mLlContainer.setVisibility(View.VISIBLE);
        mListView.setVisibility(View.VISIBLE);
        mSearchHistory.clear();
        mSearchHistory.addAll(searchHistory);
        mAdapter.setType(0);
        mAdapter.notifyDataSetChanged();
    }

}
