package com.androidnights.animerealmexample.activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import com.androidnights.animerealmexample.R;
import com.androidnights.animerealmexample.realm.RealmManager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Condesa on 03/03/16.
 */
public class BaseActivity extends AppCompatActivity {

    @Bind(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    protected RealmManager realmManager;

    public int getResLayout(){
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResLayout());
        ButterKnife.bind(this);
        realmManager = new RealmManager();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realmManager.closeRealm();
    }

    protected void showSnackbar(String message){
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    protected void showSnackbar(int message){
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(FragmentActivity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
}
