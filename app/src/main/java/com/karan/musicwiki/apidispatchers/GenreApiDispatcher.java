package com.karan.musicwiki.apidispatchers;


import com.karan.musicwiki.adapter.TracksAdapter;
import com.karan.musicwiki.api.GenreApiInterface;
import com.karan.musicwiki.database.entity.AlbumList;
import com.karan.musicwiki.database.entity.ArtistList;
import com.karan.musicwiki.database.entity.Genre;
import com.karan.musicwiki.database.entity.GenreDetails;
import com.karan.musicwiki.database.entity.TracksList;
import com.karan.musicwiki.utils.ApiCompletionHandlerInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreApiDispatcher {

    public static void getGenre(GenreApiInterface genreApiInterface, String key,String method,String format,
                                ApiCompletionHandlerInterface<Genre> completionHandler) {
        genreApiInterface.getGenre(key,method,format)
                .enqueue(new Callback<Genre>() {
                    @Override
                    public void onResponse(Call<Genre> call,
                                           Response<Genre> response) {
                        completionHandler.completed(response.body());

                    }

                    @Override
                    public void onFailure(Call<Genre> call, Throwable t) {
                        completionHandler.failed(t);
                    }
                });
    }

    public static void getGenreDetails(GenreApiInterface genreApiInterface, String key, String tag,
                                       ApiCompletionHandlerInterface<GenreDetails> completionHandler) {
        genreApiInterface.getGenreDetails(key, tag)
                .enqueue(new Callback<GenreDetails>() {
                    @Override
                    public void onResponse(Call<GenreDetails> call,
                                           Response<GenreDetails> response) {

                            completionHandler.completed(response.body());

                    }

                    @Override
                    public void onFailure(Call<GenreDetails> call, Throwable t) {
                        completionHandler.failed(t);
                    }
                });
    }

    public static void getGenreAlbums(GenreApiInterface genreApiInterface, String key, String tag,
                                      ApiCompletionHandlerInterface<AlbumList> completionHandler) {
        genreApiInterface.getAlbums(key, tag)
                .enqueue(new Callback<AlbumList>() {
                    @Override
                    public void onResponse(Call<AlbumList> call,
                                           Response<AlbumList> response) {

                            completionHandler.completed(response.body());

                    }

                    @Override
                    public void onFailure(Call<AlbumList> call, Throwable t) {
                        completionHandler.failed(t);
                    }
                });
    }

    public static void getGenreArtits(GenreApiInterface genreApiInterface, String key, String tag,
                                      ApiCompletionHandlerInterface<ArtistList> completionHandler) {
        genreApiInterface.getArtists(key, tag)
                .enqueue(new Callback<ArtistList>() {
                    @Override
                    public void onResponse(Call<ArtistList> call,
                                           Response<ArtistList> response) {

                            completionHandler.completed(response.body());
                    }

                    @Override
                    public void onFailure(Call<ArtistList> call, Throwable t) {
                        completionHandler.failed(t);
                    }
                });
    }


    public static void getGenreTracks(GenreApiInterface genreApiInterface, String key, String tag,
                                      ApiCompletionHandlerInterface<TracksList> completionHandler) {
        genreApiInterface.getTracks(key, tag)
                .enqueue(new Callback<TracksList>() {
                    @Override
                    public void onResponse(Call<TracksList> call,
                                           Response<TracksList> response) {

                        completionHandler.completed(response.body());
                    }

                    @Override
                    public void onFailure(Call<TracksList> call, Throwable t) {
                        completionHandler.failed(t);
                    }
                });
    }

}
