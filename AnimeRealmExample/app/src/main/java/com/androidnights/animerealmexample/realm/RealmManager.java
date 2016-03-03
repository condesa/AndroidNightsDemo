package com.androidnights.animerealmexample.realm;

import android.content.Context;

import com.androidnights.animerealmexample.realm.models.Anime;
import com.androidnights.animerealmexample.realm.models.Genre;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Condesa on 02/03/16.
 */
public class RealmManager {

    private Realm mRealm;

    public static void initRealmConfiguration(Context context) {
        RealmConfiguration realmConfiguration =
                new RealmConfiguration
                        .Builder(context)
                        .name("anime_schema_1")
                        .schemaVersion(1)
                        .deleteRealmIfMigrationNeeded()
                        .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public RealmManager() {
        mRealm = Realm.getDefaultInstance();
    }

    public void closeRealm() {
        mRealm.close();
    }

    public interface OnTransactionCallback {
        void onRealmSuccess();
        void onRealmError(final Exception e);
    }

    public void addAnime(final OnTransactionCallback onTransactionCallback){
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(final Realm realm) {
                Anime anime = realm.createObject(Anime.class);
                anime.setId(getAllAnimes().size() + 1);
                anime.setTitle("Kannazuki no Miko");
                anime.setImage("http://cdn.myanimelist.net/images/anime/7/20247l.jpg");
                anime.setEpisodes(12);
                RealmList<Genre> genres = new RealmList<Genre>();
                //genres.add(createOrGetGenre("Mecha", realm));
                anime.setGenres(genres);
            }
        }, new Realm.Transaction.Callback() {

            @Override
            public void onSuccess() {
                if (onTransactionCallback != null) {
                    onTransactionCallback.onRealmSuccess();
                }
            }

            @Override
            public void onError(final Exception e) {
                if (onTransactionCallback != null) {
                    onTransactionCallback.onRealmError(e);
                }
            }
        });
    }

    public void addAnimeListAsync(final List<Anime> animes,
                             final OnTransactionCallback onTransactionCallback) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(final Realm realm) {
                realm.copyToRealmOrUpdate(animes);
            }
        }, new Realm.Transaction.Callback() {

            @Override
            public void onSuccess() {
                if (onTransactionCallback != null) {
                    onTransactionCallback.onRealmSuccess();
                }
            }

            @Override
            public void onError(final Exception e) {
                if (onTransactionCallback != null) {
                    onTransactionCallback.onRealmError(e);
                }
            }
        });
    }

    public void deleteAnimeById(int animeId){
        mRealm.beginTransaction();
        RealmResults<Anime> animes = mRealm.where(Anime.class).equalTo("id", animeId).findAll();
        if(!animes.isEmpty()) {
            for(int i = animes.size() - 1; i >= 0; i--) {
                animes.get(i).removeFromRealm();
            }
        }
        mRealm.commitTransaction();
    }

    public RealmResults<Anime> getAllAnimes() {
        return mRealm.allObjects(Anime.class);
    }

    private Genre createOrGetGenre(final String genreName, final Realm realm) {
        Genre foundGenre = getGenre(genreName, realm);
        if(foundGenre == null) {
            foundGenre = addGenre(genreName, realm);
        }
        return foundGenre;
    }

    private Genre addGenre(final String genreName, final Realm realm) {
        Genre genre = realm.createObject(Genre.class);
        genre.setId(realm.allObjects(Genre.class).size());
        genre.setName(genreName);
        return genre;
    }

    private Genre getGenre(final String genreName, final Realm realm) {
        return realm.where(Genre.class).equalTo("name", genreName).findFirst();
    }

}
