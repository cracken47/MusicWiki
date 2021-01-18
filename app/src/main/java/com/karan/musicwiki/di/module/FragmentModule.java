package com.karan.musicwiki.di.module;

import com.karan.musicwiki.fragments.FragmentAlbum;
import com.karan.musicwiki.fragments.FragmentAlbumDetails;
import com.karan.musicwiki.fragments.FragmentArtistDetails;
import com.karan.musicwiki.fragments.FragmentArtists;
import com.karan.musicwiki.fragments.FragmentTracks;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract FragmentAlbum fragmentAlbum();

    @ContributesAndroidInjector
    abstract FragmentArtists fragmentArtists();

    @ContributesAndroidInjector
    abstract FragmentTracks fragmentTracks();

    @ContributesAndroidInjector
    abstract FragmentAlbumDetails fragmentAlbumDetails();

    @ContributesAndroidInjector
    abstract FragmentArtistDetails fragmentArtistDetails();

}
