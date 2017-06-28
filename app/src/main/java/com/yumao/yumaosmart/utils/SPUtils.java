
package com.yumao.yumaosmart.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.yumao.yumaosmart.constant.Constant;

/**
 * ClassName:SPUtils <br/>
 * Function: SharedPreferences工具类. <br/>
 * Date: 2016年9月3日 上午10:53:26 <br/>
 * 
 *
 * @version
 */
public class SPUtils {

    // 获取一个boolean值, 默认值为false
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    // 获取一个boolean值
    public static boolean getBoolean(Context context, String key, boolean def) {
        SharedPreferences sp = context.getSharedPreferences(
                Constant.SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, def);
    }

    // 保存一个boolean值
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(
                Constant.SP_FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    // 获取一个String值, 默认值为null
    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    // 获取一个String值
    public static String getString(Context context, String key, String def) {
        SharedPreferences sp = context.getSharedPreferences(
                Constant.SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, def);
    }

    // 保存一个String值
    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(
                Constant.SP_FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    // 获取一个int值, 默认值为-1
    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }

    // 获取一个int值
    public static int getInt(Context context, String key, int def) {
        SharedPreferences sp = context.getSharedPreferences(
                Constant.SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, def);
    }

    // 保存一个int值
    public static void putInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(
                Constant.SP_FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

}
