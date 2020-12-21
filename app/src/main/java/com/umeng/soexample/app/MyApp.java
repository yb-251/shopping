package com.umeng.soexample.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.umeng.soexample.ui.shop.db.DaoMaster;
import com.umeng.soexample.ui.shop.db.DaoSession;

public class MyApp extends Application {

    public static MyApp app;
    private DaoMaster.DevOpenHelper mHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initDb();
    }

    private void initDb() {
        mHelper = new DaoMaster.DevOpenHelper(this, "MyDb", null);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public static MyApp getInstance(){
        return app;
    }

    public DaoSession getDaoSession(){
        return mDaoSession;
    }
}
