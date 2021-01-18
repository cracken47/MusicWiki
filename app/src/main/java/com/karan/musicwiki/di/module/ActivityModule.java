package com.karan.musicwiki.di.module;


import com.karan.musicwiki.activities.BaseActivity;
import com.karan.musicwiki.activities.DetailsActivity;
import com.karan.musicwiki.activities.GenreDetailsActivity;
import com.karan.musicwiki.activities.MainActivity;
import com.karan.musicwiki.database.entity.GenreDetails;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract MainActivity  mainActivity();

    @ContributesAndroidInjector()
    abstract BaseActivity baseActivity();

    @ContributesAndroidInjector
    abstract GenreDetailsActivity genreDetails();

    @ContributesAndroidInjector
    abstract DetailsActivity detailsActivity();



}
