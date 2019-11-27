package com.cy.mvpframetest;

import android.app.Application;


import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;

/**
 * Created by lenovo on 2019/5/25
 * 功能描述：
 */
public class MyApp extends Application {
    private static MyApp instance;
    public static final HashMap<String, List<Cookie>> cookieStore = new HashMap<String, List<Cookie>>();

    public static MyApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 对Snake进行初始化
        instance = this;
    }
}
