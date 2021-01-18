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
import com.karan.musicwiki.activities.GenreDetailsActivity;
import com.karan.musicwiki.adapter.AlbumAdapter;
import com.karan.musicwiki.api.GenreApiInterface;
import com.karan.musicwiki.apidispatchers.GenreApiDispatcher;
import com.karan.musicwiki.database.entity.Album;
import com.karan.musicwiki.database.entity.AlbumList;
import com.karan.musicwiki.utils.ApiCompletionHandlerInterface;
import com.karan.musicwiki.utils.Constant;
import com.karan.musicwiki.utils.RecyclerViewClickListener;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

public class FragmentAlbum extends DaggerFragment implements RecyclerViewClickListener {

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
    private AlbumList albumList;
    private AlbumAdapter albumAdapter;
    private List<Album> listOfAlbums;

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
        getAlbum(Constant.SECRET_KEY,genreName);

    }

    public void getAlbum(String key, String tagName) {
        GenreApiDispatcher
                .getGenreAlbums(genreApiInterface, key, tagName, new ApiCompletionHandlerInterface<AlbumList>() {
                    @Override
                    public void completed(AlbumList resource) {
                        albumList = resource;
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
        listOfAlbums = albumList.getAlbums().getAlbum();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        albumAdapter = new AlbumAdapter(getContext(), listOfAlbums, this::recyclerViewListClicked);
        recyclerView.setAdapter(albumAdapter);
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("genre", genreName);
        intent.putExtra("album", new Gson().toJson(listOfAlbums.get(position)));
        intent.putExtra("code", "1");
        startActivity(intent);
    }
}
