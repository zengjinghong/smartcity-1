package com.example.demo;

import android.app.Application;

import com.example.demo.utils.OkHttpUtil;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpUtil.init(getApplicationContext());
    }
}
