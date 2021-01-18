package com.karan.musicwiki.di.module;

import androidx.lifecycle.ViewModelProvider;

import com.karan.musicwiki.viewmodels.FactoryViewModel;

import dagger.Binds;
import dagger.Module;


@Module
abstract public class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);


}
