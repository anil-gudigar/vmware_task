package com.codingchallenge.di;

import android.app.Application;
import android.content.Context;

import com.codingchallenge.helper.injection.qualifier.ApplicationContext;
import com.codingchallenge.helper.injection.viewmodel.ViewModelModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
/**
 * Created by Anil Gudigar on 11/10/18.
 */
@Module(includes = ViewModelModule.class)
public class AppModule {

    public AppModule() {
    }

    @Provides
    @Singleton
    @ApplicationContext
    Context provideContext(Application application) {
        return application;
    }

}

