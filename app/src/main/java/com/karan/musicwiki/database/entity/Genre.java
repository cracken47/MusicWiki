package com.karan.musicwiki.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Genre {

    @PrimaryKey(autoGenerate = true)
    int id;

    @SerializedName("tags")
    @Expose
    private Tags tags;

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

}
