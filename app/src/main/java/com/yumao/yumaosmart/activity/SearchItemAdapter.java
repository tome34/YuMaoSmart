package com.yumao.yumaosmart.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yumao.yumaosmart.R;

import java.util.List;

/**
 * Created by kk on 2017/7/24.
 */

public class SearchItemAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mListData ;


    public SearchItemAdapter() {
    }

    public SearchItemAdapter(Context context , List<String> listData) {
        mContext = context ;
        mListData = listData ;
    }

    @Override
    public int getCount() {
        //在此适配器中所代表的数据集中的条目数
        return mListData.size();
    }

    @Override
    public String getItem(int position) {
        //获取数据集中与指定索引对应的数据项
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        //获取在列表中与指定索引对应的行id
        return position;
    }

    //获取一个在数据集中指定索引的视图来显示数据
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder =null ;
        //如果缓存convertView为空，则需要创建View
        if (convertView == null){
            holder = new ViewHolder();
            //根据自定义的Item布局加载布局
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_search_adapter,null);
            holder.title = (TextView) convertView.findViewById(R.id.titleTv);

            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(mListData.get(position));


        return convertView;
    }
    //ViewHolder静态类
    public static class ViewHolder{
        public TextView title;
    }
}
