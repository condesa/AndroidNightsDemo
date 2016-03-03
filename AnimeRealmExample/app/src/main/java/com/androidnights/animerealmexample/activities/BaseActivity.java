package com.androidnights.animerealmexample.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.androidnights.animerealmexample.realm.RealmManager;

/**
 * Created by Condesa on 03/03/16.
 */
public class BaseActivity extends AppCompatActivity {

    protected RealmManager realmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realmManager = new RealmManager();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realmManager.closeRealm();
    }
}
