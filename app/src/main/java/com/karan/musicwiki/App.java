package com.karan.musicwiki;

import android.app.Activity;
import android.app.Application;

import androidx.fragment.app.Fragment;



import com.karan.musicwiki.di.component.AppComponent;
import com.karan.musicwiki.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;



public class App extends Application implements HasActivityInjector, HasSupportFragmentInjector {

    protected AppComponent appComponent;
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;


    @Override
    public void onCreate() {
        super.onCreate();
        this.initDagger();

    }


    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    private void initDagger() {
        DaggerAppComponent.builder().application(this).build().inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public void setAppComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }

}
