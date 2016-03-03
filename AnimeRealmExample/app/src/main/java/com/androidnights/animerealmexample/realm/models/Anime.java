package com.androidnights.animerealmexample.realm.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Condesa on 02/03/16.
 */
public class Anime extends RealmObject {

    @PrimaryKey
    private int id;
    private String image;
    private String title;
    private int episodes;

    private RealmList<Genre> genres;
    private Studio studio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public RealmList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(RealmList<Genre> genres) {
        this.genres = genres;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }
}
