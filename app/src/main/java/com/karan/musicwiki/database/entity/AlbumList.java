package com.karan.musicwiki.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumList {
    @SerializedName("albums")
    @Expose
    private Albums albums;

    public Albums getAlbums() {
        return albums;
    }

    public void setAlbums(Albums albums) {
        this.albums = albums;
    }


}
