package com.yumao.yumaosmart.utils;

/**
 * Created by kk on 2017/4/5.
 */

public class NumberGenerate {
    public static  String generate(float price, float cost,int id){
     String [] arr  = {"X","A","B","C","D","E","F","G","H","I","J","K","L","M",
             "N","O","P","Q","R","S","T","U","V","W","Y","Z"};
        float rate = Math.abs(45 * (price - cost) / price);
        int rateInt = (int) Math.floor(rate);
        int endPostion=rateInt%10;
        int firstPostion=rateInt/10;
        String length = String.valueOf(id);
        int lengthId = length.length();
        int size=8-lengthId;
        String zero = "";
        for (int i = 0; i <size; i++) {
            zero=zero+"0";
        }
            String result=arr[firstPostion]+arr[endPostion]+zero+id;
        return  result;
    }
}
