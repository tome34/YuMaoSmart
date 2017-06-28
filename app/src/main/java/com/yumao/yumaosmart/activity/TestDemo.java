package com.yumao.yumaosmart.activity;

import android.content.Context;
import android.content.res.AssetManager;

import com.yumao.yumaosmart.utils.UiUtilities;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kk on 2017/4/13.
 */

public class TestDemo {
    public  void method() throws IOException {
        Context contex = UiUtilities.getContex();
        AssetManager assets = contex.getAssets();
        InputStream open = assets.open("province.json");



    }
}
