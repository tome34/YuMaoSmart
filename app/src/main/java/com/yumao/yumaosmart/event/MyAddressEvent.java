package com.yumao.yumaosmart.event;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kk on 2017/4/11.
 */
public class MyAddressEvent implements Parcelable {
    public String name;
    public String area;
    public int aid;
    public boolean isDefaultAddress;
    public  String detailAddress;
    public  String phoneNum;
    public String getArea() {
        return area;
    }
    public  int state_province;
    public  int city_id;
    public  int county_id;

    public int getState_province() {
        return state_province;
    }

    public void setState_province(int state_province) {
        this.state_province = state_province;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getCounty_id() {
        return county_id;
    }

    public void setCounty_id(int county_id) {
        this.county_id = county_id;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public MyAddressEvent(){

    }
    public MyAddressEvent(String name,String area, String detailAddress, String phoneNum, boolean isDefaultAddress,int aid) {
        this.name = name;
        this.area=area;
        this.aid=aid;
        this.detailAddress = detailAddress;
        this.phoneNum = phoneNum;
        this.isDefaultAddress = isDefaultAddress;

    }

    protected MyAddressEvent(Parcel in) {
        area=in.readString();
        name = in.readString();
        aid=in.readInt();
        detailAddress = in.readString();
        phoneNum = in.readString();
        state_province=in.readInt();
        city_id=in.readInt();
        county_id=in.readInt();
        isDefaultAddress = in.readByte() != 0;

    }

    public static final Creator<MyAddressEvent> CREATOR = new Creator<MyAddressEvent>() {
        @Override
        public MyAddressEvent createFromParcel(Parcel in) {
            return new MyAddressEvent(in);
        }

        @Override
        public MyAddressEvent[] newArray(int size) {
            return new MyAddressEvent[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public boolean isDefaultAddress() {
        return isDefaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        isDefaultAddress = defaultAddress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(area);
        dest.writeString(name);
        dest.writeInt(aid);
        dest.writeString(detailAddress);
        dest.writeString(phoneNum);
        dest.writeInt(state_province);
        dest.writeInt(city_id);
        dest.writeInt(county_id);
        dest.writeByte((byte) (isDefaultAddress ? 1 : 0));
    }
}
