package com.androidnights.animerealmexample.activities;

import android.content.Intent;
import android.os.Bundle;

import com.androidnights.animerealmexample.R;
import com.androidnights.animerealmexample.initializer.SetUpDataActivity;

/**
 * Created by Condesa on 03/03/16.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkData();
    }

    private void checkData(){
        Intent intent = null;
        if(realmManager.getAllAnimes().size() == 0){
            intent = new Intent(this, SetUpDataActivity.class);
        }else{
            intent = new Intent(this, AnimeListActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
