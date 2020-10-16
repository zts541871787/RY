package com.zts.ryx;

import android.app.Application;

import io.rong.imkit.RongIM;

public class MyAppliCation extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(getApplicationContext(),"pwe86ga5p9pi6",true);
    }
}
