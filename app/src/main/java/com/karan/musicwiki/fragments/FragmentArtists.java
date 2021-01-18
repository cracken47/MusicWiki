package com.karan.musicwiki.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.karan.musicwiki.App;
import com.karan.musicwiki.R;
import com.karan.musicwiki.activities.DetailsActivity;
import com.karan.musicwiki.adapter.ArtistAdapter;
import com.karan.musicwiki.api.GenreApiInterface;
import com.karan.musicwiki.apidispatchers.GenreApiDispatcher;
import com.karan.musicwiki.database.entity.Artist1;
import com.karan.musicwiki.database.entity.ArtistList;
import com.karan.musicwiki.utils.ApiCompletionHandlerInterface;
import com.karan.musicwiki.utils.Constant;
import com.karan.musicwiki.utils.RecyclerViewClickListener;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

public class FragmentArtists extends DaggerFragment implements RecyclerViewClickListener {

    @BindView(R.id.album_rv)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    @Inject
    App app;
    @Inject
    Executor executor;
    @Inject
    GenreApiInterface genreApiInterface;


    private View myRoot;
    private String genreName;
    private ArtistList artistList;
    private ArtistAdapter artistAdapter;
    private List<Artist1> listOfArtists;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (myRoot == null) {
            myRoot = inflater.inflate(R.layout.fragment_album, container, false);
        }
        return myRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        genreName = getArguments().getString("genre");
        getArtist(Constant.SECRET_KEY, genreName);

    }

    public void getArtist(String key, String tagName) {
        GenreApiDispatcher
                .getGenreArtits(genreApiInterface, key, tagName, new ApiCompletionHandlerInterface<ArtistList>() {
                    @Override
                    public void completed(ArtistList resource) {
                        artistList = resource;
                        initView();

                    }

                    @Override
                    public void failed(int code) {
                        Toast.makeText(getContext(), getResources().getString(R.string.error_message), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failed(Throwable t) {
                        Toast.makeText(getContext(), getResources().getString(R.string.error_message), Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
    }

    public void initView() {
        listOfArtists = artistList.getTopartists().getArtist();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        artistAdapter = new ArtistAdapter(getContext(), listOfArtists, this::recyclerViewListClicked);
        recyclerView.setAdapter(artistAdapter);
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("genre", genreName);
        intent.putExtra("album", new Gson().toJson(listOfArtists.get(position)));
        intent.putExtra("code", "2");
        startActivity(intent);

    }
}
