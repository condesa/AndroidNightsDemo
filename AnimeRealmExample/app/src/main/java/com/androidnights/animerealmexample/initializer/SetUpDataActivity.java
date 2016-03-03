package com.androidnights.animerealmexample.initializer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.androidnights.animerealmexample.R;
import com.androidnights.animerealmexample.activities.AnimeListActivity;
import com.androidnights.animerealmexample.activities.BaseActivity;
import com.androidnights.animerealmexample.realm.RealmManager;
import com.androidnights.animerealmexample.realm.models.Anime;
import com.androidnights.animerealmexample.realm.models.Studio;
import com.androidnights.animerealmexample.utils.Utility;
import com.androidnights.animerealmexample.volley.VolleyManager;
import com.androidnights.animerealmexample.realm.models.Genre;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmList;

/**
 * Created by Condesa on 02/03/16.
 */
public class SetUpDataActivity extends BaseActivity implements VolleyManager.OnRequestListener,
    RealmManager.OnTransactionCallback{

    @Bind(R.id.label_studio_status)
    TextView labelStudioStatus;
    @Bind(R.id.label_genres_status)
    TextView labelGenresStatus;
    @Bind(R.id.label_animes_status)
    TextView labelAnimesStatus;
    private VolleyManager volleyManager;
    private static final String TAG = SetUpDataActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_data);
        ButterKnife.bind(this);

        volleyManager = VolleyManager.getInstance(this);
        volleyManager.setOnRequestListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //getGenresData();
        //getStudiosData();
        getAnimeData();
    }

    @Override
    public void onRequestSuccess(JSONArray responseArray) {
        //processGenresData(responseArray);
        //processStudiosData(responseArray);
        processAnimeData(responseArray);
    }

    @Override
    public void onRequestSuccess(JSONObject responseObject) {

    }

    @Override
    public void onRequestFail(Error error) {

    }

    private void getGenresData(){
        volleyManager.executeGetRequest("https://dl.dropboxusercontent.com/u/12316307/androideity/genres.json");
    }

    private void getStudiosData(){
        volleyManager.executeGetRequest("https://dl.dropboxusercontent.com/u/12316307/androideity/studios.json");
    }

    private void getAnimeData(){
        volleyManager.executeGetRequest("https://dl.dropboxusercontent.com/u/12316307/androideity/animes.json");
    }

    private void processStudiosData(JSONArray jsonObject){
        Type token = new TypeToken<RealmList<Studio>>(){}.getType();
        Gson gson = Utility.getGsonInstance();
        List<Studio> objects = gson.fromJson(jsonObject.toString(), token);
    }

    private void processAnimeData(JSONArray jsonObject){
        Type token = new TypeToken<RealmList<Anime>>(){}.getType();
        Gson gson = Utility.getGsonInstance();
        List<Anime> objects = gson.fromJson(jsonObject.toString(), token);
        realmManager.addAnimeListAsync(objects, this);
    }

    private void processGenresData(JSONArray jsonObject){
        Type token = new TypeToken<RealmList<Genre>>(){}.getType();
        Gson gson = Utility.getGsonInstance();
        List<Genre> objects = gson.fromJson(jsonObject.toString(), token);
    }

    @Override
    public void onRealmSuccess() {
        Log.i(TAG, "onRealmSuccess");
        Log.i(TAG, "Animes: " + realmManager.getAllAnimes().size());
        startActivity(new Intent(this, AnimeListActivity.class));
        finish();
    }

    @Override
    public void onRealmError(Exception e) {
        Log.i(TAG, "onRealmError " + e.getLocalizedMessage());
    }
}
