package com.yumao.yumaosmart.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kk on 2017/3/13.
 */

public class MyOrderlistBean implements Parcelable {
    private String name;
    private int postion;

    public MyOrderlistBean(String name, int postion) {
        this.name = name;
        this.postion = postion;
    }

    protected MyOrderlistBean(Parcel in) {
        name = in.readString();
        postion = in.readInt();
    }

    public static final Creator<MyOrderlistBean> CREATOR = new Creator<MyOrderlistBean>() {
        @Override
        public MyOrderlistBean createFromParcel(Parcel in) {
            return new MyOrderlistBean(in);
        }

        @Override
        public MyOrderlistBean[] newArray(int size) {
            return new MyOrderlistBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(postion);
    }
}
