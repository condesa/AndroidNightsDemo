package com.androidnights.animerealmexample.utils;

import com.androidnights.animerealmexample.realm.models.Anime;
import com.androidnights.animerealmexample.realm.models.Genre;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.realm.RealmObject;
/**
 * Created by Condesa on 03/03/16.
 */
public class Utility {

    public static Gson getGsonInstance(){
        return new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();
    }

    public static String getGenresInfo(final Anime anime) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Genre genre : anime.getGenres()){
            stringBuilder.append(genre.getName());
            stringBuilder.append(" | ");
        }
        return stringBuilder.toString();
    }

    public static boolean isEmptyString(String string){
        if(string == null){
            return true;
        }

        if(string.equals(null)){
            return true;
        }

        if(string.equals("null")){
            return true;
        }

        if(string.equals("") || string.equals(" ")){
            return true;
        }else{
            return false;
        }
    }
}
