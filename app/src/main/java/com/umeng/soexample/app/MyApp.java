package com.umeng.soexample.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

public class MyApp extends Application {

    public static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
