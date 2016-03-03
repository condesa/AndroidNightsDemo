package com.androidnights.animerealmexample;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Condesa on 03/03/16.
 */
public class AnimeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initRealmConfiguration();
    }

    private void initRealmConfiguration() {
        RealmConfiguration realmConfiguration =
                new RealmConfiguration
                        .Builder(this)
                        .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
