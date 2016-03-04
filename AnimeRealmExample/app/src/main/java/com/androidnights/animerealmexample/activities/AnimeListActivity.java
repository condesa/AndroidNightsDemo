package com.androidnights.animerealmexample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.androidnights.animerealmexample.R;
import com.androidnights.animerealmexample.adapter.AnimeAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class AnimeListActivity extends BaseActivity
        implements AnimeAdapter.AnimeListener{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(new AnimeAdapter(realmManager.getAllAnimes(), this));
        recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
    }

    @Override
    public void onLongClick(int animeId) {
        Log.i("TAG", "ID: " + animeId);
        realmManager.deleteAnimeById(animeId);
    }

    @OnClick(R.id.fab)
    public void addAnime(){
        startActivity(new Intent(this, AddAnimeActivity.class));
    }
}
