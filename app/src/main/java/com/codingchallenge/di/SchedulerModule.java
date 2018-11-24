package com.codingchallenge.di;

import android.support.annotation.NonNull;

import com.codingchallenge.helper.rx.AppSchedulerProvider;
import com.codingchallenge.helper.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
/**
 * Created by Anil Gudigar on 11/10/18.
 */
@Module
public class SchedulerModule {
    public SchedulerModule() {
    }

    @NonNull
    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }
}
