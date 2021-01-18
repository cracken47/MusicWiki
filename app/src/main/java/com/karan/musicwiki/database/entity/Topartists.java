package com.karan.musicwiki.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Topartists {

    @SerializedName("artist")
    @Expose
    private List<Artist1> artist = null;
    @SerializedName("@attr")
    @Expose
    private Attr_ attr;

    public List<Artist1> getArtist() {
        return artist;
    }

    public void setArtist(List<Artist1> artist) {
        this.artist = artist;
    }

    public Attr_ getAttr() {
        return attr;
    }

    public void setAttr(Attr_ attr) {
        this.attr = attr;
    }
}
