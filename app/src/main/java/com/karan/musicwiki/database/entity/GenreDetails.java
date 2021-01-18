package com.karan.musicwiki.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenreDetails {

    @SerializedName("tag")
    @Expose
    private TagDetails tag;

    public TagDetails getTag() {
        return tag;
    }

    public void setTag(TagDetails tag) {
        this.tag = tag;
    }
}
