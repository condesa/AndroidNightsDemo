package com.androidnights.animerealmexample.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.widget.EditText;

import com.androidnights.animerealmexample.R;
import com.androidnights.animerealmexample.realm.RealmManager;
import com.androidnights.animerealmexample.utils.Utility;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

/**
 * Created by Condesa on 03/03/16.
 */
public class AddAnimeActivity extends BaseActivity
        implements RealmManager.OnTransactionCallback {

    public static final String TAG = AddAnimeActivity.class.getSimpleName();

    @Bind(R.id.field_title)
    EditText fieldTitle;
    @Bind(R.id.field_image_url)
    EditText fieldImageUrl;
    @Bind(R.id.wrapper_title)
    TextInputLayout wrapperTitle;
    @Bind(R.id.wrapper_image_url)
    TextInputLayout wrapperImageUrl;


    private RealmManager realmObject;

    @Override
    public int getResLayout() {
        return R.layout.activity_add_anime;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResLayout());
        ButterKnife.bind(this);

        realmObject = new RealmManager();
    }

    @OnClick(R.id.button_add_anime)
    public void addAnime(){
        if(validateTitle() && validateImageUrl()){
            Log.i(TAG, "Clicked!");
            realmObject.addAnime(this);
        }
    }

    @Override
    public void onRealmSuccess() {
        Log.i(TAG, "onRealmSuccess()");
        finish();
    }

    @Override
    public void onRealmError(Exception e) {
        Log.i(TAG, e.getMessage());
    }

    private boolean validateTitle(){
        if(Utility.isEmptyString(fieldTitle.getText().toString())){
            wrapperTitle.setErrorEnabled(true);
            wrapperTitle.setError(getString(R.string.error_empty_title));
            return false;
        }else{
            wrapperTitle.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateImageUrl(){
        if(Utility.isEmptyString(fieldImageUrl.getText().toString())){
            wrapperImageUrl.setErrorEnabled(true);
            wrapperImageUrl.setError(getString(R.string.error_empty_image_url));
            return false;
        }else{
            wrapperImageUrl.setErrorEnabled(false);
            return true;
        }
    }
}
