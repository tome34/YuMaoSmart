package com.yumao.yumaosmart.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by kk on 2017/2/24.
 */

public class MyBaseAdapter<ITEMBEAN> extends BaseAdapter {
   public List<ITEMBEAN> mList;

   public MyBaseAdapter(List<ITEMBEAN> list) {
      mList = list;
   }

   @Override
   public int getCount() {
      return mList.size();
   }

   @Override
   public Object getItem(int position) {
      return mList.get(position);
   }

   @Override
   public long getItemId(int position) {
      return position;
   }

   @Override
   public View getView(int position, View convertView, ViewGroup parent) {

      return null;
   }
}
