package com.umeng.soexample.ui.shop;

import android.content.Context;

import java.security.SecureRandom;

import io.realm.RealmConfiguration;

public class Realms {

    public static io.realm.Realm getRealm(Context context){
        byte[] bytes = new byte[1024];
        new SecureRandom().nextBytes(bytes);
        io.realm.Realm.init(context);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("db.realm")
                .schemaVersion(2)
                //.migration(migration)
                .deleteRealmIfMigrationNeeded()
                .build();
        return io.realm.Realm.getInstance(config);
    }

}
