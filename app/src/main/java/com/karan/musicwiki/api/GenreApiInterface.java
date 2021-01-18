package com.karan.musicwiki.api;

import com.karan.musicwiki.database.entity.AlbumList;
import com.karan.musicwiki.database.entity.ArtistList;
import com.karan.musicwiki.database.entity.Genre;
import com.karan.musicwiki.database.entity.GenreDetails;
import com.karan.musicwiki.database.entity.TracksList;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GenreApiInterface {

    @FormUrlEncoded
    @POST("/2.0/")
    Call<Genre> getGenre(@Field("api_key") String apiKey,
                         @Field("method") String method,
                         @Field("format") String format);

    @FormUrlEncoded
    @POST("/2.0/?method=tag.getinfo&format=json")
    Call<GenreDetails> getGenreDetails(@Field("api_key") String apiKey,
                                       @Field("tag") String tag);

    @FormUrlEncoded
    @POST("/2.0/?method=tag.gettopalbums&format=json")
    Call<AlbumList> getAlbums(@Field("api_key") String apiKey,
                              @Field("tag") String tag);

    @FormUrlEncoded
    @POST("/2.0/?method=tag.gettopartists&format=json")
    Call<ArtistList> getArtists(@Field("api_key") String apiKey,
                                @Field("tag") String tag);

    @FormUrlEncoded
    @POST("/2.0/?method=tag.gettoptracks&format=json")
    Call<TracksList> getTracks(@Field("api_key") String apiKey,
                               @Field("tag") String tag);


}
