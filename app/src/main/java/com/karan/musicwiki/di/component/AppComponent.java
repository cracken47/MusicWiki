package com.karan.musicwiki.di.component;


import android.app.Application;


import com.karan.musicwiki.App;
import com.karan.musicwiki.di.module.ActivityModule;
import com.karan.musicwiki.di.module.AppModule;
import com.karan.musicwiki.di.module.FragmentModule;
import com.karan.musicwiki.di.module.NetworkModule;
import com.karan.musicwiki.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, ActivityModule.class, FragmentModule.class, NetworkModule.class, ViewModelModule.class})
public interface AppComponent {

    void inject(App app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}

