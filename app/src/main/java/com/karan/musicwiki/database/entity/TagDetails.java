package com.karan.musicwiki.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TagDetails {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("reach")
    @Expose
    private Integer reach;
    @SerializedName("wiki")
    @Expose
    private Wiki wiki;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getReach() {
        return reach;
    }

    public void setReach(Integer reach) {
        this.reach = reach;
    }

    public Wiki getWiki() {
        return wiki;
    }

    public void setWiki(Wiki wiki) {
        this.wiki = wiki;
    }
}
