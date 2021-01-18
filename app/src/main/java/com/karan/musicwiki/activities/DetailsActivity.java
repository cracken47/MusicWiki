package com.karan.musicwiki.activities;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.karan.musicwiki.R;
import com.karan.musicwiki.fragments.FragmentAlbumDetails;
import com.karan.musicwiki.fragments.FragmentArtistDetails;

public class DetailsActivity extends BaseActivity {

    private String genreName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        String code = getIntent().getExtras().getString("code");
        genreName = getIntent().getExtras().getString("genre");
        moveToDetailsFragment(Integer.parseInt(code));
    }

    public void moveToDetailsFragment(int code) {

        if (code == 1) {
            String album = getIntent().getExtras().getString("album");
            FragmentAlbumDetails fragmentAlbumDetails = new FragmentAlbumDetails();
            Bundle bundle = new Bundle();
            bundle.putString("album", album);
            bundle.putString("genre", genreName);
            fragmentAlbumDetails.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_home, fragmentAlbumDetails);
            fragmentTransaction.commit();

        } else {

            String artist = getIntent().getExtras().getString("artist");
            FragmentArtistDetails fragmentArtistDetails = new FragmentArtistDetails();
            Bundle bundle = new Bundle();
            bundle.putString("album", artist);
            bundle.putString("genre", genreName);
            fragmentArtistDetails.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_home, fragmentArtistDetails);
            fragmentTransaction.commit();
        }

    }
}