package com.example.dqmusic.Models;

import io.realm.RealmList;
import io.realm.RealmObject;

public class souceModel extends RealmObject {
    private RealmList<albumModel> album;

    public RealmList<albumModel> getAlbum() {
        return album;
    }

    public void setAlbum(RealmList<albumModel> album) {
        this.album = album;
    }

    public RealmList<musicmodel> getHot() {
        return hot;
    }

    public void setHot(RealmList<musicmodel> hot) {
        this.hot = hot;
    }

    private RealmList<musicmodel> hot;
}
