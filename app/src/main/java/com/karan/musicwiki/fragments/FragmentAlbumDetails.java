package com.karan.musicwiki.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.karan.musicwiki.App;
import com.karan.musicwiki.R;
import com.karan.musicwiki.api.GenreApiInterface;
import com.karan.musicwiki.apidispatchers.GenreApiDispatcher;
import com.karan.musicwiki.database.entity.Album;
import com.karan.musicwiki.database.entity.GenreDetails;
import com.karan.musicwiki.utils.ApiCompletionHandlerInterface;
import com.karan.musicwiki.utils.Constant;
import com.karan.musicwiki.utils.RecyclerViewClickListener;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

public class FragmentAlbumDetails extends DaggerFragment implements RecyclerViewClickListener {

    @BindView(R.id.imageview)
    ImageView imageView;
    @BindView(R.id.genre_tv)
    TextView genreNameTv;
    @BindView(R.id.title_tv)
    TextView title;
    @BindView(R.id.subtitle_tv)
    TextView info;


    @Inject
    App app;
    @Inject
    Executor executor;
    @Inject
    GenreApiInterface genreApiInterface;


    private View myRoot;
    private String genreName;
    private GenreDetails genreDetails;

    private Album album;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (myRoot == null) {
            myRoot = inflater.inflate(R.layout.fragment_details, container, false);
        }
        return myRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        genreName = getArguments().getString("genre");
        String albumString = getArguments().getString("album");
        album = new Gson().fromJson(albumString, Album.class);
        getGenreDetails(genreName, Constant.SECRET_KEY);
    }

    public void getGenreDetails(String tagName, String key) {
        GenreApiDispatcher
                .getGenreDetails(genreApiInterface, key, tagName, new ApiCompletionHandlerInterface<GenreDetails>() {
                    @Override
                    public void completed(GenreDetails resource) {
                        genreDetails = resource;
                        initView();
                    }

                    @Override
                    public void failed(int code) {
                        Toast.makeText(getActivity(), getResources().getString(R.string.error_message), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failed(Throwable t) {
                        Toast.makeText(getActivity(), getResources().getString(R.string.error_message), Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
    }

    public void initView() {
        Glide.with(getActivity()).load(album.getImage().get(0).getText()).into(imageView);
        genreNameTv.setText(genreName);
        title.setText(album.getArtist().getName());
        info.setText(genreDetails.getTag().getWiki().getSummary());
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

    }
}
