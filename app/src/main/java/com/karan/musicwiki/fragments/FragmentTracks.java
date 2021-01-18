package com.karan.musicwiki.fragments;

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

import com.karan.musicwiki.App;
import com.karan.musicwiki.R;
import com.karan.musicwiki.adapter.TracksAdapter;
import com.karan.musicwiki.api.GenreApiInterface;
import com.karan.musicwiki.apidispatchers.GenreApiDispatcher;
import com.karan.musicwiki.database.entity.Track;
import com.karan.musicwiki.database.entity.TracksList;
import com.karan.musicwiki.utils.ApiCompletionHandlerInterface;
import com.karan.musicwiki.utils.Constant;
import com.karan.musicwiki.utils.RecyclerViewClickListener;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

public class FragmentTracks extends DaggerFragment implements RecyclerViewClickListener {

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
    private TracksList tracksList;
    private TracksAdapter tracksAdapter;
    private List<Track> listOfTrack;

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
        getTrack(Constant.SECRET_KEY, genreName);

    }

    public void getTrack(String key, String tagName) {
        GenreApiDispatcher
                .getGenreTracks(genreApiInterface, key, tagName, new ApiCompletionHandlerInterface<TracksList>() {
                    @Override
                    public void completed(TracksList resource) {
                        tracksList = resource;
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
        listOfTrack = tracksList.getTracks().getTrack();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        tracksAdapter = new TracksAdapter(getContext(), listOfTrack, this::recyclerViewListClicked);
        recyclerView.setAdapter(tracksAdapter);
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

    }
}