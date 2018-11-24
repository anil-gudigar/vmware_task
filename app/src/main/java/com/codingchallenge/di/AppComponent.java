package com.codingchallenge.di;

import android.app.Application;
import android.support.annotation.NonNull;

import com.codingchallenge.app.CodingChallegeApp;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
/**
 * Created by Anil Gudigar on 11/10/18.
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
                AndroidSupportInjectionModule.class,
                AndroidInjectionModule.class,
                ActivityBuilderModule.class,
                DataModule.class,
                SchedulerModule.class
        }
)
public interface AppComponent {
    void inject(CodingChallegeApp application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        @NonNull
        AppComponent build();
    }
}

