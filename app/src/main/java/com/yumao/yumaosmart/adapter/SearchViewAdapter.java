package com.yumao.yumaosmart.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yumao.yumaosmart.R;
import com.yumao.yumaosmart.utils.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Desc:
 * Created by cheegon on 7/25/2017.
 */

public class SearchViewAdapter extends BaseAdapter {
    private List<String> mSearchHistory;
    private int mType;

    public SearchViewAdapter(List<String> searchHistory) {
        mSearchHistory = searchHistory;
    }

    @Override
    public int getCount() {
        return mSearchHistory == null ? 0 : mSearchHistory.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.updata(mSearchHistory.get(mSearchHistory.size() - position - 1));
        Log.d(TAG, "getView: "+mSearchHistory.size() +",position1:"+position);
        return convertView;
    }

    public void setType(int type) {

        mType = type;
    }

     class ViewHolder {
        @BindView(R.id.iv_type)
        ImageView mIvType;
        @BindView(R.id.tv_history)
        TextView mTvHistory;
        public ViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }

        public void updata(String s) {
            mTvHistory.setText(s);
            LogUtils.d("条目内容:"+s);
            //mIvType.setImageResource(mType==0?R.drawable.history:R.drawable.history);
         //
        }
    }
}
