package com.umeng.soexample.app;

import android.app.Application;

import androidx.multidex.MultiDex;

import io.realm.Realm;

public class MyApp extends Application {

    public static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Realm.init(this);
        MultiDex.install(this);
    }
}
