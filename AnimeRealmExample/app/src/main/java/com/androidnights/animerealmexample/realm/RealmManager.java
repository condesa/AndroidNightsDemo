package com.androidnights.animerealmexample.realm;

import com.androidnights.animerealmexample.realm.models.Anime;
import com.androidnights.animerealmexample.realm.models.Genre;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Condesa on 02/03/16.
 */
public class RealmManager {

    private final Realm mRealm;

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

    public RealmResults<Genre> getAllGenres(){
        return mRealm.allObjects(Genre.class);
    }

}
